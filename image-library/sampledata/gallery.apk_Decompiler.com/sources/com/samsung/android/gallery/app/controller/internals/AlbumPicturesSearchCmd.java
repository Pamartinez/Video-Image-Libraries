package com.samsung.android.gallery.app.controller.internals;

import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.search.constants.ForegroundMode;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumPicturesSearchCmd extends BaseCommand {
    private String createSearchUri(MediaItem mediaItem, String str) {
        String query = getQuery(mediaItem, str);
        UriBuilder appendArg = new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/Keyword", query)).appendArg("sub", getSub(mediaItem, str)).appendArg("title", query).appendArg("term", str).appendArg("collect_keyword", query).appendArg("collect_type", SearchWordCollector.Type.KEYWORD_INPUT.toString());
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            appendArg.appendArg("searchMode", ForegroundMode.ALBUM.name());
            appendArg.appendArg("subCategoryRemovable", false);
        }
        if (PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD) {
            appendArg.appendArg("disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        return appendArg.build();
    }

    private String getIDList(MediaItem mediaItem) {
        if (!mediaItem.isMergedAlbum()) {
            return String.valueOf(mediaItem.getAlbumID());
        }
        MediaItem[] albumsInFolder = mediaItem.getAlbumsInFolder();
        ArrayList arrayList = new ArrayList();
        for (MediaItem albumID : albumsInFolder) {
            arrayList.add(Integer.valueOf(albumID.getAlbumID()));
        }
        return TextUtils.join("\u001f", arrayList);
    }

    private String getQuery(MediaItem mediaItem, String str) {
        if (TextUtils.equals(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, str)) {
            return AppResources.getString(R.string.video);
        }
        return AlbumTitleHelper.getAlbumTitle(mediaItem.getAlbumID(), mediaItem.getDisplayName());
    }

    private String getSub(MediaItem mediaItem, String str) {
        str.getClass();
        if (str.equals("is_favorite")) {
            return "1";
        }
        if (!str.equals(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE)) {
            return getIDList(mediaItem);
        }
        return "video";
    }

    private String getUrl(MediaItem mediaItem) {
        if (BucketUtils.isVideos(mediaItem.getAlbumID())) {
            return launchVideoSearch(mediaItem);
        }
        if (BucketUtils.isFavourite(mediaItem.getAlbumID())) {
            return launchFavoriteSearch(mediaItem);
        }
        return launchAlbumSearch(mediaItem);
    }

    private String launchAlbumSearch(MediaItem mediaItem) {
        return createSearchUri(mediaItem, "bucket_id");
    }

    private String launchFavoriteSearch(MediaItem mediaItem) {
        return createSearchUri(mediaItem, "is_favorite");
    }

    private String launchVideoSearch(MediaItem mediaItem) {
        return createSearchUri(mediaItem, ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        if (Features.isEnabled(Features.SUPPORT_SCS_ALBUM_PICTURES_SEARCH) && mediaItem != null) {
            eventContext.getBlackboard().postEvent(EventMessage.obtain(8520, new Object[]{Boolean.FALSE, getUrl(mediaItem)}));
        }
    }
}
