package androidx.media3.transformer;

import F2.Y;
import android.os.Looper;
import androidx.media3.common.Format;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AssetLoader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CompositionSettings {
        public final int hdrMode;
        public final boolean retainHdrFromUltraHdrImage;

        public CompositionSettings(int i2, boolean z) {
            this.hdrMode = i2;
            this.retainHdrFromUltraHdrImage = z;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
        AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, Listener listener, CompositionSettings compositionSettings);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onDurationUs(long j2);

        void onError(ExportException exportException);

        SampleConsumer onOutputFormat(Format format);

        boolean onTrackAdded(Format format, int i2);

        void onTrackCount(int i2);
    }

    Y getDecoderNames();

    int getProgress(ProgressHolder progressHolder);

    void release();

    void start();
}
