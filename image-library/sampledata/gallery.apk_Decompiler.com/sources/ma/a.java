package ma;

import android.media.MediaMuxer;
import com.samsung.android.gallery.module.story.transcode.transcoder.audio.AbsAudioTranscoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ AbsAudioTranscoder e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ MediaMuxer g;

    public /* synthetic */ a(AbsAudioTranscoder absAudioTranscoder, MediaMuxer mediaMuxer, boolean z) {
        this.e = absAudioTranscoder;
        this.g = mediaMuxer;
        this.f = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$transcode$0(this.g, this.f);
                return;
            default:
                boolean z = this.f;
                this.e.lambda$transcodeInternal$1(z, this.g);
                return;
        }
    }

    public /* synthetic */ a(AbsAudioTranscoder absAudioTranscoder, boolean z, MediaMuxer mediaMuxer) {
        this.e = absAudioTranscoder;
        this.f = z;
        this.g = mediaMuxer;
    }
}
