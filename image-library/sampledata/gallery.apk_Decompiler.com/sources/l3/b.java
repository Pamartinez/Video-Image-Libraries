package l3;

import Ae.a;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ CapsuleManager e;

    public /* synthetic */ b(CapsuleManager capsuleManager, int i2) {
        this.d = i2;
        this.e = capsuleManager;
    }

    public final Object invoke() {
        int i2 = this.d;
        CapsuleManager capsuleManager = this.e;
        switch (i2) {
            case 0:
                return CapsuleManager.onTranslateEvent$lambda$12(capsuleManager);
            default:
                return CapsuleManager.onTranslateEvent$lambda$13(capsuleManager);
        }
    }
}
