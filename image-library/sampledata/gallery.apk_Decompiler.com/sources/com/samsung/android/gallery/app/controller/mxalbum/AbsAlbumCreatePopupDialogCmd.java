package com.samsung.android.gallery.app.controller.mxalbum;

import M9.o;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CreateAlbumCmd;
import com.samsung.android.gallery.app.controller.album.CreateAutoUpdatingAlbumCmd;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import com.samsung.android.gallery.app.controller.sharing.CreateSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestSetupWizardType;
import com.samsung.android.gallery.app.controller.sharing.StartSharingServiceSetupWizardCmd;
import com.samsung.android.gallery.module.album.AlbumCreatePopupType;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeAuthHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsAlbumCreatePopupDialogCmd extends BaseCommand {
    private AlbumCreatePopupType mCreatePopupType;

    /* renamed from: com.samsung.android.gallery.app.controller.mxalbum.AbsAlbumCreatePopupDialogCmd$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$album$AlbumCreatePopupType;

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
                com.samsung.android.gallery.module.album.AlbumCreatePopupType[] r0 = com.samsung.android.gallery.module.album.AlbumCreatePopupType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$album$AlbumCreatePopupType = r0
                com.samsung.android.gallery.module.album.AlbumCreatePopupType r1 = com.samsung.android.gallery.module.album.AlbumCreatePopupType.ALBUM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$album$AlbumCreatePopupType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.album.AlbumCreatePopupType r1 = com.samsung.android.gallery.module.album.AlbumCreatePopupType.GROUP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$album$AlbumCreatePopupType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.album.AlbumCreatePopupType r1 = com.samsung.android.gallery.module.album.AlbumCreatePopupType.SHARED_ALBUM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$album$AlbumCreatePopupType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.album.AlbumCreatePopupType r1 = com.samsung.android.gallery.module.album.AlbumCreatePopupType.AUTO_UPDATING_ALBUM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$album$AlbumCreatePopupType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.module.album.AlbumCreatePopupType r1 = com.samsung.android.gallery.module.album.AlbumCreatePopupType.FAMILY_SHARED_ALBUM     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.mxalbum.AbsAlbumCreatePopupDialogCmd.AnonymousClass2.<clinit>():void");
        }
    }

    private void createAlbum() {
        new CreateAlbumCmd().execute(getHandler(), 0, null, Boolean.FALSE);
    }

    private void createAutoUpdatingAlbum() {
        new CreateAutoUpdatingAlbumCmd().execute(getHandler(), 0, null);
    }

    private void createFamilySharedAlbum() {
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_VIEW_CREATE_SAHRED_FAMILY_ALBUM);
        getBlackboard().post("command://MoveURL", MdeAlbumHelper.buildFamilyAlbumWelcomePageLocation(false, isFromAlbumChoice()));
    }

    private void createFolder() {
        new CreateFolderCmd().execute(getHandler(), null, CreateFolderCmd.Type.CREATE_EMPTY, Boolean.TRUE, null);
    }

    /* access modifiers changed from: private */
    public void createPopupByType() {
        int i2 = AnonymousClass2.$SwitchMap$com$samsung$android$gallery$module$album$AlbumCreatePopupType[this.mCreatePopupType.ordinal()];
        if (i2 == 1) {
            createAlbum();
        } else if (i2 == 2) {
            createFolder();
        } else if (i2 == 3) {
            createSharedAlbum();
        } else if (i2 == 4) {
            createAutoUpdatingAlbum();
        } else if (i2 == 5) {
            createFamilySharedAlbum();
        }
    }

    public final void createSharedAlbum() {
        MdeSharingService.getInstance().connectSessionAsync(2, new ConnectListener() {
            public void onFailure(int i2) {
                if (i2 == 3 || !MdeAuthHelper.isReadyToUseShareService()) {
                    new StartSharingServiceSetupWizardCmd().execute(AbsAlbumCreatePopupDialogCmd.this.getHandler(), RequestSetupWizardType.SETUP_SHARING_SERVICE);
                }
            }

            public void onSuccess() {
                new CreateSharedAlbumCmd().execute(AbsAlbumCreatePopupDialogCmd.this.getHandler(), new Object[0]);
            }
        });
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_CREATE_TO.toString();
    }

    public boolean isFromAlbumChoice() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Log.d(this.TAG, "create album", eventContext.getLocationKey());
    }

    public final void onItemSelected(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null || arrayList.isEmpty() || !(arrayList.get(0) instanceof AlbumCreatePopupType)) {
            Log.e(this.TAG, "invalid data is selected");
            return;
        }
        this.mCreatePopupType = (AlbumCreatePopupType) arrayList.get(0);
        ThreadUtil.postOnUiThread(new o(24, this));
    }
}
