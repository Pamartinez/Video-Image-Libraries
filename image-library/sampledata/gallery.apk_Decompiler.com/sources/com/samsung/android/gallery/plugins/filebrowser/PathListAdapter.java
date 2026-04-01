package com.samsung.android.gallery.plugins.filebrowser;

import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PathListAdapter extends RecyclerView.Adapter<PathViewHolder> {
    private final Consumer<PathViewHolder> callback = new p(1, this);
    Consumer<String> consumer;
    List<String> list = new ArrayList();

    public PathListAdapter(Consumer<String> consumer2) {
        this.consumer = consumer2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(PathViewHolder pathViewHolder) {
        String joinText = StringCompat.joinText((CharSequence) "/", this.list.subList(0, pathViewHolder.getLayoutPosition() + 1));
        Consumer<String> consumer2 = this.consumer;
        if (consumer2 != null) {
            consumer2.accept(joinText);
        }
    }

    public int getItemCount() {
        return this.list.size();
    }

    public void update(String str) {
        Log.d("PathListAdapter", "update", str);
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.addAll((Collection) Stream.of(str.split("/")).collect(Collectors.toList()));
        }
        this.list = arrayList;
        notifyDataSetChanged();
    }

    public void onBindViewHolder(PathViewHolder pathViewHolder, int i2) {
        pathViewHolder.onBind(this.list.get(i2));
    }

    public PathViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return PathViewHolder.create(viewGroup).setOnClickListener(this.callback);
    }
}
