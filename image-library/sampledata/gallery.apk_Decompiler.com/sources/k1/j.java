package K1;

import A0.l;
import C1.b;
import C1.d;
import G0.c;
import L1.g;
import Y6.a;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import t1.C0279d;
import t1.f;
import w1.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public l f383a;
    public Bundle b;

    /* renamed from: c  reason: collision with root package name */
    public LinkedList f384c;
    public final c d = new c(1, (Object) this);
    public final f e;
    public final Context f;
    public c g;

    /* renamed from: h  reason: collision with root package name */
    public final ArrayList f385h = new ArrayList();

    public j(f fVar, Context context) {
        this.e = fVar;
        this.f = context;
    }

    public static void a(f fVar) {
        C0279d dVar = C0279d.d;
        Context context = fVar.getContext();
        int b5 = dVar.b(context, f.f1923a);
        String c5 = k.c(context, b5);
        String b8 = k.b(context, b5);
        LinearLayout linearLayout = new LinearLayout(fVar.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        fVar.addView(linearLayout);
        TextView textView = new TextView(fVar.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(c5);
        linearLayout.addView(textView);
        Intent a7 = dVar.a(context, b5, (String) null);
        if (a7 != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(b8);
            linearLayout.addView(button);
            button.setOnClickListener(new d(context, a7));
        }
    }

    public final void b(int i2) {
        while (!this.f384c.isEmpty() && ((C1.f) this.f384c.getLast()).a() >= i2) {
            this.f384c.removeLast();
        }
    }

    public final void c(Bundle bundle, C1.f fVar) {
        if (this.f383a != null) {
            fVar.b();
            return;
        }
        if (this.f384c == null) {
            this.f384c = new LinkedList();
        }
        this.f384c.add(fVar);
        if (bundle != null) {
            Bundle bundle2 = this.b;
            if (bundle2 == null) {
                this.b = (Bundle) bundle.clone();
            } else {
                bundle2.putAll(bundle);
            }
        }
        this.g = this.d;
        if (this.f383a == null) {
            try {
                Context context = this.f;
                boolean z = h.f381a;
                synchronized (h.class) {
                    h.a(context);
                }
                g f5 = D1.f.M(this.f).f(new b(this.f));
                if (f5 != null) {
                    this.g.u(new l(this.e, f5));
                    Iterator it = this.f385h.iterator();
                    while (it.hasNext()) {
                        this.f383a.l((a) it.next());
                    }
                    this.f385h.clear();
                }
            } catch (RemoteException e7) {
                throw new RuntimeException(e7);
            } catch (t1.g unused) {
            }
        }
    }
}
