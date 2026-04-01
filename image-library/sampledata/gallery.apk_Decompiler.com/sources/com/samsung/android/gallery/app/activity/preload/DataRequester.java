package com.samsung.android.gallery.app.activity.preload;

import com.samsung.android.gallery.app.activity.preload.abstraction.IDataRequester;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DataRequester implements IDataRequester {
    private static final HashSet<String> sLocationWithCurrent;
    final Blackboard mBlackboard;
    private MediaData mPreloadData;

    static {
        HashSet<String> hashSet = new HashSet<>();
        sLocationWithCurrent = hashSet;
        hashSet.add("location://albums/all");
        hashSet.add("location://albums/manage");
        hashSet.add("location://albums/hide");
        hashSet.add("location://story/albums");
        hashSet.add("location://search");
        hashSet.add("location://search/fileList/Category/MyTag");
        hashSet.add("location://search/fileList/Category/Documents");
        hashSet.add("location://search/fileList/Category/Location");
        hashSet.add("location://search/fileList/Category/People");
        hashSet.add("location://search/fileList/Category/Scene");
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            hashSet.add("location://search/fileList/Category/PeopleAndPets");
            hashSet.add("location://search/fileList/Category/Pet");
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY) {
            hashSet.add("location://search/fileList/Category/MyQuery");
        }
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT) {
            hashSet.add("location://search/fileList/Category/ScreenShot");
        }
        hashSet.add("location://search/fileList/Recommendation");
        hashSet.add("location://map");
        hashSet.add("location://map/filtered");
        hashSet.add("location://map/filteredFromStoryPictures");
        hashSet.add("location://trash");
        hashSet.add("location://virtual/album/favorite/fileList");
        hashSet.add("location://virtual/album/video/fileList");
        hashSet.add("location://virtual/album/recently/fileList");
        hashSet.add("location://virtual/album/repair/fileList");
        hashSet.add("location://folder/root");
        hashSet.add("location://sharing/albums/invitations");
        hashSet.add("location://albums/fileList");
        hashSet.add("location://sharing/albums/fileList");
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
            hashSet.add("location://sharing/albums/storageUse");
            hashSet.add("location://sharing/albums/fileList/storageUsage");
        }
        hashSet.add("location://suggestions");
        hashSet.add("location://mtp/fileList");
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            hashSet.add("location://albums/fileList/ScreenShotFilter");
        }
    }

    public DataRequester(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private void clearPreloadData() {
        MediaData mediaData = this.mPreloadData;
        if (mediaData != null) {
            mediaData.close();
            this.mPreloadData = null;
        }
    }

    private boolean isLoadDirectly(String str) {
        return sLocationWithCurrent.contains(str);
    }

    private boolean isTimelineYear() {
        return GridHelper.isTimelineYear();
    }

    private void requestSearchFileListCurrentData(String str) {
        if (this.mBlackboard.isEmpty(DataKey.DATA_CURSOR(ArgumentsUtil.removeArgs(str)))) {
            this.mBlackboard.publishIfEmpty(CommandKey.DATA_REQUEST(str), (Object) null);
        }
    }

    public void clearPreload(String str) {
        String str2;
        MediaData mediaData = this.mPreloadData;
        if (mediaData != null) {
            str2 = mediaData.getLocationKey();
        } else {
            str2 = null;
        }
        if (str2 != null && str2.startsWith(str)) {
            clearPreloadData();
        }
    }

    public void destroy() {
        clearPreloadData();
    }

    public void load(String str, String str2) {
        if (isLoadDirectly(str)) {
            publishDataByLocationKey(str2);
            return;
        }
        DataRequesterAction action = DataRequesterAction.getAction(str);
        if (action != null) {
            action.run(this.mBlackboard, str2);
        } else if (LocationKey.isStoryPictures(str)) {
            publishDataByLocationKey(str2);
        } else if (LocationKey.isSearchPictures(str)) {
            requestSearchFileListCurrentData(str2);
        } else if (LocationKey.isSearchAutoComplete(str)) {
            publishDataByLocationKey(str2);
        } else if (LocationKey.isSearchCategory(str)) {
            publishDataByLocationKey(str2);
        }
    }

    public void openPreloadData(String str) {
        this.mPreloadData = MediaDataFactory.getInstance(this.mBlackboard).open(str);
    }

    public void preload(String str) {
        if (LocationKey.isTimeline(str)) {
            if (isTimelineYear()) {
                str = ArgumentsUtil.appendArgs(str, "fakeLoadingCount", String.valueOf(3000));
            }
            publishDataByLocationKey("location://timeline/fake");
        } else if (LocationKey.isAlbumsMatch(str)) {
            publishDataByLocationKey("location://albums/cache");
        } else if (LocationKey.isStories(str)) {
            if (PreferenceFeatures.PERFORMANCE.STORIES_CURSOR_CACHE) {
                str = ArgumentsUtil.appendArgs(str, "stories_cursor_cache", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
            }
            publishDataByLocationKey(str);
        } else {
            publishDataByLocationKey(str);
        }
        openPreloadData(str);
    }

    public void publishDataByLocationKey(String str) {
        BlackboardUtils.publishDataRequest(this.mBlackboard, str);
    }
}
