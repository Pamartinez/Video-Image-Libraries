package com.samsung.android.gallery.database.dal.abstraction.table;

import com.samsung.android.gallery.database.dal.abstraction.query.IGroupMediaQuery;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import i.C0212a;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupMediaQuery implements IGroupMediaQuery {
    public String getBurstShotOnlyQuery(String str) {
        return "(A._id in (SELECT _id\nFROM " + getBurstShotTable(str) + " \n WHERE 1 \nGROUP BY group_id  , bucket_id \nHAVING count(group_id)>1 and sum(case when best_image is null or best_image=-1 then 0 else best_image end)=0  and max(" + str + ") \nUNION ALL\nSELECT _id\nFROM " + getBurstShotTable(str) + " \nWHERE 1 \nGROUP BY group_id  , bucket_id  \nHAVING count(group_id)>1 and max(case when best_image is null or best_image=-1 then 0 else best_image end) ))";
    }

    public String getBurstShotTable(String str) {
        return C0212a.m("(select _id, group_id, ", str, ",best_image, bucket_id from files where group_id > 0)");
    }

    public String getColumnGroupBestImage() {
        return "A.best_image";
    }

    public String getColumnGroupMediaId() {
        return BundleKey.GROUP_ID;
    }

    public String getColumnGroupType() {
        return "(case when A.group_type in (1,3) then A.group_type else 0 end)";
    }

    public String getGroupBestMedia() {
        return "(coalesce(A.group_id,0)<= 0 OR (A.group_id > 0 AND A.best_image = 1))";
    }

    public String getGroupFirstMedia(String str) {
        return "(A._id in (SELECT _id FROM  " + getGroupMediaTable(str) + " \n WHERE 1 \nGROUP BY group_id, bucket_id \nHAVING max(coalesce(best_image, 0))<1 AND max(" + str + ") ))";
    }

    public String getGroupMediaCountTable() {
        return "case when coalesce(A.group_id, 0)=0 then 0 else (SELECT count(*) FROM files where bucket_id=A.bucket_id and group_id=coalesce(A.group_id,0)) end";
    }

    public String getGroupMediaTable(String str) {
        return C0212a.m("(select _id, group_id, ", str, ",best_image, bucket_id from files where group_id > 0)");
    }

    public String getSingleTakeChildVideoOnlyQuery(String str) {
        return "0";
    }

    public String getSingleTakeOnlyQuery(String str) {
        return "0";
    }
}
