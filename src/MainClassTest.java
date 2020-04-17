import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass localNumber = new MainClass();
    MainClass classNumber = new MainClass();

    @Test
    public void testGetLocalNumber(){
        Assert.assertEquals("The " + localNumber.getLocalNumber() +" is not 14!", 14, localNumber.getLocalNumber());
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("The " + classNumber.getClassNumber() + " is less (or equals) then 45" ,classNumber.getClassNumber() > 45);
     }
}
