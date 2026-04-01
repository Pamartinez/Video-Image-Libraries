package com.samsung.android.gallery.module.mde.executor;

import A9.a;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateGroup extends ServiceExecutor {
    public CreateGroup(Handler handler, Context context, Blackboard blackboard) {
        super(handler, context, blackboard);
    }

    private static int getInvitationType(HashMap<Integer, Object> hashMap, int i2) {
        if (i2 == 0) {
            return -1;
        }
        return ((Integer) hashMap.keySet().toArray()[0]).intValue();
    }

    private static boolean isInvitationRequestTypeGroup(HashMap<Integer, Object> hashMap, int i2) {
        if (i2 != 103 || hashMap.get(Integer.valueOf(i2)) == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestLocalGroupCreation$0(Bundle bundle, Integer num, String[] strArr) {
        if (MdeResultCode.isSuccess(num.intValue())) {
            onSuccess(strArr, bundle);
        } else {
            onFailure(num, strArr);
        }
    }

    private void requestCreateSpace(Bundle bundle, String str, HashMap<Integer, Object> hashMap, int i2) {
        requestCreateSpace((String) ((ArrayList) ((Object[]) hashMap.get(Integer.valueOf(i2)))[0]).get(0), str, bundle.getParcelableArrayList("contentsData"));
    }

    private void requestLocalGroupCreation(Bundle bundle, String str, HashMap<Integer, Object> hashMap, int i2) {
        MdeGroupHelper.requestLocalGroupCreation(str, i2, hashMap, new a(2, this, bundle));
    }

    public void execute(Bundle bundle) {
        String string = bundle.getString("spaceTitle");
        Serializable serializable = bundle.getSerializable("invitationRequestData");
        if (string != null && serializable != null) {
            HashMap hashMap = (HashMap) serializable;
            int invitationType = getInvitationType(hashMap, hashMap.keySet().size());
            if (isInvitationRequestTypeGroup(hashMap, invitationType)) {
                requestCreateSpace(bundle, string, hashMap, invitationType);
            } else {
                requestLocalGroupCreation(bundle, string, hashMap, invitationType);
            }
        }
    }

    public void onFailure(Integer num, String[] strArr) {
        showErrorToast(strArr[2]);
        Log.d(this.TAG, "Failed create group, unknown error");
        super.onFailure(num, strArr);
    }

    public void onSuccess(String[] strArr, Bundle bundle) {
        String str = strArr[0];
        String str2 = strArr[1];
        showErrorToast(strArr[2]);
        requestCreateSpace(str, str2, bundle.getParcelableArrayList("contentsData"));
    }
}
