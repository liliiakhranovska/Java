package RestLearning;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    @JsonProperty("emp_id")
    private int empId;

    private String name;

    private String cmpName;

    public Employee (int empId, String name, String cmpName) {
        this.empId = empId;
        this.name = name;
        this.cmpName = cmpName;
    }

    public Employee() {

    }


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmpName() {
        return cmpName;
    }

    public void setCmpName(String cmpName) {
        this.cmpName = cmpName;
    }
}
