package accounts;

import DB.dataSets.UsersDataSet;
import DB.executor.DBException;
import java.util.*;

public class LocalAccountService implements AccountService {
    private final Map<String, UsersDataSet> loginToProfile;
    private final Map<String, UsersDataSet> sessionIdToProfile;

    public LocalAccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    @Override
    public void addNewUser(UsersDataSet usersDataSet) throws DBException {
        loginToProfile.put(usersDataSet.getLogin(), usersDataSet);
    }

    @Override
    public void deleteUser(UsersDataSet usersDataSet) throws DBException {
        loginToProfile.remove(usersDataSet.getLogin());
    }

    @Override
    public UsersDataSet getUserByLogin(String login) throws DBException {
        return loginToProfile.get(login);
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
