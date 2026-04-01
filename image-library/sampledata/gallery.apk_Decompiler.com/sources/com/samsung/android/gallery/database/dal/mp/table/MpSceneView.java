package com.samsung.android.gallery.database.dal.mp.table;

import B5.e;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.support.type.CategoryType;
import com.samsung.android.gallery.support.type.SubCategoryType;
import com.samsung.android.gallery.support.utils.Features;
import i.C0212a;
import java.util.ArrayList;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpSceneView extends MpTagView {
    private static final ArrayList<String> EXCLUDING_SCENE_NAMES = new ArrayList<String>() {
        {
            add("scenery");
            add("documents");
            if (Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS)) {
                addAll(SubCategoryType.DOCUMENTS_SUB_GROUP_ONEUI_61);
            }
            if (Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP)) {
                addAll(SubCategoryType.INFORMATIVE_DOCUMENTS_SUB_GROUP);
            }
            if (Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP_V2)) {
                addAll(SubCategoryType.INFORMATIVE_DOCUMENTS_SUB_GROUP_V2);
            }
        }
    };

    public MpSceneView(QueryParams queryParams) {
        super(queryParams);
    }

    private void filterDocument() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(columnToLowerCase(getColumnNameTagData()) + " in " + getCategoryName(CategoryType.VISUAL_SEARCH_DOCUMENT_CATEGORY));
    }

    private void filterNewDocuments61() {
        StringBuilder sb2 = new StringBuilder("(");
        ArrayList arrayList = new ArrayList(SubCategoryType.getDocumentSubGroupList());
        arrayList.remove(SubCategoryType.getOtherDocumentsName());
        sb2.append("T.parent_name in " + convertStringKeysToArray(arrayList) + " COLLATE NOCASE");
        sb2.append(" OR ");
        sb2.append("T.parent_name is '" + SubCategoryType.getOtherDocumentsName() + "' COLLATE NOCASE and T.sec_media_id not in (select sec_media_id from scene where parent_name in " + convertStringKeysToArray(arrayList) + ")");
        sb2.append(" OR T.scene_name is 'doc_legal_documents' )");
        this.mQueryBuilder.andCondition(sb2.toString());
    }

    private void filterNewDocuments71() {
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append("T.parent_name is 'doc_documents' COLLATE NOCASE and T.scene_name is not '" + SubCategoryType.getOtherDocumentsName() + "' COLLATE NOCASE");
        sb2.append(" OR (T.parent_name is 'doc_documents' COLLATE NOCASE and ");
        sb2.append("T.scene_name is '" + SubCategoryType.getOtherDocumentsName() + "' COLLATE NOCASE");
        sb2.append(" and T.sec_media_id not in (select sec_media_id from scene where scene_name is 'doc_qr_codes')))");
        this.mQueryBuilder.andCondition(sb2.toString());
    }

    private void filterNewDocumentsInternal() {
        if (isNewDocumentFeature()) {
            if (this.mParams.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_71) {
                filterNewDocuments71();
            } else {
                filterNewDocuments61();
            }
            this.mQueryBuilder.replaceProjectionByAlias("'Documents'", "__subGroupCategory");
        }
    }

    private boolean isNewDocumentFeature() {
        QueryParams queryParams = this.mParams;
        if (queryParams.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_71 || queryParams.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_611 || queryParams.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_61) {
            return true;
        }
        return false;
    }

    public void addOrderBySceneOrder() {
        this.mQueryBuilder.addOrderBy("__scene_order ASC");
    }

    public void addProjectionSceneOrder() {
        this.mQueryBuilder.addProjection(ScreenShotFilterType.projection("T.scene_name"), "__scene_order");
    }

    public void addScreenShotFilterFileIdsProjection() {
        this.mQueryBuilder.addProjection(getColumnNameTagData(), "__subCategory");
        this.mQueryBuilder.addProjection("group_concat(distinct(T.sec_media_id) order by A.datetime DESC, A._id DESC)", "__fileIds");
    }

    public MpSceneView createSceneView() {
        return new MpSceneView(this.mParams);
    }

    public void filterAllDocuments() {
        filterSceneOrSubScene();
        if (this.mParams.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_61) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition(getColumnNameTagData() + " in " + convertStringKeysToArray(SubCategoryType.DOCUMENTS_SUB_GROUP_ONEUI_61) + " COLLATE NOCASE");
            return;
        }
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.andCondition(getColumnNameTagData() + " ='documents'");
    }

    public void filterCapture() {
        StringJoiner stringJoiner = new StringJoiner("','", "('", "')");
        ScreenShotFilterType.sSubCategoryInfo.keySet().forEach(new e(stringJoiner, 2));
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("T.scene_name in " + stringJoiner);
    }

    public void filterDocuments() {
        if (!this.IS_GTE_Q) {
            filterSceneOrSubScene();
            filterDocument();
        } else if (isNewDocumentFeature()) {
            filterNewDocumentsInternal();
        } else {
            this.mQueryBuilder.andCondition("T.parent_name = 'documents' COLLATE NOCASE");
        }
    }

    public void filterOtherDocuments() {
        filterSceneOrSubScene();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getColumnNameTagData() + " ='documents'");
        MpSceneView createSceneView = createSceneView();
        createSceneView.filterDocuments();
        createSceneView.getQueryBuilder().clearProjection();
        createSceneView.getQueryBuilder().addProjection("A._id", "__absID");
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.andCondition("__absID not in (" + createSceneView.buildSelectQuery().buildSql() + ")");
        this.mQueryBuilder.replaceProjectionByAliasWithValue("Other Documents", "__subCategory");
    }

    public void filterParentScene(String str) {
        C0212a.w("T.parent_name COLLATE NOCASE in ('", QueryBuilder.escapeString(str), "')", this.mQueryBuilder);
    }

    public void filterRemainScenesNewCategory() {
        if (this.IS_GTE_Q) {
            QueryParams queryParams = this.mParams;
            if (queryParams.VISUAL_SEARCH_NEW_DOCUMENTS_ONEUI_61 || queryParams.VISUAL_SEARCH_ONEUI_30) {
                QueryBuilder queryBuilder = this.mQueryBuilder;
                queryBuilder.andCondition("((T.parent_name is null and T.scene_name COLLATE NOCASE not in " + convertStringKeysToArray(EXCLUDING_SCENE_NAMES) + ") or T.parent_name COLLATE NOCASE in ('scenery'))");
                return;
            }
            this.mQueryBuilder.andCondition("((T.parent_name is null and T.scene_name COLLATE NOCASE not in ('documents')) or T.parent_name COLLATE NOCASE not in ('documents'))");
            return;
        }
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.andCondition(columnToLowerCase(getColumnNameTagData()) + " not in " + getCategoryName(CategoryType.VISUAL_SEARCH_ALL_OTHER_CATEGORY));
    }

    public void filterScene(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            C0212a.w("T.parent_name is '", str, "'", this.mQueryBuilder);
        }
        if (!TextUtils.isEmpty(str2)) {
            C0212a.w("T.scene_name is '", str2, "'", this.mQueryBuilder);
        }
    }

    public void filterScenery() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.andCondition("T.parent_name COLLATE NOCASE in ('scenery')");
        }
    }

    public void filterThings() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.andCondition("((T.parent_name is null and T.scene_name COLLATE NOCASE not in ('scenery', 'documents')) or T.parent_name COLLATE NOCASE not in ('scenery', 'documents'))");
        }
    }

    public String getColumnNameSceneRegionRatio() {
        return "T.scene_region_ratio";
    }

    public String getColumnNameSceneScore() {
        return "T.scene_score";
    }

    public String getColumnNameTagData() {
        return "T.scene_name";
    }

    public String getColumnNameTagType() {
        return "CAST(4 AS NUMERIC)";
    }

    public void groupBySceneName() {
        this.mQueryBuilder.groupBy("T.scene_name");
    }

    public void havingLatestMedia() {
        if (this.IS_GTE_Q) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.having("max(A." + this.mFilesTable.getColumnDateTaken() + ") and A.media_type in (1, 3)");
            return;
        }
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.having("max(A." + this.mFilesTable.getColumnDateTaken() + ") and A.media_type in (1)");
    }

    public void havingMaxScore() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.having("max(T.scene_score) and A.media_type in (1, 3)");
        } else {
            this.mQueryBuilder.having("max(T.scene_score) and A.media_type=1");
        }
    }

    public void havingMedia() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.having("A.media_type in (1, 3)");
        } else {
            this.mQueryBuilder.having("A.media_type in (1)");
        }
    }

    public void havingMinimumTagCount() {
        if (this.IS_GTE_Q) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.having("max(" + getColumnNameSceneScore() + " ) and count(distinct __absID) >= 10 and A.media_type in (1, 3)");
            return;
        }
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.having("max(" + getColumnNameSceneScore() + " ) and count(distinct __absID) >= 10 and A.media_type=1");
    }

    public void onConstruct() {
        super.onConstruct();
        this.mFilesTable.clearSelection();
        this.mFilesTable.filterIsPending();
        this.mFilesTable.filterHidden();
        this.mFilesTable.filterMountedVolume();
        this.mFilesTable.filterGalleryMedia(this.mParams.getMediaTypeFilter());
        this.mFilesTable.filterStorageType();
    }

    public void setDefaultProjection() {
        super.setDefaultProjection();
        this.mQueryBuilder.removeProjectionByAlias("__countryCode");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
        this.mQueryBuilder.addInnerJoin("scene AS T", "A._id = T.sec_media_id");
    }

    public String tag() {
        return "MpSceneView";
    }

    public void filterSceneOrSubScene() {
    }
}
