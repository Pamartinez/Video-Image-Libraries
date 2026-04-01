package com.samsung.android.sum.core.filter;

import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptorPair;
import com.samsung.android.sum.core.descriptor.StreamPluginDescriptor;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.plugin.StreamPluginFixture;
import com.samsung.android.sum.core.types.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OutStreamPluginFilter extends PluginFilter<StreamPluginFixture> implements MediaOutputStreamFilter {
    private static final String TAG = SLog.tagOf(OutStreamPluginFilter.class);
    protected List<MediaType> mediaTypeList;
    protected Operator operator;
    private int outputChannelCount;
    private Function<Enum<?>, BufferChannel> outputChannelQuery;

    public OutStreamPluginFilter(PluginDescriptor pluginDescriptor, StreamPluginFixture streamPluginFixture) {
        super(pluginDescriptor, streamPluginFixture);
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
        this.operator = (Operator) Optional.ofNullable(getOperatorName()).map(new b(11, this)).orElse((Object) null);
        this.mediaTypeList = getMediaTypeList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ BufferChannel lambda$getOutputChannel$1(Enum enumR, Function function) {
        return (BufferChannel) function.apply(enumR);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Operator lambda$init$0(String str) {
        return ((StreamPluginFixture) this.plugin).getOperator(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepare$2(BufferChannel bufferChannel, MediaBuffer mediaBuffer) {
        String str = TAG;
        SLog.d(str, "send output(" + mediaBuffer + ") to channel(" + bufferChannel + ")");
        bufferChannel.send(mediaBuffer);
    }

    public void configOutputChannel(Function<Enum<?>, BufferChannel> function, int i2) {
        SLog.d(TAG, "configOutputChannel");
        this.outputChannelCount = i2;
        this.outputChannelQuery = function;
    }

    public MFDescriptor getDescriptor() {
        return this.descriptor;
    }

    public BufferChannel getOutputChannel(Enum<?> enumR) {
        return (BufferChannel) Optional.ofNullable(this.outputChannelQuery).map(new b(enumR, 6)).orElse((Object) null);
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

    public void prepare() {
        if (((StreamPluginFixture) this.plugin).getOutputHandlerHolder() != null) {
            ((StreamPluginFixture) this.plugin).getOutputHandlerHolder().put(new o(0, getOutputChannel(this.mediaTypeList.get(0))));
        }
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        return this.operator.run(mediaBuffer, mutableMediaBuffer);
    }

    public Stream<MediaFilter> stream() {
        return Stream.of(this);
    }
}
