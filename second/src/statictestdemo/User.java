package statictestdemo;

public class User {
    //用户名   密码   身份证号码   手机号
    //todo 这是对象 不是变量  不能这样写  不规范
    /**
     * private String userName；
     * private String password；
     * 。。。。
     */
    private String userName,password,idCardNum,phoneNum;

    //todo 这个构造方法 项目中并没有用到 可以不构造 后期直接用@Data就好了
    public User() {
    }

    public User(String userName, String password, String idCardNum, String phoneNum) {
        this.userName = userName;
        this.password = password;
        this.idCardNum = idCardNum;
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
