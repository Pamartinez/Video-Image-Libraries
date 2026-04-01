package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import android.view.View;
import android.widget.LinearLayout;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SuggesterView {
    protected static final boolean USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM = PreferenceFeatures.OneUi8x.IS_ONE_UI_80;
    protected final String TAG = getClass().getSimpleName();
    int mBackgroundColor = -1;
    final EventContext mEventContext;
    protected boolean mIsEnabled = true;
    protected Consumer<Boolean> mOnBindCompleted;
    LinearLayout mSuggesterLayout;
    final ISearchSuggestionView mSuggestionView;

    public SuggesterView(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        this.mSuggestionView = iSearchSuggestionView;
        this.mEventContext = eventContext;
    }

    public static SuggesterView create(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        if (iSearchSuggestionView.getSuggesterType() == SuggesterType.HIERARCHICAL_SUGGESTION) {
            return createHierarchicalView(iSearchSuggestionView, eventContext);
        }
        if (iSearchSuggestionView.getSuggesterType() == SuggesterType.RELATIONSHIP_SUGGESTION) {
            return new RelationshipView(iSearchSuggestionView, eventContext);
        }
        if (iSearchSuggestionView.getSuggesterType() == SuggesterType.SUGGESTED_ACTION) {
            if (PickerUtil.isNormalLaunchMode(eventContext.getBlackboard())) {
                return new SuggestedActionView(iSearchSuggestionView, eventContext);
            }
            return null;
        } else if (iSearchSuggestionView.getSuggesterType() == SuggesterType.TAG_ME_SUGGESTION) {
            return new TagMeView(iSearchSuggestionView, eventContext);
        } else {
            if (iSearchSuggestionView.getSuggesterType() == SuggesterType.SELECT_ME_SUGGESTION) {
                return new SelectMeView(iSearchSuggestionView, eventContext);
            }
            if (iSearchSuggestionView.getSuggesterType() == SuggesterType.FACE_CLUSTER_MERGE_SUGGESTION) {
                return new FaceClusterMergeView(iSearchSuggestionView, eventContext);
            }
            if (iSearchSuggestionView.getSuggesterType() == SuggesterType.SCS_SETTING_ACTION || iSearchSuggestionView.getSuggesterType() == SuggesterType.LANGUAGE_PACK_DOWNLOAD_ACTION) {
                return new SettingActionView(iSearchSuggestionView, eventContext);
            }
            if (iSearchSuggestionView.getSuggesterType() == SuggesterType.FUZZY_KEYWORD) {
                return new FuzzyKeywordSuggestionView(iSearchSuggestionView, eventContext);
            }
            if (iSearchSuggestionView.getSuggesterType() == SuggesterType.ANALYSIS_TIP_CARD) {
                return new SearchAnalysisTipOnNoItem(iSearchSuggestionView, eventContext);
            }
            return null;
        }
    }

    private static SuggesterView createHierarchicalView(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        if (Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION_V2)) {
            return new HierarchicalViewV2(iSearchSuggestionView, eventContext);
        }
        return new HierarchicalView(iSearchSuggestionView, eventContext);
    }

    public abstract void bind();

    public View getView() {
        return this.mSuggesterLayout;
    }

    public void hide() {
        ViewUtils.setVisibility(this.mSuggesterLayout, 8);
    }

    public boolean needUpdatePadding() {
        if (!ViewUtils.isVisible(this.mSuggesterLayout) || this.mSuggestionView.getNoItemView() == null) {
            return false;
        }
        return true;
    }

    public void setBackgroundResource(int i2) {
        this.mBackgroundColor = i2;
    }

    public abstract void setData(Object obj);

    public void setEnable(boolean z) {
        this.mIsEnabled = z;
        ViewUtils.setAllViewEnabled(this.mSuggesterLayout, z, true);
    }

    public void setOnBindCompleteListener(Consumer<Boolean> consumer) {
        this.mOnBindCompleted = consumer;
    }

    public void show() {
        ViewUtils.setVisibility(this.mSuggesterLayout, 0);
    }

    public void updateEmptyViewPadding() {
        if (needUpdatePadding()) {
            this.mSuggestionView.getNoItemView().setPadding(0, this.mSuggesterLayout.getMeasuredHeight(), 0, 0);
        }
    }

    public void onConfigurationChanged() {
    }

    public void refresh() {
    }
}
