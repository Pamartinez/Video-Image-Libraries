package com.samsung.android.sum.solution.filter;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.functional.OperatorMap;
import com.samsung.android.sum.core.plugin.NativeUniImgpPlugin;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.FlipType;
import com.samsung.android.sum.core.types.ImgpType;
import com.samsung.android.sum.core.types.MediaType;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NativeImgpFilterAdapter implements Operator {
    private static final String TAG = Def.tagOf((Class<?>) NativeImgpFilterAdapter.class);
    private final NativeUniImgpPlugin plugin;

    public NativeImgpFilterAdapter(MediaFormat mediaFormat, MediaFormat mediaFormat2, ColorFormat colorFormat) {
        ColorFormat colorFormat2;
        String str = TAG;
        SLog.d(str, "inputFormat=" + mediaFormat);
        SLog.d(str, "outputFormat=" + mediaFormat2);
        SLog.d(str, "preferred-ColorFormat=" + colorFormat);
        ArrayList arrayList = new ArrayList();
        if (mediaFormat != null) {
            if (mediaFormat.getMediaType() == MediaType.COMPRESSED_IMAGE) {
                arrayList.add(ImgpType.DECODE);
            }
            OperatorMap.inferOperations(mediaFormat.toMutableFormat(), mediaFormat2);
        }
        if (mediaFormat2.contains("scale")) {
            arrayList.add(ImgpType.RESIZE);
        }
        if (mediaFormat2.getShape() != null) {
            arrayList.add(ImgpType.RESIZE);
        }
        if (mediaFormat2.getCropRect() != null) {
            arrayList.add(ImgpType.CROP);
        }
        if (!(colorFormat == null || colorFormat == ColorFormat.NONE)) {
            arrayList.add(ImgpType.CVT_COLOR);
        }
        if (!(mediaFormat == null || mediaFormat.getColorFormat() == mediaFormat2.getColorFormat() || mediaFormat.getColorFormat() == (colorFormat2 = ColorFormat.NONE) || mediaFormat2.getColorFormat() == colorFormat2)) {
            arrayList.add(ImgpType.CVT_COLOR);
        }
        if (!(mediaFormat2.getColorSpace() == null || mediaFormat2.getColorSpace() == ColorSpace.NONE)) {
            arrayList.add(ImgpType.CVT_GAMUT);
        }
        if (mediaFormat2.getRotation() != 0) {
            arrayList.add(ImgpType.ROTATE);
        }
        if (mediaFormat2.getFlipType() != FlipType.NONE) {
            arrayList.add(ImgpType.FLIP);
        }
        if (mediaFormat2.getMediaType() == MediaType.COMPRESSED_IMAGE && mediaFormat2.contains("codec-type")) {
            if (mediaFormat2.contains("encode-hdr")) {
                arrayList.add(ImgpType.ENCODE_HDR);
            } else {
                arrayList.add(ImgpType.ENCODE);
            }
        }
        SLog.d(str, "opList=" + arrayList.size());
        this.plugin = new NativeUniImgpPlugin(arrayList, mediaFormat, mediaFormat2, colorFormat);
    }

    public void release() {
        this.plugin.release();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        return this.plugin.run(mediaBuffer, mutableMediaBuffer);
    }
}
