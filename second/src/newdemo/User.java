package newdemo;

public class User {
    //用户名   密码   身份证号码   手机号
    private String userName,password,idCardNum,phoneNum;

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
