package itechart.studentjpa.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Maxim on 2/6/2016.
 */

@Entity
@Table(name="lection")
public class Lecture implements Serializable,AbstractEntity {
    private Integer id;
    private String name;

    private Collection<GroupLecture> groupLectures = new ArrayList<>();

    public Lecture() {
    }

    public Lecture(String name) {
        this.name = name;
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

    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "lecture")
    public Collection<GroupLecture> getGroupLectures() {
        return groupLectures;
    }

    public void setGroupLectures(Collection<GroupLecture> groupLectures) {
        this.groupLectures = groupLectures;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (groupLectures != null ? groupLectures.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Lecture that = (Lecture) obj;

        return (id != null ? id.equals(that.id) : that.id == null)
                && (name != null ? name.equals(that.name) : that.name == null)
                && (groupLectures != null ? groupLectures.equals(that.groupLectures) : that.groupLectures == null);

    }
}
