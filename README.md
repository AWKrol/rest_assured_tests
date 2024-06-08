Проект для API автотестирования

Команды для запуск тестов:
mvn clean test - запускает по умолчанию профиль dev и suite dev.xml, который содержит все тесты.

Если необходимо выбрать профиль, необходимо использовать ключ -P и указать требуемый профиль(например dev).

Чтобы запустить определенный набор тестов, неоюходимо использовать ключ -D и указать имя файла из директории suiteXML.
dev.xml - запускает все тесты(позитивные и негативные)
devPositive.xml - запускает только позитивные тесты
devNegative.xml - запускает только негативные тесты

Примеры:
mvn clean test
mvn clean test -P dev -D suiteXml=dev.xml
mvn clean test -P dev -D suiteXml=devPositive.xml
mvn clean test -P dev -D suiteXml=devNegative.xml
