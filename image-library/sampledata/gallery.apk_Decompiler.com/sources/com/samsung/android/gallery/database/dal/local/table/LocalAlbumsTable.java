package com.samsung.android.gallery.database.dal.local.table;

import N2.j;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import i.C0212a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalAlbumsTable extends DbTable {
    public LocalAlbumsTable(QueryParams queryParams) {
        super(queryParams);
    }

    public String exceptAutoAlbum() {
        return "__albumType != 4";
    }

    public String exceptVirtualAlbum() {
        return "__albumType != 3";
    }

    public String filterFolderData() {
        return "(album_count is null OR album_count > -1)";
    }

    public String filterForExcludeMergeAlbum() {
        return "((coalesce(__albumType, 0)) != " + AlbumType.Merged.toInt() + ")";
    }

    public String filterForHidden() {
        return "(coalesce(__ishide, -1) < 1)";
    }

    public String filterFullFolder() {
        return "album_count > 0";
    }

    public String filterTopAlbumsData() {
        return "__albumLevel > 0";
    }

    public String filterVolumeName(boolean z) {
        if (!this.IS_GTE_Q) {
            return null;
        }
        return C0212a.p(j.k("(__absPath is not null AND (__volumeName in (", FileUtils.getMountedVolumeNames(), ")"), z ? " OR __volumeName is null" : "", ")) OR (__absPath is null )");
    }

    public String getSelectionForAlbum(int i2) {
        return C0086a.i(i2, "__bucketID = ");
    }

    public String getSelectionForBucketIds(List<Integer> list) {
        return "__bucketID IN " + CursorHelper.joinIds(list);
    }

    public String getSelectionForFolder(int i2) {
        return C0086a.i(i2, "folder_id = ");
    }

    public String getSelectionForFolderIds(List<Integer> list) {
        return "folder_id IN " + CursorHelper.joinIds(list);
    }

    public String getSelectionForValidFolder() {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return "(folder_id NOT NULL OR folder_id != '')";
        }
        return "(folder_id NOT NULL OR folder_id != '') AND (folder_id not in (select __bucketID from " + getTableNameRaw() + " where __albumType=2 and (folder_id IS NULL OR folder_id=0)))";
    }

    public String getTableNameRaw() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return "mxalbum";
        }
        return "album";
    }

    public String getWhereForAlbum(boolean z) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(filterForHidden());
        String filterVolumeName = filterVolumeName(z);
        if (filterVolumeName != null) {
            sb2.append(" AND ");
            sb2.append(filterVolumeName);
        }
        return sb2.toString();
    }

    public void resetProjectionForAutoComplete() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("default_cover_path", "__absPath");
        this.mQueryBuilder.addProjection("''", "__sceneRegion");
        this.mQueryBuilder.addProjection("1", "__mediaType");
        this.mQueryBuilder.addProjection("''", "__orientation");
        this.mQueryBuilder.addProjection("cover_rect");
        this.mQueryBuilder.addProjection("1", "__storageType");
        this.mQueryBuilder.addProjection("__Title");
        this.mQueryBuilder.addProjection("__Title", "__subCategory");
        this.mQueryBuilder.replaceProjectionByAliasWithValue("Search Auto Complete", "__mainCategory");
    }

    public String selectionForHidden() {
        return "(coalesce(__ishide, -1) == 1)";
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("__bucketID");
        this.mQueryBuilder.addProjection("__Title");
        this.mQueryBuilder.addProjection("folder_id");
        this.mQueryBuilder.addProjection("folder_name");
        this.mQueryBuilder.addProjection("default_cover_path");
        this.mQueryBuilder.addProjection("cover_path");
        this.mQueryBuilder.addProjection("cover_rect");
        this.mQueryBuilder.addProjection("album_order");
        this.mQueryBuilder.addProjection("album_count");
        this.mQueryBuilder.addProjection("__ishide");
        this.mQueryBuilder.addProjection("__sefFileType");
        this.mQueryBuilder.addProjection("__isDrm");
        this.mQueryBuilder.addProjection("__dateModified");
        this.mQueryBuilder.addProjection("__volumeName");
        this.mQueryBuilder.addProjection("album_sync_status");
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            this.mQueryBuilder.addProjection("__albumLevel");
            this.mQueryBuilder.addProjection("__albumType");
            this.mQueryBuilder.addProjection("__albumShowInfo");
        }
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "A");
    }

    public String tag() {
        return "LocalAlbumsTable";
    }

    public String filterVolumeName() {
        if (!this.IS_GTE_Q) {
            return null;
        }
        return "(__volumeName in (" + FileUtils.getMountedVolumeNames() + "))";
    }

    public void onConstruct() {
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
