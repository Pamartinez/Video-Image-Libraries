package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.graphics.Bitmap;
import android.os.Handler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.GifPlayer;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Optional;
import m7.c;
import o6.o;
import o6.p;
import o6.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GifPlayDelegate extends Delegate {
    private final Handler mBgHandler = new Handler(ThreadUtil.createBackgroundThreadLooper(this.TAG));
    private final EventHandler mEventHandler;
    private PlayerHolder mPlayerHolder;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PlayerHolder {
        /* access modifiers changed from: private */
        public final MediaItem item;
        private final int key;
        /* access modifiers changed from: private */
        public GifPlayer player;
        /* access modifiers changed from: private */
        public ViewPagerHolder vh;

        public /* synthetic */ PlayerHolder(ViewPagerHolder viewPagerHolder, int i2) {
            this(viewPagerHolder);
        }

        /* access modifiers changed from: private */
        public PlayerHolder createMovie() {
            this.player = new GifPlayer();
            return this;
        }

        /* access modifiers changed from: private */
        public void swapContentView(ViewPagerHolder viewPagerHolder) {
            this.vh = viewPagerHolder;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PlayerHolder) || this.key != ((PlayerHolder) obj).key) {
                return false;
            }
            return true;
        }

        private PlayerHolder(ViewPagerHolder viewPagerHolder) {
            this.vh = viewPagerHolder;
            MediaItem mediaItem = viewPagerHolder.getMediaItem();
            this.item = mediaItem;
            this.key = mediaItem.getSimpleHashCode();
        }
    }

    public GifPlayDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
        this.mEventHandler = iStoryHighlightView.getEventHandler();
    }

    private GifPlayer getPlayer(PlayerHolder playerHolder) {
        if (playerHolder != null) {
            return playerHolder.player;
        }
        return null;
    }

    private boolean isPlayable() {
        if (this.mView.isDestroyed() || !this.mView.isViewResumed() || ((Boolean) this.mEventHandler.requestData(DataRequest.PLAYER_KEEP_PAUSED, Boolean.FALSE)).booleanValue() || !this.mEventHandler.isBottomSheetHidden()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$0(Object[] objArr) {
        play(50);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onFrameUpdate$7(PlayerHolder playerHolder, Bitmap bitmap) {
        if (playerHolder.vh != null) {
            playerHolder.vh.bindOriginImage(bitmap);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$play$1() {
        play(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$play$3() {
        MediaItem mediaItem;
        if (isPlayable()) {
            ViewPagerHolder viewHolder = getViewHolder();
            if (viewHolder != null) {
                mediaItem = viewHolder.getMediaItem();
            } else {
                mediaItem = null;
            }
            if (mediaItem == null || !mediaItem.isGif()) {
                release();
                return;
            }
            swapPlayer(viewHolder);
            this.mBgHandler.post(new a(this, this.mPlayerHolder, 3));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$releaseInternal$9(GifPlayer gifPlayer) {
        gifPlayer.releaseMovie();
        Log.d(this.TAG, "release GifPlayer", Integer.valueOf(gifPlayer.hashCode()));
    }

    /* access modifiers changed from: private */
    /* renamed from: onFrameUpdate */
    public void lambda$playInternal$8(PlayerHolder playerHolder, Bitmap bitmap) {
        if (!playerHolder.equals(this.mPlayerHolder)) {
            this.mBgHandler.post(new a(this, playerHolder, 0));
        } else {
            ThreadUtil.runOnUiThread(new a(playerHolder, bitmap));
        }
    }

    /* access modifiers changed from: private */
    public void onKeepPause(Object... objArr) {
        if (objArr[0].booleanValue()) {
            stop();
        } else {
            play();
        }
    }

    /* access modifiers changed from: private */
    public void onPlayStart() {
        Log.d(this.TAG, "onPlayStart");
    }

    private void play() {
        ThreadUtil.runOnUiThread(new q(this, 1));
    }

    /* access modifiers changed from: private */
    /* renamed from: playInternal */
    public void lambda$play$2(PlayerHolder playerHolder) {
        GifPlayer player = getPlayer(playerHolder);
        if (player != null) {
            if (!player.hasMovie()) {
                player.decodeMovie(AppResources.getAppContext(), playerHolder.item, new b(this, playerHolder), new p(0, this));
            }
            player.startMovie();
        }
    }

    private void release() {
        this.mBgHandler.post(new a(this, this.mPlayerHolder, 2));
        this.mPlayerHolder = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: releaseInternal */
    public void lambda$release$5(PlayerHolder playerHolder) {
        Optional.ofNullable(getPlayer(playerHolder)).ifPresent(new o(this, 2));
    }

    private void stop() {
        this.mBgHandler.post(new a(this, this.mPlayerHolder, 1));
    }

    /* access modifiers changed from: private */
    /* renamed from: stopInternal */
    public void lambda$stop$4(PlayerHolder playerHolder) {
        Optional.ofNullable(getPlayer(playerHolder)).ifPresent(new c(28));
    }

    private void swapPlayer(ViewPagerHolder viewPagerHolder) {
        PlayerHolder playerHolder = new PlayerHolder(viewPagerHolder, 0);
        if (!playerHolder.equals(this.mPlayerHolder)) {
            release();
            PlayerHolder d = playerHolder.createMovie();
            this.mPlayerHolder = d;
            Log.d(this.TAG, "create GifPlayer", Integer.valueOf(d.player.hashCode()));
        } else {
            Log.d(this.TAG, "swap contentView");
        }
        this.mPlayerHolder.swapContentView(viewPagerHolder);
    }

    public void addListenEvent() {
        addEvent(Event.PLAYER_KEEP_PAUSE, new o(this, 0));
        addEvent(Event.VIEW_PAGER_SELECTED, new o(this, 1));
    }

    public ViewPagerHolder getViewHolder() {
        return (ViewPagerHolder) this.mEventHandler.requestData(DataRequest.CURRENT_VIEW_HOLDER);
    }

    public void handleResolutionChange(int i2) {
        stop();
        play(100);
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        stop();
        play(100);
    }

    public void onDestroy() {
        super.onDestroy();
        release();
        this.mBgHandler.getLooper().quitSafely();
    }

    public void onPause() {
        super.onPause();
        stop();
    }

    public void onResume() {
        super.onResume();
        play(100);
    }

    private void play(int i2) {
        ThreadUtil.postOnUiThreadDelayed(new q(this, 0), (long) i2);
    }
}
