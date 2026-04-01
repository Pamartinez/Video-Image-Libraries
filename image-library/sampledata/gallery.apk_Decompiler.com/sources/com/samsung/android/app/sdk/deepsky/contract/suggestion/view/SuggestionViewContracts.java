package com.samsung.android.app.sdk.deepsky.contract.suggestion.view;

import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewContracts;", "", "<init>", "()V", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SuggestionViewContracts {
    public static final Companion Companion = new Companion((e) null);
    private static final String REQUEST_DATA_KEY = "request_data";
    private static final String RESPONSE_DATA_KEY = "response_data";
    public static final int SERVICE_VERSION = 3;
    public static final String SERVICE_VERSION_KEY = "suggestion_view_service_version_v3";

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u0005J\u0010\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\u0005R\u000e\u0010\u000e\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewContracts$Companion;", "", "<init>", "()V", "createMessage", "Landroid/os/Message;", "what", "", "request", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewRequest;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewResponse;", "getRequestFromMsg", "msg", "getResponseFromMsg", "REQUEST_DATA_KEY", "", "RESPONSE_DATA_KEY", "SERVICE_VERSION_KEY", "SERVICE_VERSION", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final Message createMessage(int i2, SuggestionViewRequest suggestionViewRequest) {
            j.e(suggestionViewRequest, "request");
            Message obtain = Message.obtain((Handler) null, i2, 0, 0, (Object) null);
            obtain.getData().putParcelable(SuggestionViewContracts.REQUEST_DATA_KEY, suggestionViewRequest);
            return obtain;
        }

        public final SuggestionViewRequest getRequestFromMsg(Message message) {
            j.e(message, "msg");
            message.getData().setClassLoader(SuggestionViewRequest.class.getClassLoader());
            Parcelable parcelable = message.getData().getParcelable(SuggestionViewContracts.REQUEST_DATA_KEY);
            if (parcelable instanceof SuggestionViewRequest) {
                return (SuggestionViewRequest) parcelable;
            }
            return null;
        }

        public final SuggestionViewResponse getResponseFromMsg(Message message) {
            j.e(message, "msg");
            message.getData().setClassLoader(SuggestionViewResponse.class.getClassLoader());
            Parcelable parcelable = message.getData().getParcelable("response_data");
            if (parcelable instanceof SuggestionViewResponse) {
                return (SuggestionViewResponse) parcelable;
            }
            return null;
        }

        private Companion() {
        }

        public final Message createMessage(int i2, SuggestionViewResponse suggestionViewResponse) {
            j.e(suggestionViewResponse, "request");
            Message obtain = Message.obtain((Handler) null, i2, 0, 0, (Object) null);
            obtain.getData().putParcelable("response_data", suggestionViewResponse);
            return obtain;
        }
    }
}
