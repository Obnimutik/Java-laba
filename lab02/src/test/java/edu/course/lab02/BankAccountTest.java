package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



class BankAccountTest {

    @Test
    void returnsTrueForWithdraw() {

        boolean result;

       try 
       {
            BankAccount acc1 = new  BankAccount(100);
            
            acc1.withdraw(50);

            result = true; 
        } 
        catch (IllegalArgumentException e) 
        {
            result = false; 
        }

        assertTrue(result);

    }

@Test
    void returnsFalseForWithdraw() {

        boolean result;

       try 
       {
            BankAccount acc1 = new  BankAccount(100);
            
            acc1.withdraw(150);

            result = true; 
        } 
        catch (IllegalArgumentException e) 
        {
            result = false; 
        }

        assertFalse(result);

    }

}
