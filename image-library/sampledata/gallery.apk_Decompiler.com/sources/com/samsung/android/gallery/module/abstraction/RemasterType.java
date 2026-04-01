package com.samsung.android.gallery.module.abstraction;

import A.a;
import Ad.C0720a;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RemasterType {
    public static final String[] REMASTER_TYPE = {"EndToEnd", "Upscale", "PopImage", "LowLight", "Hdr", "Deblur", "Denoise", "Autotone", "PhotoPolish", "Demoire", "#10", "Gif", "#12", "ShadowRemoval", "ReflectionRemoval", "UwRetouch", "ClearLens", "FlareRemoval", "#18", "Colorize", "AutoTilt", "#21", "#22", "#23", "#24", "3DPhoto", "#26", "#27", "#28", "#29", "Portrait", "BestFace", "#32", "#33", "#34"};

    private static boolean equals(List<Integer> list, List<Integer> list2) {
        if (list.size() != list2.size() || !new HashSet(list).containsAll(list2)) {
            return false;
        }
        return true;
    }

    private static String getDecodedTypeEventDetail(long j2, String str) {
        List list = (List) VslMesDetectorCompat.decodeEnhances(j2, LocationKey.isRemasterPictures(str)).stream().filter(new h(4)).sorted().collect(Collectors.toCollection(new C0720a(1)));
        if (list.isEmpty()) {
            return null;
        }
        return (String) list.stream().map(new g(3)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "", GlobalPostProcInternalPPInterface.SPLIT_REGEX));
    }

    private static String getMultiTypesEventDetail(List<Integer> list) {
        if (isUpscaleDeblur(list)) {
            return AnalyticsDetail.EVENT_DETAIL_UPSCALE_DEBLUR.toString();
        }
        if (isDenoiseDeblur(list)) {
            return AnalyticsDetail.EVENT_DETAIL_DENOISE_DEBLUR.toString();
        }
        if (isLowLightDeblur(list)) {
            return AnalyticsDetail.EVENT_DETAIL_DEBLUR_LOW_LIGTH.toString();
        }
        if (isDenoiseDeblurLowLight(list)) {
            return AnalyticsDetail.EVENT_DETAIL_DEBLUR_DENOISE_LOW_LIGTH.toString();
        }
        if (isDeblurUpscaelLowLight(list)) {
            return AnalyticsDetail.EVENT_DETAIL_DEBLUR_UPSCALE_LOW_LIGTH.toString();
        }
        if (isHdrUpscale(list)) {
            return AnalyticsDetail.EVENT_DETAIL_HDR_UPSCALE.toString();
        }
        if (isLowLightUpscale(list)) {
            return AnalyticsDetail.EVENT_DETAIL_LOW_LIGTH_UPSCALE.toString();
        }
        if (isUpscaleOld(list)) {
            return AnalyticsDetail.EVENT_DETAIL_UPSCALE_SHARPEN_BLURRY_FACE.toString();
        }
        return null;
    }

    public static String getRemasterTypeEventDetail(long j2, String str, boolean z) {
        String saveEventDetail;
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            return getDecodedTypeEventDetail(j2, str);
        }
        String singleTypeEventDetail = getSingleTypeEventDetail(j2);
        if (singleTypeEventDetail != null) {
            return singleTypeEventDetail;
        }
        List list = (List) VslMesDetectorCompat.decodeEnhances(j2, LocationKey.isRemasterPictures(str)).stream().filter(new h(3)).collect(Collectors.toCollection(new C0720a(1)));
        if (!z || (saveEventDetail = getSaveEventDetail(j2, list)) == null) {
            return getMultiTypesEventDetail(list);
        }
        return saveEventDetail;
    }

    private static String getSaveEventDetail(long j2, List<Integer> list) {
        if (11 == j2) {
            return AnalyticsDetail.EVENT_DETAIL_ENHANCE_GIF.toString();
        }
        if (list.contains(15)) {
            return AnalyticsDetail.EVENT_DETAIL_FIX_LENS_DISTORTION.toString();
        }
        if (list.contains(17)) {
            return AnalyticsDetail.EVENT_DETAIL_REMOVE_FLARE.toString();
        }
        if (list.contains(19)) {
            return AnalyticsDetail.EVENT_DETAIL_COLORIZE.toString();
        }
        return null;
    }

    private static String getSingleTypeEventDetail(long j2) {
        if (1 == j2) {
            return AnalyticsDetail.EVENT_DETAIL_UPSCALE.toString();
        }
        if (3 == j2) {
            return AnalyticsDetail.EVENT_DETAIL_LOW_LIGHT.toString();
        }
        if (4 == j2) {
            return AnalyticsDetail.EVENT_DETAIL_HDR.toString();
        }
        if (5 == j2) {
            return AnalyticsDetail.EVENT_DETAIL_DEBLUR.toString();
        }
        if (7 == j2) {
            return AnalyticsDetail.EVENT_DETAIL_AUTO_TONE.toString();
        }
        if (8 == j2) {
            return AnalyticsDetail.EVENT_DETAIL_SHARPEN_BLURRY_FACE.toString();
        }
        if (9 == j2) {
            return AnalyticsDetail.EVENT_DETAIL_REMOVE_MOIRE.toString();
        }
        if (13 == j2) {
            return AnalyticsDetail.EVENT_DETAIL_REMOVE_SHADOW.toString();
        }
        if (14 == j2) {
            return AnalyticsDetail.EVENT_DETAIL_REMOVE_REFLECTIONS.toString();
        }
        return null;
    }

    public static boolean isDeblurUpscaelLowLight(List<Integer> list) {
        return equals(Arrays.asList(new Integer[]{5, 1, 3}), list);
    }

    public static boolean isDenoiseDeblur(List<Integer> list) {
        return equals(Arrays.asList(new Integer[]{6, 5}), list);
    }

    public static boolean isDenoiseDeblurLowLight(List<Integer> list) {
        return equals(Arrays.asList(new Integer[]{6, 5, 3}), list);
    }

    public static boolean isGIFs(List<Integer> list) {
        return list.contains(11);
    }

    public static boolean isHdrUpscale(List<Integer> list) {
        return equals(Arrays.asList(new Integer[]{4, 1}), list);
    }

    public static boolean isLowLightDeblur(List<Integer> list) {
        return equals(Arrays.asList(new Integer[]{3, 5}), list);
    }

    public static boolean isLowLightUpscale(List<Integer> list) {
        return equals(Arrays.asList(new Integer[]{3, 1}), list);
    }

    public static boolean isShadowAndReflection(List<Integer> list) {
        if (list.contains(13) || list.contains(14)) {
            return true;
        }
        return false;
    }

    public static boolean isUpscaleDeblur(List<Integer> list) {
        return equals(Arrays.asList(new Integer[]{1, 5}), list);
    }

    public static boolean isUpscaleOld(List<Integer> list) {
        return equals(Arrays.asList(new Integer[]{1, 8}), list);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getDecodedTypeEventDetail$1(Integer num) {
        if (num.intValue() > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getRemasterTypeEventDetail$0(Integer num) {
        if (num.intValue() > 0) {
            return true;
        }
        return false;
    }

    public static String toDebugString(long j2) {
        if (j2 >= 0) {
            String[] strArr = REMASTER_TYPE;
            if (j2 < ((long) strArr.length)) {
                return strArr[(int) j2];
            }
        }
        return a.f("Unknown#", j2);
    }

    public static String getRemasterTypeEventDetail(long j2, String str) {
        return getRemasterTypeEventDetail(j2, str, false);
    }
}
