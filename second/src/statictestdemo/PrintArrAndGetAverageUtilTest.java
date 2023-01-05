package statictestdemo;

public class PrintArrAndGetAverageUtilTest {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 9, 7};

        String str = PrintArrAndGetAverageUtil.printArr(arr);
        System.out.println(str);

        double[] arr2 = {3.2, 5.6, 9.1, 5.5};
        double avg = PrintArrAndGetAverageUtil.average(arr2);
        System.out.println(avg);
    }
}


