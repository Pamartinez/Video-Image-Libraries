package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuFactory {
    public static MenuDataBinding create(Blackboard blackboard, ISlideshowV2View iSlideshowV2View) {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_slideshow_v2);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_share) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_order_asc) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_order_desc) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_save_as_video) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        return menuDataBinding;
    }
}
