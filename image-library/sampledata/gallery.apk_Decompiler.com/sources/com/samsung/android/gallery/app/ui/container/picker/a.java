package com.samsung.android.gallery.app.ui.container.picker;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SubscriberListener {
    public final /* synthetic */ PickerConPresenter d;

    public /* synthetic */ a(PickerConPresenter pickerConPresenter) {
        this.d = pickerConPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        this.d.onStartShrinkAnimation(obj, bundle);
    }
}
