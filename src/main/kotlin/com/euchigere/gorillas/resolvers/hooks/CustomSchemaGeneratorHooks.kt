package com.euchigere.gorillas.resolvers.hooks

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.CoercingSerializeException
import graphql.schema.GraphQLScalarType
import graphql.schema.GraphQLType
import org.springframework.stereotype.Component
import java.time.Instant
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Component
class CustomSchemaGeneratorHooks : SchemaGeneratorHooks {
    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier as? KClass<*>) {
        Instant::class -> graphqlInstantType
        else -> null
    }
}

val graphqlInstantType: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("Instant")
    .description("A type representing a formatted java.time.Instant")
    .coercing(InstantCoercing)
    .build()

object InstantCoercing : Coercing<Instant, String> {
    private fun parseInstant(literal: String): Instant {
        return try {
            Instant.parse(literal)
        } catch (e: Exception) {
            throw CoercingSerializeException("Could not parse Date string: $e")
        }
    }

    override fun parseValue(input: Any): Instant = parseInstant(serialize(input))

    override fun parseLiteral(input: Any): Instant {
        val dateString = (input as? StringValue)?.value
        return dateString?.let { parseInstant(it) } ?: throw CoercingSerializeException("Invalid input")
    }

    override fun serialize(dataFetcherResult: Any): String = dataFetcherResult.toString()
}