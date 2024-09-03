package example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

public class AppTest {

    private App calculator;

    @BeforeEach
    void setUp() {
    
        calculator = new App();
    
    }

    @Test
    void testAddition() {
      
        calculator.display.setText("5");
        calculator.processOperator('+');
        calculator.display.setText("5");  // Assuming this sets up the state to add to the current number
        calculator.processEquals();
        assertEquals("10.0", calculator.display.getText(), "5 + 5 should be 10");

        calculator.display.setText("3");
        calculator.processOperator('+');
        calculator.display.setText("3");
        calculator.processEquals();
        assertEquals("6.0", calculator.display.getText(), "3 + 3 should be 6");
    }

    @Test
    void testSubtraction() {
        calculator.display.setText("5");
        calculator.processOperator('-');
        calculator.display.setText("3");
        calculator.processEquals();
        assertEquals("2.0", calculator.display.getText(), "5 - 3 should be 2");
    }
}
