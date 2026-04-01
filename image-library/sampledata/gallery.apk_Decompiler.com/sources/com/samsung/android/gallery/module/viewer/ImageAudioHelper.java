package com.samsung.android.gallery.module.viewer;

import N2.j;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.samsung.android.gallery.module.R$anim;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.media.AudioPlayer;
import com.samsung.android.gallery.support.utils.Log;
import java.io.FileInputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageAudioHelper {
    private final CharSequence TAG;
    private Animation mAnimation;
    private AudioPlayer mAudioPlayer;
    private AudioPlayer.OnAudioPlayerChangedListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AudioStreamInterface {
        long[] getAudioStreamInfo(String str);

        boolean getAudioStreamLooping();
    }

    public ImageAudioHelper(CharSequence charSequence) {
        this.TAG = charSequence;
    }

    private AudioPlayer create(Context context, AudioStreamInterface audioStreamInterface, String str) {
        FileInputStream fileInputStream;
        Throwable th;
        if (str == null) {
            Log.mpe(this.TAG, "Audio#create failed. null path");
            return null;
        }
        long[] audioStreamInfo = audioStreamInterface.getAudioStreamInfo(str);
        if (audioStreamInfo == null) {
            Log.mpe(this.TAG, "Audio#create failed. no audio data");
            return null;
        }
        long j2 = audioStreamInfo[0];
        long j3 = audioStreamInfo[1];
        CharSequence charSequence = this.TAG;
        StringBuilder j8 = j.j(j2, "Audio#create {ofs=", ",len=");
        j8.append(j3);
        j8.append("} ");
        j8.append(audioStreamInterface);
        Log.mpv(charSequence, j8.toString());
        AudioPlayer audioPlayer = new AudioPlayer(context.getApplicationContext(), str, this.mListener);
        try {
            fileInputStream = new FileInputStream(str);
            audioPlayer.setDataSource(fileInputStream.getFD(), j2, j3);
            audioPlayer.setLooping(audioStreamInterface.getAudioStreamLooping());
            fileInputStream.close();
            return audioPlayer;
        } catch (IOException e) {
            IOException iOException = e;
            CharSequence charSequence2 = this.TAG;
            Log.mpe(charSequence2, "Audio#create failed e=" + iOException.getMessage());
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public boolean isLooping() {
        AudioPlayer audioPlayer = this.mAudioPlayer;
        if (audioPlayer == null || !audioPlayer.isLooping()) {
            return false;
        }
        return true;
    }

    public boolean isPlaying() {
        if (this.mAudioPlayer != null) {
            return true;
        }
        return false;
    }

    public void pause() {
        AudioPlayer audioPlayer = this.mAudioPlayer;
        if (audioPlayer != null && audioPlayer.pause()) {
            Log.mp(this.TAG, "Audio#pause");
        }
    }

    public boolean restart() {
        AudioPlayer audioPlayer = this.mAudioPlayer;
        if (audioPlayer == null || audioPlayer.isLooping() || !this.mAudioPlayer.restart()) {
            return false;
        }
        return true;
    }

    public ImageAudioHelper setListener(AudioPlayer.OnAudioPlayerChangedListener onAudioPlayerChangedListener) {
        this.mListener = onAudioPlayerChangedListener;
        return this;
    }

    public int start(Context context, AudioStreamInterface audioStreamInterface, MediaItem mediaItem) {
        if (context == null || audioStreamInterface == null || mediaItem == null) {
            Log.mpe(this.TAG, "Audio#start failed. null params");
            return 0;
        }
        AudioPlayer create = create(context, audioStreamInterface, MediaItemMde.getHiddenFilePath(mediaItem, mediaItem.getPath()));
        this.mAudioPlayer = create;
        if (create == null) {
            return 0;
        }
        create.start();
        return this.mAudioPlayer.getDuration();
    }

    public void startAnimation(View view) {
        boolean z;
        CharSequence charSequence = this.TAG;
        StringBuilder sb2 = new StringBuilder("Audio#startAnimation {");
        if (view != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append("}");
        Log.mpv(charSequence, sb2.toString());
        if (view != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), R$anim.viewer_sound_scene_alpha);
            this.mAnimation = loadAnimation;
            loadAnimation.setRepeatCount(-1);
            view.startAnimation(this.mAnimation);
        }
    }

    public void stop() {
        if (this.mAudioPlayer != null) {
            Log.mp(this.TAG, "Audio#stop");
            this.mAudioPlayer.release();
            this.mAudioPlayer = null;
        }
    }

    public void stopAnimation(View view) {
        boolean z;
        CharSequence charSequence = this.TAG;
        StringBuilder sb2 = new StringBuilder("Audio#stopAnimation {");
        if (view != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append("}");
        Log.mpv(charSequence, sb2.toString());
        if (view != null) {
            view.clearAnimation();
        }
        this.mAnimation = null;
    }
}
