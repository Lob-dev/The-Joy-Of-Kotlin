package com.pptx.sample.pptx.domain.service

import com.pptx.sample.pptx.api.PowerPointResponse
import com.pptx.sample.pptx.domain.configuration.PowerPointMetaProperty
import com.pptx.sample.pptx.domain.utils.generateFilename
import com.pptx.sample.pptx.domain.utils.convertImage
import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.apache.poi.xslf.usermodel.XSLFSlide
import org.apache.poi.xslf.usermodel.XSLFTextShape
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class PowerPointService(property: PowerPointMetaProperty) {

    val filepath = property.imageStoragePath;

    fun extractAndConvertImage(multipartFile: MultipartFile): MutableList<PowerPointResponse> {
        val responses: MutableList<PowerPointResponse> = mutableListOf()

        XMLSlideShow(multipartFile.inputStream).use { slideShow ->
            (0 until slideShow.slides.size).forEach { index ->
                val shapes: XSLFSlide = slideShow.slides[index]

                val filename = generateFilename(index)
                convertImage(slideShow, shapes, filename, filepath, "png")

                val response = PowerPointResponse(index, filename, filepath)
                shapes.notes.forEach { shape ->
                    when (shape) {
                        is XSLFTextShape -> response.sentence = shape.textParagraphs.joinToString { it.text }
                    }
                }
                responses.add(response)
            }
            return responses;
        }
    }
}