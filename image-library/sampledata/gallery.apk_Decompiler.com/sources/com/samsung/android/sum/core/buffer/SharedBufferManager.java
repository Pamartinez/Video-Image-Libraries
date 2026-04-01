package com.samsung.android.sum.core.buffer;

import B8.b;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.os.Build;
import android.view.Surface;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;
import com.samsung.android.motionphoto.core.MPSurfaceReader;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.Status;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SharedBufferManager {
    private static final int HAL_PIXEL_FORMAT_EXYNOS_YCbCr_420_SPN = 291;
    private static final int HAL_PIXEL_FORMAT_EXYNOS_YCbCr_420_SP_M = 261;
    private static final int HAL_PIXEL_FORMAT_YCbCr_420_SP_VENUS = 2141391876;
    private static final String TAG = Def.tagOf((Class<?>) SharedBufferManager.class);
    private static volatile SharedBufferManager sInstance;
    private static final Map<ColorFormat, int[]> vendorSpecificColorFormat = new HashMap<ColorFormat, int[]>() {
        {
            put(ColorFormat.NV12, new int[]{SharedBufferManager.HAL_PIXEL_FORMAT_YCbCr_420_SP_VENUS});
            put(ColorFormat.NV21, new int[]{261, SharedBufferManager.HAL_PIXEL_FORMAT_EXYNOS_YCbCr_420_SPN});
        }
    };

    /* renamed from: com.samsung.android.sum.core.buffer.SharedBufferManager$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$ColorFormat;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.sum.core.types.ColorFormat[] r0 = com.samsung.android.sum.core.types.ColorFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$ColorFormat = r0
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.RGBA     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.RGB     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.NV12     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.NV21     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.P010     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.VENDOR_IMPLEMENTED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.SharedBufferManager.AnonymousClass2.<clinit>():void");
        }
    }

    static {
        String property = System.getProperty(Def.JUNIT_TEST_EXECUTION_MODE);
        if (property == null || !Boolean.parseBoolean(property)) {
            System.loadLibrary("sume_mediabuffer_jni.media.samsung");
        }
    }

    private SharedBufferManager() {
    }

    public static ColorFormat colorFormatFromBuffer(MediaBuffer mediaBuffer) {
        if (!isHardwareBufferType(mediaBuffer).booleanValue() || mediaBuffer.getFormat().getColorFormat() != ColorFormat.VENDOR_IMPLEMENTED) {
            return mediaBuffer.getFormat().getColorFormat();
        }
        return ColorFormat.from(nativeGetColorFormat((HardwareBuffer) mediaBuffer.getTypedData(HardwareBuffer.class)));
    }

    public static ColorFormat colorFormatFromHalFormat(int i2) {
        if (i2 != 1) {
            if (i2 == 3) {
                return ColorFormat.RGB;
            }
            if (i2 == 17) {
                return ColorFormat.NV21;
            }
            if (i2 != 22) {
                if (i2 == 54) {
                    return ColorFormat.P010;
                }
                if (i2 == 34) {
                    return ColorFormat.VENDOR_IMPLEMENTED;
                }
                if (i2 != 35) {
                    return (ColorFormat) vendorSpecificColorFormat.entrySet().stream().filter(new b(i2, 18)).map(new G(0)).findFirst().orElse(ColorFormat.NONE);
                }
                return ColorFormat.NV12;
            }
        }
        return ColorFormat.RGBA;
    }

    public static ColorFormat colorFormatFromHardwareBuffer(HardwareBuffer hardwareBuffer) {
        Def.require(!hardwareBuffer.isClosed());
        return ColorFormat.from(nativeGetColorFormat(hardwareBuffer));
    }

    public static int colorFormatToHalFormat(ColorFormat colorFormat) {
        switch (AnonymousClass2.$SwitchMap$com$samsung$android$sum$core$types$ColorFormat[colorFormat.ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 35;
            case 4:
                return 261;
            case 5:
                return 54;
            case 6:
                return 34;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static ColorSpace colorSpaceFromDataSpace(int i2) {
        if (Build.VERSION.SDK_INT < 33) {
            SLog.w(TAG, "SDK version is too low. Can't get color-space from image!");
            return ColorSpace.NONE;
        } else if (i2 == 138477568) {
            return ColorSpace.BT709_FR;
        } else {
            if (i2 != 143261696) {
                if (i2 == 146931712) {
                    return ColorSpace.BT601_FR;
                }
                if (i2 != 147193856) {
                    if (i2 == 151715840) {
                        return ColorSpace.ADOBE_RGB;
                    }
                    if (i2 != 155844608) {
                        if (i2 != 163971072) {
                            if (i2 == 281083904) {
                                return ColorSpace.BT709_LR;
                            }
                            if (i2 == 281149440 || i2 == 281280512) {
                                return ColorSpace.BT601_LR;
                            }
                            return (ColorSpace) Optional.ofNullable(android.graphics.ColorSpace.getFromDataSpace(i2)).map(new G(1)).orElse(ColorSpace.NONE);
                        }
                    }
                }
                return ColorSpace.BT2020_FR;
            }
            return ColorSpace.DISPLAY_P3;
        }
    }

    public static ColorSpace colorSpaceFromHalDataSpace(int i2) {
        android.graphics.ColorSpace colorSpace = Color.colorSpace((long) i2);
        if (colorSpace == android.graphics.ColorSpace.get(ColorSpace.Named.BT709)) {
            return com.samsung.android.sum.core.types.ColorSpace.BT709_FR;
        }
        throw new UnsupportedOperationException("Unknown color-space: " + colorSpace);
    }

    public static com.samsung.android.sum.core.types.ColorSpace colorSpaceFromImage(Image image) {
        if (Build.VERSION.SDK_INT >= 33) {
            return colorSpaceFromDataSpace(image.getDataSpace());
        }
        SLog.w(TAG, "SDK version is too low. Can't get color-space from image!");
        return com.samsung.android.sum.core.types.ColorSpace.NONE;
    }

    public static void copyByteBufferToHwBuffer(MediaFormat mediaFormat, ByteBuffer byteBuffer, HardwareBuffer hardwareBuffer) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cols", mediaFormat.getCols());
            jSONObject.put("rows", mediaFormat.getRows());
            jSONObject.put("size", mediaFormat.size());
            jSONObject.put("color-format", mediaFormat.getColorFormat().stringfy());
            Def.require(Status.from(nativeByte2HwBuffer(byteBuffer, hardwareBuffer, jSONObject.toString())) == Status.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public static void copyFromBuffer(MediaBuffer mediaBuffer, HardwareBuffer hardwareBuffer) {
        if (isHardwareBufferType(mediaBuffer).booleanValue()) {
            SLog.d(TAG, "isHardwareBufferType");
            copyHwBufferToHwBuffer((HardwareBuffer) mediaBuffer.getTypedData(HardwareBuffer.class), hardwareBuffer);
            return;
        }
        SLog.d(TAG, "isOtherBufferType");
        copyByteBufferToHwBuffer(mediaBuffer, hardwareBuffer);
    }

    public static void copyHwBufferOnRect(MediaBuffer mediaBuffer, HardwareBuffer hardwareBuffer, Rect rect) {
        boolean z;
        boolean z3;
        Class cls;
        boolean z7 = false;
        if (mediaBuffer.getCols() > hardwareBuffer.getWidth() || mediaBuffer.getRows() > hardwareBuffer.getHeight()) {
            z = false;
        } else {
            z = true;
        }
        Def.require(z);
        if (rect.width() <= 0 || rect.height() <= 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        Def.require(z3);
        Def.require(mediaBuffer.getFormat().getColorFormat().isPlanar());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cols", mediaBuffer.getCols());
            jSONObject.put("rows", mediaBuffer.getRows());
            jSONObject.put("size", mediaBuffer.size());
            jSONObject.put("color-format", mediaBuffer.getFormat().getColorFormat());
            jSONObject.put("left", rect.left);
            jSONObject.put("top", rect.top);
            jSONObject.put("right", rect.right);
            jSONObject.put("bottom", rect.bottom);
            if (isHardwareBufferType(mediaBuffer).booleanValue()) {
                cls = HardwareBuffer.class;
            } else {
                cls = ByteBuffer.class;
            }
            if (Status.from(nativeCopyHwBufferOnRect(mediaBuffer.getTypedData(cls), hardwareBuffer, jSONObject.toString())) == Status.OK) {
                z7 = true;
            }
            Def.require(z7);
        } catch (Exception e) {
            throw new IllegalStateException("fail to copyBufferToRect on e:" + e);
        }
    }

    public static void copyHwBufferToHwBuffer(HardwareBuffer hardwareBuffer, HardwareBuffer hardwareBuffer2) {
        boolean z;
        boolean z3 = false;
        if (hardwareBuffer.isClosed() || hardwareBuffer2.isClosed()) {
            z = false;
        } else {
            z = true;
        }
        Def.require(z);
        try {
            if (Status.from(nativeHw2HwBuffer(hardwareBuffer, hardwareBuffer2, new JSONObject().toString())) == Status.OK) {
                z3 = true;
            }
            Def.require(z3);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void copyToByteBuffer(MediaFormat mediaFormat, HardwareBuffer hardwareBuffer, ByteBuffer byteBuffer) {
        boolean z = true;
        Def.require(!hardwareBuffer.isClosed());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cols", mediaFormat.getShape().getCols());
            jSONObject.put("rows", mediaFormat.getShape().getRows());
            jSONObject.put("size", mediaFormat.size());
            jSONObject.put("color-format", mediaFormat.getColorFormat().stringfy());
            if (Status.from(nativeHw2ByteBuffer(hardwareBuffer, byteBuffer, jSONObject.toString())) != Status.OK) {
                z = false;
            }
            Def.require(z);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public static HardwareBuffer create(MediaFormat mediaFormat) {
        if (mediaFormat.getMediaType().isAudio()) {
            return getInstance().createAsAudio((MutableMediaFormat) mediaFormat);
        }
        return getInstance().createAsImage(mediaFormat);
    }

    private HardwareBuffer createAsAudio(MutableMediaFormat mutableMediaFormat) {
        return HardwareBuffer.create((int) mutableMediaFormat.size(), 1, 33, 1, 51);
    }

    private HardwareBuffer createAsImage(MediaFormat mediaFormat) {
        int i2 = AnonymousClass2.$SwitchMap$com$samsung$android$sum$core$types$ColorFormat[mediaFormat.getColorFormat().ordinal()];
        return HardwareBuffer.create((int) mediaFormat.size(), 1, 33, 1, 51);
    }

    public static int dataSpaceFromColorSpace(com.samsung.android.sum.core.types.ColorSpace colorSpace) {
        int i2;
        int i7;
        int standard = colorSpace.getStandard();
        if (standard == com.samsung.android.sum.core.types.ColorSpace.BT601.getValue()) {
            i2 = 131072;
        } else {
            if (standard != com.samsung.android.sum.core.types.ColorSpace.BT709.getValue()) {
                if (standard == com.samsung.android.sum.core.types.ColorSpace.BT2020.getValue()) {
                    i2 = 393216;
                } else if (standard == com.samsung.android.sum.core.types.ColorSpace.DISPLAY_P3.getValue()) {
                    i2 = 655360;
                } else if (standard != com.samsung.android.sum.core.types.ColorSpace.SRGB.getValue()) {
                    if (standard == com.samsung.android.sum.core.types.ColorSpace.ADOBE_RGB.getValue()) {
                        i2 = 720896;
                    } else {
                        SLog.v(TAG, "unsupported colorSpace(" + colorSpace + ") for DataSpace conversion");
                        i2 = 0;
                    }
                }
            }
            i2 = 65536;
        }
        if (standard == 65536) {
            i2 |= OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE;
        }
        if (colorSpace.isFullRange()) {
            i7 = 134217728;
        } else if (!colorSpace.isLimitedRange()) {
            return i2;
        } else {
            i7 = 268435456;
        }
        return i7 | i2;
    }

    public static DataType dataTypeFromHalFormat(int i2) {
        if (i2 == 1) {
            return DataType.U8C4;
        }
        if (i2 == 3) {
            return DataType.U8C3;
        }
        if (i2 == 22) {
            return DataType.F16C4;
        }
        if (i2 == 43) {
            return DataType.U32C4;
        }
        if (i2 == 54) {
            return DataType.U32C1;
        }
        if (i2 == 34 || i2 == 35) {
            return DataType.U8C1;
        }
        return DataType.NONE;
    }

    public static SharedBufferManager getInstance() {
        if (sInstance == null) {
            synchronized (SharedBufferManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SharedBufferManager();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public static Boolean isHardwareBufferType(MediaBuffer mediaBuffer) {
        boolean z;
        Class<?> dataClass = mediaBuffer.getDataClass();
        if (HardwareBuffer.class.isAssignableFrom(dataClass) || dataClass == MPSurfaceReader.MPSurfaceImage.class || Image.class.isAssignableFrom(dataClass)) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$colorFormatFromHalFormat$0(int i2, int i7) {
        if (i7 == i2) {
            return true;
        }
        return false;
    }

    private static native int nativeByte2HwBuffer(Buffer buffer, HardwareBuffer hardwareBuffer, String str);

    private static native int nativeCopyHwBufferOnRect(Object obj, HardwareBuffer hardwareBuffer, String str);

    private static native void nativeDupHwBuffer(HardwareBuffer hardwareBuffer);

    private static native int nativeGetColorFormat(HardwareBuffer hardwareBuffer);

    private static native int nativeHw2ByteBuffer(HardwareBuffer hardwareBuffer, Buffer buffer, String str);

    private static native int nativeHw2HwBuffer(HardwareBuffer hardwareBuffer, HardwareBuffer hardwareBuffer2, String str);

    private static native long nativeLockHwBuffer(HardwareBuffer hardwareBuffer);

    private static native void nativeSetSurfaceAsDroppable(Surface surface, boolean z);

    private static native void nativeUnLockHwBuffer(HardwareBuffer hardwareBuffer);

    public static void setSurfaceAsDroppable(Surface surface, boolean z) {
        nativeSetSurfaceAsDroppable(surface, z);
    }

    public void dupHardwareBuffer(HardwareBuffer hardwareBuffer) {
        SLog.d(TAG, "dupHardwareBuffer");
        nativeDupHwBuffer(hardwareBuffer);
    }

    public static void copyByteBufferToHwBuffer(MediaBuffer mediaBuffer, HardwareBuffer hardwareBuffer) {
        boolean z = true;
        Def.require(!hardwareBuffer.isClosed());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cols", mediaBuffer.getCols());
            jSONObject.put("rows", mediaBuffer.getRows());
            jSONObject.put("size", mediaBuffer.size());
            jSONObject.put("color-format", mediaBuffer.getFormat().getColorFormat().stringfy());
            if (Status.from(nativeByte2HwBuffer((Buffer) mediaBuffer.getTypedData(ByteBuffer.class), hardwareBuffer, jSONObject.toString())) != Status.OK) {
                z = false;
            }
            Def.require(z);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
