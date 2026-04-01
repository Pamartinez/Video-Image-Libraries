package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import H.a;
import android.net.Uri;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MdeData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.mobileservice.social.share.result.OriginalFile;
import com.samsung.android.sdk.mobileservice.social.share.result.OriginalFileListResult;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareVideoDownloadHandler extends ViewerObject implements FragmentLifeCycle {
    private boolean isNeedToVerify(MediaItem mediaItem) {
        boolean z;
        if (MdeData.of(mediaItem).originalContentPath != null) {
            z = true;
        } else {
            z = false;
        }
        boolean isSharingEditedItemUploading = MediaItemMde.isSharingEditedItemUploading(mediaItem);
        if (mediaItem == null || !z || isSharingEditedItemUploading) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$verify$0(MediaItem mediaItem) {
        if (isNeedToVerify(mediaItem)) {
            Boolean downloadVideoVerified = MediaItemMde.getDownloadVideoVerified(mediaItem);
            boolean verifyDownloadSharedVideo = verifyDownloadSharedVideo(this.mModel.getMediaItem());
            Log.d(this.TAG, "verifyDownloadSharedVideo", downloadVideoVerified, Boolean.valueOf(verifyDownloadSharedVideo));
            if (downloadVideoVerified == null || downloadVideoVerified.booleanValue() != verifyDownloadSharedVideo) {
                this.mActionInvoker.invoke(ViewerAction.DOWNLOADED_SHARE_VIDEO_VERIFY_UPDATED, new Object[0]);
            }
        }
    }

    private void verify(MediaItem mediaItem) {
        if (isNeedToVerify(mediaItem)) {
            this.mThread.runOnBgThread(new a(9, this, mediaItem));
        }
    }

    private boolean verifyDownloadSharedVideo(MediaItem mediaItem) {
        boolean verifyDownloadSharedVideoInternal = verifyDownloadSharedVideoInternal(mediaItem);
        if (!verifyDownloadSharedVideoInternal) {
            MediaItemMde.setDownloadSharedVideoInfo(mediaItem, (OriginalFile) null);
        }
        return verifyDownloadSharedVideoInternal;
    }

    private boolean verifyDownloadSharedVideoInternal(MediaItem mediaItem) {
        if (Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY)) {
            ArrayList arrayList = new ArrayList();
            String str = MdeData.of(mediaItem).itemHash;
            if (str != null) {
                arrayList.add(str);
                OriginalFileListResult verifiedOriginalFileList = MdeSharingService.getInstance().getVerifiedOriginalFileList(arrayList);
                if (verifiedOriginalFileList != null && verifiedOriginalFileList.getStatus().getCode() == 1) {
                    List<OriginalFile> resultList = verifiedOriginalFileList.getResultList();
                    StringCompat stringCompat = this.TAG;
                    Log.sh(stringCompat, "fileList size: " + resultList.size());
                    int i2 = 0;
                    while (i2 < resultList.size()) {
                        OriginalFile originalFile = resultList.get(i2);
                        if (originalFile.getFilePath() == null || originalFile.getContentUri() == Uri.EMPTY) {
                            i2++;
                        } else {
                            MediaItemMde.setDownloadSharedVideoInfo(mediaItem, originalFile);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        verify(mediaItem);
    }

    public void onResume() {
        verify(this.mModel.getMediaItem());
    }

    public void onViewAttached() {
        super.onViewAttached();
        verify(this.mModel.getMediaItem());
    }
}
