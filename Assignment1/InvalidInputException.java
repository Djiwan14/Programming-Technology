/*
 * Name : Shokhrukh Nigmatillaev
 * Neptun : APVAVZ
 * Task : 1
 * */
package Assignment1;

class InvalidInputException extends Exception {

    public InvalidInputException() {

    }

}
// if amount of creatures is below 0 o 0 we throw exception |
class NegativeNumberException extends Exception {

    public NegativeNumberException() {

    }

}
// if letter in file is not matched with types of creatures |
class NoSuchCreatureException extends Exception {

    public NoSuchCreatureException() {

    }

}
// if letter in file is not matched with types of days |
class NoSuchDayException extends Exception {

    public NoSuchDayException() {

    }

}
// if water level initially is below 0 |
class InvalidWaterLevelException extends Exception {

    public InvalidWaterLevelException() {

    }

}

