package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.V;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ClientInfoOrBuilder extends V {
    String getAppName();

    ByteString getAppNameBytes();

    String getAppPackageName();

    ByteString getAppPackageNameBytes();

    String getAppVersion();

    ByteString getAppVersionBytes();

    /* synthetic */ MessageLite getDefaultInstanceForType();

    boolean getIsWorkProfile();

    /* synthetic */ boolean isInitialized();
}
