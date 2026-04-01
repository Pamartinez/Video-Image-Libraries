package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import androidx.emoji2.text.EmojiCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class EmojiKeyListener implements KeyListener {
    private final EmojiCompatHandleKeyDownHelper mEmojiCompatHandleKeyDownHelper;
    private final KeyListener mKeyListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmojiCompatHandleKeyDownHelper {
        public boolean handleKeyDown(Editable editable, int i2, KeyEvent keyEvent) {
            return EmojiCompat.handleOnKeyDown(editable, i2, keyEvent);
        }
    }

    public EmojiKeyListener(KeyListener keyListener) {
        this(keyListener, new EmojiCompatHandleKeyDownHelper());
    }

    public void clearMetaKeyState(View view, Editable editable, int i2) {
        this.mKeyListener.clearMetaKeyState(view, editable, i2);
    }

    public int getInputType() {
        return this.mKeyListener.getInputType();
    }

    public boolean onKeyDown(View view, Editable editable, int i2, KeyEvent keyEvent) {
        if (this.mEmojiCompatHandleKeyDownHelper.handleKeyDown(editable, i2, keyEvent) || this.mKeyListener.onKeyDown(view, editable, i2, keyEvent)) {
            return true;
        }
        return false;
    }

    public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
        return this.mKeyListener.onKeyOther(view, editable, keyEvent);
    }

    public boolean onKeyUp(View view, Editable editable, int i2, KeyEvent keyEvent) {
        return this.mKeyListener.onKeyUp(view, editable, i2, keyEvent);
    }

    public EmojiKeyListener(KeyListener keyListener, EmojiCompatHandleKeyDownHelper emojiCompatHandleKeyDownHelper) {
        this.mKeyListener = keyListener;
        this.mEmojiCompatHandleKeyDownHelper = emojiCompatHandleKeyDownHelper;
    }
}
