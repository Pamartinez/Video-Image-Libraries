package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.appcompat.R$styleable;
import androidx.emoji2.viewsintegration.EmojiEditTextHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AppCompatEmojiEditTextHelper {
    private final EmojiEditTextHelper mEmojiEditTextHelper;
    private final EditText mView;

    public AppCompatEmojiEditTextHelper(EditText editText) {
        this.mView = editText;
        this.mEmojiEditTextHelper = new EmojiEditTextHelper(editText, false);
    }

    public KeyListener getKeyListener(KeyListener keyListener) {
        if (isEmojiCapableKeyListener(keyListener)) {
            return this.mEmojiEditTextHelper.getKeyListener(keyListener);
        }
        return keyListener;
    }

    public boolean isEmojiCapableKeyListener(KeyListener keyListener) {
        return !(keyListener instanceof NumberKeyListener);
    }

    /* JADX INFO: finally extract failed */
    public void loadFromAttributes(AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i2, 0);
        try {
            int i7 = R$styleable.AppCompatTextView_emojiCompatEnabled;
            boolean z = true;
            if (obtainStyledAttributes.hasValue(i7)) {
                z = obtainStyledAttributes.getBoolean(i7, true);
            }
            obtainStyledAttributes.recycle();
            setEnabled(z);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public InputConnection onCreateInputConnection(InputConnection inputConnection, EditorInfo editorInfo) {
        return this.mEmojiEditTextHelper.onCreateInputConnection(inputConnection, editorInfo);
    }

    public void setEnabled(boolean z) {
        this.mEmojiEditTextHelper.setEnabled(z);
    }
}
