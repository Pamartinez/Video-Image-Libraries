package com.samsung.android.sdk.moneta.memory.entity.context;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "", "type", "", "<init>", "(Ljava/lang/String;II)V", "getType", "()I", "Home", "WorkPlace", "FrequentlyVisitedPlace", "Other", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PlaceType {
    Home(2),
    WorkPlace(3),
    FrequentlyVisitedPlace(4),
    Other(1);
    
    public static final Companion Companion = null;
    private final int type;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType$Companion;", "", "<init>", "()V", "fromInt", "Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "type", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final PlaceType fromInt(int i2) {
            Object obj;
            Iterator it = PlaceType.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((PlaceType) obj).getType() == i2) {
                    break;
                }
            }
            PlaceType placeType = (PlaceType) obj;
            if (placeType == null) {
                return PlaceType.Other;
            }
            return placeType;
        }

        private Companion() {
        }
    }

    static {
        PlaceType[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private PlaceType(int i2) {
        this.type = i2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int getType() {
        return this.type;
    }
}
