package Ka;

import com.samsung.android.gallery.app.controller.externals.ShareAlbumCmd;
import com.samsung.android.gallery.database.dal.mp.impl.MpStoryApiImpl;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.knox.KnoxAlbumOperator;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2837a;
    public final /* synthetic */ ArrayList b;

    public /* synthetic */ c(ArrayList arrayList, int i2) {
        this.f2837a = i2;
        this.b = arrayList;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2837a) {
            case 0:
                Blackboard.lambda$getActivityBlackboardList$1(this.b, (String) obj, (Blackboard) obj2);
                return;
            case 1:
                ShareAlbumCmd.lambda$share$1(this.b, (AlbumType) obj, (ArrayList) obj2);
                return;
            case 2:
                KnoxAlbumOperator.lambda$getAlbumIdsMap$2(this.b, (AlbumType) obj, (ArrayList) obj2);
                return;
            default:
                MpStoryApiImpl.lambda$updateStoryTotalCropInfo$5(this.b, (Long) obj, (String[]) obj2);
                return;
        }
    }
}
