package androidx.media3.transformer;

import androidx.media3.common.audio.AudioProcessor;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AudioMixer {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
        AudioMixer create();
    }

    int addSource(AudioProcessor.AudioFormat audioFormat, long j2);

    void configure(AudioProcessor.AudioFormat audioFormat, int i2, long j2);

    ByteBuffer getOutput();

    boolean hasSource(int i2);

    boolean isEnded();

    void queueInput(int i2, ByteBuffer byteBuffer);

    void removeSource(int i2);

    void reset();
}
