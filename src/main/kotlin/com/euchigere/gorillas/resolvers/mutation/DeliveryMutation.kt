package com.euchigere.gorillas.resolvers.mutation

import com.euchigere.gorillas.exception.NotFoundException
import com.euchigere.gorillas.models.DeliveryStatus
import com.euchigere.gorillas.service.DeliveryService
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Mutation
import graphql.execution.DataFetcherResult
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component

/**
 * @author Emmanuel Chigere
 */
@Component
class DeliveryMutation(private val service: DeliveryService): Mutation {
    @GraphQLDescription("Update delivery status and mark as received")
    suspend fun markDelivery(deliveryId: Int): DataFetcherResult<DeliveryStatus?> {
        return try {
            val deliveryStatus: DeliveryStatus = service.markDeliveryAsDelivered(deliveryId).awaitSingle()
            DataFetcherResult.newResult<DeliveryStatus?>().data(deliveryStatus).build()
        } catch (e: NotFoundException) {
            DataFetcherResult.newResult<DeliveryStatus?>().error(e.getGraphQLError()).build()
        }
    }
}