package org.beanninja;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class BaseBean {
    @SuppressWarnings("rawtypes")
    private static LoadingCache<Class, List<PropertyDescriptor>> propertyCache=CacheBuilder.newBuilder().build(CacheLoader.from(new PropertyDescriptorLookup()));
    private static Set<String> propertyBlackList = Sets.newHashSet("class","asMap");
    private static Set<String> packageBlackList = Sets.newHashSet("java.util.");
    
    public Map<String, String> getAsMap() {
        Map<String, String> entityMap = Maps.newTreeMap();       
        try {
            
            for (PropertyDescriptor prop: propertyCache.get(this.getClass())) {
                String property = prop.getName();

                if (isBlacklistedProperty(property) || isBlacklistedPackage(prop.getPropertyType().getName())) {
                    continue;//skip blacklisted entries
                } else {
                    Object obj = prop.getReadMethod().invoke(this);
                    entityMap.put(property, ConvertUtils.convert(obj));
                }
            }
            return entityMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    private boolean isBlacklistedProperty(String propName){
        if(propertyBlackList.contains(propName)) return true;
        return false;
    }
    private boolean isBlacklistedPackage(String className) {
        for(String pkg: packageBlackList) {
            if(className.startsWith(pkg)) {
                return true;
            }
        }
        return false;
    }
    
    @SuppressWarnings("rawtypes")
    private static class PropertyDescriptorLookup implements Function<Class, List<PropertyDescriptor>> {
        public List<PropertyDescriptor> apply(Class clazz) {
            return Arrays.asList(PropertyUtils.getPropertyDescriptors(clazz));
        }
    }
}
