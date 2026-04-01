package com.samsung.android.gallery.database.dbtype;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MediaType {
    Unsupported(0),
    Image(1),
    Video(3),
    Media(4),
    Images(10),
    Videos(30),
    UnlockScreen(100);
    
    private static final int MEDIA_TYPE_IMAGES = 10;
    private static final int MEDIA_TYPE_MEDIA = 4;
    private static final int MEDIA_TYPE_VIDEOS = 30;
    public static final int VIDEO_COLOR_HDR10 = 1001;
    public static final int VIDEO_COLOR_HDR10PLUS = 1002;
    public static final int VIDEO_COLOR_HLG = 1000;
    public static final int VIDEO_COLOR_NONE = 0;
    private final int mValue;

    private MediaType(int i2) {
        this.mValue = i2;
    }

    public static MediaType valueOf2(int i2) {
        if (i2 == 1) {
            return Image;
        }
        if (i2 == 10) {
            return Images;
        }
        if (i2 == 30) {
            return Videos;
        }
        if (i2 == 3) {
            return Video;
        }
        if (i2 != 4) {
            return Unsupported;
        }
        return Media;
    }

    public int toInt() {
        return this.mValue;
    }

    public static int toInt(MediaType mediaType, int i2) {
        return mediaType != null ? mediaType.toInt() : i2;
    }

    public static MediaType valueOf(int i2) {
        if (i2 == 1) {
            return Image;
        }
        if (i2 != 3) {
            return Unsupported;
        }
        return Video;
    }
}
