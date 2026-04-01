package com.samsung.android.gallery.database.dal.cmh.table;

import com.samsung.android.gallery.database.dal.abstraction.query.IGroupMediaQuery;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.AbsSecFilesTable;
import com.samsung.android.gallery.database.dal.abstraction.table.GroupMediaQuery;
import com.samsung.android.gallery.database.dal.abstraction.table.GroupMediaQueryWithTable;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhFilesTable extends AbsSecFilesTable {
    private boolean mUseGalleryIndex;

    public CmhFilesTable(QueryParams queryParams) {
        super(queryParams);
    }

    public void addProjectionFileStatus() {
        this.mQueryBuilder.addProjection("A.file_status", "__fileStatus");
    }

    public void addProjectionGroupId() {
        this.mQueryBuilder.addProjection(getColumnGroupMediaId(), "__GroupMediaID");
    }

    public void addProjectionOrientationTag() {
        if (Features.isEnabled(Features.SUPPORT_CMH_ORIENTATION_TAG)) {
            this.mQueryBuilder.addProjection("A.orientation_tag", "__orientationTag");
        }
    }

    public void addProjectionUrl() {
        this.mQueryBuilder.addProjection("A.vendor", "__capturedAPP");
        this.mQueryBuilder.addProjection("A.image_url", "__capturedURL");
    }

    public IGroupMediaQuery createGroupMediaQueryImpl() {
        if (this.IS_GTE_Q) {
            return new GroupMediaQueryWithTable(this.mParams.getGroupTypes()).usedForCmh();
        }
        return new GroupMediaQuery();
    }

    public void filterFileStatus() {
        this.mQueryBuilder.andCondition("(A.file_status = 0 OR A.file_status = 4)");
    }

    public void filterGalleryMedia(String str) {
        if (MediaFilterType.IMAGE_ONLY.toString().equals(str)) {
            this.mQueryBuilder.andCondition("(A.media_type > 0 AND A.media_type < 2)");
        } else if (MediaFilterType.VIDEO_ONLY.toString().equals(str)) {
            this.mQueryBuilder.andCondition("(A.media_type > 2)");
        } else {
            filterGalleryMedia();
        }
    }

    public void filterMountedVolume() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.andCondition("(A.is_mount > 0)");
        }
    }

    public void filterSecMediaId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A.sec_media_id = " + j2);
    }

    public String getColumnDateTaken() {
        return IParameterKey.DATE_TAKEN;
    }

    public String getColumnNameCloudId() {
        return "A.cloud_id";
    }

    public String getColumnNameMediaId() {
        return "A." + getCommonColumnNameMediaId();
    }

    public String getCommonColumnNameMediaId() {
        return "media_id";
    }

    public String getDefaultIndex() {
        if (this.mUseGalleryIndex) {
            return "gallery_sort_index";
        }
        return "sort_index";
    }

    public String getFilterVolumeConditionForShotMode() {
        if (this.IS_GTE_Q) {
            return "and (A.is_mount > 0)";
        }
        return "";
    }

    public void onConstruct() {
        super.onConstruct();
    }

    public void resetProjectionForCameraAi() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection(new String[]{"sec_media_id", "effect_type", "documentScanRect"});
    }

    public void resetProjectionForGroupMediaId() {
        this.mQueryBuilder.removeProjectionByAlias("__GroupMediaID");
    }

    public void setDefaultProjection() {
        super.setDefaultProjection();
        if (this.IS_GTE_Q) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.replaceProjectionByAlias("MAX(" + getColumnNameMediaId() + ", 0)", "__fileMediaId");
        }
        if (Features.isEnabled(Features.SUPPORT_CMH_ORIENTATION_TAG)) {
            this.mQueryBuilder.addProjection("A.orientation_tag", "__orientationTag");
        }
        if (SdkConfig.atLeast(SdkConfig.SEM.T_MR1)) {
            this.mQueryBuilder.addProjection("A.mfc_Score", "__mfc_score");
        }
    }

    public String tag() {
        return "CmhFilesTable";
    }

    public void filterGalleryMedia() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.andCondition("A.media_type >= 1");
            return;
        }
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A." + getColumnDateTaken() + " >-62167219200000 and A.media_type in (1,3)");
    }

    public void addProjectionVolumeName() {
    }
}
