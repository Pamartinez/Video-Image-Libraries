package com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.ICreatureCoverChoiceView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCoverChoicePresenter<V extends ICreatureCoverChoiceView> extends PicturesPresenter<V> {
    /* access modifiers changed from: private */
    public boolean mIsDoneEnabled;

    public CreatureCoverChoicePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public MenuDataBinding createMenuDataBinding() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_crop_cancel_done);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.menu_edit_app_bar_cancel) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.menu_edit_app_bar_done) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }

    public MenuHandler createMenuHandler() {
        return new MenuHandler() {
            public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
                return ((ICreatureCoverChoiceView) CreatureCoverChoicePresenter.this.mView).onMenuItemSelected(menuItem);
            }
        };
    }

    public int getDividerButtonHeight() {
        Context context = getContext();
        if (context != null) {
            return context.getResources().getDimensionPixelOffset(R.dimen.divider_button_layout_height);
        }
        return 0;
    }

    public void setDoneButtonEnabled(boolean z) {
        this.mIsDoneEnabled = z;
        ((ICreatureCoverChoiceView) this.mView).invalidateOptionsMenu();
    }

    public void updateToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.setTitle((int) R.string.select_face_to_show);
            toolbar.setSubtitle((CharSequence) null);
            setNavigationUpButton(toolbar);
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new ListMenuUpdater(v, this) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$updateOptionsMenuAction$0(MenuItem menuItem) {
                menuItem.setEnabled(CreatureCoverChoicePresenter.this.mIsDoneEnabled);
            }

            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                super.updateOptionsMenuAction(menu, selectionMode);
                boolean isLandscape = ((ICreatureCoverChoiceView) CreatureCoverChoicePresenter.this.mView).isLandscape();
                setMenuVisibility(menu, (int) R.id.menu_edit_app_bar_cancel, isLandscape);
                setMenuVisibility(menu, (int) R.id.menu_edit_app_bar_done, isLandscape);
                if (isLandscape) {
                    Optional.ofNullable(menu.findItem(R.id.menu_edit_app_bar_done)).ifPresent(new a(this));
                }
            }
        };
    }
}
