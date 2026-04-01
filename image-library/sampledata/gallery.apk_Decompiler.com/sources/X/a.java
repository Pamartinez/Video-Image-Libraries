package X;

import E2.h;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingBufferQueue;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.mp4.Track;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements h, ReorderingBufferQueue.OutputConsumer {
    public final /* synthetic */ FragmentedMp4Extractor d;

    public /* synthetic */ a(FragmentedMp4Extractor fragmentedMp4Extractor) {
        this.d = fragmentedMp4Extractor;
    }

    public Object apply(Object obj) {
        return this.d.modifyTrack((Track) obj);
    }

    public void consume(long j2, ParsableByteArray parsableByteArray) {
        this.d.lambda$new$2(j2, parsableByteArray);
    }
}
