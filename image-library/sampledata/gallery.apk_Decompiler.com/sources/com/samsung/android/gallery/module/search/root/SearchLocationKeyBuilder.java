package com.samsung.android.gallery.module.search.root;

import A.a;
import android.text.TextUtils;
import ba.C0583b;
import com.samsung.android.gallery.database.dal.abstraction.FilterResultsKeySet;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.type.ActivityType;
import com.samsung.android.gallery.support.type.CategoryType;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.Optional;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchLocationKeyBuilder {
    private final Blackboard mBlackboard;
    private String mCategory;
    private boolean mFromMoreMenu;
    private boolean mIsSearchToolbarEnabled;
    private final MediaItem mItem;
    private String mLocationKey;
    private String mSelectedFilter;
    private boolean mShowCluster;
    private String mSubCategory;
    private boolean mViewAll;

    public SearchLocationKeyBuilder(MediaItem mediaItem, Blackboard blackboard) {
        this.mItem = mediaItem;
        this.mBlackboard = blackboard;
    }

    private void addClusterInfo(UriBuilder uriBuilder) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster)) {
            this.mBlackboard.publish("data://user/category/itemCount", Integer.valueOf(this.mItem.getCount()));
            if (this.mShowCluster) {
                uriBuilder.appendArg("support_cluster_from_visual_search", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
            }
        }
    }

    private void addCollectInfo(UriBuilder uriBuilder) {
        if (this.mIsSearchToolbarEnabled || LocationKey.supportScopedSearch(this.mLocationKey) || this.mFromMoreMenu) {
            uriBuilder.appendArg("collect_keyword", SearchWordCollector.getCollectedKeyword(this.mItem.getTitle(), this.mSubCategory, this.mCategory)).appendArg("collect_type", getVisualSearchType());
        }
    }

    private void addCreatureInfo(UriBuilder uriBuilder) {
        if (this.mItem.isPeople() || this.mItem.isPet()) {
            if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE && !this.mItem.isMyQuery()) {
                this.mBlackboard.publish("data:///CreatureHeaderItem", this.mItem);
            }
            uriBuilder.appendArg("isNamed", String.valueOf(CreatureData.hasName(this.mItem)));
            uriBuilder.appendArg("people_from_visual_search", true);
        }
    }

    private void addMyQueryInfo(UriBuilder uriBuilder) {
        if (this.mItem.isMyQuery()) {
            uriBuilder.appendArg("my_query", true);
            Optional.ofNullable(this.mItem.getExtra(ExtrasID.MY_QUERY_PEOPLE_ONLY_THEM)).ifPresent(new C0583b(uriBuilder, 0));
        }
    }

    private void addSelectedFilterInfo(UriBuilder uriBuilder) {
        String str = this.mSelectedFilter;
        if (str != null) {
            uriBuilder.appendArg("SelectedFilter", str);
        }
    }

    private UriBuilder createDefaultBuilder(String str) {
        String str2;
        String str3;
        UriBuilder appendArg = new UriBuilder(str).appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, this.mCategory);
        if (this.mShowCluster) {
            str2 = this.mItem.getTitle();
        } else {
            str2 = this.mSubCategory;
        }
        UriBuilder appendArg2 = appendArg.appendArg("sub", str2);
        if (isLocationViewAll()) {
            str3 = AppResources.getString(R$string.map);
        } else {
            str3 = this.mItem.getTitle();
        }
        return appendArg2.appendArg("title", str3).appendArg("term", getTerm()).appendArg("ViewAll", this.mViewAll).appendArg("RefreshFilterResults", !"Activity".equals(this.mCategory));
    }

    public static String fromShotModeType(String str) {
        Integer num = CategoryType.CATEGORY_SHOT_MODE_STRING_MAP.get(str);
        if (num == null) {
            a.u("fromShotModeType wrong argument=", str, "SearchLocationKey");
            return null;
        }
        String string = AppResources.getString(num.intValue());
        return new UriBuilder("location://search/fileList/Category/ShotMode/0").appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, "Camera mode").appendArg("sub", str).appendArg("term", "sef_file_type").appendArg("collect_keyword", string).appendArg("title", string).appendArg("search_skip_save_history", true).appendArg("collect_type", SearchWordCollector.getVisualSearchType("Camera mode")).appendArg("ViewAll", false).appendArg("RefreshFilterResults", true).build();
    }

    private String getActivityLocationKey() {
        String str = this.mSubCategory;
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2004043409:
                if (str.equals("Generated")) {
                    c5 = 0;
                    break;
                }
                break;
            case -885589503:
                if (str.equals("Recently edited")) {
                    c5 = 1;
                    break;
                }
                break;
            case 218729015:
                if (str.equals("Favorites")) {
                    c5 = 2;
                    break;
                }
                break;
            case 591081704:
                if (str.equals("Duplicates")) {
                    c5 = 3;
                    break;
                }
                break;
            case 1286411377:
                if (str.equals("OldDocuments")) {
                    c5 = 4;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return "location://search/fileList/Category/AiEdited";
            case 1:
                return "location://search/fileList/Category/Edited";
            case 2:
                return new UriBuilder("location://virtual/album/favorite/fileList").appendArg("with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE).appendArg("id", String.valueOf(FileUtils.getBucketId("Virtual/Favourites"))).build();
            case 3:
                return new UriBuilder("location://cleanOut/duplicated/fileList").build();
            case 4:
                return new UriBuilder("location://cleanOut/fileList").appendArg("delete_type", 1).build();
            default:
                if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !"PrivateAlbum".equals(this.mSubCategory)) {
                    return null;
                }
                return new UriBuilder("location://albums/private/fileList").build();
        }
    }

    private String getLocationKey(String str) {
        if (str == null) {
            return null;
        }
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1907941713:
                if (str.equals("People")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1790835355:
                if (str.equals("Things")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1591322833:
                if (str.equals("Activity")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1347456360:
                if (str.equals("Documents")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1179428371:
                if (str.equals("My tags")) {
                    c5 = 4;
                    break;
                }
                break;
            case -715666509:
                if (str.equals("Scenery")) {
                    c5 = 5;
                    break;
                }
                break;
            case -638961672:
                if (str.equals("Things Scenery")) {
                    c5 = 6;
                    break;
                }
                break;
            case -626311778:
                if (str.equals("Camera mode")) {
                    c5 = 7;
                    break;
                }
                break;
            case 80127:
                if (str.equals("Pet")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1576064422:
                if (str.equals("ScreenShot")) {
                    c5 = 9;
                    break;
                }
                break;
            case 1843423419:
                if (str.equals("Expressions")) {
                    c5 = 10;
                    break;
                }
                break;
            case 1965687765:
                if (str.equals("Location")) {
                    c5 = 11;
                    break;
                }
                break;
            case 2003419726:
                if (str.equals("People and Pets")) {
                    c5 = 12;
                    break;
                }
                break;
            case 2090249588:
                if (str.equals("My query")) {
                    c5 = 13;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return "location://search/fileList/Category/People";
            case 1:
                return "location://search/fileList/Category/Things";
            case 2:
                if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                    return getActivityLocationKey();
                }
                return null;
            case 3:
                return "location://search/fileList/Category/Documents";
            case 4:
                return "location://search/fileList/Category/MyTag";
            case 5:
                return "location://search/fileList/Category/Scenery";
            case 6:
                return "location://search/fileList/Category/Scene";
            case 7:
                return "location://search/fileList/Category/ShotMode";
            case 8:
                return "location://search/fileList/Category/Pet";
            case 9:
                return "location://search/fileList/Category/ScreenShot";
            case 10:
                return "location://search/fileList/Category/Expressions";
            case 11:
                return "location://search/fileList/Category/Location";
            case 12:
                return "location://search/fileList/Category/PeopleAndPets";
            case 13:
                return "location://search/fileList/Category/MyQuery";
            default:
                return null;
        }
    }

    private String getTerm() {
        String str = (String) this.mItem.getExtra(ExtrasID.MY_QUERY_TERM);
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (this.mShowCluster) {
            return "key_word";
        }
        return FilterResultsKeySet.getField(this.mCategory, this.mSubCategory, this.mItem.getTagType());
    }

    private String getVisualSearchType() {
        String visualSearchType = SearchWordCollector.getVisualSearchType(this.mCategory);
        if (!TextUtils.equals(visualSearchType, SearchWordCollector.Type.VISUAL_SEARCH.toString()) || !this.mItem.isMyQuery()) {
            return visualSearchType;
        }
        return SearchWordCollector.Type.KEYWORD_INPUT.toString();
    }

    private void initValues() {
        String str;
        if (this.mCategory == null) {
            if (this.mItem.isMyQuery()) {
                str = (String) this.mItem.getExtra(ExtrasID.MY_QUERY_MAIN_CATEGORY);
            } else {
                str = this.mItem.getCategory();
            }
            this.mCategory = str;
            if (str == null) {
                this.mCategory = "";
            }
        }
        if (this.mSubCategory == null) {
            this.mSubCategory = this.mItem.getSubCategory();
        }
        if (this.mLocationKey == null) {
            String locationKey = getLocationKey(this.mCategory);
            this.mLocationKey = locationKey;
            if (locationKey == null) {
                this.mLocationKey = "location://search/fileList/Keyword";
            }
        }
        if (this.mSelectedFilter == null) {
            this.mSelectedFilter = (String) this.mItem.getExtra(ExtrasID.SELECTED_FILTER);
        }
    }

    private boolean isActivity() {
        return "Activity".equals(this.mCategory);
    }

    private boolean isActivityInVirtual() {
        if (!isActivity() || isSearchActivity()) {
            return false;
        }
        return true;
    }

    private boolean isLocationViewAll() {
        if (!"Location".equals(this.mCategory) || !TextUtils.isEmpty(this.mSubCategory)) {
            return false;
        }
        return true;
    }

    private boolean isSearchActivity() {
        if ("Recently edited".equals(this.mSubCategory) || "Generated".equals(this.mSubCategory)) {
            return true;
        }
        return false;
    }

    private boolean isSkipHistory() {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            return !LocationKey.isSearchKeyword(this.mLocationKey);
        }
        return this.mItem.isMyQuery();
    }

    private void publishMapInitialLocation() {
        if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61 && TextUtils.equals("Location", this.mCategory)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("latitude", this.mItem.getLatitude());
                jSONObject.put("longitude", this.mItem.getLongitude());
                jSONObject.put("entryItem", this.mItem.getFileId());
                jSONObject.put(FileApiContract.Parameter.PATH, this.mItem.getPath());
                this.mBlackboard.publish("data://user/map/InitialLocation", jSONObject);
            } catch (Exception e) {
                Log.se("SearchLocationKey", e.getMessage());
            }
        }
    }

    private void setSearchToolbar(UriBuilder uriBuilder) {
        boolean z;
        if (!this.mIsSearchToolbarEnabled) {
            if (LocationKey.supportScopedSearch(this.mLocationKey) || this.mFromMoreMenu) {
                z = true;
            } else {
                z = false;
            }
            uriBuilder.appendArg("searchToolbar", z);
        }
    }

    public String build() {
        initValues();
        String searchLocationKey = LocationKey.getSearchLocationKey(this.mLocationKey, this.mSubCategory);
        this.mBlackboard.erase(searchLocationKey);
        boolean z = PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
        if (z && isActivityInVirtual()) {
            return getActivityLocationKey();
        }
        UriBuilder createDefaultBuilder = createDefaultBuilder(searchLocationKey);
        addCreatureInfo(createDefaultBuilder);
        addClusterInfo(createDefaultBuilder);
        addCollectInfo(createDefaultBuilder);
        setSearchToolbar(createDefaultBuilder);
        addSelectedFilterInfo(createDefaultBuilder);
        publishMapInitialLocation();
        addMyQueryInfo(createDefaultBuilder);
        createDefaultBuilder.appendArg("search_skip_save_history", isSkipHistory());
        if (z && isActivity()) {
            createDefaultBuilder.appendArg("title", AppResources.getString(ActivityType.getTitleRes(this.mItem.getTitle())));
        }
        boolean z3 = PreferenceFeatures.OneUi8x.IS_ONE_UI_85;
        if (z3 && this.mFromMoreMenu) {
            createDefaultBuilder.appendArg("search_shot_type_from_more", true);
        }
        if (z3 && (LocationKey.isSearchAiEdited(searchLocationKey) || LocationKey.isSearchEdited(searchLocationKey))) {
            createDefaultBuilder.appendArg("disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        return createDefaultBuilder.build();
    }

    public SearchLocationKeyBuilder fromMoreMenu(boolean z) {
        this.mFromMoreMenu = z;
        return this;
    }

    public SearchLocationKeyBuilder locationKey(String str) {
        this.mLocationKey = str;
        return this;
    }

    public SearchLocationKeyBuilder searchToolbarEnabled(boolean z) {
        this.mIsSearchToolbarEnabled = z;
        return this;
    }

    public SearchLocationKeyBuilder showCluster(boolean z) {
        this.mShowCluster = z;
        return this;
    }

    public SearchLocationKeyBuilder subCategory(String str) {
        this.mSubCategory = str;
        return this;
    }

    public SearchLocationKeyBuilder viewAll(boolean z) {
        this.mViewAll = z;
        return this;
    }
}
