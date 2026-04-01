package com.samsung.android.gallery.app.ui.viewholders;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageTitleViewHolder extends PreviewViewHolder {
    protected TextView mTitleText;

    public ImageTitleViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (TextUtils.isEmpty(getTitleText(mediaItem))) {
            this.mTitleText.setVisibility(8);
            return;
        }
        this.mTitleText.setVisibility(0);
        setTitleText(mediaItem);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mTitleText = (TextView) view.findViewById(R.id.title);
    }

    public String getContentDescription() {
        try {
            return this.mTitleText.getText().toString();
        } catch (Exception unused) {
            Log.w(this.TAG, "getContentDescription failed");
            return "";
        }
    }

    public String getTitleText(MediaItem mediaItem) {
        if (mediaItem != null) {
            return mediaItem.getTitle();
        }
        return null;
    }

    public TextView getTitleView() {
        return this.mTitleText;
    }

    public void recycle() {
        super.recycle();
        this.mTitleText.setText((CharSequence) null);
    }

    public void setTitleText(MediaItem mediaItem) {
        this.mTitleText.setText(getTitleText(mediaItem));
    }
}
