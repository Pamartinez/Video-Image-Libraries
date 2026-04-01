package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ListenableWorker {
    private Context mAppContext;
    private final AtomicInteger mStopReason = new AtomicInteger(-256);
    private boolean mUsed;
    private WorkerParameters mWorkerParams;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Result {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Failure extends Result {
            private final Data mOutputData;

            public Failure() {
                this(Data.EMPTY);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || Failure.class != obj.getClass()) {
                    return false;
                }
                return this.mOutputData.equals(((Failure) obj).mOutputData);
            }

            public Data getOutputData() {
                return this.mOutputData;
            }

            public int hashCode() {
                return this.mOutputData.hashCode() + 846803280;
            }

            public String toString() {
                return "Failure {mOutputData=" + this.mOutputData + '}';
            }

            public Failure(Data data) {
                this.mOutputData = data;
            }
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Retry extends Result {
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || Retry.class != obj.getClass()) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return 25945934;
            }

            public String toString() {
                return "Retry";
            }
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Success extends Result {
            private final Data mOutputData;

            public Success() {
                this(Data.EMPTY);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || Success.class != obj.getClass()) {
                    return false;
                }
                return this.mOutputData.equals(((Success) obj).mOutputData);
            }

            public Data getOutputData() {
                return this.mOutputData;
            }

            public int hashCode() {
                return this.mOutputData.hashCode() - 1876823561;
            }

            public String toString() {
                return "Success {mOutputData=" + this.mOutputData + '}';
            }

            public Success(Data data) {
                this.mOutputData = data;
            }
        }

        public static Result failure() {
            return new Failure();
        }

        public static Result retry() {
            return new Retry();
        }

        public static Result success() {
            return new Success();
        }

        public static Result failure(Data data) {
            return new Failure(data);
        }

        public static Result success(Data data) {
            return new Success(data);
        }
    }

    public ListenableWorker(Context context, WorkerParameters workerParameters) {
        if (context == null) {
            throw new IllegalArgumentException("Application Context is null");
        } else if (workerParameters != null) {
            this.mAppContext = context;
            this.mWorkerParams = workerParameters;
        } else {
            throw new IllegalArgumentException("WorkerParameters is null");
        }
    }

    public final Context getApplicationContext() {
        return this.mAppContext;
    }

    public Executor getBackgroundExecutor() {
        return this.mWorkerParams.getBackgroundExecutor();
    }

    public abstract ListenableFuture getForegroundInfoAsync();

    public final UUID getId() {
        return this.mWorkerParams.getId();
    }

    public final Data getInputData() {
        return this.mWorkerParams.getInputData();
    }

    public final int getStopReason() {
        return this.mStopReason.get();
    }

    public final boolean isUsed() {
        return this.mUsed;
    }

    public final ListenableFuture setForegroundAsync(ForegroundInfo foregroundInfo) {
        return this.mWorkerParams.getForegroundUpdater().setForegroundAsync(getApplicationContext(), getId(), foregroundInfo);
    }

    public final void setUsed() {
        this.mUsed = true;
    }

    public abstract ListenableFuture startWork();

    public final void stop(int i2) {
        if (this.mStopReason.compareAndSet(-256, i2)) {
            onStopped();
        }
    }

    public void onStopped() {
    }
}
