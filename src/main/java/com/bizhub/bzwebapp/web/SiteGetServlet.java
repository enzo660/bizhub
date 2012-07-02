package com.bizhub.bzwebapp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bizhub.bzwebapp.domain.Site;

public class SiteGetServlet extends AbstractDaoAccessServlet {

    private static final long serialVersionUID = -8439064187144571214L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Site site = null;
        String key = null;
        if ((key = req.getParameter("id")) != null) {
            site = super.getDaoRepository().getSiteDao().getById(
                    new Long(key));
        } else if ((key = req.getParameter("name")) != null) {
            site = super.getDaoRepository().getSiteDao().getByName(key);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Specify either 'id' or 'name' request parameter");
            return;
        }
        if (site == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "No such site: " + key);
        } else {
            req.setAttribute("site", site);
            String view = req.getRequestURI().contains("edit") ? "/siteForm.jsp"
                    : "/siteView.jsp";
            req.getRequestDispatcher(view).forward(req, resp);
        }
    }
}
