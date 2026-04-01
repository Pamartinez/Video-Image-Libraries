package com.samsung.android.sum.core.buffer;

import android.graphics.Bitmap;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.os.ParcelFileDescriptor;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.types.ColorFormat;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* renamed from: com.samsung.android.sum.core.buffer.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0923a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4043a;

    public /* synthetic */ C0923a(int i2) {
        this.f4043a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4043a) {
            case 0:
                return (Function) ((Map.Entry) obj).getValue();
            case 1:
                return BufferExtension.lambda$registerTransform$36((Map.Entry) obj);
            case 2:
                return BufferExtension.lambda$new$0((Number) obj);
            case 3:
                return BufferExtension.lambda$new$19((Image) obj);
            case 4:
                return Def.fmtstr("fd=%d, len=%d", Integer.valueOf(((ParcelFileDescriptor) obj).getFd()), Long.valueOf(((ParcelFileDescriptor) obj).getStatSize()));
            case 5:
                return MediaFormat.newBuilder().setShape(((ByteBuffer) obj).limit(), 1).buildMutable();
            case 6:
                return BufferExtension.lambda$new$2((Bitmap) obj);
            case 7:
                return MediaFormat.newBuilder().setDataType(SharedBufferManager.dataTypeFromHalFormat(((HardwareBuffer) obj).getFormat())).setColorFormat(SharedBufferManager.colorFormatFromHalFormat(((HardwareBuffer) obj).getFormat())).setShape(((HardwareBuffer) obj).getWidth(), ((HardwareBuffer) obj).getHeight()).buildMutable();
            case 8:
                return MediaFormat.newBuilder().setDataType(SharedBufferManager.dataTypeFromHalFormat(((Image) obj).getFormat())).setColorFormat(SharedBufferManager.colorFormatFromHalFormat(((Image) obj).getFormat())).setColorSpace(SharedBufferManager.colorSpaceFromImage((Image) obj)).setShape(((Image) obj).getWidth(), ((Image) obj).getHeight()).buildMutable();
            case 9:
                return ((ByteBuffer) obj).toString();
            case 10:
                return Def.fmtstr("fd=%d, len=%d", Integer.valueOf(((ParcelFileDescriptor) obj).getFd()), Long.valueOf(((ParcelFileDescriptor) obj).getStatSize()));
            case 11:
                return Boolean.valueOf(((Bitmap) obj).isRecycled());
            case 12:
                return (Consumer) ((Map.Entry) obj).getValue();
            case 13:
                return GenericMediaBuffer.lambda$dataToString$2(obj);
            case 14:
                return Integer.valueOf(((Align) obj).getDimension());
            case 15:
                return Boolean.valueOf(BufferExtension.isRequiredToRelease((Class) obj));
            case 16:
                return (String) ((Supplier) obj).get();
            case 17:
                return (String) ((Map.Entry) obj).getKey();
            case 18:
                return ((Map.Entry) obj).getValue();
            case 19:
                return ((MediaFormat) obj).contentToString();
            case 20:
                return ((Align) obj).contentToString();
            case 21:
                return Integer.valueOf((String) obj);
            case 22:
                return ((ColorFormat) obj).name().toLowerCase(Locale.ROOT);
            case 23:
                return MediaBufferFileWriter.lambda$writeSingle$2((ColorFormat) obj);
            case 24:
                return Integer.valueOf(((List) obj).size());
            case 25:
                return ((MediaBuffer) obj).getData();
            case 26:
                return ((MediaBuffer) obj).stream();
            case 27:
                return ((MediaBuffer) obj).getFormat();
            default:
                return ((MediaBuffer) obj).getDataClass();
        }
    }
}
