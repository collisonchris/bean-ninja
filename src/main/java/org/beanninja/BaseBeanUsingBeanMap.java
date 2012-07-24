package org.beanninja;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;

public class BaseBeanUsingBeanMap {
    @SuppressWarnings("unchecked")
    public Map<String, Object> asMap() {
        Map<String, Object> map = new HashMap<String, Object>(new BeanMap(this));
        map.remove("class");//filter class out of the map
        return map;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T fromMap(Map<String, Object> map) {
        BeanMap beanMap = new BeanMap(this);
        beanMap.putAll(map);
        return (T)beanMap.getBean();
    }
}
