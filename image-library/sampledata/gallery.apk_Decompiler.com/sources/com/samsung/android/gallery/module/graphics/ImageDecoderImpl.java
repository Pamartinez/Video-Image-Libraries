package com.samsung.android.gallery.module.graphics;

import A.a;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SharedByteBufferPool;
import java.io.File;
import java.io.FileInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ImageDecoderImpl {
    static final SharedByteBufferPool sBufferPool = new SharedByteBufferPool(5242880);
    final String TAG;

    public ImageDecoderImpl() {
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        Log.d(simpleName, "construct");
    }

    public Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapOptions bitmapOptions) {
        try {
            return BitmapFactory.decodeByteArray(bArr, i2, i7, bitmapOptions);
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("decodeByteArray failed. e="), this.TAG);
            return null;
        }
    }

    public Bitmap decodeFile(String str, BitmapOptions bitmapOptions) {
        try {
            return BitmapFactory.decodeFile(str, bitmapOptions);
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("decodeFile failed. e="), this.TAG);
            return null;
        }
    }

    public final byte[] readByteArray(File file) {
        FileInputStream fileInputStream;
        long length = file.length();
        if (length < 5242880) {
            int i2 = (int) length;
            byte[] byteBuffer = sBufferPool.getByteBuffer(i2);
            if (length <= ((long) byteBuffer.length)) {
                try {
                    fileInputStream = new FileInputStream(file);
                    int read = fileInputStream.read(byteBuffer, 0, i2);
                    if (((long) read) < length) {
                        String str = this.TAG;
                        Log.e(str, "readByteArray failed. file=" + length + ",read=" + read + ",buffer=" + byteBuffer.length);
                        fileInputStream.close();
                        return null;
                    }
                    fileInputStream.close();
                    return byteBuffer;
                } catch (Error | Exception e) {
                    a.z(e, new StringBuilder("readByteArray failed. e="), this.TAG);
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        return null;
        throw th;
    }

    public void recycle() {
        sBufferPool.clear();
    }

    public String toDebugString() {
        return sBufferPool.toDebugString();
    }
}
