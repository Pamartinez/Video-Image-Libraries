package com.samsung.android.gallery.widget.popover;

import android.app.Activity;
import android.graphics.Point;
import android.view.View;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PopoverHelper {
    public static void adjustPopOverOptions(Activity activity, int[] iArr, int[] iArr2, Point[] pointArr, int[] iArr3) {
        SeApiCompat.adjustPopOverOptions(activity, iArr, iArr2, pointArr, iArr3);
    }

    public static void clearPopoverInfo(Blackboard blackboard) {
        PopoverInfo popoverInfo;
        if (blackboard != null && (popoverInfo = getPopoverInfo(blackboard)) != null) {
            popoverInfo.clear();
            blackboard.erase("data://user/popoverInfo");
        }
    }

    public static PopoverInfo getPopoverInfo(Blackboard blackboard) {
        if (blackboard != null) {
            return (PopoverInfo) blackboard.read("data://user/popoverInfo");
        }
        return null;
    }

    public static void notifyAnchorChanged(Blackboard blackboard, View view, int i2, boolean z) {
        PopoverInfo popoverInfo;
        if (blackboard != null && (popoverInfo = (PopoverInfo) blackboard.read("data://user/popoverInfo")) != null && popoverInfo.getAnchorType() == i2) {
            popoverInfo.notifyAnchorChanged(view.findViewById(popoverInfo.getActionId()), z);
        }
    }

    public static void notifyAnchorSelfChanged(Blackboard blackboard) {
        PopoverInfo popoverInfo;
        if (blackboard != null && (popoverInfo = (PopoverInfo) blackboard.read("data://user/popoverInfo")) != null) {
            popoverInfo.notifyAnchorSelfChanged();
        }
    }

    public static void publishPopoverInfo(Blackboard blackboard, View view, int i2, int i7) {
        if (blackboard != null) {
            blackboard.publish("data://user/popoverInfo", new PopoverInfo().setAnchorType(i2).setAnchorView(view).setActionId(i7));
        }
    }

    public static void publishPopoverShareInfo(Blackboard blackboard, Point point) {
        if (blackboard != null) {
            blackboard.publish("data://user/shareViaAnchorPos", point);
        }
    }
}
