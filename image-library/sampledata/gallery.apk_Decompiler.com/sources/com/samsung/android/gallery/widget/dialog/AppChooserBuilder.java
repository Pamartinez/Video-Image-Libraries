package com.samsung.android.gallery.widget.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import o4.a;
import tb.C0709a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppChooserBuilder {
    Consumer<ResolveInfo> callback;
    Dialog dialog;
    final Intent intent;
    String title;
    int titleRes;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AppLabelAdapter extends RecyclerView.Adapter<AppLabelHolder> {
        Consumer<ResolveInfo> callback;
        final List<AppLabelData> list;

        public AppLabelAdapter(List<AppLabelData> list2) {
            this.list = list2;
        }

        public int getItemCount() {
            return this.list.size();
        }

        public AppLabelAdapter setCallback(Consumer<ResolveInfo> consumer) {
            this.callback = consumer;
            return this;
        }

        public void onBindViewHolder(AppLabelHolder appLabelHolder, int i2) {
            appLabelHolder.bindItem(this.list.get(i2));
        }

        public AppLabelHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return AppLabelHolder.of(viewGroup).setCallback(this.callback);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AppLabelData {
        Drawable icon;
        ResolveInfo info;
        CharSequence label;

        public AppLabelData(ResolveInfo resolveInfo, Drawable drawable, CharSequence charSequence) {
            this.info = resolveInfo;
            this.icon = drawable;
            this.label = charSequence;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AppLabelHolder extends RecyclerView.ViewHolder {
        Consumer<ResolveInfo> callback;
        AppLabelData data;
        ImageView icon;
        TextView label;

        public AppLabelHolder(View view) {
            super(view);
            view.setPadding(view.getPaddingLeft(), 20, view.getPaddingRight(), 50);
            this.icon = (ImageView) view.findViewById(R$id.thumbnail);
            TextView textView = (TextView) view.findViewById(R$id.title);
            this.label = textView;
            textView.setBackground((Drawable) null);
            this.label.setMaxLines(2);
            ((LinearLayout.LayoutParams) this.label.getLayoutParams()).topMargin = 0;
            this.icon.setBackground((Drawable) null);
        }

        public static AppLabelHolder of(ViewGroup viewGroup) {
            return new AppLabelHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.recycler_item_details_people_layout, viewGroup, false));
        }

        public void bindItem(AppLabelData appLabelData) {
            this.data = appLabelData;
            this.icon.setImageDrawable(appLabelData.icon);
            this.label.setText(appLabelData.label);
        }

        public void onIconClicked(View view) {
            Consumer<ResolveInfo> consumer = this.callback;
            if (consumer != null) {
                consumer.accept(this.data.info);
            }
        }

        public AppLabelHolder setCallback(Consumer<ResolveInfo> consumer) {
            this.callback = consumer;
            this.icon.setOnClickListener(new a(0, this));
            return this;
        }
    }

    public AppChooserBuilder(Intent intent2) {
        this.intent = intent2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$build$1(ResolveInfo resolveInfo) {
        Consumer<ResolveInfo> consumer = this.callback;
        if (consumer != null) {
            consumer.accept(resolveInfo);
        }
        this.dialog.dismiss();
        this.dialog = null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ AppLabelData lambda$queryPackages$2(PackageManager packageManager, ResolveInfo resolveInfo) {
        return new AppLabelData(resolveInfo, resolveInfo.loadIcon(packageManager), resolveInfo.loadLabel(packageManager));
    }

    public Dialog build(Context context) {
        System.currentTimeMillis();
        List<AppLabelData> queryPackages = queryPackages(context, this.intent);
        View inflate = LayoutInflater.from(context).inflate(R$layout.app_chooser_dialog_container, (ViewGroup) null);
        ((RecyclerView) inflate.findViewById(R$id.recyclerView)).setAdapter(new AppLabelAdapter(queryPackages).setCallback(new a(14, this)));
        AlertDialog.Builder view = new AlertDialog.Builder(context).setView(inflate);
        String str = this.title;
        if (str == null) {
            str = context.getString(this.titleRes);
        }
        AlertDialog create = view.setTitle(str).create();
        this.dialog = create;
        return create;
    }

    public List<AppLabelData> queryPackages(Context context, Intent intent2) {
        PackageManager packageManager = context.getPackageManager();
        return (List) packageManager.queryIntentActivities(intent2, 192).stream().map(new C0709a(0, packageManager)).collect(Collectors.toList());
    }

    public AppChooserBuilder setCallback(Consumer<ResolveInfo> consumer) {
        this.callback = consumer;
        return this;
    }

    public AppChooserBuilder setTitle(int i2) {
        this.titleRes = i2;
        return this;
    }
}
