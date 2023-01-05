package shihua.demo;

//import com.yilaguan.enity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author yilaguan
 * @date 2022/12/23
 */
public class UserService {
    //创建一个集合存储26个字母
    private static List<Character> alphabetList=buildAlphabetList();
    //常量配置
    private static final String ZERO="0";
    private static final int ONE=1;
    private static final int TWO=2;
    private static final int THREE=3;
    private static final int ELEVEN=11;
    private static final int EIGHTEEN=18;


    //主界面
    public static void main(String[] args) {
        //使用lis集合来存储数据库用户 全局变量
        List<User> userList=new ArrayList<>();
        //用户输入选择器
        Scanner scanner=new Scanner(System.in);
        //力哥 用的循环 所以加一个退出循环
        while (true){
            System.out.println("**********欢迎来到学生管理系统***************");
            System.out.println("请选择  1登录   2注册    3忘记密码");
            int input=scanner.nextInt();
            //如果输入的是其他数字 退出循环
            switch (input){
                case ONE:
                    userLogin(userList);
                    break;
                case TWO:
                    userRegister(userList);
                    break;
                case THREE:
                    forgetPassword(userList);
                    break;
                default:
                    System.out.println("**********退出学生管理系统***************");
                    System.exit(0);
            }
        }
    }

    //功能界面 一共实现三个功能

    //1、登陆

    /**
     * 1键盘录入用户名
     * 2键盘录入密码
     * 3键盘录入验证码
     * @param userList
     */
    private static void userLogin(List<User> userList) {
        System.out.println("**********欢迎来到学生管理系统登陆页面***************");
        //定义 尝试次数 3次
        int attempts=3;
        for (int i=0;i<attempts;i++){
            //输入用户名
            Scanner scanner=new Scanner(System.in);
            System.out.println("请输入用户名");
            String userName=scanner.next();
            //用户名如果未注册，直接结束方法，并提示（用户名未注册，请先注册）；
            boolean isUserRegistered=userNameCheck(userName,userList);
            if (!isUserRegistered){
                System.out.println("用户名未注册，请先注册");
                return;
            }
            //此时其实用户名已经正确，确定用户列表已经存在此用户，所以之后验证只需要验证密码是否和用户列表一致就行
            //输入密码
            System.out.println("请输入密码");
            String password=scanner.next();

            //输入验证码
            //todo：验证规则未知
            //校验规则 长度5位  由4位大小写字母和1位数字组成 同一个字母可重复 数字可以出现在任意位置
            //判断验证码是否正确，不正确要重新输入
            while (true){
                //生成一个验证码  验证码是随机生成的  我们可以打印出来 要不成功率太低  web端可以直接显示
                String randomVerificationCode=buildVerificationCode();
                System.out.println("随机验证码:"+randomVerificationCode);
                System.out.println("请输入验证码");
                String userInputCode=scanner.next();
                if (randomVerificationCode.equals(userInputCode)){
                    System.out.println("验证码正确");
                    break;
                }else {
                    System.out.println("不正确,重新输入");
                    continue;
                }
            }

            //验证成功后，注意：前面其实已经验证过用户名了 校验密码是否正确
            boolean checkUser=checkUserInfo(password,userList);
            //如果校验成功。登陆成功，退出系统。如果不成功。继续尝试，直到第三次还未成功
            if(checkUser){
                System.out.println("**********登陆学生管理系统成功***************");
                return;
            }else {
                System.out.println("**********密码错误，请重新登陆***************");
            }
        }
    }

    /**
     * 随机生成一个验证码
     * 验证码要求：长度5位  由4位大小写字母和1位数字组成 同一个字母可重复 数字可以出现在任意位置
     * @return
     */
    private static String buildVerificationCode() {
        StringBuilder verificationCode =new StringBuilder();
        //用随机数
        Random random=new Random();
        //先构建一个长度为4的字母组合
        for (int i=0;i<4;i++){
            //获取随机索引 从字母集合中获取字母
            int index=random.nextInt(alphabetList.size());
            char ch=alphabetList.get(index);
            //拼接到验证码字符串上面
            verificationCode.append(ch);
        }
        //将数字拼接到验证码字符串上面
        int num=random.nextInt(10);
        verificationCode.append(num);
        //字符串拼接完了，接下来打乱字符串的位置
        String sourceString=verificationCode.toString();
        //创建数组 存储最后筛选下来的字母
        char[] chars=sourceString.toCharArray();
        //随机组合
        for (int i=0;i<chars.length;i++){
            //随机数随机取
            int randomIndex=random.nextInt(chars.length);
            char ch=chars[i];
            chars[i]=chars[randomIndex];
            chars[randomIndex]=ch;
        }
        //字符串组合合成字符串
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<chars.length;i++){
            sb=sb.append(chars[i]);
        }
        return sb.toString();
    }

    /**
     * 通过 password是否和用户列表匹配
     * @param passwordInput
     * @param userList
     * @return
     */
    private static boolean checkUserInfo(String passwordInput, List<User> userList) {
        for (int i=0;i<userList.size();i++){
            String password=userList.get(i).getPassword();
            if (passwordInput.equals(password)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断用户名是否已经注册,如果在userList中下标存在即是已经注册过的用户
     * @param userName
     * @param userList
     * @return
     */
    private static boolean userNameCheck(String userName, List<User> userList) {
        //如果userList为空，即不存在用户已经注册
        if (userList==null && userList.isEmpty()){
            return false;
        }
        //如果userList不为空，则去循环看是否存在userList中
        for (int i=0;i<userList.size();i++){
            if (userList.get(i).getUserName().equals(userName)){
                return true;
            }
        }
        //循环整个list都不存在用户名一样。则之前从未注册过
        return false;
    }

    //2、注册
    private static void userRegister(List<User> userList) {
        System.out.println("**********欢迎来到学生管理系统注册页面***************");
        Scanner scanner=new Scanner(System.in);
        //完整的用户信息包括用户名、密码、身份证、电话号码
        String userName,password,idCard,phoneNum;
        //用户名
        //1 用户名需要满足一下要求：
        //1.1验证要求 用户名唯一
        //1.2用户名长度必须3-15位之间
        //1.3只能是字母和数字的组合，但是不能是纯数字
        while (true){
            System.out.println("请输入用户名");
            userName=scanner.next();
            //对用户名进行校验
            boolean isUniqueUserName=checkUserNameUnique(userName,userList);
            if (!isUniqueUserName){
                //用户名如果存在，可以重新输入，所以还是用while循环模式
                System.out.println("用户名："+userName+"已经存在，请重新输入");
                continue;
            }
            //用户名唯一.验证其他条件
            //用户名长度必须3-15位之间
            boolean userNameLength=checkUserLength(userName);
            if (userNameLength){
                //用户名长度不符合要求，重新输入
                System.out.println("用户名长度不符合要求，重新输入");
                continue;
            }
            //只能是字母和数字的组合，但是不能是纯数字
            boolean isLettersAndNumbers=checkUserNameInfo(userName);
            if (!isLettersAndNumbers){
                System.out.println("用户名应同时包含字母和数字,重新输入");
                continue;
            }
            //都满足，则用户名满足需求，直接跳出while循环
            break;
        }

        //2键盘输入两次密码，两次一致才可以进行注册
        //其实正常可以在一个while循环里面解决掉  为了分功能 我直接每一个功能用一个循环
        while (true){
            System.out.println("请输入密码");
            String password1=scanner.next();
            System.out.println("请再次输入密码");
            String password2=scanner.next();
            if (password1.equals(password2)){
                System.out.println("两次密码一致");
                //密码一致，写入到password中
                password=password1;
                break;
            }else {
                System.out.println("两次密码不一致，重新输入");
                continue;
            }
        }

        //3身份证号需要验证
        //要求：①长度18位；②不能0开头；③前17必须为数字；④最后一位可以为数字，也可以是大写X或小写x
        while (true){
            System.out.println("请输入身份证号码");
            idCard=scanner.next();
            //身份证校验是否有效
            boolean isValidId=checkIdCard(idCard);
            if (isValidId){
                System.out.println("有效的身份证");
                break;
            }else {
                System.out.println("身份证格式有误,重新输入");
                continue;
            }
        }

        //4手机号验证
        //要求：① 长度11位；②不能0开头；③必须全部数字。
        while (true){
            System.out.println("请输入电话号码");
            phoneNum=scanner.next();
            //电话号码校验是否有效
            boolean isValidPhoneNum=checkPhoneNum(phoneNum);
            if (isValidPhoneNum){
                System.out.println("有效的电话号码");
                break;
            }else {
                System.out.println("电话号码有误，重新输入");
                continue;
            }
        }

        //校验到这一步，用户名、密码、身份证信息、电话号码都通过了 存入到用户列表
        User user=new User(userName,password,idCard,phoneNum);
        userList.add(user);
        System.out.println("**********恭喜注册成功***************");
    }

    /**
     * 电话号码校验是否有效
     * 判断条件① 长度11位；②不能0开头；③必须全部数字
     * @param phoneNum
     * @return
     */
    private static boolean checkPhoneNum(String phoneNum) {
        //① 长度11位
        if (phoneNum.length()!=ELEVEN){
            System.out.println("电话号码长度必须为11位");
            return false;
        }
        //②不能0开头
        if (phoneNum.startsWith(ZERO)){
            System.out.println("电话号码不能以0开头");
            return false;
        }
        //③必须全部数字 即每一个字符都需要是0-9
        for (int i =0;i<phoneNum.length();i++){
            char ch=phoneNum.charAt(i);
            if (!(ch>='0'&&ch<='9')){
                System.out.println("电话号码必须是0-9");
                return false;
            }
        }
        return true;
    }

    /**
     * 校验身份证号码是否有效
     * 判断条件①长度18位；②不能0开头；③前17必须为数字；④最后一位可以为数字，也可以是大写X或小写x
     * @param idCard
     * @return
     */
    private static boolean checkIdCard(String idCard) {
        //①长度18位
        if(idCard.length()!=EIGHTEEN){
            System.out.println("身份证号码长度必须是18位");
            return false;
        }
        //②不能0开头
        if (idCard.startsWith(ZERO)){
            System.out.println("身份证号码不能是0开头");
            return false;
        }
        //③前17必须为数字
        for (int i=0;i<idCard.length()-1;i++){
            char ch=idCard.charAt(i);
            if (!(ch>='0'&&ch<='9')){
                System.out.println("身份证前17位必须是数字");
                return false;
            }
        }
        //④最后一位可以为数字，也可以是大写X或小写x
        char lastCh=idCard.charAt(idCard.length()-1);
        return (lastCh>='0'&&lastCh<='9')||lastCh=='x'||lastCh=='X';
    }

    /**
     * 用户名只能是字母和数字的组合，但是不能是纯数字
     * @param userName
     * @return
     */
    private static boolean checkUserNameInfo(String userName) {
        //分两步  先校验字母和数字的组合   只要userName里面有一个字符不是数字或者字母 校验失败
        for (int i=0;i<userName.length();i++){
            char ch=userName.charAt(i);
            //a-z  A-Z   0-9
            if (!((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')||(ch>='0'&&ch<='9'))){
                return false;
            }
        }
        //第二步   不能是纯数字 只需要统计字母个数是否大于0
        int alphabetCount=0;
        //其实两个for循环可以减少只有一个  这样写 方便理解   线上建议只循环一次
        for (int i=0;i<userName.length();i++){
            char ch=userName.charAt(i);
            if ((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')){
                //只需要找到一个字母，就可以直接跳出来。一个字母即不可能为纯数字
                alphabetCount++;
                break;
            }
        }
        return alphabetCount>0;//大于0则有字母数字组合 符合要求
    }

    /**
     * 校验长度是否符合要求：用户名长度必须3-15位之间
     * @param userName
     * @return
     */
    private static boolean checkUserLength(String userName) {
        int length=userName.length();
        return length<3||length>15;
    }

    /**
     * 校验用户名是否唯一
     * @param userName
     * @param userList
     * @return
     */
    private static boolean checkUserNameUnique(String userName, List<User> userList) {
        //若用户列表为空，肯定不唯一
        if (userList.isEmpty()||userList.size()==0){
            return true;
        }
        for (int i=0;i<userList.size();i++){
            User user=userList.get(i);
            if (userName.equals(user.getUserName())){
                //相等，则不唯一，在用户列表已经有了
                return false;
            }
        }
        return true;
    }

    //3、忘记密码
    //规则： 3.1、键盘录入用户名，用户名是否存在，不存在，直接结束方法 并且提示 未注册
    //3.2   键盘录入身份证号码、手机号码
    //3.3  判断当前身份证号码、手机号码是否一致 如果一致提示输入密码 不一致 则提示账号信息不匹配 修改失败
    private static void forgetPassword(List<User> userList) {
        //如果userList为空 默认一个注册的用户都没有
        if (userList==null||userList.size()==0){
            System.out.println("未存在用户注册，请先注册");
            return;
        }
        System.out.println("请输入用户名");
        Scanner scanner=new Scanner(System.in);
        String userName=scanner.next();
        boolean isUserRegistered=userNameCheck(userName,userList);
        if (!isUserRegistered){
            System.out.println("用户名："+userName+"未注册");
            return;
        }
        System.out.println("用户存在,请输入其他信息确认用户身份");
        System.out.println("请输入身份证号码");
        String idCard=scanner.next();
        System.out.println("请输入电话号码");
        String phoneNum=scanner.next();
        //因为用户名是唯一的，通过用户名从用户列表取出用户相关信息
        User user=findUserByUserName(userName,userList);
        //判断身份证号码和电话号码是否匹配
        if (idCard.equals(user.getIdCard())&&phoneNum.equals(user.getPhoneNum())){
            System.out.println("请输入新密码");
            String newPassword=scanner.next();
            user.setPassword(newPassword);
            //todo：此处也可以让用户输入两次 不一样 不通过之类
            System.out.println("更新成功");
        }else {
            //todo：此处也可以让用户继续输入信息进行验证 用while循环的写法
            System.out.println("账号信息不匹配 修改失败");
        }
    }

    private static User findUserByUserName(String userName, List<User> userList) {
        User user=new User();
        for (int i =0;i<userList.size();i++){
            if (userName.equals(userList.get(i).getUserName())){
                user=userList.get(i);
            }
        }
        return user;
    }

    /**
     * 创建字母集合
     * @return
     */
    private static List<Character> buildAlphabetList() {
        List<Character> list=new ArrayList<>();
        //aA bB cC .....
        for (int i=0;i<26;i++){
            list.add((char)('a'+i));
            list.add((char)('A'+i));
        }
        return list;
    }
}


