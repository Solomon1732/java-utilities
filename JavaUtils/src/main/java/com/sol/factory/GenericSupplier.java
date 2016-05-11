package com.sol.factory;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import com.google.common.base.Supplier;

/**
 * This is a generic supplier class. It is possible to use it as-is, but it's
 * recommended to wrap it inside a specific {@code Supplier} class.
 * While it is possible to use this class for suppliers that require input
 * (by taking advantage of lambdas, for example), it is recommended to use
 * the class {@link GenericFactory}.
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @param <K> - key that is associated with the instance function
 * @param <T> - output of the supplier
 */
public class GenericSupplier<K, T> {
	//The hashmap containing the suppliers
	private final HashMap<K, Supplier<T>> supplier = new HashMap<>();

	/**
	 * Associates the specified function with the specified key in this supplier.
	 * If the supplier previously contained a mapping for the key, the old function
	 * is replaced.
	 * This class does not accept null values for mappings.
	 * @param key - key with which the specified function is to be associated
	 * @param function - function to be associated with the specified key
	 * @return An {@link Optional} object containing the previous value
	 * associated with key, or {@code null} if this is the first mapping of the
	 * key.
	 * @throws NullPointerException in case the key and/or the function is
	 * {@code null}
	 */
	public Optional<Supplier<T>> put(K key, Supplier<T> supplier)
			throws NullPointerException {

		Objects.requireNonNull(key);
		Objects.requireNonNull(supplier);
		return Optional.ofNullable(this.supplier.put(key, supplier));
	}

	/**
	 * Removes the mapping for the specified key from this map if present.
	 * @param key - key whose mapping is to be removed from the supplier
	 * @return An {@link Optional} object containing the previous value
	 * associated with key, or {@code null} if there was no mapping for key.
	 * @throws NullPointerException if the key is {@code null}
	 */
	public Optional<Supplier<T>> remove(K key) {
		Objects.requireNonNull(key);
		return Optional.ofNullable(supplier.remove(key));
	}

	/**
	 * Get a new instance of the object produced by the stored supplier.
	 * @param key - key associated with the instance supplier
	 * @return a new instance of the object produced by the stored supplier.
	 * @throws NullPointerException if the key is {@code null}
	 */
	public T newInstance(K key) {
		return supplier.get(Objects.requireNonNull(key)).get();
	}
}
