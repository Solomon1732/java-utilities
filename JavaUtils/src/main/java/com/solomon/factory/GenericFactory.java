package com.solomon.factory;

import java.util.HashMap;
import java.util.Objects;

import com.google.common.base.Function;

/**
 * This is a generic factory class. It is possible to use it as-is, but it's
 * recommended to wrap it inside a specific {@code Factory} class.
 * @author Shlomi Reuveni
 * @since Apr 22 2016
 * @param <K> - the key that is associated with the instance function
 * @param <T> - the input type of the function
 * @param <R> - the output type of the function
 */
public class GenericFactory<K, T, R> {
	//The hashmap containing the functions
	private HashMap<K, Function<T, R>> factory = new HashMap<>();

	/**
	 * Associates the specified function with the specified key in this factory.
	 * If the factory previously contained a mapping for the key, the old function
	 * is replaced.
	 * Since this is a factory, this class does not accept null values for mappings.
	 * @param key - key with which the specified function is to be associated
	 * @param function - function to be associated with the specified key
	 * @return the previous value associated with key, or null if there was no
	 * mapping for key.
	 * @throws NullPointerException in case the key and/or the function is
	 * {@code null}
	 */
	public Function<T, R> put(K key, Function<T, R> function)
			throws NullPointerException {
		Objects.requireNonNull(function);
		Objects.requireNonNull(key);
		return factory.put(key, function);
	}

	/**
	 * Removes the mapping for the specified key from this map if present.
	 * @param key - key whose mapping is to be removed from the factory
	 * @return the previous value associated with key, or null if there was no
	 * mapping for key.
	 */
	public Function<T, R> remove(K key) {
		return factory.remove(key);
	}

	/**
	 * Get a new instance of the object produced by the stored function.
	 * @param key - the key associated with the instance function
	 * @param input - the input of the function
	 * @return a new instance of the object produced by the stored function.
	 * @throws NullPointerException if the key is {@code null}
	 */
	public R newInstance(K key, T input) throws NullPointerException {
		return factory.get(Objects.requireNonNull(key)).apply(input);
	}
	
}
