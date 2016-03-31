package org.ff4j.spring.boot.services;

import org.ff4j.FF4j;
import org.ff4j.cache.FF4jCacheProxy;
import org.ff4j.property.Property;
import org.ff4j.spring.boot.domain.PropertyApiBean;
import org.ff4j.spring.boot.domain.PropertyStoreApiBean;
import org.ff4j.spring.boot.exceptions.PropertyStoreNotCached;
import org.ff4j.spring.boot.domain.CacheApiBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
@Service
public class PropertyStoreService {

    @Autowired
    private FF4j ff4j;

    public PropertyStoreApiBean getPropertyStore() {
        return new PropertyStoreApiBean(ff4j.getPropertiesStore());
    }

    public List<PropertyApiBean> getAllProperties() {
        List<PropertyApiBean> properties;
        Map<String, Property<?>> propertyMap = ff4j.getPropertiesStore().readAllProperties();
        if (CollectionUtils.isEmpty(propertyMap)) {
            properties = new ArrayList<>(0);
        } else {
            properties = new ArrayList<>(propertyMap.size());
            properties.addAll(propertyMap.values().stream().map(PropertyApiBean::new).collect(Collectors.toList()));
        }
        return properties;
    }

    public void deleteAllProperties() {
        ff4j.getPropertiesStore().clear();
    }

    public CacheApiBean getPropertiesFromCache() {
        if (ff4j.getPropertiesStore() instanceof FF4jCacheProxy) {
            return new CacheApiBean(ff4j.getPropertiesStore());
        } else {
            throw new PropertyStoreNotCached();
        }
    }

    public void clearCachedPropertyStore() {
        if (ff4j.getPropertiesStore() instanceof FF4jCacheProxy) {
            ((FF4jCacheProxy) ff4j.getPropertiesStore()).getCacheManager().clearProperties();
        } else {
            throw new PropertyStoreNotCached();
        }
    }
}
