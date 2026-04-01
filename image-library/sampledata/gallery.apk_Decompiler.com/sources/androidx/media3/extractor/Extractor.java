package androidx.media3.extractor;

import F2.G;
import F2.U;
import F2.y0;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Extractor {
    List<SniffFailure> getSniffFailureDetails() {
        G g = U.e;
        return y0.f278h;
    }

    void init(ExtractorOutput extractorOutput);

    int read(ExtractorInput extractorInput, PositionHolder positionHolder);

    void release();

    void seek(long j2, long j3);

    boolean sniff(ExtractorInput extractorInput);

    Extractor getUnderlyingImplementation() {
        return this;
    }
}
