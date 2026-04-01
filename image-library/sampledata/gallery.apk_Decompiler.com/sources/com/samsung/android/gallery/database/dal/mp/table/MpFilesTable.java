package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.IGroupMediaQuery;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.AbsSecFilesTable;
import com.samsung.android.gallery.database.dal.abstraction.table.GroupMediaQuery;
import com.samsung.android.gallery.database.dal.abstraction.table.GroupMediaQueryWithTable;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpFilesTable extends AbsSecFilesTable {
    ArrayList<Integer> mExcludeAlbumList;

    public MpFilesTable(QueryParams queryParams) {
        super(queryParams);
        if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
            ArrayList<Integer> excludeAlbumIdList = queryParams.getExcludeAlbumIdList();
            this.mExcludeAlbumList = excludeAlbumIdList;
            if (excludeAlbumIdList != null) {
                filterBucketIds(excludeAlbumIdList, false);
            }
        }
    }

    public void addProjectionCloud() {
        super.addProjectionCloud();
        this.mQueryBuilder.addProjection("A.hash", "__hash");
        this.mQueryBuilder.addProjection("A._data2", "__data2");
        this.mQueryBuilder.addProjection("A.cloud_revision", "__cloudRevision");
        if (SdkConfig.atLeast(SdkConfig.SEM.T_MR1)) {
            this.mQueryBuilder.addProjection("A.cloud_original_binary_hash", "__cloudOriginalBinaryHash");
            this.mQueryBuilder.addProjection("A.cloud_original_binary_size", "__cloudOriginalBinarySize");
        }
        if (Features.isEnabled(Features.SUPPORT_ONE_TRASH_NEW_CLOUD)) {
            this.mQueryBuilder.addProjection("A.timestamp", "__timestamp");
        }
    }

    public void addProjectionGroupId() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.addProjection(getColumnGroupMediaId(), "__GroupMediaID");
            this.mQueryBuilder.addProjection(this.mGroupMediaQueryInterface.getColumnGroupType(), "__group_type");
            return;
        }
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("A." + getColumnGroupMediaId(), "__GroupMediaID");
    }

    public void addProjectionOrientationTag() {
        if (Features.isEnabled(Features.SUPPORT_SEC_MP_ORIENTATION_TAG)) {
            this.mQueryBuilder.addProjection("A.orientation_tag", "__orientationTag");
        }
    }

    public void addProjectionRelativePath() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.addProjection("A.relative_path", "__relativePath");
        }
    }

    public void addProjectionVolumeName() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.addProjection("A.volume_name", "__volumeName");
        }
    }

    public void clearSelection() {
        ArrayList<Integer> arrayList;
        super.clearSelection();
        if (PocFeatures.SUPPORT_LOCKED_ALBUM && (arrayList = this.mExcludeAlbumList) != null && arrayList.size() > 0) {
            filterBucketIds(this.mExcludeAlbumList, false);
        }
    }

    public IGroupMediaQuery createGroupMediaQueryImpl() {
        if (this.IS_GTE_Q) {
            return new GroupMediaQueryWithTable(this.mParams.getGroupTypes());
        }
        return new GroupMediaQuery();
    }

    public void filterIsPending() {
        if (this.IS_GTE_R) {
            QueryParams queryParams = this.mParams;
            if (queryParams.SUPPORT_PPP_DELETE_TO_TRASH) {
                if (queryParams.mTrashOnly) {
                    this.mQueryBuilder.andCondition("A.is_trashed=1");
                } else {
                    this.mQueryBuilder.andCondition("(A.is_trashed is null or A.is_trashed=0)");
                }
            }
            this.mQueryBuilder.andCondition("A.is_pending=0");
        }
    }

    public void filterMountedVolume() {
        if (this.IS_GTE_Q) {
            String mountedVolumeNames = FileUtils.getMountedVolumeNames();
            if (this.mParams.mReplaceVolumeName) {
                C0212a.w("A.volume_name in (", mountedVolumeNames, ",'')", this.mQueryBuilder);
            } else {
                C0212a.w("A.volume_name in (", mountedVolumeNames, ")", this.mQueryBuilder);
            }
        }
    }

    public void filterSingleTakeChildVideoOnly() {
        this.mQueryBuilder.andCondition(getSingleTakeChildVideoOnlyQuery());
    }

    public String getColumnDateTaken() {
        if (this.IS_GTE_Q) {
            return IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME;
        }
        return IParameterKey.DATE_TAKEN;
    }

    public String getColumnNameMediaId() {
        return "A." + getCommonColumnNameMediaId();
    }

    public String getCommonColumnNameMediaId() {
        if (this.IS_GTE_Q) {
            return "media_id";
        }
        return "_id";
    }

    public String getDefaultIndex() {
        if (this.IS_GTE_Q) {
            return null;
        }
        return "sec_gallery_album_index";
    }

    public String getFilterVolumeConditionForShotMode() {
        if (!this.IS_GTE_Q) {
            return "";
        }
        return "and volume_name in (" + FileUtils.getMountedVolumeNames() + ",'')";
    }

    public String getSingleTakeChildVideoOnlyQuery() {
        return this.mGroupMediaQueryInterface.getSingleTakeChildVideoOnlyQuery(getColumnDateTaken());
    }

    public void modifyQueryForYear() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("A._id", "__absID");
        this.mQueryBuilder.addProjection(" IFNULL(A._data, A.cloud_thumb_path)", "__absPath");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("A." + getColumnDateTaken(), "__dateTaken");
        this.mQueryBuilder.addProjection("A.date_modified", "__dateModified");
        this.mQueryBuilder.addProjection("A._size", "__size");
        this.mQueryBuilder.addProjection("A.mime_type", "__mimeType");
        this.mQueryBuilder.addProjection("A.media_type", "__mediaType");
        this.mQueryBuilder.addProjection("A.orientation", "__orientation");
        this.mQueryBuilder.addProjection("A.width", "__width");
        this.mQueryBuilder.addProjection("A.height", "__height");
        this.mQueryBuilder.addProjection("A.is_cloud", "__storageType");
        addProjectionHour();
        addProjectionYear();
    }

    public MpFilesTable optimizeDefaultConditionForJoin() {
        String replace = this.mQueryBuilder.getWhere().replace("A.", "A2.");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("A._id in (select _id from files as A2 where ");
        sb2.append(replace);
        sb2.append(")");
        this.mQueryBuilder.setWhere(sb2);
        return this;
    }

    public void resetProjectionForGroupMediaId() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.removeProjectionByAlias("__GroupMediaID");
            this.mQueryBuilder.removeProjectionByAlias("__group_type");
            return;
        }
        this.mQueryBuilder.removeProjectionByAlias("__GroupMediaID");
    }

    public void setDefaultProjection() {
        super.setDefaultProjection();
        if (this.IS_GTE_R) {
            this.mQueryBuilder.addProjection("A.owner_package_name", "__ownerPackage");
        }
        if (this.IS_GTE_S) {
            this.mQueryBuilder.addProjection("A.original_file_hash", "__origin_file_hash");
        }
        if (this.IS_GTE_T) {
            this.mQueryBuilder.addProjection("A.color_transfer", "__color_transfer");
        }
        if (Features.isEnabled(Features.SUPPORT_GO_TO_CAPTURED_PATH)) {
            this.mQueryBuilder.addProjection("A.captured_original_path", "__capturedPATH");
        }
        if (Features.isEnabled(Features.SUPPORT_SEC_MP_ORIENTATION_TAG)) {
            this.mQueryBuilder.addProjection("A.orientation_tag", "__orientationTag");
        }
        if (this.mParams.SUPPORT_PPP_DRAFT) {
            this.mQueryBuilder.addProjection("A._data_draft", "__draftPath");
        }
        if (QueryParams.SUPPORT_CAPTURE_FRAMERATE) {
            this.mQueryBuilder.addProjection("A.capture_framerate", "__capture_framerate");
        }
        if (QueryParams.SUPPORT_CAM_MODEL) {
            this.mQueryBuilder.addProjection("A.cam_model", "__cam_model");
        }
        if (QueryParams.SUPPORT_LOCAL_TIME) {
            this.mQueryBuilder.addProjection("A.localtime", "local_time");
        }
        List<String> projections = MpAnalyzeInfoTable.getProjections();
        if (!projections.isEmpty()) {
            this.mQueryBuilder.addProjection(projections);
        }
    }

    public String tag() {
        return "MpFilesTable";
    }
}
