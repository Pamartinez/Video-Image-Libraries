package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Subtitle {
    List<Cue> getCues(long j2);

    long getEventTime(int i2);

    int getEventTimeCount();

    int getNextEventTimeIndex(long j2);
}
