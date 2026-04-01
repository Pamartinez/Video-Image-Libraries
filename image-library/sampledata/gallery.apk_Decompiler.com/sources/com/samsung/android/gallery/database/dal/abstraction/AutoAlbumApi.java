package com.samsung.android.gallery.database.dal.abstraction;

import android.database.Cursor;
import android.net.Uri;
import android.util.Pair;
import com.samsung.android.gallery.database.dbtype.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AutoAlbumApi {
    void addAutoAlbumContents(String str, long j2);

    void addAutoAlbumContents(String str, List<Long> list);

    void addAutoAlbumRules(List<Pair<Long, String>> list, long j2, int i2);

    void addFamilyAlbumContents(String str, long j2);

    void changeAutoUpdatingProperty(int i2, long j2);

    Uri createAutoAlbum(String str, String str2);

    Uri createFamilyAutoAlbum(String str, String str2);

    int deleteAutoAlbum(String str);

    void deleteFamilyAutoAlbum();

    int getAlbumCount(int i2, MediaType mediaType);

    int[] getAlbumCount(int i2);

    Cursor getAutoAlbumCursor();

    Cursor getAutoAlbumIds(String str, boolean z);

    Cursor getAutoAlbumPicturesCursor(int i2, int i7);

    Cursor getAutoAlbumPicturesCursor(Collection<Integer> collection, int i2);

    Cursor getAutoAlbumPicturesDateCursor(int i2, int i7);

    Cursor getAutoUpdateStateCursor(int i2);

    Cursor getCreatureRuleCursor(long j2);

    Cursor getFamilyAutoAlbumIdCursor(String str);

    Cursor getSuggestedContentsRuleCursor(long j2);

    int insertSplitIds(long j2, long[] jArr, String[] strArr, boolean z) {
        return 0;
    }

    boolean isAutoAlbumAlreadyExist(String str);

    void removeAutoAlbumContent(long j2, long j3);

    void removeAutoAlbumContents(ArrayList<Long> arrayList, long j2);

    void removeAutoAlbumRules(List<Long> list, long j2, int i2);

    int removeDuplicatedRecord(long j2) {
        return 0;
    }

    int removeUnmergedId(long j2, boolean z) {
        return 0;
    }

    void renameAutoAlbum(String str, long j2);

    int updateRecommendedIds(long j2, String str, boolean z, String str2) {
        return 0;
    }

    void updateSuggestedContentsRule(int i2, long j2);
}
