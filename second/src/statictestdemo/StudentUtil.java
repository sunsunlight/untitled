package statictestdemo;

import java.util.ArrayList;
import java.util.List;

public class StudentUtil {
    private StudentUtil(){};

    public static int maxAge(ArrayList<Student> list){

        int Max = list.get(0).getAge();
        for (int i = 1; i < list.size(); i++) {
            int temp =list.get(i).getAge();
            if(temp > Max){
             Max = temp;
            }
        }
        return Max;
    }

}
