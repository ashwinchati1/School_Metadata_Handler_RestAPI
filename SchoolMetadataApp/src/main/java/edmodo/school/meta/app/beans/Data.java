package edmodo.school.meta.app.beans;

import java.util.List;

/**
 * Created by ashwin on 8/14/2017.
 */

	public class Data {

    public SchoolData external_school;
    public SchoolRating external_school_rating;
    public List<SubjectStats> external_school_subject_stats;

    public Data(){

    }

    public Data(SchoolData external_school, SchoolRating external_school_rating, List<SubjectStats> external_school_subject_stats) {
        this.external_school = external_school;
        this.external_school_rating = external_school_rating;
        this.external_school_subject_stats = external_school_subject_stats;
    }

    public SchoolData getExternal_school() {
        return external_school;
    }

    public void setExternal_school(SchoolData external_school) {
        this.external_school = external_school;
    }

    public SchoolRating getExternal_school_rating() {
        return external_school_rating;
    }

    public void setExternal_school_rating(SchoolRating external_school_rating) {
        this.external_school_rating = external_school_rating;
    }

    public List<SubjectStats> getExternal_school_subject_stats() {
        return external_school_subject_stats;
    }

    public void setExternal_school_subject_stats(List<SubjectStats> external_school_subject_stats) {
        this.external_school_subject_stats = external_school_subject_stats;
    }
}

