package com.samsung.android.gallery.app.controller.delegate;

import H7.o;
import M3.C0405a;
import M3.C0406b;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.sum.core.types.NumericEnum;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DatePickerDelegate {
    public static void create(Context context, Blackboard blackboard, Bundle bundle) {
        String string = bundle.getString("date-time", (String) null);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context);
        if (string != null) {
            try {
                String[] split = string.split(" ")[0].split(NumericEnum.SEP);
                datePickerDialog.updateDate(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
            } catch (Exception unused) {
            }
        }
        datePickerDialog.setOnDateSetListener(new C0405a(blackboard));
        datePickerDialog.setOnCancelListener(new C0406b(0, blackboard));
        datePickerDialog.setOnDismissListener(new o(1, blackboard));
        datePickerDialog.create();
        datePickerDialog.show();
    }

    public static void onCancel(Blackboard blackboard) {
        blackboard.publish("data://user/Date", (Object) null);
    }
}
