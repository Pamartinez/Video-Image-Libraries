package com.samsung.android.gallery.widget.dragdrop;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.R$anim;
import com.samsung.android.gallery.widget.SquareImageView;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsDropTargetFinder {
    private int mDropIndex = -1;
    private View mTargetDropView;

    private boolean isFolderIndex(ListViewHolder listViewHolder) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem == null || mediaItem.isFolder()) {
            return true;
        }
        return false;
    }

    private boolean isTouchWithinImageView(View view, int i2, int i7) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i8 = 0; i8 < viewGroup.getChildCount(); i8++) {
                View childAt = viewGroup.getChildAt(i8);
                if (childAt != null && childAt.getVisibility() == 0) {
                    if (!(childAt instanceof ViewGroup)) {
                        Rect rect = new Rect();
                        view.getGlobalVisibleRect(rect);
                        if ((childAt instanceof SquareImageView) && rect.contains(i2, i7)) {
                            return true;
                        }
                    } else if (((ViewGroup) childAt).getChildCount() > 0 && isTouchWithinImageView(childAt, i2, i7)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void updateDropIndex(int i2, View view) {
        View view2;
        int i7 = this.mDropIndex;
        if (i7 != i2 && (view2 = this.mTargetDropView) != view) {
            if (!(i7 == -1 || view2 == null)) {
                animateView(view2, R$anim.scale_down);
            }
            if (!(i2 == -1 || view == null)) {
                animateView(view, R$anim.scale_up);
            }
            this.mDropIndex = i2;
            this.mTargetDropView = view;
        }
    }

    public void animateView(View view, int i2) {
        if (view != null) {
            view.clearAnimation();
            view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), i2));
        }
    }

    public void findDropView(int i2, int i7, GalleryListView galleryListView) {
        int childCount = galleryListView.getChildCount();
        int i8 = -1;
        View view = null;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = galleryListView.getChildAt(i10);
            ListViewHolder listViewHolder = (ListViewHolder) galleryListView.getChildViewHolder(childAt);
            if (!isFolderIndex(listViewHolder) && isTouchWithinImageView(childAt, i2, i7)) {
                i8 = listViewHolder.getAdapterPosition();
                view = childAt;
            }
        }
        updateDropIndex(i8, view);
    }

    public MediaItem getTargetItem(GalleryListView galleryListView) {
        GalleryListAdapter adapter = galleryListView.getAdapter();
        int i2 = this.mDropIndex;
        if (i2 > -1 && adapter != null) {
            return adapter.getMediaItemSync(i2);
        }
        return null;
    }

    public void resetDrag() {
        updateDropIndex(-1, (View) null);
        this.mTargetDropView = null;
    }
}
