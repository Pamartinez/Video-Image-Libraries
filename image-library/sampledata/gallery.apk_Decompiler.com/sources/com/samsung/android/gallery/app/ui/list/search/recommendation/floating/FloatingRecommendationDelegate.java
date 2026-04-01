package com.samsung.android.gallery.app.ui.list.search.recommendation.floating;

import A4.C0367b;
import A4.C0372g;
import Ab.a;
import Bb.l;
import C3.C0391a;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.list.search.recommendation.abstraction.RecommendationViewListener;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history.FloatingHistoryCoverItemLoader;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history.FloatingHistoryListAdapter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.suggestion.FloatingSuggestionListAdapter;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.module.search.recommendation.IRecommendationItem;
import com.samsung.android.gallery.module.search.recommendation.QuerySuggesterFactory;
import com.samsung.android.gallery.module.search.recommendation.RecommendationClickHandler;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.flexboxlist.FlexBoxListView;
import com.samsung.android.gallery.widget.listview.InterceptTouchListener;
import com.samsung.android.gallery.widget.listview.TouchParentLayout;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingRecommendationDelegate extends Subscriber implements RecommendationViewListener, SearchToolbar.OptionMenuListener {
    protected final String TAG = getClass().getSimpleName();
    protected TouchParentLayout mContentLayout;
    private View mContentView;
    protected Animation mDefaultFadeInAnim;
    private FloatingHistoryListAdapter mHistoryAdapter;
    private TextView mHistoryClear;
    private View mHistoryContainer;
    private final FloatingHistoryCoverItemLoader mHistoryCoverItemLoader = new FloatingHistoryCoverItemLoader();
    private RecyclerView mHistoryListView;
    private TextView mHistoryTitle;
    private final AtomicBoolean mIsSpringAnimationPended = new AtomicBoolean();
    private final AtomicBoolean mIsSuggestionFadeInPended = new AtomicBoolean();
    protected View mLayout;
    private ViewGroup mParent;
    private final RecommendationClickHandler mRecommendationClickHandle;
    private FloatingSuggestionListAdapter mSuggestionAdapter;
    private Animation mSuggestionFadeInAnim;
    private View mSuggestionLayout;
    private FlexBoxListView mSuggestionList;
    private TextView mSuggestionTitle;
    private int mToolbarDimAreaHeight;

    public FloatingRecommendationDelegate(IMvpBaseView iMvpBaseView) {
        super(iMvpBaseView.getBlackboard());
        this.mRecommendationClickHandle = new RecommendationClickHandler(iMvpBaseView.getAnalyticsScreenId(iMvpBaseView.getScreenId()), showRecommendationOnNewFragment());
    }

    private void addView(ViewGroup viewGroup) {
        if (this.mParent == null) {
            this.mParent = viewGroup;
        }
        if (this.mLayout == null) {
            this.mLayout = inflateLayout(this.mParent);
        }
        if (this.mLayout.getParent() == null) {
            this.mParent.addView(this.mLayout, 0);
        }
    }

    private void clearAnimation(View view) {
        if (view != null) {
            view.clearAnimation();
            view.setAlpha(1.0f);
        }
    }

    private GridLayoutManager createHistoryLayoutManager(View view) {
        int i2;
        boolean z = PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V2;
        Context context = view.getContext();
        if (z) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        return new GridLayoutManager(context, i2, z ? 1 : 0, false) {
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    String str = FloatingRecommendationDelegate.this.TAG;
                    Log.e(str, "onLayoutChildren failed. e=" + e.getMessage());
                }
            }

            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        };
    }

    private void handleListViewDensityChanged(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        if (recyclerView != null && adapter != null) {
            recyclerView.setItemViewCacheSize(0);
            Optional.ofNullable(recyclerView.getLayoutManager()).ifPresent(new l(7));
            recyclerView.removeAllViews();
            recyclerView.getRecycledViewPool().clear();
            adapter.notifyDataSetChanged();
        }
    }

    private void initSuggestionList(View view) {
        this.mSuggestionLayout = view.findViewById(R.id.floating_suggestion_layout);
        this.mSuggestionTitle = (TextView) view.findViewById(R.id.floating_suggestion_title);
        this.mSuggestionList = (FlexBoxListView) view.findViewById(R.id.floating_suggestion_view);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(view.getContext());
        flexboxLayoutManager.setFlexWrap(1);
        if (PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V3) {
            flexboxLayoutManager.setFlexDirection(0);
        } else {
            flexboxLayoutManager.setFlexDirection(2);
            flexboxLayoutManager.setJustifyContent(0);
            flexboxLayoutManager.setAlignItems(0);
        }
        this.mSuggestionList.setLayoutManager(flexboxLayoutManager);
    }

    private boolean isSuggestionVisible() {
        FloatingSuggestionListAdapter floatingSuggestionListAdapter;
        if (!MiscSettingPreference.SearchShowSuggestions.isEnabled() || (floatingSuggestionListAdapter = this.mSuggestionAdapter) == null || floatingSuggestionListAdapter.isEmpty()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$1(Object obj, Bundle bundle) {
        handleRecommendationData(bundle.getString("_SUBSCRIBE_KEY", ""), obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initHistoryList$0(View view) {
        onClearSearchHistoryClick();
    }

    private void loadFadeInAnimation() {
        if (this.mSuggestionFadeInAnim == null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.mLayout.getContext(), R.anim.floating_recommendation_fade_in);
            this.mSuggestionFadeInAnim = loadAnimation;
            loadAnimation.setStartOffset(150);
            Animation loadAnimation2 = AnimationUtils.loadAnimation(this.mLayout.getContext(), R.anim.floating_recommendation_fade_in);
            this.mDefaultFadeInAnim = loadAnimation2;
            loadAnimation2.setStartOffset(250);
        }
    }

    private void onClearSearchHistoryClick() {
        this.mRecommendationClickHandle.clearHistory();
        this.mHistoryAdapter.deleteAllHistoryItem();
        updateHistoryVisibility();
    }

    /* access modifiers changed from: private */
    public void setHistoryItem(ArrayList<HistoryItem> arrayList) {
        View view = this.mLayout;
        if (view != null && view.getContext() != null) {
            if (this.mHistoryAdapter == null) {
                FloatingHistoryListAdapter floatingHistoryListAdapter = new FloatingHistoryListAdapter(this);
                this.mHistoryAdapter = floatingHistoryListAdapter;
                this.mHistoryListView.setAdapter(floatingHistoryListAdapter);
            }
            this.mHistoryAdapter.setData(arrayList);
            updateHistoryVisibility();
        }
    }

    private void setSuggestionData(Collection<? extends IRecommendationItem> collection) {
        FlexBoxListView flexBoxListView = this.mSuggestionList;
        if (flexBoxListView != null) {
            if (flexBoxListView.isComputingLayout()) {
                Log.se(this.TAG, "setSuggestionData is failed because suggestionList is computing a layout");
                return;
            }
            if (this.mSuggestionAdapter == null) {
                FloatingSuggestionListAdapter floatingSuggestionListAdapter = new FloatingSuggestionListAdapter(this);
                this.mSuggestionAdapter = floatingSuggestionListAdapter;
                this.mSuggestionList.setAdapter(floatingSuggestionListAdapter);
            }
            this.mSuggestionAdapter.setData(collection);
            updateSuggestionVisibility(this.mIsSpringAnimationPended.getAndSet(false));
        }
    }

    private boolean shouldHistoryVisible() {
        FloatingHistoryListAdapter floatingHistoryListAdapter;
        if (!MiscSettingPreference.SearchSaveRecent.isEnabled() || (floatingHistoryListAdapter = this.mHistoryAdapter) == null || floatingHistoryListAdapter.isEmpty()) {
            return false;
        }
        return true;
    }

    private void updateHistoryVisibility() {
        int i2;
        View view = this.mHistoryContainer;
        if (shouldHistoryVisible()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
    }

    private void updateSuggestionVisibility(boolean z) {
        View view;
        boolean isSuggestionVisible = isSuggestionVisible();
        ViewUtils.setVisibleOrGone(this.mSuggestionList, isSuggestionVisible);
        ViewUtils.setVisibleOrGone(this.mSuggestionTitle, isSuggestionVisible);
        if (z) {
            if (this.mSuggestionList.getHeight() > 0) {
                startSuggestionSpringAnimation();
            } else {
                this.mSuggestionList.post(new C0372g(27, this));
            }
        } else if (this.mIsSuggestionFadeInPended.getAndSet(false) && isSuggestionVisible && (view = this.mSuggestionLayout) != null) {
            view.startAnimation(this.mSuggestionFadeInAnim);
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("data://user/search/RecommendationData/#", new C0391a(3, this)).setWorkingOnUI());
    }

    public int getLayoutResId() {
        if (PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V2) {
            return R.layout.floating_recommendation_layout_v2;
        }
        return R.layout.floating_recommendation_layout;
    }

    public void handleConfigurationChanged() {
        handleListViewDensityChanged(this.mHistoryListView, this.mHistoryAdapter);
        handleListViewDensityChanged(this.mSuggestionList, this.mSuggestionAdapter);
        int dimensionPixelSize = this.mLayout.getResources().getDimensionPixelSize(R.dimen.search_recommend_main_title_text_size);
        int dimensionPixelOffset = this.mLayout.getResources().getDimensionPixelOffset(R.dimen.search_recommend_title_text_size);
        ViewUtils.setTextSize(this.mSuggestionTitle, 0, dimensionPixelSize);
        ViewUtils.setTextSize(this.mHistoryTitle, 0, dimensionPixelOffset);
        ViewUtils.setTextSize(this.mHistoryClear, 0, dimensionPixelOffset);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 8018) {
            return false;
        }
        if (PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V2) {
            Object[] objArr = (Object[]) eventMessage.obj;
            boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
            SearchToolbar searchToolbar = (SearchToolbar) objArr[1];
            if (booleanValue) {
                show(searchToolbar.getView(), false);
            } else {
                hide(searchToolbar.hasFocus());
            }
        }
        return true;
    }

    public void handleRecommendationData(String str, Object obj) {
        if ("data://user/search/RecommendationData/suggestion".equals(str)) {
            setSuggestionData((Collection) obj);
        } else if ("data://user/search/RecommendationData/history".equals(str)) {
            this.mHistoryCoverItemLoader.load((ArrayList) obj, new C0367b(21, this));
        }
    }

    public void hide(boolean z) {
        if (!z && PreferenceFeatures.isEnabled(PreferenceFeatures.AlwaysGetNewSuggestions)) {
            QuerySuggesterFactory.create().clear();
        }
        ViewUtils.setVisibleOrGone(this.mLayout, false);
    }

    public View inflateLayout(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutResId(), viewGroup, false);
        initHistoryList(inflate);
        initSuggestionList(inflate);
        this.mContentLayout = (TouchParentLayout) inflate.findViewById(R.id.floating_recommendation_layout);
        View findViewById = inflate.findViewById(R.id.floating_recommendation_view);
        this.mContentView = findViewById;
        if (findViewById instanceof NestedScrollView) {
            ((NestedScrollView) findViewById).seslSetFadingEdgeEnabled(true, true);
        }
        return inflate;
    }

    public void initHistoryList(View view) {
        this.mHistoryContainer = view.findViewById(R.id.floating_history_container);
        this.mHistoryTitle = (TextView) view.findViewById(R.id.history_title);
        TextView textView = (TextView) view.findViewById(R.id.clear_history_button);
        this.mHistoryClear = textView;
        SeApiCompat.setButtonShapeEnabled(textView);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.floating_history_view);
        this.mHistoryListView = recyclerView;
        recyclerView.setLayoutManager(createHistoryLayoutManager(view));
        this.mHistoryClear.setOnClickListener(new a(14, this));
    }

    public void invalidateHistoryView() {
        FloatingHistoryListAdapter floatingHistoryListAdapter = this.mHistoryAdapter;
        if (floatingHistoryListAdapter != null) {
            floatingHistoryListAdapter.notifyAll();
        }
    }

    public void onAttached(ViewGroup viewGroup) {
        this.mParent = viewGroup;
        if (this.mLayout == null) {
            this.mLayout = inflateLayout(viewGroup);
        }
    }

    public void onDeleteHistoryClick(HistoryItem historyItem) {
        this.mRecommendationClickHandle.deleteHistory(historyItem);
        updateHistoryVisibility();
    }

    public void onDeleteRemainingHistory(HistoryItem historyItem) {
        this.mRecommendationClickHandle.deleteRemainedHistory(historyItem);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBlackboard.erase("command://MoveURL");
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.AlwaysGetNewSuggestions)) {
            QuerySuggesterFactory.create().clear();
        }
        this.mLayout.setOnScrollChangeListener((View.OnScrollChangeListener) null);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onPause() {
        clearAnimation(this.mSuggestionLayout);
        clearAnimation(this.mHistoryContainer);
        clearAnimation(this.mLayout);
    }

    public void onRecentlyHistoryClick(HistoryItem historyItem) {
        this.mRecommendationClickHandle.clickHistory(this.mBlackboard, historyItem);
    }

    public void onSuggestionKeywordClick(IRecommendationItem iRecommendationItem, int i2) {
        this.mRecommendationClickHandle.clickSuggestion(this.mBlackboard, iRecommendationItem, i2);
    }

    public boolean onViewTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    public void setHeight(int i2) {
        ViewUtils.setHeight(this.mLayout, i2);
    }

    public void setOnInterceptTouchListener(InterceptTouchListener interceptTouchListener) {
        TouchParentLayout touchParentLayout = this.mContentLayout;
        if (touchParentLayout != null) {
            touchParentLayout.setListener(interceptTouchListener);
        }
    }

    public void setToolbarDimAreaHeight(int i2) {
        this.mToolbarDimAreaHeight = i2;
        ViewMarginUtils.setBottomPadding(this.mContentView, i2);
    }

    public void show(ViewGroup viewGroup, boolean z) {
        boolean z3;
        if (this.mLayout.getParent() == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        addView(viewGroup);
        updateHistoryVisibility();
        updateSuggestionVisibility(z);
        ViewUtils.setVisibleOrGone(this.mLayout, true);
        if (z3) {
            startShowAnimation();
        }
    }

    public boolean showRecommendationOnNewFragment() {
        return true;
    }

    public void startShowAnimation() {
        if (PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V2) {
            loadFadeInAnimation();
            if (this.mSuggestionLayout != null) {
                if (isSuggestionVisible()) {
                    this.mSuggestionLayout.startAnimation(this.mSuggestionFadeInAnim);
                } else {
                    this.mIsSuggestionFadeInPended.set(true);
                }
            }
            View view = this.mHistoryContainer;
            if (view != null) {
                view.startAnimation(this.mDefaultFadeInAnim);
                return;
            }
            return;
        }
        this.mLayout.setAlpha(0.0f);
        this.mLayout.animate().alpha(1.0f).setStartDelay(150).setDuration(300).start();
    }

    public void startSuggestionSpringAnimation() {
        boolean z;
        AtomicBoolean atomicBoolean = this.mIsSpringAnimationPended;
        if (this.mSuggestionList.getChildCount() < 1) {
            z = true;
        } else {
            z = false;
        }
        atomicBoolean.set(z);
        for (int i2 = 0; i2 < this.mSuggestionList.getChildCount(); i2++) {
            AnimatorSet animatorSet = new AnimatorSet();
            View childAt = this.mSuggestionList.getChildAt(i2);
            ViewUtils.setVisibleOrInvisible(childAt, false);
            PropertyAnimator withStartAction = new TranslationAnimator(childAt).fromYRelative((float) (childAt.getHeight() * (this.mSuggestionList.getChildCount() - i2))).setDuration(StatusCodes.INPUT_MISSING).withStartAction(new D5.a(childAt, 0));
            withStartAction.setStartDelay(300);
            withStartAction.setInterpolator(new OvershootInterpolator(1.0f));
            ViewUtils.setAlpha(childAt, 0.0f);
            PropertyAnimator duration = new AlphaAnimator(childAt).alpha(1.0f).setDuration(StatusCodes.INPUT_MISSING);
            duration.setStartDelay(300);
            duration.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SUGGEST_KEYWORD_FADE_IN));
            animatorSet.playTogether(new Animator[]{withStartAction, duration});
            animatorSet.start();
        }
    }

    public void updateSuggestionTitleTopMargin() {
        ViewMarginUtils.setTopMargin(this.mSuggestionTitle, this.mLayout.getResources().getDimensionPixelOffset(R.dimen.search_floating_recommend_suggested_title_margin_top));
    }

    public void updateViews(int i2, int i7) {
        ViewMarginUtils.setBottomPadding(this.mContentView, this.mToolbarDimAreaHeight + i7);
    }

    public void initFloatingToolbarLayout() {
    }

    public void prepareOptionsMenu(Menu menu) {
    }

    public void createOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }
}
