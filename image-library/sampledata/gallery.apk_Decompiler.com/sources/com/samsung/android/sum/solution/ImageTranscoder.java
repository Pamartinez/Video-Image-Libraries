package com.samsung.android.sum.solution;

import android.graphics.Rect;
import android.util.Pair;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.plugin.ImgpPlugin;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.FlipType;
import com.samsung.android.sum.core.types.ImgpType;
import com.samsung.android.sum.core.types.MimeType;
import com.samsung.android.sum.core.types.PadType;
import com.samsung.android.sum.core.types.SplitType;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageTranscoder implements AutoCloseable {
    private final String TAG = SLog.tagOf(ImageTranscoder.class);
    private final boolean convertToSRGB;
    private final MimeType inputMimeType;
    private final AtomicBoolean isReleased = new AtomicBoolean(false);
    private long nativeContext;
    List<Pair<ImgpType, ImgpPlugin.Type>> opList = new ArrayList();
    private final MimeType outputMimeType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean convertToSRGB = false;
        /* access modifiers changed from: private */
        public MimeType inputMimeType;
        /* access modifiers changed from: private */
        public MimeType outputMimeType;

        public Builder() {
            MimeType mimeType = MimeType.NONE;
            this.inputMimeType = mimeType;
            this.outputMimeType = mimeType;
        }

        public ImageTranscoder build() {
            return new ImageTranscoder(this);
        }

        public Builder convertToSRGB(boolean z) {
            this.convertToSRGB = z;
            return this;
        }

        public Builder setInputMimeType(MimeType mimeType) {
            this.inputMimeType = mimeType;
            return this;
        }

        public Builder setOutputMimeType(MimeType mimeType) {
            this.outputMimeType = mimeType;
            return this;
        }
    }

    static {
        System.loadLibrary("sum_jni.media.samsung");
        nativeFlattenPluginInit();
    }

    public ImageTranscoder(Builder builder) {
        this.inputMimeType = builder.inputMimeType;
        this.outputMimeType = builder.outputMimeType;
        boolean access$200 = builder.convertToSRGB;
        this.convertToSRGB = access$200;
        JSONObject jSONObject = new JSONObject();
        List<Pair<ImgpType, ImgpPlugin.Type>> list = this.opList;
        ImgpType imgpType = ImgpType.DECODE;
        ImgpPlugin.Type type = ImgpPlugin.Type.NATIVE_UNIIMGP;
        list.add(new Pair(imgpType, type));
        if (access$200) {
            this.opList.add(new Pair(ImgpType.CVT_GAMUT, type));
        }
        this.opList.add(new Pair(ImgpType.ENCODE, type));
        JSONArray jSONArray = new JSONArray();
        for (Pair next : this.opList) {
            jSONArray.put(((ImgpType) next.first).stringfy());
            jSONArray.put(String.valueOf(((ImgpPlugin.Type) next.second).ordinal()));
        }
        try {
            jSONObject.put("codec-keep-meta", true);
        } catch (Exception e) {
            String str = this.TAG;
            SLog.w(str, "failed to set option on e=" + e);
        }
        nativeCreateUniImgp(jSONArray.toString(), jSONObject.toString());
    }

    private JSONObject bufferFormatToJson(MediaBuffer mediaBuffer, Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Message.KEY_MEDIA_TYPE, mediaBuffer.getFormat().getMediaType().stringfy());
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
            if (mediaBuffer.getDataClass() == FileDescriptor.class) {
                map.put(Message.KEY_FILE_DESCRIPTOR, mediaBuffer.getData());
            }
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            String str = this.TAG;
            SLog.w(str, "fail to make bufferJson on =" + e);
            return jSONObject;
        }
    }

    private native void nativeClose();

    private native void nativeCreateUniImgp(String str, String str2);

    private static native void nativeFlattenPluginInit();

    private native int nativeRun(String str, Object obj, String str2, Object obj2, Map<String, Object> map, Map<String, Object> map2);

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00fc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.samsung.android.sum.core.buffer.MediaBuffer transcodeByUniImgp(java.lang.Object r13, java.lang.Object r14) {
        /*
            r12 = this;
            java.lang.String r0 = r12.TAG
            java.lang.String r1 = "transcodeByUniImgp"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r1)
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r0 = com.samsung.android.sum.core.buffer.MediaBuffer.newCompressedImageAlloc()
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r1 = com.samsung.android.sum.core.buffer.MediaBuffer.newCompressedImageAlloc()
            com.samsung.android.sum.core.types.MimeType r2 = r12.outputMimeType
            com.samsung.android.sum.core.types.CodecType r2 = com.samsung.android.sum.core.types.CodecType.fromMimeType(r2)
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r1 = r1.setCodecType(r2)
            boolean r2 = r12.convertToSRGB
            if (r2 == 0) goto L_0x0020
            com.samsung.android.sum.core.types.ColorFormat r2 = com.samsung.android.sum.core.types.ColorFormat.RGBA
            goto L_0x0022
        L_0x0020:
            com.samsung.android.sum.core.types.ColorFormat r2 = com.samsung.android.sum.core.types.ColorFormat.NV12
        L_0x0022:
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r1 = r1.setColorFormat(r2)
            boolean r2 = r12.convertToSRGB
            if (r2 == 0) goto L_0x002d
            com.samsung.android.sum.core.types.ColorSpace r2 = com.samsung.android.sum.core.types.ColorSpace.BT709_FR
            goto L_0x002f
        L_0x002d:
            com.samsung.android.sum.core.types.ColorSpace r2 = com.samsung.android.sum.core.types.ColorSpace.NONE
        L_0x002f:
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r1 = r1.setColorSpace(r2)
            boolean r2 = r13 instanceof java.io.FileDescriptor
            java.lang.String r3 = "Not supported argument type for input"
            r4 = 0
            if (r2 == 0) goto L_0x0040
            com.samsung.android.sum.core.buffer.MediaBuffer r13 = r0.wrap(r13)
        L_0x003e:
            r2 = r4
            goto L_0x0073
        L_0x0040:
            boolean r2 = r13 instanceof java.lang.String
            if (r2 == 0) goto L_0x004f
            java.lang.String r2 = "input-file"
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r13 = r0.setExtra(r2, r13)
            com.samsung.android.sum.core.buffer.MediaBuffer r13 = r13.allocate()
            goto L_0x003e
        L_0x004f:
            boolean r2 = r13 instanceof java.io.InputStream
            if (r2 == 0) goto L_0x011d
            r2 = r13
            java.io.InputStream r2 = (java.io.InputStream) r2     // Catch:{ IOException -> 0x0107 }
            int r2 = r2.available()     // Catch:{ IOException -> 0x0107 }
            byte[] r5 = new byte[r2]     // Catch:{ IOException -> 0x0107 }
            java.io.InputStream r13 = (java.io.InputStream) r13     // Catch:{ IOException -> 0x0107 }
            r13.read(r5)     // Catch:{ IOException -> 0x0107 }
            java.nio.ByteBuffer r13 = java.nio.ByteBuffer.allocateDirect(r2)     // Catch:{ IOException -> 0x0107 }
            java.nio.ByteBuffer r13 = r13.put(r5)     // Catch:{ IOException -> 0x0107 }
            com.samsung.android.sum.core.types.DataType r5 = com.samsung.android.sum.core.types.DataType.U8C1     // Catch:{ IOException -> 0x0107 }
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r0 = r0.setDataType(r5)     // Catch:{ IOException -> 0x0107 }
            com.samsung.android.sum.core.buffer.MediaBuffer r13 = r0.wrap(r13)     // Catch:{ IOException -> 0x0107 }
        L_0x0073:
            boolean r0 = r14 instanceof java.io.FileDescriptor
            if (r0 == 0) goto L_0x007c
            com.samsung.android.sum.core.buffer.MediaBuffer r14 = r1.wrap(r14)
            goto L_0x00ba
        L_0x007c:
            boolean r0 = r14 instanceof java.lang.String
            if (r0 == 0) goto L_0x008b
            java.lang.String r0 = "output-file"
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r14 = r1.setExtra(r0, r14)
            com.samsung.android.sum.core.buffer.MediaBuffer r14 = r14.allocate()
            goto L_0x00ba
        L_0x008b:
            boolean r14 = r14 instanceof java.io.OutputStream
            if (r14 == 0) goto L_0x0101
            com.samsung.android.sum.core.types.MimeType r14 = r12.inputMimeType
            com.samsung.android.sum.core.types.MimeType r0 = com.samsung.android.sum.core.types.MimeType.IMAGE_HEIC
            if (r14 != r0) goto L_0x00ac
            com.samsung.android.sum.core.types.MimeType r14 = r12.outputMimeType
            com.samsung.android.sum.core.types.MimeType r0 = com.samsung.android.sum.core.types.MimeType.IMAGE_JPEG
            if (r14 != r0) goto L_0x00ac
            com.samsung.android.sum.core.types.DataType r14 = com.samsung.android.sum.core.types.DataType.U8C1
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r14 = r1.setDataType(r14)
            int r2 = r2 * 3
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocateDirect(r2)
            com.samsung.android.sum.core.buffer.MediaBuffer r14 = r14.wrap(r0)
            goto L_0x00ba
        L_0x00ac:
            com.samsung.android.sum.core.types.DataType r14 = com.samsung.android.sum.core.types.DataType.U8C1
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r14 = r1.setDataType(r14)
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocateDirect(r2)
            com.samsung.android.sum.core.buffer.MediaBuffer r14 = r14.wrap(r0)
        L_0x00ba:
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            org.json.JSONObject r0 = r12.bufferFormatToJson(r13, r10)
            org.json.JSONObject r1 = r12.bufferFormatToJson(r14, r11)
            java.lang.String r6 = r0.toString()
            java.lang.String r8 = r1.toString()
            boolean r0 = r13.isNotEmpty()
            r1 = 0
            java.lang.Class<java.nio.ByteBuffer> r2 = java.nio.ByteBuffer.class
            if (r0 == 0) goto L_0x00e3
            java.lang.Object r13 = r13.getTypedData(r2)
            r7 = r13
            goto L_0x00e4
        L_0x00e3:
            r7 = r1
        L_0x00e4:
            boolean r13 = r14.isNotEmpty()
            if (r13 == 0) goto L_0x00ee
            java.lang.Object r1 = r14.getTypedData(r2)
        L_0x00ee:
            r5 = r12
            r9 = r1
            int r12 = r5.nativeRun(r6, r7, r8, r9, r10, r11)
            com.samsung.android.sum.core.types.Status r12 = com.samsung.android.sum.core.types.Status.from(r12)
            com.samsung.android.sum.core.types.Status r13 = com.samsung.android.sum.core.types.Status.OK
            if (r12 != r13) goto L_0x00fd
            r4 = 1
        L_0x00fd:
            com.samsung.android.sum.core.Def.require(r4)
            return r14
        L_0x0101:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>(r3)
            throw r12
        L_0x0107:
            r0 = move-exception
            r12 = r0
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r0 = "fail to read data from InputStream on "
            r14.<init>(r0)
            r14.append(r12)
            java.lang.String r12 = r14.toString()
            r13.<init>(r12)
            throw r13
        L_0x011d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>(r3)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.solution.ImageTranscoder.transcodeByUniImgp(java.lang.Object, java.lang.Object):com.samsung.android.sum.core.buffer.MediaBuffer");
    }

    public void close() {
        release();
    }

    public void finalize() {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public void release() {
        if (this.isReleased.compareAndSet(false, true)) {
            nativeClose();
            this.nativeContext = 0;
        }
    }

    public FileDescriptor transcode(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2) {
        transcodeByUniImgp(fileDescriptor, fileDescriptor2);
        return fileDescriptor2;
    }

    public String transcode(String str, String str2) {
        transcodeByUniImgp(str, str2);
        return str2;
    }

    public File transcode(File file, File file2) {
        transcodeByUniImgp(file.getAbsolutePath(), file2.getAbsolutePath());
        return file2;
    }

    public OutputStream transcode(InputStream inputStream, OutputStream outputStream) {
        ByteBuffer byteBuffer = (ByteBuffer) transcodeByUniImgp(inputStream, outputStream).getTypedData(ByteBuffer.class);
        String str = this.TAG;
        SLog.i(str, "output size=" + byteBuffer.remaining());
        try {
            outputStream.write(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.remaining());
            return outputStream;
        } catch (IOException e) {
            String str2 = this.TAG;
            SLog.w(str2, "failed to write encoded output on " + e);
            return outputStream;
        }
    }
}
