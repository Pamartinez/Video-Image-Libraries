package com.samsung.android.gallery.app.ui.list.picker;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AlbumPickerMenuFactory extends PickerMenuFactory {
    public static void addAlbumCreationMenu(final Blackboard blackboard, MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create) {
            public boolean getDefaultVisibility() {
                return PickerUtil.supportAlbumCreation(blackboard);
            }
        });
    }

    public static MenuDataBinding create(Blackboard blackboard, String str) {
        MenuDataBinding create = PickerMenuFactory.create(blackboard, str);
        if (PickerUtil.supportAlbumCreation(blackboard)) {
            if (create == null) {
                return createAlbumCreationMenu();
            }
            addAlbumCreationMenu(blackboard, create);
        }
        return create;
    }

    private static MenuDataBinding createAlbumCreationMenu() {
        return new MenuDataBinding(R.menu.menu_picker_creation);
    }
}
