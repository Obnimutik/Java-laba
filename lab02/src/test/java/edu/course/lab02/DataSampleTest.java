package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DataSampleTest {
    
    @Test
    void returnsTrueDataSample() {

       boolean result;

       try 
       {
            DataSample ds1 = new  DataSample("01","Первое измерение" , new double[] {1.5, 2.5, 3.5, 4.5});
            
        
            result = true; 
        } 
        catch (IllegalArgumentException e) 
        {
            result = false; 
        }

        assertTrue(result);

    }
   
    @Test
    void returnsFalseForID() {

       boolean result;

       try 
       {
            DataSample ds1 = new  DataSample(null,"Первое измерение" , new double[] {1.5, 2.5, 3.5, 4.5});
            
        
            result = true; 
        } 
        catch (IllegalArgumentException e) 
        {
            result = false; 
        }

        assertFalse(result);

    }
    @Test
    void returnsFalseForLabel() {

       boolean result;

       try 
       {
            DataSample ds1 = new  DataSample("01", null,new double[] {1.5, 2.5, 3.5, 4.5});
            
        
            result = true; 
        } 
        catch (IllegalArgumentException e) 
        {
            result = false; 
        }

        assertFalse(result);

    }

}
