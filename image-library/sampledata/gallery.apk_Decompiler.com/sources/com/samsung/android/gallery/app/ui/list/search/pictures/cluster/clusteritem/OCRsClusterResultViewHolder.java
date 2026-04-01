package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OCRsClusterResultViewHolder extends ClusterResultViewHolder {
    public OCRsClusterResultViewHolder(EventContext eventContext, View view, int i2) {
        super(eventContext, view, i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.text_extraction_button_icon);
        ViewUtils.setBackgroundResource(imageView, R.drawable.gallery_detail_ai_icon_bg);
        ViewUtils.setVisibleOrGone(imageView, true);
    }

    public String getDurationText(MediaItem mediaItem) {
        return Integer.toString(((Integer) this.mHandler.getBlackboard().read("data://user/SearchClusterOCRCount", 0)).intValue());
    }

    public String getTitleText(MediaItem mediaItem) {
        String str = (String) this.mHandler.getBlackboard().read("data://user/SearchClusterOCRTitle", null);
        if (str == null) {
            return "";
        }
        return str.replace("'", "");
    }
}
