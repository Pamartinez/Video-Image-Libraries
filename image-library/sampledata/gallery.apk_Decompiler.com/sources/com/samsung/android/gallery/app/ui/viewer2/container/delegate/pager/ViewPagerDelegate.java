package com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager;

import A.a;
import B8.j;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerAdapter;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.pagetransformer.MaskingTransformer;
import com.samsung.android.gallery.app.ui.viewer2.container.pagetransformer.ViewerPage2TransformerComposition;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MutableValue;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsupportedApiException;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.PrintWriter;
import java.util.function.Function;
import java.util.function.Predicate;
import n5.e;
import q7.C0509a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerDelegate extends AbsVuDelegate<IVuContainerView> {
    static final Handler mMainHandler = new Handler(Looper.getMainLooper());
    private final int ITEM_NOT_EXIST = -1;
    private final MutableValue<Boolean> heavyLog = new MutableValue<>(Boolean.FALSE);
    private MediaData mMediaData;
    private final ViewPager2.OnPageChangeCallback mPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onPageSelected$0(int i2) {
            if (!((IVuContainerView) ViewPagerDelegate.this.mView).isDestroyed()) {
                lambda$onPageSelectedInternal$1(i2);
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: onPageSelectedInternal */
        public void lambda$onPageSelectedInternal$1(int i2) {
            if (((IVuContainerView) ViewPagerDelegate.this.mView).isDestroyed() || !((IVuContainerView) ViewPagerDelegate.this.mView).isActivityResumed() || !((IVuContainerView) ViewPagerDelegate.this.mView).isViewResumed()) {
                Log.e((CharSequence) ViewPagerDelegate.this.TAG, C0212a.j(i2, "VP[", "] onPageSelected but view is not activated"), Boolean.valueOf(((IVuContainerView) ViewPagerDelegate.this.mView).isDestroyed()), Boolean.valueOf(((IVuContainerView) ViewPagerDelegate.this.mView).isActivityResumed()), Boolean.valueOf(((IVuContainerView) ViewPagerDelegate.this.mView).isViewResumed()));
                return;
            }
            ViewerObjectComposite findContentViewer = ViewPagerDelegate.this.mViewPagerAdapter.findContentViewer(i2);
            if (findContentViewer == null) {
                String access$800 = ViewPagerDelegate.this.TAG;
                Log.i(access$800, "onPageSelected : NULL ret pos = " + i2);
                Handler handler = ViewPagerDelegate.mMainHandler;
                handler.removeCallbacksAndMessages((Object) null);
                handler.postDelayed(new a(this, i2, 1), 10);
            } else if (findContentViewer.getModel().isViewConfirmed()) {
                Log.d(ViewPagerDelegate.this.TAG, "onPageSelectedInternal() but already viewConfirmed");
            } else {
                findContentViewer.onPageSelected();
            }
        }

        public void onPageScrollStateChanged(int i2) {
            if (i2 == 0) {
                ViewPagerDelegate.this.mActionInvoker.invoke(ViewerAction.VIEW_PAGER_PAGE_SCROLL_DONE, new Object[0]);
            }
        }

        public void onPageScrolled(int i2, float f, int i7) {
            if (!ViewPagerDelegate.this.isForceSwipe(i2)) {
                ViewPagerDelegate.this.mActionInvoker.invoke(ViewerAction.VIEW_PAGER_PAGE_SCROLLED, Integer.valueOf(i2), Float.valueOf(f), Integer.valueOf(i7));
            }
        }

        public void onPageSelected(int i2) {
            Handler handler = ViewPagerDelegate.mMainHandler;
            handler.removeCallbacksAndMessages((Object) null);
            if (ViewPagerDelegate.this.mViewPager.getScrollState() == 0) {
                handler.postDelayed(new a(this, i2, 0), 50);
            } else {
                lambda$onPageSelectedInternal$1(i2);
            }
        }
    };
    /* access modifiers changed from: private */
    public ViewPager2 mViewPager;
    /* access modifiers changed from: private */
    public VuContainerAdapter mViewPagerAdapter;

    public ViewPagerDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void blockHoverScrollEnabled() {
        View childAt = this.mViewPager.getChildAt(0);
        if (childAt instanceof RecyclerView) {
            ((RecyclerView) childAt).seslSetHoverScrollEnabled(false);
        }
    }

    private VuContainerAdapter createAdapter() {
        ViewHolderStateDelegate viewHolderStateDelegate = (ViewHolderStateDelegate) getDelegate(ViewHolderStateDelegate.class);
        if (viewHolderStateDelegate != null) {
            VuContainerAdapter vuContainerAdapter = new VuContainerAdapter(this.mBlackboard, this.mMediaData, (ContainerModel) this.mModel, viewHolderStateDelegate);
            vuContainerAdapter.setHasStableIds(true);
            return vuContainerAdapter;
        }
        throw new IllegalStateException("PageChangeDelegate is null");
    }

    private void disablePenSelection() {
        View childAt = this.mViewPager.getChildAt(0);
        if (childAt instanceof RecyclerView) {
            ((RecyclerView) childAt).seslSetPenSelectionEnabled(false);
        }
    }

    private int findCandidatePosition() {
        int i2;
        int currentItem = this.mViewPager.getCurrentItem();
        int itemCount = this.mViewPagerAdapter.getItemCount();
        if (currentItem < itemCount || itemCount - 1 < 0) {
            Log.w(this.TAG, "findCandidatePosition failed");
            return -1;
        }
        String str = this.TAG;
        Log.d(str, "findCandidatePosition(p) " + Logger.v(Integer.valueOf(currentItem), Integer.valueOf(i2)));
        return i2;
    }

    private int findHoldingPosition(MediaItem mediaItem) {
        int i2;
        int i7;
        long mediaId = mediaItem.getMediaId();
        if (LocationKey.isDynamicViewList(((IVuContainerView) this.mView).getLocationKey())) {
            i2 = this.mMediaData.findPositionBy((Predicate<MediaItem>) new j(mediaItem, 11));
        } else {
            if (mediaId > 0) {
                i7 = findItemPosition(mediaId);
                if (i7 < 0) {
                    i7 = findHoldingPositionByScan(mediaId, new e(24));
                }
                if (i7 >= 0) {
                    String str = this.TAG;
                    Log.d(str, "findHoldingPosition(m) " + Logger.v(Long.valueOf(mediaId), Integer.valueOf(i7)));
                }
            } else {
                long fileId = mediaItem.getFileId();
                int findPositionByFileId = this.mMediaData.findPositionByFileId(fileId);
                if (findPositionByFileId < 0) {
                    findPositionByFileId = findHoldingPositionByScan(fileId, new e(25));
                }
                if (i7 >= 0) {
                    String str2 = this.TAG;
                    Log.d(str2, "findHoldingPosition(f) " + Logger.v(Long.valueOf(fileId), Integer.valueOf(i7)));
                }
            }
            i2 = i7;
        }
        if (i2 >= 0 || !mediaItem.isGroupShot()) {
            return i2;
        }
        long groupMediaId = mediaItem.getGroupMediaId();
        int bucketID = mediaItem.getBucketID();
        int findPositionByGroupId = findPositionByGroupId(groupMediaId, bucketID);
        if (findPositionByGroupId >= 0) {
            String str3 = this.TAG;
            Log.d(str3, "findHoldingPosition(g) " + Logger.v(Long.valueOf(groupMediaId), Integer.valueOf(bucketID), Integer.valueOf(findPositionByGroupId)));
        }
        return findPositionByGroupId;
    }

    private int findHoldingPositionByScan(long j2, Function<MediaItem, Long> function) {
        int currentItem = this.mViewPager.getCurrentItem();
        int min = Math.min(currentItem + 100, this.mMediaData.getCount());
        for (int max = Math.max(0, currentItem - 100); max < min; max++) {
            if (function.apply(this.mMediaData.read(max)).longValue() == j2) {
                String str = this.TAG;
                Log.d(str, "findHoldingPositionByScan" + Logger.v(Long.valueOf(j2), Integer.valueOf(max)));
                return max;
            }
        }
        return -1;
    }

    private int findItemPosition(long j2) {
        try {
            if (!this.mMediaData.isDataAvailable() || j2 >= 2147483648L) {
                return -1;
            }
            return this.mMediaData.findPosition(j2);
        } catch (UnsupportedApiException e) {
            String str = this.TAG;
            Log.e(str, "findItemPosition failed. e=" + e.getMessage());
            return -1;
        }
    }

    private int findPositionByGroupId(long j2, int i2) {
        int position = ((ContainerModel) this.mModel).getPosition();
        int min = Math.min(this.mMediaData.getCount() - 1, position + 100);
        for (int max = Math.max(0, position - 100); max <= min; max++) {
            MediaItem read = this.mMediaData.read(max);
            if (read != null && read.getGroupMediaId() == j2 && read.getBucketID() == i2) {
                a.k(max, "findPositionByGroupId p=", this.TAG);
                return max;
            }
        }
        String str = this.TAG;
        Log.w(str, "findPositionByGroupId failed rp=" + position);
        return -1;
    }

    private int getViewPagerMargin() {
        Resources resources;
        Context context = ((IVuContainerView) this.mView).getContext();
        if (context != null) {
            resources = context.getResources();
        } else {
            resources = null;
        }
        if (resources != null) {
            return resources.getDimensionPixelOffset(R.dimen.viewer_item_margin);
        }
        String str = this.TAG;
        Log.e(str, "getViewPagerMargin fail " + context);
        return 60;
    }

    private int getViewPagerMasking() {
        Resources resources;
        Context context = ((IVuContainerView) this.mView).getContext();
        if (context != null) {
            resources = context.getResources();
        } else {
            resources = null;
        }
        if (resources != null) {
            return resources.getDimensionPixelOffset(R.dimen.viewer_item_masking);
        }
        String str = this.TAG;
        Log.e(str, "getViewPagerMasking fail " + context);
        return 90;
    }

    private void holdPosition() {
        int i2;
        long j2;
        int findCandidatePosition;
        if (!moveToReservedPosition() && this.mViewPager.getScrollState() == 0) {
            MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
            if (currentMediaItem != null) {
                try {
                    i2 = findHoldingPosition(currentMediaItem);
                } catch (NullPointerException e) {
                    Log.e(this.TAG, "holdPosition failed " + MediaItemUtil.getDebugLog(currentMediaItem) + " e=" + e.getMessage());
                    return;
                }
            } else {
                i2 = -1;
            }
            if (i2 == -1 && (findCandidatePosition = findCandidatePosition()) != -1) {
                MediaItem read = this.mMediaData.read(findCandidatePosition);
                if (!MediaItemUtil.equalsId(currentMediaItem, read)) {
                    currentMediaItem = read;
                    i2 = findCandidatePosition;
                }
            }
            if (currentMediaItem == null || i2 < 0) {
                Log.w(this.TAG, "holdPosition failed : item = " + MediaItemUtil.getSimpleLog(currentMediaItem));
                return;
            }
            ((IVuContainerView) this.mView).printLog(this.TAG, "hold to pos " + ((ContainerModel) this.mModel).getPosition() + " > " + i2);
            if (this.mViewPager.isFakeDragging()) {
                this.mViewPager.endFakeDrag();
            }
            if (((IVuContainerView) this.mView).isViewResumed()) {
                this.mViewPager.setCurrentItem(i2, false);
                return;
            }
            if (this.mBlackboard.read("lifecycle://last_activity_lifecycle") != "lifecycle://on_activity_stop") {
                this.mViewPager.setCurrentItem(i2, false);
            }
            ContainerModel containerModel = (ContainerModel) this.mModel;
            if (!currentMediaItem.isCloudOnly()) {
                if (!TrashData.isTrash(currentMediaItem)) {
                    j2 = currentMediaItem.getMediaId();
                    containerModel.setReservedPosition(j2);
                }
            }
            j2 = currentMediaItem.getFileId();
            containerModel.setReservedPosition(j2);
        }
    }

    /* access modifiers changed from: private */
    public boolean isForceSwipe(int i2) {
        ForceSwipeDelegate forceSwipeDelegate = (ForceSwipeDelegate) getDelegate(ForceSwipeDelegate.class);
        if (forceSwipeDelegate == null || !forceSwipeDelegate.isWorking()) {
            return false;
        }
        return true;
    }

    private boolean isNotConfirmed(ViewerObjectComposite viewerObjectComposite) {
        if (viewerObjectComposite == null || !viewerObjectComposite.getModel().isViewConfirmed()) {
            return true;
        }
        return false;
    }

    private boolean isValidPosition(int i2) {
        if (this.mMediaData.getCount() > i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$findHoldingPosition$1(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem.getMediaId() == mediaItem2.getMediaId() && mediaItem.getTitle().hashCode() == mediaItem2.getTitle().hashCode()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        if (objArr[0].booleanValue()) {
            updateViewPagerTouch();
        } else {
            setViewPagerTouchEnabled(false);
        }
    }

    private boolean moveToReservedPosition() {
        long reservedPositionMediaIdAndReset = ((ContainerModel) this.mModel).getReservedPositionMediaIdAndReset();
        if (reservedPositionMediaIdAndReset >= 0) {
            int findItemPosition = findItemPosition(reservedPositionMediaIdAndReset);
            if (findItemPosition >= 0) {
                ((IVuContainerView) this.mView).setPosition(findItemPosition, false);
                String str = this.TAG;
                Log.d(str, "moveToReservedPosition : " + reservedPositionMediaIdAndReset);
                return true;
            }
            int findPositionByFileId = this.mMediaData.findPositionByFileId(reservedPositionMediaIdAndReset);
            if (findPositionByFileId >= 0) {
                ((IVuContainerView) this.mView).setPosition(findPositionByFileId, false);
                String str2 = this.TAG;
                Log.d(str2, "moveToReservedPosition by fileId: " + reservedPositionMediaIdAndReset);
                return true;
            }
        }
        String str3 = this.TAG;
        Log.w(str3, "moveToReservedPosition fail : " + reservedPositionMediaIdAndReset);
        return false;
    }

    /* access modifiers changed from: private */
    public void onBackKeyPressed(Object... objArr) {
        if (isNotConfirmed(((IVuContainerView) this.mView).getCurrentViewer())) {
            Log.d(this.TAG, "force confirm for back");
            ViewerObjectComposite findContentViewer = this.mViewPagerAdapter.findContentViewer(this.mViewPager.getCurrentItem());
            if (findContentViewer != null) {
                this.mViewPagerAdapter.onViewConfirm(findContentViewer.getViewHolder());
                return;
            }
            Log.e((CharSequence) this.TAG, "onBackKeyPressed : composite is null", ((ContainerModel) this.mModel).getCurrentMediaItem());
            BlackboardUtils.cancelAndEraseViewerBitmap(this.mBlackboard, (FileItemInterface) ((ContainerModel) this.mModel).getCurrentMediaItem());
        }
    }

    private boolean supportMaskingTransform() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_VIEWPAGER_MASKING || LocationKey.isRevitalizationView(((IVuContainerView) this.mView).getLocationKey())) {
            return false;
        }
        return true;
    }

    public void onBindView(View view) {
        ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
        this.mViewPager = viewPager;
        ViewUtils.disableSeslRecoil(viewPager);
        blockHoverScrollEnabled();
        disablePenSelection();
        setDefaultViewPagerTransformer();
        this.mViewPager.registerOnPageChangeCallback(this.mPageChangeCallback);
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        int currentItem = this.mViewPager.getCurrentItem();
        if (z7 && isValidPosition(currentItem)) {
            VuContainerAdapter createAdapter = createAdapter();
            this.mViewPagerAdapter = createAdapter;
            this.mViewPager.setAdapter(createAdapter);
            if (this.mViewPager.isFakeDragging()) {
                this.mViewPager.endFakeDrag();
            }
            this.mViewPager.setCurrentItem(currentItem, false);
        }
    }

    public void onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mMediaData = ((ContainerModel) this.mModel).getMediaData();
        if (this.mViewPagerAdapter == null) {
            this.mViewPagerAdapter = createAdapter();
            return;
        }
        String str = this.TAG;
        Log.d(str, "viewPagerAdapter already exist " + Logger.getSimpleName((Object) this.mViewPagerAdapter));
    }

    public void onDestroy() {
        VuContainerAdapter vuContainerAdapter = this.mViewPagerAdapter;
        if (vuContainerAdapter != null) {
            vuContainerAdapter.destroy();
            this.mViewPagerAdapter = null;
        }
        this.mViewPager.unregisterOnPageChangeCallback(this.mPageChangeCallback);
        this.mViewPager.setAdapter((RecyclerView.Adapter) null);
        mMainHandler.removeCallbacksAndMessages((Object) null);
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        StringBuilder t = C0212a.t(str, "  ViewPager : ");
        t.append(this.mViewPager);
        Logger.dumpLog(printWriter, t.toString());
        Logger.dumpLog(printWriter, str + "  ViewPager pos: " + this.mViewPager.getCurrentItem());
        Logger.dumpLog(printWriter, str + "  ViewPager Visibility: " + this.mViewPager.getVisibility());
        Logger.dumpLog(printWriter, str + "  ViewPager adapter: " + this.mViewPager.getAdapter());
        if (this.mViewPagerAdapter != null && this.mViewPager.getAdapter() == this.mViewPagerAdapter) {
            StringBuilder t3 = C0212a.t(str, "     adapter size : ");
            t3.append(this.mViewPagerAdapter.getItemCount());
            Logger.dumpLog(printWriter, t3.toString());
        }
    }

    public void onEnterTransitionEnd() {
        this.mViewPager.setAdapter(this.mViewPagerAdapter);
    }

    public void onMediaDataChanged(int i2, int i7) {
        VuContainerAdapter vuContainerAdapter = this.mViewPagerAdapter;
        if (vuContainerAdapter != null) {
            vuContainerAdapter.clearCache();
            this.mViewPagerAdapter.setFirstView();
            holdPosition();
            Log.d(this.TAG, "onMediaDataChanged");
            if (i7 == 1) {
                this.mViewPagerAdapter.notifyItemChanged(i2);
            } else {
                this.mViewPagerAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        ((IVuContainerView) this.mView).setCurrentViewer(viewerObjectComposite);
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        ((IVuContainerView) this.mView).setCurrentViewer(viewerObjectComposite);
        setViewPagerTouchEnabled(true);
    }

    public void onResume() {
        super.onResume();
        if (((ContainerModel) this.mModel).hasReservedPositionMediaIdExist()) {
            moveToReservedPosition();
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.BACK_KEY_PRESSED, new C0509a(this, 0));
        actionInvoker.add(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, new C0509a(this, 1));
    }

    public void setDefaultViewPagerTransformer() {
        ViewerPage2TransformerComposition.Builder create = ViewerPage2TransformerComposition.Builder.create();
        create.addTransformer(new MarginPageTransformer(getViewPagerMargin()));
        if (supportMaskingTransform()) {
            create.addTransformer(new MaskingTransformer(getViewPagerMasking()));
        }
        try {
            this.mViewPager.setPageTransformer(create.build());
        } catch (Exception e) {
            Log.e(this.TAG, e.getMessage());
        }
    }

    public void setViewPagerTouchEnabled(boolean z) {
        if (this.heavyLog.setValue(Boolean.valueOf(z))) {
            Log.d(this.TAG, "setViewPagerTouchEnabled", Boolean.valueOf(z));
        }
        this.mViewPager.setUserInputEnabled(z);
    }

    public void updateMediaData(MediaData mediaData) {
        this.mMediaData = mediaData;
        VuContainerAdapter vuContainerAdapter = this.mViewPagerAdapter;
        if (vuContainerAdapter != null) {
            vuContainerAdapter.setMediaData(mediaData);
        }
    }

    public void updateViewPagerTouch() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        boolean z = true;
        if (currentViewer != null && (!OverlayViewState.isHide((OverlayViewState.StateListener) currentViewer.getModel()) || currentViewer.getModel().isSelectMode() || BottomSheetState.isInTransition(currentViewer.getModel()))) {
            z = false;
        }
        setViewPagerTouchEnabled(z);
    }
}
