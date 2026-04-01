package com.samsung.android.gallery.widget.livemotion.theme;

import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformAlpha;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformScale;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Transition {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TYPE {
        SLOW_FADE_IN,
        FAST_FADE_IN,
        SLOW_FADE_IN_ZOOM_OUT,
        FAST_FADE_IN_ZOOM_OUT,
        CUT;

        public int getTransitionDuration() {
            if (isSlowFadeIn() || isSlowFadeInZoomOut()) {
                return 660;
            }
            if (isCut()) {
                return 35;
            }
            return 330;
        }

        public boolean isCut() {
            if (this == CUT) {
                return true;
            }
            return false;
        }

        public boolean isFastFadeIn() {
            if (this == FAST_FADE_IN) {
                return true;
            }
            return false;
        }

        public boolean isFastFadeInZoomOut() {
            if (this == FAST_FADE_IN_ZOOM_OUT) {
                return true;
            }
            return false;
        }

        public boolean isSlowFadeIn() {
            if (this == SLOW_FADE_IN) {
                return true;
            }
            return false;
        }

        public boolean isSlowFadeInZoomOut() {
            if (this == SLOW_FADE_IN_ZOOM_OUT) {
                return true;
            }
            return false;
        }
    }

    public static ArrayList<PageTransform> build(TYPE type, int i2) {
        printDebugLog(type);
        if (type.isSlowFadeIn()) {
            return getSlowFadeIn(i2);
        }
        if (type.isFastFadeIn()) {
            return getFastFadeIn(i2);
        }
        if (type.isSlowFadeInZoomOut()) {
            return getSlowFadeInZoomOut(i2);
        }
        if (type.isFastFadeInZoomOut()) {
            return getFastFadeInZoomOut(i2);
        }
        return getCut(i2);
    }

    private static ArrayList<PageTransform> getCut(int i2) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformAlpha(1).setDelay(((float) (i2 - 35)) / ((float) i2)).setInitialValue(0.0f).setTargetValue(1.0f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getFadeIn(int i2, int i7) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformAlpha(1).setDelay(((float) (i7 - i2)) / ((float) i7)).setInitialValue(0.0f).setTargetValue(1.0f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getFadeInZoomOut(int i2, int i7) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        float f = ((float) (i7 - i2)) / ((float) i7);
        arrayList.add(new PageTransformAlpha(1).setDelay(f).setInitialValue(0.0f).setTargetValue(1.0f));
        arrayList.add(new PageTransformScale(1, R$id.transform_parent_layout).setDelay(f).setInitialValue(1.1f).setTargetValue(1.0f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getFastFadeIn(int i2) {
        return getFadeIn(330, i2);
    }

    private static ArrayList<PageTransform> getFastFadeInZoomOut(int i2) {
        return getFadeInZoomOut(330, i2);
    }

    private static ArrayList<PageTransform> getSlowFadeIn(int i2) {
        return getFadeIn(660, i2);
    }

    private static ArrayList<PageTransform> getSlowFadeInZoomOut(int i2) {
        return getFadeInZoomOut(660, i2);
    }

    private static void printDebugLog(TYPE type) {
    }
}
