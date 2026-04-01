package com.samsung.android.gallery.app.ui.list.search.editcreature;

import Bb.m;
import C3.C0392b;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import c0.C0086a;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.TextViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Optional;
import n5.e;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TagCreatureAdapter extends BaseAdapter implements Filterable {
    private final Object LOCK = new Object();
    private final ArrayList<CreatureNameData> mData = new ArrayList<>();
    private ArrayFilter mFilter;
    /* access modifiers changed from: private */
    public ArrayList<CreatureNameData> mFilteredData = new ArrayList<>();
    private String mFilteredString;
    private final LayoutInflater mInflater;
    private final Runnable mNotifyDataSetChanged = new t(14, this);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ArrayFilter extends Filter {
        public /* synthetic */ ArrayFilter(TagCreatureAdapter tagCreatureAdapter, int i2) {
            this();
        }

        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            String str;
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (charSequence == null) {
                str = "";
            } else {
                str = charSequence.toString().toLowerCase(Locale.getDefault());
            }
            ArrayList e = TagCreatureAdapter.this.getFilteredItems(str);
            filterResults.values = e;
            filterResults.count = e.size();
            return filterResults;
        }

        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            ArrayList arrayList;
            TagCreatureAdapter tagCreatureAdapter = TagCreatureAdapter.this;
            Object obj = filterResults.values;
            if (obj == null) {
                arrayList = new ArrayList();
            } else {
                arrayList = (ArrayList) obj;
            }
            tagCreatureAdapter.mFilteredData = arrayList;
            Log.d("TagCreatureAdapter", "ArrayFilter {" + TagCreatureAdapter.this.mFilteredData.size() + "}");
            TagCreatureAdapter.this.notifyDataSetChanged();
        }

        private ArrayFilter() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewHolder {
        private CreatureNameData mCreatureData;
        private final ImageView mNameImageView;
        private final TextView mNameInitialView;
        /* access modifiers changed from: private */
        public final TextView mNameTextView;

        public ViewHolder(View view) {
            this.mNameTextView = (TextView) view.findViewById(R.id.creature_name_view);
            this.mNameImageView = (ImageView) view.findViewById(R.id.creature_name_view_image);
            this.mNameInitialView = (TextView) view.findViewById(R.id.creature_name_view_text);
        }

        /* access modifiers changed from: private */
        /* renamed from: bindImageOnUi */
        public void lambda$bindImage$0(CreatureNameData creatureNameData, Bitmap bitmap) {
            int i2;
            if (!isRecycled(creatureNameData)) {
                TextView textView = this.mNameInitialView;
                if (bitmap != null) {
                    i2 = 8;
                } else {
                    i2 = 0;
                }
                textView.setVisibility(i2);
                this.mNameImageView.setImageBitmap(bitmap);
                this.mNameImageView.setClipToOutline(true);
            }
        }

        private void setContactName(CreatureNameData creatureNameData) {
            String str;
            if (creatureNameData.isMyProfile()) {
                str = this.mNameTextView.getResources().getString(R.string.f2235me);
            } else {
                str = creatureNameData.getName();
            }
            this.mNameTextView.setText(str);
        }

        public void bindData(CreatureNameData creatureNameData, String str) {
            int i2;
            int i7;
            Bitmap faceBitmap = creatureNameData.getFaceBitmap();
            int i8 = 0;
            boolean z = true;
            if (faceBitmap != null) {
                this.mNameImageView.setImageBitmap(faceBitmap);
                this.mNameImageView.setClipToOutline(true);
            } else {
                String initialLetter = creatureNameData.getInitialLetter();
                Drawable contactPresetDrawable = creatureNameData.getContactPresetDrawable(this.mNameImageView.getContext());
                if (contactPresetDrawable != null) {
                    this.mNameImageView.setImageDrawable(contactPresetDrawable);
                    this.mNameImageView.setClipToOutline(true);
                } else {
                    ImageView imageView = this.mNameImageView;
                    if (initialLetter != null) {
                        i7 = R.drawable.initial_type_thumbnail;
                    } else {
                        i7 = R.drawable.photo_id_default_thumbnail;
                    }
                    imageView.setImageResource(i7);
                }
                if (contactPresetDrawable == null) {
                    z = false;
                }
                TextView textView = this.mNameInitialView;
                Context context = textView.getContext();
                if (z) {
                    i2 = R.color.white_color;
                } else {
                    i2 = R.color.photo_id_stroke_color;
                }
                textView.setTextColor(context.getColor(i2));
                this.mNameInitialView.setText(initialLetter);
            }
            TextView textView2 = this.mNameInitialView;
            if (faceBitmap != null) {
                i8 = 8;
            }
            textView2.setVisibility(i8);
            setContactName(creatureNameData);
            TextViewUtils.highlightKeywordIgnoreTag(this.mNameTextView.getContext(), this.mNameTextView, str);
        }

        public void bindImage(CreatureNameData creatureNameData, Bitmap bitmap) {
            ThreadUtil.runOnUiThread(this.mNameImageView, new a(this, creatureNameData, bitmap));
        }

        public void bindItem(CreatureNameData creatureNameData) {
            this.mCreatureData = creatureNameData;
        }

        public boolean isRecycled(CreatureNameData creatureNameData) {
            CreatureNameData creatureNameData2 = this.mCreatureData;
            if (creatureNameData2 == null || !creatureNameData2.equals(creatureNameData)) {
                return true;
            }
            return false;
        }

        public void recycle() {
            this.mCreatureData = null;
            this.mNameInitialView.setText((CharSequence) null);
            this.mNameInitialView.setText((CharSequence) null);
            this.mNameImageView.setImageBitmap((Bitmap) null);
        }
    }

    public TagCreatureAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    /* access modifiers changed from: private */
    public ArrayList<CreatureNameData> getFilteredItems(String str) {
        ArrayList<CreatureNameData> arrayList = new ArrayList<>();
        Iterator<CreatureNameData> it = this.mData.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            CreatureNameData next = it.next();
            if (next.contains(str)) {
                if (str.length() <= 0) {
                    z = false;
                }
                if (isAvailableContactType(next, z)) {
                    arrayList.add(next);
                }
            }
        }
        int size = arrayList.size() - 1;
        if (size >= 0 && arrayList.get(size).isHeader()) {
            arrayList.remove(size);
        }
        return arrayList;
    }

    private boolean hasHeaderLayout(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null || viewHolder.mNameTextView == null) {
            return true;
        }
        return false;
    }

    private boolean isAvailableContactType(CreatureNameData creatureNameData, boolean z) {
        if (!z && !creatureNameData.isContact()) {
            return true;
        }
        if (!z || creatureNameData.isFrequentlyUsed()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBitmap$1(CreatureNameData creatureNameData, View view, int i2, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ViewHolder viewHolder;
        creatureNameData.setFaceBitmap(bitmap);
        if (bitmap != null && (view instanceof ListView)) {
            ListView listView = (ListView) view;
            View childAt = listView.getChildAt(listView.getHeaderViewsCount() + i2);
            if (childAt != null) {
                viewHolder = (ViewHolder) childAt.getTag();
            } else {
                viewHolder = null;
            }
            if (viewHolder != null) {
                viewHolder.bindImage(creatureNameData, bitmap);
                return;
            }
            view.removeCallbacks(this.mNotifyDataSetChanged);
            view.postDelayed(this.mNotifyDataSetChanged, 8);
        }
    }

    private void loadBitmap(View view, int i2, CreatureNameData creatureNameData) {
        if (creatureNameData.getFaceBitmap() == null && creatureNameData.hasValidPhoto()) {
            ThumbnailLoader.getInstance().loadThumbnail(creatureNameData.getMediaItem(), ThumbKind.SMALL_NC_KIND, new m((Object) this, (Object) creatureNameData, (Object) view, i2, 6));
        }
    }

    public void filter(CharSequence charSequence) {
        this.mFilteredString = charSequence.toString();
        getFilter().filter(this.mFilteredString);
    }

    public CreatureNameData getContactNameData(int i2) {
        return this.mFilteredData.get(i2);
    }

    public int getCount() {
        return this.mFilteredData.size();
    }

    public Filter getFilter() {
        if (this.mFilter == null) {
            this.mFilter = new ArrayFilter(this, 0);
        }
        return this.mFilter;
    }

    public Object getItem(int i2) {
        return Optional.ofNullable(getContactNameData(i2)).map(new e(21)).orElse((Object) null);
    }

    public long getItemId(int i2) {
        try {
            return ((Long) Optional.ofNullable(this.mFilteredData.get(i2)).map(new e(20)).orElse(0L)).longValue();
        } catch (IndexOutOfBoundsException unused) {
            StringBuilder o2 = C0086a.o(i2, "getItemId(): IndexOutOfBoundsException :: pos = ", ", filtered size = ");
            o2.append(getCount());
            o2.append(", total = ");
            o2.append(this.mData.size());
            Log.e("TagCreatureAdapter", o2.toString());
            return 0;
        }
    }

    public int getItemViewType(int i2) {
        return this.mFilteredData.get(i2).isHeader() ^ true ? 1 : 0;
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        CreatureNameData creatureNameData = this.mFilteredData.get(i2);
        if (creatureNameData.getName() != null) {
            if (view == null || hasHeaderLayout(view)) {
                view = this.mInflater.inflate(R.layout.creature_tag_dialog_list_item, viewGroup, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
                viewHolder.recycle();
            }
            viewHolder.bindItem(creatureNameData);
            viewHolder.bindData(creatureNameData, this.mFilteredString);
            loadBitmap(viewGroup, i2, creatureNameData);
            return view;
        } else if (view == null) {
            return this.mInflater.inflate(R.layout.creature_tag_dialog_list_header, viewGroup, false);
        } else {
            return view;
        }
    }

    public int getViewTypeCount() {
        return 2;
    }

    public boolean hasSameTaggedName(String str) {
        return this.mData.stream().filter(new C0464a(24)).anyMatch(new C0392b(str, 26));
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isEnabled(int i2) {
        if (getItemViewType(i2) != 0) {
            return true;
        }
        return false;
    }

    public void setData(ArrayList<CreatureNameData> arrayList) {
        synchronized (this.LOCK) {
            this.mData.addAll(arrayList);
            Log.d("TagCreatureAdapter", "setData {" + arrayList.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mData.size() + "}");
            ThreadUtil.postOnUiThread(new t(14, this));
        }
    }
}
