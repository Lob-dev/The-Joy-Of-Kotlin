package com.pptx.sample.pptx.domain.utils

import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.apache.poi.xslf.usermodel.XSLFSlide
import java.awt.Color
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import java.awt.image.BufferedImage
import java.io.FileOutputStream
import java.time.LocalDate
import java.util.*
import javax.imageio.ImageIO


fun generateFilename(index: Int) = "${LocalDate.now()}_${index}_${UUID.randomUUID()}.png"

fun convertImage(
    slideShow: XMLSlideShow,
    shapes: XSLFSlide,
    filename: String,
    filepath: String,
    extension: String
) {
    val pageSize = slideShow.pageSize
    val width = pageSize.width
    val height = pageSize.height

    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    shapes.draw(createGraphics(width, height, image))

    val location = """$filepath/$filename"""
    FileOutputStream(location).use { outStream ->
        ImageIO.write(image, extension, outStream)
        slideShow.write(outStream)
    }
}

fun createGraphics(
    width: Int,
    height: Int,
    image: BufferedImage
): Graphics2D {
    return image.createGraphics().apply {
        paint = Color.white
        fill(Rectangle2D.Float(0F, 0F, width.toFloat(), height.toFloat()))
    }
}