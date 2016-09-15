package com.ncredinburgh.calculator.ali_shahri;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		System.out
				.println("Calculator initialised, please type in the word \"exit\" in order to close this application");
		String str = "";
		try (Scanner reader = new Scanner(System.in)) {
			while (true) {
				System.out.println("Please enter a calculation:\n");
				str = reader.nextLine();
				str = str.toLowerCase();
				//setting an exit command
				if (str.equals("exit")) {
										System.out.println("Calculator closed");
					break;
				}
				//dividing user's input as words into array, splitting by spaces
				String[] words = str.split(" "); 
						
				//converting the words into math equivalent
				ArrayList<String> mathExpression = toMathExpression(words);
				//in case a non-math word is used, detect and restart the loop
				if (mathExpression.get(0) == "invalid")
					continue;
				//calculate the math expression
				calculateString(mathExpression);
			}
		}catch (Exception e) {
            System.out.println("There was an error in reading the line!");}
	}

	// ---------------------------------------------------------------------------------------------
						
							/* Methods used in the main method */



	// This method converts the given text to math expression
	public static ArrayList<String> toMathExpression(String[] words) {
		ArrayList<String> expression = new ArrayList<>();
		for (String word : words) {
			switch (word) {
			case "zero":
				expression.add("0");
				//we need to use continue in order to go through the whole for loop, 
				//a break would stop the loop and fail to read and convert the whole words
				continue;
			case "one":
				expression.add("1");
				continue;
			case "two":
				expression.add("2");
				continue;
			case "three":
				expression.add("3");
				continue;
			case "four":
				expression.add("4");
				continue;
			case "five":
				expression.add("5");
				continue;
			case "six":
				expression.add("6");
				continue;
			case "seven":
				expression.add("7");
				continue;
			case "eight":
				expression.add("8");
				continue;
			case "nine":
				expression.add("9");
				continue;
			case "ten":
				expression.add("10");
				continue;
			case "plus":
				expression.add("+");
				continue;
			case "add":
				expression.add("+");
				continue;
			case "subtract":
				expression.add("-");
				continue;
			case "minus":
				expression.add("-");
				continue;
			case "less":
				expression.add("-");
				continue;
			case "times":
				expression.add("*");
				continue;
			case "multiplied-by":
				expression.add("*");
				continue;
			case "over":
				expression.add("/");
				continue;
			case "divided-by":
				expression.add("/");
				continue;
			default:
				System.out
						.println("\"There is an invalid word in the input, please make sure that your\n"
								+ "input is numbers between one to ten, and correct arithmatic operations are used\"");
				//In case the first input of the user is invalid, we need to add a value to the first index 
				//so that it is created and can be replaced with what we want, in this case, string "invalid". 
				//Avoiding this will throw an exception. 
				expression.add("");
				expression.set(0, "invalid");
				return expression;
			}
		}
		return expression;
	}

	// ---------------------------------------------------------------------------------------------
	// This method calculates the converted expression
	public static void calculateString(ArrayList<String> expression) {
		int tmpIndex = 0;
		double tmp = 0d;
		String indexValue = "";
		String newTmp = "";
		// ---------------------------------------------------------------------------------------------

		/*
		 * This part calculates the division first, goes through all divisions,
		 * calculates them, replaces the result with the left hand index,
		 * removes the division and inserts Null value in the right hand index
		 */
		Iterator<String> itrDivide = expression.iterator();
		while (itrDivide.hasNext()) {
			indexValue = itrDivide.next();
			tmpIndex = expression.indexOf(indexValue);
			if (indexValue == "") {
				itrDivide.remove();
				continue;
			}
			if (indexValue == "/") {
				tmp = Double.parseDouble(expression.get(tmpIndex - 1))
						/ Double.parseDouble(expression.get(tmpIndex + 1));

				newTmp = Double.toString(tmp);
				// System.out.println(newTmp);
				expression.set(tmpIndex - 1, newTmp);
				// System.out.println(expression.get(tmpIndex));
				expression.set(tmpIndex + 1, "");
				itrDivide.remove();
			}
		}
		/*
		 * since iterator does not allow removing another index because of the
		 * Concurrent Modification Exception, the right hand indexes have been
		 * replaced with Null string, here we remove those indexes that have
		 * Null values.
		 * */
		Iterator<String> itrDivide2 = expression.iterator();
		while (itrDivide2.hasNext()) {
			indexValue = itrDivide2.next();
			tmpIndex = expression.indexOf(indexValue);
			if (indexValue == "") {
				itrDivide2.remove();
			}
		}
		// ---------------------------------------------------------------------------------------------
		/*
		 * This part calculates the multiplication, goes through all
		 * multiplications, calculates them, replaces the result with the left
		 * hand index, removes the multiplication and insert Null string as the
		 * value of the right hand index to be removed next
		 * */

		Iterator<String> itrMult = expression.iterator();
		while (itrMult.hasNext()) {
			indexValue = itrMult.next();
			tmpIndex = expression.indexOf(indexValue);
			if (indexValue == "") {
				itrMult.remove();
				continue;
			}

			if (indexValue == "*") {
				tmp = Double.parseDouble(expression.get(tmpIndex - 1))
						* Double.parseDouble(expression.get(tmpIndex + 1));
				newTmp = Double.toString(tmp);
				expression.set(tmpIndex - 1, newTmp);
				expression.set(tmpIndex + 1, "");
				itrMult.remove();
			}
		}

		/* since iterator does not allow removing another index because of the
		 * Concurrent Modification Exception,
		 * the right hand indexes have been replaced with Null string,
		 * here we remove those indexes that have Null value.
		 * */

		Iterator<String> itrMult2 = expression.iterator();
		while (itrMult2.hasNext()) {
			indexValue = itrMult2.next();
			tmpIndex = expression.indexOf(indexValue);
			if (indexValue == "") {
				itrMult2.remove();
			}
		}

		// ---------------------------------------------------------------------------------------------
		/* This part calculates the subtract operations, goes through all
		 * subtracts, calculates them, replaces the result with the left hand index,
		 * removes the subtract operation and inserts Null string
		 * as the value of the right hand index to be removed next
		 * */
		Iterator<String> itrSub = expression.iterator();
		while (itrSub.hasNext()) {
			indexValue = itrSub.next();
			tmpIndex = expression.indexOf(indexValue);
			if (indexValue == "") {
				itrSub.remove();
				continue;
			}
			if (indexValue == "-") {
				tmp = Double.parseDouble(expression.get(tmpIndex - 1))
						- Double.parseDouble(expression.get(tmpIndex + 1));
				newTmp = Double.toString(tmp);
				expression.set(tmpIndex - 1, newTmp);
				expression.set(tmpIndex + 1, "");
				itrSub.remove();
			}
		}
		/* since iterator does not allow removing another index because of the
		 * Concurrent Modification Exception,
		 * the right hand indexes have been replaced with Null string,
		 * here we remove those indexes that have Null value.
		 * */

		Iterator<String> itrSub2 = expression.iterator();
		while (itrSub2.hasNext()) {
			indexValue = itrSub2.next();
			tmpIndex = expression.indexOf(indexValue);
			if (indexValue == "") {
				itrSub2.remove();
			}
		}

		// ---------------------------------------------------------------------------------------------
		/*This part calculates the add operations, goes through all
		 * adds, calculates them, replaces the result with the left hand index,
		 * removes the add operation and inserts Null string
		 * as the value of the right hand index to be removed next
		 * */

		Iterator<String> itrAdd = expression.iterator();
		while (itrAdd.hasNext()) {
			indexValue = itrAdd.next();
			tmpIndex = expression.indexOf(indexValue);
			if (indexValue == "") {
				itrAdd.remove();
				continue;
			}
			if (indexValue == "+") {
				tmp = Double.parseDouble(expression.get(tmpIndex - 1))
						+ Double.parseDouble(expression.get(tmpIndex + 1));
				newTmp = Double.toString(tmp);
				expression.set(tmpIndex - 1, newTmp);
				expression.set(tmpIndex + 1, "");
				itrAdd.remove();
			}
		}

		/* since iterator does not allow removing another index because of the
		 * Concurrent Modification Exception, the right hand indexes have been replaced with Null string,
		 * here we remove those indexes that have Null value.
		 * */
		Iterator<String> itrAdd2 = expression.iterator();
		while (itrAdd2.hasNext()) {
			indexValue = itrAdd2.next();
			tmpIndex = expression.indexOf(indexValue);
			if (indexValue == "") {
				itrAdd2.remove();
			}
		}

		// ---------------------------------------------------------------------------------------------
		// outputting the final result
		if (expression.size() == 1) {
			System.out.printf("Result: %.2f\n",
					Double.parseDouble(expression.get(0)));
		} else {
			System.out
					.println("Please make sure that the input is valid, \nYou may have missed an operand or and operation");
		}
	}

}
