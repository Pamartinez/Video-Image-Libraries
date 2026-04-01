package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.barcode;

import Ba.g;
import Tf.n;
import X2.C0064d;
import X2.C0065e;
import X2.C0066f;
import X2.l;
import X2.q;
import X2.r;
import X2.s;
import X2.x;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.SemBlurInfo;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.result.Barcode;
import com.samsung.android.app.sdk.deepsky.textextraction.R$color;
import com.samsung.android.app.sdk.deepsky.textextraction.R$dimen;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.R$id;
import com.samsung.android.app.sdk.deepsky.textextraction.R$layout;
import com.samsung.android.app.sdk.deepsky.textextraction.R$style;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import ne.z;
import r3.a;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 F2\u00020\u0001:\u0001FB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ-\u0010%\u001a\u0004\u0018\u00010\u000e2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010$\u001a\u0004\u0018\u00010#H\u0016¢\u0006\u0004\b%\u0010&J\u0019\u0010(\u001a\u00020'2\b\u0010$\u001a\u0004\u0018\u00010#H\u0016¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020\u0010H\u0016¢\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u0010H\u0016¢\u0006\u0004\b,\u0010+J\u000f\u0010-\u001a\u00020\u0010H\u0016¢\u0006\u0004\b-\u0010+J\u000f\u0010.\u001a\u00020\u0010H\u0016¢\u0006\u0004\b.\u0010+J\u001d\u00103\u001a\u0002022\u0006\u0010/\u001a\u00020\n2\u0006\u00101\u001a\u000200¢\u0006\u0004\b3\u00104J%\u00105\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\n2\u0006\u00101\u001a\u000200¢\u0006\u0004\b5\u00106R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00107R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u00108R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u00109R\u0016\u0010;\u001a\u00020:8\u0002@\u0002X.¢\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020:8\u0002@\u0002X.¢\u0006\u0006\n\u0004\b=\u0010<R\u0016\u0010?\u001a\u00020>8\u0002@\u0002X.¢\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010A\u001a\u00020:8\u0002@\u0002X.¢\u0006\u0006\n\u0004\bA\u0010<R\u0016\u0010B\u001a\u00020\u000e8\u0002@\u0002X.¢\u0006\u0006\n\u0004\bB\u0010CR\u0018\u0010D\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bD\u0010E¨\u0006G"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeDialogListener;", "listener", "Lcom/samsung/android/app/sdk/deepsky/barcode/result/Barcode;", "barcode", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeDialogListener;Lcom/samsung/android/app/sdk/deepsky/barcode/result/Barcode;)V", "", "textSize", "getMaxTextSize", "(F)F", "Landroid/view/View;", "view", "Lme/x;", "setContent", "(Landroid/view/View;)V", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "action", "performAction", "(Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;)V", "LX2/r;", "parsedResult", "Landroid/content/Intent;", "getCallIntent", "(LX2/r;)Landroid/content/Intent;", "", "isReduceTransparencyAndBlur", "(Landroid/content/Context;)Z", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "onCreateView", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;", "Landroid/app/Dialog;", "onCreateDialog", "(Landroid/os/Bundle;)Landroid/app/Dialog;", "onStart", "()V", "dismiss", "onDestroy", "onPause", "cornerRadius", "", "color", "Landroid/graphics/drawable/GradientDrawable;", "createBackgroundDrawable", "(FI)Landroid/graphics/drawable/GradientDrawable;", "setPartialBlurForWindow", "(Landroid/view/View;FI)V", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeDialogListener;", "Lcom/samsung/android/app/sdk/deepsky/barcode/result/Barcode;", "Landroid/widget/TextView;", "txtQRHeading", "Landroid/widget/TextView;", "txtQRContent", "Landroid/widget/ImageView;", "txtQRContentNextBtn", "Landroid/widget/ImageView;", "txtQrDialogCancelBtn", "txtQRContentLayout", "Landroid/view/View;", "dialog", "Landroid/app/Dialog;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BarcodeDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion((e) null);
    private static final Map<String, Integer> actionImageMap;
    private final Barcode barcode;
    private final Context context;
    private Dialog dialog;
    private final BarcodeDialogListener listener;
    private TextView txtQRContent;
    private View txtQRContentLayout;
    private ImageView txtQRContentNextBtn;
    private TextView txtQRHeading;
    private TextView txtQrDialogCancelBtn;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeDialogFragment$Companion;", "", "<init>", "()V", "TAG", "", "FONT_LARGE_LEVEL_SCALE", "", "BLUR_RADIUS", "", "actionImageMap", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                X2.s[] r0 = X2.s.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                X2.s r1 = X2.s.TEL     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                X2.s r1 = X2.s.ADDRESSBOOK     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.barcode.BarcodeDialogFragment.WhenMappings.<clinit>():void");
        }
    }

    static {
        int i2 = R$drawable.qr_dialog_pix_icon;
        i iVar = new i("SendMatter", Integer.valueOf(i2));
        i iVar2 = new i("ConnectSmartThings", Integer.valueOf(R$drawable.qr_dialog_smart_things_icon));
        i iVar3 = new i("SearchProduct", Integer.valueOf(R$drawable.qr_dialog_search_nearby_icon));
        i iVar4 = new i("SendToPix", Integer.valueOf(i2));
        i iVar5 = new i("ConnectCmc", Integer.valueOf(R$drawable.qr_dialog_link_to_phone_icon));
        i iVar6 = new i("LaunchGalaxyWearable", Integer.valueOf(R$drawable.qr_dialog_galaxy_icon));
        i iVar7 = new i("LaunchSimCardManager", Integer.valueOf(R$drawable.qr_dialog_sim_icon));
        int i7 = R$drawable.qr_dialog_link_icon;
        i iVar8 = new i("LinkOpen", Integer.valueOf(i7));
        i iVar9 = new i("PlayStoreLinkOpen", Integer.valueOf(i7));
        i iVar10 = new i("SamsungHealthLinkOpen", Integer.valueOf(i7));
        i iVar11 = new i("SamsungPassLinkOpen", Integer.valueOf(i7));
        i iVar12 = new i("SamsungAccountLinkOpen", Integer.valueOf(R$drawable.qr_dialog_account_icon));
        int i8 = R$drawable.qr_dialog_pay_icon;
        i iVar13 = new i("SendToSamsungPayIndia", Integer.valueOf(i8));
        i iVar14 = new i("SendToSamsungPayIndonesia", Integer.valueOf(i8));
        i iVar15 = iVar;
        i iVar16 = new i("Wifi", Integer.valueOf(R$drawable.qr_dialog_wifi_icon));
        i iVar17 = new i("ViewOnMap", Integer.valueOf(R$drawable.qr_dialog_map_icon));
        i iVar18 = new i("ViewText", Integer.valueOf(R$drawable.capsule_notes));
        i iVar19 = new i("CopyText", Integer.valueOf(R$drawable.capsule_copy_all));
        i iVar20 = new i("AddToCalendar", Integer.valueOf(R$drawable.capsule_calendar));
        int i10 = R$drawable.capsule_email;
        int i11 = i10;
        i iVar21 = iVar20;
        i iVar22 = new i("Email", Integer.valueOf(i10));
        int i12 = R$drawable.capsule_call_phone;
        i iVar23 = iVar22;
        int i13 = i12;
        i iVar24 = new i("PhoneNumberCall", Integer.valueOf(i12));
        int i14 = R$drawable.capsule_send_to_message;
        i iVar25 = iVar24;
        int i15 = i14;
        i iVar26 = new i("Sms", Integer.valueOf(i14));
        i iVar27 = new i("AddToContacts", Integer.valueOf(R$drawable.capsule_add_to_contact));
        Integer valueOf = Integer.valueOf(i13);
        i iVar28 = iVar27;
        i iVar29 = new i("ContactCall", valueOf);
        Integer valueOf2 = Integer.valueOf(i11);
        i iVar30 = iVar29;
        i iVar31 = new i("ContactEmail", valueOf2);
        Integer valueOf3 = Integer.valueOf(i15);
        i iVar32 = iVar15;
        i iVar33 = iVar16;
        i iVar34 = iVar17;
        i iVar35 = iVar18;
        i iVar36 = iVar19;
        i iVar37 = iVar21;
        i iVar38 = iVar25;
        i iVar39 = iVar30;
        actionImageMap = z.b0(iVar32, iVar2, iVar3, iVar4, iVar5, iVar6, iVar7, iVar8, iVar9, iVar10, iVar11, iVar12, iVar13, iVar14, iVar33, iVar34, iVar35, iVar36, iVar37, iVar23, iVar38, iVar26, iVar28, iVar39, iVar31, new i("ContactMessage", valueOf3), new i("WebSearch", Integer.valueOf(R$drawable.capsule_internet_web)));
    }

    public BarcodeDialogFragment(Context context2, BarcodeDialogListener barcodeDialogListener, Barcode barcode2) {
        j.e(context2, "context");
        j.e(barcodeDialogListener, "listener");
        j.e(barcode2, "barcode");
        this.context = context2;
        this.listener = barcodeDialogListener;
        this.barcode = barcode2;
    }

    private final Intent getCallIntent(r rVar) {
        int i2;
        Uri uri;
        s sVar = rVar.f937a;
        if (sVar == null) {
            i2 = -1;
        } else {
            i2 = WhenMappings.$EnumSwitchMapping$0[sVar.ordinal()];
        }
        if (i2 == 1) {
            uri = Uri.parse(((x) rVar).f944c);
            j.b(uri);
        } else if (i2 != 2) {
            uri = Uri.fromParts("tel", rVar.a(), (String) null);
            j.b(uri);
        } else {
            uri = Uri.fromParts("tel", ((C0064d) rVar).g[0], (String) null);
            j.b(uri);
        }
        Intent intent = new Intent("android.intent.action.DIAL", uri);
        intent.addFlags(268435456);
        return intent;
    }

    private final float getMaxTextSize(float f) {
        float f5 = this.context.getResources().getConfiguration().fontScale;
        return Math.min(f5, 1.3f) * (f / f5);
    }

    private final boolean isReduceTransparencyAndBlur(Context context2) {
        if (Settings.System.getInt(context2.getContentResolver(), "accessibility_reduce_transparency", 0) == 1) {
            return true;
        }
        return false;
    }

    private final void performAction(Action action) {
        action.execute();
        try {
            String str = action.getIntent().getPackage();
            j.b(str);
            j.b(this.context.getPackageManager().getApplicationInfo(str, 0));
        } catch (Exception unused) {
            String string = getResources().getString(action.getTitleId());
            j.d(string, "getString(...)");
            String lowerCase = string.toLowerCase(Locale.ROOT);
            j.d(lowerCase, "toLowerCase(...)");
            if (lowerCase.equals("call")) {
                this.context.startActivity(getCallIntent(new C0064d(new String[0], new q[0], new String[0], (String[]) null, (String) null, new String[]{n.H0(String.valueOf(action.getIntent().getData()), "tel:")}, new String[]{"Home"}, new String[0], new String[0], (String) null, (String) null, new String[0], new String[0], (String) null, (String) null, (C0066f) null, (String) null, (String[]) null, (String[]) null, new C0065e[0], (l[]) null)));
            }
        }
        Dialog dialog2 = this.dialog;
        if (dialog2 != null) {
            dialog2.dismiss();
        }
    }

    private final void setContent(View view) {
        int i2;
        View findViewById = view.findViewById(R$id.txtQRContentLayout);
        findViewById.setOnClickListener(new a(this, 0));
        this.txtQRContentLayout = findViewById;
        TextView textView = (TextView) view.findViewById(R$id.txtQRHeading);
        textView.setTextSize(0, getMaxTextSize(textView.getTextSize()));
        textView.setText(this.barcode.getUiResource().getTitle());
        this.txtQRHeading = textView;
        TextView textView2 = (TextView) view.findViewById(R$id.txtQRContent);
        textView2.setTextSize(0, getMaxTextSize(textView2.getTextSize()));
        textView2.setText(n.R0(this.barcode.getUiResource().getBody()).toString());
        this.txtQRContent = textView2;
        ImageView imageView = (ImageView) view.findViewById(R$id.txtQRContentNextBtn);
        imageView.setImageTintList(ColorStateList.valueOf(imageView.getContext().getColor(R$color.textextraction_dialog_content_action_text_color)));
        this.txtQRContentNextBtn = imageView;
        TextView textView3 = (TextView) view.findViewById(R$id.cancelQRActionBtn);
        textView3.setTextSize(0, getMaxTextSize(textView3.getTextSize()));
        textView3.setOnClickListener(new a(this, 1));
        this.txtQrDialogCancelBtn = textView3;
        getResources().getDimension(R$dimen.dialog_action_padding_vertical);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.layoutQRActions);
        for (Action next : this.barcode.getUiResource().getActions()) {
            LibLogger.i("BarcodeDialogFragment", "Add action: " + next + ".getActionId()");
            View inflate = LayoutInflater.from(this.context).inflate(R$layout.qr_dialog_action_layout, (ViewGroup) null);
            TextView textView4 = (TextView) inflate.findViewById(R$id.qrActionTextView);
            ImageView imageView2 = (ImageView) inflate.findViewById(R$id.qrActionIcon);
            Resources resources = imageView2.getResources();
            Integer num = actionImageMap.get(next.getActionId());
            if (num != null) {
                i2 = num.intValue();
            } else {
                i2 = R$drawable.qr_dialog_defult_icon;
            }
            imageView2.setImageDrawable(resources.getDrawable(i2));
            Context context2 = imageView2.getContext();
            int i7 = R$color.textextraction_dialog_content_action_text_color;
            imageView2.setImageTintList(ColorStateList.valueOf(context2.getColor(i7)));
            inflate.setClickable(true);
            inflate.setOnClickListener(new g(27, this, next));
            textView4.setTextSize(0, getMaxTextSize(textView4.getTextSize()));
            textView4.setTextColor(requireContext().getColor(i7));
            textView4.setText(textView4.getResources().getString(next.getTitleId()));
            linearLayout.addView(inflate);
        }
    }

    /* access modifiers changed from: private */
    public static final void setContent$lambda$10$lambda$9(BarcodeDialogFragment barcodeDialogFragment, View view) {
        Dialog dialog2 = barcodeDialogFragment.dialog;
        if (dialog2 != null) {
            dialog2.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public static final void setContent$lambda$13$lambda$12(BarcodeDialogFragment barcodeDialogFragment, Action action, View view) {
        barcodeDialogFragment.performAction(action);
    }

    /* access modifiers changed from: private */
    public static final void setContent$lambda$5$lambda$4(BarcodeDialogFragment barcodeDialogFragment, View view) {
        if (!barcodeDialogFragment.barcode.getUiResource().getActions().isEmpty()) {
            barcodeDialogFragment.performAction(barcodeDialogFragment.barcode.getUiResource().getActions().get(0));
        }
    }

    public final GradientDrawable createBackgroundDrawable(float f, int i2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(i2);
        gradientDrawable.setCornerRadius(f);
        return gradientDrawable;
    }

    public void dismiss() {
        this.listener.onDismiss();
        super.dismiss();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (this.dialog == null) {
            Dialog dialog2 = new Dialog(this.context);
            WindowManager.LayoutParams layoutParams = null;
            View inflate = LayoutInflater.from(dialog2.getContext()).inflate(R$layout.qr_dialog, (ViewGroup) null);
            Window window = dialog2.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable(0));
                window.requestFeature(1);
                WindowManager.LayoutParams attributes = window.getAttributes();
                if (attributes != null) {
                    attributes.gravity = 81;
                    attributes.windowAnimations = R$style.DialogAnimation;
                    layoutParams = attributes;
                }
                window.setAttributes(layoutParams);
            }
            dialog2.setContentView(inflate);
            this.dialog = dialog2;
        }
        Dialog dialog3 = this.dialog;
        if (dialog3 == null) {
            return new Dialog(this.context);
        }
        return dialog3;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        j.e(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R$layout.qr_dialog, viewGroup, false);
        j.b(inflate);
        setContent(inflate);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        Dialog dialog2 = this.dialog;
        if (dialog2 != null) {
            dialog2.dismiss();
        }
        this.dialog = null;
    }

    public void onPause() {
        dismiss();
        super.onPause();
    }

    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog2 = this.dialog;
        if (dialog2 != null && (window = dialog2.getWindow()) != null) {
            View decorView = window.getDecorView();
            j.d(decorView, "getDecorView(...)");
            setPartialBlurForWindow(decorView, this.context.getResources().getDimension(R$dimen.textextraction_dialog_bg_radius), this.context.getResources().getColor(R$color.textextraction_dialog_bg_color, (Resources.Theme) null));
        }
    }

    public final void setPartialBlurForWindow(View view, float f, int i2) {
        j.e(view, "view");
        SemBlurInfo.Builder builder = new SemBlurInfo.Builder(0);
        if (isReduceTransparencyAndBlur(this.context)) {
            int i7 = -16777216 | i2;
            if (Build.VERSION.SDK_INT >= 31) {
                SemBlurInfo build = builder.setRadius(128).setBackgroundCornerRadius(f).setBackgroundColor(i7).build();
                j.d(build, "build(...)");
                view.semSetBlurInfo(build);
                return;
            }
            return;
        }
        view.setBackground(createBackgroundDrawable(f, (i2 & 16777215) | -452984832));
    }
}
