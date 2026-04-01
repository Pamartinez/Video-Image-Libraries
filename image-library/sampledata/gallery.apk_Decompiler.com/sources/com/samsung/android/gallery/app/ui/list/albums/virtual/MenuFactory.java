package com.samsung.android.gallery.app.ui.list.albums.virtual;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MenuFactory {
    private static MenuDataBinding addCommonBinding(MenuDataBinding menuDataBinding, Blackboard blackboard, String str) {
        if (menuDataBinding == null) {
            return null;
        }
        MenuDataBinder.bindActionDownload(menuDataBinding);
        MenuDataBinder.bindActionCopyToAlbum(menuDataBinding);
        MenuDataBinder.bindActionMoveToAlbum(menuDataBinding);
        MenuDataBinder.bindActionAddToSharedAlbum(menuDataBinding);
        MenuDataBinder.bindActionCreate(menuDataBinding);
        MenuDataBinder.bindKnoxDataBinding(menuDataBinding);
        MenuDataBinder.bindActionAddTag(menuDataBinding);
        MenuDataBinder.bindActionViewAs(menuDataBinding, blackboard);
        MenuDataBinder.bindSetAsWallpaper(menuDataBinding);
        MenuDataBinder.bindCopyPasteEffectBinding(menuDataBinding);
        if (ArgumentsUtil.getArgValue(str, "shortcut_album", false)) {
            menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_shortcut) {
                public boolean getDefaultExcluding() {
                    return true;
                }
            });
        } else {
            MenuDataBinder.bindActionAddShortcut(menuDataBinding);
        }
        MenuDataBinder.bindSlideshowWithSelection(menuDataBinding);
        MenuDataBinder.bindCompareImages(menuDataBinding);
        MenuDataBinder.bindRemoveBackgroundEffectInfo(menuDataBinding);
        MenuDataBinder.bindActionEditLocation(menuDataBinding);
        MenuDataBinder.bindMotionPhotoOperation(menuDataBinding);
        return menuDataBinding;
    }

    public static MenuDataBinding create(String str, Blackboard blackboard) {
        if (str == null) {
            return null;
        }
        MenuDataBinding menuDataBinding = getMenuDataBinding(str);
        if (str.contains("location://virtual/album/video/fileList")) {
            MenuDataBinder.bindActionCreateMovie(menuDataBinding);
        } else if (LocationKey.isRecentlyPictures(str)) {
            MenuDataBinder.bindInvisible(menuDataBinding, R.id.action_sortby);
            MenuDataBinder.bindInvisible(menuDataBinding, R.id.action_create_movie);
        } else if (LocationKey.isFavoritePictures(str)) {
            MenuDataBinder.bindInvisible(menuDataBinding, R.id.action_add_favorite_in_list);
            MenuDataBinder.bindInvisible(menuDataBinding, R.id.action_create_movie);
            MenuDataBinder.bindRemoveFavoriteInList(menuDataBinding);
        }
        addCommonBinding(menuDataBinding, blackboard, str);
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_gallery_assistant, Features.isEnabled(Features.SUPPORT_GALLERY_ASSISTANT)));
        if (LocationKey.isAlbumViewPictures(str)) {
            menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_add_shortcut, false, true));
            menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_sortby, false, true));
        }
        return menuDataBinding;
    }

    private static MenuDataBinding getMenuDataBinding(String str) {
        if (ArgumentsUtil.getArgValue(str, "shortcut_album", false)) {
            return new MenuDataBinding(R.menu.menu_virtual_album_pictures_shortcut);
        }
        if (str.contains("location://virtual/album/video/fileList")) {
            return new MenuDataBinding(R.menu.menu_virtual_album_video);
        }
        return new MenuDataBinding(R.menu.menu_virtual_album_pictures);
    }
}
