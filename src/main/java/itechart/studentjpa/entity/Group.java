package itechart.studentjpa.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Maxim on 2/6/2016.
 */

@Entity
@Table(name = "`group`")
public class Group implements Serializable,AbstractEntity {
    private Integer id;
    private String name;
    private Date created;

    private Teacher curator;

    private Collection<GroupLecture> groupLectures = new ArrayList<>();
    private Collection<Student> students = new ArrayList<>();

    public Group() {
    }

    public Group(String name, Date created, Teacher curator) {
        this.name = name;
        this.created = created;
        this.curator = curator;
    }

    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = true, updatable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "created", nullable = false, updatable = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curator_id", referencedColumnName = "id")
    public Teacher getCurator() {
        return curator;
    }

    public void setCurator(Teacher curator) {
        this.curator = curator;
    }

    @OneToMany(mappedBy = "group")
    public Collection<GroupLecture> getGroupLectures() {
        return groupLectures;
    }

    public void setGroupLectures(Collection<GroupLecture> groupLecturesById) {
        this.groupLectures = groupLecturesById;
    }

    @OneToMany(mappedBy = "group")
    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (curator != null ? curator.hashCode() : 0);
        result = 31 * result + (groupLectures != null ? groupLectures.hashCode() : 0);
        result = 31 * result + (students != null ? students.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Group that = (Group) obj;

        return (id != null ? id.equals(that.name) : that.id == null)
                &&(name != null ? name.equals(that.name) : that.name == null)
                && (created != null ? created.equals(that.created) : that.created == null)
                && (curator != null ? curator.equals(that.curator) : that.curator == null)
                && (groupLectures != null ? groupLectures.equals(that.groupLectures) : that.groupLectures == null)
                && (students != null ? students.equals(that.students) : that.students == null);

    }
}
