package com.samsung.android.gallery.app.ui.list.albums.mx;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuFactory {
    public static MenuDataBinding create(final Blackboard blackboard, String str) {
        final boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums);
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_mx_albums);
        MenuDataBinder.bindActionUsbOtg(menuDataBinding);
        MenuDataBinder.bindActionSearch(menuDataBinding);
        MenuDataBinder.bindActionTrash(menuDataBinding);
        MenuDataBinder.bindActionSettings(menuDataBinding);
        setDisable(menuDataBinding, R.id.action_view_as);
        setDisable(menuDataBinding, R.id.action_album_view_new_album);
        setDisable(menuDataBinding, R.id.action_folder_add_folder);
        setDisable(menuDataBinding, R.id.action_delete_shared_album_in_list);
        setDisable(menuDataBinding, R.id.action_leave_shared_album_in_list);
        setDisable(menuDataBinding, R.id.action_rename_shared_album_in_list);
        setDisableForEssentialAndUpsm(menuDataBinding, R.id.action_sortby_album);
        setDisableForEssentialAndUpsm(menuDataBinding, R.id.action_change_cover_image);
        setDisableForEssentialAndUpsm(menuDataBinding, R.id.action_folder_grouping);
        setDisableForEssentialAndUpsm(menuDataBinding, R.id.action_folder_ungrouping);
        setDisableForEssentialAndUpsm(menuDataBinding, R.id.action_hide_album);
        setDisableForEssentialAndUpsm(menuDataBinding, R.id.action_share_album);
        setDisableForEssentialAndUpsm(menuDataBinding, R.id.action_rename_album_folder);
        setDisableForEssentialAndUpsm(menuDataBinding, R.id.action_delete_album_in_list);
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_select, true));
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create_albums) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi5x.MX_ALBUMS;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_manage_albums) {
            public boolean getDefaultExcluding() {
                if (isUpsm() || !DrawerUtil.supportEssentialAlbumsLayout(blackboard)) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return isEnabled;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove_from_essential_albums) {
            public boolean getDefaultExcluding() {
                if (isUpsm() || !DrawerUtil.supportEssentialAlbumsLayout(blackboard)) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return isEnabled;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_tianyi_cloud) {
            public boolean getDefaultExcluding() {
                if (isUpsm() || isKnox() || isEnabled) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_TIANYI_CLOUD);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_verizon_cloud) {
            public boolean getDefaultExcluding() {
                if (isKnox() || isAfw() || isEnabled) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_VERIZON_CLOUD);
            }
        });
        MenuDataBinder.bindKnoxDataBinding(menuDataBinding);
        MenuDataBinder.bindActionHideAlbums(menuDataBinding);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_delete_duplicate_pictures) {
            public boolean getDefaultVisibility() {
                if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_85 || !Features.isEnabled(Features.SUPPORT_SUGGESTIONS)) {
                    return false;
                }
                return true;
            }
        });
        return menuDataBinding;
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
        MenuDataBinder.bindActionSearch(menuDataBinding);
        return menuDataBinding;
    }

    private static void setDisable(MenuDataBinding menuDataBinding, int i2) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(i2) {
            public boolean getDefaultExcluding() {
                return true;
            }
        });
    }

    private static void setDisableForEssentialAndUpsm(MenuDataBinding menuDataBinding, int i2) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(i2) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return !PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums);
            }
        });
    }
}
