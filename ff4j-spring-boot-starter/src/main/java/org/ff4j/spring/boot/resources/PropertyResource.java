package org.ff4j.spring.boot.resources;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ff4j.spring.boot.constants.CommonConstants;
import org.ff4j.spring.boot.constants.FeatureConstants;
import org.ff4j.spring.boot.domain.PropertyApiBean;
import org.ff4j.spring.boot.model.FeatureActions;
import org.ff4j.spring.boot.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.ff4j.web.FF4jWebConstants.OPERATION_UPDATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
@RestController
@RequestMapping(value = FeatureConstants.RESOURCE_PROPERTIES_STORE_PROPERTIES + CommonConstants.ROOT + FeatureConstants.PATH_PARAM_NAME)
public class PropertyResource {
    @Autowired
    private PropertyService propertyService;

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Read information about a property", response = PropertyApiBean.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Information about property"),
            @ApiResponse(code = 404, message = "Property not found")})
    public PropertyApiBean getProperty(@PathVariable(value = FeatureConstants.PARAM_NAME) String propertyName) {
        return propertyService.getProperty(propertyName);
    }

    @RequestMapping(method = PUT, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create or update a property", response = ResponseEntity.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Property name is blank (or) property name did not match with the requested property name to be created or updated"),
            @ApiResponse(code = 201, message = "Property has been created"),
            @ApiResponse(code = 202, message = "Property has been updated"),
            @ApiResponse(code = 204, message = "No content, no changes made to the feature")})
    public ResponseEntity createOrUpdateProperty(@PathVariable(value = FeatureConstants.PARAM_NAME) String propertyName, @RequestBody PropertyApiBean propertyApiBean) {
        return FeatureActions.getBooleanResponseEntityByHttpStatus(propertyService.createOrUpdateProperty(propertyName, propertyApiBean));
    }

    @RequestMapping(method = DELETE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a property", response = ResponseEntity.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "No content, property is deleted"),
            @ApiResponse(code = 404, message = "Property not found")
    })
    public ResponseEntity deleteProperty(@PathVariable(value = FeatureConstants.PARAM_NAME) String propertyName) {
        propertyService.deleteProperty(propertyName);
        return new ResponseEntity(NO_CONTENT);
    }

    @RequestMapping(value = CommonConstants.ROOT + OPERATION_UPDATE + CommonConstants.ROOT + FeatureConstants.PATH_PARAM_VALUE, method = POST, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update value of a property", response = ResponseEntity.class)
    @ApiResponses({
            @ApiResponse(code = 202, message = "Property has been updated"),
            @ApiResponse(code = 404, message = "Property not found"),
            @ApiResponse(code = 400, message = "Invalid new value")})
    public ResponseEntity updatePropertyName(@PathVariable(value = FeatureConstants.PARAM_NAME) String propertyName, @PathVariable(value = FeatureConstants.PARAM_VALUE) String newPropertyName) {
        propertyService.updatePropertyName(propertyName, newPropertyName);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
