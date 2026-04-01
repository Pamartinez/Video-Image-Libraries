package com.samsung.android.gallery.module.mde.executor;

import D7.g;
import E9.b;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateSpace extends ServiceExecutor {
    private boolean mIsFamilySharedAlbum;

    public CreateSpace(Handler handler, Context context, Blackboard blackboard) {
        super(handler, context, blackboard);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$0(String str, Bundle bundle, Integer num, String str2) {
        boolean z;
        int i2;
        String str3 = this.TAG;
        Log.sh(str3, "requestCreateSpace result [" + num + "][" + str2 + "]");
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM) || !MdeGroupHelper.isSAFamilyGroup(str)) {
            z = false;
        } else {
            z = true;
        }
        this.mIsFamilySharedAlbum = z;
        boolean isSuccess = MdeResultCode.isSuccess(num.intValue());
        if (isSuccess) {
            onSuccess(bundle, str, str2);
        } else {
            onFailure(str, num, str2);
        }
        if (this.mIsFamilySharedAlbum) {
            if (isSuccess) {
                i2 = 6014;
            } else {
                i2 = 6015;
            }
            Blackboard.postEventGlobal(EventMessage.obtain(i2));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onFailure$1() {
        int i2;
        Context applicationContext = this.mContext.getApplicationContext();
        if (this.mIsFamilySharedAlbum) {
            i2 = R$string.shared_family_album_create_fail;
        } else {
            i2 = R$string.shared_album_create_fail;
        }
        Utils.showToast(applicationContext, AppResources.getString(i2));
    }

    private void requestAddContents(String str, String str2, ArrayList<Uri> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putString("groupId", str);
        bundle.putString("spaceId", str2);
        bundle.putParcelableArrayList("contentsData", arrayList);
        Message message = new Message();
        message.what = 5;
        message.setData(bundle);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    private void requestDeleteGroup(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("groupId", str);
        Message message = new Message();
        message.what = 3;
        message.setData(bundle);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    public void execute(Bundle bundle) {
        String string = bundle.getString("groupId");
        MdeSharingHelper.requestShare(bundle.getString("spaceTitle"), string, (String) this.mBlackboard.pop("data://user/storyInfo", null), new b(this, string, bundle, 0));
    }

    public void onFailure(String str, Integer num, String str2) {
        if (MdeResultCode.isTaskCanceled(str2)) {
            sendDestroyServiceMessage();
        } else if (!MdeResultCode.isSharedAlbumAlreadyExist(num.intValue())) {
            ThreadUtil.runOnUiThread(new g(4, this));
        } else if (this.mIsFamilySharedAlbum) {
            Log.sh(this.TAG, "Fail to create family album but it will be synced.");
        } else {
            showErrorToast(AppResources.getString(R$string.could_not_create_shared_album_due_to_share_space_already_exists_error));
            requestDeleteGroup(str);
        }
    }

    public void onSuccess(Bundle bundle, String str, String str2) {
        if (this.mIsFamilySharedAlbum) {
            SamsungAccountManager.getInstance().loadFamilyMemberType(this.mContext.getApplicationContext(), true);
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("contentsData");
        if (parcelableArrayList == null || parcelableArrayList.isEmpty()) {
            sendDestroyServiceMessage();
        } else {
            requestAddContents(str, str2, bundle.getParcelableArrayList("contentsData"));
        }
    }
}
