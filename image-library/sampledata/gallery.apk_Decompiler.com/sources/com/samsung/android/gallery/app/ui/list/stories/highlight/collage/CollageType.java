package com.samsung.android.gallery.app.ui.list.stories.highlight.collage;

import android.content.res.Resources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum CollageType {
    ;
    
    static final float largeRatio = 0.6666667f;
    static final float middleRatio = 0.5f;
    static final float smallRatio = 0.33333334f;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends CollageType {
        public /* synthetic */ AnonymousClass1() {
            this("COLLAGE1", 0);
        }

        public int[] getLargeCornerIndex() {
            return new int[]{0, 1, 5, 4};
        }

        public int getLayoutNumber() {
            return 61;
        }

        public float[] getSpanRatio(int i2) {
            return new float[]{0.5f, CollageType.smallRatio};
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends CollageType {
        public /* synthetic */ AnonymousClass2() {
            this("COLLAGE2", 1);
        }

        public int[] getLargeCornerIndex() {
            return new int[]{0, 2, 5, 3};
        }

        public int getLayoutNumber() {
            return 62;
        }

        public float[] getSpanRatio(int i2) {
            return new float[]{CollageType.smallRatio, 0.5f};
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends CollageType {
        public /* synthetic */ AnonymousClass3() {
            this("COLLAGE3", 2);
        }

        public List<Integer> getLargeCells() {
            return List.of(0, 3, 4);
        }

        public int[] getLargeCornerIndex() {
            return new int[]{0, 1, 5, 4};
        }

        public int getLayoutNumber() {
            return 63;
        }

        public float[] getSpanRatio(int i2) {
            if (i2 == 0 || i2 == 3 || i2 == 4) {
                return new float[]{CollageType.largeRatio, CollageType.smallRatio};
            }
            return new float[]{CollageType.smallRatio, CollageType.smallRatio};
        }

        public boolean hasLandscapeLargeCell() {
            return true;
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends CollageType {
        public /* synthetic */ AnonymousClass4() {
            this("COLLAGE4", 3);
        }

        public List<Integer> getLargeCells() {
            return List.of(0);
        }

        public int[] getLargeCornerIndex() {
            return new int[]{0, 1, 5, 3};
        }

        public int getLayoutNumber() {
            return 65;
        }

        public float[] getSpanRatio(int i2) {
            if (i2 == 0) {
                return new float[]{CollageType.largeRatio, CollageType.largeRatio};
            }
            return new float[]{CollageType.smallRatio, CollageType.smallRatio};
        }

        private AnonymousClass4(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends CollageType {
        public /* synthetic */ AnonymousClass5() {
            this("COLLAGE5", 4);
        }

        public List<Integer> getLargeCells() {
            return List.of(4);
        }

        public int[] getLargeCornerIndex() {
            return new int[]{0, 2, 4, 5};
        }

        public int getLayoutNumber() {
            return 66;
        }

        public float[] getSpanRatio(int i2) {
            if (i2 == 4) {
                return new float[]{CollageType.largeRatio, CollageType.largeRatio};
            }
            return new float[]{CollageType.smallRatio, CollageType.smallRatio};
        }

        private AnonymousClass5(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType$6  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass6 extends CollageType {
        public /* synthetic */ AnonymousClass6() {
            this("COLLAGE6", 5);
        }

        public List<Integer> getLargeCells() {
            return List.of(0);
        }

        public int getLayoutNumber() {
            return 67;
        }

        public int getSize() {
            return 4;
        }

        public boolean hasLandscapeLargeCell() {
            return true;
        }

        public boolean isValid() {
            return PreferenceFeatures.OneUi8x.STORY_IRREGULAR_COLLAGE;
        }

        private AnonymousClass6(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType$7  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass7 extends CollageType {
        public /* synthetic */ AnonymousClass7() {
            this("COLLAGE7", 6);
        }

        public List<Integer> getLargeCells() {
            return List.of(0);
        }

        public int getLayoutNumber() {
            return 68;
        }

        public int getSize() {
            return 3;
        }

        public boolean hasLandscapeLargeCell() {
            return true;
        }

        public boolean isValid() {
            return PreferenceFeatures.OneUi8x.STORY_IRREGULAR_COLLAGE;
        }

        private AnonymousClass7(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType$8  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass8 extends CollageType {
        public /* synthetic */ AnonymousClass8() {
            this("COLLAGE8", 7);
        }

        public List<Integer> getLargeCells() {
            return List.of(0);
        }

        public int getLayoutNumber() {
            return 69;
        }

        public int getSize() {
            return 5;
        }

        public boolean isValid() {
            return PreferenceFeatures.OneUi8x.STORY_IRREGULAR_COLLAGE;
        }

        private AnonymousClass8(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType$9  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass9 extends CollageType {
        public /* synthetic */ AnonymousClass9() {
            this("NONE", 8);
        }

        public int getLayoutNumber() {
            return -1;
        }

        public boolean isValid() {
            return false;
        }

        private AnonymousClass9(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public static CollageType getType(int i2) {
        if (i2 < 0 || i2 >= values().length) {
            return NONE;
        }
        return values()[i2];
    }

    public static int getTypeValue(CollageType collageType) {
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2] == collageType) {
                return i2 + 1;
            }
        }
        return 0;
    }

    public int[] getCornerRadius(Resources resources, int i2, int i7) {
        int i8;
        int i10;
        int i11;
        float f = (float) i2;
        int floatFromDimension = (int) (ResourceCompat.getFloatFromDimension(resources, (int) R.dimen.story_collage_radius_large) * f);
        int floatFromDimension2 = (int) (ResourceCompat.getFloatFromDimension(resources, (int) R.dimen.story_collage_radius_small) * f);
        int[] largeCornerIndex = getLargeCornerIndex();
        if (largeCornerIndex[0] == i7) {
            i8 = floatFromDimension;
        } else {
            i8 = floatFromDimension2;
        }
        if (largeCornerIndex[1] == i7) {
            i10 = floatFromDimension;
        } else {
            i10 = floatFromDimension2;
        }
        if (largeCornerIndex[2] == i7) {
            i11 = floatFromDimension;
        } else {
            i11 = floatFromDimension2;
        }
        if (largeCornerIndex[3] != i7) {
            floatFromDimension = floatFromDimension2;
        }
        return new int[]{i8, i10, i11, floatFromDimension};
    }

    public List<Integer> getLargeCells() {
        return new ArrayList();
    }

    public int[] getLargeCornerIndex() {
        return new int[0];
    }

    public abstract int getLayoutNumber();

    public int getSize() {
        return 6;
    }

    public float[] getSpanRatio(int i2) {
        return new float[0];
    }

    public boolean hasLandscapeLargeCell() {
        return false;
    }

    public boolean isValid() {
        return true;
    }
}
