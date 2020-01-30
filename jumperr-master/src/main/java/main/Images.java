package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;

@WebServlet(urlPatterns = { "/images", "/images/*" })
public class Images extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("/");

        } else {

            String pathInfo = request.getPathInfo();
            String[] pathArr = null;
            String defaultImage = "avatar.png";

            if (pathInfo != null) {
                pathArr = pathInfo.split("/");
            }

            // hvis man er inde på /images URL vises avatar-billedet
            if (pathInfo == null || pathInfo.equals("/")) {
                Controller.downloadImageFromGoogleStorage(defaultImage, response);

                // hvis man er inde på /images/<IMAGENAME> URL vises det specifikke billede
            } else {
                String imageName = pathArr[1];
                Controller.downloadImageFromGoogleStorage(imageName, response);
            }
        }
    }
}
