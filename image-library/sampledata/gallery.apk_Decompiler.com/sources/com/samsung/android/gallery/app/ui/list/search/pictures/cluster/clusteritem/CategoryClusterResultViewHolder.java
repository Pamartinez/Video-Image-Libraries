package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryClusterResultViewHolder extends ImageTitleViewHolder {
    private TextView mCountView;
    private TextView mDateView;

    public CategoryClusterResultViewHolder(View view, int i2) {
        super(view, i2);
    }

    private String getCount(MediaItem mediaItem) {
        if (mediaItem != null) {
            return Integer.toString(mediaItem.getCount());
        }
        return "";
    }

    private int getDimensionPixelOffset(int i2) {
        return getContext().getResources().getDimensionPixelOffset(i2);
    }

    private void setDate(MediaItem mediaItem) {
        if ("pdc_cluster".equals((String) mediaItem.getExtra(ExtrasID.CLUSTER_TYPE))) {
            long[] jArr = (long[]) mediaItem.getExtra(ExtrasID.PDC_TIME_INFO);
            String eventDatePeriod = TimeUtil.getEventDatePeriod(jArr[0], jArr[1], 50);
            this.mDateView.setText(eventDatePeriod);
            ViewUtils.setVisibleOrGone(this.mDateView, !TextUtils.isEmpty(eventDatePeriod));
        }
    }

    private void setThumbnailShapeByViewType() {
        setThumbnailShape(1, (float) getDimensionPixelOffset(R.dimen.search_cluster_results_category_item_radius));
    }

    public void bind(MediaItem mediaItem) {
        Log.d("CarouselClusterResultViewHolder", "bind");
        super.bind(mediaItem);
        setThumbnailShapeByViewType();
        TextView textView = this.mCountView;
        if (textView != null) {
            textView.setText(getCount(mediaItem));
        }
        if (this.mDateView != null) {
            setDate(mediaItem);
        }
    }

    public void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
        addThumbnailBorder(getContext().getDrawable(R.drawable.search_thumbnail_border));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mDecoViewLayout = (ViewGroup) view.findViewById(R.id.deco_view_layout);
        this.mCountView = (TextView) view.findViewById(R.id.result_count);
        this.mDateView = (TextView) view.findViewById(R.id.result_date);
        ViewUtils.setVisibleOrGone(view.findViewById(R.id.search_cluster_divider_arrow), true);
    }

    public String getTitleText(MediaItem mediaItem) {
        if (mediaItem != null) {
            return mediaItem.getTitle();
        }
        return null;
    }
}
