package com.euchigere.gorillas.service

import com.euchigere.gorillas.models.Delivery
import com.euchigere.gorillas.models.DeliveryStatus
import reactor.core.publisher.Mono

/**
 * @author Emmanuel Chigere
 */
interface DeliveryService {
    fun markDeliveryAsDelivered(deliveryId: Int): Mono<DeliveryStatus>

    fun findDeliveriesByDeliveryStatus(isDelivered: Boolean): Mono<List<Delivery>>
}