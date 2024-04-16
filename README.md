# Math Expression TG-Bot

The bot calculate user's math expression like:
- 2+4
- (13-4)*56-6
- 32*7+5/3

Unfortunately, the bot currently working only with positive numbers.

### CI/CD

There are GitHub actions that used in the project. They used on 'push' and 'pull_request' actions. 
They only package the application and run the tests.

The application is deployed in "Heroku", and this service is integrated with GitHub. But
to run the application in this service, you need to downgrade to java version 8. 
I downgraded the Java version of the application and moved it to another repository (which is private)
and completed CD processing there.
But I want to use the features of newer java versions, so I'm not running full CI/CD processes in this repository.

### Algorithm

The Application use 'Reverse Polish Notation' algorithm. This algorithm transform human-readable expressions 
to 'special' expressions. In reverse Polish notation, the operators follow their operands. For example: 
- (13-4)*56-6 -> 13 4 - 56 * 6 - 

When expression has been transformed to RPN expression, application finds operators and evaluates 2 previous operands.

### Run Local

Instruction to run application local:
- clone the project
```sh
git clone https://github.com/ar-andersen/AndersenHW2
```
- go to project folder
- package the project
```sh
mvn package
```
- run the program
```sh
java -Dbot.name={BOT_NAME} -Dbot.token={BOT_TOKEN} -cp target/tg-bot-jar-with-dependencies.jar BotInitializer
```
where {BOT_NAME} and {BOT_TOKEN} are your OWN parameters of your bot. You can find those parameters in @BotFather (https://t.me/BotFather)

NOTE: Application use 17 version of java.

### Testing

To check the bot, you can go to https://t.me/andersen_rybak_hw2_bot