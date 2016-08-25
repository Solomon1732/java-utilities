/*******************************************************************************
 * Copyright (c) 2016 Shlomi Reuveni.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.sol.util.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Optional;

/**
 * This class is a container for soft references.
 * Similarly to {@link ReferenceContainer}, this class defers from
 * {@link SoftReference} in that this class's {@code #get()} method return an
 * {@link Optional}, instead of returning the contained object instance or
 * returning {@code null} if the object has been collected by the garbage
 * collector.
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @param <T> - type of contained objects
 * @see Reference
 */
public final class SoftReferenceContainer<T> extends ReferenceContainer<T> {

	/**
	 * Creates a new soft reference that refers to the given object. The new
	 * reference is not registered with any queue.
	 * @param referent - object the new soft reference will refer to
	 */
	public SoftReferenceContainer(final T referent) {
		super(new SoftReference<>(referent));
	}

	/**
	 * Creates a new soft reference that refers to the given object and is
	 * registered with the given queue.
	 * @param referent - object the new soft reference will refer to
	 * @param queue - the queue with which the reference is to be registered,
	 * or {@code null} if registration is not required
	 */
	public SoftReferenceContainer(T referent, ReferenceQueue<? super T> queue) {
		super(new SoftReference<>(referent, queue));
	}

}
