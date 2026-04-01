package com.samsung.android.gallery.settings.ui;

import Fa.C0550d;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.InputBlock;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BasePresenter<V extends IBaseView> {
    protected static final InputBlock sInputBlock = new InputBlock();
    protected final String TAG = getClass().getSimpleName();
    private Subscriber mSubscriber;
    protected final V mView;

    public BasePresenter(V v) {
        this.mView = v;
    }

    private boolean isSecureMode() {
        if (Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_AFW_MODE)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void setLocalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        V v = this.mView;
        Objects.requireNonNull(v);
        arrayList.add(new SubscriberInfo("command://UiEvent", new C0550d(v, 0)).setWorkingOnUI());
        V v6 = this.mView;
        Objects.requireNonNull(v6);
        arrayList.add(new SubscriberInfo("command://UiBroadcastEvent", new C0550d(v6, 1)).setWorkingOnUI());
    }

    public Activity getActivity() {
        return this.mView.getActivity();
    }

    public Context getContext() {
        return this.mView.getContext();
    }

    public boolean isDestroyed() {
        return this.mView.isDestroyed();
    }

    public void onAttach(Blackboard blackboard) {
        this.mSubscriber = new Subscriber(blackboard) {
            public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
                BasePresenter.this.setGlobalSubscriberList(arrayList);
            }

            public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
                BasePresenter.this.setLocalSubscriberList(arrayList);
            }
        };
    }

    public void onCreate() {
        this.mSubscriber.onCreate();
    }

    public void onDestroy() {
        this.mSubscriber.onDestroy();
    }

    public void onDetach() {
        this.mSubscriber = null;
    }

    public boolean setInputBlock(String str, int i2) {
        return sInputBlock.set(str, (long) i2);
    }

    public final boolean supportGalaxyApps() {
        if (Features.isEnabled(Features.IS_UPSM) || !Features.isEnabled(Features.SUPPORT_GALAXY_APPS)) {
            return false;
        }
        return true;
    }

    public final boolean supportTermsAndConditions() {
        if (isSecureMode() || Features.isEnabled(Features.IS_UPSM) || !Features.isEnabled(Features.SUPPORT_SHARING_SERVICE)) {
            return false;
        }
        return true;
    }

    public void onResume() {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void setGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }
}
