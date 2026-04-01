package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.LaunchRobinRequest;
import com.google.protobuf.MessageLite;
import com.google.protobuf.V;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface LaunchRobinRequestOrBuilder extends V {
    ClientInfo getClientInfo();

    /* synthetic */ MessageLite getDefaultInstanceForType();

    LaunchRobinRequest.RequestedActionCase getRequestedActionCase();

    TextQuery getTextQuery();

    boolean hasClientInfo();

    boolean hasTextQuery();

    /* synthetic */ boolean isInitialized();
}
