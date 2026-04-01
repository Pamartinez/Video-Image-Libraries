package com.samsung.android.gallery.app.ui.list.stories.highlight;

import B8.f;
import a6.C0418a;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import bc.d;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.story.HighlightEncodeCmd;
import com.samsung.android.gallery.app.controller.story.HighlightExportOptionsDialogCmd;
import com.samsung.android.gallery.app.controller.story.StartHighlightPlayerEditCmd;
import com.samsung.android.gallery.app.controller.story.StartHighlightSaveCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.utils.CustomRatioHelper;
import com.samsung.android.gallery.database.dal.local.table.HiddenShareHelper;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.story.ExportOptions;
import com.samsung.android.gallery.module.story.ExportType;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.transcode.util.CommonUtil;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sum.core.functional.g;
import com.samsung.scsp.framework.core.network.Network;
import com.sec.android.gallery3d.R;
import g6.C0457a;
import g6.b;
import i.C0212a;
import java.io.File;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExportHandler {
    protected static final boolean HIDDEN_SHARE;
    private static final boolean SUPPORT_STORY_HIGHLIGHT_SAVE;
    protected final String TAG = getClass().getSimpleName();
    protected final EventContext mEventContext;
    protected final EventHandler mEventHandler;
    private int mExportRatio;
    private MediaItem mExportedItem;
    private final AtomicBoolean mHiddenFileChecked = new AtomicBoolean();
    private boolean mIsPausedBySave;
    private MediaItem mPendingShareItem;
    private int mUniqueKey = -1;
    protected final IStoryHighlightView mView;

    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$story$ExportType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.module.story.ExportType[] r0 = com.samsung.android.gallery.module.story.ExportType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$story$ExportType = r0
                com.samsung.android.gallery.module.story.ExportType r1 = com.samsung.android.gallery.module.story.ExportType.ADD_TO_SHARED_ALBUM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$story$ExportType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.story.ExportType r1 = com.samsung.android.gallery.module.story.ExportType.SHARE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$story$ExportType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.story.ExportType r1 = com.samsung.android.gallery.module.story.ExportType.SHARE_SIMPLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$story$ExportType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.story.ExportType r1 = com.samsung.android.gallery.module.story.ExportType.SAVE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$story$ExportType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.module.story.ExportType r1 = com.samsung.android.gallery.module.story.ExportType.SAVE_SIMPLE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler.AnonymousClass1.<clinit>():void");
        }
    }

    static {
        boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSave);
        SUPPORT_STORY_HIGHLIGHT_SAVE = isEnabled;
        HIDDEN_SHARE = isEnabled;
    }

    public ExportHandler(IStoryHighlightView iStoryHighlightView, EventContext eventContext) {
        this.mView = iStoryHighlightView;
        this.mEventContext = eventContext;
        this.mEventHandler = iStoryHighlightView.getEventHandler();
    }

    private ExportOptions createExportOption(MediaItem mediaItem, int i2) {
        boolean z;
        ExportOptions storyId = new ExportOptions().setTitle(mediaItem.getTitle()).setSubTitle(MediaItemStory.getStoryTimeDuration(mediaItem)).setTheme(this.mEventHandler.getEffectTheme()).setFilterName(this.mEventHandler.getFilter().getRawName()).setBgmExtraInfo(this.mEventHandler.getBgmExtraInfo()).setStoryId(mediaItem.getAlbumID());
        if (i2 > 0) {
            storyId.setRequestCode(i2);
            if (SUPPORT_STORY_HIGHLIGHT_SAVE) {
                if (!HIDDEN_SHARE || isSave(i2)) {
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    storyId.setUniqueKey(configureUniqueKey());
                }
                storyId.setOutPath(getSavePath(z));
                storyId.setSize(getExportSize(this.mView.getContext()));
                storyId.setKenBurnsMap(this.mEventHandler.requestData(DataRequest.VIEW_PAGER_ENCODING_INFO)).setPlayTimeMs(((Integer) this.mEventHandler.requestData(DataRequest.TOTAL_PLAY_TIME, Integer.valueOf(Network.DEFAULT_TIMEOUT))).intValue()).setRatio(this.mExportRatio);
            }
        }
        return storyId;
    }

    private void deleteHiddenShareItem(String str) {
        if (HIDDEN_SHARE && !TextUtils.isEmpty(str)) {
            SecureFile secureFile = new SecureFile(str);
            if (FileUtils.delete((File) secureFile) || !secureFile.exists()) {
                HiddenShareHelper.delete(str);
            }
        }
    }

    private void executeExport(ExportType exportType, Consumer<Boolean> consumer) {
        MediaItem headerItem = this.mView.getHeaderItem();
        if (headerItem != null) {
            ExportOptions createExportOption = createExportOption(headerItem, exportType.getRequestCode());
            if (SUPPORT_STORY_HIGHLIGHT_SAVE) {
                consumer.accept(Boolean.TRUE);
                new HighlightEncodeCmd().execute(this.mEventContext, createExportOption);
            } else if (Features.isEnabled(Features.SUPPORT_STORY_HIGHLIGHT_SAVE_VIA_VE)) {
                consumer.accept(Boolean.TRUE);
                new StartHighlightSaveCmd().execute(this.mEventContext, Integer.valueOf(headerItem.getAlbumID()), createExportOption, this.mEventHandler.requestData(DataRequest.REQ_BGM_URI_SERVICE));
            } else {
                Log.e(this.TAG, "not support export");
            }
        } else {
            Log.e(this.TAG, "handleExport, no header");
        }
    }

    private void executeShare(MediaItem mediaItem) {
        if (this.mView.isViewResumed()) {
            new ShareViaCmd().execute(this.mEventContext, new MediaItem[]{mediaItem}, null);
            stopShareService(this.mEventContext.getContext());
            return;
        }
        this.mPendingShareItem = mediaItem;
    }

    private void findExportedItem(String str, int i2, Consumer<MediaItem> consumer) {
        SimpleThreadPool.getInstance().execute(new C0457a(this, str, (Consumer) consumer, i2));
    }

    private String getItemUniqueKey(MediaItem mediaItem) {
        if (mediaItem == null) {
            return "";
        }
        return mediaItem.getPath() + "/" + mediaItem.getDateModified();
    }

    private String getPathFromUriItem(MediaItem mediaItem) {
        String nameFromPath = FileUtils.getNameFromPath(mediaItem.getPath());
        File externalFilesDir = AppResources.getAppContext().getExternalFilesDir(".share");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(externalFilesDir.getAbsolutePath());
        return C0212a.p(sb2, File.separator, nameFromPath);
    }

    private String getThemeKey() {
        EventHandler eventHandler = this.mView.getEventHandler();
        String str = "";
        StringBuilder s = C0212a.s(str + eventHandler.getEffectTheme());
        s.append(eventHandler.getFilter());
        String sb2 = s.toString();
        BgmExtraInfo bgmExtraInfo = eventHandler.getBgmExtraInfo();
        StringBuilder s5 = C0212a.s(sb2);
        if (bgmExtraInfo != null) {
            str = bgmExtraInfo.bgmName;
        }
        s5.append(str);
        return s5.toString();
    }

    private void handleExport(ExportType exportType) {
        int i2;
        MediaItem headerItem = this.mView.getHeaderItem();
        if (headerItem != null) {
            i2 = headerItem.getAlbumID();
        } else {
            i2 = -1;
        }
        SimpleThreadPool.getInstance().execute(new C0418a((Object) this, i2, (Object) exportType, 6));
        postAnalyticsLog(exportType);
    }

    private void handleExportDone(Object obj) {
        Object[] objArr = (Object[]) obj;
        if (objArr == null || objArr.length < 3) {
            Log.w(this.TAG, "handleSaveDone, no extra(failed)");
        } else {
            Boolean bool = (Boolean) objArr[0];
            boolean booleanValue = bool.booleanValue();
            String str = (String) objArr[1];
            ExportType type = ExportType.getType(((Integer) objArr[2]).intValue());
            if (booleanValue) {
                handleExportDoneInternal(str, type, 7);
            }
            Log.d(this.TAG, "handleSaveDone", bool, type);
        }
        if (this.mIsPausedBySave) {
            this.mEventHandler.lambda$postEvent$0(Event.PLAYER_KEEP_PAUSE, Boolean.FALSE);
            this.mIsPausedBySave = false;
        }
    }

    private void handleExportDoneInternal(String str, ExportType exportType, int i2) {
        if (TextUtils.isEmpty(str) || !FileUtils.exists(str)) {
            Log.w((CharSequence) this.TAG, "handleExportDoneInternal failed", Boolean.valueOf(FileUtils.exists(str)), Logger.getEncodedString(str));
        } else if (exportType.isSave() || !HIDDEN_SHARE) {
            findExportedItem(str, i2, new e(18, this, exportType));
        } else {
            executeExportDone(UriItemLoader.createUriItem(AppResources.getAppContext(), new SecureFile(str)), exportType, true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeExportDone$4(MediaItem mediaItem) {
        lambda$showSavedInToast$13(mediaItem, R.string.video_saved_in);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeExportDone$5(MediaItem mediaItem) {
        Optional.ofNullable(mediaItem).ifPresent(new b(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$findExportedItem$7(String str, int i2, Consumer consumer) {
        findExportedItem(str, i2 - 1, consumer);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005d A[SYNTHETIC, Splitter:B:17:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$findExportedItem$8(java.lang.String r6, java.util.function.Consumer r7, int r8) {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 != 0) goto L_0x0071
            boolean r0 = com.samsung.android.gallery.support.utils.FileUtils.exists(r6)
            if (r0 != 0) goto L_0x000d
            goto L_0x0071
        L_0x000d:
            java.lang.String r0 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES     // Catch:{ Exception -> 0x006c }
            A4.B r1 = new A4.B     // Catch:{ Exception -> 0x006c }
            r2 = 25
            r1.<init>(r6, r2)     // Catch:{ Exception -> 0x006c }
            android.database.Cursor r0 = com.samsung.android.gallery.database.dal.DbCompat.query(r0, r1)     // Catch:{ Exception -> 0x006c }
            if (r0 == 0) goto L_0x002c
            boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x002c
            com.samsung.android.gallery.module.data.MediaItem r5 = com.samsung.android.gallery.module.data.MediaItemBuilder.create((android.database.Cursor) r0)     // Catch:{ all -> 0x002a }
            r7.accept(r5)     // Catch:{ all -> 0x002a }
            goto L_0x005b
        L_0x002a:
            r5 = move-exception
            goto L_0x0061
        L_0x002c:
            if (r8 <= 0) goto L_0x004c
            java.lang.String r1 = r5.TAG     // Catch:{ all -> 0x002a }
            java.lang.String r2 = "find item, retry"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x002a }
            java.lang.String r4 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r6)     // Catch:{ all -> 0x002a }
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}     // Catch:{ all -> 0x002a }
            com.samsung.android.gallery.support.utils.Log.d(r1, r2, r3)     // Catch:{ all -> 0x002a }
            g6.a r1 = new g6.a     // Catch:{ all -> 0x002a }
            r1.<init>((com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler) r5, (java.lang.String) r6, (int) r8, (java.util.function.Consumer) r7)     // Catch:{ all -> 0x002a }
            r5 = 500(0x1f4, double:2.47E-321)
            com.samsung.android.gallery.support.utils.ThreadUtil.postOnBgThreadDelayed(r1, r5)     // Catch:{ all -> 0x002a }
            goto L_0x005b
        L_0x004c:
            java.lang.String r5 = r5.TAG     // Catch:{ all -> 0x002a }
            java.lang.String r7 = "fail to find item"
            java.lang.String r6 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r6)     // Catch:{ all -> 0x002a }
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch:{ all -> 0x002a }
            com.samsung.android.gallery.support.utils.Log.d(r5, r7, r6)     // Catch:{ all -> 0x002a }
        L_0x005b:
            if (r0 == 0) goto L_0x0060
            r0.close()     // Catch:{ Exception -> 0x006c }
        L_0x0060:
            return
        L_0x0061:
            if (r0 == 0) goto L_0x006b
            r0.close()     // Catch:{ all -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch:{ Exception -> 0x006c }
        L_0x006b:
            throw r5     // Catch:{ Exception -> 0x006c }
        L_0x006c:
            r5 = move-exception
            r5.printStackTrace()
            return
        L_0x0071:
            java.lang.String r5 = r5.TAG
            boolean r7 = com.samsung.android.gallery.support.utils.FileUtils.exists(r6)
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            java.lang.String r6 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r6)
            java.lang.Object[] r6 = new java.lang.Object[]{r7, r6}
            java.lang.String r7 = "findExportedItem failed"
            com.samsung.android.gallery.support.utils.Log.w((java.lang.CharSequence) r5, (java.lang.String) r7, (java.lang.Object[]) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler.lambda$findExportedItem$8(java.lang.String, java.util.function.Consumer, int):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleExport$0(Boolean bool) {
        pauseHighlightForSave();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleExport$1(MediaItem mediaItem, ExportType exportType) {
        if (mediaItem != null) {
            executeExportDone(mediaItem, exportType, false);
            Log.d(this.TAG, "handleExport, item exist", exportType);
            return;
        }
        executeExport(exportType, new b(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleExport$2(int i2, ExportType exportType) {
        MediaItem loadExportedItem = loadExportedItem(i2);
        setExportedItem(loadExportedItem);
        ThreadUtil.postOnUiThread(new d((Object) this, (Object) loadExportedItem, (Object) exportType, 13));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleExportDoneInternal$3(ExportType exportType, MediaItem mediaItem) {
        executeExportDone(mediaItem, exportType, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadHiddenMediaItem$9(String str, File file) {
        deleteHiddenShareItem(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToStorage$10(Consumer consumer, TimeTickLog timeTickLog, MediaItem mediaItem) {
        setExportedItem(mediaItem);
        if (consumer != null) {
            consumer.accept(mediaItem);
        }
        String str = this.TAG;
        Log.v(str, "moveToStorage done : " + MediaItemUtil.getSimpleLog(mediaItem) + Logger.vt(timeTickLog));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToStorage$11(TimeTickLog timeTickLog, String str, Consumer consumer) {
        timeTickLog.tick();
        findExportedItem(str, 7, new g(this, consumer, timeTickLog, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToStorage$12(MediaItem mediaItem, Consumer consumer) {
        String str;
        if (mediaItem != null) {
            try {
                str = getPathFromUriItem(mediaItem);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else {
            str = null;
        }
        if (str == null || !FileUtils.exists(str)) {
            Log.e((CharSequence) this.TAG, "moveToStorage failed due to invalid path", Logger.getEncodedString(str));
            return;
        }
        TimeTickLog timeTickLog = new TimeTickLog();
        CommonUtil.updateCreationTime(str);
        timeTickLog.tick();
        String createSaveFilePath = StoryHelper.createSaveFilePath(StorageInfo.getDefault().stories);
        if (FileUtils.move(str, createSaveFilePath)) {
            HiddenShareHelper.delete(str);
            timeTickLog.tick();
            MediaScanner.scanFile(createSaveFilePath, new f((Object) this, (Object) timeTickLog, (Object) createSaveFilePath, (Object) consumer, 6));
            return;
        }
        Log.e((CharSequence) this.TAG, "moveToStorage failed", Logger.getEncodedString(str));
    }

    private MediaItem loadHiddenMediaItem(int i2, int i7) {
        String[] strArr;
        SecureFile secureFile;
        Cursor hiddenShareCursor = getHiddenShareCursor();
        if (hiddenShareCursor != null) {
            try {
                if (hiddenShareCursor.moveToFirst()) {
                    do {
                        String string = hiddenShareCursor.getString(0);
                        String string2 = hiddenShareCursor.getString(1);
                        if (!TextUtils.isEmpty(string2)) {
                            strArr = string2.split(";");
                        } else {
                            strArr = null;
                        }
                        if (strArr != null && strArr.length > 1 && String.valueOf(i2).equals(strArr[0])) {
                            if (!TextUtils.isEmpty(string)) {
                                secureFile = new SecureFile(string);
                            } else {
                                secureFile = null;
                            }
                            if (!String.valueOf(i7).equals(strArr[1]) || secureFile == null || !secureFile.exists()) {
                                Optional.ofNullable(secureFile).ifPresent(new e(19, this, string));
                            } else {
                                MediaItem createUriItem = UriItemLoader.createUriItem(AppResources.getAppContext(), secureFile);
                                hiddenShareCursor.close();
                                return createUriItem;
                            }
                        }
                    } while (hiddenShareCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (hiddenShareCursor != null) {
            hiddenShareCursor.close();
        }
        return null;
        throw th;
    }

    private void moveToStorage(MediaItem mediaItem, Consumer<MediaItem> consumer) {
        SimpleThreadPool.getInstance().execute(new d((Object) this, (Object) mediaItem, (Object) consumer, 14));
    }

    private void postAnalyticsLog(ExportType exportType) {
        AnalyticsEventId analyticsEventId;
        if (SUPPORT_STORY_HIGHLIGHT_SAVE) {
            if (exportType.isSave()) {
                analyticsEventId = AnalyticsEventId.EVENT_STORY_HIGHLIGHT_SAVE;
            } else if (exportType.isShare()) {
                analyticsEventId = AnalyticsEventId.EVENT_STORY_HIGHLIGHT_SHARE_AS_VIDEO;
            } else if (exportType.isAddToSharedAlbum()) {
                analyticsEventId = AnalyticsEventId.EVENT_STORY_HIGHLIGHT_ADD_TO_SHARED_ALBUM;
            } else {
                analyticsEventId = null;
            }
            if (analyticsEventId != null) {
                IStoryHighlightView iStoryHighlightView = this.mView;
                iStoryHighlightView.postAnalyticsLog(analyticsEventId, String.valueOf(MediaItemStory.getStorySaType(iStoryHighlightView.getHeaderItem())));
                return;
            }
            Log.d(this.TAG, "ignore logging ExportType=", exportType);
        }
    }

    private boolean requireCheckHiddenFile() {
        if (!HIDDEN_SHARE || this.mHiddenFileChecked.getAndSet(true)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: showSavedInToast */
    public void lambda$showSavedInToast$13(MediaItem mediaItem, int i2) {
        if (ThreadUtil.isMainThread()) {
            Utils.showToast(AppResources.getAppContext(), AppResources.getString(i2, BucketUtils.translatePath(mediaItem.getPath(), false)));
            return;
        }
        ThreadUtil.postOnUiThread(new C0418a((Object) this, (Object) mediaItem, i2, 7));
    }

    private void startHighlightReelEdit() {
        MediaItem headerItem = this.mView.getHeaderItem();
        if (headerItem != null) {
            new StartHighlightPlayerEditCmd().execute(this.mEventContext, Integer.valueOf(headerItem.getAlbumID()), createExportOption(headerItem, -1), this.mEventHandler.requestData(DataRequest.REQ_BGM_URI_SERVICE));
        }
    }

    private void stopShareService(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.HighlightEncodeService");
            intent.setAction("com.samsung.android.gallery.app.service.DELETE_SERVICE");
            context.startService(intent);
        }
    }

    public final int configureUniqueKey() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        MediaItem headerItem = this.mEventContext.getHeaderItem();
        if (headerItem != null) {
            str = headerItem.getTitle();
        } else {
            str = "";
        }
        sb2.append(str);
        sb2.append(getThemeKey());
        sb2.append(getItemUniqueKey(headerItem));
        sb2.append(this.mExportRatio);
        Iterator<MediaItem> it = this.mView.getExportItems().iterator();
        while (it.hasNext()) {
            sb2.append(getItemUniqueKey(it.next()));
        }
        return sb2.toString().hashCode();
    }

    public void executeExportDone(MediaItem mediaItem, ExportType exportType, boolean z) {
        Log.d(this.TAG, "executeExportDone", exportType, Boolean.valueOf(z));
        if (z) {
            setExportedItem(mediaItem);
        }
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$story$ExportType[exportType.ordinal()];
        if (i2 == 1) {
            new ChooseSharedAlbumCmd().execute(this.mView.getEventContext(), new MediaItem[]{mediaItem});
        } else if (i2 == 2 || i2 == 3) {
            executeShare(mediaItem);
        } else if ((i2 != 4 && i2 != 5) || z) {
        } else {
            if (!HIDDEN_SHARE || !mediaItem.isUriItem()) {
                lambda$showSavedInToast$13(mediaItem, R.string.video_already_saved_in);
            } else {
                moveToStorage(mediaItem, new b(this, 1));
            }
        }
    }

    public final int[] getExportSize(Context context) {
        if (context != null) {
            return CustomRatioHelper.getOutSize(context, this.mExportRatio, context.getResources().getConfiguration().orientation);
        }
        return new int[]{1080, 1980};
    }

    public Cursor getHiddenShareCursor() {
        return HiddenShareHelper.queryToShareTable(new String[]{"__absPath", "meta_data"}, "owner=?", new String[]{"storyHighlight"}, "date_added ASC");
    }

    public String getSavePath(boolean z) {
        File externalFilesDir;
        if (!z || (externalFilesDir = AppResources.getAppContext().getExternalFilesDir(".share")) == null) {
            return StoryHelper.createSaveFilePath(StorageInfo.getDefault().stories);
        }
        return StoryHelper.createSaveFilePath(externalFilesDir.getAbsolutePath());
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1091) {
            handleExport((ExportType) eventMessage.obj);
            return true;
        } else if (i2 == 1092) {
            handleExportDone(eventMessage.obj);
            return true;
        } else if (i2 == 1094) {
            startHighlightReelEdit();
            return true;
        } else if (i2 == 1122) {
            new HighlightExportOptionsDialogCmd().execute(this.mView.getEventContext(), Integer.valueOf(this.mExportRatio));
            return true;
        } else if (i2 != 1123) {
            return false;
        } else {
            this.mExportRatio = ((Integer) eventMessage.obj).intValue();
            return true;
        }
    }

    public boolean isSave(int i2) {
        if (i2 == 1796) {
            return true;
        }
        return false;
    }

    public final MediaItem loadExportedItem(int i2) {
        String str;
        MediaItem mediaItem = this.mExportedItem;
        int i7 = this.mUniqueKey;
        if (mediaItem != null) {
            if (mediaItem.isUriItem()) {
                str = getPathFromUriItem(mediaItem);
            } else {
                str = mediaItem.getPath();
            }
            if (i7 == configureUniqueKey() && FileUtils.exists(str)) {
                return mediaItem;
            }
            if (mediaItem.isUriItem()) {
                deleteHiddenShareItem(str);
            }
        }
        if (requireCheckHiddenFile()) {
            return loadHiddenMediaItem(i2, configureUniqueKey());
        }
        return null;
    }

    public final void pauseHighlightForSave() {
        boolean booleanValue = ((Boolean) this.mEventHandler.requestData(DataRequest.IS_VIEW_PAGER_PLAYING, Boolean.FALSE)).booleanValue();
        this.mIsPausedBySave = booleanValue;
        if (booleanValue) {
            this.mEventHandler.lambda$postEvent$0(Event.PLAYER_KEEP_PAUSE, Boolean.TRUE);
        }
        this.mEventHandler.lambda$postEvent$0(Event.STOP_VIDEO_PREVIEW, new Object[0]);
    }

    public void reset() {
        this.mExportedItem = null;
        this.mHiddenFileChecked.set(false);
    }

    public void resetPendingShare() {
        this.mPendingShareItem = null;
    }

    public void resume() {
        String str;
        String str2 = this.TAG;
        if (this.mPendingShareItem != null) {
            str = "S";
        } else {
            str = "s";
        }
        Log.d(str2, "onResume ", str);
        MediaItem mediaItem = this.mPendingShareItem;
        if (mediaItem != null) {
            executeShare(mediaItem);
            this.mPendingShareItem = null;
        }
    }

    public final void setExportedItem(MediaItem mediaItem) {
        int i2;
        this.mExportedItem = mediaItem;
        if (mediaItem != null) {
            i2 = configureUniqueKey();
        } else {
            i2 = -1;
        }
        this.mUniqueKey = i2;
    }
}
