package com.samsung.android.gallery.app.ui.viewer2.contentviewer;

import G6.a;
import android.content.res.Configuration;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.GroupLoader;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import u7.C0525f;
import u7.C0526g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewerObjectComposite implements IViewerObject, ViewerLifeCycle, FragmentLifeCycle {
    private final StringCompat TAG;
    protected final ActionInvoker mActionInvoker;
    private GroupLoader mGroupLoader;
    private final ContentModel mModel;
    private final AbsViewerHolder mViewHolder;
    private final ArrayList<IViewerObject> mViewerObjectList = new ArrayList<>();

    public ViewerObjectComposite(AbsViewerHolder absViewerHolder, ContentModel contentModel) {
        StringCompat stringCompat = new StringCompat("VuObjCmp@" + Logger.toHexString(hashCode(), 4));
        this.TAG = stringCompat;
        this.mActionInvoker = new ActionInvoker(stringCompat.toString());
        this.mModel = contentModel;
        this.mViewHolder = absViewerHolder;
        addViewerObject(absViewerHolder);
    }

    private void copyMediaItemProperty(MediaItem mediaItem, MediaItem mediaItem2) {
        if (MediaItemUtil.equals(mediaItem, mediaItem2)) {
            mediaItem2.setBroken(mediaItem.isBroken());
            DetailsData of2 = DetailsData.of(mediaItem2);
            DetailsData of3 = DetailsData.of(mediaItem);
            if (mediaItem.isVideo() || mediaItem.isMotionPhoto()) {
                VideoPropData of4 = VideoPropData.of(mediaItem2);
                VideoPropData of5 = VideoPropData.of(mediaItem);
                of4.videoDurationInPlayer = of5.videoDurationInPlayer;
                if (mediaItem.isVideo()) {
                    of4.videoFrameRate = of5.videoFrameRate;
                    of4.instantSlowMoExecutedSection = of5.instantSlowMoExecutedSection;
                }
                if (PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_VIEW_MODE && mediaItem.isMotionPhoto()) {
                    of2.motionPhotoViewMode = of3.motionPhotoViewMode;
                    of2.motionPhotoPlaybacks = of3.motionPhotoPlaybacks;
                }
            }
            of2.dualShotState = of3.dualShotState;
            if (mediaItem.getVideoThumbEndTime() > 0) {
                mediaItem2.setVideoHighlightTime((Long) null, Long.valueOf(mediaItem.getVideoThumbEndTime()));
            }
            Optional.ofNullable(MediaItemMde.getDownloadVideoUri(mediaItem)).ifPresent(new a(mediaItem2, 14));
            Optional.ofNullable(MediaItemMde.getDownloadVideoPath(mediaItem)).ifPresent(new a(mediaItem2, 15));
            Optional.ofNullable(MediaItemMde.getDownloadVideoVerified(mediaItem)).ifPresent(new a(mediaItem2, 16));
        }
    }

    private MediaItem getCurrentMediaItem(GroupLoader.SubItemsInfo subItemsInfo) {
        MediaItem mediaItem = subItemsInfo.mCurrentMediaItem;
        int i2 = subItemsInfo.mCurrentIndex;
        if (i2 < 0 || i2 >= subItemsInfo.mSubItemList.size()) {
            return mediaItem;
        }
        return subItemsInfo.mSubItemList.get(subItemsInfo.mCurrentIndex);
    }

    private void invalidateInternal(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7, long j2) {
        StringCompat stringCompat = this.TAG;
        Log.v(stringCompat, "invalidate " + this.mModel.getRepresentativeItem() + " -> " + mediaItem);
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof ViewerLifeCycle) {
                ((ViewerLifeCycle) next).invalidate(mediaItem, i2, mediaItem2, i7);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invalidate$0(GroupLoader.SubItemsInfo subItemsInfo, int i2, MediaItem mediaItem, int i7, long j2, MediaItem mediaItem2) {
        MediaItem currentMediaItem = getCurrentMediaItem(subItemsInfo);
        if (currentMediaItem != null) {
            invalidateInternal(currentMediaItem, i2, mediaItem, i7, j2);
            this.mGroupLoader.updateModelSubItems(subItemsInfo);
            this.mModel.setRepresentativeMediaItem(mediaItem2);
            return;
        }
        Log.w(this.TAG, "invalidate skip");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invalidate$1(int i2, MediaItem mediaItem, int i7, long j2, MediaItem mediaItem2, GroupLoader.SubItemsInfo subItemsInfo) {
        ThreadUtil.runOnUiThread(new C0526g(this, subItemsInfo, i2, mediaItem, i7, j2, mediaItem2));
    }

    public void addViewerObject(IViewerObject iViewerObject) {
        this.mViewerObjectList.add(iViewerObject);
        iViewerObject.bindContentModel(this.mModel);
        iViewerObject.addActionInvokeListener();
        iViewerObject.attachActionInvoker(this.mActionInvoker);
    }

    public void attachActionInvoker(ActionInvoker actionInvoker) {
        actionInvoker.attach(this.mActionInvoker);
    }

    public void detachActionInvoker() {
        this.mActionInvoker.detachFromParent();
    }

    public ContentModel getModel() {
        return this.mModel;
    }

    public AbsViewerHolder getViewHolder() {
        return this.mViewHolder;
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if ((next instanceof FragmentLifeCycle) && ((FragmentLifeCycle) next).handleBlackboardEvent(eventMessage)) {
                return true;
            }
        }
        return false;
    }

    public void initialize() {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            it.next().initialize();
        }
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        StringCompat stringCompat = this.TAG;
        stringCompat.setTag(this.mModel.getPosition() + ">" + i2);
        copyMediaItemProperty(this.mModel.getMediaItem(), mediaItem);
        GroupLoader groupLoader = this.mGroupLoader;
        if (groupLoader == null) {
            invalidateInternal(mediaItem, i2, mediaItem2, i7, 0);
            this.mModel.setRepresentativeMediaItem(mediaItem);
        } else {
            int i8 = i2;
            MediaItem mediaItem3 = mediaItem2;
            int i10 = i7;
            MediaItem mediaItem4 = mediaItem;
            groupLoader.invalidateSubItems(mediaItem4, i8, mediaItem3, i10, new C0525f(this, i8, mediaItem3, i10, mediaItem4));
        }
        this.mModel.setPosition(i2);
    }

    public void onApplyWindowInsets() {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof FragmentLifeCycle) {
                ((FragmentLifeCycle) next).onApplyWindowInsets();
            }
        }
    }

    public void onBind(MediaItem mediaItem, int i2) {
        this.mModel.set(mediaItem, i2);
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof ViewerLifeCycle) {
                ((ViewerLifeCycle) next).onBind(mediaItem, i2);
            }
        }
        this.TAG.setTag(Integer.valueOf(i2));
    }

    public void onConfigurationChanged(Configuration configuration) {
        Log.d(this.TAG, "onConfigurationChanged");
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof FragmentLifeCycle) {
                ((FragmentLifeCycle) next).onConfigurationChanged(configuration);
            }
        }
    }

    public void onDestroy() {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof FragmentLifeCycle) {
                ((FragmentLifeCycle) next).onDestroy();
            }
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onDestroy" + Logger.vt(currentTimeMillis));
    }

    public void onDump(PrintWriter printWriter, String str) {
        StringBuilder t = C0212a.t(str, "=== ");
        t.append(this.mModel.getViewerName());
        t.append(" Composite  ===");
        Logger.dumpLog(printWriter, t.toString());
        Logger.dumpLog(printWriter, str + "   representativeItem : " + this.mModel.getRepresentativeItem());
        Logger.dumpLog(printWriter, str + "   mediaItem : " + this.mModel.getMediaItem());
        Logger.dumpLog(printWriter, str + "   Position : " + this.mModel.getPosition());
        Logger.dumpLog(printWriter, str + "   SubItemCount : " + this.mModel.getSubMediaItemCount());
        Logger.dumpLog(printWriter, str + "   CurrentIndex : " + this.mModel.getSubItemCurrentIndex());
        Logger.dumpLog(printWriter, str + "   BestItemIndex : " + this.mModel.getBestItemIndex());
        Logger.dumpLog(printWriter, str + "   bitmap : " + Logger.toSimpleString(this.mModel.getBitmap()));
        Logger.dumpLog(printWriter, str + "=== " + this.mModel.getViewerName() + " Sub Composites  ===");
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            StringBuilder t3 = C0212a.t(str, " viewerObject ");
            t3.append(next.getClass().getSimpleName());
            t3.append(" hash = ");
            t3.append(Integer.toHexString(next.hashCode()));
            Logger.dumpLog(printWriter, t3.toString());
            next.onDump(printWriter, str + " + ");
        }
        Logger.dumpLog(printWriter, str + "=== Sub Composites end ===");
    }

    public void onFinalized() {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            it.next().onFinalized();
        }
    }

    public void onInitialized() {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            it.next().onInitialized();
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        Log.d(this.TAG, "onMultiWindowModeChanged");
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof FragmentLifeCycle) {
                ((FragmentLifeCycle) next).onMultiWindowModeChanged(z);
            }
        }
    }

    public void onPageSelected() {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof ViewerLifeCycle) {
                ((ViewerLifeCycle) next).onPageSelected();
            }
        }
    }

    public void onPause() {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof FragmentLifeCycle) {
                ((FragmentLifeCycle) next).onPause();
            }
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onPause" + Logger.vt(currentTimeMillis));
    }

    public void onResolutionChanged() {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof FragmentLifeCycle) {
                ((FragmentLifeCycle) next).onResolutionChanged();
            }
        }
    }

    public void onResume() {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof FragmentLifeCycle) {
                ((FragmentLifeCycle) next).onResume();
            }
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onResume" + Logger.vt(currentTimeMillis));
    }

    public void onStart() {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof FragmentLifeCycle) {
                ((FragmentLifeCycle) next).onStart();
            }
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onStart" + Logger.vt(currentTimeMillis));
    }

    public void onStop() {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof FragmentLifeCycle) {
                ((FragmentLifeCycle) next).onStop();
            }
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onStop" + Logger.vt(currentTimeMillis));
    }

    public void onTableModeChanged(boolean z, int i2) {
        Log.d(this.TAG, "onTableModeChanged");
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof FragmentLifeCycle) {
                ((FragmentLifeCycle) next).onTableModeChanged(z, i2);
            }
        }
    }

    public void onViewAttached() {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof ViewerLifeCycle) {
                ((ViewerLifeCycle) next).onViewAttached();
            }
        }
    }

    public void onViewConfirm() {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof ViewerLifeCycle) {
                ((ViewerLifeCycle) next).onViewConfirm();
            }
        }
    }

    public void onViewDetached() {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof ViewerLifeCycle) {
                ((ViewerLifeCycle) next).onViewDetached();
            }
        }
    }

    public void onViewRecycled() {
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            if (next instanceof ViewerLifeCycle) {
                ((ViewerLifeCycle) next).onViewRecycled();
            }
        }
        this.mModel.recycle();
    }

    public void setGroupLoader(GroupLoader groupLoader) {
        this.mGroupLoader = groupLoader;
    }

    public String toString() {
        return Logger.getSimpleName((Object) this) + "{" + this.mModel + "}";
    }

    public void bindContentModel(ContentModel contentModel) {
    }
}
