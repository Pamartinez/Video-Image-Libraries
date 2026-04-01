package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.ICategoryView;
import com.samsung.android.gallery.database.dal.abstraction.FilterResultsKeySet;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.album.AlbumInfo;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;
import com.samsung.android.gallery.module.search.root.VisualSearchLoggerHelper;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.ArrayList;
import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterCategoryPresenter<V extends ICategoryView> extends CategoryPresenter<V> {
    public ClusterCategoryPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private UriBuilder getBuilder() {
        return new UriBuilder("location://search/fileList/KeywordClusterPictures").appendArg("collect_type", SearchWordCollector.Type.KEYWORD_INPUT.toString()).appendArg("searchToolbar", false).appendArg("search_keyword_pictures_only", true);
    }

    private String getClusterCategoryTargetKey(String str, MediaItem mediaItem) {
        String str2 = (String) mediaItem.getExtra(ExtrasID.CLUSTER_TYPE);
        String subCategory = mediaItem.getSubCategory();
        if (str2 != null) {
            char c5 = 65535;
            switch (str2.hashCode()) {
                case -1671680240:
                    if (str2.equals("person_cluster")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case 1038965053:
                    if (str2.equals("facet_location")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 1283958266:
                    if (str2.equals("creature_cluster")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 1955912410:
                    if (str2.equals("pet_cluster")) {
                        c5 = 3;
                        break;
                    }
                    break;
            }
            String str3 = "location://search/fileList/Category/People";
            switch (c5) {
                case 0:
                    str = LocationKey.getSearchLocationKey(str3, subCategory);
                    break;
                case 1:
                    str = LocationKey.getSearchLocationKey("location://search/fileList/Category/Location", subCategory);
                    publishMapInitialLocation(mediaItem);
                    break;
                case 2:
                    if (!mediaItem.isPeople()) {
                        str3 = "location://search/fileList/Category/Pet";
                    }
                    str = LocationKey.getSearchLocationKey(str3, subCategory);
                    break;
                case 3:
                    str = LocationKey.getSearchLocationKey("location://search/fileList/Category/Pet", subCategory);
                    break;
            }
            ((ICategoryView) this.mView).postAnalyticsLog(getEventID(str2));
        }
        return str;
    }

    private AnalyticsEventId getEventID(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1671680240:
                if (str.equals("person_cluster")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1623182517:
                if (str.equals("ocrtext")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1480586582:
                if (str.equals("pdc_cluster")) {
                    c5 = 2;
                    break;
                }
                break;
            case -147112977:
                if (str.equals("usertag")) {
                    c5 = 3;
                    break;
                }
                break;
            case 627202090:
                if (str.equals("album_cluster")) {
                    c5 = 4;
                    break;
                }
                break;
            case 1038965053:
                if (str.equals("facet_location")) {
                    c5 = 5;
                    break;
                }
                break;
            case 1283958266:
                if (str.equals("creature_cluster")) {
                    c5 = 6;
                    break;
                }
                break;
            case 1955912410:
                if (str.equals("pet_cluster")) {
                    c5 = 7;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return AnalyticsEventId.EVENT_SEARCH_CLUSTER_CATEGORY_PEOPLE;
            case 1:
                return AnalyticsEventId.EVENT_SEARCH_CLUSTER_CATEGORY_OCRS;
            case 2:
                return AnalyticsEventId.EVENT_SEARCH_CLUSTER_CATEGORY_EVENTS;
            case 3:
                return AnalyticsEventId.EVENT_SEARCH_CLUSTER_CATEGORY_TAGS;
            case 4:
                return AnalyticsEventId.EVENT_SEARCH_CLUSTER_CATEGORY_ALBUMS;
            case 5:
                return AnalyticsEventId.EVENT_SEARCH_CLUSTER_CATEGORY_LOCATIONS;
            case 6:
                return AnalyticsEventId.EVENT_SEARCH_CLUSTER_CATEGORY_PET;
            case 7:
                return AnalyticsEventId.EVENT_SEARCH_CLUSTER_CATEGORY_PET;
            default:
                return null;
        }
    }

    private String getLocationKeyWithType(MediaItem mediaItem) {
        HashSet<Integer> subAlbumIds;
        int albumID = mediaItem.getAlbumID();
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !mediaItem.isMergedAlbum()) {
            return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs("location://albums/fileList/fromSearchCluster", "id", String.valueOf(albumID)), "type", String.valueOf(mediaItem.getAlbumType().toInt()));
        }
        MediaItem[] albumsInFolder = mediaItem.getAlbumsInFolder();
        ArrayList arrayList = new ArrayList();
        for (MediaItem albumID2 : albumsInFolder) {
            arrayList.add(Integer.valueOf(albumID2.getAlbumID()));
        }
        if (arrayList.isEmpty() && (subAlbumIds = AlbumInfo.getSubAlbumIds(albumID)) != null) {
            arrayList.addAll(subAlbumIds);
        }
        return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs("location://albums/fileList/fromSearchCluster", "mergedAlbumId", String.valueOf(albumID)), "ids", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList));
    }

    private UriBuilder getPdcBuilder() {
        return new UriBuilder("location://search/fileList/PdcPictures").appendArg("collect_type", SearchWordCollector.Type.KEYWORD_INPUT.toString()).appendArg("searchToolbar", false).appendArg("search_keyword_pictures_only", true);
    }

    private boolean isAlbumCategory(String str) {
        return "album_cluster".equals(str);
    }

    private boolean isClusterPicturesCategory(String str) {
        if ("ocrtext".equals(str) || "usertag".equals(str) || "pdc_cluster".equals(str)) {
            return true;
        }
        return false;
    }

    private void launchAlbumPictures(MediaItem mediaItem) {
        this.mBlackboard.publish("data://albums/current", mediaItem);
        UriBuilder uriBuilder = new UriBuilder(getLocationKeyWithType(mediaItem));
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            if (BucketUtils.isFavourite(mediaItem.getAlbumID())) {
                uriBuilder.appendArg("with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
            }
            uriBuilder.appendArg("type", String.valueOf(mediaItem.getAlbumType().toInt()));
        }
        uriBuilder.appendArg("count", String.valueOf(mediaItem.getCount())).appendArg("cluster_album_item", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE).appendArg("supportSearchIcon", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE).appendArg("album_split_blocked", true);
        ((ICategoryView) this.mView).postAnalyticsLog(getEventID("album_cluster"));
        this.mBlackboard.post("command://MoveURL", uriBuilder.build());
    }

    private void launchClusterPictures(String str, MediaItem mediaItem) {
        if ("pdc_cluster".equals(str)) {
            launchPdcClusterPictures(mediaItem);
            return;
        }
        ((ICategoryView) this.mView).postAnalyticsLog(getEventID(str));
        String str2 = "ocrtext";
        boolean equals = str2.equals(str);
        String title = mediaItem.getTitle();
        UriBuilder appendArg = getBuilder().appendArg("sub", title).appendArg("title", title);
        if (!equals) {
            str2 = "key_word";
        }
        this.mBlackboard.post("command://MoveURL", appendArg.appendArg("term", str2).appendArg("collect_keyword", title).build());
    }

    private void launchPdcClusterPictures(MediaItem mediaItem) {
        long[] jArr = (long[]) mediaItem.getExtra(ExtrasID.PDC_TIME_INFO);
        this.mBlackboard.post("command://MoveURL", getPdcBuilder().appendArg("sub", mediaItem.getTitle()).appendArg("title", mediaItem.getTitle()).appendArg("term", "pdc_cluster").appendArg("collect_keyword", mediaItem.getTitle()).appendArg("search_pdc_timerange", new String[]{String.valueOf(jArr[0]), String.valueOf(jArr[1])}).build());
    }

    private void publishMapInitialLocation(MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61 && TextUtils.equals("Location", mediaItem.getCategory())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("latitude", mediaItem.getLatitude());
                jSONObject.put("longitude", mediaItem.getLongitude());
                jSONObject.put("entryItem", mediaItem.getFileId());
                jSONObject.put(FileApiContract.Parameter.PATH, mediaItem.getPath());
                this.mBlackboard.publish("data://user/map/InitialLocation", jSONObject);
            } catch (Exception e) {
                Log.se(this.TAG, e.getMessage());
            }
        }
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what == 8002) {
            Object[] objArr = (Object[]) eventMessage.obj;
            if (objArr[0] == null) {
                return true;
            }
            this.mBlackboard.publish("data://user/SearchClusterAllEntry", new ClusterResultsEntry.Extractor().extract(objArr).build());
            getMediaData().reopen(getLocationKey());
            return true;
        }
        super.handleEvent(eventMessage);
        return true;
    }

    public boolean isEnableSearchToolbar() {
        return false;
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        String subCategory = mediaItem.getSubCategory();
        String searchLocationKey = LocationKey.getSearchLocationKey(ArgumentsUtil.removeArgs(getLocationKey()), subCategory);
        this.mBlackboard.erase(searchLocationKey);
        String str = (String) mediaItem.getExtra(ExtrasID.CLUSTER_TYPE);
        if (isAlbumCategory(str)) {
            launchAlbumPictures(mediaItem);
        } else if (isClusterPicturesCategory(str)) {
            launchClusterPictures(str, mediaItem);
        } else {
            int tagType = mediaItem.getTagType();
            String category = mediaItem.getCategory();
            UriBuilder appendArg = new UriBuilder(getClusterCategoryTargetKey(searchLocationKey, mediaItem)).appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, category).appendArg("sub", subCategory).appendArg("title", mediaItem.getTitle()).appendArg("term", FilterResultsKeySet.getField(category, subCategory, tagType));
            if (mediaItem.isPeople()) {
                appendArg.appendArg("isNamed", String.valueOf(CreatureData.hasName(mediaItem)));
                appendArg.appendArg("people_from_visual_search", true);
            }
            String build = appendArg.build();
            VisualSearchLoggerHelper.postAnalyticsOnClickCategoryItem(getScreenId(), mediaItem.getCategory(), mediaItem.getSubCategory(), CreatureData.hasName(mediaItem));
            this.mBlackboard.post("command://MoveURL", build);
        }
    }
}
