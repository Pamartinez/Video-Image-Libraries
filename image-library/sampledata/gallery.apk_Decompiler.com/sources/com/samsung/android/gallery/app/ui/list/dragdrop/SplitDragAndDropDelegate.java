package com.samsung.android.gallery.app.ui.list.dragdrop;

import A.a;
import A4.C0385u;
import Qb.c;
import T4.e;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.externals.PasteClipboardCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.dragdrop.AlbumPaneDragTargetFinder;
import com.samsung.android.gallery.widget.dragdrop.DnDAnimation;
import com.samsung.android.gallery.widget.dragdrop.DragTargetFinder;
import com.samsung.android.gallery.widget.dragdrop.DrawerDragTargetFinder;
import com.samsung.android.gallery.widget.dragdrop.NoDnDAnimation;
import com.samsung.android.gallery.widget.dragdrop.SplitDnDAnimation;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SplitDragAndDropDelegate extends ListDragAndDropDelegate {
    private DnDAnimation mAnimation = new NoDnDAnimation();
    DragAndDropListScroller mDragAndDropListScroller;
    DragTargetFinder[] mDragTargetFinder = {new AlbumPaneDragTargetFinder(), new DrawerDragTargetFinder()};
    private Runnable mSplitAutoClose;
    private SplitHandler mSplitHandler;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SplitHandler {
        MediaItem[] getSelectedItemsForSplitDrag();

        void hideSplitAnimation();

        boolean isSplitClosed();

        boolean isSplitMode() {
            return false;
        }

        void showSplitAnimation();
    }

    public SplitDragAndDropDelegate(IBaseListView iBaseListView) {
        super(iBaseListView);
    }

    private void autoScroll(View view, int i2, int i7) {
        RecyclerView albumListView = getDragTargetFinder().getAlbumListView(view);
        if (albumListView != null) {
            Rect rect = new Rect();
            albumListView.getGlobalVisibleRect(rect);
            if (i2 >= rect.left && i2 <= rect.right) {
                getAlbumListScroller().processAutoScroll(i7, albumListView);
            }
        }
    }

    private DragAndDropListScroller getAlbumListScroller() {
        if (this.mDragAndDropListScroller == null) {
            this.mDragAndDropListScroller = new DragAndDropListScroller(this.mView);
        }
        return this.mDragAndDropListScroller;
    }

    private DragTargetFinder getDragTargetFinder() {
        return this.mDragTargetFinder[this.mView.isDrawerMode()];
    }

    private MediaItem getPasteTargetAlbum(GalleryListView galleryListView) {
        if (this.mView.isDrawerMode()) {
            return getCurrentAlbum();
        }
        if (galleryListView == null) {
            return null;
        }
        View focusedChild = galleryListView.getFocusedChild();
        if (focusedChild == null) {
            return getCurrentAlbum();
        }
        ListViewHolder listViewHolder = (ListViewHolder) galleryListView.findContainingViewHolder(focusedChild);
        if (listViewHolder != null) {
            return listViewHolder.getMediaItem();
        }
        return null;
    }

    private View getRootView() {
        return this.mView.getActivity().findViewById(R.id.content);
    }

    private void handleSplitViewAnimations() {
        if (this.mSplitHandler.isSplitClosed()) {
            this.mSplitHandler.showSplitAnimation();
            this.mSplitAutoClose = new e(this, 0);
            return;
        }
        this.mSplitAutoClose = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSplitViewAnimations$2() {
        this.mSplitHandler.hideSplitAnimation();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSplitViewAnimations$3(GalleryListView galleryListView) {
        long j2;
        e eVar = new e(this, 1);
        if (galleryListView.isAnimating()) {
            j2 = 400;
        } else {
            j2 = 120;
        }
        galleryListView.postDelayed(eVar, j2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSplitViewAnimations$4() {
        Optional.ofNullable(this.mView.getListView()).ifPresent(new c(25, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$startDragInner$0() {
        return this.mSplitHandler.isSplitMode();
    }

    private void runTouchFeedbackAnimation(ListViewHolder listViewHolder) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(listViewHolder.getImage(), "scaleX", new float[]{0.9f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(listViewHolder.getImage(), "scaleY", new float[]{0.9f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(300);
        animatorSet.start();
    }

    private boolean supportDropOnList(DragEvent dragEvent) {
        if (!getHandler().isDragStartedFromGallery(dragEvent)) {
            return true;
        }
        boolean equals = TextUtils.equals(this.mView.getLocationKey(), ClipDataUtils.getStringExtra(dragEvent.getClipData(), "started_location"));
        if (!PreferenceFeatures.OneUi6x.IS_ONE_UI_60 || equals) {
            return false;
        }
        return true;
    }

    public void cancelAnimation() {
        this.mAnimation.clear();
    }

    public MediaItem getCurrentAlbum() {
        return (MediaItem) this.mView.getBlackboard().read("data://albums/current");
    }

    public boolean handleDragEnded(DragEvent dragEvent) {
        this.mAnimation.clear();
        getAlbumListScroller().setIsActive(false);
        getDragTargetFinder().resetDragState(dragEvent);
        Runnable runnable = this.mSplitAutoClose;
        if (runnable != null) {
            runnable.run();
            this.mSplitAutoClose = null;
        }
        return super.handleDragEnded(dragEvent);
    }

    public boolean handleDragLocation(View view, DragEvent dragEvent) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int x9 = (int) dragEvent.getX();
        int y = (int) dragEvent.getY();
        View view2 = view;
        getDragTargetFinder().findItemAtDropPosition(view2, getCurrentAlbum(), x9 + iArr[0], y + iArr[1], false);
        autoScroll(view2, x9, y);
        this.mAnimation.onMove(x9, y);
        return true;
    }

    public boolean handleDragStarted(DragEvent dragEvent) {
        boolean handleDragStarted = super.handleDragStarted(dragEvent);
        if (handleDragStarted) {
            handleSplitViewAnimations();
            this.mAnimation.onStarted((int) dragEvent.getX(), (int) dragEvent.getY());
        }
        return handleDragStarted;
    }

    public boolean handleDrop(View view, DragEvent dragEvent) {
        super.handleDrop(view, dragEvent);
        boolean z = false;
        getAlbumListScroller().setIsActive(false);
        try {
            if (!getHandler().isDragStartedFromGallery(dragEvent)) {
                Optional.ofNullable(this.mView.getActivity()).ifPresent(new c(24, dragEvent));
            }
            z = handleDropInternal(view, dragEvent);
        } catch (SecurityException e) {
            String str = this.TAG;
            Log.e(str, "fail to get the access permission for content URIs in DragEvent. " + e.getMessage());
        }
        this.mAnimation.onDrop(z);
        a.v("handleDrag {DROP,", "}", this.TAG, z);
        return z;
    }

    public boolean handleDropInternal(View view, DragEvent dragEvent) {
        MediaItem mediaItem;
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        if (LocationKey.isMxVirtualAlbum(this.mView.getLocationKey())) {
            mediaItem = null;
        } else {
            int[] iArr2 = iArr;
            mediaItem = getDragTargetFinder().findItemAtDropPosition(view, getCurrentAlbum(), ((int) dragEvent.getX()) + iArr2[0], ((int) dragEvent.getY()) + iArr2[1], supportDropOnList(dragEvent));
        }
        return getHandler().handleDrop(this.mView, dragEvent, mediaItem);
    }

    public boolean isAutoDragPossible() {
        if (!this.mView.isSelectionMode() || this.mView.getListView().isOngoingPinchAnimation() || Features.isEnabled(Features.IS_UPSM) || this.mView.getAdapter().getSelectableMaxCount() != -1) {
            return false;
        }
        return true;
    }

    public boolean onKeyDown(GalleryListView galleryListView, int i2, KeyEvent keyEvent) {
        if (isCtrlVPressed(i2, keyEvent) && (getMode().isDex() || SdkConfig.atLeast(SdkConfig.SEM.U))) {
            MediaItem pasteTargetAlbum = getPasteTargetAlbum(galleryListView);
            if (pasteTargetAlbum == null || MediaItemUtil.isRecentAlbum(pasteTargetAlbum) || MediaItemUtil.isFavouriteAlbum(pasteTargetAlbum) || pasteTargetAlbum.isAutoAlbum()) {
                Log.w(this.TAG, "target path is null");
            } else if (new DexOnPCDragAndDrop().handlePaste(this.mView, pasteTargetAlbum)) {
                return true;
            } else {
                if (!SdkConfig.atLeast(SdkConfig.SEM.U)) {
                    return false;
                }
                new PasteClipboardCmd().execute(this.mView.getPresenter(), new Object[0]);
                return true;
            }
        }
        return false;
    }

    public void setIsDraggingOnGallery(boolean z) {
        super.setIsDraggingOnGallery(z);
        this.mAnimation.setIsDragging(z);
    }

    public SplitDragAndDropDelegate setSplitHandler(SplitHandler splitHandler) {
        this.mSplitHandler = splitHandler;
        return this;
    }

    public boolean startAutoDrag(int i2) {
        ListViewHolder listViewHolder = (ListViewHolder) this.mView.getListView().findViewHolderForAdapterPosition(i2);
        if (listViewHolder == null) {
            Log.e(this.TAG, "startAutoDrag skipped, viewHolder is null");
            return false;
        }
        MediaItem[] selectedItemsForSplitDrag = this.mSplitHandler.getSelectedItemsForSplitDrag();
        if (selectedItemsForSplitDrag == null || selectedItemsForSplitDrag.length == 0) {
            Log.w(this.TAG, "startAutoDrag skipped, nothing selected");
            return false;
        }
        startDrag(selectedItemsForSplitDrag, listViewHolder);
        return true;
    }

    public void startDragInner(View view, MediaItem[] mediaItemArr, ListViewHolder listViewHolder) {
        DnDAnimation dnDAnimation;
        runTouchFeedbackAnimation(listViewHolder);
        ClipData clipData = ClipDataManager.getInstance().getClipData(this.mView.getContext(), ClipDataCreatorFactory.create(getMode(), this.mView.getLocationKey(), mediaItemArr));
        if (clipData == null) {
            Log.d(this.TAG, "start drag failed. invalid ClipData");
            return;
        }
        ClipDataUtils.putStringExtra(clipData, "started_location", this.mView.getLocationKey());
        ListViewHolder listViewHolder2 = listViewHolder;
        if (getHandler().start(this.mView.getListView(), view, clipData, listViewHolder2, mediaItemArr.length)) {
            this.mAnimation.clear();
            if (getMode().isGallerySolelyMode()) {
                dnDAnimation = new SplitDnDAnimation();
            } else {
                dnDAnimation = new NoDnDAnimation();
            }
            this.mAnimation = dnDAnimation;
            dnDAnimation.onPrepare((ViewGroup) getRootView(), this.mView.getListView(), listViewHolder2, new C0385u(12, this));
            this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_SPLIT_VIEW_DRAG_AND_DROP);
        }
    }
}
