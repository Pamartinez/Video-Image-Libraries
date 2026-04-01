package com.samsung.android.gallery.app.ui.list.timeline;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.timeline.ITimelineView2;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Timeline2Presenter<V extends ITimelineView2> extends TimelinePresenter<V> {
    public Timeline2Presenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        switch (eventMessage.what) {
            case 1130:
                ((ITimelineView2) this.mView).updateQuickSearchDateFilterCard();
                break;
            case 1131:
            case 1132:
                break;
            default:
                return super.handleEvent(eventMessage);
        }
        forceReloadData();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_quick_search) {
            ((ITimelineView2) this.mView).updateQuickSearchViewVisibility();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
