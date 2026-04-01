package yc;

import android.graphics.Bitmap;
import android.util.Log;
import com.samsung.android.portrait.engine.SinglePortraitWrapper;
import com.samsung.android.portrait.imagehandler.ImageHandlerJNI;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends b {

    /* renamed from: a  reason: collision with root package name */
    public int f3300a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public ByteBuffer f3301c;
    public boolean d;
    public SinglePortraitWrapper e;

    public final synchronized int a() {
        int i2;
        try {
            Log.i("SecPortraitGalleryDecision", "release E");
            i2 = 0;
            if (this.d) {
                int c5 = this.e.c();
                if (c5 == 0) {
                    this.d = false;
                }
                i2 = c5;
            } else {
                Log.i("SecPortraitGalleryDecision", "already released!");
            }
            Log.i("SecPortraitGalleryDecision", "release X");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return i2;
    }

    /* JADX INFO: finally extract failed */
    public final synchronized int b(Bitmap bitmap) {
        try {
            Log.i("SecPortraitGalleryDecision", "run E");
            if (!this.d) {
                Log.e("SecPortraitGalleryDecision", "engine is not initialized before run!");
                return 2;
            } else if (bitmap == null) {
                return 2;
            } else {
                if (e(bitmap) != 0) {
                    return 1;
                }
                SinglePortraitWrapper singlePortraitWrapper = this.e;
                ByteBuffer byteBuffer = this.f3301c;
                int i2 = this.f3300a;
                int i7 = this.b;
                singlePortraitWrapper.d(byteBuffer, i2, i7, i2, i7);
                int b5 = this.e.b();
                Log.i("SecPortraitGalleryDecision", "run X : " + Integer.toString(b5));
                return b5;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final synchronized void c(int i2) {
        try {
            Log.i("SecPortraitGalleryDecision", "setProperty: id=1004 value:" + i2);
            if (!this.d) {
                Log.e("SecPortraitGalleryDecision", "engine is not initialized before setProperty!");
            } else {
                this.e.e(i2);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final synchronized int d() {
        int i2;
        try {
            Log.i("SecPortraitGalleryDecision", "initialize E");
            if (!this.d) {
                i2 = this.e.a(56);
                if (i2 == 0) {
                    this.d = true;
                }
            } else {
                Log.i("SecPortraitGalleryDecision", "already initialized!");
                i2 = 0;
            }
            Log.i("SecPortraitGalleryDecision", "initialize X");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return i2;
    }

    public final int e(Bitmap bitmap) {
        int i2;
        int i7;
        Log.i("SecPortraitGalleryDecision", "setByteBufferFromBitmap E");
        if (bitmap.getWidth() % 2 == 0) {
            i2 = bitmap.getWidth();
        } else {
            i2 = bitmap.getWidth() + 1;
        }
        this.f3300a = i2;
        if (bitmap.getHeight() % 2 == 0) {
            i7 = bitmap.getHeight();
        } else {
            i7 = bitmap.getHeight() + 1;
        }
        this.b = i7;
        Log.i("SecPortraitGalleryDecision", "setByteBufferFromBitmap input resolution : width_" + this.f3300a + ", height_" + this.b);
        int i8 = (this.b / 2) * this.f3300a * 3;
        byte[] bArr = new byte[i8];
        ImageHandlerJNI.convertBitmaptoNV21(bitmap, bArr);
        this.f3301c = null;
        try {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i8);
            allocateDirect.put(bArr);
            this.f3301c = allocateDirect;
        } catch (NoSuchFieldException e7) {
            e7.printStackTrace();
        }
        if (this.f3301c == null) {
            Log.e("SecPortraitGalleryDecision", "setByteBufferFromBitmap fail!");
            return 2;
        }
        Log.i("SecPortraitGalleryDecision", "setByteBufferFromBitmap X");
        return 0;
    }
}
