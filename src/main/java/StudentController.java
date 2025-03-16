import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("studentController")
@ViewScoped
public class StudentController implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student student = new Student();
    private String searchKeyword="";
    private List<StudentDTO> searchResult;
    private List<Student> students;
    private String Action;
    private StudentService studentService = new StudentService();

    String studentId;
    String action;

    @PostConstruct
    public void init() {
        studentId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        action = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("action");

        // For Debugging
        if (action != null) {
            if ("update".equals(action)) {
                System.out.println("Update action triggered for studentId: " + studentId);
            } else if ("add".equals(action)) {
                System.out.println("Add action triggered");
            }
        } else {
            System.out.println("No action parameter found.");
        }
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public List<StudentDTO> getStudents() {
      return studentService.getAllStudents();
    }

    public String addStudent() {
        studentService.saveStudent(studentService.convertEntityToDTO(student));
        return "index.xhtml";
    }

    public String deleteStudent(int studentId){
        studentService.delete(studentId);
        return "index.xhtml";
    }

    public String updateStudent(int studentId){
            studentService.updateStudentData(studentId , student);
            return "index.xhtml";
    }

    public void addUpdateStudent(){
        System.out.println("Action: " + action);
        if(action.equals("add")){
            this.addStudent();
        } else if(action.equals("update")){
            this.updateStudent(Integer.parseInt(studentId));
        }
    }

    public void searchStudent(String searchKeyword){
        searchResult = studentService.searchStudent(searchKeyword);
    }

    public List<StudentDTO> getSearchResult() {
       return studentService.searchStudent(searchKeyword);
    }

}
