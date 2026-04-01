package com.samsung.android.gallery.support.library.v12;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.View;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.library.v12.remster.SemRemasterManagerCompat145;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem145ApiCompatImpl extends Sem141ApiCompatImpl {
    public void addBitmapTag(Bitmap bitmap, String str, Object obj) {
        try {
            HashMap hashMap = (HashMap) bitmap.semGetTag();
            if (hashMap == null) {
                hashMap = new HashMap();
                bitmap.semSetTag(hashMap);
            }
            hashMap.put(str, obj);
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("setBitmapTag failed. e="), this.TAG);
        }
    }

    public void adjustPopOverOptions(Activity activity, int[] iArr, int[] iArr2, Point[] pointArr, int[] iArr3) {
        if (activity != null) {
            activity.semAdjustPopOverOptions(iArr, iArr2, pointArr, iArr3);
        }
    }

    public void clearBitmapTag(Bitmap bitmap) {
        try {
            bitmap.semSetTag((Object) null);
        } catch (Error | Exception unused) {
        }
    }

    public <T> T getBitmapTag(Bitmap bitmap, String str, T t) {
        T t3;
        try {
            HashMap hashMap = (HashMap) bitmap.semGetTag();
            if (hashMap == null) {
                t3 = null;
            } else {
                t3 = hashMap.get(str);
            }
            if (t3 == null) {
                return t;
            }
            return t3;
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getBitmapTag failed. e="), this.TAG);
            return t;
        }
    }

    public ArrayList<ClipData.Item> getClipDataItems(ClipData clipData) {
        if (clipData != null) {
            return clipData.semGetItems();
        }
        return null;
    }

    public VslMesDetectorCompat getVslMesDetectorCompat(String str) {
        return new SemRemasterManagerCompat145();
    }

    public boolean updateClipData(View view, ClipData clipData) {
        if (view == null || !view.semUpdateClipData(clipData)) {
            return false;
        }
        return true;
    }
}
