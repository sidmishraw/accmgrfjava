package io.github.sidmishraw.accmgr.core;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by sidmishraw on 6/1/17.
 */
public class AccountManager {

	private static AccountManager self = new AccountManager();

	/**
	 * The map holding the accounts of the Bank
	 */
	private ConcurrentMap<String, Account> accountMap = new ConcurrentHashMap<>();

	/**
	 * Private constructor for getting a singleton instance
	 */
	private AccountManager() {
	}

	public static AccountManager getInstance() {

		return self;
	}

	/**
	 * Creates the account and returns the Account ID of the newly created account.
	 *
	 * @return The account ID of the newly created account
	 */
	public String createAccount() {

		Account newAccount = new Account();
		String  accID      = String.valueOf(new Date().getTime());

		this.accountMap.put(accID, newAccount);

		return accID;
	}

	/**
	 * Fetches the current balance of the account
	 *
	 * @param accID - The account ID of the account
	 * @return The balance of the account
	 */
	public Double getBalance(String accID) {

		return this.accountMap.get(accID).getBalance();
	}

	/**
	 * Transfer the amount from sender's bank account to receiver's bank account
	 *
	 * @param senderID   - The ID of the sender
	 * @param receiverID - The ID of the receiver
	 * @param amount     - The amount to be transferred
	 */
	public void transferAmount(String senderID, String receiverID, Double amount) {

		Account senderAccount   = this.accountMap.get(senderID);
		Account receiverAccount = this.accountMap.get(receiverID);

		senderAccount.withdraw(Math.abs(amount));
		receiverAccount.deposit(Math.abs(amount));
	}

	/**
	 * Withdraws the amount from the account
	 *
	 * @param accID  - The account ID of the account
	 * @param amount - The amount to withdraw from the account
	 */
	public void withdraw(String accID, Double amount) {

		this.accountMap.get(accID).withdraw(Math.abs(amount));
	}

	/**
	 * Deposits the amount into the account
	 *
	 * @param accID  - The account ID of the account
	 * @param amount - The amount to deposit into the account
	 */
	public void deposit(String accID, Double amount) {

		this.accountMap.get(accID).deposit(Math.abs(amount));
	}

	/**
	 * Destroys all the accounts since the bank is stopping
	 */
	public void halt() {

		this.accountMap.keySet().stream().forEach((x) -> {

			this.accountMap.get(x).destroy();
		});
	}
}
