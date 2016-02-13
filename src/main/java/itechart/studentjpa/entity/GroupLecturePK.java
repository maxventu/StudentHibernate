package itechart.studentjpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Maxim on 2/6/2016.
 */

@Embeddable
public class GroupLecturePK implements Serializable {
    private Group group;
    private Lecture lecture;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false, updatable = false)
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @ManyToOne
    @JoinColumn(name = "lection_id", nullable = false, updatable = false)
    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    @JoinColumn(name = "date",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public GroupLecturePK() {
    }

    public GroupLecturePK(Group group, Lecture lecture, Date date) {
        this.group = group;
        this.lecture = lecture;
        this.date = date;
    }

    @Override
    public int hashCode() {
        int result = group != null ? group.hashCode() : 0;
        result = 31 * result + (lecture != null ? lecture.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GroupLecturePK that = (GroupLecturePK) obj;

        return (group != null ? group.equals(that.group) : that.group == null) &&
               (lecture != null ? lecture.equals(that.lecture) : that.lecture == null) &&
               (date != null ? date.equals(that.date) : that.date == null);

    }
}
