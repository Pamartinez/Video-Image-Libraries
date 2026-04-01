package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.TextView;
import androidx.emoji2.text.EmojiCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class EmojiInputConnection extends InputConnectionWrapper {
    private final EmojiCompatDeleteHelper mEmojiCompatDeleteHelper;
    private final TextView mTextView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmojiCompatDeleteHelper {
        public boolean handleDeleteSurroundingText(InputConnection inputConnection, Editable editable, int i2, int i7, boolean z) {
            return EmojiCompat.handleDeleteSurroundingText(inputConnection, editable, i2, i7, z);
        }

        public void updateEditorInfoAttrs(EditorInfo editorInfo) {
            if (EmojiCompat.isConfigured()) {
                EmojiCompat.get().updateEditorInfo(editorInfo);
            }
        }
    }

    public EmojiInputConnection(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
        this(textView, inputConnection, editorInfo, new EmojiCompatDeleteHelper());
    }

    private Editable getEditable() {
        return this.mTextView.getEditableText();
    }

    public boolean deleteSurroundingText(int i2, int i7) {
        int i8 = i2;
        int i10 = i7;
        if (this.mEmojiCompatDeleteHelper.handleDeleteSurroundingText(this, getEditable(), i8, i10, false) || super.deleteSurroundingText(i8, i10)) {
            return true;
        }
        return false;
    }

    public boolean deleteSurroundingTextInCodePoints(int i2, int i7) {
        int i8 = i2;
        int i10 = i7;
        if (this.mEmojiCompatDeleteHelper.handleDeleteSurroundingText(this, getEditable(), i8, i10, true) || super.deleteSurroundingTextInCodePoints(i8, i10)) {
            return true;
        }
        return false;
    }

    public EmojiInputConnection(TextView textView, InputConnection inputConnection, EditorInfo editorInfo, EmojiCompatDeleteHelper emojiCompatDeleteHelper) {
        super(inputConnection, false);
        this.mTextView = textView;
        this.mEmojiCompatDeleteHelper = emojiCompatDeleteHelper;
        emojiCompatDeleteHelper.updateEditorInfoAttrs(editorInfo);
    }
}
