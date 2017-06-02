package io.github.sidmishraw.accmgr;

import io.github.sidmishraw.accmgr.core.AccountManager;

import java.util.Scanner;

/**
 * Created by sidmishraw on 6/1/17.
 */
public class ApplicationDriver {

	/**
	 * REPL
	 */
	private static void controlLoop(boolean quit) {

		if (quit) {

			System.out.println("Bye!");
			return;
		}

		System.out.println("What do you want to do today (Enter your option): ");
		System.out.println("A: Open new account");
		System.out.println("B: Check balance of account");
		System.out.println("C: Transfer Money");
		System.out.println("D: Withdraw Money");
		System.out.println("E: Deposit Money");
		System.out.println("Q: Quit!");

		Scanner sc = new Scanner(System.in);

		AccountManager mgr = AccountManager.getInstance();

		switch (sc.next()) {

			case "A":
			case "a": {

				String accID = mgr.createAccount();
				System.out.println(String.format("Account with Account#: %s has been created. " +
						"Please note down the Account#: %s", accID, accID));

				controlLoop(false);

				break;
			}

			case "B":
			case "b": {

				System.out.println("Enter the Account#: ");
				String accID = sc.next();
				System.out.println(String.format("Current Balance of Account#: %s = %s", accID,
						mgr.getBalance(accID)));

				controlLoop(false);

				break;
			}

			case "C":
			case "c": {

				System.out.println("Enter the Sender Account#: ");
				String senderID = sc.next();
				System.out.println("Enter the Receiver Account#: ");
				String receiverID = sc.next();
				System.out.println("Enter the Amount");
				Double amount = sc.nextDouble();

				mgr.transferAmount(senderID, receiverID, amount);

				System.out.println("Amount has been transferred!");

				controlLoop(false);
				break;
			}

			case "D":
			case "d": {

				System.out.println("Enter the Account#: ");
				String accID = sc.next();
				System.out.println("Enter the Amount");
				Double amount = sc.nextDouble();

				mgr.withdraw(accID, amount);

				controlLoop(false);
				break;
			}

			case "E":
			case "e": {

				System.out.println("Enter the Account#: ");
				String accID = sc.next();
				System.out.println("Enter the Amount");
				Double amount = sc.nextDouble();

				mgr.deposit(accID, amount);

				controlLoop(false);
				break;
			}

			case "Q":
			case "q": {

				mgr.halt();
				controlLoop(true);
				break;
			}

			default: {

				System.out.println("Try entering a valid input!");
				System.out.println();
				controlLoop(false);
			}
		}
	}

	public static void main(String[] args) {

		controlLoop(false);
	}
}
