package com.samsung.android.gallery.module.publisher;

import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3042a;
    public final /* synthetic */ SearchDataBasePublisher b;

    public /* synthetic */ C(SearchDataBasePublisher searchDataBasePublisher, int i2) {
        this.f3042a = i2;
        this.b = searchDataBasePublisher;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        int i2 = this.f3042a;
        SearchDataBasePublisher searchDataBasePublisher = this.b;
        switch (i2) {
            case 0:
                return searchDataBasePublisher.lambda$new$0(jobContext);
            case 1:
                return searchDataBasePublisher.lambda$new$20("location://search/fileList/Category/Stories", jobContext);
            case 2:
                return searchDataBasePublisher.lambda$new$1(jobContext);
            case 3:
                return searchDataBasePublisher.lambda$new$2(jobContext);
            case 4:
                return searchDataBasePublisher.lambda$new$3(jobContext);
            case 5:
                return searchDataBasePublisher.lambda$new$4(jobContext);
            case 6:
                return searchDataBasePublisher.lambda$new$5(jobContext);
            case 7:
                return searchDataBasePublisher.lambda$new$6(jobContext);
            case 8:
                return searchDataBasePublisher.lambda$new$7(jobContext);
            case 9:
                return searchDataBasePublisher.lambda$new$8(jobContext);
            case 10:
                return searchDataBasePublisher.lambda$new$9(jobContext);
            case 11:
                return searchDataBasePublisher.lambda$new$10(jobContext);
            case 12:
                return searchDataBasePublisher.lambda$new$11(jobContext);
            case 13:
                return searchDataBasePublisher.lambda$new$12(jobContext);
            case 14:
                return searchDataBasePublisher.lambda$new$13(jobContext);
            case 15:
                return searchDataBasePublisher.lambda$new$14(jobContext);
            case 16:
                return searchDataBasePublisher.lambda$new$15(jobContext);
            case 17:
                return searchDataBasePublisher.lambda$new$17(jobContext);
            default:
                return searchDataBasePublisher.lambda$new$18(jobContext);
        }
    }
}
