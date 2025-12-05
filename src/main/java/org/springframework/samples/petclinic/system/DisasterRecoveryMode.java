/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.system;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Represents the current disaster recovery (DR) mode of the application.
 * <p>
 * Read-only mode becomes active if either the {@code dr} profile is enabled or the
 * {@code app.read-only} property is explicitly set to {@code true}. The bean is exposed
 * as {@code drMode} for convenient access from Thymeleaf templates and controllers.
 */
@Component("drMode")
public class DisasterRecoveryMode {

	private final boolean readOnly;

	public DisasterRecoveryMode(Environment environment) {
		boolean readOnlyFlag = environment.getProperty("app.read-only", Boolean.class, Boolean.FALSE);
		boolean drProfileEnabled = Arrays.asList(environment.getActiveProfiles()).contains("dr");
		this.readOnly = readOnlyFlag || drProfileEnabled;
	}

	public boolean isReadOnly() {
		return this.readOnly;
	}

}
