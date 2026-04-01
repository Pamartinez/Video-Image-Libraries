package androidx.room;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.room.IMultiInstanceInvalidationService;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"androidx/room/MultiInstanceInvalidationClient$serviceConnection$1", "Landroid/content/ServiceConnection;", "Landroid/content/ComponentName;", "name", "Landroid/os/IBinder;", "service", "Lme/x;", "onServiceConnected", "(Landroid/content/ComponentName;Landroid/os/IBinder;)V", "onServiceDisconnected", "(Landroid/content/ComponentName;)V", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MultiInstanceInvalidationClient$serviceConnection$1 implements ServiceConnection {
    final /* synthetic */ MultiInstanceInvalidationClient this$0;

    public MultiInstanceInvalidationClient$serviceConnection$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        this.this$0 = multiInstanceInvalidationClient;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        j.e(componentName, "name");
        j.e(iBinder, ServiceManagerUtil.PHOTO_EDIT_EXTRA_SERVICE_KEY);
        this.this$0.setService(IMultiInstanceInvalidationService.Stub.asInterface(iBinder));
        this.this$0.getExecutor().execute(this.this$0.getSetUpRunnable());
    }

    public void onServiceDisconnected(ComponentName componentName) {
        j.e(componentName, "name");
        this.this$0.getExecutor().execute(this.this$0.getRemoveObserverRunnable());
        this.this$0.setService((IMultiInstanceInvalidationService) null);
    }
}
