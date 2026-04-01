package androidx.media3.transformer;

import androidx.media3.common.util.Consumer;
import androidx.media3.transformer.TransformerInternal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {
    public final /* synthetic */ TransformerInternal.SequenceAssetLoaderListener d;

    public /* synthetic */ i(TransformerInternal.SequenceAssetLoaderListener sequenceAssetLoaderListener) {
        this.d = sequenceAssetLoaderListener;
    }

    public final void accept(Object obj) {
        this.d.onError((ExportException) obj);
    }
}
