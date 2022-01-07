package com.euchigere.gorillas.converter

import com.euchigere.gorillas.models.Delivery
import com.euchigere.gorillas.models.DeliveryStatus
import io.r2dbc.spi.Row
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * @author Emmanuel Chigere
 */
@ReadingConverter
class DeliveryReadConverter : Converter<Row, Delivery> {
    override fun convert(source: Row): Delivery {
        val deliveryStatus: DeliveryStatus? = try {
            DeliveryStatus(
                source["ds_id"] as Int,
                source["delivery_id"] as Int,
                source["is_delivered"] as Boolean,
                (source["delivery_date"] as LocalDateTime?)?.toInstant(ZoneOffset.UTC)
            )
        } catch (e: Exception) {
            null
        }

        return Delivery(
            source["id"] as Int,
            source["delivery_id"] as Int,
            source["product"] as String,
            source["supplier"] as String,
            source["quantity"] as Int,
            (source["expected_date"] as LocalDateTime).toInstant(ZoneOffset.UTC),
            source["expected_warehouse"] as String,
            deliveryStatus
        )
    }
}