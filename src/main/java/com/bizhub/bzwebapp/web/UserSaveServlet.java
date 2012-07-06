package com.bizhub.bzwebapp.web;

import static com.bizhub.bzwebapp.Util.isEmpty;
import static com.bizhub.bzwebapp.Util.join;
import static com.bizhub.bzwebapp.Util.md5Digest;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;

import com.bizhub.bzwebapp.domain.User;

public class UserSaveServlet extends AbstractDaoAccessServlet {

	private static final long serialVersionUID = -8403383569839821791L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<String> errors = new LinkedList<String>();
		Object entity = req.getSession().getAttribute(
				WebConstants.CURRENT_EDIT_ENTITY);
		User user = entity instanceof User ? (User) entity : null;
		if (user == null) {
			if (super.logger.isTraceEnabled()) {
				super.logger.trace("Initializing a new user");
			}
			user = new User();
		} else {
			if (super.logger.isTraceEnabled()) {
				super.logger.trace("Editing current user: " + user);
			}
		}
		req.setAttribute("user", user);

		String id = req.getParameter("id");
		if (!isEmpty(id)) {
			user.setId(new Long(id));
		}

		String firstName = req.getParameter("firstName");
		if (isEmpty(firstName)) {
			errors.add("First name cannot be blank");
		} else {
			user.setFirstName(firstName);
		}
		String lastName = req.getParameter("lastName");
		if (isEmpty(lastName)) {
			errors.add("Last name cannot be blank");
		} else {
			user.setLastName(lastName);
		}
		user.setBusiness(req.getParameter("business"));
		user.setTitle(req.getParameter("title"));

		String email = req.getParameter("email");
		if (isEmpty(email)) {
			errors.add("Email cannot be blank");
		} else {
			user.setEmail(email);
		}

		String password = req.getParameter("password");
		String passwordVerification = req.getParameter("passwordVerification");
		boolean passwordIsSet = !isEmpty(password);
		boolean passwordVerificationIsSet = !isEmpty(passwordVerification);
		if (!passwordIsSet) {
			if (passwordVerificationIsSet || !user.isIdSet()) {
				errors.add("Password cannot be blank");
			}
		} else if (!passwordVerificationIsSet) {
			if (passwordIsSet) {
				errors.add("Password verification cannot be blank");
			}
		} else if (!password.equals(passwordVerification)) {
			errors.add("Passwords do not match.");
		} else {
			user.setPasswordDigest(md5Digest(password));
		}

		if (errors.isEmpty()) {
			if (super.logger.isDebugEnabled()) {
				super.logger.debug("Saving user " + user);
			}
			try {
				super.getDaoRepository().getUserDao().save(user);
				resp.sendRedirect(resp.encodeRedirectURL("user?id="
						+ user.getId()));
			} catch (DataIntegrityViolationException e) {
				req.setAttribute("error", "Duplicate email: " + e.getMessage());
				req.getRequestDispatcher("/userForm.jsp").forward(req, resp);
			}
		} else {
			if (super.logger.isDebugEnabled()) {
				super.logger.debug("Not saving user. Errors are present");
			}
			req.setAttribute("error", join(errors, "<br/>"));
			req.getRequestDispatcher("/userForm.jsp").forward(req, resp);
		}
	}
}
