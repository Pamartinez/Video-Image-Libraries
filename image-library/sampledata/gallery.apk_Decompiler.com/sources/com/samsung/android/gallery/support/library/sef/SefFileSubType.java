package com.samsung.android.gallery.support.library.sef;

import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.gallery.support.R$string;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SefFileSubType {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DataHolder {
        static final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>() {
            {
                put(3, Integer.valueOf(R$string.front));
                int i2 = R$string.rear;
                put(5, Integer.valueOf(i2));
                put(257, Integer.valueOf(R$string.ultra_wide));
                put(Integer.valueOf(ASVLOFFSCREEN.ASVL_PAF_RGB24_B8G8R8), Integer.valueOf(R$string.wide));
                int i7 = R$string.telephoto;
                put(1025, Integer.valueOf(i7));
                put(Integer.valueOf(ASVLOFFSCREEN.ASVL_PAF_NV12), Integer.valueOf(i7));
                put(4097, Integer.valueOf(i2));
            }
        };
    }

    public static int toStringResId(int i2) {
        Integer num = DataHolder.map.get(Integer.valueOf(i2));
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }
}
