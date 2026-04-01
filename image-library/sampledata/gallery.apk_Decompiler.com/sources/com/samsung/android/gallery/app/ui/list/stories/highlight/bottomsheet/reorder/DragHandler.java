package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder;

import android.content.ClipData;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.function.Supplier;
import m7.c;
import n4.C0489a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DragHandler implements View.OnDragListener {
    private AutoScroller mAutoScroller;
    private DragItem mDragItem;
    private final DragListener mDragListener;
    private final ViewGroup mDragRoot;
    private final Supplier<Boolean> mDraggable;
    private final int mMaxScrollDistance;
    private final RecyclerView mRecyclerView;

    public DragHandler(RecyclerView recyclerView, DragListener dragListener, Supplier<Boolean> supplier) {
        int i2 = (int) (Resources.getSystem().getDisplayMetrics().density * 12.0f);
        this.mMaxScrollDistance = i2;
        this.mDragRoot = findDragRoot(recyclerView);
        this.mRecyclerView = recyclerView;
        this.mDragListener = dragListener;
        this.mDraggable = supplier;
        Log.d("DragHandler", "ScrollDistance", Integer.valueOf(i2));
    }

    private ViewGroup findDragRoot(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return null;
        }
        if (viewGroup.getId() == R.id.content) {
            return viewGroup;
        }
        return findDragRoot((ViewGroup) viewGroup.getParent());
    }

    private int findTargetPosition(float f, float f5) {
        RecyclerView.ViewHolder findContainingViewHolder;
        RectF viewRect = ViewUtils.getViewRect(this.mRecyclerView);
        float f8 = f5 - viewRect.top;
        View findChildViewUnder = this.mRecyclerView.findChildViewUnder(f - viewRect.left, f8);
        if (findChildViewUnder == null || (findContainingViewHolder = this.mRecyclerView.findContainingViewHolder(findChildViewUnder)) == null) {
            return -1;
        }
        return findContainingViewHolder.getAbsoluteAdapterPosition();
    }

    private View.DragShadowBuilder getDummyDragShadow() {
        return new View.DragShadowBuilder() {
            public void onProvideShadowMetrics(Point point, Point point2) {
                point.set(1, 1);
                point2.set(0, 0);
            }
        };
    }

    private boolean isVerticalScroll() {
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollVertically()) {
            return false;
        }
        return true;
    }

    private void scrollBy(float f, float f5) {
        int width;
        RectF viewRect = ViewUtils.getViewRect(this.mRecyclerView);
        boolean isVerticalScroll = isVerticalScroll();
        DragItem dragItem = this.mDragItem;
        if (isVerticalScroll) {
            width = dragItem.getHeight();
        } else {
            width = dragItem.getWidth();
        }
        int i2 = width / 2;
        if (i2 > 0) {
            if (isVerticalScroll) {
                float f8 = (float) i2;
                if (viewRect.bottom - f8 < f5) {
                    this.mRecyclerView.scrollBy(0, this.mMaxScrollDistance);
                } else if (viewRect.top + f8 > f5) {
                    this.mRecyclerView.scrollBy(0, -this.mMaxScrollDistance);
                }
            } else {
                float f10 = (float) i2;
                if (viewRect.right - f10 < f) {
                    this.mRecyclerView.scrollBy(this.mMaxScrollDistance, 0);
                } else if (viewRect.left + f10 > f) {
                    this.mRecyclerView.scrollBy(-this.mMaxScrollDistance, 0);
                }
            }
        }
    }

    public boolean beginDrag(ListViewHolder listViewHolder) {
        if (!this.mDraggable.get().booleanValue()) {
            return false;
        }
        this.mDragItem = new DragItem(this.mDragRoot, this.mRecyclerView, listViewHolder);
        this.mDragRoot.setOnDragListener(this);
        listViewHolder.getRootView().startDragAndDrop(ClipData.newPlainText("", ""), getDummyDragShadow(), listViewHolder.getRootView(), 0);
        return true;
    }

    public int getFromPosition() {
        DragItem dragItem = this.mDragItem;
        if (dragItem != null) {
            return dragItem.getFromPosition();
        }
        return -1;
    }

    public boolean isDragging() {
        if (this.mDragItem != null) {
            return true;
        }
        return false;
    }

    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case 1:
                Log.d("DragHandler", "onDragBegin");
                return onDragBegin(view, dragEvent);
            case 2:
                return onDragMove(view, dragEvent);
            case 3:
                Log.d("DragHandler", "onDrop");
                return onDrop(view, dragEvent);
            case 4:
                Log.d("DragHandler", "onDragEnd");
                return onDragEnd(view, dragEvent);
            case 5:
                Log.d("DragHandler", "onDragEntered");
                return true;
            case 6:
                Log.d("DragHandler", "onDragExited");
                return true;
            default:
                return false;
        }
    }

    public boolean onDragBegin(View view, DragEvent dragEvent) {
        DragItem dragItem = this.mDragItem;
        if (dragItem == null || !dragItem.onDragBegin(dragEvent.getX(), dragEvent.getY())) {
            return false;
        }
        this.mDragListener.onDragBegin();
        if (!SdkConfig.atLeast(SdkConfig.GED.R)) {
            return true;
        }
        SeApiCompat.performHapticFeedback(this.mDragRoot.getContext(), 14);
        return true;
    }

    public boolean onDragEnd(View view, DragEvent dragEvent) {
        Optional.ofNullable(this.mAutoScroller).ifPresent(new c(11));
        this.mDragListener.onDragEnd();
        this.mDragItem.onDragEnd();
        this.mDragRoot.setOnDragListener((View.OnDragListener) null);
        this.mDragItem = null;
        return true;
    }

    public boolean onDragMove(View view, DragEvent dragEvent) {
        if (this.mDragItem == null) {
            return false;
        }
        float x9 = dragEvent.getX();
        float y = dragEvent.getY();
        if (this.mDragItem.enableDragView(x9, y)) {
            this.mDragListener.onDragStart();
        }
        if (!this.mDragItem.onDragMove(x9, y)) {
            return true;
        }
        this.mDragListener.onDragOver(findTargetPosition(x9, y), x9, y);
        if (!this.mDragListener.scrollable()) {
            return true;
        }
        if (this.mAutoScroller == null || !isVerticalScroll()) {
            scrollBy(x9, y);
            return true;
        }
        this.mAutoScroller.processAutoScroll((int) y, this.mRecyclerView);
        return true;
    }

    public boolean onDrop(View view, DragEvent dragEvent) {
        DragItem dragItem = this.mDragItem;
        if (dragItem == null) {
            return false;
        }
        this.mDragListener.onDrop(dragItem.getInitialPosition(), this.mDragItem.getFinalPosition());
        this.mDragItem.onDrop(dragEvent.getX(), dragEvent.getY(), (Runnable) null);
        return true;
    }

    public void setAutoScroller(AutoScroller autoScroller) {
        this.mAutoScroller = autoScroller;
    }

    public void updateDragPosition(int i2) {
        Optional.ofNullable(this.mDragItem).ifPresent(new C0489a(i2, 1));
    }
}
