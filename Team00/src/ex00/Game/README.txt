mvn package

java -cp 'target/classes:target/dependency/*' edu.school21.console.game.app.Main --enemiesCount=10 --wallsCount=10 --size=30 --profile=production
