package servlets;

import DB.dataSets.UsersDataSet;
import DB.executor.DBException;
import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private final AccountService accountService;

    public UsersServlet(AccountService accountService) {
        this.accountService = accountService;
    }


    //get public user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        UsersDataSet usersDataSet = new UsersDataSet(login, pass, email);
        response.getWriter().println(usersDataSet);
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
            usersDataSet = new UsersDataSet(login, pass, email);
            try {
                accountService.addNewUser(usersDataSet);
            } catch (DBException e) {
                throw new RuntimeException(e);
            }
            response.getWriter().println("Done signuped");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    //change profile
//    public void doPut(HttpServletRequest request,
//                      HttpServletResponse response) throws ServletException, IOException {
//        String login = request.getParameter("login");
//        String pass = request.getParameter("pass");
//        String email = request.getParameter("email");
//        UserProfile userProfile = accountService.getUserBySessionId(request.getRequestedSessionId());
//        accountService.deleteUser(userProfile);
//        accountService.addNewUser(new UserProfile(login, pass, email));
//        response.getWriter().println("Done changed");
//        response.setStatus(HttpServletResponse.SC_OK);
//    }
//
//    //unregister
//    public void doDelete(HttpServletRequest request,
//                         HttpServletResponse response) throws ServletException, IOException {
//        String login = request.getParameter("login");
//        String pass = request.getParameter("pass");
//        String email = request.getParameter("email");
//        UserProfile userProfile = new UserProfile(login, pass, email);
//        accountService.deleteUser(userProfile);
//        response.getWriter().println("Done deleted");
//        response.setStatus(HttpServletResponse.SC_OK);
//    }
}
