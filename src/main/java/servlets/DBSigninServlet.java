package servlets;

import DB.dao.UsersDAO;
import DB.dataSets.UsersDataSet;
import DB.executor.DBException;
import DB.executor.DBService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DBSigninServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private final DBService service;


    public DBSigninServlet(DBService service) {
        this.service = service;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        UsersDataSet usersDataSet;
        try {
            usersDataSet = service.getUser(login);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        if (usersDataSet == null || !usersDataSet.getPassword().equals(pass)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        response.getWriter().println("Authorized");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
