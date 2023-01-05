package statictestdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class UserTest {

    //定义些常量
    //todo '0' 并不是int
    private static final char ZERO = '0';
    private static final int TREE = 3;
    private static final char NINE = '9';
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
            switch (choose) {
                case "1":
                    //todo login就行
                    logIn(userList);
                    break;
                    //todo case2 方法返回值是list还行 但是也不建议  因为你的userList是全局变量  注册里面add了就行
                    //但是 case3 里面返回值为什么也要是list 如果仅仅是因为想更新userList数据的话 没有必要
                case "2":
                    register(userList);
                    break;
                case "3":
                    forgetPassword(userList);
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

    //todo  忘记了实现一个功能 3次机会
    private static void logIn(List<User> userList) {
        //登录用户名以及验证
        System.out.println("请输入要登录的用户名：");
        Scanner sc = new Scanner(System.in);
        //todo 用正确单词 loginName，loginPassword ，Verification
        String logInName, logInPassword, inputVerifacationCode;
        while (true) {
            logInName = sc.next();
            //todo boolean变量可以适当加一个is
            boolean logInNameExist = checkLogInName(userList, logInName);
            if (logInNameExist) {
                break;
            }
            //todo 如果直接登陆一个未注册的用户 checkLogInName方法里面有一个用户名未注册，请先注册 这里后面又有一个 重复
            System.out.println("用户名未注册，请先注册");
            //todo 未注册会直接退出系统  需求要求有三次尝试机会
            System.exit(0);
        }

        //密码输入以及验证密码,要找到该用户的对应密码
        //找到该用户的集合索引
        //todo 这里是想通过用户名找到索引，再根据索引找到密码 其实有两次循环
        //todo 验证密码  可以 一次循环
        //虽然是分步 不会有什么影响  但是这样好些 userList.get(i).getUserName().equals(logInName)这个为假根本不会去看密码
        /**
         * for (int i = 0; i < userList.size(); i++) {
         *             if (userList.get(i).getUserName().equals(logInName)&&userList.get(i).getPassword().equals(logInPassword)) {
         *                 return true;
         *             }
         *         }
         */
        int logInNameIndex = getLogInNameIndex(userList, logInName);

        System.out.println("请输入密码");
        while (true) {
            logInPassword = sc.next();
            boolean logInPasswordIsRight = checkLogInPassword(userList, logInPassword, logInNameIndex);
            if (!logInPasswordIsRight) {
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
            //todo buildVerification
            //todo 此处建议这样写  你这里是想构造一个对象  方法命名建议build命名
            // String verificationCode=buildVerificationCode()
            String buidVerification = getBuidVerification();
            System.out.println("验证码是" + buidVerification);
            inputVerifacationCode = sc.next();
            if (!buidVerification.equals(inputVerifacationCode)) {
                System.out.println("输入的验证码不正确，请重新输入");
                continue;
            }
            System.out.println("验证码正确");
            break;
        }
        System.out.println("登录成功");

    }

    private static String getBuidVerification() {
        //todo 这个方法能实现随机验证码  但是效率会有点问题 26个大小写字母集合 你这个如果多次运行的话 需要多次创建 弄成全局变量变量的话 整个系统运行期间只需要创建一次
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        StringBuilder specialSb = new StringBuilder();
        List<Character> localList = new ArrayList<>();
        //将‘a'到'z’和'A'到‘Z'放到数组中
        char[] ch1 = new char[26];
        char[] ch2 = new char[26];
        char[] ch3 = new char[5];
        ch1[0] = 'a';
        ch2[0] = 'A';
        for (int i = 0; i < ch1.length - 1; i++) {
            ch1[i + 1] = (char) (ch1[i] + 1);
            ch2[i + 1] = (char) (ch2[i] + 1);

        }
        for (int i = 0; i < 26; i++) {
            //todo 此处不需要强转
            localList.add((Character) (ch1[i]));
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
            if (userList.get(i).getUserName().equals(logInName)) {
                count = i;
            }
        }
        return count;
    }

    private static boolean checkLogInPassword(List<User> userList, String logInPassword, int logInNameIndex) {
        return userList.get(logInNameIndex).getPassword().equals(logInPassword);


    }

    private static boolean checkLogInName(List<User> userList, String logInName) {

        //todo  可以养成个习惯  对入参进行下校验 因为userList可能从数据库中读取得到  可能为空 后面userList.size会报异常 系统崩溃
        for (int i = 0; i < userList.size(); i++) {
            //todo 这里提个醒 string1.equal(string2) 我们一般string1用明确存在的 为了防止出现 null.equals(string)情况
            if (userList.get(i).getUserName().equals(logInName)) {
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
        //todo inputPassword
        String registerName, inputPasssword, idCardNum, phoneNum;
        Scanner sc = new Scanner(System.in);


        System.out.println("请输入要注册的用户名：");

        while (true) {

            registerName = sc.next();

            //②用户名长度必须3-15位之间
            boolean registerNameLength = checkRegisterNamelength(registerName);
            if (!registerNameLength) {
                System.out.println("用户名长度不符合要求，请重新输入");
                continue;
            }

            //走到这里说明用户名长度符合要求
            //③只能是字母和数字的组合，但是不能是纯数字
            boolean registerNameArr = checkRegisterNameArr(registerName);
            if (!registerNameArr) {
                System.out.println("注册是字母和数字组合，不能是纯数字，请重新输入：");
                continue;
            }

            //走到这里说明用户名长度和组合符合要求
            //①验证要求 用户名唯一（唯一性一般放到最后验证）
            boolean registerNameUnique = checkRegisterNameUnique(userList, registerName);
            if (!registerNameUnique) {
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
            boolean flag1 = checkInputPassword(inputPasssword);
            if (!flag1) {
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
            if (flag2) {
                System.out.println("身份证号码输入成功");
                break;
            }
            //todo  方法里面已经写了错误
            System.out.println("身份证号码有误");
            continue;
        }

        //4手机号验证
        //    要求：① 长度11位；②不能0开头；③必须全部数字。
        System.out.println("请输入手机号");
        while (true) {
            phoneNum = sc.next();
            //todo  前面flag1 flag2已经死亡了
            boolean flag3 = checkPhoneNum(phoneNum);
            if (flag3) {
                System.out.println("手机号输入成功");
                break;
            }
            System.out.println("手机号输入失败，请重新输入");
            continue;

        }

        //走到这里说明，以上全部输入成功，把值传回集合即可
        User user = new User(registerName, inputPasssword, idCardNum, phoneNum);

        userList.add(user);
        System.out.println("注册成功");
        return userList;


    }

    private static boolean checkPhoneNum(String phoneNum) {
        if (phoneNum.length() != ELEVEN) {
            System.out.println("手机号码不是11位，请重新输入手机号");
            return false;
        }
        char[] ch = phoneNum.toCharArray();
        if (ch[0] == 0) {
            System.out.println("手机号首位不能是0，请重新输入手机号");
            return false;
        }
        char e;
        for (int i = 0; i < phoneNum.length(); i++) {
            e = ch[i];
            if (!(e >= ZERO && e <= NINE)) {
                System.out.println("手机号不是纯数字，请重新输入");
                return false;
            }
        }
        return true;

    }

    private static boolean checkInputIdCardNum(String idCardNum) {
        if (idCardNum.length() != EIGHTEEN) {
            System.out.println("身份证长度不是18位，请重新输入");
            return false;
        }
        //前17位必须是数字，最后一位可以为数字,也可以大写X或小写x
        char[] ch = idCardNum.toCharArray();
        //todo c d都没初始化  直接一行就行
        char c;
        char d;
        for (int i = 0; i < idCardNum.length() - 1; i++) {
            c = ch[i];
            if (!(c >= '0' && c <= '9')) {
                System.out.println("身份证前17位必须是数字，请重新输入");
                return false;
            }
        }
        d = ch[idCardNum.length() - 1];
        if ((d >= '0' && d <= '9') || d == 'X' || d == 'x') {
            return true;
        }
        System.out.println("身份证最后一位输入错误，请重新输入身份证号码");
        return false;

    }

    private static boolean checkInputPassword(String inputPasssword) {
        System.out.println("请再次输入密码，以确认");
        Scanner sc = new Scanner(System.in);
        String inputPasswordSecond = sc.next();
        if (inputPasswordSecond.equals(inputPasssword)) {
            return true;
        }
        return false;

    }

    private static boolean checkRegisterNameUnique(List<User> userList, String registerName) {
        //如果集合没有对象，则是唯一
        if (userList == null && userList.size() == 0) {
            return true;
        }
        //集合不是空
        String registeredUserName;
        for (int i = 0; i < userList.size(); i++) {
            registeredUserName = userList.get(i).getUserName();
            if (registeredUserName.equals(registerName)) {
                return false;
            }
        }
        //走到这里说明，检查完了，集合中没有相同的名字
        return true;

    }

    private static boolean checkRegisterNameArr(String registerName) {
        //todo 如果纯字母 也能注册成功
        //也就是注册名含有字母就行

        char[] ch = registerName.toCharArray();

        for (int i = 0; i < registerName.length(); i++) {
            //todo 命名不用动词开头  直接ch就行  上面用chs 集合用复数
            char checkChar = ch[i];
            if (checkChar >= 'a' || checkChar <= 'z' || checkChar >= 'A' || checkChar <= 'Z') {
                return true;
            }
        }
        return false;

    }

    private static boolean checkRegisterNamelength(String registerName) {
        // todo 驼峰式命名 registerNameLength
        int registerNamelength = registerName.length();
        if (registerNamelength >= TREE && registerNamelength <= FIFTEEN) {
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
        //todo exsit
        boolean nameIsExisist = checkLogInName(userList, logInName);
        if (!nameIsExisist) {
            System.out.println("该用户名未注册，请先注册");
            return userList;
        }
        //2键盘录入身份证号和手机号
        //3判断当前用户的身份证号和手机号是否一致，如不一致，则提示输入密码，不一致，提示账号信息不匹配，修改失败
        //找到该对象索引
        int index = getLogInNameIndex(userList, logInName);
        System.out.println("请输入身份证号码");
        String idCard = sc.next();
        if (!(userList.get(index).getIdCardNum().equals(idCard))) {
            System.out.println("身份证号码有误，修改失败");
            return userList;
        }
        System.out.println("请输入手机号码");
        String phoneNum = sc.next();
        if (!(userList.get(index).getPhoneNum().equals(phoneNum))) {
            System.out.println("手机号码有误，修改失败");
            return userList;
        }
        //todo password
        String inputPasssword;
        while (true) {
            System.out.println("请输入要更改的密码");
            inputPasssword = sc.next();
            boolean modifyPassword = checkInputPassword(inputPasssword);
            if (!modifyPassword) {
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
