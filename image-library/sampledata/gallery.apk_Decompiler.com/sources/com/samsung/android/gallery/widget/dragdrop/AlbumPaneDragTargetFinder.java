package com.samsung.android.gallery.widget.dragdrop;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumPaneDragTargetFinder extends DragTargetFinder {
    private void animateScaleDown(View view) {
        if (view != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", new float[]{1.05f, 1.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", new float[]{1.05f, 1.0f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(300);
            animatorSet.start();
        }
    }

    private void animateScaleUp(View view) {
        if (view != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", new float[]{1.0f, 1.05f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", new float[]{1.0f, 1.05f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(300);
            animatorSet.start();
        }
    }

    private MediaItem findItemAtAlbumPane(ViewGroup viewGroup, int i2, int i7) {
        RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R$id.my_recycler_view);
        if (recyclerView != null) {
            int[] iArr = new int[2];
            recyclerView.getLocationInWindow(iArr);
            View findChildViewUnder = recyclerView.findChildViewUnder((float) (i2 - iArr[0]), (float) (i7 - iArr[1]));
            if (findChildViewUnder != null) {
                View view = this.mTargetDropView;
                if (view != findChildViewUnder) {
                    animateScaleDown(view);
                    animateScaleUp(findChildViewUnder);
                    this.mTargetDropView = findChildViewUnder;
                }
                ListViewHolder listViewHolder = (ListViewHolder) recyclerView.findContainingViewHolder(this.mTargetDropView);
                if (listViewHolder != null) {
                    return listViewHolder.getMediaItem();
                }
            }
        }
        return null;
    }

    public MediaItem findItemAtDropPosition(View view, MediaItem mediaItem, int i2, int i7, boolean z) {
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R$id.my_recycler_view);
        ViewGroup viewGroup2 = (ViewGroup) view.findViewById(R$id.albums_left_pane);
        if (viewGroup2 != null && checkPositionInView(viewGroup2, i2, i7)) {
            return findItemAtAlbumPane(viewGroup2, i2, i7);
        }
        if (!z || viewGroup == null || !checkPositionInView(viewGroup, i2, i7)) {
            return null;
        }
        return mediaItem;
    }

    public RecyclerView getAlbumListView(View view) {
        View findViewById = view.findViewById(R$id.albums_left_pane);
        if (findViewById != null) {
            return (RecyclerView) findViewById.findViewById(R$id.my_recycler_view);
        }
        return null;
    }

    public void onViewDragFocusOut(View view) {
        animateScaleDown(view);
    }
}
