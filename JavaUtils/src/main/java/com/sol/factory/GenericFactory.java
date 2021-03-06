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
import java.util.function.Function;

/**
 * This is a generic factory class. It is possible to use it as-is, but it's
 * recommended to wrap it inside a specific {@code Factory} class.
 * While it is possible to use this class for factories that do not require
 * input, it is recommended to use {@link GenericSupplier}.
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @since Apr 22 2016
 * @param <K> - key that is associated with the instance function
 * @param <T> - input type of the function
 * @param <R> - output type of the function
 */
public final class GenericFactory<K, T, R> {
	//The hashmap containing the functions
	private final HashMap<K, Function<T, R>> factory = new HashMap<>();

	/**
	 * Associates the specified function with the specified key in this factory.
	 * If the factory previously contained a mapping for the key, the old function
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
	public Optional<Function<T, R>> put(final K key, final Function<T, R> function)
			throws NullPointerException {

		Objects.requireNonNull(function);
		Objects.requireNonNull(key);
		return Optional.ofNullable(factory.put(key, function));
	}

	/**
	 * Removes the mapping for the specified key from this map if present.
	 * @param key - key whose mapping is to be removed from the factory
	 * @return An {@link Optional} object containing the previous value
	 * associated with key, or {@code null} if there was no mapping for key.
	 * @throws NullPointerException if the key is {@code null}
	 */
	public Optional<Function<T, R>> remove(final K key)
			throws NullPointerException {
		Objects.requireNonNull(key);
		return Optional.ofNullable(factory.remove(key));
	}

	/**
	 * Get a new instance of the object produced by the stored function.
	 * @param key - key associated with the instance function
	 * @param input - input of the function
	 * @return a new instance of the object produced by the stored function.
	 * @throws NullPointerException if the key is {@code null}
	 */
	public R newInstance(final K key, final T input) throws NullPointerException {
		return factory.get(Objects.requireNonNull(key)).apply(input);
	}

}
