package com.samsung.android.gallery.app.controller.externals;

import K4.a;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PasteClipboardCmd extends BaseCommand {
    private MediaItem mTargetAlbum = null;

    /* access modifiers changed from: private */
    public void onCompleted(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            try {
                if (!arrayList.isEmpty()) {
                    Object[] objArr = (Object[]) arrayList.get(0);
                    ArrayList arrayList2 = (ArrayList) objArr[0];
                    ArrayList arrayList3 = (ArrayList) objArr[1];
                    byte byteValue = ((Byte) objArr[2]).byteValue();
                    int intValue = ((Integer) objArr[3]).intValue();
                    String str = (String) objArr[4];
                    if (arrayList2.isEmpty() && arrayList3.isEmpty()) {
                        Context context = getContext();
                        if (TextUtils.isEmpty(str)) {
                            str = getContext().getString(R.string.there_is_no_media_file_to_paste);
                        }
                        Utils.showToast(context, str);
                    } else if (!arrayList2.isEmpty()) {
                        new FileOpCmd().execute(getHandler(), FileOpCmdHelper.CmdType.TYPE_DRAG_TO_ITEMS, arrayList2.toArray(new MediaItem[0]), this.mTargetAlbum, null);
                    } else {
                        getBlackboard().publish("data://user/selected", arrayList3.toArray(new MediaItem[0]));
                        Intent intent = new Intent();
                        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.PasteMultiControlService");
                        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
                        intent.putExtra("blackboard_name", getBlackboard().getName());
                        intent.putExtra("target_album_path", AlbumHelper.getInstance().getValidPath(this.mTargetAlbum));
                        intent.putExtra("clip_id", byteValue);
                        intent.putExtra("device_id", intValue);
                        getContext().startService(intent);
                    }
                }
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "failed to start operation", (Throwable) e);
            }
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem currentItem = eventContext.getCurrentItem();
        this.mTargetAlbum = currentItem;
        if (currentItem == null) {
            Log.e(this.TAG, "invalid target album");
        } else {
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData("data://user/dialog/PastePrepare").setOnDataCompleteListener(new a(11, this)).start();
        }
    }
}
