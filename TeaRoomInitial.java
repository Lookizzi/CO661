package tearoom;

import java.util.LinkedList;
import tearoom.*;

public class TeaRoomInitial implements TeaRoom {

	public static int n = 5;
	private LinkedList<Drinker> customers;
	private Machine machine;

	public LinkedList<Drinker> customers() {
		return this.customers;
	}

	public Machine machine() {
		return this.machine;
	}

	public TeaRoomInitial(int supply, int walletSize) {

		this.machine   = new Machine(supply);
		this.customers = new LinkedList<Drinker>();

		for (int i = 0; i < n; i++) {
			Drinker d = new Drinker(machine, walletSize, "Customer " + i);
			d.start();
			customers.add(d);
		}



	}
}

