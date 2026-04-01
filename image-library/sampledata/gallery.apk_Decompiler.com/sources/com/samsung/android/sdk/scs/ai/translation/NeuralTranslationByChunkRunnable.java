package com.samsung.android.sdk.scs.ai.translation;

import android.os.Bundle;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sivs.ai.sdkcommon.translation.a;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class NeuralTranslationByChunkRunnable extends TaskRunnable<Void> {
    private static final String CLASS_NAME = "NeuralTranslationByChunkRunnable";
    private static final String TAG = "ScsApi@NeuralTranslator";
    Map<String, String> authHeader;
    int chunkBaseLen;
    Bundle mBundle;
    List<a> mTextChunks;
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;
    NeuralTranslationRequest request;

    public NeuralTranslationByChunkRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2, NeuralTranslationRequest neuralTranslationRequest, AppInfo appInfo) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
        this.request = neuralTranslationRequest;
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.neuralTranslationServiceExecutor.context);
        this.chunkBaseLen = 500;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$0(NeuralTranslationResult neuralTranslationResult) {
        this.request.getOnSuccess().accept(neuralTranslationResult);
        this.request.deRegisterCallback();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$1() {
        this.request.getOnFailure().accept(NeuralTranslationErrorCode.UNKNOWN);
        this.request.deRegisterCallback();
    }

    private Bundle makeTranslateBundle(String str, String str2, String str3, String str4, String str5, boolean z, boolean z3, boolean z7, String str6, boolean z9, String str7, boolean z10, String str8, boolean z11, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("sourceText", str);
        bundle.putString("sourceLanguage", str2);
        bundle.putString("targetLanguage", str3);
        bundle.putString("id", str4);
        bundle.putString("tid", str5);
        bundle.putBoolean("verbose", z);
        bundle.putBoolean("appendMeta", z3);
        bundle.putBoolean("formality", z7);
        bundle.putString("mode", str6);
        bundle.putBoolean("forcePivot", z9);
        bundle.putString("requestingPackageName", str7);
        bundle.putBoolean("needSentenceSplit", z10);
        bundle.putString("requestingSourceId", str8);
        bundle.putBoolean("endOfContext", z11);
        bundle.putInt("contextCandidate", i2);
        for (Map.Entry next : this.authHeader.entrySet()) {
            bundle.putString((String) next.getKey(), (String) next.getValue());
        }
        return bundle;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x00e9 A[Catch:{ RemoteException -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x012b A[Catch:{ RemoteException -> 0x007f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() {
        /*
            r28 = this;
            r2 = r28
            java.lang.String r0 = "plain"
            java.lang.String r1 = "ScsApi@NeuralTranslator"
            java.lang.String r3 = "init chunk size: "
            java.lang.String r4 = "NeuralTranslationByChunkRunnable -- ["
            java.lang.String r5 = "splitTranslate is only for plain or contextual mode not "
            android.os.Handler r6 = new android.os.Handler
            android.os.Looper r7 = android.os.Looper.getMainLooper()
            r6.<init>(r7)
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationRequest r7 = r2.request     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationSource r7 = r7.getSource()     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r8 = r7.getSourceText()     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r9 = r7.getSourceLanguage()     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r10 = r7.getTargetLanguage()     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r11 = r7.getId()     // Catch:{ RemoteException -> 0x007f }
            java.util.UUID r12 = java.util.UUID.randomUUID()     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r12 = r12.toString()     // Catch:{ RemoteException -> 0x007f }
            java.lang.Boolean r13 = r7.getVerbose()     // Catch:{ RemoteException -> 0x007f }
            boolean r14 = r13.booleanValue()     // Catch:{ RemoteException -> 0x007f }
            java.lang.Boolean r15 = r7.getAppendMeta()     // Catch:{ RemoteException -> 0x007f }
            boolean r16 = r15.booleanValue()     // Catch:{ RemoteException -> 0x007f }
            r17 = r7
            java.lang.Boolean r7 = r17.getFormality()     // Catch:{ RemoteException -> 0x007f }
            boolean r18 = r7.booleanValue()     // Catch:{ RemoteException -> 0x007f }
            r19 = r14
            java.lang.String r14 = r17.getMode()     // Catch:{ RemoteException -> 0x007f }
            r20 = r17
            int r17 = r20.getContextCandidate()     // Catch:{ RemoteException -> 0x007f }
            r21 = r16
            boolean r16 = r20.getEndOfContext()     // Catch:{ RemoteException -> 0x007f }
            boolean r22 = r14.equalsIgnoreCase(r0)     // Catch:{ RemoteException -> 0x007f }
            if (r22 != 0) goto L_0x0084
            r22 = r0
            java.lang.String r0 = "contextual"
            boolean r0 = r14.equalsIgnoreCase(r0)     // Catch:{ RemoteException -> 0x007f }
            if (r0 != 0) goto L_0x0084
            java.lang.String r0 = r5.concat(r14)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.base.utils.Log.w(r1, r0)     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r0 = "mode changed to plain"
            com.samsung.android.sdk.scs.base.utils.Log.w(r1, r0)     // Catch:{ RemoteException -> 0x007f }
            r0 = r22
            goto L_0x0085
        L_0x007f:
            r0 = move-exception
            r27 = r6
            goto L_0x0160
        L_0x0084:
            r0 = r14
        L_0x0085:
            java.lang.Boolean r5 = r20.getForcePivot()     // Catch:{ RemoteException -> 0x007f }
            boolean r14 = r5.booleanValue()     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r22 = r20.getRequestingPackageName()     // Catch:{ RemoteException -> 0x007f }
            r23 = r14
            boolean r14 = r20.getSentenceSplit()     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r20 = r20.getRequestingSourceId()     // Catch:{ RemoteException -> 0x007f }
            r24 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x007f }
            r0.<init>(r4)     // Catch:{ RemoteException -> 0x007f }
            r0.append(r12)     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r4 = "] Translate requested"
            r0.append(r4)     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r0 = r0.toString()     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.base.utils.Log.i(r1, r0)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationByChunkRunnable$1 r0 = new com.samsung.android.sdk.scs.ai.translation.NeuralTranslationByChunkRunnable$1     // Catch:{ RemoteException -> 0x007f }
            r0.<init>(r12, r6)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationServiceExecutor r4 = r2.neuralTranslationServiceExecutor     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sivs.ai.sdkcommon.translation.h r4 = r4.getTranslationService()     // Catch:{ RemoteException -> 0x007f }
            r25 = r4
            int r4 = r2.chunkBaseLen     // Catch:{ RemoteException -> 0x007f }
            r26 = r12
            r12 = r25
            com.samsung.android.sivs.ai.sdkcommon.translation.f r12 = (com.samsung.android.sivs.ai.sdkcommon.translation.f) r12     // Catch:{ RemoteException -> 0x007f }
            java.util.ArrayList r4 = r12.a(r4, r8, r9)     // Catch:{ RemoteException -> 0x007f }
            r2.mTextChunks = r4     // Catch:{ RemoteException -> 0x007f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x007f }
            r4.<init>(r3)     // Catch:{ RemoteException -> 0x007f }
            java.util.List<com.samsung.android.sivs.ai.sdkcommon.translation.a> r3 = r2.mTextChunks     // Catch:{ RemoteException -> 0x007f }
            int r3 = r3.size()     // Catch:{ RemoteException -> 0x007f }
            r4.append(r3)     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r3 = r4.toString()     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.base.utils.Log.i(r1, r3)     // Catch:{ RemoteException -> 0x007f }
            java.util.List<com.samsung.android.sivs.ai.sdkcommon.translation.a> r3 = r2.mTextChunks     // Catch:{ RemoteException -> 0x007f }
            boolean r3 = r3.isEmpty()     // Catch:{ RemoteException -> 0x007f }
            if (r3 == 0) goto L_0x012b
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult.builder()     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.sourceLanguage(r9)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.targetLanguage(r10)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.sourceText(r8)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.targetText(r8)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.id(r11)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.verbose(r13)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.appendMeta(r15)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.formality(r7)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.forcePivot(r5)     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r3 = ""
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.interimResult(r3)     // Catch:{ RemoteException -> 0x007f }
            java.lang.Boolean r3 = java.lang.Boolean.TRUE     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult$TranslationResultBuilder r0 = r0.isComplete(r3)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult r0 = r0.build()     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sdk.scs.ai.translation.b r3 = new com.samsung.android.sdk.scs.ai.translation.b     // Catch:{ RemoteException -> 0x007f }
            r4 = 0
            r3.<init>(r4, r2, r0)     // Catch:{ RemoteException -> 0x007f }
            r6.post(r3)     // Catch:{ RemoteException -> 0x007f }
            return
        L_0x012b:
            java.util.List<com.samsung.android.sivs.ai.sdkcommon.translation.a> r3 = r2.mTextChunks     // Catch:{ RemoteException -> 0x007f }
            r4 = 0
            java.lang.Object r3 = r3.get(r4)     // Catch:{ RemoteException -> 0x007f }
            com.samsung.android.sivs.ai.sdkcommon.translation.a r3 = (com.samsung.android.sivs.ai.sdkcommon.translation.a) r3     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r3 = r3.d     // Catch:{ RemoteException -> 0x007f }
            r27 = r6
            r4 = r9
            r5 = r10
            r6 = r11
            r10 = r18
            r8 = r19
            r15 = r20
            r9 = r21
            r13 = r22
            r12 = r23
            r11 = r24
            r7 = r26
            android.os.Bundle r3 = r2.makeTranslateBundle(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ RemoteException -> 0x015f }
            r2.mBundle = r3     // Catch:{ RemoteException -> 0x015f }
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationServiceExecutor r3 = r2.neuralTranslationServiceExecutor     // Catch:{ RemoteException -> 0x015f }
            com.samsung.android.sivs.ai.sdkcommon.translation.h r3 = r3.getTranslationService()     // Catch:{ RemoteException -> 0x015f }
            android.os.Bundle r4 = r2.mBundle     // Catch:{ RemoteException -> 0x015f }
            com.samsung.android.sivs.ai.sdkcommon.translation.f r3 = (com.samsung.android.sivs.ai.sdkcommon.translation.f) r3     // Catch:{ RemoteException -> 0x015f }
            r3.l(r4, r0)     // Catch:{ RemoteException -> 0x015f }
            return
        L_0x015f:
            r0 = move-exception
        L_0x0160:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "NeuralTranslationByChunkRunnable -- Exception : "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.samsung.android.sdk.scs.base.utils.Log.e(r1, r3)
            r0.printStackTrace()
            com.samsung.android.sdk.scs.ai.translation.c r1 = new com.samsung.android.sdk.scs.ai.translation.c
            r3 = 0
            r1.<init>(r2, r3)
            r3 = r27
            r3.post(r1)
            com.samsung.android.sdk.scs.base.tasks.TaskCompletionSource<TResult> r1 = r2.mSource
            r1.setException(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.translation.NeuralTranslationByChunkRunnable.execute():void");
    }

    public String getFeatureName() {
        return Feature.FEATURE_NEURAL_TRANSLATION_BY_CHUNK;
    }

    public NeuralTranslationByChunkRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2, NeuralTranslationRequest neuralTranslationRequest, AppInfo appInfo, int i2) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
        this.request = neuralTranslationRequest;
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.neuralTranslationServiceExecutor.context);
        this.chunkBaseLen = i2;
    }
}
