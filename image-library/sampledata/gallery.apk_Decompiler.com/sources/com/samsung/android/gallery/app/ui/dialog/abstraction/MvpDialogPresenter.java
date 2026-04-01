package com.samsung.android.gallery.app.ui.dialog.abstraction;

import android.app.Activity;
import android.content.Context;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.dialog.abstraction.IMvpDialogView;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MvpDialogPresenter<V extends IMvpDialogView> extends Subscriber implements EventContext {
    protected V mView;

    public MvpDialogPresenter(V v) {
        super(v.getBlackboard());
        this.mView = v;
        onCreate();
    }

    public void dismissDialog() {
        this.mView.dismissDialog();
    }

    public Activity getActivity() {
        return this.mView.getActivity();
    }

    public Context getApplicationContext() {
        return this.mView.getActivity().getApplicationContext();
    }

    public Context getContext() {
        return this.mView.getActivity();
    }

    public String getLocationKey() {
        return (String) this.mBlackboard.read("location://variable/currentv1");
    }

    public void onDestroyView() {
        onDestroy();
    }

    public void onNegativeClicked() {
        dismissDialog();
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        this.mView.postAnalyticsLog(analyticsEventId);
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str) {
        this.mView.postAnalyticsLog(analyticsEventId, str);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }
}
