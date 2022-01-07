package com.euchigere.gorillas.repository

import com.euchigere.gorillas.models.Delivery
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

/**
 * @author Emmanuel Chigere
 */
@Repository
interface DeliveryRepository : ReactiveCrudRepository<Delivery, Int> {
    @Query(
        "select d.*, ds.* from delivery d join delivery_status ds on d.delivery_id = ds.delivery_id " +
                "where ds.is_delivered = :isDelivered"
    )
    fun findDeliveriesByDeliveryStatus(@Param(value = "isDelivered") isDelivered: Boolean) : Flux<Delivery>
}