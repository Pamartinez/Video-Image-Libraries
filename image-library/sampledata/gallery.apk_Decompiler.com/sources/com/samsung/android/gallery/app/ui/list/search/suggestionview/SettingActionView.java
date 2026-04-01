package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import H7.A;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.LanguagePackDownloadCmd;
import com.samsung.android.gallery.app.controller.externals.LaunchScsSettingCmd;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SettingActionView extends SuggesterView {
    private Object mData;
    private TextView mSuggestedActionButtonText;

    public SettingActionView(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        super(iSearchSuggestionView, eventContext);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bind$0(View view) {
        onSettingActionClicked();
    }

    private void onSettingActionClicked() {
        if (this.mSuggestionView.getSuggesterType() == SuggesterType.SCS_SETTING_ACTION) {
            new LaunchScsSettingCmd().execute(this.mEventContext, (String) this.mData);
        } else if (this.mSuggestionView.getSuggesterType() == SuggesterType.LANGUAGE_PACK_DOWNLOAD_ACTION) {
            new LanguagePackDownloadCmd().execute(this.mEventContext, new Object[0]);
        }
    }

    public void bind() {
        if (this.mEventContext.getContext() != null) {
            if (this.mSuggesterLayout == null) {
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mEventContext.getContext()).inflate(R.layout.search_pictures_no_item_suggestion_setting_button_layout, (ViewGroup) null, false);
                this.mSuggesterLayout = linearLayout;
                this.mSuggestedActionButtonText = (TextView) linearLayout.findViewById(R.id.suggested_action_button_text);
                this.mSuggesterLayout.findViewById(R.id.suggested_action_button_layout).setOnClickListener(new A(4, this));
            }
            this.mSuggesterLayout.setVisibility(0);
            if (this.mSuggestionView.getSuggesterType() == SuggesterType.LANGUAGE_PACK_DOWNLOAD_ACTION) {
                this.mSuggestedActionButtonText.setText(R.string.get_language_pack);
            }
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
        this.mData = obj;
    }
}
