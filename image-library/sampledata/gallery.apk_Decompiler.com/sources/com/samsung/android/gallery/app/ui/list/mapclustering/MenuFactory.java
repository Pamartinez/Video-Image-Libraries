package com.samsung.android.gallery.app.ui.list.mapclustering;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MenuFactory {
    private static MenuDataBinding addCommonBinding(MenuDataBinding menuDataBinding) {
        if (menuDataBinding == null) {
            return null;
        }
        MenuDataBinder.bindActionDownload(menuDataBinding);
        MenuDataBinder.bindActionCopyToAlbum(menuDataBinding);
        MenuDataBinder.bindActionMoveToAlbum(menuDataBinding);
        MenuDataBinder.bindActionCreate(menuDataBinding);
        MenuDataBinder.bindKnoxDataBinding(menuDataBinding);
        MenuDataBinder.bindActionAddTag(menuDataBinding);
        MenuDataBinder.bindSetAsWallpaper(menuDataBinding);
        MenuDataBinder.bindCopyPasteEffectBinding(menuDataBinding);
        MenuDataBinder.bindActionEditLocation(menuDataBinding);
        return menuDataBinding;
    }

    public static MenuDataBinding create(String str, String str2) {
        MenuDataBinding menuDataBinding;
        boolean supportBottomListMenu = supportBottomListMenu(str2);
        if (supportToolbarMenu(str)) {
            menuDataBinding = createMenuWithToolbar(supportBottomListMenu);
        } else if (supportBottomListMenu) {
            menuDataBinding = createBottomListMenu();
        } else {
            menuDataBinding = null;
        }
        if (supportBottomListMenu) {
            return addCommonBinding(menuDataBinding);
        }
        return menuDataBinding;
    }

    private static MenuDataBinding createBottomListMenu() {
        return new MenuDataBinding(R.menu.menu_bottom_list);
    }

    private static MenuDataBinding createMenuWithToolbar(boolean z) {
        int i2;
        if (z) {
            i2 = R.menu.menu_map_clustering_with_bottom_list;
        } else {
            i2 = R.menu.menu_map_clustering;
        }
        MenuDataBinding menuDataBinding = new MenuDataBinding(i2);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_select) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_slideshow) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        return menuDataBinding;
    }

    private static boolean supportBottomListMenu(String str) {
        return LocationKey.isMapCluster(str);
    }

    private static boolean supportToolbarMenu(String str) {
        if ((LocationKey.isMapMatch(str) || LocationKey.isMapFiltered(str) || LocationKey.isMapCluster(str)) && !LocationKey.isStoryPictures(str)) {
            return true;
        }
        return false;
    }
}
