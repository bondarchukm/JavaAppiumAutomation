import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass localNumber = new MainClass();
    MainClass classNumber = new MainClass();
    MainClass classString = new MainClass();

    @Test
    public void testGetLocalNumber(){
        Assert.assertEquals("The " + localNumber.getLocalNumber() +" is not 14!", 14, localNumber.getLocalNumber());
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("The " + classNumber.getClassNumber() + " is not bigger then 45" ,classNumber.getClassNumber() > 45);
     }

     @Test
    public void testGetClassString(){
        Assert.assertTrue("String does not contain 'hello' or 'Hello'", classString.getClassString().contains("hello") || classString.getClassString().contains("Hello"));
     }
}
