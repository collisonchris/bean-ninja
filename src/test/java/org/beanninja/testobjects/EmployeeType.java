package org.beanninja.testobjects;

import org.beanninja.BaseBean;

public class EmployeeType extends BaseBean {
    private Long employeeTypeId;
    private String employeeTypeCode;
    private boolean isManager;
    private boolean isDirector;
    public EmployeeType(Long employeeTypeId, String employeeTypeCode, boolean isManager, boolean isDirector) {
        super();
        this.employeeTypeId = employeeTypeId;
        this.employeeTypeCode = employeeTypeCode;
        this.isManager = isManager;
        this.isDirector = isDirector;
    }
    public Long getEmployeeTypeId() {
        return employeeTypeId;
    }
    public void setEmployeeTypeId(Long employeeTypeId) {
        this.employeeTypeId = employeeTypeId;
    }
    public String getEmployeeTypeCode() {
        return employeeTypeCode;
    }
    public void setEmployeeTypeCode(String employeeTypeCode) {
        this.employeeTypeCode = employeeTypeCode;
    }
    public boolean isManager() {
        return isManager;
    }
    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }
    public boolean isDirector() {
        return isDirector;
    }
    public void setDirector(boolean isDirector) {
        this.isDirector = isDirector;
    }
    
}
