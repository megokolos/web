package accounts;

import DB.dataSets.UsersDataSet;
import DB.executor.DBException;
import DB.executor.DBService;

public class DatabaseAccountService implements AccountService {

    private final DBService dbService;

    public DatabaseAccountService() {
        dbService = new DBService();
        dbService.printConnectInfo();
    }

    @Override
    public void addNewUser(UsersDataSet usersDataSet) throws DBException {
        dbService.addUser(usersDataSet);
    }

    @Override
    public void deleteUser(UsersDataSet usersDataSet) throws DBException {
        dbService.deleteUser(usersDataSet);
    }

    @Override
    public UsersDataSet getUserByLogin(String login) throws DBException {
        return dbService.getUser(login);
    }

    @Override
    public UsersDataSet getUserBySessionId(String sessionId) throws DBException {
        return dbService.getUserBySessionId(sessionId);
    }

    @Override
    public void addSession(String sessionId, UsersDataSet usersDataSet) throws DBException {
        dbService.addSession(sessionId, usersDataSet);
    }

    @Override
    public void deleteSession(String sessionID) throws DBException {
        dbService.deleteSession(sessionID);
    }
}

