package com.samsung.android.gallery.app.ui.dialog.creature.picker;

import com.samsung.android.gallery.app.ui.dialog.abstraction.MvpDialogPresenter;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.ICreaturePickerDialogView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import k7.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreaturePickerDialogPresenter<V extends ICreaturePickerDialogView> extends MvpDialogPresenter<V> {
    private final SubscriberListener mDismissListener;

    public CreaturePickerDialogPresenter(V v) {
        super(v);
        j jVar = new j(8, this);
        this.mDismissListener = jVar;
        this.mBlackboard.subscribe("command://DismissDialog", jVar);
    }

    public void destroy() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.unsubscribe("command://DismissDialog", this.mDismissListener);
        }
    }

    public String getRelationshipType() {
        return (String) this.mBlackboard.pop("data://user/PersonRelationship", null);
    }

    public void onDismissDialog(Object... objArr) {
        dismissDialog();
    }

    public void onItemClicked(MediaItem mediaItem) {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_PEOPLE_PICKER_CHOICE);
        this.mBlackboard.post("data://user/dialog/CreaturePicker", mediaItem);
    }
}
