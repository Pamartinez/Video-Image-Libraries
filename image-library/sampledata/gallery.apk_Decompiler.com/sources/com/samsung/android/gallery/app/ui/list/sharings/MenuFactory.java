package com.samsung.android.gallery.app.ui.list.sharings;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MenuFactory {
    public static MenuDataBinding create(final Blackboard blackboard) {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_sharings);
        if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
            menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_sharings_invitations, false, true));
            menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_sharings_invitations_no_item, false, true));
        }
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_verizon_cloud) {
            public boolean getDefaultExcluding() {
                return true;
            }

            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_VERIZON_CLOUD);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_view_as) {
            public boolean getDefaultExcluding() {
                return true;
            }

            public boolean getDefaultVisibility() {
                return isDexMode(blackboard);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_search) {
            public boolean getDefaultExcluding() {
                return true;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }
}
