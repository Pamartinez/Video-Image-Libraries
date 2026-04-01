package com.samsung.android.app.sdk.deepsky.barcode.result;

import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\b\u0018\u00002\u00020\u0001B5\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0016\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0017\u0010\fR\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/result/ActionUIResource;", "", "", "title", "body", "bodyTts", "", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "actions", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getTitle", "getBody", "getBodyTts", "Ljava/util/List;", "getActions", "()Ljava/util/List;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActionUIResource {
    private final List<Action> actions;
    private final String body;
    private final String bodyTts;
    private final String title;

    public ActionUIResource(String str, String str2, String str3, List<? extends Action> list) {
        j.e(str, "title");
        j.e(str2, "body");
        j.e(str3, "bodyTts");
        j.e(list, "actions");
        this.title = str;
        this.body = str2;
        this.bodyTts = str3;
        this.actions = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActionUIResource)) {
            return false;
        }
        ActionUIResource actionUIResource = (ActionUIResource) obj;
        if (j.a(this.title, actionUIResource.title) && j.a(this.body, actionUIResource.body) && j.a(this.bodyTts, actionUIResource.bodyTts) && j.a(this.actions, actionUIResource.actions)) {
            return true;
        }
        return false;
    }

    public final List<Action> getActions() {
        return this.actions;
    }

    public final String getBody() {
        return this.body;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.actions.hashCode() + C0212a.d(C0212a.d(this.title.hashCode() * 31, 31, this.body), 31, this.bodyTts);
    }

    public String toString() {
        String str = this.title;
        String str2 = this.body;
        String str3 = this.bodyTts;
        List<Action> list = this.actions;
        StringBuilder q = C0086a.q("ActionUIResource(title=", str, ", body=", str2, ", bodyTts=");
        q.append(str3);
        q.append(", actions=");
        q.append(list);
        q.append(")");
        return q.toString();
    }
}
