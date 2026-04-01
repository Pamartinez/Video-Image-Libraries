package androidx.media3.exoplayer;

import android.media.AudioManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements AudioManager.OnAudioFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AudioFocusManager f1020a;

    public /* synthetic */ c(AudioFocusManager audioFocusManager) {
        this.f1020a = audioFocusManager;
    }

    public final void onAudioFocusChange(int i2) {
        this.f1020a.handlePlatformAudioFocusChange(i2);
    }
}
