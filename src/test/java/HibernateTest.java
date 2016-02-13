import itechart.studentjpa.dao.*;
import itechart.studentjpa.entity.*;
import junit.framework.TestCase;

import java.sql.Date;
import java.util.List;

/**
 * Created by Maxim on 2/6/2016.
 */
public class HibernateTest extends TestCase {
    public void testCreateGroup() {
        StudentDAO studentDao = new StudentDAO();
        TeacherDAO teacherDAO = new TeacherDAO();
        GroupDAO groupDAO = new GroupDAO();

        Teacher teacher = new Teacher("Василий","Иванович");
        System.out.printf("not saved teacher: %s %s\n", teacher.getFirstName(),teacher.getLastName());
        teacherDAO.save(teacher);
        System.out.printf("saved teacher: %s %s id=%s \n", teacher.getFirstName(), teacher.getLastName(), teacher.getId());

        Group group = new Group("first",new Date(System.currentTimeMillis()),teacher);
        groupDAO.save(group);

        Student student = new Student("Вася","Петечкин",new Date(System.currentTimeMillis()),group);
        System.out.printf("Student %s %s\n", student.getFirstName(),student.getLastName());
        studentDao.save(student);
        System.out.printf("saved: %s %s id=%s \n", student.getFirstName(), student.getLastName(), student.getId());


        Integer id = student.getId();
        student = studentDao.fullFindById(id);
        System.out.printf("Student %s %s in %s group\n",student.getFirstName(),student.getLastName(), student.getGroup().getName());
        assertEquals(student.getFirstName(),"Вася");

        id = group.getId();
        group = groupDAO.findFullById(id);
        System.out.printf("Group %d \n", group.getStudents().size());


        studentDao.delete(student.getId());
        groupDAO.delete(group.getId());
        teacherDAO.delete(teacher.getId());
    }

    public void testCreateGroupLecture() {
        StudentDAO studentDao = new StudentDAO();
        TeacherDAO teacherDAO = new TeacherDAO();
        GroupDAO groupDAO = new GroupDAO();
        LectureDAO lectureDAO = new LectureDAO();
        GroupLectureDAO groupLectureDAO = new GroupLectureDAO();

        Teacher teacher = new Teacher("Василий","Иванович");
        teacherDAO.save(teacher);

        Group group = new Group("second",new Date(System.currentTimeMillis()),teacher);
        groupDAO.save(group);

        Student student = new Student("Ксения","Добронравова",new Date(System.currentTimeMillis()),group);
        studentDao.save(student);


        Integer id = student.getId();
        student = studentDao.fullFindById(id);
        assertEquals(student.getFirstName(),"Ксения");

        Lecture lecture = new Lecture("math");
        lectureDAO.save(lecture);

        GroupLecture groupLecture = new GroupLecture(group,lecture,new Date(System.currentTimeMillis()),teacher);
        groupLectureDAO.save(groupLecture);
        System.out.printf("Group %tc, %s, %s, %s /n",groupLecture.getDate(),groupLecture.getGroup().getName(),groupLecture.getLecture().getName(),groupLecture.getTeacher().getFirstName());

        groupLectureDAO.delete(group.getId()/*,groupLecture.getDate()*/,lecture.getId());
        lectureDAO.delete(lecture.getId());
        studentDao.delete(student.getId());
        groupDAO.delete(group.getId());
        teacherDAO.delete(teacher.getId());
    }

    public void testReadAllStudents() {
        StudentDAO studentDao = new StudentDAO();

        List<Student> students = studentDao.list();
        for (Student student: students)
        {
            System.out.printf("%s %s \n", student.getFirstName(), student.getLastName());
        }

    }

    public void testCreateTeacher() {
        TeacherDAO teacherDAO = new TeacherDAO();


        Teacher teacher = new Teacher();
        teacher.setFirstName("Пётр");
        teacher.setLastName("Петрович");

        System.out.printf("Student %s %s\n", teacher.getFirstName(),teacher.getLastName());

        teacherDAO.save(teacher);

        System.out.printf("saved: %s %s id=%s \n", teacher.getFirstName(), teacher.getLastName(), teacher.getId());

        Integer id = teacher.getId();
        teacher = teacherDAO.findById(id);
        System.out.printf("Teacher %s \n", teacher);
        teacherDAO.delete(teacher.getId());



    }
}
