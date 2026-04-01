package com.samsung.android.gallery.app.remote;

import N2.j;
import U3.a;
import V3.b;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Display;
import com.samsung.android.gallery.app.remote.v2.GalleryBasePresentation;
import com.samsung.android.gallery.app.remote.v2.GalleryPresentation;
import com.samsung.android.gallery.app.remote.v2.IPresentationView;
import com.samsung.android.gallery.app.remote.v2.IVuDispatcher;
import com.samsung.android.gallery.app.remote.v2.PresentationOccupiedException;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPager;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPagerAdapter;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayStatusListener;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowServiceOnRemoteV2 extends BaseService {
    private final HashMap<String, Consumer<Intent>> actions = new HashMap<>();
    private Blackboard mBlackboard;
    private final MediaData.OnDataChangeListener mDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new b(19, SlideshowServiceOnRemoteV2.this));
        }
    };
    private DisplayManagerCompat mDisplayManager;
    private final DisplayStatusListener mExtendedDisplayListener = new DisplayStatusListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onScreenSharingStatusChanged$0() {
            SlideshowServiceOnRemoteV2.this.mPresentation.show();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onScreenSharingStatusChanged$1() {
            SlideshowServiceOnRemoteV2.this.mPresentation.hide();
        }

        public void onScreenSharingStatusChanged(int i2) {
            if (i2 == 6) {
                ThreadUtil.runOnUiThread(new b(0, this));
                SlideshowServiceOnRemoteV2.this.resumeSlideshow();
                SlideshowServiceOnRemoteV2.this.mSharingPaused = false;
            } else if (i2 == 7) {
                SlideshowServiceOnRemoteV2.this.pauseSlideshow();
                ThreadUtil.runOnUiThread(new b(1, this));
                SlideshowServiceOnRemoteV2.this.mSharingPaused = true;
            }
        }
    };
    private MediaData mMediaData;
    private final RemoteSlideshowNotification mNotification = new RemoteSlideshowNotification();
    private int mPosition;
    GalleryPresentation mPresentation;
    boolean mSharingPaused;
    private Slideshow mSlideshow;

    private void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
        }
    }

    private PresentationViewPagerAdapter createAdapter(MediaData mediaData) {
        return new PresentationViewPagerAdapter(getApplicationContext(), mediaData);
    }

    private IPresentationView createPresentationViewPager() {
        PresentationViewPager presentationViewPager = new PresentationViewPager(getApplicationContext(), createAdapter(this.mMediaData), false);
        presentationViewPager.startAdapterWithPosition(0);
        return presentationViewPager;
    }

    private Display getPresentationDisplay() {
        int primaryPresentationId;
        DisplayManagerCompat displayManagerCompat = SeApiCompat.getDisplayManagerCompat(AppResources.getAppContext());
        if (displayManagerCompat == null || (primaryPresentationId = displayManagerCompat.getPrimaryPresentationId()) == -1) {
            return null;
        }
        return displayManagerCompat.getDisplay(primaryPresentationId);
    }

    private void openMediaData(String str) {
        closeMediaData();
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(str);
        this.mMediaData = open;
        open.register(this.mDataChangeListener);
    }

    /* access modifiers changed from: private */
    public void pauseSlideshow(Intent intent) {
        pauseSlideshow();
    }

    /* access modifiers changed from: private */
    public void resumeSlideshow(Intent intent) {
        if (!this.mSharingPaused) {
            resumeSlideshow();
        }
    }

    /* access modifiers changed from: private */
    public void startSlideshow(Intent intent) {
        String stringExtra = intent.getStringExtra("location_key");
        this.mPosition = UnsafeCast.toInt(ArgumentsUtil.getArgValue(stringExtra, Message.KEY_POSITION));
        this.mBlackboard = Blackboard.getInstance(intent.getStringExtra("blackboard_name"));
        Slideshow slideshow = this.mSlideshow;
        if (slideshow != null && slideshow.isRunning()) {
            Utils.showToast(getApplicationContext(), (int) R.string.unable_to_play_presentation);
        } else if (TextUtils.isEmpty(stringExtra) || this.mBlackboard == null) {
            stopSlideshow();
        } else {
            subscribeOnSlideshow();
            openMediaData(DataKey.getSlideshowDataKey(stringExtra));
            MediaData mediaData = this.mMediaData;
            if (mediaData == null || mediaData.getCount() == 0) {
                stopSlideshow();
                return;
            }
            this.mPresentation.setPresentationView(createPresentationViewPager());
            this.mSlideshow = new Slideshow(this.mPresentation, this.mMediaData);
            startSlideshow();
        }
    }

    /* access modifiers changed from: private */
    public void stopSlideshow(Intent intent) {
        stopSlideshow();
    }

    private void subscribeOnSlideshow() {
        if (this.mDisplayManager == null) {
            this.mDisplayManager = SeApiCompat.getDisplayManagerCompat(AppResources.getAppContext());
        }
        this.mSharingPaused = this.mDisplayManager.isScreenSharingPaused();
        this.mDisplayManager.registerDisplayStatusListener(this.mExtendedDisplayListener, new Handler(Looper.getMainLooper()));
    }

    private void unsubscribeOnSlideshow() {
        DisplayManagerCompat displayManagerCompat = this.mDisplayManager;
        if (displayManagerCompat != null) {
            displayManagerCompat.unregisterDisplayStatusListener(this.mExtendedDisplayListener);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        GalleryBasePresentation galleryBasePresentation;
        super.onCreate();
        Log.rm("SlideshowServiceOnRemoteV2", "onCreate");
        this.actions.put("com.samsung.android.gallery.app.service.SLIDESHOW_START_SERVICE", new Z3.b(this, 0));
        this.actions.put("com.samsung.android.gallery.app.service.SLIDESHOW_STOP_SERVICE", new Z3.b(this, 1));
        this.actions.put("com.samsung.android.gallery.app.service.SLIDESHOW_RESUME_SERVICE", new Z3.b(this, 2));
        this.actions.put("com.samsung.android.gallery.app.service.SLIDESHOW_PAUSE_SERVICE", new Z3.b(this, 3));
        Display presentationDisplay = getPresentationDisplay();
        try {
            String str = null;
            galleryBasePresentation = new GalleryBasePresentation(getApplicationContext(), presentationDisplay, 0, (IVuDispatcher) null);
            this.mPresentation = galleryBasePresentation;
            galleryBasePresentation.setOnDisplayRemovedListener(new a(27, this));
            startForeground(RemoteSlideshowNotification.OPERATION_ID, this.mNotification.create(getApplicationContext()));
            RemoteSlideshowNotification remoteSlideshowNotification = this.mNotification;
            if (presentationDisplay != null) {
                str = presentationDisplay.getName();
            }
            remoteSlideshowNotification.setDisplayName(str);
            j.q(galleryBasePresentation);
            return;
        } catch (PresentationOccupiedException unused) {
            Log.d("SlideshowServiceOnRemoteV2", "prevent slide show service on remote : not implemented yet.");
            return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public final void onDataChangedOnUi() {
        if (this.mSlideshow != null) {
            MediaData mediaData = this.mMediaData;
            if (mediaData == null || mediaData.getCount() == 0) {
                stopSlideshow();
            } else {
                startSlideshow();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        GalleryPresentation galleryPresentation = this.mPresentation;
        if (galleryPresentation != null) {
            galleryPresentation.dismiss();
        }
        stopSlideshow();
        this.actions.clear();
    }

    public int onStartCommand(Intent intent, int i2, int i7) {
        if (intent == null) {
            Consumer consumer = this.actions.get("com.samsung.android.gallery.app.service.SLIDESHOW_STOP_SERVICE");
            Objects.requireNonNull(consumer);
            consumer.accept(intent);
        } else if (!this.actions.containsKey(intent.getAction())) {
            return 2;
        } else {
            Consumer consumer2 = this.actions.get(intent.getAction());
            Objects.requireNonNull(consumer2);
            consumer2.accept(intent);
        }
        return 2;
    }

    /* access modifiers changed from: private */
    public void pauseSlideshow() {
        Slideshow slideshow = this.mSlideshow;
        if (slideshow != null) {
            slideshow.stop();
            this.mNotification.pause(getApplicationContext());
        }
    }

    public void stopSlideshow() {
        if (this.mSlideshow != null) {
            this.mNotification.cancel();
            this.mSlideshow.stop();
        }
        closeMediaData();
        stopForeground(true);
        unsubscribeOnSlideshow();
        stopSelf();
    }

    /* access modifiers changed from: private */
    public void resumeSlideshow() {
        Slideshow slideshow = this.mSlideshow;
        if (slideshow != null) {
            slideshow.stop();
            this.mSlideshow.start();
            this.mNotification.resume(getApplicationContext());
        }
    }

    private void startSlideshow() {
        Slideshow slideshow = this.mSlideshow;
        if (slideshow != null) {
            slideshow.init(this.mPosition);
            this.mNotification.show();
            if (!this.mSharingPaused) {
                resumeSlideshow();
            }
        }
    }
}
