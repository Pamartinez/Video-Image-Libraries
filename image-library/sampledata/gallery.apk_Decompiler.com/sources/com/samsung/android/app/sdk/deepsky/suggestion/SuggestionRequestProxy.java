package com.samsung.android.app.sdk.deepsky.suggestion;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.samsung.android.app.sdk.deepsky.common.Injector;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0016B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0013R\u0016\u0010\u0014\u001a\u00020\u00108\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequestProxy;", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequest;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "getSuggestionRequest", "()Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequest;", "Lcom/samsung/android/app/sdk/deepsky/suggestion/CapabilityEnum;", "capability", "Landroid/os/Bundle;", "extras", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionResponse;", "requestSuggestion", "(Lcom/samsung/android/app/sdk/deepsky/suggestion/CapabilityEnum;Landroid/os/Bundle;)Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionResponse;", "", "checkIfAccessAllowed", "()Z", "Landroid/content/Context;", "testMode", "Z", "SuggestionRequestFactory", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SuggestionRequestProxy implements SuggestionRequest {
    private final Context context;
    private boolean testMode;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequestProxy$SuggestionRequestFactory;", "", "<init>", "()V", "getSuggestionRequest", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequest;", "getDummySuggestionRequest", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SuggestionRequestFactory {
        public static final Companion Companion = new Companion((e) null);
        /* access modifiers changed from: private */
        public static SuggestionRequest sDefaultSuggestionRequest;
        /* access modifiers changed from: private */
        public static SuggestionRequest sDummyCalendarSuggestionRequest;
        /* access modifiers changed from: private */
        public static volatile SuggestionRequestFactory sInstance;

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequestProxy$SuggestionRequestFactory$Companion;", "", "<init>", "()V", "sInstance", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequestProxy$SuggestionRequestFactory;", "sDefaultSuggestionRequest", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequest;", "sDummyCalendarSuggestionRequest", "getInstance", "context", "Landroid/content/Context;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            public final SuggestionRequestFactory getInstance(Context context) {
                SuggestionRequestFactory access$getSInstance$cp;
                j.e(context, "context");
                SuggestionRequestFactory access$getSInstance$cp2 = SuggestionRequestFactory.sInstance;
                if (access$getSInstance$cp2 != null) {
                    return access$getSInstance$cp2;
                }
                synchronized (this) {
                    access$getSInstance$cp = SuggestionRequestFactory.sInstance;
                    if (access$getSInstance$cp == null) {
                        access$getSInstance$cp = new SuggestionRequestFactory();
                        Injector injector = Injector.INSTANCE;
                        SuggestionRequestFactory.sDefaultSuggestionRequest = new DefaultSuggestionRequest(context, injector.provideServiceCaller$deepsky_sdk_smartsuggestion_6_1_0_release(context), injector.shareSystemDatasource$deepsky_sdk_smartsuggestion_6_1_0_release(context));
                        SuggestionRequestFactory.sDummyCalendarSuggestionRequest = new DummySuggestionRequest(context);
                        SuggestionRequestFactory.sInstance = access$getSInstance$cp;
                    }
                }
                return access$getSInstance$cp;
            }

            private Companion() {
            }
        }

        public final SuggestionRequest getDummySuggestionRequest() {
            SuggestionRequest suggestionRequest = sDummyCalendarSuggestionRequest;
            if (suggestionRequest != null) {
                return suggestionRequest;
            }
            j.k("sDummyCalendarSuggestionRequest");
            throw null;
        }

        public final SuggestionRequest getSuggestionRequest() {
            SuggestionRequest suggestionRequest = sDefaultSuggestionRequest;
            if (suggestionRequest != null) {
                return suggestionRequest;
            }
            j.k("sDefaultSuggestionRequest");
            throw null;
        }
    }

    public SuggestionRequestProxy(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final SuggestionRequest getSuggestionRequest() {
        if (this.testMode) {
            return SuggestionRequestFactory.Companion.getInstance(this.context).getDummySuggestionRequest();
        }
        return SuggestionRequestFactory.Companion.getInstance(this.context).getSuggestionRequest();
    }

    public boolean checkIfAccessAllowed() {
        return true;
    }

    public SuggestionResponse requestSuggestion(CapabilityEnum capabilityEnum, Bundle bundle) {
        j.e(capabilityEnum, "capability");
        j.e(bundle, "extras");
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            return getSuggestionRequest().requestSuggestion(capabilityEnum, bundle);
        }
        throw new IllegalStateException("This should not be called on Main Thread");
    }
}
