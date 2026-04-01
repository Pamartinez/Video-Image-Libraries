package com.samsung.android.gallery.module.mde.executor;

import A4.C0383s;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteGroup extends ServiceExecutor {
    public DeleteGroup(Handler handler, Context context, Blackboard blackboard) {
        super(handler, context, blackboard);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$0(int i2) {
        if (MdeResultCode.isSuccess(i2)) {
            onSuccess((String[]) null, (Bundle) null);
        } else {
            onFailure(Integer.valueOf(i2), (String[]) null);
        }
    }

    public void execute(Bundle bundle) {
        MdeGroupHelper.requestLocalGroupDeletion(bundle.getString("groupId"), new C0383s(1, this));
    }

    public void onSuccess(String[] strArr, Bundle bundle) {
        sendDestroyServiceMessage();
    }
}
