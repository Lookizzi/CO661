package tearoom;

import tearoom.*;

class Drinker extends Thread implements Customer {

	private Machine machine;
	private int walletSize;
	public String name;
	private Status status;

	public Status status() {
		return this.status;
	}

	public int funds() {
		return this.walletSize;
	}

	// Allow someone else to make me wait
	public void makeMeWait() {
		this.status = Status.WAITING;
	}

	public Drinker(Machine machine, int walletSize, String name) {
		this.machine = machine;
		this.walletSize = walletSize;
		this.name = name;
	}

	public void run() {
		while (this.walletSize > 0) {

			try {
				// Have a little read of the paper...
				this.status = Status.RESTING;
				Thread.sleep(1000);

				// Let's get tea
				this.status = Status.QUEUEING;
				this.walletSize--;
				// Vend...
				Tea tea = this.machine.vend(new Coin(), this);

				if (tea != null) {

					this.status = Status.DRINKING;
					System.out.println(this.name + " is drinking some tea.");
					tea.drink();

				} else {
					System.out.println(this.name + " was robbed");
				}

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();

			}
		}

		System.out.println(this.name + ": all out of cash.");
	}
}
