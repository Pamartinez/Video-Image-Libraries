package com.samsung.android.gallery.widget.tag;

import G6.a;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.window.embedding.c;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MyTagAdapter2 extends RecyclerView.Adapter<MyTagViewHolder2> {
    private final Object LOCK = new Object();
    private final CharSequence TAG;
    private int mCount = 0;
    private ArrayList<MediaItem> mData = new ArrayList<>();
    private final ListViewHolder.OnItemClickListener mListener;

    public MyTagAdapter2(CharSequence charSequence, ListViewHolder.OnItemClickListener onItemClickListener) {
        this.TAG = charSequence;
        this.mListener = onItemClickListener;
    }

    private ArrayList<MediaItem> loadTagData(MediaItem mediaItem) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        Cursor query = DbCompat.query("mp://myTag", new a(mediaItem, 10));
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    arrayList.add(MediaItemLoader.load(query));
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    public ArrayList<MediaItem> getData() {
        return this.mData;
    }

    public int getDataCount() {
        return this.mCount;
    }

    public int getItemCount() {
        int i2 = this.mCount;
        if (i2 > 0) {
            return i2;
        }
        return 1;
    }

    public int getItemViewType(int i2) {
        if (this.mCount == 0) {
            return -4;
        }
        return 0;
    }

    public Runnable loadData(MediaItem mediaItem) {
        try {
            ThreadUtil.postOnUiThread(new c(16, this, loadTagData(mediaItem)));
            return null;
        } catch (Exception e) {
            CharSequence charSequence = this.TAG;
            Log.e(charSequence, "loadData failed " + MediaItemUtil.getSimpleLog(mediaItem), (Throwable) e);
            return null;
        }
    }

    /* renamed from: setData */
    public void lambda$loadData$0(ArrayList<MediaItem> arrayList) {
        synchronized (this.LOCK) {
            this.mData = arrayList;
            this.mCount = arrayList.size();
            notifyDataSetChanged();
        }
    }

    public void onBindViewHolder(MyTagViewHolder2 myTagViewHolder2, int i2) {
        if (myTagViewHolder2.getItemViewType() == 0) {
            myTagViewHolder2.setOnItemClickListener(this.mListener);
            myTagViewHolder2.bind(this.mData.get(i2));
        } else if (ViewHolderValue.isFooter(myTagViewHolder2.getItemViewType())) {
            myTagViewHolder2.setOnItemClickListener(this.mListener);
            myTagViewHolder2.bind(new MediaItem());
        }
    }

    public MyTagViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new MyTagViewHolder2(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.viewer_tag_item_layout, viewGroup, false), i2);
    }
}
