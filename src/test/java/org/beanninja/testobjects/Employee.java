package org.beanninja.testobjects;

import org.beanninja.BaseBean;

public class Employee extends BaseBean {
    private Long employeeId;
    private String firstName;
    private String middleName;
    private String lastame;
    private EmployeeType empType;
    
    public Employee(Long employeeId, String firstName, String middleName, String lastame, EmployeeType empType) {
        super();
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastame = lastame;
        this.empType = empType;
        
    }
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastame() {
        return lastame;
    }
    public void setLastame(String lastame) {
        this.lastame = lastame;
    }
    public EmployeeType getEmpType() {
        return empType;
    }
    public void setEmpType(EmployeeType empType) {
        this.empType = empType;
    }
    
}
