package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.MetadataRepo;
import java.util.Arrays;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class EmojiProcessor {
    private final int[] mEmojiAsDefaultStyleExceptions;
    private EmojiCompat.GlyphChecker mGlyphChecker;
    private final MetadataRepo mMetadataRepo;
    private final EmojiCompat.SpanFactory mSpanFactory;
    private final boolean mUseEmojiAsDefaultStyle;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CodepointIndexFinder {
        public static int findIndexBackward(CharSequence charSequence, int i2, int i7) {
            int length = charSequence.length();
            if (i2 < 0 || length < i2 || i7 < 0) {
                return -1;
            }
            while (true) {
                boolean z = false;
                while (i7 != 0) {
                    i2--;
                    if (i2 >= 0) {
                        char charAt = charSequence.charAt(i2);
                        if (z) {
                            if (!Character.isHighSurrogate(charAt)) {
                                return -1;
                            }
                            i7--;
                        } else if (!Character.isSurrogate(charAt)) {
                            i7--;
                        } else if (Character.isHighSurrogate(charAt)) {
                            return -1;
                        } else {
                            z = true;
                        }
                    } else if (z) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                return i2;
            }
        }

        public static int findIndexForward(CharSequence charSequence, int i2, int i7) {
            int length = charSequence.length();
            if (i2 < 0 || length < i2 || i7 < 0) {
                return -1;
            }
            while (true) {
                boolean z = false;
                while (i7 != 0) {
                    if (r7 < length) {
                        char charAt = charSequence.charAt(r7);
                        if (z) {
                            if (!Character.isLowSurrogate(charAt)) {
                                return -1;
                            }
                            i7--;
                            i2 = r7 + 1;
                        } else if (!Character.isSurrogate(charAt)) {
                            i7--;
                            r7++;
                        } else if (Character.isLowSurrogate(charAt)) {
                            return -1;
                        } else {
                            r7++;
                            z = true;
                        }
                    } else if (z) {
                        return -1;
                    } else {
                        return length;
                    }
                }
                return r7;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmojiProcessAddSpanCallback implements EmojiProcessCallback<UnprecomputeTextOnModificationSpannable> {
        private final EmojiCompat.SpanFactory mSpanFactory;
        public UnprecomputeTextOnModificationSpannable spannable;

        public EmojiProcessAddSpanCallback(UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable, EmojiCompat.SpanFactory spanFactory) {
            this.spannable = unprecomputeTextOnModificationSpannable;
            this.mSpanFactory = spanFactory;
        }

        public boolean handleEmoji(CharSequence charSequence, int i2, int i7, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            Spannable spannable2;
            if (typefaceEmojiRasterizer.isPreferredSystemRender()) {
                return true;
            }
            if (this.spannable == null) {
                if (charSequence instanceof Spannable) {
                    spannable2 = (Spannable) charSequence;
                } else {
                    spannable2 = new SpannableString(charSequence);
                }
                this.spannable = new UnprecomputeTextOnModificationSpannable(spannable2);
            }
            this.spannable.setSpan(this.mSpanFactory.createSpan(typefaceEmojiRasterizer), i2, i7, 33);
            return true;
        }

        public UnprecomputeTextOnModificationSpannable getResult() {
            return this.spannable;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EmojiProcessCallback<T> {
        T getResult();

        boolean handleEmoji(CharSequence charSequence, int i2, int i7, TypefaceEmojiRasterizer typefaceEmojiRasterizer);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MarkExclusionCallback implements EmojiProcessCallback<MarkExclusionCallback> {
        private final String mExclusion;

        public MarkExclusionCallback(String str) {
            this.mExclusion = str;
        }

        public MarkExclusionCallback getResult() {
            return this;
        }

        public boolean handleEmoji(CharSequence charSequence, int i2, int i7, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            if (!TextUtils.equals(charSequence.subSequence(i2, i7), this.mExclusion)) {
                return true;
            }
            typefaceEmojiRasterizer.setExclusion(true);
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ProcessorSm {
        private int mCurrentDepth;
        private MetadataRepo.Node mCurrentNode;
        private final int[] mEmojiAsDefaultStyleExceptions;
        private MetadataRepo.Node mFlushNode;
        private int mLastCodepoint;
        private final MetadataRepo.Node mRootNode;
        private int mState = 1;
        private final boolean mUseEmojiAsDefaultStyle;

        public ProcessorSm(MetadataRepo.Node node, boolean z, int[] iArr) {
            this.mRootNode = node;
            this.mCurrentNode = node;
            this.mUseEmojiAsDefaultStyle = z;
            this.mEmojiAsDefaultStyleExceptions = iArr;
        }

        private static boolean isEmojiStyle(int i2) {
            if (i2 == 65039) {
                return true;
            }
            return false;
        }

        private static boolean isTextStyle(int i2) {
            if (i2 == 65038) {
                return true;
            }
            return false;
        }

        private int reset() {
            this.mState = 1;
            this.mCurrentNode = this.mRootNode;
            this.mCurrentDepth = 0;
            return 1;
        }

        private boolean shouldUseEmojiPresentationStyleForSingleCodepoint() {
            if (this.mCurrentNode.getData().isDefaultEmoji() || isEmojiStyle(this.mLastCodepoint)) {
                return true;
            }
            if (this.mUseEmojiAsDefaultStyle) {
                if (this.mEmojiAsDefaultStyleExceptions == null) {
                    return true;
                }
                if (Arrays.binarySearch(this.mEmojiAsDefaultStyleExceptions, this.mCurrentNode.getData().getCodepointAt(0)) < 0) {
                    return true;
                }
            }
            return false;
        }

        public int check(int i2) {
            MetadataRepo.Node node = this.mCurrentNode.get(i2);
            int i7 = 2;
            if (this.mState != 2) {
                if (node == null) {
                    i7 = reset();
                } else {
                    this.mState = 2;
                    this.mCurrentNode = node;
                    this.mCurrentDepth = 1;
                }
            } else if (node != null) {
                this.mCurrentNode = node;
                this.mCurrentDepth++;
            } else if (isTextStyle(i2)) {
                i7 = reset();
            } else if (!isEmojiStyle(i2)) {
                if (this.mCurrentNode.getData() != null) {
                    i7 = 3;
                    if (this.mCurrentDepth != 1) {
                        this.mFlushNode = this.mCurrentNode;
                        reset();
                    } else if (shouldUseEmojiPresentationStyleForSingleCodepoint()) {
                        this.mFlushNode = this.mCurrentNode;
                        reset();
                    } else {
                        i7 = reset();
                    }
                } else {
                    i7 = reset();
                }
            }
            this.mLastCodepoint = i2;
            return i7;
        }

        public TypefaceEmojiRasterizer getCurrentMetadata() {
            return this.mCurrentNode.getData();
        }

        public TypefaceEmojiRasterizer getFlushMetadata() {
            return this.mFlushNode.getData();
        }

        public boolean isInFlushableState() {
            if (this.mState != 2 || this.mCurrentNode.getData() == null) {
                return false;
            }
            if (this.mCurrentDepth > 1 || shouldUseEmojiPresentationStyleForSingleCodepoint()) {
                return true;
            }
            return false;
        }
    }

    public EmojiProcessor(MetadataRepo metadataRepo, EmojiCompat.SpanFactory spanFactory, EmojiCompat.GlyphChecker glyphChecker, boolean z, int[] iArr, Set<int[]> set) {
        this.mSpanFactory = spanFactory;
        this.mMetadataRepo = metadataRepo;
        this.mGlyphChecker = glyphChecker;
        this.mUseEmojiAsDefaultStyle = z;
        this.mEmojiAsDefaultStyleExceptions = iArr;
        initExclusions(set);
    }

    private static boolean delete(Editable editable, KeyEvent keyEvent, boolean z) {
        EmojiSpan[] emojiSpanArr;
        if (hasModifiers(keyEvent)) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (!hasInvalidSelection(selectionStart, selectionEnd) && (emojiSpanArr = (EmojiSpan[]) editable.getSpans(selectionStart, selectionEnd, EmojiSpan.class)) != null && emojiSpanArr.length > 0) {
            int length = emojiSpanArr.length;
            int i2 = 0;
            while (i2 < length) {
                EmojiSpan emojiSpan = emojiSpanArr[i2];
                int spanStart = editable.getSpanStart(emojiSpan);
                int spanEnd = editable.getSpanEnd(emojiSpan);
                if ((!z || spanStart != selectionStart) && ((z || spanEnd != selectionStart) && (selectionStart <= spanStart || selectionStart >= spanEnd))) {
                    i2++;
                } else {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean handleDeleteSurroundingText(InputConnection inputConnection, Editable editable, int i2, int i7, boolean z) {
        int i8;
        int i10;
        if (editable != null && inputConnection != null && i2 >= 0 && i7 >= 0) {
            int selectionStart = Selection.getSelectionStart(editable);
            int selectionEnd = Selection.getSelectionEnd(editable);
            if (hasInvalidSelection(selectionStart, selectionEnd)) {
                return false;
            }
            if (z) {
                i10 = CodepointIndexFinder.findIndexBackward(editable, selectionStart, Math.max(i2, 0));
                i8 = CodepointIndexFinder.findIndexForward(editable, selectionEnd, Math.max(i7, 0));
                if (i10 == -1 || i8 == -1) {
                    return false;
                }
            } else {
                i10 = Math.max(selectionStart - i2, 0);
                i8 = Math.min(selectionEnd + i7, editable.length());
            }
            EmojiSpan[] emojiSpanArr = (EmojiSpan[]) editable.getSpans(i10, i8, EmojiSpan.class);
            if (emojiSpanArr != null && emojiSpanArr.length > 0) {
                for (EmojiSpan emojiSpan : emojiSpanArr) {
                    int spanStart = editable.getSpanStart(emojiSpan);
                    int spanEnd = editable.getSpanEnd(emojiSpan);
                    i10 = Math.min(spanStart, i10);
                    i8 = Math.max(spanEnd, i8);
                }
                int max = Math.max(i10, 0);
                int min = Math.min(i8, editable.length());
                inputConnection.beginBatchEdit();
                editable.delete(max, min);
                inputConnection.endBatchEdit();
                return true;
            }
        }
        return false;
    }

    public static boolean handleOnKeyDown(Editable editable, int i2, KeyEvent keyEvent) {
        boolean z;
        if (i2 == 67) {
            z = delete(editable, keyEvent, false);
        } else if (i2 != 112) {
            z = false;
        } else {
            z = delete(editable, keyEvent, true);
        }
        if (!z) {
            return false;
        }
        MetaKeyKeyListener.adjustMetaAfterKeypress(editable);
        return true;
    }

    private boolean hasGlyph(CharSequence charSequence, int i2, int i7, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        if (typefaceEmojiRasterizer.getHasGlyph() == 0) {
            typefaceEmojiRasterizer.setHasGlyph(this.mGlyphChecker.hasGlyph(charSequence, i2, i7, typefaceEmojiRasterizer.getSdkAdded()));
        }
        if (typefaceEmojiRasterizer.getHasGlyph() == 2) {
            return true;
        }
        return false;
    }

    private static boolean hasInvalidSelection(int i2, int i7) {
        if (i2 == -1 || i7 == -1 || i2 != i7) {
            return true;
        }
        return false;
    }

    private static boolean hasModifiers(KeyEvent keyEvent) {
        return !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState());
    }

    private void initExclusions(Set<int[]> set) {
        if (!set.isEmpty()) {
            for (int[] next : set) {
                String str = new String(next, 0, next.length);
                process(str, 0, str.length(), 1, true, new MarkExclusionCallback(str));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003c A[SYNTHETIC, Splitter:B:21:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0069 A[SYNTHETIC, Splitter:B:35:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00b9 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.CharSequence process(java.lang.CharSequence r11, int r12, int r13, int r14, boolean r15) {
        /*
            r10 = this;
            boolean r1 = r11 instanceof androidx.emoji2.text.SpannableBuilder
            if (r1 == 0) goto L_0x000a
            r0 = r11
            androidx.emoji2.text.SpannableBuilder r0 = (androidx.emoji2.text.SpannableBuilder) r0
            r0.beginBatchEdit()
        L_0x000a:
            java.lang.Class<androidx.emoji2.text.EmojiSpan> r0 = androidx.emoji2.text.EmojiSpan.class
            if (r1 != 0) goto L_0x0031
            boolean r2 = r11 instanceof android.text.Spannable     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x0013
            goto L_0x0031
        L_0x0013:
            boolean r2 = r11 instanceof android.text.Spanned     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x002f
            r2 = r11
            android.text.Spanned r2 = (android.text.Spanned) r2     // Catch:{ all -> 0x002a }
            int r3 = r12 + -1
            int r4 = r13 + 1
            int r2 = r2.nextSpanTransition(r3, r4, r0)     // Catch:{ all -> 0x002a }
            if (r2 > r13) goto L_0x002f
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r2 = new androidx.emoji2.text.UnprecomputeTextOnModificationSpannable     // Catch:{ all -> 0x002a }
            r2.<init>((java.lang.CharSequence) r11)     // Catch:{ all -> 0x002a }
            goto L_0x0039
        L_0x002a:
            r0 = move-exception
            r10 = r0
            r3 = r11
            goto L_0x00ba
        L_0x002f:
            r2 = 0
            goto L_0x0039
        L_0x0031:
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r2 = new androidx.emoji2.text.UnprecomputeTextOnModificationSpannable     // Catch:{ all -> 0x00b0 }
            r3 = r11
            android.text.Spannable r3 = (android.text.Spannable) r3     // Catch:{ all -> 0x00b0 }
            r2.<init>((android.text.Spannable) r3)     // Catch:{ all -> 0x00b0 }
        L_0x0039:
            r3 = 0
            if (r2 == 0) goto L_0x0065
            java.lang.Object[] r4 = r2.getSpans(r12, r13, r0)     // Catch:{ all -> 0x002a }
            androidx.emoji2.text.EmojiSpan[] r4 = (androidx.emoji2.text.EmojiSpan[]) r4     // Catch:{ all -> 0x002a }
            if (r4 == 0) goto L_0x0065
            int r5 = r4.length     // Catch:{ all -> 0x002a }
            if (r5 <= 0) goto L_0x0065
            int r5 = r4.length     // Catch:{ all -> 0x002a }
            r6 = r3
        L_0x0049:
            if (r6 >= r5) goto L_0x0065
            r7 = r4[r6]     // Catch:{ all -> 0x002a }
            int r8 = r2.getSpanStart(r7)     // Catch:{ all -> 0x002a }
            int r9 = r2.getSpanEnd(r7)     // Catch:{ all -> 0x002a }
            if (r8 == r13) goto L_0x005a
            r2.removeSpan(r7)     // Catch:{ all -> 0x002a }
        L_0x005a:
            int r12 = java.lang.Math.min(r8, r12)     // Catch:{ all -> 0x002a }
            int r13 = java.lang.Math.max(r9, r13)     // Catch:{ all -> 0x002a }
            int r6 = r6 + 1
            goto L_0x0049
        L_0x0065:
            r4 = r12
            r5 = r13
            if (r4 == r5) goto L_0x006f
            int r12 = r11.length()     // Catch:{ all -> 0x00b0 }
            if (r4 < r12) goto L_0x0071
        L_0x006f:
            r3 = r11
            goto L_0x00b3
        L_0x0071:
            r12 = 2147483647(0x7fffffff, float:NaN)
            if (r14 == r12) goto L_0x0084
            if (r2 == 0) goto L_0x0084
            int r12 = r2.length()     // Catch:{ all -> 0x002a }
            java.lang.Object[] r12 = r2.getSpans(r3, r12, r0)     // Catch:{ all -> 0x002a }
            androidx.emoji2.text.EmojiSpan[] r12 = (androidx.emoji2.text.EmojiSpan[]) r12     // Catch:{ all -> 0x002a }
            int r12 = r12.length     // Catch:{ all -> 0x002a }
            int r14 = r14 - r12
        L_0x0084:
            r6 = r14
            androidx.emoji2.text.EmojiProcessor$EmojiProcessAddSpanCallback r8 = new androidx.emoji2.text.EmojiProcessor$EmojiProcessAddSpanCallback     // Catch:{ all -> 0x00b0 }
            androidx.emoji2.text.EmojiCompat$SpanFactory r12 = r10.mSpanFactory     // Catch:{ all -> 0x00b0 }
            r8.<init>(r2, r12)     // Catch:{ all -> 0x00b0 }
            r2 = r10
            r3 = r11
            r7 = r15
            java.lang.Object r10 = r2.process(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00a4 }
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r10 = (androidx.emoji2.text.UnprecomputeTextOnModificationSpannable) r10     // Catch:{ all -> 0x00a4 }
            if (r10 == 0) goto L_0x00a7
            android.text.Spannable r10 = r10.getUnwrappedSpannable()     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x00a3
            r11 = r3
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
            r11.endBatchEdit()
        L_0x00a3:
            return r10
        L_0x00a4:
            r0 = move-exception
        L_0x00a5:
            r10 = r0
            goto L_0x00ba
        L_0x00a7:
            if (r1 == 0) goto L_0x00af
            r11 = r3
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
        L_0x00ac:
            r11.endBatchEdit()
        L_0x00af:
            return r3
        L_0x00b0:
            r0 = move-exception
            r3 = r11
            goto L_0x00a5
        L_0x00b3:
            if (r1 == 0) goto L_0x00b9
            r11 = r3
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
            goto L_0x00ac
        L_0x00b9:
            return r3
        L_0x00ba:
            if (r1 == 0) goto L_0x00c2
            r11 = r3
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
            r11.endBatchEdit()
        L_0x00c2:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.EmojiProcessor.process(java.lang.CharSequence, int, int, int, boolean):java.lang.CharSequence");
    }

    private <T> T process(CharSequence charSequence, int i2, int i7, int i8, boolean z, EmojiProcessCallback<T> emojiProcessCallback) {
        int i10;
        ProcessorSm processorSm = new ProcessorSm(this.mMetadataRepo.getRootNode(), this.mUseEmojiAsDefaultStyle, this.mEmojiAsDefaultStyleExceptions);
        int codePointAt = Character.codePointAt(charSequence, i2);
        int i11 = 0;
        boolean z3 = true;
        loop0:
        while (true) {
            int i12 = codePointAt;
            while (true) {
                i10 = i2;
                while (i2 < i7 && i11 < i8 && z3) {
                    int check = processorSm.check(i12);
                    if (check == 1) {
                        i2 = Character.charCount(Character.codePointAt(charSequence, i10)) + i10;
                        if (i2 < i7) {
                            break;
                        }
                    } else if (check == 2) {
                        int charCount = Character.charCount(i12) + i2;
                        if (charCount < i7) {
                            i12 = Character.codePointAt(charSequence, charCount);
                        }
                        i2 = charCount;
                    } else if (check == 3) {
                        if (z || !hasGlyph(charSequence, i10, i2, processorSm.getFlushMetadata())) {
                            z3 = emojiProcessCallback.handleEmoji(charSequence, i10, i2, processorSm.getFlushMetadata());
                            i11++;
                        }
                    }
                }
            }
            codePointAt = Character.codePointAt(charSequence, i2);
        }
        if (processorSm.isInFlushableState() && i11 < i8 && z3 && (z || !hasGlyph(charSequence, i10, i2, processorSm.getCurrentMetadata()))) {
            emojiProcessCallback.handleEmoji(charSequence, i10, i2, processorSm.getCurrentMetadata());
        }
        return emojiProcessCallback.getResult();
    }
}
