package org.beanninja;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;

public class BaseBeanUsingBeanMap {
    @SuppressWarnings("unchecked")
    public Map<String, String> asMap() {
        BeanMap beanMap = new BeanMap(this);
        Map<String, String> map = new HashMap<String, String>();
        for (Object o : beanMap.entrySet()) { // It's not parameterized :(
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) o;
            String key = entry.getKey();
            Object value = entry.getValue();
            if(!"class".equals(key)){//filter class out of the map
                map.put(key, value != null ? String.valueOf(value) : null);
            }
            
        }
        return map;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T fromMap(Map<String, String> map) {
        BeanMap beanMap = new BeanMap(this);
        beanMap.putAll(map);
        return (T)beanMap.getBean();
    }
}
