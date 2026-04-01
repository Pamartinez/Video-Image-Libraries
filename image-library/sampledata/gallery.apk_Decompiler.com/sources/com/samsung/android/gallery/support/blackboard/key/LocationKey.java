package com.samsung.android.gallery.support.blackboard.key;

import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import i.C0212a;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LocationKey {
    private static final Pattern HEADER_PATTERN = Pattern.compile("([A-Za-z0-9]\\w+://)");

    public static boolean containsTrash(String str) {
        if (str == null || !str.contains("location://trash")) {
            return false;
        }
        return true;
    }

    public static String getCacheLocationKeyForAlbum() {
        return "location://albums/cache";
    }

    public static String getCollectionDataKey() {
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            return "location://collection";
        }
        return "location://search";
    }

    public static String getCurrentLocation() {
        return GalleryPreference.getInstance().loadString("location://variable/currentv1", "location://timeline");
    }

    public static String getHeaderCacheKey(String str, int i2) {
        return "location://header/cache/" + str + "/" + i2;
    }

    private static String getSearchId(String str) {
        if (TextUtils.isEmpty(str)) {
            return Long.toHexString(System.currentTimeMillis());
        }
        return Integer.toHexString(str.hashCode());
    }

    public static String getSearchLocationKey(String str, String str2) {
        StringBuilder t = C0212a.t(str, "/");
        t.append(getSearchId(str2));
        return t.toString();
    }

    public static String getStoryPicturesAliasKey() {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return "location://story/albums/storyHighlight";
        }
        return "location://story/albums/fileList";
    }

    public static boolean is1stDepthHighlightView(String str) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || str == null) {
            return false;
        }
        if (str.startsWith("location://highlightViewList") || isHighLightPictures(str)) {
            return true;
        }
        return false;
    }

    public static boolean is1stDepthSuperSlowView(String str) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || str == null || !str.startsWith("location://superSlowViewList")) {
            return false;
        }
        return true;
    }

    public static boolean is2ndDepthHighlightView(String str) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || str == null || !str.startsWith("location://SingleTakenShotviewer/highlight")) {
            return false;
        }
        return true;
    }

    public static boolean is2ndDepthSuggestionHighlightView(String str) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || str == null || !str.startsWith("location://SingleTakenShotviewer/suggestionHighlight")) {
            return false;
        }
        return true;
    }

    public static boolean is2ndDepthSuperSlowView(String str) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || str == null || !str.startsWith("location://SingleTakenShotviewer/superslow")) {
            return false;
        }
        return true;
    }

    public static boolean isAiEditGroupPanelViewer(String str) {
        if (isHighlightGroupPanelView(str) || isSuperSlowGroupPanelView(str)) {
            return true;
        }
        return false;
    }

    public static boolean isAlbumChoice(String str) {
        if (str == null || !str.startsWith("location://albums/choice/root")) {
            return false;
        }
        return true;
    }

    public static boolean isAlbumHide(String str) {
        if (str == null || !str.startsWith("location://albums/hide")) {
            return false;
        }
        return true;
    }

    public static boolean isAlbumPictures(String str) {
        if (str == null || !str.startsWith("location://albums/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isAlbumPicturesFromSearchCluster(String str) {
        if (str == null || !str.startsWith("location://albums/fileList/fromSearchCluster")) {
            return false;
        }
        return true;
    }

    public static boolean isAlbumSetting(String str) {
        if (str == null || !str.equals("location://albums/AlbumSetting")) {
            return false;
        }
        return true;
    }

    public static boolean isAlbumViewPictures(String str) {
        if (str == null || !str.startsWith("location://virtual/album/view/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isAlbums(String str) {
        if (str == null || !str.startsWith("location://albums")) {
            return false;
        }
        return true;
    }

    public static boolean isAlbumsMatch(String str) {
        if (str == null || !"location://albums".equals(ArgumentsUtil.removeArgs(str))) {
            return false;
        }
        return true;
    }

    public static boolean isAllAlbumsMatch(String str) {
        if (str == null || !str.equals("location://albums/all")) {
            return false;
        }
        return true;
    }

    public static boolean isAllDayTimeLapse(String str) {
        if (str == null || !str.startsWith("location://AllDayTimeLapse")) {
            return false;
        }
        return true;
    }

    public static boolean isAskScreenshot(String str) {
        if (!PocFeatures.ASK_SCREENSHOT || !ArgumentsUtil.getArgValue(str, "ask_screenshot", false)) {
            return false;
        }
        return true;
    }

    public static boolean isAutoAlbumPictures(String str) {
        if (str == null || !str.startsWith("location://auto/album/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isCastInFullQualitySupportedView(String str) {
        if (!isRemoteDisplaySupportedView(str) || isSharings(str)) {
            return false;
        }
        return true;
    }

    public static boolean isCategoryList(String str) {
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            return false;
        }
        if (isSearchCategoryDocuments(str) || isSearchCategoryShotMode(str) || isSearchCategoryScreenShot(str)) {
            return true;
        }
        return false;
    }

    public static boolean isChildOfAlbums(String str) {
        if ((!isAlbumPictures(str) || isMxVirtualAlbum(str)) && !isFolder(str)) {
            return false;
        }
        return true;
    }

    public static boolean isChildOfContainer(String str) {
        if (isAlbumPictures(str) || isFolder(str) || isAllAlbumsMatch(str)) {
            return true;
        }
        return false;
    }

    public static boolean isCleanOut(String str) {
        if (str == null || !str.startsWith("location://cleanOut")) {
            return false;
        }
        return true;
    }

    public static boolean isCleanOutBurstSimilarPhoto(String str) {
        if (str == null || !str.startsWith("location://cleanOut/burstSimilar/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isCleanOutDuplicatedPictures(String str) {
        if (str == null || !str.startsWith("location://cleanOut/duplicated/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isCleanOutMotionPhoto(String str) {
        if (str == null || !str.startsWith("location://cleanOut/motionPhotoClip/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isCleanOutPictures(String str) {
        if (str == null || !str.startsWith("location://cleanOut/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isCollection(String str) {
        if (str == null || !str.startsWith("location://collection")) {
            return false;
        }
        return true;
    }

    public static boolean isColorCorrectView(String str) {
        if (str == null || !str.startsWith("location://colorCorrectView")) {
            return false;
        }
        return true;
    }

    public static boolean isContentViewer(String str) {
        if (str != null) {
            return ArgumentsUtil.removeArgs(str).endsWith("viewer");
        }
        return false;
    }

    public static boolean isCreatureCoverChoice(String str) {
        if (str == null || !str.startsWith("location://search/CreatureCoverChoice")) {
            return false;
        }
        return true;
    }

    public static boolean isCropView(String str) {
        if (str == null || !str.startsWith("location://cropView")) {
            return false;
        }
        return true;
    }

    public static boolean isDeepLink(Uri uri) {
        String str;
        if (uri != null) {
            str = uri.toString();
        } else {
            str = null;
        }
        if (str == null) {
            return false;
        }
        if (str.startsWith("app://com.sec.android.gallery3d/location/") || str.startsWith("applink://com.sec.android.gallery3d/location/")) {
            return true;
        }
        return false;
    }

    public static boolean isDrawerVisibleLocation(String str) {
        if (isChildOfContainer(str) || isRootOfContainerExceptTab(str)) {
            return true;
        }
        return false;
    }

    public static boolean isDynamicViewList(String str) {
        if (isHighlightGroupPanelView(str)) {
            return false;
        }
        if ((str == null || !str.startsWith("location://dynamicViewList")) && !isHighLightPictures(str)) {
            return false;
        }
        return true;
    }

    public static boolean isEqualIgnoringArgs(String str, String str2) {
        if (str == null || str2 == null || !ArgumentsUtil.removeArgs(str).equals(ArgumentsUtil.removeArgs(str2))) {
            return false;
        }
        return true;
    }

    public static boolean isFamilySharedTrash(String str) {
        if (str == null || !str.startsWith("location://family/shared/trash")) {
            return false;
        }
        return true;
    }

    public static boolean isFavoritePictures(String str) {
        if (str == null || !str.startsWith("location://virtual/album/favorite/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isFile(String str) {
        if (str == null || !str.startsWith("location://file")) {
            return false;
        }
        return true;
    }

    public static boolean isFolder(String str) {
        if (str == null || !str.startsWith("location://folder/root")) {
            return false;
        }
        return true;
    }

    public static boolean isFromExpand(String str) {
        if (str == null || !ArgumentsUtil.getArgValue(str, "from_expand", false)) {
            return false;
        }
        return true;
    }

    public static boolean isFromHoverView(String str) {
        if (str == null || ArgumentsUtil.getArgValue(str, "fixed_return_position_hover", -1) == -1) {
            return false;
        }
        return true;
    }

    public static boolean isFromRelated(String str) {
        if (str == null || ArgumentsUtil.getArgValue(str, "related_from_id", -1) == -1) {
            return false;
        }
        return true;
    }

    public static boolean isHighLightPictures(String str) {
        if (str == null || !str.startsWith("location://highlight/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isHighlightGroupPanelView(String str) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL) {
            return false;
        }
        if (is1stDepthHighlightView(str) || is2ndDepthHighlightView(str) || is2ndDepthSuggestionHighlightView(str)) {
            return true;
        }
        return false;
    }

    public static boolean isLongExposure(String str) {
        if (str == null || !str.startsWith("location://LongExposure")) {
            return false;
        }
        return true;
    }

    public static boolean isMapCluster(String str) {
        if (str == null || !str.startsWith("location://map/cluster")) {
            return false;
        }
        return true;
    }

    public static boolean isMapFiltered(String str) {
        if (str == null || !str.startsWith("location://map/filtered")) {
            return false;
        }
        return true;
    }

    public static boolean isMapMatch(String str) {
        if (str == null || !str.equals("location://map")) {
            return false;
        }
        return true;
    }

    public static boolean isMtp(String str) {
        if (str == null || !str.startsWith("location://mtp")) {
            return false;
        }
        return true;
    }

    public static boolean isMtpPictures(String str) {
        if (str == null || !str.startsWith("location://mtp/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isMxVirtualAlbum(String str) {
        if (str == null || !str.startsWith("location://albums/fileList/mxVirtual")) {
            return false;
        }
        return true;
    }

    public static boolean isMxVirtualFavoriteAlbum(String str) {
        if (str == null || !str.startsWith("location://albums/fileList/mxVirtual/favorite")) {
            return false;
        }
        return true;
    }

    public static boolean isMxVirtualRecentAlbum(String str) {
        if (str == null || !str.startsWith("location://albums/fileList/mxVirtual/recent")) {
            return false;
        }
        return true;
    }

    public static boolean isPictures(String str) {
        if (str == null) {
            return false;
        }
        if (str.contains("/fileList") || isTimelinePictures(str)) {
            return true;
        }
        return false;
    }

    public static boolean isPrivateAlbum(String str) {
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || str == null || !str.startsWith("location://albums/private/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isPrivateTrash(String str) {
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || str == null || !str.startsWith("location://private/trash")) {
            return false;
        }
        return true;
    }

    public static boolean isQuickView(String str) {
        if (str == null || !str.startsWith("location://quickView")) {
            return false;
        }
        return true;
    }

    public static boolean isQuickViewPictures(String str) {
        if (str == null || !str.startsWith("location://quickView/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isRecentlyPictures(String str) {
        if (str == null || !str.startsWith("location://virtual/album/recently/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isRemasterPictures(String str) {
        if (str == null || !str.startsWith("location://revitalized/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isRemasterSingle(String str) {
        if (str == null || !str.startsWith("location://revitalized/single")) {
            return false;
        }
        return true;
    }

    public static boolean isRemoteDisplaySupportedView(String str) {
        if (isTrash(str) || isFamilySharedTrash(str) || isMtp(str) || isDynamicViewList(str) || isColorCorrectView(str) || isAllDayTimeLapse(str) || isSuperSlowGroupPanelView(str) || isHighlightGroupPanelView(str) || isSuggests(str) || isRevitalizationView(str) || isFromExpand(str)) {
            return false;
        }
        return true;
    }

    public static boolean isRepairPictures(String str) {
        if (str == null || !str.startsWith("location://virtual/album/repair/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isRevitalizationView(String str) {
        if (str == null || !str.startsWith("location://revitalized")) {
            return false;
        }
        return true;
    }

    public static boolean isRootOfContainerExceptTab(String str) {
        boolean isTrash = isTrash(str);
        if (isVirtualPictures(str) || isMxVirtualAlbum(str) || isSuggests(str) || isSharings(str) || isSearchCategoryLocation(str)) {
            return true;
        }
        if ((!PreferenceFeatures.OneUi8x.SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU || !isSearchCategoryShotMode(str)) && !isTrash && !isMtp(str)) {
            return false;
        }
        return true;
    }

    public static boolean isSearchAiEdited(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/AiEdited")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchAutoComplete(String str) {
        if (str == null || !str.startsWith("location://search/AutoComplete")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategory(String str) {
        if (str.equals("location://search/fileList/Category/MyTag") || isSearchCategoryCreatureMatch(str) || str.equals("location://search/fileList/Category/ScreenShot") || str.equals("location://search/fileList/Category/Location") || str.equals("location://search/fileList/Category/ShotMode") || str.equals("location://search/fileList/Category/Documents") || str.equals("location://search/fileList/Category/Scene") || str.equals("location://search/fileList/Category/ScreenShot")) {
            return true;
        }
        return false;
    }

    public static boolean isSearchCategoryCreature(String str) {
        if (isSearchCategoryPeople(str) || isSearchCategoryPet(str)) {
            return true;
        }
        return false;
    }

    public static boolean isSearchCategoryCreatureMatch(String str) {
        if (isSearchCategoryPeopleMatch(str) || isSearchCategoryPeopleAndPetsMatch(str) || isSearchCategoryPetMatch(str)) {
            return true;
        }
        return false;
    }

    public static boolean isSearchCategoryCreatureSelect(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/CreatureSelect")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryCreatureSelectMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/Category/CreatureSelect")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryDocuments(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/Documents")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryExpressions(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/Expressions")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryHiddenPeopleAndPetsMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/Category/HiddenPeopleAndPets")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryHiddenPeopleMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/Category/HiddenPeople")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryLocation(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/Location")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryMyQuery(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/MyQuery")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryMyTag(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/MyTag")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryMyTagMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/Category/MyTag")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryOtherScene(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/Scene")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryOtherScenery(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/Scenery")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryPeople(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/People")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryPeopleAndPets(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/PeopleAndPets")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryPeopleAndPetsHideMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/Category/PeopleAndPetsHide")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryPeopleAndPetsMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/Category/PeopleAndPets")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryPeopleHideMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/Category/PeopleHide")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryPeopleMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/Category/People")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryPeopleSelectForRelation(String str) {
        if (str == null || !str.startsWith("location://search/fileList/PeopleSelectForRelation")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryPeopleSelectMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/Category/PeopleSelect")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryPet(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/Pet")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryPetMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/Category/Pet")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryScreenShot(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/ScreenShot")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryShotMode(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/ShotMode")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryStories(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/Stories")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchCategoryThings(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/Things")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchClusterCategory(String str) {
        return false;
    }

    public static boolean isSearchEdited(String str) {
        if (str == null || !str.startsWith("location://search/fileList/Category/Edited")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchKeyword(String str) {
        if (str == null) {
            return false;
        }
        if (str.startsWith("location://search/fileList/Keyword") || str.startsWith("location://search/fileList/pictures_only/Keyword")) {
            return true;
        }
        return false;
    }

    public static boolean isSearchKeywordClusterPictures(String str) {
        if (str == null || !str.startsWith("location://search/fileList/KeywordClusterPictures")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchKeywordTab(String str) {
        if (str == null || !str.startsWith("location://search/fileList/KeywordTab")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchPeopleAllPictures(String str) {
        if (str == null || !str.startsWith("location://search/fileList/PeopleAllPictures")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchPeopleClusterMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/KeywordPeoples")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchPictures(String str) {
        if (str == null) {
            return false;
        }
        if (str.contains("location://search/fileList/Category")) {
            return !isSearchCategory(str);
        }
        if (str.startsWith("location://search/fileList") || str.startsWith("location://search/fileList/PeopleAllPictures")) {
            return true;
        }
        return false;
    }

    public static boolean isSearchPicturesCreature(String str) {
        if (str == null || !isSearchCategoryCreature(str) || ArgumentsUtil.getArgValue(str, "sub", (String) null) == null) {
            return false;
        }
        return true;
    }

    public static boolean isSearchPicturesLocation(String str) {
        if (str == null || isSearchCategory(str) || !str.startsWith("location://search/fileList/Category/Location")) {
            return false;
        }
        return true;
    }

    public static boolean isSearchRelationshipPreview(String str) {
        if (str == null || !str.startsWith("location://search/fileList/RelationshipPreview")) {
            return false;
        }
        return true;
    }

    private static boolean isSearchShotModeFromMore(String str) {
        if (!isSearchCategoryShotMode(str) || !ArgumentsUtil.getArgValue(str, "search_shot_type_from_more", false)) {
            return false;
        }
        return true;
    }

    public static boolean isSearchSuggestedCreature(String str) {
        if (str == null || !str.startsWith("location://search/fileList/SuggestedCreature")) {
            return false;
        }
        return true;
    }

    public static boolean isSecondDepthGroupPanelView(String str) {
        if (str == null || !str.startsWith("location://SingleTakenShotviewer")) {
            return false;
        }
        return true;
    }

    public static boolean isSelectMe(String str) {
        if (str == null || !str.startsWith("location://search/fileList/SelectMe")) {
            return false;
        }
        return true;
    }

    public static boolean isSelectMeAll(String str) {
        if (str == null || !str.startsWith("location://search/fileList/SelectMeAll")) {
            return false;
        }
        return true;
    }

    public static boolean isSelectMeMatch(String str) {
        if (str == null || !str.equals("location://search/fileList/SelectMe")) {
            return false;
        }
        return true;
    }

    public static boolean isSelectedItems(String str) {
        if (str == null || !str.startsWith("location://selectedItems")) {
            return false;
        }
        return true;
    }

    public static boolean isSharingChoice(String str) {
        if (str == null || !str.equals("location://sharing/choice")) {
            return false;
        }
        return true;
    }

    public static boolean isSharingFamilyAlbumSuggested(String str) {
        if (str == null || !str.startsWith("location://family/shared/suggested/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isSharingPictures(String str) {
        if (str == null || !str.startsWith("location://sharing/albums/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isSharings(String str) {
        if (str == null || !str.startsWith("location://sharing/albums")) {
            return false;
        }
        return true;
    }

    public static boolean isShortcutAlbum(String str) {
        if (str == null || !ArgumentsUtil.getArgValue(str, "shortcut_album", false)) {
            return false;
        }
        return true;
    }

    public static boolean isSimilarShotSelection(String str) {
        if (str == null || !str.startsWith("location://SimilarShotSelectviewer")) {
            return false;
        }
        return true;
    }

    public static boolean isSlideShow(String str) {
        if (str == null) {
            return false;
        }
        String removeArgs = ArgumentsUtil.removeArgs(str);
        if (removeArgs.endsWith("slideshow") || removeArgs.contains("slideshow")) {
            return true;
        }
        return false;
    }

    public static boolean isSpotifySlideShow(String str) {
        if (str == null || !str.startsWith("location://story/albums/spotify")) {
            return false;
        }
        return true;
    }

    public static boolean isStories(String str) {
        if (str == null || !str.startsWith("location://story/albums")) {
            return false;
        }
        return true;
    }

    public static boolean isStoriesCategory(String str) {
        if (str == null || !str.startsWith("location://stories/category/")) {
            return false;
        }
        return true;
    }

    public static boolean isStoriesFavorite(String str) {
        if (str == null || !str.startsWith("location://stories/favorite")) {
            return false;
        }
        return true;
    }

    public static boolean isStoriesMatch(String str) {
        if (str == null || !str.equals("location://story/albums")) {
            return false;
        }
        return true;
    }

    public static boolean isStoryHighlight(String str) {
        if (str == null || !str.startsWith("location://story/albums/storyHighlight")) {
            return false;
        }
        return true;
    }

    public static boolean isStoryOnDemandFloating(String str) {
        if (str == null || !str.startsWith("location://stories/onDemandFloating")) {
            return false;
        }
        return true;
    }

    public static boolean isStoryPictures(String str) {
        if (str == null || !str.startsWith("location://story/albums/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isSuggestionViewList(String str) {
        if (str == null) {
            return false;
        }
        if (str.startsWith("location://cleanOut") || str.startsWith("location://revitalized") || str.startsWith("location://highlight/fileList") || str.startsWith("location://portrait/fileList")) {
            return true;
        }
        return false;
    }

    public static boolean isSuggests(String str) {
        if (str == null || !str.startsWith("location://suggestions")) {
            return false;
        }
        return true;
    }

    public static boolean isSuperSlowGroupPanelView(String str) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL) {
            return false;
        }
        if (is1stDepthSuperSlowView(str) || is2ndDepthSuperSlowView(str)) {
            return true;
        }
        return false;
    }

    public static boolean isTempFile(String str) {
        return ArgumentsUtil.getArgValue(str, "is_temp", false);
    }

    public static boolean isTempList(String str) {
        if (str == null || !str.startsWith("location://tempList")) {
            return false;
        }
        return true;
    }

    public static boolean isTimeline(String str) {
        if (str == null || !str.equals("location://timeline")) {
            return false;
        }
        return true;
    }

    public static boolean isTimelinePictures(String str) {
        if (str == null || !str.startsWith("location://timeline")) {
            return false;
        }
        return true;
    }

    public static boolean isTrash(String str) {
        if (str == null || !str.startsWith("location://trash")) {
            return false;
        }
        return true;
    }

    public static boolean isVideoPictures(String str) {
        if (str == null || !str.startsWith("location://virtual/album/video/fileList")) {
            return false;
        }
        return true;
    }

    public static boolean isVirtualPictures(String str) {
        if (str == null || !str.startsWith("location://virtual/album")) {
            return false;
        }
        return true;
    }

    public static String parseDeepLink(Uri uri) {
        String str;
        String str2;
        if (uri != null) {
            str = uri.toString();
        } else {
            str = null;
        }
        if (str != null) {
            if (str.startsWith("app://com.sec.android.gallery3d/location/")) {
                str2 = str.replace("app://com.sec.android.gallery3d/location/", "");
            } else if (str.startsWith("applink://com.sec.android.gallery3d/location/")) {
                str2 = str.replace("applink://com.sec.android.gallery3d/location/", "");
            } else {
                Log.e("DeepLink", "parseDeepLink failed. wrong uri=" + uri);
            }
            if (!TextUtils.isEmpty(str2)) {
                return C0212a.l("location://", str2);
            }
        }
        return null;
    }

    public static boolean supportDetails(String str) {
        if (isTrash(str) || isFamilySharedTrash(str) || isSuggests(str) || isRevitalizationView(str) || isDynamicViewList(str) || isColorCorrectView(str) || isAllDayTimeLapse(str) || isLongExposure(str) || isCleanOut(str) || isAiEditGroupPanelViewer(str) || isPrivateTrash(str)) {
            return false;
        }
        return true;
    }

    public static boolean supportScopedSearch(String str) {
        if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            return false;
        }
        if (isSearchCategoryPeople(str) || isSearchCategoryPet(str) || isSearchCategoryDocuments(str) || isSearchCategoryScreenShot(str) || isSearchShotModeFromMore(str)) {
            return true;
        }
        return false;
    }

    public static String getHeaderCacheKey(String str, String str2) {
        return C0212a.n("location://header/cache/", str, "/", str2);
    }
}
