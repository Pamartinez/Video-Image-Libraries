package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import H3.l;
import I5.g;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TagMeView extends SuggesterView {
    public TagMeView(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        super(iSearchSuggestionView, eventContext);
    }

    private void bindCancelButton() {
        TextView textView = (TextView) this.mSuggesterLayout.findViewById(R.id.cancelView);
        textView.setTextSize(0, this.mSuggesterLayout.getResources().getDimension(R.dimen.tip_card_button_text_size_on_people));
        textView.setText(R.string.not_now_20);
        textView.setOnClickListener(new g(this, 0));
    }

    private void bindDoneButton() {
        TextView textView = (TextView) this.mSuggesterLayout.findViewById(R.id.doneView);
        textView.setTextSize(0, this.mSuggesterLayout.getResources().getDimension(R.dimen.tip_card_button_text_size_on_people));
        textView.setText(R.string.review_and_select);
        textView.setOnClickListener(new g(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindCancelButton$1(View view) {
        onNegativeButtonClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindDoneButton$0(View view) {
        onPositiveButtonClicked();
    }

    private void onNegativeButtonClicked() {
        SimpleAnimator.setVisibilityAni(this.mSuggesterLayout, 8);
    }

    public void bind() {
        if (this.mEventContext.getContext() != null && this.mSuggesterLayout == null) {
            LinearLayout linearLayout = (LinearLayout) this.mSuggestionView.getEmptyView().findViewById(R.id.relation_suggestion_layout);
            this.mSuggesterLayout = linearLayout;
            ViewUtils.setVisibleOrGone(linearLayout.findViewById(R.id.headerTitle), false);
            ((TextView) this.mSuggesterLayout.findViewById(R.id.headerContent)).setText(getContentStringResID());
            ViewUtils.setBackgroundResource(this.mSuggesterLayout.findViewById(R.id.tip_card_layout), R.color.search_tag_me_tip_card_background);
            LinearLayout linearLayout2 = this.mSuggesterLayout;
            int i2 = this.mBackgroundColor;
            if (i2 == -1) {
                i2 = R.color.default_fw_background;
            }
            ViewUtils.setBackgroundResource(linearLayout2, i2);
            ViewUtils.setVisibleOrGone(this.mSuggesterLayout, true);
            ViewUtils.postDelayed(this.mSuggestionView.getEmptyView(), new l(11, this), 50);
            bindDoneButton();
            bindCancelButton();
        }
    }

    public int getContentStringResID() {
        return R.string.find_photos_of_yourself;
    }

    public void onPositiveButtonClicked() {
        this.mEventContext.getBlackboard().post("command://MoveURL", "location://search/fileList/SelectMeAll");
    }

    public void setData(Object obj) {
    }
}
