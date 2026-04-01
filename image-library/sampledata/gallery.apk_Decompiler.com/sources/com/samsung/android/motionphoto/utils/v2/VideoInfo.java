package com.samsung.android.motionphoto.utils.v2;

import android.graphics.Bitmap;
import java.io.File;
import java.io.FileDescriptor;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface VideoInfo {
    static VideoInfo parse(File file) {
        return new VideoInfoImpl(new MediaFile(file));
    }

    int getBitrate();

    Bitmap getFrameAt(long j2, int i2);

    int getHeight();

    MimeType getMimeType();

    int getRotation();

    Map<MimeType, Long> getSampleCount();

    Bitmap getThumbnailImage();

    Map<MimeType, Long> getTrackDurations();

    Map<MimeType, Long> getTrackFirstTimestampUs();

    Map<MimeType, Long> getTrackLastTimestampUs();

    long getVideoDurationMs();

    int getVideoFrameCount();

    int getWidth();

    int numberOfTracks();

    static VideoInfo parse(FileDescriptor fileDescriptor) {
        return new VideoInfoImpl(new MediaFile(fileDescriptor));
    }

    static VideoInfo parse(FileDescriptor fileDescriptor, long j2, long j3) {
        return new VideoInfoImpl(new MediaFile(fileDescriptor), j2, j3);
    }
}
