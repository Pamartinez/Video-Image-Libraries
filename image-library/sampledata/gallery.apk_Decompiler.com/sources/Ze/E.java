package Ze;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum E {
    ONE_COLLECTION_PARAMETER("Ljava/util/Collection<+Ljava/lang/Object;>;", false),
    OBJECT_PARAMETER_NON_GENERIC((String) null, true),
    OBJECT_PARAMETER_GENERIC("Ljava/lang/Object;", true);
    
    private final boolean isObjectReplacedWithTypeParameter;
    private final String valueParametersSignature;

    static {
        E[] eArr;
        $ENTRIES = c.t(eArr);
    }

    /* access modifiers changed from: public */
    E(String str, boolean z) {
        this.valueParametersSignature = str;
        this.isObjectReplacedWithTypeParameter = z;
    }
}
