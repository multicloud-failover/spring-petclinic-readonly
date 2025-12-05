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

import java.io.IOException;
import java.util.Set;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Conditional(ReadOnlyModeCondition.class)
public class ReadOnlyRequestFilter extends OncePerRequestFilter {

	private static final Set<String> ALLOWED_METHODS = Set.of("GET", "HEAD", "OPTIONS");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!ALLOWED_METHODS.contains(request.getMethod())) {
			response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
			request.setAttribute("readOnlyMessage", ReadOnlyModeAdvice.READ_ONLY_MESSAGE);
			request.getRequestDispatcher("/read-only").forward(request, response);
			return;
		}

		filterChain.doFilter(request, response);
	}

}
