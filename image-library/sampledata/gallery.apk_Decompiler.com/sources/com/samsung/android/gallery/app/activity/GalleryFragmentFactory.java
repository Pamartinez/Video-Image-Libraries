package com.samsung.android.gallery.app.activity;

import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GalleryFragmentFactory {
    private static final HashMap<String, FragmentFactory> sFragmentFactories;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FragmentFactory {
        MvpBaseFragment create();
    }

    static {
        HashMap<String, FragmentFactory> hashMap = new HashMap<>();
        sFragmentFactories = hashMap;
        hashMap.put("location://albums/fileList", new g(0));
        hashMap.put("location://sharing/albums/fileList", new h(17));
        hashMap.put("location://mtp/fileList", new g(6));
        hashMap.put("location://search", new g(17));
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            hashMap.put("location://collection", new g(25));
        }
        hashMap.put("location://search/fileList/Category/MyTag", new g(11));
        if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            hashMap.put("location://search/fileList/Category/Location", new h(8));
        } else {
            hashMap.put("location://search/fileList/Category/Location", new g(11));
        }
        if (!Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS)) {
            hashMap.put("location://search/fileList/Category/Documents", new g(11));
        } else if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            hashMap.put("location://search/fileList/Category/Documents", new g(11));
        } else {
            hashMap.put("location://search/fileList/Category/Documents", new h(11));
        }
        hashMap.put("location://search/fileList/Category/People", createCreatureCategoryCompat());
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            hashMap.put("location://search/fileList/Category/PeopleAndPets", createCreatureCategoryCompat());
        }
        hashMap.put("location://search/fileList/Category/Scene", new g(11));
        if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
            hashMap.put("location://search/fileList/Category/PeopleHide", new g(22));
            hashMap.put("location://search/fileList/Category/PeopleAndPetsHide", new g(22));
        }
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            hashMap.put("location://albums/all", new h(3));
            hashMap.put("location://albums/manage", new h(13));
            hashMap.put("location://search/fileList/Category/PeopleSelect", new h(14));
        }
        if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
            hashMap.put("location://search/fileList/Category/CreatureSelect", new h(15));
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY) {
            hashMap.put("location://search/fileList/Category/MyQuery", new h(16));
        }
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT) {
            hashMap.put("location://search/fileList/Category/ScreenShot", new g(11));
        }
        hashMap.put("location://map", new h(18));
        hashMap.put("location://map/filtered", new h(18));
        hashMap.put("location://map/filteredFromStoryPictures", new g(1));
        hashMap.put("location://map/filteredFromTripStory", new h(18));
        hashMap.put("location://search/fileList/Recommendation", new g(2));
        hashMap.put("location://search/FloatingRecommendation", new g(3));
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH)) {
            hashMap.put("location://trash", new g(4));
        } else {
            hashMap.put("location://trash", new g(5));
        }
        hashMap.put("location://albums/fileList/mxVirtual/favorite", new g(0));
        hashMap.put("location://albums/fileList/mxVirtual/recent", new g(0));
        hashMap.put("location://albums/fileList/fromSearchCluster", new g(0));
        hashMap.put("location://virtual/album/video/fileList", new g(7));
        hashMap.put("location://virtual/album/repair/fileList", new g(8));
        hashMap.put("location://albums/hide", new g(9));
        hashMap.put("location://albums/choice/root", new g(10));
        hashMap.put("location://folder/choice", new g(12));
        hashMap.put("location://mtp", new g(13));
        hashMap.put("location://sharing/albums", new g(14));
        hashMap.put("location://sharing/choice", new g(15));
        hashMap.put("location://sharing/albums/invitations", new g(16));
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM)) {
            hashMap.put("location://family/shared/welcome", new g(18));
        }
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_SUGGEST)) {
            hashMap.put("location://family/shared/suggested/fileList", new g(19));
        }
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
            hashMap.put("location://sharing/albums/storageUse", new g(20));
            hashMap.put("location://sharing/albums/fileList/storageUsage", new h(17));
        }
        hashMap.put("location://BurstShotSelectviewer", new g(21));
        hashMap.put("location://SimilarShotSelectviewer", new g(21));
        hashMap.put("location://suggestions", new g(23));
        hashMap.put("location://cleanOut/fileList", new g(24));
        hashMap.put("location://cleanOut/motionPhotoClip/fileList", new g(24));
        hashMap.put("location://cleanOut/burstSimilar/fileList", new g(24));
        hashMap.put("location://cleanOut/duplicated/fileList", new g(24));
        hashMap.put("location://highlight/fileList", new g(26));
        hashMap.put("location://portrait/fileList", new g(26));
        hashMap.put("location://stories/hideRule", new g(27));
        if (PreferenceFeatures.OneUi6x.SUPPORT_NEW_HIDE_SCENE_SELECTION) {
            hashMap.put("location://stories/hideSceneSelection", new g(28));
        } else {
            hashMap.put("location://stories/hideSceneSelection", new g(29));
        }
        hashMap.put("location://stories/onDemandFloating", new h(0));
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster)) {
            hashMap.put("location://search/fileList/ClusterCategoryLocation", new h(1));
            hashMap.put("location://search/fileList/ClusterCategoryPeople", new h(1));
            hashMap.put("location://search/fileList/ClusterCategoryAlbum", new h(1));
            hashMap.put("location://search/fileList/KeywordClusterPictures", new h(2));
            if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
                hashMap.put("location://search/fileList/PdcPictures", new h(2));
            }
            hashMap.put("location://search/fileList/PeopleAllPictures", new h(4));
            hashMap.put("location://search/fileList/LocationAllPictures", new h(4));
        }
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            hashMap.put("location://search/fileList/CarouselCluster", new h(1));
            hashMap.put("location://search/fileList/Category/HiddenPeople", new h(5));
            hashMap.put("location://search/fileList/Category/HiddenPeopleAndPets", new h(5));
            hashMap.put("location://search/fileList/SelectMe", new h(6));
            hashMap.put("location://search/fileList/SelectMeAll", new h(6));
            hashMap.put("location://search/fileList/PeopleSelectForRelation", new h(7));
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU) {
            hashMap.put("location://search/fileList/Category/ShotMode", new g(11));
        }
        hashMap.put("location://virtual/album/view/fileList", new g(7));
        hashMap.put("location://search/EditCreatureName", new h(9));
        if (PreferenceFeatures.OneUi8x.SEARCH_CREATURE_COVER_CHOICE) {
            hashMap.put("location://search/CreatureCoverChoice", new h(10));
        }
    }

    private static FragmentFactory createCreatureCategoryCompat() {
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return new h(12);
        }
        return new g(11);
    }

    public static FragmentFactory get(String str) {
        return sFragmentFactories.get(str);
    }
}
