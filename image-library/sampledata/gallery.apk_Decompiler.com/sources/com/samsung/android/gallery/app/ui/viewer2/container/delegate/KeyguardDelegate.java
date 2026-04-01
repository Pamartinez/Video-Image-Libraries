package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import A8.C0545b;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.SystemUi;
import java.util.ArrayList;
import k7.j;
import k7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeyguardDelegate extends AbsVuDelegate<IVuContainerView> {
    public KeyguardDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private String getQuickViewDataLocationKey() {
        int[] iArr;
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
        if (launchIntent != null) {
            iArr = launchIntent.getViewBuckets().stream().mapToInt(new C0545b(27)).toArray();
        } else {
            iArr = null;
        }
        if (iArr == null || iArr.length <= 0) {
            return DataKey.getQuickViewDataKeyLegacy();
        }
        return DataKey.getQuickViewDataKey(iArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$0(Subscriber subscriber, Object obj, Bundle bundle) {
        subscriber.unsubscribe("global://event/keyguardUnlocked");
        subscriber.unsubscribe("global://event/screenOff");
        onKeyguardUnlocked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$1(Object obj, Bundle bundle) {
        onScreenOff();
    }

    private void onKeyguardUnlocked() {
        Log.d(this.TAG, "onKeyguardUnlocked");
        SystemUi.clearShowWhenLocked(((IVuContainerView) this.mView).getActivity());
        MediaDataDelegate mediaDataDelegate = (MediaDataDelegate) getDelegate(MediaDataDelegate.class);
        if (mediaDataDelegate == null || !((ContainerModel) this.mModel).getStateHelper().isQuickView()) {
            Log.d(this.TAG, "onKeyguardUnlocked : not quickView");
        } else {
            mediaDataDelegate.reopenMediaData(getQuickViewDataLocationKey());
        }
    }

    private void onScreenOff() {
        Log.majorEvent(this.TAG, "do CMD_SUICIDE when screen off in screen locked");
        this.mBlackboard.post("command://request_suicide", (Object) null);
    }

    public void createGlobalSubscriberList(Subscriber subscriber, ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://event/keyguardUnlocked", new l(0, this, subscriber)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://event/screenOff", new j(1, this)));
    }
}
