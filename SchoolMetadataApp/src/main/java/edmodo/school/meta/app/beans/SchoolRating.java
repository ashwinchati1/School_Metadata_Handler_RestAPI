package edmodo.school.meta.app.beans;

/**
 * Created by ashwin on 8/14/2017.
 */
public class SchoolRating {

    private int id;
    private int external_school_id;
    private int five;
    private int four;
    private int three;
    private int two;
    private int one;
    private float average;
    private int num_ratings;

    public SchoolRating(){

    }

    public SchoolRating(int id, int external_school_id, int five, int four, int three, int two, int one, float average, int num_ratings) {
        this.id = id;
        this.external_school_id = external_school_id;
        this.five = five;
        this.four = four;
        this.three = three;
        this.two = two;
        this.one = one;
        this.average = average;
        this.num_ratings = num_ratings;
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

    public int getFive() {
        return five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getThree() {
        return three;
    }

    public void setThree(int three) {
        this.three = three;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public int getNum_ratings() {
        return num_ratings;
    }

    public void setNum_ratings(int num_ratings) {
        this.num_ratings = num_ratings;
    }
}
