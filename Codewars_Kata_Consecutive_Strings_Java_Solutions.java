//The first one --> simple but ez to understand:
class LongestConsec {
    public static String longestConsec(String[] strarr, int k) {
        if (strarr.length == 0 || k > strarr.length || k <= 0)
            return "";

        String longestStr = "";
        for (int index = 0; index < strarr.length - k + 1; index++) {
            StringBuilder sb = new StringBuilder();
            for (int i = index; i < index + k; i++) {
                sb.append(strarr[i]);
            }
            if (sb.toString().length() > longestStr.length()) {
                longestStr = sb.toString();
            }
        }
        return longestStr;
    }
}
//The second solution:
import java.util.stream.*;
class LongestConsec {
  public static String longestConsec(String[] strarr, int k) {
    String maxStr = "";
    for (int i=0; i<=strarr.length-k; i++) {
      String current = IntStream.range(i, i+k).mapToObj(j -> strarr[j]).collect(Collectors.joining());
      if (current.length() > maxStr.length()) maxStr = current;
    }
    return maxStr;
  }
}
//The third solution:
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class LongestConsec {

  public static String longestConsec(String[] strArr, int k) {
    return (k > 0 && strArr.length > 0 && k <= strArr.length) ?
      IntStream.rangeClosed(0, strArr.length - k)
        .mapToObj(i -> IntStream.range(0, k).mapToObj(j -> strArr[i + j]).collect(Collectors.joining()))
        .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))
        .findFirst().get()
      : "";
  }
}
//The forth solution:
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LongestConsec {
    public static String longestConsec(String[] strarr, int k) {
        if (k <= 0) {
            return "";
        }

        return IntStream.rangeClosed(0, strarr.length - k)
                .mapToObj(i -> Arrays.stream(strarr, i, i + k).collect(Collectors.joining()))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}
//The fifth solution:
import java.util.Arrays;

class LongestConsec {
    
    public static String longestConsec(String[] strarr, int k) {
        String ans = "", test = "";
        if (k > 0) {
            for (int i = 0 ; i < strarr.length+1-k ; i++) {
                test = String.join("", Arrays.copyOfRange(strarr, i, i+k));
                ans = ans.length() < test.length() ? test : ans;
            }
        }
        return ans;    
    }
}
