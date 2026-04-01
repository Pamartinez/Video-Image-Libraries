package com.samsung.android.gallery.database.dal.mp.helper;

import A.a;
import N2.j;
import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureFacesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureTagTable;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureView;
import com.samsung.android.gallery.database.dal.mp.table.MpPetFacesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpPetTagTable;
import com.samsung.android.gallery.database.dal.mp.table.MpPetView;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PetApi extends CreatureApi {
    private String filterRecommendedIds(List<Long> list) {
        return "recommended_id IN " + CursorHelper.joinIds(list);
    }

    private long findPetIdForTagged(long j2, String str) {
        if (isDefault(j2)) {
            return insert(str);
        }
        return updateName(j2, str);
    }

    private ContentProviderOperation getHideOperation(List<Long> list, int i2) {
        return ContentProviderOperation.newUpdate(CmhUri.getPetFaces()).withValue("hide", Integer.valueOf(i2)).withSelection(filterRecommendedIds(list), (String[]) null).build();
    }

    private ContentValues getNameContentValues(String str) {
        return C0086a.c("name", str);
    }

    private long updateName(long j2, String str) {
        this.mQueryExecutor.getContentResolver().update(CmhUri.getPetTags(), getNameContentValues(str), a.f("_id = ", j2), (String[]) null);
        return j2;
    }

    private void updatePetFaceInBatch(ArrayList<ContentProviderOperation> arrayList) {
        if (arrayList.isEmpty()) {
            Log.w(this.TAG, "updatePetFaceInBatch : operations is empty");
        }
        try {
            this.mQueryExecutor.getContentResolver().applyBatch(CmhUri.getPetFaces().getAuthority(), arrayList);
        } catch (Exception e) {
            j.v("updatePetFaceInBatch failed : ", e, this.TAG);
        }
    }

    public ContentProviderOperation.Builder createContentProviderOperationBuilder(Uri uri, SearchRemoveInfo searchRemoveInfo) {
        return super.createContentProviderOperationBuilder(uri, searchRemoveInfo);
    }

    public MpCreatureView createCreatureView() {
        return new MpPetView(this.mParams);
    }

    public MpCreatureFacesTable createFacesTable() {
        return new MpPetFacesTable(this.mParams);
    }

    public MpCreatureTagTable createTagTable() {
        return new MpPetTagTable(this.mParams);
    }

    public long edit(String str, String str2, long j2) {
        long identityId = IdentityCreatureUtil.getIdentityId(str2);
        if (!IdentityCreatureUtil.isAssignedCreature(str2)) {
            identityId = 1;
        }
        if (j2 <= 1) {
            j2 = findPetIdForTagged(identityId, str);
        }
        updateId(str2, j2);
        return j2;
    }

    public Uri getCreatureFaceUri(boolean z) {
        return CmhUri.getPetFaces();
    }

    public String getCreatureIdColumn() {
        return "cluster_info_id";
    }

    public String getCreatureType() {
        return MpCreatureView.CreatureType.PET.name();
    }

    public String getFaceTableName() {
        return "cluster_rect";
    }

    public int handleRemoveTo(SearchRemoveInfo searchRemoveInfo) {
        return remove(CmhUri.getPetFaces(), searchRemoveInfo);
    }

    public long insert(String str) {
        Uri insert = this.mQueryExecutor.getContentResolver().insert(CmhUri.getPetTags(), getNameContentValues(str));
        if (insert != null) {
            return ContentUris.parseId(insert);
        }
        return -1;
    }

    public String tag() {
        return "PetApi";
    }

    public void updateCreatureHideState(List<Long> list, List<Long> list2) {
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            if (!list.isEmpty()) {
                hideCreature(list);
            }
            if (!list2.isEmpty()) {
                unHideCreature(list2);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            arrayList.add(getHideOperation(list, 1));
        }
        if (!list2.isEmpty()) {
            arrayList.add(getHideOperation(list2, 0));
        }
        updatePetFaceInBatch(arrayList);
    }

    public boolean updateFace(ContentValues contentValues, String str, String[] strArr) {
        if (this.mQueryExecutor.getContentResolver().update(CmhUri.getPetFaces(), contentValues, str, strArr) > 0) {
            return true;
        }
        return false;
    }

    public void updateId(String str, long j2) {
        updateByUnifiedId(IdentityCreatureUtil.getUnifiedIdentityId(str), j2);
    }

    public void updateId(long[] jArr, long j2) {
        updateByUnifiedId(jArr, j2);
    }

    public void delete(long[] jArr) {
    }
}
