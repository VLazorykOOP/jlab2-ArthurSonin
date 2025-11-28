import java.time.Year;

public class Main {

    public static void main(String[] args) {
        System.out.println("Java Lab 2 ");
	// write your code here
         // 1
        System.out.println("\n--- Payment Test ---");
        Payment p = new Payment("Ivanenko", "Petro", 12000, 2015,
                                20, 18, 20, 30);
        p.printInfoP();

        // 2
        System.out.println("\n--- Circle Test ---");

        Circle c1 = new Circle(5, 0, 0);
        Circle c2 = new Circle(3, 4, 0);

        System.out.println("\nc1");
        c1.print(c1);
        System.out.println("\nc2");
        c2.print(c2);
        
         System.out.println("\n- c1 -");
        c1.printInfoC(c1, c2);
         System.out.println("\n- c2 -");
        c2.printInfoC(c2, c1);

        // 3
        System.out.println("\n--- Comp Test ---");


        if (args.length != 4) {
            System.out.println("Usage: java Complex <real1> <imag1> <real2> <imag2>");
            return;
        }

        double r1 = Double.parseDouble(args[0]);
        double i1 = Double.parseDouble(args[1]);
        double r2 = Double.parseDouble(args[2]);
        double i2 = Double.parseDouble(args[3]);

        Complex cl1 = new Complex(r1, i1);
        Complex cl2 = new Complex(r2, i2);

        System.out.print("c1 = "); cl1.print();
        System.out.print("c2 = "); cl2.print();

        System.out.print("c1 + c2 = "); cl1.add(cl2).print();
        System.out.print("c1 - c2 = "); cl1.subtract(cl2).print();
        System.out.print("c1 * c2 = "); cl1.multiply(cl2).print();
        System.out.print("c1 / c2 = "); cl1.divide(cl2).print();

        System.out.printf("Modulus of c1: %.2f%n", cl1.modulus());
        System.out.printf("Argument of c1 (radians): %.2f%n", cl2.argument());

    }
}

// Клас для розрахунку зарплати
class Payment {
    private final String lastName; // доступ можливий лише в класі Payment, значення не може бути змінено
    private final String firstName;
    private final double salary;
    private final int yearOfEmployment;
    private final double allowancePercent;
    private final double taxPercent;
    private final int workedDays;
    private final int daysInMonth;

    public Payment(String lastName, String firstName, double salary, int yearOfEmployment,
                   double allowancePercent, double taxPercent, int workedDays, int daysInMonth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.salary = salary;
        this.yearOfEmployment = yearOfEmployment;
        this.allowancePercent = allowancePercent;
        this.taxPercent = taxPercent;
        this.workedDays = workedDays;
        this.daysInMonth = daysInMonth;
    }

    public int WorkExperience() { // обчислює стаж роботи
        int currentYear = Year.now().getValue(); // джава клас рік, нинішній, витягує  
        return currentYear - yearOfEmployment;
    }

    public double AccruedAmount() { // обчислює нараховану суму
        double base = (salary / daysInMonth) * workedDays;
        return base + (base * allowancePercent / 100.0);
    }

    public double WithheldAmount() { // обчислює утриману суму
        return AccruedAmount() * taxPercent / 100.0;
    }

    public double calculateNetPayment() { // обчислює чисту виплату
        return AccruedAmount() - WithheldAmount();
    }



    public void printInfoP() {
        System.out.println("Employee: " + lastName + " " + firstName);
        System.out.println("Work experience: " + WorkExperience() + " years");
        System.out.println("Accrued amount: " + AccruedAmount());
        System.out.println("Withheld amount (taxes): " + WithheldAmount());
        System.out.println("Net payment: " + calculateNetPayment());
    }
}

// Клас для роботи з колом
class Circle {
    private final double radius;
    private final double centerX;
    private final double centerY;

    public Circle(double radius, double centerX, double centerY) {
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void print(Circle x) {
        System.out.println("Radius: " + x.radius);
        System.out.println("x: " + x.centerX);
        System.out.println("y: " + x.centerY);

    }

    public double Circumference() { // обчислює довжину
        return 2 * Math.PI * radius;
    }

    public double Area() { // обчислює площуё
        return Math.PI * radius * radius;
    }

    public boolean containsPoint(double x, double y) { // чи належить точка
        double dx = x - centerX;
        double dy = y - centerY;
        return dx * dx + dy * dy <= radius * radius; // Якщо ця нерівність виконується — точка лежить всередині або на межі кола.
    }

    public int IntersectionCount(Circle other) {
        double dx = other.centerX - this.centerX;
        double dy = other.centerY - this.centerY;
        double distance = Math.sqrt(dx * dx + dy * dy);
        double r1 = this.radius;
        double r2 = other.radius;

        if (distance > r1 + r2) { // не перетин
            return 0;
        } else if (distance == r1 + r2 || distance == Math.abs(r1 - r2)) { // дотикаються зовнішньо
            return 1;
        } else if (distance < Math.abs(r1 - r2)) { // Дотик внутрішньо
            return 0;
        } else { // перетин в двух точках
            return 2;
        }
    }

    public void printInfoC(Circle x, Circle y) {
        System.out.println("Circumference: " + x.Circumference());
        System.out.println("Area: " + x.Area());
        System.out.println("Contains point (2,2): " + x.containsPoint(2, 2));
        System.out.println("Intersection points with c2: " + x.IntersectionCount(y));

    }
}

class Complex {
    private final double real;
    private final double imag;

    // Конструктор
    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex add(Complex other) { // Додавання
        return new Complex(this.real + other.real, this.imag + other.imag);
    }

    public Complex subtract(Complex other) {  // Віднімання
        return new Complex(this.real - other.real, this.imag - other.imag);
    }

    public Complex multiply(Complex other) { // Множення
        double r = this.real * other.real - this.imag * other.imag;
        double i = this.real * other.imag + this.imag * other.real;
        return new Complex(r, i);
    }

    public Complex divide(Complex other) { // Ділення
        double d = other.real * other.real + other.imag * other.imag;
        double r = (this.real * other.real + this.imag * other.imag) / d;
        double i = (this.imag * other.real - this.real * other.imag) / d;
        return new Complex(r, i);
    }

    public double modulus() { // Модуль
        return Math.sqrt(real * real + imag * imag);
    }

    public double argument() { // Аргумент (в радіанах)
        return Math.atan2(imag, real);
    }

    public void print() {
        System.out.printf("%.2f + %.2fi%n", real, imag);
    }
}
