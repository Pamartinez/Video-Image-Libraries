package com.samsung.android.gallery.module.story.smartrect;

import android.graphics.Rect;
import android.graphics.RectF;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.crop.SmartCropUtils;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoryHighlightRect {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RectBuilder {
        /* access modifiers changed from: private */
        public RectF cropRect;
        /* access modifiers changed from: private */
        public int imageH;
        /* access modifiers changed from: private */
        public int imageW;
        /* access modifiers changed from: private */
        public final FileItemInterface item;
        /* access modifiers changed from: private */
        public Rect screenRect;
        /* access modifiers changed from: private */
        public boolean withRotation = true;
        /* access modifiers changed from: private */
        public boolean withSmartCrop = true;

        public RectBuilder(FileItemInterface fileItemInterface) {
            this.item = fileItemInterface;
        }

        /* access modifiers changed from: private */
        public int getOrientation() {
            if (this.item.isVideo() || this.item.isBroken()) {
                return 0;
            }
            return this.item.getOrientation();
        }

        /* access modifiers changed from: private */
        public boolean isSmartCropSupported() {
            if (this.item.isVideo() || this.item.isBroken()) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public void setCropRect(RectF rectF) {
            this.cropRect = rectF;
        }

        public RectInfo build() {
            return StoryHighlightRect.getDisplayRectInfo(this);
        }

        public RectBuilder setImageSize(int i2, int i7) {
            this.imageW = i2;
            this.imageH = i7;
            return this;
        }

        public RectBuilder setScreenRect(Rect rect) {
            this.screenRect = rect;
            return this;
        }

        public RectBuilder withRotation(boolean z) {
            this.withRotation = z;
            return this;
        }

        public RectBuilder withSmartCrop(boolean z) {
            this.withSmartCrop = z;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RectInfo {
        public final Rect displayRect;
        public final Rect imageCropRect;

        public RectInfo(Rect rect, Rect rect2) {
            this.displayRect = rect;
            this.imageCropRect = rect2;
        }
    }

    private static Rect getDisplayRect(RectBuilder rectBuilder) {
        boolean z;
        float width = ((float) rectBuilder.screenRect.width()) / ((float) rectBuilder.screenRect.height());
        if (rectBuilder.item.getOrientation() % MOCRLang.KHMER == 0) {
            z = true;
        } else {
            z = false;
        }
        float width2 = (rectBuilder.cropRect.width() * ((float) rectBuilder.imageW)) / (rectBuilder.cropRect.height() * ((float) rectBuilder.imageH));
        if (!z) {
            width2 = 1.0f / width2;
        }
        if (width >= width2) {
            int height = (int) (((float) rectBuilder.screenRect.height()) * width2);
            Rect rect = new Rect();
            int width3 = (rectBuilder.screenRect.width() - height) / 2;
            rect.left = width3;
            rect.right = width3 + height;
            rect.top = 0;
            rect.bottom = rectBuilder.screenRect.height();
            return rect;
        }
        int width4 = (int) ((1.0f / width2) * ((float) rectBuilder.screenRect.width()));
        Rect rect2 = new Rect();
        rect2.left = 0;
        rect2.right = rectBuilder.screenRect.width();
        int height2 = (rectBuilder.screenRect.height() - width4) / 2;
        rect2.top = height2;
        rect2.bottom = height2 + width4;
        return rect2;
    }

    /* access modifiers changed from: private */
    public static RectInfo getDisplayRectInfo(RectBuilder rectBuilder) {
        Rect rect;
        if (rectBuilder.withSmartCrop && rectBuilder.isSmartCropSupported()) {
            rectBuilder.setCropRect(SmartRect.getBestCropRect(rectBuilder.item, rectBuilder.screenRect));
        }
        if (RectUtils.isValidRect(rectBuilder.cropRect)) {
            rect = getDisplayRect(rectBuilder);
        } else {
            rectBuilder.setCropRect(new RectF(0.0f, 0.0f, 1.0f, 1.0f));
            rect = getFitDisplayRect(rectBuilder);
        }
        Rect calcSmartCropRectForStory = SmartCropUtils.calcSmartCropRectForStory(new SmartCropUtils.CropInfo.Builder(rectBuilder.cropRect).setDrawableSize(rectBuilder.imageW, rectBuilder.imageH).setViewSize((float) rect.width(), (float) rect.height()).setRotation(rectBuilder.getOrientation()).build());
        if (rectBuilder.withRotation) {
            calcSmartCropRectForStory = RectUtils.getRotatedRect(calcSmartCropRectForStory, rectBuilder.imageW, rectBuilder.imageH, rectBuilder.getOrientation());
        }
        return new RectInfo(rect, calcSmartCropRectForStory);
    }

    private static Rect getFitDisplayRect(RectBuilder rectBuilder) {
        boolean z;
        float f;
        int i2;
        int i7;
        float width = (float) rectBuilder.screenRect.width();
        float height = (float) rectBuilder.screenRect.height();
        if (rectBuilder.getOrientation() % MOCRLang.KHMER == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            f = Math.min(width / ((float) rectBuilder.imageW), height / ((float) rectBuilder.imageH));
        } else {
            f = Math.min(width / ((float) rectBuilder.imageH), height / ((float) rectBuilder.imageW));
        }
        if (z) {
            i2 = rectBuilder.imageW;
        } else {
            i2 = rectBuilder.imageH;
        }
        float f5 = ((float) i2) * f;
        if (z) {
            i7 = rectBuilder.imageH;
        } else {
            i7 = rectBuilder.imageW;
        }
        return new Rect(0, 0, (int) f5, (int) (((float) i7) * f));
    }
}
