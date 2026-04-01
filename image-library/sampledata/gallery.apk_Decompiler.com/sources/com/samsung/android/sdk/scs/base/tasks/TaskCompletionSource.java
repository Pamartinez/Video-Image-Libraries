package com.samsung.android.sdk.scs.base.tasks;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TaskCompletionSource<TResult> {
    private final TaskImpl<TResult> task;

    public TaskCompletionSource() {
        this(new TaskImpl());
    }

    public Task<TResult> getTask() {
        return this.task;
    }

    public void setException(Exception exc) {
        this.task.setException(exc);
    }

    public void setResult(TResult tresult) {
        this.task.setResult(tresult);
    }

    public boolean trySetException(Exception exc) {
        return this.task.trySetException(exc);
    }

    public boolean trySetResult(TResult tresult) {
        return this.task.trySetResult(tresult);
    }

    public TaskCompletionSource(TaskImpl<TResult> taskImpl) {
        this.task = taskImpl;
    }
}
