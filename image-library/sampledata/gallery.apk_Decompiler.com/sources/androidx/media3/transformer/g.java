package androidx.media3.transformer;

import android.os.Handler;
import android.os.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TransformerInternal f1028a;

    public /* synthetic */ g(TransformerInternal transformerInternal) {
        this.f1028a = transformerInternal;
    }

    public final boolean handleMessage(Message message) {
        return this.f1028a.handleMessage(message);
    }
}
