package itechart.studentjpa.dao;

import org.hibernate.HibernateException;

/**
 * Created by Maxim on 2/13/2016.
 */
public class LectureDAO extends BaseDAO {
    public void delete(Integer id) {
        beginTransaction();
        try{
            getSession().createQuery("delete from Lecture where id=:id").setParameter("id", id).executeUpdate();

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }
}
