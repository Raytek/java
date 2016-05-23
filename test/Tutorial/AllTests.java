package Tutorial;

import junit.framework.TestSuite;
import junit.framework.Test;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        suite.addTestSuite(LivroTest.class);
        suite.addTestSuite(PessoaTest.class);
        suite.addTestSuite(BibliotecaTest.class);
        return suite;
    }
}

