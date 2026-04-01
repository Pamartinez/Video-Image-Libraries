package com.samsung.android.gallery.plugins.motionphoto;

import A4.C0372g;
import Ba.c;
import Ba.d;
import Ba.e;
import Ba.f;
import Ba.g;
import Ba.h;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.plugins.R$color;
import com.samsung.android.gallery.plugins.R$drawable;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemParams;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoActivity extends AppCompatActivity {
    private final Blackboard mBlackboard;
    private IntentData mData;
    protected final AtomicBoolean mFirstLaunch = new AtomicBoolean(true);
    protected final AtomicBoolean mInitialized = new AtomicBoolean(false);
    private boolean mLastPlayingState;
    private View mOsd;
    private boolean mOsdEnabled;
    private View mPreview;
    private VideoCtrlView mVideoCtrl;
    private final Handler mVideoHandler;
    private MotionPhotoViewCompat mVideoViewCompat;
    private final MotionPhotoViewHolder mVideoViewHolder;
    private final Runnable player;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IntentData {
        Bitmap bitmap;
        MediaItem item;

        public IntentData(Intent intent) {
            Object[] objArr = (Object[]) Blackboard.getApplicationInstance().pop(intent.getStringExtra("data-key"));
            if (objArr != null) {
                MediaItem mediaItem = (MediaItem) objArr[0];
                this.item = mediaItem;
                Bitmap bitmap2 = (Bitmap) objArr[1];
                this.bitmap = bitmap2;
                Log.d("MotionPhotoActivity", "intentData", mediaItem, bitmap2);
                return;
            }
            throw new IllegalArgumentException("failed to get data-key from intent");
        }
    }

    public MotionPhotoActivity() {
        Blackboard instance = Blackboard.getInstance(toString());
        this.mBlackboard = instance;
        this.mVideoHandler = ThreadUtil.createMainThreadHandler();
        this.player = new d(this, 1);
        this.mVideoViewHolder = new MotionPhotoViewHolder(AppResources.getAppContext());
        instance.publish("data://activity", this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindFastOptions$2(int i2, View view) {
        Log.d("MotionPhotoActivity", "FastOptionView#onClick", Integer.valueOf(i2));
        this.mVideoViewHolder.pausePlayback();
        Functions functions = new Functions(view.getContext(), this.mData.item);
        if (i2 == R$id.action_capture) {
            functions.capture(this.mVideoViewHolder);
        } else if (i2 == R$id.action_create_gif) {
            functions.createGif(view.getContext());
        } else if (i2 == R$id.action_download) {
            functions.saveVideo();
        } else if (i2 == R$id.action_delete) {
            functions.delete(new d(this, 2));
        } else if (i2 == R$id.action_share) {
            functions.share();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindToolbar$1(Toolbar toolbar, View view) {
        toolbar.postDelayed(new d(this, 2), 120);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindVideoCtrl$4(View view) {
        Log.d("MotionPhotoActivity", "onVideoPlayClick", Boolean.valueOf(this.mVideoViewHolder.isInPlayState()), Boolean.valueOf(this.mVideoViewHolder.isPlaying()));
        if (this.mVideoViewHolder.isPlaying()) {
            this.mVideoViewHolder.pausePlayback();
            this.mVideoCtrl.pauseAni();
            return;
        }
        this.mVideoViewHolder.resumePlayback();
        this.mVideoCtrl.resumeAni();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindVideoCtrl$5(VideoCtrlView.SeekState seekState, Integer num) {
        if (seekState == VideoCtrlView.SeekState.SEEK_PROGRESS) {
            this.mVideoViewHolder.seekTo((int) ((((long) num.intValue()) * ((long) this.mVideoViewHolder.getDuration())) / 100));
        } else if (seekState == VideoCtrlView.SeekState.SEEK_BEGIN) {
            boolean isPlaying = this.mVideoViewHolder.isPlaying();
            this.mLastPlayingState = isPlaying;
            if (isPlaying) {
                this.mVideoViewHolder.pausePlayback();
                this.mVideoCtrl.pauseAni();
            }
            this.mVideoViewHolder.prepareSeekTo();
        } else if (seekState == VideoCtrlView.SeekState.SEEK_END) {
            this.mVideoViewHolder.completeSeekTo(true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3() {
        startVideo(this.mData.item);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        setOsdEnabled(!this.mOsdEnabled);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMediaPlayerInfo$7() {
        this.mVideoCtrl.setPlayButton(false);
        setOsdEnabled(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startVideo$6(MediaItem mediaItem, long j2, MediaHelper.VideoInfo videoInfo) {
        if (isDestroyed()) {
            Log.mp("MotionPhotoActivity", "prepareVideo failed. already destroyed");
            return;
        }
        mediaItem.setVideoMetadataOrientation(videoInfo.orientation);
        Log.d("MotionPhotoActivity", "prepareVideo " + MediaItemUtil.getMediaLog(mediaItem) + ArcCommonLog.TAG_COMMA + videoInfo + " +" + (System.currentTimeMillis() - j2));
        int i2 = videoInfo.orientation;
        if (i2 == 90 || i2 == 270) {
            this.mVideoViewCompat.setVideoSize(videoInfo.height, videoInfo.width);
        } else {
            this.mVideoViewCompat.setVideoSize(videoInfo.width, videoInfo.height);
        }
        this.mVideoViewCompat.setVisibility(0);
        this.mVideoViewCompat.requestLayout();
        this.mVideoViewCompat.setDebugText(videoInfo.orientation);
        this.mVideoViewHolder.bindView(this.mVideoViewCompat, this.mPreview, true);
        this.mVideoViewHolder.setVideoData(mediaItem, videoInfo.offset, videoInfo.length);
        this.mVideoViewHolder.setVideoCallback(new c(this));
        this.mVideoViewHolder.setAudioMute(false);
        this.mVideoViewHolder.setLooping(false);
        this.mVideoViewHolder.setTimeTickEnabled(true);
        this.mVideoViewHolder.startPlayback();
    }

    /* access modifiers changed from: private */
    public boolean onMediaPlayerInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        switch (i2) {
            case 9000001:
                this.mVideoCtrl.setProgress((((float) i7) * 100.0f) / ((float) mediaPlayerCompat.getDurationMs()));
                return true;
            case 9000002:
                Log.d("MotionPhotoActivity", "onMediaPlayerInfo", "MEDIA_INFO_STARTED", Integer.valueOf(i7));
                this.mVideoCtrl.initAni(this.mVideoViewHolder.getDuration());
                this.mVideoCtrl.setPlayButton(true);
                return true;
            case 9000004:
            case 9000005:
            case 9000006:
                Log.d("MotionPhotoActivity", "onMediaPlayerInfo", mediaPlayerCompat.getMediaInfoString(i2), Integer.valueOf(i7));
                ThreadUtil.runOnUiThread(this.mVideoCtrl, new d(this, 0));
                return true;
            case 9000008:
                this.mVideoViewHolder.setStartOnPrepared(this.mFirstLaunch.getAndSet(false));
                return true;
            default:
                return false;
        }
    }

    public void bindFastOptions(FastOptionView fastOptionView) {
        fastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(R$string.video_captures).setMenuId(R$id.action_capture).setFastOptionMenu(true), R$drawable.ic_gallery_ic_detail_capture);
        fastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(R$string.create_gif_menu).setMenuId(R$id.action_create_gif).setFastOptionMenu(true), R$drawable.ic_gallery_ic_detail_gif);
        fastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(R$string.share_short).setMenuId(R$id.action_share).setFastOptionMenu(true), R$drawable.gallery_ic_detail_share);
        fastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(R$string.download).setMenuId(R$id.action_download).setFastOptionMenu(true), R$drawable.gallery_ic_detail_save);
        fastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(R$string.delete).setMenuId(R$id.action_delete).setFastOptionMenu(true), R$drawable.gallery_ic_detail_delete);
        fastOptionView.setItemSelectedListener(new c(this));
    }

    public void bindPreview(PhotoPreView photoPreView) {
        this.mPreview = photoPreView;
        Bitmap bitmap = this.mData.bitmap;
        if (bitmap != null) {
            Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, false);
            photoPreView.setBasicInfo(copy, this.mData.item, new MarginParams());
            photoPreView.setImageBitmap(copy);
        }
        Objects.requireNonNull(photoPreView);
        photoPreView.post(new C0372g(12, photoPreView));
    }

    public void bindToolbar(Toolbar toolbar) {
        toolbar.setBackgroundColor(toolbar.getContext().getColor(R$color.viewer_tool_bar_background_color));
        toolbar.setNavigationIcon(R$drawable.tw_ic_ab_back_mtrl_detailview);
        toolbar.setNavigationOnClickListener(new g(0, this, toolbar));
    }

    public void bindVideoCtrl(VideoCtrlView videoCtrlView) {
        this.mVideoCtrl = videoCtrlView;
        IntentData intentData = this.mData;
        videoCtrlView.load(intentData.item, intentData.bitmap);
        videoCtrlView.setPlayClickListener(new e(this, 1));
        videoCtrlView.setSeekStateListener(new h(0, this));
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        SystemUiCompat.setFullScreen(this);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d("MotionPhotoActivity", "onConfigurationChanged", Integer.valueOf(configuration.orientation), Integer.valueOf(configuration.densityDpi), Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels));
        bindPreview((PhotoPreView) findViewById(R$id.preview));
    }

    public void onCreate(Bundle bundle) {
        TimeTickLog timeTickLog = new TimeTickLog();
        super.onCreate(bundle);
        timeTickLog.tick();
        SystemUiCompat.setActivityProperties(this);
        setContentView(R$layout.motion_photo_main);
        timeTickLog.tick();
        if (this.mData == null) {
            try {
                this.mData = new IntentData(getIntent());
            } catch (IllegalArgumentException | NullPointerException e) {
                Log.e("MotionPhotoActivity", "onCrate failed to get data e=" + e.getMessage());
                finish();
                return;
            }
        }
        this.mOsd = findViewById(R$id.osd);
        findViewById(R$id.container).setOnClickListener(new e(this, 0));
        bindToolbar((Toolbar) findViewById(R$id.toolbar));
        bindPreview((PhotoPreView) findViewById(R$id.preview));
        bindFastOptions((FastOptionView) findViewById(R$id.fast_option_view));
        this.mVideoViewCompat = (MotionPhotoViewCompat) findViewById(R$id.video_view);
        bindVideoCtrl((VideoCtrlView) findViewById(R$id.video_ctrl));
        timeTickLog.tick();
        Log.d("MotionPhotoActivity", "MotionPhotoActivity", timeTickLog.summary());
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBlackboard.reset(toString());
    }

    public void onPause() {
        super.onPause();
        this.mVideoViewHolder.setLifeCycle(false);
        stopVideoOnUi();
    }

    public void onResume() {
        super.onResume();
        this.mVideoViewHolder.setLifeCycle(true);
        this.mVideoViewHolder.activatePlayback(true);
        if (!this.mInitialized.getAndSet(true)) {
            SystemUiCompat.setFullScreen(this);
            SystemUiCompat.setSystemBarEnabled(this, false);
            startVideoOnUi();
        }
    }

    public void setOsdEnabled(boolean z) {
        int i2;
        if (z != this.mOsdEnabled) {
            Log.d("MotionPhotoActivity", "setOsdEnabled", Boolean.valueOf(z));
            this.mOsdEnabled = z;
            SystemUiCompat.setSystemBarEnabled(this, z);
            View view = this.mOsd;
            if (z) {
                i2 = 0;
            } else {
                i2 = 4;
            }
            SimpleAnimator.setVisibility(view, i2, 120);
        }
    }

    public void startVideo(MediaItem mediaItem) {
        long currentTimeMillis = System.currentTimeMillis();
        this.mVideoViewHolder.enablePlay(true);
        MediaItem mediaItem2 = mediaItem;
        this.mVideoViewHolder.computeVideo(mediaItem2, new f((Object) this, (Object) mediaItem2, currentTimeMillis, 0));
    }

    public void startVideoOnUi() {
        this.mVideoHandler.removeCallbacks(this.player);
        this.mVideoHandler.postDelayed(this.player, 100);
    }

    public void stopVideoOnUi() {
        this.mVideoViewHolder.pausePlayback();
        this.mVideoCtrl.pauseAni();
        this.mVideoHandler.removeCallbacks(this.player);
        Handler handler = this.mVideoHandler;
        MotionPhotoViewHolder motionPhotoViewHolder = this.mVideoViewHolder;
        Objects.requireNonNull(motionPhotoViewHolder);
        handler.post(new C0372g(11, motionPhotoViewHolder));
        ViewUtils.setVisibleOrGone(this.mPreview, true);
    }
}
