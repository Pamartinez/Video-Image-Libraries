package androidx.room;

import androidx.room.IMultiInstanceInvalidationCallback;
import com.samsung.o3dp.lib3dphotography.i;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0006\u001a\u00020\u00052\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"androidx/room/MultiInstanceInvalidationClient$callback$1", "Landroidx/room/IMultiInstanceInvalidationCallback$Stub;", "", "", "tables", "Lme/x;", "onInvalidation", "([Ljava/lang/String;)V", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MultiInstanceInvalidationClient$callback$1 extends IMultiInstanceInvalidationCallback.Stub {
    final /* synthetic */ MultiInstanceInvalidationClient this$0;

    public MultiInstanceInvalidationClient$callback$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        this.this$0 = multiInstanceInvalidationClient;
    }

    /* access modifiers changed from: private */
    public static final void onInvalidation$lambda$0(MultiInstanceInvalidationClient multiInstanceInvalidationClient, String[] strArr) {
        j.e(multiInstanceInvalidationClient, "this$0");
        j.e(strArr, "$tables");
        multiInstanceInvalidationClient.getInvalidationTracker().notifyObserversByTableNames((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public void onInvalidation(String[] strArr) {
        j.e(strArr, "tables");
        this.this$0.getExecutor().execute(new i(18, this.this$0, strArr));
    }
}
