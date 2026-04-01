package com.samsung.android.gallery.app.ui.list.stories.hiderule.v1;

import a6.C0419b;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideRuleSceneItemViewHolder extends ListViewHolder {
    TextView mCount;
    View mDimView;
    ImageView mImage;

    public HideRuleSceneItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bindCountView(int i2, boolean z) {
        if (!z || i2 <= 0) {
            ViewUtils.setVisibility(this.mCount, 8);
        } else {
            TextView textView = this.mCount;
            textView.setText(StringCompat.toReadableCount(i2) + "+");
            ViewUtils.setVisibility(this.mCount, 0);
        }
        setDimViewEnabled(z);
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
        ImageView imageView = (ImageView) view.findViewById(R.id.thumbnail);
        this.mImage = imageView;
        imageView.setOnClickListener(new C0419b(11, this));
        this.mCount = (TextView) view.findViewById(R.id.hide_scene_item_count);
        this.mDimView = view.findViewById(R.id.dim_view);
    }

    public void onClickThumbnailView(View view) {
        onItemClickInternal(1003);
    }

    public void recycle() {
        super.recycle();
        ViewUtils.setVisibility(this.mImage, 8);
        ViewUtils.setVisibility(this.mCount, 8);
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
}
