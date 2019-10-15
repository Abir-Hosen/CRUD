package sample.entity;

public class Department {

    String dept_name;
    int building;
    double budget;

    public Department(String dept_name, int building, double budget) {
        this.dept_name = dept_name;
        this.building = building;
        this.budget = budget;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
