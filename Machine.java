package tearoom;

import java.util.concurrent.locks.ReentrantLock;

import tearoom.*;

class Machine implements VendingMachine {

	private int supply;
	private int profit;

	// Boilerplate getters

	public int supply() {
		return this.supply;
	}

	public int profit() {
		return this.profit;
	}

	// Constructor

	public Machine(int supply) {
		this.supply = supply;
		this.profit = 0;
	}

	// Main action
	
	public static final ReentrantLock lock = new ReentrantLock(true);

	public Tea vend(Coin c, Drinker drinker) {

		lock.lock();
		try {
			Tea possibleTea = null; // might end up with no tea if not enough supply
			drinker.makeMeWait();
	
			if (this.supply > 0) {
	
				// Economics
				this.profit++;
				this.supply--;
	
				// Pouring the tea
				try {
	
					Thread.sleep(2000);
	
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
	
				// Tea is ready
				System.out.println("Machine is dispensing for " + drinker.name);
				possibleTea = new Tea();
	
			}
	
			return possibleTea;
		}
		finally {
			lock.unlock();
		}
	}

}