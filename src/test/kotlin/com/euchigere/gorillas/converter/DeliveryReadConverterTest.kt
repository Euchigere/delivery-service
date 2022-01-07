package com.euchigere.gorillas.converter

import com.euchigere.gorillas.models.Delivery
import com.euchigere.gorillas.repository.DeliveryRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Order(3)
class DeliveryReadConverterTest(@Autowired val deliveryRepo: DeliveryRepository) {
    @Test
    fun shouldMapDatabaseRowToDeliveryModelCorrectly() {
        var delivery: Delivery = deliveryRepo.findById(2).block()!!
        assertAll(delivery)
        Assertions.assertEquals(2, delivery.id)
        Assertions.assertNull(delivery.deliveryStatus)

        delivery = deliveryRepo.findDeliveriesByDeliveryStatus(false)
            .collectList()
            .block()?.get(0)!!
        assertAll(delivery)
        Assertions.assertNotNull(delivery.deliveryStatus)
        Assertions.assertNotNull(delivery.deliveryStatus?.id)
        Assertions.assertFalse(delivery.deliveryStatus?.isDelivered!!)
    }

    private fun assertAll(delivery: Delivery) {
        Assertions.assertNotNull(delivery)
        Assertions.assertNotNull(delivery.deliveryId)
        Assertions.assertNotNull(delivery.product)
        Assertions.assertNotNull(delivery.expectedDate)
        Assertions.assertNotNull(delivery.expectedWarehouse)
        Assertions.assertNotNull(delivery.quantity)
    }
}