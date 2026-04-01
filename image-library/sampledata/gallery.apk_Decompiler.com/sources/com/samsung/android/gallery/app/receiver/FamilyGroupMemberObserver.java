package com.samsung.android.gallery.app.receiver;

import A.a;
import android.content.Context;
import android.database.ContentObserver;
import com.samsung.android.gallery.module.account.FamilyGroupHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FamilyGroupMemberObserver extends ContentObserver {
    public FamilyGroupMemberObserver() {
        super(ThreadUtil.createMainThreadHandler());
    }

    public void onChange(boolean z) {
        Log.d("FamilyGroupMemberObserver", "onChange");
        Blackboard.getApplicationInstance().post("global://sharing/member/infoChanged", (Object) null);
    }

    public void registerObserver(Context context) {
        try {
            context.getContentResolver().registerContentObserver(FamilyGroupHelper.FAMILY_GROUP_MEMBER_INFO_URI, true, this);
            Log.d("FamilyGroupMemberObserver", "registerObserver " + this);
        } catch (Exception e) {
            Log.e("FamilyGroupMemberObserver", "registerObserver failed e=" + e.getMessage());
            unregisterObserver(context);
        }
    }

    public void unregisterObserver(Context context) {
        try {
            context.getContentResolver().unregisterContentObserver(this);
        } catch (Exception e) {
            a.s(e, new StringBuilder("unregisterObserver failed e="), "FamilyGroupMemberObserver");
        }
    }
}
