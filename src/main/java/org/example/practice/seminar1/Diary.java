package org.example.practice.seminar1;

import com.ibm.icu.text.BreakIterator;

public class Diary {
    private Long id;
    private final String body;

    private Diary(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public static Diary of(Long id, String body) {
        if (!checkBodyLength(body)) {
            throw new IllegalArgumentException("일기는 30자를 넘길 수 없습니다.");
        }
        return new Diary(id, body);
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    private static boolean checkBodyLength(String body) {
        BreakIterator it = BreakIterator.getCharacterInstance();
        it.setText(body);
        int count = 0;
        while (it.next() != BreakIterator.DONE) {
            count++;
        }
        return count <= 30;
    }
}
