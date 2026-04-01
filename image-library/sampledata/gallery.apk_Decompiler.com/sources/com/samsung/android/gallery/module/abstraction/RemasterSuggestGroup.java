package com.samsung.android.gallery.module.abstraction;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.AppResources;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum RemasterSuggestGroup {
    NONE(0),
    REMASTER_GIF(R$string.remaster_gifs),
    REMOVE_SHADOWS_AND_REFLECTIONS(R$string.remove_shadows_and_reflections),
    REMOVE_MOIRE_PATTERNS(R$string.remove_moire_patterns),
    BRIGHTEN_AND_REDUCE_BLUR(R$string.brighten_and_reduce_blur),
    CLEAR_UP_BLURRY_FACES(R$string.clear_up_blurry_faces),
    UPSCALE(R$string.Upscale),
    CLEAR_LENS(R$string.improve_color);
    
    final int resId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BrightenAndReduceBlurHolder {
        static final List<List<Integer>> set = null;

        static {
            set = Arrays.asList(new List[]{Collections.singletonList(5), Collections.singletonList(4), Collections.singletonList(3), Arrays.asList(new Integer[]{5, 3}), Arrays.asList(new Integer[]{5, 6, 3}), Arrays.asList(new Integer[]{5, 6}), Arrays.asList(new Integer[]{16, 5}), Arrays.asList(new Integer[]{17, 4}), Arrays.asList(new Integer[]{3, 17}), Arrays.asList(new Integer[]{17, 5, 16}), Arrays.asList(new Integer[]{17, 4, 5}), Arrays.asList(new Integer[]{17, 3, 5}), Arrays.asList(new Integer[]{5, 1, 3}), Arrays.asList(new Integer[]{1, 4}), Arrays.asList(new Integer[]{1, 3}), Arrays.asList(new Integer[]{5, 1})});
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$match$0(List list, List list2) {
            if (list.size() != list2.size() || !new HashSet(list2).containsAll(list)) {
                return false;
            }
            return true;
        }

        public static boolean match(List<Integer> list) {
            return set.stream().anyMatch(new e(0, list));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClearLensHolder {
        static final List<List<Integer>> set = null;

        static {
            set = Arrays.asList(new List[]{Collections.singletonList(16), Arrays.asList(new Integer[]{16, 1}), Arrays.asList(new Integer[]{16, 17})});
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$match$0(List list, List list2) {
            if (list.size() != list2.size() || !new HashSet(list2).containsAll(list)) {
                return false;
            }
            return true;
        }

        public static boolean match(List<Integer> list) {
            return set.stream().anyMatch(new e(1, list));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClearUpBlurryFacesHolder {
        static final List<List<Integer>> set = null;

        static {
            set = Arrays.asList(new List[]{Collections.singletonList(8), Arrays.asList(new Integer[]{8, 1})});
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$match$0(List list, List list2) {
            if (list.size() != list2.size() || !new HashSet(list2).containsAll(list)) {
                return false;
            }
            return true;
        }

        public static boolean match(List<Integer> list) {
            return set.stream().anyMatch(new e(2, list));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RemoveShadowAndReflections {
        static final List<List<Integer>> set = null;

        static {
            set = Arrays.asList(new List[]{Collections.singletonList(14), Collections.singletonList(13), Arrays.asList(new Integer[]{5, 13, 16}), Arrays.asList(new Integer[]{17, 5, 13}), Arrays.asList(new Integer[]{17, 13, 16}), Arrays.asList(new Integer[]{17, 5, 13, 16})});
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$match$0(List list, List list2) {
            if (list.size() != list2.size() || !new HashSet(list2).containsAll(list)) {
                return false;
            }
            return true;
        }

        public static boolean match(List<Integer> list) {
            return set.stream().anyMatch(new e(3, list));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UpscaleHolder {
        static final List<List<Integer>> set = null;

        static {
            set = Arrays.asList(new List[]{Collections.singletonList(11), Arrays.asList(new Integer[]{5, 1, 3}), Arrays.asList(new Integer[]{1, 4}), Arrays.asList(new Integer[]{1, 3}), Arrays.asList(new Integer[]{5, 1}), Arrays.asList(new Integer[]{8, 1}), Collections.singletonList(1), Arrays.asList(new Integer[]{16, 1})});
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$match$0(List list, List list2) {
            if (list.size() != list2.size() || !new HashSet(list2).containsAll(list)) {
                return false;
            }
            return true;
        }

        public static boolean match(List<Integer> list) {
            return set.stream().anyMatch(new e(4, list));
        }
    }

    private RemasterSuggestGroup(int i2) {
        this.resId = i2;
    }

    public static RemasterSuggestGroup get(int i2) {
        RemasterSuggestGroup[] values = values();
        return (i2 < 0 || i2 >= values.length) ? NONE : values[i2];
    }

    public static String getTitle(int i2) {
        int i7 = get(i2).resId;
        if (i7 > 0) {
            return AppResources.getString(i7);
        }
        return "";
    }

    public static boolean isResolution(List<Integer> list) {
        return UpscaleHolder.match((List) list.stream().filter(new h(2)).collect(Collectors.toList()));
    }

    public static RemasterSuggestGroup get(List<Integer> list) {
        List list2 = (List) list.stream().filter(new h(1)).collect(Collectors.toList());
        if (BrightenAndReduceBlurHolder.match(list2)) {
            return BRIGHTEN_AND_REDUCE_BLUR;
        }
        if (ClearUpBlurryFacesHolder.match(list2)) {
            return CLEAR_UP_BLURRY_FACES;
        }
        if (ClearLensHolder.match(list2)) {
            return CLEAR_LENS;
        }
        if (RemoveShadowAndReflections.match(list2)) {
            return REMOVE_SHADOWS_AND_REFLECTIONS;
        }
        if (list2.size() == 1) {
            if (list2.contains(11)) {
                return REMASTER_GIF;
            }
            if (list2.contains(9)) {
                return REMOVE_MOIRE_PATTERNS;
            }
            if (list2.contains(1)) {
                return UPSCALE;
            }
        }
        return NONE;
    }
}
