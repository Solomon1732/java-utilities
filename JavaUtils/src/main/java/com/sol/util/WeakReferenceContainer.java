package com.sol.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Optional;

/**
 * This class is a container for weak references.
 * Similarly to {@link ReferenceContainer}, this class defers from
 * {@link WeakReference} in that this class's {@code #get()} method return an
 * {@link Optional}, instead of returning the contained object instance or
 * returning {@code null} if the object has been collected by the garbage
 * container.
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @param <T> - type of contained objects
 * @see Reference
 */
public class WeakReferenceContainer<T> extends ReferenceContainer<T> {

	/**
	 * Creates a new weak reference that refers to the given object. The new
	 * reference is not registered with any queue.
	 * @param referent - object the new soft reference will refer to
	 */
	public WeakReferenceContainer(T referent) {
		super(new WeakReference<>(referent));
	}
	
	/**
	 * Creates a new weak reference that refers to the given object and is
	 * registered with the given queue.
	 * @param referent - object the new soft reference will refer to
	 * @param queue - the queue with which the reference is to be registered,
	 * or {@code null} if registration is not required
	 */
	public WeakReferenceContainer(T referent, ReferenceQueue<? super T> queue) {
		super(new WeakReference<>(referent, queue));
	}
}
