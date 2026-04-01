package com.samsung.android.gallery.support.blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SubscriberInfo {
    private boolean mHighPriority;
    private String mKey;
    private SubscriberListener mSubscriberListener;
    private boolean mTriggerPreloadedAsync;
    private boolean mTriggerPreloadedData = false;
    private boolean mWorkingOnCurrentThread;
    private boolean mWorkingOnUiThread = false;

    public SubscriberInfo(String str, SubscriberListener subscriberListener) {
        this.mKey = str;
        this.mSubscriberListener = subscriberListener;
    }

    public String getKey() {
        return this.mKey;
    }

    public SubscriberListener getSubscriber() {
        return this.mSubscriberListener;
    }

    public boolean isHighPriority() {
        return this.mHighPriority;
    }

    public boolean isTriggerPreloadedAsync() {
        return this.mTriggerPreloadedAsync;
    }

    public boolean isTriggerPreloadedData() {
        return this.mTriggerPreloadedData;
    }

    public boolean isWorkingOnCurrent() {
        return this.mWorkingOnCurrentThread;
    }

    public boolean isWorkingOnUI() {
        return this.mWorkingOnUiThread;
    }

    public SubscriberInfo setHighPriority() {
        this.mHighPriority = true;
        return this;
    }

    public SubscriberInfo setTriggerPreloadedAsync() {
        if (!this.mKey.endsWith("/#")) {
            this.mTriggerPreloadedAsync = true;
            return this;
        }
        throw new AssertionError("not support with wildcard");
    }

    public SubscriberInfo setTriggerPreloadedData() {
        if (!this.mKey.endsWith("/#")) {
            this.mTriggerPreloadedData = true;
            return this;
        }
        throw new AssertionError("not support with wildcard");
    }

    public SubscriberInfo setWorkingCurrent() {
        this.mWorkingOnUiThread = false;
        this.mWorkingOnCurrentThread = true;
        return this;
    }

    public SubscriberInfo setWorkingOnUI() {
        this.mWorkingOnUiThread = true;
        this.mWorkingOnCurrentThread = false;
        return this;
    }
}
