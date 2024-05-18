/*
 * Copyright 2015-2024 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.platform.commons.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apiguardian.api.API;

/**
 * {@code Nullable} is used to signal that an element may be {@code null}.
 * <p>
 * Specifically, this annotation indicates that it's perfectly valid to return
 * (for methods) or hold (parameters, local variables, and fields) {@code null}.
 * <p>
 * This annotation serves as a hint for static analysis tools and IDEs to detect potential runtime errors
 * and as documentation for the element contract.
 *
 * @since 1.11
 */
@API(status = API.Status.INTERNAL, since = "1.11")
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE })
public @interface Nullable {

	/**
	 * The reason or scenarios in which {@code null} is used.
	 */
	String value() default "";

}
