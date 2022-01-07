package com.euchigere.gorillas.service

import com.euchigere.gorillas.exception.NotFoundException
import com.euchigere.gorillas.models.Delivery
import com.euchigere.gorillas.models.DeliveryStatus
import com.euchigere.gorillas.repository.DeliveryRepository
import com.euchigere.gorillas.repository.DeliveryStatusRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Instant

/**
 * @author Emmanuel Chigere
 */
@Service
class DeliveryServiceImpl(
    private val deliveryRepo: DeliveryRepository,
    private val deliveryStatusRepo: DeliveryStatusRepository
): DeliveryService {

    override fun markDeliveryAsDelivered(deliveryId: Int): Mono<DeliveryStatus> {
        return deliveryStatusRepo.findByDeliveryId(deliveryId)
            .switchIfEmpty(Mono.error(NotFoundException()))
            .flatMap { updateDeliveryStatus(it) }
    }

    override fun findDeliveriesByDeliveryStatus(isDelivered: Boolean): Mono<List<Delivery>> {
        return deliveryRepo.findDeliveriesByDeliveryStatus(isDelivered).collectList();
    }

    private fun updateDeliveryStatus(deliveryStatus: DeliveryStatus): Mono<DeliveryStatus> {
        if (!deliveryStatus.isDelivered) {
            deliveryStatus.isDelivered = true
            deliveryStatus.deliveryDate = Instant.now()
            return deliveryStatusRepo.save(deliveryStatus)
        }
        return Mono.just(deliveryStatus);
    }
}