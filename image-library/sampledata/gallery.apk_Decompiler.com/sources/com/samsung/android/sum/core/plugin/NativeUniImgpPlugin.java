package com.samsung.android.sum.core.plugin;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.media.Image;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.motionphoto.core.MPSurfaceReader;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.UniExifInterface;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.format.Shape;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.FlipType;
import com.samsung.android.sum.core.types.ImgpType;
import com.samsung.android.sum.core.types.PadType;
import com.samsung.android.sum.core.types.SplitType;
import com.samsung.android.sum.core.types.Status;
import i.C0212a;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NativeUniImgpPlugin implements Plugin<ImgpPlugin>, Operator {
    private static final String TAG = Def.tagOf((Class<?>) NativeUniImgpPlugin.class);
    private final ReentrantLock lock = new ReentrantLock();
    private long nativeContext;
    private long nativeUniImgpOpContext;
    private MediaFormat persistentInputFormat;
    private MediaFormat persistentOutputFormat;
    private ColorFormat preferredColorFormat;

    static {
        String property = System.getProperty(Def.JUNIT_TEST_EXECUTION_MODE);
        if (property == null || !Boolean.parseBoolean(property)) {
            System.loadLibrary("sume_jni.media.samsung");
            nativeSetup();
        }
    }

    public NativeUniImgpPlugin() {
    }

    private MediaBuffer adjustExif(MediaBuffer mediaBuffer) {
        UniExifInterface uniExifInterface = (UniExifInterface) mediaBuffer.getTypedData(UniExifInterface.class);
        int attributeInt = uniExifInterface.getAttributeInt("PixelXDimension", 0);
        int attributeInt2 = uniExifInterface.getAttributeInt("PixelYDimension", 0);
        if ((attributeInt & 1) == 0 && (attributeInt2 & 1) == 0) {
            return mediaBuffer;
        }
        int i2 = (attributeInt >> 1) << 1;
        uniExifInterface.setAttribute("ImageWidth", String.valueOf(i2));
        int i7 = (attributeInt2 >> 1) << 1;
        uniExifInterface.setAttribute("ImageLength", String.valueOf(i7));
        uniExifInterface.setAttribute("PixelXDimension", String.valueOf(i2));
        uniExifInterface.setAttribute("PixelYDimension", String.valueOf(i7));
        SLog.v(TAG, a.d(i2, i7, "adjust exif to... [", ArcCommonLog.TAG_COMMA, "]"));
        try {
            uniExifInterface.saveAttributes();
            return MediaBuffer.metadataBufferOf(1, uniExifInterface);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject bufferToJson(MediaBuffer mediaBuffer) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cols", mediaBuffer.getCols());
            jSONObject.put("rows", mediaBuffer.getRows());
            jSONObject.put("data-type", mediaBuffer.getFormat().getDataType().stringfy());
            jSONObject.put("color-format", mediaBuffer.getFormat().getColorFormat().stringfy());
            jSONObject.put("color-space", mediaBuffer.getFormat().getColorSpace().stringfy());
            jSONObject.put("rotation", mediaBuffer.getFormat().getRotation());
            if (mediaBuffer.containsExtra(Message.KEY_IN_FILE)) {
                jSONObject.put(Message.KEY_IN_FILE, (String) mediaBuffer.getExtra(Message.KEY_IN_FILE));
            }
            if (mediaBuffer.containsExtra(Message.KEY_OUT_FILE)) {
                jSONObject.put(Message.KEY_OUT_FILE, (String) mediaBuffer.getExtra(Message.KEY_OUT_FILE));
            }
            if (mediaBuffer.containsExtra("exposure-value")) {
                jSONObject.put("exposure-value", ((Integer) mediaBuffer.getExtra("exposure-value")).intValue());
            }
            if (mediaBuffer.getFormat().getCodecType() != CodecType.NONE) {
                jSONObject.put("codec-type", mediaBuffer.getFormat().getCodecType().stringfy());
            }
            if (mediaBuffer.getFormat().contains("scale")) {
                jSONObject.put("scale", (double) ((Float) mediaBuffer.getFormat().get("scale", Float.valueOf(1.0f))).floatValue());
            }
            if (mediaBuffer.getFormat().getCropRect() != null) {
                jSONObject.put("crop-rect", mediaBuffer.getFormat().getCropRect().flattenToString());
            }
            if (mediaBuffer.getFormat().getSplitType() != SplitType.NONE) {
                jSONObject.put("split-type", mediaBuffer.getFormat().getSplitType().stringfy());
            }
            if (mediaBuffer.getFormat().getFlipType() != FlipType.NONE) {
                jSONObject.put("flip-type", mediaBuffer.getFormat().getFlipType().stringfy());
            }
            if (mediaBuffer.containsAllExtra("row-offset", "scan-lines")) {
                jSONObject.put("row-offset", ((Float) mediaBuffer.getExtra("row-offset")).doubleValue());
                jSONObject.put("scan-lines", ((Float) mediaBuffer.getExtra("scan-lines")).doubleValue());
            }
            if (mediaBuffer.containsAllExtra("roi-on-image", "roi-on-block")) {
                jSONObject.put("roi-on-image", ((Rect) mediaBuffer.getExtra("roi-on-image")).flattenToString());
                jSONObject.put("roi-on-block", ((Rect) mediaBuffer.getExtra("roi-on-block")).flattenToString());
            }
            if (mediaBuffer.getFormat().contains("pad-type")) {
                jSONObject.put("pad-type", ((PadType) mediaBuffer.getFormat().get("pad-type", PadType.NONE)).stringfy());
            }
            if (mediaBuffer.getFormat().contains("pad-size")) {
                jSONObject.put("pad-size", ((Integer) mediaBuffer.getFormat().get("pad-size", 0)).intValue());
            }
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    private JSONObject createJsonImgpOption(MediaBuffer mediaBuffer, MediaBuffer mediaBuffer2) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (mediaBuffer2.getFormat().getSplitType() != SplitType.NONE) {
                jSONObject.put("split-type", mediaBuffer2.getFormat().getSplitType().stringfy());
            }
            if (mediaBuffer2.getFormat().getFlipType() != FlipType.NONE) {
                jSONObject.put("flip-type", mediaBuffer2.getFormat().getFlipType().stringfy());
            }
            if (mediaBuffer2.getFormat().contains("pad-type")) {
                jSONObject.put("pad-type", ((PadType) mediaBuffer2.getFormat().get("pad-type", PadType.NONE)).stringfy());
            }
            if (mediaBuffer.isNotEmpty() && mediaBuffer2.isNotEmpty() && mediaBuffer.getData().equals(mediaBuffer2.getData())) {
                jSONObject.put("prefer-fast", true);
            }
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    private Object getNativeSupportBuffer(MediaBuffer mediaBuffer) {
        if (mediaBuffer.isEmpty()) {
            return null;
        }
        Class<?> dataClass = mediaBuffer.getDataClass();
        if (isHwBufferType(dataClass)) {
            return mediaBuffer.getTypedData(HardwareBuffer.class);
        }
        Class cls = Bitmap.class;
        if (cls.isAssignableFrom(dataClass)) {
            return mediaBuffer.getTypedData(cls);
        }
        SLog.d(TAG, "convert as default... (ByteBuffer)");
        return mediaBuffer.getTypedData(ByteBuffer.class);
    }

    private boolean isHDRSupported() {
        return SemFloatingFeature.getInstance().getBoolean("SEC_FLOATING_FEATURE_MMFW_SUPPORT_PHOTOHDR");
    }

    private boolean isHeifSupported(MediaBuffer mediaBuffer, Shape shape) {
        if (shape == null || shape.getDimension() <= 0) {
            if (mediaBuffer.getCols() < 512 || mediaBuffer.getRows() < 512) {
                return false;
            }
            return true;
        } else if (shape.getCols() < 512 || shape.getRows() < 512) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isHwBufferType(Class<?> cls) {
        if (HardwareBuffer.class.isAssignableFrom(cls) || Image.class.isAssignableFrom(cls) || MPSurfaceReader.MPSurfaceImage.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$encode$1(MutableMediaBuffer mutableMediaBuffer, HashMap hashMap, MediaBuffer mediaBuffer) {
        Class cls = ByteBuffer.class;
        if (mediaBuffer.getFormat().contains("exif") || mediaBuffer.containsExtra("exif")) {
            if (mutableMediaBuffer.getFormat().getColorFormat().isPlanar()) {
                mediaBuffer = adjustExif(mediaBuffer);
            }
            hashMap.put("exif", mediaBuffer.getTypedData(cls));
        } else if (mediaBuffer.getFormat().contains("icc") || mediaBuffer.containsExtra("icc")) {
            hashMap.put("icc", mediaBuffer.getTypedData(cls));
        } else {
            String str = TAG;
            SLog.w(str, "Unused buffer is given for encoding" + mediaBuffer);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$encodeHDR$2(MutableMediaBuffer mutableMediaBuffer, HashMap hashMap, MediaBuffer mediaBuffer) {
        Class cls = ByteBuffer.class;
        if (mediaBuffer.getFormat().contains("exif") || mediaBuffer.containsExtra("exif")) {
            if (mutableMediaBuffer.getFormat().getColorFormat().isPlanar()) {
                mediaBuffer = adjustExif(mediaBuffer);
            }
            hashMap.put("exif", mediaBuffer.getTypedData(cls));
        } else if (mediaBuffer.getFormat().contains("icc") || mediaBuffer.containsExtra("icc")) {
            hashMap.put("icc", mediaBuffer.getTypedData(cls));
        } else if (mediaBuffer.getFormat().contains("gain-map") || mediaBuffer.containsExtra("gain-map")) {
            hashMap.put("gain-map-format", bufferToJson(mediaBuffer).toString());
            hashMap.put("gain-map", mediaBuffer.getTypedData(cls));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaBuffer lambda$makeBufferFromMap$4(HashMap hashMap, int i2) {
        return makeImageBuffer((String) hashMap.get("block" + i2 + "-buffer"), hashMap.get("block" + i2 + "-data"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$3(HashMap hashMap, MediaBuffer mediaBuffer) {
        MediaBuffer mediaBuffer2;
        SLog.v(TAG, mediaBuffer.toString());
        Class cls = ByteBuffer.class;
        if (mediaBuffer.getFormat().contains("exif")) {
            if (this.persistentOutputFormat.getColorFormat().isPlanar() || this.preferredColorFormat.isPlanar()) {
                mediaBuffer2 = adjustExif(mediaBuffer);
            } else {
                mediaBuffer2 = mediaBuffer;
            }
            hashMap.put("exif", mediaBuffer2.getTypedData(cls));
        } else if (mediaBuffer.getFormat().contains("icc")) {
            hashMap.put("icc", mediaBuffer.getTypedData(cls));
        } else if (mediaBuffer.getFormat().contains("gain-map")) {
            JSONObject bufferToJson = bufferToJson(mediaBuffer);
            try {
                bufferToJson.put("gain-map", true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            hashMap.put("gain-map-format", bufferToJson.toString());
            hashMap.put("gain-map", mediaBuffer.getTypedData(cls));
        }
        if (mediaBuffer.containsExtra(BuddyContract.Image.THUMBNAIL)) {
            hashMap.put(BuddyContract.Image.THUMBNAIL, mediaBuffer.getTypedData(cls));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.samsung.android.sum.core.buffer.MediaBuffer makeBufferFromMap(java.util.HashMap<java.lang.String, java.lang.Object> r8) {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = "output-buffer"
            boolean r3 = r8.containsKey(r2)
            if (r3 == 0) goto L_0x0023
            java.lang.Object r2 = r8.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "output-data"
            java.lang.Object r3 = r8.get(r3)
            com.samsung.android.sum.core.buffer.MediaBuffer r2 = r7.makeImageBuffer(r2, r3)
            goto L_0x0024
        L_0x0023:
            r2 = 0
        L_0x0024:
            java.lang.String r3 = "block-num"
            java.lang.Object r3 = r8.get(r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            r4 = 0
            if (r3 == 0) goto L_0x005f
            java.lang.String r1 = TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "block num: "
            r5.<init>(r6)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            com.samsung.android.sum.core.SLog.v((java.lang.String) r1, (java.lang.String) r5)
            int r1 = r3.intValue()
            java.util.stream.IntStream r1 = java.util.stream.IntStream.range(r4, r1)
            com.samsung.android.sum.core.graph.r r3 = new com.samsung.android.sum.core.graph.r
            r5 = 1
            r3.<init>(r5, r7, r8)
            java.util.stream.Stream r7 = r1.mapToObj(r3)
            java.util.stream.Collector r1 = java.util.stream.Collectors.toList()
            java.lang.Object r7 = r7.collect(r1)
            r1 = r7
            java.util.List r1 = (java.util.List) r1
        L_0x005f:
            java.lang.String r7 = "exif"
            boolean r3 = r8.containsKey(r7)
            r5 = 1
            if (r3 == 0) goto L_0x007d
            java.lang.Object r7 = r8.get(r7)
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            if (r7 == 0) goto L_0x007d
            boolean r3 = r7.isDirect()
            if (r3 == 0) goto L_0x007d
            com.samsung.android.sum.core.buffer.MediaBuffer r7 = com.samsung.android.sum.core.buffer.MediaBuffer.metadataBufferOf((int) r5, (java.nio.ByteBuffer) r7)
            r0.add(r7)
        L_0x007d:
            java.lang.String r7 = "icc"
            boolean r3 = r8.containsKey(r7)
            if (r3 == 0) goto L_0x009b
            java.lang.Object r7 = r8.get(r7)
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            if (r7 == 0) goto L_0x009b
            boolean r8 = r7.isDirect()
            if (r8 == 0) goto L_0x009b
            r8 = 2
            com.samsung.android.sum.core.buffer.MediaBuffer r7 = com.samsung.android.sum.core.buffer.MediaBuffer.metadataBufferOf((int) r8, (java.nio.ByteBuffer) r7)
            r0.add(r7)
        L_0x009b:
            int r7 = r1.size()
            if (r7 <= r5) goto L_0x00b7
            int r7 = r0.size()
            if (r7 <= 0) goto L_0x00aa
            r1.addAll(r0)
        L_0x00aa:
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r7 = com.samsung.android.sum.core.buffer.MediaBuffer.newGroupAlloc()
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r7 = r7.setBuffers((com.samsung.android.sum.core.buffer.MediaBuffer) r2, (java.util.List<com.samsung.android.sum.core.buffer.MediaBuffer>) r1)
            com.samsung.android.sum.core.buffer.MediaBuffer r7 = r7.allocate()
            return r7
        L_0x00b7:
            if (r2 == 0) goto L_0x00cf
            int r7 = r0.size()
            if (r7 <= 0) goto L_0x00cf
            r0.add(r4, r2)
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r7 = com.samsung.android.sum.core.buffer.MediaBuffer.newGroupAlloc()
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r7 = r7.setBuffers((com.samsung.android.sum.core.buffer.MediaBuffer) r2, (java.util.List<com.samsung.android.sum.core.buffer.MediaBuffer>) r0)
            com.samsung.android.sum.core.buffer.MediaBuffer r7 = r7.allocate()
            return r7
        L_0x00cf:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.plugin.NativeUniImgpPlugin.makeBufferFromMap(java.util.HashMap):com.samsung.android.sum.core.buffer.MediaBuffer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0088 A[Catch:{ JSONException -> 0x011b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.samsung.android.sum.core.buffer.MediaBuffer makeImageBuffer(java.lang.String r14, java.lang.Object r15) {
        /*
            r13 = this;
            java.lang.String r13 = "color-space"
            java.lang.String r0 = "color-format"
            java.lang.String r1 = "data-type"
            java.lang.String r2 = "rows"
            java.lang.String r3 = "cols"
            java.lang.String r4 = "roi-on-image"
            java.lang.String r5 = "roi-on-block"
            java.lang.String r6 = "scan-lines"
            java.lang.String r7 = "row-offset"
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ JSONException -> 0x011b }
            r8.<init>(r14)     // Catch:{ JSONException -> 0x011b }
            com.samsung.android.sum.core.format.MediaFormatBuilder r14 = com.samsung.android.sum.core.format.MediaFormat.newImageBuilder()     // Catch:{ JSONException -> 0x011b }
            boolean r9 = r8.has(r3)     // Catch:{ JSONException -> 0x011b }
            if (r9 == 0) goto L_0x0028
            int r3 = r8.getInt(r3)     // Catch:{ JSONException -> 0x011b }
            r14.setCols(r3)     // Catch:{ JSONException -> 0x011b }
        L_0x0028:
            boolean r3 = r8.has(r2)     // Catch:{ JSONException -> 0x011b }
            if (r3 == 0) goto L_0x0035
            int r2 = r8.getInt(r2)     // Catch:{ JSONException -> 0x011b }
            r14.setRows(r2)     // Catch:{ JSONException -> 0x011b }
        L_0x0035:
            boolean r2 = r8.has(r1)     // Catch:{ JSONException -> 0x011b }
            if (r2 == 0) goto L_0x0046
            java.lang.String r1 = r8.getString(r1)     // Catch:{ JSONException -> 0x011b }
            com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.valueOf(r1)     // Catch:{ JSONException -> 0x011b }
            r14.setDataType(r1)     // Catch:{ JSONException -> 0x011b }
        L_0x0046:
            boolean r1 = r8.has(r0)     // Catch:{ JSONException -> 0x011b }
            if (r1 == 0) goto L_0x0057
            java.lang.String r0 = r8.getString(r0)     // Catch:{ JSONException -> 0x011b }
            com.samsung.android.sum.core.types.ColorFormat r0 = com.samsung.android.sum.core.types.ColorFormat.valueOf(r0)     // Catch:{ JSONException -> 0x011b }
            r14.setColorFormat(r0)     // Catch:{ JSONException -> 0x011b }
        L_0x0057:
            boolean r0 = r8.has(r13)     // Catch:{ JSONException -> 0x011b }
            if (r0 == 0) goto L_0x0068
            java.lang.String r13 = r8.getString(r13)     // Catch:{ JSONException -> 0x011b }
            com.samsung.android.sum.core.types.ColorSpace r13 = com.samsung.android.sum.core.types.ColorSpace.valueOf(r13)     // Catch:{ JSONException -> 0x011b }
            r14.setColorSpace(r13)     // Catch:{ JSONException -> 0x011b }
        L_0x0068:
            com.samsung.android.sum.core.format.MediaFormat r13 = r14.build()     // Catch:{ JSONException -> 0x011b }
            boolean r14 = r15 instanceof java.nio.ByteBuffer     // Catch:{ JSONException -> 0x011b }
            if (r14 != 0) goto L_0x007e
            boolean r14 = r15 instanceof android.graphics.Bitmap     // Catch:{ JSONException -> 0x011b }
            if (r14 == 0) goto L_0x0075
            goto L_0x007e
        L_0x0075:
            java.lang.String r13 = TAG     // Catch:{ JSONException -> 0x011b }
            java.lang.String r14 = "do nothing to obuf"
            com.samsung.android.sum.core.SLog.v((java.lang.String) r13, (java.lang.String) r14)     // Catch:{ JSONException -> 0x011b }
            r13 = 0
            goto L_0x0086
        L_0x007e:
            java.lang.Object[] r13 = new java.lang.Object[]{r13, r15}     // Catch:{ JSONException -> 0x011b }
            com.samsung.android.sum.core.buffer.MediaBuffer r13 = com.samsung.android.sum.core.buffer.MediaBuffer.of((java.lang.Object[]) r13)     // Catch:{ JSONException -> 0x011b }
        L_0x0086:
            if (r13 == 0) goto L_0x011a
            boolean r14 = r8.has(r7)     // Catch:{ JSONException -> 0x011b }
            if (r14 == 0) goto L_0x009a
            double r14 = r8.getDouble(r7)     // Catch:{ JSONException -> 0x011b }
            float r14 = (float) r14     // Catch:{ JSONException -> 0x011b }
            java.lang.Float r14 = java.lang.Float.valueOf(r14)     // Catch:{ JSONException -> 0x011b }
            r13.setExtra(r7, r14)     // Catch:{ JSONException -> 0x011b }
        L_0x009a:
            boolean r14 = r8.has(r6)     // Catch:{ JSONException -> 0x011b }
            if (r14 == 0) goto L_0x00ac
            double r14 = r8.getDouble(r6)     // Catch:{ JSONException -> 0x011b }
            float r14 = (float) r14     // Catch:{ JSONException -> 0x011b }
            java.lang.Float r14 = java.lang.Float.valueOf(r14)     // Catch:{ JSONException -> 0x011b }
            r13.setExtra(r6, r14)     // Catch:{ JSONException -> 0x011b }
        L_0x00ac:
            boolean r14 = r8.has(r5)     // Catch:{ JSONException -> 0x011b }
            r15 = 3
            r0 = 2
            r1 = 1
            r2 = 0
            java.lang.String r3 = ","
            java.lang.String r6 = ""
            java.lang.String r7 = "[^0-9|,]"
            if (r14 == 0) goto L_0x00e8
            java.lang.String r14 = r8.getString(r5)     // Catch:{ JSONException -> 0x011b }
            java.lang.String r14 = r14.replaceAll(r7, r6)     // Catch:{ JSONException -> 0x011b }
            java.lang.String[] r14 = r14.split(r3)     // Catch:{ JSONException -> 0x011b }
            android.graphics.Rect r9 = new android.graphics.Rect     // Catch:{ JSONException -> 0x011b }
            r10 = r14[r2]     // Catch:{ JSONException -> 0x011b }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ JSONException -> 0x011b }
            r11 = r14[r1]     // Catch:{ JSONException -> 0x011b }
            int r11 = java.lang.Integer.parseInt(r11)     // Catch:{ JSONException -> 0x011b }
            r12 = r14[r0]     // Catch:{ JSONException -> 0x011b }
            int r12 = java.lang.Integer.parseInt(r12)     // Catch:{ JSONException -> 0x011b }
            r14 = r14[r15]     // Catch:{ JSONException -> 0x011b }
            int r14 = java.lang.Integer.parseInt(r14)     // Catch:{ JSONException -> 0x011b }
            r9.<init>(r10, r11, r12, r14)     // Catch:{ JSONException -> 0x011b }
            r13.setExtra(r5, r9)     // Catch:{ JSONException -> 0x011b }
        L_0x00e8:
            boolean r14 = r8.has(r4)     // Catch:{ JSONException -> 0x011b }
            if (r14 == 0) goto L_0x011a
            java.lang.String r14 = r8.getString(r4)     // Catch:{ JSONException -> 0x011b }
            java.lang.String r14 = r14.replaceAll(r7, r6)     // Catch:{ JSONException -> 0x011b }
            java.lang.String[] r14 = r14.split(r3)     // Catch:{ JSONException -> 0x011b }
            android.graphics.Rect r3 = new android.graphics.Rect     // Catch:{ JSONException -> 0x011b }
            r2 = r14[r2]     // Catch:{ JSONException -> 0x011b }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ JSONException -> 0x011b }
            r1 = r14[r1]     // Catch:{ JSONException -> 0x011b }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ JSONException -> 0x011b }
            r0 = r14[r0]     // Catch:{ JSONException -> 0x011b }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ JSONException -> 0x011b }
            r14 = r14[r15]     // Catch:{ JSONException -> 0x011b }
            int r14 = java.lang.Integer.parseInt(r14)     // Catch:{ JSONException -> 0x011b }
            r3.<init>(r2, r1, r0, r14)     // Catch:{ JSONException -> 0x011b }
            r13.setExtra(r4, r3)     // Catch:{ JSONException -> 0x011b }
        L_0x011a:
            return r13
        L_0x011b:
            r13 = move-exception
            java.lang.RuntimeException r14 = new java.lang.RuntimeException
            r14.<init>(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.plugin.NativeUniImgpPlugin.makeImageBuffer(java.lang.String, java.lang.Object):com.samsung.android.sum.core.buffer.MediaBuffer");
    }

    private native int nativeCrop(String str, Object obj, String str2, Object obj2);

    private native int nativeCvtColor(String str, Object obj, String str2, Object obj2);

    private native int nativeCvtData(String str, ByteBuffer byteBuffer, String str2, ByteBuffer byteBuffer2);

    private native int nativeCvtGamutV2(String str, Object obj, String str2, Object obj2);

    private native int nativeDecode(String str, ByteBuffer byteBuffer, String str2, HashMap<String, Object> hashMap);

    private native int nativeEncode(String str, ByteBuffer byteBuffer, String str2, HashMap<String, Object> hashMap);

    private native int nativeEncodeHDR(String str, ByteBuffer byteBuffer, String str2, HashMap<String, Object> hashMap);

    private native int nativeFlipV2(String str, String str2, Object obj, String str3, Object obj2);

    private native int nativeInit(String str, String str2);

    private native int nativeMeasureQuality(String str, ByteBuffer byteBuffer, String str2, ByteBuffer byteBuffer2, HashMap<String, Object> hashMap);

    private native int nativeMerge(String str, String str2, ByteBuffer byteBuffer, String str3, HashMap<String, Object> hashMap);

    private native int nativeRelease();

    private native int nativeResize(String str, Object obj, String str2, Object obj2);

    private native int nativeRotate(String str, Object obj, String str2, Object obj2);

    private native int nativeRun(String str, ByteBuffer byteBuffer, String str2, ByteBuffer byteBuffer2, HashMap<String, Object> hashMap);

    private static native void nativeSetup();

    private native int nativeSplit(String str, String str2, ByteBuffer byteBuffer, String str3, HashMap<String, Object> hashMap);

    public MutableMediaBuffer crop(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        String str = TAG;
        SLog.v(str, "ibuf=" + mediaBuffer);
        SLog.v(str, "obuf=" + mutableMediaBuffer);
        if (mutableMediaBuffer.isEmpty()) {
            MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) mediaBuffer.getFormat().toMutableFormat().dupAll();
            mutableMediaFormat.setCropRect(mutableMediaBuffer.getFormat().getCropRect());
            mutableMediaFormat.setRows(mutableMediaBuffer.getFormat().getCropRect().height());
            mutableMediaFormat.setCols(mutableMediaBuffer.getFormat().getCropRect().width());
            mutableMediaBuffer.put(MediaBuffer.allocate(mutableMediaFormat));
        }
        JSONObject bufferToJson = bufferToJson(mediaBuffer);
        JSONObject bufferToJson2 = bufferToJson(mutableMediaBuffer);
        try {
            if (mutableMediaBuffer.getFormat().getCropRect() != null) {
                bufferToJson2.put("crop-rect", mutableMediaBuffer.getFormat().getCropRect().flattenToString());
            } else if (mutableMediaBuffer.getFixedFormat().getCropRect() != null) {
                bufferToJson2.put("crop-rect", mutableMediaBuffer.getFixedFormat().getCropRect().flattenToString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Status.from(nativeCrop(bufferToJson.toString(), getNativeSupportBuffer(mediaBuffer), bufferToJson2.toString(), getNativeSupportBuffer(mutableMediaBuffer))) == Status.OK) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        return mutableMediaBuffer;
    }

    public MutableMediaBuffer cvtColor(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        String str = TAG;
        SLog.v(str, "ibuf=" + mediaBuffer);
        SLog.v(str, "obuf=" + mutableMediaBuffer);
        if (mutableMediaBuffer.isEmpty()) {
            MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) mediaBuffer.getFormat().toMutableFormat().dup();
            mutableMediaFormat.setColorFormat(mutableMediaBuffer.getFormat().getColorFormat());
            mutableMediaBuffer.put(MediaBuffer.allocate(mutableMediaFormat));
        }
        if (Status.from(nativeCvtColor(bufferToJson(mediaBuffer).toString(), getNativeSupportBuffer(mediaBuffer), bufferToJson(mutableMediaBuffer).toString(), getNativeSupportBuffer(mutableMediaBuffer))) == Status.OK) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        return mutableMediaBuffer;
    }

    public MutableMediaBuffer cvtData(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        String str = TAG;
        SLog.v(str, "ibuf=" + mediaBuffer);
        SLog.v(str, "obuf=" + mutableMediaBuffer);
        Class cls = ByteBuffer.class;
        ByteBuffer byteBuffer = (ByteBuffer) mediaBuffer.getTypedData(cls);
        if (mutableMediaBuffer.isEmpty()) {
            MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) mediaBuffer.getFormat().toMutableFormat().dup();
            mutableMediaFormat.setDataType(mutableMediaBuffer.getFormat().getDataType());
            mutableMediaBuffer.put(MediaBuffer.allocate(mutableMediaFormat));
        }
        JSONObject bufferToJson = bufferToJson(mediaBuffer);
        JSONObject bufferToJson2 = bufferToJson(mutableMediaBuffer);
        if (Status.from(nativeCvtData(bufferToJson.toString(), byteBuffer, bufferToJson2.toString(), (ByteBuffer) mutableMediaBuffer.getTypedData(cls))) == Status.OK) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        return mutableMediaBuffer;
    }

    public MutableMediaBuffer cvtGamut(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        MutableMediaBuffer mutableMediaBuffer2;
        boolean z;
        String str = TAG;
        SLog.v(str, "run...E");
        if (mutableMediaBuffer.isEmpty()) {
            MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) mediaBuffer.getFormat().toMutableFormat().dup();
            mutableMediaFormat.setColorSpace(mutableMediaBuffer.getFormat().getColorSpace());
            mutableMediaBuffer.put(MediaBuffer.allocate(mutableMediaFormat));
        }
        ColorSpace colorSpace = mediaBuffer.getFormat().getColorSpace();
        ColorSpace colorSpace2 = mutableMediaBuffer.getFormat().getColorSpace();
        ColorSpace colorSpace3 = ColorSpace.DISPLAY_P3;
        if (colorSpace != colorSpace3) {
            SLog.d(str, "not supported colorSpace. force in-color-space " + colorSpace + " to display-p3");
            mediaBuffer = MediaBuffer.newImageAlloc().setMediaFormat(mediaBuffer.getFormat().toMutableFormat().setColorSpace(colorSpace3)).allocateMutable(mediaBuffer);
        }
        ColorSpace colorSpace4 = ColorSpace.BT709_FR;
        if (colorSpace2 == colorSpace4) {
            mutableMediaBuffer2 = mutableMediaBuffer;
        } else {
            SLog.d(str, "not supported colorSpace. force out-color-space " + colorSpace2 + " to bt709-fr");
            mutableMediaBuffer2 = MediaBuffer.mutableOf(mutableMediaBuffer.getFormat().toMutableFormat().setColorSpace(colorSpace4), mutableMediaBuffer);
        }
        Status from = Status.from(nativeCvtGamutV2(bufferToJson(mediaBuffer).toString(), getNativeSupportBuffer(mediaBuffer), bufferToJson(mutableMediaBuffer2).toString(), getNativeSupportBuffer(mutableMediaBuffer2)));
        if (mutableMediaBuffer != mutableMediaBuffer2) {
            mutableMediaBuffer.put((MediaBuffer) mutableMediaBuffer2);
        }
        if (from == Status.OK) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        SLog.v(str, "run...X");
        return mutableMediaBuffer;
    }

    public MutableMediaBuffer decode(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        HashMap hashMap = new HashMap();
        String str = TAG;
        SLog.v(str, mediaBuffer.toString());
        SLog.v(str, mutableMediaBuffer.toString());
        JSONObject bufferToJson = bufferToJson(mediaBuffer);
        JSONObject bufferToJson2 = bufferToJson(mutableMediaBuffer);
        Class cls = FileDescriptor.class;
        if (mediaBuffer.getDataClass() == cls) {
            hashMap.put("infile-descriptor", mediaBuffer.getTypedData(cls));
        }
        if (Status.from(nativeDecode(bufferToJson.toString(), (ByteBuffer) null, bufferToJson2.toString(), hashMap)) == Status.OK) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        MediaBuffer makeBufferFromMap = makeBufferFromMap(hashMap);
        if (makeBufferFromMap != null) {
            mutableMediaBuffer.put(makeBufferFromMap);
        }
        return mutableMediaBuffer;
    }

    public MutableMediaBuffer encode(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        HashMap hashMap = new HashMap();
        SLog.v(TAG, mutableMediaBuffer.toString());
        mediaBuffer.asList().forEach(new d(this, mutableMediaBuffer, hashMap, 0));
        ByteBuffer byteBuffer = (ByteBuffer) mediaBuffer.getTypedData(ByteBuffer.class);
        JSONObject bufferToJson = bufferToJson(mediaBuffer);
        JSONObject bufferToJson2 = bufferToJson(mutableMediaBuffer);
        try {
            if (mutableMediaBuffer.getFormat().contains(Message.KEY_OUT_FILE)) {
                bufferToJson2.put(Message.KEY_OUT_FILE, (String) mutableMediaBuffer.getFormat().get(Message.KEY_OUT_FILE));
            }
            if (mutableMediaBuffer.getFormat().contains(Message.KEY_FILE_DESCRIPTOR)) {
                hashMap.put("outfile-descriptor", (FileDescriptor) mutableMediaBuffer.getFormat().get(Message.KEY_FILE_DESCRIPTOR));
            }
            if (mutableMediaBuffer.getFormat().getCodecType() != CodecType.NONE) {
                bufferToJson2.put("codec-type", mutableMediaBuffer.getFormat().getCodecType().stringfy());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Status.from(nativeEncode(bufferToJson.toString(), byteBuffer, bufferToJson2.toString(), hashMap)) == Status.OK) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        return mutableMediaBuffer;
    }

    public MutableMediaBuffer encodeHDR(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        if (isHDRSupported()) {
            HashMap hashMap = new HashMap();
            SLog.v(TAG, mutableMediaBuffer.toString());
            mediaBuffer.asList().forEach(new d(this, mutableMediaBuffer, hashMap, 1));
            ByteBuffer byteBuffer = (ByteBuffer) mediaBuffer.getTypedData(ByteBuffer.class);
            JSONObject bufferToJson = bufferToJson(mediaBuffer);
            JSONObject bufferToJson2 = bufferToJson(mutableMediaBuffer);
            try {
                if (mutableMediaBuffer.getFormat().contains(Message.KEY_OUT_FILE)) {
                    bufferToJson2.put(Message.KEY_OUT_FILE, (String) mutableMediaBuffer.getFormat().get(Message.KEY_OUT_FILE));
                }
                if (mutableMediaBuffer.getFormat().contains(Message.KEY_FILE_DESCRIPTOR)) {
                    hashMap.put("outfile-descriptor", (FileDescriptor) mutableMediaBuffer.getFormat().get(Message.KEY_FILE_DESCRIPTOR));
                }
                if (mutableMediaBuffer.getFormat().getCodecType() != CodecType.NONE) {
                    if (mutableMediaBuffer.getFormat().getCodecType() == CodecType.HEIF) {
                        if (!isHeifSupported(mediaBuffer, mutableMediaBuffer.getFormat().getShape())) {
                            throw new UnsupportedOperationException("encode size must bigger than [512x512]");
                        }
                    }
                    bufferToJson2.put("codec-type", mutableMediaBuffer.getFormat().getCodecType().stringfy());
                }
                if (mutableMediaBuffer.containsExtra("exposure-value")) {
                    bufferToJson2.put("exposure-value", ((Integer) mutableMediaBuffer.getExtra("exposure-value")).intValue());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (Status.from(nativeEncodeHDR(bufferToJson.toString(), byteBuffer, bufferToJson2.toString(), hashMap)) == Status.OK) {
                z = true;
            } else {
                z = false;
            }
            Def.check(z);
            return mutableMediaBuffer;
        }
        throw new UnsupportedOperationException("HDR is not supported!");
    }

    public MutableMediaBuffer flip(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        String str = TAG;
        SLog.v(str, "ibuf=" + mediaBuffer);
        SLog.v(str, "obuf=" + mutableMediaBuffer);
        Object nativeSupportBuffer = getNativeSupportBuffer(mediaBuffer);
        if (mutableMediaBuffer.isEmpty()) {
            MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) mediaBuffer.getFormat().toMutableFormat().dup();
            mutableMediaFormat.setFlipType(mutableMediaBuffer.getFormat().getFlipType());
            mutableMediaBuffer.put(MediaBuffer.allocate(mutableMediaFormat));
        }
        if (Status.from(nativeFlipV2(createJsonImgpOption(mediaBuffer, mutableMediaBuffer).toString(), bufferToJson(mediaBuffer).toString(), nativeSupportBuffer, bufferToJson(mutableMediaBuffer).toString(), getNativeSupportBuffer(mutableMediaBuffer))) == Status.OK) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        return mutableMediaBuffer;
    }

    public MutableMediaBuffer measureQuality(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        String str = TAG;
        SLog.v(str, "ibuf=" + mediaBuffer);
        SLog.v(str, "obuf=" + mutableMediaBuffer);
        boolean z3 = true;
        if (mediaBuffer.asList().size() == 2) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z, "ibuf size is not 2", new Object[0]);
        if (!mutableMediaBuffer.getFormat().contains("quality-metric")) {
            SLog.v(str, "quality-metric is not given. force to PSNR");
        } else if (((Integer) mutableMediaBuffer.getFormat().get("quality-metric")).intValue() != 0) {
            throw new UnsupportedOperationException("currently only PSNR is supported!");
        }
        HashMap hashMap = new HashMap();
        Class cls = ByteBuffer.class;
        if (Status.from(nativeMeasureQuality(bufferToJson(mediaBuffer.asList().get(0)).toString(), (ByteBuffer) mediaBuffer.asList().get(0).getTypedData(cls), bufferToJson(mediaBuffer.asList().get(1)).toString(), (ByteBuffer) mediaBuffer.asList().get(1).getTypedData(cls), hashMap)) != Status.OK) {
            z3 = false;
        }
        Def.check(z3);
        mutableMediaBuffer.put(MediaBuffer.scalaOf((Float) hashMap.get("quality-value")));
        return mutableMediaBuffer;
    }

    public MutableMediaBuffer merge(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        ByteBuffer byteBuffer;
        HashMap hashMap = new HashMap();
        Class cls = ByteBuffer.class;
        if (mediaBuffer.getData() != null) {
            byteBuffer = (ByteBuffer) mediaBuffer.getTypedData(cls);
        } else {
            byteBuffer = null;
        }
        ByteBuffer byteBuffer2 = byteBuffer;
        if (mutableMediaBuffer.getFormat() == null || mutableMediaBuffer.getFormat().getShape() == null) {
            MutableMediaFormat mutableFormat = mutableMediaBuffer.getFormat().toMutableFormat();
            mutableFormat.setShape(mediaBuffer.getFormat().getShape());
            mutableMediaBuffer.setFixedFormat(mutableFormat);
        }
        String str = TAG;
        SLog.v(str, mediaBuffer.toString());
        SLog.v(str, mutableMediaBuffer.toString());
        JSONObject bufferToJson = bufferToJson(mediaBuffer);
        JSONObject bufferToJson2 = bufferToJson(mutableMediaBuffer);
        List<MediaBuffer> asList = mediaBuffer.asList();
        try {
            bufferToJson.put("block-num", asList.size());
            boolean z = false;
            int i2 = 0;
            for (MediaBuffer next : asList) {
                hashMap.put(C0212a.j(i2, "block", "-format"), bufferToJson(next).toString());
                hashMap.put("block" + i2 + "-data", next.getTypedData(cls));
                i2++;
            }
            String jSONObject = createJsonImgpOption(mediaBuffer, mutableMediaBuffer).toString();
            String jSONObject2 = bufferToJson.toString();
            if (Status.from(nativeMerge(jSONObject, jSONObject2, byteBuffer2, bufferToJson2.toString(), hashMap)) == Status.OK) {
                z = true;
            }
            Def.check(z);
            MediaBuffer makeBufferFromMap = makeBufferFromMap(hashMap);
            if (makeBufferFromMap != null) {
                mutableMediaBuffer.put(makeBufferFromMap);
            }
            return mutableMediaBuffer;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a0 A[SYNTHETIC, Splitter:B:30:0x00a0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.sum.core.buffer.MediaBuffer readCompressedImage(com.samsung.android.sum.core.format.MediaFormat r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = "decode color-format"
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "read compressed image: "
            r2.<init>(r3)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            com.samsung.android.sum.core.SLog.v((java.lang.String) r1, (java.lang.String) r2)
            r2 = 46
            int r2 = r7.lastIndexOf(r2)
            int r2 = r2 + 1
            java.lang.String r2 = r7.substring(r2)
            java.util.Locale r3 = java.util.Locale.ROOT
            java.lang.String r2 = r2.toLowerCase(r3)
            java.lang.String r3 = "jpg"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x0040
            java.lang.String r3 = "heic"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0038
            goto L_0x0040
        L_0x0038:
            java.lang.UnsupportedOperationException r5 = new java.lang.UnsupportedOperationException
            java.lang.String r6 = "not supported yet"
            r5.<init>(r6)
            throw r5
        L_0x0040:
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0097 }
            r3.<init>(r7)     // Catch:{ IOException -> 0x0097 }
            java.io.FileDescriptor r7 = r3.getFD()     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            com.samsung.android.sum.core.types.MediaType r2 = com.samsung.android.sum.core.types.MediaType.COMPRESSED_IMAGE     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            java.lang.Object[] r7 = new java.lang.Object[]{r2, r7}     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            com.samsung.android.sum.core.buffer.MediaBuffer r7 = com.samsung.android.sum.core.buffer.MediaBuffer.of((java.lang.Object[]) r7)     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            com.samsung.android.sum.core.format.MutableMediaFormat r2 = r6.toMutableFormat()     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            com.samsung.android.sum.core.types.ColorFormat r6 = r6.getColorFormat()     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            com.samsung.android.sum.core.types.ColorFormat r4 = com.samsung.android.sum.core.types.ColorFormat.NONE     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            if (r6 != r4) goto L_0x006c
            com.samsung.android.sum.core.types.ColorFormat r6 = com.samsung.android.sum.core.types.ColorFormat.RGBA     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            r2.setColorFormat(r6)     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            goto L_0x006c
        L_0x0066:
            r5 = move-exception
            r2 = r3
            goto L_0x009e
        L_0x0069:
            r5 = move-exception
            r2 = r3
            goto L_0x0098
        L_0x006c:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            r6.<init>(r0)     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            com.samsung.android.sum.core.types.ColorFormat r0 = r2.getColorFormat()     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            r6.append(r0)     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            com.samsung.android.sum.core.SLog.v((java.lang.String) r1, (java.lang.String) r6)     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            java.lang.Object[] r6 = new java.lang.Object[]{r2}     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            com.samsung.android.sum.core.buffer.MutableMediaBuffer r6 = com.samsung.android.sum.core.buffer.MediaBuffer.mutableOf(r6)     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            r5.decode(r7, r6)     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            r3.close()     // Catch:{ IOException -> 0x008e }
            return r6
        L_0x008e:
            r5 = move-exception
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            r6.<init>(r5)
            throw r6
        L_0x0095:
            r5 = move-exception
            goto L_0x009e
        L_0x0097:
            r5 = move-exception
        L_0x0098:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ all -> 0x0095 }
            r6.<init>(r5)     // Catch:{ all -> 0x0095 }
            throw r6     // Catch:{ all -> 0x0095 }
        L_0x009e:
            if (r2 == 0) goto L_0x00ab
            r2.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x00ab
        L_0x00a4:
            r5 = move-exception
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            r6.<init>(r5)
            throw r6
        L_0x00ab:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.plugin.NativeUniImgpPlugin.readCompressedImage(com.samsung.android.sum.core.format.MediaFormat, java.lang.String):com.samsung.android.sum.core.buffer.MediaBuffer");
    }

    public void release() {
        if (this.nativeContext != 0 || this.nativeUniImgpOpContext != 0) {
            try {
                this.lock.lock();
                nativeRelease();
                this.nativeContext = 0;
                this.nativeUniImgpOpContext = 0;
            } finally {
                this.lock.unlock();
            }
        }
    }

    public MutableMediaBuffer resize(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        String str = TAG;
        SLog.v(str, "ibuf=" + mediaBuffer);
        SLog.v(str, "obuf=" + mutableMediaBuffer);
        if (mutableMediaBuffer.isEmpty()) {
            MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) mediaBuffer.getFormat().toMutableFormat().dup();
            if (mutableMediaBuffer.getFormat().contains("scale")) {
                float floatValue = ((Float) mutableMediaBuffer.getFormat().get("scale")).floatValue();
                mutableMediaFormat.setCols((int) (((float) mediaBuffer.getCols()) * floatValue));
                mutableMediaFormat.setRows((int) (((float) mediaBuffer.getRows()) * floatValue));
            } else {
                mutableMediaFormat.setShape(mutableMediaBuffer.getFormat().getShape());
            }
            mutableMediaBuffer.put(MediaBuffer.allocate(mutableMediaFormat));
        }
        if (Status.from(nativeResize(bufferToJson(mediaBuffer).toString(), getNativeSupportBuffer(mediaBuffer), bufferToJson(mutableMediaBuffer).toString(), getNativeSupportBuffer(mutableMediaBuffer))) == Status.OK) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        return mutableMediaBuffer;
    }

    public MutableMediaBuffer rotate(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        String str = TAG;
        SLog.v(str, "ibuf=" + mediaBuffer);
        SLog.v(str, "obuf=" + mutableMediaBuffer);
        if (mutableMediaBuffer.isEmpty()) {
            MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) mediaBuffer.getFormat().toMutableFormat().dup();
            mutableMediaFormat.setRotation(mutableMediaBuffer.getFormat().getRotation());
            mutableMediaBuffer.put(MediaBuffer.allocate(mutableMediaFormat));
        }
        if (Status.from(nativeRotate(bufferToJson(mediaBuffer).toString(), getNativeSupportBuffer(mediaBuffer), bufferToJson(mutableMediaBuffer).toString(), getNativeSupportBuffer(mutableMediaBuffer))) == Status.OK) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        return mutableMediaBuffer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:83:0x01d1 A[SYNTHETIC, Splitter:B:83:0x01d1] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x021e A[Catch:{ all -> 0x0026 }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0224 A[Catch:{ all -> 0x0026 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.sum.core.buffer.MutableMediaBuffer run(com.samsung.android.sum.core.buffer.MediaBuffer r16, com.samsung.android.sum.core.buffer.MutableMediaBuffer r17) {
        /*
            r15 = this;
            r0 = r16
            r7 = r17
            java.lang.Class<java.nio.ByteBuffer> r2 = java.nio.ByteBuffer.class
            java.lang.String r3 = "scale"
            java.util.concurrent.locks.ReentrantLock r4 = r15.lock     // Catch:{ all -> 0x0026 }
            r4.lock()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.format.MediaFormat r4 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            java.lang.String r5 = "encode-hdr"
            boolean r4 = r4.contains(r5)     // Catch:{ all -> 0x0026 }
            if (r4 == 0) goto L_0x0029
            boolean r4 = r15.isHDRSupported()     // Catch:{ all -> 0x0026 }
            if (r4 == 0) goto L_0x001e
            goto L_0x0029
        L_0x001e:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x0026 }
            java.lang.String r2 = "HDR is not supported!"
            r0.<init>(r2)     // Catch:{ all -> 0x0026 }
            throw r0     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r0 = move-exception
            goto L_0x0242
        L_0x0029:
            long r4 = r15.nativeContext     // Catch:{ all -> 0x0026 }
            r8 = 0
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 == 0) goto L_0x023c
            com.samsung.android.sum.core.format.MediaFormat r4 = r0.getFormat()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.MediaType r4 = r4.getMediaType()     // Catch:{ all -> 0x0026 }
            int r4 = r4.flag()     // Catch:{ all -> 0x0026 }
            r10 = 0
            r11 = 1
            if (r4 != r11) goto L_0x0043
            r4 = r11
            goto L_0x0044
        L_0x0043:
            r4 = r10
        L_0x0044:
            com.samsung.android.sum.core.format.MediaFormat r5 = r7.getFormat()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.MediaType r5 = r5.getMediaType()     // Catch:{ all -> 0x0026 }
            int r5 = r5.flag()     // Catch:{ all -> 0x0026 }
            if (r5 != r11) goto L_0x0054
            r5 = r11
            goto L_0x0055
        L_0x0054:
            r5 = r10
        L_0x0055:
            java.lang.Object r6 = r0.getTypedData(r2)     // Catch:{ all -> 0x0026 }
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6     // Catch:{ all -> 0x0026 }
            boolean r12 = r7.isEmpty()     // Catch:{ all -> 0x0026 }
            if (r12 != 0) goto L_0x0063
            if (r5 == 0) goto L_0x01ad
        L_0x0063:
            com.samsung.android.sum.core.format.MediaFormat r12 = r0.getFormat()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.format.MutableMediaFormat r12 = r12.toMutableFormat()     // Catch:{ all -> 0x0026 }
            java.lang.Object r12 = r12.dup()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.format.MutableMediaFormat r12 = (com.samsung.android.sum.core.format.MutableMediaFormat) r12     // Catch:{ all -> 0x0026 }
            if (r4 == 0) goto L_0x00af
            if (r5 == 0) goto L_0x009c
            com.samsung.android.sum.core.format.MediaFormat r13 = r15.persistentInputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorFormat r13 = r13.getColorFormat()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorFormat r14 = com.samsung.android.sum.core.types.ColorFormat.NONE     // Catch:{ all -> 0x0026 }
            if (r13 == r14) goto L_0x00af
            com.samsung.android.sum.core.format.MediaFormat r13 = r15.persistentInputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorFormat r13 = r13.getColorFormat()     // Catch:{ all -> 0x0026 }
            r12.setColorFormat(r13)     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.DataType r13 = com.samsung.android.sum.core.types.DataType.U8     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.format.MediaFormat r14 = r15.persistentInputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorFormat r14 = r14.getColorFormat()     // Catch:{ all -> 0x0026 }
            int r14 = r14.getChannels()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.DataType r13 = com.samsung.android.sum.core.types.DataType.of(r13, r14)     // Catch:{ all -> 0x0026 }
            r12.setDataType(r13)     // Catch:{ all -> 0x0026 }
            goto L_0x00af
        L_0x009c:
            com.samsung.android.sum.core.types.DataType r13 = com.samsung.android.sum.core.types.DataType.U8     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.format.MediaFormat r14 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorFormat r14 = r14.getColorFormat()     // Catch:{ all -> 0x0026 }
            int r14 = r14.getChannels()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.DataType r13 = com.samsung.android.sum.core.types.DataType.of(r13, r14)     // Catch:{ all -> 0x0026 }
            r12.setDataType(r13)     // Catch:{ all -> 0x0026 }
        L_0x00af:
            if (r5 == 0) goto L_0x00b7
            com.samsung.android.sum.core.types.MediaType r13 = com.samsung.android.sum.core.types.MediaType.COMPRESSED_IMAGE     // Catch:{ all -> 0x0026 }
            r12.setMediaType(r13)     // Catch:{ all -> 0x0026 }
            goto L_0x00bc
        L_0x00b7:
            com.samsung.android.sum.core.types.MediaType r13 = com.samsung.android.sum.core.types.MediaType.IMAGE     // Catch:{ all -> 0x0026 }
            r12.setMediaType(r13)     // Catch:{ all -> 0x0026 }
        L_0x00bc:
            com.samsung.android.sum.core.format.MediaFormat r13 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            boolean r13 = r13.contains(r3)     // Catch:{ all -> 0x0026 }
            if (r13 == 0) goto L_0x00e7
            com.samsung.android.sum.core.format.MediaFormat r13 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            java.lang.Object r13 = r13.get(r3)     // Catch:{ all -> 0x0026 }
            java.lang.Float r13 = (java.lang.Float) r13     // Catch:{ all -> 0x0026 }
            float r14 = r13.floatValue()     // Catch:{ all -> 0x0026 }
            r12.set(r3, r13)     // Catch:{ all -> 0x0026 }
            int r3 = r0.getCols()     // Catch:{ all -> 0x0026 }
            float r3 = (float) r3     // Catch:{ all -> 0x0026 }
            float r3 = r3 * r14
            int r3 = (int) r3     // Catch:{ all -> 0x0026 }
            r12.setCols(r3)     // Catch:{ all -> 0x0026 }
            int r3 = r0.getRows()     // Catch:{ all -> 0x0026 }
            float r3 = (float) r3     // Catch:{ all -> 0x0026 }
            float r3 = r3 * r14
            int r3 = (int) r3     // Catch:{ all -> 0x0026 }
            r12.setRows(r3)     // Catch:{ all -> 0x0026 }
        L_0x00e7:
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.format.Shape r3 = r3.getShape()     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x00f8
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.format.Shape r3 = r3.getShape()     // Catch:{ all -> 0x0026 }
            r12.setShape(r3)     // Catch:{ all -> 0x0026 }
        L_0x00f8:
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            android.graphics.Rect r3 = r3.getCropRect()     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x0109
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            android.graphics.Rect r3 = r3.getCropRect()     // Catch:{ all -> 0x0026 }
            r12.setCropRect(r3)     // Catch:{ all -> 0x0026 }
        L_0x0109:
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            int r3 = r3.getRotation()     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x011a
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            int r3 = r3.getRotation()     // Catch:{ all -> 0x0026 }
            r12.setRotation(r3)     // Catch:{ all -> 0x0026 }
        L_0x011a:
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.FlipType r3 = r3.getFlipType()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.FlipType r13 = com.samsung.android.sum.core.types.FlipType.NONE     // Catch:{ all -> 0x0026 }
            if (r3 == r13) goto L_0x012d
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.FlipType r3 = r3.getFlipType()     // Catch:{ all -> 0x0026 }
            r12.setFlipType(r3)     // Catch:{ all -> 0x0026 }
        L_0x012d:
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorFormat r3 = r3.getColorFormat()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorFormat r13 = com.samsung.android.sum.core.types.ColorFormat.NONE     // Catch:{ all -> 0x0026 }
            if (r3 == r13) goto L_0x0140
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorFormat r3 = r3.getColorFormat()     // Catch:{ all -> 0x0026 }
            r12.setColorFormat(r3)     // Catch:{ all -> 0x0026 }
        L_0x0140:
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorSpace r3 = r3.getColorSpace()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorSpace r13 = com.samsung.android.sum.core.types.ColorSpace.NONE     // Catch:{ all -> 0x0026 }
            if (r3 == r13) goto L_0x0153
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentInputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.ColorSpace r3 = r3.getColorSpace()     // Catch:{ all -> 0x0026 }
            r12.setColorSpace(r3)     // Catch:{ all -> 0x0026 }
        L_0x0153:
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.CodecType r3 = r3.getCodecType()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.CodecType r13 = com.samsung.android.sum.core.types.CodecType.NONE     // Catch:{ all -> 0x0026 }
            if (r3 == r13) goto L_0x0185
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.CodecType r3 = r3.getCodecType()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.CodecType r13 = com.samsung.android.sum.core.types.CodecType.HEIF     // Catch:{ all -> 0x0026 }
            if (r3 != r13) goto L_0x017c
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.format.Shape r3 = r3.getShape()     // Catch:{ all -> 0x0026 }
            boolean r3 = r15.isHeifSupported(r0, r3)     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x0174
            goto L_0x017c
        L_0x0174:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x0026 }
            java.lang.String r2 = "encode size must bigger than [512x512]"
            r0.<init>(r2)     // Catch:{ all -> 0x0026 }
            throw r0     // Catch:{ all -> 0x0026 }
        L_0x017c:
            com.samsung.android.sum.core.format.MediaFormat r3 = r15.persistentOutputFormat     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.CodecType r3 = r3.getCodecType()     // Catch:{ all -> 0x0026 }
            r12.setCodecType(r3)     // Catch:{ all -> 0x0026 }
        L_0x0185:
            if (r5 == 0) goto L_0x0197
            java.lang.Object r3 = r7.getData()     // Catch:{ all -> 0x0026 }
            java.lang.Object[] r3 = new java.lang.Object[]{r12, r3}     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.buffer.MediaBuffer r3 = com.samsung.android.sum.core.buffer.MediaBuffer.of((java.lang.Object[]) r3)     // Catch:{ all -> 0x0026 }
            r7.put((com.samsung.android.sum.core.buffer.MediaBuffer) r3)     // Catch:{ all -> 0x0026 }
            goto L_0x01ad
        L_0x0197:
            long r13 = r12.size()     // Catch:{ all -> 0x0026 }
            int r3 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1))
            if (r3 == 0) goto L_0x01aa
            if (r4 == 0) goto L_0x01a2
            goto L_0x01aa
        L_0x01a2:
            com.samsung.android.sum.core.buffer.MediaBuffer r3 = com.samsung.android.sum.core.buffer.MediaBuffer.allocate((com.samsung.android.sum.core.format.MutableMediaFormat) r12)     // Catch:{ all -> 0x0026 }
            r7.put((com.samsung.android.sum.core.buffer.MediaBuffer) r3)     // Catch:{ all -> 0x0026 }
            goto L_0x01ad
        L_0x01aa:
            r7.setFixedFormat(r12)     // Catch:{ all -> 0x0026 }
        L_0x01ad:
            if (r5 != 0) goto L_0x01be
            boolean r3 = r7.isEmpty()     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x01b6
            goto L_0x01be
        L_0x01b6:
            java.lang.Object r2 = r7.getTypedData(r2)     // Catch:{ all -> 0x0026 }
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2     // Catch:{ all -> 0x0026 }
        L_0x01bc:
            r3 = r6
            goto L_0x01c0
        L_0x01be:
            r2 = 0
            goto L_0x01bc
        L_0x01c0:
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ all -> 0x0026 }
            r6.<init>()     // Catch:{ all -> 0x0026 }
            org.json.JSONObject r8 = r15.bufferToJson(r16)     // Catch:{ all -> 0x0026 }
            org.json.JSONObject r9 = r15.bufferToJson(r7)     // Catch:{ all -> 0x0026 }
            java.lang.Class<java.io.FileDescriptor> r12 = java.io.FileDescriptor.class
            if (r4 == 0) goto L_0x01e4
            java.lang.Object r4 = r0.getData()     // Catch:{ all -> 0x0026 }
            java.lang.Class r4 = r4.getClass()     // Catch:{ all -> 0x0026 }
            if (r4 != r12) goto L_0x01e4
            java.lang.String r4 = "infile-descriptor"
            java.lang.Object r13 = r0.getTypedData(r12)     // Catch:{ all -> 0x0026 }
            r6.put(r4, r13)     // Catch:{ all -> 0x0026 }
        L_0x01e4:
            if (r5 == 0) goto L_0x01f9
            java.lang.Object r4 = r7.getData()     // Catch:{ all -> 0x0026 }
            java.lang.Class r4 = r4.getClass()     // Catch:{ all -> 0x0026 }
            if (r4 != r12) goto L_0x01f9
            java.lang.String r4 = "outfile-descriptor"
            java.lang.Object r5 = r7.getTypedData(r12)     // Catch:{ all -> 0x0026 }
            r6.put(r4, r5)     // Catch:{ all -> 0x0026 }
        L_0x01f9:
            java.util.List r0 = r0.asList()     // Catch:{ all -> 0x0026 }
            com.samsung.android.gallery.settings.activity.e r4 = new com.samsung.android.gallery.settings.activity.e     // Catch:{ all -> 0x0026 }
            r5 = 12
            r4.<init>(r5, r15, r6)     // Catch:{ all -> 0x0026 }
            r0.forEach(r4)     // Catch:{ all -> 0x0026 }
            java.lang.String r0 = r8.toString()     // Catch:{ all -> 0x0026 }
            java.lang.String r4 = r9.toString()     // Catch:{ all -> 0x0026 }
            r1 = r15
            r5 = r2
            r2 = r0
            int r0 = r1.nativeRun(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.Status r0 = com.samsung.android.sum.core.types.Status.from(r0)     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.types.Status r2 = com.samsung.android.sum.core.types.Status.OK     // Catch:{ all -> 0x0026 }
            if (r0 != r2) goto L_0x021f
            r10 = r11
        L_0x021f:
            com.samsung.android.sum.core.Def.check(r10)     // Catch:{ all -> 0x0026 }
            if (r5 != 0) goto L_0x0236
            com.samsung.android.sum.core.buffer.MediaBuffer r0 = r15.makeBufferFromMap(r6)     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x022d
            r7.put((com.samsung.android.sum.core.buffer.MediaBuffer) r0)     // Catch:{ all -> 0x0026 }
        L_0x022d:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x0026 }
            java.lang.String r2 = r7.toString()     // Catch:{ all -> 0x0026 }
            com.samsung.android.sum.core.SLog.v((java.lang.String) r0, (java.lang.String) r2)     // Catch:{ all -> 0x0026 }
        L_0x0236:
            java.util.concurrent.locks.ReentrantLock r0 = r15.lock
            r0.unlock()
            return r7
        L_0x023c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0026 }
            r0.<init>()     // Catch:{ all -> 0x0026 }
            throw r0     // Catch:{ all -> 0x0026 }
        L_0x0242:
            java.util.concurrent.locks.ReentrantLock r1 = r15.lock
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.plugin.NativeUniImgpPlugin.run(com.samsung.android.sum.core.buffer.MediaBuffer, com.samsung.android.sum.core.buffer.MutableMediaBuffer):com.samsung.android.sum.core.buffer.MutableMediaBuffer");
    }

    public MutableMediaBuffer split(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean z;
        HashMap hashMap = new HashMap();
        String str = TAG;
        SLog.v(str, mediaBuffer.toString());
        SLog.v(str, mutableMediaBuffer.toString());
        JSONObject bufferToJson = bufferToJson(mediaBuffer);
        JSONObject bufferToJson2 = bufferToJson(mutableMediaBuffer);
        String jSONObject = createJsonImgpOption(mediaBuffer, mutableMediaBuffer).toString();
        String jSONObject2 = bufferToJson.toString();
        String jSONObject3 = bufferToJson2.toString();
        if (Status.from(nativeSplit(jSONObject, jSONObject2, (ByteBuffer) mediaBuffer.getTypedData(ByteBuffer.class), jSONObject3, hashMap)) == Status.OK) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z);
        MediaBuffer makeBufferFromMap = makeBufferFromMap(hashMap);
        if (makeBufferFromMap != null) {
            mutableMediaBuffer.put(makeBufferFromMap);
        }
        return mutableMediaBuffer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0099 A[SYNTHETIC, Splitter:B:30:0x0099] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean writeCompressedImage(com.samsung.android.sum.core.buffer.MediaBuffer r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "write compressed image: "
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            com.samsung.android.sum.core.SLog.v((java.lang.String) r0, (java.lang.String) r1)
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0090 }
            r1.<init>(r7)     // Catch:{ IOException -> 0x0090 }
            java.io.FileDescriptor r0 = r1.getFD()     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            com.samsung.android.sum.core.format.MediaFormatBuilder r2 = com.samsung.android.sum.core.format.MediaFormat.newImageBuilder()     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            com.samsung.android.sum.core.format.MediaFormatBuilder r2 = r2.asCompressed()     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            com.samsung.android.sum.core.types.ColorFormat r3 = com.samsung.android.sum.core.types.ColorFormat.NV12     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            com.samsung.android.sum.core.format.MediaFormatBuilder r2 = r2.setColorFormat(r3)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            com.samsung.android.sum.core.format.MutableMediaFormat r2 = r2.buildMutable()     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.lang.String r3 = "file-descriptor"
            r2.set(r3, r0)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            r0 = 46
            int r0 = r7.lastIndexOf(r0)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            r3 = 1
            int r0 = r0 + r3
            java.lang.String r0 = r7.substring(r0)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.util.Locale r4 = java.util.Locale.ROOT     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.lang.String r0 = r0.toLowerCase(r4)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.lang.String r4 = "jpg"
            boolean r4 = r0.equals(r4)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            if (r4 == 0) goto L_0x005a
            com.samsung.android.sum.core.types.CodecType r0 = com.samsung.android.sum.core.types.CodecType.JPEG_QURAM     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            r2.setCodecType(r0)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            goto L_0x0067
        L_0x0054:
            r5 = move-exception
            r0 = r1
            goto L_0x0097
        L_0x0057:
            r5 = move-exception
            r0 = r1
            goto L_0x0091
        L_0x005a:
            java.lang.String r4 = "heic"
            boolean r0 = r0.equals(r4)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            if (r0 == 0) goto L_0x0086
            com.samsung.android.sum.core.types.CodecType r0 = com.samsung.android.sum.core.types.CodecType.HEIF     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            r2.setCodecType(r0)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
        L_0x0067:
            java.lang.Object[] r0 = new java.lang.Object[]{r2}     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            com.samsung.android.sum.core.buffer.MutableMediaBuffer r0 = com.samsung.android.sum.core.buffer.MediaBuffer.mutableOf(r0)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            r5.encode(r6, r0)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            r1.close()     // Catch:{ IOException -> 0x0076 }
            goto L_0x007a
        L_0x0076:
            r5 = move-exception
            r5.printStackTrace()
        L_0x007a:
            java.lang.String r5 = TAG
            java.lang.String r6 = "succes to save"
            java.lang.String r6 = r6.concat(r7)
            com.samsung.android.sum.core.SLog.i((java.lang.String) r5, (java.lang.String) r6)
            return r3
        L_0x0086:
            java.lang.UnsupportedOperationException r5 = new java.lang.UnsupportedOperationException     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.lang.String r6 = "not supported yet"
            r5.<init>(r6)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            throw r5     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
        L_0x008e:
            r5 = move-exception
            goto L_0x0097
        L_0x0090:
            r5 = move-exception
        L_0x0091:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ all -> 0x008e }
            r6.<init>(r5)     // Catch:{ all -> 0x008e }
            throw r6     // Catch:{ all -> 0x008e }
        L_0x0097:
            if (r0 == 0) goto L_0x00a1
            r0.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x00a1
        L_0x009d:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00a1:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.plugin.NativeUniImgpPlugin.writeCompressedImage(com.samsung.android.sum.core.buffer.MediaBuffer, java.lang.String):boolean");
    }

    public void bindToFixture(ImgpPlugin imgpPlugin) {
        imgpPlugin.setDescriptorLoader(new f(0));
        imgpPlugin.setImgProcessor(ImgpType.RESIZE, new e(this, 12));
        imgpPlugin.setImgProcessor(ImgpType.CVT_COLOR, new e(this, 13));
        imgpPlugin.setImgProcessor(ImgpType.CVT_DATA, new e(this, 0));
        imgpPlugin.setImgProcessor(ImgpType.CVT_GAMUT, new e(this, 1));
        imgpPlugin.setImgProcessor(ImgpType.CVT_HDR2SDR, new e(this, 2));
        imgpPlugin.setImgProcessor(ImgpType.ROTATE, new e(this, 3));
        imgpPlugin.setImgProcessor(ImgpType.FLIP, new e(this, 4));
        imgpPlugin.setImgProcessor(ImgpType.CROP, new e(this, 5));
        imgpPlugin.setImgProcessor(ImgpType.SPLIT, new e(this, 6));
        imgpPlugin.setImgProcessor(ImgpType.MERGE, new e(this, 7));
        imgpPlugin.setImgProcessor(ImgpType.QUALITY_MEASURE, new e(this, 8));
        imgpPlugin.setImgProcessor(ImgpType.DECODE, new e(this, 9));
        imgpPlugin.setImgProcessor(ImgpType.ENCODE, new e(this, 10));
        imgpPlugin.setImgProcessor(ImgpType.ENCODE_HDR, new e(this, 11));
        imgpPlugin.setDeInitializer(new g(this));
    }

    public NativeUniImgpPlugin(List<ImgpType> list, MediaFormat mediaFormat, MediaFormat mediaFormat2, ColorFormat colorFormat) {
        String str = TAG;
        SLog.d(str, "NativeUniImgpPlugin: version= [core=" + Def.getCoreVersion() + "]");
        StringBuilder sb2 = new StringBuilder("opList=");
        sb2.append(list);
        SLog.d(str, sb2.toString());
        this.persistentInputFormat = mediaFormat;
        this.persistentOutputFormat = mediaFormat2;
        if (colorFormat != null) {
            this.preferredColorFormat = colorFormat;
        } else {
            this.preferredColorFormat = ColorFormat.NONE;
        }
        JSONArray jSONArray = new JSONArray();
        for (ImgpType next : list) {
            if (next != ImgpType.ENCODE_HDR || isHDRSupported()) {
                jSONArray.put(next.stringfy());
            } else {
                throw new UnsupportedOperationException("HDR is not supported!");
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            ColorFormat colorFormat2 = this.preferredColorFormat;
            if (colorFormat2 != ColorFormat.NONE) {
                jSONObject.put("preferred-color-format", colorFormat2.stringfy());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        nativeInit(jSONArray.toString(), jSONObject.toString());
    }

    public MutableMediaBuffer cvtHdr2Sdr(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        return mutableMediaBuffer;
    }
}
