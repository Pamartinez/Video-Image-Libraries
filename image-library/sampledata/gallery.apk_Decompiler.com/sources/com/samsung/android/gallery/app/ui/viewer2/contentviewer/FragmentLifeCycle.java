package com.samsung.android.gallery.app.ui.viewer2.contentviewer;

import android.content.res.Configuration;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface FragmentLifeCycle {
    boolean handleBlackboardEvent(EventMessage eventMessage) {
        return false;
    }

    void onApplyWindowInsets() {
    }

    void onDestroy() {
    }

    void onPause() {
    }

    void onResolutionChanged() {
    }

    void onResume() {
    }

    void onStart() {
    }

    void onStop() {
    }

    void onConfigurationChanged(Configuration configuration) {
    }

    void onMultiWindowModeChanged(boolean z) {
    }

    void onTableModeChanged(boolean z, int i2) {
    }
}
