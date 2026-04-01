package M8;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.utils.Log;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;

    public /* synthetic */ c(long j2, int i2) {
        this.d = i2;
        this.e = j2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                Log.e("PPP_Helper", "updateCompletedPpp fail " + this.e);
                return;
            case 1:
                ((QueryParams) obj).setFileId(this.e);
                return;
            case 2:
                ((QueryParams) obj).setFileId(this.e);
                return;
            case 3:
                ((QueryParams) obj).setFileId(this.e);
                return;
            case 4:
                ((QueryParams) obj).setFileId(this.e).setLimit(2);
                return;
            case 5:
                ((QueryParams) obj).setFileId(this.e).addRequiredColumns("__storageType");
                return;
            default:
                ((QueryParams) obj).setFileId(this.e);
                return;
        }
    }
}
