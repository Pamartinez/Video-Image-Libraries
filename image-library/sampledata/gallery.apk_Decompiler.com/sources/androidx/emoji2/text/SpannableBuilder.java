package androidx.emoji2.text;

import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import androidx.core.util.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SpannableBuilder extends SpannableStringBuilder {
    private final Class<?> mWatcherClass;
    private final List<WatcherWrapper> mWatchers = new ArrayList();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WatcherWrapper implements TextWatcher, SpanWatcher {
        private final AtomicInteger mBlockCalls = new AtomicInteger(0);
        final Object mObject;

        public WatcherWrapper(Object obj) {
            this.mObject = obj;
        }

        private boolean isEmojiSpan(Object obj) {
            return obj instanceof EmojiSpan;
        }

        public void afterTextChanged(Editable editable) {
            ((TextWatcher) this.mObject).afterTextChanged(editable);
        }

        public void beforeTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            ((TextWatcher) this.mObject).beforeTextChanged(charSequence, i2, i7, i8);
        }

        public final void blockCalls() {
            this.mBlockCalls.incrementAndGet();
        }

        public void onSpanAdded(Spannable spannable, Object obj, int i2, int i7) {
            if (this.mBlockCalls.get() <= 0 || !isEmojiSpan(obj)) {
                ((SpanWatcher) this.mObject).onSpanAdded(spannable, obj, i2, i7);
            }
        }

        public void onSpanChanged(Spannable spannable, Object obj, int i2, int i7, int i8, int i10) {
            if (this.mBlockCalls.get() <= 0 || !isEmojiSpan(obj)) {
                ((SpanWatcher) this.mObject).onSpanChanged(spannable, obj, i2, i7, i8, i10);
            }
        }

        public void onSpanRemoved(Spannable spannable, Object obj, int i2, int i7) {
            if (this.mBlockCalls.get() <= 0 || !isEmojiSpan(obj)) {
                ((SpanWatcher) this.mObject).onSpanRemoved(spannable, obj, i2, i7);
            }
        }

        public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            ((TextWatcher) this.mObject).onTextChanged(charSequence, i2, i7, i8);
        }

        public final void unblockCalls() {
            this.mBlockCalls.decrementAndGet();
        }
    }

    public SpannableBuilder(Class<?> cls, CharSequence charSequence) {
        super(charSequence);
        Preconditions.checkNotNull(cls, "watcherClass cannot be null");
        this.mWatcherClass = cls;
    }

    private void blockWatchers() {
        for (int i2 = 0; i2 < this.mWatchers.size(); i2++) {
            this.mWatchers.get(i2).blockCalls();
        }
    }

    public static SpannableBuilder create(Class<?> cls, CharSequence charSequence) {
        return new SpannableBuilder(cls, charSequence);
    }

    private void fireWatchers() {
        for (int i2 = 0; i2 < this.mWatchers.size(); i2++) {
            this.mWatchers.get(i2).onTextChanged(this, 0, length(), length());
        }
    }

    private WatcherWrapper getWatcherFor(Object obj) {
        for (int i2 = 0; i2 < this.mWatchers.size(); i2++) {
            WatcherWrapper watcherWrapper = this.mWatchers.get(i2);
            if (watcherWrapper.mObject == obj) {
                return watcherWrapper;
            }
        }
        return null;
    }

    private boolean isWatcher(Object obj) {
        return obj != null && isWatcher(obj.getClass());
    }

    private void unblockwatchers() {
        for (int i2 = 0; i2 < this.mWatchers.size(); i2++) {
            this.mWatchers.get(i2).unblockCalls();
        }
    }

    public void beginBatchEdit() {
        blockWatchers();
    }

    public void endBatchEdit() {
        unblockwatchers();
        fireWatchers();
    }

    public int getSpanEnd(Object obj) {
        WatcherWrapper watcherFor;
        if (isWatcher(obj) && (watcherFor = getWatcherFor(obj)) != null) {
            obj = watcherFor;
        }
        return super.getSpanEnd(obj);
    }

    public int getSpanFlags(Object obj) {
        WatcherWrapper watcherFor;
        if (isWatcher(obj) && (watcherFor = getWatcherFor(obj)) != null) {
            obj = watcherFor;
        }
        return super.getSpanFlags(obj);
    }

    public int getSpanStart(Object obj) {
        WatcherWrapper watcherFor;
        if (isWatcher(obj) && (watcherFor = getWatcherFor(obj)) != null) {
            obj = watcherFor;
        }
        return super.getSpanStart(obj);
    }

    public <T> T[] getSpans(int i2, int i7, Class<T> cls) {
        if (!isWatcher((Class<?>) cls)) {
            return super.getSpans(i2, i7, cls);
        }
        WatcherWrapper[] watcherWrapperArr = (WatcherWrapper[]) super.getSpans(i2, i7, WatcherWrapper.class);
        T[] tArr = (Object[]) Array.newInstance(cls, watcherWrapperArr.length);
        for (int i8 = 0; i8 < watcherWrapperArr.length; i8++) {
            tArr[i8] = watcherWrapperArr[i8].mObject;
        }
        return tArr;
    }

    public int nextSpanTransition(int i2, int i7, Class<WatcherWrapper> cls) {
        if (cls == null || isWatcher((Class<?>) cls)) {
            cls = WatcherWrapper.class;
        }
        return super.nextSpanTransition(i2, i7, cls);
    }

    public void removeSpan(Object obj) {
        WatcherWrapper watcherWrapper;
        if (isWatcher(obj)) {
            watcherWrapper = getWatcherFor(obj);
            if (watcherWrapper != null) {
                obj = watcherWrapper;
            }
        } else {
            watcherWrapper = null;
        }
        super.removeSpan(obj);
        if (watcherWrapper != null) {
            this.mWatchers.remove(watcherWrapper);
        }
    }

    public void setSpan(Object obj, int i2, int i7, int i8) {
        if (isWatcher(obj)) {
            WatcherWrapper watcherWrapper = new WatcherWrapper(obj);
            this.mWatchers.add(watcherWrapper);
            obj = watcherWrapper;
        }
        super.setSpan(obj, i2, i7, i8);
    }

    public CharSequence subSequence(int i2, int i7) {
        return new SpannableBuilder(this.mWatcherClass, this, i2, i7);
    }

    private boolean isWatcher(Class<?> cls) {
        return this.mWatcherClass == cls;
    }

    public SpannableStringBuilder delete(int i2, int i7) {
        super.delete(i2, i7);
        return this;
    }

    public SpannableStringBuilder insert(int i2, CharSequence charSequence) {
        super.insert(i2, charSequence);
        return this;
    }

    public SpannableStringBuilder replace(int i2, int i7, CharSequence charSequence) {
        blockWatchers();
        super.replace(i2, i7, charSequence);
        unblockwatchers();
        return this;
    }

    public SpannableStringBuilder insert(int i2, CharSequence charSequence, int i7, int i8) {
        super.insert(i2, charSequence, i7, i8);
        return this;
    }

    public SpannableBuilder(Class<?> cls, CharSequence charSequence, int i2, int i7) {
        super(charSequence, i2, i7);
        Preconditions.checkNotNull(cls, "watcherClass cannot be null");
        this.mWatcherClass = cls;
    }

    public SpannableStringBuilder replace(int i2, int i7, CharSequence charSequence, int i8, int i10) {
        blockWatchers();
        super.replace(i2, i7, charSequence, i8, i10);
        unblockwatchers();
        return this;
    }

    public SpannableStringBuilder append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    public SpannableStringBuilder append(char c5) {
        super.append(c5);
        return this;
    }

    public SpannableStringBuilder append(CharSequence charSequence, int i2, int i7) {
        super.append(charSequence, i2, i7);
        return this;
    }

    public SpannableStringBuilder append(CharSequence charSequence, Object obj, int i2) {
        super.append(charSequence, obj, i2);
        return this;
    }
}
