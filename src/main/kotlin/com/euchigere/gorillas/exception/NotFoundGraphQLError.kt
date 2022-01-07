package com.euchigere.gorillas.exception

import graphql.ErrorClassification
import graphql.ErrorType
import graphql.GraphQLError
import graphql.language.SourceLocation

/**
 * @author Emmanuel Chigere
 */
class NotFoundGraphQLError(private val message: String): GraphQLError {
    override fun getMessage(): String {
        return message
    }

    override fun getLocations(): MutableList<SourceLocation>? {
        return null;
    }

    override fun getErrorType(): ErrorClassification {
        return ErrorType.DataFetchingException
    }
}