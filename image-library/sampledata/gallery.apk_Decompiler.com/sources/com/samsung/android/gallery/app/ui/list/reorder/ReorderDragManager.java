package com.samsung.android.gallery.app.ui.list.reorder;

import A4.C0385u;
import A4.J;
import A4.W;
import C4.i;
import D5.e;
import Fa.C0569x;
import Fb.d;
import H7.B;
import J5.c;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.IReorderHandler;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.samsung.android.gallery.app.ui.list.dragdrop.DnDMode;
import com.samsung.android.gallery.app.ui.list.dragdrop.DragAndDropListScroller;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderItemController;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.album.reorder.ReorderDragType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Lazy;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.android.sum.core.filter.f;
import com.sec.android.gallery3d.R;
import e6.C0453a;
import g5.a;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReorderDragManager implements ReorderItemController.DragDropListener {
    private final boolean SUPPORT_REORDER = Features.isEnabled(Features.SUPPORT_REORDER);
    protected Blackboard mBlackboard;
    DragAndDropListScroller mDragAndDropListScroller;
    ReorderDragType mDragType = new ReorderDragType(ReorderDragType.Type.NONE);
    private int mDraggedIndex = -1;
    private MediaItem mDraggedItem = null;
    private int mFolderIndex = -1;
    private MediaItem mHighlightItem = null;
    private int mHighlightedIndex = -1;
    private Lazy<DnDMode> mMode;
    private boolean mNeedToExitSelection = false;
    private int mReorderIndex = -1;
    protected ReorderItemController mReorderItemController;
    private boolean mReorderOngoing = false;
    private CountDownTimer mReorderTimer;
    private float mTouchX = -1.0f;
    private float mTouchY = -1.0f;
    protected IBaseListView mView;

    public ReorderDragManager(Blackboard blackboard, IBaseListView iBaseListView) {
        this.mBlackboard = blackboard;
        this.mView = iBaseListView;
        FrameLayout initRootLayout = initRootLayout(iBaseListView.getListView());
        if (initRootLayout != null) {
            initializeController(initRootLayout);
        }
        this.mMode = new Lazy<>(new c(28, iBaseListView));
    }

    private void beginDrag(GalleryListView galleryListView, ListViewHolder listViewHolder, boolean z) {
        ReorderItemController reorderItemController = this.mReorderItemController;
        if (reorderItemController != null && !reorderItemController.isDragging() && !isLocalDbUpdating() && this.mTouchX != -1.0f && this.mTouchY != -1.0f) {
            if (listViewHolder.getMediaItem() != null) {
                this.mDragType.initDragType(new W(4, this, galleryListView), new C0385u(16, this));
            }
            if (this.mDragType.get() != ReorderDragType.Type.NONE) {
                this.mReorderItemController.setListener(this);
                this.mReorderItemController.startAlbumDrag(galleryListView, listViewHolder, (AlbumsBaseLayoutManager) galleryListView.getLayoutManager(), isGridView(galleryListView), z, this.mTouchX, this.mTouchY);
            }
        }
    }

    private void createFolder(boolean z, MediaItem[] mediaItemArr, int i2, int i7) {
        MediaItem[] mediaItemArr2 = mediaItemArr;
        ThreadUtil.postOnUiThreadDelayed(new C0569x(this, z, mediaItemArr2, i7, 1), 500);
        Optional.ofNullable(getReorderHandler()).ifPresent(new i((Object) this, i2, (Object) mediaItemArr2, 6));
    }

    private void findTargetView(GalleryListView galleryListView, float f, float f5) {
        if (this.mDragType.get() == ReorderDragType.Type.REORDER_AND_FOLDER) {
            findTargetViewForReorderAndFolder(galleryListView, f, f5);
        } else if (this.mDragType.get() == ReorderDragType.Type.REORDER) {
            findTargetViewForReorder(galleryListView, f, f5);
        } else if (this.mDragType.get() == ReorderDragType.Type.FOLDER) {
            updateHighlightedIndex(getHighlightedIndex(galleryListView, f, f5));
        } else if (this.mDragType.get() == ReorderDragType.Type.NONE) {
            updateHighlightedIndex(-1);
        }
    }

    private void findTargetViewForReorder(GalleryListView galleryListView, float f, float f5) {
        updateHighlightedIndex(-1);
        int closestViewIndex = DragUtil.getClosestViewIndex(galleryListView, f, f5);
        if (closestViewIndex == -1) {
            this.mReorderIndex = -1;
        }
        int targetReorderIndex = getTargetReorderIndex(galleryListView, closestViewIndex);
        if (closestViewIndex != -1 && this.mReorderIndex != targetReorderIndex) {
            this.mReorderIndex = targetReorderIndex;
            reorderTo(targetReorderIndex);
        }
    }

    private void findTargetViewForReorderAndFolder(GalleryListView galleryListView, float f, float f5) {
        int highlightedIndex = getHighlightedIndex(galleryListView, f, f5);
        updateHighlightedIndex(highlightedIndex);
        int closestViewIndex = DragUtil.getClosestViewIndex(galleryListView, f, f5);
        int targetReorderIndex = getTargetReorderIndex(galleryListView, closestViewIndex);
        if (!(highlightedIndex == -1 && closestViewIndex != -1 && this.mReorderIndex == targetReorderIndex)) {
            finishReorderTimer();
            this.mReorderIndex = -1;
        }
        if (highlightedIndex == -1 && closestViewIndex != -1 && this.mReorderIndex != targetReorderIndex) {
            this.mReorderIndex = targetReorderIndex;
            startReorderTimer(targetReorderIndex);
        }
    }

    private void finishReorderTimer() {
        CountDownTimer countDownTimer = this.mReorderTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private DragAndDropListScroller getAlbumListScroller() {
        if (this.mDragAndDropListScroller == null) {
            this.mDragAndDropListScroller = new DragAndDropListScroller(this.mView);
        }
        return this.mDragAndDropListScroller;
    }

    /* access modifiers changed from: private */
    /* renamed from: getFolderPossible */
    public boolean lambda$beginDrag$1(GalleryListView galleryListView) {
        return isGridView(galleryListView);
    }

    private int getHighlightedIndex(GalleryListView galleryListView, float f, float f5) {
        int highlightIndex = DragUtil.getHighlightIndex(galleryListView, f, f5);
        if (highlightIndex == this.mDraggedIndex) {
            return -1;
        }
        return highlightIndex;
    }

    private IReorderHandler getReorderHandler() {
        IReorderHandler reorderHandler = this.mView.getReorderHandler();
        if (reorderHandler == null) {
            Log.d("ReorderDragManager", "reorderHandler is null");
        }
        return reorderHandler;
    }

    /* access modifiers changed from: private */
    public boolean getReorderPossible() {
        if (!this.SUPPORT_REORDER || SortByType.getSortBy(SortByType.getAlbumsOrder()) != 40) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public Runnable getScrollEndRunnable(int i2, int i7) {
        return new B(this, i2, i7, 2);
    }

    private int getTargetReorderIndex(GalleryListView galleryListView, int i2) {
        View childAt = galleryListView.getChildAt(i2);
        if (childAt != null) {
            return ((ListViewHolder) galleryListView.getChildViewHolder(childAt)).getAdapterPosition();
        }
        return -1;
    }

    private FrameLayout initRootLayout(View view) {
        if (view == null) {
            return null;
        }
        if (view.getId() == R.id.content) {
            return (FrameLayout) view;
        }
        return initRootLayout((View) view.getParent());
    }

    private boolean isCreateNewFolder() {
        MediaItem mediaItem = this.mHighlightItem;
        if (mediaItem == null || mediaItem.isFolder()) {
            return false;
        }
        return true;
    }

    private boolean isGridView(GalleryListView galleryListView) {
        if (galleryListView.getColumnCount() != 1) {
            return true;
        }
        return false;
    }

    private boolean isLocalDbUpdating() {
        Object obj;
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            obj = blackboard.read("local_db_updating");
        } else {
            obj = null;
        }
        if (obj == null || !((Boolean) obj).booleanValue()) {
            return false;
        }
        return true;
    }

    private boolean isPossibleCreateFolder(ArrayList<MediaItem> arrayList) {
        MediaItem mediaItem = this.mDraggedItem;
        MediaItem mediaItem2 = this.mHighlightItem;
        if (mediaItem == null || mediaItem2 == null) {
            return false;
        }
        arrayList.add(mediaItem2);
        arrayList.add(mediaItem);
        return true;
    }

    private boolean isPossibleReorder() {
        if (!this.SUPPORT_REORDER || this.mDraggedIndex == -1 || this.mHighlightedIndex != -1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createFolder$5(boolean z, MediaItem[] mediaItemArr, int i2) {
        postEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_AGENT_NOT_ACTIVATED, new Object[]{Boolean.valueOf(z), mediaItemArr, Integer.valueOf(i2)}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createFolder$6(int i2, MediaItem[] mediaItemArr, IReorderHandler iReorderHandler) {
        this.mView.setInputBlock("ReorderDragManager_createFolder", 800);
        if (i2 != -1) {
            iReorderHandler.removeItemAt(i2);
        }
        iReorderHandler.folderCreatedAt(this.mFolderIndex, mediaItemArr[1], -1, "");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getScrollEndRunnable$10() {
        endDrag(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getScrollEndRunnable$11(int i2, int i7) {
        Optional.ofNullable(getReorderHandler()).ifPresent(new f(i2, i7, 1));
        ThreadUtil.postOnUiThreadDelayed(new a(this, 1), 500);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DnDMode lambda$new$0(IBaseListView iBaseListView) {
        return new DnDMode(iBaseListView.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDragEnd$4(IReorderHandler iReorderHandler) {
        iReorderHandler.updateOrder(this.mView.getPresenter());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reorderTo$7(int i2, IReorderHandler iReorderHandler) {
        iReorderHandler.moveItemTo(this.mDraggedIndex, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reorderTo$8() {
        this.mReorderOngoing = false;
    }

    private MediaItem onIndexUpdate(int i2, int i7, int i8) {
        boolean z;
        IReorderHandler reorderHandler = getReorderHandler();
        if (reorderHandler == null) {
            A.a.B(i2, "onIndexUpdate: handler is null, type = ", "ReorderDragManager");
            return null;
        }
        if (i7 != i8) {
            z = true;
        } else {
            z = false;
        }
        if (i2 == 1) {
            updateDragIndex(z, i7, i8, reorderHandler);
        } else if (i2 == 2) {
            updateHighlightIndex(z, i7, i8, reorderHandler);
        }
        if (i8 == -1) {
            return null;
        }
        return reorderHandler.getSyncedItem(i8);
    }

    private void postEvent(EventMessage eventMessage) {
        Optional.ofNullable(this.mBlackboard).ifPresent(new e(2, eventMessage));
    }

    /* access modifiers changed from: private */
    public void reorderTo(int i2) {
        if (this.mDraggedIndex != -1 && i2 != -1) {
            Optional.ofNullable(getReorderHandler()).ifPresent(new J((Object) this, i2, 9));
            this.mReorderOngoing = true;
            ThreadUtil.postOnUiThreadDelayed(new a(this, 0), 100);
            updateDraggedIndex(i2);
        }
    }

    private void startReorderTimer(int i2) {
        if (this.SUPPORT_REORDER) {
            finishReorderTimer();
            final int i7 = i2;
            AnonymousClass1 r1 = new CountDownTimer(300, 100) {
                public void onFinish() {
                    ReorderDragManager.this.reorderTo(i7);
                }

                public void onTick(long j2) {
                }
            };
            this.mReorderTimer = r1;
            r1.start();
        }
    }

    private void updateDragIndex(boolean z, int i2, int i7, IReorderHandler iReorderHandler) {
        if (z) {
            if (i7 != -1) {
                iReorderHandler.orderChanged(i7, "add_drag_index");
            } else {
                iReorderHandler.orderChanged(i2, "remove_drag_index");
            }
        }
        iReorderHandler.setDraggedIndex(i7);
    }

    private void updateDraggedIndex(int i2) {
        this.mDraggedItem = onIndexUpdate(1, this.mDraggedIndex, i2);
        this.mDraggedIndex = i2;
    }

    private void updateHighlightIndex(boolean z, int i2, int i7, IReorderHandler iReorderHandler) {
        if (z) {
            if (i7 != -1) {
                iReorderHandler.orderChanged(i7, "add_highlight_index");
            }
            if (i2 != -1) {
                iReorderHandler.orderChanged(i2, "remove_highlight_index");
            }
        }
    }

    private void updateHighlightedIndex(int i2) {
        this.mHighlightItem = onIndexUpdate(2, this.mHighlightedIndex, i2);
        this.mHighlightedIndex = i2;
    }

    public void clearReorderController() {
        if (this.mView.supportTabLayout()) {
            Blackboard blackboard = this.mBlackboard;
            if (blackboard != null) {
                blackboard.erase("data://drag_and_drop");
            }
            ReorderItemController reorderItemController = this.mReorderItemController;
            if (reorderItemController != null) {
                reorderItemController.destroy();
                this.mReorderItemController = null;
                return;
            }
            return;
        }
        ReorderItemController reorderItemController2 = this.mReorderItemController;
        if (reorderItemController2 != null) {
            reorderItemController2.setListener((ReorderItemController.DragDropListener) null);
        }
    }

    public void collectNotifyDataChange(boolean z) {
        BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, z);
    }

    public void destroy() {
        collectNotifyDataChange(false);
        endDrag(false);
        clearReorderController();
        this.mBlackboard = null;
        this.mView = null;
    }

    public void endDrag(boolean z) {
        updateDraggedIndex(-1);
        updateHighlightedIndex(-1);
        this.mFolderIndex = -1;
        this.mDraggedItem = null;
        this.mHighlightItem = null;
        onDragEnd(z);
        this.mNeedToExitSelection = false;
        this.mDragType = new ReorderDragType(ReorderDragType.Type.NONE);
        finishReorderTimer();
        this.mTouchX = -1.0f;
        this.mTouchY = -1.0f;
    }

    public int getFolderIndex() {
        return this.mFolderIndex;
    }

    public void initializeController(FrameLayout frameLayout) {
        ReorderItemController reorderItemController = (ReorderItemController) this.mBlackboard.read("data://drag_and_drop");
        if (reorderItemController != null) {
            this.mReorderItemController = reorderItemController;
            return;
        }
        ReorderItemController reorderItemController2 = new ReorderItemController(frameLayout);
        this.mReorderItemController = reorderItemController2;
        this.mBlackboard.publish("data://drag_and_drop", reorderItemController2);
    }

    public boolean isAvailableReorderDrag(GalleryListView galleryListView, Context context) {
        if (!isGridView(galleryListView) || !this.mMode.get().isGallerySolelyMode() || galleryListView.isOngoingPinchAnimation()) {
            return false;
        }
        return true;
    }

    public boolean isDragStarted() {
        ReorderItemController reorderItemController = this.mReorderItemController;
        if (reorderItemController == null || !reorderItemController.isDragStarted()) {
            return false;
        }
        return true;
    }

    public boolean isDragging() {
        ReorderItemController reorderItemController = this.mReorderItemController;
        if (reorderItemController == null || !reorderItemController.isDragging()) {
            return false;
        }
        return true;
    }

    public boolean isItemAnimating(GalleryListView galleryListView) {
        RecyclerView.ItemAnimator itemAnimator;
        if (galleryListView != null) {
            itemAnimator = galleryListView.getItemAnimator();
        } else {
            itemAnimator = null;
        }
        if (itemAnimator == null || !itemAnimator.isRunning()) {
            return false;
        }
        return true;
    }

    public void onDragEnd() {
        Log.d("ReorderDragManager", "onDragEnd", Integer.valueOf(this.mDraggedIndex), Integer.valueOf(this.mHighlightedIndex));
        ArrayList arrayList = new ArrayList();
        if (isPossibleCreateFolder(arrayList)) {
            boolean isCreateNewFolder = isCreateNewFolder();
            MediaItem[] mediaItemArr = (MediaItem[]) arrayList.toArray(new MediaItem[0]);
            int i2 = this.mHighlightedIndex;
            this.mFolderIndex = i2;
            int i7 = this.mDraggedIndex;
            if (i7 != -1 && i2 > i7) {
                this.mFolderIndex = i2 - 1;
            }
            updateDraggedIndex(-1);
            createFolder(isCreateNewFolder, mediaItemArr, i7, this.mHighlightedIndex);
            return;
        }
        if (isPossibleReorder()) {
            Optional.ofNullable(getReorderHandler()).ifPresent(new b(27, this));
            this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_REORDER);
        }
        endDrag(false);
    }

    public boolean onDragEvent(View view, DragEvent dragEvent, GalleryListView galleryListView) {
        float x9 = dragEvent.getX();
        float y = dragEvent.getY();
        if (this.mReorderItemController.isDragging() && !getAlbumListScroller().processAutoScroll((int) y, galleryListView) && !isItemAnimating(galleryListView) && !this.mReorderOngoing) {
            if (galleryListView.isScrollIdle()) {
                findTargetView(galleryListView, x9, y - ((float) DragUtil.getRelativeTop(view, galleryListView)));
            } else {
                updateHighlightedIndex(-1);
            }
        }
        return true;
    }

    public void onDragStart() {
        Optional.ofNullable(getReorderHandler()).ifPresent(new C0453a(13));
        int draggedIndex = this.mReorderItemController.getDraggedIndex();
        if (this.mNeedToExitSelection) {
            postEvent(EventMessage.obtain(1003));
        }
        updateDraggedIndex(draggedIndex);
    }

    public boolean onDropEvent(View view, DragEvent dragEvent, GalleryListView galleryListView, boolean z) {
        if (z) {
            if (this.SUPPORT_REORDER) {
                finishReorderTimer();
                this.mReorderIndex = -1;
            }
            RecyclerView.LayoutManager layoutManager = galleryListView.getLayoutManager();
            int i2 = this.mHighlightedIndex;
            if (i2 == -1) {
                i2 = this.mDraggedIndex;
            }
            this.mReorderItemController.endAlbumDrag(layoutManager.findViewByPosition(i2));
            getAlbumListScroller().setIsActive(false);
            return true;
        }
        Log.d("ReorderDragManager", "Updating menu manually : Reorder/Folder drag");
        this.mReorderItemController.endAlbumDrag((View) null);
        if (!galleryListView.isTouchOngoing()) {
            galleryListView.onTouchUp();
        } else {
            ThreadUtil.postOnUiThreadDelayed(new d(1, galleryListView), 100);
        }
        endDrag(false);
        return true;
    }

    public void onListItemLongClick(ListViewHolder listViewHolder, GalleryListView galleryListView, boolean z) {
        if (isAvailableReorderDrag(galleryListView, galleryListView.getContext())) {
            this.mNeedToExitSelection = !z;
            beginDrag(galleryListView, listViewHolder, z);
        }
    }

    public void onRecyclerViewTouchEvent(GalleryListView galleryListView, MotionEvent motionEvent, boolean z) {
        ListViewHolder reorderTouchViewHolder;
        if (motionEvent.getAction() == 0) {
            this.mTouchX = motionEvent.getX();
            this.mTouchY = motionEvent.getY();
            if (!isGridView(galleryListView) && z && (reorderTouchViewHolder = DragUtil.getReorderTouchViewHolder(galleryListView, this.mTouchX, this.mTouchY)) != null) {
                beginDrag(galleryListView, reorderTouchViewHolder, true);
            }
        }
    }

    public void setDragListener(boolean z) {
        ReorderItemController reorderItemController = this.mReorderItemController;
        if (reorderItemController != null) {
            reorderItemController.setDragListener(z);
        }
    }

    public void startFolderCreateAnimation(GalleryListView galleryListView, int i2, int i7) {
        final View viewAt = DragUtil.getViewAt(galleryListView, this.mFolderIndex);
        if (viewAt != null) {
            final GalleryListView galleryListView2 = galleryListView;
            final int i8 = i2;
            final int i10 = i7;
            DragUtil.animateView(viewAt, R.anim.fade_out_folder, new SimpleAnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    viewAt.setAlpha(0.0f);
                    galleryListView2.scrollToPositionWithAnimation(i10);
                    ThreadUtil.postOnUiThreadDelayed(ReorderDragManager.this.getScrollEndRunnable(i8, i10), 500);
                }
            });
            return;
        }
        endDrag(true);
    }

    public void stopAlbumDrag() {
        this.mReorderItemController.endAlbumDrag((View) null);
    }

    private void onDragEnd(boolean z) {
        if (z) {
            Optional.ofNullable(this.mView.getPresenter()).ifPresent(new C0453a(11));
        }
        Optional.ofNullable(getReorderHandler()).ifPresent(new C0453a(12));
    }
}
