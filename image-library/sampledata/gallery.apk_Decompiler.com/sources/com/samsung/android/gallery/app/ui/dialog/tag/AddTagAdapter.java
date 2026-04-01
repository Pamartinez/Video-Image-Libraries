package com.samsung.android.gallery.app.ui.dialog.tag;

import A.a;
import A4.C0381p;
import android.content.Context;
import android.database.Cursor;
import android.graphics.PointF;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.Consumer;
import u4.C0518a;
import x7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddTagAdapter extends GalleryListAdapter<NameViewHolder> implements Filterable {
    /* access modifiers changed from: private */
    public final Object LOCK = new Object();
    private Context mAppContext;
    /* access modifiers changed from: private */
    public ArrayList<String> mFilteredTagList = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<String> mMyTagList = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<String> mTotalList = new ArrayList<>();
    private IAddTagDialogView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NameViewHolder extends ListViewHolder {
        TextView mNameView;

        public NameViewHolder(View view) {
            super(view, 0);
        }

        public final void bindView(View view) {
            this.mNameView = (TextView) view.findViewById(R.id.name_text);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.itemView.setOnClickListener(onClickListener);
        }

        public void setText(Spannable spannable) {
            this.mNameView.setText(spannable);
        }
    }

    public AddTagAdapter(Context context, IAddTagDialogView iAddTagDialogView) {
        super(iAddTagDialogView.getBlackboard());
        this.mAppContext = context;
        this.mView = iAddTagDialogView;
        ThreadPool.getInstance().submit(new C0381p(18, this));
    }

    private void addHistoryList() {
        Cursor cursor = SearchHistory.getInstance().getCursor();
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        String string = cursor.getString(0);
                        if (!this.mTotalList.contains(string) && !StringCompat.includeSpecialCharacter(string)) {
                            this.mTotalList.add(string);
                        }
                    } while (cursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (cursor != null) {
            cursor.close();
            return;
        }
        return;
        throw th;
    }

    private Spannable getSpannableText(TextView textView, String str) {
        Spannable newSpannable;
        String inputText = this.mView.getInputText();
        int indexOf = str.toLowerCase(Locale.getDefault()).indexOf(inputText.toLowerCase(Locale.getDefault()));
        int length = inputText.length() + indexOf;
        try {
            String prefixForSpan = SeApiCompat.getPrefixForSpan(textView, str, str.subSequence(indexOf, length).toString());
            if (prefixForSpan != null) {
                int indexOf2 = str.toLowerCase().indexOf(prefixForSpan);
                if (indexOf2 > -1) {
                    try {
                        length = indexOf2 + prefixForSpan.length();
                        indexOf = indexOf2;
                    } catch (NullPointerException | StringIndexOutOfBoundsException unused) {
                        indexOf = indexOf2;
                        String str2 = this.TAG;
                        StringBuilder sb2 = new StringBuilder("wrong index : length = ");
                        sb2.append(str.length());
                        sb2.append(", start = ");
                        sb2.append(indexOf);
                        sb2.append(", end = ");
                        a.w(sb2, length, str2);
                        newSpannable = Spannable.Factory.getInstance().newSpannable(str);
                        newSpannable.setSpan(new ForegroundColorSpan(this.mAppContext.getColor(R.color.search_matched_text_color)), indexOf, length, 33);
                        return newSpannable;
                    }
                }
            }
        } catch (NullPointerException | StringIndexOutOfBoundsException unused2) {
            String str22 = this.TAG;
            StringBuilder sb22 = new StringBuilder("wrong index : length = ");
            sb22.append(str.length());
            sb22.append(", start = ");
            sb22.append(indexOf);
            sb22.append(", end = ");
            a.w(sb22, length, str22);
            newSpannable = Spannable.Factory.getInstance().newSpannable(str);
            newSpannable.setSpan(new ForegroundColorSpan(this.mAppContext.getColor(R.color.search_matched_text_color)), indexOf, length, 33);
            return newSpannable;
        }
        newSpannable = Spannable.Factory.getInstance().newSpannable(str);
        if (indexOf >= 0 && length <= newSpannable.length()) {
            newSpannable.setSpan(new ForegroundColorSpan(this.mAppContext.getColor(R.color.search_matched_text_color)), indexOf, length, 33);
        }
        return newSpannable;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$new$0(ThreadPool.JobContext jobContext) {
        loadMyTagList();
        this.mTotalList = (ArrayList) this.mMyTagList.clone();
        this.mFilteredTagList = (ArrayList) this.mMyTagList.clone();
        addHistoryList();
        ThreadUtil.postOnUiThread(new l(7, this));
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(String str, View view) {
        this.mView.onItemClicked(str);
    }

    private void loadMyTagList() {
        Cursor query = DbCompat.query("mp://myTag", (Consumer<QueryParams>) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        String subCategory = MediaItemLoader.load(query).getSubCategory();
                        if (!TextUtils.isEmpty(subCategory) && !StringCompat.includeSpecialCharacter(subCategory)) {
                            this.mMyTagList.add(subCategory);
                        }
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
            return;
        }
        return;
        throw th;
    }

    private void makeRoundCorner(NameViewHolder nameViewHolder, int i2) {
        if (i2 == 0 && getItemCount() == 1) {
            nameViewHolder.setRoundMode(15);
        } else if (i2 == 0) {
            nameViewHolder.setRoundMode(3);
        } else if (i2 == getItemCount() - 1) {
            nameViewHolder.setRoundMode(12);
        } else {
            nameViewHolder.setRoundMode(0);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            /* access modifiers changed from: private */
            public static /* synthetic */ void lambda$performFiltering$0(CharSequence charSequence, ArrayList arrayList, String str) {
                if (str.toLowerCase(Locale.getDefault()).contains(charSequence.toString().toLowerCase(Locale.getDefault()))) {
                    arrayList.add(str);
                }
            }

            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                Filter.FilterResults filterResults;
                synchronized (AddTagAdapter.this.LOCK) {
                    try {
                        filterResults = new Filter.FilterResults();
                        if (TextUtils.isEmpty(charSequence)) {
                            filterResults.values = AddTagAdapter.this.mMyTagList.clone();
                        } else {
                            ArrayList arrayList = new ArrayList();
                            AddTagAdapter.this.mTotalList.forEach(new a(charSequence, arrayList));
                            filterResults.values = arrayList;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return filterResults;
            }

            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                synchronized (AddTagAdapter.this.LOCK) {
                    try {
                        Object obj = filterResults.values;
                        if (obj != null) {
                            AddTagAdapter.this.mFilteredTagList = (ArrayList) obj;
                            AddTagAdapter.this.notifyDataSetChanged();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        };
    }

    public int getItemCount() {
        return this.mFilteredTagList.size();
    }

    public long getItemId(int i2) {
        return 0;
    }

    public NameViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new NameViewHolder(C0086a.d(viewGroup, R.layout.add_tag_list_view_holder, viewGroup, false));
    }

    public void onBindViewHolder(NameViewHolder nameViewHolder, int i2) {
        String str = this.mFilteredTagList.get(i2);
        nameViewHolder.setText(getSpannableText(nameViewHolder.mNameView, str));
        nameViewHolder.setOnClickListener(new C0518a(4, this, str));
        makeRoundCorner(nameViewHolder, i2);
        super.onBindViewHolder(nameViewHolder, i2);
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
    }
}
