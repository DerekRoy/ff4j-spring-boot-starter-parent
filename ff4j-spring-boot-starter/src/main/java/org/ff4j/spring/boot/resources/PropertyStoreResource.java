package org.ff4j.spring.boot.resources;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ff4j.spring.boot.constants.CommonConstants;
import org.ff4j.spring.boot.constants.FeatureConstants;
import org.ff4j.spring.boot.domain.CacheApiBean;
import org.ff4j.spring.boot.domain.PropertyApiBean;
import org.ff4j.spring.boot.domain.PropertyStoreApiBean;
import org.ff4j.spring.boot.services.PropertyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.ff4j.web.FF4jWebConstants.RESOURCE_CACHE;
import static org.ff4j.web.FF4jWebConstants.STORE_CLEAR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
@RestController
@RequestMapping(value = FeatureConstants.RESOURCE_FF4J_PROPERTY_STORE)
public class PropertyStoreResource {

    @Autowired
    private PropertyStoreService propertyStoreService;

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Display information regarding <b>Properties Store</b>", response = PropertyStoreApiBean.class)
    @ApiResponses(@ApiResponse(code = 200, message = "status of current properties store"))
    public PropertyStoreApiBean getPropertyStore() {
        return propertyStoreService.getPropertyStore();
    }

    @RequestMapping(value = FeatureConstants.RESOURCE_PROPERTIES, method = GET, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Display all the <b>Properties</b>", response = PropertyApiBean.class)
    @ApiResponses(@ApiResponse(code = 200, message = "get all Properties"))
    public List<PropertyApiBean> getAllProperties() {
        return propertyStoreService.getAllProperties();
    }

    @RequestMapping(value = CommonConstants.ROOT + STORE_CLEAR, method = DELETE)
    @ApiOperation(value = "Delete all <b>Properties</b> in store")
    @ApiResponses(@ApiResponse(code = 204, message = "all properties have been deleted", response = ResponseEntity.class))
    public ResponseEntity deleteAllProperties() {
        propertyStoreService.deleteAllProperties();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = CommonConstants.ROOT + RESOURCE_CACHE, method = GET, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Display information related to <b>Cache</b>")
    @ApiResponses({@ApiResponse(code = 200, message = "Gets the cached properties", response = CacheApiBean.class),
            @ApiResponse(code = 404, message = "property store is not cached")})
    public CacheApiBean getPropertiesFromCache() {
        return propertyStoreService.getPropertiesFromCache();
    }

    @RequestMapping(value = FeatureConstants.RESOURCE_CLEAR_CACHE, method = DELETE)
    @ApiOperation(value = "Clear cache", response = ResponseEntity.class)
    @ApiResponses({@ApiResponse(code = 204, message = "cache is cleared"),
            @ApiResponse(code = 404, message = "property store is not cached")})
    public ResponseEntity clearCachedPropertyStore() {
        propertyStoreService.clearCachedPropertyStore();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
