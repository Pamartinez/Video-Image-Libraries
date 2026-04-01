package androidx.media3.transformer;

import androidx.media3.transformer.SequenceAssetLoader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ f(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((SequenceAssetLoader) obj).lambda$onOutputFormat$0();
                return;
            case 1:
                ((SequenceAssetLoader.GapSignalingAssetLoader) obj).outputFormatToSequenceAssetLoader();
                return;
            default:
                ((SequenceAssetLoader.SampleConsumerWrapper) obj).lambda$switchAssetLoader$0();
                return;
        }
    }
}
