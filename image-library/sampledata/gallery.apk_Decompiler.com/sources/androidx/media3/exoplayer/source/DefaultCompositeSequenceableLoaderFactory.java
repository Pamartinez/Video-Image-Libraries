package androidx.media3.exoplayer.source;

import F2.G;
import F2.U;
import F2.y0;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultCompositeSequenceableLoaderFactory implements CompositeSequenceableLoaderFactory {
    public SequenceableLoader create(List<? extends SequenceableLoader> list, List<List<Integer>> list2) {
        return new CompositeSequenceableLoader(list, list2);
    }

    public SequenceableLoader empty() {
        G g = U.e;
        y0 y0Var = y0.f278h;
        return new CompositeSequenceableLoader(y0Var, y0Var);
    }
}
