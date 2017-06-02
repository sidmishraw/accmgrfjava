package io.github.sidmishraw.accmgr.core;


import io.github.sidmishraw.fjava.core.Variable;

/**
 * Created by sidmishraw on 6/1/17.
 * <p>
 * Account class used FJava, so no assignment, iteration, mutable data structures allowed.
 */
public class Account {

	/**
	 * The balance of the account
	 */
	private Variable<Double> balance;

	public Account() {

		// Account initialized with 0 balance
		this.balance = new Variable<>(0.00);
	}

	/**
	 * Withdraws the amount from the account
	 *
	 * @param amount - The amount to be withdrawn from the account
	 */
	public void withdraw(Double amount) {

		this.balance.set(this.balance.get() - amount);
	}

	/**
	 * Deposits the amount into the account
	 *
	 * @param amount - The amount to be deposited into the account
	 */
	public void deposit(Double amount) {

		this.balance.set(this.balance.get() + amount);
	}

	/**
	 * Returns the balance of the account
	 *
	 * @return the current balance of the account
	 */
	public Double getBalance() {

		return this.balance.get();
	}

	/**
	 * Destroys the account, a destructor that is called to delete the account
	 */
	public void destroy() {

		this.balance.delete();
	}
}
