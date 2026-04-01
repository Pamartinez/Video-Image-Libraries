package l3;

import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleManager;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Ae.a {
    public final /* synthetic */ int d;
    public final /* synthetic */ CapsuleManager e;
    public final /* synthetic */ CapsuleEvent.OnClick f;

    public /* synthetic */ a(CapsuleManager capsuleManager, CapsuleEvent.OnClick onClick, int i2) {
        this.d = i2;
        this.e = capsuleManager;
        this.f = onClick;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                return CapsuleManager.onClickEvent$lambda$8(this.e, this.f);
            default:
                return CapsuleManager.onClickEvent$lambda$9(this.e, this.f);
        }
    }
}
