package com.samsung.android.gallery.module.settings;

import A4.C0385u;
import N2.j;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CmhFeatures {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PoiHolder {
        static final boolean enabled = CmhFeatures.computeFeature("poi");
    }

    public static String buildPreferenceKey(String str) {
        StringBuilder k = j.k("cmh:", str, NumericEnum.SEP);
        k.append(SdkConfig.SEM_VER);
        return k.toString();
    }

    public static boolean computeFeature(String str) {
        String buildPreferenceKey = buildPreferenceKey(str);
        try {
            boolean computeBooleanIfAbsent = GalleryPreference.getInstanceCache().computeBooleanIfAbsent(buildPreferenceKey, new C0385u(18, str));
            Log.d("CmhFeatures", buildPreferenceKey + "=" + computeBooleanIfAbsent);
            return computeBooleanIfAbsent;
        } catch (Exception unused) {
            Log.e("CmhFeatures", buildPreferenceKey + " failed");
            return false;
        }
    }

    public static boolean loadFeature(String str) {
        Cursor query;
        Throwable th;
        if (Features.isEnabled(Features.USE_CMH)) {
            long currentTimeMillis = System.currentTimeMillis();
            String buildPreferenceKey = buildPreferenceKey(str);
            try {
                query = AppResources.getAppContext().getContentResolver().query(CmhUri.getFeature(), new String[]{"value"}, C0212a.m("key like '", str, "'"), (String[]) null, (String) null);
                if (query != null) {
                    if (query.getCount() > 0 && query.moveToFirst()) {
                        int i2 = query.getInt(0);
                        StringBuilder sb2 = new StringBuilder("loadFeature");
                        sb2.append(Logger.vt(buildPreferenceKey + "=" + i2, Long.valueOf(currentTimeMillis)));
                        Log.d("CmhFeatures", sb2.toString());
                        DebugLogger.getInstance().insertLog(buildPreferenceKey + "=" + i2);
                        boolean z = true;
                        if (i2 != 1) {
                            z = false;
                        }
                        query.close();
                        return z;
                    }
                }
                Log.w("CmhFeatures", "loadFeature {" + buildPreferenceKey + "} failed +" + (System.currentTimeMillis() - currentTimeMillis));
                if (query != null) {
                    query.close();
                }
            } catch (Error | Exception e) {
                Throwable th2 = e;
                StringBuilder k = j.k("loadFeature {", buildPreferenceKey, "} failed. e=");
                k.append(Logger.toSimpleString(th2));
                Log.e("CmhFeatures", k.toString());
            } catch (Throwable th3) {
                th.addSuppressed(th3);
            }
        }
        return false;
        throw th;
    }

    public static boolean supportPoi() {
        return PoiHolder.enabled;
    }
}
