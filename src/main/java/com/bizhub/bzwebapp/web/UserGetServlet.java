package com.bizhub.bzwebapp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bizhub.bzwebapp.domain.User;

public class UserGetServlet extends AbstractDaoAccessServlet {

    private static final long serialVersionUID = -8439064187144571214L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = null;
        String key = null;
        if ((key = req.getParameter("id")) != null) {
            user = super.getDaoRepository().getUserDao().getById(new Long(key));
        } else if ((key = req.getParameter("email")) != null) {
            user = super.getDaoRepository().getUserDao().getByEmail(key);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Specify either 'id' or 'email' request parameter");
            return;
        }
        if (user == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No such user: "
                    + key);
        } else {
            req.setAttribute("user", user);
            String view = "/userView.jsp";
			if (req.getRequestURI().contains("edit")) {
				view = "/userForm.jsp";
				req.getSession().setAttribute(WebConstants.CURRENT_EDIT_ENTITY,user);
			}
            req.getRequestDispatcher(view).forward(req, resp);
        }
    }
}
