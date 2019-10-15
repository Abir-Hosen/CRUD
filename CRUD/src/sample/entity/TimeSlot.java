package sample.entity;


import java.sql.Time;

public class TimeSlot {

    int time_slot_id;
    int day;
    Time start_time;
    Time end_time;

    public TimeSlot(int time_slot_id, int day, Time start_time, Time end_time) {
        this.time_slot_id = time_slot_id;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public int getTime_slot_id() {
        return time_slot_id;
    }

    public void setTime_slot_id(int time_slot_id) {
        this.time_slot_id = time_slot_id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }
}
