package newdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class UserTest {

    //定义些常量
    private static final int ZERO = '0';
    private static final int TREE = 3;
    private static final int NINE = '9';
    private static final int ELEVEN = 11;
    private static final int FIFTEEN = 15;
    private static final int EIGHTEEN = 18;

    public static void main(String[] args) {
    //     首界面
    //             ************欢迎来到学生管理系统*************
    //     请选择  1登录   2注册    3忘记密码
        //键盘输入选择
        Scanner sc = new Scanner(System.in);
        //创建集合存放学生信息
        List<User> userList = new ArrayList<>();

        //循环进入首页
        while (true) {
            //打印首页菜单
            System.out.println(" ************欢迎来到学生管理系统*************");
            System.out.println("请选择  1登录   2注册    3忘记密码  任意键退出");

            //键盘输入选择并即进入对象选项
            String choose = sc.next();
            switch (choose){
                case "1" :
                    logIn( userList);
                    break;
                case "2" :
                    register(userList);
                    break;
                case "3" :
                    forgetPassword( userList);
                    break;
                default:
                    System.out.println("退出学生系统");
                    System.exit(0);
            }
        }


    }
//实现三个功能
    //一、登录
    // 1键盘录入用户名
    //2键盘录入密码
    //3键盘录入验证码
    // 验证要求：用户名如果未注册，直接结束方法，并提示（用户名未注册，请先注册）；
    //判断验证码是否正确，不正确要重新输入，，再判断用户名和密码是否正确，有3次机会
    //    验证码规则
    //1长度5位
    //2由四位大写和小写字母和1位数字组成，同一个字母可重复，数字可出现在任意位置
    private static void logIn(List<User> userList) {
        //登录用户名以及验证
        System.out.println("请输入要登录的用户名：");
        Scanner sc = new Scanner(System.in);
        String logInName,logInPassword,inputVerifacationCode;
        while (true) {
            logInName = sc.next();
            boolean logInNameExist = checkLogInName(userList,logInName);
            if(logInNameExist){
                break;
            }
            System.out.println("用户名未注册，请先注册");
            System.exit(0);
        }

        //密码输入以及验证密码,要找到该用户的对应密码
        //找到该用户的集合索引
        int logInNameIndex = getLogInNameIndex(userList,logInName);

        System.out.println("请输入密码");
        while (true) {
            logInPassword = sc.next();
            boolean logInPasswordIsRight = checkLogInPassword(userList,logInPassword,logInNameIndex);
            if(!logInPasswordIsRight){
                System.out.println("密码不正确，请重新输入");
                continue;
            }
            break;
        }
        //判断验证码是否正确，不正确要重新输入，，再判断用户名和密码是否正确，有3次机会
        //    验证码规则
        //1长度5位
        //2由四位大写和小写字母和1位数字组成，同一个字母可重复，数字可出现在任意位置

        //先产生验证码
        System.out.println("请输入验证码");
        while (true) {
            String buidVerification = getBuidVerification();
            System.out.println("验证码是"+buidVerification );
            inputVerifacationCode = sc.next();
            if(!buidVerification.equals(inputVerifacationCode)){
                System.out.println("输入的验证码不正确，请重新输入");
                continue;
            }
            System.out.println("验证码正确");
            break;
        }
        System.out.println("登录成功");

    }

    private static String getBuidVerification() {
        Random random= new Random();
        StringBuilder sb = new StringBuilder();
        StringBuilder specialSb = new StringBuilder();
        List<Character> localList = new ArrayList<>();
        //将‘a'到'z’和'A'到‘Z'放到数组中
        char[] ch1 = new char[26];
        char[] ch2 = new char[26];
        char[] ch3 = new char[5];
        ch1[0] = 'a';
        ch2[0] = 'A';
        for (int i = 0; i < ch1.length-1; i++) {
            ch1[i+1] = (char)(ch1[i] +1);
            ch2[i+1] = (char)(ch2[i] +1);

        }
        for (int i = 0; i < 26; i++) {
            localList.add((Character)(ch1[i]) );
            localList.add((Character) (ch2[i]));
        }
        int randomInt;
        for (int i = 0; i < 4; i++) {
            randomInt = random.nextInt(52);
            ch3[i] = localList.get(randomInt).charValue();
        }
         specialSb.append(random.nextInt(10));

        ch3[4] = specialSb.charAt(0);
        char temp;
        for (int i = 0; i < 5; i++) {
            temp = ch3[i];
            int t = random.nextInt(5);
            ch3[i] = ch3[t];
            ch3[t] = temp;
        }
        for (int i = 0; i < 5; i++) {
            sb.append(ch3[i]);
        }
        return sb.toString();

    }

    private static int getLogInNameIndex(List<User> userList, String logInName) {
    int count = -1;
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUserName().equals(logInName)){
                count = i;
            }
        }
        return count;
    }

    private static boolean checkLogInPassword(List<User> userList,String logInPassword,int logInNameIndex) {
            return userList.get(logInNameIndex).getPassword().equals(logInPassword);


    }

    private static boolean checkLogInName(List<User> userList,String logInName) {

        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUserName().equals(logInName)){
                return true;
            }

        }
        System.out.println("用户名未注册，请先注册");
        return false;

    }

    //二、注册
    //    1 用户名需要满足一下要求：
    //①验证要求 用户名唯一（唯一性一般放到最后验证）
    //②用户名长度必须3-15位之间
    //③只能是字母和数字的组合，但是不能是纯数字
    //2键盘输入两次密码，两次一致才可以进行注册
    //3身份证号需要验证
    //    要求：①长度18位；②不能0开头；③前17必须为数字；④最后一位可以为数字，也可以是大写X或小写x
    //4手机号验证
    //    要求：① 长度11位；②不能0开头；③必须全部数字。
    //数据返回到集合
    private static List<User> register(List<User> userList) {
        String registerName,inputPasssword,idCardNum,phoneNum;
        Scanner sc = new Scanner(System.in);


        System.out.println("请输入要注册的用户名：");

        while (true) {

            registerName = sc.next();

            //②用户名长度必须3-15位之间
            boolean registerNameLength = checkRegisterNamelength(registerName);
            if(!registerNameLength){
                System.out.println("用户名长度不符合要求，请重新输入");
                continue;
            }

            //走到这里说明用户名长度符合要求
            //③只能是字母和数字的组合，但是不能是纯数字
            boolean registerNameArr = checkRegisterNameArr(registerName);
            if(!registerNameArr){
                System.out.println("注册是字母和数字组合，不能是纯数字，请重新输入：");
                continue;
            }

            //走到这里说明用户名长度和组合符合要求
            //①验证要求 用户名唯一（唯一性一般放到最后验证）
            boolean registerNameUnique = checkRegisterNameUnique(userList,registerName);
            if(!registerNameUnique){
                System.out.println("注册名字已存在，请重新输入：");
                continue;
            }
            //走到这里是，姓名完全符合要求,跳出循环
            break;
        }
        //2键盘输入两次密码，两次一致才可以进行注册

        while (true) {
            System.out.println("请输入密码");
            inputPasssword = sc.next();
            boolean  flag1= checkInputPassword(inputPasssword);
            if(!flag1){
                System.out.println("两处输入的密码不一致，请重新输入密码");
                continue;
            }
            //到这里说明密码符合要求,退出密码输入循环
            break;
        }

        //3身份证号需要验证
        //    要求：①长度18位；②不能0开头；③前17必须为数字；④最后一位可以为数字，也可以是大写X或小写x
        System.out.println("请输入身份证号码");
        while (true) {
            idCardNum = sc.next();
            boolean flag2 = checkInputIdCardNum(idCardNum);
            if(flag2){
                System.out.println("身份证号码输入成功");
                break;
            }
            System.out.println("身份证号码有误");
            continue;
        }

        //4手机号验证
        //    要求：① 长度11位；②不能0开头；③必须全部数字。
        System.out.println("请输入手机号");
        while (true) {
            phoneNum  = sc.next();
            boolean flag3 = checkPhoneNum(phoneNum);
            if(flag3){
                System.out.println("手机号输入成功");
                break;
            }
            System.out.println("手机号输入失败，请重新输入");
            continue;

        }

        //走到这里说明，以上全部输入成功，把值传回集合即可
        User user = new User(registerName,inputPasssword,idCardNum,phoneNum);

        userList.add(user);
        System.out.println("注册成功");
        return userList;


    }

    private static boolean checkPhoneNum(String phoneNum) {
        if(phoneNum.length() != ELEVEN){
            System.out.println("手机号码不是11位，请重新输入手机号");
            return false;
        }
        char[] ch = phoneNum.toCharArray();
        if(ch[0] == 0){
            System.out.println("手机号首位不能是0，请重新输入手机号");
            return false;
        }
        char e;
        for (int i = 0; i < phoneNum.length(); i++) {
            e =ch[i];
            if(!(e >= ZERO && e<= NINE)){
                System.out.println("手机号不是纯数字，请重新输入");
                return false;
            }
        }
        return true;

    }

    private static boolean checkInputIdCardNum(String idCardNum) {
        if(idCardNum.length() !=EIGHTEEN){
            System.out.println("身份证长度不是18位，请重新输入");
            return false;
        }
        //前17位必须是数字，最后一位可以为数字,也可以大写X或小写x
        char[] ch = idCardNum.toCharArray();
        char c;
        char d;
        for (int i = 0; i < idCardNum.length()-1; i++) {
            c = ch[i];
            if(!(c >='0' && c <='9')){
                System.out.println("身份证前17位必须是数字，请重新输入");
                return false;
            }
        }
        d = ch[idCardNum.length()-1];
        if((d >='0' && d<= '9') || d == 'X' || d == 'x'){
            return true;
        }
        System.out.println("身份证最后一位输入错误，请重新输入身份证号码");
        return false;

    }

    private static boolean checkInputPassword(String inputPasssword) {
        System.out.println("请再次输入密码，以确认");
        Scanner sc = new Scanner(System.in);
        String inputPasswordSecond = sc.next();
        if(inputPasswordSecond.equals(inputPasssword)){
            return true;
        }
        return false;

    }

    private static boolean checkRegisterNameUnique(List<User> userList,String registerName) {
        //如果集合没有对象，则是唯一
        if(userList == null && userList.size() ==0){
            return true;
        }
        //集合不是空
        String registeredUserName;
        for (int i = 0; i < userList.size(); i++) {
             registeredUserName = userList.get(i).getUserName();
             if(registeredUserName.equals(registerName)){
                 return false;
             }
        }
        //走到这里说明，检查完了，集合中没有相同的名字
        return true;

    }

    private static boolean checkRegisterNameArr(String registerName) {
        //也就是注册名含有字母就行

        char[] ch = registerName.toCharArray();

        for (int i = 0; i < registerName.length(); i++) {
            char checkChar = ch[i];
            if(checkChar >= 'a' || checkChar <= 'z' || checkChar>='A' || checkChar <= 'Z'){
                return true;
            }
        }
        return false;

    }

    private static boolean checkRegisterNamelength(String registerName) {
        int registerNamelength = registerName.length();
        if(registerNamelength >= TREE && registerNamelength <= FIFTEEN){
            return true;
        }
        return false;
    }

    //三、忘记密码
    // 忘记密码
    //1键盘录入用户名，判断当前用户名是否存在，如不存在，直接结束当前方法，并提示：未注册
    //2键盘录入身份证号和手机号
    //3判断当前用户的身份证号和手机号是否一致，如不一致，则提示输入密码，不一致，提示账号信息不匹配，修改失败

    private static List forgetPassword(List<User> userList) {

        //1键盘录入用户名，判断当前用户名是否存在，如不存在，直接结束当前方法，并提示：未注册
        Scanner sc = new Scanner(System.in);
        String logInName;
        System.out.println("请输入用户名");
        logInName = sc.next();
        boolean nameIsExisist = checkLogInName(userList,logInName);
        if(!nameIsExisist){
            System.out.println("该用户名未注册，请先注册");
            return userList;
        }
        //2键盘录入身份证号和手机号
        //3判断当前用户的身份证号和手机号是否一致，如不一致，则提示输入密码，不一致，提示账号信息不匹配，修改失败
        //找到该对象索引
        int index = getLogInNameIndex( userList,logInName);
        System.out.println("请输入身份证号码");
        String idCard = sc.next();
        if(!(userList.get(index).getIdCardNum().equals(idCard))){
            System.out.println("身份证号码有误，修改失败");
            return userList;
        }
        System.out.println("请输入手机号码");
        String phoneNum = sc.next();
        if(!(userList.get(index).getPhoneNum().equals(phoneNum) )){
            System.out.println("手机号码有误，修改失败");
            return userList;
        }
        String inputPasssword;
        while (true) {
            System.out.println("请输入要更改的密码");
            inputPasssword = sc.next();
            boolean modifyPassword = checkInputPassword(inputPasssword);
            if(!modifyPassword){
                System.out.println("修改失败");
                continue;
            }
            break;
        }

        userList.get(index).setPassword(inputPasssword);
        System.out.println("恭喜你，密码修改成功");
        return userList;

    }

}
