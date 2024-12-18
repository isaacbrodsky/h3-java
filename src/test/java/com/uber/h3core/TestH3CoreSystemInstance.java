/*
 * Copyright 2018, 2022 Uber Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uber.h3core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test that {@link H3Core#newSystemInstance()} can load the H3 library. This test is only run if
 * the system property <code>h3.test.system</code> has the value <code>true</code>. It is expected
 * that when running this test, the JVM has been setup to find the native library, either by
 * installing it in a place it can be found, or setting the <code>java.library.path</code> system
 * property before starting the test JVM.
 */
class TestH3CoreSystemInstance {
  @BeforeAll
  static void assumptions() {
    assumeTrue(
        "true".equals(System.getProperty("h3.test.system")), "System instance tests enabled");
  }

  @Test
  void test() {
    final H3Core h3 = H3Core.newSystemInstance();
    assertNotNull(h3);
    assertEquals("84194adffffffff", h3.latLngToCellAddress(51.5008796, -0.1253643, 4));
  }
}
