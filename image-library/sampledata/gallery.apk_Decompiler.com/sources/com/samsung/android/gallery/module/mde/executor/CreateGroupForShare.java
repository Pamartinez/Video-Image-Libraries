package com.samsung.android.gallery.module.mde.executor;

import A9.a;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateGroupForShare extends ServiceExecutor {
    public CreateGroupForShare(Handler handler, Context context, Blackboard blackboard) {
        super(handler, context, blackboard);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$0(Bundle bundle, Integer num, String[] strArr) {
        if (MdeResultCode.isSuccess(num.intValue())) {
            onSuccess(strArr, bundle);
        } else {
            onFailure(num, strArr);
        }
    }

    public void execute(Bundle bundle) {
        String string = bundle.getString("spaceTitle");
        if (string != null) {
            MdeGroupHelper.requestGroupCreationForShare(string, new a(3, this, bundle));
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
        String str3 = strArr[2];
        requestCreateSpace(str, str2, bundle.getParcelableArrayList("contentsData"));
        if (!TextUtils.isEmpty(str3)) {
            MdeGroupHelper.shareGroupInvitationLink(this.mContext.getApplicationContext(), str2, str3);
        }
    }
}
