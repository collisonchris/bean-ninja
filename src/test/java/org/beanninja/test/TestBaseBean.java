package org.beanninja.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.beanninja.testobjects.Employee;
import org.beanninja.testobjects.EmployeeType;
import org.junit.Test;

import com.google.common.collect.Maps;


public class TestBaseBean {
    
    @Test public void testBaseBeanToMap() {
        EmployeeType empType = new EmployeeType(1L, "Engineer", false, false);
        Employee emp = new Employee(1L, "Chris", "Ray", "Collison", empType);
        
        Map<String, String> map = emp.getAsMap(); 
        assertEquals("Should be 5 entries!",5,map.size());
    }
    
    @Test public void testBaseBeanFromMap() throws Exception {
        Map<String, String> map = Maps.newHashMap();
        map.put("lastName", "Johnson");
        map.put("firstName", "Joe");
        
        Employee newEmp = new Employee();
        newEmp.setFromMap(map, false);

        assertEquals("First name should be copied","Joe",newEmp.getFirstName());
        assertEquals("Last name should be copied","Johnson",newEmp.getLastName());
    }
}
