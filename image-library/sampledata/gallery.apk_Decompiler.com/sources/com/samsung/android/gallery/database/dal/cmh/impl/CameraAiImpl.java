package com.samsung.android.gallery.database.dal.cmh.impl;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.table.CmhFilesTable;
import com.samsung.android.gallery.database.dal.mp.impl.MpAnalyzeInfoUtil;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CameraAiImpl extends BaseImpl {
    public CameraAiImpl(QueryParams queryParams) {
        super(queryParams);
    }

    public Cursor getCameraAiCursor() {
        String str;
        QueryParams queryParams = this.mParams;
        if (queryParams.CMH_TO_MP_MIGRATION) {
            if (queryParams.getFileId() > 0) {
                str = "sec_media_id=" + this.mParams.getFileId();
            } else {
                str = null;
            }
            Log.i(this.TAG, "getCameraAi by Cmh.Extra : " + str);
            return MpAnalyzeInfoUtil.query(new String[]{"sec_media_id", "effect_type"}, str, (String[]) null, (String) null);
        }
        CmhFilesTable cmhFilesTable = new CmhFilesTable(this.mParams);
        if (this.mParams.getFileId() > 0) {
            cmhFilesTable.filterSecMediaId(this.mParams.getFileId());
        } else {
            cmhFilesTable.getQueryBuilder().andCondition("effect_type!=0");
        }
        cmhFilesTable.resetProjectionForCameraAi();
        return getCursor(cmhFilesTable.buildSelectQuery(), "CameraAiInfo : " + this.mParams.getFileId());
    }

    public String tag() {
        return "CameraAiImpl";
    }
}
