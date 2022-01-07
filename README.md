## Description of Code

Test code can be found [here](./src/test/java/net/benwoodworth/katas/socialNetwork), and the main implementation code [here](./src/main/java/net/benwoodworth/katas/socialNetwork).

The code itself is pretty straightforward, with the `SocialNetwork` class having all the business logic.
There are also `Post` and `User` classes (POJOs) that just hold data, without any state.

[`User`](./src/main/java/net/benwoodworth/katas/socialNetwork/User.java) only has a name (`String`).

[`Post`](./src/main/java/net/benwoodworth/katas/socialNetwork/Post.java) stores the posting user (`User`), a message (`String`), and a time (`Instant`).

[`SocialNetwork`](./src/main/java/net/benwoodworth/katas/socialNetwork/SocialNetwork.java) works by keeping track of user profiles internally (a user's posts, and who they follow).
All its methods (`publishMessage`, `viewTimeline`, `follow`, and `viewWall`) access or manipulate
the profiles in order to fulfill the business requirements.

## Building/Testing
(Requires Java 11 or newer)

Open a terminal and `cd` into this project's directory.

##### To Build 
On Windows: `.\gradlew.bat build`  
On macOS/Linux: `./gradlew build`

##### To Test:
On Windows: `.\gradlew.bat test`  
On macOS/Linux: `./gradlew test`
