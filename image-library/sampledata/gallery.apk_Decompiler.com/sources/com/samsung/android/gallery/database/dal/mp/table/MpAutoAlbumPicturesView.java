package com.samsung.android.gallery.database.dal.mp.table;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpAutoAlbumPicturesView extends BaseView {
    public MpAutoAlbumPicturesView(QueryParams queryParams) {
        super(queryParams);
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        return super.buildSelectQuery();
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public void filterAlbumId(int i2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("AU._id = " + i2);
    }

    public String getSortByForDateCursor(int i2) {
        if (!SortByType.isByTime(i2)) {
            return "__dateTaken DESC";
        }
        getQueryBuilder().addProjection("A.date_modified", "__dateModified");
        return this.mFilesTable.getAlbumOrderByQuery(i2).replace("A.date_modified", "__dateModified").replace("A.datetime", "__dateTaken").replace(", A._ID DESC", "").replace(", A._ID ASC", "");
    }

    public String getTableNameRaw() {
        return "auto_album";
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.addLeftOuterJoin("auto_album_map as AM", "AM.album_id = AU._id");
        this.mQueryBuilder.addLeftOuterJoin(this.mFilesTable.getTableName(), "AM.sec_media_id = A._id");
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            C0212a.w("A.volume_name in (", FileUtils.getMountedVolumeNames(), ")", this.mQueryBuilder);
        }
    }

    public void setDefaultOrder() {
        this.mQueryBuilder.addOrderBy("A.datetime DESC");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("AU._id", "__albumID");
        this.mQueryBuilder.addProjection("AU.title", "__albumName");
        this.mQueryBuilder.addProjection("AU.update_status", "__updateStatus");
        this.mQueryBuilder.addProjection("A.media_id", "__fileMediaId");
        this.mQueryBuilder.addProjection("A._id", "__absID");
        this.mQueryBuilder.addProjection("IFNULL(A._data, A.cloud_thumb_path)", "__absPath");
        this.mQueryBuilder.addProjection("A.datetime", "__dateTaken");
        this.mQueryBuilder.addProjection("A.date_added", "__dateAdded");
        this.mQueryBuilder.addProjection("A.date_modified", "__dateModified");
        this.mQueryBuilder.addProjection("A._size", "__size");
        this.mQueryBuilder.addProjection("A._display_name", "__Title");
        this.mQueryBuilder.addProjection("A.mime_type", "__mimeType");
        this.mQueryBuilder.addProjection("A.duration", "__fileDuration");
        this.mQueryBuilder.addProjection("A.media_type", "__mediaType");
        this.mQueryBuilder.addProjection("A.orientation", "__orientation");
        this.mQueryBuilder.addProjection("A.sef_file_type", "__sefFileType");
        this.mQueryBuilder.addProjection("A.sef_file_types", "__sefFileTypes");
        this.mQueryBuilder.addProjection("A.sef_file_sub_type", "__sefFileSubType");
        this.mQueryBuilder.addProjection("A.recording_mode", "__recordingMode");
        this.mQueryBuilder.addProjection("A.recordingtype", "__recordingType");
        this.mQueryBuilder.addProjection("A.is_360_video", "__is360Video");
        this.mQueryBuilder.addProjection("A.is_hdr10_video", "__isHdr10Video");
        this.mQueryBuilder.addProjection("A.resolution", "__resolution");
        this.mQueryBuilder.addProjection("A.width", "__width");
        this.mQueryBuilder.addProjection("A.height", "__height");
        this.mQueryBuilder.addProjection("A.is_favorite", "__isFavourite");
        this.mQueryBuilder.addProjection("A.is_drm", "__isDrm");
        this.mQueryBuilder.addProjection("A.smartcrop_rect_ratio", "__sceneRegion");
        this.mQueryBuilder.addProjection("A.latitude", "__latitude");
        this.mQueryBuilder.addProjection("A.longitude", "__longitude");
        this.mQueryBuilder.addProjection("A.is_cloud", "__storageType");
        this.mQueryBuilder.addProjection("A.cloud_server_id", "__cloudServerId");
        this.mQueryBuilder.addProjection("A.cloud_server_path", "__cloudServerPath");
        this.mQueryBuilder.addProjection("A.cloud_thumb_path", "__cloudTP");
        this.mQueryBuilder.addProjection("A.cloud_original_size", "__cloudOriginalSize");
        this.mQueryBuilder.addProjection("A.audio_codec_info", "__audioCodecInfo");
        this.mQueryBuilder.addProjection("A.video_codec_info", "__videoCodecInfo");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "AU");
    }

    public void setSortBy(int i2) {
        this.mQueryBuilder.clearOrderBy();
        this.mQueryBuilder.addOrderBy(this.mFilesTable.getAlbumOrderByQuery(i2));
    }

    public void filterAlbumId(Collection<Integer> collection) {
        Log.e((CharSequence) this.TAG, "filterAlbumId", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, collection));
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("AU._id in (" + TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, collection) + ")");
    }
}
