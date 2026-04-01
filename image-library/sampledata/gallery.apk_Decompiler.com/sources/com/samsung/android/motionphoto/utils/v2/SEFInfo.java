package com.samsung.android.motionphoto.utils.v2;

import androidx.core.util.Pair;
import java.io.File;
import java.io.FileDescriptor;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SEFInfo {
    static SEFInfo parse(FileDescriptor fileDescriptor) {
        return new SEFInfoImpl(new MediaFile(fileDescriptor), new Consumer[0]);
    }

    boolean containsKey(String str);

    boolean containsType(int i2);

    SEFEdit edit() {
        return new SEFEditImpl(this);
    }

    byte[] getData(int i2);

    byte[] getData(int i2, String str);

    byte[] getData(String str);

    int getDataCount();

    Pair<Long, Integer> getDataPositionLength(int i2);

    Pair<Long, Integer> getDataPositionLength(String str);

    List<String> getKeys();

    long getSize();

    int getVersion();

    static SEFEdit edit(FileDescriptor fileDescriptor) {
        return new SEFEditImpl(parse(fileDescriptor));
    }

    static SEFInfo parse(File file) {
        return new SEFInfoImpl(new MediaFile(file), new Consumer[0]);
    }

    static SEFEdit edit(File file) {
        return new SEFEditImpl(parse(file));
    }

    static SEFInfo parse(MediaFile mediaFile) {
        return new SEFInfoImpl(mediaFile, new Consumer[0]);
    }
}
