package com.bizhub.bzwebapp.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bizhub.bzwebapp.domain.Site;

public class SiteListServlet extends AbstractDaoAccessServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		
		List<Site> siteList = super.getDaoRepository().getSiteDao().getAll();
       
        req.setAttribute("siteList", siteList);
        req.getRequestDispatcher("siteList.jsp").forward(req, resp);
    }
}
