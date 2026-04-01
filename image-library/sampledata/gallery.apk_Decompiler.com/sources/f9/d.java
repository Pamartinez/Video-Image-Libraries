package F9;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import com.samsung.android.gallery.database.dal.local.table.LocalTable;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.sdk.scs.ai.asr.Repository;
import com.samsung.android.sdk.scs.ai.asr_6_0.Repository;
import com.samsung.android.sivs.ai.sdkcommon.asr.ServerFeature;
import com.samsung.android.sum.core.channel.ReceiveChannelRouter;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.functional.OpPriorityByDataSize;
import com.samsung.android.sum.core.functional.OpPriorityComputable;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2802a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2803c;
    public final /* synthetic */ Object d;

    public /* synthetic */ d(Object obj, Object obj2, Object obj3, int i2) {
        this.f2802a = i2;
        this.b = obj;
        this.f2803c = obj2;
        this.d = obj3;
    }

    public final Object apply(Object obj) {
        switch (this.f2802a) {
            case 0:
                return ((MetadataManager) this.b).lambda$load$1((FileItemInterface) this.f2803c, (VideoReqInfo) this.d, (Integer) obj);
            case 1:
                return Repository.SharedPrefRepository.lambda$getServerType$1((ServerFeature) this.b, (SharedPreferences) this.f2803c, (String) this.d, (String) obj);
            case 2:
                return Repository.SharedPrefRepository.lambda$getServerType$1((com.samsung.android.scs.ai.sdkcommon.asr.ServerFeature) this.b, (SharedPreferences) this.f2803c, (String) this.d, (String) obj);
            case 3:
                return ((ReceiveChannelRouter) this.b).lambda$receiveAny$2((ExecutorService) this.f2803c, (LinkedBlockingQueue) this.d, (Integer) obj);
            case 4:
                return Float.valueOf(((OpPriorityComputable.ComputeBridge) ((Map.Entry) obj).getValue()).compute((MutableMediaFormat) this.b, (MediaFormat) this.f2803c, (OpPriorityByDataSize) this.d));
            default:
                return ((LocalTable) this.b).lambda$computeColumnNames$0((SQLiteDatabase) this.f2803c, (String) this.d, (String) obj);
        }
    }
}
