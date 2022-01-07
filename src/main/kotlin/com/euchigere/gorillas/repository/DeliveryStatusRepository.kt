package com.euchigere.gorillas.repository

import com.euchigere.gorillas.models.DeliveryStatus
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/**
 * @author Emmanuel Chigere
 */
@Repository
interface DeliveryStatusRepository : ReactiveCrudRepository<DeliveryStatus, Int> {
    fun findByDeliveryId(deliveryId: Int): Mono<DeliveryStatus>
}