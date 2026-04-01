package Ta;

import com.samsung.android.media.mediacapture.SemMediaCapture;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SemMediaCapture.OnPreparedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Consumer f2877a;

    public /* synthetic */ a(Consumer consumer) {
        this.f2877a = consumer;
    }

    public final void onPrepared(SemMediaCapture semMediaCapture) {
        this.f2877a.accept(Boolean.TRUE);
    }
}
