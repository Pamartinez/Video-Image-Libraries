package com.samsung.android.gallery.module.utils;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.Iterator;
import java.util.Stack;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewerUtils {
    public static float getDecorAlphaInBottomSheetSlide(float f) {
        return Math.max(0.0f, Math.min(1.0f, 1.0f - (f * 2.0f)));
    }

    public static boolean isEditDetailsBottomViewVisible(boolean z, boolean z3) {
        if (z || !z3) {
            return false;
        }
        return true;
    }

    public static boolean isMultipleViewerStacked(Blackboard blackboard) {
        Stack stack = (Stack) blackboard.read("debug://FragmentStack");
        if (stack != null) {
            Iterator it = stack.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                if (((String) it.next()).contains("viewer") && (i2 = i2 + 1) > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean supportFilmStrip(String str) {
        if ((!PreferenceFeatures.OneUi30.PHOTO_STRIP || LocationKey.isDynamicViewList(str) || LocationKey.isColorCorrectView(str) || LocationKey.isRevitalizationView(str) || LocationKey.isSuggests(str) || LocationKey.isMtp(str) || LocationKey.isAllDayTimeLapse(str) || LocationKey.isLongExposure(str) || LocationKey.isAiEditGroupPanelViewer(str) || LocationKey.isSecondDepthGroupPanelView(str) || ArgumentsUtil.getArgValue(str, "quickCropId", -1) != -1) && !PocFeatures.isEnabled(PocFeatures.ExpandedViewNavWidget)) {
            return false;
        }
        return true;
    }
}
