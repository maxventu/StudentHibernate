package itechart.studentjpa.dao;

import itechart.studentjpa.entity.Teacher;
import org.hibernate.HibernateException;

/**
 * Created by Maxim on 2/6/2016.
 */
public class TeacherDAO  extends BaseDAO {

    public void delete(Integer id) {
        beginTransaction();
        try{
            getSession().createQuery("delete from Teacher where id=:id").setParameter("id", id).executeUpdate();

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }
    public Teacher findById(Integer id) {
        beginTransaction();
        try{
            Teacher result = (Teacher) getSession().get(Teacher.class, id);
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }
}
