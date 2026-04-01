package com.samsung.android.gallery.widget.livemotion.theme;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.livemotion.theme.Transform;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformScale;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformTranslate;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TransformNext implements Transform {
    public static ArrayList<PageTransform> buildNext(MediaItem mediaItem) {
        Transform.TYPE type = Transform.getType(mediaItem);
        Transform.printDebugLog(type, "TransformNext");
        if (type.isScaleIn()) {
            if (Transform.hasRoi(mediaItem)) {
                return getDollyIn(mediaItem);
            }
            return getScaleIn(mediaItem);
        } else if (type.isScaleOut()) {
            if (Transform.hasRoi(mediaItem)) {
                return getDollyOut(mediaItem);
            }
            return getScaleOut(mediaItem);
        } else if (type.isPanningLeft()) {
            return getPanningLeft(mediaItem);
        } else {
            if (type.isPanningRight()) {
                return getPanningRight(mediaItem);
            }
            if (type.isTiltUp()) {
                return getTiltUp(mediaItem);
            }
            if (type.isTileDown()) {
                return getTiltDown(mediaItem);
            }
            return new ArrayList<>();
        }
    }

    private static ArrayList<PageTransform> getDollyIn(MediaItem mediaItem) {
        float[] pivotByROI = Transform.getPivotByROI(mediaItem);
        return getScaleIn(pivotByROI[0], pivotByROI[1]);
    }

    private static ArrayList<PageTransform> getDollyOut(MediaItem mediaItem) {
        float[] pivotByROI = Transform.getPivotByROI(mediaItem);
        return getScaleOut(pivotByROI[0], pivotByROI[1]);
    }

    private static ArrayList<PageTransform> getPanning(float f) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformScale(1).setInitialValue(1.025f).setTargetValue(1.025f));
        arrayList.add(new PageTransformTranslate(1).setInitialDeltaX(f).setTargetDeltaX(f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getPanningLeft(MediaItem mediaItem) {
        return getPanning(0.012499988f);
    }

    private static ArrayList<PageTransform> getPanningRight(MediaItem mediaItem) {
        return getPanning(-0.012499988f);
    }

    private static ArrayList<PageTransform> getScale(float f, float f5, float f8) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformScale(1).setPivotX(f5).setPivotY(f8).setInitialValue(f).setTargetValue(f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getScaleIn(MediaItem mediaItem) {
        return getScaleIn(0.5f, 0.5f);
    }

    private static ArrayList<PageTransform> getScaleOut(MediaItem mediaItem) {
        return getScaleOut(0.5f, 0.5f);
    }

    private static ArrayList<PageTransform> getTilt(float f) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformScale(1).setInitialValue(1.025f).setTargetValue(1.025f));
        arrayList.add(new PageTransformTranslate(1).setInitialDeltaY(f).setTargetDeltaY(f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getTiltDown(MediaItem mediaItem) {
        return getTilt(-0.012499988f);
    }

    private static ArrayList<PageTransform> getTiltUp(MediaItem mediaItem) {
        return getTilt(0.012499988f);
    }

    private static ArrayList<PageTransform> getScaleIn(float f, float f5) {
        return getScale(1.0f, f, f5);
    }

    private static ArrayList<PageTransform> getScaleOut(float f, float f5) {
        return getScale(1.025f, f, f5);
    }
}
