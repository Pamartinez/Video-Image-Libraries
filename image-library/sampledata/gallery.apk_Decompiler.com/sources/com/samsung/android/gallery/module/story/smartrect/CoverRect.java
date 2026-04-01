package com.samsung.android.gallery.module.story.smartrect;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.ocr.MOCRLang;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CoverRect {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CoverRectBuilder {
        private int bHeight;
        private int bWidth;
        private float expandMore;
        private RectF mergingRect;
        private RectF objectRect;
        private final int orientation;
        private int vHeight;
        private int vWidth;

        public /* synthetic */ CoverRectBuilder(int i2, int i7) {
            this(i2);
        }

        /* access modifiers changed from: private */
        public RectF build() {
            if (!RectUtils.isValidRect(this.objectRect) || this.bWidth <= 0 || this.bHeight <= 0 || this.vWidth <= 0 || this.vHeight <= 0) {
                return new RectF();
            }
            RectF rectF = new RectF(this.objectRect);
            CoverRect.adjustRectToViewRatio(rectF, this.vWidth, this.vHeight, this.bWidth, this.bHeight);
            if (RectUtils.isValidRect(this.mergingRect)) {
                CoverRect.expandRectToMergingRect(rectF, this.objectRect, this.mergingRect);
                CoverRect.addMoreSpace(rectF, this.mergingRect, this.expandMore);
            } else {
                CoverRect.addMoreVerticalSpace(rectF, this.expandMore);
            }
            CoverRect.adjustRectToViewRatio(rectF, this.vWidth, this.vHeight, this.bWidth, this.bHeight);
            return RectUtils.getRotatedRectFRatio(rectF, 360 - this.orientation);
        }

        /* access modifiers changed from: private */
        public CoverRectBuilder setExpandMore(float f) {
            this.expandMore = f;
            return this;
        }

        /* access modifiers changed from: private */
        public CoverRectBuilder setImageRatio(Drawable drawable) {
            if (drawable != null) {
                setImageRatio(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
            return this;
        }

        /* access modifiers changed from: private */
        public CoverRectBuilder setMergingRect(RectF rectF) {
            this.mergingRect = rectF;
            return this;
        }

        /* access modifiers changed from: private */
        public CoverRectBuilder setObjectRect(RectF rectF) {
            this.objectRect = rectF;
            return this;
        }

        /* access modifiers changed from: private */
        public CoverRectBuilder setViewRatio(int i2, int i7) {
            this.vWidth = i2;
            this.vHeight = i7;
            return this;
        }

        private CoverRectBuilder(int i2) {
            this.expandMore = 0.0f;
            this.orientation = i2;
        }

        private CoverRectBuilder setImageRatio(int i2, int i7) {
            boolean z = this.orientation % MOCRLang.KHMER != 0;
            this.bWidth = z ? i7 : i2;
            if (!z) {
                i2 = i7;
            }
            this.bHeight = i2;
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static void addMoreSpace(RectF rectF, RectF rectF2, float f) {
        rectF.left = Math.max(0.0f, Math.min(rectF2.left, rectF.left) - f);
        rectF.right = Math.min(1.0f, Math.max(rectF2.right, rectF.right) + f);
        rectF.top = Math.max(0.0f, Math.min(rectF2.top, rectF.top) - f);
        rectF.bottom = Math.min(1.0f, Math.max(rectF2.bottom, rectF.bottom) + f);
    }

    /* access modifiers changed from: private */
    public static void addMoreVerticalSpace(RectF rectF, float f) {
        rectF.top = Math.max(rectF.top - (rectF.height() * f), 0.0f);
        rectF.bottom = Math.min((rectF.height() * f) + rectF.bottom, 1.0f);
    }

    /* access modifiers changed from: private */
    public static void adjustRectToViewRatio(RectF rectF, int i2, int i7, int i8, int i10) {
        if ((rectF.height() * ((float) i10)) / (rectF.width() * ((float) i8)) > ((float) i7) / ((float) i2)) {
            expandHorizontalToViewRatio(rectF, i2, i7, i8, i10);
            reduceVerticalToViewRatio(rectF, i2, i7, i8, i10);
            return;
        }
        expandVerticalToViewRatio(rectF, i2, i7, i8, i10);
        reduceHorizontalToViewRatio(rectF, i2, i7, i8, i10);
    }

    private static float calculateXDelta(RectF rectF, RectF rectF2) {
        if (rectF2.centerX() < rectF.centerX()) {
            return -Math.max(rectF.left - rectF2.left, 0.0f);
        }
        return Math.max(rectF2.right - rectF.right, 0.0f);
    }

    private static float calculateYDelta(RectF rectF, RectF rectF2) {
        if (rectF2.centerY() < rectF.centerY()) {
            return -Math.max(rectF.top - rectF2.top, 0.0f);
        }
        return Math.max(rectF2.bottom - rectF.bottom, 0.0f);
    }

    private static void expandHorizontalToViewRatio(RectF rectF, int i2, int i7, int i8, int i10) {
        float f = (float) i10;
        float f5 = (float) i8;
        float height = (rectF.height() * f) / (rectF.width() * f5);
        float f8 = rectF.left;
        float f10 = 1.0f - rectF.right;
        float f11 = (float) i7;
        float height2 = (((((float) i2) - (f11 / height)) / (f11 / (rectF.height() * f))) / f5) / 2.0f;
        rectF.left -= Math.min(f8, height2);
        rectF.right = Math.min(f10, height2) + rectF.right;
    }

    /* access modifiers changed from: private */
    public static void expandRectToMergingRect(RectF rectF, RectF rectF2, RectF rectF3) {
        boolean z;
        if (!rectF.contains(rectF3)) {
            if (rectF3.centerY() < rectF2.centerY()) {
                z = true;
            } else {
                z = false;
            }
            if (RectF.intersects(rectF, rectF3) && z) {
                float calculateXDelta = calculateXDelta(rectF, rectF3);
                float calculateYDelta = calculateYDelta(rectF, rectF3);
                rectF.left += calculateXDelta;
                rectF.right += calculateXDelta;
                rectF.top += calculateYDelta;
                rectF.bottom += calculateYDelta;
            }
        }
    }

    private static void expandVerticalToViewRatio(RectF rectF, int i2, int i7, int i8, int i10) {
        float f = (float) i10;
        float f5 = (float) i8;
        float height = (rectF.height() * f) / (rectF.width() * f5);
        float f8 = rectF.top;
        float f10 = 1.0f - rectF.bottom;
        float f11 = (float) i2;
        float width = (((((float) i7) - (f11 * height)) / (f11 / (rectF.width() * f5))) / f) / 2.0f;
        rectF.top -= Math.min(f8, width);
        rectF.bottom = Math.min(f10, width) + rectF.bottom;
    }

    public static RectF getCoverDisplayRect(FileItemInterface fileItemInterface, ImageView imageView, boolean z) {
        try {
            RectF rotatedRectFRatio = RectUtils.getRotatedRectFRatio(getSmartCropForCover(fileItemInterface, z), fileItemInterface.getOrientation());
            return new CoverRectBuilder(fileItemInterface.getOrientation(), 0).setObjectRect(rotatedRectFRatio).setMergingRect(unionRect(RectUtils.getRotatedRectList(SmartRect.getFaceRectList(fileItemInterface), fileItemInterface.getOrientation()))).setViewRatio(imageView.getWidth(), imageView.getHeight()).setImageRatio(imageView.getDrawable()).setExpandMore(0.15f).build();
        } catch (Exception e) {
            Log.e("CoverRect", "fail to crop rect e=" + e.getMessage());
            return getSmartCropForCover(fileItemInterface);
        }
    }

    private static int getCoverIndex(boolean z) {
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_80) {
            if (z) {
                return -1;
            }
            return 18;
        } else if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            return 1;
        } else {
            if (z) {
                return 19;
            }
            return 18;
        }
    }

    private static RectF getSmartCropForCover(FileItemInterface fileItemInterface, int i2) {
        ArrayList<RectF> totalSmartCropRect = SmartRect.getTotalSmartCropRect(fileItemInterface);
        if (!totalSmartCropRect.isEmpty()) {
            return totalSmartCropRect.get(i2);
        }
        return new RectF();
    }

    private static void reduceHorizontalToViewRatio(RectF rectF, int i2, int i7, int i8, int i10) {
        float f = (float) i10;
        float f5 = (float) i8;
        float f8 = (float) i7;
        float height = ((((f8 / ((rectF.height() * f) / (rectF.width() * f5))) - ((float) i2)) / (f8 / (rectF.height() * f))) / f5) / 2.0f;
        rectF.left += height;
        rectF.right -= height;
    }

    private static void reduceVerticalToViewRatio(RectF rectF, int i2, int i7, int i8, int i10) {
        float f = (float) i10;
        float f5 = (float) i8;
        float f8 = (float) i2;
        float height = ((((f8 * ((rectF.height() * f) / (rectF.width() * f5))) - ((float) i7)) / (f8 / (rectF.width() * f5))) / f) / 2.0f;
        rectF.top += height;
        rectF.bottom -= height;
    }

    private static RectF unionRect(ArrayList<RectF> arrayList) {
        if (arrayList.size() > 3 || arrayList.isEmpty()) {
            return new RectF();
        }
        Iterator<RectF> it = arrayList.iterator();
        RectF rectF = null;
        while (it.hasNext()) {
            RectF next = it.next();
            if (rectF == null) {
                rectF = new RectF(next);
            } else {
                rectF.union(next);
            }
        }
        return rectF;
    }

    public static RectF getSmartCropForCover(FileItemInterface fileItemInterface) {
        return getSmartCropForCover(fileItemInterface, false);
    }

    public static RectF getSmartCropForCover(FileItemInterface fileItemInterface, boolean z) {
        return getCoverIndex(z) != -1 ? getSmartCropForCover(fileItemInterface, getCoverIndex(z)) : new RectF();
    }
}
