package $01_core_oop;

public class OuterClass {
    private int outerField = 10;

    public class InnerClass {
        public void displayOuter() {
            System.out.println("Outer Field: " + outerField);
        }
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.displayOuter();
    }
}