package com.samsung.android.gallery.app.ui.list.stories.highlight.bgm;

import N2.j;
import O3.b;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.media.AudioManagerHelper;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerFactory;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.StatusCodes;
import e6.C0453a;
import i6.a;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmPlayerV2 implements AudioManagerHelper.OnAudioFocusManagerListener {
    private boolean mAudioFocused = true;
    private AudioManagerHelper mAudioManager;
    private String mBgmName;
    private final BgmUriService mBgmUriService;
    private BgmPlayerCallback mCallback;
    private float mCurrentVolume = 1.0f;
    private int mDuration = 10000;
    private final Handler mHandler = new Handler(ThreadUtil.createBackgroundThreadLooper("BgmPlayerV2")) {
        public void handleMessage(Message message) {
            BgmPlayerV2.this.handlePlayer(message);
        }
    };
    private boolean mIsBgm;
    private String mMusicFilePath;
    private MediaPlayerRef mPlayerRef;
    private final AtomicReference<Float> mTargetVolume = new AtomicReference<>(Float.valueOf(1.0f));

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaPlayerRef {
        /* access modifiers changed from: private */
        public final String bgmName;
        /* access modifiers changed from: private */
        public final boolean isBgm;
        /* access modifiers changed from: private */
        public boolean isPaused;
        /* access modifiers changed from: private */
        public boolean isStarted;
        /* access modifiers changed from: private */
        public final String path;
        /* access modifiers changed from: private */
        public final MediaPlayerCompat player;
        /* access modifiers changed from: private */
        public int seekPos;

        public /* synthetic */ MediaPlayerRef(int i2, String str, String str2, boolean z) {
            this(str, str2, z);
        }

        /* access modifiers changed from: private */
        public boolean equals(MediaPlayerCompat mediaPlayerCompat) {
            if (mediaPlayerCompat == null || mediaPlayerCompat != this.player) {
                return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("{");
            sb2.append(Logger.getSimpleName((Object) this.player));
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.bgmName);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.isBgm);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return j.h(sb2, this.isPaused, "}");
        }

        public void updateSeekPos() {
            if (this.isStarted) {
                int renderingPosition = this.player.getRenderingPosition();
                this.seekPos = renderingPosition;
                if (renderingPosition == 0) {
                    this.seekPos = this.player.getCurrentPositionMs();
                }
            }
        }

        private MediaPlayerRef(String str, String str2, boolean z) {
            this.player = MediaPlayerFactory.createBgmAudioPlayer();
            this.bgmName = str;
            this.path = str2;
            this.isBgm = z;
        }
    }

    public BgmPlayerV2(BgmUriService bgmUriService) {
        this.mBgmUriService = bgmUriService;
        this.mAudioManager = new AudioManagerHelper(AppResources.getAppContext(), this);
    }

    private void audioFocusChanged(boolean z) {
        this.mAudioFocused = z;
        BgmPlayerCallback bgmPlayerCallback = this.mCallback;
        if (bgmPlayerCallback != null) {
            bgmPlayerCallback.onAudioFocus(z);
        }
    }

    private void checkAudioFocus() {
        boolean audioFocusEnabled = this.mAudioManager.setAudioFocusEnabled(true);
        if (audioFocusEnabled != this.mAudioFocused) {
            this.mAudioFocused = audioFocusEnabled;
            BgmPlayerCallback bgmPlayerCallback = this.mCallback;
            if (bgmPlayerCallback != null) {
                bgmPlayerCallback.onAudioFocus(audioFocusEnabled);
            }
            Log.d("BgmPlayerV2", "checkAudioFocus", Boolean.valueOf(audioFocusEnabled));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: clearPlayer */
    public void lambda$notifyFail$3(MediaPlayerCompat mediaPlayerCompat) {
        ThreadUtil.runOnUiThread(new a(this, mediaPlayerCompat, 0));
    }

    private void closePlayer(MediaPlayerCompat mediaPlayerCompat, BgmOptions bgmOptions, AudioManagerHelper audioManagerHelper) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            lambda$notifyFail$3(mediaPlayerCompat);
            Optional.ofNullable(bgmOptions).ifPresent(new C0453a(25));
            Optional.ofNullable(audioManagerHelper).ifPresent(new C0453a(26));
            mediaPlayerCompat.setPlaybackCompleteListener((MediaPlayerCompat.OnPlayBackCompleteListener) null);
            mediaPlayerCompat.reset();
            mediaPlayerCompat.release();
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("closePlayer failed e="), "BgmPlayerV2");
        }
        Log.d("BgmPlayerV2", "closePlayer " + Logger.getSimpleName((Object) mediaPlayerCompat) + " +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    private Uri getBaseUri(BgmOptions bgmOptions) {
        if (bgmOptions != null) {
            return bgmOptions.getBgmOption(0).uri;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void handlePlayer(Message message) {
        int i2 = message.what;
        if (i2 == 0) {
            MediaPlayerRef mediaPlayerRef = (MediaPlayerRef) message.obj;
            openPlayer(mediaPlayerRef.player, mediaPlayerRef.bgmName, mediaPlayerRef.path, mediaPlayerRef.isBgm);
        } else if (i2 == 1) {
            MediaPlayerRef mediaPlayerRef2 = (MediaPlayerRef) message.obj;
            int i7 = message.arg1;
            if (i7 == -1) {
                i7 = mediaPlayerRef2.seekPos;
            }
            resumePlayer(mediaPlayerRef2.player, i7);
        } else if (i2 == 2) {
            MediaPlayerRef mediaPlayerRef3 = (MediaPlayerRef) message.obj;
            mediaPlayerRef3.updateSeekPos();
            pausePlayer(mediaPlayerRef3.player);
        } else if (i2 == 3) {
            closePlayer(((MediaPlayerRef) message.obj).player, (BgmOptions) null, this.mAudioManager);
        } else if (i2 == 4) {
            Object[] objArr = (Object[]) message.obj;
            setBgmVolume((MediaPlayerCompat) objArr[0], ((Float) objArr[1]).floatValue());
        }
    }

    private boolean isCriticalError(int i2, int i7) {
        if (i2 != 1) {
            return false;
        }
        if (i7 == -1004 || i7 == -1007 || i7 == -1010) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clearPlayer$0(MediaPlayerCompat mediaPlayerCompat) {
        MediaPlayerRef mediaPlayerRef = this.mPlayerRef;
        if (mediaPlayerRef != null && mediaPlayerRef.equals(mediaPlayerCompat)) {
            Log.d("BgmPlayerV2", "clearPlayer = " + this.mPlayerRef);
            this.mPlayerRef = null;
        }
    }

    private void notifyFail(MediaPlayerCompat mediaPlayerCompat, int i2) {
        ThreadUtil.runOnUiThread(new a(this, mediaPlayerCompat, 1));
        BgmPlayerCallback bgmPlayerCallback = this.mCallback;
        if (bgmPlayerCallback != null) {
            bgmPlayerCallback.onFail(i2);
        }
    }

    /* access modifiers changed from: private */
    public boolean onError(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        StringBuilder h5 = A.a.h(i2, i7, "onError {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "} ");
        h5.append(Logger.getSimpleName((Object) mediaPlayerCompat));
        Log.d("BgmPlayerV2", h5.toString());
        if (isCriticalError(i2, i7)) {
            notifyFail(mediaPlayerCompat, i2);
            return false;
        }
        Log.d("BgmPlayerV2", "notifyFail is ignored");
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: onPrepared */
    public void lambda$openPlayer$1(MediaPlayerCompat mediaPlayerCompat, BgmOptions bgmOptions) {
        try {
            MediaPlayerRef mediaPlayerRef = this.mPlayerRef;
            if (mediaPlayerRef == null || !mediaPlayerRef.equals(mediaPlayerCompat)) {
                closePlayer(mediaPlayerCompat, bgmOptions, this.mAudioManager);
                return;
            }
            mediaPlayerCompat.setLooping(true);
            preparePlayback(mediaPlayerCompat, bgmOptions);
            if (mediaPlayerRef.seekPos != 0) {
                Log.d("BgmPlayerV2", "onPrepared seekTo " + mediaPlayerRef.seekPos);
                mediaPlayerCompat.seekTo(mediaPlayerRef.seekPos);
            }
            float f = this.mCurrentVolume;
            mediaPlayerCompat.setVolume(f, f);
            checkAudioFocus();
            Log.d("BgmPlayerV2", "onPrepared", Boolean.valueOf(true ^ mediaPlayerRef.isPaused), Logger.getSimpleName((Object) mediaPlayerCompat));
            if (this.mAudioFocused && !mediaPlayerRef.isPaused) {
                mediaPlayerCompat.start();
                mediaPlayerRef.isStarted = true;
                sendSetVolumeMsg(mediaPlayerCompat, this.mTargetVolume.get().floatValue());
            }
        } catch (Exception e) {
            Log.e("BgmPlayerV2", "start failed e=" + e.getMessage() + " state:" + mediaPlayerCompat.getPlaybackState());
            closePlayer(mediaPlayerCompat, bgmOptions, this.mAudioManager);
        }
    }

    private void openPlayer(MediaPlayerCompat mediaPlayerCompat, String str, String str2, boolean z) {
        BgmOptions bgmOptions;
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            bgmOptions = this.mBgmUriService.createBgmOptionsSync(str, this.mDuration);
        } else {
            bgmOptions = null;
        }
        if (bgmOptions != null || !z) {
            mediaPlayerCompat.setTag("BgmPlayerV2");
            if (z) {
                mediaPlayerCompat.setDataSource(AppResources.getAppContext(), getBaseUri(bgmOptions));
            } else {
                mediaPlayerCompat.setDataSource(str2);
            }
            mediaPlayerCompat.setWfdTcpDisable();
            mediaPlayerCompat.setOnErrorListener(new com.samsung.android.sdk.scs.ai.language.a(20, this));
            mediaPlayerCompat.setOnPreparedListener(new b(27, this, bgmOptions));
            mediaPlayerCompat.prepareAsync();
            Log.d("BgmPlayerV2", "openBgmPlayer" + Logger.vt(Logger.getSimpleName((Object) mediaPlayerCompat), str, Boolean.valueOf(z), bgmOptions, Long.valueOf(currentTimeMillis)));
            return;
        }
        try {
            Log.e((CharSequence) "BgmPlayerV2", "openBgmPlayer failed with null bgm", str);
            notifyFail(mediaPlayerCompat, -2000);
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("openBgmPlayer failed e="), "BgmPlayerV2");
            closePlayer(mediaPlayerCompat, bgmOptions, this.mAudioManager);
            notifyFail(mediaPlayerCompat, StatusCodes.UNDEFINED);
        }
    }

    private void pausePlayer(MediaPlayerCompat mediaPlayerCompat) {
        try {
            mediaPlayerCompat.pause();
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("pausePlayer failed e="), "BgmPlayerV2");
        }
    }

    private void preparePlayback(MediaPlayerCompat mediaPlayerCompat, BgmOptions bgmOptions) {
        Object obj;
        if (bgmOptions != null) {
            mediaPlayerCompat.initBackgroundMusic(bgmOptions);
        }
        if (bgmOptions != null) {
            obj = Integer.valueOf(bgmOptions.size());
        } else {
            obj = "empty";
        }
        Log.d("BgmPlayerV2", "bgm prepared", obj);
    }

    private void resumePlayer(MediaPlayerCompat mediaPlayerCompat, int i2) {
        if (i2 != -1) {
            try {
                mediaPlayerCompat.seekTo(i2);
            } catch (Exception e) {
                A.a.s(e, new StringBuilder("resumePlayer failed e="), "BgmPlayerV2");
                return;
            }
        }
        checkAudioFocus();
        if (this.mAudioFocused) {
            mediaPlayerCompat.start();
            sendSetVolumeMsg(mediaPlayerCompat, this.mTargetVolume.get().floatValue());
        }
        Log.d("BgmPlayerV2", "resumePlayer", Integer.valueOf(i2), Logger.getSimpleName((Object) mediaPlayerCompat));
    }

    private void sendSetVolumeMsg(MediaPlayerCompat mediaPlayerCompat, float f) {
        this.mHandler.removeMessages(4);
        this.mHandler.sendMessageDelayed(Message.obtain(this.mHandler, 4, new Object[]{mediaPlayerCompat, Float.valueOf(f)}), 50);
    }

    private void setBgmVolume(MediaPlayerCompat mediaPlayerCompat, float f) {
        int i2;
        if (mediaPlayerCompat != null && mediaPlayerCompat.isPrepared()) {
            try {
                if (Float.compare(this.mTargetVolume.get().floatValue(), f) == 0 && Float.compare(this.mCurrentVolume, f) != 0) {
                    float f5 = this.mCurrentVolume;
                    if (f > f5) {
                        i2 = 1;
                    } else {
                        i2 = -1;
                    }
                    float f8 = ((float) (((int) (f5 * 10.0f)) + i2)) / 10.0f;
                    this.mCurrentVolume = f8;
                    mediaPlayerCompat.setVolume(f8, f8);
                    if (this.mPlayerRef != null && Float.compare(this.mCurrentVolume, f) != 0) {
                        sendSetVolumeMsg(this.mPlayerRef.player, f);
                    }
                }
            } catch (Exception unused) {
                Log.w((CharSequence) "BgmPlayerV2", "audio fade fail", Float.valueOf(f), Float.valueOf(this.mCurrentVolume));
            }
        }
    }

    public void changeVolume(float f) {
        MediaPlayerCompat mediaPlayerCompat;
        Log.d("BgmPlayerV2", "change volume", Float.valueOf(this.mCurrentVolume), Float.valueOf(f));
        MediaPlayerRef mediaPlayerRef = this.mPlayerRef;
        if (mediaPlayerRef != null) {
            mediaPlayerCompat = mediaPlayerRef.player;
        } else {
            mediaPlayerCompat = null;
        }
        this.mTargetVolume.set(Float.valueOf(f));
        if (mediaPlayerCompat != null) {
            sendSetVolumeMsg(mediaPlayerCompat, this.mTargetVolume.get().floatValue());
        } else {
            this.mCurrentVolume = f;
        }
    }

    public void destroy() {
        stop();
        this.mHandler.getLooper().quitSafely();
        this.mCallback = null;
        this.mAudioManager.destroy();
        this.mAudioManager = null;
    }

    public float getCurrentVolume() {
        return this.mCurrentVolume;
    }

    public boolean isPaused() {
        MediaPlayerRef mediaPlayerRef = this.mPlayerRef;
        if (mediaPlayerRef == null || !mediaPlayerRef.isPaused) {
            return false;
        }
        return true;
    }

    public boolean isPlaying() {
        MediaPlayerRef mediaPlayerRef = this.mPlayerRef;
        if (mediaPlayerRef == null || mediaPlayerRef.isPaused) {
            return false;
        }
        return true;
    }

    public void onAudioDucked(boolean z) {
        Log.v("BgmPlayerV2", "onAudioDucked{" + z + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mPlayerRef + "}");
        audioFocusChanged(z ^ true);
    }

    public void onAudioEnabled(boolean z) {
        Log.v("BgmPlayerV2", "onAudioEnabled{" + z + "}");
        audioFocusChanged(z);
    }

    public void pause() {
        Log.d("BgmPlayerV2", "pause=" + this);
        MediaPlayerRef mediaPlayerRef = this.mPlayerRef;
        if (mediaPlayerRef != null) {
            mediaPlayerRef.isPaused = true;
            Message obtain = Message.obtain(this.mHandler, 2);
            obtain.obj = this.mPlayerRef;
            this.mHandler.sendMessage(obtain);
        }
    }

    public void resume(int i2) {
        Log.d("BgmPlayerV2", "resume", Boolean.valueOf(isPaused()));
        if (isPaused()) {
            this.mPlayerRef.isPaused = false;
            Message obtain = Message.obtain(this.mHandler, 1);
            obtain.obj = this.mPlayerRef;
            obtain.arg1 = i2;
            this.mHandler.sendMessage(obtain);
            return;
        }
        start();
    }

    public void setBgm(String str, String str2, boolean z) {
        stop();
        this.mBgmName = str;
        this.mMusicFilePath = str2;
        this.mIsBgm = z;
        Log.d("BgmPlayerV2", "setBgm", str, Boolean.valueOf(z), Logger.getEncodedString(this.mMusicFilePath));
    }

    public void setCallback(BgmPlayerCallback bgmPlayerCallback) {
        this.mCallback = bgmPlayerCallback;
    }

    public void setDuration(int i2) {
        int max = Math.max(i2, 10000);
        this.mDuration = max;
        Log.d("BgmPlayerV2", "setDuration", Integer.valueOf(max), Integer.valueOf(i2));
    }

    public void start() {
        stop();
        this.mPlayerRef = new MediaPlayerRef(0, this.mBgmName, this.mMusicFilePath, this.mIsBgm);
        Message obtain = Message.obtain(this.mHandler, 0);
        obtain.obj = this.mPlayerRef;
        this.mHandler.sendMessage(obtain);
        Log.d("BgmPlayerV2", "start=" + this);
    }

    public void stop() {
        Log.v("BgmPlayerV2", "stop=" + this);
        MediaPlayerRef mediaPlayerRef = this.mPlayerRef;
        if (mediaPlayerRef != null) {
            lambda$notifyFail$3(mediaPlayerRef.player);
            Message obtain = Message.obtain(this.mHandler, 3);
            obtain.obj = mediaPlayerRef;
            this.mHandler.sendMessage(obtain);
        }
    }

    public String toString() {
        return "BgmPlayerV2@" + Integer.toHexString(hashCode()) + "::" + this.mPlayerRef + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mAudioFocused + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mCurrentVolume + " " + this.mTargetVolume.get();
    }
}
