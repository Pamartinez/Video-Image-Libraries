package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder;

import O3.l;
import android.animation.Animator;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.ocr.MOCRLang;
import g6.g;
import java.util.Optional;
import n4.C0491c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DragItem {
    private float mDeltaX;
    private float mDeltaY;
    private View mDragView;
    private ListViewHolder mDragViewHolder;
    private Animator mDropAnimator;
    private int mFromPosition = -1;
    private float mInitX;
    private float mInitY;
    private final int mInitialPosition;
    private final RecyclerView mRecyclerView;
    private ListViewHolder mStartViewHolder;

    public DragItem(ViewGroup viewGroup, RecyclerView recyclerView, ListViewHolder listViewHolder) {
        this.mRecyclerView = recyclerView;
        this.mInitialPosition = listViewHolder.getAbsoluteAdapterPosition();
        initDragView(viewGroup, listViewHolder);
    }

    private void destroyInternal() {
        ViewUtils.removeSelf(this.mDragView);
        this.mDragView = null;
        this.mDropAnimator = null;
    }

    /* access modifiers changed from: private */
    public void dropComplete(Runnable runnable) {
        int i2;
        if (this.mDragViewHolder != null) {
            i2 = getFinalPosition();
        } else {
            i2 = -1;
        }
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(i2);
        Optional.ofNullable(findViewHolderForAdapterPosition).ifPresent(new C0491c(5, this, findViewHolderForAdapterPosition));
        Optional.ofNullable(runnable).ifPresent(new l(0));
        destroyInternal();
    }

    private View getContentView(RecyclerView.ViewHolder viewHolder) {
        return ((ListViewHolder) viewHolder).getDecoView(2);
    }

    private void initDragView(ViewGroup viewGroup, ListViewHolder listViewHolder) {
        int i2;
        PinchLayoutManager pinchLayoutManager = (PinchLayoutManager) this.mRecyclerView.getLayoutManager();
        RecyclerView.Adapter adapter = this.mRecyclerView.getAdapter();
        if (adapter != null && pinchLayoutManager != null) {
            int viewType = listViewHolder.getViewType();
            final int absoluteAdapterPosition = listViewHolder.getAbsoluteAdapterPosition();
            this.mStartViewHolder = listViewHolder;
            ListViewHolder listViewHolder2 = (ListViewHolder) adapter.onCreateViewHolder(this.mRecyclerView, viewType);
            this.mDragViewHolder = listViewHolder2;
            listViewHolder2.setFakePosition(absoluteAdapterPosition);
            this.mDragView = this.mDragViewHolder.itemView;
            this.mDragViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -1) {
                public int getAbsoluteAdapterPosition() {
                    return absoluteAdapterPosition;
                }

                public int getViewAdapterPosition() {
                    return getAbsoluteAdapterPosition();
                }
            });
            View view = this.mDragView;
            if (isRealRatio(pinchLayoutManager)) {
                i2 = 1;
            } else {
                i2 = pinchLayoutManager.getSpanCount();
            }
            pinchLayoutManager.updateViewSize(view, viewType, i2);
            pinchLayoutManager.bindHolder(this.mDragViewHolder, absoluteAdapterPosition);
            viewGroup.addView(this.mDragView);
            ViewUtils.setVisibility(this.mDragView, 4);
        }
    }

    private boolean isRealRatio(PinchLayoutManager pinchLayoutManager) {
        if (pinchLayoutManager.getSpanCount() > 50) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dropComplete$1(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        ViewUtils.setAlpha(getContentView(viewHolder), 1.0f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$enableDragView$0(ListViewHolder listViewHolder) {
        View contentView = getContentView(listViewHolder);
        if (contentView != null) {
            ViewUtils.setAlpha(contentView, 0.0f);
            return;
        }
        throw new IllegalStateException("ViewHolderValue.DECO_CONTENT view should be exist");
    }

    private boolean overThreshold(float f, float f5) {
        if (Math.abs(this.mInitX - f) > 20.0f || Math.abs(this.mInitY - f5) > 20.0f) {
            return true;
        }
        return false;
    }

    private boolean setPosition(float f, float f5) {
        View view = this.mDragView;
        if (view == null) {
            return false;
        }
        view.setX(f - this.mDeltaX);
        this.mDragView.setY(f5 - this.mDeltaY);
        return true;
    }

    private void startDrop(final Runnable runnable) {
        if (this.mDragViewHolder != null) {
            RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(getFinalPosition());
            if (findViewHolderForAdapterPosition != null) {
                RectF viewRect = ViewUtils.getViewRect(findViewHolderForAdapterPosition.itemView);
                PropertyAnimator duration = new TranslationAnimator(this.mDragViewHolder.itemView, ViewUtils.getViewRect(this.mDragViewHolder.itemView), viewRect).setDuration(MOCRLang.KHMER);
                this.mDropAnimator = duration;
                duration.addListener(new SimpleAnimatorListener() {
                    public void onAnimationEnd(Animator animator) {
                        DragItem.this.dropComplete(runnable);
                    }
                });
                this.mDropAnimator.start();
                return;
            }
            dropComplete(runnable);
            return;
        }
        dropComplete(runnable);
    }

    public boolean dropInProgress() {
        if (this.mDropAnimator != null) {
            return true;
        }
        return false;
    }

    public boolean enableDragView(float f, float f5) {
        View view = this.mDragView;
        if (view == null || ViewUtils.isVisible(view) || !overThreshold(f, f5)) {
            return false;
        }
        enableDragView();
        return true;
    }

    public int getFinalPosition() {
        if (this.mDragViewHolder != null) {
            return this.mFromPosition;
        }
        return this.mInitialPosition;
    }

    public int getFromPosition() {
        return this.mFromPosition;
    }

    public int getHeight() {
        View view = this.mDragView;
        if (view != null) {
            return view.getHeight();
        }
        return 0;
    }

    public int getInitialPosition() {
        return this.mInitialPosition;
    }

    public int getWidth() {
        View view = this.mDragView;
        if (view != null) {
            return view.getWidth();
        }
        return 0;
    }

    public boolean onDragBegin(float f, float f5) {
        RectF viewRect = ViewUtils.getViewRect(this.mStartViewHolder.itemView);
        this.mInitX = f;
        this.mInitY = f5;
        this.mDeltaX = f - viewRect.left;
        this.mDeltaY = f5 - viewRect.top;
        return setPosition(f, f5);
    }

    public void onDragEnd() {
        if (!dropInProgress()) {
            dropComplete((Runnable) null);
        }
    }

    public boolean onDragMove(float f, float f5) {
        return setPosition(f, f5);
    }

    public boolean onDrop(float f, float f5, Runnable runnable) {
        if (this.mDragViewHolder == null) {
            return false;
        }
        startDrop(runnable);
        return true;
    }

    public void updateDragPosition(int i2) {
        this.mFromPosition = i2;
    }

    private void enableDragView() {
        this.mFromPosition = this.mInitialPosition;
        this.mDragViewHolder.setCheckboxEnabled(false);
        ViewUtils.setVisibility(this.mDragView, 0);
        Optional.ofNullable(this.mStartViewHolder).ifPresent(new g(28, this));
    }
}
