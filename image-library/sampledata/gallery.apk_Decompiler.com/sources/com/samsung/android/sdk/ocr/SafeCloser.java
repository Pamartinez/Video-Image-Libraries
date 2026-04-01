package com.samsung.android.sdk.ocr;

import android.os.Looper;
import android.util.Log;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SafeCloser {
    private static final String TAG = "SafeCloser";
    private static final int THREAD_SLEEP_TIME_INTERVAL = 10;
    private final int[] API_CALL_COUNTS;
    private int mInstanceID = 0;

    public SafeCloser(int i2) {
        int[] iArr = new int[RecognizerApi.values().length];
        this.API_CALL_COUNTS = iArr;
        this.mInstanceID = i2;
        Arrays.fill(iArr, 0);
    }

    private int getAllApiCallingCount() {
        return Arrays.stream(this.API_CALL_COUNTS).sum();
    }

    private void waitingSeveralTimes(int i2) {
        for (int i7 = 0; i7 < i2; i7++) {
            Log.i(TAG, "waitUntilOtherThreadIsDone() : waiting check [" + i7 + "]th");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (getAllApiCallingCount() > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e7) {
                    e7.printStackTrace();
                }
            }
        }
    }

    public synchronized void decreaseCallingCountOf(RecognizerApi recognizerApi) {
        int index = recognizerApi.toIndex();
        Log.i(TAG, "decreaseCallingCountOf : [" + this.mInstanceID + "] apiName = " + recognizerApi.toName() + ", apiIndex = " + index);
        StringBuilder sb2 = new StringBuilder("decreaseCallingCountOf : [");
        sb2.append(this.mInstanceID);
        sb2.append("] currCount = ");
        sb2.append(this.API_CALL_COUNTS[index]);
        Log.d(TAG, sb2.toString());
        int[] iArr = this.API_CALL_COUNTS;
        iArr[index] = Math.max(iArr[index] - 1, 0);
        Log.d(TAG, "decreaseCallingCountOf : [" + this.mInstanceID + "] nextCount = " + this.API_CALL_COUNTS[index]);
    }

    public synchronized void increaseCallingCountOf(RecognizerApi recognizerApi) {
        int index = recognizerApi.toIndex();
        Log.i(TAG, "increaseCallingCountOf : [" + this.mInstanceID + "] apiName = " + recognizerApi.toName() + ", apiIndex = " + index);
        StringBuilder sb2 = new StringBuilder("increaseCallingCountOf : [");
        sb2.append(this.mInstanceID);
        sb2.append("] currCount = ");
        sb2.append(this.API_CALL_COUNTS[index]);
        Log.d(TAG, sb2.toString());
        int[] iArr = this.API_CALL_COUNTS;
        iArr[index] = Math.min(iArr[index] + 1, Integer.MAX_VALUE);
        Log.d(TAG, "increaseCallingCountOf : [" + this.mInstanceID + "] nextCount = " + this.API_CALL_COUNTS[index]);
    }

    public void waitUntilOtherThreadIsDone() {
        int allApiCallingCount = getAllApiCallingCount();
        Log.i(TAG, "waitUntilOtherThreadIsDone() : [" + this.mInstanceID + "] workingApiCount = " + allApiCallingCount);
        if (allApiCallingCount <= 0) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.i(TAG, "waitUntilOtherThreadIsDone() : This is worker thread, so wait until ocr process will be done.");
            waitingSeveralTimes(2);
            Log.i(TAG, "waitUntilOtherThreadIsDone() : workingApiCount is 0, so resume this thread.");
            return;
        }
        Log.i(TAG, "waitUntilOtherThreadIsDone() : This is main thread, so cannot wait!");
    }
}
