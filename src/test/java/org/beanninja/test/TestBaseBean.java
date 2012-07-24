package org.beanninja.test;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.beanninja.testobjects.Employee;
import org.beanninja.testobjects.EmployeeType;
import org.beanninja.testobjects.Person;
import org.junit.Test;

import com.google.common.collect.Maps;


public class TestBaseBean {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    
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
    
    @Test public void testBeanMapGet() throws ParseException {
        Person person = new Person("George", "Washington", true, df.parse("02/22/1732"));
        Map<String, Object> map = person.asMap();
        assertEquals("Should be 4 entries!",5,map.size());
    }
    
    @Test public void testBeanMapSet() throws ParseException {
        Map<String, Object> map = Maps.newHashMap();
        map.put("lastName", "Johnson");
        map.put("firstName", "Joe");
        map.put("deceased", Boolean.FALSE);
        map.put("dateOfBirth", "09/12/1912");
        map.put("personId", 4);
        
        Person person = new Person();
        person.fromMap(map);
        
        assertEquals("First name should be copied","Joe",person.getFirstName());
        assertEquals("Last name should be copied","Johnson",person.getLastName());
        assertEquals("Deceased should be copied",false,person.isDeceased());
        assertEquals("Date of Birth should be copied",df.parse("09/12/1912"),person.getDateOfBirth());
        assertEquals("PersonId should be copied!", (Integer)4, person.getPersonId());
    }
}
