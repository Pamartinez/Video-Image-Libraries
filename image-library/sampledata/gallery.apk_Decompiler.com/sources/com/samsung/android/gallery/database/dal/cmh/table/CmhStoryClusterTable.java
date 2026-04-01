package com.samsung.android.gallery.database.dal.cmh.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.support.helper.CursorHelper;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhStoryClusterTable extends DbTable {
    public CmhStoryClusterTable(QueryParams queryParams) {
        super(queryParams);
    }

    public void filterStoryId(int[] iArr) {
        if (iArr != null) {
            String joinIds = CursorHelper.joinIds((Collection) IntStream.of(iArr).boxed().collect(Collectors.toList()));
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition("SC.cluster_story_id IN " + joinIds);
        }
    }

    public String getTableNameRaw() {
        return "story_cluster";
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("SC.cluster_story_id", "__albumID");
        this.mQueryBuilder.addProjection("SC.chapter_id", "chapter_id");
        this.mQueryBuilder.addProjection("SC.chapter_title", "chapter_title");
        this.mQueryBuilder.addProjection("SC.chunk_id", "chunk_id");
        this.mQueryBuilder.addProjection("SC.chunkData", "chunkData");
        this.mQueryBuilder.addProjection("SC.chunkType", "chunkType");
        this.mQueryBuilder.addProjection("A.sec_media_id", "__fileMediaId");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "SC");
        this.mQueryBuilder.addLeftOuterJoin("files as A", "A._id = SC.cluster_fileID");
    }

    public String tag() {
        return "CmhStoryClusterTable";
    }

    public void onConstruct() {
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
