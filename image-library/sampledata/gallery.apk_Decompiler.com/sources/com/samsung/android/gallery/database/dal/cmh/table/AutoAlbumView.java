package com.samsung.android.gallery.database.dal.cmh.table;

import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoAlbumView extends BaseView {
    public AutoAlbumView(QueryParams queryParams) {
        super(queryParams);
    }

    public void addAutoAlbumCoverData() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        String tableName = this.mFilesTable.getTableName();
        queryBuilder.addLeftOuterJoin(tableName, "(AU.cover = A.sec_media_id) OR ((AU.cover = 0 OR NOT EXISTS (SELECT sec_media_id FROM " + this.mFilesTable.getTableName() + " WHERE AU.cover = A.sec_media_id)) AND AM.fk_file_id = " + this.mFilesTable.getAliasColumnName("_id") + ")");
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        return super.buildSelectQuery();
    }

    public SecFilesTable createFilesTable() {
        return new CmhFilesTable(this.mParams);
    }

    public String getTableNameRaw() {
        return "auto_album";
    }

    public void groupByAlbum() {
        this.mQueryBuilder.groupBy("AU.album_id");
        String aliasColumnName = this.mFilesTable.getAliasColumnName(IParameterKey.DATE_TAKEN);
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder q = C0086a.q("max(case when ", aliasColumnName, "=0 or ", aliasColumnName, " is null then 1 else ");
        q.append(aliasColumnName);
        q.append(" end)");
        queryBuilder.having(q.toString());
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition(" (AM.fk_file_id IS NOT NULL AND A.uri IS NOT NULL) ");
    }

    public void setDefaultOrder() {
        this.mQueryBuilder.addOrderBy("AU.creation_time DESC, AU.album_id DESC");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("AU.album_id", "__albumID");
        this.mQueryBuilder.addProjection("AU.title", "__albumName");
        this.mQueryBuilder.addProjection("AU.title", "__Title");
        this.mQueryBuilder.addProjection("A.sec_media_id", "__absID");
        this.mQueryBuilder.addProjection("IFNULL(A._data, A.cloud_thumb_path)", "__absPath");
        this.mQueryBuilder.addProjection("A.date_modified", "__dateModified");
        this.mQueryBuilder.addProjection("A.media_type", "__mediaType");
        this.mQueryBuilder.addProjection("A.orientation", "__orientation");
        this.mQueryBuilder.addProjection("A.sef_file_type", "__sefFileType");
        this.mQueryBuilder.addProjection("A.width", "__width");
        this.mQueryBuilder.addProjection("A.height", "__height");
        this.mQueryBuilder.addProjection("A.smartcrop_rect_ratio", "__sceneRegion");
        this.mQueryBuilder.addProjection("A.is_drm", "__isDrm");
        this.mQueryBuilder.addProjection("A.mime_type", "__mimeType");
        this.mQueryBuilder.addProjection("A._size", "__size");
        this.mQueryBuilder.addProjection("A.is_cloud", "__storageType");
        this.mQueryBuilder.addProjection("count(distinct(AM.fk_file_id))", "__count");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "AU");
        this.mQueryBuilder.addLeftOuterJoin("auto_album_map as AM", "AM.fk_album_id = AU.album_id and (AU.sa_type is null or AU.sa_type < 1)");
    }
}
