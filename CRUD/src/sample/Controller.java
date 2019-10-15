package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.entity.*;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public Tab student;
    public TableView<Student> table_student;
    public TableColumn<Student, Integer> student_id;
    public TableColumn<Student, String> student_name;
    public TableColumn<Student, String> student_dept_name;
    public TableColumn<Student, Integer> student_total_credit;
    public TableColumn student_edit;

    public TableView<Takes> table_takes;
    public TableColumn<Takes, Integer> takes_id;
    public TableColumn<Takes, Integer> takes_course_id;
    public TableColumn<Takes, Integer> takes_sec_id;
    public TableColumn<Takes, Integer> takes_semester;
    public TableColumn<Takes, Integer> takes_year;
    public TableColumn<Takes, Float> takes_grade;
    public TableColumn takes_edit;

    public TableView<Section> table_section;
    public TableColumn<Section, Integer> section_course_id;
    public TableColumn<Section, Integer> section_sec_id;
    public TableColumn<Section, Integer> section_semester;
    public TableColumn<Section, Integer> section_year;
    public TableColumn<Section, Integer> section_building;
    public TableColumn<Section, Integer> section_room_no;
    public TableColumn<Section, Integer> section_time_slot_id;
    public TableColumn section_edit;

    public TableView<ClassRoom> table_classroom;
    public TableColumn<ClassRoom, Integer> classroom_building;
    public TableColumn<ClassRoom, Integer> classroom_room_no;
    public TableColumn<ClassRoom, Integer> classroom_capacity;
    public TableColumn classroom_edit;

    public TableView<TimeSlot> table_timeslot;
    public TableColumn<TimeSlot, Integer> timeslot_id;
    public TableColumn<TimeSlot, Integer> timeslot_day;
    public TableColumn<TimeSlot, Time> timeslot_start;
    public TableColumn<TimeSlot, Time> timeslot_end;
    public TableColumn timeslot_edit;

    public TableView<Teaches> table_teaches;
    public TableColumn<Teaches, Integer> teaches_id;
    public TableColumn<Teaches, Integer> teaches_course_id;
    public TableColumn<Teaches, Integer> teaches_sec_id;
    public TableColumn<Teaches, Integer> teaches_semester;
    public TableColumn<Teaches, Integer> teaches_year;
    public TableColumn teaches_edit;
    
    public TableView<Courses> table_course;
    public TableColumn<Courses, Integer> course_id;
    public TableColumn<Courses, String> course_title;
    public TableColumn<Courses, String> course_dept_name;
    public TableColumn<Courses, Float> course_credit;
    public TableColumn course_edit;
    
    public TableView<PreReq> table_prereq;
    public TableColumn<PreReq, Integer> prereq_course_id;
    public TableColumn<PreReq, Integer> prereq_id;
    public TableColumn prereq_edit;
    
    public TableView<Department> table_dept;
    public TableColumn<Department, String> dept_name;
    public TableColumn<Department, Integer> dept_building;
    public TableColumn<Department, Double> dept_budget;
    public TableColumn dept_edit;
    
    public TableView<Instructor> table_instructor;
    public TableColumn<Instructor, Integer> instructor_id;
    public TableColumn<Instructor, String> instructor_name;
    public TableColumn<Instructor, String> instructor_dept_name;
    public TableColumn<Instructor, Double> instructor_salary;
    public TableColumn instructor_edit;
    
    public TableView<Advisor> table_advisor;
    public TableColumn<Advisor, Integer> advisor_student_id;
    public TableColumn<Advisor, Integer> advisor_instructor_id;
    public TableColumn advisor_edit;

    // ----------------------------------              READ DATA VARIABLE FINISHED           ---------------------------

    public TextField student_field_id;
    public TextField student_field_name;
    public ComboBox student_field_dept_name;
    public TextField student_field_tot_credit;
    public Label student_message;
    
    public ComboBox takes_field_id;
    public ComboBox takes_field_course_id;
    public ComboBox takes_field_section_id;
    public ComboBox takes_field_semester;
    public ComboBox takes_field_year;
    public TextField takes_field_grade;
    public Label takes_message;

    public TextField dept_field_name;
    public TextField deft_field_budget;
    public ComboBox dept_field_building;
    public Label dept_message;

    public TextField ins_field_id;
    public TextField ins_field_name;
    public ComboBox ins_field_dept_name;
    public TextField ins_field_salary;
    public Label insMessage;

    public TextField course_field_id;
    public TextField course_field_title;
    public TextField course_Field_credit;
    public ComboBox course_field_department_name;
    public Label courseMessage;
    
    public TextField class_field_building;
    public TextField class_field_room;
    public TextField class_field_capacity;
    public Label classMessage;


    ObservableList<Student> student_oblist= FXCollections.observableArrayList();
    ObservableList<Takes> takesObservableList= FXCollections.observableArrayList();
    ObservableList<Section> sectionObservableList=FXCollections.observableArrayList();
    ObservableList<ClassRoom> classRoomObservableList= FXCollections.observableArrayList();
    ObservableList<TimeSlot> timeSlotObservableList=FXCollections.observableArrayList();
    ObservableList<Teaches> teachesObservableList= FXCollections.observableArrayList();
    ObservableList<Courses> coursesObservableList= FXCollections.observableArrayList();
    ObservableList<PreReq> preReqObservableList= FXCollections.observableArrayList();
    ObservableList<Department> departmentObservableList= FXCollections.observableArrayList();
    ObservableList<Instructor> instructorObservableList= FXCollections.observableArrayList();
    ObservableList<Advisor> advisorObservableList= FXCollections.observableArrayList();

    // ------------------------  SINGLE DATA LIST   --------------------------

    ObservableList deptNameList = FXCollections.observableArrayList();
    ObservableList courseIdList = FXCollections.observableArrayList();
    ObservableList studentIdList =FXCollections.observableArrayList();
    ObservableList<Integer> sectionList =FXCollections.observableArrayList(1);
    ObservableList<Integer> semesterList= FXCollections.observableArrayList(1,2,3,4,5,6,7,8);
    ObservableList<Integer> yearList= FXCollections.observableArrayList(1,2,3,4);
    ObservableList<Integer> buildingList= FXCollections.observableArrayList(1,2);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //---------------------------           READING DATA        -----------------------------

    @FXML
    public void viewStudent(){
        table_student.getItems().clear();
        student_field_dept_name.getItems().clear();
        try {
            Connection connection= DatabaseConnector.getConnection();
            ResultSet resultSet= connection.createStatement().executeQuery("SELECT * FROM student");
            while (resultSet.next()){
                student_oblist.add(new Student(resultSet.getInt("id"),resultSet.getString("name")
                        ,resultSet.getString("dept_name"),resultSet.getInt("tot_cred")));
            }

            ResultSet resultSetDept= connection.createStatement().executeQuery("SELECT dept_name FROM department");
            while(resultSetDept.next()){
                deptNameList.add(resultSetDept.getString("dept_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        student_field_dept_name.setItems(deptNameList);

        student_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        student_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        student_dept_name.setCellValueFactory(new PropertyValueFactory<>("dept_name"));
        student_total_credit.setCellValueFactory(new PropertyValueFactory<>("tot_cred"));

        table_student.setItems(student_oblist);
    }

    @FXML
    public void viewTakes(){
        table_takes.getItems().clear();
        takes_field_id.getItems().clear();
        takes_field_course_id.getItems().clear();
        takes_field_semester.getItems().clear();
        takes_field_year.getItems().clear();
        takes_field_section_id.getItems().clear();

        try{
            Connection connection=DatabaseConnector.getConnection();
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM takes");
            while (resultSet.next()){
                takesObservableList.add(new Takes(resultSet.getInt("id"), resultSet.getInt("course_id")
                        , resultSet.getInt("sec_id"),resultSet.getInt("semester"),resultSet.getInt("year"), resultSet.getFloat("grade")));
            }

            ResultSet resultSetCourse= connection.createStatement().executeQuery("SELECT course_id FROM course ORDER BY course.course_id ASC");
            while(resultSetCourse.next()){
                courseIdList.add(resultSetCourse.getInt("course_id"));
            }

            ResultSet resultSetStudentId= connection.createStatement().executeQuery("SELECT id FROM student ORDER BY student.id ASC");
            while(resultSetStudentId.next()){
                studentIdList.add(resultSetStudentId.getInt("id"));
            }

            takes_field_id.setItems(studentIdList);
            takes_field_course_id.setItems(courseIdList);
            takes_field_section_id.setItems(sectionList);
            takes_field_semester.setItems(semesterList);
            takes_field_year.setItems(yearList);

        }catch (SQLException e){
            e.printStackTrace();
        }

        takes_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        takes_course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        takes_sec_id.setCellValueFactory(new PropertyValueFactory<>("sec_id"));
        takes_semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        takes_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        takes_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        table_takes.setItems(takesObservableList);
    }


    @FXML
    public void viewSection(){
        table_section.getItems().clear();

        try{
            Connection connection=DatabaseConnector.getConnection();
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM section");
            while (resultSet.next()){
                sectionObservableList.add(new Section(resultSet.getInt("course_id"), resultSet.getInt("sec_id")
                        , resultSet.getInt("semester"),resultSet.getInt("year"),resultSet.getInt("building")
                        , resultSet.getInt("room_no"), resultSet.getInt("time_slot_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        section_course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        section_sec_id.setCellValueFactory(new PropertyValueFactory<>("sec_id"));
        section_semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        section_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        section_building.setCellValueFactory(new PropertyValueFactory<>("building"));
        section_room_no.setCellValueFactory(new PropertyValueFactory<>("room_no"));
        section_time_slot_id.setCellValueFactory(new PropertyValueFactory<>("time_slot_id"));

        table_section.setItems(sectionObservableList);
    }

    @FXML
    public void viewClassroom(){
        table_classroom.getItems().clear();
        try{
            Connection connection=DatabaseConnector.getConnection();
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM classroom");
            while (resultSet.next()){
                classRoomObservableList.add(new ClassRoom(resultSet.getInt("buildings"), resultSet.getInt("room_no")
                        , resultSet.getInt("capacity")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        classroom_building.setCellValueFactory(new PropertyValueFactory<>("building"));
        classroom_room_no.setCellValueFactory(new PropertyValueFactory<>("room_no"));
        classroom_capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        table_classroom.setItems(classRoomObservableList);
    }

    @FXML
    public void viewTimeSlot(){

        table_timeslot.getItems().clear();

        try{
            Connection connection=DatabaseConnector.getConnection();
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM time_slot");
            while (resultSet.next()){
                timeSlotObservableList.add(new TimeSlot(resultSet.getInt("time_slot_id"), resultSet.getInt("day")
                        , resultSet.getTime("start_time"), resultSet.getTime("end_time")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        timeslot_id.setCellValueFactory(new PropertyValueFactory<>("time_slot_id"));
        timeslot_day.setCellValueFactory(new PropertyValueFactory<>("day"));
        timeslot_start.setCellValueFactory(new PropertyValueFactory<>("start_time"));
        timeslot_end.setCellValueFactory(new PropertyValueFactory<>("end_time"));

        table_timeslot.setItems(timeSlotObservableList);
    }

    @FXML
    public void viewTeaches(){

        table_teaches.getItems().clear();
        try{
            Connection connection=DatabaseConnector.getConnection();
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM teaches");
            while (resultSet.next()){
                teachesObservableList.add(new Teaches(resultSet.getInt("id"), resultSet.getInt("course_id")
                        , resultSet.getInt("sec_id"),resultSet.getInt("semester"),resultSet.getInt("year")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        teaches_id.setCellValueFactory((new PropertyValueFactory<>("id")));
        teaches_course_id.setCellValueFactory((new PropertyValueFactory<>("course_id")));
        teaches_sec_id.setCellValueFactory((new PropertyValueFactory<>("sec_id")));
        teaches_semester.setCellValueFactory((new PropertyValueFactory<>("semester")));
        teaches_year.setCellValueFactory((new PropertyValueFactory<>("year")));

        table_teaches.setItems(teachesObservableList);
    }

    @FXML
    public void viewCourse(){
        table_course.getItems().clear();
        course_field_department_name.getItems().clear();
        try{
            Connection connection=DatabaseConnector.getConnection();
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM course");
            while (resultSet.next()){
                coursesObservableList.add(new Courses(resultSet.getInt("course_id"), resultSet.getString("title"),
                        resultSet.getString("dept_name"),resultSet.getFloat("credits")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        course_field_department_name.setItems(deptNameList);

        course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        course_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        course_dept_name.setCellValueFactory(new PropertyValueFactory<>("dept_name"));
        course_credit.setCellValueFactory(new PropertyValueFactory<>("credits"));

        table_course.setItems(coursesObservableList);

    }

    @FXML
    public void viewPrereq(){

        table_prereq.getItems().clear();
        try{
            Connection connection=DatabaseConnector.getConnection();
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM prereq");
            while (resultSet.next()){
                preReqObservableList.add(new PreReq(resultSet.getInt("course_id"), resultSet.getInt("prereq_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        prereq_course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        prereq_id.setCellValueFactory(new PropertyValueFactory<>("prereq_id"));

        table_prereq.setItems(preReqObservableList);

    }

    @FXML
    public void viewDepartment(){

        table_dept.getItems().clear();
        try{
            Connection connection=DatabaseConnector.getConnection();
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM department");
            while (resultSet.next()){
                departmentObservableList.add(new Department(resultSet.getString("dept_name"),
                        resultSet.getInt("building"),resultSet.getDouble("budget")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        dept_field_building.setItems(buildingList);

        dept_name.setCellValueFactory(new PropertyValueFactory<>("dept_name"));
        dept_building.setCellValueFactory(new PropertyValueFactory<>("building"));
        dept_budget.setCellValueFactory(new PropertyValueFactory<>("budget"));

        table_dept.setItems(departmentObservableList);

    }

    @FXML
    public void viewInstructor(){

        table_instructor.getItems().clear();
        ins_field_dept_name.getItems().clear();
        try {
            Connection connection= DatabaseConnector.getConnection();
            ResultSet resultSet= connection.createStatement().executeQuery("SELECT * FROM instructor");
            while (resultSet.next()){
                instructorObservableList.add(new Instructor(resultSet.getInt("id"),resultSet.getString("name")
                        ,resultSet.getString("dept_name"),resultSet.getDouble("salary")));
            }

            ResultSet resultSetDept= connection.createStatement().executeQuery("SELECT dept_name FROM department");
            while(resultSetDept.next()){
                deptNameList.add(resultSetDept.getString("dept_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ins_field_dept_name.setItems(deptNameList);

        instructor_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        instructor_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        instructor_dept_name.setCellValueFactory(new PropertyValueFactory<>("dept_name"));
        instructor_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        table_instructor.setItems(instructorObservableList);

    }

    @FXML
    public void viewAdvisor(){

        table_advisor.getItems().clear();
        try {
            Connection connection= DatabaseConnector.getConnection();
            ResultSet resultSet= connection.createStatement().executeQuery("SELECT * FROM advisor");
            while (resultSet.next()){
                advisorObservableList.add(new Advisor(resultSet.getInt("s_id"),resultSet.getInt("i_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        advisor_student_id.setCellValueFactory(new PropertyValueFactory<>("s_id"));
        advisor_instructor_id.setCellValueFactory(new PropertyValueFactory<>("i_id"));

        table_advisor.setItems(advisorObservableList);
    }

    //---------------------           READING DATA FINISHED          -------------------------



    //---------------------               CREATING DATA              -------------------------

    @FXML
    public void addStudent(){

        student_message.setText("");
        int id= Integer.parseInt(student_field_id.getText());
        String name= student_field_name.getText();
        String deptname= (String) student_field_dept_name.getSelectionModel().getSelectedItem();
        int tot_cred= Integer.parseInt(student_field_tot_credit.getText());

        String query= "INSERT INTO `university`.`student` (`id`, `name`, `dept_name`, `tot_cred`) VALUES (?, ?, ?, ?)";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, deptname);
            preparedStatement.setInt(4, tot_cred);

            preparedStatement.execute();
            student_message.setText("Student add successfully.");

        }catch (SQLException e){
            e.printStackTrace();
            student_message.setText("Error!");
        }

        viewStudent();
    }

    @FXML
    public void addTakes(){

        takes_message.setText("");
        int id= (int) takes_field_id.getSelectionModel().getSelectedItem();
        int course= (int) takes_field_course_id.getSelectionModel().getSelectedItem();
        int section= (int) takes_field_section_id.getSelectionModel().getSelectedItem();
        int session= (int) takes_field_semester.getSelectionModel().getSelectedItem();
        int year= (int) takes_field_year.getSelectionModel().getSelectedItem();
        float grade= Float.parseFloat(takes_field_grade.getText());

        String query= "INSERT INTO `university`.`takes` (`id`, `course_id`, `sec_id`, `semester`, `year`, `grade`) VALUES (?, ?, ?, ?, ?, ?)";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, course);
            preparedStatement.setInt(3, section);
            preparedStatement.setInt(4, session);
            preparedStatement.setInt(5, year);
            preparedStatement.setFloat(6, grade);

            preparedStatement.execute();
            takes_message.setText("Takes add successfully.");

        }catch (SQLException e){
            e.printStackTrace();
            takes_message.setText("Error!");
        }

        viewTakes();
    }



    @FXML
    public void addDept() {

        dept_message.setText("");
        String dptName= dept_field_name.getText();
        int dptBld= (int) dept_field_building.getSelectionModel().getSelectedItem();
        Double dptBud= Double.parseDouble(deft_field_budget.getText());

        String query= "INSERT INTO `university`.`department` (`dept_name`, `building`, `budget`) VALUES (?, ?, ?)";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, dptName);
            preparedStatement.setInt(2, dptBld);
            preparedStatement.setDouble(3, dptBud);

            preparedStatement.execute();
            dept_message.setText("success!");

        }catch (SQLException e){
            e.printStackTrace();
            dept_message.setText("Error!");
        }

        viewDepartment();

    }

    @FXML
    public void addInstructor(){

        insMessage.setText("");
        int id= Integer.parseInt(ins_field_id.getText());
        String name= ins_field_name.getText();
        String deptname= (String) ins_field_dept_name.getSelectionModel().getSelectedItem();
        double salary = Double.parseDouble(ins_field_salary.getText());

        String query= "INSERT INTO `university`.`Instructor` (`id`, `name`, `dept_name`, `salary`) VALUES (?, ?, ?, ?)";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, deptname);
            preparedStatement.setDouble(4, salary);

            preparedStatement.execute();
            insMessage.setText("Instructor add successfully.");

        }catch (SQLException e){
            e.printStackTrace();
            insMessage.setText("Error!");
        }

        viewInstructor();
    }

    @FXML
    public void addCourse(){
        courseMessage.setText("");
        int id= Integer.parseInt(course_field_id.getText());
        String name= course_field_title.getText();
        String deptname= (String) course_field_department_name.getSelectionModel().getSelectedItem();
        int credit = Integer.parseInt(course_Field_credit.getText());

        String query= "INSERT INTO `university`.`course` (`course_id`, `title`, `dept_name`, `credits`) VALUES (?, ?, ?, ?)";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, deptname);
            preparedStatement.setDouble(4, credit);

            preparedStatement.execute();
            courseMessage.setText("Course add successfully.");

        }catch (SQLException e){
            e.printStackTrace();
            courseMessage.setText("Error!");
        }

        viewCourse();
    }

    @FXML
    public void addClassroom(){

        classMessage.setText("");
        int buildingid= Integer.parseInt(class_field_building.getText());
        int roomid= Integer.parseInt(class_field_room.getText());
        int capacity= Integer.parseInt(class_field_capacity.getText());

        String query= "INSERT INTO `university`.`classroom` (`buildings`, `room_no`, `capacity`) VALUES (?, ?, ?)";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);

            preparedStatement.setInt(1, buildingid);
            preparedStatement.setInt(2, roomid);
            preparedStatement.setInt(3, capacity);

            preparedStatement.execute();
            classMessage.setText("Class add successfully.");

        }catch (SQLException e){
            e.printStackTrace();
            classMessage.setText("Error!");
        }

        viewClassroom();
    }

    //---------------------           CREATING DATA FINISHED          -------------------------


    //---------------------               UPDATING DATA              -------------------------

    @FXML
    public void updateStudent(){

        int id= Integer.parseInt(student_field_id.getText());
        String name= student_field_name.getText();
        String deptname= (String)student_field_dept_name.getValue();
        int tot_cred= Integer.parseInt(student_field_tot_credit.getText());

        String query= "UPDATE `university`.`student` SET `id` = ?, `name` = ?,`dept_name` = ?, `tot_cred` = ? WHERE `student`.`id` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, deptname);
            preparedStatement.setInt(4, tot_cred);
            preparedStatement.setInt(5, id);

            preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

        viewStudent();
    }

    @FXML
    public void updateDept(){

        dept_message.setText("");
        String dptName= dept_field_name.getText();
        int dptBld= (int) dept_field_building.getSelectionModel().getSelectedItem();
        Double dptBud= Double.parseDouble(deft_field_budget.getText());

        String query= "UPDATE `university`.`department` SET `dept_name` = ?, `building` = ?,`budget` = ?  WHERE `department`.`dept_name` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);

            preparedStatement.setString(1, dptName);
            preparedStatement.setInt(2, dptBld);
            preparedStatement.setDouble(3, dptBud);
            preparedStatement.setString(4, dptName);

            preparedStatement.execute();
            dept_message.setText("success!");

        }catch (SQLException e){
            e.printStackTrace();
            dept_message.setText("error!");
        }

        viewDepartment();
    }

    @FXML
    public void updateInstructor(){
        insMessage.setText("");
        int id= Integer.parseInt(ins_field_id.getText());
        String name= ins_field_name.getText();
        String deptname= (String)ins_field_dept_name.getSelectionModel().getSelectedItem();;
        double salary= Integer.parseInt(ins_field_salary.getText());

        String query= "UPDATE `university`.`instructor` SET `id` = ?, `name` = ?,`dept_name` = ?, `salary` = ? WHERE `instructor`.`id` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, deptname);
            preparedStatement.setDouble(4, salary);
            preparedStatement.setInt(5, id);

            preparedStatement.execute();
            insMessage.setText("Successfully updated");

        }catch (SQLException e){
            e.printStackTrace();
            insMessage.setText("Error!");
        }

        viewInstructor();
    }

    @FXML
    public void courseUpdate(){
        courseMessage.setText("");
        int id= Integer.parseInt(course_field_id.getText());
        String name= course_field_title.getText();
        String deptname= (String) course_field_department_name.getSelectionModel().getSelectedItem();
        int credit = Integer.parseInt(course_Field_credit.getText());

        String query= "UPDATE `university`.`course` SET `course_id` = ?, `title` = ?,`dept_name` = ?, `credits` = ? WHERE `course`.`course_id` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, deptname);
            preparedStatement.setDouble(4, credit);

            preparedStatement.execute();
            insMessage.setText("Course Updated successfully.");

        }catch (SQLException e){
            e.printStackTrace();
            insMessage.setText("Error!");
        }

        viewCourse();
    }

    @FXML
    public void updateClassroom(){
        classMessage.setText("");
        int buildingid= Integer.parseInt(class_field_building.getText());
        int roomid= Integer.parseInt(class_field_room.getText());
        int capacity= Integer.parseInt(class_field_capacity.getText());

        String query= "UPDATE `university`.`classroom` SET `buildings` = ?, `room_no` = ?,`capacity` = ? WHERE `classroom`.`buildings` = ? AND `classroom`.`room_no` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);

            preparedStatement.setInt(1, buildingid);
            preparedStatement.setInt(2, roomid);
            preparedStatement.setInt(3, capacity);
            preparedStatement.setInt(4, buildingid);
            preparedStatement.setInt(5, roomid);

            preparedStatement.execute();
            classMessage.setText("Class updated successfully.");

        }catch (SQLException e){
            e.printStackTrace();
            classMessage.setText("Error!");
        }

        viewClassroom();
    }

    //---------------------           UPDATING DATA FINISHED          -------------------------


    //---------------------               DELETING DATA              -------------------------

    @FXML
    public void deleteStudent(){

        int id= Integer.parseInt(student_field_id.getText());

        String query= "DELETE FROM `university`.`student` WHERE `student`.`id` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

        viewStudent();
    }

    @FXML
    public void deleteTakes(){

        int id= (int) takes_field_id.getSelectionModel().getSelectedItem();

        String query= "DELETE FROM `university`.`student` WHERE `takes`.`id` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

        viewTakes();
    }

    @FXML
    public void deleteDept(){
        dept_message.setText("");
        String dptName= dept_field_name.getText();

        String query= "DELETE FROM `university`.`department` WHERE `department`.`dept_name` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, dptName);

            preparedStatement.execute();
            dept_message.setText("Success!");

        }catch (SQLException e){
            e.printStackTrace();
            dept_message.setText("Error!");
        }

        viewDepartment();
    }

    @FXML
    public void deleteInstructor(){
        insMessage.setText("");
        int id= Integer.parseInt(ins_field_id.getText());

        String query= "DELETE FROM `university`.`instructor` WHERE `instructor`.`id` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            insMessage.setText("Succes!");

        }catch (SQLException e){
            e.printStackTrace();
            insMessage.setText("error!");
        }

        viewInstructor();
    }

    @FXML
    public void courseDelete(){
        courseMessage.setText("");
        int id= Integer.parseInt(course_field_id.getText());

        String query= "DELETE FROM `university`.`course` WHERE `course`.`course_id` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            insMessage.setText("Succes!");

        }catch (SQLException e){
            e.printStackTrace();
            insMessage.setText("error!");
        }

        viewCourse();

    }
    public void deleteClassroom(){
        classMessage.setText("");
        int buildingid= Integer.parseInt(class_field_building.getText());
        int roomid= Integer.parseInt(class_field_room.getText());

        String query= "DELETE FROM `university`.`classroom` WHERE `classroom`.`buildings` = ? AND `classroom`.`room_no` = ?";

        try{
            Connection connection= DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=null;

            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, buildingid);
            preparedStatement.setInt(2, roomid);

            preparedStatement.execute();
            classMessage.setText("Succes!");

        }catch (SQLException e){
            e.printStackTrace();
            classMessage.setText("error!");
        }

        viewClassroom();
    }
}

    //---------------------           DELETING DATA FINISHED          -------------------------


