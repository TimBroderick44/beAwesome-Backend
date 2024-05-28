package io.nology.todo_app.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    public <T> NotFoundException(Class<T> entityType, Long id) {
        super(String.format("Could not find %s with id %s", entityType.getSimpleName(), id), HttpStatus.NOT_FOUND);
    }
}