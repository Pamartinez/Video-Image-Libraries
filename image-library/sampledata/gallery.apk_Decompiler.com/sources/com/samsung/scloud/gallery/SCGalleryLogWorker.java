package com.samsung.scloud.gallery;

import Sd.o;
import Sd.q;
import Sd.r;
import android.content.Context;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SCGalleryLogWorker extends Worker {
    public SCGalleryLogWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    public final ListenableWorker.Result doWork() {
        q qVar;
        try {
            Context applicationContext = getApplicationContext();
            try {
                File t = o.t(applicationContext);
                if (t == null) {
                    qVar = q.SUCCESS_NO_LOGS;
                } else {
                    qVar = o.u(applicationContext, t);
                }
            } catch (Exception e) {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "send: exception occurred", e);
                qVar = q.FAILURE;
            }
            int i2 = r.f3715a[qVar.ordinal()];
            if (i2 == 1) {
                return ListenableWorker.Result.success();
            }
            if (i2 == 2) {
                return ListenableWorker.Result.success();
            }
            if (i2 == 3) {
                return ListenableWorker.Result.failure();
            }
            throw new IncompatibleClassChangeError();
        } catch (Exception e7) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogWorker]", "doWork: exception occurred", e7);
            return ListenableWorker.Result.failure();
        }
    }
}
