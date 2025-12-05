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

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Enables beans only when the application runs in disaster recovery read-only mode.
 * <p>
 * Read-only mode becomes active when either the {@code app.read-only} property is set to
 * {@code true} or when the {@code dr} Spring profile is enabled.
 */
class ReadOnlyModeCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment environment = context.getEnvironment();
		boolean readOnlyFlag = environment.getProperty("app.read-only", Boolean.class, Boolean.FALSE);
		boolean drProfileEnabled = Arrays.asList(environment.getActiveProfiles()).contains("dr");
		return readOnlyFlag || drProfileEnabled;
	}

}
