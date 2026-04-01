package com.samsung.android.sdk.scs.ai.visual.c2pa;

import Bd.C0725a;
import Sf.h;
import Sf.k;
import Sf.n;
import Sf.s;
import c0.C0086a;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.samsung.android.sdk.scs.base.utils.Log;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import ne.C1194l;
import ne.C1195m;
import ne.C1202t;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001?B\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u0005J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u00103\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005HÆ\u0003J\u0011\u00104\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0005HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0012HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u0001\u00109\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00052\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÆ\u0001J\u0013\u0010:\u001a\u00020\u00122\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010<\u001a\u00020=HÖ\u0001J\t\u0010>\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010%\"\u0004\b&\u0010'R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006@"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paManifest;", "", "claimGenerator", "", "claimGeneratorInfo", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ClaimGeneratorInfo;", "title", "format", "instanceId", "ingredients", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Ingredients;", "assertions", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paAssertion;", "signatureInfo", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/SignatureInfo;", "label", "isInvalid", "", "assertionsJsonArray", "Lcom/google/gson/JsonArray;", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/samsung/android/sdk/scs/ai/visual/c2pa/SignatureInfo;Ljava/lang/String;ZLcom/google/gson/JsonArray;)V", "getClaimGenerator", "()Ljava/lang/String;", "getClaimGeneratorInfo", "()Ljava/util/List;", "setClaimGeneratorInfo", "(Ljava/util/List;)V", "getTitle", "getFormat", "getInstanceId", "getIngredients", "getAssertions", "getSignatureInfo", "()Lcom/samsung/android/sdk/scs/ai/visual/c2pa/SignatureInfo;", "getLabel", "()Z", "setInvalid", "(Z)V", "getAssertionsJsonArray", "()Lcom/google/gson/JsonArray;", "setAssertionsJsonArray", "(Lcom/google/gson/JsonArray;)V", "getActions", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Action;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "hashCode", "", "toString", "Builder", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C2paManifest {
    private final List<C2paAssertion> assertions;
    private JsonArray assertionsJsonArray;
    private final String claimGenerator;
    private List<ClaimGeneratorInfo> claimGeneratorInfo;
    private final String format;
    private final List<Ingredients> ingredients;
    private final String instanceId;
    private boolean isInvalid;
    private final String label;
    private final SignatureInfo signatureInfo;
    private final String title;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\tJ\u0006\u0010\f\u001a\u00020\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paManifest$Builder;", "", "<init>", "()V", "TAG", "", "claimGenerator", "assertions", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paAssertion;", "addAssertion", "assertion", "build", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String TAG = v.f4727a.b(Builder.class).o();
        private List<C2paAssertion> assertions = new ArrayList();
        private String claimGenerator = Constant.CLAIM_GENERATOR;

        /* access modifiers changed from: private */
        public static final k build$lambda$2(C2paAssertion c2paAssertion) {
            Iterable iterable;
            j.e(c2paAssertion, "it");
            Data data = c2paAssertion.getData();
            if (data != null) {
                iterable = data.getActions();
            } else {
                iterable = null;
            }
            if (iterable == null) {
                iterable = C1202t.d;
            }
            return C1194l.F0(iterable);
        }

        /* access modifiers changed from: private */
        public static final String build$lambda$3(Action action) {
            j.e(action, "it");
            return action.getAction();
        }

        public final Builder addAssertion(C2paAssertion c2paAssertion) {
            j.e(c2paAssertion, "assertion");
            this.assertions.add(c2paAssertion);
            return this;
        }

        public final String build() {
            boolean G02 = C1194l.G0(C1195m.q0(C2paAction.C2PA_CREATED.getStr(), C2paAction.C2PA_OPENED.getStr()), n.q0(n.t0(new h(C1194l.F0(this.assertions), new C0725a(26), s.d), new C0725a(27))));
            if (!G02) {
                String str = this.TAG;
                Log.e(str, "firstActionCheck = " + G02 + "c2pa.created or c2pa.opened should used for the first action in the manifest of c2pa V2 onlyIgnore this error for c2pa V1");
            }
            String json = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create().toJson((Object) new C2paManifest(this.claimGenerator, (List<ClaimGeneratorInfo>) null, (String) null, (String) null, (String) null, (List<Ingredients>) null, this.assertions, (SignatureInfo) null, (String) null, false, (JsonArray) null));
            j.d(json, "toJson(...)");
            return json;
        }

        public final Builder claimGenerator(String str) {
            j.e(str, "claimGenerator");
            this.claimGenerator = str;
            return this;
        }
    }

    public C2paManifest(String str, List<ClaimGeneratorInfo> list, String str2, String str3, String str4, List<Ingredients> list2, List<C2paAssertion> list3, SignatureInfo signatureInfo2, String str5, boolean z, JsonArray jsonArray) {
        this.claimGenerator = str;
        this.claimGeneratorInfo = list;
        this.title = str2;
        this.format = str3;
        this.instanceId = str4;
        this.ingredients = list2;
        this.assertions = list3;
        this.signatureInfo = signatureInfo2;
        this.label = str5;
        this.isInvalid = z;
        this.assertionsJsonArray = jsonArray;
    }

    public static /* synthetic */ C2paManifest copy$default(C2paManifest c2paManifest, String str, List<ClaimGeneratorInfo> list, String str2, String str3, String str4, List<Ingredients> list2, List<C2paAssertion> list3, SignatureInfo signatureInfo2, String str5, boolean z, JsonArray jsonArray, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = c2paManifest.claimGenerator;
        }
        if ((i2 & 2) != 0) {
            list = c2paManifest.claimGeneratorInfo;
        }
        if ((i2 & 4) != 0) {
            str2 = c2paManifest.title;
        }
        if ((i2 & 8) != 0) {
            str3 = c2paManifest.format;
        }
        if ((i2 & 16) != 0) {
            str4 = c2paManifest.instanceId;
        }
        if ((i2 & 32) != 0) {
            list2 = c2paManifest.ingredients;
        }
        if ((i2 & 64) != 0) {
            list3 = c2paManifest.assertions;
        }
        if ((i2 & 128) != 0) {
            signatureInfo2 = c2paManifest.signatureInfo;
        }
        if ((i2 & 256) != 0) {
            str5 = c2paManifest.label;
        }
        if ((i2 & 512) != 0) {
            z = c2paManifest.isInvalid;
        }
        if ((i2 & 1024) != 0) {
            jsonArray = c2paManifest.assertionsJsonArray;
        }
        boolean z3 = z;
        JsonArray jsonArray2 = jsonArray;
        SignatureInfo signatureInfo3 = signatureInfo2;
        String str6 = str5;
        List<Ingredients> list4 = list2;
        List<C2paAssertion> list5 = list3;
        String str7 = str3;
        String str8 = str4;
        return c2paManifest.copy(str, list, str2, str7, str8, list4, list5, signatureInfo3, str6, z3, jsonArray2);
    }

    public final String component1() {
        return this.claimGenerator;
    }

    public final boolean component10() {
        return this.isInvalid;
    }

    public final JsonArray component11() {
        return this.assertionsJsonArray;
    }

    public final List<ClaimGeneratorInfo> component2() {
        return this.claimGeneratorInfo;
    }

    public final String component3() {
        return this.title;
    }

    public final String component4() {
        return this.format;
    }

    public final String component5() {
        return this.instanceId;
    }

    public final List<Ingredients> component6() {
        return this.ingredients;
    }

    public final List<C2paAssertion> component7() {
        return this.assertions;
    }

    public final SignatureInfo component8() {
        return this.signatureInfo;
    }

    public final String component9() {
        return this.label;
    }

    public final C2paManifest copy(String str, List<ClaimGeneratorInfo> list, String str2, String str3, String str4, List<Ingredients> list2, List<C2paAssertion> list3, SignatureInfo signatureInfo2, String str5, boolean z, JsonArray jsonArray) {
        return new C2paManifest(str, list, str2, str3, str4, list2, list3, signatureInfo2, str5, z, jsonArray);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2paManifest)) {
            return false;
        }
        C2paManifest c2paManifest = (C2paManifest) obj;
        if (j.a(this.claimGenerator, c2paManifest.claimGenerator) && j.a(this.claimGeneratorInfo, c2paManifest.claimGeneratorInfo) && j.a(this.title, c2paManifest.title) && j.a(this.format, c2paManifest.format) && j.a(this.instanceId, c2paManifest.instanceId) && j.a(this.ingredients, c2paManifest.ingredients) && j.a(this.assertions, c2paManifest.assertions) && j.a(this.signatureInfo, c2paManifest.signatureInfo) && j.a(this.label, c2paManifest.label) && this.isInvalid == c2paManifest.isInvalid && j.a(this.assertionsJsonArray, c2paManifest.assertionsJsonArray)) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: ne.t} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.samsung.android.sdk.scs.ai.visual.c2pa.Action> getActions() {
        /*
            r8 = this;
            java.util.List<com.samsung.android.sdk.scs.ai.visual.c2pa.C2paAssertion> r0 = r8.assertions
            r1 = 0
            if (r0 == 0) goto L_0x002f
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x0010:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x002e
            java.lang.Object r3 = r0.next()
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paAssertion r3 = (com.samsung.android.sdk.scs.ai.visual.c2pa.C2paAssertion) r3
            com.samsung.android.sdk.scs.ai.visual.c2pa.Data r3 = r3.getData()
            if (r3 == 0) goto L_0x0027
            java.util.List r3 = r3.getActions()
            goto L_0x0028
        L_0x0027:
            r3 = r1
        L_0x0028:
            if (r3 == 0) goto L_0x0010
            r2.add(r3)
            goto L_0x0010
        L_0x002e:
            r1 = r2
        L_0x002f:
            ne.t r0 = ne.C1202t.d
            if (r1 != 0) goto L_0x0034
            r1 = r0
        L_0x0034:
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r1 = ne.C1196n.x0(r1)
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x00ce
            java.util.Iterator r2 = r1.iterator()
        L_0x0044:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00ce
            java.lang.Object r3 = r2.next()
            com.samsung.android.sdk.scs.ai.visual.c2pa.Action r3 = (com.samsung.android.sdk.scs.ai.visual.c2pa.Action) r3
            com.samsung.android.sdk.scs.ai.visual.c2pa.SignatureInfo r4 = r8.signatureInfo
            if (r4 == 0) goto L_0x005a
            java.lang.String r4 = r4.getTime()
            if (r4 != 0) goto L_0x005c
        L_0x005a:
            java.lang.String r4 = "1970-01-01T00:00:00+00:00"
        L_0x005c:
            r3.setActionTime(r4)
            com.samsung.android.sdk.scs.ai.visual.c2pa.SignatureInfo r4 = r8.signatureInfo
            java.lang.String r5 = "Unknown"
            if (r4 == 0) goto L_0x006b
            java.lang.String r4 = r4.getIssuer()
            if (r4 != 0) goto L_0x006c
        L_0x006b:
            r4 = r5
        L_0x006c:
            r3.setIssuer(r4)
            java.util.List<com.samsung.android.sdk.scs.ai.visual.c2pa.ClaimGeneratorInfo> r4 = r8.claimGeneratorInfo
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = ne.C1194l.N0(r4)
            com.samsung.android.sdk.scs.ai.visual.c2pa.ClaimGeneratorInfo r4 = (com.samsung.android.sdk.scs.ai.visual.c2pa.ClaimGeneratorInfo) r4
            if (r4 == 0) goto L_0x008e
            java.lang.String r6 = r4.getName()
            java.lang.String r4 = r4.getVersion()
            java.lang.String r7 = " "
            java.lang.String r4 = i.C0212a.B(r6, r7, r4)
            if (r4 != 0) goto L_0x008c
            goto L_0x008e
        L_0x008c:
            r5 = r4
            goto L_0x0092
        L_0x008e:
            java.lang.String r4 = r8.claimGenerator
            if (r4 != 0) goto L_0x008c
        L_0x0092:
            r3.setClaimGenerator(r5)
            boolean r4 = r8.isInvalid
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r3.setInvalid(r4)
            java.lang.String r4 = r8.title
            r3.setTitle(r4)
            java.util.List<com.samsung.android.sdk.scs.ai.visual.c2pa.Ingredients> r4 = r8.ingredients
            if (r4 == 0) goto L_0x00c8
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r4 = r4.iterator()
        L_0x00b2:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x00c9
            java.lang.Object r6 = r4.next()
            com.samsung.android.sdk.scs.ai.visual.c2pa.Ingredients r6 = (com.samsung.android.sdk.scs.ai.visual.c2pa.Ingredients) r6
            java.lang.String r6 = r6.getTitle()
            if (r6 == 0) goto L_0x00b2
            r5.add(r6)
            goto L_0x00b2
        L_0x00c8:
            r5 = r0
        L_0x00c9:
            r3.setIngredientsFile(r5)
            goto L_0x0044
        L_0x00ce:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifest.getActions():java.util.List");
    }

    public final List<C2paAssertion> getAssertions() {
        return this.assertions;
    }

    public final JsonArray getAssertionsJsonArray() {
        return this.assertionsJsonArray;
    }

    public final String getClaimGenerator() {
        return this.claimGenerator;
    }

    public final List<ClaimGeneratorInfo> getClaimGeneratorInfo() {
        return this.claimGeneratorInfo;
    }

    public final String getFormat() {
        return this.format;
    }

    public final List<Ingredients> getIngredients() {
        return this.ingredients;
    }

    public final String getInstanceId() {
        return this.instanceId;
    }

    public final String getLabel() {
        return this.label;
    }

    public final SignatureInfo getSignatureInfo() {
        return this.signatureInfo;
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
        String str = this.claimGenerator;
        int i16 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i17 = i2 * 31;
        List<ClaimGeneratorInfo> list = this.claimGeneratorInfo;
        if (list == null) {
            i7 = 0;
        } else {
            i7 = list.hashCode();
        }
        int i18 = (i17 + i7) * 31;
        String str2 = this.title;
        if (str2 == null) {
            i8 = 0;
        } else {
            i8 = str2.hashCode();
        }
        int i19 = (i18 + i8) * 31;
        String str3 = this.format;
        if (str3 == null) {
            i10 = 0;
        } else {
            i10 = str3.hashCode();
        }
        int i20 = (i19 + i10) * 31;
        String str4 = this.instanceId;
        if (str4 == null) {
            i11 = 0;
        } else {
            i11 = str4.hashCode();
        }
        int i21 = (i20 + i11) * 31;
        List<Ingredients> list2 = this.ingredients;
        if (list2 == null) {
            i12 = 0;
        } else {
            i12 = list2.hashCode();
        }
        int i22 = (i21 + i12) * 31;
        List<C2paAssertion> list3 = this.assertions;
        if (list3 == null) {
            i13 = 0;
        } else {
            i13 = list3.hashCode();
        }
        int i23 = (i22 + i13) * 31;
        SignatureInfo signatureInfo2 = this.signatureInfo;
        if (signatureInfo2 == null) {
            i14 = 0;
        } else {
            i14 = signatureInfo2.hashCode();
        }
        int i24 = (i23 + i14) * 31;
        String str5 = this.label;
        if (str5 == null) {
            i15 = 0;
        } else {
            i15 = str5.hashCode();
        }
        int e = C0212a.e((i24 + i15) * 31, 31, this.isInvalid);
        JsonArray jsonArray = this.assertionsJsonArray;
        if (jsonArray != null) {
            i16 = jsonArray.hashCode();
        }
        return e + i16;
    }

    public final boolean isInvalid() {
        return this.isInvalid;
    }

    public final void setAssertionsJsonArray(JsonArray jsonArray) {
        this.assertionsJsonArray = jsonArray;
    }

    public final void setClaimGeneratorInfo(List<ClaimGeneratorInfo> list) {
        this.claimGeneratorInfo = list;
    }

    public final void setInvalid(boolean z) {
        this.isInvalid = z;
    }

    public String toString() {
        String str = this.claimGenerator;
        List<ClaimGeneratorInfo> list = this.claimGeneratorInfo;
        String str2 = this.title;
        String str3 = this.format;
        String str4 = this.instanceId;
        List<Ingredients> list2 = this.ingredients;
        List<C2paAssertion> list3 = this.assertions;
        SignatureInfo signatureInfo2 = this.signatureInfo;
        String str5 = this.label;
        boolean z = this.isInvalid;
        JsonArray jsonArray = this.assertionsJsonArray;
        StringBuilder sb2 = new StringBuilder("C2paManifest(claimGenerator=");
        sb2.append(str);
        sb2.append(", claimGeneratorInfo=");
        sb2.append(list);
        sb2.append(", title=");
        C0086a.z(sb2, str2, ", format=", str3, ", instanceId=");
        sb2.append(str4);
        sb2.append(", ingredients=");
        sb2.append(list2);
        sb2.append(", assertions=");
        sb2.append(list3);
        sb2.append(", signatureInfo=");
        sb2.append(signatureInfo2);
        sb2.append(", label=");
        sb2.append(str5);
        sb2.append(", isInvalid=");
        sb2.append(z);
        sb2.append(", assertionsJsonArray=");
        sb2.append(jsonArray);
        sb2.append(")");
        return sb2.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2paManifest(String str, List list, String str2, String str3, String str4, List list2, List list3, SignatureInfo signatureInfo2, String str5, boolean z, JsonArray jsonArray, int i2, e eVar) {
        this(str, list, str2, str3, str4, list2, list3, signatureInfo2, str5, (i2 & 512) != 0 ? false : z, jsonArray);
    }
}
