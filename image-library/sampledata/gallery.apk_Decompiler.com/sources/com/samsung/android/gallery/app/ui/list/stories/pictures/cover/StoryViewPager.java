package com.samsung.android.gallery.app.ui.list.stories.pictures.cover;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.simpleslideshow.MultiTransformBuilder;
import com.samsung.android.gallery.widget.simpleslideshow.SimpleSlideShowViewPager;
import com.samsung.android.gallery.widget.simpleslideshow.TransformBuilder;
import com.samsung.android.ocr.MOCRLang;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryViewPager extends SimpleSlideShowViewPager {
    private TransformBuilder mTransformBuilder;

    public StoryViewPager(ViewGroup viewGroup) {
        super(viewGroup);
    }

    private PeopleData findPeopleData(int i2) {
        ViewPager2 viewPager2 = this.mViewPager;
        if (!(viewPager2 == null || viewPager2.getWidth() == 0)) {
            List<PeopleData> peopleData = this.mAdapter.getPeopleData(i2);
            MediaItem mediaItem = this.mAdapter.getMediaItem(i2);
            if (!(peopleData == null || peopleData.isEmpty() || mediaItem == null)) {
                for (PeopleData next : peopleData) {
                    RectF displayRectF = getDisplayRectF(this.mViewPager, mediaItem);
                    next.setFaceRectF(displayRectF, mediaItem.getOrientation());
                    if (intersect(displayRectF, next.getFaceRectF())) {
                        return next;
                    }
                }
            }
        }
        return null;
    }

    private RectF getDisplayRectF(View view, MediaItem mediaItem) {
        boolean z;
        int i2;
        int i7;
        float height;
        int height2;
        int i8;
        int i10;
        if (mediaItem == null) {
            return new RectF(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        }
        boolean z3 = false;
        if (mediaItem.getOrientation() % MOCRLang.KHMER == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i2 = mediaItem.getWidth();
        } else {
            i2 = mediaItem.getHeight();
        }
        if (z) {
            i7 = mediaItem.getHeight();
        } else {
            i7 = mediaItem.getWidth();
        }
        if (i7 > i2) {
            z3 = true;
        }
        if (z3) {
            height = (float) view.getWidth();
            height2 = mediaItem.getWidth();
        } else {
            height = (float) view.getHeight();
            height2 = mediaItem.getHeight();
        }
        float f = height / ((float) height2);
        if (z3) {
            i8 = view.getWidth();
        } else {
            i8 = (int) (((float) i2) * f);
        }
        if (z3) {
            i10 = (int) (((float) i7) * f);
        } else {
            i10 = view.getHeight();
        }
        return new RectF(0.0f, 0.0f, (float) i8, (float) i10);
    }

    private boolean intersect(RectF rectF, RectF rectF2) {
        RectF centerCropRect = RectUtils.getCenterCropRect(rectF);
        if (centerCropRect.intersect(rectF2)) {
            RectF rectF3 = new RectF();
            rectF3.left = Math.max(centerCropRect.left, rectF2.left);
            rectF3.right = Math.min(centerCropRect.right, rectF2.right);
            rectF3.top = Math.max(centerCropRect.top, rectF2.top);
            rectF3.bottom = Math.min(centerCropRect.bottom, rectF2.bottom);
            if ((rectF3.height() * rectF3.width()) / (rectF2.height() * rectF2.width()) > 0.7f) {
                return true;
            }
        }
        return false;
    }

    public int getBackgroundResId() {
        return R.color.light_black_color;
    }

    public int getInterval() {
        MediaItem mediaItem = this.mAdapter.getMediaItem(getCurrentDataPosition());
        if (PreviewFactory.isPreviewableFormat(mediaItem)) {
            return Math.max(Math.min(mediaItem.getFileDuration() - 3000, 2000), 0);
        }
        return 0;
    }

    public TransformBuilder getTransformBuilder(int i2) {
        int i7;
        if (this.mTransformBuilder == null) {
            this.mTransformBuilder = new MultiTransformBuilder();
        }
        MediaItem mediaItem = this.mAdapter.getMediaItem(i2);
        if (mediaItem != null) {
            i7 = mediaItem.getOrientation();
        } else {
            i7 = 0;
        }
        this.mTransformBuilder.setNextPeopleData(findPeopleData(i2), i7);
        return this.mTransformBuilder;
    }

    public void recycle() {
        super.recycle();
        TransformBuilder transformBuilder = this.mTransformBuilder;
        if (transformBuilder != null) {
            transformBuilder.recycle();
        }
    }
}
