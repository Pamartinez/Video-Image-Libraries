package com.samsung.android.gallery.app.ui.viewer2.menu;

import A.a;
import A4.C0385u;
import O3.y;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.DownloadCmd;
import com.samsung.android.gallery.app.controller.internals.SaveAsCopyCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.controller.story.SaveNotifiedContentCmd;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadMenuItem extends ViewerMenuItem {
    public DownloadMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.download);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveTempFile$1() {
        Utils.showToast(this.mEventContext.getContext(), (int) R.string.toast_image_saved);
        this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(3014));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0() {
        return MediaItemMde.isSharingEditedItemUploading(this.mEventContext.getCurrentItem());
    }

    private void saveTempFile(MediaItem mediaItem) {
        String path = mediaItem.getPath();
        if (path == null) {
            Log.e(this.TAG, "saveTempFile failed. null source");
            return;
        }
        String str = StorageInfo.getDefault().pictures + File.separator + FileUtils.getNameFromPath(path);
        if (FileUtils.move(path, str)) {
            MediaScanner.scanFile(str, new y(10, this));
        } else {
            a.u("fail move : ", str, this.TAG);
        }
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "Download Menu Select failed: null item");
            return false;
        } else if (ViewerMenuItem.isNotificationsData(currentItem)) {
            new SaveNotifiedContentCmd().execute(this.mEventContext, currentItem);
            this.mEventContext.getBlackboard().postBroadcastEvent(EventMessage.obtain(1030));
            return true;
        } else if (currentItem.isSharing()) {
            new RequestSharedAlbumCmd().execute(this.mEventContext, RequestCmdType.REQUEST_DOWNLOAD_CONTENTS, Collections.singletonList(currentItem));
            return true;
        } else if (FileUtils.isInExternalCacheDir(currentItem.getPath())) {
            saveTempFile(currentItem);
            return true;
        } else if (DynamicViewData.of(currentItem).dynamicViewPlayInfo != null || MediaItemUtil.isHighLightClip(currentItem) || MediaItemUtil.isSuperSlowClip(currentItem)) {
            executeMediaCaptureCmd(currentItem, ConvertingUsageType.NONE);
            return true;
        } else if (this.mEventContext.getLocationKey() != null && (LocationKey.isAllDayTimeLapse(this.mEventContext.getLocationKey()) || LocationKey.isLongExposure(this.mEventContext.getLocationKey()))) {
            new SaveAsCopyCmd().execute(this.mEventContext, currentItem);
            return true;
        } else if (!currentItem.isLocal() || !currentItem.isLogVideo()) {
            new DownloadCmd().execute(this.mEventContext, new MediaItem[]{currentItem});
            return true;
        } else {
            executeMediaCaptureCmd(currentItem, ConvertingUsageType.NONE);
            return true;
        }
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_save).setFastOptionMenu().setShowAsActionFlag(2).setDisabledDimCondition(new C0385u(10, this)).include("location://sharing/albums/fileList").validate(ViewerMenuItem.IS_NOT_BROKEN);
    }

    public DownloadMenuItem(EventContext eventContext, ActionInvoker actionInvoker, int i2) {
        super(eventContext, actionInvoker, i2);
    }
}
