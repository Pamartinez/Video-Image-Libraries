package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingBufferQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ReorderingBufferQueue.OutputConsumer {
    public final /* synthetic */ UserDataReader d;

    public /* synthetic */ a(UserDataReader userDataReader) {
        this.d = userDataReader;
    }

    public final void consume(long j2, ParsableByteArray parsableByteArray) {
        this.d.lambda$new$0(j2, parsableByteArray);
    }
}
