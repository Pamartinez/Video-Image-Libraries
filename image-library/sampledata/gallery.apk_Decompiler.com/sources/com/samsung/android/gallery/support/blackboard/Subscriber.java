package com.samsung.android.gallery.support.blackboard;

import Ka.d;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Subscriber {
    private final String HASH_TAG;
    /* access modifiers changed from: protected */
    public final StringCompat TAG;
    /* access modifiers changed from: protected */
    public final Blackboard mBlackboard;
    private ArrayList<SubscriberInfo> mGlobalSubscriberInfoList;
    private final InstantSubscriberHolder mInstantHolder = new InstantSubscriberHolder();
    private final ArrayList<SubscriberInfo> mSubscriberInfoList = new ArrayList<>();

    public Subscriber(Blackboard blackboard) {
        StringCompat stringCompat = new StringCompat(getClass().getSimpleName());
        this.TAG = stringCompat;
        this.HASH_TAG = stringCompat + Log.TAG_SEPARATOR + Integer.toHexString(hashCode());
        this.mBlackboard = blackboard;
    }

    private SubscriberInfo findSubscriber(ArrayList<SubscriberInfo> arrayList, String str) {
        if (arrayList == null) {
            return null;
        }
        Iterator<SubscriberInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            SubscriberInfo next = it.next();
            if (str.equals(next.getKey())) {
                return next;
            }
        }
        return null;
    }

    private ArrayList<SubscriberInfo> getGlobalSubscriberList() {
        if (this.mGlobalSubscriberInfoList == null) {
            ArrayList<SubscriberInfo> arrayList = new ArrayList<>();
            this.mGlobalSubscriberInfoList = arrayList;
            createGlobalSubscriberList(arrayList);
        }
        return this.mGlobalSubscriberInfoList;
    }

    private ArrayList<SubscriberInfo> getSubscriberList() {
        if (this.mSubscriberInfoList.size() == 0) {
            createSubscriberList(this.mSubscriberInfoList);
        }
        return this.mSubscriberInfoList;
    }

    private void notifyOnSubscription(Blackboard blackboard, SubscriberInfo subscriberInfo) {
        if (subscriberInfo.isWorkingOnUI()) {
            ThreadUtil.postOnUiThread(new d(blackboard, subscriberInfo, 0));
        } else {
            ThreadUtil.postOnBgThreadNoDebug(new d(blackboard, subscriberInfo, 1));
        }
    }

    private void subscribe(Blackboard blackboard, SubscriberInfo subscriberInfo) {
        if (subscriberInfo.isWorkingOnUI()) {
            blackboard.subscribeOnUi(subscriberInfo.getKey(), subscriberInfo.getSubscriber());
        } else if (subscriberInfo.isWorkingOnCurrent()) {
            blackboard.subscribeOnCurrent(subscriberInfo.getKey(), subscriberInfo.getSubscriber());
        } else if (subscriberInfo.isHighPriority()) {
            blackboard.subscribe(subscriberInfo.getKey(), subscriberInfo.getSubscriber(), 3);
        } else {
            blackboard.subscribe(subscriberInfo.getKey(), subscriberInfo.getSubscriber());
        }
        if (subscriberInfo.isTriggerPreloadedData()) {
            if (!blackboard.isEmpty(subscriberInfo.getKey())) {
                blackboard.notify(subscriberInfo.getKey(), blackboard.read(subscriberInfo.getKey()), subscriberInfo.getSubscriber());
            }
        } else if (subscriberInfo.isTriggerPreloadedAsync() && !blackboard.isEmpty(subscriberInfo.getKey())) {
            notifyOnSubscription(blackboard, subscriberInfo);
        }
    }

    private InstantSubscriberListener toInstant(String str, InstantSubscriberListener instantSubscriberListener) {
        return this.mInstantHolder.toInstant(str, instantSubscriberListener);
    }

    public abstract void createSubscriberList(ArrayList<SubscriberInfo> arrayList);

    public final Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public final String hashTag() {
        return this.HASH_TAG;
    }

    public void onCreate() {
        subscribe();
    }

    public void onDestroy() {
        unsubscribe();
        this.mInstantHolder.clear(this.mBlackboard);
        this.mGlobalSubscriberInfoList = null;
    }

    public final void subscribeInstant(String str, InstantSubscriberListener instantSubscriberListener) {
        this.mBlackboard.subscribe(str, toInstant(str, instantSubscriberListener));
    }

    public final void subscribeInstantOnUi(String str, InstantSubscriberListener instantSubscriberListener) {
        this.mBlackboard.subscribeOnUi(str, toInstant(str, instantSubscriberListener));
    }

    public String toString() {
        return hashTag() + "{" + this.mBlackboard.hashTag() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mSubscriberInfoList.size() + "}";
    }

    public final void unsubscribe(String str) {
        SubscriberInfo findSubscriber = findSubscriber(this.mSubscriberInfoList, str);
        if (findSubscriber != null) {
            this.mSubscriberInfoList.remove(findSubscriber);
            this.mBlackboard.unsubscribe(findSubscriber.getKey(), findSubscriber.getSubscriber());
            return;
        }
        SubscriberInfo findSubscriber2 = findSubscriber(this.mGlobalSubscriberInfoList, str);
        if (findSubscriber2 != null) {
            this.mGlobalSubscriberInfoList.remove(findSubscriber2);
            Blackboard.getApplicationInstance().unsubscribe(findSubscriber2.getKey(), findSubscriber2.getSubscriber());
            StringCompat stringCompat = this.TAG;
            com.samsung.android.gallery.support.utils.Log.d(stringCompat, "unsubscribe global key=" + str);
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        com.samsung.android.gallery.support.utils.Log.e(stringCompat2, "unsubscribe failed invalid key=" + str);
    }

    private void unsubscribe() {
        Iterator<SubscriberInfo> it = getSubscriberList().iterator();
        while (it.hasNext()) {
            SubscriberInfo next = it.next();
            this.mBlackboard.unsubscribe(next.getKey(), next.getSubscriber());
        }
        this.mSubscriberInfoList.clear();
        ArrayList<SubscriberInfo> globalSubscriberList = getGlobalSubscriberList();
        if (globalSubscriberList.size() > 0) {
            Blackboard applicationInstance = Blackboard.getApplicationInstance();
            Iterator<SubscriberInfo> it2 = globalSubscriberList.iterator();
            while (it2.hasNext()) {
                SubscriberInfo next2 = it2.next();
                applicationInstance.unsubscribe(next2.getKey(), next2.getSubscriber());
            }
            globalSubscriberList.clear();
        }
    }

    private void subscribe() {
        Iterator<SubscriberInfo> it = getSubscriberList().iterator();
        while (it.hasNext()) {
            subscribe(this.mBlackboard, it.next());
        }
        Blackboard applicationInstance = Blackboard.getApplicationInstance();
        Iterator<SubscriberInfo> it2 = getGlobalSubscriberList().iterator();
        while (it2.hasNext()) {
            subscribe(applicationInstance, it2.next());
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }

    public void dump(PrintWriter printWriter) {
    }
}
