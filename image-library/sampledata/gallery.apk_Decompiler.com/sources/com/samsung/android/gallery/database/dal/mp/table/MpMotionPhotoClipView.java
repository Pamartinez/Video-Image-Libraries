package com.samsung.android.gallery.database.dal.mp.table;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.type.SubCategoryType;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import i.C0212a;
import java.util.List;
import java.util.stream.Collectors;
import q8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpMotionPhotoClipView extends BaseView {
    private boolean ENABLE_DEVELOPER_TEST = false;

    public MpMotionPhotoClipView(QueryParams queryParams) {
        super(queryParams);
    }

    private String getDocumentTypes() {
        if (Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP_V2)) {
            return "('doc_documents')";
        }
        if (Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS)) {
            return (String) SubCategoryType.getDocumentSubGroupList().stream().map(new a(13)).collect(Collectors.joining(ArcCommonLog.TAG_COMMA, "(", ")"));
        }
        return "('documents')";
    }

    private String getFilterHidden() {
        return C0212a.p(new StringBuilder("A.bucket_id NOT IN (select bucket_id from (select * from files where is_hide=1) where media_type in (1,3) and bucket_id not in "), getNonHideBucketIds(), " group by bucket_id)");
    }

    private String getNonHideBucketIds() {
        return CursorHelper.joinIds(BucketUtils.values());
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        return super.buildSelectQuery();
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public void onConstruct() {
        super.onConstruct();
        this.mFilesTable.clearSelection();
        this.mFilesTable.filterMountedVolume();
    }

    public void resetDefaultProjectionForCover() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("A._id", "__absID");
        this.mQueryBuilder.addProjection("A.media_id", "__mediaId");
        this.mQueryBuilder.addProjection("A.title", "__Title");
        this.mQueryBuilder.addProjection("IFNULL(A._data, A.cloud_thumb_path)", "__data");
        this.mQueryBuilder.addProjection("A.smartcrop_rect_ratio", "__rect");
        this.mQueryBuilder.addProjection("A.media_type", "__mediaType");
        this.mQueryBuilder.addProjection("A.orientation", "__orientation");
        this.mQueryBuilder.addProjection("A.width", "__width");
        this.mQueryBuilder.addProjection("A.height", "__height");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("(select count(*) from files as A where " + getWhere() + ")", KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition("A.sef_file_type in (2608)");
        if (!this.ENABLE_DEVELOPER_TEST) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition("A._id in (SELECT sec_media_id FROM scene WHERE scene_name COLLATE NOCASE in " + getDocumentTypes() + ")");
            this.mQueryBuilder.andCondition("A._id not in (SELECT sec_media_id FROM scene WHERE scene_name COLLATE NOCASE in ('people'))");
            this.mQueryBuilder.andCondition(getFilterHidden());
        }
        filterLocalOnly(true);
    }

    public void setDefaultOrder() {
        this.mQueryBuilder.addOrderBy("A._id DESC");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        this.mQueryBuilder.addProjection("A.smartcrop_rect_ratio", "__sceneRegion");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
        if (this.ENABLE_DEVELOPER_TEST) {
            this.mQueryBuilder.addLeftOuterJoin("scene as SC", "A._id = SC.sec_media_id");
        } else {
            this.mQueryBuilder.addInnerJoin("scene as SC", "A._id = SC.sec_media_id");
        }
    }

    public String tag() {
        return "MpMotionPhotoClipView";
    }
}
