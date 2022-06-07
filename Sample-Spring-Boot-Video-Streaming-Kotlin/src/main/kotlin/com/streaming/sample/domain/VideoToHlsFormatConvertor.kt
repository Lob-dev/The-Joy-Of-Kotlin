package com.streaming.sample.domain

import net.bramp.ffmpeg.FFmpeg
import net.bramp.ffmpeg.FFmpegExecutor
import net.bramp.ffmpeg.FFprobe
import net.bramp.ffmpeg.builder.FFmpegBuilder
import org.springframework.stereotype.Service

@Service
class VideoToHlsFormatConvertor {

    var ffmpeg: FFmpeg = FFmpeg("C:/Program Files/ffmpeg/bin/ffmpeg")
    var ffmprobe: FFprobe = FFprobe("C:/Program Files/ffmpeg/bin/ffmprobe")
    var executor: FFmpegExecutor = FFmpegExecutor(ffmpeg, ffmprobe)

    fun convert(filepath: String, filename: String) {
        val builder: FFmpegBuilder = FFmpegBuilder()
            .setInput("$filepath/$filename.mp4")
            .addOutput("$filepath/${filename}.m3u8")
            .addExtraArgs("-profile:v", "baseline")
            .addExtraArgs("-level", "3.0")
            .addExtraArgs("-start_number", "0") // default: 0
            .addExtraArgs("-hls_time", "10") // segment length by seconds. default: 2
            .addExtraArgs("-hls_list_size", "0") // set max play items. default: 0 (size > 0 => use sliding window algorithm)
            .addExtraArgs("-f", "hls")
            .done();
        executor.createJob(builder).run()
    }
}