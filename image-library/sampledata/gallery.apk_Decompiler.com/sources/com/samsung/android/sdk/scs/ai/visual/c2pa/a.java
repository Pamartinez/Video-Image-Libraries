package com.samsung.android.sdk.scs.ai.visual.c2pa;

import com.samsung.android.sum.core.DebugUtils;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.Tag;
import com.samsung.android.sum.core.UniExifInterface;
import com.samsung.android.sum.core.cache.KeyGenerator;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.descriptor.NNDescriptor;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.filter.MediaMuxerFilter;
import com.samsung.android.sum.core.filter.MediaParserFilter;
import com.samsung.android.sum.core.format.MediaFormatBuilder;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.nn.NNFileDescriptor;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1667a;

    public /* synthetic */ a(int i2) {
        this.f1667a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f1667a) {
            case 0:
                return C2paUtils.getParcelFileDescriptor((String) obj);
            case 1:
                return C2paUtils.getFileExtension((String) obj);
            case 2:
                return DebugUtils.SumDebugLevel.of((String) obj);
            case 3:
                return ((DebugUtils.SumDebugLevel) obj).getLevelName();
            case 4:
                return Def.lambda$taglnOf$0(obj);
            case 5:
                return ((Tag) obj).getPrimary();
            case 6:
                return ((Tag) obj).getSecondary();
            case 7:
                return UniExifInterface.of((ByteBuffer) obj);
            case 8:
                return ByteBuffer.wrap((byte[]) obj);
            case 9:
                return Arrays.stream(((String) obj).split("#")).filter(new com.samsung.android.gallery.module.dynamicview.a(6)).findFirst();
            case 10:
                return ((MediaType) obj).rank();
            case 11:
                return ((NNFileDescriptor) obj).getName();
            case 12:
                return ((NNDescriptor) obj).getFilterOption();
            case 13:
                return ((Enum) obj).name();
            case 14:
                return ((PluginDescriptor) obj).getTargetFormat();
            case 15:
                return ((PluginDescriptor) obj).getInputFormat();
            case 16:
                return ((PluginDescriptor) obj).getOutputFormat();
            case 17:
                return ((DataType) obj).toString();
            case 18:
                return ((ImgpDescriptor) obj).getSplitType();
            case 19:
                return ((MutableMediaFormat) obj).getShape();
            case 20:
                return Boolean.valueOf(((MediaType) obj).isAudio());
            case 21:
                return ((MediaType) obj).name();
            case 22:
                return ((MediaFormatBuilder) obj).build();
            case 23:
                return MediaMuxerFilter.lambda$onReceiveExtraData$11((Integer) obj);
            case 24:
                return KeyGenerator.getSimpleKey((String) obj);
            case 25:
                return MediaMuxerFilter.lambda$prepare$5(obj);
            case 26:
                return MediaMuxerFilter.lambda$onReceiveCodecFormatData$9((Integer) obj);
            case 27:
                return MediaMuxerFilter.lambda$prepare$4((String) obj);
            case 28:
                return MediaParserFilter.lambda$getFileDescriptorFromBuffer$0(obj);
            default:
                return (MutableMediaFormat) ((MutableMediaFormat) obj).dup();
        }
    }
}
