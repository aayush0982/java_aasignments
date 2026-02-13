/*
Design an analytics system to track actions performed within an application.

The system stores all actions performed in the application.
Once the system has registered a specified number of actions K,
it sends them to an AnalyticsStore for storage.

Actions are represented by an enumeration called ActionEnum.
(The definition of ActionEnum is provided in the code stub.)

------------------------------------------------------------
Implement the Analytics class with the following specifications:
------------------------------------------------------------

1. Constructor:
   Analytics(AnalyticsStore analyticsStore, int K)

   - Initializes the Analytics class with:
     • an AnalyticsStore object
     • an integer K representing the batch size

------------------------------------------------------------
2. Methods:
------------------------------------------------------------

1) void registerAction(ActionEnum action)

   - Registers an action in the system.
   - When the number of registered actions reaches K:
     • Call analyticsStore.storeActions(Queue<ActionEnum> q)
     • The queue must contain exactly K actions
     • Actions must be in the order they occurred

------------------------------------------------------------

2) int getNumberOfActionRegisteredButNotSentToAnalyticsStore()

   - Returns the number of actions currently registered
     but not yet sent to the AnalyticsStore.

------------------------------------------------------------

3) int getTotalNumberOfLoggedActions()

   - Returns the total number of actions that have been logged
     (both stored in AnalyticsStore and currently pending).

------------------------------------------------------------

4) List<ActionEnum> getMostFrequentlyUsedActions()

   - Returns a list of the most frequently performed actions
     among all actions registered so far.
   - If multiple actions have the same highest frequency,
     include all of them.
   - The returned list must be sorted alphabetically
     based on the string value of the ActionEnum entries.

------------------------------------------------------------
Input Format:
------------------------------------------------------------

- The input starts with:
  • totalNumberOfRequests
  • integer K

- Followed by totalNumberOfRequests commands.

Each request can be one of the following:

1) registerAction X
   - Calls registerAction(X)

2) getTotalNumberOfLoggedActions
   - Calls getTotalNumberOfLoggedActions()

3) getMostFrequentlyUsedActions
   - Calls getMostFrequentlyUsedActions()

4) getNumberOfActionRegisteredButNotSentToAnalyticsStore
   - Calls getNumberOfActionRegisteredButNotSentToAnalyticsStore()

------------------------------------------------------------
Constraints:
------------------------------------------------------------

- 1 ≤ totalNumberOfRequests ≤ 100000
- 1 ≤ K ≤ 20

------------------------------------------------------------
Note:
------------------------------------------------------------

- The code stub handles input and output.
- Only the Analytics class needs to be implemented.
- Order of actions must be preserved when sending to the store.
*/

package Feb_13_Prac;

import java.util.*;

enum ActionEnum {
	LOGIN, LOGOUT, CLICK, VIEW, PURCHASE
}

class AnalyticsStore {
	List<ActionEnum> list = new ArrayList<>();
	void storeActions(Queue<ActionEnum> q) {
		while(!q.isEmpty()) {
			list.add(q.poll());
		}
	};
}

class Analytics {
	Queue<ActionEnum> pending;
	Map<ActionEnum, Integer> m;
	final AnalyticsStore analyticsStore;
	int opertaions;
	int loggedActions;

	Analytics(AnalyticsStore analyticsStore, int K) {
		this.analyticsStore = analyticsStore;
		opertaions = K;
		pending = new LinkedList<>();
		m = new HashMap<>();
		loggedActions = 0;
	}

	void registerAction(ActionEnum action) {
		pending.offer(action);
		m.put(action, m.getOrDefault(action, 0) + 1);
		loggedActions += 1;
		if (pending.size() == opertaions) {
			analyticsStore.storeActions(pending);
			pending.clear();
		}
	}

	int getNumberOfActionRegisteredButNotSentToAnalyticsStore() {
		return pending.size();
	}

	int getTotalNumberOfLoggedActions() {
		return loggedActions;
	}

	List<ActionEnum> getMostFrequentlyUsedActions() {
		int maxi = Integer.MIN_VALUE;
		for (int i : m.values()) {
			maxi = Math.max(maxi, i);
		}
		List<ActionEnum> l = new ArrayList<>();
		for (Map.Entry<ActionEnum, Integer> e : m.entrySet()) {
			if (e.getValue() == maxi) {
				l.add(e.getKey());
			}
		}

		l.sort(Comparator.comparing(Enum::name));
		return l;
	}
}

public class Analytic_System_Core_java {
	public static void main(String[] args) {
		AnalyticsStore store = new AnalyticsStore();
		Analytics analytics = new Analytics(store, 2);

		analytics.registerAction(ActionEnum.LOGIN);
		analytics.registerAction(ActionEnum.LOGOUT);

		System.out.println(analytics.getNumberOfActionRegisteredButNotSentToAnalyticsStore());

		System.out.println(analytics.getTotalNumberOfLoggedActions());

		System.out.println(analytics.getMostFrequentlyUsedActions());

		analytics.registerAction(ActionEnum.LOGIN);

		System.out.println(analytics.getNumberOfActionRegisteredButNotSentToAnalyticsStore());

		System.out.println(analytics.getTotalNumberOfLoggedActions());

		System.out.println(analytics.getMostFrequentlyUsedActions());
	}

}
