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
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.Optional;

/**
 * Abstract base class for reference containers. This class defers from the
 * reference classes in package {@link java.lang.ref} in that this class
 * return an {@link Optional} object, instead of returning the contained instance,
 * or returning {@code null} if the object has been collected by the garbage
 * collector.
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @param <T> - type of contained objects
 * @see Reference
 * @see SoftReference
 * @see WeakReference
 */
public abstract class ReferenceContainer<T> {

	/*
	 * This is a stored {@code Reference} instance.
	 */
	private final Reference<T> reference;

	/**
	 * Stores the given reference in a new instance of this class
	 * @param reference - the reference to be stored
	 */
	protected ReferenceContainer(final Reference<T> reference) {
		this.reference = Objects.requireNonNull(reference);
	}

	/**
	 * Clears this reference object. Invoking this method will not cause this
	 * object to be enqueued.
	 * 
	 * This method is invoked only by Java code; when the garbage collector
	 * clears references it does so directly, without invoking this method.
	 */
	public void clear() {
		reference.clear();
	}

	/**
	 * Adds this reference object to the queue with which it is registered, if any.
	 * 
	 * This method is invoked only by Java code; when the garbage collector
	 * enqueues references it does so directly, without invoking this method.
	 * @return true if this reference object was successfully enqueued; false
	 * if it was already enqueued or if it was not registered with a queue when
	 * it was created
	 */
	public boolean enqueue() {
		return reference.enqueue();
	}

	/**
	 * Returns this reference object's referent contained in an {@code Optional}
	 * @return an {@code Optional} instance of the referenced object
	 */
	public Optional<T> get() {
		return Optional.ofNullable(reference.get());
	}

	/**
	 * Tells whether or not this reference object has been enqueued, either by
	 * the program or by the garbage collector. If this reference object was
	 * not registered with a queue when it was created, then this method will
	 * always return false.
	 * @return true if and only if this reference object has been enqueued
	 */
	public boolean isEnqueued() {
		return reference.isEnqueued();
	}

	/**
	 * {@code ReferenceContainer} classes cannot have finalize methods.
	 */
	@Override
	protected final void finalize() { }
	
}
