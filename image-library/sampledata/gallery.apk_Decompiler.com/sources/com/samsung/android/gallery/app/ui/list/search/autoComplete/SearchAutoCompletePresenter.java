package com.samsung.android.gallery.app.ui.list.search.autoComplete;

import C3.C0391a;
import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.ISearchAutoCompleteView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAutoCompletePresenter<V extends ISearchAutoCompleteView> extends BaseListPresenter<V> {
    private String mKeyword;

    public SearchAutoCompletePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        setKeywordFromLocationKey();
    }

    /* access modifiers changed from: private */
    public void onKeywordChanged(Object obj, Bundle bundle) {
        this.mKeyword = (String) obj;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://event/KeywordChanged", new C0391a(27, this)).setWorkingOnUI());
    }

    public String getKeyword() {
        return this.mKeyword;
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        ((ISearchAutoCompleteView) this.mView).setProgressBarVisibility(false);
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_KEYWORD_ITEM);
        String title = mediaItem.getTitle();
        this.mBlackboard.post("command://MoveURL", new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/Keyword", title)).appendArg("sub", title).appendArg("title", title).appendArg("collect_keyword", title).appendArg("collect_type", SearchWordCollector.Type.KEYWORD_AUTOCOMPLETE.toString()).build());
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        ((ISearchAutoCompleteView) this.mView).setProgressBarVisibility(true);
    }

    public void resetKeyword() {
        this.mKeyword = null;
    }

    public void setKeywordFromLocationKey() {
        this.mKeyword = ArgumentsUtil.getArgValue(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), "keyword");
    }
}
