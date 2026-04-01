package e3;

import Fd.C0744a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import java.nio.ByteBuffer;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public Context f1727a;
    public C0744a b;

    /* renamed from: c  reason: collision with root package name */
    public ByteBuffer f1728c;
    public ByteBuffer d;
    public Bundle e;
    public Uri f;

    public final void a() {
        this.f1728c.clear();
        this.d.clear();
        this.e.clear();
    }

    public final void b(Bitmap bitmap, int i2, Rect rect) {
        this.e.putInt("imageWidth", bitmap.getWidth());
        this.e.putInt("imageHeight", bitmap.getHeight());
        this.e.putInt("imageSize", bitmap.getByteCount());
        this.e.putInt("scaleFactor", i2);
        if (rect != null) {
            this.e.putIntArray("imagePadding", new int[]{rect.top, rect.bottom, rect.right, rect.left});
        }
    }

    public final boolean c(int i2, String str) {
        try {
            k.m("AsImageUpscale [UPSCALE]", " createSession, requested capacity: " + i2, str);
            Bundle bundle = new Bundle();
            bundle.putInt("capacity", i2);
            bundle.putString("engineType", str);
            Bundle call = this.f1727a.getContentResolver().call(this.f, "upscaleImage", "createSession", bundle);
            this.f1728c = ((SharedMemory) call.getParcelable("readMemory")).mapReadWrite();
            this.d = ((SharedMemory) call.getParcelable("writeMemory")).mapReadWrite();
            this.e = new Bundle();
            return true;
        } catch (Exception e7) {
            Log.e("AsImageUpscale [UPSCALE]", k.j(" createSession failed", new Object[0]), e7);
            return false;
        }
    }
}
