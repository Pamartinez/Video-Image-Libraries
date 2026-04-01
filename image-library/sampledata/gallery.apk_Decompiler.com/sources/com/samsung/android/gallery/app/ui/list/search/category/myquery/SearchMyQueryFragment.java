package com.samsung.android.gallery.app.ui.list.search.category.myquery;

import A4.J;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import com.google.android.material.oneui.dividerbuttonlayout.DividerButtonLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.IMyQueryCategoryView;
import com.samsung.android.gallery.app.ui.list.search.category.myquery.SearchMyQueryPresenter;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.scs.ai.language.a;
import com.sec.android.gallery3d.R;
import g6.g;
import i4.C0468a;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMyQueryFragment<V extends IMyQueryCategoryView, P extends SearchMyQueryPresenter> extends CategoryFragment<V, P> implements IMyQueryCategoryView {
    private DividerButtonLayout mDividerButtonLayout;
    private FloatingBottomLayout mDividerButtonLayoutContainer;

    private Menu getBottomMenu() {
        DividerButtonLayout dividerButtonLayout = this.mDividerButtonLayout;
        if (dividerButtonLayout != null) {
            return dividerButtonLayout.getMenu();
        }
        return null;
    }

    private MenuItem getSaveMenuItem() {
        return (MenuItem) Optional.ofNullable(getBottomMenu()).map(new C0468a(25)).orElse((Object) null);
    }

    private void initBottomMenu(View view) {
        this.mDividerButtonLayoutContainer = (FloatingBottomLayout) view.findViewById(R.id.divider_button_floating_container);
        DividerButtonLayout dividerButtonLayout = (DividerButtonLayout) view.findViewById(R.id.divider_button_layout);
        this.mDividerButtonLayout = dividerButtonLayout;
        SearchMyQueryPresenter searchMyQueryPresenter = (SearchMyQueryPresenter) this.mPresenter;
        Objects.requireNonNull(searchMyQueryPresenter);
        dividerButtonLayout.setOnMenuItemClickListener(new a(21, searchMyQueryPresenter));
        this.mDividerButtonLayout.c(R.menu.menu_search_category_myquery);
        if (isLandscape()) {
            ViewUtils.setVisibleOrGone(this.mDividerButtonLayoutContainer, false);
        } else {
            SimpleAnimator.setVisibilityAni(this.mDividerButtonLayoutContainer, 0);
        }
        updateSaveMenu();
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

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$removeItem$0(int i2, SearchMyQueryAdapter searchMyQueryAdapter) {
        searchMyQueryAdapter.removeItem(i2);
        updateSaveMenu();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSaveMenu$1(MenuItem menuItem) {
        setEnableMenuItem(menuItem, ((SearchMyQueryPresenter) this.mPresenter).hasCandidatesForDeletion());
    }

    private void setEnableMenuItem(MenuItem menuItem, boolean z) {
        float f;
        menuItem.setEnabled(z);
        DividerButtonLayout dividerButtonLayout = this.mDividerButtonLayout;
        if (dividerButtonLayout != null) {
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

    public void bindView(View view) {
        super.bindView(view);
        initBottomMenu(view);
    }

    public int getDataCount() {
        return getItemCount();
    }

    public int getItemCount() {
        if (getAdapter() != null) {
            return getAdapter().getItemCount();
        }
        return 0;
    }

    public int getLayoutId() {
        return R.layout.fragment_my_query_layout;
    }

    public void initializeAdapter() {
        SearchMyQueryAdapter searchMyQueryAdapter = new SearchMyQueryAdapter(this, getLocationKey(), this.mPropertyHelper, true);
        this.mListAdapter = searchMyQueryAdapter;
        setFlexBoxListItemAdapter(searchMyQueryAdapter);
    }

    public boolean isDataPrepared() {
        if (getItemCount() > 0) {
            return true;
        }
        return false;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        updateBottomMargin(windowInsets);
        return windowInsets;
    }

    public boolean onBackPressed() {
        if (((SearchMyQueryPresenter) this.mPresenter).isConsumeBackPress() || super.onBackPressed()) {
            return true;
        }
        return false;
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
        updateSaveMenu();
    }

    public void removeItem(int i2) {
        Optional.ofNullable(getAdapter()).ifPresent(new J((Object) this, i2, 12));
    }

    public void updateSaveMenu() {
        if (this.mPresenter != null) {
            Optional.ofNullable(getSaveMenuItem()).ifPresent(new g(24, this));
        }
    }

    public SearchMyQueryPresenter createPresenter(IMyQueryCategoryView iMyQueryCategoryView) {
        return new SearchMyQueryPresenter(this.mBlackboard, iMyQueryCategoryView);
    }

    public SearchMyQueryAdapter getAdapter() {
        return (SearchMyQueryAdapter) this.mListAdapter;
    }
}
