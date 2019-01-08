import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

public class NumberGame {

    private static void scanMaxAndMin(int[] numbers) {
        int max = 0, min = 0;
        // Your stuff here
        if (numbers.length>0) {
            max = min = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i] > max) {
                    max = numbers[i];
                }
                if (numbers[i] < min) {
                    min = numbers[i];
                }
            }
            System.out.printf("Max is %d and min is %d%n", max, min);
        }
    }

    private static void scanFirstTwoMaxs(int[] numbers) {
        int max = 0, secondMax = 0;
        // Your stuff here
        if (numbers.length == 1){
            max = secondMax = numbers[0];
        }
        if (numbers.length > 1) {
            max = numbers[0];
            secondMax = Integer.MIN_VALUE;
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i] >= max) {
                    secondMax = max;
                    max = numbers[i];
                }else if (numbers[i] > secondMax) {
                    secondMax = numbers[i];
                }
            }
            System.out.printf("Max is %d and second max is %d%n", max, secondMax);
        }
    }

    private static void scanFirstThreeMaxs(int[] numbers) {
        int max = 0, secondMax = 0, thirdMax = 0;
        // Your stuff here
        int maxIndex, secondMaxIndex, thirdMaxIndex;

        max = secondMax = thirdMax = Integer.MIN_VALUE;
        maxIndex = secondMaxIndex = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
                maxIndex = i;
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > secondMax && i != maxIndex)  {
                secondMax = numbers[i];
                secondMaxIndex = i;
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > thirdMax && i != maxIndex && i != secondMaxIndex)  {
                thirdMax = numbers[i];
            }
        }

        System.out.printf("Max is %d, second max is %d and third max is %d%n", max, secondMax, thirdMax);


    }

    private static void threeSums(int[] numbers) {
        int count = 0;
        // Your stuff here
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        for (int i=0; i<numbers.length; i++){
            map1.put(numbers[i], i);
            map2.put(-numbers[i], i);
        }
        HashSet set = new HashSet();

        for (int i=0; i<numbers.length; i++){
            if (!set.contains(i)) {
                for (int j = i + 1; j < numbers.length; j++) {
                    if (!set.contains(j)) {
                        for (int k = j + 1; k < numbers.length; k++) {
                            if (!set.contains(k)) {
                                if (numbers[i] + numbers[j] + numbers[k] == 0) {
                                    count++;
                                    set.add(i);
                                    set.add(j);
                                    set.add(k);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.printf("There are %d combinations of 3 numbers that sum of them is 0%n", count);
    }

    private static void threeMillionSums(int[] numbers) {
        int count = 0;
        // Your stuff here
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Arrays.sort(numbers);
        for (int i=0; i<numbers.length; i++){
            map.put(numbers[i], i);
        }
        HashSet set = new HashSet();
        int maxIndex = numbers.length - 1;
        int k = Math.abs(Arrays.binarySearch(numbers, 0));
        for (int i=0; i<k; i++){
            int s = Math.abs(Arrays.binarySearch(numbers, 0 - numbers[i] - numbers[maxIndex]));
            if (s<=i) {
                break;
            }
            int t = Math.abs(Arrays.binarySearch(numbers, (0 - numbers[i])/2));
            for (int j = s; j <= t; j++) {
                if (!set.contains(j)) {
                    int p = Math.abs(Arrays.binarySearch(numbers, 0 - numbers[i] - numbers[j]));
                    if (p < j) {
                        break;
                    }
                    if (map.get(0 - numbers[i] - numbers[j]) != null) {
                        if (map.get(0 - numbers[i] - numbers[j]) > j) {
                            count++;
                            set.add(j);
                            set.add(map.get(0 - numbers[i] - numbers[j]));
                            break;
                        }

                    }
                }

            }
        }
        System.out.printf("There are %d combinations of 3 numbers that sum of them is 0%n", count);
    }

    public static int[] toArrayByFileReader1(String name) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对ArrayList中存储的字符串进行处理
        int length = arrayList.size();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            String s = arrayList.get(i).trim();
            array[i] = Integer.parseInt(s);
        }
        // 返回数组

        return array;
    }



    public static void main(String[] args) {
        int[] numbers = {-1, -2, 3, -4, -5, 9, 10, 11};
        String filePath = "/Users/sky/Desktop/Fibonacci/src/1Mints.txt";
        scanMaxAndMin(numbers);
        scanFirstTwoMaxs(numbers);
        scanFirstThreeMaxs(numbers);
        threeSums(numbers);
        threeMillionSums(toArrayByFileReader1(filePath));
    }

}
