package Gb;

import android.media.MediaExtractor;
import com.samsung.android.gallery.widget.listview.handler.AdvancedMouseSelectionHandler;
import com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager;
import com.samsung.android.sum.core.filter.MediaMuxerFilter;
import com.samsung.android.sum.core.graph.MFGraph;
import java.util.List;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2823a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2824c;

    public /* synthetic */ c(int i2, Object obj, Object obj2) {
        this.f2823a = i2;
        this.b = obj;
        this.f2824c = obj2;
    }

    public final void accept(int i2) {
        switch (this.f2823a) {
            case 0:
                ((AdvancedMouseSelectionHandler) this.b).lambda$handleSingleItemSelectionOnShift$2((AdvancedMouseFocusManager) this.f2824c, i2);
                return;
            case 1:
                ((MediaMuxerFilter) this.b).lambda$feedExistFramesToBufferChannel$16((MediaExtractor) this.f2824c, i2);
                return;
            default:
                MFGraph.lambda$run$7((List) this.b, (List) this.f2824c, i2);
                return;
        }
    }
}
