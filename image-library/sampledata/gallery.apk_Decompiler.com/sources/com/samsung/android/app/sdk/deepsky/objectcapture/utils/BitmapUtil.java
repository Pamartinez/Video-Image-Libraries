package com.samsung.android.app.sdk.deepsky.objectcapture.utils;

import A.a;
import a.C0068a;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/utils/BitmapUtil;", "", "<init>", "()V", "TAG", "", "resizeBitmapByScale", "Landroid/graphics/Bitmap;", "source", "scale", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BitmapUtil {
    public static final BitmapUtil INSTANCE = new BitmapUtil();
    private static final String TAG = "BitmapUtil";

    private BitmapUtil() {
    }

    public final Bitmap resizeBitmapByScale(Bitmap bitmap, float f) {
        j.e(bitmap, "source");
        int W6 = C0068a.W(((float) bitmap.getWidth()) * f);
        int W10 = C0068a.W(((float) bitmap.getHeight()) * f);
        if (W6 <= 0 || W10 <= 0) {
            Log.w(TAG, a.d(W6, W10, "resizeBitmapByScale failed {", "x", "}"));
            return null;
        } else if (W6 == bitmap.getWidth() && W10 == bitmap.getHeight()) {
            return bitmap;
        } else {
            Bitmap.Config config = bitmap.getConfig();
            j.b(config);
            Bitmap createBitmap = Bitmap.createBitmap(W6, W10, config);
            j.d(createBitmap, "createBitmap(...)");
            Canvas canvas = new Canvas(createBitmap);
            canvas.scale(f, f);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
            return createBitmap;
        }
    }
}
