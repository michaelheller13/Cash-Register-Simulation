/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Michael Heller and Sami Dunn
 * Section: 11 am
 * Date: 9/15/19
 * Time:
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: CashRegisterChecker
 *
 * Description:  Test program for the CashRegister and Transaction classes.
 *
 * ****************************************
 */

package lab06;

/**
 * Initializes CashRegisterChecker class to see if the cash register program can successfully simulate
 * a store cash register.
 *
 * @author Dr. King
 */
public class CashRegisterChecker {

    public static void main(String[] args) {

        // First, let's evaluate a transaction
        Transaction trans = new Transaction();
        trans.addItemToTransaction(10);
        trans.addItemToTransaction(2.50);
        System.out.println("trans: 2 items, total = 12.50");
        System.out.println(trans);

        // Now, let's evaluate a couple of cash registers
        CashRegister reg1 = new CashRegister(),
                reg2 = new CashRegister("Register 2");
        System.out.println("Constructed:\n" + reg1 + "\n" + reg2);
        reg1.setName("Register 1");
        reg1.startShift(100.0);
        reg2.startShift(200.0);
        System.out.println("Started day:\n" + reg1 + "\n" + reg2);
        System.out.println("TESTING: reg1: New transaction: $2.50, $9.95, $5.50 = $17.95");
        reg1.startTransaction();
        reg1.scanItem(2.50);
        reg1.scanItem(9.95);
        reg1.scanItem(5.50);
        System.out.println(reg1);
        System.out.println("reg1.isInTransaction() = true. ACTUAL = " + reg1.isInTransaction() );
        System.out.println("reg1.getAmountOwed() = 17.95. ACTUAL = " + reg1.getAmountOwed());
        double amtBack = reg1.collectPayment(15.0);
        System.out.printf("reg1: made payment of $15. Result = -2.95: ACTUAL = $%.2f%n", amtBack);
        System.out.println(reg1);
        System.out.println("reg1: reg1.finishTransaction() = false. ACTUAL = " + reg1.finishTransaction());
                System.out.printf("reg1: reg1.collectPayment(10), result: $7.05 ACTUAL = $%.2f%n",
                        reg1.collectPayment(10));
        System.out.println(reg1);
        System.out.println("reg1: reg1.finishTransaction() = true. ACTUAL = " + reg1.finishTransaction());
                System.out.println(reg1);
        System.out.println("reg1: New transaction: $10, $7.50, $19.95, $5 = $42.45");
        reg1.startTransaction();
        reg1.scanItem(10);
        reg1.scanItem(7.50);
        reg1.scanItem(19.95);
        reg1.scanItem(5.00);
        System.out.println(reg1);
        System.out.printf("reg1.collectPayment($42.45) = 0.0. ACTUAL = $%.2f%n",
                reg1.collectPayment(42.45));
        System.out.println(reg1);
        System.out.printf("Finish shift before starting?%n reg.finishShift() < 0.0. ACTUAL = %.1f%n", reg1.finishShift());
                reg1.finishTransaction();
        System.out.printf("reg1.finishDay() = $160.40. ACTUAL = $%.2f%n", reg1.finishShift());
        System.out.println("Reg1 should be empty:\n" + reg1);
        System.out.printf("reg2.finishDay(): = $200. ACTUAL = $%.2f%n", reg2.finishShift());
        System.out.println("Reg2 should be empty:\n" + reg2);
    }
}