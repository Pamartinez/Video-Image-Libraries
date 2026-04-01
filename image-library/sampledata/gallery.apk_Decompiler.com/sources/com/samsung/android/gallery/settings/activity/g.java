package com.samsung.android.gallery.settings.activity;

import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SettingActivity f3110a;

    public /* synthetic */ g(SettingActivity settingActivity) {
        this.f3110a = settingActivity;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f3110a.lambda$initDateFormat$1(jobContext);
    }
}
