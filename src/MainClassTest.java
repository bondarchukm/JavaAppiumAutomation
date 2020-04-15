import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass number = new MainClass();

    @Test
    public void testGetLocalNumber(){
        Assert.assertEquals("Ups! The number is not 14!", 14, number.getLocalNumber());
    }
}
