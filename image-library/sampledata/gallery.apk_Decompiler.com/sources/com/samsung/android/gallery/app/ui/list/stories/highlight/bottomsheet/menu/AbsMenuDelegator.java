package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.menu;

import android.content.res.ColorStateList;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.abstraction.Mode;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.abstraction.SelectInfoSupplier;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.bottom.BottomBar;
import com.samsung.android.gallery.widget.bottom.BottomBarData;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import m6.C0487a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsMenuDelegator extends ListMenuUpdater {
    protected final String TAG = getClass().getSimpleName();
    private ColorStateList mIconColor;
    protected Menu mMenu;
    protected final MenuDataBinding mMenuDataBinding;
    protected final MenuHandler mMenuHandler;
    protected Mode mMenuMode;
    protected Mode mMode = Mode.CURATION;
    private boolean mNeedRestore;
    protected SelectInfoSupplier mSelectInfoSupplier;
    private ColorStateList mTextColor;
    protected Toolbar mToolbar;

    public AbsMenuDelegator(IBaseListView iBaseListView, ListMenuUpdater.IMenuDelegation iMenuDelegation) {
        super(iBaseListView, iMenuDelegation);
        this.mMenuDataBinding = createMenuDataBinding(iBaseListView);
        this.mMenuHandler = new StoryHighlightListV2MenuHandler();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getBottomBarInitListener$1(String str, BottomBar bottomBar) {
        if (LocationKey.isStoryHighlight(str)) {
            saveDefaultColor(bottomBar);
            setBottomBar(bottomBar);
            updateMenuEnableState(bottomBar);
            this.mNeedRestore = true;
            Log.d(this.TAG, "customize bottomBar");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setToolbar$0(MenuItem menuItem) {
        return this.mMenuHandler.onOptionsItemSelected((EventContext) this.mInterface, menuItem);
    }

    private void saveDefaultColor(BottomBar bottomBar) {
        if (this.mIconColor == null || this.mTextColor == null) {
            this.mIconColor = bottomBar.getItemIconTintList();
            this.mTextColor = bottomBar.getItemTextColor();
        }
    }

    private void setButtonShape(BottomBar bottomBar) {
        ViewGroup viewGroup = (ViewGroup) bottomBar.getMenuView();
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            SeApiCompat.setButtonShapeEnabled((TextView) viewGroup.getChildAt(i2).findViewById(R.id.navigation_bar_item_small_label_view));
        }
    }

    private void updateMenuEnableState(BottomBar bottomBar) {
        SelectInfoSupplier selectInfoSupplier = getSelectInfoSupplier();
        if (Mode.REMOVE.equals(this.mMode) && selectInfoSupplier != null && selectInfoSupplier.isSelectedEntireShowItems()) {
            ViewUtils.setViewEnabled(bottomBar.findViewById(R.id.action_remove_from_story_in_list), false);
        }
    }

    public abstract MenuDataBinding createMenuDataBinding(IBaseListView iBaseListView);

    public BottomBarData.InitListener getBottomBarInitListener() {
        return new C0487a(this);
    }

    public MenuDataBinding getMenuDataBinding() {
        return this.mMenuDataBinding;
    }

    public MenuHandler getMenuHandler() {
        return this.mMenuHandler;
    }

    public SelectInfoSupplier getSelectInfoSupplier() {
        if (this.mSelectInfoSupplier == null) {
            this.mSelectInfoSupplier = (SelectInfoSupplier) getView().getAdapter();
        }
        return this.mSelectInfoSupplier;
    }

    public void hideBottomSelectionMenu(Menu menu) {
        for (int i2 = 0; i2 < menu.size(); i2++) {
            MenuItem item = menu.getItem(i2);
            if (item.getGroupId() == R.id.select_mode_bottom) {
                item.setVisible(false);
            }
        }
    }

    public void invalidateOptionMenu() {
        invalidateOptionMenu(this.mMode);
    }

    public abstract void invalidateOptionMenu(Mode mode);

    public void resetBottomBar(BottomBar bottomBar) {
        if (bottomBar == null) {
            Log.e(this.TAG, "no bottomBar");
        } else if (this.mNeedRestore) {
            bottomBar.setItemIconTintList(this.mIconColor);
            bottomBar.setItemTextColor(this.mTextColor);
            boolean z = true;
            bottomBar.setAnimationType(true);
            bottomBar.setAlpha(1.0f);
            this.mNeedRestore = false;
            String str = this.TAG;
            if (this.mIconColor == null) {
                z = false;
            }
            Log.d(str, "restore bottomBar", Boolean.valueOf(z), this.mTextColor);
        }
    }

    public void setBottomBar(BottomBar bottomBar) {
        bottomBar.setAnimationType(false);
        setButtonShape(bottomBar);
    }

    public void setMode(Mode mode) {
        this.mMode = mode;
    }

    public void setToolbar(Toolbar toolbar) {
        this.mToolbar = toolbar;
        this.mMenu = toolbar.getMenu();
        this.mToolbar.setOnMenuItemClickListener(new C0487a(this));
    }

    public void updateDoneMenu(Mode mode) {
        MenuItem findItem;
        boolean z;
        if (Mode.EDIT_CURATION.equals(mode) && (findItem = this.mMenu.findItem(R.id.action_done)) != null) {
            if (getView().getSelectedItemCount() > 0) {
                z = true;
            } else {
                z = false;
            }
            if (findItem.isEnabled() != z) {
                findItem.setEnabled(z);
                updateViewAlpha(R.id.action_done, z);
            }
        }
    }

    public void updateOptionsMenu(Menu menu, EventContext.OnSelectionListener onSelectionListener) {
        Log.d(this.TAG, "updateOptionsMenu");
    }

    public void updateViewAlpha(int i2, boolean z) {
        ViewUtils.setViewEnabled(this.mToolbar.findViewById(i2), z);
    }
}
