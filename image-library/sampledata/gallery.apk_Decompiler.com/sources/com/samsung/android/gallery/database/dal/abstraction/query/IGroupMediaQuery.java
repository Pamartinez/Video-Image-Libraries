package com.samsung.android.gallery.database.dal.abstraction.query;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IGroupMediaQuery {
    String getBurstShotOnlyQuery(String str);

    String getBurstShotOnlyQueryIgnoreBestPolicy(String str) {
        return "(A._id in (select _id from files where burst_group_id > 0 and group_type = 1 GROUP BY burst_group_id  , bucket_id HAVING count(burst_group_id)>1))";
    }

    String getColumnGroupBestImage();

    String getColumnGroupMediaId();

    String getColumnGroupType();

    String getGroupBestMedia();

    String getGroupFirstMedia(String str);

    String getGroupMediaCountTable();

    String getGroupMediaSizeSumTable() {
        return "";
    }

    String getSingleTakeChildVideoOnlyQuery(String str);

    String getSingleTakeOnlyQuery(String str);
}
