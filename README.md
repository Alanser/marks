Тестовое задание:

<details> 
  <summary> Тестовое задание </summary>
   
   <pre>
   Используя Spring Boot (версия > 2.0) и openjdk 11 реализовать веб сервис со следующими методами:
   
   1.  Метод для сохранения оценок учеников из переданного текстового файл (принимает на входе файл):
   
   В котором на каждой строке содержатся, разделенные знаком доветочие «;» следующие данные
   a.  Номер класса ученика
   b.  ФИО ученика
   c.  Название предмета
   d.  Оценку
   e.  Дату получения оценки в UTC0
   
   При получении файла возвращает 200 код и запускает в фоне его обработку.
   Если в базе уже есть строка из файла, они игнорируется.
   
   2.  Метод для получения фио и среднего балла учеников по месяцам.
   
   Получает на входе номер класса.
   На выходе возвращает ФИО, месяц, средний бал.
   Усли класс не найден нужно вернуть 404 ошибку с текстом «Класс не найден»
   Реализовать через stream api.
   
   3.  Метод для получения фио и среднего балла учеников за весь период по номеру класса через реализацию на стороне бд, если класс не найден нужно вернуть 404 ошибку с текстом «Класс не найден»
   
   Все методы должны общаться с помощью rest, контейнер для передачи данных json.
   Для хранения данных нужно использовать бд (h2 или postgresql).
   </pre>
   
</details>


SpringBoot приложение, дополнительных установок не требуется. База данных H2 in memory. Запускается стандартно, через класс <code>MarksApplication</code>

<h2>Эндпойнты: </h2>

  - Экспорт файла: POST http://localhost:8080/upload Нужно передать файл через form-data с ключом 'file'. Тестовый файл с данными находится в корне проекта - marks.txt

Получение учеников и среднего балла:

  - По месяцам: GET http://localhost:8080/average-estimate-per-month/5 

  - За весь период: GET http://localhost:8080/average-estimate/5 

В базе хранятся 5, 6, 7, 8 классы. Если передать другие номера классов, то отдаст статус 404 и текст «Класс не найден».
