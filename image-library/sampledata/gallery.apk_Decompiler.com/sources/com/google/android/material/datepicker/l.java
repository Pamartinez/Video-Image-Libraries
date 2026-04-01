package com.google.android.material.datepicker;

import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.icu.util.TimeZone;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends RecyclerView.OnScrollListener {
    public final /* synthetic */ y d;
    public final /* synthetic */ MaterialButton e;
    public final /* synthetic */ p f;

    public l(p pVar, y yVar, MaterialButton materialButton) {
        this.f = pVar;
        this.d = yVar;
        this.e = materialButton;
    }

    public final void onScrollStateChanged(RecyclerView recyclerView, int i2) {
        if (i2 == 0) {
            recyclerView.announceForAccessibility(this.e.getText());
        }
    }

    public final void onScrolled(RecyclerView recyclerView, int i2, int i7) {
        int i8;
        C0117b bVar = this.d.d;
        p pVar = this.f;
        if (i2 < 0) {
            i8 = ((LinearLayoutManager) pVar.k.getLayoutManager()).findFirstVisibleItemPosition();
        } else {
            i8 = ((LinearLayoutManager) pVar.k.getLayoutManager()).findLastVisibleItemPosition();
        }
        Calendar a7 = D.a(bVar.d.d);
        a7.add(2, i8);
        pVar.g = new u(a7);
        Calendar a10 = D.a(bVar.d.d);
        a10.add(2, i8);
        a10.set(5, 1);
        Calendar a11 = D.a(a10);
        a11.get(2);
        a11.get(1);
        a11.getMaximum(7);
        a11.getActualMaximum(5);
        a11.getTimeInMillis();
        long timeInMillis = a11.getTimeInMillis();
        Locale locale = Locale.getDefault();
        AtomicReference atomicReference = D.f1449a;
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton("yMMMM", locale);
        instanceForSkeleton.setTimeZone(TimeZone.getTimeZone("UTC"));
        instanceForSkeleton.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
        this.e.setText(instanceForSkeleton.format(new Date(timeInMillis)));
    }
}
