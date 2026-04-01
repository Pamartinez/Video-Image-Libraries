package com.samsung.android.gallery.plugins.filebrowser;

import A4.C0381p;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.LeadingMarginSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.plugins.R$color;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.plugins.R$menu;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.BiDirectionUnicode;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SearchLog;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import n4.C0491c;
import y5.a;
import y7.j;
import z5.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LogViewFragment extends FileBaseFragment {
    RecyclerView mListView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LeadingMarginSpanImpl implements LeadingMarginSpan {
        int leading;
        final int margin;

        public LeadingMarginSpanImpl(int i2) {
            this.margin = i2;
        }

        public void drawLeadingMargin(Canvas canvas, Paint paint, int i2, int i7, int i8, int i10, int i11, CharSequence charSequence, int i12, int i13, boolean z, Layout layout) {
            int i14;
            if (i12 <= 0 || charSequence.charAt(i12 - 1) != 10) {
                i14 = 0;
            } else {
                i14 = this.margin;
            }
            this.leading = i14;
        }

        public int getLeadingMargin(boolean z) {
            if (z) {
                return this.leading;
            }
            return this.margin;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LineViewAdapter extends RecyclerView.Adapter<LineViewHolder> {
        Consumer<Integer> callback;
        final HashSet<Integer> clipboard = new HashSet<>();
        final List<String> list;
        boolean selectMode;

        public LineViewAdapter(List<String> list2) {
            ArrayList arrayList = new ArrayList(list2.subList(Math.max(0, list2.size() - 50), list2.size()));
            this.list = arrayList;
            Collections.reverse(arrayList);
        }

        private String getAllText() {
            return (String) this.list.stream().map(new e(4)).collect(Collectors.joining("\n"));
        }

        private String getSelectedText() {
            ArrayList arrayList = new ArrayList(this.clipboard);
            Collections.reverse(arrayList);
            return (String) arrayList.stream().map(new q(this)).collect(Collectors.joining("\n"));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ String lambda$getSelectedText$1(Integer num) {
            return this.list.get(num.intValue()).substring(1);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onCreateViewHolder$3(LineViewHolder lineViewHolder, Boolean bool) {
            if (bool.booleanValue() && !this.selectMode) {
                setSelectMode(true);
            }
            if (this.selectMode) {
                onItemSelected(lineViewHolder, lineViewHolder.getLayoutPosition());
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$setSelectMode$0() {
            this.callback.accept(Integer.valueOf(this.selectMode ? 1 : 0));
        }

        private void onItemSelected(LineViewHolder lineViewHolder, int i2) {
            if (this.clipboard.contains(Integer.valueOf(i2))) {
                lineViewHolder.setSelected(false);
                this.clipboard.remove(Integer.valueOf(i2));
                return;
            }
            lineViewHolder.setSelected(true);
            this.clipboard.add(Integer.valueOf(i2));
        }

        public int getItemCount() {
            return this.list.size();
        }

        public String getText() {
            if (this.selectMode) {
                return getSelectedText();
            }
            return getAllText();
        }

        public LineViewAdapter setCallback(Consumer<Integer> consumer) {
            this.callback = consumer;
            return this;
        }

        public void setSelectMode(boolean z) {
            this.selectMode = z;
            if (!z) {
                this.clipboard.clear();
                notifyDataSetChanged();
            }
            if (this.callback != null) {
                ThreadUtil.postOnUiThread(new f(2, this));
            }
        }

        public void onBindViewHolder(LineViewHolder lineViewHolder, int i2) {
            lineViewHolder.bindItem(this.list.get(i2));
            lineViewHolder.setSelected(this.selectMode && this.clipboard.contains(Integer.valueOf(i2)));
        }

        public LineViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return LineViewHolder.of(viewGroup).setClickListener(new r(this));
        }

        public void onViewRecycled(LineViewHolder lineViewHolder) {
            lineViewHolder.recycle();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LineViewHolder extends RecyclerView.ViewHolder {
        BiConsumer<LineViewHolder, Boolean> clickConsumer;
        TextView textView;

        public LineViewHolder(View view) {
            super(view);
            TextView textView2 = (TextView) view.findViewById(R$id.textView);
            this.textView = textView2;
            textView2.setOnLongClickListener(new o(this, 1));
            this.textView.setOnClickListener(new d(2, this));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$new$0(View view) {
            BiConsumer<LineViewHolder, Boolean> biConsumer = this.clickConsumer;
            if (biConsumer == null) {
                return true;
            }
            biConsumer.accept(this, Boolean.TRUE);
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$new$1(View view) {
            BiConsumer<LineViewHolder, Boolean> biConsumer = this.clickConsumer;
            if (biConsumer != null) {
                biConsumer.accept(this, Boolean.FALSE);
            }
        }

        public static LineViewHolder of(ViewGroup viewGroup) {
            return new LineViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.recycler_line_layout, viewGroup, false));
        }

        public void bindItem(String str) {
            this.textView.setText(createSpannableString(str));
        }

        public SpannableString createSpannableString(String str) {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new LeadingMarginSpanImpl(15), 0, str.length(), 0);
            return spannableString;
        }

        public void recycle() {
            this.textView.setText((CharSequence) null);
            this.textView.setBackgroundColor(0);
        }

        public LineViewHolder setClickListener(BiConsumer<LineViewHolder, Boolean> biConsumer) {
            this.clickConsumer = biConsumer;
            return this;
        }

        public void setSelected(boolean z) {
            if (z) {
                TextView textView2 = this.textView;
                textView2.setBackgroundColor(textView2.getContext().getColor(R$color.line_text_background_selected));
                return;
            }
            this.textView.setBackgroundColor(0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$0(LineViewAdapter lineViewAdapter) {
        this.mListView.setAdapter(lineViewAdapter);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onBindView$1(ThreadPool.JobContext jobContext) {
        List<String> readFile = readFile(this.mPath);
        if (this.mListView != null) {
            this.mListView.post(new h(this, new LineViewAdapter(readFile).setCallback(new a(7, this)), 2));
        }
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$readFile$3(StringBuilder sb2, LinkedList linkedList, String str) {
        if (str.startsWith(BiDirectionUnicode.LEFT_TO_RIGHT_MARK)) {
            if (sb2.length() > 0) {
                linkedList.add(sb2.toString());
            }
            sb2.setLength(0);
            sb2.append(str);
            return;
        }
        sb2.append("\n");
        sb2.append(str);
    }

    /* access modifiers changed from: private */
    public void onAdapterStateChanged(int i2) {
        if (i2 == 1) {
            this.mToolbar.setTitle(R$string.select_items);
        } else if (i2 == 0) {
            this.mToolbar.setTitle((CharSequence) getTitle());
        }
    }

    /* access modifiers changed from: private */
    public boolean onMenuSelected(MenuItem menuItem) {
        String str = (String) Optional.ofNullable((LineViewAdapter) this.mListView.getAdapter()).map(new e(3)).orElse((Object) null);
        if (TextUtils.isEmpty(str)) {
            Log.e(this.TAG, "onMenuSelected failed. empty message");
            return false;
        }
        try {
            if (menuItem.getItemId() == R$id.action_share) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", str);
                startActivity(Intent.createChooser(intent, getString(R$string.open_with)));
                return true;
            } else if (menuItem.getItemId() != R$id.action_copy_to_clipboard) {
                return true;
            } else {
                ((ClipboardManager) getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("log", str));
                return true;
            }
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("onMenuSelected failed. e="), this.TAG);
            return true;
        }
    }

    public String getSubtitle() {
        return new File(this.mPath).getName();
    }

    public String getTitle() {
        return "Log Viewer";
    }

    public View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R$layout.fragment_log_view_layout, viewGroup, false);
    }

    public /* bridge */ /* synthetic */ void onAttach(Context context) {
        super.onAttach(context);
    }

    public boolean onBackPressed() {
        LineViewAdapter lineViewAdapter = (LineViewAdapter) this.mListView.getAdapter();
        if (lineViewAdapter == null || !lineViewAdapter.selectMode) {
            return false;
        }
        lineViewAdapter.setSelectMode(false);
        return true;
    }

    public void onBindToolbar(Toolbar toolbar) {
        super.onBindToolbar(toolbar);
        toolbar.inflateMenu(R$menu.menu_log_view);
        toolbar.setOnMenuItemClickListener(new l(this));
    }

    public void onBindView(ViewGroup viewGroup) {
        super.onBindView(viewGroup);
        this.mListView = (RecyclerView) viewGroup.findViewById(R$id.listview);
        ThreadPool.getInstance().submit(new C0381p(20, this));
    }

    public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null) {
            recyclerView.setAdapter((RecyclerView.Adapter) null);
            this.mListView = null;
        }
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ void onDestroyView() {
        super.onDestroyView();
    }

    public /* bridge */ /* synthetic */ void onDetach() {
        super.onDetach();
    }

    public /* bridge */ /* synthetic */ void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public List<String> readFile(String str) {
        List list;
        LinkedList linkedList = new LinkedList();
        try {
            ArrayList arrayList = new ArrayList();
            if ("/storage/emulated/0/Android/media/com.sec.android.gallery3d/log/[DEBUG] search.log".equals(str)) {
                SearchLog.listOf().stream().map(new j(3)).forEach(new f4.a(arrayList, 17));
                list = arrayList;
            } else {
                list = Files.readAllLines(new File(str).toPath());
            }
            Log.d(this.TAG, "readFile", Integer.valueOf(list.size()));
            StringBuilder sb2 = new StringBuilder(2048);
            list.forEach(new C0491c(25, sb2, linkedList));
            if (sb2.length() > 0) {
                linkedList.add(sb2.toString());
            }
            return linkedList;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("readFile failed. e="), this.TAG);
            return linkedList;
        }
    }
}
