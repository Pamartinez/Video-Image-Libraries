package androidx.room;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.IMultiInstanceInvalidationService;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u000f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u00062\u000e\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"androidx/room/MultiInstanceInvalidationService$binder$1", "Landroidx/room/IMultiInstanceInvalidationService$Stub;", "Landroidx/room/IMultiInstanceInvalidationCallback;", "callback", "", "name", "", "registerCallback", "(Landroidx/room/IMultiInstanceInvalidationCallback;Ljava/lang/String;)I", "clientId", "Lme/x;", "unregisterCallback", "(Landroidx/room/IMultiInstanceInvalidationCallback;I)V", "", "tables", "broadcastInvalidation", "(I[Ljava/lang/String;)V", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MultiInstanceInvalidationService$binder$1 extends IMultiInstanceInvalidationService.Stub {
    final /* synthetic */ MultiInstanceInvalidationService this$0;

    public MultiInstanceInvalidationService$binder$1(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.this$0 = multiInstanceInvalidationService;
    }

    public void broadcastInvalidation(int i2, String[] strArr) {
        j.e(strArr, "tables");
        RemoteCallbackList<IMultiInstanceInvalidationCallback> callbackList$room_runtime_release = this.this$0.getCallbackList$room_runtime_release();
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
        synchronized (callbackList$room_runtime_release) {
            String str = multiInstanceInvalidationService.getClientNames$room_runtime_release().get(Integer.valueOf(i2));
            if (str == null) {
                Log.w("ROOM", "Remote invalidation client ID not registered");
                return;
            }
            int beginBroadcast = multiInstanceInvalidationService.getCallbackList$room_runtime_release().beginBroadcast();
            int i7 = 0;
            while (i7 < beginBroadcast) {
                try {
                    Object broadcastCookie = multiInstanceInvalidationService.getCallbackList$room_runtime_release().getBroadcastCookie(i7);
                    j.c(broadcastCookie, "null cannot be cast to non-null type kotlin.Int");
                    Integer num = (Integer) broadcastCookie;
                    int intValue = num.intValue();
                    String str2 = multiInstanceInvalidationService.getClientNames$room_runtime_release().get(num);
                    if (i2 != intValue && str.equals(str2)) {
                        try {
                            multiInstanceInvalidationService.getCallbackList$room_runtime_release().getBroadcastItem(i7).onInvalidation(strArr);
                        } catch (RemoteException e) {
                            Log.w("ROOM", "Error invoking a remote callback", e);
                        }
                    }
                    i7++;
                } catch (Throwable th) {
                    multiInstanceInvalidationService.getCallbackList$room_runtime_release().finishBroadcast();
                    throw th;
                }
            }
            multiInstanceInvalidationService.getCallbackList$room_runtime_release().finishBroadcast();
        }
    }

    public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) {
        j.e(iMultiInstanceInvalidationCallback, Profile.PhoneNumberData.TYPE_CALLBACK);
        int i2 = 0;
        if (str == null) {
            return 0;
        }
        RemoteCallbackList<IMultiInstanceInvalidationCallback> callbackList$room_runtime_release = this.this$0.getCallbackList$room_runtime_release();
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
        synchronized (callbackList$room_runtime_release) {
            try {
                multiInstanceInvalidationService.setMaxClientId$room_runtime_release(multiInstanceInvalidationService.getMaxClientId$room_runtime_release() + 1);
                int maxClientId$room_runtime_release = multiInstanceInvalidationService.getMaxClientId$room_runtime_release();
                if (multiInstanceInvalidationService.getCallbackList$room_runtime_release().register(iMultiInstanceInvalidationCallback, Integer.valueOf(maxClientId$room_runtime_release))) {
                    multiInstanceInvalidationService.getClientNames$room_runtime_release().put(Integer.valueOf(maxClientId$room_runtime_release), str);
                    i2 = maxClientId$room_runtime_release;
                } else {
                    multiInstanceInvalidationService.setMaxClientId$room_runtime_release(multiInstanceInvalidationService.getMaxClientId$room_runtime_release() - 1);
                    multiInstanceInvalidationService.getMaxClientId$room_runtime_release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i2) {
        j.e(iMultiInstanceInvalidationCallback, Profile.PhoneNumberData.TYPE_CALLBACK);
        RemoteCallbackList<IMultiInstanceInvalidationCallback> callbackList$room_runtime_release = this.this$0.getCallbackList$room_runtime_release();
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
        synchronized (callbackList$room_runtime_release) {
            multiInstanceInvalidationService.getCallbackList$room_runtime_release().unregister(iMultiInstanceInvalidationCallback);
            String remove = multiInstanceInvalidationService.getClientNames$room_runtime_release().remove(Integer.valueOf(i2));
        }
    }
}
