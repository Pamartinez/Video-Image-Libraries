package com.google.android.material.datepicker;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.sec.android.gallery3d.R;
import java.util.Locale;
import x2.C0338e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F extends RecyclerView.Adapter {
    public final p d;

    public F(p pVar) {
        this.d = pVar;
    }

    public final int getItemCount() {
        return this.d.f.f1452i;
    }

    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        String str;
        E e = (E) viewHolder;
        p pVar = this.d;
        int i7 = pVar.f.d.f + i2;
        e.d.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i7)}));
        TextView textView = e.d;
        Context context = textView.getContext();
        if (D.b().get(1) == i7) {
            str = String.format(context.getString(R.string.mtrl_picker_navigate_to_current_year_description), new Object[]{Integer.valueOf(i7)});
        } else {
            str = String.format(context.getString(R.string.mtrl_picker_navigate_to_year_description), new Object[]{Integer.valueOf(i7)});
        }
        textView.setContentDescription(str);
        C0118c cVar = pVar.f1459i;
        if (D.b().get(1) == i7) {
            C0338e eVar = cVar.b;
        } else {
            C0338e eVar2 = cVar.f1454a;
        }
        throw null;
    }

    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new E((TextView) C0086a.d(viewGroup, R.layout.mtrl_calendar_year, viewGroup, false));
    }
}
