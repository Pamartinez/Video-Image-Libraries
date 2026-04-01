package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpSuggestedCleanOutView extends DbTable {
    public MpSuggestedCleanOutView(QueryParams queryParams) {
        super(queryParams);
    }

    private String getFileIdColumnName() {
        return "files._id";
    }

    private String getFilterDeleteGroupID() {
        StringBuilder sb2 = new StringBuilder("delete_group_id in (select delete_group_id from delete_recommendation INNER JOIN files ON (delete_recommendation.sec_media_id = files._id AND (is_favorite is null OR is_favorite = 0) ) where ");
        sb2.append(getWhere());
        sb2.append(" GROUP BY delete_group_id HAVING count(distinct ");
        return C0212a.p(sb2, getFileIdColumnName(), ")>1)");
    }

    private String getFilterHidden() {
        return C0212a.p(new StringBuilder("bucket_id NOT IN (select bucket_id from (select * from files where is_hide=1) where media_type in (1,3) and bucket_id not in "), getNonHideBucketIds(), " group by bucket_id)");
    }

    private String getNonHideBucketIds() {
        return CursorHelper.joinIds(BucketUtils.values());
    }

    private void resetDefaultProjectionForCover() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection(getFileIdColumnName(), "__absID");
        this.mQueryBuilder.addProjection("files.media_id", "__mediaId");
        this.mQueryBuilder.addProjection("_display_name", "__Title");
        this.mQueryBuilder.addProjection("IFNULL(_data, cloud_thumb_path)", "__data");
        this.mQueryBuilder.addProjection("smartcrop_rect_ratio", "__rect");
        this.mQueryBuilder.addProjection(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, "__mediaType");
        this.mQueryBuilder.addProjection("orientation", "__orientation");
        this.mQueryBuilder.addProjection("width", "__width");
        this.mQueryBuilder.addProjection("height", "__height");
        this.mQueryBuilder.addProjection("delete_type", "__deleteType");
        if (PreferenceFeatures.OneUi41.CLEANOUT_DUPLICATED_IMAGE) {
            this.mQueryBuilder.addProjection("delete_group_id", "delete_group_id");
        }
    }

    public void addProjectionForDuplicate() {
        this.mQueryBuilder.addProjection("sum(_size) over()", "total_size");
    }

    public void filterDeleteGroupID() {
        this.mQueryBuilder.andCondition(getFilterDeleteGroupID());
    }

    public void filterDeleteType(int i2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("delete_type = " + i2);
    }

    public String getTableNameRaw() {
        return "delete_recommendation";
    }

    public void groupByFileId() {
        this.mQueryBuilder.addGroupBy(getFileIdColumnName());
    }

    public void orderByGroupID() {
        this.mQueryBuilder.clearOrderBy();
        this.mQueryBuilder.addOrderBy("delete_group_id DESC");
    }

    public void resetProjectionForCover() {
        resetDefaultProjectionForCover();
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            String mountedVolumeNames = FileUtils.getMountedVolumeNames();
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.addProjection("(select count(*) from " + getTableNameRaw() + " INNER JOIN files ON (delete_recommendation.sec_media_id = files._id AND (is_favorite is null OR is_favorite = 0) ) AND files.volume_name in (" + mountedVolumeNames + ") AND " + getFilterHidden() + ")", KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
            return;
        }
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.addProjection("(select count(*) from " + getTableNameRaw() + " INNER JOIN files ON (delete_recommendation.sec_media_id = files._id AND (is_favorite is null OR is_favorite = 0) ) AND " + getFilterHidden() + ")", KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
    }

    public void resetProjectionForCoverV2(int i2) {
        resetDefaultProjectionForCover();
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            String mountedVolumeNames = FileUtils.getMountedVolumeNames();
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.addProjection("(select count(*) from " + getTableNameRaw() + " INNER JOIN files ON (delete_recommendation.sec_media_id = files._id AND (is_favorite is null OR is_favorite = 0) ) AND clean_state = 0 AND files.volume_name in (" + mountedVolumeNames + ") AND delete_type = " + i2 + " AND " + getFilterHidden() + ")", KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
            return;
        }
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.addProjection("(select count(*) from " + getTableNameRaw() + " INNER JOIN files ON (delete_recommendation.sec_media_id = files._id AND (is_favorite is null OR is_favorite = 0) ) AND delete_type = " + i2 + " AND " + getFilterHidden() + ")", KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
    }

    public void resetProjectionForDuplicateCover() {
        resetDefaultProjectionForCover();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("(select count(distinct " + getFileIdColumnName() + ") from " + getTableNameRaw() + " INNER JOIN files ON (delete_recommendation.sec_media_id = files._id AND (is_favorite is null OR is_favorite = 0) ) AND delete_type = 3 AND " + getFilterDeleteGroupID() + ")", KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
    }

    public void setDefaultCondition() {
        String str;
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            str = C0212a.m("clean_state = 0 AND files.volume_name in (", FileUtils.getMountedVolumeNames(), ")");
        } else {
            str = "clean_state = 0";
        }
        this.mQueryBuilder.andCondition(str);
        this.mQueryBuilder.andCondition(getFilterHidden());
    }

    public void setDefaultOrder() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy("datetaken DESC, " + getFileIdColumnName() + " DESC");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection(getFileIdColumnName(), "__absID");
        this.mQueryBuilder.addProjection("files.media_id", "__fileMediaId");
        this.mQueryBuilder.addProjection("_display_name", "__Title");
        this.mQueryBuilder.addProjection("IFNULL(_data, cloud_thumb_path)", "__absPath");
        this.mQueryBuilder.addProjection("duration", "__fileDuration");
        this.mQueryBuilder.addProjection("mime_type", "__mimeType");
        this.mQueryBuilder.addProjection(IParameterKey.DATE_TAKEN, "__dateTaken");
        this.mQueryBuilder.addProjection("bucket_id", "__bucketID");
        this.mQueryBuilder.addProjection("clean_state", "__cleanState");
        this.mQueryBuilder.addProjection("delete_type", "__deleteType");
        this.mQueryBuilder.addProjection(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, "__mediaType");
        this.mQueryBuilder.addProjection("orientation", "__orientation");
        this.mQueryBuilder.addProjection("is_cloud", "__storageType");
        this.mQueryBuilder.addProjection("cloud_server_id", "__cloudServerId");
        this.mQueryBuilder.addProjection("cloud_server_path", "__cloudServerPath");
        this.mQueryBuilder.addProjection("width", "__width");
        this.mQueryBuilder.addProjection("height", "__height");
        if (PreferenceFeatures.OneUi41.CLEANOUT_DUPLICATED_IMAGE) {
            this.mQueryBuilder.addProjection(IParameterKey.SIZE, "__size");
            this.mQueryBuilder.addProjection("delete_group_id", "delete_group_id");
        }
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw());
        this.mQueryBuilder.addInnerJoin("files", "(delete_recommendation.sec_media_id = files._id AND (is_favorite is null OR is_favorite = 0) )");
    }

    public String tag() {
        return "SuggestedCleanOutView";
    }

    public void onConstruct() {
    }
}
