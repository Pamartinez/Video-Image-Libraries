package com.quramsoft.agifDecoder;

import android.graphics.Bitmap;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuramAGIFDecoder {
    protected long mHandle = 0;

    static {
        loadLib();
    }

    public QuramAGIFDecoder(String str) {
        if (str != null) {
            nativeInitHandle(this, str);
        }
    }

    public static void loadLib() {
        try {
            System.loadLibrary("agifencoder.quram");
        } catch (Exception e) {
            Log.e("PEDIT_QuramAGIFEncoder", "Load library fail : " + e.toString());
        }
    }

    public int decodeFrame(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        return nativeDecodeFrame(this.mHandle, bitmap);
    }

    public boolean finish() {
        return nativeFinish(this.mHandle);
    }

    public int getDelay() {
        return nativeGetDelay(this.mHandle);
    }

    public int getHeight() {
        return nativeGetHeight(this.mHandle);
    }

    public int getNumOfFrame() {
        return nativeGetNumOfFrame(this.mHandle);
    }

    public int getWidth() {
        return nativeGetWidth(this.mHandle);
    }

    public native int nativeDecodeFrame(long j2, Bitmap bitmap);

    public native boolean nativeFinish(long j2);

    public native int nativeGetDelay(long j2);

    public native int nativeGetHeight(long j2);

    public native int nativeGetNumOfFrame(long j2);

    public native int nativeGetWidth(long j2);

    public native void nativeInitByteArrayHandle(QuramAGIFDecoder quramAGIFDecoder, byte[] bArr, int i2);

    public native void nativeInitHandle(QuramAGIFDecoder quramAGIFDecoder, String str);

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public QuramAGIFDecoder(android.content.Context r8, android.net.Uri r9) {
        /*
            r7 = this;
            r7.<init>()
            r0 = 0
            r7.mHandle = r0
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch:{ FileNotFoundException -> 0x001b }
            java.lang.String r2 = "r"
            android.os.ParcelFileDescriptor r8 = r8.openFileDescriptor(r9, r2)     // Catch:{ FileNotFoundException -> 0x001b }
            goto L_0x0020
        L_0x0018:
            r8 = move-exception
            r9 = r1
            goto L_0x002e
        L_0x001b:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ Exception -> 0x0018 }
            r8 = r1
        L_0x0020:
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch:{ Exception -> 0x002a }
            java.io.FileDescriptor r2 = r8.getFileDescriptor()     // Catch:{ Exception -> 0x002a }
            r9.<init>(r2)     // Catch:{ Exception -> 0x002a }
            goto L_0x0033
        L_0x002a:
            r9 = move-exception
            r6 = r9
            r9 = r8
            r8 = r6
        L_0x002e:
            r8.printStackTrace()
            r8 = r9
            r9 = r1
        L_0x0033:
            java.lang.String r2 = "PEDIT_QuramAGIFEncoder"
            if (r9 != 0) goto L_0x003d
            java.lang.String r7 = "inputstream is null"
            android.util.Log.e(r2, r7)
            goto L_0x0084
        L_0x003d:
            r3 = 65536(0x10000, float:9.18355E-41)
            byte[] r3 = new byte[r3]
        L_0x0041:
            int r4 = r9.read(r3)     // Catch:{ IOException -> 0x0057 }
            r5 = -1
            if (r4 != r5) goto L_0x0059
            r8.close()     // Catch:{ IOException -> 0x0057 }
            r9.close()     // Catch:{ IOException -> 0x0057 }
            byte[] r8 = r0.toByteArray()     // Catch:{ IOException -> 0x0057 }
            r0.close()     // Catch:{ IOException -> 0x0057 }
            r1 = r8
            goto L_0x0066
        L_0x0057:
            r8 = move-exception
            goto L_0x005e
        L_0x0059:
            r5 = 0
            r0.write(r3, r5, r4)     // Catch:{ IOException -> 0x0057 }
            goto L_0x0041
        L_0x005e:
            java.lang.String r9 = "IOException happens"
            android.util.Log.e(r2, r9)
            r8.printStackTrace()
        L_0x0066:
            if (r1 != 0) goto L_0x006e
            java.lang.String r7 = "buffer is Null"
            android.util.Log.e(r2, r7)
            goto L_0x0084
        L_0x006e:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "buffer size is "
            r8.<init>(r9)
            int r9 = r1.length
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.util.Log.i(r2, r8)
            int r8 = r1.length
            r7.nativeInitByteArrayHandle(r7, r1, r8)
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.quramsoft.agifDecoder.QuramAGIFDecoder.<init>(android.content.Context, android.net.Uri):void");
    }

    public QuramAGIFDecoder(InputStream inputStream) {
        int i2;
        if (inputStream == null) {
            Log.e("PEDIT_QuramAGIFEncoder", "inputstream is null");
            return;
        }
        try {
            i2 = inputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
            i2 = 0;
        }
        if (i2 <= 0) {
            Log.e("PEDIT_QuramAGIFEncoder", "inpustream open fail");
            return;
        }
        byte[] bArr = new byte[i2];
        try {
            inputStream.read(bArr);
        } catch (IOException e7) {
            e7.printStackTrace();
            bArr = null;
        }
        nativeInitByteArrayHandle(this, bArr, bArr.length);
    }
}
