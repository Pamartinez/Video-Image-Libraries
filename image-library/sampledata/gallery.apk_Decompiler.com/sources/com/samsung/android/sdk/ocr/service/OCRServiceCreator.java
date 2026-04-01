package com.samsung.android.sdk.ocr.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.samsung.android.sdk.ocr.service.IOCRService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OCRServiceCreator {
    private static final String TAG = "OCRServiceCreator";
    /* access modifiers changed from: private */
    public final Condition mConnectionCondition;
    /* access modifiers changed from: private */
    public final ReentrantLock mConnectionLock;
    private Context mContext;
    /* access modifiers changed from: private */
    public IOCRService mIOCRService;
    /* access modifiers changed from: private */
    public boolean mIsConnected = false;
    /* access modifiers changed from: private */
    public final ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(OCRServiceCreator.TAG, "onServiceConnected : " + componentName);
            OCRServiceCreator.this.mIOCRService = IOCRService.Stub.asInterface(iBinder);
            OCRServiceCreator.this.mConnectionLock.lock();
            try {
                OCRServiceCreator.this.mIsConnected = true;
                Log.d(OCRServiceCreator.TAG, "connected, signal all : " + OCRServiceCreator.this.mServiceConnection);
                OCRServiceCreator.this.mConnectionCondition.signalAll();
            } finally {
                OCRServiceCreator.this.mConnectionLock.unlock();
            }
        }

        /* JADX INFO: finally extract failed */
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(OCRServiceCreator.TAG, "onServiceDisconnected " + componentName);
            OCRServiceCreator.this.mConnectionLock.lock();
            try {
                OCRServiceCreator.this.mIsConnected = false;
                Log.d(OCRServiceCreator.TAG, "disconnected, signal all : " + OCRServiceCreator.this.mServiceConnection);
                OCRServiceCreator.this.mConnectionCondition.signalAll();
                OCRServiceCreator.this.mConnectionLock.unlock();
                OCRServiceCreator.this.mIOCRService = null;
            } catch (Throwable th) {
                OCRServiceCreator.this.mConnectionLock.unlock();
                throw th;
            }
        }
    };

    public OCRServiceCreator(Context context) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mConnectionLock = reentrantLock;
        this.mConnectionCondition = reentrantLock.newCondition();
        this.mContext = context.getApplicationContext();
    }

    private Intent getIntent() {
        return C0280e.a(OCRServiceConstant.ACTION_OCR_SERVICE, "com.samsung.android.sdk.ocr");
    }

    /* JADX INFO: finally extract failed */
    public void close() {
        this.mConnectionLock.lock();
        Log.d(TAG, "close() : connected? " + this.mIsConnected);
        try {
            if (this.mIsConnected) {
                this.mContext.unbindService(this.mServiceConnection);
            }
            this.mConnectionLock.unlock();
            this.mContext = null;
        } catch (Throwable th) {
            this.mConnectionLock.unlock();
            throw th;
        }
    }

    public void connect() {
        this.mConnectionLock.lock();
        try {
            if (!this.mIsConnected) {
                this.mContext.bindService(getIntent(), this.mServiceConnection, 1);
                while (!this.mIsConnected) {
                    this.mConnectionCondition.await();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.mConnectionLock.unlock();
            throw th;
        }
        this.mConnectionLock.unlock();
    }

    public IOCRService getService() {
        return this.mIOCRService;
    }
}
