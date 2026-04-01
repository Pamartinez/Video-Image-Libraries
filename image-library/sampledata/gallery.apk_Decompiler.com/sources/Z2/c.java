package Z2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum c {
    SAMSUNG_PAY_INDONESIA(d.DANA, d.EMVCo_IND),
    SAMSUNG_PAY_INDIA(d.UPI, d.UPI_PAYTM, d.PAYTM, d.EMVCo_BHARAT),
    PIX(d.EMVCo_PIX),
    OTHER(d.EMVCo_OTHER);
    
    private final Set<d> mPayResultTypeSet;

    /* access modifiers changed from: public */
    c(d... dVarArr) {
        HashSet hashSet = new HashSet();
        this.mPayResultTypeSet = hashSet;
        hashSet.addAll(Arrays.asList((d[]) dVarArr.clone()));
    }

    public final boolean a(d dVar) {
        return this.mPayResultTypeSet.contains(dVar);
    }
}
