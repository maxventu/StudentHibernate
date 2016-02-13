package itechart.studentjpa.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Maxim on 2/6/2016.
 */

@Entity
@Table(name = "student")
public class Student implements Serializable,AbstractEntity{
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    private Group group;

    public Student() {
    }

    public Student(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Student(String firstName, String lastName, Date birthDate, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.group = group;
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
    @Column(name = "first_name", nullable = false, length = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Column(name = "last_name", nullable = false, length = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Column(name = "birthdate", nullable = false)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false, referencedColumnName = "id")
    @Temporal(TemporalType.DATE)
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Student that = (Student) obj;

        return (id != null ? id.equals(that.id) : that.id == null)
                && (firstName != null ? firstName.equals(that.firstName) : that.firstName == null)
                && (lastName != null ? lastName.equals(that.lastName) : that.lastName == null)
                && (birthDate != null ? birthDate.equals(that.birthDate) : that.birthDate == null)
                && (group != null ? group.equals(that.group) : that.group == null);

    }
}
