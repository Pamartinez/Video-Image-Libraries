package com.samsung.android.gallery.app.controller.internals;

import M5.a;
import O3.b;
import O3.v;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.PdfWriter;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveAsPdfCmd extends BaseCommand implements SelectableChecker<MediaItem> {
    MediaScannerConnection connection;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$save$3(List list) {
        Bitmap bitmap;
        if (list == null || list.size() == 0) {
            Log.e(this.TAG, "unable to save pdf. no item selected.");
            return;
        }
        showProgress(false);
        PdfWriter pdfWriter = new PdfWriter();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = (MediaItem) it.next();
            if (mediaItem.isVideo()) {
                bitmap = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND);
            } else {
                bitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.of(mediaItem).adjustInSampleSize(BitmapSizeHolder.size()));
            }
            if (bitmap != null) {
                if (!mediaItem.isVideo()) {
                    bitmap = new BitmapOperator(bitmap).rotate(mediaItem.getOrientation(), mediaItem.getOrientationTag()).apply();
                }
                pdfWriter.addPage(bitmap);
            }
        }
        final File write = pdfWriter.write("Gallery_" + TimeUtil.getFileTimestamp() + ".pdf");
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(AppResources.getAppContext(), new MediaScannerConnection.MediaScannerConnectionClient() {
            public void onMediaScannerConnected() {
                SaveAsPdfCmd.this.connection.scanFile(write.getAbsolutePath(), (String) null);
            }

            public void onScanCompleted(String str, Uri uri) {
                Intent dataAndType = new Intent("android.intent.action.VIEW").setDataAndType(uri, "application/pdf");
                dataAndType.setPackage("com.samsung.android.app.notes");
                try {
                    SaveAsPdfCmd.this.getActivity().startActivity(dataAndType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.connection = mediaScannerConnection;
        mediaScannerConnection.connect();
        hideProgress();
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showConfirmDialogAndSave$1(MediaItem[] mediaItemArr, EventContext eventContext, ArrayList arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList(Arrays.asList(mediaItemArr));
            if (((Integer) arrayList.get(0)).intValue() == 1) {
                arrayList2.sort(new v(this, 0));
                save(arrayList2);
                return;
            }
            arrayList2.sort(new v(this, 1));
            save(arrayList2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showConfirmDialogAndSave$2(EventContext eventContext, String str, MediaItem[] mediaItemArr) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(str).setOnDataCompleteListener(new b(7, this, mediaItemArr)).start();
    }

    private void save(List<MediaItem> list) {
        SimpleThreadPool.getInstance().execute(new a(21, this, list));
    }

    private void showConfirmDialogAndSave(EventContext eventContext, MediaItem[] mediaItemArr) {
        ThreadUtil.postOnUiThread(new A6.a((Object) this, (Object) eventContext, (Object) new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", getContext().getString(R.string.save_as_pdf)).appendArg("description", getContext().getString(R.string.select_order)).appendArg("option1", getContext().getString(R.string.reverse_order)).appendArg("option2", getContext().getString(R.string.default_order)).build(), (Object) mediaItemArr, 14));
    }

    /* renamed from: compare */
    public int lambda$showConfirmDialogAndSave$0(MediaItem mediaItem, MediaItem mediaItem2) {
        int i2 = (mediaItem.getDateTaken() > mediaItem2.getDateTaken() ? 1 : (mediaItem.getDateTaken() == mediaItem2.getDateTaken() ? 0 : -1));
        if (i2 == 0) {
            if (mediaItem.getFileId() > mediaItem2.getFileId()) {
                return 1;
            }
            return -1;
        } else if (i2 > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getMaxCount() {
        return 100;
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            int selectedItemCount = eventContext.getSelectedItemCount();
            if (selectedItemCount == 0) {
                getBlackboard().publish("data://user/pick/ItemChecker", this);
                Bundle bundle = new Bundle();
                bundle.putString("title", getContext().getString(R.string.save_as_pdf));
                bundle.putInt("maxCount", 100);
                EventMessage obtain = EventMessage.obtain(1025);
                obtain.setData(bundle);
                getBlackboard().postEvent(obtain);
            } else if (selectedItemCount > 100) {
                showToast("Cannot create pdf file more than 100 files. Failed with " + eventContext.getSelectedItemCount() + " files");
            } else {
                MediaItem[] selectedItems = eventContext.getSelectedItems();
                if (selectedItems == null || selectedItems.length == 0) {
                    Log.e(this.TAG, "unable to save pdf. no item selected.");
                } else {
                    showConfirmDialogAndSave(eventContext, selectedItems);
                }
            }
        }
    }

    public void showExceedMaxCountToast(Context context) {
        showToast("Cannot create pdf file more than 100 files");
    }

    public void done(MediaItem[] mediaItemArr) {
        if (mediaItemArr != null && mediaItemArr.length > 0) {
            showConfirmDialogAndSave(getHandler(), mediaItemArr);
        }
    }

    public boolean isSupported(MediaItem mediaItem) {
        return mediaItem != null && !mediaItem.isBroken();
    }
}
