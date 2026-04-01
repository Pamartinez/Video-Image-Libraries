package com.samsung.android.gallery.app.controller.album;

import H3.c;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateAlbumCmd extends BaseCommand {
    protected int mFolderId;
    protected String mFolderName = null;
    private String mNewAlbumName;
    private String mNewAlbumPath;

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createNewEmptyAlbum(java.util.ArrayList<java.lang.Object> r7, com.samsung.android.gallery.app.controller.EventContext r8) {
        /*
            r6 = this;
            r8 = 1
            r0 = 0
            if (r7 == 0) goto L_0x0036
            r1 = 0
            java.lang.Object r2 = r7.get(r1)
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            r1 = r2[r1]
            java.lang.String r1 = (java.lang.String) r1
            r6.mNewAlbumName = r1
            r1 = r2[r8]
            java.lang.String r1 = (java.lang.String) r1
            r6.mNewAlbumPath = r1
            int r1 = r7.size()
            r2 = 2
            if (r1 <= r2) goto L_0x0040
            java.lang.Object r0 = r7.get(r8)
            com.samsung.android.gallery.module.data.MediaItem[] r0 = (com.samsung.android.gallery.module.data.MediaItem[]) r0
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            if (r7 != r2) goto L_0x0033
            java.lang.String r7 = "copy"
            goto L_0x0041
        L_0x0033:
            java.lang.String r7 = "move"
            goto L_0x0041
        L_0x0036:
            java.lang.String r7 = r6.mNewAlbumName
            if (r7 == 0) goto L_0x00b8
            java.lang.String r7 = r6.mNewAlbumPath
            if (r7 != 0) goto L_0x0040
            goto L_0x00b8
        L_0x0040:
            r7 = r0
        L_0x0041:
            com.samsung.android.gallery.module.album.AlbumHelper r1 = com.samsung.android.gallery.module.album.AlbumHelper.getInstance()
            java.lang.String r2 = r6.mNewAlbumPath
            java.lang.String r3 = r6.mNewAlbumName
            int r4 = r6.mFolderId
            java.lang.String r5 = r6.mFolderName
            int r1 = r1.addNewEmptyAlbum(r2, r3, r4, r5)
            if (r1 == r8) goto L_0x0077
            boolean r7 = com.samsung.android.gallery.support.utils.StorageUtil.isLowStorage()
            if (r7 == 0) goto L_0x0068
            android.content.Context r6 = r6.getContext()
            r7 = 2131887939(0x7f120743, float:1.94105E38)
            android.widget.Toast r6 = android.widget.Toast.makeText(r6, r7, r8)
            r6.show()
            return
        L_0x0068:
            android.content.Context r6 = r6.getContext()
            r7 = 2131888904(0x7f120b08, float:1.9412456E38)
            android.widget.Toast r6 = android.widget.Toast.makeText(r6, r7, r8)
            r6.show()
            return
        L_0x0077:
            java.lang.String r8 = r6.mNewAlbumName
            java.lang.String r1 = r6.mNewAlbumPath
            int r2 = r6.mFolderId
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r3 = r6.mFolderName
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r1, r2, r3}
            java.lang.String r1 = "data://usernew_item_creation"
            com.samsung.android.gallery.support.blackboard.Blackboard.publishGlobal(r1, r8)
            java.lang.String r8 = r6.mNewAlbumPath
            int r8 = com.samsung.android.gallery.support.utils.FileUtils.getBucketId(r8)
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            java.lang.String r2 = r6.mNewAlbumPath
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r2}
            r2 = 2004(0x7d4, float:2.808E-42)
            com.samsung.android.gallery.support.blackboard.key.EventMessage r8 = com.samsung.android.gallery.support.blackboard.key.EventMessage.obtain(r2, r8, r1)
            com.samsung.android.gallery.support.blackboard.Blackboard.postEventGlobal(r8)
            if (r0 == 0) goto L_0x00b7
            int r8 = r0.length
            if (r8 <= 0) goto L_0x00b7
            if (r7 == 0) goto L_0x00b7
            com.samsung.android.gallery.app.controller.album.FileOpCmdHelper r8 = com.samsung.android.gallery.app.controller.album.FileOpCmdHelper.getInstance()
            com.samsung.android.gallery.app.controller.EventContext r1 = r6.getHandler()
            java.lang.String r6 = r6.mNewAlbumPath
            r8.startService(r1, r0, r6, r7)
        L_0x00b7:
            return
        L_0x00b8:
            java.lang.String r6 = r6.TAG
            java.lang.String r7 = "createNewEmptyAlbum data is null"
            com.samsung.android.gallery.support.utils.Log.e(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.album.CreateAlbumCmd.createNewEmptyAlbum(java.util.ArrayList, com.samsung.android.gallery.app.controller.EventContext):void");
    }

    /* access modifiers changed from: private */
    public void handleUpdate(String str, Object obj) {
        if (!TextUtils.isEmpty(str) && ArgumentsUtil.removeArgs(str).equals("data://user/dialog/AlbumName")) {
            try {
                Object[] objArr = (Object[]) obj;
                if (objArr != null) {
                    this.mNewAlbumName = (String) objArr[0];
                    this.mNewAlbumPath = (String) objArr[1];
                } else if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                    getBlackboard().post("data://user/pick/Item", (Object) null);
                }
            } catch (ClassCastException e) {
                String str2 = this.TAG;
                Log.e(str2, "unexpected result delivered. e=" + e.getMessage());
            }
        }
    }

    public void createAlbum(EventContext eventContext, ArrayList<Object> arrayList) {
        Log.d(this.TAG, "create new empty album");
        createNewEmptyAlbum(arrayList, eventContext);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_CREATE_ALBUM.toString();
    }

    public String getTargetKey(EventContext eventContext) {
        return new UriBuilder("data://user/dialog/AlbumName").appendArg("screenId", eventContext.getScreenId()).build();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            this.mFolderId = objArr[0].intValue();
            this.mFolderName = objArr[1];
            boolean booleanValue = objArr[2].booleanValue();
            if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || booleanValue) {
                DataCollectionDelegate.getInitInstance(eventContext).setRequestData(getTargetKey(eventContext)).setOnDataCompleteListener(new c(this)).start();
            } else {
                DataCollectionDelegate.getInitInstance(eventContext).setRequestData(getTargetKey(eventContext), "data://user/pick/Item", new UriBuilder("data://user/dialog/CopyOrMove").appendArg("dataKey", "data://user/pick/Item").build()).setOnDataUpdateListener(new c(this)).setOnDataCompleteListener(new c(this)).start();
            }
        }
    }
}
