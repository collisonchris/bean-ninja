package org.beanninja.testobjects;

import java.util.Date;

import org.beanninja.BaseBeanUsingBeanMap;

public class Person extends BaseBeanUsingBeanMap {
    private Integer personId;
    private String firstName;
    private String lastName;
    private boolean deceased;
    private Date dateOfBirth;
    
      
    public Person() {
        super();
    }
    public Person(String firstName, String lastName, boolean deceased, Date dateOfBirth) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.deceased = deceased;
        this.dateOfBirth = dateOfBirth;
    }
    public Integer getPersonId() {
        return personId;
    }
    public void setPersonId(Integer personId) {
        this.personId = personId;
    } 
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public boolean isDeceased() {
        return deceased;
    }
    public void setDeceased(boolean deceased) {
        this.deceased = deceased;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
