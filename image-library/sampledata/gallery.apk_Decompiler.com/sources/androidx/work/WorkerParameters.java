package androidx.work;

import android.net.Network;
import android.net.Uri;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerParameters {
    private Executor mBackgroundExecutor;
    private ForegroundUpdater mForegroundUpdater;
    private int mGeneration;
    private UUID mId;
    private Data mInputData;
    private ProgressUpdater mProgressUpdater;
    private int mRunAttemptCount;
    private RuntimeExtras mRuntimeExtras;
    private Set<String> mTags;
    private TaskExecutor mWorkTaskExecutor;
    private C1232h mWorkerContext;
    private WorkerFactory mWorkerFactory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RuntimeExtras {
        public Network network;
        public List<String> triggeredContentAuthorities;
        public List<Uri> triggeredContentUris;

        public RuntimeExtras() {
            List<String> list = Collections.EMPTY_LIST;
            this.triggeredContentAuthorities = list;
            this.triggeredContentUris = list;
        }
    }

    public WorkerParameters(UUID uuid, Data data, Collection<String> collection, RuntimeExtras runtimeExtras, int i2, int i7, Executor executor, C1232h hVar, TaskExecutor taskExecutor, WorkerFactory workerFactory, ProgressUpdater progressUpdater, ForegroundUpdater foregroundUpdater) {
        this.mId = uuid;
        this.mInputData = data;
        this.mTags = new HashSet(collection);
        this.mRuntimeExtras = runtimeExtras;
        this.mRunAttemptCount = i2;
        this.mGeneration = i7;
        this.mBackgroundExecutor = executor;
        this.mWorkerContext = hVar;
        this.mWorkTaskExecutor = taskExecutor;
        this.mWorkerFactory = workerFactory;
        this.mProgressUpdater = progressUpdater;
        this.mForegroundUpdater = foregroundUpdater;
    }

    public Executor getBackgroundExecutor() {
        return this.mBackgroundExecutor;
    }

    public ForegroundUpdater getForegroundUpdater() {
        return this.mForegroundUpdater;
    }

    public UUID getId() {
        return this.mId;
    }

    public Data getInputData() {
        return this.mInputData;
    }
}
