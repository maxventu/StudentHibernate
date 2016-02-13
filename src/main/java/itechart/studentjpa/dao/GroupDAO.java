package itechart.studentjpa.dao;

import itechart.studentjpa.entity.Group;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * Created by Maxim on 2/13/2016.
 */
public class GroupDAO extends BaseDAO{
    public void delete(Integer id) {
        beginTransaction();
        try{
            getSession().createQuery("delete from Group where id=:id").setParameter("id", id).executeUpdate();

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Group findById(Integer id) {
        beginTransaction();
        try{
            Group result = (Group) getSession().get(Group.class, id);
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Group findFullById(Integer id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM Group gr left join fetch gr.students where gr.id=:id");
            query.setParameter("id", id);

            Group result = (Group)query.uniqueResult();
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
