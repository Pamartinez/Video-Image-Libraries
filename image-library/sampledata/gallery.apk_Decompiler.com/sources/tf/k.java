package Tf;

import Ae.a;
import android.app.RemoteAction;
import com.samsung.android.app.sdk.deepsky.textextraction.action.ActionManager;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleFactory;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.HandleDrawInfo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ k(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                CharSequence charSequence = (CharSequence) this.f;
                j.e(charSequence, "input");
                Matcher matcher = ((Pattern) ((m) this.e).e).matcher(charSequence);
                j.d(matcher, "matcher(...)");
                if (!matcher.find(0)) {
                    return null;
                }
                return new j(matcher, charSequence);
            case 1:
                return CapsuleFactory.createAiSuggestionCapsuleListener$lambda$18((CapsuleFactory) this.e, (ActionManager) this.f);
            case 2:
                return CapsuleFactory.createEntityCapsuleListener$lambda$11((CapsuleFactory) this.e, (RemoteAction) this.f);
            default:
                return Handle.updateHandle$lambda$2$lambda$1((Handle) this.e, (HandleDrawInfo) this.f);
        }
    }
}
