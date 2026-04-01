package io.grpc.stub;

import Df.n;
import He.F;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import ee.C0972e;
import ee.Z;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientInterceptor;
import io.grpc.Deadline;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractStub {
    private final CallOptions callOptions;
    private final Channel channel;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface StubFactory {
        AbstractStub newStub(Channel channel, CallOptions callOptions);
    }

    public AbstractStub(Channel channel2, CallOptions callOptions2) {
        F.n(channel2, ContentProviderConstants.Social.ParameterKey.CHANNEL);
        this.channel = channel2;
        F.n(callOptions2, "callOptions");
        this.callOptions = callOptions2;
    }

    public abstract AbstractStub build(Channel channel2, CallOptions callOptions2);

    public final CallOptions getCallOptions() {
        return this.callOptions;
    }

    public final Channel getChannel() {
        return this.channel;
    }

    public final AbstractStub withCallCredentials(CallCredentials callCredentials) {
        Channel channel2 = this.channel;
        CallOptions callOptions2 = this.callOptions;
        callOptions2.getClass();
        n b = CallOptions.b(callOptions2);
        b.f3359c = callCredentials;
        return build(channel2, new CallOptions(b));
    }

    @Deprecated
    public final AbstractStub withChannel(Channel channel2) {
        return build(channel2, this.callOptions);
    }

    public final AbstractStub withCompression(String str) {
        Channel channel2 = this.channel;
        CallOptions callOptions2 = this.callOptions;
        callOptions2.getClass();
        n b = CallOptions.b(callOptions2);
        b.d = str;
        return build(channel2, new CallOptions(b));
    }

    public final AbstractStub withDeadline(Deadline deadline) {
        Channel channel2 = this.channel;
        CallOptions callOptions2 = this.callOptions;
        callOptions2.getClass();
        n b = CallOptions.b(callOptions2);
        b.f3358a = deadline;
        return build(channel2, new CallOptions(b));
    }

    public final AbstractStub withDeadlineAfter(long j2, TimeUnit timeUnit) {
        Channel channel2 = this.channel;
        CallOptions callOptions2 = this.callOptions;
        callOptions2.getClass();
        if (timeUnit != null) {
            Deadline deadline = new Deadline(timeUnit.toNanos(j2));
            n b = CallOptions.b(callOptions2);
            b.f3358a = deadline;
            return build(channel2, new CallOptions(b));
        }
        Z z = Deadline.g;
        throw new NullPointerException("units");
    }

    public final AbstractStub withExecutor(Executor executor) {
        Channel channel2 = this.channel;
        CallOptions callOptions2 = this.callOptions;
        callOptions2.getClass();
        n b = CallOptions.b(callOptions2);
        b.b = executor;
        return build(channel2, new CallOptions(b));
    }

    public final AbstractStub withInterceptors(ClientInterceptor... clientInterceptorArr) {
        Channel channel2 = this.channel;
        List<ClientInterceptor> asList = Arrays.asList(clientInterceptorArr);
        F.n(channel2, ContentProviderConstants.Social.ParameterKey.CHANNEL);
        for (ClientInterceptor eVar : asList) {
            channel2 = new C0972e(channel2, eVar);
        }
        return build(channel2, this.callOptions);
    }

    public final AbstractStub withMaxInboundMessageSize(int i2) {
        return build(this.channel, this.callOptions.c(i2));
    }

    public final AbstractStub withMaxOutboundMessageSize(int i2) {
        return build(this.channel, this.callOptions.d(i2));
    }

    public final <T> AbstractStub withOption(CallOptions.Key key, T t) {
        return build(this.channel, this.callOptions.e(key, t));
    }

    public final AbstractStub withWaitForReady() {
        Channel channel2 = this.channel;
        CallOptions callOptions2 = this.callOptions;
        callOptions2.getClass();
        n b = CallOptions.b(callOptions2);
        b.g = Boolean.TRUE;
        return build(channel2, new CallOptions(b));
    }
}
