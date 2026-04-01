package androidx.media3.exoplayer.mediacodec;

import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Comparator {
    public final /* synthetic */ MediaCodecUtil.ScoreProvider d;

    public /* synthetic */ c(MediaCodecUtil.ScoreProvider scoreProvider) {
        this.d = scoreProvider;
    }

    public final int compare(Object obj, Object obj2) {
        return MediaCodecUtil.lambda$sortByScore$4(this.d, obj, obj2);
    }
}
