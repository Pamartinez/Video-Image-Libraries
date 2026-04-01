package com.samsung.android.gallery.module.publisher;

import A.a;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.DataStamp;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mdebase.constants.MdeConstants;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DataChangeEventPublisher extends Subscriber {
    private static final Uri CLOUD_WATCH_URI = MediaUri.getInstance().getCloudWatchUri();
    private static final Uri IMAGE_WATCH_URI = MediaUri.getInstance().getImageWatchUri();
    private static final Uri LOCAL_ALBUMS_WATCH_URI;
    private static final Uri VIDEO_WATCH_URI = MediaUri.getInstance().getVideoWatchUri();
    private Context mAppContext;
    private boolean mCollect;
    private final ConcurrentHashMap<Integer, ArrayList<EventMessage>> mDeferredMap = new ConcurrentHashMap<>();
    /* access modifiers changed from: private */
    public final DelayManager mDelayManager;
    private final HashMap<Integer, EventMessage> mEventCollectList = new HashMap<>();
    private Runnable mInitChecker;
    private final AtomicBoolean mInitialized = new AtomicBoolean(false);
    protected final ArrayList<ContentObserver> mObservers = new ArrayList<>();
    private final EventPoster mPoster;
    private final AtomicBoolean mReady = new AtomicBoolean(false);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AutoUpdateUriHolder {
        static final Uri uri = CmhUri.getAutoUpdate();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultContentObserver extends ContentObserver {
        private static final Uri[] MEDIA_BATCH_URIS = {Uri.parse("content://media"), Uri.parse("content://media/external")};
        private BooleanSupplier mEnabler;
        private final int mEventKey;
        private final WeakReference<DataChangeEventPublisher> mPublisher;

        public DefaultContentObserver(DataChangeEventPublisher dataChangeEventPublisher, int i2) {
            super(ThreadUtil.createMainThreadHandler());
            this.mEventKey = i2;
            this.mPublisher = new WeakReference<>(dataChangeEventPublisher);
        }

        private boolean isMediaBatchUri(Uri uri) {
            if (uri != null) {
                for (Uri equals : MEDIA_BATCH_URIS) {
                    if (equals.equals(uri)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean isSpaceDetailDataUri(Uri uri) {
            if (uri == null) {
                return false;
            }
            String path = uri.getPath();
            if (path == null || !path.endsWith("/space")) {
                return true;
            }
            return false;
        }

        private void publishChange(int i2, boolean z, Uri uri) {
            DataChangeEventPublisher dataChangeEventPublisher = this.mPublisher.get();
            if (dataChangeEventPublisher != null) {
                BooleanSupplier booleanSupplier = this.mEnabler;
                if (booleanSupplier == null || booleanSupplier.getAsBoolean()) {
                    Log.d("DataChangeEventPublisher", "onChange {" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + z + GlobalPostProcInternalPPInterface.SPLIT_REGEX + uri + GlobalPostProcInternalPPInterface.SPLIT_REGEX + dataChangeEventPublisher.mDelayManager.get() + "}");
                    dataChangeEventPublisher.processOnChange(i2, z, uri);
                    return;
                }
                Log.d("DataChangeEventPublisher", "onChange skip by enabler", Integer.valueOf(i2));
                return;
            }
            Log.e("DataChangeEventPublisher", "onChange {" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + z + GlobalPostProcInternalPPInterface.SPLIT_REGEX + uri + "} skip by null publisher");
        }

        public void onChange(boolean z) {
            publishChange(this.mEventKey, z, (Uri) null);
        }

        public void setEnabler(BooleanSupplier booleanSupplier) {
            this.mEnabler = booleanSupplier;
        }

        public void onChange(boolean z, Uri uri) {
            int i2 = this.mEventKey;
            if (i2 == 106 && isSpaceDetailDataUri(uri)) {
                i2 = 109;
            }
            if (!isMediaBatchUri(uri)) {
                publishChange(i2, z, uri);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DelayManager {
        public abstract void clear();

        public abstract int get();

        public abstract void update(boolean z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DynamicDelayManager extends DelayManager {
        private final Blackboard mBlackboard;
        private int mDynamicDelayTime = StatusCodes.INPUT_MISSING;

        public DynamicDelayManager(Blackboard blackboard) {
            this.mBlackboard = blackboard;
        }

        public void clear() {
            this.mDynamicDelayTime = StatusCodes.INPUT_MISSING;
        }

        public int get() {
            return this.mDynamicDelayTime;
        }

        public void update(boolean z) {
            if (z) {
                this.mDynamicDelayTime = 1000;
                return;
            }
            int i2 = this.mDynamicDelayTime;
            if (i2 < 1010) {
                this.mDynamicDelayTime = i2 + 1;
                return;
            }
            if (i2 < 4000 && "lifecycle://on_activity_pause".equals(this.mBlackboard.read("lifecycle://last_activity_lifecycle"))) {
                this.mDynamicDelayTime = TextToSpeechConst.MAX_SPEECH_INPUT;
            }
            if (this.mDynamicDelayTime < 2000) {
                this.mDynamicDelayTime = 2000;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EventPoster extends Handler {
        private final Blackboard mBlackboard;
        /* access modifiers changed from: private */
        public volatile DataStamp mDataStamp;

        public EventPoster(Blackboard blackboard) {
            super(ThreadUtil.createBackgroundThreadLooper("EventPoster"));
            this.mBlackboard = blackboard;
            resetDataStamp();
        }

        private boolean isPicturesDataChanged() {
            boolean z;
            DataStamp dataStamp = new DataStamp(queryDataStamp());
            if (this.mDataStamp == null || !this.mDataStamp.equals(dataStamp)) {
                z = true;
            } else {
                z = false;
            }
            this.mDataStamp = dataStamp;
            return z;
        }

        public static String queryDataStamp() {
            Cursor query;
            String string;
            String str = "no item";
            QueryParams queryParams = new QueryParams(DbKey.FILES_DATA_STAMP);
            queryParams.setCloudSync(SamsungCloudCompat.isCloudSyncOnCached(AppResources.getAppContext()));
            try {
                query = DbCompat.query(queryParams);
                if (query != null) {
                    if (query.moveToFirst() && (string = query.getString(0)) != null) {
                        str = string;
                    }
                }
                if (query != null) {
                    query.close();
                }
                return str;
            } catch (Exception e) {
                Log.e((CharSequence) "DataChangeEventPublisher", "queryDataStamp failed", (Throwable) e);
                return str;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public void handleMessage(Message message) {
            Log.d("DataChangeEventPublisher", "handleMessage {" + message.what + GlobalPostProcInternalPPInterface.SPLIT_REGEX + EventKey.getName(message.what) + "}");
            if (message.what == 101) {
                DataStamp dataStamp = this.mDataStamp;
                EventMessage eventMessage = (EventMessage) message.obj;
                boolean z = true;
                if (eventMessage.arg1 != 1) {
                    z = false;
                }
                if (isPicturesDataChanged() || z) {
                    Log.d("DataChangeEventPublisher", "dataStamp changed : " + dataStamp + ">" + this.mDataStamp, Boolean.valueOf(z));
                    if (SdkConfig.atLeast(SdkConfig.SEM.T)) {
                        Bundle bundle = new Bundle();
                        bundle.putString("data_stamp", this.mDataStamp.getRaw());
                        eventMessage.setData(bundle);
                    }
                    this.mBlackboard.post("command://event/DataChanged", eventMessage);
                    return;
                }
                Log.d("DataChangeEventPublisher", "dataStamp same : " + this.mDataStamp);
                return;
            }
            this.mBlackboard.post("command://event/DataChanged", message.obj);
        }

        public void resetDataStamp() {
            this.mDataStamp = new DataStamp("");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MdeUriHolder {
        static final Uri gallery_setting = MdeConstants.GALLERY_SETTING_CONTENT_URI;
        static final Uri group = MdeConstants.GROUP_CONTENT_URI;
        static final Uri invitation = MdeConstants.INVITATION_CONTENT_URI.buildUpon().appendPath("invitation").build();
        static final Uri space = MdeConstants.SPACE_CONTENT_URI;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StoryUriHolder {
        static final Uri uri = CmhUri.getStory();
    }

    static {
        Uri uri;
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            uri = LocalDatabase.MX_ALBUM_URI;
        } else {
            uri = LocalDatabase.ALBUM_URI;
        }
        LOCAL_ALBUMS_WATCH_URI = uri;
    }

    public DataChangeEventPublisher(Blackboard blackboard) {
        super(blackboard);
        this.mPoster = new EventPoster(blackboard);
        this.mDelayManager = new DynamicDelayManager(blackboard);
    }

    private ContentObserver createContentObserver(int i2) {
        return new DefaultContentObserver(this, i2);
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Object, java.util.function.BooleanSupplier] */
    private ContentObserver createMdeContentObserver(int i2) {
        DefaultContentObserver defaultContentObserver = new DefaultContentObserver(this, i2);
        if (PreferenceFeatures.CHINA.SHARING_SERVICE_ENABLER) {
            defaultContentObserver.setEnabler(new Object());
        }
        return defaultContentObserver;
    }

    private void init(Context context) {
        if (context != null && !this.mInitialized.getAndSet(true)) {
            SimpleThreadPool.getInstance().execute(new C0629i(this, context, System.currentTimeMillis(), 2));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: initContentObserver */
    public void lambda$init$4(Context context, long j2) {
        ContentObserver createContentObserver = createContentObserver(101);
        Uri uri = IMAGE_WATCH_URI;
        if (uri != null) {
            register(context, uri, createContentObserver);
        }
        Uri uri2 = VIDEO_WATCH_URI;
        if (uri2 != null && !uri2.equals(uri)) {
            register(context, uri2, createContentObserver);
        }
        Uri uri3 = CLOUD_WATCH_URI;
        if (uri3 != null && !uri3.equals(uri)) {
            register(context, uri3, createContentObserver);
        }
        ContentObserver createContentObserver2 = createContentObserver(104);
        register(context, LOCAL_ALBUMS_WATCH_URI, createContentObserver2);
        Log.i("DataChangeEventPublisher", "init ContentObserver{" + uri + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mObservers.size() + "} +" + (System.currentTimeMillis() - j2));
        if (Features.isEnabled(Features.USE_CMH)) {
            if (SdkConfig.atLeast(SdkConfig.GED.T)) {
                try {
                    register(context, AutoUpdateUriHolder.uri, createContentObserver2);
                } catch (Exception e) {
                    a.s(e, new StringBuilder("init auto-album failed. e="), "DataChangeEventPublisher");
                }
            }
            try {
                register(context, StoryUriHolder.uri, createContentObserver(102));
            } catch (Exception e7) {
                a.s(e7, new StringBuilder("init story failed. e="), "DataChangeEventPublisher");
            }
        }
        if (Features.isEnabled(Features.SUPPORT_SHARING_SERVICE) && MdeSharingService.getInstance().isServiceAvailable()) {
            try {
                register(context, MdeUriHolder.space, createMdeContentObserver(106));
                register(context, MdeUriHolder.group, createMdeContentObserver(107));
                register(context, MdeUriHolder.invitation, createMdeContentObserver(108));
                if (Features.isEnabled(Features.SUPPORT_SORT_SHARINGS)) {
                    register(context, MdeUriHolder.gallery_setting, createMdeContentObserver(112));
                }
            } catch (Exception e8) {
                a.s(e8, new StringBuilder("init mde failed. e="), "DataChangeEventPublisher");
            }
        }
        Log.i("DataChangeEventPublisher", "init ContentObserver{" + this.mObservers.size() + "} +" + (System.currentTimeMillis() - j2));
        this.mBlackboard.post("lifecycle://on_data_change_observe_done", (Object) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$deferChangeEvent$1(Integer num) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataStamp$0(DataStamp dataStamp) {
        if (this.mPoster.mDataStamp == null || this.mPoster.mDataStamp.isEmpty()) {
            Log.i("DataChangeEventPublisher", "init dataStamp : " + this.mPoster.mDataStamp + " > " + dataStamp);
            this.mPoster.mDataStamp = dataStamp;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$processDeferredChangeEvent$2(Integer num, ArrayList arrayList) {
        if ((num.intValue() == 101 || num.intValue() == 102) && arrayList != null && arrayList.size() > 0) {
            processOnChange(num.intValue(), (EventMessage) arrayList.get(0));
        }
    }

    /* access modifiers changed from: private */
    public void notifyOnChangeOnResume(Object obj, Bundle bundle) {
        if (SdkConfig.atLeast(SdkConfig.GED.B)) {
            sendMessageToPoster(EventMessage.obtain(101, 0, 0, (Object) null), 0);
        }
    }

    /* access modifiers changed from: private */
    public void onCollect(Object obj, Bundle bundle) {
        boolean z = BundleWrapper.getBoolean(bundle, "enable", false);
        this.mCollect = z;
        if (!z) {
            postCollectedEvent();
        }
    }

    /* access modifiers changed from: private */
    public void onContext(Object obj, Bundle bundle) {
        Context context = (Context) obj;
        this.mAppContext = context;
        init(context);
        ThreadUtil.postOnBgThreadDelayed(new C0639t(this, 0), 8000);
    }

    /* access modifiers changed from: private */
    public void onDataStamp(Object obj, Bundle bundle) {
        this.mPoster.post(new r(1, this, new DataStamp((String) obj)));
    }

    /* access modifiers changed from: private */
    public void onForceRefreshOnResume(Object obj, Bundle bundle) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean z = BundleWrapper.getBoolean(bundle, "force", true);
        if (booleanValue) {
            this.mEventCollectList.putIfAbsent(101, EventMessage.obtain(101, z ? 1 : 0, 0, (Object) null));
        } else {
            this.mEventCollectList.remove(101);
        }
    }

    /* access modifiers changed from: private */
    public void onPause(Object obj, Bundle bundle) {
        Log.d("DataChangeEventPublisher", "onPause " + this);
        processDeferredChangeEvent();
    }

    /* access modifiers changed from: private */
    public void onRefreshWithoutDelay(Object obj, Bundle bundle) {
        if (this.mPoster.hasMessages(101)) {
            this.mPoster.removeMessages(101);
            EventMessage obtain = EventMessage.obtain(101, 1, 0, (Object) null);
            Log.d("DataChangeEventPublisher", "onRefreshWithoutDelay send pending");
            sendMessageToPoster(obtain, 0);
            return;
        }
        Log.d("DataChangeEventPublisher", "onRefreshWithoutDelay remove delay");
        this.mDelayManager.clear();
        this.mPoster.resetDataStamp();
    }

    /* access modifiers changed from: private */
    public void onResume(Object obj, Bundle bundle) {
        int i2;
        if (!this.mCollect) {
            postCollectedEvent();
        }
        if (this.mPoster.hasMessages(101)) {
            this.mPoster.removeCallbacksAndMessages((Object) null);
            this.mPoster.resetDataStamp();
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (i2 == 1 || (SdkConfig.atLeast(SdkConfig.GED.B) && this.mReady.get())) {
            a.k(i2, "onResume EVENT_PICTURES_DATA_CHANGED/", "DataChangeEventPublisher");
            sendMessageToPoster(EventMessage.obtain(101, i2, 0, (Object) null), 0);
        }
        if (this.mDelayManager.get() != 0) {
            this.mDelayManager.update(true);
        }
    }

    /* access modifiers changed from: private */
    public void onThumbnailLoadDone(Object obj, Bundle bundle) {
        Log.d("DataChangeEventPublisher", "onThumbnailLoadDone " + this);
        processDeferredChangeEvent();
    }

    private void postCollectedEvent() {
        if (this.mEventCollectList.size() > 0) {
            for (EventMessage next : this.mEventCollectList.values()) {
                int i2 = next.what;
                boolean z = true;
                if (next.arg1 != 1) {
                    z = false;
                }
                processOnChange(i2, z, (Uri) next.obj);
            }
            this.mEventCollectList.clear();
        }
    }

    private void register(Context context, Uri uri, ContentObserver contentObserver) {
        if (context != null) {
            try {
                synchronized (this.mObservers) {
                    context.getContentResolver().registerContentObserver(uri, true, contentObserver);
                    this.mObservers.add(contentObserver);
                }
            } catch (Exception e) {
                Log.e((CharSequence) "DataChangeEventPublisher", "register failed. content observer=" + uri, (Throwable) e);
            }
        }
    }

    private void releaseInitChecker() {
        Runnable runnable = this.mInitChecker;
        if (runnable != null) {
            ThreadUtil.removeCallbackOnBgThread(runnable);
            this.mInitChecker = null;
        }
    }

    private void sendMessageToPoster(EventMessage eventMessage, long j2) {
        EventPoster eventPoster = this.mPoster;
        eventPoster.sendMessageDelayed(Message.obtain(eventPoster, eventMessage.what, eventMessage), j2);
    }

    private void unregisterAll(Context context) {
        if (context != null) {
            synchronized (this.mObservers) {
                try {
                    Iterator<ContentObserver> it = this.mObservers.iterator();
                    while (it.hasNext()) {
                        context.getContentResolver().unregisterContentObserver(it.next());
                    }
                    this.mObservers.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("data://app_context", new C0638s(this, 0)));
        arrayList.add(new SubscriberInfo("data://pppCompleted", new C0638s(this, 1)));
        arrayList.add(new SubscriberInfo("data://DataStamp", new C0638s(this, 2)));
        arrayList.add(new SubscriberInfo("lifecycle://on_thumbnail_load_done", new C0642w(this, 0)));
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_pause", new C0642w(this, 1)));
        arrayList.add(new SubscriberInfo("command://CollectExternalDataChangeEvent", new C0638s(this, 3)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ForceNotifyChangeFromEditor", new C0638s(this, 4)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_resume", new C0638s(this, 5)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ForceRefreshOnResume", new C0638s(this, 6)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command:///RefreshWithoutDelay", new C0638s(this, 7)).setWorkingOnUI());
    }

    public void deferChangeEvent(int i2, EventMessage eventMessage) {
        Log.i("DataChangeEventPublisher", "deferChangeEvent {" + i2 + "}");
        this.mDeferredMap.computeIfAbsent(Integer.valueOf(i2), new C0632l(2)).add(eventMessage);
    }

    public void onDestroy() {
        unregisterAll(this.mAppContext);
        this.mAppContext = null;
        super.onDestroy();
        releaseInitChecker();
        this.mPoster.getLooper().quitSafely();
    }

    public void processDeferredChangeEvent() {
        if (!this.mReady.getAndSet(true)) {
            Log.i("DataChangeEventPublisher", "processDeferredChangeEvent {" + this.mDeferredMap.size() + "}");
            if (this.mDeferredMap.size() > 0) {
                this.mDeferredMap.forEach(new C0640u(0, this));
                this.mDeferredMap.clear();
            }
        }
    }

    public void processOnChange(int i2, boolean z, Uri uri) {
        EventMessage obtain = EventMessage.obtain(i2, z ? 1 : 0, 0, uri);
        if (!this.mReady.get()) {
            deferChangeEvent(i2, obtain);
        } else {
            processOnChange(i2, obtain);
        }
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("State{");
        sb2.append(this.mInitialized.get());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mReady.get()) {
            str = "ready";
        } else {
            str = "unready";
        }
        return C0212a.p(sb2, str, "}");
    }

    public void processOnChange(int i2, EventMessage eventMessage) {
        Boolean bool;
        if (this.mCollect) {
            Log.d("DataChangeEventPublisher", "processOnChange skip by collecting state");
            this.mEventCollectList.putIfAbsent(Integer.valueOf(i2), eventMessage);
            return;
        }
        long j2 = (long) this.mDelayManager.get();
        if (this.mPoster.hasMessages(i2)) {
            this.mPoster.removeMessages(i2);
            this.mDelayManager.update(false);
        } else {
            this.mDelayManager.update(true);
        }
        if (!SdkConfig.atLeast(SdkConfig.GED.Q) || (bool = (Boolean) this.mBlackboard.pop("viewer_force_refresh")) == null || !bool.booleanValue()) {
            sendMessageToPoster(eventMessage, j2);
            return;
        }
        Log.d("DataChangeEventPublisher", "processOnChange > force refresh viewer");
        sendMessageToPoster(eventMessage, 0);
    }

    /* access modifiers changed from: private */
    public void onPppCompleted(Object obj, Bundle bundle) {
    }
}
