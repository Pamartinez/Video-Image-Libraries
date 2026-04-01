package androidx.media3.extractor.ogg;

import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SeekMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface OggSeeker {
    SeekMap createSeekMap();

    long read(ExtractorInput extractorInput);

    void startSeek(long j2);
}
