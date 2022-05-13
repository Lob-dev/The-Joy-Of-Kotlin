package com.pptx.sample.pptx.domain.configuration

import mu.KotlinLogging
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Configuration
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Paths
import javax.annotation.PostConstruct


@ConstructorBinding
@ConfigurationProperties("pptx")
data class PowerPointMetaProperty(val fontPath: String, val imageStoragePath: String)


@Configuration
@ConfigurationPropertiesScan
class PowerPointConfiguration(val property: PowerPointMetaProperty) {

    private val logger = KotlinLogging.logger { }

    @PostConstruct
    fun graphicsEnvironment() {
        logger.info { property }

        val environment: GraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment()
        Files.newDirectoryStream(Paths.get(property.fontPath)).use {
            it
                .filterNot { file -> Files.isDirectory(file) }
                .forEach { file ->
                    FileInputStream("${property.fontPath}/${file.fileName}").use { inputStream ->
                        environment.registerFont(Font.createFont(Font.TRUETYPE_FONT, inputStream))
                    }
                }
        }
        logger.info { "supported fonts = ${environment.allFonts.map { it.fontName }}" }
    }
}