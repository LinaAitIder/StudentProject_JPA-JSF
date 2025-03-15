import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public Student getStudentById(int studentId){
        EntityManager em = JPAUtil.getEntityManager();
        Student student = em.find(Student.class, studentId);
        em.close();
        return student;
    }

    public void addStudent(Student student) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    public List<Student> getAllStudents() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }

    public void delete(int studentId) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        // Retrieve the student using the same EntityManager
        Student student = em.find(Student.class, studentId);

        if (student != null) {
            em.remove(student);
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }

        em.getTransaction().commit();
        em.close();
    }


    /*public List<Student> searchStudent(String keyword) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s WHERE s.name LIKE :keyword", Student.class
        );
        query.setParameter("keyword", "%" + keyword + "%");
        List<Student> result = query.getResultList();
        em.close();
        return result;
    }
    */


}
