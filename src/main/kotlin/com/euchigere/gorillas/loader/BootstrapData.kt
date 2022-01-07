package com.euchigere.gorillas.loader

import com.euchigere.gorillas.models.Delivery
import com.euchigere.gorillas.models.DeliveryStatus
import com.euchigere.gorillas.repository.DeliveryRepository
import com.euchigere.gorillas.repository.DeliveryStatusRepository
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.stream.Collectors

/**
 * @author Emmanuel Chigere
 */
@Component
class BootstrapData(
    val deliveryRepo: DeliveryRepository,
    val deliveryStatusRepo: DeliveryStatusRepository,
) {
    var alreadySetup: Boolean = false

    @EventListener
    @Transactional
    fun onApplicationEvent(event: ContextRefreshedEvent): Mono<Void> {
        if (alreadySetup) return Mono.empty()
        val deliveries: List<Delivery> = loadDeliveries()
        try {
            val deliveryStatus: Flux<DeliveryStatus> = deliveryRepo.saveAll(deliveries)
                .flatMap { deliveryStatusRepo.save(DeliveryStatus(null, it.deliveryId, false, null)) }
            alreadySetup = true
            return deliveryStatus.then()
        } catch (e: Exception) {}
        return Mono.empty()
    }

    fun loadDeliveries(): List<Delivery> {
        val stream: InputStream = ClassPathResource("deliveries.json").inputStream
        val jsonString: String = stream.use {
            val reader = BufferedReader(InputStreamReader(stream))
            reader.lines().collect(Collectors.joining("\n"))
        }
        val mapper = jacksonObjectMapper().registerModule(JavaTimeModule())
        return mapper.readValue(jsonString)
    }
}