package tb;

import android.content.Context;
import android.text.TextWatcher;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.widget.dialog.CustomPinPrompt;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements View.OnClickListener {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ CustomPinPrompt e;
    public final /* synthetic */ TextWatcher f;
    public final /* synthetic */ Context g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Consumer f3290h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ AlertDialog f3291i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Serializable f3292j;
    public final /* synthetic */ Runnable k;

    public /* synthetic */ f(CustomPinPrompt customPinPrompt, AlertDialog alertDialog, Consumer consumer, AtomicInteger atomicInteger, Context context, TextWatcher textWatcher, e eVar) {
        this.e = customPinPrompt;
        this.f3291i = alertDialog;
        this.f3290h = consumer;
        this.f3292j = atomicInteger;
        this.g = context;
        this.f = textWatcher;
        this.k = eVar;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                this.e.lambda$authenticate$8(this.f3291i, this.f3290h, (AtomicInteger) this.f3292j, this.g, this.f, (e) this.k, view);
                return;
            default:
                Consumer consumer = this.f3290h;
                AlertDialog alertDialog = this.f3291i;
                this.e.lambda$createNew$6((AtomicReference) this.f3292j, this.f, this.g, (e) this.k, consumer, alertDialog, view);
                return;
        }
    }

    public /* synthetic */ f(CustomPinPrompt customPinPrompt, AtomicReference atomicReference, TextWatcher textWatcher, Context context, e eVar, Consumer consumer, AlertDialog alertDialog) {
        this.e = customPinPrompt;
        this.f3292j = atomicReference;
        this.f = textWatcher;
        this.g = context;
        this.k = eVar;
        this.f3290h = consumer;
        this.f3291i = alertDialog;
    }
}
