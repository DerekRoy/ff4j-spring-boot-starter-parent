/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2013-2016 the original author or authors.
 */

package org.ff4j.services.monitoring;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.ff4j.services.AbstractStepDef;
import org.ff4j.services.MonitoringServices;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
public class MonitoringServicesStepDef extends AbstractStepDef {

    @Autowired
    private MonitoringServices monitoringServices;

    @Given("^the feature store is cleared$")
    public void the_feature_store_is_cleared() {
        clearFeatureStore();
    }

    @Given("^the following features exists in the feature store$")
    public void the_following_features_exists_in_the_feature_store(List<FeaturePojo> features) {
        createFeatures(features);
    }

    @Given("^the property store is cleared$")
    public void the_property_store_is_cleared() {
        clearPropertyStore();
    }

    @Given("^the following properties exists in the property store$")
    public void the_following_properties_exists_in_the_property_store(List<PropertyPojo> properties) {
        createProperties(properties);
    }

    @When("^the user requests for the feature monitoring information$")
    public void the_user_requests_for_the_feature_monitoring_information() {
        actualResponse = monitoringServices.getMonitoringStatus();
    }

    @Then("^the user gets the response as$")
    public void the_user_gets_the_response_as(String expectedResponse) throws JSONException {
        assertLenientResponse(expectedResponse);
    }
}


