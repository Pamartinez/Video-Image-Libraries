package com.samsung.android.gallery.widget.listview.pinch.v3;

import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IFocused {
    float getFocusedX() {
        return 0.0f;
    }

    float getFocusedY() {
        return 0.0f;
    }

    float getFocusedYInView() {
        return 0.0f;
    }

    int getGridSize();

    String getLog() {
        return "FocusedInfo[" + getGridSize() + "] - ViewPosition : " + getViewPosition() + ", ScrollOffset : " + getScrollOffset() + ", Coor[" + getFocusedX() + ArcCommonLog.TAG_COMMA + getFocusedY() + ArcCommonLog.TAG_COMMA + getFocusedYInView() + "]";
    }

    float getScrollOffset();

    int getViewPosition();
}
