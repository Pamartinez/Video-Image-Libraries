package com.samsung.android.sdk.scs.ai.visual.c2pa;

import Tf.n;
import c0.C0086a;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b8\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001LB\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0006\u00104\u001a\u00020\rJ\u0006\u00105\u001a\u00020\rJ\u0006\u00106\u001a\u00020\rJ\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011J\u0012\u00108\u001a\u00020\r2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0002J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010@\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010$J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010C\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¤\u0001\u0010E\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010FJ\u0013\u0010G\u001a\u00020\r2\b\u0010H\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010I\u001a\u00020JHÖ\u0001J\t\u0010K\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0016\"\u0004\b\u001f\u0010\u0018R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0016\"\u0004\b#\u0010\u0018R\u001e\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0010\n\u0002\u0010'\u001a\u0004\b\f\u0010$\"\u0004\b%\u0010&R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0016\"\u0004\b)\u0010\u0018R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0016\"\u0004\b+\u0010\u0018R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R \u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0016\"\u0004\b1\u0010\u0018R\u0011\u00102\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b3\u0010\u0016¨\u0006M"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Action;", "", "action", "", "digitalSourceType", "softwareAgentElement", "Lcom/google/gson/JsonElement;", "parameters", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Parameters;", "actionTime", "issuer", "claimGenerator", "isInvalid", "", "title", "activeManifest", "ingredientsFile", "", "instanceId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/gson/JsonElement;Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Parameters;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getAction", "()Ljava/lang/String;", "setAction", "(Ljava/lang/String;)V", "getDigitalSourceType", "getSoftwareAgentElement", "()Lcom/google/gson/JsonElement;", "getParameters", "()Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Parameters;", "getActionTime", "setActionTime", "getIssuer", "setIssuer", "getClaimGenerator", "setClaimGenerator", "()Ljava/lang/Boolean;", "setInvalid", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getTitle", "setTitle", "getActiveManifest", "setActiveManifest", "getIngredientsFile", "()Ljava/util/List;", "setIngredientsFile", "(Ljava/util/List;)V", "getInstanceId", "setInstanceId", "softwareAgent", "getSoftwareAgent", "isAiGenerated", "isEnhanced", "isEdited", "getAuthorsList", "hasAiSourceType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/gson/JsonElement;Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Parameters;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Action;", "equals", "other", "hashCode", "", "toString", "Builder", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Action {
    private String action;
    private String actionTime;
    private String activeManifest;
    private String claimGenerator;
    @SerializedName("digitalSourceType")
    private final String digitalSourceType;
    private List<String> ingredientsFile;
    @SerializedName("instanceId")
    private String instanceId;
    private Boolean isInvalid;
    private String issuer;
    private final Parameters parameters;
    @SerializedName("softwareAgent")
    private final JsonElement softwareAgentElement;
    private String title;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\fJ\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0005J:\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011J\u0006\u0010\u0012\u001a\u00020\u0013R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Action$Builder;", "", "<init>", "()V", "action", "", "digitalSourceType", "softwareAgent", "parameters", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Parameters;", "c2PaAction", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paAction;", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/DigitalSourceType;", "type", "version", "value", "authors", "", "build", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Action;", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String action;
        private String digitalSourceType;
        private Parameters parameters;
        private String softwareAgent;

        public static /* synthetic */ Builder parameters$default(Builder builder, String str, String str2, String str3, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = null;
            }
            if ((i2 & 2) != 0) {
                str2 = null;
            }
            if ((i2 & 4) != 0) {
                str3 = null;
            }
            return builder.parameters(str, str2, str3, list);
        }

        public final Builder action(C2paAction c2paAction) {
            j.e(c2paAction, "c2PaAction");
            this.action = c2paAction.getStr();
            return this;
        }

        public final Action build() {
            JsonPrimitive jsonPrimitive;
            String str = this.action;
            String str2 = this.digitalSourceType;
            String str3 = this.softwareAgent;
            if (str3 != null) {
                jsonPrimitive = new JsonPrimitive(str3);
            } else {
                jsonPrimitive = null;
            }
            return new Action(str, str2, jsonPrimitive, this.parameters, (String) null, (String) null, (String) null, (Boolean) null, (String) null, (String) null, (List<String>) null, (String) null);
        }

        public final Builder digitalSourceType(DigitalSourceType digitalSourceType2) {
            j.e(digitalSourceType2, "digitalSourceType");
            this.digitalSourceType = digitalSourceType2.getUri();
            return this;
        }

        public final Builder parameters(String str, String str2, String str3, List<String> list) {
            if (list == null) {
                this.parameters = new Parameters((Ingredient) null, str, str2, str3, (List<Author>) null);
                return this;
            }
            String str4 = str;
            String str5 = str2;
            String str6 = str3;
            ArrayList arrayList = new ArrayList();
            for (String author : list) {
                arrayList.add(new Author(author));
            }
            String str7 = str6;
            this.parameters = new Parameters((Ingredient) null, str4, str5, str7, arrayList);
            return this;
        }

        public final Builder softwareAgent(String str) {
            j.e(str, "softwareAgent");
            this.softwareAgent = str;
            return this;
        }
    }

    public Action(String str, String str2, JsonElement jsonElement, Parameters parameters2, String str3, String str4, String str5, Boolean bool, String str6, String str7, List<String> list, String str8) {
        this.action = str;
        this.digitalSourceType = str2;
        this.softwareAgentElement = jsonElement;
        this.parameters = parameters2;
        this.actionTime = str3;
        this.issuer = str4;
        this.claimGenerator = str5;
        this.isInvalid = bool;
        this.title = str6;
        this.activeManifest = str7;
        this.ingredientsFile = list;
        this.instanceId = str8;
    }

    public static /* synthetic */ Action copy$default(Action action2, String str, String str2, JsonElement jsonElement, Parameters parameters2, String str3, String str4, String str5, Boolean bool, String str6, String str7, List<String> list, String str8, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = action2.action;
        }
        if ((i2 & 2) != 0) {
            str2 = action2.digitalSourceType;
        }
        if ((i2 & 4) != 0) {
            jsonElement = action2.softwareAgentElement;
        }
        if ((i2 & 8) != 0) {
            parameters2 = action2.parameters;
        }
        if ((i2 & 16) != 0) {
            str3 = action2.actionTime;
        }
        if ((i2 & 32) != 0) {
            str4 = action2.issuer;
        }
        if ((i2 & 64) != 0) {
            str5 = action2.claimGenerator;
        }
        if ((i2 & 128) != 0) {
            bool = action2.isInvalid;
        }
        if ((i2 & 256) != 0) {
            str6 = action2.title;
        }
        if ((i2 & 512) != 0) {
            str7 = action2.activeManifest;
        }
        if ((i2 & 1024) != 0) {
            list = action2.ingredientsFile;
        }
        if ((i2 & 2048) != 0) {
            str8 = action2.instanceId;
        }
        List<String> list2 = list;
        String str9 = str8;
        String str10 = str6;
        String str11 = str7;
        String str12 = str5;
        Boolean bool2 = bool;
        String str13 = str3;
        String str14 = str4;
        Parameters parameters3 = parameters2;
        String str15 = str2;
        return action2.copy(str, str15, jsonElement, parameters3, str13, str14, str12, bool2, str10, str11, list2, str9);
    }

    private final boolean hasAiSourceType(String str) {
        if (str == null) {
            return false;
        }
        if (n.u0(str, DigitalSourceType.TRAINED_ALGORITHMIC_MEDIA.getUri()) || n.u0(str, DigitalSourceType.C2PA_TRAINED_ALGORITHMIC_MEDIA_NEW.getUri()) || n.u0(str, DigitalSourceType.C2PA_TRAINED_ALGORITHMIC_MEDIA_OLD.getUri()) || n.u0(str, DigitalSourceType.COMPOSITE_SYNTHETIC.getUri()) || n.u0(str, DigitalSourceType.COMPOSITE_WITH_TRAINED_ALGORITHMIC_MEDIA.getUri())) {
            return true;
        }
        return false;
    }

    public final String component1() {
        return this.action;
    }

    public final String component10() {
        return this.activeManifest;
    }

    public final List<String> component11() {
        return this.ingredientsFile;
    }

    public final String component12() {
        return this.instanceId;
    }

    public final String component2() {
        return this.digitalSourceType;
    }

    public final JsonElement component3() {
        return this.softwareAgentElement;
    }

    public final Parameters component4() {
        return this.parameters;
    }

    public final String component5() {
        return this.actionTime;
    }

    public final String component6() {
        return this.issuer;
    }

    public final String component7() {
        return this.claimGenerator;
    }

    public final Boolean component8() {
        return this.isInvalid;
    }

    public final String component9() {
        return this.title;
    }

    public final Action copy(String str, String str2, JsonElement jsonElement, Parameters parameters2, String str3, String str4, String str5, Boolean bool, String str6, String str7, List<String> list, String str8) {
        return new Action(str, str2, jsonElement, parameters2, str3, str4, str5, bool, str6, str7, list, str8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Action)) {
            return false;
        }
        Action action2 = (Action) obj;
        if (j.a(this.action, action2.action) && j.a(this.digitalSourceType, action2.digitalSourceType) && j.a(this.softwareAgentElement, action2.softwareAgentElement) && j.a(this.parameters, action2.parameters) && j.a(this.actionTime, action2.actionTime) && j.a(this.issuer, action2.issuer) && j.a(this.claimGenerator, action2.claimGenerator) && j.a(this.isInvalid, action2.isInvalid) && j.a(this.title, action2.title) && j.a(this.activeManifest, action2.activeManifest) && j.a(this.ingredientsFile, action2.ingredientsFile) && j.a(this.instanceId, action2.instanceId)) {
            return true;
        }
        return false;
    }

    public final String getAction() {
        return this.action;
    }

    public final String getActionTime() {
        return this.actionTime;
    }

    public final String getActiveManifest() {
        return this.activeManifest;
    }

    public final List<String> getAuthorsList() {
        ArrayList arrayList;
        List<Author> author;
        Parameters parameters2 = this.parameters;
        if (parameters2 == null || (author = parameters2.getAuthor()) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (Author name : author) {
                String name2 = name.getName();
                if (name2 != null) {
                    arrayList.add(name2);
                }
            }
        }
        if (arrayList == null) {
            return C1202t.d;
        }
        return arrayList;
    }

    public final String getClaimGenerator() {
        return this.claimGenerator;
    }

    public final String getDigitalSourceType() {
        return this.digitalSourceType;
    }

    public final List<String> getIngredientsFile() {
        return this.ingredientsFile;
    }

    public final String getInstanceId() {
        return this.instanceId;
    }

    public final String getIssuer() {
        return this.issuer;
    }

    public final Parameters getParameters() {
        return this.parameters;
    }

    public final String getSoftwareAgent() {
        JsonElement jsonElement = this.softwareAgentElement;
        if (jsonElement == null || !jsonElement.isJsonPrimitive()) {
            JsonElement jsonElement2 = this.softwareAgentElement;
            if (jsonElement2 == null || !jsonElement2.isJsonObject() || !this.softwareAgentElement.getAsJsonObject().has("name")) {
                return "";
            }
            String asString = this.softwareAgentElement.getAsJsonObject().get("name").getAsString();
            j.d(asString, "getAsString(...)");
            return asString;
        }
        String asString2 = this.softwareAgentElement.getAsString();
        j.d(asString2, "getAsString(...)");
        return asString2;
    }

    public final JsonElement getSoftwareAgentElement() {
        return this.softwareAgentElement;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        String str = this.action;
        int i18 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i19 = i2 * 31;
        String str2 = this.digitalSourceType;
        if (str2 == null) {
            i7 = 0;
        } else {
            i7 = str2.hashCode();
        }
        int i20 = (i19 + i7) * 31;
        JsonElement jsonElement = this.softwareAgentElement;
        if (jsonElement == null) {
            i8 = 0;
        } else {
            i8 = jsonElement.hashCode();
        }
        int i21 = (i20 + i8) * 31;
        Parameters parameters2 = this.parameters;
        if (parameters2 == null) {
            i10 = 0;
        } else {
            i10 = parameters2.hashCode();
        }
        int i22 = (i21 + i10) * 31;
        String str3 = this.actionTime;
        if (str3 == null) {
            i11 = 0;
        } else {
            i11 = str3.hashCode();
        }
        int i23 = (i22 + i11) * 31;
        String str4 = this.issuer;
        if (str4 == null) {
            i12 = 0;
        } else {
            i12 = str4.hashCode();
        }
        int i24 = (i23 + i12) * 31;
        String str5 = this.claimGenerator;
        if (str5 == null) {
            i13 = 0;
        } else {
            i13 = str5.hashCode();
        }
        int i25 = (i24 + i13) * 31;
        Boolean bool = this.isInvalid;
        if (bool == null) {
            i14 = 0;
        } else {
            i14 = bool.hashCode();
        }
        int i26 = (i25 + i14) * 31;
        String str6 = this.title;
        if (str6 == null) {
            i15 = 0;
        } else {
            i15 = str6.hashCode();
        }
        int i27 = (i26 + i15) * 31;
        String str7 = this.activeManifest;
        if (str7 == null) {
            i16 = 0;
        } else {
            i16 = str7.hashCode();
        }
        int i28 = (i27 + i16) * 31;
        List<String> list = this.ingredientsFile;
        if (list == null) {
            i17 = 0;
        } else {
            i17 = list.hashCode();
        }
        int i29 = (i28 + i17) * 31;
        String str8 = this.instanceId;
        if (str8 != null) {
            i18 = str8.hashCode();
        }
        return i29 + i18;
    }

    public final boolean isAiGenerated() {
        String str;
        String str2;
        Ingredient ingredient;
        if (!hasAiSourceType(this.digitalSourceType)) {
            Parameters parameters2 = this.parameters;
            if (parameters2 == null || (ingredient = parameters2.getIngredient()) == null) {
                str = null;
            } else {
                str = ingredient.getDigitalSourceType();
            }
            if (!hasAiSourceType(str) || (str2 = this.action) == null || !n.u0(str2, C2paAction.C2PA_PLACED.getStr())) {
                return false;
            }
        }
        return true;
    }

    public final boolean isEdited() {
        String str = this.action;
        if (str == null) {
            return false;
        }
        return n.u0(str, C2paAction.C2PA_EDITED.getStr());
    }

    public final boolean isEnhanced() {
        String str = this.digitalSourceType;
        if (str == null) {
            return false;
        }
        return n.u0(str, DigitalSourceType.ALGORITHMICALLY_ENHANCED.getUri());
    }

    public final Boolean isInvalid() {
        return this.isInvalid;
    }

    public final void setAction(String str) {
        this.action = str;
    }

    public final void setActionTime(String str) {
        this.actionTime = str;
    }

    public final void setActiveManifest(String str) {
        this.activeManifest = str;
    }

    public final void setClaimGenerator(String str) {
        this.claimGenerator = str;
    }

    public final void setIngredientsFile(List<String> list) {
        this.ingredientsFile = list;
    }

    public final void setInstanceId(String str) {
        this.instanceId = str;
    }

    public final void setInvalid(Boolean bool) {
        this.isInvalid = bool;
    }

    public final void setIssuer(String str) {
        this.issuer = str;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        String str = this.action;
        String str2 = this.digitalSourceType;
        JsonElement jsonElement = this.softwareAgentElement;
        Parameters parameters2 = this.parameters;
        String str3 = this.actionTime;
        String str4 = this.issuer;
        String str5 = this.claimGenerator;
        Boolean bool = this.isInvalid;
        String str6 = this.title;
        String str7 = this.activeManifest;
        List<String> list = this.ingredientsFile;
        String str8 = this.instanceId;
        StringBuilder q = C0086a.q("Action(action=", str, ", digitalSourceType=", str2, ", softwareAgentElement=");
        q.append(jsonElement);
        q.append(", parameters=");
        q.append(parameters2);
        q.append(", actionTime=");
        C0086a.z(q, str3, ", issuer=", str4, ", claimGenerator=");
        q.append(str5);
        q.append(", isInvalid=");
        q.append(bool);
        q.append(", title=");
        C0086a.z(q, str6, ", activeManifest=", str7, ", ingredientsFile=");
        q.append(list);
        q.append(", instanceId=");
        q.append(str8);
        q.append(")");
        return q.toString();
    }
}
