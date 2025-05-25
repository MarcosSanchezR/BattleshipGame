package es.upm.etsisi.fis;
import es.upm.etsisi.fis.logic.PlayerManager;


import static org.junit.Assert.*;
import org.junit.*;

public class PlayerManagerTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{

    }

    @Before
    public  void setUp() throws Exception{

    }

    @After
    public void tearDowns() throws  Exception{

    }

    @Test
    public void testDarDeAlta(){
        PlayerManager playerManager = new PlayerManager();
        assertTrue(playerManager.darDeAlta("Eva","eva@upm.es","1234"));
    }

}

