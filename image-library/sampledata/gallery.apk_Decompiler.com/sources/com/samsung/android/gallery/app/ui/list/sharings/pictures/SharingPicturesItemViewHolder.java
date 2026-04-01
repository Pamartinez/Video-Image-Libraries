package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeMediaItemHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SharingPicturesItemViewHolder extends ImageViewHolder {
    View mContentDescriptionContainer;
    private final Bitmap mDefaultBitmap;
    int mOwnerMarginBottom;
    int mOwnerMarginBottomSelected;
    TextView mOwnerTextView;
    int mOwnerVisibleMinSize;

    public SharingPicturesItemViewHolder(View view, int i2) {
        super(view, i2);
        this.mDefaultBitmap = ThumbnailLoader.getInstance().getReplacedThumbnail(view.getContext(), (int) R.drawable.gallery_ic_album_no_pic, (int) R.color.album_no_pic_background_color);
        resetImageView();
    }

    private void resetImageView() {
        this.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.mImageView.setImageBitmap(this.mDefaultBitmap);
        this.mImageView.setBackground((Drawable) null);
    }

    private void setOwnerView(int i2) {
        int i7;
        TextView textView = this.mOwnerTextView;
        if (textView != null) {
            if (i2 > this.mOwnerVisibleMinSize) {
                i7 = 0;
            } else {
                i7 = 4;
            }
            textView.setVisibility(i7);
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        String creatorName = MdeMediaItemHelper.getCreatorName(mediaItem);
        this.mOwnerTextView.setText(creatorName);
        this.mOwnerTextView.setContentDescription(creatorName);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mOwnerTextView = (TextView) view.findViewById(R.id.owner);
        this.mContentDescriptionContainer = view.findViewById(R.id.content_description_container);
        this.mOwnerMarginBottom = view.getResources().getDimensionPixelSize(R.dimen.thumbnail_me_margin_bottom_normal);
        this.mOwnerMarginBottomSelected = view.getResources().getDimensionPixelSize(R.dimen.thumbnail_me_margin_bottom_selected);
        this.mOwnerVisibleMinSize = view.getResources().getDimensionPixelSize(R.dimen.enable_show_owner_name_size);
    }

    public View getDecoView(int i2) {
        if (i2 == 41) {
            return this.mContentDescriptionContainer;
        }
        return super.getDecoView(i2);
    }

    public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        setOwnerView(i8 - i2);
        super.onLayoutChange(view, i2, i7, i8, i10, i11, i12, i13, i14);
    }

    public void recycle() {
        super.recycle();
        this.mOwnerTextView.setText((CharSequence) null);
        this.mOwnerTextView.setContentDescription((CharSequence) null);
        resetImageView();
    }

    public void setDecoViewVisibilityInSelectionMode() {
        int i2;
        super.setDecoViewVisibilityInSelectionMode();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mOwnerTextView.getLayoutParams();
        if (isSelectionMode()) {
            i2 = this.mOwnerMarginBottomSelected;
        } else {
            i2 = this.mOwnerMarginBottom;
        }
        marginLayoutParams.bottomMargin = i2;
    }
}
