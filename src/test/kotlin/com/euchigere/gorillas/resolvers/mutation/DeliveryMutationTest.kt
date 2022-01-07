package com.euchigere.gorillas.resolvers.mutation

import com.euchigere.gorillas.exception.NotFoundGraphQLError
import com.euchigere.gorillas.models.DeliveryStatus
import com.euchigere.gorillas.repository.DeliveryStatusRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Order(2)
class DeliveryMutationTest(
    @Autowired val deliveryMutation: DeliveryMutation,
    @Autowired val deliveryStatusRepo: DeliveryStatusRepository
) {
    @Test
    fun shouldMarkDeliveryAsReceived() {
        checkDeliveryStatusIsUpdated(101)
        checkDeliveryStatusIsUpdated(106)
    }

    @Test
    fun shouldHandleWrongDeliveryId() {
        runBlocking {
            val result = deliveryMutation.markDelivery(10)
            Assertions.assertNotNull(result)
            Assertions.assertTrue(result.hasErrors())
            Assertions.assertNull(result.data)
            Assertions.assertEquals(1, result.errors.size)
            Assertions.assertTrue(result.errors[0] is NotFoundGraphQLError)
        }
    }

    private fun checkDeliveryStatusIsUpdated(deliveryId: Int) {
        val status: DeliveryStatus = deliveryStatusRepo.findByDeliveryId(deliveryId).block()!!
        Assertions.assertNotNull(status)
        Assertions.assertFalse(status.isDelivered)

        runBlocking {
            val result = deliveryMutation.markDelivery(deliveryId)
            Assertions.assertNotNull(result)
            Assertions.assertFalse(result.hasErrors())
            Assertions.assertNotNull(result.data)
            Assertions.assertTrue(result.data?.isDelivered!!)
        }
    }
}