//This is our Player object class.
public class Player {
    //These variables are part of our Player object.
    private String name = "unknown";
    private int diceSum = 0;

    public void setName(String name) {
        this.name = name;
    }//This is our setter for our name

    public void setDiceSum(int diceSum) {//DiceSum is basically the score.
        this.diceSum = diceSum;
    }//This is our setter for our DiceSum. Instrumental in recording progress of the game.

    public String getName() {
        return this.name;
    } //This is out getter for our name.

    public int getDiceSum() {
        return this.diceSum;
    }//This returns our total value.
}