package androidx.emoji2.viewsintegration;

import android.text.Editable;
import androidx.emoji2.text.SpannableBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class EmojiEditableFactory extends Editable.Factory {
    private static final Object INSTANCE_LOCK = new Object();
    private static volatile Editable.Factory sInstance;
    private static Class<?> sWatcherClass;

    private EmojiEditableFactory() {
        try {
            sWatcherClass = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, EmojiEditableFactory.class.getClassLoader());
        } catch (Throwable unused) {
        }
    }

    public static Editable.Factory getInstance() {
        if (sInstance == null) {
            synchronized (INSTANCE_LOCK) {
                try {
                    if (sInstance == null) {
                        sInstance = new EmojiEditableFactory();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public Editable newEditable(CharSequence charSequence) {
        Class<?> cls = sWatcherClass;
        if (cls != null) {
            return SpannableBuilder.create(cls, charSequence);
        }
        return super.newEditable(charSequence);
    }
}
