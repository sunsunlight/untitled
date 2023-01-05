package a05interfacedemo5;
//因为不想让外界直接创建人的对象
//因为直接创建顶层父类人的对象此时是没有意义的
//所以把它写为抽象
public abstract class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
