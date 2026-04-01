package com.samsung.android.gallery.app.controller.album;

import Fa.C0558l;
import Fa.C0571z;
import Gb.a;
import N2.j;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.DownloadCmd;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ServiceManager;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CloudDownloadCopyCmd extends BaseCommand {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ICloudDownloadListener {
    }

    private String getAlbumName(String str) {
        return AlbumTitleHelper.getAlbumTitle(FileUtils.getBucketId(str), FileUtils.getBucketNameFromPath(str));
    }

    /* access modifiers changed from: private */
    public MediaItem[] getFilterList(MediaItem[] mediaItemArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            long groupMediaId = mediaItem.getGroupMediaId();
            if (groupMediaId == 0) {
                arrayList.add(mediaItem);
            } else if (!arrayList2.contains(Long.valueOf(groupMediaId))) {
                arrayList2.add(Long.valueOf(groupMediaId));
                arrayList.add(mediaItem);
            }
        }
        if (mediaItemArr.length != arrayList.size()) {
            Log.e(this.TAG, "download item update (separation) all item count => before count : " + mediaItemArr.length + ", after count : " + arrayList.size());
        }
        return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
    }

    private ResultReceiver getResultReceiver(MediaItem[] mediaItemArr, List<MediaItem> list, ICloudDownloadListener iCloudDownloadListener) {
        final MediaItem[] mediaItemArr2 = mediaItemArr;
        final List<MediaItem> list2 = list;
        final ICloudDownloadListener iCloudDownloadListener2 = iCloudDownloadListener;
        return new ResultReceiver((Handler) null) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onReceiveResult$2(List list, List list2, MediaItem[] mediaItemArr, ICloudDownloadListener iCloudDownloadListener) {
                Cursor query;
                MediaItem[] mediaItemArr2 = null;
                if (!list.isEmpty()) {
                    try {
                        query = DbCompat.query((Consumer<QueryParams>) new e(list));
                        if (query != null) {
                            while (query.moveToNext()) {
                                MediaItem create = MediaItemBuilder.create(query);
                                int binarySearch = Collections.binarySearch(list2, Long.valueOf(create.getFileId()));
                                if (binarySearch >= 0) {
                                    if (create.isCloudOnly()) {
                                        create = null;
                                    }
                                    mediaItemArr[binarySearch] = create;
                                }
                            }
                        }
                        MediaItem[] j2 = CloudDownloadCopyCmd.this.getFilterList(mediaItemArr);
                        if (query != null) {
                            query.close();
                        }
                        mediaItemArr2 = j2;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                }
                ((f) iCloudDownloadListener).a(mediaItemArr2);
                return;
                throw th;
            }

            /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Object, java.util.function.Function] */
            public void onReceiveResult(int i2, Bundle bundle) {
                if (i2 == 0) {
                    CloudDownloadCopyCmd.this.getBlackboard().publish("data://is_running_cloud_download_copy", Boolean.TRUE);
                } else if (i2 == 1 || i2 == 2) {
                    ThreadUtil.postOnBgThreadDelayed(new d(this, (List) list2.stream().map(new a(4)).collect(Collectors.toList()), (List) Arrays.stream(mediaItemArr2).filter(new C0571z(4)).map(new Object()).collect(Collectors.toList()), mediaItemArr2, iCloudDownloadListener2), 2000);
                    CloudDownloadCopyCmd.this.getBlackboard().erase("data://is_running_cloud_download_copy");
                }
            }
        };
    }

    private boolean isRunningCloudDownloadCopy() {
        if (!((Boolean) getBlackboard().read("data://is_running_cloud_download_copy", Boolean.FALSE)).booleanValue()) {
            return false;
        }
        if (ServiceManager.getInstance().hasRunningService(getContext(), "com.samsung.android.gallery.app.service.DownloadService")) {
            return true;
        }
        getBlackboard().erase("data://is_running_cloud_download_copy");
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startCloudDownloadCopy$1(EventContext eventContext, List list, MediaItem[] mediaItemArr, ICloudDownloadListener iCloudDownloadListener, Boolean bool) {
        new DownloadCmd().execute(eventContext, list.toArray(new MediaItem[0]), getResultReceiver(mediaItemArr, list, iCloudDownloadListener));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startCloudDownloadCopy$2(List list, String str, EventContext eventContext, MediaItem[] mediaItemArr, ICloudDownloadListener iCloudDownloadListener) {
        List list2 = list;
        showCloudDownloadCopyDialog(list2, str, new b(this, eventContext, list2, mediaItemArr, iCloudDownloadListener));
    }

    private String[] makeTitleAndDescription(Context context, List<MediaItem> list, String str) {
        int i2;
        String str2;
        int i7;
        String str3;
        int i8;
        Context context2 = context;
        String[] strArr = new String[2];
        long j2 = 0;
        long j3 = 0;
        long j8 = 0;
        for (MediaItem next : list) {
            if (next.isGroupShot()) {
                j2 += (long) next.getCount();
            } else if (next.isVideo()) {
                j8++;
            } else if (next.isImage()) {
                j3++;
            } else {
                Log.e(this.TAG, "invalid item=" + MediaItemUtil.getSimpleLog(next));
            }
        }
        String str4 = this.TAG;
        StringBuilder j10 = j.j(j2, "Contents{g=", ",i=");
        j10.append(j3);
        j10.append(",v=");
        j10.append(j8);
        j10.append("}");
        Log.d(str4, j10.toString());
        String albumName = getAlbumName(str);
        if (j2 > 0 || (j3 > 0 && j8 > 0)) {
            strArr[0] = context2.getString(R.string.cloud_items_download_copy_dialog_title, new Object[]{Long.valueOf(j2 + j3 + j8)});
            strArr[1] = context2.getString(R.string.cloud_items_download_copy_dialog_contents, new Object[]{albumName, albumName});
            return strArr;
        } else if (j8 > 0) {
            int i10 = (j8 > 1 ? 1 : (j8 == 1 ? 0 : -1));
            if (i10 == 0) {
                str3 = context2.getString(R.string.cloud_video_download_copy_dialog_title);
            } else {
                str3 = context2.getString(R.string.cloud_videos_download_copy_dialog_title, new Object[]{Long.valueOf(j8)});
            }
            strArr[0] = str3;
            if (i10 == 0) {
                i8 = R.string.cloud_video_download_copy_dialog_contents;
            } else {
                i8 = R.string.cloud_videos_download_copy_dialog_contents;
            }
            strArr[1] = context2.getString(i8, new Object[]{albumName, albumName});
            return strArr;
        } else if (i2 <= 0) {
            return null;
        } else {
            int i11 = (j3 > 1 ? 1 : (j3 == 1 ? 0 : -1));
            if (i11 == 0) {
                str2 = context2.getString(R.string.cloud_image_download_copy_dialog_title);
            } else {
                str2 = context2.getString(R.string.cloud_images_download_copy_dialog_title, new Object[]{Long.valueOf(j3)});
            }
            strArr[0] = str2;
            if (i11 == 0) {
                i7 = R.string.cloud_image_download_copy_dialog_contents;
            } else {
                i7 = R.string.cloud_images_download_copy_dialog_contents;
            }
            strArr[1] = context2.getString(i7, new Object[]{albumName, albumName});
            return strArr;
        }
    }

    private void showCloudDownloadCopyDialog(List<MediaItem> list, String str, Consumer<Boolean> consumer) {
        String[] makeTitleAndDescription = makeTitleAndDescription(getContext(), list, str);
        if (makeTitleAndDescription != null) {
            new AlertDialog.Builder(getContext()).setTitle((CharSequence) makeTitleAndDescription[0]).setMessage((CharSequence) makeTitleAndDescription[1]).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) new C0558l(4, consumer)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setCancelable(true).create().show();
        }
    }

    private void startCloudDownloadCopy(EventContext eventContext, MediaItem[] mediaItemArr, String str, String str2, ICloudDownloadListener iCloudDownloadListener) {
        List arrayList;
        int i2;
        if (isRunningCloudDownloadCopy()) {
            Context context = getContext();
            if ("move".equals(str2)) {
                i2 = R.string.caution_already_running_cloud_download_copy_routine_for_move;
            } else {
                i2 = R.string.caution_already_running_cloud_download_copy_routine_for_copy;
            }
            Toast.makeText(context, i2, 0).show();
            return;
        }
        if (!"rename".equals(str2)) {
            try {
                Arrays.sort(mediaItemArr, Comparator.comparing(new a(3)));
            } catch (NullPointerException unused) {
                Log.e(this.TAG, "selected item is null");
                return;
            }
        }
        if (mediaItemArr != null) {
            arrayList = (List) Arrays.stream(mediaItemArr).filter(new C0571z(4)).filter(new C0571z(5)).collect(Collectors.toList());
        } else {
            arrayList = new ArrayList();
        }
        List list = arrayList;
        if ("move".equals(str2) || "rename".equals(str2) || list.isEmpty()) {
            ((f) iCloudDownloadListener).a(mediaItemArr);
        } else if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || (!"move_to_secured".equals(str2) && !"move_from_secured".equals(str2))) {
            ThreadUtil.runOnUiThread(new a(this, list, str, eventContext, mediaItemArr, iCloudDownloadListener));
        } else {
            ((f) iCloudDownloadListener).a(mediaItemArr);
        }
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        int i2;
        MediaItem[] mediaItemArr = objArr[0];
        String str = objArr[1];
        String str2 = objArr[2];
        ICloudDownloadListener iCloudDownloadListener = objArr[3];
        String str3 = this.TAG;
        StringBuilder k = j.k("CloudDownloadCopyCmd {", str2, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (mediaItemArr != null) {
            i2 = mediaItemArr.length;
        } else {
            i2 = -1;
        }
        k.append(i2);
        k.append("}");
        Log.d(str3, k.toString());
        startCloudDownloadCopy(eventContext, mediaItemArr, str, str2, iCloudDownloadListener);
    }
}
