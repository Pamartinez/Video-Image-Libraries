package com.samsung.android.gallery.widget.listviewholder;

import Fb.b;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.l;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.abstraction.SharedViewElement;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ListViewHolder extends RecyclerView.ViewHolder implements SharedViewElement, IPinchViewHolder {
    /* access modifiers changed from: protected */
    public final String TAG = getClass().getSimpleName();
    private int mChildItemCount;
    private int mFakePosition = -1;
    protected MediaItem mMediaItem;
    protected OnItemTouchListener mOnAdvancedMouseDragListener;
    protected OnItemTouchListener mOnHorizontalDrag;
    protected OnHoverListener mOnHoverListener;
    protected OnImageBindListener mOnImageBindListener;
    protected OnItemBindListener mOnItemBindListener;
    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;
    protected OnItemSecondaryClickListener mOnItemSecondaryClickListener;
    private int mRoundMode = 0;
    protected int mSupportDecoItemType = -1;
    protected boolean mTempMode;
    private final int mViewType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnConcatThumbListener {
        void onAccessibilityClicked(View view, int i2, int i7);

        void onClicked(int i2, int i7, float f, float f5, float f8, float f10);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnHoverListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnImageBindListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemBindListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemClickListener {
        void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemLongClickListener {
        boolean onItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemSecondaryClickListener {
        void onItemSecondaryClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemTouchListener {
        boolean onItemTouch(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SelectListener {
        int getSelectedCount();

        boolean isListMoveMode();

        boolean isListSelectionMode();

        boolean isListSingSelectionMode();

        void onTouchUp();
    }

    public ListViewHolder(View view, int i2) {
        super(view);
        this.mViewType = i2;
        bindView(view);
    }

    public void bind(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        OnItemBindListener onItemBindListener = this.mOnItemBindListener;
        if (onItemBindListener != null) {
            ((b) onItemBindListener).b.updateCheckboxOnBindMediaItem(this, mediaItem);
        }
    }

    public abstract void bindView(View view);

    public boolean equalBitmap(Bitmap bitmap) {
        return false;
    }

    public Bitmap getBitmap() {
        return null;
    }

    public CheckBox getCheckbox() {
        return null;
    }

    public int getChildItemCount() {
        return this.mChildItemCount;
    }

    public float getCornerRadius() {
        return 0.0f;
    }

    public TextView getCountView() {
        return null;
    }

    public TextView getDateTextView() {
        return null;
    }

    public View getDecoView(int i2) {
        return null;
    }

    public View getDividerView() {
        return null;
    }

    public ListViewHolder[] getFolderImageHolders() {
        return null;
    }

    public ImageView getImage() {
        return null;
    }

    public final View getItemView() {
        return this.itemView;
    }

    public View getListHorizontalDividerView() {
        return null;
    }

    public TextView getLocationTextView() {
        return null;
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public final View getRootView() {
        return this.itemView;
    }

    public int getRoundMode() {
        return this.mRoundMode;
    }

    public View getScalableView() {
        return null;
    }

    public TextView getSubTitleView() {
        return null;
    }

    public int getSupportDecoItemType() {
        return this.mSupportDecoItemType;
    }

    public ThumbKind getThumbKind() {
        return null;
    }

    public Drawable getThumbnailBorder() {
        return null;
    }

    public TextView getTitleView() {
        return null;
    }

    public final int getViewPosition() {
        int i2 = this.mFakePosition;
        if (i2 != -1) {
            return i2;
        }
        try {
            return getBindingAdapterPosition();
        } catch (NullPointerException unused) {
            return getBindingAdapterPosition();
        } catch (IndexOutOfBoundsException unused2) {
            return this.mFakePosition;
        }
    }

    public final int getViewType() {
        return this.mViewType;
    }

    public boolean hasAlbumTypeIcon() {
        return false;
    }

    public boolean hasBitmap() {
        return false;
    }

    public boolean hasCheckbox() {
        return false;
    }

    public boolean hasEnoughSpaceForDuration(MediaItem mediaItem, int i2, int i7) {
        if (i2 >= 0) {
            return true;
        }
        return false;
    }

    public boolean hasImageView() {
        return false;
    }

    public boolean isAlbumSyncEnable() {
        return false;
    }

    public boolean isCheckBoxEnabled() {
        return false;
    }

    public boolean isDurationAvailable(MediaItem mediaItem, int i2, int i7) {
        if (i2 >= 0) {
            return true;
        }
        return false;
    }

    public boolean isFake() {
        if (this.mFakePosition != -1) {
            return true;
        }
        return false;
    }

    public boolean isFolder() {
        return false;
    }

    public final boolean isRecycled(int i2) {
        int viewPosition;
        if (this.mMediaItem == null || (viewPosition = getViewPosition()) < 0 || viewPosition != i2) {
            return true;
        }
        return false;
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        return false;
    }

    public final boolean onAdvancedMouseDrag(int i2) {
        OnItemTouchListener onItemTouchListener = this.mOnAdvancedMouseDragListener;
        if (onItemTouchListener != null) {
            return onItemTouchListener.onItemTouch(this, getViewPosition(), getMediaItem(), i2);
        }
        return false;
    }

    public final boolean onHorizontalDrag(int i2) {
        OnItemTouchListener onItemTouchListener = this.mOnHorizontalDrag;
        if (onItemTouchListener != null) {
            return onItemTouchListener.onItemTouch(this, getViewPosition(), getMediaItem(), i2);
        }
        return false;
    }

    public final boolean onHoverInternal(MotionEvent motionEvent) {
        OnHoverListener onHoverListener = this.mOnHoverListener;
        if (onHoverListener != null) {
            return ((l) onHoverListener).a(this, motionEvent);
        }
        return false;
    }

    public final void onItemClickInternal(int i2) {
        int viewPosition = getViewPosition();
        if (viewPosition == -1) {
            Log.e(this.TAG, "item is not set, ignore click");
        } else if (this.mOnItemClickListener != null) {
            MediaItem mediaItem = getMediaItem();
            if (mediaItem != null) {
                this.mOnItemClickListener.onItemClick(this, viewPosition, mediaItem, i2);
            } else {
                Log.e(this.TAG, "ignore onClick. item is not ready");
            }
        }
    }

    public final boolean onItemLongClickInternal(int i2) {
        OnItemLongClickListener onItemLongClickListener = this.mOnItemLongClickListener;
        if (onItemLongClickListener != null) {
            return onItemLongClickListener.onItemLongClick(this, getViewPosition(), getMediaItem(), i2);
        }
        return true;
    }

    public final void onItemSecondaryClickInternal(int i2, PointF pointF, MotionEvent motionEvent) {
        OnItemSecondaryClickListener onItemSecondaryClickListener = this.mOnItemSecondaryClickListener;
        if (onItemSecondaryClickListener != null) {
            onItemSecondaryClickListener.onItemSecondaryClick(this, getViewPosition(), getMediaItem(), i2, pointF, motionEvent);
        }
    }

    public void recycle() {
        this.mMediaItem = null;
        this.mOnItemClickListener = null;
        this.mOnItemLongClickListener = null;
        this.mOnItemSecondaryClickListener = null;
        this.mOnItemBindListener = null;
        this.mOnImageBindListener = null;
        this.mOnHoverListener = null;
        this.mOnHorizontalDrag = null;
        this.mOnAdvancedMouseDragListener = null;
        this.mFakePosition = -1;
        this.mRoundMode = 0;
        this.mSupportDecoItemType = -1;
    }

    public boolean requireThumbnail() {
        return false;
    }

    public void setCheckboxEnabled(boolean z) {
    }

    public void setChildItemCount(int i2) {
        this.mChildItemCount = i2;
    }

    public void setFakePosition(int i2) {
        this.mFakePosition = i2;
    }

    public final void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public final void setImageDrawable(ImageView imageView, Drawable drawable) {
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public final void setOnAdvancedMouseDragListener(OnItemTouchListener onItemTouchListener) {
        this.mOnAdvancedMouseDragListener = onItemTouchListener;
    }

    public final void setOnBindImageListener(OnImageBindListener onImageBindListener) {
        this.mOnImageBindListener = onImageBindListener;
    }

    public void setOnBindMediaItem(OnItemBindListener onItemBindListener) {
        this.mOnItemBindListener = onItemBindListener;
    }

    public final void setOnHorizontalDragListener(OnItemTouchListener onItemTouchListener) {
        this.mOnHorizontalDrag = onItemTouchListener;
    }

    public final void setOnHoverListener(OnHoverListener onHoverListener) {
        this.mOnHoverListener = onHoverListener;
    }

    public final void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public final void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public final void setOnSecondaryClickListener(OnItemSecondaryClickListener onItemSecondaryClickListener) {
        this.mOnItemSecondaryClickListener = onItemSecondaryClickListener;
    }

    public void setRoundMode(int i2) {
        this.mRoundMode = i2;
    }

    public void setSupportDecoItemType(int i2) {
        this.mSupportDecoItemType = i2;
    }

    public void setTempMode() {
        this.mTempMode = true;
    }

    public String toString() {
        Object obj;
        MediaItem mediaItem = this.mMediaItem;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.TAG);
        sb2.append("{");
        sb2.append(getViewPosition());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mViewType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (mediaItem != null) {
            obj = Long.valueOf(mediaItem.getFileId());
        } else {
            obj = "null";
        }
        sb2.append(obj);
        sb2.append("}");
        return sb2.toString();
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        return false;
    }

    public boolean useSelectListener() {
        return false;
    }

    public Bitmap getBitmap(boolean z) {
        return null;
    }

    public void setCheckboxEnabled(int i2, boolean z) {
        setSupportDecoItemType(i2);
    }

    public void recycleToBackup() {
    }

    public void resetViewProperty() {
    }

    public void restoreThumbnailBorder() {
    }

    public void setAlbumSyncView() {
    }

    public void skipCheckboxAnimation() {
    }

    public void updateDecoItemViews() {
    }

    public void updateMatrix() {
    }

    public void addThumbnailBorder(Drawable drawable) {
    }

    public void bindImage(Bitmap bitmap) {
    }

    public void enableBlur(boolean z) {
    }

    public void inflateFolderView(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setEnable(boolean z) {
    }

    public void setFocusedFilterOnAdvancedMouseEvent(boolean z) {
    }

    public void setImageUid(Object obj) {
    }

    public void setNewLabel(int i2) {
    }

    public void setOnConcatThumbListener(OnConcatThumbListener onConcatThumbListener) {
    }

    public void setSelectListener(SelectListener selectListener) {
    }

    public void setSelectable(boolean z) {
    }

    public void setShowDeco(boolean z) {
    }

    public void setThumbKind(ThumbKind thumbKind) {
    }

    public void setThumbnailBackgroundColor(int i2) {
    }

    public void updateDuration(int i2) {
    }

    public void handleEvent(int i2, Object... objArr) {
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        return this;
    }

    public void updateBlurInfo(int i2, int i7) {
    }
}
