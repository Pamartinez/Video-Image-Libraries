package com.samsung.android.gallery.app.activity;

import A6.a;
import Ba.h;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UsbAttachActivity extends Activity {
    private boolean checkValidation(UsbDevice usbDevice) {
        MtpClient instance = MtpClient.getInstance(getApplicationContext());
        if (usbDevice == null) {
            Log.w("UsbAttachActivity", "Unable to start gallery, device is null.");
            return false;
        } else if (!instance.isCamera(usbDevice)) {
            Log.w("UsbAttachActivity", "Unable to start gallery, device is not camera.");
            return false;
        } else if (!instance.getDeviceList().isEmpty()) {
            return true;
        } else {
            Log.w("UsbAttachActivity", "Unable to start gallery, nothing opened.");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(AtomicBoolean atomicBoolean, UsbDevice usbDevice, CountDownLatch countDownLatch) {
        atomicBoolean.set(checkValidation(usbDevice));
        countDownLatch.countDown();
    }

    private void startGalleryActivity() {
        Map<String, Blackboard> blackboardMap;
        h hVar;
        try {
            Intent intent = new Intent(this, GalleryActivity.class);
            intent.addFlags(PropertyOptions.DELETE_EXISTING);
            startActivity(intent);
            EventMessage obtain = EventMessage.obtain(105, 1, 0, (Object) null);
            blackboardMap = Blackboard.getBlackboardMap();
            hVar = new h(1, obtain);
        } catch (ActivityNotFoundException e) {
            Log.e("UsbAttachActivity", "unable to start gallery activity e=" + e.getMessage());
            EventMessage obtain2 = EventMessage.obtain(105, 1, 0, (Object) null);
            blackboardMap = Blackboard.getBlackboardMap();
            hVar = new h(1, obtain2);
        } catch (Throwable th) {
            Blackboard.getBlackboardMap().forEach(new h(1, EventMessage.obtain(105, 1, 0, (Object) null)));
            throw th;
        }
        blackboardMap.forEach(hVar);
    }

    public void onCreate(Bundle bundle) {
        UsbAttachActivity usbAttachActivity;
        super.onCreate(bundle);
        if (Features.isEnabled(Features.IS_KNOX_MODE)) {
            Log.w("UsbAttachActivity", "knox mode, so skip.");
        } else {
            Intent intent = getIntent();
            String action = intent.getAction();
            Log.d("UsbAttachActivity", "received action [" + action + "]");
            if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                CountDownLatch countDownLatch = new CountDownLatch(1);
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                usbAttachActivity = this;
                ThreadUtil.postOnBgThread(new a((Object) usbAttachActivity, (Object) atomicBoolean, (Object) (UsbDevice) intent.getParcelableExtra(IdentityApiContract.Parameter.DEVICE), (Object) countDownLatch, 3));
                try {
                    if (!countDownLatch.await(10000, TimeUnit.MILLISECONDS)) {
                        Log.e("UsbAttachActivity", "time out");
                    }
                } catch (InterruptedException e) {
                    Log.e("UsbAttachActivity", e.getMessage());
                }
                if (atomicBoolean.get()) {
                    usbAttachActivity.startGalleryActivity();
                } else {
                    Log.d("UsbAttachActivity", "checkValidation : false");
                }
                usbAttachActivity.finish();
            }
        }
        usbAttachActivity = this;
        usbAttachActivity.finish();
    }
}
