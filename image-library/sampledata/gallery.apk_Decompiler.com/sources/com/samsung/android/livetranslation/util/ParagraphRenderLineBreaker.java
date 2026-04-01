package com.samsung.android.livetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ParagraphRenderLineBreaker implements LineBreaker {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BulletParagraphDetector {
        private static final String SPECIAL_CHAR = "•-*#+@0123456789";
        private final boolean isBulletParagraph;

        public BulletParagraphDetector(LttOcrResult.BlockInfo blockInfo) {
            boolean z;
            double count = (double) blockInfo.getLineInfo().stream().filter(new f(1)).count();
            if (blockInfo.getLineInfo().isEmpty() || count / ((double) blockInfo.getLineInfo().size()) < 0.4d) {
                z = false;
            } else {
                z = true;
            }
            this.isBulletParagraph = z;
        }

        public boolean isBulletLine(LttOcrResult.LineInfo lineInfo) {
            String trim = lineInfo.getString().trim();
            if (!trim.isEmpty() && this.isBulletParagraph && SPECIAL_CHAR.contains(Character.toString(trim.charAt(0)))) {
                return true;
            }
            return false;
        }
    }

    private static int getFirstWordWidth(LttOcrResult.LineInfo lineInfo) {
        if (lineInfo.getWordInfo().isEmpty()) {
            return 0;
        }
        LttOcrResult.WordInfo wordInfo = lineInfo.getWordInfo().get(0);
        int width = wordInfo.getRect().width();
        if (wordInfo.getString().length() > 3 || lineInfo.getWordInfo().size() <= 1) {
            return width;
        }
        return lineInfo.getWordInfo().get(1).getRect().width() + width;
    }

    public String getLinedString(LttOcrResult.BlockInfo blockInfo) {
        StringBuilder sb2 = new StringBuilder();
        int maxLineWidth = BlockInfoUtil.getMaxLineWidth(blockInfo);
        BrokenLineDetector brokenLineDetector = new BrokenLineDetector(blockInfo);
        BulletParagraphDetector bulletParagraphDetector = new BulletParagraphDetector(blockInfo);
        for (int i2 = 0; i2 < blockInfo.getLineInfo().size(); i2++) {
            LttOcrResult.LineInfo lineInfo = blockInfo.getLineInfo().get(i2);
            sb2.append(lineInfo.getString());
            if (i2 != blockInfo.getLineInfo().size() - 1) {
                LttOcrResult.LineInfo lineInfo2 = blockInfo.getLineInfo().get(i2 + 1);
                if (!lineInfo2.getWordInfo().isEmpty() && ((maxLineWidth - lineInfo.getRect().width() > getFirstWordWidth(lineInfo2) && !brokenLineDetector.isContinuousLine(lineInfo2)) || bulletParagraphDetector.isBulletLine(lineInfo2))) {
                    sb2.append("\n");
                }
            }
        }
        return sb2.toString();
    }
}
