package com.samsung.android.ocr;

import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MOCRLayout {
    public static final int LAYOUT_FIGURE = 4;
    public static final int LAYOUT_LIST = 2;
    public static final int LAYOUT_TABLE = 3;
    public static final int LAYOUT_TEXT = 0;
    public static final int LAYOUT_TITLE = 1;
    public static final int SUPPORT_COUNT = 5;

    public static List<Integer> getColors() {
        return Arrays.asList(new Integer[]{-16711681, -16711936, -65281, -65536, -16776961});
    }

    public static String getString(int i2) {
        if (i2 == 0) {
            return "Text";
        }
        if (1 == i2) {
            return "Title";
        }
        if (2 == i2) {
            return "LIST";
        }
        if (3 == i2) {
            return "Table";
        }
        if (4 == i2) {
            return "Figure";
        }
        return "";
    }
}
