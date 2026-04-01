package C3;

import android.database.Cursor;
import com.samsung.android.gallery.app.activity.VersionUpdateHandler;
import com.samsung.android.gallery.database.dal.mp.table.MpAnalyzeInfoTable;
import com.samsung.android.gallery.module.analyticsquery.SearchAnalysisStatus;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.knox.KnoxAlbumOperator;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashMap e;

    public /* synthetic */ p(int i2, HashMap hashMap) {
        this.d = i2;
        this.e = hashMap;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        HashMap hashMap = this.e;
        switch (i2) {
            case 0:
                VersionUpdateHandler.lambda$updateVersion15$1(hashMap, (String) obj);
                return;
            case 1:
                hashMap.put((String) obj, SearchAnalysisStatus.EMPTY);
                return;
            case 2:
                hashMap.put("vFace", (SearchAnalysisStatus) obj);
                return;
            case 3:
                hashMap.put("vMediaSearch", (SearchAnalysisStatus) obj);
                return;
            case 4:
                hashMap.put("Face/Pet", (SearchAnalysisStatus) obj);
                return;
            case 5:
                hashMap.put("Face", (SearchAnalysisStatus) obj);
                return;
            case 6:
                hashMap.put("MediaSearch", (SearchAnalysisStatus) obj);
                return;
            case 7:
                ((HistoryItem) obj).assignCoverItem(hashMap);
                return;
            case 8:
                hashMap.remove(((MediaItem) obj).getSubCategory());
                return;
            case 9:
                hashMap.put("location://virtual/album/recently/fileList", new int[]{((MediaItem) obj).getAlbumID()});
                return;
            case 10:
                KnoxAlbumOperator.lambda$getItemsFromAlbums$7(hashMap, (Cursor) obj);
                return;
            case 11:
                MpAnalyzeInfoTable.lambda$unpack$1(hashMap, (String) obj);
                return;
            default:
                hashMap.remove((Integer) obj);
                return;
        }
    }
}
