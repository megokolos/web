package accounts;

import DB.dataSets.UsersDataSet;
import DB.executor.DBException;

/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public interface AccountService {

    public void addNewUser(UsersDataSet usersDataSet) throws DBException;

    public void deleteUser(UsersDataSet usersDataSet) throws DBException;

    public UsersDataSet getUserByLogin(String login) throws DBException;

    public UsersDataSet getUserBySessionId(String sessionId) throws DBException;

    public void addSession(String sessionId, UsersDataSet usersDataSet) throws DBException;

    public void deleteSession(String sessionId) throws DBException;
}
