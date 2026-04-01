package com.samsung.android.gallery.app.ui.list.albums.choice;

import A4.O;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import com.google.android.material.oneui.dividerbuttonlayout.DividerButtonLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumChoicePresenter;
import com.samsung.android.gallery.app.ui.list.albums.choice.IAlbumChoiceView;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBaseFragment;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumChoiceFragment<V extends IAlbumChoiceView, P extends AlbumChoicePresenter<IAlbumChoiceView>> extends AlbumChoiceBaseFragment<V, P> implements IAlbumChoiceView {
    private DividerButtonLayout mDividerButtonLayout;
    private FloatingBottomLayout mDividerButtonLayoutContainer;
    private String mType;

    private void initFloatingLayout() {
        FloatingBottomLayout floatingBottomLayout = this.mDividerButtonLayoutContainer;
        if (floatingBottomLayout != null && this.mDividerButtonLayout != null) {
            floatingBottomLayout.m(true, false);
            this.mDividerButtonLayoutContainer.a(List.of(this.mDividerButtonLayout));
            this.mDividerButtonLayoutContainer.setRecyclerView(this.mRecyclerView);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onBindView$0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_cancel) {
            return true;
        }
        onCancelClicked();
        return true;
    }

    private void onCancelClicked() {
        ((AlbumChoicePresenter) this.mPresenter).exitChoiceFragment();
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mDividerButtonLayoutContainer = (FloatingBottomLayout) view.findViewById(R.id.divider_button_floating_container);
        DividerButtonLayout dividerButtonLayout = (DividerButtonLayout) view.findViewById(R.id.divider_button_layout);
        this.mDividerButtonLayout = dividerButtonLayout;
        if (dividerButtonLayout != null) {
            dividerButtonLayout.c(R.menu.menu_cancel_bottom_bar);
        }
        initFloatingLayout();
    }

    public AlbumsViewDummyAdapter getDummyAdapter() {
        return new AlbumChoiceDummyAdapter(getListView());
    }

    public int getLayoutId() {
        return R.layout.album_choice_fragment_layout;
    }

    public String getScreenId() {
        if ("copy".equals(this.mType)) {
            return AnalyticsScreenId.SCREEN_COPY_TO_ALBUM.toString();
        }
        return AnalyticsScreenId.SCREEN_MOVE_TO_ALBUM.toString();
    }

    public boolean isMoveBarMode() {
        return super.isMoveMode();
    }

    public boolean isMoveMode() {
        return false;
    }

    public void loadValues() {
        this.mType = ArgumentsUtil.getArgValue(getLocationKey(), "type", "title");
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        ViewMarginUtils.setBottomMargin(this.mDividerButtonLayoutContainer, WindowUtils.getSystemInsetsBottom(windowInsets));
        return windowInsets;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        if (isMoveBarMode()) {
            onEnterMoveMode();
            ViewUtils.setVisibility(this.mDividerButtonLayoutContainer, 8);
            return;
        }
        DividerButtonLayout dividerButtonLayout = this.mDividerButtonLayout;
        if (dividerButtonLayout != null) {
            dividerButtonLayout.setOnMenuItemClickListener(new O(18, this));
        }
    }

    public void onCanceled() {
        onCancelClicked();
    }

    public void onExitMoveMode() {
        if (isMoveBarMode()) {
            ((AlbumChoicePresenter) this.mPresenter).onExitMoveMode();
        } else {
            super.onExitMoveMode();
        }
    }

    public void onInitMoveMode() {
        if (!isMoveBarMode()) {
            super.onInitMoveMode();
        }
    }

    public AlbumChoicePresenter<?> createPresenter(IAlbumChoiceView iAlbumChoiceView) {
        return new AlbumChoicePresenter<>(this.mBlackboard, iAlbumChoiceView);
    }

    public AlbumChoiceAdapter<?> createListViewAdapter(GalleryListView galleryListView) {
        return new AlbumChoiceAdapter<>(this, getLocationKey());
    }
}
