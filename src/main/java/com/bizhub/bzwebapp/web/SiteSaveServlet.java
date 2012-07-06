package com.bizhub.bzwebapp.web;

import static com.bizhub.bzwebapp.Util.isEmpty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;

import com.bizhub.bzwebapp.domain.Site;

public class SiteSaveServlet extends AbstractDaoAccessServlet {

    private static final long serialVersionUID = -8403383569839821791L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Site site = new Site();
        req.setAttribute("site", site);
        String id = req.getParameter("id");
        if (!isEmpty(id)) {
            site.setId(new Long(id));
        }
        site.setDescription(req.getParameter("description"));
        String name = req.getParameter("name");
        if (isEmpty(name)) {
            if (super.logger.isDebugEnabled()) {
                super.logger.debug("Not saving site. Name is blank");
            }
            req.setAttribute("error", "Name cannot be blank");
            req.getRequestDispatcher("/siteForm.jsp").forward(req, resp);
        } else {
            site.setName(name);
            if (super.logger.isDebugEnabled()) {
                super.logger.debug("Saving " + site);
            }
            try {
                super.getDaoRepository().getSiteDao().save(site);

                req.getRequestDispatcher("/siteView.jsp").forward(req, resp);
            } catch (DataIntegrityViolationException e) {
                req.setAttribute("error", "Duplicate name: " + e.getMessage());
                req.getRequestDispatcher("/siteForm.jsp").forward(req, resp);
            }
        }
    }

}
