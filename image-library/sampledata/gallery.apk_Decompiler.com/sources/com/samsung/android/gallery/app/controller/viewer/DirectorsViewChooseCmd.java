package com.samsung.android.gallery.app.controller.viewer;

import U3.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DirectorsViewChooseCmd extends BaseCommand {
    private Consumer<Integer> mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Item {
        private int descriptionResId;
        private int iconResId;
        private int titleResId;

        public /* synthetic */ Item(int i2, int i7, int i8, int i10) {
            this(i2, i7, i8);
        }

        public String toString() {
            return this.iconResId + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.titleResId + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.descriptionResId;
        }

        private Item(int i2, int i7, int i8) {
            this.iconResId = i2;
            this.titleResId = i7;
            this.descriptionResId = i8;
        }
    }

    private String[] createItemArrays() {
        return new String[]{new Item(R.drawable.gallery_ic_edit_directors_view, R.string.dual_video, R.string.dual_video_description, 0).toString(), new Item(R.drawable.gallery_ic_edit_singlevideo, R.string.single_video, R.string.single_video_description, 0).toString()};
    }

    /* access modifiers changed from: private */
    public void onSelected(EventContext eventContext, ArrayList<Object> arrayList) {
        Object obj;
        int i2;
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("onDataCompleted ");
        if (arrayList != null) {
            obj = arrayList.get(0);
        } else {
            obj = null;
        }
        sb2.append(obj);
        Log.d(str, sb2.toString());
        Consumer<Integer> consumer = this.mListener;
        if (consumer != null) {
            if (arrayList != null) {
                i2 = ((Integer) arrayList.get(0)).intValue();
            } else {
                i2 = -1;
            }
            consumer.accept(Integer.valueOf(i2));
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Consumer<Integer> consumer;
        if (objArr.length > 0) {
            consumer = objArr[0];
        } else {
            consumer = null;
        }
        this.mListener = consumer;
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/CustomChooser").appendArg("title", (int) R.string.choose_how_to_edit).appendArg("list_chooser_items", createItemArrays()).build()).setOnDataCompleteListener(new a(12, this)).start();
    }
}
