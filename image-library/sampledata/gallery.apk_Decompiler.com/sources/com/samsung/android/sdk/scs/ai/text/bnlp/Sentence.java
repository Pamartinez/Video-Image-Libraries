package com.samsung.android.sdk.scs.ai.text.bnlp;

import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sentence extends AbstractRawTextHolder {
    protected List<Word> wordList;

    public Sentence() {
    }

    public void addWord(Word word) {
        if (this.wordList == null) {
            this.wordList = new ArrayList();
        }
        this.wordList.add(word);
    }

    public List<Word> getWordList() {
        return this.wordList;
    }

    public int indexOf(Word word) {
        List<Word> list = this.wordList;
        if (list == null) {
            return -1;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Word word2 = this.wordList.get(i2);
            if (word2.getPosition() == word.getPosition() && word2.getRawText().equals(word.getRawText())) {
                return i2;
            }
        }
        return -1;
    }

    public void setWordList(List<Word> list) {
        this.wordList = list;
    }

    public String toString() {
        return this.rawText;
    }

    public Sentence(String str) {
        super(str);
    }

    public Sentence(String str, int i2) {
        super(str, i2);
    }

    public Sentence(String str, int i2, List<Word> list) {
        super(str, i2);
        this.wordList = list;
    }
}
