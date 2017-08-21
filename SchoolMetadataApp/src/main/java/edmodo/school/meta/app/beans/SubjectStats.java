package edmodo.school.meta.app.beans;

/**
 * Created by ashwin on 8/14/2017.
 */
public class SubjectStats {

    private int id;
    private int external_school_id;
    private int edmodo_subject_id;
    private float avg;
    private float state_avg;

    public SubjectStats(){

    }

    public SubjectStats(int id, int external_school_id, int edmodo_subject_id, float avg, float state_avg) {
        this.id = id;
        this.external_school_id = external_school_id;
        this.edmodo_subject_id = edmodo_subject_id;
        this.avg = avg;
        this.state_avg = state_avg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExternal_school_id() {
        return external_school_id;
    }

    public void setExternal_school_id(int external_school_id) {
        this.external_school_id = external_school_id;
    }

    public int getEdmodo_subject_id() {
        return edmodo_subject_id;
    }

    public void setEdmodo_subject_id(int edmodo_subject_id) {
        this.edmodo_subject_id = edmodo_subject_id;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public float getState_avg() {
        return state_avg;
    }

    public void setState_avg(float state_avg) {
        this.state_avg = state_avg;
    }
}