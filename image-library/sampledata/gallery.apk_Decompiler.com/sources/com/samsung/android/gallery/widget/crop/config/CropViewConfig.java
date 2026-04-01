package com.samsung.android.gallery.widget.crop.config;

import A.a;
import android.graphics.RectF;
import android.os.Bundle;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CropViewConfig {
    public float mHeightWidthRatio = 1.0f;
    public int mOrientation;
    public ShapeType mShapeType;
    public RectF mSmartCropRect;
    public boolean mSquareAspectRatio;
    public boolean mUseFixedAspectRatio;
    public float mWidthHeightRatio = 1.0f;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ShapeType {
        RECTANGLE,
        OVAL,
        RECT_1X1,
        RECT_2X1,
        RECT_2X2,
        RECT_4X1,
        RECT_4X2
    }

    public CropViewConfig(Bundle bundle, MediaItem mediaItem) {
        boolean z;
        int i2 = 0;
        int i7 = bundle.getInt("aspectX", 0);
        int i8 = bundle.getInt("aspectY", 0);
        boolean z3 = true;
        if (i7 == 0 || i8 == 0) {
            z = false;
        } else {
            z = true;
        }
        this.mUseFixedAspectRatio = z;
        if (z) {
            this.mSquareAspectRatio = i7 != i8 ? false : z3;
            float f = (float) i7;
            float f5 = (float) i8;
            this.mWidthHeightRatio = f / f5;
            this.mHeightWidthRatio = f5 / f;
        }
        this.mShapeType = getShapeType(bundle);
        this.mOrientation = mediaItem != null ? mediaItem.getOrientation() : i2;
        try {
            setSmartCropRect(mediaItem);
        } catch (Exception e) {
            a.s(e, new StringBuilder("setSmartCropRect failed. e="), "CropViewConfig");
        }
    }

    private int getClosetSmartCropRatioIndex() {
        float f = this.mWidthHeightRatio;
        if (f > 1.5555556f) {
            return 5;
        }
        if (f > 1.1666667f) {
            return 3;
        }
        if (f > 0.875f) {
            return 1;
        }
        if (f > 0.65625f) {
            return 2;
        }
        return 4;
    }

    private ShapeType getShapeType(Bundle bundle) {
        if (bundle.getBoolean("set-as-contactphoto", false)) {
            return ShapeType.OVAL;
        }
        switch (bundle.getInt("shape-type", 0)) {
            case 1:
                return ShapeType.OVAL;
            case 2:
                return ShapeType.RECT_1X1;
            case 3:
                return ShapeType.RECT_2X1;
            case 4:
                return ShapeType.RECT_2X2;
            case 5:
                return ShapeType.RECT_4X2;
            case 6:
                return ShapeType.RECT_4X1;
            default:
                return ShapeType.RECTANGLE;
        }
    }

    private void setSmartCropRect(MediaItem mediaItem) {
        ArrayList cropRectRatioList;
        int i2;
        if (mediaItem != null && (cropRectRatioList = mediaItem.getCropRectRatioList()) != null) {
            if (this.mSquareAspectRatio) {
                i2 = 1;
            } else {
                i2 = getClosetSmartCropRatioIndex();
            }
            RectF rectF = (RectF) cropRectRatioList.get(i2);
            Log.d("CropViewConfig", "setSmartCropRect", Integer.valueOf(i2), Integer.valueOf(this.mOrientation), rectF);
            RectF rectF2 = new RectF();
            this.mSmartCropRect = rectF2;
            int i7 = this.mOrientation;
            if (i7 == 0) {
                rectF2.set(rectF);
            } else if (i7 == 90) {
                rectF2.set(rectF.top, rectF.left, rectF.bottom, rectF.right);
            } else if (i7 == 180) {
                rectF2.set(1.0f - rectF.right, 1.0f - rectF.bottom, 1.0f - rectF.left, 1.0f - rectF.top);
            } else {
                rectF2.set(1.0f - rectF.bottom, 1.0f - rectF.right, 1.0f - rectF.top, 1.0f - rectF.left);
            }
        }
    }
}
