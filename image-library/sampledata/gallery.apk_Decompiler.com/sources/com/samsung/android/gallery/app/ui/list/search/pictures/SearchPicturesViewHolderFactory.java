package com.samsung.android.gallery.app.ui.list.search.pictures;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.SearchPicturesDateLocationViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesViewHolderFactory extends PicturesViewHolderFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PeopleImageViewHolder extends ImageViewHolder {
        public PeopleImageViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void bindImage(Bitmap bitmap) {
            RectF rectF;
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem != null && bitmap != null && bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
                if (MediaItemUtil.isCreatureCandidate(mediaItem.getSubCategory())) {
                    rectF = mediaItem.getFaceRect();
                } else {
                    rectF = null;
                }
                if (rectF != null) {
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint();
                    paint.setColor(-256);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(4.0f);
                    if (mediaItem.isVideo() && mediaItem.getOrientation() > 0) {
                        rectF = RectUtils.getRotatedRectFRatio(rectF, mediaItem.getOrientation());
                    }
                    canvas.drawRect(RectUtils.getSmartCropRect(rectF, bitmap.getWidth(), bitmap.getHeight()), paint);
                }
            }
            super.bindImage(bitmap);
        }
    }

    public SearchPicturesViewHolderFactory(Context context) {
        super(context);
    }

    public ListViewHolder getDataViewHolder(View view, int i2) {
        if (PocFeatures.DEBUG_FACE_RECT) {
            return new PeopleImageViewHolder(view, i2);
        }
        return super.getDataViewHolder(view, i2);
    }

    public ListViewHolder getDateLocationViewHolder(View view, int i2) {
        if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            return new SearchPicturesDateLocationViewHolder(view, i2);
        }
        return super.getDateLocationViewHolder(view, i2);
    }

    public int getFirstTimelineLayoutId() {
        if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            return R.layout.recycler_item_search_pictures_timeline_first_layout;
        }
        return super.getFirstTimelineLayoutId();
    }

    public int getTimelineLayoutId() {
        if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            return R.layout.recycler_item_search_pictures_timeline_body_layout;
        }
        return super.getTimelineLayoutId();
    }
}
