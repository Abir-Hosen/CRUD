package sample.entity;

public class Instructor {

    int id;
    String name;
    String dept_name;
    double salary;

    public Instructor(int id, String name, String dept_name, double salary) {
        this.id = id;
        this.name = name;
        this.dept_name = dept_name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
