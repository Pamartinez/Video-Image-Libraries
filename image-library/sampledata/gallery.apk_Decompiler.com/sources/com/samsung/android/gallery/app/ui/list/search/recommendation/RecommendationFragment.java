package com.samsung.android.gallery.app.ui.list.search.recommendation;

import A4.C0367b;
import Ab.a;
import B2.i;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.abstraction.RecommendationViewListener;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.module.search.recommendation.IRecommendationItem;
import com.samsung.android.gallery.module.search.recommendation.QuerySuggesterFactory;
import com.samsung.android.gallery.module.search.recommendation.RecommendationClickHandler;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.flexboxlist.FlexBoxListView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecommendationFragment<V extends IMvpBaseView, P extends MvpBasePresenter<?>> extends MvpBaseFragment<V, P> implements RecommendationViewListener {
    private AlphaAnimator mAlphaAnimator;
    protected TextView mClearHistoryButton;
    private RecentlyHistoryListAdapter mHistoryAdapter;
    private FlexBoxListView mHistoryFlexBoxListView;
    protected LinearLayout mHistoryList;
    private SearchMyTagAdapter mMyTagAdapter;
    private FlexBoxListView mMyTagFlexBoxListView;
    protected LinearLayout mMyTagList;
    private final RecommendationClickHandler mRecommendationClickHandle = new RecommendationClickHandler(getAnalyticsScreenId(getScreenId()));
    protected FrameLayout mRecommendationList;
    private SuggestionKeywordAdapter mSuggestionAdapter;
    private FlexBoxListView mSuggestionFlexBoxListView;
    protected LinearLayout mSuggestionList;
    /* access modifiers changed from: private */
    public MediaData mTagData;
    private final MediaData.OnDataChangeListener mTagDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            Object obj;
            StringCompat access$000 = RecommendationFragment.this.TAG;
            if (RecommendationFragment.this.mTagData != null) {
                obj = Integer.valueOf(RecommendationFragment.this.mTagData.getCount());
            } else {
                obj = "-1]";
            }
            Log.d(access$000, "onDataChanged TAG count [", obj);
            if (RecommendationFragment.this.mTagData != null && RecommendationFragment.this.mTagData.getCount() > 0) {
                RecommendationFragment.this.initializeTagView();
            }
        }
    };

    private void checkVisibility() {
        if (this.mSuggestionAdapter != null && this.mHistoryAdapter != null) {
            boolean unShowSuggestion = unShowSuggestion();
            boolean unShowHistory = unShowHistory();
            if (!unShowHistory && !unShowSuggestion) {
                this.mHistoryFlexBoxListView.getLayoutParams().height = -2;
                this.mHistoryList.getLayoutParams().height = -2;
                this.mSuggestionFlexBoxListView.getLayoutParams().height = -2;
                this.mSuggestionList.getLayoutParams().height = -2;
            } else if (unShowHistory) {
                this.mSuggestionFlexBoxListView.getLayoutParams().height = -2;
                ((ViewGroup) this.mSuggestionFlexBoxListView.getParent()).getLayoutParams().height = -2;
            } else if (unShowSuggestion) {
                this.mHistoryFlexBoxListView.getLayoutParams().height = -2;
                ((ViewGroup) this.mHistoryFlexBoxListView.getParent()).getLayoutParams().height = -2;
            }
        }
    }

    private void closeTagData() {
        MediaData mediaData = this.mTagData;
        if (mediaData != null) {
            mediaData.unregister(this.mTagDataChangeListener);
            this.mTagData.close();
            this.mTagData = null;
        }
    }

    private void handleDensityChanged(TextView textView) {
        if (textView != null) {
            textView.getPaint().setTextSize(getResources().getDimension(R.dimen.search_recommend_header_font_size));
        }
    }

    private void initAnimator() {
        AlphaAnimator alphaAnimator = new AlphaAnimator((View) this.mRecommendationList, 0.0f, 1.0f);
        this.mAlphaAnimator = alphaAnimator;
        alphaAnimator.setDuration(500);
    }

    /* access modifiers changed from: private */
    public void initializeTagView() {
        SearchMyTagAdapter searchMyTagAdapter = this.mMyTagAdapter;
        if (searchMyTagAdapter == null) {
            SearchMyTagAdapter searchMyTagAdapter2 = new SearchMyTagAdapter(getBlackboard(), getScreenId());
            this.mMyTagAdapter = searchMyTagAdapter2;
            searchMyTagAdapter2.setData(this.mTagData);
            Optional.ofNullable(this.mMyTagFlexBoxListView).ifPresent(new C0367b(19, this));
            ViewUtils.setVisibility(this.mMyTagList, 0);
            startAlphaAnimation();
            return;
        }
        searchMyTagAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeTagView$0(FlexBoxListView flexBoxListView) {
        flexBoxListView.setAdapter(this.mMyTagAdapter);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFlexBoxListView$1(View view) {
        onClearSearchHistoryClick();
    }

    private void onClearSearchHistoryClick() {
        this.mRecommendationClickHandle.clearHistory();
        this.mHistoryAdapter.deleteAllHistoryItem();
        setHistoryVisibility();
    }

    private void openTagData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(ArgumentsUtil.appendArgs("location://search/fileList/Category/MyTag", "vs_use_cache", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE));
        this.mTagData = open;
        open.register(this.mTagDataChangeListener);
    }

    private void setFlexBoxListView() {
        FlexBoxListView flexBoxListView = (FlexBoxListView) this.mMyTagList.findViewById(R.id.flex_box_list);
        this.mMyTagFlexBoxListView = flexBoxListView;
        flexBoxListView.setLayoutManager(new FlexboxLayoutManager(getApplicationContext()));
        this.mMyTagFlexBoxListView.setOnTouchListener(new i(4, this));
        FlexBoxListView flexBoxListView2 = (FlexBoxListView) this.mHistoryList.findViewById(R.id.flex_box_list);
        this.mHistoryFlexBoxListView = flexBoxListView2;
        flexBoxListView2.setLayoutManager(new FlexboxLayoutManager(getApplicationContext()));
        this.mHistoryFlexBoxListView.setOnTouchListener(new i(4, this));
        this.mClearHistoryButton.setOnClickListener(new a(10, this));
        FlexBoxListView flexBoxListView3 = (FlexBoxListView) this.mSuggestionList.findViewById(R.id.flex_box_list);
        this.mSuggestionFlexBoxListView = flexBoxListView3;
        flexBoxListView3.setLayoutManager(new FlexboxLayoutManager(getApplicationContext()));
        this.mSuggestionFlexBoxListView.setOnTouchListener(new i(4, this));
    }

    private void setHeaderTitle() {
        ((TextView) this.mHistoryList.findViewById(R.id.gallery_header_title_text)).setText(R.string.recent_search);
        ((TextView) this.mSuggestionList.findViewById(R.id.gallery_header_title_text)).setText(R.string.suggest_word);
        this.mSuggestionList.findViewById(R.id.clear_history_button).setVisibility(8);
        ((TextView) this.mMyTagList.findViewById(R.id.gallery_header_title_text)).setText(R.string.my_tags);
        this.mMyTagList.findViewById(R.id.clear_history_button).setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void setHistoryItem(ArrayList<HistoryItem> arrayList) {
        if (getContext() == null) {
            Log.se(this.TAG, "fragment detached. ignore");
            return;
        }
        RecentlyHistoryListAdapter recentlyHistoryListAdapter = this.mHistoryAdapter;
        if (recentlyHistoryListAdapter == null) {
            RecentlyHistoryListAdapter recentlyHistoryListAdapter2 = new RecentlyHistoryListAdapter(this);
            this.mHistoryAdapter = recentlyHistoryListAdapter2;
            recentlyHistoryListAdapter2.setHistoryItem(arrayList);
            this.mHistoryFlexBoxListView.setAdapter(this.mHistoryAdapter);
        } else {
            recentlyHistoryListAdapter.setHistoryItem(arrayList);
            this.mHistoryAdapter.notifyDataSetChanged();
        }
        setHistoryVisibility();
    }

    private void setHistoryVisibility() {
        if (this.mHistoryList != null) {
            if (unShowHistory()) {
                this.mHistoryList.setVisibility(8);
                return;
            }
            this.mHistoryList.setVisibility(0);
            checkVisibility();
        }
    }

    /* access modifiers changed from: private */
    public void setSuggestionData(Collection<? extends IRecommendationItem> collection) {
        SuggestionKeywordAdapter suggestionKeywordAdapter = this.mSuggestionAdapter;
        if (suggestionKeywordAdapter == null) {
            SuggestionKeywordAdapter suggestionKeywordAdapter2 = new SuggestionKeywordAdapter(this, new A5.a(3, this));
            this.mSuggestionAdapter = suggestionKeywordAdapter2;
            suggestionKeywordAdapter2.setData(collection);
            this.mSuggestionFlexBoxListView.setAdapter(this.mSuggestionAdapter);
            startAlphaAnimation();
        } else {
            suggestionKeywordAdapter.setData(collection);
        }
        setSuggestionVisibility();
    }

    private void setSuggestionVisibility() {
        int i2;
        LinearLayout linearLayout = this.mSuggestionList;
        if (linearLayout != null) {
            if (unShowSuggestion()) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            linearLayout.setVisibility(i2);
            checkVisibility();
        }
    }

    private void startAlphaAnimation() {
        AlphaAnimator alphaAnimator = this.mAlphaAnimator;
        if (alphaAnimator != null && !alphaAnimator.isStarted()) {
            this.mAlphaAnimator.start();
        }
    }

    private void startShowAnimation() {
        FrameLayout frameLayout = this.mRecommendationList;
        if (frameLayout != null) {
            frameLayout.scheduleLayoutAnimation();
        }
    }

    private boolean unShowHistory() {
        if (this.mHistoryAdapter.isEmpty() || !MiscSettingPreference.SearchSaveRecent.isEnabled()) {
            return true;
        }
        return false;
    }

    private boolean unShowSuggestion() {
        if (this.mSuggestionAdapter.isEmpty() || !MiscSettingPreference.SearchShowSuggestions.isEnabled()) {
            return true;
        }
        return false;
    }

    private void updateMarginOnSmallSizeScreenChanged() {
        if (isViewActive() && ResourceCompat.isSmallScreenSizeChanged(getContext())) {
            SuggestionKeywordAdapter suggestionKeywordAdapter = this.mSuggestionAdapter;
            if (suggestionKeywordAdapter != null) {
                suggestionKeywordAdapter.notifyItemRangeChanged(0, suggestionKeywordAdapter.getItemCount(), "updateSideMargin");
            }
            RecentlyHistoryListAdapter recentlyHistoryListAdapter = this.mHistoryAdapter;
            if (recentlyHistoryListAdapter != null) {
                recentlyHistoryListAdapter.notifyItemRangeChanged(0, recentlyHistoryListAdapter.getItemCount(), "updateSideMargin");
            }
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMyTagList = (LinearLayout) view.findViewById(R.id.tag_list);
        this.mRecommendationList = (FrameLayout) view.findViewById(R.id.recommendation_view);
        this.mSuggestionList = (LinearLayout) view.findViewById(R.id.suggestion_keyword);
        this.mHistoryList = (LinearLayout) view.findViewById(R.id.recently_history);
        TextView textView = (TextView) view.findViewById(R.id.clear_history_button);
        this.mClearHistoryButton = textView;
        SeApiCompat.setButtonShapeEnabled(textView);
        this.mSuggestionList.setOnTouchListener(new i(4, this));
        this.mHistoryList.setOnTouchListener(new i(4, this));
    }

    public MvpBasePresenter<?> createPresenter(IMvpBaseView iMvpBaseView) {
        return new MvpBasePresenter<IMvpBaseView>(this.mBlackboard, iMvpBaseView) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$createSubscriberList$0(Object obj, Bundle bundle) {
                if (RecommendationFragment.this.isResumed()) {
                    RecommendationFragment.this.setSuggestionData((Collection) obj);
                }
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$createSubscriberList$1(Object obj, Bundle bundle) {
                if (RecommendationFragment.this.isResumed()) {
                    RecommendationFragment.this.setHistoryItem((ArrayList) obj);
                }
            }

            public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
                arrayList.add(new SubscriberInfo("data://user/search/RecommendationData/suggestion", new e(this, 0)));
                arrayList.add(new SubscriberInfo("data://user/search/RecommendationData/history", new e(this, 1)));
            }

            public void initMenu() {
            }
        };
    }

    public int getLayoutId() {
        return R.layout.fragment_recommendation;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_SUGGESTION_LIST.toString();
    }

    public void handleDensityChange(int i2) {
        getBoosterCompat().acquireFull();
        handleDensityChanged((TextView) this.mHistoryList.findViewById(R.id.gallery_header_title_text));
        handleDensityChanged((TextView) this.mSuggestionList.findViewById(R.id.gallery_header_title_text));
        updateMarginOnSmallSizeScreenChanged();
    }

    public void handleResolutionChange(int i2) {
        if (!isConfigStateChanged(2)) {
            updateMarginOnSmallSizeScreenChanged();
        }
    }

    public void initView(View view) {
        setHeaderTitle();
        setFlexBoxListView();
        initAnimator();
    }

    public void onAttach(Context context) {
        setLocationKey(getTag());
        super.onAttach(context);
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchTagInRecommendationView) && PickerUtil.isNormalLaunchMode(this.mBlackboard)) {
            openTagData();
        }
    }

    public void onDeleteHistoryClick(HistoryItem historyItem) {
        this.mRecommendationClickHandle.deleteHistory(historyItem);
        setHistoryVisibility();
    }

    public void onDeleteRemainingHistory(HistoryItem historyItem) {
        this.mRecommendationClickHandle.deleteRemainedHistory(historyItem);
    }

    public void onDestroy() {
        super.onDestroy();
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.AlwaysGetNewSuggestions)) {
            QuerySuggesterFactory.create().clear();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mBlackboard.erase("command://MoveURL");
        closeTagData();
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            startShowAnimation();
            startAlphaAnimation();
        }
    }

    public void onRecentlyHistoryClick(HistoryItem historyItem) {
        this.mRecommendationClickHandle.clickHistory(this.mBlackboard, historyItem);
    }

    public void onSuggestionKeywordClick(IRecommendationItem iRecommendationItem, int i2) {
        this.mRecommendationClickHandle.clickSuggestion(this.mBlackboard, iRecommendationItem, i2);
    }

    public boolean onViewTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this.mBlackboard.postEvent(EventMessage.obtain(8502));
        return false;
    }

    public boolean supportActivityToolbar() {
        return !PickerUtil.isNormalLaunchMode(this.mBlackboard);
    }

    public String toString() {
        return this.TAG + com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR + hashCode();
    }
}
