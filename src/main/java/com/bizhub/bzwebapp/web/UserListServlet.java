package com.bizhub.bzwebapp.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bizhub.bzwebapp.domain.User;

public class UserListServlet extends AbstractDaoAccessServlet {

    private static final long serialVersionUID = -3888240037022980476L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> userList = super.getDaoRepository().getUserDao().getAll();
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("userList.jsp").forward(req, resp);
    }
}
