package itechart.studentjpa.dao;

import itechart.studentjpa.entity.GroupLecture;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.Date;

/**
 * Created by Maxim on 2/13/2016.
 */
public class GroupLectureDAO extends BaseDAO {
    public void delete(Integer groupId/*,Date date*/, Integer lectureId){
        beginTransaction();
        try{
            //Calendar cal=Calendar.getInstance();
           // cal.setTime(date);
            Query q=getSession().createQuery(
                    "delete FROM GroupLecture grLec"  +
                            " where  grLec.lecture.id=:lectureId" +
                            " and grLec.group.id=:groupId"/*+
                            " and grLec.date=:date"*/)
                    .setParameter("lectureId", lectureId)
                    .setParameter("groupId", groupId)
                    //.setParameter("date", cal.getTime())
            ;
            q.executeUpdate();

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public GroupLecture findFullById(Integer groupId,Date date, Integer lectureId) {
        beginTransaction();
        try{
            Query query = getSession().createQuery(
                    "FROM GroupLecture grLec left join fetch grLec.group " +
                            "left join fetch grLec.lecture where grLec.date=:date" +
                            " and grLec.lecture.id=:lectureId" +
                            " and grLec.group.id=:groupId");
            query.setParameter("date", date);
            query.setParameter("lectureId", lectureId);
            query.setParameter("groupId", groupId);

            GroupLecture result = (GroupLecture)query.uniqueResult();
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
