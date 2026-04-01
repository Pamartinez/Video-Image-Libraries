package com.samsung.android.gallery.app.ui.list.suggestions;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionsImageViewHolder extends ListViewHolder {
    TextView mCount;
    View mDimView;
    ImageView mImage;
    View mPlayIcon;
    View mThumbnailContainer;

    public SuggestionsImageViewHolder(View view, int i2) {
        super(view, i2);
    }

    private boolean isVideoType(MediaItem mediaItem) {
        return MediaItemSuggest.isHighLight(mediaItem);
    }

    public void bind(MediaItem mediaItem) {
        int i2;
        super.bind(mediaItem);
        ImageView imageView = this.mImage;
        Context context = this.itemView.getContext();
        if (isVideoType(mediaItem)) {
            i2 = R.string.video;
        } else {
            i2 = R.string.image;
        }
        ViewUtils.setContentDescription(imageView, context.getString(i2));
    }

    public void bindCountView(int i2) {
        if (i2 > 0) {
            TextView textView = this.mCount;
            textView.setText(StringCompat.toReadableCount(i2) + "+");
            ViewUtils.setVisibility(this.mCount, 0);
            return;
        }
        ViewUtils.setVisibility(this.mCount, 8);
    }

    public void bindImage(Bitmap bitmap) {
        int i2;
        this.mImage.setImageBitmap(bitmap);
        ImageView imageView = this.mImage;
        if (bitmap == null) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        ViewUtils.setVisibility(imageView, i2);
    }

    public void bindView(View view) {
        this.mImage = (ImageView) view.findViewById(R.id.thumbnail);
        this.mCount = (TextView) view.findViewById(R.id.suggestions_count);
        this.mDimView = view.findViewById(R.id.dim_view);
        this.mPlayIcon = view.findViewById(R.id.play_icon);
        this.mThumbnailContainer = view.findViewById(R.id.thumbnail_container);
    }

    public void recycle() {
        super.recycle();
        ViewUtils.setVisibility(this.mThumbnailContainer, 8);
    }

    public void setDimViewEnabled(boolean z) {
        int i2;
        View view = this.mDimView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        view.setVisibility(i2);
    }

    public void setPlayIconVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mPlayIcon, z);
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ViewUtils.setViewShape(this.mThumbnailContainer, i2, f);
        return this;
    }
}
