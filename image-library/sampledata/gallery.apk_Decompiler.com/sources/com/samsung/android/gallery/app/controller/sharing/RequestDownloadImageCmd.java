package com.samsung.android.gallery.app.controller.sharing;

import O3.y;
import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestDownloadImageCmd extends BaseCommand {
    private EventContext mHandler;
    private boolean mIsDirectDownload;
    private MediaItem mMediaItem;

    private void downloadDirectly() {
        downloadInternal();
    }

    private void downloadInternal() {
        RequestCmdType requestCmdType;
        if (this.mHandler == null) {
            Log.she(this.TAG, "eventContext is null");
            return;
        }
        List list = (List) Arrays.stream(new MediaItem[]{this.mMediaItem}).collect(Collectors.toList());
        RequestSharedAlbumCmd requestSharedAlbumCmd = new RequestSharedAlbumCmd();
        EventContext eventContext = this.mHandler;
        if (this.mIsDirectDownload) {
            requestCmdType = RequestCmdType.REQUEST_DOWNLOAD_FOR_PLAY_IN_SHARING;
        } else {
            requestCmdType = RequestCmdType.REQUEST_DOWNLOAD_CONTENTS;
        }
        requestSharedAlbumCmd.execute(eventContext, requestCmdType, list, Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public void onDownloadConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            Log.she(this.TAG, "onDownloadConfirmed skip with no data");
        } else if (((Integer) arrayList.get(0)).intValue() == 1) {
            downloadInternal();
        }
    }

    private void requestDownloadDialog(EventContext eventContext) {
        int i2;
        Context context = getContext();
        String string = context.getString(R.string.download_image_q);
        String string2 = context.getString(R.string.ok);
        if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
            i2 = R.string.you_need_to_download_the_image_to_view_it_on_your_tablet;
        } else {
            i2 = R.string.you_need_to_download_the_image_to_view_it_on_your_phone;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", string).appendArg("description", context.getString(i2)).appendArg("option1", string2).build()).setOnDataCompleteListener(new y(22, this)).start();
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mMediaItem = objArr[0];
        this.mHandler = eventContext;
        boolean booleanValue = objArr[1].booleanValue();
        this.mIsDirectDownload = booleanValue;
        if (booleanValue) {
            downloadDirectly();
        } else {
            requestDownloadDialog(eventContext);
        }
    }
}
