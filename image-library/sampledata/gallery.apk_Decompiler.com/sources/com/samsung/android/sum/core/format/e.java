package com.samsung.android.sum.core.format;

import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4101a;

    public /* synthetic */ e(int i2) {
        this.f4101a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4101a) {
            case 0:
                return StapleMutableMediaFormat.lambda$bytePerPixel$4((ColorFormat) obj);
            case 1:
                return Float.valueOf(((DataType) obj).size());
            case 2:
                return StapleMutableMediaFormat.lambda$bytePerSample$2((ColorFormat) obj);
            case 3:
                return Float.valueOf(((DataType) obj).size() * ((float) ((DataType) obj).channels()));
            case 4:
                return MediaFormat.lambda$getPlanes$1((MutableMediaFormat) obj);
            case 5:
                return ((MutableShape) obj).toShape();
            case 6:
                return Integer.valueOf(((MutableShape) obj).getBatch());
            case 7:
                return Integer.valueOf(((DataType) obj).channels());
            case 8:
                return Integer.valueOf(((ColorFormat) obj).getChannels());
            case 9:
                return Integer.valueOf(((MutableShape) obj).getDimension());
            case 10:
                return Integer.valueOf(((MutableShape) obj).getCols());
            case 11:
                return Integer.valueOf(((MutableShape) obj).getChannels());
            default:
                return Integer.valueOf(((MutableShape) obj).getRows());
        }
    }
}
