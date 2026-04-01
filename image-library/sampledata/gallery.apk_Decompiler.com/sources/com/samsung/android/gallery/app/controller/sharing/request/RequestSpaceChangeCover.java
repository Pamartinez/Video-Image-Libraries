package com.samsung.android.gallery.app.controller.sharing.request;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mde.MdeUploadingChecker;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RequestSpaceChangeCover extends AbsRequest {
    private String mCoverRectRatio;
    private MediaItem mMediaItem;

    public RequestSpaceChangeCover(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
        this.mMediaItem = objArr[1];
        this.mCoverRectRatio = objArr[2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestChangeSpaceCover$0(Integer num) {
        String str = this.TAG;
        Log.sh(str, "requestChangeSpaceCover result" + Logger.v(num));
        if (!MdeResultCode.isSuccess(num.intValue())) {
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, num.intValue());
        }
    }

    private void requestChangeSpaceCover() {
        Log.sh(this.TAG, "requestChangeSpaceCover called");
        if (!MdeUploadingChecker.showToastIfUploading(getContext(), (FileItemInterface) this.mMediaItem, this.TAG)) {
            MdeSharingHelper.changeSpaceCover(this.mMediaItem, this.mCoverRectRatio, new c(this));
        }
    }

    public void request() {
        requestChangeSpaceCover();
    }
}
