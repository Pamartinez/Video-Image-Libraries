package com.samsung.android.motionphoto.utils.v2;

import android.graphics.RectF;
import java.io.File;
import java.io.FileDescriptor;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MotionPhotoInfo {
    static MotionPhotoEdit edit(File file) {
        return new MotionPhotoEditImpl(parse(file));
    }

    static MotionPhotoInfo parse(File file) {
        return new MotionPhotoInfoImpl(file);
    }

    int getAutoPlayVideoBitrate();

    long getAutoPlayVideoPosition();

    long getAutoPlayVideoSize();

    long getCaptureTimestampUs();

    RectF getCropRect();

    String getDateTimeTaken();

    ExifInfo getExifInfo();

    String getExtraInfo();

    long getFileSize();

    int getImageHeight();

    MimeType getImageMimeType();

    int getImageRotation();

    long getImageSize();

    long getImageVideoPadding();

    int getImageWidth();

    int getNumTracksOfVideo();

    SEFInfo getSEFInfo();

    MotionPhotoFormat getSEFMotionPhotoVersion();

    Map<MimeType, Long> getTrackDurationsOfVideo();

    long getVideo(File file);

    long getVideo(FileDescriptor fileDescriptor);

    byte[] getVideo();

    int getVideoBitrate();

    long getVideoDurationUs();

    VideoInfo getVideoInfo();

    MimeType getVideoMimeType();

    long getVideoPosition();

    long getVideoSize();

    XMPInfo getXMPInfo();

    MotionPhotoFormat getXMPMotionPhotoVersion();

    boolean isValid();

    static MotionPhotoEdit edit(FileDescriptor fileDescriptor) {
        return new MotionPhotoEditImpl(parse(fileDescriptor));
    }

    static MotionPhotoInfo parse(FileDescriptor fileDescriptor) {
        return new MotionPhotoInfoImpl(fileDescriptor);
    }

    static MotionPhotoEdit edit(FileDescriptor fileDescriptor, String str) {
        return new MotionPhotoEditImpl(parse(fileDescriptor, str));
    }

    static MotionPhotoInfo parse(FileDescriptor fileDescriptor, String str) {
        return new MotionPhotoInfoImpl(new MediaFile(fileDescriptor, new File(str)));
    }

    MotionPhotoEdit edit() {
        return new MotionPhotoEditImpl(this);
    }
}
