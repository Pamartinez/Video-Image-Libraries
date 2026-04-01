package com.samsung.android.gallery.database.dal.mp.table;

import C3.C0392b;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.support.type.ExpressionsType;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpFacesView extends BaseView {
    protected MpFacesTable mFacesTable;

    /* renamed from: com.samsung.android.gallery.database.dal.mp.table.MpFacesView$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$support$type$ExpressionsType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.support.type.ExpressionsType[] r0 = com.samsung.android.gallery.support.type.ExpressionsType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$support$type$ExpressionsType = r0
                com.samsung.android.gallery.support.type.ExpressionsType r1 = com.samsung.android.gallery.support.type.ExpressionsType.EXPRESSION_DISLIKE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$support$type$ExpressionsType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.support.type.ExpressionsType r1 = com.samsung.android.gallery.support.type.ExpressionsType.EXPRESSION_HAPPY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$support$type$ExpressionsType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.support.type.ExpressionsType r1 = com.samsung.android.gallery.support.type.ExpressionsType.EXPRESSION_NEUTRAL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$support$type$ExpressionsType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.support.type.ExpressionsType r1 = com.samsung.android.gallery.support.type.ExpressionsType.EXPRESSION_SURPRISE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.mp.table.MpFacesView.AnonymousClass1.<clinit>():void");
        }
    }

    public MpFacesView(QueryParams queryParams) {
        super(queryParams);
    }

    private void filterExpressionWithOnlyFilesTable(ExpressionsType expressionsType, boolean z) {
        this.mQueryBuilder.clearJoinFrom();
        this.mFacesTable.clearSelection();
        if (z) {
            resetProjectionForID();
        } else {
            this.mQueryBuilder.clearProjection();
            this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        }
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A._id in (" + this.mFacesTable.getRawQueryMediaIdForExpression(expressionsType.getExpressionColumn()) + ")");
        if (this.mParams.useDefaultOrder) {
            this.mQueryBuilder.clearOrderBy();
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.addOrderBy("A." + this.mFilesTable.getColumnDateTaken() + " DESC");
            this.mQueryBuilder.addOrderBy("A._id DESC");
        }
    }

    public static MpFacesView getExpressionFileView(QueryParams queryParams, String str) {
        MpFacesView mpFacesView = new MpFacesView(queryParams);
        mpFacesView.setExpressionFilter(getType(str));
        mpFacesView.modifyForPictures();
        return mpFacesView;
    }

    public static ExpressionsType getType(String str) {
        return (ExpressionsType) Arrays.stream(ExpressionsType.values()).filter(new C0392b(str, 28)).findFirst().orElse(ExpressionsType.EXPRESSION_UNKNOWN);
    }

    public void addOrderByDate() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy("A." + this.mFilesTable.getColumnDateTaken() + " DESC, A._id DESC");
    }

    public Query buildSelectQuery() {
        if (this.IS_GTE_Q) {
            this.mFilesTable.appendVolumeNameForFaces();
        }
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        this.mQueryBuilder.andCondition(this.mFacesTable.getWhere());
        return super.buildSelectQuery();
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public void filterDisLike() {
        filterDisLike(false);
    }

    public void filterFileIds(String str) {
        this.mFilesTable.filterIds(str);
    }

    public void filterNeutral() {
        filterNeutral(false);
    }

    public void filterSmile() {
        filterSmile(false);
    }

    public void filterSurprise() {
        filterSurprise(false);
    }

    public void groupBy(String str) {
        this.mQueryBuilder.groupBy(str);
    }

    public void modifyForPictures() {
        this.mQueryBuilder.groupBy("A._id");
        this.mQueryBuilder.removeProjectionByAlias("__mainCategory");
    }

    public void onConstruct() {
        super.onConstruct();
        this.mFacesTable = new MpFacesTable(this.mParams);
    }

    public void resetProjectionForAutoComplete() {
        this.mFilesTable.resetProjectionForAutoComplete();
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
    }

    public void setDefaultCondition() {
        this.mFilesTable.filterGroupMediaBest(true);
        this.mFilesTable.filterGalleryMedia();
    }

    public void setDefaultOrder() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy("A." + this.mFilesTable.getColumnDateTaken() + " DESC");
        this.mQueryBuilder.addOrderBy("A._id DESC");
        this.mQueryBuilder.addOrderBy("F._id ASC");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        this.mQueryBuilder.addProjection((List<String>) this.mFacesTable.getProjectionArray());
        this.mQueryBuilder.replaceProjectionByAlias("case when F.pos_ratio is null then coalesce(cast(1.0*F.pos_left/A.width as text) || ',' || cast(1.0*F.pos_top/A.height as text) ||',' || cast(1.0*F.pos_right/A.width as text) ||',' || cast( 1.0*F.pos_bottom/A.height as text),'1,1,1,1') else F.pos_ratio end", "__creatureFacePosRatio");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
        QueryBuilder queryBuilder = this.mQueryBuilder;
        String tableName = this.mFacesTable.getTableName();
        queryBuilder.addInnerJoin(tableName, this.mFacesTable.getFkForFiles() + "=" + this.mFilesTable.getAliasColumnName("_id"));
        this.mQueryBuilder.addProjection("F.group_id", "__subCategory");
    }

    public void setExpressionFilter(ExpressionsType expressionsType) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$support$type$ExpressionsType[expressionsType.ordinal()];
        if (i2 == 1) {
            filterDisLike();
        } else if (i2 == 2) {
            filterSmile();
        } else if (i2 == 3) {
            filterNeutral();
        } else if (i2 == 4) {
            filterSurprise();
        }
    }

    public String tag() {
        return "MpFacesView";
    }

    public void filterDisLike(boolean z) {
        filterExpressionWithOnlyFilesTable(ExpressionsType.EXPRESSION_DISLIKE, z);
    }

    public void filterNeutral(boolean z) {
        filterExpressionWithOnlyFilesTable(ExpressionsType.EXPRESSION_NEUTRAL, z);
    }

    public void filterSmile(boolean z) {
        filterExpressionWithOnlyFilesTable(ExpressionsType.EXPRESSION_HAPPY, z);
    }

    public void filterSurprise(boolean z) {
        filterExpressionWithOnlyFilesTable(ExpressionsType.EXPRESSION_SURPRISE, z);
    }
}
