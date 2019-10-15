package sample.entity;

public class Advisor {

    int s_id;
    int i_id;

    public Advisor(int s_id, int i_id) {
        this.s_id = s_id;
        this.i_id = i_id;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getI_id() {
        return i_id;
    }

    public void setI_id(int i_id) {
        this.i_id = i_id;
    }
}
