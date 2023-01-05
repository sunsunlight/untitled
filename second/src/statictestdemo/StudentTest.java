package statictestdemo;

import java.util.ArrayList;
import java.util.List;

public class StudentTest {
    public static void main(String[] args) {
        //创建集合存放学生对象
        ArrayList<Student> list = new ArrayList<>();

        //创建学生对象
        Student st1 = new Student("zhangsan",20,"男");
        Student st2 = new Student("zhangsan",15,"男");
        Student st3 = new Student("zhangsan",25,"男");
        Student st4 = new Student("zhangsan",29,"男");

        //把学生对象添加到集合
        list.add(st1);
        list.add(st2);
        list.add(st3);
        list.add(st4);

        //调用学生工具类，找出最大年龄的学生
        int studentMaxAge = StudentUtil.maxAge(list);
        System.out.println(studentMaxAge);
    }
}
