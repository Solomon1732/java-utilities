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
import java.util.Objects;
import java.util.Optional;

/**
 * This class is a container for soft references.
 * Similarly to {@link ReferenceContainer}, this class defers from
 * {@link ReferenceQueue} in that this class's {@code #poll()} method return an
 * {@link Optional}, instead of returning the contained object instance or
 * returning {@code null} if the objects contained within the queue have been
 * collected by the garbage collector.
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @param <T> - type of contained objects
 */
public final class ReferenceQueueContainer<T> {
	/**
	 * This is a stored {@code ReferenceQueue} object
	 */
	private final ReferenceQueue<T> queue;

	/**
	 * Constructs a new reference-object queue.
	 * @param queue - reference queue to be contained
	 */
	public ReferenceQueueContainer(final ReferenceQueue<T> queue) {
		this.queue = Objects.requireNonNull(queue, "Queue is null");
	}

	/**
	 * Polls this queue to see if a reference object is available. If one is
	 * available without further delay then it is removed from the queue and
	 * returned. Otherwise this method immediately returns an empty
	 * {@code Optional} instance.
	 * @return An {@code Optional} instance containing a reference object, if
	 * one was immediately available, otherwise an empty {@code Optional}
	 */
	public Optional<Reference<? extends T>> poll() {
		return Optional.ofNullable(queue.poll());
	}

	/**
	 * Removes the next reference object in this queue, blocking until either
	 * one becomes available or the given timeout period expires. This method
	 * does not offer real-time guarantees: It schedules the timeout as if by
	 * invoking the {@link #wait(long)} method.
	 * @param timeout - if positive, block for up to timeout milliseconds while
	 * waiting for a reference to be added to this queue. If zero, block
	 * indefinitely.
	 * @return An {@code Optional} instance containing a reference object, if
	 * one was immediately available, otherwise an empty {@code Optional}
	 * @throws IllegalArgumentException - if the value of the timeout argument
	 * is negative
	 * @throws InterruptedException - If the timeout wait is interrupted
	 */
	public Reference<? extends T> remove(final long timeout)
			throws IllegalArgumentException, InterruptedException {
		return queue.remove(timeout);
	}

	/**
	 * Removes the next reference object in this queue, blocking until one
	 * becomes available.
	 * @return A reference object, blocking until one becomes available
	 * @throws InterruptedException - if the wait is interrupted
	 */
	public Reference<? extends T> remove() throws InterruptedException {
		return queue.remove();
	}

}
