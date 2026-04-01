package com.samsung.android.motionphoto.utils.v2;

import java.io.File;
import java.io.FileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SEFEdit extends SEFInfo {
    void commit();

    void commit(MediaFile mediaFile);

    void commit(File file) {
        commit(new MediaFile(file));
    }

    boolean isDirty();

    SEFEdit putData(int i2, MediaFile mediaFile);

    SEFEdit putData(int i2, String str, MediaFile mediaFile);

    SEFEdit putData(int i2, String str, byte[] bArr);

    SEFEdit putData(int i2, byte[] bArr);

    SEFEdit putData(String str, MediaFile mediaFile);

    SEFEdit putData(String str, byte[] bArr);

    SEFEdit removeData(int... iArr);

    SEFEdit removeData(String... strArr);

    SEFEdit setPrimaryType(int i2);

    void commit(FileDescriptor fileDescriptor) {
        commit(new MediaFile(fileDescriptor));
    }
}
