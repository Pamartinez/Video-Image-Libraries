package com.samsung.android.sdk.scs.ai.language;

import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Summarizer e;
    public final /* synthetic */ AppInfo f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ SummarizeLevel f1659h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ SummarizeSubTask f1660i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Map f1661j;

    public /* synthetic */ b(Summarizer summarizer, AppInfo appInfo, String str, SummarizeLevel summarizeLevel, SummarizeSubTask summarizeSubTask, Map map, int i2) {
        this.d = i2;
        this.e = summarizer;
        this.f = appInfo;
        this.g = str;
        this.f1659h = summarizeLevel;
        this.f1660i = summarizeSubTask;
        this.f1661j = map;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$getTokenCount$2(this.f, this.g, this.f1659h, this.f1660i, this.f1661j, (LlmServiceObserver2) obj);
                return;
            default:
                this.e.lambda$summarize$0(this.f, this.g, this.f1659h, this.f1660i, this.f1661j, (LlmServiceObserver2) obj);
                return;
        }
    }
}
