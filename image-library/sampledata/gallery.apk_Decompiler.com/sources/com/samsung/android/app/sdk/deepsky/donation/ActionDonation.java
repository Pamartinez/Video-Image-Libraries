package com.samsung.android.app.sdk.deepsky.donation;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\n\u001a\u00020\u0007J\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/donation/ActionDonation;", "", "builder", "Lcom/samsung/android/app/sdk/deepsky/donation/ActionDonation$Builder;", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/donation/ActionDonation$Builder;)V", "actionName", "", "paramDetailMap", "", "getActionName", "getParamDetailMap", "", "Builder", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActionDonation {
    private final String actionName;
    private final Builder builder;
    private final Map<String, String> paramDetailMap;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\u000f\u001a\u00020\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/donation/ActionDonation$Builder;", "", "actionName", "", "<init>", "(Ljava/lang/String;)V", "getActionName", "()Ljava/lang/String;", "paramDetailMap", "", "getParamDetailMap", "()Ljava/util/Map;", "addParamDetails", "key", "value", "build", "Lcom/samsung/android/app/sdk/deepsky/donation/ActionDonation;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private final String actionName;
        private final Map<String, String> paramDetailMap = new HashMap();

        public Builder(String str) {
            j.e(str, "actionName");
            this.actionName = str;
        }

        public final Builder addParamDetails(String str, String str2) {
            j.e(str, "key");
            this.paramDetailMap.put(str, new Gson().toJson((Object) str2));
            return this;
        }

        public final ActionDonation build() {
            return new ActionDonation(this, (e) null);
        }

        public final String getActionName() {
            return this.actionName;
        }

        public final Map<String, String> getParamDetailMap() {
            return this.paramDetailMap;
        }
    }

    public /* synthetic */ ActionDonation(Builder builder2, e eVar) {
        this(builder2);
    }

    public final String getActionName() {
        return this.actionName;
    }

    public final Map<String, String> getParamDetailMap() {
        return this.paramDetailMap;
    }

    private ActionDonation(Builder builder2) {
        this.builder = builder2;
        this.actionName = builder2.getActionName();
        HashMap hashMap = new HashMap();
        this.paramDetailMap = hashMap;
        hashMap.putAll(builder2.getParamDetailMap());
    }
}
