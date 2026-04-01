package com.samsung.android.gallery.module.mde.executor;

import Da.f;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import c0.C0086a;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ServiceExecutor {
    protected final String TAG = getClass().getSimpleName();
    protected Blackboard mBlackboard;
    protected Context mContext;
    protected Handler mHandler;

    public ServiceExecutor(Handler handler, Context context, Blackboard blackboard) {
        this.mHandler = handler;
        this.mContext = context;
        this.mBlackboard = blackboard;
    }

    public static ServiceExecutor create(int i2, Handler handler, Context context, Blackboard blackboard) {
        if (i2 == 0) {
            return new CreateGroup(handler, context, blackboard);
        }
        if (i2 == 1) {
            return new CreateGroupForShare(handler, context, blackboard);
        }
        if (i2 == 2) {
            return new CreateFamilyGroup(handler, context, blackboard);
        }
        if (i2 == 3) {
            return new DeleteGroup(handler, context, blackboard);
        }
        if (i2 == 4) {
            return new CreateSpace(handler, context, blackboard);
        }
        if (i2 == 5) {
            return new AddContents(handler, context, blackboard);
        }
        throw new IllegalStateException(C0086a.i(i2, "Illegal request type : "));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showErrorToast$0(String str) {
        Toast.makeText(this.mContext, str, 0).show();
    }

    public abstract void execute(Bundle bundle);

    public void onFailure(Integer num, String[] strArr) {
        MdeAlbumHelper.handleMdeFailResultCode(this.mContext, num.intValue());
    }

    public void requestCreateSpace(String str, String str2, ArrayList<Uri> arrayList) {
        String str3 = this.TAG;
        Log.i(str3, "Created space GID[ " + str + "]");
        Bundle bundle = new Bundle();
        bundle.putString("groupId", str);
        bundle.putString("spaceTitle", str2);
        bundle.putParcelableArrayList("contentsData", arrayList);
        Message message = new Message();
        message.what = 4;
        message.setData(bundle);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    public void sendDestroyServiceMessage() {
        Log.d(this.TAG, "sendDestroyServiceMessage");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(6, Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
        }
    }

    public void showErrorToast(String str) {
        if (str != null && !str.isEmpty()) {
            ThreadUtil.postOnUiThread(new f(6, this, str));
        }
    }
}
