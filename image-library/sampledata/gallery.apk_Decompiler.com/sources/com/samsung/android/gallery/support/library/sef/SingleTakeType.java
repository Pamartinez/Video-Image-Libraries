package com.samsung.android.gallery.support.library.sef;

import com.samsung.android.gallery.support.R$string;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashMap;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SingleTakeType {
    VideoOriginal(2948, R$string.shot_mode_original_video),
    VideoFastForward(2949, R$string.shot_mode_fast_forward_clip),
    VideoBoomerang(2950, R$string.shot_mode_rebound_clip),
    VideoReverse(2951, R$string.shot_mode_rewind_clip),
    ImagePanorama(2952, r1),
    ImageSmartCrop(2953, r1),
    ImageFiltered(2954, r1),
    ImageBlackAndWhite(2955, r1),
    ImageCollage(2956, R$string.shot_mode_collage),
    VideoHighlight(2957, R$string.shot_mode_highlight_video),
    VideoSlowMo(2958, R$string.shot_mode_slow_mo_clip),
    Video24TimeLaps(2959, R$string.shot_mode_twenty_four_hr_time_lapse);
    
    public final int stringResId;
    public final int value;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DataHolder {
        static final HashMap<Integer, Integer> map = null;

        static {
            map = new HashMap<Integer, Integer>() {
                {
                    Stream.of(SingleTakeType.values()).forEach(new a(2, this));
                    Integer valueOf = Integer.valueOf(SefInfo.SINGLE_RELIGHTING_BOKEH_INFO.keyCode);
                    int i2 = R$string.shot_mode_portrait;
                    put(valueOf, Integer.valueOf(i2));
                    put(Integer.valueOf(SefInfo.SINGLE_SHOT_BOKEH_INFO.keyCode), Integer.valueOf(i2));
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$new$0(SingleTakeType singleTakeType) {
                    put(Integer.valueOf(singleTakeType.value), Integer.valueOf(singleTakeType.stringResId));
                }
            };
        }
    }

    private SingleTakeType(int i2, int i7) {
        this.value = i2;
        this.stringResId = i7;
    }

    public static int toStringResId(String str) {
        Integer num;
        if (str != null) {
            for (String str2 : str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                if (str2.length() > 0 && (num = DataHolder.map.get(Integer.valueOf(Integer.parseInt(str2)))) != null) {
                    return num.intValue();
                }
            }
        }
        return 0;
    }
}
