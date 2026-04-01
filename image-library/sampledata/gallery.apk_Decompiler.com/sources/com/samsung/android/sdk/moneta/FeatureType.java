package com.samsung.android.sdk.moneta;

import Tf.v;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/FeatureType;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "PERSON_LINK_SERVICE", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum FeatureType {
    PERSON_LINK_SERVICE("PersonLinkService");
    
    public static final Companion Companion = null;
    private final String value;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/FeatureType$Companion;", "", "<init>", "()V", "fromString", "Lcom/samsung/android/sdk/moneta/FeatureType;", "input", "", "contains", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean contains(String str) {
            j.e(str, "input");
            C1295a<FeatureType> entries = FeatureType.getEntries();
            if (entries != null && entries.isEmpty()) {
                return false;
            }
            for (FeatureType name : entries) {
                if (v.p0(name.name(), str, true)) {
                    return true;
                }
            }
            return false;
        }

        public final FeatureType fromString(String str) {
            Object obj;
            j.e(str, "input");
            Iterator it = FeatureType.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (v.p0(((FeatureType) obj).name(), str, true)) {
                    break;
                }
            }
            return (FeatureType) obj;
        }

        private Companion() {
        }
    }

    static {
        FeatureType[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private FeatureType(String str) {
        this.value = str;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final String getValue() {
        return this.value;
    }
}
