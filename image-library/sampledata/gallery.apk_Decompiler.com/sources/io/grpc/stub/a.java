package io.grpc.stub;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.stub.AbstractStub;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a extends AbstractStub {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static <T extends AbstractStub> T newStub(AbstractStub.StubFactory stubFactory, Channel channel) {
        return newStub(stubFactory, channel, CallOptions.f4611j);
    }

    public static <T extends AbstractStub> T newStub(AbstractStub.StubFactory stubFactory, Channel channel, CallOptions callOptions) {
        return stubFactory.newStub(channel, callOptions.e(i.f4635c, g.ASYNC));
    }
}
