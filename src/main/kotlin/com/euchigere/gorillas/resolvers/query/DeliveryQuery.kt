package com.euchigere.gorillas.resolvers.query

import com.euchigere.gorillas.models.Delivery
import com.euchigere.gorillas.service.DeliveryService
import com.expediagroup.graphql.server.operations.Query
import kotlinx.coroutines.reactive.awaitFirstOrDefault
import org.springframework.stereotype.Component

/**
 * @author Emmanuel Chigere
 */
@Component
class DeliveryQuery(private val service: DeliveryService): Query {
    suspend fun deliveries(isDelivered: Boolean) : List<Delivery> {
        return service.findDeliveriesByDeliveryStatus(isDelivered)
            .awaitFirstOrDefault(listOf())
    }
}