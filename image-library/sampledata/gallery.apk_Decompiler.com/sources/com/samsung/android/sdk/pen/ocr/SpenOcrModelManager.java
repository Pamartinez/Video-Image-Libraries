package com.samsung.android.sdk.pen.ocr;

import android.content.Context;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrModelManager implements SpenIOcrModelManager {
    private static final String TAG = "SpenOcrManager";
    private long mNativeHandle;

    public SpenOcrModelManager(long j2) {
        this.mNativeHandle = j2;
        Log.i(TAG, "SpenOcrModelManager is created! mNativeHandle : " + Long.toHexString(this.mNativeHandle));
    }

    private native void Native_finalize(long j2);

    private native int Native_findDB(long j2, SpenDBConfig spenDBConfig);

    private native String Native_getDBVersion(long j2, int i2);

    private native int Native_loadDB(long j2, FileDescriptor fileDescriptor, long j3, long j8, SpenDBConfig spenDBConfig);

    private native int Native_loadDB(long j2, String str, SpenDBConfig spenDBConfig);

    private native void Native_setMaxDBNumber(long j2, int i2);

    private native void Native_setModelLoader(long j2, long j3);

    private native void Native_unloadAllDB(long j2);

    private native void Native_unloadDB(long j2, int i2);

    private static void saveFile(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        if (bArr == null) {
            Log.e(TAG, "SpenOcrManager::saveFile data is invalid");
            return;
        }
        try {
            fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            return;
        } catch (IOException unused) {
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void close() {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            Native_finalize(j2);
        }
        this.mNativeHandle = 0;
    }

    public void finalize() {
        super.finalize();
        close();
    }

    public int findDB(SpenDBConfig spenDBConfig) {
        Log.i(TAG, "SpenOcrManager::FindDB");
        return Native_findDB(this.mNativeHandle, spenDBConfig);
    }

    public String getDBVersion(int i2) {
        Log.i(TAG, "SpenOcrManager::GetDBVersion id[" + i2 + "]");
        return Native_getDBVersion(this.mNativeHandle, i2);
    }

    public int loadDB(Context context, String str, SpenDBConfig spenDBConfig) {
        if (str == null) {
            Log.e(TAG, "SpenOcrManager::LoadDB : filePath is null!");
            return -1;
        }
        int findDB = findDB(spenDBConfig);
        if (findDB != -1) {
            Log.w(TAG, "SpenOcrManager::LoadDB : There is already DB num[" + findDB + "]");
            return findDB;
        }
        int Native_loadDB = Native_loadDB(this.mNativeHandle, str, spenDBConfig);
        Log.i(TAG, "SpenOcrManager::LoadDB : succeed! DB num[" + Native_loadDB + "]");
        return Native_loadDB;
    }

    public void setMaxDBNumber(int i2) {
        Log.i(TAG, "SpenOcrManager::setMaxDBNumber " + i2);
        Native_setMaxDBNumber(this.mNativeHandle, i2);
    }

    public void setModelLoader(SpenIOcrModelLoaderNative spenIOcrModelLoaderNative) {
        long j2;
        if (spenIOcrModelLoaderNative != null) {
            j2 = spenIOcrModelLoaderNative.nativeHandle();
        } else {
            j2 = 0;
        }
        Log.i(TAG, "SpenOcrManager::setModelLoader " + j2);
        Native_setModelLoader(this.mNativeHandle, j2);
    }

    public void unloadAllDB() {
        Log.i(TAG, "SpenOcrManager::UnloadAllDB");
        Native_unloadAllDB(this.mNativeHandle);
    }

    public void unloadDB(int i2) {
        Log.i(TAG, "SpenOcrManager::UnloadDB id[" + i2 + "]");
        Native_unloadDB(this.mNativeHandle, i2);
    }

    public int loadDB(Context context, Object[] objArr, SpenDBConfig spenDBConfig) {
        if (objArr == null || objArr[0] == null) {
            Log.e(TAG, "SpenOcrManager::LoadDB : FileDescriptor is null!");
            return -1;
        }
        Log.i(TAG, "SpenOcrManager::LoadDB : fd[" + objArr[0] + "], start[" + objArr[1] + "], len[" + objArr[2] + "]");
        SpenDBConfig spenDBConfig2 = spenDBConfig;
        int findDB = findDB(spenDBConfig2);
        if (findDB != -1) {
            Log.w(TAG, "SpenOcrManager::LoadDB : There is already DB num[" + findDB + "]");
            return findDB;
        }
        int Native_loadDB = Native_loadDB(this.mNativeHandle, objArr[0], Long.valueOf(String.valueOf(objArr[1])).longValue(), Long.valueOf(String.valueOf(objArr[2])).longValue(), spenDBConfig2);
        Log.i(TAG, "SpenOcrManager::LoadDB : succeed! DB num[" + Native_loadDB + "]");
        return Native_loadDB;
    }
}
