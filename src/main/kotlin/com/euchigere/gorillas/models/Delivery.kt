package com.euchigere.gorillas.models

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

/**
 * @author Emmanuel Chigere
 */
@GraphQLDescription("Delivery records")
@Table("delivery")
data class Delivery(
    @Id
    var id: Int?,
    val deliveryId: Int,
    val product: String,
    val supplier: String,
    val quantity: Int,
    val expectedDate: Instant,
    val expectedWarehouse: String,
    @org.springframework.data.annotation.Transient
    val deliveryStatus: DeliveryStatus?
)
