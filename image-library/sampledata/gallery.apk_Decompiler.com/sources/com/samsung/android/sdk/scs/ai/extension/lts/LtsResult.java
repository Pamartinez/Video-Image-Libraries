package com.samsung.android.sdk.scs.ai.extension.lts;

import com.samsung.android.sdk.scs.ai.language.Result;
import com.samsung.android.sdk.scs.base.tasks.Task;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LtsResult {
    private String content;
    private boolean isSuccess;
    private int progressCount;
    private int progressRate;
    private Task<Result> task;
    private int tokenCount;
    private int totalCount;

    public LtsResult(String str, boolean z) {
        this.progressCount = 0;
        this.totalCount = 0;
        this.progressRate = 0;
        this.tokenCount = 0;
        this.task = null;
        this.content = str;
        this.isSuccess = z;
    }

    public String getContent() {
        return this.content;
    }

    public int getProgressCount() {
        return this.progressCount;
    }

    public int getProgressRate() {
        return this.progressRate;
    }

    public Task<Result> getTask() {
        return this.task;
    }

    public int getTokenCount() {
        return this.tokenCount;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void increaseRepeatCount() {
        int i2;
        int i7 = this.progressCount + 1;
        this.progressCount = i7;
        int i8 = this.totalCount;
        if (i8 == 0) {
            i2 = 100;
        } else {
            i2 = (int) ((((float) i7) / ((float) i8)) * 100.0f);
        }
        this.progressRate = i2;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setProgressRate(int i2) {
        this.progressRate = i2;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }

    public void setTask(Task<Result> task2) {
        this.task = task2;
    }

    public void setTokenCount(int i2) {
        this.tokenCount = i2;
    }

    public void setTotalCount(int i2) {
        this.totalCount = i2;
    }

    public LtsResult(int i2, String str, boolean z) {
        this.progressCount = 0;
        this.progressRate = 0;
        this.tokenCount = 0;
        this.task = null;
        this.content = str;
        this.isSuccess = z;
        this.totalCount = i2;
    }
}
