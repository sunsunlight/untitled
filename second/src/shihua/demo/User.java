package shihua.demo;

/**
 * @author yilaguan
 * @date 2022/12/23
 */

/**
 * 用户类      用户名   密码   身份证号码   手机号
 */
public class User {
    private String userName;
    private String password;
    private String idCard;
    private String phoneNum;


    //构造器、get set 方法推荐用Lombok插件

    public User() {
    }

    public User(String userName, String password, String idCard, String phoneNum) {
        this.userName = userName;
        this.password = password;
        this.idCard = idCard;
        this.phoneNum = phoneNum;
    }

    //登录的时候，用户并不需要输入电话号码和身份证信息
    //通过userName password构造用户信息


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}


