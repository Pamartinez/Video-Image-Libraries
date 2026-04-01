package com.samsung.android.gallery.module.dataset;

import A.a;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataFactory {
    private static final HashMap<String, MediaDataConstructor> sCreatorMap;
    private final Blackboard mBlackboard;
    protected final ConcurrentHashMap<String, MediaData> mFactory = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MediaDataConstructor {
        MediaData create(Blackboard blackboard, String str);
    }

    static {
        A a7;
        A a10;
        HashMap<String, MediaDataConstructor> hashMap = new HashMap<>();
        sCreatorMap = hashMap;
        if (!Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            a7 = new A(0);
        } else {
            a7 = new A(13);
        }
        hashMap.put("location://quickView/fileList", a7);
        hashMap.put("location://selectedItems", new A(11));
        hashMap.put("location://quickView", new A(11));
        hashMap.put("location://map", new A(12));
        hashMap.put("location://map/filtered", new A(12));
        hashMap.put("location://cropView", new A(11));
        hashMap.put("location://BurstShotSelectviewer", new A(11));
        hashMap.put("location://SimilarShotSelectviewer", new A(11));
        hashMap.put("location://SingleTakenShotviewer", new A(11));
        hashMap.put("location://SingleTakenShotviewer/highlight", new A(11));
        hashMap.put("location://SingleTakenShotviewer/suggestionHighlight", new A(11));
        hashMap.put("location://SingleTakenShotviewer/superslow", new A(11));
        hashMap.put("location://timeline", new A(13));
        hashMap.put("location://allPictures", new A(13));
        hashMap.put("location://trash", new A(14));
        hashMap.put("location://family/shared/trash", new A(15));
        hashMap.put("location://collection", new A(16));
        hashMap.put("location://search", new A(16));
        hashMap.put("location://sharing/albums/fileList", new A(17));
        hashMap.put("location://sharing/albums/fileList/storageUsage", new A(17));
        hashMap.put("location://sharing/groups", new A(18));
        hashMap.put("location://sharing/groupMembers", new A(19));
        hashMap.put("location://sharing/albums/storageUse", new A(20));
        hashMap.put("location://sharing/choice", new A(20));
        hashMap.put("location://sharing/albums/invitations", new A(1));
        hashMap.put("location://suggestions", new A(2));
        hashMap.put("location://cleanOut/fileList", new A(3));
        hashMap.put("location://cleanOut/motionPhotoClip/fileList", new A(3));
        hashMap.put("location://cleanOut/burstSimilar/fileList", new A(4));
        hashMap.put("location://cleanOut/duplicated/fileList", new A(4));
        hashMap.put("location://highlight/fileList", new A(5));
        hashMap.put("location://portrait/fileList", new A(5));
        hashMap.put("location://stories/hideRule", new A(6));
        if (Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
            a10 = new A(7);
        } else {
            a10 = new A(8);
        }
        hashMap.put("location://stories/hideSceneSelection", a10);
        hashMap.put("location://albums/fileList/ScreenShotFilter", new A(9));
        hashMap.put("location://albums/AlbumSetting/ScreenShotFilterCustom", new A(10));
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            hashMap.put("location://albums/private/fileList", new A(13));
            hashMap.put("location://private/trash", new A(13));
        }
        hashMap.put("location://virtual/album/view/fileList", new A(13));
    }

    private MediaDataFactory(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private MediaData createAlbumsMediaDataCompat(String str) {
        boolean isAlbumHide = isAlbumHide(str);
        boolean z = false;
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS && !isAlbumHide) {
            return new MediaDataMxAlbum(this.mBlackboard, str, isEnableAlbumCache(str), false);
        }
        Blackboard blackboard = this.mBlackboard;
        if (!isAlbumHide && isEnableAlbumCache(str)) {
            z = true;
        }
        return new MediaDataNested(blackboard, str, z, isAlbumHide);
    }

    /* access modifiers changed from: private */
    /* renamed from: createMediaData */
    public MediaData lambda$open$1(String str, String str2) {
        MediaDataConstructor mediaDataConstructor = sCreatorMap.get(str);
        if (mediaDataConstructor != null) {
            return mediaDataConstructor.create(this.mBlackboard, str2);
        }
        if (isAlbums(str)) {
            return createAlbumsMediaDataCompat(str2);
        }
        if (isAllAlbums(str) || isManageAlbums(str)) {
            return new MediaDataAllAlbum(this.mBlackboard, str2);
        } else if (isFile(str)) {
            return new MediaDataList(this.mBlackboard, str2);
        } else {
            if (isMtp(str)) {
                return new MediaDataMtp(this.mBlackboard, str2);
            }
            if (isSharings(str)) {
                return createSharingMediaData(str);
            }
            if (isKeywordStories(str)) {
                return new MediaDataStories(this.mBlackboard, str2);
            }
            if (isKeyWordAlbums(str)) {
                return new MediaDataSearchAlbum(this.mBlackboard, str2, false, false, false);
            }
            String str3 = str2;
            if (isSearchKeyword(str)) {
                return createSearchPicturesCompat(str3);
            }
            if (isSearchClusterTopResults(str)) {
                return new MediaDataCursor(this.mBlackboard, str3);
            }
            boolean z = false;
            if (isKeyWordPeoples(str)) {
                return new MediaDataSearchCategory(this.mBlackboard, str3, false);
            }
            if (isKeyWordClusterList(str) || isKeyWordLocations(str) || isKeyWordOCRs(str)) {
                return new MediaDataList(this.mBlackboard, str3);
            }
            if (isStories(str) || isStoriesFavorite(str)) {
                return createStoriesCompat(str3);
            }
            if (isStoryPictures(str)) {
                if (PreferenceFeatures.OneUi30.MEMORIES) {
                    return new MediaDataStory(this.mBlackboard, str3);
                }
                return new MediaDataStoryBase(this.mBlackboard, str3);
            } else if (isStoryHighlightList(str)) {
                return new MediaDataStoryHighlightList(this.mBlackboard, str3);
            } else {
                if (isTripStoryMap(str)) {
                    return new MediaDataClusterMapList(this.mBlackboard, str3);
                }
                if (isStoryHighlight(str)) {
                    return new MediaDataStoryHighlight(this.mBlackboard, str3);
                }
                if (isStoriesCategory(str) || isSearchCategoryStoriesChild(str)) {
                    return new MediaDataStoriesCategory(this.mBlackboard, str3);
                }
                if (isStoryForOnDemand(str3)) {
                    return new MediaDataList(this.mBlackboard, str3);
                }
                if (isSearchCategory(str) || isCategoryLocationCluster(str) || isSuggestedCreature(str) || isSelectMeAll(str)) {
                    return createSearchCategoryCompat(str3);
                }
                if (isSelectMe(str)) {
                    return new MediaDataList(this.mBlackboard, str3);
                }
                if (isSearchPictures(str) || LocationKey.isCreatureCoverChoice(str)) {
                    return createSearchPicturesCompat(str3);
                }
                if (isRemasterPictures(str)) {
                    return createRemasterMediaDataCompat(str3);
                }
                if (isPictures(str)) {
                    int argValue = ArgumentsUtil.getArgValue(str3, "id", 0);
                    if (ArgumentsUtil.getArgValue(str3, "quick_view", false) && !Features.isEnabled(Features.SUPPORT_PPP_V3) && !BucketUtils.isRecent(argValue)) {
                        return new MediaDataQuickView(this.mBlackboard, str3);
                    }
                    if (LocationKey.isAlbumPictures(str) || BucketUtils.isFavourite(argValue) || BucketUtils.isRecent(argValue)) {
                        z = true;
                    }
                    return createMediaDataTimeline(str3, z);
                } else if (isSearchAutoComplete(str)) {
                    return new MediaDataFilter(this.mBlackboard, str3);
                } else {
                    if (isFolder(str)) {
                        return new MediaDataFolder(this.mBlackboard, str3);
                    }
                    if (isFolderChoice(str)) {
                        return new MediaDataFolderChoice(this.mBlackboard, str3);
                    }
                    if (isAlbumHide(str)) {
                        return createAlbumsMediaDataCompat(str3);
                    }
                    if (isAlbumsPane(str)) {
                        return new MediaDataAlbumPane(this.mBlackboard, str3);
                    }
                    if (isAlbumsChoice(str)) {
                        return new MediaDataAlbumChoice(this.mBlackboard, str3);
                    }
                    if (isTemporaryList(str)) {
                        return new MediaDataList(this.mBlackboard, str3);
                    }
                    if (isDynamicViewList(str) || isSuperSlowList(str) || isHighlightViewList(str) || isColorCorrectView(str)) {
                        return new MediaDataList(this.mBlackboard, str3);
                    }
                    if (isRevitalizeSingleImage(str)) {
                        return new MediaDataList(this.mBlackboard, str3);
                    }
                    if (isAllDayTimeLapseList(str)) {
                        return new MediaDataList(this.mBlackboard, str3);
                    }
                    if (isLongExposureList(str)) {
                        return new MediaDataList(this.mBlackboard, str3);
                    }
                    if (isMapCluster(str)) {
                        return new MediaDataTimeline2(this.mBlackboard, str3);
                    }
                    return new MediaDataCursor(this.mBlackboard, str3);
                }
            }
        }
    }

    private MediaData createMediaDataTimeline(String str, boolean z) {
        if (z) {
            return new MediaDataTimeline2(this.mBlackboard, str);
        }
        return new MediaDataTimeline(this.mBlackboard, str);
    }

    private MediaData createRemasterMediaDataCompat(String str) {
        if (PreferenceFeatures.OneUi5x.REMASTER_PICTURES_V2) {
            return new MediaDataRemasterV2(this.mBlackboard, str);
        }
        return new MediaDataRemaster(this.mBlackboard, str);
    }

    private MediaDataSearchCategory createSearchCategoryCompat(String str) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE && LocationKey.isSearchCategoryCreatureMatch(ArgumentsUtil.removeArgs(str))) {
            return new MediaDataSearchCreatureCategory(this.mBlackboard, str, true);
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY && LocationKey.isSearchCategoryMyQuery(str)) {
            return new MediaDataSearchMyQueryCategory(this.mBlackboard, str, true);
        }
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT && LocationKey.isSearchCategoryScreenShot(str)) {
            return new MediaDataSearchScreenShotCategory(this.mBlackboard, str, true);
        }
        if (LocationKey.isSearchCategoryPeopleSelectForRelation(str)) {
            return new MediaDataSearchCreatureSelectForRelation(this.mBlackboard, str, true);
        }
        if (LocationKey.isSearchSuggestedCreature(str)) {
            return new MediaDataSearchSuggestedCreature(this.mBlackboard, str, true);
        }
        return new MediaDataSearchCategory(this.mBlackboard, str, true);
    }

    private MediaData createSearchPicturesCompat(String str) {
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT && LocationKey.isSearchCategoryScreenShot(str) && !ScreenShotFilterType.All.key().equals(ArgumentsUtil.getArgValue(str, "sub"))) {
            return new MediaDataSearchScreenshotPictures(this.mBlackboard, str);
        }
        if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            return new MediaDataExpandedSearchPictures(this.mBlackboard, str);
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_QOD_SEARCH) {
            return new MediaDataSearchPicturesV2(this.mBlackboard, str);
        }
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
            return new MediaDataSearchPictures(this.mBlackboard, str);
        }
        return new MediaDataTimeline(this.mBlackboard, str);
    }

    private MediaData createSharingMediaData(String str) {
        return new MediaDataMdeSpace(this.mBlackboard, str);
    }

    private MediaData createStoriesCompat(String str) {
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            if (isStoriesFavorite(str) || ArgumentsUtil.getArgValue(str, "from_picker", false)) {
                return new MediaDataStoriesV5(this.mBlackboard, str);
            }
            return new MediaDataStoriesV7(this.mBlackboard, str);
        } else if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return new MediaDataStoriesV5(this.mBlackboard, str);
        } else {
            if (PreferenceFeatures.OneUi30.MEMORIES) {
                return new MediaDataStories(this.mBlackboard, str);
            }
            return new MediaDataStoriesBase(this.mBlackboard, str);
        }
    }

    public static MediaDataFactory getInstance(Blackboard blackboard) {
        return (MediaDataFactory) blackboard.computeIfAbsent("data://MediaDataFactory", new C0608m(3, blackboard));
    }

    private boolean isAlbumHide(String str) {
        return "location://albums/hide".equals(str);
    }

    private boolean isAlbums(String str) {
        return "location://albums".equals(str);
    }

    private boolean isAlbumsChoice(String str) {
        return LocationKey.isAlbumChoice(str);
    }

    private boolean isAlbumsPane(String str) {
        return "location://albums/pane".equals(str);
    }

    private boolean isAllAlbums(String str) {
        return "location://albums/all".equals(str);
    }

    private boolean isAllDayTimeLapseList(String str) {
        return str.startsWith("location://AllDayTimeLapse");
    }

    private boolean isCategoryLocationCluster(String str) {
        return str.equals("location://search/fileList/ClusterCategoryLocation");
    }

    private boolean isColorCorrectView(String str) {
        return str.startsWith("location://colorCorrectView");
    }

    private boolean isDynamicViewList(String str) {
        return str.startsWith("location://dynamicViewList");
    }

    private boolean isEnableAlbumCache(String str) {
        if ((!ArgumentsUtil.removeArgs(str).equals(str) || Features.isEnabled(Features.IS_UPSM)) && !PickerUtil.isPickerMode(this.mBlackboard)) {
            return false;
        }
        return true;
    }

    private boolean isFile(String str) {
        return str.startsWith("location://file");
    }

    private boolean isFolder(String str) {
        return LocationKey.isFolder(str);
    }

    private boolean isFolderChoice(String str) {
        return "location://folder/choice".equals(str);
    }

    private boolean isHighlightViewList(String str) {
        return str.startsWith("location://highlightViewList");
    }

    private boolean isKeyWordAlbums(String str) {
        return str.startsWith("location://search/fileList/KeywordAlbums");
    }

    private boolean isKeyWordClusterList(String str) {
        return str.startsWith("location://search/fileList/KeywordClusterList");
    }

    private boolean isKeyWordLocations(String str) {
        return str.startsWith("location://search/fileList/KeywordLocations");
    }

    private boolean isKeyWordOCRs(String str) {
        return str.startsWith("location://search/fileList/KeywordOcrs");
    }

    private boolean isKeyWordPeoples(String str) {
        return str.startsWith("location://search/fileList/KeywordPeoples");
    }

    private boolean isKeywordStories(String str) {
        return str.startsWith("location://search/fileList/KeywordStories");
    }

    private boolean isLongExposureList(String str) {
        return str.startsWith("location://LongExposure");
    }

    private boolean isManageAlbums(String str) {
        return "location://albums/manage".equals(str);
    }

    private boolean isMapCluster(String str) {
        return str.startsWith("location://map/cluster");
    }

    private boolean isMtp(String str) {
        return str.startsWith("location://mtp");
    }

    private boolean isPictures(String str) {
        return str.contains("/fileList");
    }

    private boolean isRemasterPictures(String str) {
        return LocationKey.isRemasterPictures(str);
    }

    private boolean isRevitalizeSingleImage(String str) {
        return str.startsWith("location://revitalized/single");
    }

    private boolean isSearchAutoComplete(String str) {
        return str.startsWith("location://search/AutoComplete");
    }

    private boolean isSearchCategory(String str) {
        if (str.equals("location://search/fileList/Category/MyTag") || LocationKey.isSearchCategoryCreatureMatch(str) || str.equals("location://search/fileList/Category/PeopleHide") || str.equals("location://search/fileList/Category/HiddenPeople") || str.equals("location://search/fileList/Category/PeopleSelect") || str.equals("location://search/fileList/PeopleSelectForRelation") || str.equals("location://search/fileList/Category/CreatureSelect") || str.equals("location://search/fileList/Category/CreatureMultiPick") || str.equals("location://search/fileList/Category/Location") || str.equals("location://search/fileList/Category/ShotMode") || str.equals("location://search/fileList/Category/Documents") || str.equals("location://search/fileList/Category/Scene") || str.equals("location://search/fileList/Category/PeopleAndPetsHide") || str.equals("location://search/fileList/Category/HiddenPeopleAndPets") || str.equals("location://search/fileList/Category/MyQuery") || str.equals("location://search/fileList/Category/ScreenShot")) {
            return true;
        }
        return false;
    }

    public static boolean isSearchCategoryStoriesChild(String str) {
        if (str == null || !str.matches("location://search/fileList/Category/Stories/?[^/]*")) {
            return false;
        }
        return true;
    }

    private boolean isSearchClusterTopResults(String str) {
        return str.startsWith("location://search/fileList/TopResultsPictures");
    }

    private boolean isSearchKeyword(String str) {
        if (str.startsWith("location://search/fileList/Keyword") || str.startsWith("location://search/fileList/pictures_only/Keyword") || str.startsWith("location://search/fileList/KeywordClusterPictures") || str.startsWith("location://search/fileList/RelationshipPreview") || str.startsWith("location://search/fileList/PdcPictures")) {
            return true;
        }
        return false;
    }

    private boolean isSearchPictures(String str) {
        if (str.contains("location://search/fileList/Category")) {
            return !isSearchCategory(str);
        }
        return str.startsWith("location://search/fileList");
    }

    private boolean isSelectMe(String str) {
        return str.equals("location://search/fileList/SelectMe");
    }

    private boolean isSelectMeAll(String str) {
        return str.equals("location://search/fileList/SelectMeAll");
    }

    private boolean isSharings(String str) {
        return "location://sharing/albums".equals(str);
    }

    private boolean isStories(String str) {
        return "location://story/albums".equals(str);
    }

    private boolean isStoriesCategory(String str) {
        return str.startsWith("location://stories/category/");
    }

    private boolean isStoriesFavorite(String str) {
        return LocationKey.isStoriesFavorite(str);
    }

    private boolean isStoryForOnDemand(String str) {
        return str.startsWith("location://stories/category/ondemand");
    }

    private boolean isStoryHighlight(String str) {
        return str.startsWith("location://story/albums/storyHighlight");
    }

    private boolean isStoryHighlightList(String str) {
        return str.startsWith("location://story/albums/storyHighlightList");
    }

    private boolean isStoryPictures(String str) {
        return str.startsWith("location://story/albums/fileList");
    }

    private boolean isSuggestedCreature(String str) {
        return str.equals("location://search/fileList/SuggestedCreature");
    }

    private boolean isSuperSlowList(String str) {
        return str.startsWith("location://superSlowViewList");
    }

    private boolean isTemporaryList(String str) {
        return str.startsWith("location://tempList");
    }

    private boolean isTripStoryMap(String str) {
        return str.startsWith("location://map/filteredFromTripStory");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaDataFactory lambda$getInstance$0(Blackboard blackboard, String str) {
        return new MediaDataFactory(blackboard);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaData lambda$static$2(Blackboard blackboard, String str) {
        return new MediaDataScreenShotFilter(blackboard, str, false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaData lambda$static$3(Blackboard blackboard, String str) {
        return new MediaDataScreenShotFilter(blackboard, str, true);
    }

    public MediaData create(String str) {
        return lambda$open$1(ArgumentsUtil.removeArgs(str), str);
    }

    public MediaData find(String str) {
        return this.mFactory.get(ArgumentsUtil.removeArgs(str));
    }

    public MediaData open(String str) {
        return open(str, false);
    }

    public void remove(String str) {
        try {
            String removeArgs = ArgumentsUtil.removeArgs(str);
            if (this.mFactory.containsKey(removeArgs)) {
                Log.d("MediaDataFactory", "clear " + removeArgs);
                MediaData mediaData = this.mFactory.get(removeArgs);
                if (mediaData != null) {
                    int refCount = mediaData.getRefCount();
                    for (int i2 = 0; i2 < refCount; i2++) {
                        mediaData.close();
                    }
                    this.mFactory.remove(removeArgs);
                }
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("clear failed e="), "MediaDataFactory");
        }
    }

    public MediaData open(String str, boolean z) {
        return this.mFactory.computeIfAbsent(ArgumentsUtil.removeArgs(str), new m0(2, this, str)).open(str, z);
    }
}
