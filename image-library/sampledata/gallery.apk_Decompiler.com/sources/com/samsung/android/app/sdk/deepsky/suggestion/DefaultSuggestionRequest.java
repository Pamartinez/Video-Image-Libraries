package com.samsung.android.app.sdk.deepsky.suggestion;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.samsung.android.app.sdk.deepsky.common.ContentProviderCaller;
import com.samsung.android.app.sdk.deepsky.common.SystemDataSource;
import com.samsung.android.app.sdk.deepsky.contract.DeepSkyMethod;
import com.samsung.android.app.sdk.deepsky.contract.suggestion.SuggestionItem;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/suggestion/DefaultSuggestionRequest;", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequest;", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;", "contentProviderCaller", "Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;", "systemDatasource", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;)V", "Landroid/os/Bundle;", "suggestionParam", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionResponseImpl;", "getSuggestionResponseImpl", "(Landroid/os/Bundle;)Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionResponseImpl;", "Lcom/samsung/android/app/sdk/deepsky/suggestion/CapabilityEnum;", "capability", "extras", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionResponse;", "requestSuggestion", "(Lcom/samsung/android/app/sdk/deepsky/suggestion/CapabilityEnum;Landroid/os/Bundle;)Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionResponse;", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;", "Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultSuggestionRequest implements SuggestionRequest {
    public static final Companion Companion = new Companion((e) null);
    private final ContentProviderCaller contentProviderCaller;
    private final Context context;
    private final SystemDataSource systemDatasource;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/suggestion/DefaultSuggestionRequest$Companion;", "", "<init>", "()V", "TAG", "", "GET_CAPABILITIES", "REQUEST_SUGGESTION", "SUBSCRIBE_SUGGESTION", "UNSUBSCRIBE_SUGGESTION", "IS_SUBSCRIBED", "GET_SUBSCRIPTION_ID_LIST", "KEY_SUBSCRIPTION_ID", "KEY_CAPABILITY_ID", "KEY_SUGGESTION_PARAMETER", "KEY_NOTIFICATION_INTENT", "KEY_CAPABILITY_RESULT", "KEY_SUGGESTION_RESULT", "KEY_ID", "KEY_NAME", "KEY_EXTRAS", "KEY_IS_SUPPORTED", "KEY_SDK_VERSION_NAME", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public DefaultSuggestionRequest(Context context2, ContentProviderCaller contentProviderCaller2, SystemDataSource systemDataSource) {
        j.e(context2, "context");
        j.e(contentProviderCaller2, "contentProviderCaller");
        j.e(systemDataSource, "systemDatasource");
        this.context = context2;
        this.contentProviderCaller = contentProviderCaller2;
        this.systemDatasource = systemDataSource;
    }

    private final SuggestionResponseImpl getSuggestionResponseImpl(Bundle bundle) {
        Bundle sendCommand = this.contentProviderCaller.sendCommand(DeepSkyMethod.REQUEST_SUGGESTION, bundle);
        if (sendCommand == null) {
            return null;
        }
        sendCommand.setClassLoader(SuggestionItem.class.getClassLoader());
        SuggestionItem suggestionItem = (SuggestionItem) sendCommand.getParcelable("key_suggestion_result");
        if (suggestionItem != null) {
            return new SuggestionResponseImpl(this.context, suggestionItem);
        }
        return null;
    }

    public SuggestionResponse requestSuggestion(CapabilityEnum capabilityEnum, Bundle bundle) {
        j.e(capabilityEnum, "capability");
        j.e(bundle, "extras");
        Log.i("SuggestionApi", "requestSuggestion " + capabilityEnum);
        Bundle newBundle = this.systemDatasource.newBundle();
        newBundle.putBundle("suggestion_parameter", bundle);
        newBundle.putInt("suggestion_capability_id", capabilityEnum.getId());
        return getSuggestionResponseImpl(newBundle);
    }
}
