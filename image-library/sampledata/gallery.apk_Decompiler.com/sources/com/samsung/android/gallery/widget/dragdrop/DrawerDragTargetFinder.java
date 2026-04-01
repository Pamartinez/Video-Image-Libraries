package com.samsung.android.gallery.widget.dragdrop;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.drawer.DrawerDragView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerDragTargetFinder extends DragTargetFinder {
    private boolean mPreviousCheckPositionInView;
    private RecyclerView mRecyclerView;

    private void expandItemIfPossible() {
        DrawerDragView targetViewHolder = getTargetViewHolder(this.mTargetDropView);
        if (targetViewHolder != null && targetViewHolder.supportExpand() && !targetViewHolder.isExpanded()) {
            targetViewHolder.getLayout().callOnClick();
        }
    }

    private MediaItem findItemInDrawer(ViewGroup viewGroup, int i2, int i7) {
        View findViewWithTag;
        RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R$id.drawer_recyclerView);
        this.mRecyclerView = recyclerView;
        if (recyclerView != null) {
            int[] iArr = new int[2];
            recyclerView.getLocationInWindow(iArr);
            View findChildViewUnder = this.mRecyclerView.findChildViewUnder((float) (i2 - iArr[0]), (float) (i7 - iArr[1]));
            if (findChildViewUnder == null || (findViewWithTag = findChildViewUnder.findViewWithTag("drawer_tab_item")) == null || !isItemEnabled(findViewWithTag)) {
                return null;
            }
            View view = this.mTargetDropView;
            if (view != findViewWithTag) {
                onTargetViewChanged(view, findViewWithTag);
                this.mTargetDropView = findViewWithTag;
            }
            expandItemIfPossible();
            return getTargetItem();
        }
        return null;
    }

    private MediaItem getTargetItem() {
        DrawerDragView targetViewHolder = getTargetViewHolder(this.mTargetDropView);
        if (targetViewHolder == null || targetViewHolder.getMediaItem() == null) {
            return null;
        }
        return targetViewHolder.getMediaItem();
    }

    private DrawerDragView getTargetViewHolder(View view) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return (DrawerDragView) recyclerView.findContainingViewHolder(view);
        }
        return null;
    }

    private boolean isItemEnabled(View view) {
        DrawerDragView targetViewHolder = getTargetViewHolder(view);
        if (targetViewHolder == null) {
            return false;
        }
        if (targetViewHolder.supportDrop()) {
            return true;
        }
        if (!targetViewHolder.supportExpand() || targetViewHolder.isExpanded()) {
            return false;
        }
        return true;
    }

    private void onTargetViewChanged(View view, View view2) {
        onViewDragFocusOut(view);
        onViewDragFocusIn(view2);
    }

    private void onViewDragFocusChange(View view, boolean z) {
        DrawerDragView targetViewHolder;
        if (view != null && (targetViewHolder = getTargetViewHolder(view)) != null) {
            targetViewHolder.setDragFocused(z);
            targetViewHolder.updateForeground();
        }
    }

    public boolean checkPositionInView(View view, int i2, int i7) {
        boolean checkPositionInView = super.checkPositionInView(view, i2, i7);
        if (this.mPreviousCheckPositionInView && !checkPositionInView) {
            onViewDragFocusOut(this.mTargetDropView);
            this.mTargetDropView = null;
        }
        this.mPreviousCheckPositionInView = checkPositionInView;
        return checkPositionInView;
    }

    public MediaItem findItemAtDropPosition(View view, MediaItem mediaItem, int i2, int i7, boolean z) {
        ViewGroup viewGroup;
        View rootView = view.getRootView();
        if (rootView != null) {
            viewGroup = (ViewGroup) rootView.findViewWithTag("drawer_layout");
        } else {
            viewGroup = null;
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R$id.my_recycler_view);
        if (viewGroup != null && checkPositionInView(viewGroup, i2, i7)) {
            return findItemInDrawer(viewGroup, i2, i7);
        }
        if (!z || recyclerView == null || !checkPositionInView(recyclerView, i2, i7)) {
            return null;
        }
        return mediaItem;
    }

    public RecyclerView getAlbumListView(View view) {
        return this.mRecyclerView;
    }

    public void onViewDragFocusIn(View view) {
        onViewDragFocusChange(view, true);
    }

    public void onViewDragFocusOut(View view) {
        onViewDragFocusChange(view, false);
    }
}
