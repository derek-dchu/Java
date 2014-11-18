/**
 * LeetCode OJ: Gas Station
 */


public class GasStation {
	/* O(n) with two facts:
	 * 1. If start from index k where can only travel to k + m station, starting points
	 * 	  k + 1, ... , k + m - 1 cannot travel beyond k + m station.
	 * 2. If total available gas is less that total cost, any starting point wouldn't has enough
	 * 	  gas to travel around the circular.
	 */
	public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
        	return -1;
        }
        
        int tank = 0;
        int totalGasCostDifferece = 0;
        int index = -1;
        
        for (int i = 0; i < gas.length; i++) {
        	tank += gas[i] - cost[i];
        	totalGasCostDifferece += gas[i] - cost[i];
        	if (tank < 0) {
        		index = i;
        		tank = 0;
        	}
        }
        
        return totalGasCostDifferece < 0 ? -1 : index + 1;
        
        //return bruteForce(gas, cost);
    }
	
	/* O(n^2) try every starting point */
	public int bruteForce(int[] gas, int[] cost) {
		int tank = 0;
		for (int i = 0; i < gas.length; i++) {
			int j = 0;
			for (; j < gas.length; j++) {
				int tmp = i + j;
				int acutal_j = tmp >= gas.length ? tmp - gas.length : tmp;
				if (tank + gas[acutal_j] < cost[acutal_j]) {
					break;
				}
				
				tank = tank + gas[acutal_j] - cost[acutal_j];
			}
			
			if (j == gas.length) return i;
			
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		GasStation gs = new GasStation();
		int[] gas = {5};
		int[] cost = {4};
		assert gs.canCompleteCircuit(gas, cost) == 0 : "Not match";
		gas = new int[]{1, 2};
		cost = new int[]{2, 1};
		assert gs.canCompleteCircuit(gas, cost) == 1 : "Not match";
	}
}
