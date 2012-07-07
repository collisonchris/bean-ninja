package org.beanninja;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class BaseBean {
    @SuppressWarnings("rawtypes")
    private static LoadingCache<Class, List<PropertyDescriptor>> propertyCache=CacheBuilder.newBuilder().build(CacheLoader.from(new PropertyDescriptorLookup()));
    @SuppressWarnings("rawtypes")
    private static LoadingCache<Class, HashMap<String,PropertyDescriptor>> propertyHash=CacheBuilder.newBuilder().build(CacheLoader.from(new PropertyDescriptorHashLookup()));
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
    
    
    public void setFromMap(final Map<String, String> map, boolean update) throws Exception {
        Map<String, String> copy = Maps.newHashMap();
        copy.putAll(map);
        
        Iterator<Entry<String, String>> iterator = copy.entrySet().iterator();
        Field field;
        try {
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();

                try {
                    Method setMethod = findSetMethod(key);           
                    if (setMethod != null) {
                        field = this.getClass().getDeclaredField(key);
                        if (update || StringUtils.isNotEmpty(value)) {
                            Object convertedValue = ConvertUtils.convert(value, field.getType());
                            setMethod.invoke(this, convertedValue);
                            //field.set(this, convertedValue);
                        }
                        iterator.remove();
                    }
                }
                catch (Exception e) {
                    //probably a conversion error
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Method findSetMethod(String key) throws ExecutionException {
        PropertyDescriptor pDesc = null;
        try {
            pDesc = propertyHash.get(this.getClass()).get(key);
        } catch (ExecutionException e) {
            throw e;
        }
        return pDesc.getWriteMethod();
    }
    
    @SuppressWarnings("rawtypes")
    private static class PropertyDescriptorLookup implements Function<Class, List<PropertyDescriptor>> {
        public List<PropertyDescriptor> apply(Class clazz) {
            return Arrays.asList(PropertyUtils.getPropertyDescriptors(clazz));
        }
    }
    
    @SuppressWarnings("rawtypes")
    private static class PropertyDescriptorHashLookup implements Function<Class, HashMap<String,PropertyDescriptor>> {
        public HashMap<String,PropertyDescriptor> apply(Class clazz) {
            HashMap<String,PropertyDescriptor> map = Maps.newHashMap();
            for(PropertyDescriptor pDesc: PropertyUtils.getPropertyDescriptors(clazz)) {
                map.put(pDesc.getName(), pDesc);
            }
            return map;
        }
    }
}
