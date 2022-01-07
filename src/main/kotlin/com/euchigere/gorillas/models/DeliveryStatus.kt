package com.euchigere.gorillas.models

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

/**
 * @author Emmanuel Chigere
 */
@GraphQLDescription("Delivery status with meta data")
@Table("delivery_status")
data class DeliveryStatus(
    @Id
    var id: Int?,
    val deliveryId: Int,
    var isDelivered: Boolean,
    var deliveryDate: Instant?,
)