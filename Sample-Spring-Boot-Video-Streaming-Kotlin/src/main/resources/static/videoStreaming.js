const video = document.querySelector("video");
const input = document.querySelector("input");
input.addEventListener("change", (event) => {
    let filename = event.target.value;
    let videoSrc = `/api/videos/${filename}.m3u8`;

    if (Boolean(filename)) {
        if (Hls.isSupported()) {
            const hls = new Hls();
            hls.attachMedia(video);

            hls.on(Hls.Events.MEDIA_ATTACHED, () => {
                hls.loadSource(videoSrc);

                hls.on(Hls.Events.MANIFEST_PARSED, () => {
                    video.play();
                })
            })
        } else if (video.canPlayType('application/vnd.apple.mpegurl')) {
            video.src = videoSrc;
        }
    }
})