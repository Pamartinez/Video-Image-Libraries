package com.samsung.android.sum.core.filter;

import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.channel.BufferChannelHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AsyncFilter extends DecorateFilter {
    private int id = -1;
    protected BufferChannelHolder inputChannel = new BufferChannelHolder();
    protected BufferChannelHolder outputChannel = new BufferChannelHolder();

    public AsyncFilter(MediaFilter mediaFilter) {
        super(mediaFilter);
    }

    public AsyncFilter addBufferChannels(BufferChannel bufferChannel, BufferChannel bufferChannel2) {
        setInputChannel(bufferChannel);
        setOutputChannel(bufferChannel2);
        return this;
    }

    public String getId() {
        if (this.id == -1) {
            return super.getId();
        }
        return super.getId() + Log.TAG_SEPARATOR + this.id;
    }

    public BufferChannel getInputChannel() {
        return this.inputChannel;
    }

    public BufferChannel getOutputChannel() {
        return this.outputChannel;
    }

    public void prepare() {
        boolean z;
        if (!this.inputChannel.isNotEmpty() || !this.outputChannel.isNotEmpty()) {
            z = false;
        } else {
            z = true;
        }
        Def.require(z, "either input-channel or output-channel is not given", new Object[0]);
        super.prepare();
    }

    public void release() {
        super.release();
        this.inputChannel.close();
        this.outputChannel.close();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        throw new UnsupportedOperationException("do not call, instead call prepare & release");
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setInputChannel(BufferChannel bufferChannel) {
        this.inputChannel.put(bufferChannel);
    }

    public void setOutputChannel(BufferChannel bufferChannel) {
        this.outputChannel.put(bufferChannel);
    }
}
