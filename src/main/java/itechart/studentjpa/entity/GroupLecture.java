package itechart.studentjpa.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Maxim on 2/6/2016.
 */

@Entity
@Table(name = "group_lection")
//@IdClass(GroupLecturePK.class)
public class GroupLecture implements Serializable,AbstractEntity {


    private Group group;
    private Lecture lecture;
    private Date date;

    private Teacher teacher;

    public GroupLecture() {
    }

    public GroupLecture(Group group, Lecture lecture, Date date, Teacher teacher) {
        this.group = group;
        this.lecture = lecture;
        this.date = date;
        this.teacher = teacher;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "group_id", referencedColumnName = "id", updatable = false)
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "lection_id", nullable = false, referencedColumnName = "id")
    public Lecture getLecture() {
        return lecture;
    }
    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    @Id
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "teacher_id", nullable = false, referencedColumnName = "id")
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        int result = group != null ? group.hashCode() : 0;
        result = 31 * result + (lecture != null ? lecture.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GroupLecture that = (GroupLecture) obj;

        return (group != null ? group.equals(that.group) : that.group == null)
                && (lecture != null ? lecture.equals(that.lecture) : that.lecture == null)
                && (date != null ? date.equals(that.date) : that.date == null)
                && (teacher != null ? teacher.equals(that.teacher) : that.teacher == null);

    }

}

