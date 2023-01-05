package statictestdemo;

public class PrintArrAndGetAverageUtil {
    //私有化构造方法
    //目的：不让外界创建他的对象
    private PrintArrAndGetAverageUtil() {
    }

    //定义静态的方法，方便调用

    //遍历数组
    public static String printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                sb.append(arr[i]);
                sb.append("]");
            } else {
                sb.append(arr[i]).append(",");
            }
        }

        return sb.toString();
    }

    //求平均值
    public static double average(double[] arr2) {
        double sum = 0;
        for (int i = 0; i < arr2.length; i++) {
            sum = sum + arr2[i];
        }
        return sum / arr2.length;
    }
}
