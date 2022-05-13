package com.pptx.sample.pptx.api

import com.pptx.sample.pptx.domain.service.PowerPointService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
class PowerPointController(val powerPointUseCase: PowerPointService) {

    @PostMapping("/api/pptx/extract")
    fun extractAndWriteToImage(@RequestPart multipartFile: MultipartFile) =
        powerPointUseCase.extractAndConvertImage(multipartFile)
}