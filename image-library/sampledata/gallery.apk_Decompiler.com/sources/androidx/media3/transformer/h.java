package androidx.media3.transformer;

import androidx.media3.common.Format;
import androidx.media3.transformer.TransformerInternal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements OnMediaItemChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TransformerInternal.SequenceAssetLoaderListener f1029a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ GraphInput f1030c;

    public /* synthetic */ h(TransformerInternal.SequenceAssetLoaderListener sequenceAssetLoaderListener, int i2, GraphInput graphInput) {
        this.f1029a = sequenceAssetLoaderListener;
        this.b = i2;
        this.f1030c = graphInput;
    }

    public final void onMediaItemChanged(EditedMediaItem editedMediaItem, long j2, Format format, boolean z) {
        this.f1029a.lambda$onOutputFormat$0(this.b, this.f1030c, editedMediaItem, j2, format, z);
    }
}
