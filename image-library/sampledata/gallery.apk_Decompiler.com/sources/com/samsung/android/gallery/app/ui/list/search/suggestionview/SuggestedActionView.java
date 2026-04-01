package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import E9.a;
import H7.A;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.util.ActionSuggester;
import com.samsung.android.gallery.module.search.root.ActionResultsEntry;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedActionView extends SuggesterView {
    private final ActionResultsEntry mActionResultsEntry = new ActionResultsEntry();
    private ImageView mSuggestedActionButtonIcon;
    private TextView mSuggestedActionButtonText;

    public SuggestedActionView(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        super(iSearchSuggestionView, eventContext);
    }

    private void bindView() {
        String str;
        int i2;
        LinearLayout linearLayout = this.mSuggesterLayout;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
            Context context = this.mEventContext.getContext();
            ActionSuggester actionSuggester = ActionSuggester.get(this.mActionResultsEntry.getId());
            this.mSuggestedActionButtonText.setTag(this.mActionResultsEntry.getId());
            TextView textView = this.mSuggestedActionButtonText;
            if (context == null || actionSuggester == null) {
                str = this.mActionResultsEntry.getName();
            } else {
                str = context.getString(actionSuggester.getTitle());
            }
            textView.setText(str);
            ImageView imageView = this.mSuggestedActionButtonIcon;
            if (actionSuggester != null) {
                i2 = actionSuggester.getIcon();
            } else {
                i2 = R.drawable.gallery_ic_search_keyword;
            }
            imageView.setImageResource(i2);
        }
        Consumer<Boolean> consumer = this.mOnBindCompleted;
        if (consumer != null) {
            consumer.accept(Boolean.TRUE);
        }
    }

    private void inflate() {
        if (this.mSuggesterLayout == null) {
            if (SuggesterView.USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM) {
                this.mSuggesterLayout = (LinearLayout) LayoutInflater.from(this.mEventContext.getContext()).inflate(R.layout.search_pictures_no_item_suggestion_action_button_layout, (ViewGroup) null, false);
            } else {
                this.mSuggesterLayout = (LinearLayout) this.mSuggestionView.getEmptyView().findViewById(R.id.suggested_action_layout);
            }
            this.mSuggestedActionButtonText = (TextView) this.mSuggesterLayout.findViewById(R.id.suggested_action_button_text);
            this.mSuggestedActionButtonIcon = (ImageView) this.mSuggesterLayout.findViewById(R.id.suggested_action_button_icon);
            this.mSuggesterLayout.findViewById(R.id.suggested_action_button_layout).setOnClickListener(new A(5, this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflate$0(View view) {
        onSuggestedActionClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSuggestedActionClicked$1(ActionSuggester actionSuggester) {
        actionSuggester.run(this.mEventContext);
    }

    private void onSuggestedActionClicked() {
        String obj = this.mSuggestedActionButtonText.getTag().toString();
        if (TextUtils.equals(this.mActionResultsEntry.getId(), obj)) {
            Optional.ofNullable(ActionSuggester.get(obj)).ifPresent(new a(28, this));
        }
    }

    public void bind() {
        if (this.mEventContext.getContext() != null && !TextUtils.isEmpty(this.mActionResultsEntry.getId())) {
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

    public void setData(Object obj) {
        this.mActionResultsEntry.parse((String) obj);
    }
}
