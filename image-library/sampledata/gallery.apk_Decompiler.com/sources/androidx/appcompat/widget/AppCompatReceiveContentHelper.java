package androidx.appcompat.widget;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AppCompatReceiveContentHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OnDropApi24Impl {
        /* JADX INFO: finally extract failed */
        public static boolean onDropForTextView(DragEvent dragEvent, TextView textView, Activity activity) {
            activity.requestDragAndDropPermissions(dragEvent);
            int offsetForPosition = textView.getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
            textView.beginBatchEdit();
            try {
                Selection.setSelection((Spannable) textView.getText(), offsetForPosition);
                ViewCompat.performReceiveContent(textView, new ContentInfoCompat.Builder(dragEvent.getClipData(), 3).build());
                textView.endBatchEdit();
                return true;
            } catch (Throwable th) {
                textView.endBatchEdit();
                throw th;
            }
        }

        public static boolean onDropForView(DragEvent dragEvent, View view, Activity activity) {
            activity.requestDragAndDropPermissions(dragEvent);
            ViewCompat.performReceiveContent(view, new ContentInfoCompat.Builder(dragEvent.getClipData(), 3).build());
            return true;
        }
    }

    public static boolean maybeHandleDragEventViaPerformReceiveContent(View view, DragEvent dragEvent) {
        if (Build.VERSION.SDK_INT < 31 && dragEvent.getLocalState() == null && ViewCompat.getOnReceiveContentMimeTypes(view) != null) {
            Activity tryGetActivity = tryGetActivity(view);
            if (tryGetActivity == null) {
                Log.i("ReceiveContent", "Can't handle drop: no activity: view=" + view);
                return false;
            } else if (dragEvent.getAction() == 1) {
                if (!(view instanceof TextView)) {
                    return true;
                }
                return false;
            } else if (dragEvent.getAction() == 3) {
                if (view instanceof TextView) {
                    return OnDropApi24Impl.onDropForTextView(dragEvent, (TextView) view, tryGetActivity);
                }
                return OnDropApi24Impl.onDropForView(dragEvent, view, tryGetActivity);
            }
        }
        return false;
    }

    public static boolean maybeHandleMenuActionViaPerformReceiveContent(TextView textView, int i2) {
        ClipData clipData;
        int i7 = 0;
        if (Build.VERSION.SDK_INT >= 31 || ViewCompat.getOnReceiveContentMimeTypes(textView) == null || (i2 != 16908322 && i2 != 16908337)) {
            return false;
        }
        ClipboardManager clipboardManager = (ClipboardManager) textView.getContext().getSystemService("clipboard");
        if (clipboardManager == null) {
            clipData = null;
        } else {
            clipData = clipboardManager.getPrimaryClip();
        }
        if (clipData != null && clipData.getItemCount() > 0) {
            ContentInfoCompat.Builder builder = new ContentInfoCompat.Builder(clipData, 1);
            if (i2 != 16908322) {
                i7 = 1;
            }
            ViewCompat.performReceiveContent(textView, builder.setFlags(i7).build());
        }
        return true;
    }

    public static Activity tryGetActivity(View view) {
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }
}
