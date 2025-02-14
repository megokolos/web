package DB.dao;

import org.hibernate.Criteria;
import DB.dataSets.UsersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        UsersDataSet user = (UsersDataSet) criteria.add(Restrictions.eq("login", name)).uniqueResult();
        return (user != null) ? user.getId() : 0;
    }

    public long insertUser(UsersDataSet usersDataSet) throws HibernateException {
        return (Long) session.save(usersDataSet);
    }
}
