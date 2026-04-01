package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.samsung.o3dp.lib3dphotography.graphics.Texture;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.nativelib.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class O3DPAttributeParser {
    private static final String TAG = "O3DPAttributeParser";

    private void applyAttributes(TypedArray typedArray, O3DPSurfaceView o3DPSurfaceView, O3DPhotoConfig o3DPhotoConfig) {
        int color = typedArray.getColor(R.styleable.O3DPSurfaceView_android_background, 0);
        o3DPhotoConfig.setBgColor(color);
        o3DPSurfaceView.setBackgroundColor(color);
        int i2 = R.styleable.O3DPSurfaceView_android_scaleType;
        ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_INSIDE;
        ImageView.ScaleType scaleType2 = ImageView.ScaleType.values()[typedArray.getInt(i2, scaleType.ordinal())];
        if (ImageView.ScaleType.CENTER_CROP.equals(scaleType2)) {
            o3DPhotoConfig.setFitMode(Texture.FitMode.FIT_BEST);
        } else if (scaleType.equals(scaleType2)) {
            o3DPhotoConfig.setFitMode(Texture.FitMode.FIT_FULL);
        } else {
            LogUtil.e(TAG, "Unsupported scaleType: " + scaleType2);
        }
    }

    public boolean parseAttributes(Context context, O3DPSurfaceView o3DPSurfaceView, AttributeSet attributeSet, O3DPhotoConfig o3DPhotoConfig) {
        TypedArray typedArray = null;
        try {
            typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.O3DPSurfaceView, 0, 0);
            applyAttributes(typedArray, o3DPSurfaceView, o3DPhotoConfig);
            if (typedArray != null) {
                typedArray.recycle();
            }
            return true;
        } catch (Resources.NotFoundException e) {
            LogUtil.e(TAG, "Attribute or resource not found", e);
            if (typedArray != null) {
                typedArray.recycle();
            }
            return false;
        } catch (IndexOutOfBoundsException e7) {
            LogUtil.e(TAG, "Index out of bounds in TypedArray", e7);
            if (typedArray != null) {
                typedArray.recycle();
            }
            return false;
        } catch (RuntimeException e8) {
            LogUtil.e(TAG, "Unexpected runtime error", e8);
            if (typedArray != null) {
                typedArray.recycle();
            }
            return false;
        } catch (Throwable th) {
            if (typedArray != null) {
                typedArray.recycle();
            }
            throw th;
        }
    }
}
