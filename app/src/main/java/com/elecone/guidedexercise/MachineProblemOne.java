package com.elecone.guidedexercise;

import android.os.Bundle;
import android.widget.TextView;

public class MachineProblemOne extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_problem_one);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("8 Java Practice Problems");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Job Class
        Job developer1 = new SoftwareDeveloper(50000, 5, "Senior Software Developer", "TechCorp");
        Job developer2 = new SoftwareDeveloper(45000, 2, "Junior Software Developer", "StartUp Inc");
        Job developer3 = new SoftwareDeveloper(60000, 4, "Software Architect", "BigTech");

        // Displaying developer details and salary with increase
        String jobResult = "Developer 1:\n" + developer1.displaySummaryDetails() +
                "\n\nDeveloper 2:\n" + developer2.displaySummaryDetails() +
                "\n\nDeveloper 3:\n" + developer3.displaySummaryDetails();

        // Triangle
        Triangle triangle = new Triangle();
        String triangleResult = "Area of triangle: " + triangle.calculateArea() + "\nPerimeter: " + triangle.calculatePerimeter();

        // Circle
        Circle circle1 = new Circle();
        circle1.setRadius(5.0);
        String circleResult = "Circumference of circle: " + circle1.calculateCircumference();

        // Subclasses
        Parent p1 = new SubClass1();
        Parent p2 = new SubClass2();
        String subclassResult = "Subclass 1 message: " + p1.Message() + "\nSubclass 2 message: " + p2.Message();

        // Bank
        Bank bankA = new BankA();
        Bank bankB = new BankB();
        Bank bankC = new BankC();
        String bankResult = "Bank A Balance: $" + bankA.getBalance() + "\nBank B Balance: $" + bankB.getBalance() + "\nBank C Balance: $" + bankC.getBalance();

        // Marks
        A studentA = new A(99, 90, 98);
        B studentB = new B(98, 95, 97, 99);
        String marksResult = "Student A Percentage: " + studentA.getPercentage() + "\nStudent B Percentage: " + studentB.getPercentage();

        // Abstract Class Methods
        TheAbstractClass obj = new SubClass();
        String abstractResult = obj.a_method() + "\n" + obj.nonAbstractMethod();

        // Shape Area
        Shape shape = new Area();
        String shapeResult = "Rectangle Area: " + shape.RectangleArea(10, 4) + "\nSquare Area: " + shape.SquareArea(10) + "\nCircle Area: " + shape.CircleArea(5);

        // Display results in TextView
        TextView jobResultDetails = findViewById(R.id.jobResultDetails);
        jobResultDetails.setText(jobResult);

        TextView triangleResultDetails = findViewById(R.id.triangleResultDetails);
        triangleResultDetails.setText(triangleResult);

        TextView circleResultDetails = findViewById(R.id.circleResultDetails);
        circleResultDetails.setText(circleResult);

        TextView bankResultDetails = findViewById(R.id.bankResultDetails);
        bankResultDetails.setText(bankResult);

        TextView marksResultDetails = findViewById(R.id.marksResultDetails);
        marksResultDetails.setText(marksResult);

        TextView shapeResultDetails = findViewById(R.id.shapeResultDetails);
        shapeResultDetails.setText(shapeResult);

        // Display abstract results
        TextView abstractResultDetails = findViewById(R.id.abstractResultDetails);
        abstractResultDetails.setText(abstractResult);

        // Display subclass message results
        TextView subclassResultDetails = findViewById(R.id.subclassResultDetails); // Make sure this TextView exists in your XML
        subclassResultDetails.setText(subclassResult);
    }

    // Job Class
    class Job {
        double salary;
        int yearsOfExperience;
        String jobPosition;
        String companyName;

        public Job(double salary, int yearsOfExperience, String jobPosition, String companyName) {
            this.salary = salary;
            this.yearsOfExperience = yearsOfExperience;
            this.jobPosition = jobPosition;
            this.companyName = companyName;
        }

        public void getSalary() {
            System.out.println("Salary: $" + salary);
        }

        public String displaySummaryDetails() {
            return "Salary: $" + salary + "\nYears of Experience: " + yearsOfExperience +
                    "\nJob Position: " + jobPosition + "\nCompany Name: " + companyName;
        }
    }

    // Software Developer Class, Extending Job Class
    class SoftwareDeveloper extends Job {
        public SoftwareDeveloper(double salary, int yearsOfExperience, String jobPosition, String companyName) {
            super(salary, yearsOfExperience, jobPosition, companyName);
        }

        @Override
        public void getSalary() {
            double increasedSalary;
            if (yearsOfExperience > 3) {
                increasedSalary = salary * 1.3;
                System.out.println("Salary with 30% increase: $" + increasedSalary);
            } else {
                increasedSalary = salary * 1.05;
                System.out.println("Salary with 5% increase: $" + increasedSalary);
            }
        }
    }

    // Triangle Class
    class Triangle {
        double side1, side2, side3;

        public Triangle() { side1 = 3; side2 = 4; side3 = 5; }

        public double calculateArea() {
            double s = (side1 + side2 + side3) / 2;
            return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        }

        public double calculatePerimeter() { return side1 + side2 + side3; }
    }

    // Circle Class
    class Circle {
        private double radius = 1.0;
        private final double PI = Math.PI;

        public void setRadius(double radius) { this.radius = radius; }

        public double calculateCircumference() { return 2 * PI * radius; }
    }

    // Parent Abstract Class for Activity 3
    abstract class Parent { abstract String Message(); }

    class SubClass1 extends Parent {
        public String Message() { return "This is the first subclass"; }
    }

    class SubClass2 extends Parent {
        public String Message() { return "This is the second subclass"; }
    }

    // Bank Abstract Class
    abstract class Bank { abstract int getBalance(); }

    class BankA extends Bank {
        int balance = 100;
        public int getBalance() { return balance; }
    }

    class BankB extends Bank {
        int balance = 150;
        public int getBalance() { return balance; }
    }

    class BankC extends Bank {
        int balance = 200;
        public int getBalance() { return balance; }
    }

    // Marks Abstract Class
    abstract class Marks { abstract double getPercentage(); }

    class A extends Marks {
        private int sub1, sub2, sub3;
        A(int s1, int s2, int s3) { sub1 = s1; sub2 = s2; sub3 = s3; }
        public double getPercentage() { return (sub1 + sub2 + sub3) / 3.0; }
    }

    class B extends Marks {
        private int sub1, sub2, sub3, sub4;
        B(int s1, int s2, int s3, int s4) { sub1 = s1; sub2 = s2; sub3 = s3; sub4 = s4; }
        public double getPercentage() { return (sub1 + sub2 + sub3 + sub4) / 4.0; }
    }

    // TheAbstractClass
    abstract class TheAbstractClass {
        public TheAbstractClass() {
            System.out.println("Constructor of the abstract class");
        }
        abstract String a_method();
        public String nonAbstractMethod() {
            return "Normal method of the abstract class";
        }
    }

    class SubClass extends TheAbstractClass {
        public String a_method() {
            return "This is an abstract method";
        }
    }

    // Shape Abstract Class
    abstract class Shape {
        abstract int RectangleArea(int length, int breadth);
        abstract double SquareArea(int side);
        abstract double CircleArea(int radius);
    }

    class Area extends Shape {
        public int RectangleArea(int length, int breadth) { return length * breadth; }
        public double SquareArea(int side) { return Math.pow(side, 2); }
        public double CircleArea(int radius) { return Math.PI * Math.pow(radius, 2); }
    }
}
