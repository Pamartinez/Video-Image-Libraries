package A4;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c0 implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ List e;

    public /* synthetic */ c0(int i2, List list) {
        this.d = i2;
        this.e = list;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        List list = this.e;
        switch (i2) {
            case 0:
                list.add((PreviewViewHolder) obj);
                return;
            default:
                ((QueryParams) obj).mCreatureFaceGroupIds = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, list);
                return;
        }
    }
}
