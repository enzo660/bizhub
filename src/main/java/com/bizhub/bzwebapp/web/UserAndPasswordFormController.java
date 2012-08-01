package com.bizhub.bzwebapp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bizhub.bzwebapp.dao.UserDao;
import com.bizhub.bzwebapp.domain.User;
import com.bizhub.bzwebapp.service.UserContextService;
import com.bizhub.bzwebapp.service.UserStoreService;

@Controller
@RequestMapping("/user_form")
@SessionAttributes("userAndPassword")
public class UserAndPasswordFormController {
    private final UserDao dao;
    private final UserStoreService userStoreService;
    private final UserContextService userContextService;
    
    @Autowired
    public UserAndPasswordFormController(UserDao dao,  UserStoreService userStoreService, UserContextService userContextService) {
        this.dao = dao;
        this.userStoreService = userStoreService;
        this.userContextService = userContextService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setupForm(
            @RequestParam(value = "id", required = false) Long id) {
    	
    	User loggedInUser = this.userContextService.getUserFromContext();
    	User userToModify = null;
    	
    	//If the user is not an admin, they can edit their own profile
    	if(!loggedInUser.isAdmin()){
    		userToModify = loggedInUser;
    	}else{
    		//If the user is admin and if the id is provided, edit the user corresponding to that id
    		//otherwise, edit the logged in user (admin)
    		userToModify = id == null ? loggedInUser : this.dao.getById(id);
    	}

        return new ModelAndView("userForm")
                .addObject(new UserAndPassword(userToModify));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(
            @ModelAttribute("userAndPassword") @Valid UserAndPassword userAndPassword,
            BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
            try {
                User user = userAndPassword.getUser();
                String password = userAndPassword.isPasswordVerified() ? userAndPassword
                        .getPassword() : null;
                this.userStoreService.store(user, password);
                status.setComplete();
                
                User loggedInUser = this.userContextService.getUserFromContext();
                if(!loggedInUser.isAdmin()){
                	return "redirect:user";
                }else{
                	return "redirect:user?id=" + user.getId();
                }
                
            } catch (DataIntegrityViolationException e) {
                result.rejectValue("user.email", "DuplicateEmailFailure");
            } catch (ConcurrencyFailureException e) {
                result.reject("ConcurrentModificatonFailure",
                        new String[] { "user" }, null);
            }
        }
        return "userForm";
    }
}