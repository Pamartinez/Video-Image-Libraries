package com.samsung.android.sum.core.filter;

import c4.C0438h;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.channel.SurfaceChannel;
import com.samsung.android.sum.core.channel.r;
import com.samsung.android.sum.core.descriptor.DecorateStreamPluginDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptorPair;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.functional.PlaceHolder;
import com.samsung.android.sum.core.plugin.PluginFixture;
import com.samsung.android.sum.core.types.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecoratePluginInOutStreamFilter extends PluginFilter<PluginFixture<?>> implements MediaInputStreamFilter, MediaOutputStreamFilter {
    private static final String TAG = Def.tagOf((Class<?>) DecoratePluginInOutStreamFilter.class);
    private int inputChannelCount;
    private Function<Enum<?>, BufferChannel> inputChannelQuery;
    private List<MediaType> mediaTypeList;
    private Operator operator;
    private int outputChannelCount;
    private Function<Enum<?>, BufferChannel> outputChannelQuery;
    protected boolean reachedInputEos;
    protected boolean reachedOutputEos;
    private MFDescriptor successorDescriptor;
    MediaFilter successorFilter;

    public DecoratePluginInOutStreamFilter(PluginDescriptor pluginDescriptor, PluginFixture<?> pluginFixture, MediaFilter mediaFilter) {
        super(pluginDescriptor, pluginFixture);
        this.successorFilter = mediaFilter;
        if (pluginDescriptor instanceof DecorateStreamPluginDescriptor) {
            this.successorDescriptor = ((DecorateStreamPluginDescriptor) pluginDescriptor).getSuccessorDescriptor();
        } else {
            this.successorDescriptor = mediaFilter.getDescriptor();
        }
        init();
    }

    private List<MediaType> getMediaTypeList() {
        PluginDescriptor pluginDescriptor = this.descriptor;
        if (pluginDescriptor instanceof DecorateStreamPluginDescriptor) {
            return ((DecorateStreamPluginDescriptor) pluginDescriptor).getMediaTypeList();
        }
        if (pluginDescriptor instanceof PluginDescriptorPair) {
            return ((DecorateStreamPluginDescriptor) ((PluginDescriptorPair) pluginDescriptor).getPrimaryDescriptor()).getMediaTypeList();
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

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$run$2(BufferChannel bufferChannel) {
        return "inputChannel = " + bufferChannel;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$run$4(BufferChannel bufferChannel) {
        return "outputChannel = " + bufferChannel;
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
        return (BufferChannel) Optional.ofNullable(this.inputChannelQuery).map(new b(enumR, 1)).orElse((Object) null);
    }

    public int getInputChannelCount() {
        return this.inputChannelCount;
    }

    public String getOperatorName() {
        PluginDescriptor pluginDescriptor = this.descriptor;
        if (pluginDescriptor instanceof DecorateStreamPluginDescriptor) {
            return pluginDescriptor.getOperatorName();
        }
        if (!(pluginDescriptor instanceof PluginDescriptorPair)) {
            return null;
        }
        return (String) Optional.ofNullable(((PluginDescriptorPair) pluginDescriptor).getPrimaryDescriptor().getOperatorName()).orElse(((PluginDescriptorPair) this.descriptor).getSubDescriptor().getOperatorName());
    }

    public BufferChannel getOutputChannel(Enum<?> enumR) {
        return (BufferChannel) Optional.ofNullable(this.outputChannelQuery).map(new b(enumR, 0)).orElse((Object) null);
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

    public void prepare() {
        super.prepare();
        PlaceHolder<MediaFilter> successorFilterHolder = this.plugin.getSuccessorFilterHolder();
        if (successorFilterHolder != null) {
            successorFilterHolder.put(this.successorFilter);
        }
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        MediaBuffer mediaBuffer2;
        BufferChannel inputChannel = getInputChannel(this.mediaTypeList.get(0));
        BufferChannel outputChannel = getOutputChannel(this.mediaTypeList.get(0));
        String str = TAG;
        SLog.v(str, (Supplier<String>) new r(1, inputChannel), (Consumer<Exception>) new C0438h(7));
        SLog.v(str, (Supplier<String>) new r(2, outputChannel), (Consumer<Exception>) new C0438h(8));
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
                SLog.v(str2, "[" + inputChannel.hashCode() + "] received buffer= " + mediaBuffer2);
                boolean isReachedEos = isReachedEos(mediaBuffer2);
                this.reachedInputEos = isReachedEos;
                if (isReachedEos) {
                    SLog.i(str2, "reached input EOS");
                }
            } else {
                mediaBuffer2 = null;
            }
            if (mediaBuffer2 == null) {
                mediaBuffer2 = MediaBuffer.mutableOf(new Object[0]);
            }
            MutableMediaBuffer run = this.operator.run(mediaBuffer2, (MediaBuffer) MediaBuffer.mutableOf(new Object[0]));
            run.setExtra(mediaBuffer2.getExtra());
            if (run.equals(mediaBuffer2)) {
                mediaBuffer2.release();
            }
            if (isReachedEos(run)) {
                SLog.d(TAG, "reached output EOS");
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

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$run$3(Exception exc) {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$run$5(Exception exc) {
    }
}
