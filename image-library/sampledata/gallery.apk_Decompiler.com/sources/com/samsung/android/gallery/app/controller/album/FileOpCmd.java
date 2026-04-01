package com.samsung.android.gallery.app.controller.album;

import A4.A;
import A4.C0371f;
import A4.H;
import Gb.a;
import H3.g;
import H3.h;
import H3.i;
import H3.j;
import H3.k;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileOpCmd extends BaseCommand {
    private FileOpCmdHelper.CmdType mCmdType;
    private String mOperationType;
    private MediaItem[] mSelectedItems;

    /* renamed from: com.samsung.android.gallery.app.controller.album.FileOpCmd$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$app$controller$album$FileOpCmdHelper$CmdType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.app.controller.album.FileOpCmdHelper$CmdType[] r0 = com.samsung.android.gallery.app.controller.album.FileOpCmdHelper.CmdType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$app$controller$album$FileOpCmdHelper$CmdType = r0
                com.samsung.android.gallery.app.controller.album.FileOpCmdHelper$CmdType r1 = com.samsung.android.gallery.app.controller.album.FileOpCmdHelper.CmdType.TYPE_CHOICE_ALBUM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$album$FileOpCmdHelper$CmdType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.app.controller.album.FileOpCmdHelper$CmdType r1 = com.samsung.android.gallery.app.controller.album.FileOpCmdHelper.CmdType.TYPE_ADD_TO_ITEMS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$album$FileOpCmdHelper$CmdType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.app.controller.album.FileOpCmdHelper$CmdType r1 = com.samsung.android.gallery.app.controller.album.FileOpCmdHelper.CmdType.TYPE_DRAG_TO_ITEMS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$album$FileOpCmdHelper$CmdType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.app.controller.album.FileOpCmdHelper$CmdType r1 = com.samsung.android.gallery.app.controller.album.FileOpCmdHelper.CmdType.TYPE_RENAME_ALBUM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.album.FileOpCmd.AnonymousClass1.<clinit>():void");
        }
    }

    private void addItemsToAutoAlbum(MediaItem mediaItem) {
        SimpleThreadPool.getInstance().execute(new g(this, mediaItem, 1));
    }

    private void enableModeBarMode() {
        if (PreferenceFeatures.OneUi40.ALBUM_CHOICE_MOVE_BAR && LocationKey.isAlbumPictures(getHandler().getLocationKey())) {
            if ("copy".equals(this.mOperationType) || "move".equals(this.mOperationType)) {
                getBlackboard().publish("data://album_move", this.mSelectedItems);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addItemsToAutoAlbum$11(MediaItem mediaItem) {
        AutoAlbumHelper.getInstance().addAutoAlbumContents((String) Arrays.stream(this.mSelectedItems).map(new a(6)).distinct().collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), (long) mediaItem.getAlbumID());
        getBlackboard().postBroadcastEvent(EventMessage.obtain(101));
        if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
            getBlackboard().postBroadcastEvent(EventMessage.obtain(104));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$0(MediaItem[] mediaItemArr, long j2, EventContext eventContext, Object[] objArr, Integer num) {
        String str = this.TAG;
        A.a.A(new Object[]{Integer.valueOf(mediaItemArr.length), num, Long.valueOf(j2)}, new StringBuilder("onPreExecute#PppChecker"), str);
        Object obj = objArr[0];
        if (num.intValue() > 0) {
            mediaItemArr = replacePppItem(eventContext, mediaItemArr);
        }
        super.onPreExecute(eventContext, obj, mediaItemArr, objArr[1]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$1(EventContext eventContext, Object[] objArr, MediaItem[] mediaItemArr) {
        if (GppmHelper.SUPPORT_DONATION_MULTIPLE) {
            EventContext eventContext2 = eventContext;
            MediaItem[] mediaItemArr2 = mediaItemArr;
            executePppChecker(eventContext2, mediaItemArr2, new j(this, mediaItemArr2, System.currentTimeMillis(), eventContext2, objArr, 0));
            return;
        }
        Object[] objArr2 = objArr;
        super.onPreExecute(eventContext, objArr2[0], mediaItemArr, objArr2[1]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startAddToAlbumDialogCmd$4(MediaItem mediaItem, ArrayList arrayList) {
        if (arrayList.get(0) instanceof MediaItem[]) {
            this.mSelectedItems = (MediaItem[]) arrayList.get(0);
            addItemsToAutoAlbum(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startAddToAlbumDialogCmd$5(MediaItem mediaItem, EventContext eventContext, ArrayList arrayList) {
        Optional.ofNullable(arrayList).ifPresent(new A(18, (Object) this, (Object) mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startAddToAlbumDialogCmd$6(String str, ArrayList arrayList) {
        String str2;
        if ((arrayList.get(0) instanceof MediaItem[]) && (arrayList.get(1) instanceof Integer)) {
            this.mSelectedItems = (MediaItem[]) arrayList.get(0);
            if (((Integer) arrayList.get(1)).intValue() == 2) {
                str2 = "copy";
            } else {
                str2 = "move";
            }
            this.mOperationType = str2;
            FileOpCmdHelper.getInstance().startService(getHandler(), this.mSelectedItems, str, this.mOperationType);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startAddToAlbumDialogCmd$7(String str, EventContext eventContext, ArrayList arrayList) {
        Optional.ofNullable(arrayList).ifPresent(new A(17, (Object) this, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startAddToAlbumDialogCmd$8(MediaItem mediaItem, String str) {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !mediaItem.isAutoAlbum()) {
            FileOpCmdHelper.getInstance().startAddToAlbumDialogCmd(getHandler(), new h(this, str, 0));
        } else {
            FileOpCmdHelper.getInstance().startAddToAutoAlbumDialogCmd(getHandler(), new H(12, (Object) this, (Object) mediaItem));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startAddToAlbumDialogCmd$9(MediaItem mediaItem) {
        ThreadUtil.postOnUiThread(new k(this, mediaItem, AlbumHelper.getInstance().getValidPath(mediaItem), 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startChoiceAlbumCmd$2(ArrayList arrayList) {
        String str = (String) ((Object[]) arrayList.get(0))[1];
        boolean booleanValue = ((Boolean) ((Object[]) arrayList.get(0))[2]).booleanValue();
        int intValue = ((Integer) getBlackboard().read("album_copy_move_tgt_folder", 0)).intValue();
        getBlackboard().erase("album_copy_move_tgt_folder");
        if (str == null) {
            getBlackboard().erase("data://user/selected");
            return;
        }
        if (booleanValue) {
            if (intValue != 0) {
                if (!AlbumHelper.getInstance().updateFolder(getContext(), str, intValue, 0)) {
                    Log.e(this.TAG, "Unable to add album to folder");
                }
            } else if (PreferenceFeatures.OneUi5x.MX_ALBUMS && AlbumHelper.getInstance().addNewEmptyAlbum(str, FileUtils.getNameFromPath(str), 0, (String) null) != 1) {
                Log.e(this.TAG, "unable to new empty album");
            }
        }
        FileOpCmdHelper.getInstance().startService(getHandler(), this.mSelectedItems, str, this.mOperationType, booleanValue);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startChoiceAlbumCmd$3(EventContext eventContext, ArrayList arrayList) {
        Optional.ofNullable(arrayList).ifPresent(new E9.a(21, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startDragToAlbumDialogCmd$12(String str, EventContext eventContext, ArrayList arrayList) {
        String str2;
        int i2 = 0;
        if (arrayList != null && !arrayList.isEmpty()) {
            i2 = ((Integer) arrayList.get(0)).intValue();
        }
        if (i2 == 2) {
            str2 = "copy";
        } else {
            str2 = "move";
        }
        this.mOperationType = str2;
        if (i2 == 1 || i2 == 2) {
            FileOpCmdHelper.getInstance().startService(getHandler(), this.mSelectedItems, str, this.mOperationType);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startDragToAlbumDialogCmd$13(String str) {
        if (getHandler() == null || getHandler().getContext() == null) {
            String str2 = this.TAG;
            Log.e(str2, "startDragToAlbumDialogCmd failed. Handler is " + getHandler());
        } else if (this.mOperationType == null) {
            FileOpCmdHelper.getInstance().startDragToAlbumDialogCmd(getHandler(), this.mSelectedItems, new h(this, str, 1));
        } else {
            FileOpCmdHelper.getInstance().startService(getHandler(), this.mSelectedItems, str, this.mOperationType);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startDragToAlbumDialogCmd$14(MediaItem mediaItem) {
        ThreadUtil.postOnUiThread(new H.a(3, this, AlbumHelper.getInstance().getValidPath(mediaItem)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startRenameAlbumCmd$15(MediaItem mediaItem, String str) {
        getBlackboard().postEvent(EventMessage.obtain(1003));
        AlbumHelper.getInstance().renameNewEmptyAlbum(getHandler().getContext(), mediaItem.getAlbumID(), str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startRenameAlbumCmd$16(MediaItem mediaItem, boolean z, EventContext eventContext, ArrayList arrayList) {
        if (arrayList != null) {
            Object[] objArr = (Object[]) arrayList.get(0);
            if (objArr[0] != null) {
                String str = ((String) objArr[1]) + objArr[0];
                if (mediaItem.isEmptyAlbum()) {
                    SimpleThreadPool.getInstance().execute(new k(this, mediaItem, str, 0));
                } else {
                    FileOpCmdHelper.getInstance().startService(getHandler(), new MediaItem[]{mediaItem}, str, "rename", false, mediaItem.getAlbumID(), z);
                }
            }
        }
    }

    private void startAddToAlbumDialogCmd(Object obj) {
        try {
            ThreadUtil.postOnBgThread(new g(this, (MediaItem) ((Object[]) obj)[1], 0));
        } catch (ClassCastException e) {
            String str = this.TAG;
            Log.e(str, "unexpected result delivered. e=" + e.getMessage());
        }
    }

    private void startChoiceAlbumCmd(Object obj) {
        Object[] objArr = (Object[]) obj;
        this.mSelectedItems = (MediaItem[]) objArr[1];
        this.mOperationType = (String) objArr[2];
        enableModeBarMode();
        FileOpCmdHelper.getInstance().startChoiceAlbumCmd(getHandler(), this.mOperationType, new Fb.h(6, this));
    }

    private void startDragToAlbumDialogCmd(Object obj) {
        Object[] objArr = (Object[]) obj;
        this.mSelectedItems = (MediaItem[]) objArr[1];
        this.mOperationType = (String) objArr[3];
        ThreadUtil.postOnBgThread(new g(this, (MediaItem) objArr[2], 2));
    }

    private void startRenameAlbumCmd(Object obj) {
        MediaItem mediaItem = (MediaItem) ((Object[]) obj)[1];
        FileOpCmdHelper.getInstance().startRenameAlbumCmd(getHandler(), mediaItem, getScreenId(), new i((Object) this, (Object) mediaItem, mediaItem.isEmptyAlbum()));
    }

    public Long getAnalyticsValue() {
        if (FileOpCmdHelper.CmdType.TYPE_CHOICE_ALBUM.equals(this.mCmdType)) {
            return Long.valueOf((long) this.mSelectedItems.length);
        }
        return super.getAnalyticsValue();
    }

    public String getEventId() {
        if (FileOpCmdHelper.CmdType.TYPE_CHOICE_ALBUM.equals(this.mCmdType)) {
            if ("copy".equals(this.mOperationType)) {
                return AnalyticsEventId.EVENT_MENU_COPY_TO_ALBUM.toString();
            }
            return AnalyticsEventId.EVENT_MENU_MOVE_TO_ALBUM.toString();
        } else if (FileOpCmdHelper.CmdType.TYPE_RENAME_ALBUM.equals(this.mCmdType)) {
            if (AnalyticsScreenId.SCREEN_SPLIT_VIEW_NORMAL.toString().equals(getScreenId())) {
                return AnalyticsEventId.EVENT_MENU_SPLIT_RENAME_ALBUM.toString();
            }
            return AnalyticsEventId.EVENT_MENU_ALBUM_RENAME.toString();
        } else if (FileOpCmdHelper.CmdType.TYPE_ADD_TO_ITEMS.equals(this.mCmdType)) {
            return AnalyticsEventId.EVENT_MENU_ADD_ITEMS.toString();
        } else {
            return super.getEventId();
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        FileOpCmdHelper.CmdType cmdType = objArr[0];
        this.mCmdType = cmdType;
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$controller$album$FileOpCmdHelper$CmdType[cmdType.ordinal()];
        if (i2 == 1) {
            startChoiceAlbumCmd(objArr);
        } else if (i2 == 2) {
            startAddToAlbumDialogCmd(objArr);
        } else if (i2 == 3) {
            startDragToAlbumDialogCmd(objArr);
        } else if (i2 == 4) {
            startRenameAlbumCmd(objArr);
        }
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        if (!FileOpCmdHelper.CmdType.TYPE_CHOICE_ALBUM.equals(objArr[0])) {
            super.onPreExecute(eventContext, objArr);
        } else if (eventContext.isSelectionMode() || LocationKey.isContentViewer(eventContext.getLocationKey())) {
            Log.d(this.TAG, "onPreExecute", Integer.valueOf(eventContext.getSelectedItemCount()));
            loadAndExecute(eventContext, new C0371f((Object) this, (Object) eventContext, (Object) objArr, 3));
        } else {
            super.onPreExecute(eventContext, objArr[0], eventContext.getSelectedItems(), objArr[1]);
        }
    }
}
