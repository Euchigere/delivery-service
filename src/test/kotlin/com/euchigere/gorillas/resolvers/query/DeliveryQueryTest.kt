package com.euchigere.gorillas.resolvers.query

import com.euchigere.gorillas.service.DeliveryService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Order(1)
class DeliveryQueryTest(
    @Autowired val deliveryQuery: DeliveryQuery,
    @Autowired val service: DeliveryService
) {

    @BeforeAll
    fun setup() {
        service.markDeliveryAsDelivered(105).block()
        service.markDeliveryAsDelivered(108).block()
    }

    @Test
    fun shouldListDeliveriesNotYetReceived() {
        runBlocking {
            val result = deliveryQuery.deliveries(false)
            Assertions.assertNotNull(result)
            Assertions.assertEquals(6, result.size)
            Assertions.assertNotNull(result.find { it.deliveryId == 101 })
            Assertions.assertNull(result.find { it.deliveryId == 105 })
            Assertions.assertNull(result.find { it.deliveryId == 108 })
        }
    }

    @Test
    fun shouldListDeliveriesMarkedAsReceived() {
        runBlocking {
            val result = deliveryQuery.deliveries(true)
            Assertions.assertNotNull(result)
            Assertions.assertEquals(2, result.size)
            Assertions.assertNull(result.find { it.deliveryId == 101 })
            Assertions.assertNotNull(result.find { it.deliveryId == 105 })
            Assertions.assertNotNull(result.find { it.deliveryId == 108 })
        }
    }

}