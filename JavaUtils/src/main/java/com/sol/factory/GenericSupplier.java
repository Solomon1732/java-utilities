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
package com.sol.factory;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

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
public final class GenericSupplier<K, T> {
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
	public Optional<Supplier<T>> put(final K key, final Supplier<T> supplier)
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
	public Optional<Supplier<T>> remove(final K key) {
		Objects.requireNonNull(key);
		return Optional.ofNullable(supplier.remove(key));
	}

	/**
	 * Get a new instance of the object produced by the stored supplier.
	 * @param key - key associated with the instance supplier
	 * @return a new instance of the object produced by the stored supplier.
	 * @throws NullPointerException if the key is {@code null}
	 */
	public T newInstance(final K key) {
		return supplier.get(Objects.requireNonNull(key)).get();
	}
}
