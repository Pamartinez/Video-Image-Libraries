package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.DataReader;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ProgressiveMediaExtractor {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
    }

    void disableSeekingOnMp3Streams();

    long getCurrentInputPosition();

    void init(DataReader dataReader, Uri uri, Map<String, List<String>> map, long j2, long j3, ExtractorOutput extractorOutput);

    int read(PositionHolder positionHolder);

    void release();

    void seek(long j2, long j3);
}
