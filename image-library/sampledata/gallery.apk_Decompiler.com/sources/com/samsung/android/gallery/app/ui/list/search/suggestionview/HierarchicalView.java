package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import H3.l;
import H7.A;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HierarchicalView extends SuggesterView {
    private final String[] mHierarchicalInfo = new String[2];
    private TextView mHierarchicalSuggestionButtonText;

    public HierarchicalView(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        super(iSearchSuggestionView, eventContext);
    }

    private void bindView() {
        if (this.mSuggesterLayout != null) {
            String[] strArr = this.mHierarchicalInfo;
            String str = strArr[0];
            String str2 = strArr[1];
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                this.mSuggesterLayout.setVisibility(8);
                return;
            }
            if (!Features.isEnabled(Features.IS_KNOX_MODE) && this.mSuggestionView.getNoItemView() != null) {
                this.mSuggestionView.getNoItemView().setDescription(this.mEventContext.getContext().getResources().getString(R.string.empty_set_description_search_no_item_vi));
            }
            this.mSuggesterLayout.setVisibility(0);
            this.mHierarchicalSuggestionButtonText.setTag(str);
            this.mHierarchicalSuggestionButtonText.setText(str2);
            ViewUtils.postDelayed(this.mSuggestionView.getEmptyView(), new l(8, this), 50);
        }
    }

    private void inflate() {
        if (this.mSuggesterLayout == null) {
            if (SuggesterView.USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM) {
                this.mSuggesterLayout = (LinearLayout) LayoutInflater.from(this.mEventContext.getContext()).inflate(R.layout.hierarchical_suggestion_layout, (ViewGroup) null, false);
            } else {
                this.mSuggesterLayout = (LinearLayout) this.mSuggestionView.getEmptyView().findViewById(R.id.hierarchical_suggestion_layout);
            }
            this.mHierarchicalSuggestionButtonText = (TextView) this.mSuggesterLayout.findViewById(R.id.hierarchical_suggestion_button_text);
            this.mSuggesterLayout.findViewById(R.id.hierarchical_suggestion_button_layout).setOnClickListener(new A(3, this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflate$0(View view) {
        onHierarchicalSuggestionClicked();
    }

    public void bind() {
        if (this.mEventContext.getContext() != null) {
            inflate();
            bindView();
        }
    }

    public boolean needUpdatePadding() {
        if (SuggesterView.USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM || !super.needUpdatePadding()) {
            return false;
        }
        return true;
    }

    public void onHierarchicalSuggestionClicked() {
        if (this.mEventContext.getBlackboard() != null) {
            String obj = this.mHierarchicalSuggestionButtonText.getTag().toString();
            this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(1066, new UriBuilder(BlackboardUtils.readLocationKeyCurrent(this.mEventContext.getBlackboard())).appendArg("sub", obj).appendArg("title", this.mHierarchicalSuggestionButtonText.getText().toString()).appendArg("term", "scenetag").appendArg("collect_keyword", obj).appendArg("collect_type", SearchWordCollector.Type.HIERARCHICAL_SUGGESTION.toString()).build()));
        }
    }

    public void setData(Object obj) {
        Object[] objArr = (Object[]) obj;
        String[] strArr = this.mHierarchicalInfo;
        strArr[0] = (String) objArr[0];
        strArr[1] = (String) objArr[1];
    }
}
