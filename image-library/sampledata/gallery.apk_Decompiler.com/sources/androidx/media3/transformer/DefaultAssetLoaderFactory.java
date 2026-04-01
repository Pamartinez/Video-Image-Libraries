package androidx.media3.transformer;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.media.metrics.LogSessionId;
import android.os.Looper;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Clock;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.ExoPlayerAssetLoader;
import androidx.media3.transformer.ImageAssetLoader;
import com.google.common.util.concurrent.C;
import com.google.common.util.concurrent.y;
import com.google.common.util.concurrent.z;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultAssetLoaderFactory implements AssetLoader.Factory {
    private final BitmapLoader bitmapLoader;
    private final Clock clock;
    private final Context context;
    private final Codec.DecoderFactory decoderFactory;
    private AssetLoader.Factory exoPlayerAssetLoaderFactory;
    private AssetLoader.Factory imageAssetLoaderFactory;
    private final LogSessionId logSessionId;
    private final MediaSource.Factory mediaSourceFactory = null;
    private final TrackSelector.Factory trackSelectorFactory = null;

    public DefaultAssetLoaderFactory(Context context2, Codec.DecoderFactory decoderFactory2, Clock clock2, LogSessionId logSessionId2) {
        y yVar;
        y zVar;
        this.context = context2.getApplicationContext();
        this.decoderFactory = decoderFactory2;
        this.clock = clock2;
        this.logSessionId = logSessionId2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        if (newSingleThreadExecutor instanceof y) {
            yVar = (y) newSingleThreadExecutor;
        } else {
            if (newSingleThreadExecutor instanceof ScheduledExecutorService) {
                zVar = new C((ScheduledExecutorService) newSingleThreadExecutor);
            } else {
                zVar = new z(newSingleThreadExecutor);
            }
            yVar = zVar;
        }
        this.bitmapLoader = new DataSourceBitmapLoader(yVar, new DefaultDataSource.Factory(context2), options, 4096);
    }

    public AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, AssetLoader.Listener listener, AssetLoader.CompositionSettings compositionSettings) {
        MediaItem mediaItem = editedMediaItem.mediaItem;
        if (!TransformerUtil.isImage(this.context, mediaItem) || ((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration)).imageDurationMs == -9223372036854775807L) {
            if (this.exoPlayerAssetLoaderFactory == null) {
                this.exoPlayerAssetLoaderFactory = new ExoPlayerAssetLoader.Factory(this.context, this.decoderFactory, this.clock, this.mediaSourceFactory, this.trackSelectorFactory, this.logSessionId);
            }
            return this.exoPlayerAssetLoaderFactory.createAssetLoader(editedMediaItem, looper, listener, compositionSettings);
        }
        if (this.imageAssetLoaderFactory == null) {
            this.imageAssetLoaderFactory = new ImageAssetLoader.Factory(this.context, this.bitmapLoader);
        }
        return this.imageAssetLoaderFactory.createAssetLoader(editedMediaItem, looper, listener, compositionSettings);
    }
}
