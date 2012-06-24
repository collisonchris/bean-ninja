package org.beanninja.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.beanninja.testobjects.Employee;
import org.beanninja.testobjects.EmployeeType;
import org.junit.Test;


public class TestBaseBean {
    
    @Test public void testBaseBeanToMap() {
        EmployeeType empType = new EmployeeType(1L, "Engineer", false, false);
        Employee emp = new Employee(1L, "Chris", "Ray", "Collison", empType);
        
        Map<String, String> map = emp.getAsMap(); 
        assertEquals("Should be 5 entries!",5,map.size());
    }
}
