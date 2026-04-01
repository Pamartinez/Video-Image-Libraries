package com.samsung.android.gallery.app.controller.sharing.request;

import A6.a;
import N3.c;
import Qb.e;
import android.app.Dialog;
import android.net.Uri;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mde.MdeUploadingChecker;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestStreamingVideo extends AbsRequest {
    private Dialog mProgressDialog;

    public RequestStreamingVideo(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    private void hideProgressDialog() {
        try {
            Dialog dialog = this.mProgressDialog;
            if (dialog != null && dialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
        } catch (IllegalArgumentException e) {
            String str = this.TAG;
            Log.she(str, "hideProgressDialog failed. e=" + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestStreamingVideo$1(Integer num, Uri uri, FileItemInterface fileItemInterface) {
        hideProgressDialog();
        if (!MdeResultCode.isSuccess(num.intValue())) {
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, num.intValue());
        } else if (uri != null) {
            Optional.ofNullable(getEventContext()).ifPresent(new c(28, fileItemInterface, uri));
        } else {
            Log.she(this.TAG, "streamingUrl is null.");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestStreamingVideo$2(FileItemInterface fileItemInterface, Integer num, Uri uri) {
        String str = this.TAG;
        Log.sh(str, "requestStreamingVideo result" + Logger.v(num));
        ThreadUtil.postOnUiThread(new a((Object) this, (Object) num, (Object) uri, (Object) fileItemInterface, 17));
    }

    private void requestStreamingVideo() {
        Log.sh(this.TAG, "requestStreamingVideo called");
        FileItemInterface fileItemInterface = (FileItemInterface) this.mParams[1];
        if (!TextUtils.isEmpty(MediaItemMde.getSpaceId(fileItemInterface)) && !MdeUploadingChecker.showToastIfUploading(getContext(), fileItemInterface, this.TAG)) {
            if (MdeSharingHelper.requestShareItem(MediaItemMde.getSpaceId(fileItemInterface), MediaItemMde.getItemId(fileItemInterface), new A9.a(10, this, fileItemInterface)) == -1) {
                Log.she(this.TAG, "requestShareItem call fail.");
            } else {
                ThreadUtil.postOnUiThread(new e(16, this));
            }
        }
    }

    /* access modifiers changed from: private */
    public void showProgressDialog() {
        Dialog dialog = this.mProgressDialog;
        if (dialog != null) {
            if (dialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
            this.mProgressDialog = null;
        }
        AlertDialog create = new ProgressCircleBuilder(BlackboardUtils.readActivity(this.mBlackboard)).setProgressMessage(this.mAppContext.getResources().getString(R.string.processing)).setTextColor(R.color.progress_avd_message_text_color).setLightTheme(true).create();
        this.mProgressDialog = create;
        create.show();
    }

    public int getNetworkErrorStringId() {
        return R.string.check_network_connection_description;
    }

    public void request() {
        requestStreamingVideo();
    }
}
