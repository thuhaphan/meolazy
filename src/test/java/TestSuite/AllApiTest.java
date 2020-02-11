package TestSuite;

import ApiTests.ValuationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ApiTests.MakesTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        MakesTest.class,
        ValuationTest.class
})
public class AllApiTest {
}