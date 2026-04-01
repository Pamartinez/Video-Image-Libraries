package com.samsung.android.gallery.settings.helper;

import A.a;
import A4.A;
import D3.i;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.knox.SemEnterpriseDeviceManager;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KnoxRestrictions {
    final HashSet<String> set;

    public KnoxRestrictions(Context context) {
        SemEnterpriseDeviceManager semEnterpriseDeviceManager;
        HashSet<String> hashSet = new HashSet<>();
        this.set = hashSet;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (context == null) {
                semEnterpriseDeviceManager = null;
            } else {
                semEnterpriseDeviceManager = SemEnterpriseDeviceManager.getInstance(context.getApplicationContext());
            }
            if (semEnterpriseDeviceManager != null) {
                int myUserId = SeApiCompat.getMyUserId();
                Bundle applicationRestrictions = semEnterpriseDeviceManager.getApplicationRestrictions("com.samsung.android.knox.galaxyai", myUserId);
                if (applicationRestrictions != null) {
                    List.of("key_photo_editor", "key_audio_eraser").forEach(new A(11, (Object) this, (Object) applicationRestrictions));
                }
                Log.d("KnoxRestrictions", "load" + Logger.vt(Integer.valueOf(myUserId), (String) hashSet.stream().map(new i(17)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]")), Long.valueOf(currentTimeMillis)));
            }
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("load failed, e="), "KnoxRestrictions");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Bundle bundle, String str) {
        Bundle bundle2 = bundle.getBundle(str);
        if (bundle2 != null && bundle2.getBoolean("grayout")) {
            this.set.add(str);
        }
    }

    public static KnoxRestrictions of(Context context) {
        return new KnoxRestrictions(context);
    }

    public boolean contains(String str) {
        return this.set.contains(str);
    }
}
