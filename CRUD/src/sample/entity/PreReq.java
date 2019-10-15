package sample.entity;

public class PreReq {

    int course_id;
    int prereq_id;

    public PreReq(int course_id, int prereq_id) {
        this.course_id = course_id;
        this.prereq_id = prereq_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getPrereq_id() {
        return prereq_id;
    }

    public void setPrereq_id(int prereq_id) {
        this.prereq_id = prereq_id;
    }
}
