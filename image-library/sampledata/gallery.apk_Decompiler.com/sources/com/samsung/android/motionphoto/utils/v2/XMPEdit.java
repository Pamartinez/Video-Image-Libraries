package com.samsung.android.motionphoto.utils.v2;

import java.io.File;
import java.io.FileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface XMPEdit extends XMPInfo {
    void commit();

    void commit(MediaFile mediaFile);

    void commit(File file) {
        commit(new MediaFile(file));
    }

    boolean isDirty();

    XMPEdit removeAll();

    XMPEdit removeItem(String str);

    XMPEdit removeItemField(String str, String str2);

    XMPEdit removeProperty(String str, String str2);

    XMPEdit replaceItemField(String str, String str2, Object obj);

    XMPEdit setItem(String str, MimeType mimeType);

    XMPEdit setItemField(String str, String str2, Object obj);

    XMPEdit setProperty(String str, String str2, Object obj);

    void commit(FileDescriptor fileDescriptor) {
        commit(new MediaFile(fileDescriptor));
    }
}
