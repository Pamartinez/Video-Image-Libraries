package com.samsung.android.gallery.app.ui.list.search.pictures.relationship;

import A4.A;
import A4.C0366a;
import A4.C0384t;
import A4.O;
import B5.d;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import com.google.android.material.oneui.dividerbuttonlayout.DividerButtonLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResult2Fragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchRelationshipHeaderView;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.ISearchRelationshipPreviewView;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.SearchRelationshipPreviewPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchRelationshipPreviewFragment<V extends ISearchRelationshipPreviewView, P extends SearchRelationshipPreviewPresenter<V>> extends SearchClusterResult2Fragment<V, P> implements ISearchRelationshipPreviewView {
    private DividerButtonLayout mDividerButtonLayout;
    private FloatingBottomLayout mDividerButtonLayoutContainer;
    private ArrayList<String> mPendingCandidates;
    private final AtomicBoolean mPendingReloadCreatureList = new AtomicBoolean(false);
    private View mProgressParent;
    private LinkedHashMap<String, List<Long>> mRelationshipRecommendMap;

    private Menu getBottomMenu() {
        DividerButtonLayout dividerButtonLayout = this.mDividerButtonLayout;
        if (dividerButtonLayout != null) {
            return dividerButtonLayout.getMenu();
        }
        return null;
    }

    private MenuItem getSetAsRelationMenuItem() {
        return (MenuItem) Optional.ofNullable(getBottomMenu()).map(new C0384t(17)).orElse((Object) null);
    }

    private void initBottomMenu(View view) {
        this.mDividerButtonLayoutContainer = (FloatingBottomLayout) view.findViewById(R.id.divider_button_floating_container);
        DividerButtonLayout dividerButtonLayout = (DividerButtonLayout) view.findViewById(R.id.divider_button_layout);
        this.mDividerButtonLayout = dividerButtonLayout;
        dividerButtonLayout.setOnMenuItemClickListener(new O(9, this));
        this.mDividerButtonLayout.c(R.menu.menu_search_category_suggested_creature_select);
        ViewUtils.setVisibleOrGone(this.mDividerButtonLayoutContainer, !isLandscape());
        updateSetAsRelationMenu();
        Optional.ofNullable(getSetAsRelationMenuItem()).ifPresent(new C0366a(22));
        initFloatingLayout();
    }

    private void initFloatingLayout() {
        FloatingBottomLayout floatingBottomLayout = this.mDividerButtonLayoutContainer;
        if (floatingBottomLayout != null && this.mDividerButtonLayout != null) {
            floatingBottomLayout.m(true, false);
            this.mDividerButtonLayoutContainer.a(List.of(this.mDividerButtonLayout));
            this.mDividerButtonLayoutContainer.setRecyclerView(this.mRecyclerView);
        }
    }

    private void initRelationshipDelegate() {
        Optional.ofNullable(getPdcSearchDelegate()).ifPresent(new d(this, 0));
    }

    private boolean isPdcCandidateItemSelected() {
        PdcSearchDelegate pdcSearchDelegate = getPdcSearchDelegate();
        if (pdcSearchDelegate == null || pdcSearchDelegate.getSelectedItems().isEmpty()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initRelationshipDelegate$2(PdcSearchDelegate pdcSearchDelegate) {
        pdcSearchDelegate.setOnSelectedFaceChangedListener(new d(this, 1));
        pdcSearchDelegate.setOnDataLoadedListener(new A2.d(11, this, pdcSearchDelegate));
        if (hasNoData()) {
            this.mPendingCandidates = null;
        }
        pdcSearchDelegate.setData(this.mRelationshipRecommendMap, this.mPendingCandidates);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onFaceDataLoaded$3(PdcSearchDelegate pdcSearchDelegate, SearchRelationshipPreviewPresenter searchRelationshipPreviewPresenter) {
        if (this.mPendingReloadCreatureList.getAndSet(false)) {
            ((SearchRelationshipPreviewPresenter) this.mPresenter).reloadListWithRelationship(pdcSearchDelegate.getCurrentItems());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSetAsRelationMenu$4(MenuItem menuItem) {
        int i2;
        setEnableMenuItem(menuItem, isPdcCandidateItemSelected());
        if (this.mRelationshipRecommendMap.size() > 1) {
            i2 = R.string.next;
        } else {
            i2 = R.string.done;
        }
        menuItem.setTitle(i2);
    }

    /* access modifiers changed from: private */
    /* renamed from: onFaceDataLoaded */
    public void lambda$initRelationshipDelegate$1(PdcSearchDelegate pdcSearchDelegate) {
        Optional.ofNullable((SearchRelationshipPreviewPresenter) this.mPresenter).ifPresent(new A(2, (Object) this, (Object) pdcSearchDelegate));
        if (pdcSearchDelegate.isFiltered()) {
            setProgressBarVisibility(false);
        }
    }

    /* access modifiers changed from: private */
    public boolean onMenuItemSelected(MenuItem menuItem) {
        PdcSearchDelegate pdcSearchDelegate = getPdcSearchDelegate();
        if (pdcSearchDelegate == null) {
            Log.w(this.TAG, "onNavigationItemSelected : delegate is null!");
            return true;
        }
        if (menuItem.getItemId() == R.id.action_later) {
            pdcSearchDelegate.skipRelationship(false);
            AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_SEARCH_RELATIONSHIP_SUGGESTION.toString(), AnalyticsEventId.EVENT_SEARCH_RELATIONSHIP_NOT_NOW.toString());
        } else if (menuItem.getItemId() == R.id.action_set_as_relation && menuItem.isEnabled()) {
            pdcSearchDelegate.onDoneClicked();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onSelectedFaceChanged(ArrayList<MediaItem> arrayList) {
        ((SearchRelationshipPreviewPresenter) this.mPresenter).reloadListWithRelationship(arrayList);
        updateSetAsRelationMenu();
    }

    private void setEnableMenuItem(MenuItem menuItem, boolean z) {
        DividerButtonLayout dividerButtonLayout;
        float f;
        menuItem.setEnabled(z);
        if (!isLandscape() && (dividerButtonLayout = this.mDividerButtonLayout) != null) {
            View findViewById = dividerButtonLayout.findViewById(menuItem.getItemId());
            if (z) {
                f = 1.0f;
            } else {
                f = 0.4f;
            }
            findViewById.setAlpha(f);
        }
    }

    private void updateBottomMargin(WindowInsets windowInsets) {
        if (this.mDividerButtonLayoutContainer != null && !isDestroyed()) {
            int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(windowInsets);
            int measuredHeight = this.mDividerButtonLayoutContainer.getMeasuredHeight();
            if (measuredHeight == 0) {
                int paddingBottom = this.mDividerButtonLayoutContainer.getPaddingBottom() + this.mDividerButtonLayoutContainer.getPaddingTop();
                if (isLandscape()) {
                    measuredHeight = 0;
                } else {
                    measuredHeight = getResources().getDimensionPixelOffset(R.dimen.divider_button_layout_height) + paddingBottom;
                }
            }
            ViewMarginUtils.setBottomPadding(getListView(), measuredHeight + systemInsetsBottom);
            ViewMarginUtils.setBottomMargin(this.mDividerButtonLayoutContainer, systemInsetsBottom);
            this.mRecyclerView.setClipToPadding(false);
        }
    }

    private void updateSetAsRelationMenu() {
        if (this.mPresenter != null) {
            Optional.ofNullable(getSetAsRelationMenuItem()).ifPresent(new d(this, 2));
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mProgressParent = view.findViewById(R.id.progress_parent);
        initBottomMenu(view);
    }

    public View createHeaderView() {
        View createHeaderView = super.createHeaderView();
        initRelationshipDelegate();
        return createHeaderView;
    }

    public SearchHeaderView.HeaderType getHeaderType() {
        return SearchHeaderView.HeaderType.RELATIONSHIP;
    }

    public int getLayoutId() {
        return R.layout.fragment_search_relationship_preview_layout;
    }

    public PdcSearchDelegate getPdcSearchDelegate() {
        SearchHeaderView headerView = getHeaderView();
        if (headerView instanceof SearchRelationshipHeaderView) {
            return ((SearchRelationshipHeaderView) headerView).getPdcDelegate();
        }
        return null;
    }

    public boolean hasNoData() {
        if (this.mMediaData.getCount() == 0) {
            return true;
        }
        return false;
    }

    public boolean isDataFiltered() {
        PdcSearchDelegate pdcSearchDelegate = getPdcSearchDelegate();
        if (pdcSearchDelegate == null || !pdcSearchDelegate.isFiltered()) {
            return false;
        }
        return true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        updateBottomMargin(windowInsets);
        return windowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mRelationshipRecommendMap = (LinkedHashMap) this.mBlackboard.pop("data://user/PersonRelationshipCandidateMap", new LinkedHashMap());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean z = true;
        if (configuration.orientation != 1) {
            z = false;
        }
        ViewUtils.setVisibleOrGone(this.mDividerButtonLayoutContainer, z);
        invalidateOptionsMenu();
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (hasNoData()) {
            ((SearchRelationshipPreviewPresenter) this.mPresenter).handleNoData();
        }
    }

    public void pendingFilterData(ArrayList<String> arrayList) {
        if (!isDataFiltered()) {
            this.mPendingCandidates = arrayList;
        }
    }

    public void pendingReloadCreatureList() {
        this.mPendingReloadCreatureList.set(true);
    }

    public void setBackgroundAndSystemUiBarColor(boolean z) {
        super.setBackgroundAndSystemUiBarColor(false);
    }

    public void setProgressBarVisibility(boolean z) {
        boolean z3;
        if (this.mProgressCircle != null) {
            View view = this.mProgressParent;
            if (isDataFiltered() || !z) {
                z3 = false;
            } else {
                z3 = true;
            }
            ViewUtils.setVisibleOrGone(view, z3);
            this.mProgressCircle.updateVisibility(z);
        }
    }

    public boolean skipEmptyView(boolean z) {
        return true;
    }

    public boolean supportMenu() {
        return PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85;
    }

    public boolean supportSelection() {
        return false;
    }

    public void updateHeader() {
        updateItemCounts();
    }

    public SearchRelationshipPreviewPresenter createPresenter(ISearchRelationshipPreviewView iSearchRelationshipPreviewView) {
        return new SearchRelationshipPreviewPresenter(this.mBlackboard, iSearchRelationshipPreviewView);
    }

    public void initForegroundController(View view) {
    }
}
