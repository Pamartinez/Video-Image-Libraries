package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.content.Context;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuFactory {
    private static void addBinding(MenuDataBinding menuDataBinding, int i2, final boolean z) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(i2) {
            public boolean getDefaultVisibility() {
                return z;
            }
        });
    }

    public static MenuDataBinding create(Context context, String str) {
        MenuDataBinding menuDataBinding;
        if (isNeedForCleanOutMenu(str)) {
            menuDataBinding = createMenuDataBiding(R.menu.menu_clean_out_pictures);
            addBinding(menuDataBinding, R.id.action_select, true);
            addBinding(menuDataBinding, R.id.action_keep_contents, !CleanOutDelegate.isCleanOutDuplicatedPictures(str));
        } else {
            menuDataBinding = createMenuDataBiding(R.menu.menu_clean_motion_photo_clip);
        }
        addBinding(menuDataBinding, R.id.action_view_as, isSupportViewAsMenu(context, str));
        return menuDataBinding;
    }

    private static MenuDataBinding createMenuDataBiding(int i2) {
        return new MenuDataBinding(i2);
    }

    private static boolean isNeedForCleanOutMenu(String str) {
        if (LocationKey.isCleanOutPictures(str) || CleanOutDelegate.isCleanOutDuplicatedPictures(str)) {
            return true;
        }
        return false;
    }

    private static boolean isSupportViewAsMenu(Context context, String str) {
        if (LocationKey.isCleanOutDuplicatedPictures(str) || !DeviceInfo.isDexMode(context)) {
            return false;
        }
        return true;
    }
}
