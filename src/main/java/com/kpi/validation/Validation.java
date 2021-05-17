package com.kpi.validation;

public interface Validation<T> {
    ValidationResult validate(T data);
}
