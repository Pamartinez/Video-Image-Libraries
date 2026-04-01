package com.samsung.android.gallery.app.ui.list.suggestions.remaster;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterItemViewHolder extends ImageViewHolder {
    View mGradientView;
    TextView mNewResolutionView;
    TextView mOriginResolutionView;
    View mThumbnailContainer;
    View mTitleContainer;

    public RemasterItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    private boolean compareResolution(int i2, int i7, int i8, int i10) {
        if (i2 == i8 && i7 == i10) {
            return true;
        }
        return false;
    }

    public void bindInfo(MediaItem mediaItem, boolean z) {
        View view = this.mTitleContainer;
        if (view != null && this.mGradientView != null) {
            ViewUtils.setVisibleOrInvisible(view, z);
            if (z) {
                setResolutionInfo(mediaItem);
            }
            ViewUtils.setVisibleOrInvisible(this.mGradientView, z);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mOriginResolutionView = (TextView) view.findViewById(R.id.original_resolution);
        this.mNewResolutionView = (TextView) view.findViewById(R.id.new_resolution);
        this.mTitleContainer = view.findViewById(R.id.title_container);
        this.mThumbnailContainer = view.findViewById(R.id.thumbnail_container);
        this.mGradientView = view.findViewById(R.id.gradient_view);
    }

    public void recycle() {
        TextView textView = this.mOriginResolutionView;
        if (textView != null) {
            textView.setText((CharSequence) null);
        }
        TextView textView2 = this.mNewResolutionView;
        if (textView2 != null) {
            textView2.setText((CharSequence) null);
        }
        super.recycle();
    }

    public void setResolutionInfo(MediaItem mediaItem) {
        int widthInDB = mediaItem.getWidthInDB();
        int heightInDB = mediaItem.getHeightInDB();
        int remasterWidth = MediaItemSuggest.getRemasterWidth(mediaItem);
        int remasterHeight = MediaItemSuggest.getRemasterHeight(mediaItem);
        boolean compareResolution = compareResolution(widthInDB, heightInDB, remasterWidth, remasterHeight);
        ViewUtils.setVisibleOrInvisible(this.mTitleContainer, !compareResolution);
        if (!compareResolution) {
            String string = getContext().getString(R.string.original_resolution, new Object[]{Integer.valueOf(widthInDB), Integer.valueOf(heightInDB)});
            String string2 = getContext().getString(R.string.new_resolution, new Object[]{Integer.valueOf(remasterWidth), Integer.valueOf(remasterHeight)});
            TextView textView = this.mOriginResolutionView;
            if (textView != null) {
                textView.setText(string);
            }
            TextView textView2 = this.mNewResolutionView;
            if (textView2 != null) {
                textView2.setText(string2);
            }
        }
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ViewUtils.setViewShape(this.mThumbnailContainer, i2, f);
        return this;
    }
}
