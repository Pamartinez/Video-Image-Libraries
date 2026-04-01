package com.samsung.android.gallery.widget.livemotion.theme;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.livemotion.theme.Transform;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformScale;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformTranslate;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TransformCurrent implements Transform {
    public static ArrayList<PageTransform> buildNext(MediaItem mediaItem) {
        Transform.TYPE type = Transform.getType(mediaItem);
        Transform.printDebugLog(type, "TransformCurrent");
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

    private static ArrayList<PageTransform> getPanningLeft(MediaItem mediaItem) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformScale(0).setInitialValue(1.025f).setTargetValue(1.025f));
        arrayList.add(new PageTransformTranslate(0).setInitialDeltaX(0.012499988f).setTargetDeltaX(-0.012499988f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getPanningRight(MediaItem mediaItem) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformScale(0).setInitialValue(1.025f).setTargetValue(1.025f));
        arrayList.add(new PageTransformTranslate(0).setInitialDeltaX(-0.012499988f).setTargetDeltaX(0.012499988f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getScaleIn(MediaItem mediaItem) {
        return getScaleIn(0.5f, 0.5f);
    }

    private static ArrayList<PageTransform> getScaleOut(MediaItem mediaItem) {
        return getScaleOut(0.5f, 0.5f);
    }

    private static ArrayList<PageTransform> getTiltDown(MediaItem mediaItem) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformScale(0).setInitialValue(1.025f).setTargetValue(1.025f));
        arrayList.add(new PageTransformTranslate(0).setInitialDeltaY(-0.012499988f).setTargetDeltaY(0.012499988f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getTiltUp(MediaItem mediaItem) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformScale(0).setInitialValue(1.025f).setTargetValue(1.025f));
        arrayList.add(new PageTransformTranslate(0).setInitialDeltaY(0.012499988f).setTargetDeltaY(-0.012499988f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getScaleIn(float f, float f5) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformScale(0).setPivotX(f).setPivotY(f5).setInitialValue(1.0f).setTargetValue(1.025f));
        return arrayList;
    }

    private static ArrayList<PageTransform> getScaleOut(float f, float f5) {
        ArrayList<PageTransform> arrayList = new ArrayList<>();
        arrayList.add(new PageTransformScale(0).setPivotX(f).setPivotY(f5).setInitialValue(1.025f).setTargetValue(1.0f));
        return arrayList;
    }
}
