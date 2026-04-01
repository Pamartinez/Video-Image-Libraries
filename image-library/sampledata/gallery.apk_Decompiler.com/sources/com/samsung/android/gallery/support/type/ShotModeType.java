package com.samsung.android.gallery.support.type;

import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.Features;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ShotModeType {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DualRecShotHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                add(3088);
                if (Features.isEnabled(Features.SUPPORT_DUAL_REC)) {
                    add(3312);
                }
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ImageShotHolder {
        static ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                addAll(ImageShotHolder.listNonSelfie);
                addAll(SelfieShotHolder.list);
            }
        };
        static ArrayList<Integer> listNonSelfie = new ArrayList<Integer>() {
            {
                add(2272);
                add(2640);
                add(2608);
                add(2736);
                if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                    add(3008);
                    add(3024);
                    add(3040);
                }
                if (SdkConfig.atLeast(SdkConfig.GED.T)) {
                    add(3216);
                    add(3232);
                }
                add(2880);
                add(2752);
                add(2864);
                if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
                    add(2960);
                    add(2544);
                }
                addAll(DualRecShotHolder.list);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LiveFocusShotHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                add(2736);
                add(2752);
                add(2880);
                if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                    add(3008);
                    add(3024);
                    add(3040);
                }
                if (SdkConfig.atLeast(SdkConfig.GED.T)) {
                    add(3216);
                    add(3232);
                }
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SelfieShotHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                addAll(SelfieShotHolder.listNonLiveFocus);
                if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                    add(3008);
                }
            }
        };
        static final ArrayList<Integer> listNonLiveFocus = new ArrayList<Integer>() {
            {
                add(2304);
                add(2320);
                add(2416);
                add(2417);
                add(2432);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SpecialVideoHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                add(Integer.valueOf(SefInfo.SUPER_SLOW_MOTION_DATA.keyCode));
                add(Integer.valueOf(SefInfo.SLOW_MOTION_DATA.keyCode));
                add(Integer.valueOf(SefInfo.FAST_MOTION_DATA.keyCode));
                add(Integer.valueOf(SefInfo.HIGHLIGHT_VIDEO_DATA.keyCode));
                add(Integer.valueOf(SefInfo.HYPER_LAPSE_DATA.keyCode));
            }
        };
    }

    public static boolean is3dCapture(String str) {
        if ("3d_capture".equals(str) || "3d_capture_image".equals(str) || "3d_capture_video".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isLiveFocus(int i2) {
        return LiveFocusShotHolder.list.contains(Integer.valueOf(i2));
    }

    public static ArrayList<Integer> listOfDualRec() {
        return DualRecShotHolder.list;
    }

    public static ArrayList<Integer> listOfImage(boolean z) {
        if (z) {
            return ImageShotHolder.listNonSelfie;
        }
        return ImageShotHolder.list;
    }

    public static ArrayList<Integer> listOfLiveFocus() {
        return LiveFocusShotHolder.list;
    }

    public static ArrayList<Integer> listOfSelfie(boolean z) {
        if (z) {
            return SelfieShotHolder.listNonLiveFocus;
        }
        return SelfieShotHolder.list;
    }

    public static ArrayList<Integer> listOfSpecialVideo() {
        return SpecialVideoHolder.list;
    }
}
