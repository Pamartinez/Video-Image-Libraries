package com.samsung.android.gallery.app.ui.list.search.autoComplete;

import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.ISearchAutoCompleteViewV2;
import com.samsung.android.gallery.module.search.abstraction.AutoCompleteUrlCompatible;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteUriBuilder;
import com.samsung.android.gallery.module.search.filter.SearchFilterUtil;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import i5.C0470a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAutoCompletePresenterV2<V extends ISearchAutoCompleteViewV2> extends MvpBasePresenter<V> {
    private String mKeyword;

    public SearchAutoCompletePresenterV2(Blackboard blackboard, V v) {
        super(blackboard, v);
        setKeywordFromLocationKey();
    }

    private void autoCompleteDataRequest() {
        this.mBlackboard.post(CommandKey.DATA_REQUEST(ArgumentsUtil.appendArgs("location://search/AutoComplete", "keyword", this.mKeyword)), (Object) null);
    }

    /* access modifiers changed from: private */
    public void onAutoCompletePublished(Object obj, Bundle bundle) {
        String str;
        if (((ISearchAutoCompleteViewV2) this.mView).isDestroyed() || ((ISearchAutoCompleteViewV2) this.mView).isViewHidden()) {
            StringCompat stringCompat = this.TAG;
            if (((ISearchAutoCompleteViewV2) this.mView).isDestroyed()) {
                str = "destroyed";
            } else if (((ISearchAutoCompleteViewV2) this.mView).isViewHidden()) {
                str = "hidden";
            } else {
                str = "";
            }
            Log.sw(stringCompat, "onAutoCompletePublished skip. ".concat(str));
            return;
        }
        ((ISearchAutoCompleteViewV2) this.mView).onAutoCompletePublished((ArrayList) obj);
    }

    /* access modifiers changed from: private */
    public void onKeywordChanged(Object obj, Bundle bundle) {
        String str = (String) obj;
        this.mKeyword = str;
        if (!TextUtils.isEmpty(str)) {
            autoCompleteDataRequest();
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://event/KeywordChanged", new C0470a(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://user/AutoComplete", new C0470a(this, 1)).setWorkingOnUI());
    }

    public String getKeyword() {
        return this.mKeyword;
    }

    public void onAutoCompleteItemClick(AutoCompleteItem autoCompleteItem) {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_KEYWORD_ITEM);
        String selectedFilter = SearchFilterUtil.getSelectedFilter(this.mBlackboard);
        Blackboard blackboard = this.mBlackboard;
        blackboard.post("command://MoveURL", AutoCompleteUriBuilder.build(blackboard, (AutoCompleteUrlCompatible) autoCompleteItem, selectedFilter, (String) null));
    }

    public void resetKeyword() {
        this.mKeyword = null;
    }

    public void setKeywordFromLocationKey() {
        this.mKeyword = ArgumentsUtil.getArgValue(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), "keyword");
    }
}
