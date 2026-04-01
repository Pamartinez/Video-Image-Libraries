package com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction;

import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Delegate {
    /* access modifiers changed from: protected */
    public final String TAG = getClass().getSimpleName();
    private final HashMap<DataRequest, DataProvider> mDataResolver = new HashMap<>();
    /* access modifiers changed from: protected */
    public EventHandler mEventHandler;
    private final HashMap<Event, Consumer<Object[]>> mEventListener = new HashMap<>();
    private boolean mIsResume;
    /* access modifiers changed from: protected */
    public final IStoryHighlightView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DataProvider {
        Object get(DataRequest dataRequest, Object... objArr);
    }

    public Delegate(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
        this.mEventHandler = iStoryHighlightView.getEventHandler();
        addListenEvent();
        addRequestProvider();
    }

    public final void addEvent(Event event) {
        addEvent(event, (Consumer<Object[]>) null);
    }

    public void addRequestProvider() {
    }

    public String getScreenId() {
        return this.mView.getScreenId();
    }

    public void handlePostEvent(Event event, Object... objArr) {
        Consumer consumer = this.mEventListener.get(event);
        if (consumer != null) {
            consumer.accept(objArr);
        } else {
            handleEvent(event, objArr);
        }
    }

    public Object handleRequestData(DataRequest dataRequest, Object... objArr) {
        DataProvider dataProvider = this.mDataResolver.get(dataRequest);
        if (dataProvider != null) {
            return dataProvider.get(dataRequest, objArr);
        }
        return null;
    }

    public final boolean hasRequestProvider(DataRequest dataRequest) {
        return this.mDataResolver.containsKey(dataRequest);
    }

    public final boolean isListening(Event event) {
        return this.mEventListener.containsKey(event);
    }

    public boolean isResumed() {
        return this.mIsResume;
    }

    public boolean onBackPressed() {
        return false;
    }

    public boolean onKeyEvent(int i2, KeyEvent keyEvent) {
        return false;
    }

    public void onPause() {
        this.mIsResume = false;
    }

    public void onResume() {
        this.mIsResume = true;
    }

    public void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        postAnalyticsLog(getScreenId(), analyticsEventId, (String) null);
    }

    public <T> T requestData(DataRequest dataRequest, T t) {
        return this.mEventHandler.requestData(dataRequest, t);
    }

    public final void addEvent(Event event, Consumer<Object[]> consumer) {
        this.mEventListener.put(event, consumer);
    }

    public final void addRequestProvider(DataRequest dataRequest, DataProvider dataProvider) {
        this.mDataResolver.put(dataRequest, dataProvider);
    }

    public void postAnalyticsLog(AnalyticsEventId analyticsEventId, AnalyticsDetail analyticsDetail) {
        postAnalyticsLog(getScreenId(), analyticsEventId, analyticsDetail != null ? analyticsDetail.toString() : null);
    }

    public void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId, String str2) {
        if (analyticsEventId != null && str2 != null) {
            this.mView.postAnalyticsLog(str, analyticsEventId, str2);
        } else if (analyticsEventId != null) {
            this.mView.postAnalyticsLog(str, analyticsEventId);
        } else {
            Log.w("Delegate", "No event and detail");
        }
    }

    public void addListenEvent() {
    }

    public void onAttach() {
    }

    public void onDataChangedOnUi() {
    }

    public void onDestroy() {
    }

    public void onDestroyView() {
    }

    public void onHeaderUpdated() {
    }

    public void setScreenMode() {
    }

    public void handleDensityChange(int i2) {
    }

    public void handleOrientationChange(int i2) {
    }

    public void handleResolutionChange(int i2) {
    }

    public void initView(View view) {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    public void onTrimMemory(int i2) {
    }

    public void handleEvent(Event event, Object... objArr) {
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
    }
}
