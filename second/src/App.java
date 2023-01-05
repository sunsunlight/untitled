import com.sinopec.ui.GameJFrame;
import com.sinopec.ui.LoginJFrame;
import com.sinopec.ui.RegisterJFrame;

import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    public static void main(String[] args) {
       // new LoginJFrame();
       // new RegisterJFrame();
        //new GameJFrame();
        //
        // System.out.println(Runtime.getRuntime().availableProcessors());
        Date d = new Date();
        System.out.println(d);

        Date d2 =new Date(0L);
        System.out.println(d2);

        d2.setTime(1000L);
        System.out.println(d2);
        System.out.println("--------------------------------------");

        SimpleDateFormat sdf = new SimpleDateFormat();
        String str = sdf.format(d);
        System.out.println(str);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E");
        String str2 = sdf2.format(d2);
        System.out.println(str2);

        String regex = "";























    }
}
