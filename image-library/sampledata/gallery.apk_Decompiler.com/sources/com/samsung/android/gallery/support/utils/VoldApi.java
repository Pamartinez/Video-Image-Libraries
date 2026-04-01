package com.samsung.android.gallery.support.utils;

import N2.j;
import android.content.Context;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import i.C0212a;
import java.io.File;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VoldApi {
    static final boolean SUPPORT = SdkConfig.atLeast(SdkConfig.GED.U);

    public static boolean makeDirectoryIfAbsent(File file) {
        try {
            File file2 = new File(new File("/data/sec/gallery/.dummy.jpg").getParent());
            if (!file2.exists()) {
                file2.mkdirs();
            }
            new File("/data/sec/gallery/.dummy.jpg").createNewFile();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(file.getAbsolutePath());
            String p6 = C0212a.p(sb2, File.separator, ".dummy.jpg");
            Log.d("VoldApi", "makeDirectoryIfAbsent", Boolean.valueOf(SeApiCompat.copyOnVold(AppResources.getAppContext(), "/data/sec/gallery/.dummy.jpg", p6.replaceFirst("/storage/emulated/", "/data/media/"))), Boolean.valueOf(new File(p6).delete()));
            return file.exists();
        } catch (IOException e) {
            j.r(e, new StringBuilder("makeDirectoryIfAbsent: fail to create dummy"), "VoldApi");
            return false;
        }
    }

    public static boolean touchOnVold(Context context, String str) {
        if (context == null || !SeApiCompat.touchOnVold(context, str)) {
            return false;
        }
        return true;
    }
}
