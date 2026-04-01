package ua;

import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.function.BiConsumer;

/* renamed from: ua.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0711a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f3294a;
    public final /* synthetic */ boolean b;

    public /* synthetic */ C0711a(String str, boolean z) {
        this.f3294a = str;
        this.b = z;
    }

    public final void accept(Object obj, Object obj2) {
        BlackboardUtils.lambda$publishDataChangedToOtherActivities$0(this.f3294a, this.b, (String) obj, (Blackboard) obj2);
    }
}
