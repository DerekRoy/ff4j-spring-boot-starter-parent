package org.ff4j.spring.boot.constants;

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
public final class FeatureConstants {
    // PATH PARAM
    public static final String PATH_PARAM_GROUP = "{groupName}";
    public static final String PATH_PARAM_UID = "{uid}";
    public static final String PATH_PARAM_ROLE = "{role}";
    public static final String PATH_PARAM_NAME = "{name}";
    public static final String PATH_PARAM_VALUE = "{value}";
    // PARAM
    public static final String PARAM_ROLE = "role";
    public static final String PARAM_GROUP = "groupName";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_VALUE = "value";
    // RESOURCE
    public static final String RESOURCE_FF4J = CommonConstants.ROOT + "ff4j";
    public static final String RESOURCE_STORE = CommonConstants.ROOT + "store";
    public static final String RESOURCE_FEATURES = CommonConstants.ROOT + "features";
    public static final String RESOURCE_FF4J_STORE_FEATURES = RESOURCE_FF4J + RESOURCE_STORE + RESOURCE_FEATURES;
    public static final String RESOURCE_GROUPS = CommonConstants.ROOT + "groups";
    public static final String RESOURCE_FF4J_STORE_GROUPS = RESOURCE_FF4J + RESOURCE_STORE + RESOURCE_GROUPS;
    public static final String RESOURCE_PROPERTY_STORE = CommonConstants.ROOT + "propertyStore";
    public static final String RESOURCE_PROPERTIES = CommonConstants.ROOT + "properties";
    public static final String RESOURCE_PROPERTIES_STORE_PROPERTIES = RESOURCE_FF4J + RESOURCE_PROPERTY_STORE + RESOURCE_PROPERTIES;
    public static final String RESOURCE_FF4J_PROPERTY_STORE = RESOURCE_FF4J + RESOURCE_PROPERTY_STORE;
    public static final String RESOURCE_CLEAR_CACHE = CommonConstants.ROOT + "clearCache";
    public static final String RESOURCE_FF4J_STORE = RESOURCE_FF4J + RESOURCE_STORE;
    public static final String RESOURCE_FF4J_MONITORING = RESOURCE_FF4J + CommonConstants.ROOT + "monitoring";

    private FeatureConstants() {
        throw new UnsupportedOperationException();
    }
}
