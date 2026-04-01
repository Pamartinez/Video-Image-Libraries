package com.samsung.android.gallery.app.activity.abstraction;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IGalleryActivityView {
    boolean clearBackStackImmediate();

    boolean commitFragment(Fragment fragment, String str);

    boolean finishFragment();

    Activity getActivity();

    Context getContext();

    LaunchIntent getLaunchIntent();

    IBaseFragment getTopFragment();

    String getTopFragmentTag();

    void handleBroadcastEvent(EventMessage eventMessage);

    void handleEvent(EventMessage eventMessage);

    boolean isActivityDestroyed();

    boolean isActivityResumed();

    boolean isDialogShowing(String str);

    boolean isExistFragment(String str);

    boolean isRestartedFromQuickView() {
        return false;
    }

    void resetAutoRotation(boolean z);

    void setBackPressedCallbackEnabled();

    void setBackPressedCallbackEnabled(String str);

    void setBackgroundColor(int i2);

    void setShapeButtonStatusChanged();

    void showDialog(DialogFragment dialogFragment, String str);

    void syncFragmentStack();

    void updateGradientProtection(int i2);

    void clearContentLayoutBgForExternal() {
    }

    void restoreContentLayoutBgForExternal() {
    }

    void setContentLayoutBgForExternal() {
    }
}
