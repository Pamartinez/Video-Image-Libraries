package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpCleanOutBurstSimilarView extends BaseView {
    public MpCleanOutBurstSimilarView(QueryParams queryParams) {
        super(queryParams);
    }

    public void addProjectionForDuplicate() {
        this.mQueryBuilder.addProjection("sum(_size) over()", "total_size");
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public void onConstruct() {
        super.onConstruct();
        this.mFilesTable.clearSelection();
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        this.mQueryBuilder.andCondition("(__group_type == 1 OR __group_type == 2)");
        this.mQueryBuilder.andCondition("(burst_count >= 3)");
        filterLocalOnly(true);
    }

    public void setDefaultOrder() {
        this.mQueryBuilder.addOrderBy("datetaken DESC");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        this.mQueryBuilder.addProjection("CASE WHEN A._id IN ( SELECT sec_media_id FROM group_contents WHERE group_type = 2 ) THEN ( SELECT group_id FROM group_contents WHERE sec_media_id = A._id ) ELSE A.burst_group_id END", "delete_group_id");
        this.mQueryBuilder.addProjection("CASE WHEN group_type IN (1) THEN (SELECT count(*) FROM files WHERE group_type = A.group_type AND bucket_id = A.bucket_id AND burst_group_id = coalesce(A.burst_group_id, 0)) WHEN G.__group_type IN (2) THEN (G.__burst_count) ELSE 0 END", "burst_count");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
        this.mQueryBuilder.addLeftOuterJoin("(WITH TMP AS (SELECT group_id,COUNT(group_id) AS cnt FROM group_contents GROUP BY group_id) SELECT sec_media_id AS __sec_media_id,group_id AS __group_id,group_type AS __group_type,(SELECT cnt FROM TMP WHERE group_id = tmpG.group_id) AS __burst_count FROM group_contents AS tmpG) AS G", "A._id = G.__sec_media_id");
    }

    public void setProjectionForCover() {
        this.mQueryBuilder.addProjection("A._id", "__absID");
        this.mQueryBuilder.addProjection("A.media_id", "__mediaId");
        this.mQueryBuilder.addProjection("A.title", "__Title");
        this.mQueryBuilder.addProjection("IFNULL(A._data, A.cloud_thumb_path)", "__data");
        this.mQueryBuilder.addProjection("A.smartcrop_rect_ratio", "__rect");
        this.mQueryBuilder.addProjection("A.media_type", "__mediaType");
        this.mQueryBuilder.addProjection("A.orientation", "__orientation");
        this.mQueryBuilder.addProjection("A.width", "__width");
        this.mQueryBuilder.addProjection("A.height", "__height");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("(select count(*) from (" + this.mQueryBuilder.build() + "))", KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
    }

    public String tag() {
        return "MpCleanOutBurstSimilarView";
    }
}
