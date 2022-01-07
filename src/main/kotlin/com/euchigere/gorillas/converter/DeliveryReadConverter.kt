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
        val deliveryStatus = DeliveryStatus(
            null,
            source.get("delivery_id") as Int,
            source.get("is_delivered") as Boolean,
            (source.get("delivery_date") as LocalDateTime?)?.toInstant(ZoneOffset.UTC)
        )
        return Delivery(
            source.get("id") as Int,
            source.get("delivery_id") as Int,
            source.get("product") as String,
            source.get("supplier") as String,
            source.get("quantity") as Int,
            (source.get("expected_date") as LocalDateTime).toInstant(ZoneOffset.UTC),
            source.get("expected_warehouse")!! as String,
            deliveryStatus
        )
    }
}