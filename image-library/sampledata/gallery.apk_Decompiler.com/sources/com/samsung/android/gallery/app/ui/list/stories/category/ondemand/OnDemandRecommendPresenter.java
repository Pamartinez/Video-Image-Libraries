package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import A5.a;
import U5.c;
import V3.b;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.module.story.ondemand.OnDemandSuggestionDataManager;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandRecommendPresenter {
    private final OnDemandFloatingView mFloatingView;
    private final Runnable mSuggestionDataChanged = new b(24, this);
    private final IMvpBaseView mView;

    public OnDemandRecommendPresenter(IMvpBaseView iMvpBaseView, OnDemandFloatingView onDemandFloatingView) {
        this.mView = iMvpBaseView;
        this.mFloatingView = onDemandFloatingView;
        initOnDemandRecommendData();
    }

    private OnDemandFloatingItem createItem(String str, int i2) {
        return new OnDemandFloatingItem(str, i2);
    }

    private void initOnDemandRecommendData() {
        OnDemandSuggestionDataManager.getInstance().init();
        OnDemandSuggestionDataManager.getInstance().registerDataChangedListener(this.mSuggestionDataChanged);
        onRecommendDataChanged(loadData(OnDemandSuggestionDataManager.getInstance().getData()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ OnDemandFloatingItem lambda$loadData$1(String str) {
        return createItem(str, 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRecommendDataChanged$0(List list) {
        this.mFloatingView.setData(list);
    }

    private List<OnDemandFloatingItem> loadData(List<String> list) {
        return (List) list.stream().map(new a(29, this)).collect(Collectors.toList());
    }

    private void onRecommendDataChanged(List<OnDemandFloatingItem> list) {
        Log.s("OnDemandRecommendPresenter", "onRecommendDataChanged");
        if (this.mFloatingView.getContext() == null) {
            Log.sw("OnDemandRecommendPresenter", "Context is null");
        } else {
            ThreadUtil.runOnUiThread(new c(24, this, list));
        }
    }

    /* access modifiers changed from: private */
    public void onSuggestionChanged() {
        onRecommendDataChanged(loadData(OnDemandSuggestionDataManager.getInstance().getData()));
    }

    public void onDestroy() {
        OnDemandSuggestionDataManager.getInstance().unregisterDataChangedListener(this.mSuggestionDataChanged);
    }

    public void onRecommendItemClick(OnDemandFloatingItem onDemandFloatingItem) {
        this.mFloatingView.getBlackboard().postEvent(EventMessage.obtain(1140, new Object[]{onDemandFloatingItem.getQuery(), Integer.valueOf(onDemandFloatingItem.getType())}));
        this.mView.postAnalyticsLog(this.mFloatingView.getScreenId(), AnalyticsEventId.EVENT_ONDEMAND_STORY_RECOMMENDED_CLICKED);
    }
}
