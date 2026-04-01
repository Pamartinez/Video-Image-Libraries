package com.samsung.android.sum.core.filter;

import Bd.C0726b;
import J5.c;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.plugin.PluginFixture;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StaplePluginFilter extends PluginFilter<PluginFixture<?>> {
    private static final String TAG = Def.tagOf((Class<?>) StaplePluginFilter.class);
    private Operator operator;

    public StaplePluginFilter(PluginDescriptor pluginDescriptor, PluginFixture<?> pluginFixture) {
        super(pluginDescriptor, pluginFixture);
        init();
    }

    private void init() {
        String operatorName = getOperatorName();
        if (operatorName == null) {
            this.operator = null;
        } else {
            this.operator = this.plugin.getOperator(operatorName);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$run$0(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        return "run: ibuf = " + mediaBuffer + " -> " + mutableMediaBuffer;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$run$1(MutableMediaBuffer mutableMediaBuffer) {
        return "obuf: " + mutableMediaBuffer;
    }

    public MFDescriptor getDescriptor() {
        return this.descriptor;
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        MediaFormat mediaFormat;
        boolean z;
        String str = TAG;
        SLog.v(str, (Supplier<String>) new C0726b(8, mediaBuffer, mutableMediaBuffer));
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
        if (mutableMediaBuffer.isEmpty()) {
            mutableMediaBuffer.setFixedFormat(mediaFormat);
        }
        this.operator.run(mediaBuffer, mutableMediaBuffer);
        mutableMediaBuffer.addExtra(mediaBuffer.getExtra());
        mutableMediaBuffer.setFlags(new int[]{mediaBuffer.getFlags()});
        SLog.v(str, (Supplier<String>) new c(22, mutableMediaBuffer));
        return mutableMediaBuffer;
    }

    public Stream<MediaFilter> stream() {
        return Stream.of(this);
    }
}
