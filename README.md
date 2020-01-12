
## Автоматический тест формы "Карта с доставкой!" с использованием генератора отчетов "Allure"
(проверка визуализации скрытых форм)
___

* Создать папку, открыть терминал по адресу папки и инициализировать систему Git `git init`
* Склонировать репозиторий `git clone https://github.com/AlexanderKachalov/Allure-Report.git`
* Перейти в директорию artifacts `cd artifacts` и выполнить команду `java -jar app-card-delivery-next.jar`
* Форма заявки находится по адресу `localhost:9999`
* Вернуться в директорию с проектом `cd /Allure-Report`
* Выполнить команду `./gradlew clean test`
* Отчет о выполнении тестов в файле `build/reports/tests/test/index.html`
* Для генерации отчета в 'Allure' выполнить команду `./gradlew allureReport`
* Allure-отчет о выполнении тестов в файле `build/reports/allure-report/index.html`
___
* Тестируемая форма (1) и отображение скрытых форм (2 и 3) при запланировании даты встречи и перепланировании даты:
---
            1 - тестирумая форма
![](pictures/order.png)

---
            2 - отображение 1-й скрытой формы при успешном запланировании встречи
![](pictures/success.png)

---
            3 - отображение 2-й скрытой формы при перепланировании встречи (отображается при заполнении даты теми же данными и измененной даты встречи)
![](pictures/replan.png)