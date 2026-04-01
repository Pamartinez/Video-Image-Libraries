package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.Tag;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.MessageProducer;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaInputStreamDecorateFilter extends MediaFilterBase implements MediaInputStreamFilter {
    protected MediaInputStreamFilter successor;

    public MediaInputStreamDecorateFilter(MediaInputStreamFilter mediaInputStreamFilter) {
        this.successor = mediaInputStreamFilter;
    }

    public void accept(MediaFilterRetriever mediaFilterRetriever, MediaFilter mediaFilter) {
        this.successor.accept(mediaFilterRetriever, mediaFilter);
    }

    public void addTag(String str) {
        this.successor.addTag(str);
    }

    public void configInputChannel(Function<Enum<?>, BufferChannel> function, int i2) {
        this.successor.configInputChannel(function, i2);
    }

    public MFDescriptor getDescriptor() {
        return this.successor.getDescriptor();
    }

    public BufferChannel getInputChannel() {
        return this.successor.getInputChannel();
    }

    public int getInputChannelCount() {
        return this.successor.getInputChannelCount();
    }

    public int[] getMessagesToReceive() {
        return this.successor.getMessagesToReceive();
    }

    public Tag getTag() {
        return this.successor.getTag();
    }

    public boolean isInputChannelConfigured() {
        return this.successor.isInputChannelConfigured();
    }

    public boolean onMessageReceived(Message message) {
        return this.successor.onMessageReceived(message);
    }

    public void prepare() {
        this.successor.prepare();
    }

    public void release() {
        this.successor.release();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        return this.successor.run(mediaBuffer, mutableMediaBuffer);
    }

    public void setMessageProducer(MessageProducer messageProducer) {
        this.successor.setMessageProducer(messageProducer);
    }

    public Stream<MediaFilter> stream() {
        return this.successor.stream();
    }

    public void addTag(Enum<?> enumR, String str) {
        this.successor.addTag(enumR, str);
    }

    public BufferChannel getInputChannel(Enum<?> enumR) {
        return this.successor.getInputChannel(enumR);
    }

    public Tag getTag(Enum<?> enumR) {
        return this.successor.getTag(enumR);
    }
}
