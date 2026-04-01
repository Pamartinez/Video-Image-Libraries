package com.samsung.android.gallery.module.story.smartrect;

import Ad.C0720a;
import android.graphics.RectF;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.RectUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TitlePosition {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TitleInfo {
        double distanceToObject;
        float faceOccupyPortion;
        int index;
        /* access modifiers changed from: package-private */
        public boolean intersectFace;
        /* access modifiers changed from: package-private */
        public boolean intersectObject;

        public /* synthetic */ TitleInfo(int i2) {
            this();
        }

        private TitleInfo() {
        }
    }

    private static ArrayList<TitleInfo> createTitleInfo(RectF rectF, RectF rectF2, ArrayList<RectF> arrayList) {
        boolean z;
        ArrayList<TitleInfo> arrayList2 = new ArrayList<>();
        for (int i2 = 0; i2 < 2; i2++) {
            for (int i7 = 0; i7 < 3; i7++) {
                RectF rectF3 = new RectF();
                float f = (float) 3;
                float width = ((rectF.width() / f) * ((float) i7)) + rectF.left;
                rectF3.left = width;
                rectF3.right = (rectF.width() / f) + width;
                float f5 = (float) 2;
                float height = ((rectF.height() / f5) * ((float) i2)) + rectF.top;
                rectF3.top = height;
                rectF3.bottom = (rectF.height() / f5) + height;
                TitleInfo titleInfo = new TitleInfo(0);
                Iterator<RectF> it = arrayList.iterator();
                while (it.hasNext()) {
                    titleInfo.faceOccupyPortion += intersectArea(rectF3, it.next());
                }
                if (titleInfo.faceOccupyPortion > 0.0f) {
                    z = true;
                } else {
                    z = false;
                }
                titleInfo.intersectFace = z;
                titleInfo.index = (i2 * 3) + i7;
                titleInfo.intersectObject = new RectF(rectF).intersect(rectF2);
                titleInfo.distanceToObject = Math.sqrt(Math.pow((double) (rectF3.centerY() - rectF2.centerY()), 2.0d) + Math.pow((double) (rectF3.centerX() - rectF2.centerX()), 2.0d));
                arrayList2.add(titleInfo);
            }
        }
        return arrayList2;
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [java.lang.Object, java.util.function.BinaryOperator] */
    public static int getTitleAlignIndex(FileItemInterface fileItemInterface) {
        ArrayList<RectF> totalSmartCropRect = SmartRect.getTotalSmartCropRect(fileItemInterface);
        ArrayList<RectF> rotatedRectList = RectUtils.getRotatedRectList(SmartRect.getFaceRectList(fileItemInterface), fileItemInterface.getOrientation());
        if (totalSmartCropRect.isEmpty()) {
            return -1;
        }
        RectF rotatedRectFRatio = RectUtils.getRotatedRectFRatio(totalSmartCropRect.get(0), fileItemInterface.getOrientation());
        RectF rotatedRectFRatio2 = RectUtils.getRotatedRectFRatio(totalSmartCropRect.get(18), fileItemInterface.getOrientation());
        if (RectUtils.isValidRect(rotatedRectFRatio) && RectUtils.isValidRect(rotatedRectFRatio2)) {
            ArrayList<TitleInfo> createTitleInfo = createTitleInfo(rotatedRectFRatio2, rotatedRectFRatio, rotatedRectList);
            ArrayList arrayList = (ArrayList) createTitleInfo.stream().filter(new a(0)).collect(Collectors.toCollection(new C0720a(1)));
            int preferredIndexBaseOnFace = preferredIndexBaseOnFace(createTitleInfo, arrayList);
            if (preferredIndexBaseOnFace != -1) {
                return preferredIndexBaseOnFace;
            }
            int preferredIndexBaseOnObjectPortion = preferredIndexBaseOnObjectPortion(rotatedRectFRatio2, rotatedRectFRatio);
            if (preferredIndexBaseOnObjectPortion != -1) {
                return preferredIndexBaseOnObjectPortion;
            }
            if (arrayList.stream().anyMatch(new a(1))) {
                return ((TitleInfo) arrayList.stream().reduce(new Object()).orElse((Object) null)).index;
            }
        }
        return -1;
    }

    private static float intersectArea(RectF rectF, RectF rectF2) {
        RectF rectF3 = new RectF(rectF);
        if (!rectF3.intersect(rectF2)) {
            return 0.0f;
        }
        return rectF3.height() * rectF3.width();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getTitleAlignIndex$0(TitleInfo titleInfo) {
        return !titleInfo.intersectFace;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ TitleInfo lambda$getTitleAlignIndex$2(TitleInfo titleInfo, TitleInfo titleInfo2) {
        double d = titleInfo2.distanceToObject;
        double d2 = titleInfo.distanceToObject;
        if (d != d2 ? d <= d2 : titleInfo2.index <= titleInfo.index) {
            return titleInfo;
        }
        return titleInfo2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ double lambda$preferredIndexBaseOnFace$4(int i2, TitleInfo titleInfo) {
        int i7 = titleInfo.index / i2;
        float f = titleInfo.faceOccupyPortion;
        if (i7 != 0) {
            f = -f;
        }
        return (double) f;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$preferredIndexBaseOnFace$5(int i2, TitleInfo titleInfo) {
        if (titleInfo.index / i2 == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$preferredIndexBaseOnFace$6(int i2, TitleInfo titleInfo) {
        if (titleInfo.index / i2 != 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r11v4, types: [java.util.function.ToDoubleFunction, java.lang.Object] */
    private static int preferredIndexBaseOnFace(ArrayList<TitleInfo> arrayList, ArrayList<TitleInfo> arrayList2) {
        int i2;
        if (!arrayList.stream().anyMatch(new a(2))) {
            return -1;
        }
        if (!arrayList2.isEmpty()) {
            long count = arrayList2.stream().filter(new a(3)).count();
            long count2 = arrayList2.stream().filter(new a(4)).count();
            long j2 = (long) 3;
            int i7 = (count > j2 ? 1 : (count == j2 ? 0 : -1));
            int i8 = 0;
            if (i7 == 0) {
                if (arrayList.get(3).faceOccupyPortion <= arrayList.get(5).faceOccupyPortion) {
                    i8 = 2;
                }
                if (arrayList.get(4).faceOccupyPortion < arrayList.get(i8).faceOccupyPortion) {
                    return 2 - (i8 % 3);
                }
            } else if (count2 != j2) {
                return -1;
            } else {
                if (arrayList.get(0).faceOccupyPortion > arrayList.get(2).faceOccupyPortion) {
                    i2 = 3;
                } else {
                    i2 = 5;
                }
                if (arrayList.get(1).faceOccupyPortion < arrayList.get(i2).faceOccupyPortion) {
                    return 5 - (i2 % 3);
                }
                return 4;
            }
        } else if (((float) arrayList.stream().mapToDouble(new Object()).sum()) <= 0.0f) {
            return 4;
        }
        return 1;
    }

    private static int preferredIndexBaseOnObjectPortion(RectF rectF, RectF rectF2) {
        RectF rectF3 = new RectF(rectF);
        if (!rectF3.intersect(rectF2)) {
            return -1;
        }
        if ((rectF3.height() * rectF3.width()) / (rectF.height() * rectF.width()) <= 0.6f) {
            return -1;
        }
        if ((rectF2.top - rectF.top) / rectF.height() > 0.3f) {
            return 1;
        }
        return 4;
    }
}
