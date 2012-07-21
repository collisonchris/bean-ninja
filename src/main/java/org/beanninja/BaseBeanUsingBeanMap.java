package org.beanninja;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;

public class BaseBeanUsingBeanMap {
    @SuppressWarnings("unchecked")
    public Map<String, String> asMap() {
        BeanMap beanMap = new BeanMap(this);
        Map<String, String> map = new HashMap<String, String>(beanMap);
        map.remove("class");//filter class out of the map
        return map;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T fromMap(Map<String, String> map) {
        BeanMap beanMap = new BeanMap(this);
        beanMap.putAll(map);
        return (T)beanMap.getBean();
    }
}