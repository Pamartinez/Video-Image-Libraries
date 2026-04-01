package com.samsung.o3dp.lib3dphotography;

import A.a;
import android.content.Context;
import android.graphics.Bitmap;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.o3dp.lib3dphotography.MeshUtils;
import com.samsung.o3dp.lib3dphotography.utils.ImageUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;
import com.samsung.o3dp.nativelib.BuildConfig;
import com.samsung.scsp.media.file.FileApiContract;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GLTFMeshUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DEFAULT_ENABLE_DRACO_COMPRESSION = true;
    private static final String DRACO_EXTENSION_STRING = "KHR_draco_mesh_compression";
    private static final Map<Bitmap.CompressFormat, String> IMAGE_MIME_TYPES = Map.of(Bitmap.CompressFormat.PNG, "image/png", Bitmap.CompressFormat.JPEG, "image/jpeg");
    private static final String TAG = "O3DP_MESHUTILS";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AccessorInfo {
        int bufferView;
        int byteOffset;
        int componentType;
        int count;
        double[] max;
        double[] min;
        String type;

        public /* synthetic */ AccessorInfo(int i2) {
            this();
        }

        private AccessorInfo() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ByteBufferInfo {
        public final int alignedLength;
        public final ByteBuffer buffer;
        public final int length;
        public final int stride;
        public final int target;
        public final String type;

        public ByteBufferInfo(String str, ByteBuffer byteBuffer) {
            this(str, byteBuffer, 0, 0);
        }

        public ByteBufferInfo(String str, ByteBuffer byteBuffer, int i2) {
            this(str, byteBuffer, i2, 0);
        }

        public ByteBufferInfo(String str, ByteBuffer byteBuffer, int i2, int i7) {
            this.type = str;
            this.buffer = byteBuffer;
            this.target = i2;
            this.stride = i7;
            int length2 = byteBuffer.array().length;
            this.length = length2;
            this.alignedLength = GLTFMeshUtils.alignedSize(length2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DataChunk {
        public ByteBuffer data;
        public String type;

        public /* synthetic */ DataChunk(int i2) {
            this();
        }

        private DataChunk() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MeshDetails {
        int bitmapIndex;
        int colorAccessor;
        int dracoBufferView;
        boolean dracoCompressed;
        int indicesAccessor;
        int positionAccessor;
        int primitive;
        int texCoordAccessor;

        public /* synthetic */ MeshDetails(int i2) {
            this();
        }

        private MeshDetails() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MeshVersionInfo {
        String generator;
        int version;
    }

    /* access modifiers changed from: private */
    public static int alignedSize(int i2) {
        int i7 = i2 % 4;
        if (i7 != 0) {
            return (4 - i7) + i2;
        }
        return i2;
    }

    public static byte[] buildMeshData(Context context, MeshUtils.MeshInfo meshInfo, ImageUtil.FormatInfo formatInfo, boolean z) {
        List<ByteBufferInfo> list = setupByteBuffers(context, meshInfo, formatInfo, z);
        if (list.isEmpty()) {
            LogUtil.e(TAG, "Failed to setupByteBuffers()");
            return null;
        }
        JSONObject jSONObject = setupJSONDescription(list, meshInfo, formatInfo, z);
        if (jSONObject == null) {
            LogUtil.e(TAG, "Failed to create JSON for glTF file format");
            return null;
        }
        try {
            String jSONObject2 = jSONObject.toString();
            String str = jSONObject2 + "    ".substring(jSONObject2.length() % 4);
            int i2 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                i2 += list.get(i7).alignedLength;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int length = str.length();
            int i8 = i2 + 8 + length + 20;
            Charset charset = StandardCharsets.UTF_8;
            byteArrayOutputStream.write("glTF".getBytes(charset));
            ByteBuffer allocate = ByteBuffer.allocate(4);
            ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
            byteArrayOutputStream.write(allocate.order(byteOrder).putInt(2).array());
            byteArrayOutputStream.write(ByteBuffer.allocate(4).order(byteOrder).putInt(i8).array());
            LogUtil.i(TAG, "Total: " + i8);
            byteArrayOutputStream.write(ByteBuffer.allocate(4).order(byteOrder).putInt(length).array());
            byteArrayOutputStream.write("JSON".getBytes(charset));
            byteArrayOutputStream.write(str.getBytes(charset));
            LogUtil.i(TAG, "JSON chunkSize: " + length);
            byteArrayOutputStream.write(ByteBuffer.allocate(4).order(byteOrder).putInt(i2).array());
            byteArrayOutputStream.write("BIN\u0000".getBytes(charset));
            for (int i10 = 0; i10 < list.size(); i10++) {
                ByteBufferInfo byteBufferInfo = list.get(i10);
                byteArrayOutputStream.write(byteBufferInfo.buffer.array());
                int i11 = byteBufferInfo.alignedLength - byteBufferInfo.length;
                if (i11 > 0) {
                    byteArrayOutputStream.write(new byte[i11]);
                }
                LogUtil.i(TAG, "Buffer " + i10 + " (" + byteBufferInfo.type + "): size " + byteBufferInfo.buffer.array().length + ", padding " + i11);
            }
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LogUtil.e(TAG, "Failed to generate mesh data: " + e);
            return null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:30|31) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0071, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0072, code lost:
        r1 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(TAG, "Unable to parse accessor data at index " + r3);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x009d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.AccessorInfo> extractAccessors(org.json.JSONObject r11, java.util.List<com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.ByteBufferInfo> r12) {
        /*
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.lang.String r0 = "accessors"
            org.json.JSONArray r11 = r11.optJSONArray(r0)
            java.lang.String r0 = "O3DP_MESHUTILS"
            r1 = 0
            if (r11 != 0) goto L_0x0016
            java.lang.String r11 = "Unable to find accessors element in JSON data"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r0, r11)
            return r1
        L_0x0016:
            r2 = 0
            r3 = r2
        L_0x0018:
            int r4 = r11.length()
            if (r3 >= r4) goto L_0x00ba
            org.json.JSONObject r4 = r11.getJSONObject(r3)     // Catch:{ JSONException -> 0x009c, all -> 0x009a }
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$AccessorInfo r5 = new com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$AccessorInfo     // Catch:{ JSONException -> 0x009c, all -> 0x009a }
            r5.<init>(r2)     // Catch:{ JSONException -> 0x009c, all -> 0x009a }
            java.lang.String r6 = "bufferView"
            int r6 = r4.optInt(r6, r2)     // Catch:{ JSONException -> 0x009d }
            r5.bufferView = r6     // Catch:{ JSONException -> 0x009d }
            java.lang.String r6 = "byteOffset"
            int r6 = r4.optInt(r6, r2)     // Catch:{ JSONException -> 0x009d }
            r5.byteOffset = r6     // Catch:{ JSONException -> 0x009d }
            java.lang.String r6 = "componentType"
            int r6 = r4.getInt(r6)     // Catch:{ JSONException -> 0x009d }
            r5.componentType = r6     // Catch:{ JSONException -> 0x009d }
            java.lang.String r6 = "type"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ JSONException -> 0x009d }
            r5.type = r6     // Catch:{ JSONException -> 0x009d }
            java.lang.String r6 = "count"
            int r6 = r4.getInt(r6)     // Catch:{ JSONException -> 0x009d }
            r5.count = r6     // Catch:{ JSONException -> 0x009d }
            java.lang.String r6 = "min"
            org.json.JSONArray r6 = r4.optJSONArray(r6)     // Catch:{ JSONException -> 0x009d }
            if (r6 == 0) goto L_0x0074
            int r7 = r6.length()     // Catch:{ JSONException -> 0x009d }
            double[] r7 = new double[r7]     // Catch:{ JSONException -> 0x009d }
            r5.min = r7     // Catch:{ JSONException -> 0x009d }
            r7 = r2
        L_0x0060:
            int r8 = r6.length()     // Catch:{ JSONException -> 0x009d }
            if (r7 >= r8) goto L_0x0074
            double[] r8 = r5.min     // Catch:{ JSONException -> 0x009d }
            double r9 = r6.getDouble(r7)     // Catch:{ JSONException -> 0x009d }
            r8[r7] = r9     // Catch:{ JSONException -> 0x009d }
            int r7 = r7 + 1
            goto L_0x0060
        L_0x0071:
            r11 = move-exception
            r1 = r5
            goto L_0x00b6
        L_0x0074:
            java.lang.String r6 = "max"
            org.json.JSONArray r4 = r4.optJSONArray(r6)     // Catch:{ JSONException -> 0x009d }
            if (r4 == 0) goto L_0x0096
            int r6 = r4.length()     // Catch:{ JSONException -> 0x009d }
            double[] r6 = new double[r6]     // Catch:{ JSONException -> 0x009d }
            r5.max = r6     // Catch:{ JSONException -> 0x009d }
            r6 = r2
        L_0x0085:
            int r7 = r4.length()     // Catch:{ JSONException -> 0x009d }
            if (r6 >= r7) goto L_0x0096
            double[] r7 = r5.max     // Catch:{ JSONException -> 0x009d }
            double r8 = r4.getDouble(r6)     // Catch:{ JSONException -> 0x009d }
            r7[r6] = r8     // Catch:{ JSONException -> 0x009d }
            int r6 = r6 + 1
            goto L_0x0085
        L_0x0096:
            r12.add(r5)
            goto L_0x00b2
        L_0x009a:
            r11 = move-exception
            goto L_0x00b6
        L_0x009c:
            r5 = r1
        L_0x009d:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r4.<init>()     // Catch:{ all -> 0x0071 }
            java.lang.String r6 = "Unable to parse accessor data at index "
            r4.append(r6)     // Catch:{ all -> 0x0071 }
            r4.append(r3)     // Catch:{ all -> 0x0071 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0071 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r0, r4)     // Catch:{ all -> 0x0071 }
            goto L_0x0096
        L_0x00b2:
            int r3 = r3 + 1
            goto L_0x0018
        L_0x00b6:
            r12.add(r1)
            throw r11
        L_0x00ba:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.extractAccessors(org.json.JSONObject, java.util.List):java.util.List");
    }

    private static List<ByteBuffer> extractBinaryData(List<DataChunk> list) {
        ArrayList arrayList = new ArrayList();
        for (DataChunk next : list) {
            if (next.type.equals("BIN\u0000")) {
                arrayList.add(next.data);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004e, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(TAG, "Unable to parse image at index " + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ae, code lost:
        r1.add((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b1, code lost:
        throw r9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0095 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<android.graphics.Bitmap> extractBitmaps(org.json.JSONObject r9, java.util.List<com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.ByteBufferInfo> r10) {
        /*
            java.lang.String r0 = "O3DP_MESHUTILS"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = "images"
            org.json.JSONArray r9 = r9.optJSONArray(r2)
            if (r9 != 0) goto L_0x0011
            goto L_0x00b2
        L_0x0011:
            r2 = 0
            r3 = r2
        L_0x0013:
            int r4 = r9.length()
            if (r3 >= r4) goto L_0x00b2
            r4 = 0
            org.json.JSONObject r5 = r9.getJSONObject(r3)     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r6 = "bufferView"
            int r5 = r5.getInt(r6)     // Catch:{ JSONException -> 0x0095 }
            if (r5 < 0) goto L_0x0076
            int r6 = r10.size()     // Catch:{ JSONException -> 0x0095 }
            if (r5 >= r6) goto L_0x0076
            java.lang.Object r6 = r10.get(r5)     // Catch:{ JSONException -> 0x0095 }
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$ByteBufferInfo r6 = (com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.ByteBufferInfo) r6     // Catch:{ JSONException -> 0x0095 }
            if (r6 != 0) goto L_0x0050
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0095 }
            r6.<init>()     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r7 = "bufferView at index "
            r6.append(r7)     // Catch:{ JSONException -> 0x0095 }
            r6.append(r5)     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r5 = " is null"
            r6.append(r5)     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r5 = r6.toString()     // Catch:{ JSONException -> 0x0095 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r0, r5)     // Catch:{ JSONException -> 0x0095 }
            goto L_0x0072
        L_0x004e:
            r9 = move-exception
            goto L_0x00ae
        L_0x0050:
            java.nio.ByteBuffer r5 = r6.buffer     // Catch:{ JSONException -> 0x0095 }
            byte[] r5 = r5.array()     // Catch:{ JSONException -> 0x0095 }
            int r6 = r6.length     // Catch:{ JSONException -> 0x0095 }
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeByteArray(r5, r2, r6)     // Catch:{ JSONException -> 0x0095 }
            if (r4 != 0) goto L_0x0072
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0095 }
            r5.<init>()     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r6 = "unable to decode image at index "
            r5.append(r6)     // Catch:{ JSONException -> 0x0095 }
            r5.append(r3)     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r5 = r5.toString()     // Catch:{ JSONException -> 0x0095 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r0, r5)     // Catch:{ JSONException -> 0x0095 }
        L_0x0072:
            r1.add(r4)
            goto L_0x00aa
        L_0x0076:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ JSONException -> 0x0095 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0095 }
            r7.<init>()     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r8 = "bufferView index "
            r7.append(r8)     // Catch:{ JSONException -> 0x0095 }
            r7.append(r5)     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r5 = " is out of bounds for image "
            r7.append(r5)     // Catch:{ JSONException -> 0x0095 }
            r7.append(r3)     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r5 = r7.toString()     // Catch:{ JSONException -> 0x0095 }
            r6.<init>(r5)     // Catch:{ JSONException -> 0x0095 }
            throw r6     // Catch:{ JSONException -> 0x0095 }
        L_0x0095:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r5.<init>()     // Catch:{ all -> 0x004e }
            java.lang.String r6 = "Unable to parse image at index "
            r5.append(r6)     // Catch:{ all -> 0x004e }
            r5.append(r3)     // Catch:{ all -> 0x004e }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x004e }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r0, r5)     // Catch:{ all -> 0x004e }
            goto L_0x0072
        L_0x00aa:
            int r3 = r3 + 1
            goto L_0x0013
        L_0x00ae:
            r1.add(r4)
            throw r9
        L_0x00b2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.extractBitmaps(org.json.JSONObject, java.util.List):java.util.List");
    }

    private static List<ByteBufferInfo> extractBufferViews(JSONObject jSONObject, List<ByteBuffer> list) {
        JSONArray optJSONArray = jSONObject.optJSONArray("bufferViews");
        if (optJSONArray == null) {
            LogUtil.e(TAG, "Unable to find bufferViews element in glTF data");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
            if (optJSONObject == null) {
                LogUtil.e(TAG, "Invalid bufferView specification at index " + i2);
                arrayList.add((Object) null);
            } else {
                int optInt = optJSONObject.optInt("buffer", 0);
                if (optInt > list.size() - 1) {
                    LogUtil.e(TAG, a.d(i2, optInt, "bufferView ", " references byte buffer ", " which is not defined"));
                    arrayList.add((Object) null);
                } else {
                    int optInt2 = optJSONObject.optInt("byteOffset", 0);
                    int optInt3 = optJSONObject.optInt("byteLength", 0);
                    arrayList.add(new ByteBufferInfo("", ByteBuffer.wrap(Arrays.copyOfRange(list.get(optInt).array(), optInt2, optInt3 + optInt2)), optJSONObject.optInt("target", 0), optJSONObject.optInt("byteStride", 0)));
                }
            }
        }
        return arrayList;
    }

    private static List<DataChunk> extractDataChunks(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int i2 = 12;
        while (i2 < bArr.length) {
            int i7 = ByteBuffer.wrap(bArr, i2, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
            DataChunk dataChunk = new DataChunk(0);
            int i8 = i2 + 8;
            dataChunk.type = new String(Arrays.copyOfRange(bArr, i2 + 4, i8), StandardCharsets.UTF_8);
            dataChunk.data = ByteBuffer.wrap(Arrays.copyOfRange(bArr, i8, i8 + i7));
            arrayList.add(dataChunk);
            i2 += i7 + 8;
        }
        return arrayList;
    }

    private static JSONObject extractJSONDescription(List<DataChunk> list) {
        String jsonDescription = getJsonDescription(list);
        if (jsonDescription == null) {
            LogUtil.e(TAG, "extractJSONDescription() : jsonDescription is null");
            return null;
        }
        try {
            return new JSONObject(jsonDescription);
        } catch (JSONException e) {
            LogUtil.e(TAG, "Unable to parse JSON: " + e);
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$MeshDetails} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v14, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v53, resolved type: org.json.JSONObject} */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02ab, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02ac, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02ae, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0110, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0111, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0114, code lost:
        r2 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0116, code lost:
        r5 = null;
        r7 = null;
        r16 = 0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x02ab A[ExcHandler: all (th java.lang.Throwable), Splitter:B:80:0x024b] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0110 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:33:0x00c7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.MeshDetails> extractMeshInfo(org.json.JSONObject r17, java.util.List<com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.AccessorInfo> r18, java.util.List<java.lang.Integer> r19) {
        /*
            r0 = r18
            java.lang.String r1 = "meshes"
            r2 = r17
            org.json.JSONArray r1 = r2.optJSONArray(r1)
            r2 = 0
            java.lang.String r3 = "O3DP_MESHUTILS"
            if (r1 != 0) goto L_0x0015
            java.lang.String r0 = "Unable to find meshes element in JSON data"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r0)
            return r2
        L_0x0015:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r5 = 0
            r6 = r5
        L_0x001c:
            int r7 = r1.length()
            if (r6 >= r7) goto L_0x02e4
            org.json.JSONObject r7 = r1.getJSONObject(r6)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            java.lang.String r8 = "primitives"
            org.json.JSONArray r7 = r7.getJSONArray(r8)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            int r8 = r7.length()     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            if (r8 != 0) goto L_0x005b
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            r7.<init>()     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            java.lang.String r8 = "no primitives defined for mesh "
            r7.append(r8)     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            r7.append(r6)     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            java.lang.String r7 = r7.toString()     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r7)     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            r4.add(r2)
            r16 = r5
            r5 = r2
        L_0x004c:
            r2 = r19
            goto L_0x02d7
        L_0x0050:
            r0 = move-exception
            goto L_0x02e0
        L_0x0053:
            r7 = r2
            r16 = r5
            r2 = r19
            r5 = r7
            goto L_0x02c0
        L_0x005b:
            int r8 = r7.length()     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            r9 = 1
            if (r8 <= r9) goto L_0x007b
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            r8.<init>()     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            java.lang.String r10 = "found multiple primitives for mesh "
            r8.append(r10)     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            r8.append(r6)     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            java.lang.String r10 = ": ignoring all but the first"
            r8.append(r10)     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            java.lang.String r8 = r8.toString()     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r8)     // Catch:{ JSONException -> 0x0053, all -> 0x0050 }
        L_0x007b:
            org.json.JSONObject r7 = r7.getJSONObject(r5)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            java.lang.String r8 = "attributes"
            org.json.JSONObject r8 = r7.getJSONObject(r8)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            java.lang.String r10 = "mode"
            int r10 = r7.getInt(r10)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            java.lang.String r11 = "POSITION"
            int r11 = r8.getInt(r11)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            java.lang.String r12 = "COLOR_0"
            int r12 = r8.getInt(r12)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            java.lang.String r13 = "TEXCOORD_0"
            int r8 = r8.getInt(r13)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            java.lang.String r13 = "indices"
            int r13 = r7.getInt(r13)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            java.lang.String r14 = "material"
            int r14 = r7.getInt(r14)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            java.lang.String r15 = "extensions"
            org.json.JSONObject r7 = r7.optJSONObject(r15)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
            if (r7 != 0) goto L_0x00b3
            r7 = r2
            goto L_0x00b9
        L_0x00b3:
            java.lang.String r15 = "KHR_draco_mesh_compression"
            org.json.JSONObject r7 = r7.optJSONObject(r15)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
        L_0x00b9:
            if (r7 != 0) goto L_0x00bd
            r15 = r5
            goto L_0x00c3
        L_0x00bd:
            java.lang.String r15 = "bufferView"
            int r15 = r7.getInt(r15)     // Catch:{ JSONException -> 0x02ba, all -> 0x02b7 }
        L_0x00c3:
            java.lang.String r9 = "mesh "
            if (r7 != 0) goto L_0x024b
            int r5 = r0.size()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r13 >= r5) goto L_0x0231
            java.lang.Object r5 = r0.get(r13)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r5 != 0) goto L_0x00d5
            goto L_0x0231
        L_0x00d5:
            java.lang.Object r5 = r0.get(r13)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$AccessorInfo r5 = (com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.AccessorInfo) r5     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = r5.type     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = "SCALAR"
            boolean r2 = r5.equals(r2)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r2 != 0) goto L_0x011c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.<init>()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r9)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r6)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = " references invalid indices accessor of type "
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.Object r5 = r0.get(r13)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$AccessorInfo r5 = (com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.AccessorInfo) r5     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = r5.type     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r2)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2 = 0
        L_0x0108:
            r4.add(r2)
            r5 = r2
            r16 = 0
            goto L_0x004c
        L_0x0110:
            r0 = move-exception
            r2 = 0
            goto L_0x02e0
        L_0x0114:
            r2 = r19
        L_0x0116:
            r5 = 0
            r7 = 0
            r16 = 0
            goto L_0x02c0
        L_0x011c:
            int r2 = r0.size()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r11 >= r2) goto L_0x0217
            java.lang.Object r2 = r0.get(r11)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r2 != 0) goto L_0x012a
            goto L_0x0217
        L_0x012a:
            java.lang.Object r2 = r0.get(r11)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$AccessorInfo r2 = (com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.AccessorInfo) r2     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.type     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = "VEC3"
            boolean r2 = r2.equals(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r2 != 0) goto L_0x015e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.<init>()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r9)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r6)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = " references invalid position accessor of type "
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.Object r5 = r0.get(r11)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$AccessorInfo r5 = (com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.AccessorInfo) r5     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = r5.type     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r2)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2 = 0
            goto L_0x0108
        L_0x015e:
            int r2 = r0.size()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r12 >= r2) goto L_0x01fd
            java.lang.Object r2 = r0.get(r12)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r2 != 0) goto L_0x016c
            goto L_0x01fd
        L_0x016c:
            java.lang.Object r2 = r0.get(r12)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$AccessorInfo r2 = (com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.AccessorInfo) r2     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.type     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = "VEC4"
            boolean r2 = r2.equals(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r2 != 0) goto L_0x01a1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.<init>()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r9)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r6)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = " references invalid color accessor of type "
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.Object r5 = r0.get(r12)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$AccessorInfo r5 = (com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.AccessorInfo) r5     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = r5.type     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r2)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2 = 0
            goto L_0x0108
        L_0x01a1:
            int r2 = r0.size()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r8 >= r2) goto L_0x01e3
            java.lang.Object r2 = r0.get(r8)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r2 != 0) goto L_0x01ae
            goto L_0x01e3
        L_0x01ae:
            java.lang.Object r2 = r0.get(r8)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$AccessorInfo r2 = (com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.AccessorInfo) r2     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.type     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = "VEC2"
            boolean r2 = r2.equals(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            if (r2 != 0) goto L_0x024b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.<init>()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r9)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r6)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = " references invalid texCoord accessor of type "
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.Object r5 = r0.get(r8)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$AccessorInfo r5 = (com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.AccessorInfo) r5     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = r5.type     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r2)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2 = 0
            goto L_0x0108
        L_0x01e3:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.<init>()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r9)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r6)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = " references undefined texCoord accessor"
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r2)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2 = 0
            goto L_0x0108
        L_0x01fd:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.<init>()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r9)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r6)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = " references undefined color accessor"
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r2)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2 = 0
            goto L_0x0108
        L_0x0217:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.<init>()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r9)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r6)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = " references undefined position accessor"
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r2)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2 = 0
            goto L_0x0108
        L_0x0231:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.<init>()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r9)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2.append(r6)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r5 = " references undefined indices accessor"
            r2.append(r5)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r2)     // Catch:{ JSONException -> 0x0114, all -> 0x0110 }
            r2 = 0
            goto L_0x0108
        L_0x024b:
            int r2 = r19.size()     // Catch:{ JSONException -> 0x02b1, all -> 0x02ab }
            if (r14 > r2) goto L_0x028c
            r2 = r19
            java.lang.Object r5 = r2.get(r14)     // Catch:{ JSONException -> 0x0116, all -> 0x0110 }
            if (r5 != 0) goto L_0x025c
        L_0x0259:
            r16 = 0
            goto L_0x028f
        L_0x025c:
            com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$MeshDetails r5 = new com.samsung.o3dp.lib3dphotography.GLTFMeshUtils$MeshDetails     // Catch:{ JSONException -> 0x0116, all -> 0x0110 }
            r9 = 0
            r5.<init>(r9)     // Catch:{ JSONException -> 0x0287, all -> 0x0110 }
            r5.primitive = r10     // Catch:{ JSONException -> 0x0282, all -> 0x027e }
            r5.positionAccessor = r11     // Catch:{ JSONException -> 0x0282, all -> 0x027e }
            r5.colorAccessor = r12     // Catch:{ JSONException -> 0x0282, all -> 0x027e }
            r5.texCoordAccessor = r8     // Catch:{ JSONException -> 0x0282, all -> 0x027e }
            r5.indicesAccessor = r13     // Catch:{ JSONException -> 0x0282, all -> 0x027e }
            r5.bitmapIndex = r14     // Catch:{ JSONException -> 0x0282, all -> 0x027e }
            if (r7 == 0) goto L_0x0272
            r7 = 1
            goto L_0x0273
        L_0x0272:
            r7 = r9
        L_0x0273:
            r5.dracoCompressed = r7     // Catch:{ JSONException -> 0x0282, all -> 0x027e }
            r5.dracoBufferView = r15     // Catch:{ JSONException -> 0x0282, all -> 0x027e }
            r4.add(r5)
            r16 = r9
            r5 = 0
            goto L_0x02d7
        L_0x027e:
            r0 = move-exception
        L_0x027f:
            r2 = r5
            goto L_0x02e0
        L_0x0282:
            r7 = r5
            r16 = r9
            r5 = 0
            goto L_0x02c0
        L_0x0287:
            r16 = r9
            r5 = 0
            r7 = 0
            goto L_0x02c0
        L_0x028c:
            r2 = r19
            goto L_0x0259
        L_0x028f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x02ae, all -> 0x02ab }
            r5.<init>()     // Catch:{ JSONException -> 0x02ae, all -> 0x02ab }
            r5.append(r9)     // Catch:{ JSONException -> 0x02ae, all -> 0x02ab }
            r5.append(r6)     // Catch:{ JSONException -> 0x02ae, all -> 0x02ab }
            java.lang.String r7 = " references undefined material"
            r5.append(r7)     // Catch:{ JSONException -> 0x02ae, all -> 0x02ab }
            java.lang.String r5 = r5.toString()     // Catch:{ JSONException -> 0x02ae, all -> 0x02ab }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r5)     // Catch:{ JSONException -> 0x02ae, all -> 0x02ab }
            r5 = 0
            r4.add(r5)
            goto L_0x02d7
        L_0x02ab:
            r0 = move-exception
            r5 = 0
            goto L_0x027f
        L_0x02ae:
            r5 = 0
        L_0x02af:
            r7 = r5
            goto L_0x02c0
        L_0x02b1:
            r2 = r19
            r5 = 0
            r16 = 0
            goto L_0x02af
        L_0x02b7:
            r0 = move-exception
            r5 = r2
            goto L_0x02e0
        L_0x02ba:
            r16 = r5
            r5 = r2
            r2 = r19
            goto L_0x02af
        L_0x02c0:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x02de }
            r8.<init>()     // Catch:{ all -> 0x02de }
            java.lang.String r9 = "Unable to read mesh object at index "
            r8.append(r9)     // Catch:{ all -> 0x02de }
            r8.append(r6)     // Catch:{ all -> 0x02de }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x02de }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r8)     // Catch:{ all -> 0x02de }
            r4.add(r7)
        L_0x02d7:
            int r6 = r6 + 1
            r2 = r5
            r5 = r16
            goto L_0x001c
        L_0x02de:
            r0 = move-exception
            r2 = r7
        L_0x02e0:
            r4.add(r2)
            throw r0
        L_0x02e4:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.extractMeshInfo(org.json.JSONObject, java.util.List, java.util.List):java.util.List");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|39) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(TAG, "Unable to process node at " + r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        r4.add((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        throw r9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<java.lang.Integer> extractSceneMeshes(org.json.JSONObject r9) {
        /*
            java.lang.String r0 = "nodes"
            org.json.JSONArray r1 = r9.optJSONArray(r0)
            r2 = 0
            java.lang.String r3 = "O3DP_MESHUTILS"
            if (r1 != 0) goto L_0x0011
            java.lang.String r9 = "Unable to find node list"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r9)
            return r2
        L_0x0011:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r5 = 0
            r6 = r5
        L_0x0018:
            int r7 = r1.length()
            if (r6 >= r7) goto L_0x0050
            org.json.JSONObject r7 = r1.getJSONObject(r6)     // Catch:{ JSONException -> 0x0032 }
            java.lang.String r8 = "mesh"
            int r7 = r7.getInt(r8)     // Catch:{ JSONException -> 0x0032 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ JSONException -> 0x0032 }
            r4.add(r7)
            goto L_0x0049
        L_0x0030:
            r9 = move-exception
            goto L_0x004c
        L_0x0032:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0030 }
            r7.<init>()     // Catch:{ all -> 0x0030 }
            java.lang.String r8 = "Unable to process node at "
            r7.append(r8)     // Catch:{ all -> 0x0030 }
            r7.append(r6)     // Catch:{ all -> 0x0030 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0030 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r7)     // Catch:{ all -> 0x0030 }
            r4.add(r2)
        L_0x0049:
            int r6 = r6 + 1
            goto L_0x0018
        L_0x004c:
            r4.add(r2)
            throw r9
        L_0x0050:
            java.lang.String r1 = "scenes"
            org.json.JSONArray r1 = r9.optJSONArray(r1)
            if (r1 != 0) goto L_0x005e
            java.lang.String r9 = "Unable to find scene list"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r9)
            return r2
        L_0x005e:
            java.lang.String r6 = "scene"
            int r9 = r9.optInt(r6, r5)
            int r6 = r1.length()
            if (r9 < r6) goto L_0x0081
            java.lang.String r0 = "scene index out of range: index "
            java.lang.String r4 = ", available "
            java.lang.StringBuilder r9 = c0.C0086a.o(r9, r0, r4)
            int r0 = r1.length()
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r9)
            return r2
        L_0x0081:
            org.json.JSONObject r1 = r1.optJSONObject(r9)
            if (r1 != 0) goto L_0x0099
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unable to parse scene "
            r0.<init>(r1)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r9)
            return r2
        L_0x0099:
            org.json.JSONArray r9 = r1.optJSONArray(r0)
            if (r9 != 0) goto L_0x00a5
            java.lang.String r9 = "Unable to parse scene nodes"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r3, r9)
            return r2
        L_0x00a5:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x00aa:
            int r1 = r9.length()
            if (r5 >= r1) goto L_0x00c0
            int r1 = r9.optInt(r5)
            java.lang.Object r1 = r4.get(r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r0.add(r1)
            int r5 = r5 + 1
            goto L_0x00aa
        L_0x00c0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.extractSceneMeshes(org.json.JSONObject):java.util.List");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|36) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(TAG, "unable to parse texture information at index " + r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        r3.add((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0089, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(TAG, "unable to parse material information at index " + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a5, code lost:
        r0.add((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a8, code lost:
        throw r8;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x008b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<java.lang.Integer> extractTextures(org.json.JSONObject r8) {
        /*
            java.lang.String r0 = "textures"
            org.json.JSONArray r0 = r8.optJSONArray(r0)
            r1 = 0
            java.lang.String r2 = "O3DP_MESHUTILS"
            if (r0 != 0) goto L_0x0011
            java.lang.String r8 = "unable to read textures"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r8)
            return r1
        L_0x0011:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = 0
            r5 = r4
        L_0x0018:
            int r6 = r0.length()
            if (r5 >= r6) goto L_0x0050
            org.json.JSONObject r6 = r0.getJSONObject(r5)     // Catch:{ JSONException -> 0x0032 }
            java.lang.String r7 = "source"
            int r6 = r6.getInt(r7)     // Catch:{ JSONException -> 0x0032 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ JSONException -> 0x0032 }
            r3.add(r6)
            goto L_0x0049
        L_0x0030:
            r8 = move-exception
            goto L_0x004c
        L_0x0032:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0030 }
            r6.<init>()     // Catch:{ all -> 0x0030 }
            java.lang.String r7 = "unable to parse texture information at index "
            r6.append(r7)     // Catch:{ all -> 0x0030 }
            r6.append(r5)     // Catch:{ all -> 0x0030 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0030 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r6)     // Catch:{ all -> 0x0030 }
            r3.add(r1)
        L_0x0049:
            int r5 = r5 + 1
            goto L_0x0018
        L_0x004c:
            r3.add(r1)
            throw r8
        L_0x0050:
            java.lang.String r0 = "materials"
            org.json.JSONArray r8 = r8.optJSONArray(r0)
            if (r8 != 0) goto L_0x005e
            java.lang.String r8 = "unable to read materials"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r8)
            return r1
        L_0x005e:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x0063:
            int r5 = r8.length()
            if (r4 >= r5) goto L_0x00a9
            org.json.JSONObject r5 = r8.getJSONObject(r4)     // Catch:{ JSONException -> 0x008b }
            java.lang.String r6 = "pbrMetallicRoughness"
            org.json.JSONObject r5 = r5.getJSONObject(r6)     // Catch:{ JSONException -> 0x008b }
            java.lang.String r6 = "baseColorTexture"
            org.json.JSONObject r5 = r5.getJSONObject(r6)     // Catch:{ JSONException -> 0x008b }
            java.lang.String r6 = "index"
            int r5 = r5.getInt(r6)     // Catch:{ JSONException -> 0x008b }
            java.lang.Object r5 = r3.get(r5)     // Catch:{ JSONException -> 0x008b }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ JSONException -> 0x008b }
            r0.add(r5)
            goto L_0x00a2
        L_0x0089:
            r8 = move-exception
            goto L_0x00a5
        L_0x008b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0089 }
            r5.<init>()     // Catch:{ all -> 0x0089 }
            java.lang.String r6 = "unable to parse material information at index "
            r5.append(r6)     // Catch:{ all -> 0x0089 }
            r5.append(r4)     // Catch:{ all -> 0x0089 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0089 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r5)     // Catch:{ all -> 0x0089 }
            r0.add(r1)
        L_0x00a2:
            int r4 = r4 + 1
            goto L_0x0063
        L_0x00a5:
            r0.add(r1)
            throw r8
        L_0x00a9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.GLTFMeshUtils.extractTextures(org.json.JSONObject):java.util.List");
    }

    private static MeshVersionInfo extractVersionInfo(JSONObject jSONObject) {
        JSONObject optJSONObject;
        MeshVersionInfo meshVersionInfo = new MeshVersionInfo();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("asset");
            String optString = jSONObject2.optString("generator", "");
            meshVersionInfo.generator = optString;
            if (optString.equals("LiveEffect") && (optJSONObject = jSONObject2.optJSONObject("extras")) != null) {
                meshVersionInfo.version = StringUtil.versionFromString(optJSONObject.optString("liveEffectVersion", "0.0.0.0"));
            }
            return meshVersionInfo;
        } catch (JSONException unused) {
            LogUtil.e(TAG, "Unable to parse glTF asset information");
            return meshVersionInfo;
        }
    }

    private static byte[] getBitmapData(Bitmap bitmap, ImageUtil.FormatInfo formatInfo) {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bitmap != null) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(formatInfo.compressFormat, formatInfo.quality, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (IOException e) {
                LogUtil.e(TAG, "Failed to get texture data: " + e);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return new byte[0];
        throw th;
    }

    private static String getJsonDescription(List<DataChunk> list) {
        String str = null;
        for (DataChunk next : list) {
            if (next.type.equals("JSON")) {
                str = new String(next.data.array(), StandardCharsets.UTF_8);
            }
        }
        return str;
    }

    private static int indicesCount(int i2, MeshUtils.MeshLayerInfo meshLayerInfo) {
        int i7;
        int i8 = meshLayerInfo.primitive;
        if (i2 == i8) {
            return meshLayerInfo.indices.length;
        }
        if (i2 == 4 && i8 == 5) {
            int i10 = 0;
            int i11 = 0;
            boolean z = false;
            while (true) {
                int[] iArr = meshLayerInfo.indices;
                int i12 = 2;
                if (i10 >= iArr.length - 2) {
                    return i11 * 3;
                }
                int i13 = iArr[i10];
                if (z) {
                    i7 = 2;
                } else {
                    i7 = 1;
                }
                int i14 = iArr[i7 + i10];
                if (z) {
                    i12 = 1;
                }
                int i15 = iArr[i12 + i10];
                if (!(i13 == i14 || i14 == i15 || i13 == i15)) {
                    i11++;
                }
                z = !z;
                i10++;
            }
        } else {
            LogUtil.e(TAG, "Unsupported conversion");
            StringBuilder o2 = C0086a.o(i2, "Unsupported indices conversion ", " -> ");
            o2.append(meshLayerInfo.primitive);
            throw new RuntimeException(o2.toString());
        }
    }

    public static MeshUtils.MeshInfo loadMesh(Context context, byte[] bArr) {
        List<ByteBufferInfo> extractBufferViews;
        List<Integer> extractTextures;
        List<MeshDetails> extractMeshInfo;
        if (!verifyHeader(bArr)) {
            return null;
        }
        List<DataChunk> extractDataChunks = extractDataChunks(bArr);
        if (!verifyDataChunks(extractDataChunks)) {
            return null;
        }
        JSONObject extractJSONDescription = extractJSONDescription(extractDataChunks);
        if (extractJSONDescription == null) {
            LogUtil.e(TAG, "Unable to extract JSON description from glTF data");
            return null;
        }
        MeshVersionInfo extractVersionInfo = extractVersionInfo(extractJSONDescription);
        List<Integer> extractSceneMeshes = extractSceneMeshes(extractJSONDescription);
        if (extractSceneMeshes == null || (extractBufferViews = extractBufferViews(extractJSONDescription, extractBinaryData(extractDataChunks))) == null) {
            return null;
        }
        try {
            List<Bitmap> extractBitmaps = extractBitmaps(extractJSONDescription, extractBufferViews);
            List<AccessorInfo> extractAccessors = extractAccessors(extractJSONDescription, extractBufferViews);
            if (extractAccessors == null || (extractTextures = extractTextures(extractJSONDescription)) == null || (extractMeshInfo = extractMeshInfo(extractJSONDescription, extractAccessors, extractTextures)) == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < extractSceneMeshes.size(); i2++) {
                arrayList.add(extractMeshInfo.get(extractSceneMeshes.get(i2).intValue()));
            }
            return setupMeshData(context, extractVersionInfo, arrayList, extractBufferViews, extractAccessors, extractBitmaps);
        } catch (IllegalStateException e) {
            LogUtil.e(TAG, "Failed to extract bitmaps from buffer view", e);
            return null;
        }
    }

    public static boolean saveMesh(Context context, File file, MeshUtils.MeshInfo meshInfo, ImageUtil.FormatInfo formatInfo) {
        FileOutputStream fileOutputStream;
        byte[] buildMeshData = buildMeshData(context, meshInfo, formatInfo, true);
        if (buildMeshData == null) {
            LogUtil.e(TAG, "Unable to build mesh data for write");
            return false;
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(buildMeshData);
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            LogUtil.e(TAG, "Failed to write mesh data: " + e);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static List<ByteBufferInfo> setupByteBuffers(Context context, MeshUtils.MeshInfo meshInfo, ImageUtil.FormatInfo formatInfo, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            for (MeshUtils.MeshLayerInfo next : meshInfo.layers) {
                Context context2 = context;
                byte[] encode = DracoCompression.encode(context2, next.primitive, next.indices, next.vertices, next.colors, next.texCoords);
                if (encode == null) {
                    LogUtil.e(TAG, "DracoCompression.encode() returns null");
                    return new ArrayList();
                }
                ByteBuffer allocate = ByteBuffer.allocate(encode.length);
                allocate.put(encode);
                arrayList.add(new ByteBufferInfo("draco", allocate, 0, 0));
                context = context2;
            }
        } else {
            for (MeshUtils.MeshLayerInfo next2 : meshInfo.layers) {
                int length = next2.vertices.length / 3;
                ByteBuffer allocate2 = ByteBuffer.allocate(next2.indices.length * 4);
                ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                allocate2.order(byteOrder);
                allocate2.asIntBuffer().put(next2.indices);
                arrayList.add(new ByteBufferInfo("indices", allocate2, 34963));
                ByteBuffer allocate3 = ByteBuffer.allocate(length * 12);
                allocate3.order(byteOrder);
                allocate3.asFloatBuffer().put(next2.vertices);
                arrayList.add(new ByteBufferInfo("vertices", allocate3, 34962, 12));
                ByteBuffer allocate4 = ByteBuffer.allocate(length * 16);
                allocate4.order(byteOrder);
                allocate4.asFloatBuffer().put(next2.colors);
                arrayList.add(new ByteBufferInfo("colors", allocate4, 34962, 16));
                ByteBuffer allocate5 = ByteBuffer.allocate(length * 8);
                allocate5.order(byteOrder);
                allocate5.asFloatBuffer().put(next2.texCoords);
                arrayList.add(new ByteBufferInfo("texcoords", allocate5, 34962, 8));
            }
        }
        for (MeshUtils.MeshLayerInfo meshLayerInfo : meshInfo.layers) {
            byte[] bitmapData = getBitmapData(meshLayerInfo.bitmap, formatInfo);
            ByteBuffer allocate6 = ByteBuffer.allocate(bitmapData.length);
            allocate6.put(bitmapData);
            arrayList.add(new ByteBufferInfo("image", allocate6));
        }
        return arrayList;
    }

    private static JSONObject setupJSONDescription(List<ByteBufferInfo> list, MeshUtils.MeshInfo meshInfo, ImageUtil.FormatInfo formatInfo, boolean z) {
        String str;
        JSONObject jSONObject;
        String str2;
        ArrayList arrayList;
        String str3;
        JSONObject jSONObject2;
        String str4;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        List<ByteBufferInfo> list2 = list;
        MeshUtils.MeshInfo meshInfo2 = meshInfo;
        int i2 = 0;
        for (int i7 = 0; i7 < list2.size(); i7++) {
            i2 += list2.get(i7).alignedLength;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i8 = 0; i8 < meshInfo2.layers.size(); i8++) {
            MeshUtils.MeshLayerInfo meshLayerInfo = meshInfo2.layers.get(i8);
            float[] fArr = meshLayerInfo.vertices;
            float[] fArr2 = {fArr[0], fArr[1], fArr[2]};
            float[] fArr3 = {fArr[0], fArr[1], fArr[2]};
            int i10 = 0;
            while (true) {
                float[] fArr4 = meshLayerInfo.vertices;
                if (i10 >= fArr4.length) {
                    break;
                }
                int i11 = i10 % 3;
                fArr2[i11] = Math.min(fArr2[i11], fArr4[i10]);
                fArr3[i11] = Math.max(fArr3[i11], meshLayerInfo.vertices[i10]);
                i10++;
            }
            arrayList2.add(fArr2);
            arrayList3.add(fArr3);
        }
        if (z) {
            for (int i12 = 0; i12 < meshInfo2.layers.size(); i12++) {
            }
        } else {
            for (int i13 = 0; i13 < meshInfo2.layers.size(); i13++) {
            }
        }
        int i14 = 0;
        while (true) {
            if (i14 >= list2.size()) {
                i14 = -1;
                break;
            } else if (list2.get(i14).type.compareTo("image") == 0) {
                break;
            } else {
                i14++;
            }
        }
        if (i14 < 0) {
            LogUtil.e(TAG, "Failed to find buffer information for JPEG images");
            return null;
        }
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject = null;
            try {
                int i15 = 2;
                int i16 = 1;
                jSONObject3.put("asset", new JSONObject().put("version", "2.0").put("generator", "LiveEffect").put("extras", new JSONObject().put("liveEffectVersion", BuildConfig.SDK_VERSION)));
                JSONArray jSONArray3 = new JSONArray();
                int i17 = 0;
                int i18 = 0;
                while (i17 < list2.size()) {
                    ByteBufferInfo byteBufferInfo = list2.get(i17);
                    JSONObject put = new JSONObject().put("buffer", 0).put("byteOffset", i18).put("byteLength", byteBufferInfo.length);
                    int i19 = byteBufferInfo.stride;
                    if (i19 > 0) {
                        put.put("byteStride", i19);
                    }
                    int i20 = byteBufferInfo.target;
                    if (i20 > 0) {
                        put.put("target", i20);
                    }
                    i18 += byteBufferInfo.alignedLength;
                    jSONArray3.put(put);
                    i17++;
                    list2 = list;
                }
                JSONArray jSONArray4 = new JSONArray();
                JSONArray jSONArray5 = new JSONArray();
                JSONArray jSONArray6 = new JSONArray();
                JSONArray jSONArray7 = new JSONArray();
                JSONArray jSONArray8 = new JSONArray();
                int size = meshInfo2.layers.size();
                int i21 = i14;
                str = TAG;
                int i22 = 0;
                while (true) {
                    str2 = "name";
                    arrayList = arrayList3;
                    str3 = "bufferView";
                    if (i22 >= size) {
                        break;
                    }
                    try {
                        jSONArray4.put(i22);
                        jSONArray5.put(new JSONObject().put("mesh", i22));
                        jSONArray6.put(new JSONObject().put(str3, i21 + i22).put(FileApiContract.Parameter.MIME_TYPE, IMAGE_MIME_TYPES.get(formatInfo.compressFormat)));
                        jSONArray7.put(new JSONObject().put("sampler", 0).put("source", i22));
                        jSONArray8.put(new JSONObject().put(str2, "layer " + i22).put("alphaMode", "BLEND").put("pbrMetallicRoughness", new JSONObject().put("baseColorTexture", new JSONObject().put("index", i22)).put("baseColorFactor", new JSONArray().put(1.0d).put(1.0d).put(1.0d).put(1.0d)).put("metallicFactor", MapUtil.INVALID_LOCATION)));
                        i22++;
                        size = size;
                        MeshUtils.MeshInfo meshInfo3 = meshInfo;
                        arrayList3 = arrayList;
                        arrayList2 = arrayList2;
                        jSONArray3 = jSONArray3;
                        jSONArray7 = jSONArray7;
                    } catch (JSONException e) {
                        e = e;
                        LogUtil.e(str, "Failed to generate glTF JSON: " + e);
                        return jSONObject;
                    }
                }
                int i23 = size;
                ArrayList arrayList4 = arrayList2;
                jSONObject3.put("scene", 0);
                jSONObject3.put("scenes", new JSONArray().put(new JSONObject().put("nodes", jSONArray4)));
                jSONObject3.put("nodes", jSONArray5);
                jSONObject3.put("buffers", new JSONArray().put(new JSONObject().put("byteLength", i2)));
                jSONObject3.put("bufferViews", jSONArray3);
                jSONObject3.put("samplers", new JSONArray().put(new JSONObject().put("magFilter", 9729).put("minFilter", 9729).put("wrapS", 33071).put("wrapT", 33071)));
                jSONObject3.put("images", jSONArray6);
                jSONObject3.put("textures", jSONArray7);
                jSONObject3.put("materials", jSONArray8);
                JSONArray jSONArray9 = new JSONArray();
                JSONArray jSONArray10 = new JSONArray();
                String str5 = DRACO_EXTENSION_STRING;
                String str6 = "TEXCOORD_0";
                String str7 = "COLOR_0";
                String str8 = "attributes";
                String str9 = "count";
                String str10 = "type";
                if (z) {
                    int i24 = 0;
                    int i25 = i23;
                    while (i24 < i25) {
                        MeshUtils.MeshInfo meshInfo4 = meshInfo;
                        JSONObject jSONObject4 = jSONObject3;
                        int i26 = i25;
                        int indicesCount = indicesCount(4, meshInfo4.layers.get(i24));
                        int length = meshInfo4.layers.get(i24).vertices.length / 3;
                        JSONArray jSONArray11 = jSONArray10;
                        jSONArray9.put(new JSONObject().put("componentType", 5125).put(str10, "SCALAR").put(str9, indicesCount));
                        String str11 = str5;
                        ArrayList arrayList5 = arrayList4;
                        String str12 = str3;
                        String str13 = str8;
                        String str14 = str7;
                        ArrayList arrayList6 = arrayList;
                        jSONArray9.put(new JSONObject().put("componentType", 5126).put(str10, "VEC3").put(str9, length).put("min", new JSONArray().put((double) ((float[]) arrayList5.get(i24))[0]).put((double) ((float[]) arrayList5.get(i24))[i16]).put((double) ((float[]) arrayList5.get(i24))[i15])).put("max", new JSONArray().put((double) ((float[]) arrayList6.get(i24))[0]).put((double) ((float[]) arrayList6.get(i24))[i16]).put((double) ((float[]) arrayList6.get(i24))[i15])));
                        jSONArray9.put(new JSONObject().put("componentType", 5126).put(str10, "VEC4").put(str9, length));
                        jSONArray9.put(new JSONObject().put("componentType", 5126).put(str10, "VEC2").put(str9, length));
                        JSONObject put2 = new JSONObject().put(str2, "Layer " + i24);
                        JSONArray jSONArray12 = new JSONArray();
                        int i27 = i24 * 4;
                        JSONObject put3 = new JSONObject().put("mode", 4).put("indices", i27).put("material", i24);
                        String str15 = str6;
                        JSONObject put4 = new JSONObject().put("POSITION", i27 + 1);
                        int i28 = i27 + 2;
                        int i29 = i27;
                        String str16 = str14;
                        JSONObject put5 = put4.put(str16, i28);
                        int i30 = i29 + 3;
                        String str17 = str2;
                        String str18 = str15;
                        JSONObject put6 = put5.put(str18, i30);
                        String str19 = str13;
                        ArrayList arrayList7 = arrayList6;
                        ArrayList arrayList8 = arrayList5;
                        JSONArray jSONArray13 = jSONArray9;
                        String str20 = str12;
                        String str21 = str9;
                        String str22 = str11;
                        JSONArray jSONArray14 = jSONArray11;
                        jSONArray14.put(put2.put("primitives", jSONArray12.put(put3.put(str19, put6).put("extensions", new JSONObject().put(str22, new JSONObject().put(str20, i24).put(str19, new JSONObject().put("POSITION", 0).put(str16, i16).put(str18, i15)))))));
                        i24++;
                        str3 = str20;
                        jSONArray10 = jSONArray14;
                        str5 = str22;
                        str6 = str18;
                        str8 = str19;
                        str7 = str16;
                        jSONObject3 = jSONObject4;
                        str2 = str17;
                        arrayList = arrayList7;
                        i25 = i26;
                        arrayList4 = arrayList8;
                        str9 = str21;
                        i15 = 2;
                        i16 = 1;
                        jSONArray9 = jSONArray13;
                    }
                    str4 = str5;
                    jSONObject2 = jSONObject3;
                    jSONArray2 = jSONArray9;
                    jSONArray = jSONArray10;
                } else {
                    int i31 = i23;
                    JSONArray jSONArray15 = jSONArray9;
                    String str23 = str7;
                    String str24 = str3;
                    String str25 = str9;
                    jSONObject2 = jSONObject3;
                    ArrayList arrayList9 = arrayList4;
                    str4 = str5;
                    String str26 = str8;
                    ArrayList arrayList10 = arrayList;
                    jSONArray = jSONArray10;
                    String str27 = str2;
                    String str28 = str6;
                    int i32 = 0;
                    while (true) {
                        int i33 = i31;
                        if (i32 >= i33) {
                            break;
                        }
                        MeshUtils.MeshInfo meshInfo5 = meshInfo;
                        int indicesCount2 = indicesCount(meshInfo5.layers.get(i32).primitive, meshInfo5.layers.get(i32));
                        int length2 = meshInfo5.layers.get(i32).vertices.length / 3;
                        int i34 = i32 * 4;
                        i31 = i33;
                        String str29 = str25;
                        JSONObject put7 = new JSONObject().put(str24, i34).put("componentType", 5125).put(str10, "SCALAR").put(str29, indicesCount2);
                        JSONArray jSONArray16 = jSONArray15;
                        jSONArray16.put(put7);
                        String str30 = str4;
                        int i35 = i34 + 1;
                        JSONArray jSONArray17 = jSONArray;
                        String str31 = str26;
                        String str32 = str28;
                        ArrayList arrayList11 = arrayList9;
                        String str33 = str23;
                        int i36 = i34;
                        ArrayList arrayList12 = arrayList10;
                        ArrayList arrayList13 = arrayList11;
                        jSONArray16.put(new JSONObject().put(str24, i35).put("componentType", 5126).put(str10, "VEC3").put(str29, length2).put("min", new JSONArray().put((double) ((float[]) arrayList11.get(i32))[0]).put((double) ((float[]) arrayList11.get(i32))[1]).put((double) ((float[]) arrayList11.get(i32))[2])).put("max", new JSONArray().put((double) ((float[]) arrayList12.get(i32))[0]).put((double) ((float[]) arrayList12.get(i32))[1]).put((double) ((float[]) arrayList12.get(i32))[2])));
                        int i37 = i36 + 2;
                        jSONArray16.put(new JSONObject().put(str24, i37).put("componentType", 5126).put(str10, "VEC4").put(str29, length2));
                        int i38 = i36 + 3;
                        jSONArray16.put(new JSONObject().put(str24, i38).put("componentType", 5126).put(str10, "VEC2").put(str29, length2));
                        String str34 = str27;
                        JSONObject put8 = new JSONObject().put(str34, "Layer " + i32);
                        JSONArray jSONArray18 = new JSONArray();
                        String str35 = str24;
                        String str36 = str10;
                        JSONObject put9 = new JSONObject().put("mode", meshInfo.layers.get(i32).primitive).put("indices", i36).put("material", i32);
                        String str37 = str33;
                        JSONObject put10 = new JSONObject().put("POSITION", i35).put(str37, i37);
                        String str38 = str32;
                        JSONObject put11 = put10.put(str38, i38);
                        String str39 = str31;
                        jSONArray = jSONArray17;
                        jSONArray.put(put8.put("primitives", jSONArray18.put(put9.put(str39, put11))));
                        i32++;
                        jSONArray15 = jSONArray16;
                        str26 = str39;
                        arrayList10 = arrayList12;
                        arrayList9 = arrayList13;
                        str24 = str35;
                        str25 = str29;
                        str23 = str37;
                        str27 = str34;
                        str10 = str36;
                        str28 = str38;
                        str4 = str30;
                    }
                    jSONArray2 = jSONArray15;
                }
                String str40 = str4;
                JSONObject jSONObject5 = jSONObject2;
                jSONObject5.put("accessors", jSONArray2);
                jSONObject5.put("meshes", jSONArray);
                if (z) {
                    String str41 = str40;
                    jSONObject5.put("extensionsRequired", new JSONArray().put(str41));
                    jSONObject5.put("extensionsUsed", new JSONArray().put(str41));
                }
                return jSONObject5;
            } catch (JSONException e7) {
                e = e7;
                str = TAG;
                LogUtil.e(str, "Failed to generate glTF JSON: " + e);
                return jSONObject;
            }
        } catch (JSONException e8) {
            e = e8;
            jSONObject = null;
            str = TAG;
            LogUtil.e(str, "Failed to generate glTF JSON: " + e);
            return jSONObject;
        }
    }

    private static MeshUtils.MeshInfo setupMeshData(Context context, MeshVersionInfo meshVersionInfo, List<MeshDetails> list, List<ByteBufferInfo> list2, List<AccessorInfo> list3, List<Bitmap> list4) {
        double[] dArr;
        MeshUtils.MeshInfo meshInfo = new MeshUtils.MeshInfo();
        meshInfo.layers = new ArrayList();
        for (MeshDetails next : list) {
            MeshUtils.MeshLayerInfo meshLayerInfo = new MeshUtils.MeshLayerInfo();
            if (next.dracoCompressed) {
                MeshUtils.MeshLayerInfo decode = DracoCompression.decode(context, list2.get(next.dracoBufferView).buffer.array());
                if (decode != null) {
                    meshLayerInfo.primitive = decode.primitive;
                    meshLayerInfo.indices = decode.indices;
                    meshLayerInfo.vertices = decode.vertices;
                    meshLayerInfo.colors = decode.colors;
                    meshLayerInfo.texCoords = decode.texCoords;
                } else {
                    LogUtil.e(TAG, "Failed to decode at DracoCompression");
                    throw new RuntimeException("Failed to decode Draco data");
                }
            } else {
                meshLayerInfo.primitive = next.primitive;
                ByteBuffer byteBuffer = list2.get(list3.get(next.indicesAccessor).bufferView).buffer;
                ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                IntBuffer asIntBuffer = byteBuffer.order(byteOrder).asIntBuffer();
                int[] iArr = new int[asIntBuffer.limit()];
                meshLayerInfo.indices = iArr;
                asIntBuffer.get(iArr);
                FloatBuffer asFloatBuffer = list2.get(list3.get(next.positionAccessor).bufferView).buffer.order(byteOrder).asFloatBuffer();
                float[] fArr = new float[asFloatBuffer.limit()];
                meshLayerInfo.vertices = fArr;
                asFloatBuffer.get(fArr);
                FloatBuffer asFloatBuffer2 = list2.get(list3.get(next.colorAccessor).bufferView).buffer.order(byteOrder).asFloatBuffer();
                float[] fArr2 = new float[asFloatBuffer2.limit()];
                meshLayerInfo.colors = fArr2;
                asFloatBuffer2.get(fArr2);
                FloatBuffer asFloatBuffer3 = list2.get(list3.get(next.texCoordAccessor).bufferView).buffer.order(byteOrder).asFloatBuffer();
                float[] fArr3 = new float[asFloatBuffer3.limit()];
                meshLayerInfo.texCoords = fArr3;
                asFloatBuffer3.get(fArr3);
            }
            meshLayerInfo.bitmap = list4.get(next.bitmapIndex);
            meshInfo.layers.add(meshLayerInfo);
        }
        if (meshVersionInfo.generator.equals("LiveEffect") && meshVersionInfo.version < StringUtil.versionFromString("8.5.10")) {
            MeshDetails meshDetails = list.get(0);
            AccessorInfo accessorInfo = list3.get(meshDetails.positionAccessor);
            Bitmap bitmap = list4.get(meshDetails.bitmapIndex);
            double[] dArr2 = accessorInfo.min;
            if (dArr2 == null || (dArr = accessorInfo.max) == null) {
                LogUtil.e(TAG, "Min/max data not present for position accessor");
            } else {
                float f = ((float) (dArr[0] - dArr2[0])) / ((float) (dArr[1] - dArr2[1]));
                float width = ((float) bitmap.getWidth()) / ((float) bitmap.getHeight());
                LogUtil.d(TAG, "mesh aspect ratio " + f + ", bitmap aspect ratio " + width);
                if (((double) Math.abs(f - width)) > 0.001d) {
                    float f5 = width / f;
                    LogUtil.d(TAG, "Scaling mesh vertices during import: scale " + f5);
                    for (MeshUtils.MeshLayerInfo next2 : meshInfo.layers) {
                        int i2 = 0;
                        while (true) {
                            float[] fArr4 = next2.vertices;
                            if (i2 < fArr4.length) {
                                fArr4[i2] = fArr4[i2] * f5;
                                i2 += 3;
                            }
                        }
                    }
                }
            }
        }
        return meshInfo;
    }

    private static boolean verifyDataChunks(List<DataChunk> list) {
        boolean z;
        TreeMap treeMap = new TreeMap();
        for (DataChunk dataChunk : list) {
            String str = dataChunk.type;
            Integer num = (Integer) treeMap.getOrDefault(str, 0);
            Objects.requireNonNull(num);
            treeMap.put(str, Integer.valueOf(num.intValue() + 1));
        }
        Integer num2 = (Integer) treeMap.getOrDefault("JSON", 0);
        Objects.requireNonNull(num2);
        int intValue = num2.intValue();
        if (intValue > 1) {
            LogUtil.e(TAG, "Expecting a single JSON data chunk; found " + intValue);
            z = false;
        } else {
            z = true;
        }
        Integer num3 = (Integer) treeMap.getOrDefault("BIN\u0000", 0);
        Objects.requireNonNull(num3);
        int intValue2 = num3.intValue();
        if (intValue2 >= 1) {
            return z;
        }
        LogUtil.e(TAG, "Expecting at least one BIN data chunk; found " + intValue2);
        return false;
    }

    private static boolean verifyHeader(byte[] bArr) {
        if (bArr.length < 12) {
            LogUtil.e(TAG, "Invalid glTF file; header too short");
            return false;
        } else if (!Arrays.equals(Arrays.copyOfRange(bArr, 0, 4), "glTF".getBytes(StandardCharsets.UTF_8))) {
            LogUtil.e(TAG, "Not a glTF file");
            return false;
        } else if (ByteBuffer.wrap(bArr, 4, 4).order(ByteOrder.LITTLE_ENDIAN).getInt() == 2) {
            return true;
        } else {
            LogUtil.e(TAG, "Invalid glTF file version");
            return false;
        }
    }

    public static MeshUtils.MeshInfo loadMesh(Context context, File file) {
        FileInputStream fileInputStream;
        byte[] bArr = new byte[((int) file.length())];
        try {
            fileInputStream = new FileInputStream(file);
            do {
            } while (fileInputStream.read(bArr) != -1);
            fileInputStream.close();
            return loadMesh(context, bArr);
        } catch (IOException e) {
            LogUtil.e(TAG, "Failed to read mesh data: " + e);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
