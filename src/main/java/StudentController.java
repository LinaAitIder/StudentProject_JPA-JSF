import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("studentController")
@ApplicationScoped
public class StudentController implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student student = new Student();
    private StudentDAO studentDAO = new StudentDAO();
    private String searchKeyword="";
    private List<Student> students;

    @PostConstruct
    public void init() {
        students = studentDAO.getAllStudents(); // Make sure this returns the correct data.
    }
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
    public String getSearchKeyword() {
        return searchKeyword;
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudents() {
        if (students == null) {
            students = studentDAO.getAllStudents();
        }
        return students;
    }

    public String addStudent() {
        studentDAO.addStudent(student);
        students = null;
        return "index.xhtml";
    }

    public String deleteStudent(int studentId){
        studentDAO.delete(studentId);
        students = null;
        return "index.xhtml";
    }

    public void searchStudent(){
        if(searchKeyword != null && !searchKeyword.isEmpty()){
            students = new ArrayList<Student>();
        }else {
            students = studentDAO.getAllStudents().stream()
                    .filter(s->s.getName().toLowerCase().contains(searchKeyword.toLowerCase()))
                    .collect(Collectors.toList());
        }

    }
}
