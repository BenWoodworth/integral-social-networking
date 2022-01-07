## Description of Code

The code is simple, with `Post` and `User` classes that just hold data (POJOs, with no business logic),
and a `SocialNetwork` class with all the business logic.

`User` only stores the user's name as a `String`.

`Post` stores the poster (`User`), a message (`String`), and a time (`Instant`).

`SocialNetwork` keeps track of user profiles internally (a user's posts, and who they follow).
All its methods (`publishMessage`, `viewTimeline`, `follow`, and `viewWall`) access or manipulate
the profiles in order to do what's needed.

## Building/Testing
(Requires Java 11 or newer)

Open a terminal and `cd` into this project's directory.

##### To Build 
On Windows: `.\gradlew.bat build`  
On macOS/Linux: `./gradlew build`

##### To Test:
On Windows: `.\gradlew.bat test`  
On macOS/Linux: `./gradlew test`
