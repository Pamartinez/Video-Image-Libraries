package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.channel.SurfaceChannel;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptorPair;
import com.samsung.android.sum.core.descriptor.StreamPluginDescriptor;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.plugin.PluginFixture;
import com.samsung.android.sum.core.types.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PluginInOutStreamFilter extends PluginFilter<PluginFixture<?>> implements MediaInputStreamFilter, MediaOutputStreamFilter {
    private static final String TAG = Def.tagOf((Class<?>) PluginInOutStreamFilter.class);
    private int inputChannelCount;
    private Function<Enum<?>, BufferChannel> inputChannelQuery;
    protected int maxInputSize = 0;
    protected List<MediaType> mediaTypeList;
    protected AtomicInteger numWholeFrames = new AtomicInteger(0);
    protected Operator operator;
    private int outputChannelCount;
    private Function<Enum<?>, BufferChannel> outputChannelQuery;
    protected int processedFrames = 0;
    protected boolean reachedInputEos;
    protected boolean reachedOutputEos;

    public PluginInOutStreamFilter(PluginDescriptor pluginDescriptor, PluginFixture<?> pluginFixture) {
        super(pluginDescriptor, pluginFixture);
        init();
    }

    private List<MediaType> getMediaTypeList() {
        PluginDescriptor pluginDescriptor = this.descriptor;
        if (pluginDescriptor instanceof StreamPluginDescriptor) {
            return ((StreamPluginDescriptor) pluginDescriptor).getMediaTypeList();
        }
        if (pluginDescriptor instanceof PluginDescriptorPair) {
            return ((StreamPluginDescriptor) ((PluginDescriptorPair) pluginDescriptor).getPrimaryDescriptor()).getMediaTypeList();
        }
        SLog.w(TAG, "not supported descriptor");
        return null;
    }

    private void init() {
        String operatorName = getOperatorName();
        if (operatorName == null) {
            this.operator = null;
        } else {
            this.operator = this.plugin.getOperator(operatorName);
        }
        this.mediaTypeList = getMediaTypeList();
    }

    private boolean isMatchedMediaType(Message message) {
        return this.mediaTypeList.contains(message.get(Message.KEY_MEDIA_TYPE));
    }

    private boolean isReachedEos(MediaBuffer mediaBuffer) {
        if (mediaBuffer.containFlags(8)) {
            return true;
        }
        if (!mediaBuffer.containsExtra("chunk-size") || ((Integer) mediaBuffer.getExtra("chunk-size")).intValue() >= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ BufferChannel lambda$getInputChannel$0(Enum enumR, Function function) {
        return (BufferChannel) function.apply(enumR);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ BufferChannel lambda$getOutputChannel$1(Enum enumR, Function function) {
        return (BufferChannel) function.apply(enumR);
    }

    public void configInputChannel(Function<Enum<?>, BufferChannel> function, int i2) {
        this.inputChannelQuery = function;
        this.inputChannelCount = i2;
    }

    public void configOutputChannel(Function<Enum<?>, BufferChannel> function, int i2) {
        this.outputChannelQuery = function;
        this.outputChannelCount = i2;
    }

    public MFDescriptor getDescriptor() {
        return this.descriptor;
    }

    public BufferChannel getInputChannel(Enum<?> enumR) {
        return (BufferChannel) Optional.ofNullable(this.inputChannelQuery).map(new b(enumR, 8)).orElse((Object) null);
    }

    public int getInputChannelCount() {
        return this.inputChannelCount;
    }

    public String getOperatorName() {
        PluginDescriptor pluginDescriptor = this.descriptor;
        if (pluginDescriptor instanceof StreamPluginDescriptor) {
            return pluginDescriptor.getOperatorName();
        }
        if (!(pluginDescriptor instanceof PluginDescriptorPair)) {
            return null;
        }
        return (String) Optional.ofNullable(((PluginDescriptorPair) pluginDescriptor).getPrimaryDescriptor().getOperatorName()).orElse(((PluginDescriptorPair) this.descriptor).getSubDescriptor().getOperatorName());
    }

    public BufferChannel getOutputChannel(Enum<?> enumR) {
        return (BufferChannel) Optional.ofNullable(this.outputChannelQuery).map(new b(enumR, 7)).orElse((Object) null);
    }

    public int getOutputChannelCount() {
        return this.outputChannelCount;
    }

    public boolean isInputChannelConfigured() {
        if (this.inputChannelQuery != null) {
            return true;
        }
        return false;
    }

    public boolean isOutputChannelConfigured() {
        if (this.outputChannelQuery != null) {
            return true;
        }
        return false;
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        MediaBuffer mediaBuffer2;
        String str = TAG;
        SLog.v(str, "run");
        BufferChannel inputChannel = getInputChannel(this.mediaTypeList.get(0));
        BufferChannel outputChannel = getOutputChannel(this.mediaTypeList.get(0));
        SLog.v(str, "inputChannel = " + inputChannel);
        SLog.v(str, "outputChannel = " + outputChannel);
        this.reachedInputEos = false;
        this.reachedOutputEos = false;
        while (true) {
            boolean z = this.reachedInputEos;
            if (z && this.reachedOutputEos) {
                return mutableMediaBuffer;
            }
            if (!z) {
                mediaBuffer2 = (MediaBuffer) inputChannel.receive();
                String str2 = TAG;
                SLog.v(str2, "[" + inputChannel + "] received buffer= " + mediaBuffer2);
                this.reachedInputEos = isReachedEos(mediaBuffer2);
            } else {
                mediaBuffer2 = null;
            }
            if (mediaBuffer2 == null) {
                mediaBuffer2 = MediaBuffer.mutableOf(new Object[0]);
            }
            MutableMediaBuffer run = this.operator.run(mediaBuffer2, (MediaBuffer) MediaBuffer.mutableOf(new Object[0]));
            run.setExtra(mediaBuffer2.getExtra());
            if (run.equals(mediaBuffer2) && !isReachedEos(mediaBuffer2)) {
                mediaBuffer2.release();
            }
            if (isReachedEos(run)) {
                SLog.v(TAG, "reached output EOS");
                this.reachedOutputEos = true;
                outputChannel.send(run);
            } else if (!(outputChannel instanceof SurfaceChannel) || !outputChannel.isClosedForSend()) {
                outputChannel.send(run);
            } else {
                throw new CancellationException("output channel is already closed");
            }
        }
    }

    public Stream<MediaFilter> stream() {
        return Stream.of(this);
    }
}
