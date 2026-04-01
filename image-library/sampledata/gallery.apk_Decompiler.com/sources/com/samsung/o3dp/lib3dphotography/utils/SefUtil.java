package com.samsung.o3dp.lib3dphotography.utils;

import He.F;
import android.util.Log;
import com.samsung.android.media.SemExtendedFormat;
import i.C0212a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SefUtil {
    private static final String TAG = "SefUtil";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SefData {
        private final Map<String, SefDataInfo> data;

        public SefData(Map<String, SefDataInfo> map) {
            this.data = new HashMap(map);
        }

        public Map<String, SefDataInfo> getData() {
            return new HashMap(this.data);
        }

        public int getDataCount() {
            return this.data.size();
        }

        public boolean isEmpty() {
            return this.data.isEmpty();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SefDataInfo {
        private final byte[] data;
        private final int dataType;

        public SefDataInfo(byte[] bArr, int i2) {
            this.data = (byte[]) bArr.clone();
            this.dataType = i2;
        }

        public byte[] getData() {
            return (byte[]) this.data.clone();
        }

        public int getDataType() {
            return this.dataType;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SefMetadata {
        PHOTO_3D("3DPhoto_Info", 3505);
        
        private final int dataType;
        private final String key;

        private SefMetadata(String str, int i2) {
            this.key = str;
            this.dataType = i2;
        }

        public int getDataType() {
            return this.dataType;
        }

        public String getKey() {
            return this.key;
        }
    }

    private SefUtil() {
        throw new AssertionError();
    }

    public static boolean addSefData(String str, byte[] bArr, SefMetadata sefMetadata) {
        try {
            if (SemExtendedFormat.addData(new File(str), sefMetadata.getKey(), bArr, sefMetadata.getDataType(), 1) == 0) {
                return true;
            }
            return false;
        } catch (IOException e) {
            F.D(TAG, "addSefData " + e);
            return false;
        }
    }

    public static void attachSef(InputStream inputStream, OutputStream outputStream, byte[] bArr, SefMetadata sefMetadata) {
        byte[] bytes = ByteUtil.toBytes(inputStream);
        byte[] createSef = createSef(bArr, sefMetadata);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(bytes);
        byteArrayOutputStream.write(createSef);
        outputStream.write(byteArrayOutputStream.toByteArray());
    }

    public static void copyAllSefData(File file, File file2) {
        try {
            SemExtendedFormat.copyAllData(file, file2);
        } catch (IOException e) {
            Log.e("LE-".concat(TAG), "Failed to copy sef data", e);
        }
    }

    public static byte[] createSef(byte[] bArr, SefMetadata sefMetadata) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[(bArr.length + 500)]);
        long addData = SemExtendedFormat.addData(wrap, sefMetadata.getKey(), bArr, sefMetadata.getDataType(), 1);
        wrap.position(0);
        wrap.limit((int) addData);
        return new byte[wrap.remaining()];
    }

    public static boolean deleteLiveEffectSefData(String str) {
        try {
            if (!SemExtendedFormat.deleteData(new File(str), SefMetadata.PHOTO_3D.getKey())) {
                return false;
            }
            F.C(TAG, "deleteSefData at " + str + ".");
            return true;
        } catch (IOException e) {
            F.D(TAG, "deleteSefData " + e);
            return false;
        }
    }

    public static SefData getAllSefData(String str) {
        return getAllSefData(new File(str));
    }

    public static byte[] getSefData(String str, SefMetadata sefMetadata) {
        File file = new File(str);
        String key = sefMetadata.getKey();
        try {
            return SemExtendedFormat.getData(file, key);
        } catch (IOException e) {
            F.D(TAG, "getSefData for " + key + ", error: " + e);
            return null;
        }
    }

    public static SefData getAllSefData(File file) {
        HashMap hashMap = new HashMap();
        String[] keyNameArray = SemExtendedFormat.getKeyNameArray(file);
        if (keyNameArray == null || keyNameArray.length == 0) {
            F.C(TAG, "No SEF data found in file: " + file.getPath());
            return new SefData(hashMap);
        }
        int length = keyNameArray.length;
        int i2 = 0;
        while (i2 < length) {
            String str = keyNameArray[i2];
            try {
                byte[] data = SemExtendedFormat.getData(file, str);
                int dataType = SemExtendedFormat.getDataType(file, str);
                if (data != null) {
                    hashMap.put(str, new SefDataInfo(data, dataType));
                }
                i2++;
            } catch (IOException e) {
                Log.e("LE-".concat(TAG), C0212a.l("Failed to get SEF data for key: ", str), e);
                throw e;
            }
        }
        F.C(TAG, "Successfully got " + hashMap.size() + " SEF data entries");
        return new SefData(hashMap);
    }

    public static void addSefData(String str, SefData sefData) {
        addSefData(new File(str), sefData);
    }

    public static void addSefData(File file, SefData sefData) {
        Map<String, SefDataInfo> data = sefData.getData();
        if (data.isEmpty()) {
            F.C(TAG, "No SEF data to restore");
            return;
        }
        for (Map.Entry next : data.entrySet()) {
            String str = (String) next.getKey();
            SefDataInfo sefDataInfo = (SefDataInfo) next.getValue();
            int addData = SemExtendedFormat.addData(file, str, sefDataInfo.getData(), sefDataInfo.getDataType(), 1);
            if (addData == 0) {
                throw new IOException("Failed to restore SEF data for key: " + str + ", result: " + addData);
            }
        }
    }
}
