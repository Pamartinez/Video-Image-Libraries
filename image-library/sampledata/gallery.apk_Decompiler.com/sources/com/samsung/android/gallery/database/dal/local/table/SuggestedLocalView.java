package com.samsung.android.gallery.database.dal.local.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedLocalView extends DbTable {
    public SuggestedLocalView(QueryParams queryParams) {
        super(queryParams);
    }

    public String getOrderByType() {
        return "__Type DESC, __deleteType DESC";
    }

    public String getSelectionForIds(List<Long> list, boolean z) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        if (z) {
            str = "__mediaId";
        } else {
            str = "__absID";
        }
        sb2.append(str);
        sb2.append(" IN ");
        sb2.append(CursorHelper.joinIds(list));
        return sb2.toString();
    }

    public String getTableNameRaw() {
        return "suggested";
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("_id");
        this.mQueryBuilder.addProjection("__Type");
        this.mQueryBuilder.addProjection("__Title");
        this.mQueryBuilder.addProjection("__absID");
        this.mQueryBuilder.addProjection("__mediaId");
        this.mQueryBuilder.addProjection("__data");
        this.mQueryBuilder.addProjection("__width");
        this.mQueryBuilder.addProjection("__height");
        this.mQueryBuilder.addProjection("__orientation");
        this.mQueryBuilder.addProjection("__rect");
        this.mQueryBuilder.addProjection("__mediaType");
        this.mQueryBuilder.addProjection("__storageType");
        this.mQueryBuilder.addProjection(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
        this.mQueryBuilder.addProjection("extraValue");
        this.mQueryBuilder.addProjection("extraData");
        this.mQueryBuilder.addProjection("__deleteType");
        this.mQueryBuilder.addProjection("video_thumb_start_time");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "N");
    }

    public String tag() {
        return "SuggestedLocalView";
    }

    public void onConstruct() {
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
