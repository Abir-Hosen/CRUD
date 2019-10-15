package sample.entity;

public class ClassRoom {

    int building;
    int room_no;
    int capacity;

    public ClassRoom(int building, int room_no, int capacity) {
        this.building = building;
        this.room_no = room_no;
        this.capacity = capacity;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getRoom_no() {
        return room_no;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
