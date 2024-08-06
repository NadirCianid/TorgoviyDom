package backend.service;

import java.util.regex.Pattern;

import static InterfaceControllers.StartPoint.openSecondWindow;

public class ClientValidator {
    public boolean isDataValid(String fio, String agreementNumber, String phoneNumber, String email) {
        boolean dataIsCorrect = false;
        try {
            dataIsCorrect = validateFio(fio)
                    && validateAgreementNumber(agreementNumber)
                    && validatePhoneNumber(phoneNumber)
                    && validateEmail(email);
        } catch (NumberFormatException e ) {
            System.out.println("Ошибка обработки введенных строк.");
        }

        return dataIsCorrect;
    }

    private boolean validateAgreementNumber(String agreementNumber) {
        // Проверка формата номера договора (две буквы кирилицы в верхнем регистре и 16 цифр)
        if (!Pattern.matches("^[A-Z]{2}\\d{16}$", agreementNumber)) {
            openSecondWindow("Неправильный формат номера договора. Проверьте введенные данные.",
                    "Ошибка формата введенных данных.");

            return false;  // Недопустимый формат
        }

        // Если все проверки пройдены, возвращаем true
        return true;
    }



    public boolean validateFio(String inputString) {
        // Проверка допустимых символов (только буквы, пробелы и дефисы)
        if (!Pattern.matches("^[а-яА-ЯЁё\\s\\-]+$", inputString)) {
            openSecondWindow("Неправильный формат ФИО. Проверьте введенные данные.",
                    "Ошибка формата введенных данных.");

            return false;  // Недопустимые символы
        }

        // Проверка количества слов (требуется три слова)
        String[] words = inputString.split("\\s+");
        if (words.length < 3) {
            openSecondWindow("ФИО должно содержать минимум 3 слова. Проверьте введенные данные.",
                    "Ошибка формата введенных данных.");

            return false;  // Недопустимое количество слов
        }

        // Проверка каждого слова на начальную заглавную букву
        for (String word : words) {
            if (!Character.isUpperCase(word.charAt(0))) {
                openSecondWindow("Слова в ФИО должны начинаться с заглавной буквы. Проверьте введенные данные.",
                        "Ошибка формата введенных данных.");

                return false;  // Начальная буква слова не является заглавной
            }
        }

        // Проверка минимальной и максимальной длины (примерно от 2 до 100 символов)
        int minLength = 10;
        int maxLength = 100;
        if (!(inputString.length() >= minLength && inputString.length() <= maxLength)) {
            openSecondWindow("ФИО либо слишком короткое или слишком длинное. Проверьте введенные данные.",
                    "Ошибка формата введенных данных.");

            return false;  // Недопустимая длина
        }

        // Если все проверки пройдены, возвращаем true
        return true;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        // Проверка формата номера телефона (+7XXXXXXXXXX)
        if (!Pattern.matches("^\\+7\\d{10}$", phoneNumber)) {
            openSecondWindow("Неправильный формат номера телефона. Проверьте введенные данные.",
                    "Ошибка формата введенных данных.");

            return false;  // Недопустимый формат
        }

        // Если все проверки пройдены, возвращаем true
        return true;
    }

    public boolean validateEmail(String email) {
        // Проверка формата email
        if (!Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email)) {
            openSecondWindow("Неправильный формат email. Проверьте введенные данные.",
                    "Ошибка формата введенных данных.");

            return false;  // Недопустимый формат
        }

        // Если все проверки пройдены, возвращаем true
        return true;
    }
}
