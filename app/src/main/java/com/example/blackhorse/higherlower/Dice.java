package com.example.blackhorse.higherlower;

public class Dice {

private int DiceNumberOfEyes;

    public Dice(int diceNumberOfEyes) {
        DiceNumberOfEyes = diceNumberOfEyes;
    }

    public int getDiceNumberOfEyes() {
        return DiceNumberOfEyes;
    }

    @Override
    public String toString() {
        return "Throw is " + (DiceNumberOfEyes+1);
    }
}
