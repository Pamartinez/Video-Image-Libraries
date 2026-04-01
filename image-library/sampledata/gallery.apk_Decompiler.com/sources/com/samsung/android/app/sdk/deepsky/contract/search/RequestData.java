package com.samsung.android.app.sdk.deepsky.contract.search;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001c\u001dB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000bR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000bR\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/RequestData;", "", "bundle", "Landroid/os/Bundle;", "<init>", "(Landroid/os/Bundle;)V", "getBundle", "()Landroid/os/Bundle;", "query", "", "getQuery", "()Ljava/lang/String;", "promise", "Lcom/samsung/android/app/sdk/deepsky/contract/search/Promise;", "getPromise", "()Lcom/samsung/android/app/sdk/deepsky/contract/search/Promise;", "promiseId", "", "getPromiseId", "()Ljava/lang/Integer;", "variable", "getVariable", "headers", "getHeaders", "command", "Lcom/samsung/android/app/sdk/deepsky/contract/search/Command;", "getCommand", "()Lcom/samsung/android/app/sdk/deepsky/contract/search/Command;", "Companion", "Builder", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RequestData {
    public static final Companion Companion = new Companion((e) null);
    private final Bundle bundle;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\u000e\u001a\u00020\u0000J\u0006\u0010\u000f\u001a\u00020\u0000J\u0006\u0010\u0010\u001a\u00020\u0011R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/RequestData$Builder;", "", "<init>", "()V", "query", "", "variable", "headers", "promise", "command", "setQuery", "setVariable", "setHeaders", "setPromise", "setCancelCommand", "setStateCommand", "build", "Lcom/samsung/android/app/sdk/deepsky/contract/search/RequestData;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String command;
        private String headers;
        private String promise;
        private String query;
        private String variable;

        public final RequestData build() {
            Bundle bundle = new Bundle();
            String str = this.query;
            if (str != null) {
                bundle.putString(Contract.QUERY, str);
            }
            String str2 = this.variable;
            if (str2 != null) {
                bundle.putString(Contract.VARIABLE, str2);
            }
            String str3 = this.headers;
            if (str3 != null) {
                bundle.putString(Contract.HEADERS, str3);
            }
            String str4 = this.promise;
            if (str4 != null) {
                bundle.putString(Contract.PROMISE, str4);
            }
            String str5 = this.command;
            if (str5 != null) {
                bundle.putString(Contract.COMMAND, str5);
            }
            return new RequestData(bundle);
        }

        public final Builder setCancelCommand() {
            this.command = Contract.COMMAND_ID_CANCEL;
            return this;
        }

        public final Builder setHeaders(String str) {
            j.e(str, Contract.HEADERS);
            this.headers = str;
            return this;
        }

        public final Builder setPromise(String str) {
            j.e(str, Contract.PROMISE);
            this.promise = str;
            return this;
        }

        public final Builder setQuery(String str) {
            j.e(str, Contract.QUERY);
            this.query = str;
            return this;
        }

        public final Builder setStateCommand() {
            this.command = "state";
            return this;
        }

        public final Builder setVariable(String str) {
            j.e(str, Contract.VARIABLE);
            this.variable = str;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/RequestData$Companion;", "", "<init>", "()V", "from", "Lcom/samsung/android/app/sdk/deepsky/contract/search/RequestData;", "bundle", "Landroid/os/Bundle;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final RequestData from(Bundle bundle) {
            j.e(bundle, "bundle");
            return new RequestData(bundle);
        }

        private Companion() {
        }
    }

    public RequestData(Bundle bundle2) {
        j.e(bundle2, "bundle");
        this.bundle = bundle2;
    }

    public final Bundle getBundle() {
        return this.bundle;
    }

    public final Command getCommand() {
        String string = this.bundle.getString(Contract.COMMAND);
        if (string != null) {
            return Command.Companion.fromId(string);
        }
        return null;
    }

    public final String getHeaders() {
        return this.bundle.getString(Contract.HEADERS);
    }

    public final Promise getPromise() {
        String string = this.bundle.getString(Contract.PROMISE);
        if (string != null) {
            return Promise.Companion.parse(string);
        }
        return null;
    }

    public final Integer getPromiseId() {
        Promise parse;
        String id;
        String string = this.bundle.getString(Contract.PROMISE);
        if (string == null || (parse = Promise.Companion.parse(string)) == null || (id = parse.getId()) == null) {
            return null;
        }
        return Integer.valueOf(Integer.parseInt(id));
    }

    public final String getQuery() {
        return this.bundle.getString(Contract.QUERY);
    }

    public final String getVariable() {
        return this.bundle.getString(Contract.VARIABLE);
    }
}
