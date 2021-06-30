public class Word {
    private Coordinates start, end;
    private String word;
    private String orientation;

    public Word(String w, Coordinates s, Coordinates e) {
        start = s;
        end = e;
        word = w;
    }

    public void setOrientation(String input) {
        orientation = input;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getStartX() {
        return start.getX();
    }

    public int getStartY() {
        return start.getY();
    }

    public int getEndX() {
        return end.getX();
    }

    public int getEndY() {
        return end.getY();
    }

    public void setStartX(int i) {
        start.setX(i);
    }

    public void setStartY(int i) {
        start.setY(i);
    }

    public void setEndX(int i) {
        end.setX(i);
    }

    public void setEndY(int i) {
        end.setY(i);
    }

    public String showWord() {
        return word;
    }

    public int getLength() {
        return word.length();
    }

    public void setWord(String word) {
        this.word = word;
    }
}
