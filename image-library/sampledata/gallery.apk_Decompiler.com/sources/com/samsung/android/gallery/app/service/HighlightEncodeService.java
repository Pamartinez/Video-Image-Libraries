package com.samsung.android.gallery.app.service;

import A.a;
import A4.C0369d;
import A4.I;
import Ba.g;
import C3.C0391a;
import E5.b;
import E7.c;
import Fa.V;
import W8.C0579a;
import a8.d;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import bc.C0584a;
import c0.C0086a;
import c4.C0432b;
import c4.C0433c;
import c4.C0434d;
import c4.C0435e;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.dialog.NormalDialogHelper;
import com.samsung.android.gallery.module.service.notification.StoryNotificationHelper;
import com.samsung.android.gallery.module.story.transcode.EncodeEventListener;
import com.samsung.android.gallery.module.story.transcode.HighlightEncoder;
import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;
import com.samsung.android.gallery.module.story.transcode.config.Mode;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.type.PendingShare;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import z2.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightEncodeService extends BaseService implements EncodeEventListener {
    protected Blackboard mBlackboard;
    private final SubscriberListener mCancelListener = new C0391a(20, this);
    protected DialogHelper mDialogHelper;
    private EncodeInfo mEncodeInfo;
    private HighlightEncoder mEncoder;
    private final AtomicBoolean mInterrupt = new AtomicBoolean(false);
    private StoryNotificationHelper mNotificationHelper;
    private String mNotificationMessage;
    private String mNotificationTitle;
    private int mPercentage;
    private volatile Handler mProgressHandler;
    private int mRequestCode = 1796;
    private String mResultFilePath;
    private HandlerThread mThread;
    private volatile Looper mThreadLooper;
    private int mUniqueKey;

    private void dismissNotification(int i2) {
        Optional.ofNullable(this.mNotificationHelper).ifPresent(new C0369d(i2, 16));
    }

    private void executeHandlePost(Runnable runnable) {
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0435e(runnable, 0));
    }

    private Mode getMode(Blackboard blackboard) {
        String str = (String) blackboard.read("location://variable/currentv1");
        if (this.mRequestCode == 2321) {
            return Mode.MOTION_PHOTO;
        }
        if (LocationKey.isSelectedItems(str)) {
            return Mode.SLIDESHOW;
        }
        return Mode.STORY;
    }

    private int getNotificationId(String str) {
        if (str != null) {
            return str.hashCode();
        }
        return this.TAG.hashCode();
    }

    private String getNotificationMessage(boolean z) {
        int i2;
        if (z && isShare()) {
            return getString(R.string.video_saved_tap_to_share_it);
        }
        if (this.mNotificationMessage == null) {
            if (Mode.MOTION_PHOTO.equals(this.mEncodeInfo.mode)) {
                i2 = R.string.merge_video_clips_option;
            } else {
                i2 = R.string.saving_video;
            }
            this.mNotificationMessage = getString(i2);
        }
        return this.mNotificationMessage;
    }

    private String getNotificationTitle() {
        return FileUtils.getNameFromPath(this.mResultFilePath);
    }

    private void initializeDialogHelper() {
        NormalDialogHelper normalDialogHelper = new NormalDialogHelper();
        this.mDialogHelper = normalDialogHelper;
        normalDialogHelper.init(this.mBlackboard);
    }

    private void initializeNotificationHelper() {
        StoryNotificationHelper storyNotificationHelper = new StoryNotificationHelper(this, getNotificationId(this.mResultFilePath), this.TAG, "com.samsung.android.gallery.app.service.HighlightEncodeService");
        this.mNotificationHelper = storyNotificationHelper;
        storyNotificationHelper.create(this.mNotificationMessage);
        this.mNotificationHelper.show(this);
    }

    private boolean isHiddenShare() {
        if (!isShare() || this.mUniqueKey == 0) {
            return false;
        }
        return true;
    }

    private boolean isShare() {
        int i2 = this.mRequestCode;
        if (i2 == 1797 || i2 == 1798 || i2 == 1802) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCompleted$6(boolean z, Blackboard blackboard) {
        blackboard.postEvent(EventMessage.obtain(1092, new Object[]{Boolean.valueOf(z), this.mResultFilePath, Integer.valueOf(this.mRequestCode)}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCompleted$7(Blackboard blackboard, boolean z) {
        unsubscribeEvents();
        this.mDialogHelper.dismissDialog();
        Optional.ofNullable(blackboard).ifPresent(new c(this, z, 7));
        if (z) {
            lambda$showSuccessToast$9(this.mResultFilePath);
        } else {
            Utils.showToast((Context) this, getString(R.string.could_not_save_video));
        }
        Log.d(this.TAG, "onCompleted", Boolean.valueOf(z));
        onCompletedInternal(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onStartService$2(Boolean bool) {
        if (!bool.booleanValue() || this.mInterrupt.get()) {
            Log.e((CharSequence) this.TAG, "prepare failed", bool, Boolean.valueOf(this.mInterrupt.get()));
        } else {
            startEncode();
        }
        this.mInterrupt.set(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTerminateService$1(Handler handler) {
        handler.post(new C0584a(8, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareMotionPhotoInfo$13(EncodeInfo encodeInfo, AtomicBoolean atomicBoolean, Consumer consumer) {
        boolean z;
        Iterator<MotionPhotoUtils.SectionInfo> it;
        List<MotionPhotoUtils.SectionInfo> list;
        List list2;
        TimeTickLog timeTickLog;
        float f;
        EncodeInfo encodeInfo2 = encodeInfo;
        Consumer consumer2 = consumer;
        TimeTickLog timeTickLog2 = new TimeTickLog(C0212a.p(new StringBuilder(), this.TAG, ":prepareMotionPhotoInfo"));
        List<ThumbnailInterface> list3 = (List) encodeInfo2.kenBurnsMap.keySet().stream().sorted(Comparator.comparingLong(new b(9))).collect(Collectors.toList());
        ArrayList arrayList = new ArrayList();
        for (ThumbnailInterface thumbnailInterface : list3) {
            if (atomicBoolean.get()) {
                consumer2.accept(Boolean.FALSE);
                return;
            }
            MotionPhotoUtils.SectionInfo sectionInfo = new MotionPhotoUtils.SectionInfo();
            sectionInfo.filePath = thumbnailInterface.getPath();
            sectionInfo.dateTaken = thumbnailInterface.getDateTaken();
            sectionInfo.duration = (long) MetadataManager.getInstance().lambda$preload$0(thumbnailInterface).duration;
            arrayList.add(sectionInfo);
        }
        timeTickLog2.tick("loadVideoInfo");
        List<MotionPhotoUtils.SectionInfo> overlappingSection = MotionPhotoUtils.getOverlappingSection(arrayList);
        timeTickLog2.tick("loadSections");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        HashMap hashMap = new HashMap();
        Iterator<MotionPhotoUtils.SectionInfo> it2 = overlappingSection.iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            MotionPhotoUtils.SectionInfo next = it2.next();
            int i7 = (int) (next.endPosition - next.startPosition);
            i2 += i7;
            ThumbnailInterface thumbnailInterface2 = (ThumbnailInterface) list3.stream().filter(new I(16, next)).findAny().orElse((Object) null);
            if (thumbnailInterface2 != null) {
                KenBurnsInfo kenBurnsInfo = encodeInfo2.kenBurnsMap.get(thumbnailInterface2);
                if (kenBurnsInfo != null) {
                    KenBurnsInfo.Property transitionInProperty = kenBurnsInfo.getTransitionInProperty();
                    if (i7 > 150) {
                        f = ((float) (i7 - 150)) / ((float) i7);
                    } else {
                        f = 1.0f;
                    }
                    transitionInProperty.setDelay(f).setInitAlpha(0.0f).setTargetAlpha(1.0f);
                }
                linkedHashMap.put(thumbnailInterface2, kenBurnsInfo);
                hashMap.put(thumbnailInterface2, new Pair(Long.valueOf(next.startPosition), Long.valueOf(next.endPosition)));
                long j2 = (long) MetadataManager.getInstance().lambda$preload$0(thumbnailInterface2).duration;
                String str = this.TAG;
                list2 = list3;
                StringBuilder sb2 = new StringBuilder("datetaken[");
                list = overlappingSection;
                sb2.append(thumbnailInterface2.getDateTaken());
                sb2.append("]");
                String sb3 = sb2.toString();
                it = it2;
                StringBuilder sb4 = new StringBuilder("section[");
                timeTickLog = timeTickLog2;
                sb4.append(next.startPosition);
                sb4.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb4.append(next.endPosition);
                sb4.append("(");
                Log.d(str, "loaded MotionPhoto SectionInfo", sb3, C0212a.o(sb4, next.endPosition - next.startPosition, ")]"), a.e(j2, "duration[", "]"), MediaItemUtil.getSimpleLog((MediaItem) thumbnailInterface2));
            } else {
                list2 = list3;
                list = overlappingSection;
                it = it2;
                timeTickLog = timeTickLog2;
            }
            Consumer consumer3 = consumer;
            timeTickLog2 = timeTickLog;
            list3 = list2;
            overlappingSection = list;
            it2 = it;
        }
        List list4 = list3;
        List<MotionPhotoUtils.SectionInfo> list5 = overlappingSection;
        encodeInfo2.setDuration(i2).setKenBurnMap(linkedHashMap).setSectionInfoMap(hashMap);
        timeTickLog2.tock(0);
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        ThreadUtil.runOnUiThread(new V(consumer, 1, z));
        Log.d(this.TAG, "prepareMotionPhotoInfo Result", C0086a.i(i2, "duration="), "sectionSize=" + list5.size(), "originSize=" + list4.size());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showSnackBar$14(MediaItem mediaItem, View view) {
        new VuLauncher(this.mBlackboard).disableTimeline().forceDisableTransition().launchSingle(mediaItem);
    }

    private MediaItem loadItem(String str) {
        Cursor query = DbCompat.query(new QueryParams(DbKey.ALL_PICTURES).setFilePath(str));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    MediaItem create = MediaItemBuilder.create(query);
                    query.close();
                    return create;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query == null) {
            return null;
        }
        query.close();
        return null;
        throw th;
    }

    /* access modifiers changed from: private */
    public void onDialogCancelled(Object obj, Bundle bundle) {
        Log.d(this.TAG, "dialog is cancelled.");
        this.mInterrupt.set(true);
        onInterruptService();
    }

    /* access modifiers changed from: private */
    /* renamed from: onInterruptInternal */
    public void lambda$onInterruptService$3(HighlightEncoder highlightEncoder) {
        if (highlightEncoder != null) {
            highlightEncoder.stopEncoding();
        } else {
            Log.w(this.TAG, "onInterruptService : no encoder");
        }
        Optional.ofNullable(this.mDialogHelper).ifPresent(new C0579a(27));
        Optional.ofNullable(this.mBlackboard).ifPresent(new C0579a(28));
        dismissNotification(getNotificationId(this.mResultFilePath));
        Blackboard.getApplicationInstance().erase("data://running_story_highlight_encoding");
        Log.d(this.TAG, "stop by onInterrupt");
        onTerminateService();
    }

    private void onInterruptService() {
        HighlightEncoder highlightEncoder = this.mEncoder;
        this.mEncoder = null;
        executeHandlePost(new androidx.window.embedding.c(6, this, highlightEncoder));
        if (!isHiddenShare()) {
            Utils.showToast((Context) this, getString(R.string.save_canceled));
        }
    }

    private void onStartService(Intent intent) {
        onPrepare(intent, new C0434d(this, 0));
    }

    private void release() {
        this.mThread.quitSafely();
        this.mThreadLooper.quit();
        this.mProgressHandler = null;
        this.mThread = null;
        this.mBlackboard = null;
    }

    private void showDialog() {
        int i2;
        if (isShare()) {
            if (isHiddenShare()) {
                i2 = R.string.preparing_story_for_sharing;
            } else {
                i2 = R.string.saving_video_to_share_it;
            }
        } else if (Mode.MOTION_PHOTO.equals(this.mEncodeInfo.mode)) {
            i2 = R.string.merge_video_clips_option;
        } else {
            i2 = R.string.saving_video;
        }
        this.mDialogHelper.showDialog(getString(i2), -1, -1, 0, false, false);
    }

    /* access modifiers changed from: private */
    /* renamed from: showSnackBar */
    public void lambda$showSuccessToast$8(String str) {
        MediaItem loadItem = loadItem(str);
        Activity readActivity = BlackboardUtils.readActivity(this.mBlackboard);
        if (loadItem != null && readActivity != null) {
            View findViewById = readActivity.findViewById(16908290);
            String string = AppResources.getString(R.string.video_saved_in, BucketUtils.getTranslatedDirectory(str));
            if (FileUtils.exists(str)) {
                r j2 = r.j(findViewById, 0, 0, false, string);
                j2.k(j2.f2220h.getText(R.string.view), new g(17, this, loadItem));
                j2.l();
                return;
            }
            Log.e(this.TAG, "show snackBar failed");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: showSuccessToast */
    public void lambda$showSuccessToast$9(String str) {
        if (!ThreadUtil.isMainThread()) {
            ThreadUtil.postOnUiThread(new C0433c(this, str, 1));
        } else if (isHiddenShare()) {
        } else {
            if (Mode.MOTION_PHOTO.equals(this.mEncodeInfo.mode)) {
                ThreadUtil.postOnBgThreadDelayed(new C0433c(this, str, 0), 500);
                return;
            }
            Utils.showToast(AppResources.getAppContext(), AppResources.getString(R.string.video_saved_in, BucketUtils.translatePath(str, false)));
        }
    }

    private void startEncode() {
        HighlightEncoder highlightEncoder = new HighlightEncoder(this.mEncodeInfo);
        this.mEncoder = highlightEncoder;
        highlightEncoder.setListener(this);
        Blackboard.getApplicationInstance().publish("data://running_story_highlight_encoding", Boolean.TRUE);
        try {
            this.mEncoder.encode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void subscribeEvents() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.subscribe("command://CancelDialog", this.mCancelListener);
        }
    }

    private void unsubscribeEvents() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.unsubscribe("command://CancelDialog", this.mCancelListener);
        }
    }

    public final void executeHandlePostDelay(Runnable runnable, long j2) {
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0432b(runnable, j2, 0));
    }

    public int getPercentage() {
        return this.mPercentage;
    }

    public void onCallActivity(Intent intent) {
        String str;
        if (isShare()) {
            if (intent != null) {
                str = intent.getStringExtra("notification_data");
            } else {
                str = this.mResultFilePath;
            }
            Log.d(this.TAG, "call gallery activity, finish service", Logger.getEncodedString(str));
            if (TextUtils.isEmpty(str) || !new SecureFile(str).exists()) {
                Log.w(this.TAG, "onCallActivity fail due to not exist");
                return;
            }
            savePathToPreference(str);
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.GalleryActivity");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.addFlags(805306368);
            intent2.putExtra("output_file_path", str);
            intent2.putExtra("key-pending-sharing-event", true);
            intent2.putExtra("usage_type", ConvertingUsageType.COMMON_SHARE.ordinal());
            Blackboard blackboard = this.mBlackboard;
            if (blackboard != null) {
                intent2.putExtra("key_pending_blackboard_name", blackboard.getName());
            }
            startActivity(intent2);
            dismissNotification(getNotificationId(str));
        }
    }

    public void onCompleted(boolean z) {
        long j2;
        B8.g gVar = new B8.g((Object) this, (Object) this.mBlackboard, z, 8);
        if (z) {
            j2 = 0;
        } else {
            j2 = 200;
        }
        executeHandlePostDelay(gVar, j2);
    }

    public void onCompletedInternal(boolean z) {
        Log.d(this.TAG, "onCompletedInternal", Boolean.valueOf(z), this.mEncoder);
        Blackboard.getApplicationInstance().erase("data://running_story_highlight_encoding");
        if (this.mEncoder == null) {
            stopForeground(true);
            executeHandlePostDelay(new C0584a(8, this), 500);
        } else if (!z || !isShare()) {
            stopForeground(true);
            dismissNotification(getNotificationId(this.mResultFilePath));
        } else {
            stopForeground(false);
            StoryNotificationHelper storyNotificationHelper = this.mNotificationHelper;
            if (storyNotificationHelper != null) {
                storyNotificationHelper.showStopNotification(this, FileUtils.getNameFromPath(this.mResultFilePath), getNotificationMessage(true), this.mResultFilePath);
            }
        }
        this.mEncoder = null;
    }

    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread(this.TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        this.mThreadLooper = this.mThread.getLooper();
        this.mProgressHandler = new Handler(this.mThreadLooper);
    }

    public void onDestroy() {
        release();
        super.onDestroy();
    }

    public void onPrepare(Intent intent, Consumer<Boolean> consumer) {
        this.mBlackboard = Blackboard.getInstance(intent.getStringExtra("blackboard_name"));
        this.mRequestCode = intent.getIntExtra("key-request-code", 1796);
        this.mResultFilePath = intent.getStringExtra("output_file_path");
        this.mUniqueKey = intent.getIntExtra("key-unique-key", 0);
        this.mEncodeInfo = EncodeInfo.from(intent, this.mBlackboard).setMode(getMode(this.mBlackboard));
        this.mNotificationTitle = getNotificationTitle();
        this.mNotificationMessage = getNotificationMessage(false);
        initializeDialogHelper();
        initializeNotificationHelper();
        startForeground(this.mNotificationHelper.getSummaryId(), this.mNotificationHelper.getSummaryNotification());
        subscribeEvents();
        this.mInterrupt.set(false);
        if (Mode.MOTION_PHOTO.equals(this.mEncodeInfo.mode)) {
            prepareMotionPhotoInfo(this.mEncodeInfo, consumer, this.mInterrupt);
        } else {
            consumer.accept(Boolean.TRUE);
        }
    }

    public void onProgressChanged(float f) {
        this.mPercentage = (int) (100.0f * f);
        this.mDialogHelper.updateDialog((int) f, -1, getPercentage());
        StoryNotificationHelper storyNotificationHelper = this.mNotificationHelper;
        if (storyNotificationHelper != null) {
            storyNotificationHelper.update(this, getPercentage(), this.mNotificationTitle, this.mNotificationMessage);
        }
    }

    public int onStartCommand(Intent intent, int i2, int i7) {
        if (intent != null) {
            String action = intent.getAction();
            Log.d(this.TAG, "service receives [" + action + "]");
            if (action != null) {
                char c5 = 65535;
                switch (action.hashCode()) {
                    case -670797158:
                        if (action.equals("com.samsung.android.gallery.app.service.STOP_SERVICE")) {
                            c5 = 0;
                            break;
                        }
                        break;
                    case 980299926:
                        if (action.equals("com.samsung.android.gallery.app.service.START_SERVICE")) {
                            c5 = 1;
                            break;
                        }
                        break;
                    case 1768765646:
                        if (action.equals("com.samsung.android.gallery.app.service.CALL_ACTIVITY")) {
                            c5 = 2;
                            break;
                        }
                        break;
                }
                switch (c5) {
                    case 0:
                        onInterruptService();
                        break;
                    case 1:
                        onStartService(intent);
                        break;
                    case 2:
                        onCallActivity(intent);
                        break;
                    default:
                        onTerminateService();
                        break;
                }
            }
        } else {
            Log.w(this.TAG, "unable to operate startCommand");
        }
        return 2;
    }

    public void onStarted() {
        if (!Mode.MOTION_PHOTO.equals(this.mEncodeInfo.mode)) {
            showDialog();
        }
    }

    public void onTerminateService() {
        unsubscribeEvents();
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0434d(this, 1));
    }

    public void prepareMotionPhotoInfo(EncodeInfo encodeInfo, Consumer<Boolean> consumer, AtomicBoolean atomicBoolean) {
        showDialog();
        SimpleThreadPool.getInstance().submit(new d((Object) this, (Object) encodeInfo, (Object) atomicBoolean, (Object) consumer, 3));
    }

    public void savePathToPreference(String str) {
        PendingShare.set(str);
    }
}
