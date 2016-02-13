package itechart.studentjpa.dao;

/**
 * Created by Maxim on 2/6/2016.
 */


import itechart.studentjpa.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.util.List;
public class StudentDAO extends BaseDAO {

    public List<Student> list() {
        beginTransaction();
        try{
            List<Student> result = (List<Student>) getSession().createQuery("FROM Student").list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public List<Student> list(int start, int size) {
        beginTransaction();
        try{
            List<Student> result = (List<Student>) getSession().createQuery("FROM Student").setFirstResult(start).setMaxResults(size).list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public List<Student> list(String firstName) {
        beginTransaction();
        try{
            List<Student> result =(List<Student>) getSession().createCriteria(Student.class).add(Restrictions.eq("firstName", firstName)).list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Long getCount() {
        beginTransaction();
        try{
            Long result = (Long) getSession().createQuery("select count(*) from Student").uniqueResult();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Student findById(Integer id) {
        beginTransaction();
        try{
            Student result = (Student) getSession().get(Student.class, id);
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Student fullFindById(Integer id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM Student st left join fetch st.group where st.id=:id");
            query.setParameter("id", id);

            Student result = (Student) query.uniqueResult();

            commitTransaction();

            return result;

        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }



    public void delete(Integer id) {
        beginTransaction();
        try{
            getSession().createQuery("delete from Student where id=:id").setParameter("id", id).executeUpdate();

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }
}
