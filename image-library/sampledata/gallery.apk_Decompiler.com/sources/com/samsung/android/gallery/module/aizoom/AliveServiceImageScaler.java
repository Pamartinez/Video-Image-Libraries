package com.samsung.android.gallery.module.aizoom;

import B1.b;
import E2.k;
import Fd.C0744a;
import S1.n;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.SharedMemory;
import android.util.Log;
import b3.C0083a;
import b3.C0084b;
import e3.a;
import java.nio.ByteBuffer;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AliveServiceImageScaler implements ImageScaleInterface {
    private final a mAsImageUpscale;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, e3.a] */
    /* JADX WARNING: type inference failed for: r1v2, types: [Fd.a, java.lang.Object] */
    public AliveServiceImageScaler(Context context) {
        ? obj = new Object();
        obj.f1727a = context;
        obj.f = Uri.parse("content://com.samsung.android.alive.service.image");
        ? obj2 = new Object();
        k kVar = new k();
        kVar.b = false;
        kVar.e = new C0083a(0, kVar);
        obj2.d = kVar;
        obj2.f3376i = new C0084b(obj2);
        obj.b = obj2;
        if (!kVar.e()) {
            C0744a aVar = obj.b;
            Object obj3 = new Object();
            if (context == null) {
                aVar.getClass();
                Log.e("AsServiceConnection", og.k.j("Context is null. init failed", new Object[0]));
            } else {
                aVar.getClass();
                new Intent("alive.intent.action.BIND_IMAGE_SERVICE").setPackage("com.samsung.android.alive.service");
                HandlerThread handlerThread = new HandlerThread("AsServiceConnection", 0);
                aVar.f = handlerThread;
                handlerThread.start();
                aVar.g = new n(aVar, ((HandlerThread) aVar.f).getLooper(), 1);
                aVar.e = obj3;
                Intent a7 = C0280e.a("alive.intent.action.BIND_IMAGE_SERVICE", "com.samsung.android.alive.service");
                b bVar = new b(9, aVar);
                k kVar2 = (k) aVar.d;
                if (!kVar2.e()) {
                    kVar2.f169c = bVar;
                    if (kVar2.e()) {
                        og.k.m("AsConnectionManager", "just return already bound service obj", new Object[0]);
                    } else {
                        og.k.m("AsConnectionManager", "connectToService mIsConnected = ", Boolean.valueOf(kVar2.b));
                        boolean z = true;
                        if (!kVar2.b) {
                            og.k.m("AsConnectionManager", "Binding service with app context", new Object[0]);
                            kVar2.d = context;
                            z = context.bindService(a7, (C0083a) kVar2.e, 1);
                        } else {
                            og.k.m("AsConnectionManager", "already bound", new Object[0]);
                        }
                        og.k.m("AsConnectionManager", "connectToService result", Boolean.valueOf(z));
                        if (!z) {
                            kVar2.f(3, (ComponentName) null, (IBinder) null);
                        }
                    }
                }
                try {
                    Bundle bundle = context.getPackageManager().getApplicationInfo("com.samsung.android.alive.service", 128).metaData;
                    if (bundle != null) {
                        bundle.getInt("com.samsung.android.alive.service.image");
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("Utils", og.k.j("Package NameNotFoundException", new Object[]{e}));
                }
            }
        }
        this.mAsImageUpscale = obj;
    }

    public boolean createSession(int i2, String str) {
        a aVar = this.mAsImageUpscale;
        aVar.getClass();
        try {
            return aVar.c(i2, str);
        } catch (Exception e) {
            Log.e("AsImageUpscale [UPSCALE]", og.k.j(" createSession failed", new Object[]{e}));
            return false;
        }
    }

    public int detectScene(Bitmap bitmap) {
        a aVar = this.mAsImageUpscale;
        aVar.getClass();
        if (bitmap != null) {
            og.k.m("AsImageUpscale [UPSCALE]", "detectScene, mConnection not null", new Object[0]);
            try {
                aVar.a();
                aVar.b(bitmap, -1, (Rect) null);
                long currentTimeMillis = System.currentTimeMillis();
                bitmap.copyPixelsToBuffer(aVar.f1728c);
                Bundle call = aVar.f1727a.getContentResolver().call(aVar.f, "upscaleImage", "performUpscale", aVar.e);
                aVar.a();
                int i2 = call.getInt("sceneType");
                og.k.m("AsImageUpscale [UPSCALE]", "detectScene, time taken, scene is " + (System.currentTimeMillis() - currentTimeMillis) + " , " + i2, new Object[0]);
                return i2;
            } catch (Exception e) {
                Log.e("AsImageUpscale [UPSCALE]", og.k.j("detectScene", new Object[]{e}));
            }
        }
        return -1;
    }

    public void endSession() {
        a aVar = this.mAsImageUpscale;
        aVar.getClass();
        try {
            C0744a aVar2 = aVar.b;
            if (aVar2 != null && ((k) aVar2.d).e()) {
                aVar.b.e();
            }
            ByteBuffer byteBuffer = aVar.f1728c;
            if (byteBuffer != null) {
                SharedMemory.unmap(byteBuffer);
                aVar.f1728c = null;
            }
            ByteBuffer byteBuffer2 = aVar.d;
            if (byteBuffer2 != null) {
                SharedMemory.unmap(byteBuffer2);
                aVar.d = null;
            }
            aVar.f1727a.getContentResolver().call(aVar.f, "upscaleImage", "endSession", new Bundle());
            og.k.m("AsImageUpscale [UPSCALE]", " endSession", new Object[0]);
        } catch (Exception e) {
            Log.e("AsImageUpscale [UPSCALE]", og.k.j(" Exception endSession ", new Object[0]), e);
        }
    }

    public Bitmap upscaleImage(Bitmap bitmap, int i2, Rect rect, int i7) {
        Bitmap bitmap2;
        Bitmap bitmap3 = bitmap;
        a aVar = this.mAsImageUpscale;
        aVar.getClass();
        og.k.m("AsImageUpscale [UPSCALE]", "upscaleImage", Integer.valueOf(i2));
        if (bitmap3 != null) {
            og.k.m("AsImageUpscale [UPSCALE]", "upscaleImage, mConnection not null", new Object[0]);
            long currentTimeMillis = System.currentTimeMillis();
            try {
                aVar.a();
                aVar.b(bitmap3, i2, rect);
                aVar.e.putInt("sceneType", i7);
                long currentTimeMillis2 = System.currentTimeMillis();
                bitmap3.copyPixelsToBuffer(aVar.f1728c);
                og.k.m("AsImageUpscale [UPSCALE]", " input copyPixelsToBuffer TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis2), new Object[0]);
                long currentTimeMillis3 = System.currentTimeMillis();
                bitmap2 = null;
                try {
                    Bundle call = aVar.f1727a.getContentResolver().call(aVar.f, "upscaleImage", "performUpscale", aVar.e);
                    og.k.m("AsImageUpscale [UPSCALE]", " perform upscale SDK TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis3), new Object[0]);
                    aVar.a();
                    int i8 = call.getInt("imageWidth");
                    int i10 = call.getInt("imageHeight");
                    int i11 = call.getInt("imageSize");
                    if (i11 == 0) {
                        og.k.m("AsImageUpscale [UPSCALE]", " received empty buffer", new Object[0]);
                        return null;
                    }
                    og.k.m("AsImageUpscale [UPSCALE]", " received buffer", Integer.valueOf(i11));
                    aVar.d.limit(i8 * i10 * 4);
                    long currentTimeMillis4 = System.currentTimeMillis();
                    Bitmap createBitmap = Bitmap.createBitmap(i8, i10, bitmap3.getConfig(), true, bitmap3.getColorSpace());
                    og.k.m("AsImageUpscale [UPSCALE]", " create scaled bitmap TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis4), new Object[0]);
                    long currentTimeMillis5 = System.currentTimeMillis();
                    createBitmap.copyPixelsFromBuffer(aVar.d);
                    og.k.m("AsImageUpscale [UPSCALE]", " output copyPixelsFromBuffer TIME TAKEN " + (System.currentTimeMillis() - currentTimeMillis5), new Object[0]);
                    og.k.m("AsImageUpscale [UPSCALE]", " total time by upscaleImage at SDK ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    return createBitmap;
                } catch (Exception e) {
                    e = e;
                    Log.e("AsImageUpscale [UPSCALE]", og.k.j(" error in upscale", new Object[0]), e);
                    return bitmap2;
                }
            } catch (Exception e7) {
                e = e7;
                bitmap2 = null;
                Log.e("AsImageUpscale [UPSCALE]", og.k.j(" error in upscale", new Object[0]), e);
                return bitmap2;
            }
        } else {
            Log.e("AsImageUpscale [UPSCALE]", og.k.j(" Initialization Error :: Please call createSession() before calling upscaleImage", new Object[0]));
            return null;
        }
    }
}
