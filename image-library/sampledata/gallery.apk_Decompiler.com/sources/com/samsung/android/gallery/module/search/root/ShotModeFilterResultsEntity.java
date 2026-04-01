package com.samsung.android.gallery.module.search.root;

import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShotModeFilterResultsEntity extends FilterResultsEntity {
    public ShotModeFilterResultsEntity(String str, String str2) {
        super("", str);
        addRawLabel(str2);
        if ("3d_capture".equals(getRawLabels())) {
            addRawLabel(str2);
        }
    }

    public String getSelection() {
        if (!"3d_capture".equals(getRawLabels())) {
            return super.getSelection();
        }
        StringJoiner stringJoiner = new StringJoiner(" =? OR ", "(", " =?)");
        stringJoiner.add("sef_file_type");
        stringJoiner.add("recording_mode");
        return stringJoiner.toString();
    }
}
