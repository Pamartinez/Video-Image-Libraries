package com.samsung.android.gallery.image360.engine.view;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import com.samsung.android.gallery.image360.engine.texture.StatusHandler;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapTask extends AsyncTask<int[], Void, Object> {
    private final int mHeight;
    protected final StatusHandler mStatusHandler;
    private final int mWidth;

    public BitmapTask(int i2, int i7, StatusHandler statusHandler) {
        this.mWidth = i2;
        this.mHeight = i7;
        this.mStatusHandler = statusHandler;
    }

    private int[] convertTexturePixels(int[] iArr) {
        int[] iArr2 = new int[(this.mWidth * this.mHeight)];
        int i2 = 0;
        while (true) {
            int i7 = this.mHeight;
            if (i2 >= i7) {
                return iArr2;
            }
            int i8 = this.mWidth;
            int i10 = i2 * i8;
            int i11 = ((i7 - i2) - 1) * i8;
            for (int i12 = 0; i12 < this.mWidth; i12++) {
                int i13 = iArr[i10 + i12];
                iArr2[i11 + i12] = (i13 & -16711936) | ((i13 << 16) & 16711680) | ((i13 >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
            }
            i2++;
        }
    }

    public void onPostExecute(Object obj) {
        if (obj instanceof Bitmap) {
            this.mStatusHandler.onBitmapCreated((Bitmap) obj);
        } else {
            Log.e("BitmapTask", "invalid bitmap");
        }
    }

    public Object doInBackground(int[]... iArr) {
        int[] iArr2;
        if (iArr == null || (iArr2 = iArr[0]) == null) {
            return Boolean.FALSE;
        }
        return Bitmap.createBitmap(convertTexturePixels(iArr2), this.mWidth, this.mHeight, Bitmap.Config.ARGB_8888);
    }
}
