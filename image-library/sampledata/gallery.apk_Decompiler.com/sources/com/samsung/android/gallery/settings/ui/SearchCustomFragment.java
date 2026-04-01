package com.samsung.android.gallery.settings.ui;

import Fa.J;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.settings.R$dimen;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$menu;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.helper.SettingLayoutUtils;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseActivity;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchCustomFragment extends BaseFragment {
    private final IBaseActivity mActivity;
    private SearchCustomViewAdapter mAdapter;
    private View mMainLayout;
    /* access modifiers changed from: private */
    public Menu mMenu;
    private final MenuProvider mMenuProvider = new MenuProvider() {
        public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
            menu.clear();
            menuInflater.inflate(R$menu.menu_search_customize, menu);
            SearchCustomFragment.this.mMenu = menu;
        }

        public boolean onMenuItemSelected(MenuItem menuItem) {
            if (menuItem.getItemId() != R$id.search_customize_reorder) {
                return true;
            }
            SearchCustomFragment.this.startReorder();
            return true;
        }
    };
    protected RecyclerView mRecyclerView;

    public SearchCustomFragment(IBaseActivity iBaseActivity) {
        this.mActivity = iBaseActivity;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$0(FragmentActivity fragmentActivity) {
        fragmentActivity.addMenuProvider(this.mMenuProvider, getViewLifecycleOwner());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMainLayoutPaddingHorizontal$1(FragmentActivity fragmentActivity) {
        ViewMarginUtils.setHorizontalPadding(this.mMainLayout, SettingLayoutUtils.getPreferencePadding(fragmentActivity));
    }

    /* access modifiers changed from: private */
    public void startReorder() {
        this.mActivity.setBackPressedCallbackEnabled(true);
        this.mAdapter.startReorder();
        this.mMenu.setGroupVisible(R$id.normal_mode, false);
    }

    private void updateMainLayoutPaddingHorizontal() {
        Optional.ofNullable(getActivity()).ifPresent(new J(this, 0));
    }

    public void addDividerToRecyclerView() {
        this.mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));
    }

    public void bindViews(ViewGroup viewGroup) {
        this.mMainLayout = viewGroup.findViewById(R$id.main_layout);
        this.mRecyclerView = (RecyclerView) viewGroup.findViewById(R$id.recycler_view);
        FloatingToolbarLayout floatingToolbarLayout = getFloatingToolbarLayout();
        if (floatingToolbarLayout != null) {
            floatingToolbarLayout.setRecyclerView(this.mRecyclerView);
        }
        ViewUtils.setViewShape(this.mRecyclerView, 1, (float) viewGroup.getContext().getResources().getDimensionPixelOffset(R$dimen.search_customize_setting_item_radius));
        recyclerViewInit();
        addDividerToRecyclerView();
        updateMainLayoutPaddingHorizontal();
    }

    public SearchCustomViewAdapter createListAdapter() {
        return new SearchCustomViewAdapter(VisualSearchCategory.listOf());
    }

    public void exitReorderMode() {
        this.mActivity.setBackPressedCallbackEnabled(false);
        this.mAdapter.exitReorder();
        this.mMenu.setGroupVisible(R$id.normal_mode, true);
    }

    public int getLayoutId() {
        return R$layout.fragment_search_customize_layout;
    }

    public int getTitleId() {
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            return R$string.edit_collections;
        }
        return R$string.search_categories;
    }

    public boolean isReorderMode() {
        return this.mAdapter.isReorderMode();
    }

    public boolean onBackPressed() {
        if (!isReorderMode()) {
            return false;
        }
        exitReorderMode();
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateMainLayoutPaddingHorizontal();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (supportMenu()) {
            Optional.ofNullable(getActivity()).ifPresent(new J(this, 1));
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void recyclerViewInit() {
        SearchCustomViewAdapter createListAdapter = createListAdapter();
        this.mAdapter = createListAdapter;
        this.mRecyclerView.setAdapter(createListAdapter);
        this.mAdapter.attachTouchHelper(this.mRecyclerView);
    }

    public boolean supportMenu() {
        return true;
    }
}
