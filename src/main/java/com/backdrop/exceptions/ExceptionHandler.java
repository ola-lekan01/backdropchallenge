package com.backdrop.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(@NotNull Throwable ex, @NotNull DataFetchingEnvironment env) {
        ErrorType type;
        if (ex instanceof DataIntegrityViolationException) type = ErrorType.BAD_REQUEST;
        else if(ex instanceof IOException) type = ErrorType.BAD_REQUEST;
        else type = ErrorType.INTERNAL_ERROR;

        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(type)
                .build();
    }
}
