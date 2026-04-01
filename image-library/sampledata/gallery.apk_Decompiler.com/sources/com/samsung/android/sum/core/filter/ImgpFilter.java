package com.samsung.android.sum.core.filter;

import com.samsung.android.gallery.module.dynamicview.a;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.Align;
import com.samsung.android.sum.core.buffer.DeriveBufferGroup;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.SequentialDescriptor;
import com.samsung.android.sum.core.filter.collection.SequentialFilter;
import com.samsung.android.sum.core.filter.collection.SimpleConveyorFilter;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.format.UpdatableMediaFormat;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.functional.OperatorChain;
import com.samsung.android.sum.core.functional.OperatorMap;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.plugin.ImgpPlugin;
import java.io.FileDescriptor;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImgpFilter extends PluginFilter<ImgpPlugin> {
    private static final String TAG = Def.tagOf((Class<?>) ImgpFilter.class);
    private Operator imgp;

    public ImgpFilter(ImgpDescriptor imgpDescriptor, ImgpPlugin imgpPlugin) {
        super(imgpDescriptor, imgpPlugin);
        init();
    }

    private void init() {
        if (this.descriptor.getTargetFormat() != null) {
            this.descriptor.getTargetFormat().set(this.descriptor.getFilterOption().asInputOption());
        }
        Operator operator = (Operator) Stream.of(new Object[]{((ImgpDescriptor) this.descriptor).getImgpTypeName(), ((ImgpDescriptor) this.descriptor).getImgpType()}).filter(new a(10)).findFirst().map(new b(7, this)).orElseThrow(new u(4));
        this.imgp = operator;
        if (operator instanceof OperatorMap) {
            ((OperatorMap) operator).config((ImgpDescriptor) this.descriptor);
        } else if (operator instanceof OperatorChain) {
            ((OperatorChain) operator).config((ImgpDescriptor) this.descriptor);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Operator lambda$init$0(Object obj) {
        if (obj instanceof String) {
            return ((ImgpPlugin) this.plugin).getImgProcessor((String) obj);
        }
        return ((ImgpPlugin) this.plugin).getImgProcessor((Enum<?>) (Enum) obj);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$run$2(int i2, int i7, MediaBuffer mediaBuffer) {
        if (i2 != -1) {
            mediaBuffer.setExtra("media-id", Integer.valueOf(i2));
        }
        mediaBuffer.setExtra(Message.KEY_NUM_BLOCKS, Integer.valueOf(i7));
    }

    public static MediaFilter of(MediaFilter mediaFilter, MediaFilter mediaFilter2, MediaFilter mediaFilter3) {
        ImgpDecorateFilter imgpDecorateFilter = new ImgpDecorateFilter(mediaFilter);
        imgpDecorateFilter.setPreFilter(mediaFilter2);
        imgpDecorateFilter.setPostFilter(mediaFilter3);
        return imgpDecorateFilter;
    }

    public static MediaFilter sequentialOf(MediaFilter mediaFilter, MediaFilter mediaFilter2) {
        return new SimpleConveyorFilter((SequentialDescriptor) MFDescriptor.newBuilder().setDescriptors(mediaFilter.getDescriptor(), mediaFilter2.getDescriptor()).setSequentialType(SequentialFilter.Type.CONVEYOR).setSequentialMode(SequentialFilter.Mode.BATCHED).build(SequentialDescriptor.class));
    }

    public MFDescriptor getDescriptor() {
        return this.descriptor;
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        MediaFormat mediaFormat;
        boolean z;
        MediaBuffer mediaBuffer2;
        boolean z3;
        Float valueOf = Float.valueOf(1.0f);
        String str = TAG;
        SLog.d(str, "run...E");
        if (mutableMediaBuffer.isNotEmpty() || mutableMediaBuffer.getFormat() != null || this.descriptor.getTargetFormat() == null) {
            mediaFormat = mutableMediaBuffer.getFormat();
        } else {
            mediaFormat = this.descriptor.getTargetFormat();
        }
        if (mutableMediaBuffer.getFixedFormat() != null) {
            MutableMediaFormat mutableFormat = mediaFormat.toMutableFormat();
            mutableMediaBuffer.getFixedFormat().getAttributeKeys().forEach(new e(5, mutableFormat, mutableMediaBuffer));
            mediaFormat = mutableFormat.toMediaFormat();
        }
        if (mediaFormat instanceof MutableMediaFormat) {
            mediaFormat = ((MutableMediaFormat) mediaFormat).toMediaFormat();
        }
        boolean z7 = true;
        if (mediaFormat != null) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z, "designate format is not given, one of output buffer or descriptor should be given", new Object[0]);
        Boolean bool = Boolean.FALSE;
        if (((Boolean) mediaFormat.get("keep-org-ratio", bool)).booleanValue()) {
            MutableMediaFormat mutableFormat2 = mediaFormat.toMutableFormat();
            mutableFormat2.setCols((int) (((float) mutableFormat2.getCols()) / ((Float) mediaBuffer.getExtra("scale-cols", valueOf)).floatValue()));
            mutableFormat2.setRows((int) (((float) mutableFormat2.getRows()) / ((Float) mediaBuffer.getExtra("scale-rows", valueOf)).floatValue()));
            mediaFormat = mutableFormat2.toMediaFormat();
        }
        if (mediaBuffer.containsExtra("force-rotate")) {
            MutableMediaFormat mutableFormat3 = mediaFormat.toMutableFormat();
            mutableFormat3.set(Message.KEY_ROTATION, mediaBuffer.getExtra("force-rotate"));
            mediaFormat = mutableFormat3.toMediaFormat();
            mediaBuffer.removeExtra("force-rotate");
        }
        if (((Boolean) mediaFormat.get("rotate-ifnot-fit", bool)).booleanValue()) {
            MediaFormat format = mediaBuffer.getFormat();
            if (format.getCols() >= mediaFormat.getCols() || format.getRows() <= mediaFormat.getRows()) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (format.getCols() <= mediaFormat.getCols() || format.getRows() >= mediaFormat.getRows()) {
                z7 = false;
            }
            if (z7 ^ z3) {
                MutableMediaFormat mutableFormat4 = mediaFormat.toMutableFormat();
                mutableFormat4.set(Message.KEY_ROTATION, 90);
                mediaFormat = mutableFormat4.toMediaFormat();
                mediaBuffer.setExtra("force-rotate", 270);
            }
        }
        Class<FileDescriptor> cls = FileDescriptor.class;
        if (mutableMediaBuffer.getData() != null && mutableMediaBuffer.getData().getClass() == cls) {
            MutableMediaFormat mutableFormat5 = mediaFormat.toMutableFormat();
            mutableFormat5.set(Message.KEY_FILE_DESCRIPTOR, mutableMediaBuffer.getTypedData(cls));
            mediaFormat = mutableFormat5.toMediaFormat();
        }
        if (mutableMediaBuffer.containsExtra(Message.KEY_OUT_FILE)) {
            mediaFormat.toMutableFormat().set(Message.KEY_OUT_FILE, (String) mutableMediaBuffer.getExtra(Message.KEY_OUT_FILE));
        }
        if (mutableMediaBuffer.getData() == null || mutableMediaBuffer.getDataClass() == cls) {
            if (mediaBuffer.getFormat() != null) {
                mediaFormat = UpdatableMediaFormat.of(mediaFormat).with(mediaBuffer.getFormat()).set(UpdatableMediaFormat.UPDATE_AT_ALLOC);
            }
            mediaBuffer2 = MediaBuffer.mutableOf(mediaFormat, new Align(mutableMediaBuffer.getStride(), mutableMediaBuffer.getScanline()));
        } else {
            mediaBuffer2 = MediaBuffer.mutableOf(mediaFormat, mutableMediaBuffer).setFlags(new int[]{16});
        }
        mediaBuffer2.setExtra(mutableMediaBuffer.getExtra());
        mutableMediaBuffer.put((MediaBuffer) this.imgp.run(mediaBuffer, mediaBuffer2));
        mutableMediaBuffer.addExtra(mediaBuffer.getExtra());
        if (mediaBuffer != mutableMediaBuffer.get() && !(mediaBuffer instanceof DeriveBufferGroup) && (mutableMediaBuffer.get() instanceof DeriveBufferGroup)) {
            mutableMediaBuffer.stream().forEach(new f(((Integer) mutableMediaBuffer.getExtra("media-id", -1)).intValue(), (int) mutableMediaBuffer.size(), 0));
        }
        SLog.d(str, "run...X");
        return mutableMediaBuffer;
    }

    public Stream<MediaFilter> stream() {
        return Stream.of(this);
    }
}
