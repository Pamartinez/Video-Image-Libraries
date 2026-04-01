package com.samsung.android.motionphoto.utils.v2;

import java.io.File;
import java.io.FileDescriptor;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ExifInfo {
    static ExifInfo parse(FileDescriptor fileDescriptor) {
        return new ExifInfoImpl(fileDescriptor);
    }

    String getDateTimeTaken();

    int getHeight();

    int getRotation();

    int getWidth();

    byte[] getXMP();

    ByteBuffer toByteBuffer(boolean z);

    static ExifInfo parse(File file) {
        return new ExifInfoImpl(file);
    }
}
