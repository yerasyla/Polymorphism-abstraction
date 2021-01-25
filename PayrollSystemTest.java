// Employee.java
// Employee abstract superclass.

public abstract class Employee {
	private final String firstName;
	private final String lastName;
	private final String socialSecurityNumber;
	// constructor
	public Employee(String firstName, String lastName,String socialSecurityNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
	}
	// return first name
	public String getFirstName() {return firstName;}
	// return last name
	public String getLastName() {return lastName;}
	// return social security number
	public String getSocialSecurityNumber() {return socialSecurityNumber;}
	// return String representation of Employee object
	@Override
	public String toString() {
		return String.format("%s %s%nsocial security number: %s",
				getFirstName(), getLastName(), getSocialSecurityNumber());
	}
	// abstract method must be overridden by concrete subclasses
	public abstract double earnings(); // no implementation here
}


// HourlyEmployee.java
// HourlyEmployee class extends Employee.

public class HourlyEmployee extends Employee {
	private double wage; // wage per hour
	private double hours; // hours worked for week
	// constructor
	public HourlyEmployee(String firstName, String lastName,
			String socialSecurityNumber, double wage, double hours) {
		super(firstName, lastName, socialSecurityNumber);
		if (wage < 0.0) { // validate wage
			throw new IllegalArgumentException("Hourly wage must be >= 0.0");
		}
		if ((hours < 0.0) || (hours > 168.0)) { // validate hours
throw new IllegalArgumentException("Hours worked must be >= 0.0 and <= 168.0");
		}
		this.wage = wage;
		this.hours = hours;
	}
	// set wage
	public void setWage(double wage) {
		if (wage < 0.0) { // validate wage
			throw new IllegalArgumentException("Hourly wage must be >= 0.0");
		}
		this.wage = wage;
	}
	// return wage
	public double getWage() {return wage;}
	// set hours worked
	public void setHours(double hours) {
		if ((hours < 0.0) || (hours > 168.0)) { // validate hours
throw new IllegalArgumentException("Hours worked must be >= 0.0 and <= 168.0");
		}
		this.hours = hours;
	}
	// return hours worked
	public double getHours() {return hours;}
	// calculate earnings; override abstract method earnings in Employee
	@Override
	public double earnings() {
		if (getHours() <= 40) { // no overtime
			return getWage() * getHours();
		}
		else {
			return 40 * getWage() + (getHours() - 40) * getWage() * 1.5;
		}
	}
	// return String representation of HourlyEmployee object
	@Override
	public String toString() {
		return String.format("hourly employee: %s%n%s: $%,.2f; %s: %,.2f",
				super.toString(), "hourly wage", getWage(),
				"hours worked", getHours());
	}
}


// SalariedEmployee.java
// SalariedEmployee concrete class extends abstract class Employee.

public class SalariedEmployee extends Employee {
	private double weeklySalary;
	// constructor
	public SalariedEmployee(String firstName, String lastName,
			String socialSecurityNumber, double weeklySalary) {
		super(firstName, lastName, socialSecurityNumber);
		if (weeklySalary < 0.0) {
throw new IllegalArgumentException("Weekly salary must be >= 0.0");
		}
		this.weeklySalary = weeklySalary;
	}
	// set salary
	public void setWeeklySalary(double weeklySalary) {
		if (weeklySalary < 0.0) {
			throw new IllegalArgumentException(
					"Weekly salary must be >= 0.0");
		}
		this.weeklySalary = weeklySalary;
	}
	// return salary
	public double getWeeklySalary() {return weeklySalary;}
	// calculate earnings; override abstract method earnings in Employee
	@Override
	public double earnings() {return getWeeklySalary();}
	// return String representation of SalariedEmployee object
	@Override
	public String toString() {
		return String.format("salaried employee: %s%n%s: $%,.2f",
				super.toString(), "weekly salary", getWeeklySalary());
	}
}


// CommissionEmployee.java
// CommissionEmployee class extends Employee.
public class CommissionEmployee extends Employee {
	private double grossSales; // gross weekly sales
	private double commissionRate; // commission percentage
	// constructor
	public CommissionEmployee(String firstName, String lastName,
			String socialSecurityNumber, double grossSales,
			double commissionRate) {
		super(firstName, lastName, socialSecurityNumber);
		if (commissionRate <= 0.0 || commissionRate >= 1.0) { // validate
throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
		}
		if (grossSales < 0.0) {
			throw new IllegalArgumentException("Gross sales must be >= 0.0");
		}
		this.grossSales = grossSales;
		this.commissionRate = commissionRate;
	}
	// set gross sales amount
	public void setGrossSales(double grossSales) {
		if (grossSales < 0.0) { // validate
			throw new IllegalArgumentException("Gross sales must be >= 0.0");
		}
		this.grossSales = grossSales;
	}
	// return gross sales amount
	public double getGrossSales() {return grossSales;}
	// set commission rate
	public void setCommissionRate(double commissionRate) {
		if (commissionRate <= 0.0 || commissionRate >= 1.0) { // validate
throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
		}
		this.commissionRate = commissionRate;
	}
	// return commission rate
	public double getCommissionRate() {return commissionRate;}
	// calculate earnings; override abstract method earnings in Employee
	@Override
	public double earnings() {
		return getCommissionRate() * getGrossSales();
	}
	// return String representation of CommissionEmployee object
	@Override
	public String toString() {
		return String.format("%s: %s%n%s: $%,.2f; %s: %.2f",
				"commission employee", super.toString(),
				"gross sales", getGrossSales(),
				"commission rate", getCommissionRate());
	}
}


// BasePlusCommissionEmployee.java
// BasePlusCommissionEmployee class extends CommissionEmployee.
public class BasePlusCommissionEmployee extends CommissionEmployee {
	private double baseSalary; // base salary per week
	// constructor
	public BasePlusCommissionEmployee(String firstName, String lastName,
			String socialSecurityNumber, double grossSales,
			double commissionRate, double baseSalary) {
		super(firstName, lastName, socialSecurityNumber,
				grossSales, commissionRate);
		if (baseSalary < 0.0) { // validate baseSalary
			throw new IllegalArgumentException("Base salary must be >= 0.0");
		}
		this.baseSalary = baseSalary;
	}
	// set base salary
	public void setBaseSalary(double baseSalary) {
		if (baseSalary < 0.0) { // validate baseSalary
			throw new IllegalArgumentException("Base salary must be >= 0.0");
		}
		this.baseSalary = baseSalary;
	}
	// return base salary
	public double getBaseSalary() {return baseSalary;}
	// calculate earnings; override method earnings in CommissionEmployee
	@Override
	public double earnings() {return getBaseSalary() + super.earnings();}
	// return String representation of BasePlusCommissionEmployee object
	@Override
	public String toString() {
		return String.format("%s %s; %s: $%,.2f",
				"base-salaried", super.toString(),
				"base salary", getBaseSalary());
	}
}

// PayrollSystemTest.java
// Employee hierarchy test program.
public class PayrollSystemTest {
	public static void main(String[] args) {
		// create subclass objects
		SalariedEmployee salariedEmployee =
new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
		HourlyEmployee hourlyEmployee =
new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
		CommissionEmployee commissionEmployee =
				new CommissionEmployee(
						"Sue", "Jones", "333-33-3333", 10000, .06);
		BasePlusCommissionEmployee basePlusCommissionEmployee =
				new BasePlusCommissionEmployee(
"Bob", "Lewis", "444-44-4444", 5000, .04, 300);
		System.out.println("Employees processed individually:");
		System.out.printf("%n%s%n%s: $%,.2f%n%n",
				salariedEmployee, "earned", salariedEmployee.earnings());
		System.out.printf("%s%n%s: $%,.2f%n%n",
				hourlyEmployee, "earned", hourlyEmployee.earnings());
		System.out.printf("%s%n%s: $%,.2f%n%n",
commissionEmployee, "earned", commissionEmployee.earnings());
		System.out.printf("%s%n%s: $%,.2f%n%n",
basePlusCommissionEmployee,"earned", basePlusCommissionEmployee.earnings());
		// create four-element Employee array
		Employee[] employees = new Employee[4];
		// initialize array with Employees
		employees[0] = salariedEmployee;
		employees[1] = hourlyEmployee;
		employees[2] = commissionEmployee;
		employees[3] = basePlusCommissionEmployee;
		System.out.printf("Employees processed polymorphically:%n%n");
		// generically process each element in array employees
		for (Employee currentEmployee : employees) {
			System.out.println(currentEmployee); // invokes toString
			// determine whether element is a BasePlusCommissionEmployee
			if (currentEmployee instanceof BasePlusCommissionEmployee) {
				// downcast Employee reference to
				// BasePlusCommissionEmployee reference
				BasePlusCommissionEmployee employee =
						(BasePlusCommissionEmployee) currentEmployee;
				employee.setBaseSalary(1.10 * employee.getBaseSalary());
				System.out.printf(
"new base salary with 10%% increase is: $%,.2f%n",
						employee.getBaseSalary());
			}
System.out.printf("earned $%,.2f%n%n", currentEmployee.earnings());
		}
		// get type name of each object in employees array
		for (int j = 0; j < employees.length; j++) {
			System.out.printf("Employee %d is a %s%n", j,
					employees[j].getClass().getName());
		}
	}
}
