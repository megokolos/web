package servlets;

import DB.dataSets.UsersDataSet;
import DB.executor.DBException;
import accounts.AccountService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class SignUpServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }


    //get public user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(returnUserByParams(request));
    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        UsersDataSet usersDataSet;
        if (login == null || pass == null || email == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            usersDataSet = returnUserByParams(request);
            try {
                accountService.addNewUser(usersDataSet);
            } catch (DBException e) {
                throw new RuntimeException(e);
            }
            response.getWriter().println("Done signuped");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    //    change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        UsersDataSet usersDataSet = null;
        try {
            usersDataSet = accountService.getUserBySessionId(request.getRequestedSessionId());
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        try {
            accountService.deleteUser(usersDataSet);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        try {
            accountService.addNewUser(returnUserByParams(request));
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().println("Done changed");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    //unregister
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        UsersDataSet usersDataSet = returnUserByParams(request);
        try {
            accountService.deleteUser(usersDataSet);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().println("Done deleted");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private UsersDataSet returnUserByParams(HttpServletRequest request) {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        return new UsersDataSet(login, pass, email);
    }
}
