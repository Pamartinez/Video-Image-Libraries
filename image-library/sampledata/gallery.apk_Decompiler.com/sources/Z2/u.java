package z2;

import D0.f;
import android.os.Handler;
import android.os.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ f f2232a;

    public u(f fVar) {
        this.f2232a = fVar;
    }

    public final boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        f fVar = this.f2232a;
        v vVar = (v) message.obj;
        synchronized (fVar.e) {
            if (((v) fVar.g) == vVar || ((v) fVar.f106h) == vVar) {
                fVar.z(vVar, 2);
            }
        }
        return true;
    }
}
