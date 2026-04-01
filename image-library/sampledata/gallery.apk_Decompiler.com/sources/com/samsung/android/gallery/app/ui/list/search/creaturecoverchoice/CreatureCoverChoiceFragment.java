package com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice;

import A4.C0381p;
import Bb.l;
import E7.c;
import Fa.K;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.google.android.material.oneui.dividerbuttonlayout.DividerButtonLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.CreatureCoverChoicePresenter;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.ICreatureCoverChoiceView;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import h.C0199b;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import o6.t;
import p5.C0503a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCoverChoiceFragment<V extends ICreatureCoverChoiceView, P extends CreatureCoverChoicePresenter> extends PicturesFragment<V, P> implements ICreatureCoverChoiceView {
    private AppCompatRadioButton mAutoSelect;
    private String mAutoSelectCoverFaceGroupId;
    private String mAutoSelectCoverId;
    private String mCurrentCoverFaceGroupId;
    private String mCurrentCoverId;
    private DividerButtonLayout mDividerButtonLayout;
    private FloatingBottomLayout mDividerButtonLayoutContainer;

    private View createHeaderView() {
        View inflate = getLayoutInflater().inflate(R.layout.creature_cover_choice_header_layout, getListView(), false);
        initAutoSelect(inflate);
        return inflate;
    }

    private String[] getAutoSelectCoverData() {
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "sub", (String) null);
        if (IdentityCreatureUtil.isPerson(argValue)) {
            return PeopleDataManager.getAutoSelectCoverData(argValue);
        }
        return PetDataManager.getAutoSelectCoverData(argValue);
    }

    private Menu getBottomMenu() {
        DividerButtonLayout dividerButtonLayout = this.mDividerButtonLayout;
        if (dividerButtonLayout != null) {
            return dividerButtonLayout.getMenu();
        }
        return null;
    }

    private void initAutoSelect(View view) {
        this.mAutoSelect = (AppCompatRadioButton) view.findViewById(R.id.auto_select);
        this.mCurrentCoverId = ArgumentsUtil.getArgValue(getLocationKey(), "searchCurrentCoverItem", (String) null);
        this.mCurrentCoverFaceGroupId = ArgumentsUtil.getArgValue(getLocationKey(), "searchCurrentCoverFaceGroupId", (String) null);
        if (ArgumentsUtil.getArgValue(getLocationKey(), "searchCurrentCoverItemIsCustom", false)) {
            ThreadUtil.postOnBgThread(new t(6, this));
            updateAutoSelect(false);
        } else {
            this.mAutoSelectCoverId = this.mCurrentCoverId;
            this.mAutoSelectCoverFaceGroupId = this.mCurrentCoverFaceGroupId;
            updateAutoSelect(true);
        }
        this.mAutoSelect.setOnCheckedChangeListener(new K(4, this));
    }

    private void initBottomMenu(View view) {
        this.mDividerButtonLayoutContainer = (FloatingBottomLayout) view.findViewById(R.id.divider_button_floating_container);
        DividerButtonLayout dividerButtonLayout = (DividerButtonLayout) view.findViewById(R.id.divider_button_layout);
        this.mDividerButtonLayout = dividerButtonLayout;
        dividerButtonLayout.setOnMenuItemClickListener(new C0503a(this));
        this.mDividerButtonLayout.c(R.menu.menu_crop_cancel_done);
        ViewUtils.setVisibleOrGone(this.mDividerButtonLayoutContainer, !isLandscape());
        setDoneButtonEnabled(false);
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

    private boolean isCustomCover() {
        return !this.mAutoSelect.isChecked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAutoSelect$2() {
        String[] autoSelectCoverData = getAutoSelectCoverData();
        this.mAutoSelectCoverId = autoSelectCoverData[0];
        this.mAutoSelectCoverFaceGroupId = autoSelectCoverData[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAutoSelect$3(CompoundButton compoundButton, boolean z) {
        if (this.mAutoSelect.isChecked()) {
            setDoneButtonEnabled(true);
            setCurrentCoverId(this.mAutoSelectCoverId);
            setCurrentCoverFaceGroupId(this.mAutoSelectCoverFaceGroupId);
            PicturesViewAdapter adapter = getAdapter();
            Objects.requireNonNull(adapter);
            adapter.notifyItemRangeChanged("update_cue");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaItem lambda$updateEditCreatureNameHeader$4(ThreadPool.JobContext jobContext) {
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "sub", (String) null);
        if (IdentityCreatureUtil.isPerson(argValue)) {
            return PeopleDataManager.loadHeaderItem(argValue, getCurrentCoverId(), Long.parseLong(getCurrentCoverFaceGroupId()));
        }
        return PetDataManager.loadHeaderItem(argValue, getCurrentCoverId(), Long.parseLong(getCurrentCoverFaceGroupId()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateEditCreatureNameHeader$5(Future future) {
        getBlackboard().post("command://event/UpdateCreatureNameDialogHeader", new Object[]{future.get(), Boolean.valueOf(isCustomCover())});
    }

    /* access modifiers changed from: private */
    /* renamed from: setEnableMenuItem */
    public void lambda$setDoneButtonEnabled$0(MenuItem menuItem, boolean z) {
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

    private void updateAutoSelect(boolean z) {
        this.mAutoSelect.setChecked(z);
    }

    /* access modifiers changed from: private */
    /* renamed from: updateBottomMargin */
    public void lambda$onApplyWindowInsets$1(WindowInsets windowInsets) {
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

    private void updateEditCreatureNameHeader() {
        ThreadPool.getInstance().submit(new C0381p(12, this), new C0503a(this));
    }

    public void adjustContentAreaMargin(View view, WindowInsets windowInsets, boolean z) {
        super.adjustContentAreaMargin(view, windowInsets, z);
        ViewMarginUtils.setBottomMargin(view, 0);
    }

    public void bindView(View view) {
        super.bindView(view);
        Optional.ofNullable(this.mAppBarLayout).ifPresent(new l(17));
        initBottomMenu(view);
    }

    public String getCurrentCoverFaceGroupId() {
        return this.mCurrentCoverFaceGroupId;
    }

    public String getCurrentCoverId() {
        return this.mCurrentCoverId;
    }

    public int getDefaultSidePadding() {
        return getResources().getDimensionPixelOffset(R.dimen.creature_cover_choice_default_side_spacing);
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new PicturesViewDummyAdapter(getListView(), getColumnCount()) {
            public PicturesViewHolderFactory createViewHolderFactory(Context context) {
                return new CreatureCoverChoiceViewHolderFactory(context);
            }
        };
    }

    public int getLayoutId() {
        return R.layout.fragment_creature_cover_choice_layout;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        view.post(new C0199b(20, this, windowInsets));
        return windowInsets;
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

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        setDoneButtonEnabled(true);
        updateAutoSelect(false);
    }

    public boolean onMenuItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_edit_app_bar_done) {
            updateEditCreatureNameHeader();
        }
        finish();
        return true;
    }

    public void setCurrentCoverFaceGroupId(String str) {
        this.mCurrentCoverFaceGroupId = str;
    }

    public void setCurrentCoverId(String str) {
        this.mCurrentCoverId = str;
    }

    public void setDoneButtonEnabled(boolean z) {
        Menu bottomMenu = getBottomMenu();
        if (bottomMenu != null) {
            Optional.ofNullable(bottomMenu.findItem(R.id.menu_edit_app_bar_done)).ifPresent(new c(this, z, 18));
        }
        ((CreatureCoverChoicePresenter) this.mPresenter).setDoneButtonEnabled(z);
    }

    public boolean supportSelection() {
        return false;
    }

    public PicturesLayoutManager createLayoutManager() {
        AnonymousClass2 r0 = new PicturesLayoutManager(getContext(), getColumnCount()) {
            public int getSpacing(int i2) {
                return CreatureCoverChoiceFragment.this.getGridSpacing(i2);
            }

            public void updateViewSize(View view, int i2, int i7) {
                if (ViewHolderValue.isData(i2)) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    int hintWidthSpace = getHintWidthSpace(i7) / getRealGridSize(i7);
                    if (hintWidthSpace != layoutParams.width || hintWidthSpace != layoutParams.height) {
                        layoutParams.width = hintWidthSpace;
                        layoutParams.height = -2;
                        view.setLayoutParams(layoutParams);
                        return;
                    }
                    return;
                }
                super.updateViewSize(view, i2, i7);
            }
        };
        r0.setSpanSizeLookup(createSpanSizeLookUp(r0));
        return r0;
    }

    public PicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new CreatureCoverChoiceAdapter(this, getLocationKey(), createHeaderView(), false);
    }

    public CreatureCoverChoicePresenter createPresenter(ICreatureCoverChoiceView iCreatureCoverChoiceView) {
        return new CreatureCoverChoicePresenter(this.mBlackboard, iCreatureCoverChoiceView);
    }
}
