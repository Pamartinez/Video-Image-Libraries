package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuFactory {
    private static void bindActionCreate(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create_albums) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi5x.MX_ALBUMS;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_album_view_new_album) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return !PreferenceFeatures.OneUi5x.MX_ALBUMS;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_folder_add_folder) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return !PreferenceFeatures.OneUi5x.MX_ALBUMS;
            }
        });
    }

    public static MenuDataBinding create(Blackboard blackboard, String str) {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_albums);
        bindActionCreate(menuDataBinding);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_tianyi_cloud) {
            public boolean getDefaultExcluding() {
                if (isUpsm() || isKnox()) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_TIANYI_CLOUD);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_change_cover_image) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_move) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_folder_grouping) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_folder_ungrouping) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_manage_albums, false));
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_remove_from_essential_albums, false));
        MenuDataBinder.bindActionShareAlbums(menuDataBinding);
        MenuDataBinder.bindActionVerizonCloud(menuDataBinding);
        MenuDataBinder.bindActionViewAs(menuDataBinding, blackboard);
        MenuDataBinder.bindKnoxDataBinding(menuDataBinding);
        MenuDataBinder.bindActionSearch(menuDataBinding);
        MenuDataBinder.bindActionHideAlbums(menuDataBinding);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_auto_grouping) {
            public boolean getDefaultVisibility() {
                return PocFeatures.isEnabled(PocFeatures.AlbumAutoGroup);
            }
        });
        return menuDataBinding;
    }

    public static MenuDataBinding createMenuForAlbumChoice() {
        return new MenuDataBinding(R.menu.menu_albums_choice);
    }

    public static MenuDataBinding createMenuForFirstEntry() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_albums_first);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create_albums) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi5x.MX_ALBUMS;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_album_view_new_album) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return !PreferenceFeatures.OneUi5x.MX_ALBUMS;
            }
        });
        MenuDataBinder.bindActionSearch(menuDataBinding);
        return menuDataBinding;
    }
}
