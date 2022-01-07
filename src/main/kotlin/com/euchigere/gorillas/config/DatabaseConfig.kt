package com.euchigere.gorillas.config

import com.euchigere.gorillas.converter.DeliveryReadConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.H2Dialect

/**
 * @author Emmanuel Chigere
 */
@Configuration
class DatabaseConfig {
    @Bean
    fun customConversions(): R2dbcCustomConversions? {
        val converters: MutableList<Converter<*, *>> = ArrayList()
        converters.add(DeliveryReadConverter())
        return R2dbcCustomConversions.of(H2Dialect.INSTANCE, converters)
    }
}