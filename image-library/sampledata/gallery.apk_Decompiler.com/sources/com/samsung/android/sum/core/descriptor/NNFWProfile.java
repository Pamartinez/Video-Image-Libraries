package com.samsung.android.sum.core.descriptor;

import com.samsung.android.sum.core.types.HwUnit;
import com.samsung.android.sum.core.types.nn.NNFW;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NNFWProfile implements Serializable {
    private static final long serialVersionUID = 1;
    private final NNFW fw;
    private final HwUnit hw;
    private int units;

    public NNFWProfile(NNFW nnfw, HwUnit hwUnit, int i2) {
        this.fw = nnfw;
        this.hw = hwUnit;
        this.units = i2;
    }

    public List<NNFWProfile> flatten() {
        int i2 = this.units;
        this.units = 1;
        return Collections.nCopies(i2, this);
    }

    public NNFW getFw() {
        return this.fw;
    }

    public HwUnit getHw() {
        return this.hw;
    }

    public int getUnits() {
        return this.units;
    }
}
