package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.message.MessageProducer;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaInputStreamFilterBase extends MediaFilterBase implements MediaInputStreamFilter {
    private static final String TAG = Def.tagOf((Class<?>) MediaInputStreamFilterBase.class);
    private int inputChannelCount;
    private Function<Enum<?>, BufferChannel> inputChannelQuery;
    protected MessageProducer messageProducer;
    private MFDescriptor mfDescriptor;

    public MediaInputStreamFilterBase(MFDescriptor mFDescriptor) {
        this.mfDescriptor = mFDescriptor;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ BufferChannel lambda$getInputChannel$0(Enum enumR, Function function) {
        return (BufferChannel) function.apply(enumR);
    }

    public void configInputChannel(Function<Enum<?>, BufferChannel> function, int i2) {
        this.inputChannelQuery = function;
        this.inputChannelCount = i2;
    }

    public MFDescriptor getDescriptor() {
        return this.mfDescriptor;
    }

    public BufferChannel getInputChannel(Enum<?> enumR) {
        return (BufferChannel) Optional.ofNullable(this.inputChannelQuery).map(new b(enumR, 4)).orElse((Object) null);
    }

    public int getInputChannelCount() {
        return this.inputChannelCount;
    }

    public boolean isInputChannelConfigured() {
        if (this.inputChannelQuery != null) {
            return true;
        }
        return false;
    }

    public void setMessageProducer(MessageProducer messageProducer2) {
        this.messageProducer = messageProducer2;
    }

    public Stream<MediaFilter> stream() {
        return Stream.of(this);
    }
}
