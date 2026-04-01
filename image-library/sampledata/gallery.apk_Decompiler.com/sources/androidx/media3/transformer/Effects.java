package androidx.media3.transformer;

import F2.G;
import F2.U;
import F2.y0;
import androidx.media3.common.Effect;
import androidx.media3.common.audio.AudioProcessor;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Effects {
    public static final Effects EMPTY;
    public final U audioProcessors;
    public final U videoEffects;

    static {
        G g = U.e;
        y0 y0Var = y0.f278h;
        EMPTY = new Effects(y0Var, y0Var);
    }

    public Effects(List<AudioProcessor> list, List<Effect> list2) {
        this.audioProcessors = U.y(list);
        this.videoEffects = U.y(list2);
    }
}
