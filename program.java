import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;
import java.time.LocalDate;

class Main {
  public static void main(String[] args) {
    Scanner keyboardInput = new Scanner(System.in);

    System.out.print("Monthly Spending in Retirement: ");
    String spendingStr = keyboardInput.nextLine();
    double spending = Double.parseDouble(spendingStr);

    System.out.print("Initial Investment: ");
    String investmentStr = keyboardInput.nextLine();
    double investment = Double.parseDouble(investmentStr);

    System.out.print("Annual Contribution: ");
    String annualContributionStr = keyboardInput.nextLine();
    double annualContribution = Double.parseDouble(annualContributionStr);

    System.out.print("Return Rate (%): ");
    String returnRateStr = keyboardInput.nextLine();
    double returnRate = Double.parseDouble(returnRateStr);
    returnRate /= 100;
    double retirementGoal = (spending / returnRate) * 12;
    returnRate += 1;

    System.out.print("Current Age: ");
    String ageString = keyboardInput.nextLine();
    int age = Integer.parseInt(ageString);

    keyboardInput.close();

    System.out.println("----------------------------------------");

    LocalDate currentdate = LocalDate.now();
    int year = currentdate.getYear();
    int count = 1;
    String xtraDigit = "0";
    String retirementMarker = "";
    boolean retireYet = false;
    int endAge = 65;

    if (age > (endAge - 10)) { // Always show at LEAST 10 years after retirement
      endAge = age + 10;
    }

    while (age <= endAge) {
      if (count >= 10) {
        xtraDigit = "";
      }
      if (investment >= retirementGoal && retireYet == false) {
        retirementMarker = " <<< This is when you can retire!";
        retireYet = true;
        if (age > (endAge - 10)) { // Always show at LEAST 10 years after retirement
          endAge = age + 10;
        }
      }
      NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
      String label = xtraDigit + count + ". Age " + age + "(" + year + ")" + ": ";
      System.out.println(label + format.format(investment) + retirementMarker);
      investment *= returnRate;
      investment += annualContribution;
      year++;
      age++;
      count++;
      retirementMarker = "";
    }
  }
}