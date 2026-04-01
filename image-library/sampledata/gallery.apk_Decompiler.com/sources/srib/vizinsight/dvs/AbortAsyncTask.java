package srib.vizinsight.dvs;

import android.os.AsyncTask;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AbortAsyncTask extends AsyncTask<String, Integer, Void> {
    private static final String TAG = "DVS_AbortAsyncTask";
    private AbortListener listener;
    private DVS segmenter;
    private boolean taskStatus = false;

    public AbortAsyncTask(DVS dvs, AbortListener abortListener) {
        this.segmenter = dvs;
        this.listener = abortListener;
    }

    public void onPreExecute() {
        Log.d(TAG, "onPreExecute");
        DVS dvs = this.segmenter;
        if (dvs != null && dvs.DVSTaskStatus()) {
            Log.d(TAG, "Cancelling task.");
            this.taskStatus = true;
            this.segmenter.DVSAbort();
        }
    }

    public Void doInBackground(String... strArr) {
        Log.d(TAG, "doInBackground");
        if (!this.taskStatus) {
            return null;
        }
        try {
            DVSegmenter.cancellationFinished.await();
        } catch (InterruptedException e) {
            Log.d(TAG, e.getMessage());
        }
        Log.d(TAG, "Done cancelling task.");
        return null;
    }

    public void onPostExecute(Void voidR) {
        Log.d(TAG, "onPostExecute");
        if (this.segmenter != null) {
            DVSegmenter.releaseSegmenter();
        }
        this.listener.onAbortCompleted();
    }
}
