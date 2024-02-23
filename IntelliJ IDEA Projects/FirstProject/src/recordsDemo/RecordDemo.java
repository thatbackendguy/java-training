package recordsDemo;

//import java.lang.*;

//class Person
//{
//    private int id;
//    private String name;
//    public Person(int id)
//    {
//        this.id = id;
//    }
//    public Person(String name)
//    {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return id == person.id && Objects.equals(name, person.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }
//}
record Person(int id, String name) {}

public class RecordDemo
{
    public static void main(String[] args)
    {
        Person p1 = new Person(1, "Yash");
        Person p2 = new Person(2, "Shivam");
        Person p3 = new Person(1, "Yash");

        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p1.equals(p3));
    }
}
