/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/16/19
 * Time:
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: CashRegisterSimpleClient
 *
 * Description:  Tester client code for the updated CashRegister and Transaction classes.
 *
 * ****************************************
 */

package lab06;

/**
 * Simple cash register client to test the updated code for part 2 of lab06.
 * @athor Michael Heller and Sami Dunn
 * @version 0.1
 */
public class CashRegisterSimpleClient {
    /**
     * main method to test CashRegister and Transaction
     * @param args
     */
    public static void main(String[] args) {

        // First, let's evaluate a transaction
        Transaction trans = new Transaction();
        trans.addItemToTransaction(10);
        trans.addItemToTransaction(2.50);
        trans.addItemToTransaction(9.75);
        System.out.println("Testing Transaction:\n" + trans);

        // Now, let's evaluate one cash registers
        CashRegister reg = new CashRegister("Register 1");
        reg.setName("Register 1");
        reg.startShift(100.0);
        System.out.println("reg: New transaction: $2.50, $9.95, $5.50 = $17.95");
        reg.startTransaction();
        reg.scanItem(2.50);
        reg.scanItem(9.95);
        reg.scanItem(5.50);
        double amtBack = reg.collectPayment(15.0);
        amtBack = reg.collectPayment(10);
        reg.finishTransaction();
        System.out.println("reg: New transaction: $10, $7.50, $19.95, $5 = $42.45");
        reg.startTransaction();
        reg.scanItem(10);
        reg.scanItem(7.50);
        reg.scanItem(19.95);
        reg.scanItem(5.00);
        amtBack = reg.collectPayment(42.45);
        reg.finishTransaction();
        System.out.println("reg: Finishing the shift\n");
        reg.finishShift();
        System.out.println("\nreg: Should be empty:\n" + reg);
    }
}