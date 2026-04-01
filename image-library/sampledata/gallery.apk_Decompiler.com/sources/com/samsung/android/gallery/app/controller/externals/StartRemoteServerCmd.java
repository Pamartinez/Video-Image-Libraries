package com.samsung.android.gallery.app.controller.externals;

import A.a;
import C3.g;
import G7.e;
import M3.C0406b;
import N3.d;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.service.RemoteServerService;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remotegallery.RemoteServer;
import com.samsung.android.gallery.module.remotegallery.RemoteWebServer;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ServiceManager;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartRemoteServerCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$2(DialogInterface dialogInterface, int i2) {
        stopService();
        dialogInterface.dismiss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$3(String str, DialogInterface dialogInterface, int i2) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", RemoteWebServer.getWebLink(str));
        intent.setType("text/plain");
        getActivity().startActivity(Intent.createChooser(intent, (CharSequence) null));
        startService();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$4(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        startService();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$5(DialogInterface dialogInterface) {
        stopService();
        dialogInterface.dismiss();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onExecute$6(AlertDialog alertDialog, Object obj, Bundle bundle) {
        if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    private void startService() {
        Intent intent = new Intent(getContext(), RemoteServerService.class);
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        startService(intent);
    }

    private void stopService() {
        Class<RemoteServerService> cls = RemoteServerService.class;
        if (ServiceManager.getInstance().hasRunningService(AppResources.getAppContext(), cls.getName())) {
            Intent intent = new Intent(getContext(), cls);
            intent.setAction("com.samsung.android.gallery.app.service.STOP_SERVICE");
            startService(intent);
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr;
        RemoteWebServer instance = RemoteWebServer.getInstance();
        RemoteServer instance2 = RemoteServer.getInstance();
        if (instance.isRun() || instance2.isRun()) {
            stopService();
        }
        String wifiAddress = Utils.getWifiAddress();
        if (wifiAddress != null) {
            if (LocationKey.isPictures(eventContext.getLocationKey())) {
                mediaItemArr = eventContext.getSelectedItems();
            } else {
                mediaItemArr = null;
            }
            instance2.clearSelectedData();
            if (mediaItemArr == null || mediaItemArr.length <= 0) {
                MediaItem[] allItems = eventContext.getAllItems();
                instance2.setFiles(allItems);
                a.w(new StringBuilder("album : "), allItems.length, this.TAG);
            } else {
                instance2.setFiles(mediaItemArr);
            }
            instance2.setThumbnailLoader(new K.a(8));
            instance.setThumbnailLoader(new K.a(9));
            instance2.start();
            instance.start();
            ImageView imageView = new ImageView(getContext());
            imageView.setImageBitmap(RemoteWebServer.getQr(wifiAddress));
            AlertDialog create = new AlertDialog.Builder(getContext()).setTitle((CharSequence) "Connection Info").setMessage((CharSequence) "server created.\n\nip address : ".concat(wifiAddress)).setView((View) imageView).setNegativeButton((CharSequence) "Stop", (DialogInterface.OnClickListener) new d(this, 0)).setNeutralButton((CharSequence) "Share", (DialogInterface.OnClickListener) new g(5, this, wifiAddress)).setPositiveButton((CharSequence) "Hide", (DialogInterface.OnClickListener) new d(this, 1)).setOnCancelListener(new C0406b(1, this)).create();
            create.show();
            eventContext.getBlackboard().subscribe("lifecycle://on_activity_pause", new e(1, create));
            return;
        }
        Utils.showToast(getContext(), "wifi not connected");
    }
}
