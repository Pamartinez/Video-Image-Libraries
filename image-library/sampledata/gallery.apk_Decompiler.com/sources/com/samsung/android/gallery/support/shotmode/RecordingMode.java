package com.samsung.android.gallery.support.shotmode;

import com.samsung.android.gallery.support.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RecordingMode {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ApvHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                add(30);
                add(31);
                add(32);
                add(33);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Hdr10PlusHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                add(10);
                add(25);
                add(88);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class HyperLapseHolder {
        static final ArrayList<Integer> listNds = new ArrayList<Integer>() {
            {
                add(5);
                add(122);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LogVideoHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                add(26);
                add(27);
                add(87);
                add(31);
                add(33);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class NdsHolder {
        static final ArrayList<Integer> listNds = new ArrayList<Integer>() {
            {
                add(101);
                add(111);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PortraitVideoHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                add(24);
                add(28);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ProHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                add(16);
                add(25);
                add(27);
                add(32);
                add(33);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class QueryHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                addAll(SuperSlowMoHolder.list);
                addAll(SlowMoHolder.list);
                addAll(HyperLapseHolder.listNds);
                if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
                    addAll(ProHolder.list);
                    addAll(PortraitVideoHolder.list);
                }
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SlowMoHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                add(1);
                add(12);
                add(13);
                add(15);
                add(19);
                add(21);
            }
        };
        public static final ArrayList<Integer> listNds = new ArrayList<Integer>() {
            {
                addAll(SlowMoHolder.list);
                add(101);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SuggestedEditTitleHolder {
        static final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>() {
            {
                put(90, Integer.valueOf(R$string.superslow_effect_forward));
                put(91, Integer.valueOf(R$string.superslow_effect_reverse));
                put(92, Integer.valueOf(R$string.superslow_effect_forward_and_reverse));
                put(93, Integer.valueOf(R$string.speed_mix));
                put(94, Integer.valueOf(R$string.speed_run));
                put(95, Integer.valueOf(R$string.highlights));
                put(96, Integer.valueOf(R$string.clip));
                put(98, Integer.valueOf(R$string.slow_motion));
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SuperSlowMoHolder {
        static final ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                add(7);
                add(8);
                add(9);
                add(18);
                add(22);
            }
        };
        static final ArrayList<Integer> listNds = new ArrayList<Integer>() {
            {
                addAll(SuperSlowMoHolder.list);
                add(111);
            }
        };
    }

    public static boolean isApv(int i2) {
        return ApvHolder.list.contains(Integer.valueOf(i2));
    }

    public static boolean isHdr10Plus(int i2) {
        return Hdr10PlusHolder.list.contains(Integer.valueOf(i2));
    }

    public static boolean isLogVideo(int i2) {
        return LogVideoHolder.list.contains(Integer.valueOf(i2));
    }

    public static boolean isNds(int i2) {
        return NdsHolder.listNds.contains(Integer.valueOf(i2));
    }

    public static boolean isPortraitVideo(int i2) {
        return PortraitVideoHolder.list.contains(Integer.valueOf(i2));
    }

    public static boolean isPro(int i2) {
        return ProHolder.list.contains(Integer.valueOf(i2));
    }

    public static boolean isSlowMo(int i2) {
        return SlowMoHolder.list.contains(Integer.valueOf(i2));
    }

    public static boolean isSuperSlowMo(int i2) {
        return SuperSlowMoHolder.list.contains(Integer.valueOf(i2));
    }

    public static ArrayList<Integer> listOfApv() {
        return ApvHolder.list;
    }

    public static ArrayList<Integer> listOfHyperLapse() {
        return HyperLapseHolder.listNds;
    }

    public static ArrayList<Integer> listOfLogVideo() {
        return LogVideoHolder.list;
    }

    public static ArrayList<Integer> listOfNds() {
        return NdsHolder.listNds;
    }

    public static ArrayList<Integer> listOfPro() {
        return ProHolder.list;
    }

    public static ArrayList<Integer> listOfQuery() {
        return QueryHolder.list;
    }

    public static ArrayList<Integer> listOfSlowMo() {
        return SlowMoHolder.list;
    }

    public static ArrayList<Integer> listOfSlowMoNds() {
        return SlowMoHolder.listNds;
    }

    public static ArrayList<Integer> listOfSuperSlowMo() {
        return SuperSlowMoHolder.list;
    }

    public static ArrayList<Integer> listOfSuperSlowMoNds() {
        return SuperSlowMoHolder.listNds;
    }

    public static int toSuggestedEditTitleResId(int i2) {
        Integer num = SuggestedEditTitleHolder.map.get(Integer.valueOf(i2));
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }
}
