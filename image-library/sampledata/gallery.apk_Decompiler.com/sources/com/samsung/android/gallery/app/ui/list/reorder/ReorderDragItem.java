package com.samsung.android.gallery.app.ui.list.reorder;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ReorderDragItem {
    private final boolean mBlockSidewaysMovement;
    private View mDragObject = null;
    private final MediaItem mDraggedItem;
    /* access modifiers changed from: private */
    public boolean mEndDragAnimationOngoing = false;
    private int mInitPosX = 0;
    private final FrameLayout mRootLayout;
    private float mTouchOffsetX = -1.0f;
    private float mTouchOffsetY = -1.0f;
    private final ListViewHolder mViewHolder;

    public ReorderDragItem(FrameLayout frameLayout, RecyclerView recyclerView, ListViewHolder listViewHolder, AlbumsBaseLayoutManager albumsBaseLayoutManager, boolean z) {
        this.mRootLayout = frameLayout;
        this.mViewHolder = listViewHolder;
        this.mDraggedItem = listViewHolder.getMediaItem();
        this.mBlockSidewaysMovement = z;
        initDragObject(recyclerView, listViewHolder, albumsBaseLayoutManager);
    }

    private ListViewHolder createListViewHolder(RecyclerView recyclerView, int i2, AlbumsBaseLayoutManager albumsBaseLayoutManager) {
        RecyclerView.RecycledViewPool recycledViewPool = recyclerView.getRecycledViewPool();
        if (recycledViewPool == null || recycledViewPool.getRecycledViewCount(i2) <= 0) {
            return albumsBaseLayoutManager.createListViewHolder(recyclerView, i2);
        }
        return (ListViewHolder) recycledViewPool.getRecycledView(i2);
    }

    private void disableCheckBoxAnimation(ListViewHolder listViewHolder, ListViewHolder listViewHolder2) {
        CheckBox checkbox;
        if (listViewHolder.getCheckbox() != null && listViewHolder.getCheckbox().isChecked() && (checkbox = listViewHolder2.getCheckbox()) != null) {
            checkbox.setChecked(true);
            checkbox.jumpDrawablesToCurrentState();
        }
    }

    private Drawable getBackgroundDrawable() {
        int dimensionPixelOffset;
        Context context = Blackboard.getContext();
        if (context == null) {
            return null;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        Resources resources = context.getResources();
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.album_view_corner_radius_grid_blur);
        } else {
            dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mx_albums_reorder_item_border_radius);
        }
        gradientDrawable.setCornerRadius((float) dimensionPixelOffset);
        gradientDrawable.setStroke(resources.getDimensionPixelOffset(R.dimen.mx_albums_reorder_item_border_tickness), context.getColor(R.color.similar_group_icon_on_color));
        gradientDrawable.setColor(context.getColor(R.color.default_background));
        return gradientDrawable;
    }

    private void initDragObject(RecyclerView recyclerView, ListViewHolder listViewHolder, AlbumsBaseLayoutManager albumsBaseLayoutManager) {
        View rootView = listViewHolder.getRootView();
        ListViewHolder createListViewHolder = createListViewHolder(recyclerView, listViewHolder.getViewType(), albumsBaseLayoutManager);
        disableCheckBoxAnimation(listViewHolder, createListViewHolder);
        View rootView2 = createListViewHolder.getRootView();
        this.mDragObject = rootView2;
        rootView2.setAlpha(0.7f);
        this.mDragObject.setBackground(getBackgroundDrawable());
        this.mRootLayout.addView(this.mDragObject);
        updateViewSize(albumsBaseLayoutManager, listViewHolder.getViewType());
        createListViewHolder.setFakePosition(listViewHolder.getAdapterPosition());
        albumsBaseLayoutManager.bindHolder(createListViewHolder, listViewHolder.getAdapterPosition());
        this.mInitPosX = (int) DragUtil.getRootX(rootView, this.mRootLayout);
        DragUtil.measureView(this.mDragObject, this.mInitPosX, (int) DragUtil.getRootY(rootView, this.mRootLayout), rootView.getWidth(), rootView.getHeight());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$redrawCheckboxParent$0(CheckBox checkBox) {
        if (checkBox.getParent() != null) {
            checkBox.getParent().requestLayout();
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.function.Consumer, java.lang.Object] */
    /* access modifiers changed from: private */
    public void redrawCheckboxParent() {
        Optional.ofNullable(this.mViewHolder.getCheckbox()).ifPresent(new Object());
    }

    private void updateViewSize(AlbumsBaseLayoutManager albumsBaseLayoutManager, int i2) {
        if (i2 == 1) {
            int extraStartPadding = albumsBaseLayoutManager.getExtraStartPadding(albumsBaseLayoutManager.getSpanCount());
            this.mDragObject.getLayoutParams().width = albumsBaseLayoutManager.getHintWidthSpace(albumsBaseLayoutManager.getSpanCount());
            this.mDragObject.getLayoutParams().height = -2;
            View view = this.mDragObject;
            float x9 = view.getX();
            if (Features.isEnabled(Features.IS_RTL)) {
                extraStartPadding = -extraStartPadding;
            }
            view.setX(x9 + ((float) extraStartPadding));
            return;
        }
        albumsBaseLayoutManager.updateViewSize(this.mDragObject, i2, albumsBaseLayoutManager.getSpanCount());
    }

    public int getViewPosition() {
        return this.mViewHolder.getViewPosition();
    }

    public void initTouchOffset(RecyclerView recyclerView, ListViewHolder listViewHolder, float f, float f5, boolean z) {
        int i2;
        View rootView = listViewHolder.getRootView();
        float rootX = DragUtil.getRootX(rootView, recyclerView);
        float rootY = DragUtil.getRootY(rootView, recyclerView);
        float f8 = f - rootX;
        int i7 = 0;
        if (z) {
            i2 = 20;
        } else {
            i2 = 0;
        }
        this.mTouchOffsetX = f8 + ((float) i2);
        float f10 = f5 - rootY;
        if (z) {
            i7 = -20;
        }
        this.mTouchOffsetY = f10 + ((float) i7);
    }

    public boolean isAnimating() {
        return this.mEndDragAnimationOngoing;
    }

    public void postOnMeasure(Runnable runnable) {
        ViewUtils.post(this.mDragObject, runnable);
    }

    public void resetDragObject() {
        ViewUtils.removeView(this.mRootLayout, this.mDragObject);
        this.mDragObject = null;
    }

    public void setPosition(float f, float f5) {
        this.mDragObject.bringToFront();
        if (!this.mBlockSidewaysMovement) {
            this.mDragObject.setX(f - this.mTouchOffsetX);
        } else {
            this.mDragObject.setX((float) this.mInitPosX);
        }
        this.mDragObject.setY(f5 - this.mTouchOffsetY);
    }

    public void setVisibility(boolean z) {
        int i2;
        View view = this.mDragObject;
        if (z) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        view.setVisibility(i2);
    }

    public void startDragEndAnimation(View view, final Animator.AnimatorListener animatorListener) {
        TranslationAnimator translationAnimator = new TranslationAnimator(this.mDragObject, DragUtil.createRootRect(this.mDragObject, this.mRootLayout), DragUtil.createRootRect(view, this.mRootLayout));
        translationAnimator.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                ReorderDragItem.this.redrawCheckboxParent();
                ReorderDragItem.this.mEndDragAnimationOngoing = false;
                animatorListener.onAnimationEnd(animator);
            }

            public void onAnimationStart(Animator animator) {
                ReorderDragItem.this.mEndDragAnimationOngoing = true;
            }
        });
        translationAnimator.setDuration(StatusCodes.INPUT_MISSING);
        translationAnimator.start();
    }
}
