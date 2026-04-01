package com.samsung.android.gallery.plugins.filebrowser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaInfo {
    int height;
    String mimeType;
    int orientation;
    int orientationTag;
    int width;

    public static MediaInfo copyOf(MediaInfo mediaInfo) {
        MediaInfo mediaInfo2 = new MediaInfo();
        mediaInfo2.width = mediaInfo.width;
        mediaInfo2.height = mediaInfo.height;
        mediaInfo2.orientation = mediaInfo.orientation;
        mediaInfo2.orientationTag = mediaInfo.orientationTag;
        mediaInfo2.mimeType = mediaInfo.mimeType;
        return mediaInfo2;
    }
}
