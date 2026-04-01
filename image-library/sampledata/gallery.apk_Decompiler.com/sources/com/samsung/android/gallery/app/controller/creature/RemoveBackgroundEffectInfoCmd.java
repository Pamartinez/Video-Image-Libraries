package com.samsung.android.gallery.app.controller.creature;

import Fb.h;
import I3.j;
import android.content.Context;
import android.media.MediaScannerConnection;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.dialog.NormalDialogHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.type.ShotModeType;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.NamedThreadHandler;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveBackgroundEffectInfoCmd extends BaseCommand {
    private DialogHelper mDialogHelper;
    private MediaItem[] mItems;
    private int mSuccessCount;
    private final NamedThreadHandler mThreadHandler = new NamedThreadHandler("RemoveBgEffect");

    private void createDialog() {
        NormalDialogHelper normalDialogHelper = new NormalDialogHelper();
        this.mDialogHelper = normalDialogHelper;
        normalDialogHelper.init(getBlackboard());
        this.mDialogHelper.showDialog("Removing live focus extra info", 0, this.mItems.length, -1);
    }

    private void dismissDialog() {
        this.mDialogHelper.dismissDialog();
        this.mDialogHelper = null;
    }

    private void doJob() {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        BlackboardUtils.collectExternalDataChangedEvent(getBlackboard(), true);
        int i2 = 0;
        while (true) {
            MediaItem[] mediaItemArr = this.mItems;
            if (i2 >= mediaItemArr.length) {
                break;
            }
            this.mDialogHelper.updateDialog(i2, mediaItemArr.length, (i2 / mediaItemArr.length) * 100);
            MediaItem mediaItem = this.mItems[i2];
            if (ShotModeType.isLiveFocus(mediaItem.getSefFileType()) && removeSef(new File(mediaItem.getPath()))) {
                this.mSuccessCount++;
                arrayList.add(mediaItem.getPath());
            }
            i2++;
        }
        if (this.mSuccessCount > 0) {
            MediaScanner.scanFiles(getHandler().getContext(), (String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
        }
        BlackboardUtils.collectExternalDataChangedEvent(getBlackboard(), false);
        Log.d(this.TAG, "elapsed ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfirmed$0() {
        String str;
        dismissDialog();
        Context context = getContext();
        StringBuilder sb2 = new StringBuilder("Background effect info removed.");
        if (this.mSuccessCount < this.mItems.length) {
            str = "\nSuccess : " + this.mSuccessCount + "      Not support : " + (this.mItems.length - this.mSuccessCount);
        } else {
            str = "";
        }
        sb2.append(str);
        Utils.showToast(context, sb2.toString());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfirmed$1() {
        doJob();
        DialogHelper dialogHelper = this.mDialogHelper;
        MediaItem[] mediaItemArr = this.mItems;
        dialogHelper.updateDialog(mediaItemArr.length, mediaItemArr.length, 100);
        ThreadUtil.postOnUiThreadDelayed(new j(this, 1), 100);
    }

    /* access modifiers changed from: private */
    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        createDialog();
        this.mThreadHandler.run(new j(this, 0), 100);
    }

    private boolean removeSef(File file) {
        try {
            return SeApiCompat.getSefFileCompat().deleteAllData(file);
        } catch (IOException e) {
            Log.e((CharSequence) this.TAG, "fail remove portrait info", (Throwable) e);
            return false;
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr;
        if (objArr.length > 0) {
            mediaItemArr = objArr[0];
        } else {
            mediaItemArr = null;
        }
        this.mItems = mediaItemArr;
        if (mediaItemArr != null) {
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("type", 0).appendArg("title", "Remove background effect info?").appendArg("description", "File size is reduced to 30% even same bitmap.\nBut you cannot change background effect after removing.").appendArg("option1", "OK").appendArg("option1ColorRed", true).build()).setOnDataCompleteListener(new h(19, this)).start();
        }
    }
}
