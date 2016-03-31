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

package org.ff4j.spring.boot.constants;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
public class CommonConstantsTest {
    @Test
    public void constructorInvocation() {
        try {
            final Constructor<CommonConstants> c = CommonConstants.class.getDeclaredConstructor();
            c.setAccessible(true);
            final CommonConstants newInstance = c.newInstance();
            assertThat(newInstance).isNull();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            assertThat(e).hasCauseExactlyInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Test
    public void constantValuesTest() {
        assertThat(CommonConstants.ROOT).isEqualTo("/");
        assertThat(CommonConstants.N_A).isEqualTo("N/A");
        assertThat(CommonConstants.HTML_WHITE).isEqualTo("FFFFFF");
    }
}
