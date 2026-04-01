package com.samsung.android.gallery.app.controller.sharing.request;

import Qb.c;
import T3.e;
import android.app.Dialog;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeServiceString;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mde.MdeUploadingChecker;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestDownloadForPlayInSharing extends AbsRequest {
    private boolean mIgnoreDownload;
    private Dialog mProgressDialog;

    public RequestDownloadForPlayInSharing(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    private void ignoreDownload() {
        Optional.ofNullable(this.mProgressDialog).ifPresent(new e(0));
        this.mIgnoreDownload = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDownloadContents$0(ContentDownloadResult contentDownloadResult) {
        try {
            int code = contentDownloadResult.getStatus().getCode();
            String message = contentDownloadResult.getStatus().getMessage();
            String str = this.TAG;
            Log.sh(str, "requestDownloadContents result" + Logger.v(Integer.valueOf(code), message));
            ignoreDownload();
            if (MdeResultCode.isSuccess(code)) {
                try {
                    FileItemInterface fileItemInterface = getMdeItemList().get(0);
                    if (Features.isEnabled(Features.SUPPORT_HIDDEN_PATH_IN_SEMS_SHARE_DB)) {
                        ContentDownloadResult.DownloadedContent downloadedContent = contentDownloadResult.getSuccessList().get(0);
                        MediaItemMde.setHiddenFilePath(fileItemInterface, downloadedContent.getFileUri().getPath());
                        MediaItemMde.setHiddenContentUri(fileItemInterface, downloadedContent.getContentUri());
                    }
                    this.mBlackboard.postEvent(EventMessage.obtain(3050, fileItemInterface));
                } catch (Exception e) {
                    Log.she(this.TAG, e.toString());
                }
            } else if (!MdeResultCode.isTaskCanceled(message)) {
                MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, code, getUnknownErrorStringId());
            }
        } catch (IllegalArgumentException e7) {
            String str2 = this.TAG;
            Log.she(str2, "requestDownloadContents failed e=" + e7.getMessage());
        }
    }

    private void requestDownloadContents() {
        List<FileItemInterface> mdeItemList = getMdeItemList();
        if (mdeItemList == null || mdeItemList.isEmpty() || mdeItemList.get(0) == null) {
            Log.she(this.TAG, "requestDownloadContents failed. no items");
            ignoreDownload();
        } else if (MdeUploadingChecker.showToastIfUploading(getContext(), mdeItemList.get(0), this.TAG)) {
            ignoreDownload();
        } else {
            ArrayList arrayList = new ArrayList();
            String str = null;
            for (FileItemInterface next : mdeItemList) {
                if (next != null) {
                    if (str == null) {
                        str = MediaItemMde.getSpaceId(next);
                    }
                    arrayList.add(MediaItemMde.getItemId(next));
                }
            }
            if (str == null) {
                Log.she(this.TAG, "requestDownloadContents failed. null spaceId");
                ignoreDownload();
                return;
            }
            Log.sh(this.TAG, "requestDownloadContents");
            MdeSharingHelper.requestItemDownloadToHiddenFolder(str, arrayList, new c(20, this));
        }
    }

    public List<FileItemInterface> getMdeItemList() {
        return (List) this.mParams[1];
    }

    public int getNetworkErrorStringId() {
        return MdeServiceString.getDownloadNetworkError(getItemTypeWithMediaList());
    }

    public int getUnknownErrorStringId() {
        return MdeServiceString.getDownloadUnknownError(getItemTypeWithMediaList());
    }

    public void onPostExecute(boolean z) {
        if (z) {
            this.mBlackboard.postEvent(EventMessage.obtain(1003));
        }
        if (!this.mIgnoreDownload) {
            AlertDialog create = new ProgressCircleBuilder(getEventContext().getActivity()).setProgressMessage(this.mAppContext.getString(R.string.downloading_image)).create();
            this.mProgressDialog = create;
            create.show();
        }
    }

    public void request() {
        requestDownloadContents();
    }
}
