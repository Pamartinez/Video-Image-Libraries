package com.samsung.android.livetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BrokenLineDetector {
    private final boolean couldBeBrokenLineBlock;

    public BrokenLineDetector(LttOcrResult.BlockInfo blockInfo) {
        AnonymousClass1 r0 = new Object() {
            boolean isLowercaseExist = false;
            boolean isUppercaseExist = false;

            public boolean isMixedCaseExist() {
                if (!this.isUppercaseExist || !this.isLowercaseExist) {
                    return false;
                }
                return true;
            }
        };
        blockInfo.getLineInfo().forEach(new b(0, r0));
        this.couldBeBrokenLineBlock = r0.isMixedCaseExist();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(AnonymousClass1 r1, LttOcrResult.LineInfo lineInfo) {
        char charAt = lineInfo.getString().trim().charAt(0);
        if (!r1.isUppercaseExist) {
            r1.isUppercaseExist = Character.isUpperCase(charAt);
        }
        if (!r1.isLowercaseExist) {
            r1.isLowercaseExist = Character.isLowerCase(charAt);
        }
    }

    public boolean isContinuousLine(LttOcrResult.LineInfo lineInfo) {
        if (!lineInfo.getString().trim().isEmpty() && this.couldBeBrokenLineBlock && Character.isLowerCase(lineInfo.getString().trim().charAt(0))) {
            return true;
        }
        return false;
    }
}
