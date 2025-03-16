

public class StudentDTO {
    private int id;
    private String name;
    private String mail;
    private String course;
    private int year;

    public StudentDTO(int id, String name, String mail, String course, int year) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.course = course;
        this.year = year;
    }

    //Getter and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}
