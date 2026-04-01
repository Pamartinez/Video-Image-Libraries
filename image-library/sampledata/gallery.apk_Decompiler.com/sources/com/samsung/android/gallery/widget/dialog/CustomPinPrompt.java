package com.samsung.android.gallery.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.hash.SHA256;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import o4.a;
import t8.e;
import tb.f;
import tb.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CustomPinPrompt {
    private String mAuthKey;
    /* access modifiers changed from: private */
    public TextView mDescriptionView;
    /* access modifiers changed from: private */
    public EditText mEditText;
    private TextView mNoticeView;
    private String mTitle;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$authenticate$7(Context context) {
        this.mDescriptionView.setText(R$string.enter_your_pin);
        this.mDescriptionView.setTextColor(context.getColor(R$color.drawer_tab_count_text_color));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$authenticate$8(AlertDialog alertDialog, Consumer consumer, AtomicInteger atomicInteger, Context context, TextWatcher textWatcher, Runnable runnable, View view) {
        String obj = this.mEditText.getText().toString();
        this.mEditText.setText((CharSequence) null);
        if (this.mAuthKey.equals(new SHA256().hashString(obj))) {
            alertDialog.dismiss();
            if (consumer != null) {
                consumer.accept(Boolean.TRUE);
            }
        } else if (atomicInteger.incrementAndGet() >= 5) {
            alertDialog.dismiss();
            Toast.makeText(context, R$string.incorrect_pin_entered, 0).show();
            if (consumer != null) {
                consumer.accept(Boolean.FALSE);
            }
        } else {
            this.mEditText.addTextChangedListener(textWatcher);
            this.mDescriptionView.setText(R$string.incorrect_pin_entered);
            this.mDescriptionView.setTextColor(context.getColor(R$color.pinkish_red));
            this.mDescriptionView.postDelayed(runnable, 2000);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createNew$5(Context context) {
        this.mDescriptionView.setText(R$string.enter_new_pin);
        this.mDescriptionView.setTextColor(context.getColor(R$color.drawer_tab_count_text_color));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createNew$6(AtomicReference atomicReference, TextWatcher textWatcher, Context context, Runnable runnable, Consumer consumer, AlertDialog alertDialog, View view) {
        String obj = this.mEditText.getText().toString();
        this.mEditText.setText((CharSequence) null);
        if (atomicReference.get() == null) {
            atomicReference.set(obj);
            this.mDescriptionView.setText(R$string.enter_new_pin_again);
            ((Button) view).setText(R$string.ok);
        } else if (!((String) atomicReference.get()).equals(obj)) {
            atomicReference.set((Object) null);
            this.mEditText.addTextChangedListener(textWatcher);
            ((Button) view).setText(R$string.button_continue);
            this.mDescriptionView.setText(R$string.incorrect_pin_entered);
            this.mDescriptionView.setTextColor(context.getColor(R$color.pinkish_red));
            this.mDescriptionView.postDelayed(runnable, 2000);
        } else {
            String hashString = new SHA256().hashString(obj);
            if (consumer != null) {
                consumer.accept(hashString);
            }
            alertDialog.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$showDialog$2(AlertDialog alertDialog, View view, int i2, KeyEvent keyEvent) {
        if (i2 != 66 || keyEvent.getAction() != 0) {
            return false;
        }
        view.post(new e(2, alertDialog));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialog$3(InputMethodManager inputMethodManager) {
        inputMethodManager.showSoftInput(this.mEditText, 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialog$4(Context context) {
        this.mEditText.requestFocus();
        Optional.ofNullable((InputMethodManager) context.getSystemService("input_method")).ifPresent(new a(15, this));
    }

    public Dialog authenticate(Context context, Consumer<Boolean> consumer) {
        if (this.mAuthKey != null) {
            final tb.e eVar = new tb.e(this, context, 0);
            AnonymousClass2 r72 = new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
                    CustomPinPrompt.this.mEditText.removeTextChangedListener(this);
                    CustomPinPrompt.this.mDescriptionView.removeCallbacks(eVar);
                    eVar.run();
                }

                public void afterTextChanged(Editable editable) {
                }

                public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
                }
            };
            AtomicInteger atomicInteger = new AtomicInteger(0);
            AlertDialog showDialog = showDialog(context);
            showDialog.getButton(-1).setOnClickListener(new f(this, showDialog, (Consumer) consumer, atomicInteger, context, (TextWatcher) r72, eVar));
            return showDialog;
        }
        throw new IllegalArgumentException("authentication key is not available");
    }

    public Dialog createNew(Context context, Consumer<String> consumer) {
        AlertDialog showDialog = showDialog(context);
        final tb.e eVar = new tb.e(this, context, 1);
        AnonymousClass1 r32 = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
                CustomPinPrompt.this.mEditText.removeTextChangedListener(this);
                CustomPinPrompt.this.mDescriptionView.removeCallbacks(eVar);
                eVar.run();
            }

            public void afterTextChanged(Editable editable) {
            }

            public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            }
        };
        this.mNoticeView.setVisibility(0);
        this.mDescriptionView.setText(R$string.enter_new_pin);
        showDialog.getButton(-1).setText(R$string.button_continue);
        showDialog.getButton(-1).setOnClickListener(new f(this, new AtomicReference((Object) null), (TextWatcher) r32, context, eVar, (Consumer) consumer, showDialog));
        return showDialog;
    }

    public CustomPinPrompt setAuthKey(String str) {
        this.mAuthKey = str;
        return this;
    }

    public CustomPinPrompt setTitle(String str) {
        this.mTitle = str;
        return this;
    }

    public AlertDialog showDialog(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R$layout.alert_dialog_password, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R$id.title);
        if (TextUtils.isEmpty(this.mTitle)) {
            textView.setVisibility(8);
        } else {
            textView.setText(this.mTitle);
        }
        this.mNoticeView = (TextView) inflate.findViewById(R$id.notice);
        this.mDescriptionView = (TextView) inflate.findViewById(R$id.description);
        this.mEditText = (EditText) inflate.findViewById(R$id.password);
        AlertDialog create = new AlertDialog.Builder(context).setView(inflate).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) null).create();
        create.show();
        Optional.ofNullable(create.getWindow()).ifPresent(new r6.e(9));
        this.mEditText.setOnKeyListener(new g(create));
        this.mEditText.postDelayed(new tb.e(this, context, 2), 300);
        return create;
    }
}
