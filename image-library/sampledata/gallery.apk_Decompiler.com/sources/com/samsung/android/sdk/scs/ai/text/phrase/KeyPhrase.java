package com.samsung.android.sdk.scs.ai.text.phrase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeyPhrase {
    private double phraseScore;
    private String phraseText;

    public static KeyPhrase create() {
        return new KeyPhrase();
    }

    public double getScore() {
        return this.phraseScore;
    }

    public String getString() {
        return this.phraseText;
    }

    public void setScore(double d) {
        this.phraseScore = d;
    }

    public void setString(String str) {
        this.phraseText = str;
    }
}
