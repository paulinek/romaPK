package framework;

import framework.interfaces.AcceptanceInterface;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Class to run several tests, and handle the results
 *
 * @author Matthew Moss (matthew.moss)
 * @author Lasath Fernando (lasath.fernando)
 * @author Benjamin James Wright (ben.wright)
 * @author Damon Stacey (damon.stacey)
 * @author Alexis Shaw (alexis.shaw)
 * @author Lauren Spooner
 * @author Shannon Green
 */
public class TestRunner {

    AcceptanceInterface testee;
    boolean colorful;

    public static final String COLOR_RESET       = "\033[0m";
    public static final String COLOR_BOLD        = "\033[1m";
    public static final String COLOR_UNDERLINE   = "\033[4m";
    public static final String COLOR_REVERSE     = "\033[7m";
    public static final String COLOR_BLACK       = "\033[1;30m";
    public static final String COLOR_RED         = "\033[1;31m";
    public static final String COLOR_GREEN       = "\033[1;32m";
    public static final String COLOR_YELLOW      = "\033[1;33m";
    public static final String COLOR_BLUE        = "\033[1;34m";
    public static final String COLOR_PURPLE      = "\033[1;35m";
    public static final String COLOR_CYAN        = "\033[1;36m";
    public static final String CLEAR_SCREEN      = "\033[2J\033[0;0H";
    private static final int scalingFactor = 20;
    int[] numTestsPassed;
    int[] numTestFailed;
    int[] numNotImplemented;
    int[] numInvalidTests;


    private static Test[] getUnverifiedTests() {
        return getTestsInPackage("tests.unverified");
    }

    private static Test[] getBorderlineTests() {
        return getTestsInPackage("tests.borderline");
    }

    private static Test[] getVerifiedTests() {
        return getTestsInPackage("tests.verified");
    }

    private static Test[] getTestsInPackage(String packageName){
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .filterInputsBy(new FilterBuilder.Include(FilterBuilder.prefix(packageName)))
                .setUrls(ClasspathHelper.forJavaClassPath())
                .setScanners(new SubTypesScanner()));
        Set<Class<? extends Test>> testClasses = reflections.getSubTypesOf(Test.class);

        Test[] returnValue = new Test[testClasses.size()];
        int NumClassesWithEmptyConstructor = 0;
        for(Class<? extends Test> testClass : testClasses){
             try{
                Constructor<? extends Test> testConstructor = testClass.getConstructor();
                returnValue[NumClassesWithEmptyConstructor] = testConstructor.newInstance();
                NumClassesWithEmptyConstructor++;
            } catch (Exception e){}
        }
        assert(NumClassesWithEmptyConstructor == testClasses.size());

        return returnValue;
    }
    private static AcceptanceInterface[] getAcceptanceInterfacesInPackage(String packageName){
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath())
                .setScanners(new SubTypesScanner()));
        Set<Class<? extends AcceptanceInterface>> acceptanceInterfaceClasses =
                    reflections.getSubTypesOf(AcceptanceInterface.class);
        for(Class<? extends AcceptanceInterface> c : acceptanceInterfaceClasses){
            System.out.println(c.getCanonicalName());
        }
        AcceptanceInterface[] returnValue = new AcceptanceInterface[acceptanceInterfaceClasses.size()];
        int noClassesWithEmptyConstructor = 0;
        for(Class<? extends AcceptanceInterface> acceptanceInterfaceClass:acceptanceInterfaceClasses){
            try{
                Constructor<? extends AcceptanceInterface> acceptanceInterfaceConstructor =
                        acceptanceInterfaceClass.getConstructor();
                returnValue[noClassesWithEmptyConstructor] = acceptanceInterfaceConstructor.newInstance();
                noClassesWithEmptyConstructor++;
            } catch (Exception e){
               e.printStackTrace();
            }
        }
        assert(noClassesWithEmptyConstructor == acceptanceInterfaceClasses.size());
        if (acceptanceInterfaceClasses.size() == 0) {
            System.out.println("We have found NO acceptance implementation");
        }
        return returnValue;
    }

    public TestRunner(String[] args) {
      List<String> arguments = Arrays.asList(args);
      if (arguments.size() > 0) {
         if (arguments.contains("-color")) {
            colorful = true;
         } else {
            colorful = false;
         }
      }
    }

    public static void main (String[] args) {
         TestRunner runner = new TestRunner(args);
         runner.testGame();
    }

    public void testGame() {
        if (colorful) {
           System.out.print(COLOR_BOLD);
           System.out.println(".-.                             .                .       .        .         . .         ");
           System.out.println("|-'.-..-.-..-.   .-. .-.-.-,.-.-|-.-. .-..-.-,  -|-.-,.--|-.-  .--|-.-. .-.-|-..-..-.   ");
           System.out.println("'`-`-'' ' '`-`-  `-`-`-`-`'-|-' '-`-`-' '`-`'-   '-`'--' '--'  -' '-`-`-'   '-'' '`-|ooo");
           System.out.println(COLOR_RESET);
        } else {
           System.out.println("Roma acceptance tests starting...");
        }
        boolean assertionsEnabled = false;
        try {
            assert (false);
        } catch (AssertionError e) {
            assertionsEnabled = true;
        }
        AcceptanceInterface[] acceptanceInterfaces = getAcceptanceInterfacesInPackage("");
        if (acceptanceInterfaces.length == 0) {
            if (colorful) {
               System.out.println(COLOR_RED);
            }
            System.out.println("No acceptance implementation found.");
            if (colorful) {
               System.out.println(COLOR_RESET);
            }
            return;
        }
        int interfaceTestNumber = 0;
        numTestsPassed = new int[acceptanceInterfaces.length];
        numTestFailed = new int[acceptanceInterfaces.length];
        numNotImplemented = new int[acceptanceInterfaces.length];
        numInvalidTests = new int[acceptanceInterfaces.length];

        if (!assertionsEnabled) {
            if (colorful) {
               System.out.println(COLOR_RED);
            }
            System.out.println("Please enable assertions, run with java -ea");
            if (colorful) {
               System.out.println(COLOR_RESET);
            }
            return;
        } else {
            for (AcceptanceInterface acceptanceInterface: acceptanceInterfaces) {
               if (colorful) {
                   System.out.print(COLOR_CYAN);
               }
               System.out.println("Now testing: " + acceptanceInterface.getClass()  + ".");
               if (colorful) {
                   System.out.print(COLOR_RESET);
               }
               runTests(getVerifiedTests(), acceptanceInterface, interfaceTestNumber);
               runTests(getBorderlineTests(), acceptanceInterface, interfaceTestNumber);
               runTests(getUnverifiedTests(), acceptanceInterface, interfaceTestNumber);
               interfaceTestNumber++;
            }
        }
        interfaceTestNumber = 0;
        for (AcceptanceInterface acceptanceInterface: acceptanceInterfaces) {
            if ((numNotImplemented[interfaceTestNumber] > 0)) {
                if (colorful) {
                    System.out.print(COLOR_RED);
                }
                System.out.println("Not fully implemented: " +  acceptanceInterface.getClass());
            } else if ((numTestFailed[interfaceTestNumber] > 0)) {
                if (colorful) {
                    System.out.print(COLOR_RED);
                }
                System.out.println("Needs work: " +  acceptanceInterface.getClass());
            } else {
                if (colorful) {
                    System.out.print(COLOR_GREEN);
                }
                System.out.println("Accepted: " +  acceptanceInterface.getClass() + "is awesome!!! You are Awesome! That's a pretty massive achievement right there! All Tests passed!");
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("Now go and write some more...");
            }
            double proportionPassed = (numTestsPassed[interfaceTestNumber]*1.00/(numNotImplemented[interfaceTestNumber]*1.00 + numTestFailed[interfaceTestNumber]*1.00 + numTestsPassed[interfaceTestNumber]*1.00) * scalingFactor);
            double proportionNotImplemented = (numNotImplemented[interfaceTestNumber]*1.00/(numNotImplemented[interfaceTestNumber]*1.00 + numTestFailed[interfaceTestNumber]*1.00 + numTestsPassed[interfaceTestNumber]*1.00) * scalingFactor);
            double proportionFailed = (numTestFailed[interfaceTestNumber]*1.00/(numNotImplemented[interfaceTestNumber]*1.00 + numTestFailed[interfaceTestNumber]*1.00 + numTestsPassed[interfaceTestNumber]*1.00) * scalingFactor);

            if (colorful) {
                System.out.print(COLOR_CYAN);
                System.out.print("\t[");
                for (int i = 0; i < proportionPassed; i++) {
                    System.out.print(COLOR_GREEN);
                    System.out.print("-");
                }
                for (int i = 0; i < proportionNotImplemented; i++) {
                    System.out.print(COLOR_YELLOW);
                    System.out.print("-");
                }
                for (int i = 0; i < proportionFailed; i++) {
                    System.out.print(COLOR_RED);
                    System.out.print("-");
                }
                System.out.print(COLOR_CYAN);
                System.out.println("]\t" + (int)(proportionPassed*100/scalingFactor) + "% correct. Note 100% is necessary for pass.");
                System.out.print(COLOR_RESET);
            } else {
                System.out.print("\t[");
                for (int i = 0; i < proportionPassed; i++) {
                    System.out.print("-");
                }
                for (int i = 0; i < proportionNotImplemented; i++) {
                    System.out.print("?");
                }
                for (int i = 0; i < proportionFailed; i++) {
                    System.out.print(" ");
                }
                System.out.println("]\t" + (int)(proportionPassed*100/scalingFactor) + "% correct. Note 100% is necessary for pass.");
            }

            interfaceTestNumber++;
        }
        System.exit(numTestFailed[0] + numNotImplemented[0]);
    }

    private void runTests(Test[] tests, AcceptanceInterface acceptanceInterface, int interfaceTestNumber) {
        if (acceptanceInterface == null){
            System.out.println("Cannot find your acceptanceInterface,");
            System.out.println("please place your jar containg you accpetanceInterface");
            System.out.println("implementation in the testee folder.");
        }
        for (Test current : tests) {
            try {
                if (colorful) {
                    System.out.print(COLOR_CYAN);
                }
                System.out.println("   " + current.getClass().toString().split("class tests.")[1] + ":");
                System.out.println("      " + current.getShortDescription());
                if (colorful) {
                    System.out.print(COLOR_RESET);
                }
                GameState state = acceptanceInterface.getInitialState();
                MoveMaker mover = acceptanceInterface.getMover(state);
                SanityChecker checkedMover = new SanityChecker(mover, state, current.out);
                current.run(state,mover);
                if (colorful) {
                    System.out.print(COLOR_GREEN);
                }
                numTestsPassed[interfaceTestNumber]++;
                System.out.println("      Test passed\n");
                if (colorful) {
                    System.out.print(COLOR_RESET);
                }

            } catch (UnsupportedOperationException ex) {
                if (colorful) {
                    System.out.print(COLOR_YELLOW);
                }
                numNotImplemented[interfaceTestNumber]++;
                System.out.println("      Feature not implemented yet. Skipping test...\n");
                if (colorful) {
                    System.out.print(COLOR_RESET);
                }
            } catch (IllegalArgumentException ex) {
                numInvalidTests[interfaceTestNumber]++;
                System.out.print(current.getOutputSteam());
                if (colorful) {
                    System.out.print(COLOR_YELLOW);
                }
                System.out.println("      Error in test. Please report this to your "
                        + "representative.\n");
                if (colorful) {
                    System.out.print(COLOR_RESET);
                }

            } catch (Exception ex) {
                numTestFailed[interfaceTestNumber]++;
                System.out.print(current.getOutputSteam());
                if (colorful) {
                    System.out.print(COLOR_RED + COLOR_UNDERLINE);
                }
                System.out.print("      Test Failed\n");
                Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace(System.out);
                if (colorful) {
                    System.out.print(COLOR_RESET);
                }

            } catch (AssertionError ex) {
                numTestFailed[interfaceTestNumber]++;
                System.out.print(current.getOutputSteam());
                if (colorful) {
                    System.out.print(COLOR_RED);
                }
                System.out.print("      Test Failed:\n");
                ex.printStackTrace(System.out);
                if (colorful) {
                    System.out.print(COLOR_RESET);
                }
            }
        }
    }

}
