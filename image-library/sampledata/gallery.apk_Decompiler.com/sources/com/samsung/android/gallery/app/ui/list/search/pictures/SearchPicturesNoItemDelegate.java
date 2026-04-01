package com.samsung.android.gallery.app.ui.list.search.pictures;

import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.ISearchSuggestionView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterType;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterView;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.search.languagepack.NeuralTranslator;
import com.samsung.android.gallery.support.config.Component$SamsungSearch;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.NoItemCustomView;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import r6.e;
import v5.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesNoItemDelegate {
    private static final boolean USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM = PreferenceFeatures.OneUi8x.IS_ONE_UI_80;
    private NoItemCustomView mNoItemView;
    private SuggesterView mSuggesterView;
    private final ISearchSuggestionView mSuggestionView;
    private final ISearchPicturesView mView;

    public SearchPicturesNoItemDelegate(ISearchPicturesView iSearchPicturesView, ISearchSuggestionView iSearchSuggestionView) {
        this.mView = iSearchPicturesView;
        this.mSuggestionView = iSearchSuggestionView;
    }

    private void bindSuggestionView() {
        if (this.mSuggestionView.getSuggesterData() != null && !this.mView.handleRelationshipRecommend()) {
            SuggesterView create = SuggesterView.create(this.mSuggestionView, this.mView.getEventContext());
            this.mSuggesterView = create;
            Optional.ofNullable(create).ifPresent(new c(this, 0));
        }
    }

    private int getNoItemCustomViewTopMargin() {
        SuggesterType suggesterType = this.mSuggestionView.getSuggesterType();
        if (suggesterType == SuggesterType.HIERARCHICAL_SUGGESTION || suggesterType == SuggesterType.SUGGESTED_ACTION || suggesterType == SuggesterType.SCS_SETTING_ACTION || suggesterType == SuggesterType.LANGUAGE_PACK_DOWNLOAD_ACTION || suggesterType == SuggesterType.RELATIONSHIP_SUGGESTION || suggesterType == SuggesterType.FUZZY_KEYWORD) {
            return this.mView.getContext().getResources().getDimensionPixelOffset(R.dimen.noitem_custom_layout_margin_top);
        }
        return 0;
    }

    private String getNoItemDescription() {
        int i2;
        SuggesterType suggesterType = this.mSuggestionView.getSuggesterType();
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 && suggesterType == SuggesterType.RELATIONSHIP_SUGGESTION) {
            return this.mView.getContext().getString(R.string.select_your_relationship, new Object[]{getRelationshipName()});
        }
        if (suggesterType == SuggesterType.FUZZY_KEYWORD) {
            return SeApiCompat.naturalizeText(this.mView.getContext().getString(R.string.try_searching_for_ps_instead, new Object[]{this.mSuggestionView.getSuggesterData()}));
        }
        if (suggesterType == SuggesterType.SCS_SETTING_ACTION) {
            if (Component$SamsungSearch.SDK_B_MR5) {
                i2 = R.string.empty_set_description_turn_on_pdi;
            } else {
                i2 = R.string.empty_set_description_turn_on_scs;
            }
        } else if (suggesterType == SuggesterType.LANGUAGE_PACK_DOWNLOAD_ACTION) {
            i2 = R.string.explain_download_language;
        } else {
            if (USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM) {
                if (suggesterType == SuggesterType.HIERARCHICAL_SUGGESTION) {
                    i2 = R.string.try_one_of_these_related_searches;
                } else if (suggesterType == SuggesterType.SUGGESTED_ACTION) {
                    i2 = R.string.looking_for_the_trash_q;
                }
            }
            i2 = -1;
        }
        if (i2 != -1) {
            return this.mView.getContext().getString(i2);
        }
        return "";
    }

    private String getNoItemString() {
        int i2;
        String locationKey = this.mView.getLocationKey();
        if (locationKey.startsWith("location://search/fileList/Category/Edited")) {
            i2 = R.string.no_items_edited;
        } else if (locationKey.startsWith("location://search/fileList/Category/AiEdited")) {
            i2 = R.string.no_items_ai_edited;
        } else {
            i2 = R.string.ocr_no_result;
        }
        return this.mView.getContext().getResources().getString(i2);
    }

    private String getRelationshipName() {
        return RelationshipKeySet.getRelationshipName(this.mView.getContext(), RelationshipKeySet.parseRelationshipType((String) ((Object[]) this.mSuggestionView.getSuggesterData())[0]));
    }

    private String getScsDisabledReason() {
        return (String) this.mView.getBlackboard().read("data://user/ScsDisabledReason", null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindSuggestionView$0(Boolean bool) {
        setCustomView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindSuggestionView$1(SuggesterView suggesterView) {
        suggesterView.setData(this.mSuggestionView.getSuggesterData());
        suggesterView.setOnBindCompleteListener(new c(this, 1));
        suggesterView.bind();
        suggesterView.refresh();
        suggesterView.updateEmptyViewPadding();
    }

    private void setCustomView() {
        View view;
        SuggesterView suggesterView;
        NoItemCustomView noItemCustomView = this.mNoItemView;
        if (noItemCustomView != null) {
            if (!supportSuggesterViewInNoItemCustom() || (suggesterView = this.mSuggesterView) == null) {
                view = null;
            } else {
                view = suggesterView.getView();
            }
            noItemCustomView.setCustomView(view, Integer.valueOf(getNoItemCustomViewTopMargin()));
        }
    }

    private boolean supportSuggesterViewInNoItemCustom() {
        if ((USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM && this.mSuggestionView.getSuggesterType() != SuggesterType.ANALYSIS_TIP_CARD) || this.mSuggestionView.getSuggesterType() == SuggesterType.SCS_SETTING_ACTION || this.mSuggestionView.getSuggesterType() == SuggesterType.LANGUAGE_PACK_DOWNLOAD_ACTION) {
            return true;
        }
        return false;
    }

    private void updateSuggesterViewPadding() {
        if (!supportSuggesterViewInNoItemCustom()) {
            Optional.ofNullable(this.mSuggesterView).ifPresent(new e(20));
        }
    }

    public NoItemView getNoItemView() {
        return this.mNoItemView;
    }

    public void hideSuggesterView() {
        Optional.ofNullable(this.mSuggesterView).ifPresent(new m7.c(10));
    }

    public void onConfigurationChanged() {
        Optional.ofNullable(this.mSuggesterView).ifPresent(new e(19));
        updateSuggesterViewPadding();
    }

    public void reset() {
        SuggesterView suggesterView = this.mSuggesterView;
        if (suggesterView != null) {
            suggesterView.hide();
            this.mSuggesterView = null;
        }
        this.mNoItemView = null;
    }

    public void setNoItemCustomView(View view) {
        NoItemCustomView noItemCustomView;
        boolean z;
        if (view != null) {
            noItemCustomView = (NoItemCustomView) view.findViewById(R.id.no_item_custom_view);
        } else {
            noItemCustomView = null;
        }
        this.mNoItemView = noItemCustomView;
        if (noItemCustomView != null) {
            String scsDisabledReason = getScsDisabledReason();
            boolean isEmpty = TextUtils.isEmpty(scsDisabledReason);
            if (!Features.isEnabled(Features.SUPPORT_DOWNLOAD_LANGUAGE_PACK) || !NeuralTranslator.getInstance().isLanguagePackDownloadable()) {
                z = false;
            } else {
                z = true;
            }
            if (!isEmpty) {
                this.mView.setSuggesterForSettingAction(new Object[]{scsDisabledReason, SuggesterType.SCS_SETTING_ACTION});
            } else if (z) {
                this.mView.setSuggesterForSettingAction(new Object[]{"null", SuggesterType.LANGUAGE_PACK_DOWNLOAD_ACTION});
            }
            this.mNoItemView.setLabel(getNoItemString());
            this.mNoItemView.setDescription(getNoItemDescription());
            bindSuggestionView();
        }
        ViewUtils.setVisibleOrGone(this.mNoItemView, true);
    }
}
