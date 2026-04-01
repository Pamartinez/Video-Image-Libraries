package com.samsung.android.gallery.app.ui.dialog.creature.picker;

import A4.C0381p;
import B8.e;
import S6.b;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.ICreaturePickerDialogView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.function.Consumer;
import u4.C0518a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreaturePickerAdapter<V extends ICreaturePickerDialogView> extends RecyclerView.Adapter<ImageTitleViewHolder> {
    protected final ArrayList<MediaItem> mList = new ArrayList<>();
    protected V mView;

    public CreaturePickerAdapter(V v) {
        this.mView = v;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$load$4(ThreadPool.JobContext jobContext) {
        Cursor query;
        synchronized (this.mList) {
            try {
                query = DbCompat.query("mp://People", (Consumer<QueryParams>) null);
                if (query != null) {
                    if (query.moveToFirst()) {
                        do {
                            this.mList.add(MediaItemLoader.load(query));
                        } while (query.moveToNext());
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return null;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$3(MediaItem mediaItem, View view) {
        this.mView.onItemClicked(mediaItem);
    }

    public int getItemCount() {
        int size;
        synchronized (this.mList) {
            size = this.mList.size();
        }
        return size;
    }

    public int getItemViewLayoutId() {
        return R.layout.recycler_item_merge_creature_layout;
    }

    public void load() {
        ThreadPool.getInstance().submit(new C0381p(15, this));
    }

    public void onBindViewHolder(ImageTitleViewHolder imageTitleViewHolder, int i2) {
        synchronized (this.mList) {
            MediaItem mediaItem = this.mList.get(i2);
            imageTitleViewHolder.bind(mediaItem);
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, ThumbKind.MEDIUM_KIND, new e(mediaItem, 6), new b(imageTitleViewHolder, 1));
            imageTitleViewHolder.itemView.setOnClickListener(new C0518a(0, this, mediaItem));
        }
    }

    public ImageTitleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(getItemViewLayoutId(), viewGroup, false);
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        layoutParams.width = (int) viewGroup.getResources().getDimension(R.dimen.search_face_tag_registered_name_icon_size);
        inflate.setLayoutParams(layoutParams);
        ImageTitleViewHolder imageTitleViewHolder = new ImageTitleViewHolder(inflate, i2);
        imageTitleViewHolder.setThumbnailShape(0, 0.0f);
        return imageTitleViewHolder;
    }
}
