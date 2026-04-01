package com.samsung.android.sdk.scs.ai.visual.c2pa;

import Ad.f;
import Ae.a;
import Ae.b;
import Sf.n;
import Sf.u;
import Tf.h;
import Tf.k;
import Tf.l;
import Tf.m;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GraphPathNodeBundleWrapper;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;
import ne.C1195m;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b;\b\u0002\u0018\u0000 =2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001=B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<¨\u0006>"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paError;", "", "errString", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getErrString", "()Ljava/lang/String;", "NO_C2PA_MANIFEST", "CLAIM_MISSING", "CLAIM_MULTIPLE", "HARD_BINDINGS_MISSING", "CLAIM_REQUIRED_MISSING", "CLAIM_CBOR_INVALID", "INGREDIENT_HASHEDURI_MISMATCH", "CLAIM_SIGNATURE_MISSING", "CLAIM_SIGNATURE_MISMATCH", "MANIFEST_INACCESSIBLE", "MANIFEST_MULTIPLE_PARENTS", "MANIFEST_UPDATE_INVALID", "MANIFEST_UPDATE_WRONG_PARENTS", "SIGNING_CREDENTIAL_UNTRUSTED", "SIGNING_CREDENTIAL_INVALID", "SIGNING_CREDENTIAL_REVOKED", "SIGNING_CREDENTIAL_EXPIRED", "TIMESTAMP_MISMATCH", "TIMESTAMP_UNTRUSTED", "TIMESTAMP_OUTSIDE_VALIDITY", "ASSERTION_HASHEDURI_MISMATCH", "ASSERTION_MISSING", "ASSERTION_UNDECLARED", "ASSERTION_INACCESSIBLE", "ASSERTION_NOT_REDACTED", "ASSERTION_SELF_REDACTED", "ASSERTION_REQUIRED_MISSING", "ASSERTION_JSON_INVALID", "ASSERTION_CBOR_INVALID", "ACTION_ASSERTION_INGREDIENT_MISMATCH", "ACTION_ASSERTION_REDACTED", "ASSERTION_DATAHASH_MISMATCH", "ASSERTION_BMFFHASH_MISMATCH", "ASSERTION_BOXHASH_MISMATCH", "ASSERTION_BOXHASH_UNKNOWN", "ASSERTION_CLOUD_DATA_HARD_BINDING", "ASSERTION_CLOUD_DATA_ACTIONS", "ALGORITHM_UNSUPPORTED", "GENERAL_ERROR", "OLD_VERSION", "UNSUPPORTED_TYPE", "INVALID_CLAIM_SIGNATURE", "INVALID_PATH", "MANIFEST_PARSING_ERROR", "INVALID_SIGN_ALG", "C2PA_ERROR_UNKNOWN", "SERVICE_NOT_INITIALIZED", "INVALID_PARENT_PATH", "INVALID_INGREDIENT_PATH", "INTERNAL_SERVICE_ERROR", "PFD_READ_ERROR", "MISSING_CONFIG_ERROR", "TRUST_SET_ERROR", "Companion", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C2paError {
    NO_C2PA_MANIFEST("no JUMBF data found"),
    CLAIM_MISSING("claim.missing"),
    CLAIM_MULTIPLE("claim.multiple"),
    HARD_BINDINGS_MISSING("claim.hardBindings.missing"),
    CLAIM_REQUIRED_MISSING("claim.required.missing"),
    CLAIM_CBOR_INVALID("claim.cbor.invalid"),
    INGREDIENT_HASHEDURI_MISMATCH("ingredient.hashedURI.mismatch"),
    CLAIM_SIGNATURE_MISSING("claimSignature.missing"),
    CLAIM_SIGNATURE_MISMATCH("claimSignature.mismatch"),
    MANIFEST_INACCESSIBLE("manifest.inaccessible"),
    MANIFEST_MULTIPLE_PARENTS("manifest.multipleParents"),
    MANIFEST_UPDATE_INVALID("manifest.update.invalid"),
    MANIFEST_UPDATE_WRONG_PARENTS("manifest.update.wrongParents"),
    SIGNING_CREDENTIAL_UNTRUSTED("signingCredential.untrusted"),
    SIGNING_CREDENTIAL_INVALID("signingCredential.invalid"),
    SIGNING_CREDENTIAL_REVOKED("signingCredential.revoked"),
    SIGNING_CREDENTIAL_EXPIRED("signingCredential.expired"),
    TIMESTAMP_MISMATCH("timeStamp.mismatch"),
    TIMESTAMP_UNTRUSTED("timeStamp.untrusted"),
    TIMESTAMP_OUTSIDE_VALIDITY("timeStamp.outsideValidity"),
    ASSERTION_HASHEDURI_MISMATCH("assertion.hashedURI.mismatch"),
    ASSERTION_MISSING("assertion.missing"),
    ASSERTION_UNDECLARED("assertion.undeclared"),
    ASSERTION_INACCESSIBLE("assertion.inaccessible"),
    ASSERTION_NOT_REDACTED("assertion.notRedacted"),
    ASSERTION_SELF_REDACTED("assertion.selfRedacted"),
    ASSERTION_REQUIRED_MISSING("assertion.required.missing"),
    ASSERTION_JSON_INVALID("assertion.json.invalid"),
    ASSERTION_CBOR_INVALID("assertion.cbor.invalid"),
    ACTION_ASSERTION_INGREDIENT_MISMATCH("assertion.action.ingredientMismatch"),
    ACTION_ASSERTION_REDACTED("assertion.action.redacted"),
    ASSERTION_DATAHASH_MISMATCH("assertion.dataHash.mismatch"),
    ASSERTION_BMFFHASH_MISMATCH("assertion.bmffHash.mismatch"),
    ASSERTION_BOXHASH_MISMATCH("assertion.boxesHash.mismatch"),
    ASSERTION_BOXHASH_UNKNOWN("::assertion.boxesHash."),
    ASSERTION_CLOUD_DATA_HARD_BINDING("assertion.cloud-data.hardBinding"),
    ASSERTION_CLOUD_DATA_ACTIONS("assertion.cloud-data.actions"),
    ALGORITHM_UNSUPPORTED("algorithm.unsupported"),
    GENERAL_ERROR("general.error"),
    OLD_VERSION("prerelease content detected"),
    UNSUPPORTED_TYPE("type is unsupported"),
    INVALID_CLAIM_SIGNATURE("claim signature"),
    INVALID_PATH("invalid path"),
    MANIFEST_PARSING_ERROR("ManifestParsingError"),
    INVALID_SIGN_ALG("InvalidSignAlg"),
    C2PA_ERROR_UNKNOWN("C2PAUnKnown"),
    SERVICE_NOT_INITIALIZED("ServiceNotInitialized"),
    INVALID_PARENT_PATH("ParentPathSetError"),
    INVALID_INGREDIENT_PATH("IngredietPathError"),
    INTERNAL_SERVICE_ERROR("InternalServiceError"),
    PFD_READ_ERROR("PfdReadError"),
    MISSING_CONFIG_ERROR("MissingConfigError"),
    TRUST_SET_ERROR("TrustSetError");
    
    public static final Companion Companion = null;
    private final String errString;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paError$Companion;", "", "<init>", "()V", "fromErrorString", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paError;", "errString", "", "checkInvalidV1", "", "checkInvalidV2", "checkInvalid", "soVersion", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paSoVersionInfo;", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    com.samsung.android.sdk.scs.ai.visual.c2pa.C2paSoVersionInfo[] r0 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paSoVersionInfo.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.samsung.android.sdk.scs.ai.visual.c2pa.C2paSoVersionInfo r1 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paSoVersionInfo.V2     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.samsung.android.sdk.scs.ai.visual.c2pa.C2paSoVersionInfo r1 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paSoVersionInfo.V1     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.visual.c2pa.C2paError.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private final boolean checkInvalidV1(String str) {
            int i2;
            int i7;
            int i8;
            int i10;
            List<C2paError> fromErrorString = fromErrorString(str);
            Iterable<C2paError> iterable = fromErrorString;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                for (C2paError contains : iterable) {
                    if (!C1192j.z0(new C2paError[]{C2paError.CLAIM_SIGNATURE_MISMATCH, C2paError.SIGNING_CREDENTIAL_UNTRUSTED, C2paError.GENERAL_ERROR}).contains(contains)) {
                        return true;
                    }
                }
            }
            if (fromErrorString.contains(C2paError.CLAIM_SIGNATURE_MISMATCH) && !fromErrorString.contains(C2paError.SIGNING_CREDENTIAL_UNTRUSTED)) {
                return true;
            }
            Iterable<C2paError> iterable2 = fromErrorString;
            boolean z = iterable2 instanceof Collection;
            if (!z || !((Collection) iterable2).isEmpty()) {
                i2 = 0;
                for (C2paError c2paError : iterable2) {
                    if (c2paError == C2paError.SIGNING_CREDENTIAL_UNTRUSTED && (i2 = i2 + 1) < 0) {
                        C1195m.u0();
                        throw null;
                    }
                }
            } else {
                i2 = 0;
            }
            if (!z || !((Collection) iterable2).isEmpty()) {
                i7 = 0;
                for (C2paError c2paError2 : iterable2) {
                    if (c2paError2 == C2paError.GENERAL_ERROR && (i7 = i7 + 1) < 0) {
                        C1195m.u0();
                        throw null;
                    }
                }
            } else {
                i7 = 0;
            }
            if (i2 != i7) {
                if (!z || !((Collection) iterable2).isEmpty()) {
                    i8 = 0;
                    for (C2paError c2paError3 : iterable2) {
                        if (c2paError3 == C2paError.SIGNING_CREDENTIAL_UNTRUSTED && (i8 = i8 + 1) < 0) {
                            C1195m.u0();
                            throw null;
                        }
                    }
                } else {
                    i8 = 0;
                }
                if (!z || !((Collection) iterable2).isEmpty()) {
                    i10 = 0;
                    for (C2paError c2paError4 : iterable2) {
                        if (c2paError4 == C2paError.CLAIM_SIGNATURE_MISMATCH && (i10 = i10 + 1) < 0) {
                            C1195m.u0();
                            throw null;
                        }
                    }
                } else {
                    i10 = 0;
                }
                if (i8 != i10) {
                    return true;
                }
            }
            return false;
        }

        private final boolean checkInvalidV2(String str) {
            int i2;
            int i7;
            int i8;
            boolean z;
            boolean z3;
            ArrayList m12 = C1194l.m1(fromErrorString(str));
            if (!m12.isEmpty()) {
                if (m12.isEmpty()) {
                    i2 = 0;
                } else {
                    Iterator it = m12.iterator();
                    i2 = 0;
                    while (it.hasNext()) {
                        C2paError c2paError = (C2paError) it.next();
                        if (c2paError != C2paError.SIGNING_CREDENTIAL_EXPIRED && c2paError != C2paError.SIGNING_CREDENTIAL_REVOKED && c2paError != C2paError.CLAIM_SIGNATURE_MISMATCH && (i2 = i2 + 1) < 0) {
                            C1195m.u0();
                            throw null;
                        }
                    }
                }
                if (i2 <= 0) {
                    if (m12.isEmpty()) {
                        i7 = 0;
                    } else {
                        Iterator it2 = m12.iterator();
                        i7 = 0;
                        while (it2.hasNext()) {
                            C2paError c2paError2 = (C2paError) it2.next();
                            if (c2paError2 == C2paError.SIGNING_CREDENTIAL_EXPIRED || c2paError2 == C2paError.SIGNING_CREDENTIAL_REVOKED) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (z3 && (i7 = i7 + 1) < 0) {
                                C1195m.u0();
                                throw null;
                            }
                        }
                    }
                    if (m12.isEmpty()) {
                        i8 = 0;
                    } else {
                        Iterator it3 = m12.iterator();
                        i8 = 0;
                        while (it3.hasNext()) {
                            if (((C2paError) it3.next()) == C2paError.CLAIM_SIGNATURE_MISMATCH) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z && (i8 = i8 + 1) < 0) {
                                C1195m.u0();
                                throw null;
                            }
                        }
                    }
                    if (i7 <= 0 || i7 < i8) {
                        return true;
                    }
                }
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static final C2paError fromErrorString$lambda$0(C2paError c2paError, h hVar) {
            j.e(hVar, "it");
            return c2paError;
        }

        public final boolean checkInvalid(String str, C2paSoVersionInfo c2paSoVersionInfo) {
            j.e(str, "errString");
            j.e(c2paSoVersionInfo, "soVersion");
            int i2 = WhenMappings.$EnumSwitchMapping$0[c2paSoVersionInfo.ordinal()];
            if (i2 == 1) {
                return checkInvalidV2(str);
            }
            if (i2 == 2) {
                return checkInvalidV1(str);
            }
            throw new RuntimeException();
        }

        public final List<C2paError> fromErrorString(String str) {
            j.e(str, "errString");
            ArrayList arrayList = new ArrayList();
            for (C2paError c2paError : C2paError.getEntries()) {
                String errString = c2paError.getErrString();
                j.e(errString, GraphPathNodeBundleWrapper.BUNDLE_KEY_LITERAL);
                String quote = Pattern.quote(errString);
                j.d(quote, "quote(...)");
                m mVar = new m(quote);
                if (str.length() >= 0) {
                    u t02 = n.t0(new Sf.j((a) new k(0, mVar, str), (b) l.d), new f(12, (Object) c2paError));
                    ArrayList arrayList2 = new ArrayList();
                    for (Object invoke : t02.f3739a) {
                        arrayList2.add(t02.b.invoke(invoke));
                    }
                    arrayList.addAll(arrayList2);
                } else {
                    throw new IndexOutOfBoundsException("Start index out of bounds: 0, input length: " + str.length());
                }
            }
            if (str.length() > 0 && arrayList.isEmpty()) {
                arrayList.add(C2paError.C2PA_ERROR_UNKNOWN);
            }
            return arrayList;
        }

        private Companion() {
        }
    }

    static {
        C2paError[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private C2paError(String str) {
        this.errString = str;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final String getErrString() {
        return this.errString;
    }
}
