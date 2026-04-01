package com.samsung.android.gallery.app.ui.list.stories.abstraction;

import O3.b;
import android.graphics.PointF;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.PopupMenu;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuFactory {
    public static MenuDataBinding create() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_stories);
        MenuDataBinder.bindActionVerizonCloud(menuDataBinding);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create_story_album) {
            public boolean getDefaultExcluding() {
                return !Features.isEnabled(Features.SUPPORT_STORY);
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_pin) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_hide_content) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi40.SUPPORT_STORIES_HIDE_RULE;
            }
        });
        return menuDataBinding;
    }

    public static MenuDataBinding createLegacy() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_stories_legacy);
        MenuDataBinder.bindActionVerizonCloud(menuDataBinding);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create_story_album) {
            public boolean getDefaultExcluding() {
                return !Features.isEnabled(Features.SUPPORT_STORY);
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }

    public static MenuDataBinding createPopupMenu() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_stories);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_share_cover) {
            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_MEMORY_COVER_SAVE);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_save_cover) {
            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_MEMORY_COVER_SAVE);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_rename) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_delete_story) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createPopupMenu$0(Menu menu, Blackboard blackboard, PopupMenu popupMenu) {
        menu.setGroupVisible(R.id.popup_mode, false);
        blackboard.publish("data://floating_pop_menu", Boolean.FALSE);
        blackboard.erase("data://focused_item");
    }

    private static void setMenuItemVisibility(MenuDataBinding menuDataBinding, MenuItem menuItem) {
        if (menuItem != null) {
            menuItem.setVisible(menuDataBinding.isItemVisible(menuItem.getItemId()));
        }
    }

    private static void updateMenuOperations(MenuDataBinding menuDataBinding, Menu menu) {
        menuDataBinding.setMenu(menu);
        menuDataBinding.prepareOptionsMenu(menu);
        setMenuItemVisibility(menuDataBinding, menu.findItem(R.id.action_share_cover));
        setMenuItemVisibility(menuDataBinding, menu.findItem(R.id.action_save_cover));
        setMenuItemVisibility(menuDataBinding, menu.findItem(R.id.action_rename));
        setMenuItemVisibility(menuDataBinding, menu.findItem(R.id.action_delete_story));
    }

    public static PopupMenu createPopupMenu(Blackboard blackboard, View view, PointF pointF, MediaItem mediaItem, int i2, MenuDataBinding menuDataBinding, PopupMenu.OnMenuItemClickListener onMenuItemClickListener) {
        if (view == null) {
            return null;
        }
        View view2 = view;
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view2, 8388613, 0, R.style.PopupMenuDropItemStyle);
        Menu menu = popupMenu.getMenu();
        popupMenu.getMenuInflater().inflate(i2, menu);
        menu.removeGroup(R.id.normal_mode);
        if (!PreferenceFeatures.OneUi40.SUPPORT_MEMORY_COVER_ACTION_ON_SELECTION_MODE) {
            menu.removeGroup(R.id.select_mode);
        }
        menu.removeGroup(R.id.select_mode_bottom);
        menu.removeGroup(R.id.select_mode_with_done);
        menu.removeGroup(R.id.no_item);
        menu.setGroupVisible(R.id.popup_mode, true);
        popupMenu.setOnMenuItemClickListener(onMenuItemClickListener);
        popupMenu.setOnDismissListener(new b(12, menu, blackboard));
        blackboard.publish("data://floating_pop_menu", Boolean.TRUE);
        blackboard.publish("data://focused_item", mediaItem);
        updateMenuOperations(menuDataBinding, popupMenu.getMenu());
        popupMenu.seslSetOffset(view2.getWidth() / 3, 0);
        return popupMenu;
    }
}
