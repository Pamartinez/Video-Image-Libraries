package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.message.MessageProducer;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaOutputStreamFilterBase extends MediaFilterBase implements MediaOutputStreamFilter {
    protected MessageProducer messageProducer;
    private MFDescriptor mfDescriptor;
    private int outputChannelCount;
    private Function<Enum<?>, BufferChannel> outputChannelQuery;

    public MediaOutputStreamFilterBase(MFDescriptor mFDescriptor) {
        this.mfDescriptor = mFDescriptor;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ BufferChannel lambda$getOutputChannel$0(Enum enumR, Function function) {
        return (BufferChannel) function.apply(enumR);
    }

    public void configOutputChannel(Function<Enum<?>, BufferChannel> function, int i2) {
        this.outputChannelQuery = function;
        this.outputChannelCount = i2;
    }

    public MFDescriptor getDescriptor() {
        return this.mfDescriptor;
    }

    public BufferChannel getOutputChannel(Enum<?> enumR) {
        return (BufferChannel) Optional.ofNullable(this.outputChannelQuery).map(new b(enumR, 5)).orElse((Object) null);
    }

    public int getOutputChannelCount() {
        return this.outputChannelCount;
    }

    public boolean isOutputChannelConfigured() {
        if (this.outputChannelQuery != null) {
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
