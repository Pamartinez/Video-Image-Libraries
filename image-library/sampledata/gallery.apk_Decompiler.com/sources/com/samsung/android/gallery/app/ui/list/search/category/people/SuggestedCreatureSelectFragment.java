package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.oneui.dividerbuttonlayout.DividerButtonLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ISuggestedCreatureSelectView;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.scs.ai.language.a;
import com.sec.android.gallery3d.R;
import java.util.List;
import java.util.Optional;
import n5.e;
import n5.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedCreatureSelectFragment<V extends ISuggestedCreatureSelectView, P extends SuggestedCreatureSelectPresenter<V>> extends CreatureSelectFragment<V, P> implements ISuggestedCreatureSelectView {
    private DividerButtonLayout mDividerButtonLayout;
    private FloatingBottomLayout mDividerButtonLayoutContainer;
    private ISuggestedCreatureHeaderView mHeaderViewDelegate;

    public SuggestedCreatureSelectFragment() {
        createHeaderView();
    }

    private void createHeaderView() {
        this.mHeaderViewDelegate = new SuggestedCreatureHeaderView(this);
    }

    private GridLayoutManager.SpanSizeLookup createSpanSizeLookUp(GridLayoutManager gridLayoutManager) {
        return new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                if (SuggestedCreatureSelectFragment.this.getAdapter() != null) {
                    return SuggestedCreatureSelectFragment.this.getAdapter().getStartSpan(i2, i7);
                }
                return 0;
            }

            public int getSpanSize(int i2) {
                if (SuggestedCreatureSelectFragment.this.getAdapter() != null) {
                    return SuggestedCreatureSelectFragment.this.getAdapter().getSpanSize(i2);
                }
                return 1;
            }
        };
    }

    private Menu getBottomMenu() {
        DividerButtonLayout dividerButtonLayout = this.mDividerButtonLayout;
        if (dividerButtonLayout != null) {
            return dividerButtonLayout.getMenu();
        }
        return null;
    }

    private MenuItem getSetAsRelationMenuItem() {
        return (MenuItem) Optional.ofNullable(getBottomMenu()).map(new e(3)).orElse((Object) null);
    }

    private void initBottomMenu(View view) {
        this.mDividerButtonLayoutContainer = (FloatingBottomLayout) view.findViewById(R.id.divider_button_floating_container);
        DividerButtonLayout dividerButtonLayout = (DividerButtonLayout) view.findViewById(R.id.divider_button_layout);
        this.mDividerButtonLayout = dividerButtonLayout;
        dividerButtonLayout.setOnMenuItemClickListener(new a(25, this));
        this.mDividerButtonLayout.c(R.menu.menu_search_category_suggested_creature_select);
        if (isLandscape()) {
            ViewUtils.setVisibleOrGone(this.mDividerButtonLayoutContainer, false);
        } else {
            SimpleAnimator.setVisibilityAni(this.mDividerButtonLayoutContainer, 0);
        }
        updateBottomMenuVisibility(getBottomMenu());
        updateSetAsRelationMenu();
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

    private void initHeaderView() {
        this.mHeaderViewDelegate.initView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateBottomMenuVisibility$0(MenuItem menuItem) {
        menuItem.setVisible(!((SuggestedCreatureSelectPresenter) this.mPresenter).isFromStoriesCategory());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSetAsRelationMenu$1(MenuItem menuItem) {
        boolean z;
        int i2;
        if (getSelectedCreatures().size() > 0) {
            z = true;
        } else {
            z = false;
        }
        setEnableMenuItem(menuItem, z);
        if (((SuggestedCreatureSelectPresenter) this.mPresenter).getRelationShipRecommendMapSize() > 1) {
            i2 = R.string.next;
        } else {
            i2 = R.string.done;
        }
        menuItem.setTitle(i2);
    }

    private void notifyPickerCompleted(boolean z) {
        if (ArgumentsUtil.getArgValue(getLocationKey(), "fromRelationshipPreview", false)) {
            this.mBlackboard.postBroadcastEvent(EventMessage.obtain(8015, Boolean.FALSE));
            if (z) {
                finish();
                return;
            }
            return;
        }
        P p6 = this.mPresenter;
        if (p6 == null) {
            return;
        }
        if (z) {
            ((SuggestedCreatureSelectPresenter) p6).skipCurrentRelationship();
        } else if (((SuggestedCreatureSelectPresenter) p6).isAnyoneTagged()) {
            ((SuggestedCreatureSelectPresenter) this.mPresenter).sendBroadcastEvent();
        }
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

    private void updateBottomMenuVisibility(Menu menu) {
        if (menu != null && this.mPresenter != null) {
            Optional.ofNullable(menu.findItem(R.id.action_later)).ifPresent(new h(this, 1));
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        initBottomMenu(view);
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), getMaxColumnSize());
        gridLayoutManager.setSpanSizeLookup(createSpanSizeLookUp(gridLayoutManager));
        return gridLayoutManager;
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SuggestedCreatureSelectItemAdapter(this, getLocationKey(), this.mHeaderViewDelegate.get(), galleryListView, this.mPropertyHelper, true);
    }

    public MediaItem[] getHeaderItems() {
        return this.mHeaderViewDelegate.getAllItems();
    }

    public int getHeaderListColumnSize() {
        return getMaxColumnSize();
    }

    public int getLayoutId() {
        return R.layout.fragment_suggested_creature_select_layout;
    }

    public MediaData getMediaData(String str) {
        String removeArgs = ArgumentsUtil.removeArgs(str);
        if (LocationKey.isSearchCategoryPeopleSelectForRelation(removeArgs)) {
            return this.mMediaData;
        }
        return this.mMediaData.getChildMediaData(removeArgs);
    }

    public void initView(View view) {
        super.initView(view);
        initHeaderView();
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        updateBottomMargin(windowInsets);
        return windowInsets;
    }

    public boolean onBackPressed() {
        boolean onBackPressed = super.onBackPressed();
        if (!onBackPressed) {
            notifyPickerCompleted(false);
        }
        return onBackPressed;
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
        this.mHeaderViewDelegate.onDataChangedOnUI();
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (listViewHolder.getViewType() == 11) {
            P p6 = this.mPresenter;
            if (p6 != null) {
                ((SuggestedCreatureSelectPresenter) p6).onListHeaderItemClick(mediaItem);
            }
            this.mHeaderViewDelegate.onListItemClick(i2);
        } else {
            super.onListItemClick(listViewHolder, i2, mediaItem, i7);
        }
        updateSetAsRelationMenu();
    }

    public boolean onMenuItemSelected(MenuItem menuItem) {
        if (this.mPresenter == null || menuItem.getItemId() == R.id.action_later) {
            notifyPickerCompleted(true);
        } else if (menuItem.getItemId() == R.id.action_set_as_relation && menuItem.isEnabled()) {
            ((SuggestedCreatureSelectPresenter) this.mPresenter).selectCreatures();
        }
        return true;
    }

    public void setLocationKey(String str) {
        super.setLocationKey(str);
    }

    public void updateListColumn() {
        super.updateListColumn();
        this.mHeaderViewDelegate.updateListColumn();
    }

    public void updateSetAsRelationMenu() {
        if (this.mPresenter != null) {
            Optional.ofNullable(getSetAsRelationMenuItem()).ifPresent(new h(this, 0));
        }
    }

    public SuggestedCreatureSelectPresenter createPresenter(ISuggestedCreatureSelectView iSuggestedCreatureSelectView) {
        return new SuggestedCreatureSelectPresenter(this.mBlackboard, iSuggestedCreatureSelectView);
    }

    public SuggestedCreatureSelectItemAdapter getAdapter() {
        return (SuggestedCreatureSelectItemAdapter) super.getAdapter();
    }
}
