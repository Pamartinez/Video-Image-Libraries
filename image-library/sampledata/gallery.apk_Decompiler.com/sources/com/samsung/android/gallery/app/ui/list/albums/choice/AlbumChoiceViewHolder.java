package com.samsung.android.gallery.app.ui.list.albums.choice;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumChoiceViewHolder extends AlbumTitleCountViewHolder {
    private boolean mImageColorFilterEnabled = true;
    protected ViewGroup mTitleContainer;

    public AlbumChoiceViewHolder(View view, int i2) {
        super(view, i2);
        setUseThumbnailCheckbox(false);
    }

    private boolean hasDisabledAlpha() {
        if (this.itemView.getAlpha() < 1.0f) {
            return true;
        }
        return false;
    }

    public boolean applyImageColorFilter() {
        return this.mImageColorFilterEnabled;
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mTitleContainer = (ViewGroup) view.findViewById(R.id.title_container);
    }

    public void enableImageColorFilter(boolean z) {
        this.mImageColorFilterEnabled = z;
    }

    public String getContentDescription() {
        if (hasDisabledAlpha()) {
            return getString(R.string.disabled);
        }
        if (ViewHolderValue.isHeader(getViewType())) {
            return getString(R.string.create_album_dialog) + ArcCommonLog.TAG_COMMA + getString(R.string.speak_button);
        }
        return getMediaItem().getDisplayName() + " " + getString(R.string.album) + ArcCommonLog.TAG_COMMA + String.format(getString(R.string.speak_folder_name_n_items), new Object[]{Integer.valueOf(getMediaItem().getCount())});
    }

    public void setEnable(boolean z) {
        float f;
        ImageView imageView = this.mImageView;
        float f5 = 0.5f;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.5f;
        }
        ViewUtils.setAlpha(imageView, f);
        ViewGroup viewGroup = this.mTitleContainer;
        if (z) {
            f5 = 1.0f;
        }
        ViewUtils.setAlpha(viewGroup, f5);
        this.itemView.setEnabled(z);
        CheckBox checkBox = this.mCheckboxView;
        if (checkBox != null) {
            checkBox.setEnabled(z);
        }
    }
}
