package com.samsung.android.sum.core.filter;

import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MediaBufferGroup;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.NNFWDescriptor;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.functional.ExecuteDelegator;
import com.samsung.android.sum.core.types.Status;
import com.samsung.android.sum.solution.filter.UniImgp;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class NNFWFilter extends MediaFilterBase implements MediaFilter {
    private static final String TAG = Def.tagOf((Class<?>) NNFWFilter.class);
    protected NNFWDescriptor descriptor;
    protected ExecuteDelegator executeDelegator;
    private MediaFormat targetFormat;

    public NNFWFilter(NNFWDescriptor nNFWDescriptor) {
        this.descriptor = nNFWDescriptor;
    }

    public MFDescriptor getDescriptor() {
        return this.descriptor;
    }

    public void prepare() {
        if (this.descriptor.getTargetFormat() != null) {
            this.targetFormat = this.descriptor.getTargetFormat().toMediaFormat();
        }
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        if (mutableMediaBuffer.isEmpty()) {
            MediaFormat format = mediaBuffer.getFormat();
            MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) Optional.ofNullable(this.descriptor.getOutputFormat()).map(new a(29)).orElse((Object) null);
            if (format == null && mutableMediaFormat == null) {
                z = false;
            } else {
                z = true;
            }
            Def.require(z);
            if (format == null || mutableMediaFormat == null) {
                if (mutableMediaFormat == null) {
                    Objects.requireNonNull(format);
                    mutableMediaFormat = format.toMutableFormat();
                }
            } else if (mutableMediaFormat.getShape() == null || mutableMediaFormat.getShape().isEmpty()) {
                mutableMediaFormat.setShape(format.getShape());
            }
            mutableMediaBuffer.setFixedFormat(mutableMediaFormat.toMediaFormat());
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            ExecuteDelegator executeDelegator2 = this.executeDelegator;
            if (executeDelegator2 != null) {
                executeDelegator2.execute(mediaBuffer, mutableMediaBuffer, new com.samsung.android.sdk.scs.ai.language.a(7, this));
            } else {
                mutableMediaBuffer.put(MediaBuffer.allocate(mutableMediaBuffer.getFormat()));
                runAdapter(mediaBuffer, mutableMediaBuffer);
            }
            mutableMediaBuffer.addExtra(mediaBuffer.getExtra());
            if (mediaBuffer instanceof MediaBufferGroup) {
                while (mediaBuffer instanceof MediaBufferGroup) {
                    mediaBuffer = ((MediaBufferGroup) mediaBuffer).getPrimaryBuffer();
                }
                Objects.requireNonNull(mediaBuffer);
                mutableMediaBuffer.addExtra(mediaBuffer.getExtra());
            }
            if (this.targetFormat != null) {
                String str = TAG;
                SLog.d(str, "convert to target-format: " + this.targetFormat);
                mutableMediaBuffer.put((MediaBuffer) UniImgp.ofCvtData().run((MediaBuffer) mutableMediaBuffer, MediaBuffer.mutableOf(this.targetFormat)));
            }
            String str2 = TAG;
            SLog.d(str2, "[" + this.descriptor.getFw() + "] processing nn ts: " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            return mutableMediaBuffer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(Def.makeExceptionTag(e, this), e);
        }
    }

    public abstract Status runAdapter(MediaBuffer mediaBuffer, MediaBuffer mediaBuffer2);

    public void setExecuteDelegator(ExecuteDelegator executeDelegator2) {
        this.executeDelegator = executeDelegator2;
    }

    public Stream<MediaFilter> stream() {
        return Stream.of(this);
    }
}
