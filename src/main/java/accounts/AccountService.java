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

     void addNewUser(UsersDataSet usersDataSet) throws DBException;

     void deleteUser(UsersDataSet usersDataSet) throws DBException;

     UsersDataSet getUserByLogin(String login) throws DBException;

     UsersDataSet getUserBySessionId(String sessionId) throws DBException;

     void addSession(String sessionId, UsersDataSet usersDataSet) throws DBException;

     void deleteSession(String sessionId) throws DBException;
}
