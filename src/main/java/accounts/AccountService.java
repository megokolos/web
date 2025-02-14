package accounts;

import DB.dao.UsersDAO;
import DB.dataSets.UsersDataSet;
import DB.executor.DBException;
import DB.executor.DBService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class AccountService {
    private Map<String, UsersDataSet> loginToProfile;
    private Map<String, UsersDataSet> sessionIdToProfile;
    private DBService dbService;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public AccountService(DBService dbService) {
        this.dbService = dbService;
    }

    public void addNewUser(UsersDataSet usersDataSet) throws DBException {
        dbService.addUser(usersDataSet);
    }

    public void addNewUserMap(UsersDataSet UsersDataSet) {
        loginToProfile.put(UsersDataSet.getLogin(), UsersDataSet);
    }

    public void deleteUser(UsersDataSet usersDataSet) {
        loginToProfile.remove(usersDataSet.getLogin());
    }

    public UsersDataSet getUserById(String login) throws DBException {
        return dbService.getUser(login);
    }

    public UsersDataSet getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UsersDataSet usersDataSet) {
        sessionIdToProfile.put(sessionId, usersDataSet);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
