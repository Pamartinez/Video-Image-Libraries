package com.samsung.android.gallery.app.ui.list.stories.recap;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuFactory {
    public static MenuDataBinding create() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_story_recap);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_save_story) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_share_menu) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_delete_story) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_to_shared_album) {
            public boolean getDefaultExcluding() {
                if (!MdeSharingService.getInstance().isServiceSupported() || isUpsm()) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }
}
