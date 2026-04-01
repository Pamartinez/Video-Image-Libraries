package com.samsung.android.gallery.app.controller.sharing;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditSharingItemCmd extends BaseCommand {
    private Consumer mConsumer;
    private EventContext mHandler;
    private MediaItem mMediaItem;

    private void downloadDirectly() {
        downloadInternal();
    }

    private void downloadInternal() {
        if (this.mHandler == null) {
            Log.she(this.TAG, "eventContext is null");
            return;
        }
        new RequestSharedAlbumCmd().execute(this.mHandler, RequestCmdType.REQUEST_DOWNLOAD_CONTENTS_FOR_SHARING_EDIT, getMediaItemList(), this.mConsumer);
    }

    private List<FileItemInterface> getMediaItemList() {
        return (List) Arrays.stream(new MediaItem[]{this.mMediaItem}).collect(Collectors.toList());
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mMediaItem = objArr[0];
        this.mConsumer = objArr[1];
        this.mHandler = eventContext;
        downloadDirectly();
    }
}
