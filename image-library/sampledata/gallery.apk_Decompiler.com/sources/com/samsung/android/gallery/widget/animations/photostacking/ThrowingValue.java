package com.samsung.android.gallery.widget.animations.photostacking;

import android.content.Context;
import android.util.Size;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.R$dimen;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ThrowingValue {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViFixedValues {
        float distanceXFromCenter;
        float distanceYFromCenter;
        float rotate;

        public ViFixedValues(float f, float f5, double d) {
            this.distanceXFromCenter = f;
            this.distanceYFromCenter = f5;
            this.rotate = (float) d;
        }
    }

    public static ViFixedValues getFixedViValue(int i2) {
        if (i2 == Integer.MAX_VALUE) {
            return new ViFixedValues(0.0f, 0.0f, MapUtil.INVALID_LOCATION);
        }
        switch (i2 % 16) {
            case 0:
                return new ViFixedValues(3.0f, -156.0f, -4.414167d);
            case 1:
                return new ViFixedValues(-118.0f, -51.0f, 7.1779275d);
            case 2:
                return new ViFixedValues(-110.0f, 72.0f, -17.735233d);
            case 3:
                return new ViFixedValues(-7.0f, 160.0f, 12.956253d);
            case 4:
                return new ViFixedValues(152.0f, 117.0f, 10.1749125d);
            case 5:
                return new ViFixedValues(158.0f, -53.0f, 4.158244d);
            case 6:
                return new ViFixedValues(-176.0f, -125.0f, -7.1815223d);
            case 7:
                return new ViFixedValues(-136.0f, 109.0f, 14.205498d);
            case 8:
                return new ViFixedValues(-55.0f, -196.0f, 0.4935488d);
            case 9:
                return new ViFixedValues(149.0f, -185.0f, 7.666392d);
            case 10:
                return new ViFixedValues(-205.0f, 86.0f, -1.2208413d);
            case 11:
                return new ViFixedValues(-110.0f, 215.0f, 8.761251d);
            case 12:
                return new ViFixedValues(107.0f, -241.0f, 14.891048d);
            case 13:
                return new ViFixedValues(153.0f, 227.0f, -7.529728d);
            case 14:
                return new ViFixedValues(228.0f, 108.0f, 9.717535d);
            case 15:
                return new ViFixedValues(228.0f, -33.0f, -9.717535d);
            case 16:
                return new ViFixedValues(85.0f, 228.0f, -3.2697504d);
            case 17:
                return new ViFixedValues(-230.0f, -30.0f, -2.8326983d);
            case 18:
                return new ViFixedValues(112.0f, -100.0f, 11.132059d);
            case 19:
                return new ViFixedValues(-182.0f, -153.0f, 2.1322086d);
            default:
                return new ViFixedValues(77.0f, -246.0f, -4.414167d);
        }
    }

    public static Size getImageViewSize(Context context) {
        return new Size(ResourceCompat.getDimensionPixelOffset(context, R$dimen.ondemand_story_vi_thumb_width, 0), ResourceCompat.getDimensionPixelOffset(context, R$dimen.ondemand_story_vi_thumb_height, 0));
    }
}
