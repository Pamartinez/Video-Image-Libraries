package com.samsung.android.gallery.app.controller.album;

import Fa.C0571z;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileOpCmdHelper {
    static final boolean FILE_OPERATION_SERVICE_V2;
    private static final FileOpCmdHelper sInstance = new FileOpCmdHelper();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum CmdType {
        TYPE_CHOICE_ALBUM,
        TYPE_ADD_TO_ITEMS,
        TYPE_DRAG_TO_ITEMS,
        TYPE_RENAME_ALBUM
    }

    static {
        boolean z;
        if (!SdkConfig.atLeast(SdkConfig.SEM.S) || !PocFeatures.isEnabled(PocFeatures.FileOpService2)) {
            z = false;
        } else {
            z = true;
        }
        FILE_OPERATION_SERVICE_V2 = z;
    }

    private boolean checkEmptyAlbum(Context context) {
        if (!Features.isEnabled(Features.IS_UPSM)) {
            return false;
        }
        Toast.makeText(context, SeApiCompat.naturalizeText(context.getString(R.string.unable_in_max_power_saving, new Object[]{context.getString(R.string.empty_albums)})), 0).show();
        return true;
    }

    private String getDisabledAlbumType() {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return null;
        }
        return "" + AlbumType.AutoUpdated.toInt() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + AlbumType.Virtual.toInt();
    }

    public static FileOpCmdHelper getInstance() {
        return sInstance;
    }

    private String getTitleOfDragToAlbumDialog(Context context, MediaItem[] mediaItemArr) {
        int i2;
        int i7;
        if (mediaItemArr.length == 1) {
            MediaItem mediaItem = mediaItemArr[0];
            if (mediaItem == null) {
                return "";
            }
            if (mediaItem.isVideo()) {
                i7 = R.string.copy_or_move_1_video;
            } else {
                i7 = R.string.copy_or_move_1_image;
            }
            return context.getString(i7);
        }
        int count = (int) Arrays.stream(mediaItemArr).filter(new C0571z(4)).filter(new C0571z(10)).count();
        int count2 = (int) Arrays.stream(mediaItemArr).filter(new C0571z(4)).filter(new C0571z(11)).count();
        if (count > 0 && count2 > 0) {
            i2 = R.string.copy_or_move_n_items;
        } else if (count > 0) {
            i2 = R.string.copy_or_move_n_videos;
        } else {
            i2 = R.string.copy_or_move_n_images;
        }
        return context.getString(i2, new Object[]{Integer.valueOf(count + count2)});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$startService$0(EventContext eventContext, String str, String str2, boolean z, int i2, boolean z3, MediaItem[] mediaItemArr) {
        if (mediaItemArr != null) {
            eventContext.getBlackboard().publish("data://user/selected", mediaItemArr);
            Intent intent = new Intent();
            if (FILE_OPERATION_SERVICE_V2) {
                intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.FileOpService");
            } else {
                intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.FileOperationService");
            }
            intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
            intent.putExtra("blackboard_name", eventContext.getBlackboard().getName());
            intent.putExtra("location_key", eventContext.getLocationKey());
            intent.putExtra("target_album_path", str);
            intent.putExtra("operation_type", str2);
            intent.putExtra("is_new_album", z);
            intent.putExtra("selected_album_id", i2);
            intent.putExtra("is_empty_album", z3);
            Context readAppContext = BlackboardUtils.readAppContext(eventContext.getBlackboard());
            if (readAppContext != null) {
                try {
                    readAppContext.startService(intent);
                } catch (IllegalStateException e) {
                    Log.e((CharSequence) "FileOpCmdHelper", "copy failed", (Throwable) e);
                }
            }
        }
    }

    public void startAddToAlbumDialogCmd(EventContext eventContext, DataCollectionDelegate.OnDataCompletionListener onDataCompletionListener) {
        if (eventContext == null || eventContext.getContext() == null) {
            Log.e("FileOpCmdHelper", "startAddToAlbumDialogCmd: context is null");
        } else if (!checkEmptyAlbum(eventContext.getContext())) {
            String build = new UriBuilder("data://user/pick/Item").appendArg("pick-max-item", 1000).build();
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(build, new UriBuilder("data://user/dialog/CopyOrMove").appendArg("dataKey", build).build()).setOnDataCompleteListener(onDataCompletionListener).start();
        }
    }

    public void startAddToAutoAlbumDialogCmd(EventContext eventContext, DataCollectionDelegate.OnDataCompletionListener onDataCompletionListener) {
        if (eventContext == null || eventContext.getContext() == null) {
            Log.e("FileOpCmdHelper", "startAddToAlbumDialogCmd: context is null");
        } else if (!checkEmptyAlbum(eventContext.getContext())) {
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/pick/Item").appendArg("pick-max-item", 1000).build()).setOnDataCompleteListener(onDataCompletionListener).start();
        }
    }

    public void startChoiceAlbumCmd(EventContext eventContext, String str, DataCollectionDelegate.OnDataCompletionListener onDataCompletionListener) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/move/AlbumChoice").appendArg("type", str).appendArg("disabledAlbumType", getDisabledAlbumType()).build()).setOnDataCompleteListener(onDataCompletionListener).start();
    }

    public void startDragToAlbumDialogCmd(EventContext eventContext, MediaItem[] mediaItemArr, DataCollectionDelegate.OnDataCompletionListener onDataCompletionListener) {
        Context context = eventContext.getContext();
        String titleOfDragToAlbumDialog = getTitleOfDragToAlbumDialog(context, mediaItemArr);
        String string = context.getString(R.string.move);
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", titleOfDragToAlbumDialog).appendArg("option1", string).appendArg("option2", context.getString(R.string.copy)).appendArg("screenId", AnalyticsScreenId.SCREEN_SPLIT_VIEW_LONG_PRESS.toString()).appendArg("option1EventId", AnalyticsEventId.EVENT_ITEM_MOVE.toString()).appendArg("option2EventId", AnalyticsEventId.EVENT_ITEM_COPY.toString()).appendArg("cancelEventId", AnalyticsEventId.EVENT_COPY_OR_MOVE_ITEM_CANCEL.toString()).build()).setOnDataCompleteListener(onDataCompletionListener).start();
    }

    public void startRenameAlbumCmd(EventContext eventContext, MediaItem mediaItem, String str, DataCollectionDelegate.OnDataCompletionListener onDataCompletionListener) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/AlbumRename").appendArg(FileApiContract.Parameter.PATH, FileUtils.getParentDirectory(FileUtils.getDirectoryFromPath(mediaItem.getAlbumPath()))).appendArg("name", mediaItem.getTitle()).appendArg("screenId", str).build()).setOnDataCompleteListener(onDataCompletionListener).start();
    }

    public void startService(EventContext eventContext, MediaItem[] mediaItemArr, String str, String str2, boolean z) {
        startService(eventContext, mediaItemArr, str, str2, z, -1, false);
    }

    public void startService(EventContext eventContext, MediaItem[] mediaItemArr, String str, String str2) {
        startService(eventContext, mediaItemArr, str, str2, false, -1, false);
    }

    public void startService(EventContext eventContext, MediaItem[] mediaItemArr, String str, String str2, boolean z, int i2, boolean z3) {
        EventContext eventContext2 = eventContext;
        String str3 = str;
        String str4 = str2;
        new CloudDownloadCopyCmd().execute(eventContext2, mediaItemArr, str3, str4, new f(eventContext2, str3, str4, z, i2, z3));
    }
}
