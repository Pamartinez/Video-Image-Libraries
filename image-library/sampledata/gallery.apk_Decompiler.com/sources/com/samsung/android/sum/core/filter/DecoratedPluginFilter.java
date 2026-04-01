package com.samsung.android.sum.core.filter;

import Ad.C0720a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.DecoratePluginDescriptor;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptorPair;
import com.samsung.android.sum.core.descriptor.StaplePluginDescriptor;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.functional.PlaceHolder;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.MessageProducer;
import com.samsung.android.sum.core.plugin.PluginFixture;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecoratedPluginFilter extends PluginFilter<PluginFixture<?>> {
    private final String TAG = Def.tagOf((Class<?>) DecoratedPluginFilter.class);
    private Operator operator;
    private MFDescriptor successorDescriptor;
    MediaFilter successorFilter;

    public DecoratedPluginFilter(PluginDescriptor pluginDescriptor, PluginFixture<?> pluginFixture, MediaFilter mediaFilter) {
        super(pluginDescriptor, pluginFixture);
        this.successorFilter = mediaFilter;
        if (pluginDescriptor instanceof DecoratePluginDescriptor) {
            this.successorDescriptor = ((DecoratePluginDescriptor) pluginDescriptor).getSuccessorDescriptor();
        } else {
            this.successorDescriptor = mediaFilter.getDescriptor();
        }
        init();
    }

    private void init() {
        PluginDescriptor pluginDescriptor = this.descriptor;
        if (pluginDescriptor instanceof DecoratePluginDescriptor) {
            this.operator = this.plugin.getOperator(((DecoratePluginDescriptor) pluginDescriptor).getOperatorName());
        } else if (pluginDescriptor instanceof PluginDescriptorPair) {
            PluginDescriptor primaryDescriptor = ((PluginDescriptorPair) pluginDescriptor).getPrimaryDescriptor();
            PluginDescriptor subDescriptor = ((PluginDescriptorPair) this.descriptor).getSubDescriptor();
            String operatorName = ((DecoratePluginDescriptor) primaryDescriptor).getOperatorName();
            if (operatorName == null) {
                if (subDescriptor instanceof StaplePluginDescriptor) {
                    operatorName = ((StaplePluginDescriptor) subDescriptor).getOperatorName();
                } else if (subDescriptor instanceof ImgpDescriptor) {
                    operatorName = ((ImgpDescriptor) subDescriptor).getImgpTypeName();
                }
            }
            if (operatorName != null) {
                this.operator = this.plugin.getOperator(operatorName);
            }
        } else {
            SLog.w(this.TAG, "operator is not set");
            this.operator = null;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int[] lambda$getMessagesToReceive$0() {
        return new int[0];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int[] lambda$getMessagesToReceive$1() {
        return new int[0];
    }

    public MFDescriptor getDescriptor() {
        return this.descriptor;
    }

    public int[] getMessagesToReceive() {
        return IntStream.concat(IntStream.of((int[]) Optional.ofNullable(super.getMessagesToReceive()).orElseGet(new C0720a(14))), IntStream.of((int[]) Optional.ofNullable(this.successorFilter.getMessagesToReceive()).orElseGet(new C0720a(15)))).toArray();
    }

    public boolean onMessageReceived(Message message) {
        boolean z;
        if (this.plugin.getMessageConsumer() != null) {
            z = this.plugin.getMessageConsumer().apply(message).booleanValue();
        } else {
            z = false;
        }
        MediaFilter mediaFilter = this.successorFilter;
        if (mediaFilter != null) {
            z |= mediaFilter.onMessageReceived(message);
        }
        if (!z) {
            return super.onMessageReceived(message) | z;
        }
        return z;
    }

    public void prepare() {
        super.prepare();
        PlaceHolder<MediaFilter> successorFilterHolder = this.plugin.getSuccessorFilterHolder();
        if (successorFilterHolder != null) {
            successorFilterHolder.put(this.successorFilter);
        }
        if (((Boolean) this.descriptor.getExtra("run-plugin-directly", Boolean.FALSE)).booleanValue()) {
            this.plugin.setExtra("run-plugin-directly", Boolean.TRUE);
        }
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        MediaFormat mediaFormat;
        boolean z;
        SLog.v(this.TAG, "run: ibuf = " + mediaBuffer + " -> " + mutableMediaBuffer);
        if (mutableMediaBuffer.isNotEmpty() || this.descriptor.getTargetFormat() == null) {
            mediaFormat = mutableMediaBuffer.getFormat();
        } else {
            mediaFormat = this.descriptor.getTargetFormat();
        }
        if (mediaFormat instanceof MutableMediaFormat) {
            mediaFormat = ((MutableMediaFormat) mediaFormat).toMediaFormat();
        }
        if (mediaFormat != null) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z, "designate format is not given, one of output buffer or descriptor should be given", new Object[0]);
        MutableMediaBuffer mutableOf = MediaBuffer.mutableOf(mediaFormat, mutableMediaBuffer.getExtra());
        this.operator.run(mediaBuffer, (MediaBuffer) mutableOf);
        SLog.d(this.TAG, "preprocess buffer = " + mutableOf);
        mutableMediaBuffer.put((MediaBuffer) this.successorFilter.run((MediaBuffer) mutableOf, MediaBuffer.mutableOf(mediaFormat)));
        mutableMediaBuffer.addExtra(mediaBuffer.getExtra());
        return mutableMediaBuffer;
    }

    public void setDescriptor(StaplePluginDescriptor staplePluginDescriptor) {
        this.successorDescriptor = staplePluginDescriptor;
    }

    public void setMessageProducer(MessageProducer messageProducer) {
        this.successorFilter.setMessageProducer(messageProducer);
    }

    public Stream<MediaFilter> stream() {
        return null;
    }

    public DecoratedPluginFilter(PluginDescriptor pluginDescriptor, PluginFixture<?> pluginFixture, MediaFilter mediaFilter, MFDescriptor mFDescriptor) {
        super(pluginDescriptor, pluginFixture);
        this.successorDescriptor = mFDescriptor;
        this.successorFilter = mediaFilter;
        init();
    }
}
