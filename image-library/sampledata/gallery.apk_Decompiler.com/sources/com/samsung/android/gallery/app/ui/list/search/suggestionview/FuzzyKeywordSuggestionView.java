package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import H7.A;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.SearchSelectedFilter;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FuzzyKeywordSuggestionView extends SuggesterView {
    private String mFuzzyKeyword;
    private TextView mSuggestedActionButtonText;

    public FuzzyKeywordSuggestionView(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        super(iSearchSuggestionView, eventContext);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bind$0(View view) {
        onFuzzyKeywordClicked();
    }

    private void onFuzzyKeywordClicked() {
        String locationKey = this.mEventContext.getLocationKey();
        UriBuilder removeArg = new UriBuilder(locationKey).removeArg("from_instant_search");
        boolean argValue = ArgumentsUtil.getArgValue(locationKey, "from_instant_search", false);
        String argValue2 = ArgumentsUtil.getArgValue(locationKey, "SelectedFilter", (String) null);
        if (TextUtils.isEmpty(argValue2)) {
            removeArg.appendArg("sub", this.mFuzzyKeyword).appendArg("title", this.mFuzzyKeyword).build();
        } else {
            SearchSelectedFilter searchSelectedFilter = new SearchSelectedFilter(argValue2);
            if (!TextUtils.isEmpty(searchSelectedFilter.getLastKeyword())) {
                searchSelectedFilter.replaceLastKeyword(this.mFuzzyKeyword);
                removeArg.appendArg("SelectedFilter", searchSelectedFilter.getContent()).build();
                FilterResultsEntity filterResultsEntity = new FilterResultsEntity(this.mFuzzyKeyword, "key_word");
                filterResultsEntity.addRawLabel(this.mFuzzyKeyword);
                if (argValue) {
                    this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(8016, filterResultsEntity));
                    return;
                }
                this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(8527, filterResultsEntity));
            } else {
                removeArg.appendArg("sub", this.mFuzzyKeyword).build();
            }
        }
        this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(8506, Boolean.FALSE));
        this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(1066, removeArg.build()));
    }

    public void bind() {
        if (this.mEventContext.getContext() != null) {
            if (this.mSuggesterLayout == null) {
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mEventContext.getContext()).inflate(R.layout.search_pictures_no_item_suggestion_setting_button_layout, (ViewGroup) null, false);
                this.mSuggesterLayout = linearLayout;
                this.mSuggestedActionButtonText = (TextView) linearLayout.findViewById(R.id.suggested_action_button_text);
                this.mSuggesterLayout.setOnClickListener(new A(2, this));
            }
            this.mSuggestedActionButtonText.setText(this.mEventContext.getContext().getString(R.string.search_for_ps, new Object[]{this.mFuzzyKeyword}));
            this.mSuggesterLayout.setVisibility(0);
            Consumer<Boolean> consumer = this.mOnBindCompleted;
            if (consumer != null) {
                consumer.accept(Boolean.TRUE);
            }
        }
    }

    public boolean needUpdatePadding() {
        if (SuggesterView.USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM || !super.needUpdatePadding()) {
            return false;
        }
        return true;
    }

    public void setData(Object obj) {
        this.mFuzzyKeyword = (String) obj;
    }
}
