package com.samsung.android.gallery.app.controller.sharing.request;

import A.a;
import Ba.h;
import android.database.Cursor;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestFamilyQuota extends RequestMyQuota {
    public RequestFamilyQuota(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    private long getTrashUsage() {
        Cursor familySharedTrashStorage;
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH)) {
            return 0;
        }
        try {
            familySharedTrashStorage = new MdeDatabase().getFamilySharedTrashStorage();
            if (familySharedTrashStorage != null) {
                if (familySharedTrashStorage.moveToFirst()) {
                    long j2 = familySharedTrashStorage.getLong(0);
                    familySharedTrashStorage.close();
                    return j2;
                }
            }
            if (familySharedTrashStorage == null) {
                return 0;
            }
            familySharedTrashStorage.close();
            return 0;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getTrashUsage failed : "), this.TAG);
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestQuota$0(Long l, Long l8) {
        long trashUsage = getTrashUsage();
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("requestQuota[Family] : ");
        sb2.append(Logger.getEncodedString("totalUsage [" + l + "] trashUsage [" + trashUsage + "]] limit [" + l8));
        Log.sh(str, sb2.toString());
        this.mBlackboard.post("command://SharingUpdateFamilyQuota", new Object[]{l, l8, Long.valueOf(trashUsage)});
    }

    public void requestQuota() {
        Log.sh(this.TAG, "requestQuota[Family] called");
        MdeSharingHelper.getFamilyQuota(new h(13, this));
    }
}
