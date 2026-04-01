package Sd;

import D0.e;
import android.content.Context;
import com.samsung.android.sdk.scs.ai.asr.safety.WatchDog;
import com.samsung.android.sdk.scs.ai.asr.safety.WatchDogCallback;
import java.io.Closeable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Closeable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ s(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void close() {
        switch (this.d) {
            case 0:
                ((Context) ((e) this.e).e).getContentResolver().unregisterContentObserver((v) this.f);
                return;
            case 1:
                ((Context) ((e) this.e).e).getContentResolver().unregisterContentObserver((v) this.f);
                return;
            default:
                ((WatchDog) this.e).lambda$wrapBlocking$0((WatchDogCallback) this.f);
                return;
        }
    }
}
