package androidx.window.embedding;

import android.util.Pair;
import java.util.Set;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1037a;
    public final /* synthetic */ EmbeddingAdapter b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Set f1038c;

    public /* synthetic */ b(EmbeddingAdapter embeddingAdapter, Set set, int i2) {
        this.f1037a = i2;
        this.b = embeddingAdapter;
        this.f1038c = set;
    }

    public final boolean test(Object obj) {
        switch (this.f1037a) {
            case 0:
                return EmbeddingAdapter.m27translateActivityPairPredicates$lambda1(this.b, this.f1038c, (Pair) obj);
            default:
                return EmbeddingAdapter.m26translateActivityIntentPredicates$lambda3(this.b, this.f1038c, (Pair) obj);
        }
    }
}
