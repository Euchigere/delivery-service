package com.euchigere.gorillas.exception

import graphql.GraphQLError

/**
 * @author Emmanuel Chigere
 */
class NotFoundException(override val message: String = "Delivery not found"): RuntimeException() {
    fun getGraphQLError(): GraphQLError = NotFoundGraphQLError(message)
}