package com.samsung.android.gallery.module.mde.executor;

import A9.a;
import D7.g;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateFamilyGroup extends ServiceExecutor {
    public CreateFamilyGroup(Handler handler, Context context, Blackboard blackboard) {
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

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onFailure$1() {
        Utils.showToast(this.mContext.getApplicationContext(), AppResources.getString(R$string.shared_family_album_create_fail));
    }

    public void execute(Bundle bundle) {
        String string = bundle.getString("spaceTitle");
        if (string != null) {
            MdeGroupHelper.requestFamilyGroupCreation(string, new a(1, this, bundle));
        }
    }

    public void onFailure(Integer num, String[] strArr) {
        Log.d(this.TAG, "Failed create family group, unknown error");
        super.onFailure(num, strArr);
        ThreadUtil.runOnUiThread(new g(3, this));
        Blackboard.postEventGlobal(EventMessage.obtain(6015));
    }

    public void onSuccess(String[] strArr, Bundle bundle) {
        requestCreateSpace(strArr[0], strArr[1], bundle.getParcelableArrayList("contentsData"));
    }
}
