package com.samsung.android.sdk.scs.ai.text.bnlp;

import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Word extends AbstractRawTextHolder {
    protected List<Token> tokenList;

    public Word() {
    }

    public void addToken(Token token) {
        if (this.tokenList == null) {
            this.tokenList = new ArrayList();
        }
        this.tokenList.add(token);
    }

    public List<Token> getTokenList() {
        return this.tokenList;
    }

    public int indexOf(Token token) {
        List<Token> list = this.tokenList;
        if (list == null) {
            return -1;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Token token2 = this.tokenList.get(i2);
            if (token2.getPosition() == token.getPosition() && token2.getRawText().equals(token.getRawText())) {
                return i2;
            }
        }
        return -1;
    }

    public void setTokenList(List<Token> list) {
        this.tokenList = list;
    }

    public String toString() {
        return this.rawText;
    }

    public Word(String str) {
        super(str);
    }

    public Word(String str, int i2) {
        super(str, i2);
    }

    public Word(String str, int i2, List<Token> list) {
        super(str, i2);
        this.tokenList = list;
    }
}
