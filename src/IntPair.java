public class IntPair {
    public int firstPlayer;
    public int secondPlayer;
    public boolean isFirstplayerMax = false;
    public boolean isSecondplayerMax = false;






    public IntPair(int first,int second){
        this.firstPlayer=first;
        this.secondPlayer=second;
    }

    @Override
    public String toString() {
        return "(" +
                " " + firstPlayer +
                "," + secondPlayer +
                ')';
    }
}
