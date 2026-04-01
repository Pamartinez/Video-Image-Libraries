package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader;

import A4.C0371f;
import A4.O;
import A9.b;
import B7.d;
import Bb.m;
import D7.a;
import android.graphics.Bitmap;
import android.util.Pair;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.viewer.LastPreviewData;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.type.ShotModeType;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.abstraction.VideoBackupInfo;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PreviewLoader extends ViewerObject implements FragmentLifeCycle {
    private AtomicBoolean mRecycled = new AtomicBoolean(false);
    private boolean mSkipPreload = false;

    private Bitmap getBrokenBitmap(MediaItem mediaItem) {
        return ThumbnailLoader.getInstance().getReplacedThumbnail(this.mModel.getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    private Bitmap getRoundedBitmap(Bitmap bitmap) {
        return BitmapUtils.getRoundedCornerBitmap(bitmap, ((float) Math.max(bitmap.getWidth(), bitmap.getHeight())) * 0.028f);
    }

    private boolean handleVideoBackupInfo(MediaItem mediaItem, String str) {
        VideoBackupInfo videoBackupInfo = (VideoBackupInfo) this.mModel.getBlackboard().read(str);
        if (videoBackupInfo == null || videoBackupInfo.hash != mediaItem.getSimpleHashCode()) {
            return false;
        }
        this.mModel.getContainerModel().setLastPreviewData(new LastPreviewData(ThumbnailLoader.getInstance().getMemCache(mediaItem, ThumbKind.MEDIUM_KIND), mediaItem, this.mModel.getPosition(), this.mModel.getSubItemCurrentIndex()), "vr");
        Optional.ofNullable(videoBackupInfo.preview).ifPresent(new C0371f((Object) this, mediaItem, (Object) videoBackupInfo, 1));
        return true;
    }

    private boolean isLiveFocusOnSingleTaken(MediaItem mediaItem) {
        if (!mediaItem.isSingleTakenShot() || !ShotModeType.isLiveFocus(mediaItem.getSefFileType())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mSkipPreload = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleVideoBackupInfo$1(MediaItem mediaItem, VideoBackupInfo videoBackupInfo, Bitmap bitmap) {
        setPreviewBmp(mediaItem, videoBackupInfo.preview);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadThumbnail$2(AtomicBoolean atomicBoolean, Bitmap bitmap, MediaItem mediaItem, int i2) {
        if (!atomicBoolean.get()) {
            this.mModel.getContainerModel().setLastPreviewData(new LastPreviewData(bitmap, mediaItem, this.mModel.getPosition(), this.mModel.getSubItemCurrentIndex()), "pla");
            setPreviewBmp(mediaItem, bitmap);
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.local(stringCompat, "interrupted " + i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadThumbnail$3(int i2, AtomicBoolean atomicBoolean, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        boolean z;
        StringCompat stringCompat = this.TAG;
        Integer valueOf = Integer.valueOf(i2);
        if (bitmap != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d(stringCompat, "Preview load async", valueOf, Boolean.valueOf(z));
        if (bitmap != null) {
            this.mThread.runOnUiThread(new a(this, atomicBoolean, bitmap, mediaItem, i2, 2));
            return;
        }
        MediaItem mediaItem2 = mediaItem;
        Log.e(this.TAG, "null preview bitmap. set broken : " + MediaItemUtil.getDebugLog(mediaItem2));
        mediaItem2.setBroken(true);
        loadBroken(mediaItem2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPreviewBmp$4(Bitmap bitmap, MediaItem mediaItem) {
        setPreviewBmpInternal(getRoundedBitmap(bitmap), mediaItem);
    }

    private void loadBroken(MediaItem mediaItem) {
        Bitmap brokenBitmap = getBrokenBitmap(mediaItem);
        if (mediaItem != null) {
            mediaItem.setSize(brokenBitmap.getWidth(), brokenBitmap.getHeight());
        }
        setPreviewBmp(mediaItem, brokenBitmap);
    }

    private boolean loadThumbnail(MediaItem mediaItem, int i2) {
        AtomicBoolean atomicBoolean = this.mRecycled;
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        MediaItem mediaItem2 = mediaItem;
        m mVar = new m((Object) this, i2, (Object) atomicBoolean, (Object) mediaItem2, 1);
        Objects.requireNonNull(atomicBoolean);
        Bitmap loadPreview = instance.loadPreview(mediaItem2, mVar, new O(22, atomicBoolean));
        if (loadPreview == null) {
            return false;
        }
        this.mModel.getContainerModel().setLastPreviewData(new LastPreviewData(loadPreview, mediaItem2, this.mModel.getPosition(), this.mModel.getSubItemCurrentIndex()), "pl");
        setPreviewBmp(mediaItem2, loadPreview);
        return true;
    }

    private boolean setDragExitPreview(MediaItem mediaItem) {
        Pair pair;
        if (mediaItem.isVideo()) {
            return handleVideoBackupInfo(mediaItem, "data://video_viewer_return_info");
        }
        if (!mediaItem.isImage() || (pair = (Pair) getBlackboard().read("data://image_viewer_return_info", null)) == null || ((Integer) pair.first).intValue() != mediaItem.getSimpleHashCode()) {
            return false;
        }
        getBlackboard().erase("data://image_viewer_return_info");
        setPreviewBmp(mediaItem, (Bitmap) pair.second);
        return true;
    }

    private void setPostLastPreviewCandidate() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            this.mModel.getContainerModel().setLastPreviewData(new LastPreviewData(this.mModel.getBitmap(), mediaItem, this.mModel.getPosition()).setPostCandidate(), "plp");
        }
    }

    private void setPreviewBmp(MediaItem mediaItem, Bitmap bitmap) {
        if (!this.mModel.getContainerModel().isFlipCoverGallery()) {
            setPreviewBmpInternal(bitmap, mediaItem);
        } else if (this.mModel.isFirstView()) {
            setPreviewBmpInternal(getRoundedBitmap(bitmap), mediaItem);
        } else {
            this.mThread.runOnBgThread(new b(this, bitmap, mediaItem, 10));
        }
    }

    private void setPreviewBmpInternal(Bitmap bitmap, MediaItem mediaItem) {
        if (this.mModel.setPreViewBmp(bitmap, mediaItem)) {
            this.mActionInvoker.invoke(ViewerAction.PREVIEW_LOADED, bitmap, mediaItem);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.UPDATE_REMASTER_STATUS, new d(2, this));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        MediaItem mediaItem;
        if (eventMessage.what != 3056 || (mediaItem = this.mModel.getMediaItem()) == null || !mediaItem.getThumbCacheKey().equals(eventMessage.obj)) {
            return false;
        }
        setPostLastPreviewCandidate();
        return true;
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        LastPreviewData lastPreviewData;
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        MediaItem mediaItem3 = this.mModel.getMediaItem();
        if ((mediaItem3 != null && mediaItem3.isSingleTakenPostProcessing()) || MediaItemUtil.equalsBitmap(mediaItem3, mediaItem) || this.mModel.getContainerModel().getStateHelper().isGroupItemLoading() || isLiveFocusOnSingleTaken(mediaItem)) {
            return;
        }
        if (mediaItem3 == null || !mediaItem3.isSingleTakenShot() || !mediaItem.isSingleTakenShot()) {
            this.mRecycled.set(false);
            if (mediaItem3 != null) {
                ThumbnailLoader.getInstance().removeMemCache(mediaItem3);
                if (mediaItem3.getDateModified() == mediaItem.getDateModified() && mediaItem3.getFileSize() != mediaItem.getFileSize()) {
                    ThumbnailLoader.getInstance().removeDiskCache(mediaItem3);
                }
                if (mediaItem3.isPostProcessing() && (lastPreviewData = this.mModel.getContainerModel().getLastPreviewData()) != null && !lastPreviewData.isPostCandidate()) {
                    this.mModel.getContainerModel().clearLastPreviewData();
                }
            }
            if (!mediaItem.isTransparent()) {
                preloadThumbnail(mediaItem, i2);
            }
        }
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        this.mRecycled = new AtomicBoolean(false);
        preloadThumbnail(this.mModel.getMediaItem(), this.mModel.getPosition());
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mRecycled.set(true);
        this.mSkipPreload = false;
    }

    public void preloadThumbnail(MediaItem mediaItem, int i2) {
        boolean z;
        boolean z3;
        boolean z7 = false;
        if (this.mSkipPreload) {
            Log.v(this.TAG, "Preview skip by flag");
            this.mSkipPreload = false;
        } else if (mediaItem == null || mediaItem.isBroken()) {
            loadBroken(mediaItem);
        } else if (setDragExitPreview(mediaItem) || handleVideoBackupInfo(mediaItem, "data://viewer_group_panel_video_current_info")) {
        } else {
            if (this.mModel.isGroupPanelViewer() || getBlackboard().read(MediaItemUtil.getViewerBitmapKey(mediaItem)) == null) {
                LastPreviewData lastPreviewData = this.mModel.getContainerModel().getLastPreviewData();
                boolean z9 = true;
                if (lastPreviewData == null || lastPreviewData.getBitmap() == null || lastPreviewData.getPosition() != this.mModel.getPosition()) {
                    z = false;
                } else {
                    z = true;
                }
                if (mediaItem.isGroupShot()) {
                    if (!z || lastPreviewData.getGroupPosition() != this.mModel.getSubItemCurrentIndex()) {
                        z = false;
                    } else {
                        z = true;
                    }
                }
                if (this.mModel.getBitmap() == null || !z || !MediaItemUtil.equalsPpp(lastPreviewData.getMediaItem(), mediaItem)) {
                    if (!z || !lastPreviewData.isPostCandidate()) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    if (!z || VideoPropData.of(mediaItem).videoResumePos == null) {
                        z9 = false;
                    }
                    if (!z3 || lastPreviewData.getMediaItem().getFileId() == mediaItem.getFileId()) {
                        z7 = z3;
                    } else {
                        Log.w(this.TAG, "Preview candidate drop caused by file change");
                    }
                    if (z7) {
                        Log.v(this.TAG, "Preview reuse candidate lastData", Integer.valueOf(i2), lastPreviewData.getBitmap());
                    } else if (z9) {
                        Log.v(this.TAG, "Preview reuse from camera", Integer.valueOf(i2), lastPreviewData.getBitmap());
                    } else if (loadThumbnail(mediaItem, i2)) {
                        return;
                    }
                    if (z) {
                        if (lastPreviewData.isPostCandidate() && !mediaItem.isPostProcessing() && lastPreviewData.getMediaItem().getFileId() == mediaItem.getFileId()) {
                            lastPreviewData.setPppTargetPath(mediaItem.getPath());
                        }
                        this.mActionInvoker.invoke(ViewerAction.LAST_PREVIEW_DATA, lastPreviewData);
                        if (MediaItemUtil.isDualShot(mediaItem) && !MediaItemUtil.isDualShot(lastPreviewData.getMediaItem())) {
                            this.mModel.getContainerModel().clearLastPreviewData();
                            Log.w(this.TAG, "clear non-dualShot preview");
                            return;
                        }
                        return;
                    }
                    Log.e((CharSequence) this.TAG, "blinking. preview not ready", Integer.valueOf(i2), mediaItem);
                    return;
                }
                Log.v(this.TAG, "sameFile : ", Long.valueOf(mediaItem.getFileId()));
                return;
            }
            Log.v(this.TAG, "Preview skip by original");
        }
    }
}
