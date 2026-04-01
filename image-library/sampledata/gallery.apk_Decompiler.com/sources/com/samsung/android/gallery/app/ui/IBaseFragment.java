package com.samsung.android.gallery.app.ui;

import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import java.io.PrintWriter;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IBaseFragment {
    boolean clearAdvancedMouseFocus() {
        return false;
    }

    boolean commitChildFragment(Fragment fragment, String str) {
        return false;
    }

    String getAnalyticsScreenId(String str);

    String getAppState() {
        return null;
    }

    List<Fragment> getChildFragments() {
        return null;
    }

    IBaseFragment getFocusedChild() {
        return null;
    }

    Lifecycle getLifecycle();

    String getScreenId() {
        return null;
    }

    boolean isDefaultExitTransitioning();

    boolean isDestroyed();

    boolean isInputBlocked();

    boolean isInputBlockedExceptBackKey() {
        return false;
    }

    boolean isVolatileFragment() {
        return false;
    }

    boolean onAdvancedMouseEvent(MotionEvent motionEvent) {
        return false;
    }

    boolean onBackPressed();

    boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return false;
    }

    boolean onHandleEvent(EventMessage eventMessage);

    boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        return false;
    }

    boolean onKeyUp(int i2, KeyEvent keyEvent) {
        return false;
    }

    boolean onPenEvent(MotionEvent motionEvent) {
        return false;
    }

    boolean onPreSyncFragmentStack() {
        return false;
    }

    void releaseInputBlocking();

    void setDefaultExitTransitioning(boolean z);

    boolean setInputBlock(String str) {
        return false;
    }

    boolean setInputBlock(String str, int i2);

    boolean supportEnterDefaultTransition() {
        return false;
    }

    boolean supportExitDefaultTransition() {
        return false;
    }

    boolean supportExitPredictiveBack() {
        return false;
    }

    boolean supportPinchShrink() {
        return true;
    }

    void onPrePause(boolean z) {
    }

    void reservePreventNoBackground(boolean z) {
    }

    void dump(PrintWriter printWriter, String str) {
    }

    void setCustomAnimations(FragmentTransaction fragmentTransaction, IBaseFragment iBaseFragment) {
    }
}
