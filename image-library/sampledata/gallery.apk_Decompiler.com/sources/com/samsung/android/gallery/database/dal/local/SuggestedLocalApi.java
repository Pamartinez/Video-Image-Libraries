package com.samsung.android.gallery.database.dal.local;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryExecutor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.table.SuggestedLocalView;
import com.samsung.android.gallery.database.dbtype.SuggestedType;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import i.C0212a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedLocalApi {
    private static final Uri SUGGESTED_URI = LocalDatabase.SUGGEST_URI;
    QueryExecutor mQueryExecutor = new QueryExecutor();

    public ContentProviderOperation getDeleteOperation(List<Long> list, boolean z) {
        SuggestedLocalView suggestedLocalView = new SuggestedLocalView(new QueryParams());
        ContentProviderOperation.Builder newDelete = ContentProviderOperation.newDelete(SUGGESTED_URI);
        newDelete.withSelection(suggestedLocalView.getSelectionForIds(list, z), (String[]) null);
        return newDelete.build();
    }

    public ContentProviderOperation getInsertOperation(ContentValues contentValues) {
        ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(SUGGESTED_URI);
        newInsert.withValues(contentValues);
        return newInsert.build();
    }

    public Uri getTableUri() {
        return SUGGESTED_URI;
    }

    public ContentProviderOperation getUpdateOperation(ContentValues contentValues, List<Long> list, boolean z) {
        SuggestedLocalView suggestedLocalView = new SuggestedLocalView(new QueryParams());
        ContentProviderOperation.Builder newUpdate = ContentProviderOperation.newUpdate(SUGGESTED_URI);
        newUpdate.withSelection(suggestedLocalView.getSelectionForIds(list, z), (String[]) null);
        newUpdate.withValues(contentValues);
        return newUpdate.build();
    }

    public Cursor loadSuggestedInfo() {
        SuggestedLocalView suggestedLocalView = new SuggestedLocalView(new QueryParams());
        String[] strArr = (String[]) suggestedLocalView.getProjectionArray().toArray(new String[0]);
        String str = "(__Type = " + SuggestedType.CLEANOUT.toInt();
        if (PreferenceFeatures.OneUi41.CLEANOUT_MOTION_PHOTO_CLIP) {
            StringBuilder t = C0212a.t(str, " OR __Type = ");
            t.append(SuggestedType.CLEANOUT_MOTION_PHOTO_CLIP.toInt());
            str = t.toString();
        }
        if (PreferenceFeatures.OneUi41.CLEANOUT_DUPLICATED_IMAGE) {
            StringBuilder t3 = C0212a.t(str, " OR __Type = ");
            t3.append(SuggestedType.CLEANOUT_DUPLICATED_IMAGE.toInt());
            str = t3.toString();
        }
        if (PreferenceFeatures.OneUi41.REMASTER_SUGGESTIONS) {
            StringBuilder t5 = C0212a.t(str, " OR __Type = ");
            t5.append(SuggestedType.REMASTER.toInt());
            str = t5.toString();
        }
        if (PreferenceFeatures.OneUi41.HIGHLIGHT_SUGGESTIONS) {
            StringBuilder t6 = C0212a.t(str, " OR __Type = ");
            t6.append(SuggestedType.HIGHLIGHT.toInt());
            str = t6.toString();
        }
        if (PreferenceFeatures.OneUi41.PORTRAIT_SUGGESTIONS) {
            StringBuilder t7 = C0212a.t(str, " OR __Type = ");
            t7.append(SuggestedType.PORTRAIT.toInt());
            str = t7.toString();
        }
        if (PocFeatures.isEnabled(PocFeatures.CleanOutBurstSimilar)) {
            StringBuilder t10 = C0212a.t(str, " OR __Type = ");
            t10.append(SuggestedType.CLEANOUT_BURST_SIMILAR.toInt());
            str = t10.toString();
        }
        if (PreferenceFeatures.OneUi41.DISABLE_CLEANOUT_BAD_QUALITY_IMAGE) {
            str = C0212a.A(str, ") AND ( __deleteType != 2");
        }
        return this.mQueryExecutor.getCursor(SUGGESTED_URI, strArr, C0212a.A(str, ") AND __deleteType is not null group by __Type, __deleteType"), (String[]) null, suggestedLocalView.getOrderByType(), "getSuggestedInfo");
    }
}
