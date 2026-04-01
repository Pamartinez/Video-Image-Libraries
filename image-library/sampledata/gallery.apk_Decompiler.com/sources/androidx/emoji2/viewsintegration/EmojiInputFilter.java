package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.widget.TextView;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class EmojiInputFilter implements InputFilter {
    private EmojiCompat.InitCallback mInitCallback;
    private final TextView mTextView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class InitCallbackImpl extends EmojiCompat.InitCallback {
        private final Reference<EmojiInputFilter> mEmojiInputFilterReference;
        private final Reference<TextView> mViewRef;

        public InitCallbackImpl(TextView textView, EmojiInputFilter emojiInputFilter) {
            this.mViewRef = new WeakReference(textView);
            this.mEmojiInputFilterReference = new WeakReference(emojiInputFilter);
        }

        private boolean isInputFilterCurrentlyRegisteredOnTextView(TextView textView, InputFilter inputFilter) {
            InputFilter[] filters;
            if (inputFilter == null || textView == null || (filters = textView.getFilters()) == null) {
                return false;
            }
            for (InputFilter inputFilter2 : filters) {
                if (inputFilter2 == inputFilter) {
                    return true;
                }
            }
            return false;
        }

        public void onInitialized() {
            CharSequence text;
            CharSequence process;
            super.onInitialized();
            TextView textView = this.mViewRef.get();
            if (isInputFilterCurrentlyRegisteredOnTextView(textView, this.mEmojiInputFilterReference.get()) && textView.isAttachedToWindow() && (text = textView.getText()) != (process = EmojiCompat.get().process(text))) {
                int selectionStart = Selection.getSelectionStart(process);
                int selectionEnd = Selection.getSelectionEnd(process);
                textView.setText(process);
                if (process instanceof Spannable) {
                    EmojiInputFilter.updateSelection((Spannable) process, selectionStart, selectionEnd);
                }
            }
        }
    }

    public EmojiInputFilter(TextView textView) {
        this.mTextView = textView;
    }

    private EmojiCompat.InitCallback getInitCallback() {
        if (this.mInitCallback == null) {
            this.mInitCallback = new InitCallbackImpl(this.mTextView, this);
        }
        return this.mInitCallback;
    }

    public static void updateSelection(Spannable spannable, int i2, int i7) {
        if (i2 >= 0 && i7 >= 0) {
            Selection.setSelection(spannable, i2, i7);
        } else if (i2 >= 0) {
            Selection.setSelection(spannable, i2);
        } else if (i7 >= 0) {
            Selection.setSelection(spannable, i7);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r0 != 3) goto L_0x004a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.CharSequence filter(java.lang.CharSequence r3, int r4, int r5, android.text.Spanned r6, int r7, int r8) {
        /*
            r2 = this;
            android.widget.TextView r0 = r2.mTextView
            boolean r0 = r0.isInEditMode()
            if (r0 == 0) goto L_0x0009
            goto L_0x004a
        L_0x0009:
            androidx.emoji2.text.EmojiCompat r0 = androidx.emoji2.text.EmojiCompat.get()
            int r0 = r0.getLoadState()
            if (r0 == 0) goto L_0x004b
            r1 = 1
            if (r0 == r1) goto L_0x001a
            r4 = 3
            if (r0 == r4) goto L_0x004b
            goto L_0x004a
        L_0x001a:
            if (r8 != 0) goto L_0x002d
            if (r7 != 0) goto L_0x002d
            int r6 = r6.length()
            if (r6 != 0) goto L_0x002d
            android.widget.TextView r2 = r2.mTextView
            java.lang.CharSequence r2 = r2.getText()
            if (r3 != r2) goto L_0x002d
            return r3
        L_0x002d:
            if (r3 == 0) goto L_0x004a
            if (r4 != 0) goto L_0x0038
            int r2 = r3.length()
            if (r5 != r2) goto L_0x0038
            goto L_0x003c
        L_0x0038:
            java.lang.CharSequence r3 = r3.subSequence(r4, r5)
        L_0x003c:
            androidx.emoji2.text.EmojiCompat r2 = androidx.emoji2.text.EmojiCompat.get()
            r4 = 0
            int r5 = r3.length()
            java.lang.CharSequence r2 = r2.process(r3, r4, r5)
            return r2
        L_0x004a:
            return r3
        L_0x004b:
            androidx.emoji2.text.EmojiCompat r4 = androidx.emoji2.text.EmojiCompat.get()
            androidx.emoji2.text.EmojiCompat$InitCallback r2 = r2.getInitCallback()
            r4.registerInitCallback(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.viewsintegration.EmojiInputFilter.filter(java.lang.CharSequence, int, int, android.text.Spanned, int, int):java.lang.CharSequence");
    }
}
