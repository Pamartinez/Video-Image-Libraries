package com.samsung.android.gallery.app.ui.viewer2.contentviewer;

import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.ViewerObjectThread;
import java.util.concurrent.CopyOnWriteArrayList;
import o4.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewerObject implements IViewerObject, ViewerLifeCycle {
    /* access modifiers changed from: protected */
    public final StringCompat TAG;
    /* access modifiers changed from: protected */
    public final ActionInvoker mActionInvoker;
    /* access modifiers changed from: protected */
    public ContentModel mModel;
    private final CopyOnWriteArrayList<SubscriberListenerInfo> mSubscriberList = new CopyOnWriteArrayList<>();
    protected final ViewerObjectThread mThread = new ViewerObjectThread();

    public ViewerObject() {
        StringCompat stringCompat = new StringCompat(getClass().getSimpleName());
        this.TAG = stringCompat;
        this.mActionInvoker = new ActionInvoker(stringCompat.toString());
    }

    private void clearSubscribe() {
        this.mSubscriberList.forEach(new a(17, this));
        this.mSubscriberList.clear();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clearSubscribe$0(SubscriberListenerInfo subscriberListenerInfo) {
        getBlackboard().unsubscribe(subscriberListenerInfo.mKey, subscriberListenerInfo.mSubscriberListener);
    }

    public final void attachActionInvoker(ActionInvoker actionInvoker) {
        actionInvoker.attach(this.mActionInvoker);
    }

    public final void bindContentModel(ContentModel contentModel) {
        this.mModel = contentModel;
    }

    public Blackboard getBlackboard() {
        Blackboard blackboard = this.mModel.getBlackboard();
        if (blackboard != null) {
            return blackboard;
        }
        throw new IllegalStateException("blackboard is null");
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        StringCompat stringCompat = this.TAG;
        stringCompat.setTag(this.mModel.getPosition() + ">" + i2);
    }

    public void onBind(MediaItem mediaItem, int i2) {
        this.TAG.setTag(Integer.valueOf(i2));
    }

    public void onViewRecycled() {
        this.mThread.cancel();
        clearSubscribe();
    }

    public void postAnalyticsLog(AnalyticsScreenId analyticsScreenId, AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(analyticsScreenId.toString(), analyticsEventId.toString());
    }

    public SubscriberListenerInfo subscribe(String str, SubscriberListener subscriberListener) {
        SubscriberListenerInfo subscriberListenerInfo = new SubscriberListenerInfo(str, subscriberListener);
        getBlackboard().subscribe(subscriberListenerInfo.mKey, subscriberListenerInfo.mSubscriberListener);
        this.mSubscriberList.add(subscriberListenerInfo);
        return subscriberListenerInfo;
    }

    public void unsubscribe(SubscriberListenerInfo subscriberListenerInfo) {
        getBlackboard().unsubscribe(subscriberListenerInfo.mKey, subscriberListenerInfo.mSubscriberListener);
        this.mSubscriberList.remove(subscriberListenerInfo);
    }

    public void postAnalyticsLog(AnalyticsScreenId analyticsScreenId, AnalyticsEventId analyticsEventId, String str) {
        AnalyticsLogger.getInstance().postLog(analyticsScreenId.toString(), analyticsEventId.toString(), str);
    }
}
