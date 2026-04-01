package com.samsung.android.gallery.app.controller.ppp;

import A.a;
import Qb.c;
import Qb.e;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PppChecker {
    private Consumer<Integer> mConsumer;
    private final EventContext mHandler;
    /* access modifiers changed from: private */
    public volatile PppHandler mPppHandler;
    private final ArrayList<FileItemInterface> mPppList = new ArrayList<>();
    private volatile Looper mPppLooper;
    private Dialog mProgressDialog;
    private final MediaItem[] mSelectedItems;
    private long mStartTime = 0;
    private HandlerThread mThread = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PppHandler extends Handler {
        Consumer<Object> mCallback;
        final ArrayList<Long> mPppIdList = new ArrayList<>();
        final ArrayList<FileItemInterface> mPppItemList = new ArrayList<>();

        public PppHandler(Looper looper, ArrayList<FileItemInterface> arrayList, Consumer<Object> consumer) {
            super(looper);
            this.mCallback = consumer;
            Iterator<FileItemInterface> it = arrayList.iterator();
            while (it.hasNext()) {
                FileItemInterface next = it.next();
                this.mPppIdList.add(Long.valueOf(next.getFileId()));
                this.mPppItemList.add(next);
            }
        }

        private boolean checkPppCompleted() {
            Cursor rawQuery;
            String str = "select _id from files where sef_file_type=2928 and _id in " + CursorHelper.joinIds(this.mPppIdList);
            ArrayList arrayList = new ArrayList();
            try {
                rawQuery = new SecMpQueryExecutor().rawQuery(str, "PppChecker");
                if (rawQuery != null) {
                    if (rawQuery.moveToFirst()) {
                        do {
                            arrayList.add(Long.valueOf(rawQuery.getLong(0)));
                        } while (rawQuery.moveToNext());
                    }
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                Log.d("PppChecker", "remainList=" + arrayList.size());
                synchronized (this.mPppIdList) {
                    this.mPppIdList.clear();
                    this.mPppIdList.addAll(arrayList);
                }
                return this.mPppIdList.isEmpty();
            } catch (Exception e) {
                a.s(e, new StringBuilder("checkPppCompleted failed. e="), "PppChecker");
                return true;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        private void handleCompleted() {
            Consumer<Object> consumer = this.mCallback;
            if (consumer != null) {
                consumer.accept(Boolean.TRUE);
            }
        }

        private void handleNext() {
            if (checkPppCompleted()) {
                if (hasMessages(3)) {
                    removeMessages(3);
                }
                sendEmptyMessageDelayed(3, 1500);
                return;
            }
            if (hasMessages(1)) {
                removeMessages(1);
            }
            sendEmptyMessageDelayed(1, Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
        }

        private void handleStart() {
            GppmHelper.donate((Collection<FileItemInterface>) this.mPppItemList);
            sendEmptyMessageDelayed(1, 1000);
        }

        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                handleStart();
            } else if (i2 == 1) {
                handleNext();
            } else if (i2 == 3) {
                handleCompleted();
            }
        }
    }

    public PppChecker(EventContext eventContext, MediaItem[] mediaItemArr, Consumer<Integer> consumer) {
        this.mHandler = eventContext;
        this.mSelectedItems = mediaItemArr;
        this.mConsumer = consumer;
    }

    /* access modifiers changed from: private */
    public void finish() {
        finish(this.mPppList.size());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCompleted$0() {
        try {
            Dialog dialog = this.mProgressDialog;
            if (dialog != null && dialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
            this.mProgressDialog = null;
        } catch (IllegalArgumentException unused) {
        }
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialog$1(EventContext eventContext) {
        AlertDialog create = new ProgressCircleBuilder(eventContext.getContext()).setCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                Log.d("PppChecker", Contract.COMMAND_ID_CANCEL);
                if (PppChecker.this.mPppHandler.hasMessages(1)) {
                    PppChecker.this.mPppHandler.removeMessages(1);
                }
                PppChecker.this.finish();
            }
        }).create();
        this.mProgressDialog = create;
        create.show();
    }

    private void operatePpp() {
        this.mStartTime = System.currentTimeMillis();
        threadInit();
        processStart();
    }

    private void processStart() {
        this.mPppHandler.sendEmptyMessage(0);
    }

    private void showDialog(EventContext eventContext) {
        ThreadUtil.runOnUiThread(new Ob.a(9, this, eventContext));
    }

    private void threadInit() {
        HandlerThread handlerThread = new HandlerThread("PppChecker");
        this.mThread = handlerThread;
        handlerThread.start();
        this.mPppLooper = this.mThread.getLooper();
        this.mPppHandler = new PppHandler(this.mPppLooper, this.mPppList, new c(1, this));
    }

    private void threadRelease() {
        try {
            HandlerThread handlerThread = this.mThread;
            if (handlerThread != null) {
                handlerThread.quitSafely();
                this.mPppLooper.quit();
                this.mPppHandler = null;
                this.mThread = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCompleted(Object obj) {
        Log.d("PppChecker", "completed");
        ThreadUtil.runOnUiThread(new e(1, this));
    }

    public void start() {
        MediaItem[] mediaItemArr = this.mSelectedItems;
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e("PppChecker", "no selected items");
            finish(0);
            return;
        }
        int i2 = 0;
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem.isPostProcessing()) {
                this.mPppList.add(mediaItem);
            }
            i2++;
            if (i2 >= 5000) {
                break;
            }
        }
        Log.d("PppChecker", "ppp", Integer.valueOf(this.mSelectedItems.length), Integer.valueOf(this.mPppList.size()));
        if (this.mPppList.isEmpty()) {
            finish(0);
            return;
        }
        showDialog(this.mHandler);
        operatePpp();
    }

    private void finish(int i2) {
        Log.d("PppChecker", "finish");
        threadRelease();
        Consumer<Integer> consumer = this.mConsumer;
        if (consumer != null) {
            consumer.accept(Integer.valueOf(i2));
            this.mConsumer = null;
        }
    }
}
