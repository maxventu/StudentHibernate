package itechart.studentjpa.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Maxim on 2/6/2016.
 */

@Entity
@Table(name = "loss_student")
public class LostStudent implements Serializable, AbstractEntity{

    private Integer id;
    private String reason;
    private Student student;
    private Lecture lecture;
    private Group group;
    private Date date;

    public LostStudent() {
    }

    @Id
    @NotNull
    @Column(name = "loss_student_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "reason", nullable = true, length = 20)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @NotNull
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lection_id", nullable = false, referencedColumnName = "id")
    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false, referencedColumnName = "id")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (lecture != null ? lecture.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        LostStudent that = (LostStudent) obj;

        return (id != null ? id.equals(that.id) : that.id == null)
                && (reason != null ? reason.equals(that.reason) : that.reason == null)
                && (student != null ? student.equals(that.student) : that.student == null)
                && (lecture != null ? lecture.equals(that.lecture) : that.lecture == null)
                && (group != null ? group.equals(that.group) : that.group == null)
                && (date != null ? date.equals(that.date) : that.date == null)
                ;


    }
}
