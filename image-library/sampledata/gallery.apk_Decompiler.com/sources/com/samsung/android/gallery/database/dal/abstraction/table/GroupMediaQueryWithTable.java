package com.samsung.android.gallery.database.dal.abstraction.table;

import N2.j;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.IGroupMediaQuery;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupMediaQueryWithTable implements IGroupMediaQuery {
    String SIMILAR_BEST_IMAGE_ID = "(select sec_media_id from group_contents where group_type=2 and best_image=1)";
    private String checkIsPending = " and is_pending=0";
    public String mIsDisabledGroup;
    public String mIsEnabledGroup;
    public String mIsSingleMedia;
    private boolean mSimilar;
    private boolean mSingleTaken;
    String similarFiles = "(select sec_media_id from group_contents where group_type=2)";

    public GroupMediaQueryWithTable(GroupType... groupTypeArr) {
        String str;
        ArrayList arrayList = new ArrayList(Arrays.asList(groupTypeArr));
        arrayList.remove((Object) null);
        if (!Features.isEnabled(Features.SUPPORT_SINGLE_TAKEN)) {
            arrayList.remove(GroupType.SINGLE_TAKEN);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            GroupType groupType = (GroupType) it.next();
            if (groupType == GroupType.SIMILAR) {
                this.mSimilar = true;
            } else if (groupType == GroupType.SINGLE_TAKEN) {
                this.mSingleTaken = true;
            }
        }
        if (this.mSingleTaken) {
            str = "1,3";
        } else {
            str = "1";
        }
        this.mIsDisabledGroup = C0212a.m("(A.group_type not in (", str, ") or A.group_type is null)");
        this.mIsEnabledGroup = C0212a.m("A.group_type in (", str, ")");
        this.mIsSingleMedia = "A.burst_group_id = 0 OR (A.burst_group_id is null)";
    }

    private String getGroupMediaOnlyQuery(GroupType groupType, String str) {
        StringBuilder sb2 = new StringBuilder("(A._id in (SELECT _id\nFROM ");
        C0086a.z(sb2, getGroupMediaTable(groupType, str), " \n WHERE 1 \nGROUP BY burst_group_id  , bucket_id \nHAVING count(burst_group_id)>1 and sum(case when best_image is null or best_image=-1 then 0 else best_image end)=0  and max(", str, ") \nUNION ALL\nSELECT _id\nFROM ");
        return C0212a.p(sb2, getGroupMediaTable(groupType, str), " \nWHERE 1 \nGROUP BY burst_group_id  , bucket_id  \nHAVING count(burst_group_id)>1 and max(case when best_image is null or best_image=-1 then 0 else best_image end) ))");
    }

    public String getBurstShotOnlyQuery(String str) {
        return getGroupMediaOnlyQuery(GroupType.BURST, str);
    }

    public String getColumnGroupBestImage() {
        if (this.mSimilar) {
            return "case when A._id in (select sec_media_id from group_contents where group_type=2) then (select best_image from group_contents where sec_media_id=A._id) else A.best_image end";
        }
        return "A.best_image";
    }

    public String getColumnGroupMediaId() {
        if (this.mSimilar) {
            return "case when A._id in (select sec_media_id from group_contents where group_type=2) then (select group_id from group_contents where sec_media_id=A._id) else A.burst_group_id end";
        }
        return "A.burst_group_id";
    }

    public String getColumnGroupType() {
        String p6 = C0212a.p(new StringBuilder("(case when "), this.mIsEnabledGroup, " then A.group_type ");
        if (this.mSimilar) {
            return C0212a.A(p6, "when A._id in (select sec_media_id from group_contents where group_type=2) then (select group_type from group_contents where sec_media_id=A._id) else 0 end)");
        }
        return C0212a.A(p6, " else 0 end)");
    }

    public String getGroupBestMedia() {
        if (this.mSimilar) {
            StringBuilder sb2 = new StringBuilder("(A._id not in ");
            sb2.append(this.similarFiles);
            sb2.append(" and (");
            sb2.append(this.mIsSingleMedia);
            sb2.append(" OR ");
            sb2.append(this.mIsDisabledGroup);
            sb2.append(" OR (");
            sb2.append(this.mIsEnabledGroup);
            sb2.append(" AND A.best_image = 1) )OR A._id in ");
            return C0212a.p(sb2, this.SIMILAR_BEST_IMAGE_ID, ")");
        }
        StringBuilder sb3 = new StringBuilder("(");
        sb3.append(this.mIsSingleMedia);
        sb3.append(" OR ");
        sb3.append(this.mIsDisabledGroup);
        sb3.append(" OR (");
        return C0212a.p(sb3, this.mIsEnabledGroup, " AND A.best_image = 1) )");
    }

    public String getGroupFirstMedia(String str) {
        return "(A._id in (SELECT _id FROM  " + getGroupMediaTable(str) + " \n WHERE 1 \nGROUP BY burst_group_id, bucket_id \nHAVING max(coalesce(best_image, 0))<1 AND max(" + str + ") ))";
    }

    public String getGroupMediaCountTable() {
        String str;
        StringBuilder sb2 = new StringBuilder("case when ");
        sb2.append(this.mIsEnabledGroup);
        sb2.append(" then (SELECT count(*) FROM files where group_type=A.group_type and bucket_id=A.bucket_id and burst_group_id=coalesce(A.burst_group_id,0)");
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            str = " and is_pending=0";
        } else {
            str = "";
        }
        String p6 = C0212a.p(sb2, str, ")");
        if (this.mSimilar) {
            return C0086a.l(C0212a.t(p6, "when A._id in (select sec_media_id from group_contents where group_type=2) then "), DbTable.UNLOADED, " else 0 end");
        }
        return C0212a.A(p6, " else 0 end");
    }

    public String getGroupMediaSizeSumTable() {
        String str;
        StringBuilder sb2 = new StringBuilder("case when ");
        sb2.append(this.mIsEnabledGroup);
        sb2.append(" then (SELECT sum(case when is_cloud in(1,3) then _size else cloud_original_size end) FROM files where group_type=A.group_type and bucket_id=A.bucket_id and burst_group_id=coalesce(A.burst_group_id,0)");
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            str = " and is_pending=0";
        } else {
            str = "";
        }
        String p6 = C0212a.p(sb2, str, ")");
        if (this.mSimilar) {
            return C0212a.A(p6, "when A._id in (select sec_media_id from group_contents where group_type=2) then (select sum(case when is_cloud in(1,3) then _size else cloud_original_size end) from group_contents left outer join files on files._id=group_contents.sec_media_id  where files.bucket_id=A.bucket_id and group_contents.group_id in (select group_id from group_contents where sec_media_id=A._id)) else 0 end");
        }
        return C0212a.A(p6, " else 0 end");
    }

    public String getGroupMediaTable(String str) {
        String replace = this.mIsEnabledGroup.replace("A.group_type", "files.group_type");
        if (this.mSimilar) {
            return C0212a.m("(select files._id as _id, case when group_contents.group_type=2 then group_contents.group_id else files.burst_group_id end as burst_group_id, datetime,case when group_contents.group_type=2 then group_contents.best_image else files.best_image end as best_image, files.bucket_id from files left outer join group_contents on files._id=group_contents.sec_media_id where (burst_group_id > 0 and ", replace, ") or group_contents.group_type=2)");
        }
        StringBuilder sb2 = new StringBuilder("(select _id, burst_group_id, ");
        sb2.append(str);
        sb2.append(",best_image, bucket_id from files where burst_group_id > 0 and ");
        sb2.append(replace);
        return C0212a.p(sb2, this.checkIsPending, ")");
    }

    public String getSingleTakeChildVideoOnlyQuery(String str) {
        StringBuilder sb2 = new StringBuilder("A.media_type = ");
        sb2.append(MediaType.Video.toInt());
        sb2.append(" AND\nA.burst_group_id in (SELECT burst_group_id\nFROM files \nWHERE group_type = ");
        return C0086a.l(sb2, GroupType.SINGLE_TAKEN.value, "\nGROUP BY burst_group_id \nHAVING count(burst_group_id) > 1)");
    }

    public String getSingleTakeOnlyQuery(String str) {
        return getGroupMediaOnlyQuery(GroupType.SINGLE_TAKEN, str);
    }

    public GroupMediaQueryWithTable usedForCmh() {
        this.checkIsPending = "";
        return this;
    }

    private String getGroupMediaTable(GroupType groupType, String str) {
        if (GroupType.SIMILAR.equals(groupType)) {
            return "(select files._id as _id, case when group_contents.group_type=2 then group_contents.group_id else files.burst_group_id end as burst_group_id, datetime,case when group_contents.group_type=2 then group_contents.best_image else files.best_image end as best_image, files.bucket_id from files left outer join group_contents on files._id=group_contents.sec_media_id where group_contents.group_type=2)";
        }
        StringBuilder k = j.k("(select _id, burst_group_id, ", str, ",best_image, bucket_id from files where burst_group_id > 0 and group_type = ");
        k.append(groupType.type);
        return C0212a.p(k, this.checkIsPending, ")");
    }
}
