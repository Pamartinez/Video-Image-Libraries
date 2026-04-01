package com.samsung.android.gallery.support.library.v12;

import android.content.Context;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.SemBlurInfo;
import android.view.View;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v0.system.DexInfo;
import com.samsung.android.gallery.support.library.v12.media.Sec150MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v12.system.DexInfo150;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem150ApiCompatImpl extends Sem145ApiCompatImpl {
    static volatile StorageManager sStorageManager;
    private final int COLOR_CURVE_LENGTH = 6;
    private final HashMap<Integer, float[]> mColorCurveMap = new HashMap<Integer, float[]>() {
        {
            put(126, new float[]{300.0f, 0.45f, -5.0f, 0.0f, 255.0f, 13.3f, 235.1f});
            put(115, new float[]{300.0f, 0.75f, 25.0f, 15.0f, 235.0f, 208.1f, 246.3f});
            put(111, new float[]{300.0f, 0.4f, 20.0f, 5.0f, 23.0f, 17.8f, 247.3f});
        }
    };

    private float[] getColorCurve(int i2) {
        return this.mColorCurveMap.get(Integer.valueOf(i2));
    }

    public DexInfo computeDexInfo(Context context) {
        return new DexInfo150().compute(context);
    }

    public boolean copyOnVold(Context context, String str, String str2) {
        try {
            if (sStorageManager == null) {
                sStorageManager = (StorageManager) context.getApplicationContext().getSystemService("storage");
            }
            return sStorageManager.semCopyFileAtData(str, str2);
        } catch (Error | Exception e) {
            String str3 = this.TAG;
            Log.w(str3, "copyOnVold failed. e=" + e.getMessage());
            return false;
        }
    }

    public MediaPlayerCompat createSecMediaPlayer(int i2) {
        return new Sec150MediaPlayerCompat(i2);
    }

    public boolean moveOnVold(Context context, String str, String str2) {
        try {
            if (sStorageManager == null) {
                sStorageManager = (StorageManager) context.getApplicationContext().getSystemService("storage");
            }
            return sStorageManager.semMoveFileAtData(str, str2);
        } catch (Error | Exception e) {
            String str3 = this.TAG;
            Log.w(str3, "moveOnVold failed. e=" + e.getMessage());
            return false;
        }
    }

    public void removeBlur(View view) {
        if (view != null) {
            view.semSetBlurInfo((SemBlurInfo) null);
        }
    }

    public void setBlurPreset(View view, int i2, float f, int i7) {
        if (view != null) {
            SemBlurInfo.Builder builder = new SemBlurInfo.Builder(i2);
            if (i7 < 11 || i7 > 16) {
                float[] colorCurve = getColorCurve(i7);
                if (colorCurve == null || colorCurve.length <= 6) {
                    String str = this.TAG;
                    Log.w(str, "setBlurPreset failed. not supported preset=" + i7);
                    return;
                }
                builder.setColorCurve(colorCurve[0], colorCurve[1], colorCurve[2], colorCurve[3], colorCurve[4], colorCurve[5]);
            } else {
                builder.setColorCurvePreset(i7);
            }
            if (f > 0.0f) {
                builder.setBackgroundCornerRadius(f);
            }
            view.semSetBlurInfo(builder.build());
        }
    }

    public void setCanvasBlurPreset(View view, int i2) {
        setBlurPreset(view, 2, 0.0f, i2);
    }
}
