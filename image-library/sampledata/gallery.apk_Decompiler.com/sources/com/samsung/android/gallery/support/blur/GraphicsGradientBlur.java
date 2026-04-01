package com.samsung.android.gallery.support.blur;

import android.graphics.RenderEffect;
import com.samsung.android.sesl.REImageFilter;
import com.samsung.android.sesl.renderEffectImageFilter.ImageFilterParams$GradientBlur;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GraphicsGradientBlur implements GradientBlurCompatible {
    private static ImageFilterParams$GradientBlur getGradientBlur(BlurParams blurParams) {
        ImageFilterParams$GradientBlur imageFilterParams$GradientBlur = new ImageFilterParams$GradientBlur();
        imageFilterParams$GradientBlur.direction = blurParams.getGradientBlurDirection();
        imageFilterParams$GradientBlur.percent = blurParams.getGradientBlurPercent();
        imageFilterParams$GradientBlur.pivotPercent = blurParams.getGradientBlurPivotPercent();
        return imageFilterParams$GradientBlur;
    }

    public RenderEffect getRenderEffect(BlurParams blurParams) {
        REImageFilter curveMaxY = new REImageFilter().setDither(blurParams.isDither()).setBlurRadius(blurParams.getRadius()).setProportionalSaturation(blurParams.getSaturation()).setCurveLevel(blurParams.getCurveLevel()).setCurveMinX(blurParams.getCurveMinX()).setCurveMaxX(blurParams.getCurveMaxX()).setCurveMinY(blurParams.getCurveMinY()).setCurveMaxY(blurParams.getCurveMaxY());
        if (blurParams.hasGradientBlur()) {
            curveMaxY.setGradientBlur(getGradientBlur(blurParams));
        }
        return curveMaxY.build(blurParams.getWidth(), blurParams.getHeight());
    }
}
