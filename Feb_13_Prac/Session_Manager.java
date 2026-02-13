/*
Design a session management system.

Each session has:
- sessionId (String)
- userId (String)

The system can store at most N active sessions.

When a new session is added and the limit is exceeded:
- Remove the OLDEST session.

Implement SessionManager with:

1. addSession(String sessionId, String userId)
2. int getActiveSessionCount()
	-Returns the current number of active sessions stored in the system.
3. List<String> getActiveUsers()
   - Return unique userIds
   - Sorted alphabetically

Constraints:
- Do not use Set
- Maintain insertion order

*/

package Feb_13_Prac;
import java.util.*;

class SessionManager {

    class Session {
        String sessionId;
        String userId;

        Session(String sId, String uId) {
            this.sessionId = sId;
            this.userId = uId;
        }
    }

    Queue<Session> q;
    Map<String, Integer> userCount;
    int limit;

    SessionManager(int n) {
        q = new LinkedList<>();
        userCount = new HashMap<>();
        limit = n;
    }

    void addSession(String s_Id, String u_Id) {

        if (q.size() == limit) {
            Session old = q.poll();
            userCount.put(old.userId, userCount.get(old.userId) - 1);
            if (userCount.get(old.userId) == 0) {
                userCount.remove(old.userId);
            }
        }

        Session s = new Session(s_Id, u_Id);
        q.offer(s);
        userCount.put(u_Id, userCount.getOrDefault(u_Id, 0) + 1);
    }

    int getActiveSessionCount() {
        return q.size();
    }

    List<String> getActiveUsers() {
        List<String> users = new ArrayList<>(userCount.keySet());
        Collections.sort(users);
        return users;
    }
}

public class Session_Manager {
    public static void main(String[] args) {

        SessionManager manager = new SessionManager(3);

        manager.addSession("S1", "Alice");
        manager.addSession("S2", "Bob");
        manager.addSession("S3", "Alice");
        manager.addSession("S4", "Charlie");

        System.out.println(manager.getActiveSessionCount());
        System.out.println(manager.getActiveUsers());
    }
}
