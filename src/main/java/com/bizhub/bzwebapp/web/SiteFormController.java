package com.bizhub.bzwebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bizhub.bzwebapp.dao.SiteDao;
import com.bizhub.bzwebapp.domain.Site;

@Controller
@RequestMapping("/site_form")
@SessionAttributes("site")
public class SiteFormController {
    private final SiteDao dao;

    @Autowired
    public SiteFormController(SiteDao dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setupForm(
            @RequestParam(value = "id", required = false) Long id) {
        Site site = id == null ? new Site() : this.dao.getById(id);
        return new ModelAndView("siteForm").addObject(site);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(
            @ModelAttribute("site") Site site,
            BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
            this.dao.save(site);
            status.setComplete();
            return "redirect:site?id=" + site.getId();
        }
        return "siteForm";
    }
}