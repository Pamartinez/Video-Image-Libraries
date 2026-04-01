package com.samsung.android.gallery.database.dal.cmh.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedPortraitView extends DbTable {
    public SuggestedPortraitView(QueryParams queryParams) {
        super(queryParams);
    }

    private String getFilterHidden() {
        return C0212a.p(new StringBuilder("bucket_id NOT IN (select bucket_id from (select * from files where is_hide=1) where media_type in (1,3) and bucket_id not in "), getNonHideBucketIds(), " group by bucket_id)");
    }

    private String getNonHideBucketIds() {
        return CursorHelper.joinIds(BucketUtils.values());
    }

    public String getTableNameRaw() {
        return "files";
    }

    public void resetProjectionForCover() {
        String str;
        this.mQueryBuilder.clearProjection();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        if (this.USE_GMP) {
            str = "_id";
        } else {
            str = "sec_media_id";
        }
        queryBuilder.addProjection(str, "__absID");
        this.mQueryBuilder.addProjection("media_id", "__mediaId");
        this.mQueryBuilder.addProjection("portrait_path", "__data");
        this.mQueryBuilder.addProjection("smartcrop_rect_ratio", "__rect");
        this.mQueryBuilder.addProjection(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, "__mediaType");
        this.mQueryBuilder.addProjection("orientation", "__orientation");
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.addProjection("(select count(*) from files as A where " + getWhere() + ")", KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition("portrait_single = 1");
        this.mQueryBuilder.andCondition("portrait_path is not null AND portrait_path != ''");
        this.mQueryBuilder.andCondition(getFilterHidden());
    }

    public void setDefaultOrder() {
        if (this.USE_GMP) {
            this.mQueryBuilder.addOrderBy("_id DESC");
        } else {
            this.mQueryBuilder.addOrderBy("sec_media_id DESC");
        }
    }

    public void setDefaultProjection() {
        String str;
        QueryBuilder queryBuilder = this.mQueryBuilder;
        if (this.USE_GMP) {
            str = "_id";
        } else {
            str = "sec_media_id";
        }
        queryBuilder.addProjection(str, "__absID");
        this.mQueryBuilder.addProjection("media_id", "__fileMediaId");
        this.mQueryBuilder.addProjection(OCRServiceConstant.KEY_PARAM_URI, "__fileUri");
        this.mQueryBuilder.addProjection("duration", "__fileDuration");
        this.mQueryBuilder.addProjection(IParameterKey.DATE_TAKEN, "__dateTaken");
        this.mQueryBuilder.addProjection("bucket_id", "__bucketID");
        this.mQueryBuilder.addProjection(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, "__mediaType");
        this.mQueryBuilder.addProjection("orientation", "__orientation");
        this.mQueryBuilder.addProjection("is_cloud", "__storageType");
        this.mQueryBuilder.addProjection("cloud_server_id", "__cloudServerId");
        this.mQueryBuilder.addProjection("cloud_server_path", "__cloudServerPath");
        this.mQueryBuilder.replaceProjectionByAlias("portrait_path", "__absPath");
        this.mQueryBuilder.replaceProjectionByAlias("portrait_path", "__data");
        this.mQueryBuilder.replaceProjectionByAlias("IFNULL(_data, cloud_thumb_path)", "__originPath");
        this.mQueryBuilder.addProjection("smartcrop_rect_ratio", "__sceneRegion");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw());
    }

    public String tag() {
        return "SuggestedPortraitView";
    }

    public void onConstruct() {
    }
}
