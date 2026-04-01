package com.samsung.android.gallery.app.ui.menu;

import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BottomBarMenuResources {
    private static final HashMap<String, Integer> RESOURCE_MAP = new HashMap() {
        {
            Integer valueOf = Integer.valueOf(R.menu.menu_timeline_pictures_bottom_bar);
            Integer valueOf2 = Integer.valueOf(R.menu.menu_sharing_pictures_bottom_bar);
            Integer valueOf3 = Integer.valueOf(R.menu.menu_clean_pictures_bottom_bar);
            Integer valueOf4 = Integer.valueOf(R.menu.menu_albums_bottom_bar);
            put("location://albums", valueOf4);
            put("location://albums/all", valueOf4);
            Integer valueOf5 = Integer.valueOf(R.menu.menu_album_pictures_bottom_bar);
            put("location://albums/fileList", valueOf5);
            Integer valueOf6 = Integer.valueOf(R.menu.menu_stories_bottom_bar);
            put("location://story/albums", valueOf6);
            put("location://stories/favorite", valueOf6);
            Integer valueOf7 = Integer.valueOf(R.menu.menu_mtp_pictures_bottom_bar);
            put("location://search", valueOf7);
            put("location://mtp/fileList", valueOf7);
            Integer valueOf8 = Integer.valueOf(R.menu.menu_trash_bottom_bar);
            put("location://trash", valueOf8);
            boolean z = PocFeatures.SUPPORT_PRIVATE_ALBUM;
            if (z) {
                put("location://private/trash", valueOf8);
            }
            put("location://family/shared/trash", valueOf8);
            put("location://sharing/albums", Integer.valueOf(R.menu.menu_sharings_bottom_bar));
            put("location://sharing/albums/fileList", valueOf2);
            if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
                put("location://sharing/albums/fileList/storageUsage", valueOf2);
            }
            put("location://folder/choice", Integer.valueOf(R.menu.menu_add_album));
            put("location://cleanOut/fileList", valueOf3);
            put("location://cleanOut/motionPhotoClip/fileList", Integer.valueOf(R.menu.menu_clean_motion_photo_clip_bottom_bar));
            put("location://cleanOut/duplicated/fileList", valueOf3);
            put("location://cleanOut/burstSimilar/fileList", valueOf3);
            put("location://revitalized/fileList", Integer.valueOf(R.menu.menu_revitalized_pictures_bottom_bar));
            put("location://highlight/fileList", Integer.valueOf(R.menu.menu_highlight_pictures_bottom_bar));
            put("location://portrait/fileList", Integer.valueOf(R.menu.menu_portrait_pictures_bottom_bar));
            put("location://albums/fileList/mxVirtual/favorite", valueOf5);
            put("location://albums/fileList/mxVirtual/recent", valueOf5);
            put("location://virtual/album/video/fileList", valueOf);
            put("location://virtual/album/repair/fileList", Integer.valueOf(R.menu.menu_virtual_album_repair_bottom_bar));
            put("location://stories/hideSceneSelection", Integer.valueOf(R.menu.menu_hide_scene_selection_bottom_bar));
            put("location://timeline", valueOf);
            if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM)) {
                put("location://family/shared/suggested/fileList", Integer.valueOf(R.menu.menu_family_suggested_pictures));
            }
            if (z) {
                put("location://albums/private/fileList", Integer.valueOf(R.menu.menu_private_album_bottom_bar));
            }
        }
    };

    public static int getMenuResourceId(String str) {
        String removeArgs = ArgumentsUtil.removeArgs(str);
        HashMap<String, Integer> hashMap = RESOURCE_MAP;
        if (hashMap.containsKey(removeArgs)) {
            return hashMap.get(removeArgs).intValue();
        }
        if (LocationKey.isStoryPictures(removeArgs)) {
            return getStoryPicturesResourceId();
        }
        if (LocationKey.isStoriesCategory(removeArgs)) {
            return R.menu.menu_stories_bottom_bar;
        }
        if (LocationKey.isFolder(removeArgs)) {
            return R.menu.menu_folder_bottom_bar;
        }
        if (LocationKey.isSelectMe(str) || LocationKey.isSelectMeAll(str)) {
            return R.menu.menu_search_category_select_me;
        }
        if (LocationKey.isSearchPictures(removeArgs)) {
            return R.menu.menu_timeline_pictures_bottom_bar;
        }
        if (LocationKey.isStoryHighlight(removeArgs)) {
            return R.menu.menu_story_highlight_list_bottom_bar_oneui61;
        }
        if (LocationKey.isSearchCategoryPeople(str) || LocationKey.isSearchCategoryPeopleAndPets(str)) {
            return R.menu.menu_search_category_people;
        }
        return R.menu.menu_default_bottom_bar;
    }

    private static int getStoryPicturesResourceId() {
        if (PreferenceFeatures.OneUi30.MEMORIES) {
            return R.menu.menu_story_pictures_bottom_bar;
        }
        return R.menu.menu_story_pictures_bottom_bar_legacy;
    }
}
