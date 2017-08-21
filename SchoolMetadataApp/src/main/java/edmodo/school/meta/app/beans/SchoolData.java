package edmodo.school.meta.app.beans;

/**
 * Created by ashwin on 8/14/2017.
 */
public class SchoolData {

    private int id;
    private int edmodo_school_id;
    private String external_school_url;
    private String name;
    private String grades;
    private int student_count;
    private int school_type;
    private long nces_id;

    public SchoolData(){

    }

    public SchoolData(int id, int edmodo_school_id, String external_school_url, String name, String grades, int student_count, int school_type, long nces_id) {
        this.id = id;
        this.edmodo_school_id = edmodo_school_id;
        this.external_school_url = external_school_url;
        this.name = name;
        this.grades = grades;
        this.student_count = student_count;
        this.school_type = school_type;
        this.nces_id = nces_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdmodo_school_id() {
        return edmodo_school_id;
    }

    public void setEdmodo_school_id(int edmodo_school_id) {
        this.edmodo_school_id = edmodo_school_id;
    }

    public String getExternal_school_url() {
        return external_school_url;
    }

    public void setExternal_school_url(String external_school_url) {
        this.external_school_url = external_school_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public int getStudent_count() {
        return student_count;
    }

    public void setStudent_count(int student_count) {
        this.student_count = student_count;
    }

    public int getSchool_type() {
        return school_type;
    }

    public void setSchool_type(int school_type) {
        this.school_type = school_type;
    }

    public long getNces_id() {
        return nces_id;
    }

    public void setNces_id(long nces_id) {
        this.nces_id = nces_id;
    }
}
