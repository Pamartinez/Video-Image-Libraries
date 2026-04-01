package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import com.google.protobuf.MessageLite;
import com.google.protobuf.V;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface RobinAvailabilityRequestOrBuilder extends V {
    ClientInfo getClientInfo();

    /* synthetic */ MessageLite getDefaultInstanceForType();

    RobinIntegrationCapability getRequestedCapabilities(int i2);

    int getRequestedCapabilitiesCount();

    List<RobinIntegrationCapability> getRequestedCapabilitiesList();

    int getRequestedCapabilitiesValue(int i2);

    List<Integer> getRequestedCapabilitiesValueList();

    boolean hasClientInfo();

    /* synthetic */ boolean isInitialized();
}
