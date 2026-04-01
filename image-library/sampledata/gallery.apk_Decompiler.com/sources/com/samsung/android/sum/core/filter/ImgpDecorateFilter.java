package com.samsung.android.sum.core.filter;

import android.graphics.Rect;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.format.MutableShape;
import com.samsung.android.sum.core.format.Shape;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.SplitType;
import com.samsung.android.sum.solution.filter.UniImgp;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImgpDecorateFilter extends DecorateFilter {
    private static final String TAG = Def.tagOf((Class<?>) ImgpDecorateFilter.class);
    private MediaFilter postFilter;
    private ImgpDescriptor postImgpDescriptor;
    private MediaFilter preFilter;
    private ImgpDescriptor preImgpDescriptor;

    public ImgpDecorateFilter(MediaFilter mediaFilter) {
        super(mediaFilter);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$run$1(MutableMediaBuffer mutableMediaBuffer, int i2) {
        if (i2 == ((Integer) mutableMediaBuffer.getExtra("force-rotate")).intValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DataType lambda$run$2(MutableMediaBuffer mutableMediaBuffer, MutableMediaFormat mutableMediaFormat) {
        DataType dataType = mutableMediaFormat.getDataType();
        if (dataType == DataType.NONE) {
            return mutableMediaBuffer.getFormat().getDataType();
        }
        return dataType;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ColorFormat lambda$run$3(MutableMediaBuffer mutableMediaBuffer, MutableMediaFormat mutableMediaFormat) {
        ColorFormat colorFormat = mutableMediaFormat.getColorFormat();
        if (colorFormat == ColorFormat.NONE) {
            return mutableMediaBuffer.getFormat().getColorFormat();
        }
        return colorFormat;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$run$4(float f, float f5, MediaBuffer mediaBuffer) {
        if (mediaBuffer.containsAllExtra("roi-on-block", "roi-on-image")) {
            Rect rect = (Rect) mediaBuffer.getExtra("roi-on-block");
            rect.top = (int) (((float) rect.top) * f);
            rect.bottom = (int) (((float) rect.bottom) * f);
            rect.right = (int) (((float) rect.right) * f5);
            rect.left = (int) (((float) rect.left) * f5);
            Rect rect2 = (Rect) mediaBuffer.getExtra("roi-on-image");
            rect2.top = (int) (((float) rect2.top) * f);
            rect2.bottom = (int) (((float) rect2.bottom) * f);
            rect2.right = (int) (((float) rect2.right) * f5);
            rect2.left = (int) (((float) rect2.left) * f5);
        }
    }

    public MediaFilter getPostFilter() {
        return this.postFilter;
    }

    public MediaFilter getPreFilter() {
        return this.preFilter;
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        String str = TAG;
        SLog.v(str, "run: pre=" + this.preImgpDescriptor + ", post=" + this.postImgpDescriptor);
        SplitType splitType = (SplitType) Optional.ofNullable(this.preImgpDescriptor).map(new a(18)).orElse(SplitType.NONE);
        MediaFormat format = mediaBuffer.getFormat();
        MediaBuffer mediaBuffer2 = (MediaBuffer) Optional.ofNullable(this.preFilter).map(new b(6, mediaBuffer)).orElse(mediaBuffer.toMutable());
        mediaBuffer2.addExtra(mediaBuffer.getExtra());
        super.run(mediaBuffer2, mutableMediaBuffer);
        SplitType splitType2 = SplitType.TILE;
        if (splitType == splitType2) {
            Objects.requireNonNull(this.preImgpDescriptor);
            Objects.requireNonNull(this.postImgpDescriptor);
            this.postImgpDescriptor.getTargetFormat().set("merge-type", splitType2);
            Shape shape = (Shape) Optional.ofNullable(this.preImgpDescriptor.getTargetFormat()).map(new a(19)).orElse(mediaBuffer2.getFormat().getShape());
            Shape shape2 = (Shape) Optional.ofNullable(this.postImgpDescriptor.getTargetFormat()).map(new a(19)).orElse(mutableMediaBuffer.getFormat().getShape());
            float rows = ((float) shape2.getRows()) / ((float) shape.getRows());
            float cols = ((float) shape2.getCols()) / ((float) shape.getCols());
            MutableShape mutableShape = format.getShape().toMutableShape();
            mutableShape.setRows((int) (((float) mutableShape.getRows()) * rows));
            mutableShape.setCols((int) (((float) mutableShape.getCols()) * cols));
            if (mutableMediaBuffer.containsExtra("force-rotate") && Arrays.stream(new int[]{90, 270}).anyMatch(new Gb.b(2, mutableMediaBuffer))) {
                int cols2 = mutableShape.getCols();
                mutableShape.setCols(mutableShape.getRows());
                mutableShape.setRows(cols2);
            }
            MediaBuffer allocate = MediaBuffer.newImageAlloc().setDataType((DataType) Optional.ofNullable(this.postImgpDescriptor.getTargetFormat()).map(new d(mutableMediaBuffer, 0)).orElse(mutableMediaBuffer.getFormat().getDataType())).setShape(mutableShape.toShape()).setColorFormat((ColorFormat) Optional.ofNullable(this.postImgpDescriptor.getTargetFormat()).map(new d(mutableMediaBuffer, 1)).orElse(mutableMediaBuffer.getFormat().getColorFormat())).allocate();
            if (cols * rows != 1.0f) {
                mutableMediaBuffer.stream().forEach(new e(rows, cols));
            }
            MediaBuffer allocate2 = MediaBuffer.newGroupAlloc().setBuffers(allocate, mutableMediaBuffer.asList()).allocate();
            allocate2.addExtra(mutableMediaBuffer.getExtra());
            if (this.postFilter != null) {
                mutableMediaBuffer.reset();
                this.postFilter.run(allocate2, mutableMediaBuffer);
            }
            allocate2.release();
        } else if (getDescriptor().getFilterOption().isInputWithEvaluationValue()) {
            MediaBuffer allocate3 = MediaBuffer.newGroupAlloc().setBuffers((MediaBuffer) mediaBuffer2.dup(), mutableMediaBuffer.asList()).allocate();
            allocate3.setFlags(2);
            mutableMediaBuffer.put(allocate3);
        } else {
            MediaFilter mediaFilter = this.postFilter;
            if (mediaFilter != null) {
                mediaFilter.run(mutableMediaBuffer.reset(), mutableMediaBuffer);
            }
        }
        if (!Stream.of(new MFDescriptor[]{getDescriptor(), this.postImgpDescriptor}).anyMatch(new com.samsung.android.gallery.module.dynamicview.a(9))) {
            SLog.v(str, "convert output data-type to one of input");
            mutableMediaBuffer.put((MediaBuffer) UniImgp.ofCvtData().run(mutableMediaBuffer.get(), (MediaBuffer) MediaBuffer.mutableOf(format.getDataType())));
        }
        mutableMediaBuffer.addExtra(mutableMediaBuffer.getExtra());
        mutableMediaBuffer.addExtra(mediaBuffer2.getExtra());
        if (mediaBuffer != mediaBuffer2) {
            mediaBuffer2.release();
        }
        SLog.v(str, "ret: obuf=" + mutableMediaBuffer);
        return mutableMediaBuffer;
    }

    public void setPostFilter(MediaFilter mediaFilter) {
        this.postFilter = mediaFilter;
        if (mediaFilter != null) {
            this.postImgpDescriptor = (ImgpDescriptor) mediaFilter.getDescriptor();
        }
    }

    public void setPreFilter(MediaFilter mediaFilter) {
        this.preFilter = mediaFilter;
        if (mediaFilter != null) {
            this.preImgpDescriptor = (ImgpDescriptor) mediaFilter.getDescriptor();
        }
    }
}
