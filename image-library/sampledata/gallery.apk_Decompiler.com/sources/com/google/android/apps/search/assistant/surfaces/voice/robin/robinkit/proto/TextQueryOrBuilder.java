package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.V;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TextQueryOrBuilder extends V {
    /* synthetic */ MessageLite getDefaultInstanceForType();

    File getFiles(int i2);

    int getFilesCount();

    List<File> getFilesList();

    String getQuery();

    ByteString getQueryBytes();

    boolean getShouldAutoSubmit();

    /* synthetic */ boolean isInitialized();
}
