package com.example.sportsbettest.validation;

/**
 * General Interface to validate for any type of the input
 * @param <T> the type of the inputs to validate
 */
public interface Validator<T> {
 void validate(T input);
}
