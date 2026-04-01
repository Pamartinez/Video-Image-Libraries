package com.samsung.android.gallery.app.ui.viewer2.grouppanel.list;

import android.view.MotionEvent;
import com.samsung.android.gallery.widget.listview.SingleTakenListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupPanelListViewTouchListener implements SingleTakenListView.onDelegateTouchListener {
    public boolean onDelegateTouch(MotionEvent motionEvent) {
        return true;
    }

    public boolean supportDelegateTouchEvent() {
        return true;
    }

    public boolean supportFlingDownTouchEvent() {
        return true;
    }

    public void onStartDelegate(MotionEvent motionEvent) {
    }
}
