package edu.course.lab02;

public class BankAccount {

    private int balance;

    public BankAccount(int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Начальный баланс не может быть отрицательным: " + initialBalance);
        }
        this.balance = initialBalance;
    }

    /**
     * Пополнение счёта.
     * @param amount сумма пополнения, должна быть положительной
     * @throws IllegalArgumentException если сумма не положительна
     */
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Сумма пополнения должна быть положительной: " + amount);
        }
        balance += amount;
    }

    /**
     * Снятие средств со счёта.
     * @param amount сумма снятия, должна быть положительной и не превышать остаток
     * @throws IllegalArgumentException если сумма не положительна или превышает остаток
     */
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Сумма снятия должна быть положительной: " + amount);
        }
        if (amount > balance) {
            throw new IllegalArgumentException(
                    "Недостаточно средств. Запрошено: " + amount + ", доступно: " + balance);
        }
        balance -= amount;
    }

    /**
     * Текущий баланс счёта.
     * @return баланс
     */
    public int getBalance() {
        return balance;
    }
}