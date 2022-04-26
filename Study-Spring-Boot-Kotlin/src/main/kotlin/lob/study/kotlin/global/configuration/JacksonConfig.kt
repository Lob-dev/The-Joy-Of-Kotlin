package lob.study.kotlin.global.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JacksonConfig {

    @Bean
    fun jacksonCustomizer(): Jackson2ObjectMapperBuilderCustomizer = Jackson2ObjectMapperBuilderCustomizer {
            jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.featuresToDisable(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    DeserializationFeature.ACCEPT_FLOAT_AS_INT,
                    SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
                )
    }
}
