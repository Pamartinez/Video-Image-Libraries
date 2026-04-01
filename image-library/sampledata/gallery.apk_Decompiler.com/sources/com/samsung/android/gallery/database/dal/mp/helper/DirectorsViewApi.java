package com.samsung.android.gallery.database.dal.mp.helper;

import android.database.Cursor;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DirectorsViewApi extends BaseImpl {
    public DirectorsViewApi() {
        super(new QueryParams());
    }

    private String getColumnNameDateTaken() {
        if (this.IS_GTE_Q) {
            return IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME;
        }
        return IParameterKey.DATE_TAKEN;
    }

    private String getColumnNameMediaId() {
        if (this.IS_GTE_Q) {
            return "media_id";
        }
        return "_id";
    }

    private SecFilesTable getDefaultTable() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        setProjection(mpFilesTable);
        filterGroupDirectorsView(mpFilesTable.getQueryBuilder());
        setDateTakenFrom(mpFilesTable);
        if (useDateTimeIndex()) {
            mpFilesTable.setIndex("datetime_id_idx");
        }
        if (mpFilesTable.getDefaultIndex() != null) {
            mpFilesTable.setIndex(mpFilesTable.getDefaultIndex());
        }
        return mpFilesTable;
    }

    private String getSefFileSubType() {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        stringJoiner.add(String.valueOf(3));
        stringJoiner.add(String.valueOf(5));
        if (Features.isEnabled(Features.SUPPORT_DUAL_REC)) {
            stringJoiner.add(String.valueOf(257));
            stringJoiner.add(String.valueOf(ASVLOFFSCREEN.ASVL_PAF_RGB24_B8G8R8));
            stringJoiner.add(String.valueOf(1025));
            stringJoiner.add(String.valueOf(ASVLOFFSCREEN.ASVL_PAF_NV12));
            stringJoiner.add(String.valueOf(4097));
        }
        return stringJoiner.toString();
    }

    private void setProjection(SecFilesTable secFilesTable) {
        QueryBuilder queryBuilder = secFilesTable.getQueryBuilder();
        queryBuilder.clearProjection();
        queryBuilder.addProjection("A._id", "__absID");
        queryBuilder.addProjection("A." + getColumnNameMediaId(), "__fileMediaId");
        queryBuilder.addProjection(" IFNULL(A._data, A.cloud_thumb_path)", "__absPath");
        queryBuilder.addProjection("A." + getColumnNameDateTaken(), "__dateTaken");
        queryBuilder.addProjection("A.mime_type", "__mimeType");
        queryBuilder.addProjection("A.media_type", "__mediaType");
        queryBuilder.addProjection("A.bucket_id", "__albumID");
        queryBuilder.addProjection("A." + getColumnNameGroupId(), "__GroupMediaID");
        queryBuilder.addProjection("A.sef_file_type", "__sefFileType");
        queryBuilder.addProjection("A.sef_file_sub_type", "__sefFileSubType");
        queryBuilder.addProjection("A.is_cloud", "__storageType");
        queryBuilder.addProjection("A._id", "__cloudId");
        queryBuilder.addProjection("A.cloud_server_id", "__cloudServerId");
        queryBuilder.addProjection("A.cloud_server_path", "__cloudServerPath");
    }

    private boolean useDateTimeIndex() {
        if (!QueryParams.TargetDbTypes.SEC.equals(this.mParams.mTargetDb) || this.mParams.getOsVersion() < 30) {
            return false;
        }
        return true;
    }

    public void filterGroupDirectorsView(QueryBuilder queryBuilder) {
        queryBuilder.andCondition("(A._id in (SELECT _id FROM (select _id," + getColumnNameGroupId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getColumnNameDateTaken() + ", bucket_id, sef_file_sub_type from files where " + getColumnNameGroupId() + " > 0 and sef_file_sub_type in (" + getSefFileSubType() + ")) WHERE 1 GROUP BY " + getColumnNameGroupId() + ", bucket_id, sef_file_sub_type HAVING min(" + getColumnNameDateTaken() + ")))");
    }

    public String getColumnNameGroupId() {
        if (this.IS_GTE_Q) {
            return "burst_group_id";
        }
        return BundleKey.GROUP_ID;
    }

    public Cursor getDirectorsViewCursor() {
        return getCursor(getDefaultTable().buildSelectQuery(), "getDirectorsViewCursor");
    }

    public String tag() {
        return "DirectorsViewApi";
    }
}
