import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO;
    public StudentService() {
        studentDAO = new StudentDAO();
    }

    // Convert Entity to DTO
    public StudentDTO convertEntityToDTO(Student student) {
       return new StudentDTO(student.getId(), student.getName(), student.getMail(), student.getCourse(),student.getYear());
    }

    // Get all students as DTOs
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        return students.stream().map(this::convertEntityToDTO).toList();
    }

    // Save new Student
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setMail(studentDTO.getMail());
        student.setCourse(studentDTO.getCourse());
        student.setYear(studentDTO.getYear());
        studentDAO.addStudent(student);
        return convertEntityToDTO(student);
    }

    //Delete a student
    public void delete(int studentId){
        studentDAO.delete(studentId);
    }

    // Update Student's Data
    public void updateStudentData(int studentId , Student student){
        studentDAO.update(studentId , student);
    }

    // Search for students
    public List<StudentDTO> searchStudent(String searchKeyword){
        List<Student> students;
        students = studentDAO.searchStudent(searchKeyword);
        if(students.isEmpty()){
            students = studentDAO.getAllStudents();
        }
        return students.stream().map(this::convertEntityToDTO).toList();
    }
}
