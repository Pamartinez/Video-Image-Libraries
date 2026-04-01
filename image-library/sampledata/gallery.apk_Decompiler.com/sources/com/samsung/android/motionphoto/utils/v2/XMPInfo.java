package com.samsung.android.motionphoto.utils.v2;

import java.io.File;
import java.io.FileDescriptor;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface XMPInfo {
    static XMPInfo parse(File file) {
        return new XMPInfoImpl(new MediaFile(file), ExifInfo.parse(file).getXMP());
    }

    boolean containsItem(String str);

    boolean containsItemField(String str, String str2);

    boolean containsProperty(String str);

    XMPEdit edit() {
        return new XMPEditImpl(this);
    }

    String getItemField(String str, String str2);

    String getItemNameAt(int i2);

    List<String> getItemNames();

    String getLastItem();

    String getProperty(String str, String str2);

    List<String> getPropertyNames();

    boolean isEmpty();

    boolean isNotEmpty();

    static XMPEdit edit(File file) {
        return parse(file).edit();
    }

    static XMPEdit edit(FileDescriptor fileDescriptor) {
        return parse(fileDescriptor).edit();
    }

    static XMPInfo parse(FileDescriptor fileDescriptor) {
        return new XMPInfoImpl(new MediaFile(fileDescriptor), ExifInfo.parse(fileDescriptor).getXMP());
    }

    static XMPInfo parse(File file, byte[] bArr) {
        return new XMPInfoImpl(new MediaFile(file), bArr);
    }

    static XMPInfo parse(FileDescriptor fileDescriptor, byte[] bArr) {
        return new XMPInfoImpl(new MediaFile(fileDescriptor), bArr);
    }
}
