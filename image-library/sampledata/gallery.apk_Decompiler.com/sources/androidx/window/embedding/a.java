package androidx.window.embedding;

import android.app.Activity;
import android.content.Intent;
import java.util.Set;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1036a;
    public final /* synthetic */ Set b;

    public /* synthetic */ a(int i2, Set set) {
        this.f1036a = i2;
        this.b = set;
    }

    public final boolean test(Object obj) {
        int i2 = this.f1036a;
        Set set = this.b;
        switch (i2) {
            case 0:
                return EmbeddingAdapter.m29translateIntentPredicates$lambda8(set, (Intent) obj);
            default:
                return EmbeddingAdapter.m28translateActivityPredicates$lambda6(set, (Activity) obj);
        }
    }
}
