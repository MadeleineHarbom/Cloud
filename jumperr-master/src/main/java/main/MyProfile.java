package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.Controller;
import model.User;

@WebServlet("/MyProfile")

// @MultipartConfig gør det muligt at uploade filer (i det her tilfælde er det
// til at uploade billeder)
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024
        * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class MyProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {

            response.sendRedirect("/");

        } else {

            // hvis det er Admin
            if (user.getAdmin() == 1) {

                request.getRequestDispatcher("/WEB-INF/jsp/admin/myProfile_adminPage.jsp").forward(request, response);

            } else {

                request.getRequestDispatcher("/WEB-INF/jsp/loggedIn/myProfile.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        Part file = request.getPart("file");
        String imageName = Controller.getFileName(file);

        // billede-navnet skal være unikt - derfor bliver billedets navn ændret til
        // brugerens username som også er unik
        String newImageName = user.getUsername() + Controller.getFileExtension(imageName);

        user.setProfilePicture(newImageName);
        Controller.uploadImageToGoogleStorage(newImageName, request, response);

        response.sendRedirect("/MyProfile");
    }
}