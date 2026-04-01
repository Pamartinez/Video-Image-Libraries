package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryScreenShotItemViewHolder extends ImageViewHolder {
    private View mBackgroundView;
    private TextView mFilterName;
    private ImageView mIcon;
    private View mIconContainer;
    private View mIconOverlay;

    public CategoryScreenShotItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    private int getIcon(String str) {
        return ScreenShotFilterType.getTypeIcon(str).intValue();
    }

    private void setIcon(String str) {
        Drawable drawable;
        int icon = getIcon(str);
        ImageView imageView = this.mIcon;
        if (icon != -1) {
            drawable = this.itemView.getContext().getDrawable(icon);
        } else {
            drawable = null;
        }
        imageView.setBackground(drawable);
    }

    public boolean applyImageColorFilter() {
        return false;
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.itemView.setOnClickListener(this);
        this.mFilterName.setText(mediaItem.getTitle());
        setIcon(mediaItem.getSubCategory());
    }

    public void bindImage(Bitmap bitmap) {
        ViewUtils.setVisibleOrGone(this.mImageView, false);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mBackgroundView = view.findViewById(R.id.background);
        this.mFilterName = (TextView) view.findViewById(R.id.filter_name);
        this.mIconContainer = view.findViewById(R.id.icon_container);
        this.mIconOverlay = view.findViewById(R.id.iconOverlay);
        this.mIcon = (ImageView) view.findViewById(R.id.icon);
        this.mBackgroundView.setForeground(this.itemView.getContext().getDrawable(R.drawable.recoil_ripple_oval));
    }

    public String getContentDescription() {
        return (String) Optional.ofNullable(getMediaItem()).map(new e(5)).orElse("");
    }

    public ThumbKind getThumbKind() {
        return ThumbKind.SMALL_CROP_KIND;
    }

    public void onClick(View view) {
        onItemClickInternal(0);
    }

    public void recycle() {
        super.recycle();
        this.mBackgroundView.setForeground((Drawable) null);
        this.mImageView.setBackground((Drawable) null);
    }

    public void addThumbnailBorder(Drawable drawable) {
    }
}
