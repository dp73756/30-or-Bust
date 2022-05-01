//David Pitt-Gryaznov
//CSC 160 Computer Science I, section 680
//April 30, 2022
//Summary: This program lets two players play "30 or Bust!". Each player will be prompted to enter their respective names. The goal of the game is for either player to reach a total sum of 30 with their two die over multiple turns. If either player
//rolls over 30 then their total die sum is set to 0 and the game continues.

import java.util.Scanner;

public class Main {
    private static Player PlayaOne = new Player();//I declared my objects here so my methods could use them.
    private static Player PlayaTwo = new Player();

    public static void main(String[] args) {

        int dice1 = 0, dice2 = 0, score1 = 0, score2 = 0, diceSum1 = 0, diceSum2 = 0, playerToggle = 0; //These variables track the progress of our game besides playerToggle.

        Scanner input = new Scanner(System.in);
        String name;
        String name2;

        System.out.println("Hello, what is the name of the fist player?");//Get the users names
        PlayaOne.setName(name = input.nextLine());

        System.out.println("Hello, what is the name of the second player?");
        PlayaTwo.setName(name2 = input.nextLine());

        System.out.println("Here we go!");

        while (diceSum1 != 30 && diceSum2 != 30) {//This condition statement runs if either player has not won yet.
            dice1 = rollDice();//We get a variable for our die for our first player
            dice2 = rollDice();
            score1 = PlayaOne.getDiceSum();//We set score equal to our previous diceSum so that the user can easily keep track of what occured during their previoius roll.
            diceSum1 = score1 + dice1 + dice2;//This is our total value.
            playerToggle = 0;//This toggle allows us to filter our method to use playerOne's values or playerTwo's values.
            PlayaOne.setDiceSum(diceSum1);//This allows us to keep track of our Player's total score.
            System.out.println();
            System.out.print("Player " + name);
            System.out.println(", it is your turn!");
            playThirtyOrBust(dice1, dice2, score1, playerToggle);

            dice1 = rollDice();
            dice2 = rollDice();
            score2 = PlayaTwo.getDiceSum();
            diceSum2 = score2 + dice1 + dice2;
            playerToggle = 1;
            PlayaTwo.setDiceSum(diceSum2);
            System.out.println();
            System.out.print("Player " + name2);
            System.out.println(", it is your turn!");
            playThirtyOrBust(dice1, dice2, score2, playerToggle);
        }

    }

    public static void playThirtyOrBust(int dice1, int dice2, int score, int playerToggle) {//There are so many factors to consider when writing this method. So much can go wrong and it is very important to make sure all conditional statements connect.
        int diceSum;//We must have two diceSums so that we can keep track of what is the total and what is the score.
        int currentDiceSum = dice1 + dice2;
        if (playerToggle == 0) {
            diceSum = PlayaOne.getDiceSum();
            System.out.println("Your score: " + score);//Figure out to have it be 0 in the beginning.
        } else {
            diceSum = PlayaTwo.getDiceSum();
            System.out.println("Your score: " + score);//Figure out to have it be 0 in the beginning.
        }

        System.out.println("Your roll: ");
        System.out.println("Die 1: " + dice1);
        System.out.println("Die 2: " + dice2);
        System.out.println("Total: " + currentDiceSum);

        System.out.println("Do you wish to (1) Keep die 1, (2) Keep die 2, (3) Keep the total? (Respond with 1 or 2 or 3):");
        Scanner input = new Scanner(System.in);
        int choose = input.nextInt();
        int sentinel = -1;//I use this sentinel value to filter through the user's responses.

        while (sentinel == -1) {//If you start experiencing bugs come back to this and delete this.
            if (choose == 1) {
                diceSum = diceSum - dice2;
                System.out.println("Your total: " + diceSum);
                sentinel = 0;
            } else if (choose == 2) {
                diceSum = diceSum - dice1;
                System.out.println("Your total: " + diceSum);
                sentinel = 0;
            } else if (choose == 3) {
                sentinel = 0;
                System.out.println("Your total: " + diceSum);
                //DiceSum stays the same.
            } else {
                System.out.println("Wrong input. Please read the prompt carefully and choose again");
            }
        }
        //The following conditional statements decide to continue the game if a player's dice Sum exceeds 30.
        if (diceSum > 30 && playerToggle == 0) {
            System.out.print("Your total: " + diceSum + ", your score is now reset to ");
            PlayaOne.setDiceSum(0);
            diceSum = PlayaOne.getDiceSum();
            System.out.println(PlayaOne.getDiceSum());
        } else if (diceSum > 30 && playerToggle != 0) {
            System.out.print("Your total: " + diceSum + ", your score is now reset to ");
            PlayaTwo.setDiceSum(0);
            diceSum = PlayaTwo.getDiceSum();
            System.out.println(PlayaTwo.getDiceSum());
        }

        //The following conditional statements make sure our Object.DiceSum (total) is up to date with each iteration of playThirtyorBust.
        if (playerToggle == 0) {
            PlayaOne.setDiceSum(diceSum);
        } else {
            PlayaTwo.setDiceSum(diceSum);
        }

        //The following conditional statements decide to end the game if either player's sum equals 30.
        if (diceSum == 30 && playerToggle != 0) {
            System.out.println("Your total: " + diceSum + "!" + "Congratulations, you WIN!!");
            PlayaTwo.setDiceSum(999);
        } else if (diceSum == 30 && playerToggle == 0) {
            System.out.println("Your total: " + diceSum + "!" + "Congratulations, you WIN!!");
            PlayaOne.setDiceSum(999);
        }

    }

    //This method rolls our die for us.
    public static int rollDice() {
        int dice = (int) (Math.random() * 7);
            return dice;
    }
}

