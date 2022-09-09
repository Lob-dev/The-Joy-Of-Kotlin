package com.streaming.sample.interfaces

import com.streaming.sample.domain.VideoToHlsFormatConvertor
import org.springframework.core.io.FileUrlResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/videos")
class VideoAPI(
    val videoConvertor: VideoToHlsFormatConvertor
) {

    @GetMapping("/{filename}", produces = ["application/x-mpegURL"])
    fun streamingByVideo(@PathVariable filename: String): ResponseEntity<ByteArray?> {
        val bytes: ByteArray? = FileUrlResource("$FILE_PATH/${filename}").inputStream.use {
            it.readAllBytes()
        }
        return ResponseEntity.ok().body(bytes)
    }

    @GetMapping("/{filename}/encode")
    fun convertVideoToHlsFormat(@PathVariable filename: String) {
        videoConvertor.convert(FILE_PATH, filename)
    }

    companion object {
        const val FILE_PATH = "/data/etc"
    }
}