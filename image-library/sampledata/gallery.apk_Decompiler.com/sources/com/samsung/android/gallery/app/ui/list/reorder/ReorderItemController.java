package com.samsung.android.gallery.app.ui.list.reorder;

import A4.C0378m;
import a8.d;
import android.animation.Animator;
import android.content.ClipData;
import android.graphics.Point;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import g5.b;
import g5.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReorderItemController {
    private boolean mDragEnded = false;
    private boolean mDragStarted = false;
    private Handler mHandler;
    private boolean mIsDragging = false;
    private DragDropListener mListener;
    private int mMinDragDistance = -1;
    private boolean mPosInited = false;
    private ReorderDragItem mReorderDragItem;
    private FrameLayout mRootLayout;
    private float mTouchX = -1.0f;
    private float mTouchY = -1.0f;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DragDropListener {
        void collectNotifyDataChange(boolean z);

        boolean isItemAnimating(GalleryListView galleryListView);

        void onDragEnd();

        boolean onDragEvent(View view, DragEvent dragEvent, GalleryListView galleryListView);

        void onDragStart();

        boolean onDropEvent(View view, DragEvent dragEvent, GalleryListView galleryListView, boolean z);
    }

    public ReorderItemController(FrameLayout frameLayout) {
        this.mRootLayout = frameLayout;
        this.mHandler = new Handler();
    }

    /* access modifiers changed from: private */
    public void finishDrag() {
        DragDropListener dragDropListener = this.mListener;
        if (dragDropListener != null) {
            dragDropListener.onDragEnd();
        }
        ReorderDragItem reorderDragItem = this.mReorderDragItem;
        if (reorderDragItem != null) {
            reorderDragItem.resetDragObject();
        } else {
            Log.d("ReorderItemController", "activity is destroyed");
        }
        reset();
    }

    private View.DragShadowBuilder getDummyDragShadow() {
        return new View.DragShadowBuilder() {
            public void onProvideShadowMetrics(Point point, Point point2) {
                point.set(1, 1);
                point2.set(0, 0);
            }
        };
    }

    /* access modifiers changed from: private */
    public boolean handleDrag(View view, DragEvent dragEvent) {
        if (this.mReorderDragItem == null) {
            return false;
        }
        GalleryListView galleryListView = (GalleryListView) view.findViewWithTag("DRAG_LISTVIEW");
        if (galleryListView == null) {
            Log.w("ReorderItemController", "handleDrag skip by no list-view");
            return false;
        }
        switch (dragEvent.getAction()) {
            case 1:
            case 5:
            case 6:
                break;
            case 2:
                if (this.mPosInited) {
                    setDragLocation(dragEvent.getX(), dragEvent.getY());
                    DragDropListener dragDropListener = this.mListener;
                    if (dragDropListener != null) {
                        dragDropListener.onDragEvent(view, dragEvent, galleryListView);
                        break;
                    }
                }
                break;
            case 3:
            case 4:
                if (!this.mDragEnded) {
                    lambda$sendDropEvent$2(view, dragEvent, galleryListView);
                    this.mDragEnded = true;
                    break;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    private boolean isDragItemAnimating() {
        ReorderDragItem reorderDragItem = this.mReorderDragItem;
        if (reorderDragItem == null || !reorderDragItem.isAnimating()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startAlbumDrag$0(float f, float f5, float f8, float f10) {
        lambda$setInitialPosition$1(f + f5, f8 + f10);
    }

    private void reset() {
        this.mReorderDragItem = null;
        this.mMinDragDistance = -1;
        this.mIsDragging = false;
        this.mDragStarted = false;
        this.mDragEnded = false;
        this.mPosInited = false;
        this.mTouchX = -1.0f;
        this.mTouchY = -1.0f;
    }

    /* access modifiers changed from: private */
    /* renamed from: sendDropEvent */
    public void lambda$sendDropEvent$2(View view, DragEvent dragEvent, GalleryListView galleryListView) {
        ReorderItemController reorderItemController;
        DragDropListener dragDropListener = this.mListener;
        if (dragDropListener != null) {
            if (dragDropListener.isItemAnimating(galleryListView) || isDragItemAnimating()) {
                Handler handler = this.mHandler;
                if (handler != null) {
                    reorderItemController = this;
                    handler.postDelayed(new d((Object) reorderItemController, (Object) view, (Object) dragEvent, (Object) galleryListView, 10), 300);
                    reorderItemController.mListener.collectNotifyDataChange(false);
                }
            } else {
                this.mListener.onDropEvent(view, dragEvent, galleryListView, this.mIsDragging);
            }
            reorderItemController = this;
            reorderItemController.mListener.collectNotifyDataChange(false);
        }
    }

    private void setDragLocation(float f, float f5) {
        if (!isDragItemAnimating()) {
            if (!this.mIsDragging) {
                float f8 = this.mTouchX;
                if (f8 == -1.0f && this.mTouchY == -1.0f) {
                    this.mTouchX = f;
                    this.mTouchY = f5;
                    if (this.mMinDragDistance == -1) {
                        startDrag(f, f5);
                        return;
                    }
                    return;
                }
                if (Math.sqrt(Math.pow((double) (f5 - this.mTouchY), 2.0d) + Math.pow((double) (f - f8), 2.0d)) > ((double) this.mMinDragDistance)) {
                    startDrag(f, f5);
                    return;
                }
                return;
            }
            this.mReorderDragItem.setPosition(f, f5);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setInitialPosition */
    public void lambda$setInitialPosition$1(float f, float f5) {
        if (this.mDragStarted) {
            setDragLocation(f, f5);
            this.mPosInited = true;
            return;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.postDelayed(new c(this, f, f5, 0), 100);
        } else {
            Log.w("ReorderItemController", "fail setInitialPosition");
        }
    }

    private void startDrag(float f, float f5) {
        this.mIsDragging = true;
        this.mReorderDragItem.setVisibility(true);
        setDragLocation(f, f5);
        DragDropListener dragDropListener = this.mListener;
        if (dragDropListener != null) {
            dragDropListener.onDragStart();
        }
    }

    public void destroy() {
        reset();
        this.mListener = null;
        this.mHandler = null;
        this.mRootLayout.setOnDragListener((View.OnDragListener) null);
    }

    public void endAlbumDrag(View view) {
        if (!this.mIsDragging || view == null) {
            finishDrag();
        } else {
            this.mReorderDragItem.startDragEndAnimation(view, new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    ReorderItemController.this.finishDrag();
                }
            });
        }
    }

    public int getDraggedIndex() {
        return this.mReorderDragItem.getViewPosition();
    }

    public boolean isDragStarted() {
        return this.mDragStarted;
    }

    public boolean isDragging() {
        return this.mIsDragging;
    }

    public void setDragListener(boolean z) {
        if (z) {
            this.mRootLayout.setOnDragListener(new C0378m(2, this));
        } else {
            this.mRootLayout.setOnDragListener((View.OnDragListener) null);
        }
    }

    public void setListener(DragDropListener dragDropListener) {
        this.mListener = dragDropListener;
    }

    public void startAlbumDrag(RecyclerView recyclerView, ListViewHolder listViewHolder, AlbumsBaseLayoutManager albumsBaseLayoutManager, boolean z, boolean z3, float f, float f5) {
        boolean z7;
        int i2;
        ReorderItemController reorderItemController;
        SeApiCompat.requestAccessibilityFocus(listViewHolder.itemView);
        reset();
        RecyclerView recyclerView2 = recyclerView;
        ListViewHolder listViewHolder2 = listViewHolder;
        ReorderDragItem reorderDragItem = new ReorderDragItem(this.mRootLayout, recyclerView2, listViewHolder2, albumsBaseLayoutManager, !z);
        RecyclerView recyclerView3 = recyclerView2;
        ListViewHolder listViewHolder3 = listViewHolder2;
        this.mReorderDragItem = reorderDragItem;
        if (!z || !z3) {
            z7 = false;
        } else {
            z7 = true;
        }
        float f8 = f;
        float f10 = f5;
        reorderDragItem.initTouchOffset(recyclerView3, listViewHolder3, f8, f10, z7);
        this.mReorderDragItem.setVisibility(false);
        this.mDragStarted = listViewHolder3.getRootView().startDragAndDrop(ClipData.newPlainText("", ""), getDummyDragShadow(), listViewHolder3.getRootView(), 0);
        if (!z || z3) {
            i2 = -1;
        } else {
            i2 = 20;
        }
        this.mMinDragDistance = i2;
        if (!z || !z3) {
            reorderItemController = this;
            reorderItemController.mPosInited = true;
        } else {
            reorderItemController = this;
            this.mReorderDragItem.postOnMeasure(new b(reorderItemController, f8, DragUtil.getRootX(recyclerView3, this.mRootLayout), f10, DragUtil.getRootY(recyclerView3, this.mRootLayout)));
        }
        DragDropListener dragDropListener = reorderItemController.mListener;
        if (dragDropListener != null) {
            dragDropListener.collectNotifyDataChange(true);
        }
    }
}
