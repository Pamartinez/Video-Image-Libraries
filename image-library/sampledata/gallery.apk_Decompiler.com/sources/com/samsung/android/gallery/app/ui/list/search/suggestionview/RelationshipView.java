package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import A4.H;
import A9.b;
import H3.l;
import I5.e;
import I5.f;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.people.RelationshipPickerCmd;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.search.abstraction.FragmentVolatileStatus;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.RoundedRelativeLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelationshipView extends SuggesterView {
    private TextView mCancel;
    protected TextView mRelationSuggestionButton;
    protected TextView mRelationSuggestionDescription;
    protected TextView mRelationSuggestionHeader;
    protected String mRelationship;
    private RoundedRelativeLayout mTipCardLayout;
    protected String noRelationshipKeyword;

    public RelationshipView(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        super(iSearchSuggestionView, eventContext);
    }

    private void bindView(String str, String str2) {
        if (this.mSuggesterLayout != null) {
            Context context = this.mEventContext.getContext();
            this.mRelationSuggestionButton.setTag(str);
            if (!SuggesterView.USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM || !PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                initGuideTextView(context, str2);
                ViewUtils.setVisibleOrGone(this.mCancel, false);
                this.mTipCardLayout.setBackgroundColor(context.getColor(R.color.search_tip_card_background_color));
                LinearLayout linearLayout = this.mSuggesterLayout;
                int i2 = this.mBackgroundColor;
                if (i2 == -1) {
                    i2 = R.color.default_fw_background;
                }
                linearLayout.setBackgroundResource(i2);
            } else {
                this.mRelationSuggestionButton.setText(context.getString(R.string.select_ps, new Object[]{RelationshipKeySet.getRelationshipName(context, str2)}));
            }
            this.mSuggesterLayout.setVisibility(0);
            ViewUtils.postDelayed(this.mSuggestionView.getEmptyView(), new l(10, this), 50);
        }
    }

    private void inflate() {
        if (this.mSuggesterLayout != null) {
            return;
        }
        if (!SuggesterView.USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM || !PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            this.mSuggesterLayout = (LinearLayout) this.mSuggestionView.getEmptyView().findViewById(R.id.relation_suggestion_layout);
            this.mRelationSuggestionHeader = (TextView) this.mSuggestionView.getEmptyView().findViewById(R.id.headerTitle);
            this.mRelationSuggestionDescription = (TextView) this.mSuggestionView.getEmptyView().findViewById(R.id.headerContent);
            TextView textView = (TextView) this.mSuggestionView.getEmptyView().findViewById(R.id.doneView);
            this.mRelationSuggestionButton = textView;
            textView.setOnClickListener(new f(this, 1));
            this.mTipCardLayout = (RoundedRelativeLayout) this.mSuggestionView.getEmptyView().findViewById(R.id.tip_card_layout);
            this.mCancel = (TextView) this.mSuggestionView.getEmptyView().findViewById(R.id.cancelView);
            return;
        }
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mEventContext.getContext()).inflate(R.layout.search_pictures_no_item_suggestion_setting_button_layout, (ViewGroup) null, false);
        this.mSuggesterLayout = linearLayout;
        this.mRelationSuggestionButton = (TextView) linearLayout.findViewById(R.id.suggested_action_button_text);
        this.mSuggesterLayout.setOnClickListener(new f(this, 0));
    }

    private void initGuideTextView(Context context, String str) {
        String relationshipName = RelationshipKeySet.getRelationshipName(context, str);
        String string = context.getString(R.string.relationship_suggestion_header, new Object[]{relationshipName});
        String string2 = context.getString(R.string.relationship_suggestion_description, new Object[]{relationshipName});
        String string3 = context.getString(R.string.relationship_suggestion_btn);
        this.mRelationSuggestionHeader.setText(string);
        this.mRelationSuggestionDescription.setText(string2);
        this.mRelationSuggestionButton.setText(string3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$bind$0(String str, ThreadPool.JobContext jobContext) {
        return Boolean.valueOf(this.mSuggestionView.isRelationshipEnabled(str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bind$1(Future future, String str) {
        if (((Boolean) future.get()).booleanValue()) {
            initRelationshipSuggestion(this.mRelationship, str);
            Consumer<Boolean> consumer = this.mOnBindCompleted;
            if (consumer != null) {
                consumer.accept(Boolean.TRUE);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bind$2(String str, Future future) {
        ThreadUtil.postOnUiThread(new b(this, future, str, 27));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflate$3(View view) {
        onRelationSuggestionClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflate$4(View view) {
        onRelationSuggestionClicked();
    }

    private void onRelationSuggestionClicked() {
        if (this.mSuggestionView.getSuggesterType() == SuggesterType.RELATIONSHIP_SUGGESTION) {
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(8031));
                return;
            }
            FragmentVolatileStatus.setVolatile();
            new RelationshipPickerCmd().execute(this.mEventContext, this.mRelationSuggestionButton.getTag().toString());
        }
    }

    public void bind() {
        if (!TextUtils.isEmpty(this.mRelationship)) {
            String parseRelationshipType = RelationshipKeySet.parseRelationshipType(this.mRelationship);
            ThreadPool.getInstance().submit(new e(0, this, parseRelationshipType), new H(15, (Object) this, (Object) parseRelationshipType));
        }
    }

    public void initRelationshipSuggestion(String str, String str2) {
        if (this.mEventContext.getContext() != null) {
            inflate();
            bindView(str, str2);
        }
    }

    public boolean needUpdatePadding() {
        if (SuggesterView.USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM || !super.needUpdatePadding()) {
            return false;
        }
        return true;
    }

    public void setData(Object obj) {
        Object[] objArr = (Object[]) obj;
        this.mRelationship = (String) objArr[0];
        if (objArr.length > 1) {
            this.noRelationshipKeyword = (String) objArr[1];
        }
    }
}
