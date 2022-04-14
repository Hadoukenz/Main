class InputChecker {
    int resInt = 0;
    String resStr = "";
    int romanCounter = 0;
    int arabicCounter = 0;
    int romanCount1 = 0;
    int romanCount2 = 0;
    int arabicCount1 = 0;
    int arabicCount2 = 0;
    String toCalc = "";

    boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    InputChecker(String userInput) throws WrongCasesHandler {
        String[] arrayOfInput = userInput.split(" ");
        Roman2Arabic[] convertedToArray = Roman2Arabic.values();
        if (arrayOfInput.length > 3) {
            throw new WrongCasesHandler("Redundant operand(s) and/or operator(s)");
        } else if (arrayOfInput.length < 3) {
            throw new WrongCasesHandler("Not an equation, needs second operand or " +
                    "lacks operator and operand(s).");
        }
        String zerothOperand = arrayOfInput[0];
        String secondOperand = arrayOfInput[arrayOfInput.length - 1];

        for (int iterator = 0; iterator < 10; iterator++) {
            if (zerothOperand.toUpperCase().equals(convertedToArray[iterator].getString())) {
                romanCount1 = convertedToArray[iterator].getArabic();
                romanCounter++;
            }
        }

        if (romanCount1 == 0 && isStringInt(arrayOfInput[0])) {
            arabicCount1 = Integer.parseInt(arrayOfInput[0]);
        } else if (!isStringInt(arrayOfInput[0]) && romanCount1 == 0){
            throw new WrongCasesHandler("Roman numeral is outside of [I..X] range or integer was not used.");
        }

        if (((arabicCount1 > 0) && (arabicCount1 < 11)) && romanCount1 == 0) {
            arabicCounter++;
        } else if (((arabicCount1 < 1) || (arabicCount1 > 10)) && romanCount1 == 0){
            throw new WrongCasesHandler("Arabic numeral is outside of [1..10] range.");
        }


        for (int iterator = 0; iterator < 10; iterator++) {
            if (secondOperand.toUpperCase().equals(convertedToArray[iterator].getString())) {
                romanCount2 = convertedToArray[iterator].getArabic();
                romanCounter++;
            }
        }

        if (romanCount2 == 0 && isStringInt(arrayOfInput[arrayOfInput.length - 1])) {
            arabicCount2 = Integer.parseInt(arrayOfInput[arrayOfInput.length - 1]);
        } else if (romanCount2 == 0 && !isStringInt(arrayOfInput[arrayOfInput.length - 1])){
            throw new WrongCasesHandler("Roman numeral is outside of [I..X] range or integer was not used.");
        }

        if (romanCounter !=2){
            if ((arabicCount2 > 0) && (arabicCount2 < 11) && romanCount2 == 0) {
                arabicCounter++;
            } else if (romanCount1 != 0 || romanCount2 != 0) {
                throw new WrongCasesHandler("Only one type of numerals for both operands is allowed.");
            } else if ((arabicCount2 < 1) || (arabicCount2 > 10)){
                throw new WrongCasesHandler("Arabic numeral is outside of [1..10] range.");
            }
        }

        switch(romanCounter) {
            case 2: break;
            case 1: if (arabicCounter == 1) {
                throw new WrongCasesHandler("Only one type of numerals for both operands is allowed.");
            } else if (arabicCounter != 1){
                throw new WrongCasesHandler("Please enter correct value for other operand " +
                        "(out of [1..10] or [I..X] bounds)");
            }
        }

        int firstOperandsSum = arabicCount1 + romanCount1;
        int secondOperandsSum = arabicCount2 + romanCount2;

        switch (arrayOfInput[1]) {
            case "+": resInt = firstOperandsSum + secondOperandsSum;
                break;
            case "-": resInt = firstOperandsSum - secondOperandsSum;
                break;
            case "*": resInt = firstOperandsSum * secondOperandsSum;
                break;
            case "/": resInt = firstOperandsSum / secondOperandsSum;
                break;
            default: throw new WrongCasesHandler("Wrong operator been used.");
        }

        if (resInt < 1 && romanCounter == 2) {
            throw new WrongCasesHandler("Roman numbers can't be less than 1");
        }

        if (romanCounter == 2) {
            while (resInt >= 100) {
                resStr += "C";
                resInt -= 100;
            }
            while (resInt >= 90) {
                resStr += "XC";
                resInt -= 90;
            }
            while (resInt >= 50) {
                resStr += "L";
                resInt -= 50;
            }
            while (resInt >= 40) {
                resStr += "XL";
                resInt -= 40;
            }
            while (resInt >= 10) {
                resStr += "X";
                resInt -= 10;
            }
            while (resInt >= 9) {
                resStr += "IX";
                resInt -= 9;
            }
            while (resInt >= 5) {
                resStr += "V";
                resInt -= 5;
            }
            while (resInt >= 4) {
                resStr += "IV";
                resInt -= 4;
            }
            while (resInt >= 1) {
                resStr += "I";
                resInt -= 1;
            }
        }

        if (arabicCounter == 2) {
            toCalc = Integer.toString(resInt);
        } else if (romanCounter == 2) {
            toCalc = resStr;
        }
    }

    String getToCalc() {
        return toCalc;
    }
}