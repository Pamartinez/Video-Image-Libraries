package com.samsung.android.gallery.app.controller.sharing;

import O3.y;
import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteSharedAlbumCmd extends BaseCommand {
    private AnalyticsEventId mEventId;
    private MediaItem[] mItems;

    private String getTitle(Context context, String str) {
        if (MdeGroupHelper.isSAFamilyGroup(str)) {
            return context.getString(R.string.delete_shared_family_album_question);
        }
        return context.getString(R.string.delete_shared_album_question_local_group);
    }

    private boolean isFamilySpace(List<FileItemInterface> list) {
        FileItemInterface fileItemInterface;
        if (list.size() != 1 || (fileItemInterface = list.get(0)) == null || !MdeGroupHelper.isSAFamilyGroup(MediaItemMde.getGroupId(fileItemInterface))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void requestDeleteSharedAlbum(EventContext eventContext, ArrayList<Object> arrayList) {
        RequestCmdType requestCmdType;
        int i2 = 0;
        if (arrayList != null) {
            i2 = ((Integer) arrayList.get(0)).intValue();
        }
        if (i2 == 1) {
            ArrayList arrayList2 = new ArrayList();
            Collections.addAll(arrayList2, this.mItems);
            if (selectedOwnedGalleryLocalGroupSpace(arrayList2)) {
                requestCmdType = RequestCmdType.REQUEST_DELETE_GROUP;
            } else if (isFamilySpace(arrayList2)) {
                requestCmdType = RequestCmdType.REQUEST_DELETE_FAMILY_SPACE;
            } else {
                requestCmdType = RequestCmdType.REQUEST_DELETE_SPACE;
            }
            new RequestSharedAlbumCmd().execute(eventContext, requestCmdType, arrayList2);
        }
    }

    private boolean selectedOwnedGalleryLocalGroupSpace(List<FileItemInterface> list) {
        FileItemInterface fileItemInterface;
        if (list.size() != 1 || (fileItemInterface = list.get(0)) == null || !MediaItemMde.isOwnedByMe(fileItemInterface) || !MediaItemMde.getGroupId(fileItemInterface).startsWith("UNM1")) {
            return false;
        }
        return true;
    }

    public String getEventId() {
        return this.mEventId.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        Context context = getContext();
        this.mEventId = objArr[0];
        MediaItem[] mediaItemArr = objArr[1];
        this.mItems = mediaItemArr;
        MediaItem mediaItem = mediaItemArr[0];
        String groupId = MediaItemMde.getGroupId(mediaItem);
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !LocationKey.isAlbums(eventContext.getLocationKey())) {
            MediaData open = MediaDataFactory.getInstance(getBlackboard()).open("location://sharing/groups", true);
            try {
                MediaItem readByKey = open.readByKey(groupId);
                open.close();
                mediaItem = readByKey;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (mediaItem == null) {
            Log.sh(this.TAG, "Group already deleted.");
            return;
        }
        String title = getTitle(getContext(), groupId);
        String string = context.getString(R.string.delete);
        String string2 = context.getString(R.string.delete_shared_album_body);
        if (isFamilySpace(List.of(this.mItems))) {
            str = "data://user/dialog/SimpleConfirmProgress";
        } else {
            str = "data://user/dialog/SimpleConfirm";
        }
        String build = new UriBuilder(str).appendArg("title", title).appendArg("description", string2).appendArg("option1", string).appendArg("screenId", getScreenId()).appendArg("dismissKey", "command://FamilySharedAlbumRemoveFinished").appendArg("option1EventId", AnalyticsEventId.EVENT_SHARED_VIEW_DELETE_DIALOG_OK.toString()).appendArg("cancelEventId", AnalyticsEventId.EVENT_SHARED_VIEW_DELETE_DIALOG_CANCEL.toString()).build();
        String str2 = this.TAG;
        Log.d(str2, "DataCollectionDelegate" + Logger.vt(str, Long.valueOf(currentTimeMillis)));
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(build).setOnDataCompleteListener(new y(15, this)).start();
        return;
        throw th;
    }
}
