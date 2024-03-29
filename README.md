AutoAssembly README 
Класи Сутностей
Клас Entity
Призначення: Клас Entity є базовим класом для всіх сутностей в системі. Він визначає загальну структуру для сутностей, що мають унікальний ідентифікатор (UUID), стан валідації та відстеження помилок.

Методи та Поля:

UUID id: Поле, що представляє унікальний ідентифікатор сутності.
List<String> errors: Список для відстеження помилок валідації, пов'язаних із сутністю.
boolean isValid: Поле, яке вказує, чи є сутність валідною (без помилок).
boolean isValid(): Метод, який перевіряє, чи є сутність валідною, перевіряючи відсутність помилок.
List<String> getErrors(): Метод, що повертає список помилок, пов'язаних із сутністю.
equals(Object o): Метод, що порівнює об'єкти сутностей за їхнім унікальним ідентифікатором.
hashCode(): Метод, що перевизначає генерацію хеш-коду на основі унікального ідентифікатора сутності.
Клас User
Призначення: Клас User представляє користувача системи та наслідується від базового класу Entity. Цей клас визначає особливості користувача, такі як пароль, електронна пошта, дата народження та ім'я користувача.

Методи та Поля:

UUID id: Поле, що представляє унікальний ідентифікатор користувача.
String password: Поле, що зберігає пароль користувача.
String email: Поле, що зберігає електронну пошту користувача.
LocalDate birthday: Поле, що зберігає дату народження користувача.
String username: Поле, що зберігає ім'я користувача.
Методи:

User(UUID id, String password, String email, LocalDate birthday, String username): Конструктор класу, який ініціалізує об'єкт користувача.
setEmail(String email): Метод, який встановлює значення електронної пошти після валідації.
setUsername(String username): Метод, який встановлює значення ім'я користувача після валідації.
validatedPassword(String password): Приватний метод для валідації паролю користувача.
validatedBirthday(LocalDate birthday): Приватний метод для валідації дати народження користувача.
toJsonObject(): Метод, який перетворює об'єкт користувача в об'єкт JsonObject для подальшого використання.

© 2024 Puchka Andrian. Всі права захищені.
