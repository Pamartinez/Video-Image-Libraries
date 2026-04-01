package com.samsung.android.sdk.scs.ai.extension.lts;

import N2.j;
import android.content.Context;
import android.util.Log;
import bc.C0584a;
import bc.d;
import com.samsung.android.sdk.scs.ai.AiServices;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.Result;
import com.samsung.android.sdk.scs.ai.language.SummarizeLevel;
import com.samsung.android.sdk.scs.ai.language.SummarizeSubTask;
import com.samsung.android.sdk.scs.ai.language.Summarizer;
import com.samsung.android.sdk.scs.base.tasks.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongTextSummarizer implements ILongTextSummarizer {
    private static final int SEPARATE_N = 4;
    private final int[] DEPTH_LEVEL;
    private final String TAG;
    private final AppInfo.Builder appinfoBuilder;
    private OnCompleteListener<LtsResult> clientCallback;
    CompressionSummary compressionSummary;
    Context context;
    private int depthLevel;
    private boolean isCanceled;
    AtomicBoolean isProcessing;
    private Iterator<String> iterator;
    LtsResult ltsResult;
    private int mMaxTokenCount;
    private final Summarizer mSummarizer;
    Boolean needToaddContent;
    AtomicBoolean run;
    List<String> separatedTextList;
    Thread thread;

    public LongTextSummarizer(Context context2, Summarizer summarizer) {
        this.TAG = "LongTextSummarizer";
        this.isProcessing = new AtomicBoolean(false);
        this.run = new AtomicBoolean(false);
        this.DEPTH_LEVEL = new int[]{1, 2, 3};
        this.depthLevel = 1;
        this.needToaddContent = Boolean.FALSE;
        this.appinfoBuilder = new AppInfo.Builder();
        this.mMaxTokenCount = 1024;
        this.isCanceled = false;
        this.mSummarizer = summarizer;
        this.context = context2;
    }

    /* access modifiers changed from: private */
    /* renamed from: callback */
    public void lambda$summarize$4(Task<Result> task, CompletableFuture<Integer> completableFuture) {
        int i2;
        int i7;
        if (!this.isProcessing.get()) {
            Log.d("LongTextSummarizer", "processing is stopped. do not call callback method.");
        } else if (task.isSuccessful()) {
            String content = task.getResult().getContent();
            int addSentence = this.compressionSummary.addSentence(content);
            if (!this.needToaddContent.booleanValue()) {
                int i8 = this.depthLevel;
                if (i8 == 0 || i8 == 1) {
                    if (this.compressionSummary.getDividedNQueue().element().size() <= 1 && ((Integer) this.compressionSummary.getDividedNQueue().element().element()).intValue() == 1) {
                        this.needToaddContent = Boolean.TRUE;
                    }
                } else if (i8 != 2) {
                    if (this.compressionSummary.getDividedNQueue().element().size() <= 4) {
                        this.needToaddContent = Boolean.TRUE;
                    }
                } else if (this.compressionSummary.getDividedNQueue().element().size() <= 1) {
                    this.needToaddContent = Boolean.TRUE;
                }
            }
            if (this.needToaddContent.booleanValue()) {
                this.ltsResult.setContent(content);
            }
            this.ltsResult.increaseRepeatCount();
            handleCallback(this.ltsResult, false);
            if (!this.iterator.hasNext()) {
                int i10 = this.depthLevel;
                int[] iArr = this.DEPTH_LEVEL;
                int i11 = iArr[0];
                if (!(i10 == i11 && addSentence == i11) && ((i10 != (i2 = iArr[1]) || addSentence > i2) && (i10 != (i7 = iArr[2]) || addSentence > i7))) {
                    this.iterator = this.compressionSummary.getChunkedSummarySentenceList().iterator();
                } else {
                    completableFuture.complete(0);
                    Log.i("LongTextSummarizer", "summary : " + this.compressionSummary.getSummary());
                    handleCallback((LtsResult) null, false);
                    return;
                }
            }
            completableFuture.complete(1);
        } else {
            Exception exception = task.getException();
            if (exception != null) {
                handleCallback(new LtsResult(getErrorMessage(exception), false), true);
                j.D(exception, new StringBuilder("failed: "), "LongTextSummarizer");
            } else {
                handleCallback(new LtsResult("Unknown error occurred.", false), true);
                Log.e("LongTextSummarizer", "failed: Unknown error occurred.");
            }
            completableFuture.complete(-1);
        }
    }

    private static String getErrorMessage(Exception exc) {
        if (exc == null || exc.getMessage() == null) {
            return "unknown error";
        }
        return exc.getMessage();
    }

    private void handleCallback(LtsResult ltsResult2, boolean z) {
        if (z) {
            if (this.isCanceled) {
                this.isCanceled = false;
                ltsResult2.setContent("Client Error Cancel");
            }
            this.clientCallback.onError(ltsResult2);
            this.isProcessing.set(false);
        } else if (ltsResult2 == null) {
            this.clientCallback.onComplete();
            this.isProcessing.set(false);
        } else {
            this.clientCallback.onNext(ltsResult2);
        }
    }

    private void initialize(Context context2, String str) {
        this.context = context2;
        this.separatedTextList = new ArrayList();
        this.compressionSummary = new CompressionSummary(4);
        TextManager.getChunkSenteneList(str, this.separatedTextList);
        this.iterator = this.separatedTextList.iterator();
        this.compressionSummary.divideAndCeil(this.separatedTextList.size());
        if (this.depthLevel > this.compressionSummary.getDividedCount().size()) {
            this.depthLevel = this.compressionSummary.getDividedCount().size();
        }
        this.compressionSummary.balanceGroup();
        int i2 = 0;
        for (int i7 = 0; i7 < this.compressionSummary.getDividedCount().size() - this.depthLevel; i7++) {
            i2 += this.compressionSummary.getDividedCount().get(i7).intValue();
        }
        this.ltsResult = new LtsResult(i2, "", true);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x01af  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01a2 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$process$2(java.lang.String r21, java.util.concurrent.atomic.AtomicReference r22) {
        /*
            r20 = this;
            r1 = r20
            java.lang.String r2 = "Summarize Exception : "
            java.lang.String r3 = "Token Calculation Error"
            java.lang.String r4 = "LongTextSummarizer"
            java.util.concurrent.CompletableFuture r0 = new java.util.concurrent.CompletableFuture
            r0.<init>()
            com.samsung.android.sdk.scs.ai.language.AppInfo$Builder r5 = new com.samsung.android.sdk.scs.ai.language.AppInfo$Builder
            r5.<init>()
            com.samsung.android.sdk.scs.ai.language.AppInfo$RequestType r6 = com.samsung.android.sdk.scs.ai.language.AppInfo.RequestType.ONDEVICE
            com.samsung.android.sdk.scs.ai.language.AppInfo$Builder r5 = r5.setRequestType(r6)
            com.samsung.android.sdk.scs.ai.language.AppInfo r7 = r5.build()
            com.samsung.android.sdk.scs.ai.language.Summarizer r6 = r1.mSummarizer
            com.samsung.android.sdk.scs.ai.language.SummarizeLevel r9 = com.samsung.android.sdk.scs.ai.language.SummarizeLevel.BRIEFLY
            com.samsung.android.sdk.scs.ai.language.SummarizeSubTask r10 = com.samsung.android.sdk.scs.ai.language.SummarizeSubTask.ARTICLE
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            r8 = r21
            com.samsung.android.sdk.scs.base.tasks.Task r5 = r6.getTokenCount(r7, r8, r9, r10, r11)
            com.samsung.android.sdk.scs.ai.extension.lts.b r6 = new com.samsung.android.sdk.scs.ai.extension.lts.b
            r8 = 0
            r6.<init>(r1, r0, r8)
            r5.addOnCompleteListener(r6)
            r5 = 0
            r12 = 1
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x0071 }
            r13 = 120(0x78, double:5.93E-322)
            java.lang.Object r0 = r0.get(r13, r6)     // Catch:{ Exception -> 0x0071 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x0071 }
            r15 = r22
            r15.set(r0)     // Catch:{ Exception -> 0x0071 }
            java.lang.Object r0 = r15.get()     // Catch:{ Exception -> 0x0071 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x0071 }
            int r0 = r0.intValue()     // Catch:{ Exception -> 0x0071 }
            r6 = 4096(0x1000, float:5.74E-42)
            if (r0 <= r6) goto L_0x0074
            com.samsung.android.sdk.scs.ai.extension.lts.LtsResult r0 = r1.ltsResult     // Catch:{ Exception -> 0x0071 }
            java.lang.String r6 = "Token Max Exceeded Error"
            r0.setContent(r6)     // Catch:{ Exception -> 0x0071 }
            com.samsung.android.sdk.scs.ai.extension.lts.LtsResult r0 = r1.ltsResult     // Catch:{ Exception -> 0x0071 }
            java.lang.Object r6 = r15.get()     // Catch:{ Exception -> 0x0071 }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ Exception -> 0x0071 }
            int r6 = r6.intValue()     // Catch:{ Exception -> 0x0071 }
            r0.setTokenCount(r6)     // Catch:{ Exception -> 0x0071 }
            com.samsung.android.sdk.scs.ai.extension.lts.LtsResult r0 = r1.ltsResult     // Catch:{ Exception -> 0x0071 }
            r1.handleCallback(r0, r12)     // Catch:{ Exception -> 0x0071 }
            return
        L_0x0071:
            r0 = move-exception
            goto L_0x0220
        L_0x0074:
            java.lang.Object r0 = r15.get()     // Catch:{ Exception -> 0x0071 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x0071 }
            int r0 = r0.intValue()     // Catch:{ Exception -> 0x0071 }
            if (r0 > 0) goto L_0x0089
            com.samsung.android.sdk.scs.ai.extension.lts.LtsResult r0 = new com.samsung.android.sdk.scs.ai.extension.lts.LtsResult     // Catch:{ Exception -> 0x0071 }
            r0.<init>(r3, r5)     // Catch:{ Exception -> 0x0071 }
            r1.handleCallback(r0, r12)     // Catch:{ Exception -> 0x0071 }
            return
        L_0x0089:
            java.lang.Object r0 = r15.get()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            if (r0 <= 0) goto L_0x021f
            java.lang.Object r0 = r15.get()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            int r0 = r0 + -100
            double r8 = (double) r0
            int r0 = r1.mMaxTokenCount
            int r0 = r0 + -100
            double r10 = (double) r0
            double r8 = r8 / r10
            double r8 = java.lang.Math.ceil(r8)
            int r0 = (int) r8
            r6 = 3
            r8 = r12
        L_0x00af:
            int r16 = r6 + -1
            if (r6 == 0) goto L_0x01ad
            if (r8 == 0) goto L_0x01ad
            int r6 = r21.length()
            double r8 = (double) r6
            double r10 = (double) r0
            double r8 = r8 / r10
            double r8 = java.lang.Math.ceil(r8)
            int r6 = (int) r8
            java.lang.Object r8 = r15.get()
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            double r8 = (double) r8
            double r8 = r8 / r10
            double r8 = java.lang.Math.ceil(r8)
            int r8 = (int) r8
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "text size :"
            r9.<init>(r10)
            int r10 = r21.length()
            r9.append(r10)
            java.lang.String r10 = " needToSeparatedCount "
            r9.append(r10)
            r9.append(r0)
            java.lang.String r10 = " before maxSeparatedTextSize : "
            r9.append(r10)
            r9.append(r6)
            java.lang.String r9 = r9.toString()
            android.util.Log.i(r4, r9)
            int r9 = r1.mMaxTokenCount
            int r10 = r9 - r8
            r17 = r12
            double r12 = (double) r10
            r18 = r12
            double r11 = (double) r9
            double r12 = r18 / r11
            double r14 = (double) r6
            double r14 = r14 * r12
            r18 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r18 = r18 - r12
            double r14 = r14 / r18
            int r9 = (int) r14
            java.lang.String r11 = " marginTokenCount : "
            java.lang.String r14 = " marginTokenRate : "
            java.lang.String r15 = "estimatedAverageTokenCount : "
            java.lang.StringBuilder r8 = A.a.h(r8, r10, r15, r11, r14)
            r8.append(r12)
            java.lang.String r10 = " marginTextLength : "
            r8.append(r10)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.util.Log.i(r4, r8)
            int r0 = r0 + 1
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r1.separatedTextList = r8
            com.samsung.android.sdk.scs.ai.extension.lts.CompressionSummary r8 = new com.samsung.android.sdk.scs.ai.extension.lts.CompressionSummary
            r10 = 4
            r8.<init>(r10)
            r1.compressionSummary = r8
            java.util.List<java.lang.String> r8 = r1.separatedTextList
            r12 = r21
            com.samsung.android.sdk.scs.ai.extension.lts.TextManager.getChunkSenteneList(r12, r8, r6, r9)
            java.util.List<java.lang.String> r6 = r1.separatedTextList
            java.util.Iterator r13 = r6.iterator()
        L_0x0147:
            boolean r6 = r13.hasNext()
            if (r6 == 0) goto L_0x01a2
            java.lang.Object r6 = r13.next()
            r8 = r6
            java.lang.String r8 = (java.lang.String) r8
            java.util.concurrent.CompletableFuture r14 = new java.util.concurrent.CompletableFuture
            r14.<init>()
            com.samsung.android.sdk.scs.ai.language.Summarizer r6 = r1.mSummarizer
            com.samsung.android.sdk.scs.ai.language.SummarizeLevel r9 = com.samsung.android.sdk.scs.ai.language.SummarizeLevel.BRIEFLY
            com.samsung.android.sdk.scs.ai.language.SummarizeSubTask r10 = com.samsung.android.sdk.scs.ai.language.SummarizeSubTask.ARTICLE
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            com.samsung.android.sdk.scs.base.tasks.Task r6 = r6.getTokenCount(r7, r8, r9, r10, r11)
            com.samsung.android.sdk.scs.ai.extension.lts.b r8 = new com.samsung.android.sdk.scs.ai.extension.lts.b
            r9 = 1
            r8.<init>(r1, r14, r9)
            r6.addOnCompleteListener(r8)
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x018a }
            r9 = 120(0x78, double:5.93E-322)
            java.lang.Object r6 = r14.get(r9, r6)     // Catch:{ Exception -> 0x018a }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ Exception -> 0x018a }
            int r8 = r1.mMaxTokenCount     // Catch:{ Exception -> 0x018a }
            int r11 = r6.intValue()     // Catch:{ Exception -> 0x018a }
            if (r8 < r11) goto L_0x018c
            int r6 = r6.intValue()     // Catch:{ Exception -> 0x018a }
            if (r6 > 0) goto L_0x0147
            goto L_0x018c
        L_0x018a:
            r0 = move-exception
            goto L_0x018f
        L_0x018c:
            r8 = r17
            goto L_0x01a5
        L_0x018f:
            com.samsung.android.sdk.scs.ai.extension.lts.LtsResult r6 = new com.samsung.android.sdk.scs.ai.extension.lts.LtsResult
            r6.<init>(r3, r5)
            r3 = r17
            r1.handleCallback(r6, r3)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            N2.j.D(r0, r1, r4)
            return
        L_0x01a2:
            r9 = 120(0x78, double:5.93E-322)
            r8 = r5
        L_0x01a5:
            r15 = r22
            r13 = r9
            r6 = r16
            r12 = 1
            goto L_0x00af
        L_0x01ad:
            if (r8 != 0) goto L_0x021f
            com.samsung.android.sdk.scs.ai.extension.lts.CompressionSummary r0 = r1.compressionSummary
            java.util.List<java.lang.String> r2 = r1.separatedTextList
            int r2 = r2.size()
            r0.divideAndCeil(r2)
            int r0 = r1.depthLevel
            com.samsung.android.sdk.scs.ai.extension.lts.CompressionSummary r2 = r1.compressionSummary
            java.util.List r2 = r2.getDividedCount()
            int r2 = r2.size()
            if (r0 <= r2) goto L_0x01d4
            com.samsung.android.sdk.scs.ai.extension.lts.CompressionSummary r0 = r1.compressionSummary
            java.util.List r0 = r0.getDividedCount()
            int r0 = r0.size()
            r1.depthLevel = r0
        L_0x01d4:
            com.samsung.android.sdk.scs.ai.extension.lts.CompressionSummary r0 = r1.compressionSummary
            r0.balanceGroup()
            r0 = r5
        L_0x01da:
            com.samsung.android.sdk.scs.ai.extension.lts.CompressionSummary r2 = r1.compressionSummary
            java.util.List r2 = r2.getDividedCount()
            int r2 = r2.size()
            int r3 = r1.depthLevel
            int r2 = r2 - r3
            if (r5 >= r2) goto L_0x01fd
            com.samsung.android.sdk.scs.ai.extension.lts.CompressionSummary r2 = r1.compressionSummary
            java.util.List r2 = r2.getDividedCount()
            java.lang.Object r2 = r2.get(r5)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            int r0 = r0 + r2
            int r5 = r5 + 1
            goto L_0x01da
        L_0x01fd:
            com.samsung.android.sdk.scs.ai.extension.lts.LtsResult r2 = new com.samsung.android.sdk.scs.ai.extension.lts.LtsResult
            java.lang.String r3 = ""
            r4 = 1
            r2.<init>(r0, r3, r4)
            r1.ltsResult = r2
            java.lang.Object r0 = r22.get()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r2.setTokenCount(r0)
            java.util.List<java.lang.String> r0 = r1.separatedTextList
            java.util.Iterator r0 = r0.iterator()
            r1.iterator = r0
            r1.process()
        L_0x021f:
            return
        L_0x0220:
            com.samsung.android.sdk.scs.ai.extension.lts.LtsResult r6 = new com.samsung.android.sdk.scs.ai.extension.lts.LtsResult
            r6.<init>(r3, r5)
            r3 = 1
            r1.handleCallback(r6, r3)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            N2.j.D(r0, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.extension.lts.LongTextSummarizer.lambda$process$2(java.lang.String, java.util.concurrent.atomic.AtomicReference):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$request$3() {
        while (this.run.get() && this.iterator.hasNext()) {
            summarize();
        }
        this.isProcessing.set(false);
        this.run.set(false);
    }

    private Boolean postProcess() {
        if (!this.isProcessing.compareAndSet(false, true) || this.depthLevel == this.DEPTH_LEVEL[0]) {
            Log.e("LongTextSummarizer", "isProcessing : " + this.isProcessing.get() + " depthLevel : " + this.depthLevel);
            return Boolean.FALSE;
        }
        this.iterator = this.compressionSummary.getChunkedSummarySentenceList().iterator();
        this.depthLevel = this.DEPTH_LEVEL[0];
        request();
        return Boolean.TRUE;
    }

    private void release() {
        Log.i("LongTextSummarizer", "release");
        this.isProcessing.set(false);
        this.run.set(false);
        Thread thread2 = this.thread;
        if (thread2 != null && thread2.isAlive()) {
            this.thread.interrupt();
        }
    }

    private void request() {
        this.thread = new Thread(new C0584a(22, this));
        this.run.set(true);
        this.thread.start();
    }

    private Boolean summarize() {
        Log.i("LongTextSummarizer", "summarize");
        AppInfo build = this.appinfoBuilder.setStreamingMode(true).setRequestType(AppInfo.RequestType.ONDEVICE).build();
        CompletableFuture completableFuture = new CompletableFuture();
        this.mSummarizer.summarize(build, this.iterator.next(), SummarizeLevel.BRIEFLY, SummarizeSubTask.ARTICLE, new HashMap()).addOnCompleteListener(new b(this, completableFuture, 2));
        try {
            completableFuture.get(120, TimeUnit.SECONDS);
        } catch (Exception e) {
            handleCallback(new LtsResult(getErrorMessage(e), false), true);
            j.D(e, new StringBuilder("Summarize Exception : "), "LongTextSummarizer");
            release();
        }
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    /* renamed from: tokenCallback */
    public void lambda$process$0(Task<Result> task, CompletableFuture<Integer> completableFuture) {
        if (!task.isSuccessful() || task.getResult() == null) {
            completableFuture.complete(-1);
            Exception exception = task.getException();
            LtsResult ltsResult2 = new LtsResult(getErrorMessage(exception), false);
            ltsResult2.setTask(task);
            handleCallback(ltsResult2, true);
            j.D(exception, new StringBuilder("failed: "), "LongTextSummarizer");
            return;
        }
        String content = task.getResult().getContent();
        Log.d("LongTextSummarizer", "content: " + content);
        Map<String, String> token = TokenParser.getToken(content);
        try {
            int parseInt = Integer.parseInt(token.get("count"));
            int parseInt2 = Integer.parseInt(token.get("countMax"));
            if (parseInt2 <= 0) {
                parseInt2 = 1024;
            }
            this.mMaxTokenCount = parseInt2;
            Log.i("LongTextSummarizer", "tokenCount : " + parseInt + " maxCount : " + this.mMaxTokenCount);
            completableFuture.complete(Integer.valueOf(parseInt));
        } catch (Exception unused) {
            handleCallback(new LtsResult("Token Calculation Error", false), true);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: tokenSeparateTextCallback */
    public void lambda$process$1(Task<Result> task, CompletableFuture<Integer> completableFuture) {
        if (!task.isSuccessful() || task.getResult() == null) {
            completableFuture.complete(-1);
            LtsResult ltsResult2 = new LtsResult(getErrorMessage(task.getException()), false);
            ltsResult2.setTask(task);
            handleCallback(ltsResult2, true);
            return;
        }
        String content = task.getResult().getContent();
        Log.d("LongTextSummarizer", "content: " + content);
        try {
            int parseInt = Integer.parseInt(TokenParser.getToken(content).get("count"));
            Log.i("LongTextSummarizer", "tokenCount : " + parseInt);
            completableFuture.complete(Integer.valueOf(parseInt));
        } catch (Exception unused) {
            completableFuture.complete(-1);
        }
    }

    public void addOnCompleteListener(OnCompleteListener<LtsResult> onCompleteListener) {
        this.clientCallback = onCompleteListener;
    }

    public boolean process(String str) {
        if (this.isProcessing.compareAndSet(false, true)) {
            this.isCanceled = false;
            AtomicReference atomicReference = new AtomicReference(0);
            this.ltsResult = new LtsResult(0, "", false);
            this.needToaddContent = Boolean.FALSE;
            new Thread(new d((Object) this, (Object) str, (Object) atomicReference, 9)).start();
            return true;
        }
        Log.e("LongTextSummarizer", "Do not use this object more than once. Please recreate the instance.");
        return false;
    }

    public void stop() {
        if (this.isProcessing.get()) {
            this.isCanceled = true;
        }
        release();
    }

    private Boolean process() {
        if (!this.iterator.hasNext()) {
            Log.e("LongTextSummarizer", "separatedTextList size :" + this.separatedTextList.size());
            return Boolean.FALSE;
        }
        request();
        return Boolean.TRUE;
    }

    private LongTextSummarizer(Context context2, String str) {
        this.TAG = "LongTextSummarizer";
        this.isProcessing = new AtomicBoolean(false);
        this.run = new AtomicBoolean(false);
        this.DEPTH_LEVEL = new int[]{1, 2, 3};
        this.depthLevel = 1;
        this.needToaddContent = Boolean.FALSE;
        this.appinfoBuilder = new AppInfo.Builder();
        this.mMaxTokenCount = 1024;
        this.isCanceled = false;
        this.mSummarizer = AiServices.getSummarizer(context2);
        initialize(context2, str);
    }

    private LongTextSummarizer(Context context2, String str, int i2) {
        this.TAG = "LongTextSummarizer";
        this.isProcessing = new AtomicBoolean(false);
        this.run = new AtomicBoolean(false);
        int[] iArr = {1, 2, 3};
        this.DEPTH_LEVEL = iArr;
        this.depthLevel = 1;
        this.needToaddContent = Boolean.FALSE;
        this.appinfoBuilder = new AppInfo.Builder();
        this.mMaxTokenCount = 1024;
        this.isCanceled = false;
        this.mSummarizer = AiServices.getSummarizer(context2);
        if (i2 == 0 || i2 == 1) {
            this.depthLevel = iArr[0];
        } else if (i2 != 2) {
            this.depthLevel = iArr[1];
        } else {
            this.depthLevel = iArr[1];
        }
        initialize(context2, str);
    }
}
