package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SimpleItemAnimator extends RecyclerView.ItemAnimator {
    boolean mSupportsChangeAnimations = true;

    public abstract boolean animateAdd(RecyclerView.ViewHolder viewHolder);

    public boolean animateAppearance(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i2;
        int i7;
        if (itemHolderInfo == null || ((i2 = itemHolderInfo.left) == (i7 = itemHolderInfo2.left) && itemHolderInfo.top == itemHolderInfo2.top)) {
            return animateAdd(viewHolder);
        }
        return animateMove(viewHolder, i2, itemHolderInfo.top, i7, itemHolderInfo2.top);
    }

    public abstract boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i2, int i7, int i8, int i10);

    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i2;
        int i7;
        int i8 = itemHolderInfo.left;
        int i10 = itemHolderInfo.top;
        if (viewHolder2.shouldIgnore()) {
            int i11 = itemHolderInfo.left;
            i7 = itemHolderInfo.top;
            i2 = i11;
        } else {
            i2 = itemHolderInfo2.left;
            i7 = itemHolderInfo2.top;
        }
        return animateChange(viewHolder, viewHolder2, i8, i10, i2, i7);
    }

    public boolean animateDisappearance(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i2;
        int i7;
        int i8 = itemHolderInfo.left;
        int i10 = itemHolderInfo.top;
        View view = viewHolder.itemView;
        if (itemHolderInfo2 == null) {
            i2 = view.getLeft();
        } else {
            i2 = itemHolderInfo2.left;
        }
        int i11 = i2;
        if (itemHolderInfo2 == null) {
            i7 = view.getTop();
        } else {
            i7 = itemHolderInfo2.top;
        }
        int i12 = i7;
        if (viewHolder.isRemoved() || (i8 == i11 && i10 == i12)) {
            return animateRemove(viewHolder);
        }
        view.layout(i11, i12, view.getWidth() + i11, view.getHeight() + i12);
        return animateMove(viewHolder, i8, i10, i11, i12);
    }

    public abstract boolean animateMove(RecyclerView.ViewHolder viewHolder, int i2, int i7, int i8, int i10);

    public boolean animatePersistence(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i2 = itemHolderInfo.left;
        int i7 = itemHolderInfo2.left;
        if (i2 == i7 && itemHolderInfo.top == itemHolderInfo2.top) {
            dispatchMoveFinished(viewHolder);
            return false;
        }
        return animateMove(viewHolder, i2, itemHolderInfo.top, i7, itemHolderInfo2.top);
    }

    public abstract boolean animateRemove(RecyclerView.ViewHolder viewHolder);

    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
        if (!this.mSupportsChangeAnimations || viewHolder.isInvalid()) {
            return true;
        }
        return false;
    }

    public final void dispatchAddFinished(RecyclerView.ViewHolder viewHolder) {
        onAddFinished(viewHolder);
        dispatchAnimationFinished(viewHolder);
    }

    public final void dispatchAddStarting(RecyclerView.ViewHolder viewHolder) {
        onAddStarting(viewHolder);
    }

    public final void dispatchChangeFinished(RecyclerView.ViewHolder viewHolder, boolean z) {
        onChangeFinished(viewHolder, z);
        dispatchAnimationFinished(viewHolder);
    }

    public final void dispatchChangeStarting(RecyclerView.ViewHolder viewHolder, boolean z) {
        onChangeStarting(viewHolder, z);
    }

    public final void dispatchMoveFinished(RecyclerView.ViewHolder viewHolder) {
        onMoveFinished(viewHolder);
        dispatchAnimationFinished(viewHolder);
    }

    public final void dispatchMoveStarting(RecyclerView.ViewHolder viewHolder) {
        onMoveStarting(viewHolder);
    }

    public final void dispatchRemoveFinished(RecyclerView.ViewHolder viewHolder) {
        onRemoveFinished(viewHolder);
        dispatchAnimationFinished(viewHolder);
    }

    public final void dispatchRemoveStarting(RecyclerView.ViewHolder viewHolder) {
        onRemoveStarting(viewHolder);
    }

    public void setSupportsChangeAnimations(boolean z) {
        this.mSupportsChangeAnimations = z;
    }

    public void onAddFinished(RecyclerView.ViewHolder viewHolder) {
    }

    public void onAddStarting(RecyclerView.ViewHolder viewHolder) {
    }

    public void onMoveFinished(RecyclerView.ViewHolder viewHolder) {
    }

    public void onMoveStarting(RecyclerView.ViewHolder viewHolder) {
    }

    public void onRemoveFinished(RecyclerView.ViewHolder viewHolder) {
    }

    public void onRemoveStarting(RecyclerView.ViewHolder viewHolder) {
    }

    public void onChangeFinished(RecyclerView.ViewHolder viewHolder, boolean z) {
    }

    public void onChangeStarting(RecyclerView.ViewHolder viewHolder, boolean z) {
    }
}
