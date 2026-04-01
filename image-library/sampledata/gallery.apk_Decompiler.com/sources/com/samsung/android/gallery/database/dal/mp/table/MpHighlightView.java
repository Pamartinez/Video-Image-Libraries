package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import i.C0212a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpHighlightView extends BaseView {
    public MpHighlightView(QueryParams queryParams) {
        super(queryParams);
    }

    private String getFilterHidden() {
        return C0212a.p(new StringBuilder("bucket_id NOT IN (select bucket_id from (select * from files where is_hide=1) where media_type in (1,3) and bucket_id not in "), getNonHideBucketIds(), " group by bucket_id)");
    }

    private String getNonHideBucketIds() {
        return CursorHelper.joinIds(BucketUtils.values());
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        return super.buildSelectQuery();
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public void onConstruct() {
        super.onConstruct();
        this.mFilesTable.clearSelection();
        this.mFilesTable.filterMountedVolume();
    }

    public void resetDefaultProjectionForCover() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("_id", "__absID");
        this.mQueryBuilder.addProjection("media_id", "__mediaId");
        this.mQueryBuilder.addProjection("IFNULL(_data, cloud_thumb_path)", "__data");
        this.mQueryBuilder.addProjection("smartcrop_rect_ratio", "__rect");
        this.mQueryBuilder.addProjection(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, "__mediaType");
        this.mQueryBuilder.addProjection("orientation", "__orientation");
        this.mQueryBuilder.addProjection("width", "__width");
        this.mQueryBuilder.addProjection("height", "__height");
        this.mQueryBuilder.addProjection("duration", "__fileDuration");
        this.mQueryBuilder.addProjection("dynamic_view_info", "__dynamicViewInfo");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("(select count(*) from files as A INNER JOIN highlight as H ON A._id = H.sec_media_id where " + getWhere() + ")", KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition("H.highlight_time is not null");
        this.mQueryBuilder.andCondition("dynamic_view_info is not null");
        this.mQueryBuilder.andCondition("duration > 0");
        this.mQueryBuilder.andCondition(getFilterHidden());
    }

    public void setDefaultOrder() {
        this.mQueryBuilder.addOrderBy("A._id DESC");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        this.mQueryBuilder.addProjection("smartcrop_rect_ratio", "__sceneRegion");
        this.mQueryBuilder.addProjection("dynamic_view_info", "__dynamicViewInfo");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
        this.mQueryBuilder.addInnerJoin("highlight as H", "A._id = H.sec_media_id");
    }

    public String tag() {
        return "MpHighlightView";
    }
}
