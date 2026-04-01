package com.samsung.android.gallery.support.library.v16;

import android.content.Context;
import android.os.UserManager;
import android.util.Log;
import com.samsung.android.gallery.support.library.v0.system.DexInfo;
import com.samsung.android.gallery.support.library.v12.Sem160ApiCompatImpl;
import com.samsung.android.gallery.support.library.v16.system.DexInfo170;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem170ApiCompatImpl extends Sem160ApiCompatImpl {
    public DexInfo computeDexInfo(Context context) {
        return new DexInfo170().compute(context);
    }

    public boolean isProfileNotSupportSdCard(Context context) {
        try {
            UserManager userManager = (UserManager) context.getSystemService("user");
            if (userManager == null || !userManager.isManagedProfile()) {
                return false;
            }
            return true;
        } catch (Error | Exception e) {
            String str = this.TAG;
            Log.w(str, "isProfileNotSupportSdCard failed e=" + e.getMessage());
            return false;
        }
    }
}
