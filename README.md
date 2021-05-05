### Для запуска тестов необходимо:
1. Запустить контейнеры командой 'docker-compose up -d'
2. Запустить приложение командой 'java -jar ./artifacts/aqa-shop.jar' (по умолчанию приложение работает с базой MySQl. Для работы с базой PostgreSQL запустить приложение командой 'java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app')
3. Запустить автотесты командой 'gradlew clean test'. (Для работы с базой PostgreSQL запустить автотесты командой 'gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app')