import java.util.*;

public class version {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String v1=in.nextLine();
        String v2=in.nextLine();
        System.out.println(compareVersion(v1,v2));
    }
    public static int compareVersion(String version1, String version2) {
            int i = 0, j = 0, len1 = version1.length(), len2 = version2.length();
            int num1 = 0, num2 = 0;
            while (i < len1 || j < len2) {
                while (i < len1 && version1.charAt(i) != '.') {
                    num1 = num1 * 10 + (version1.charAt(i) - '0');
                    i++;
                }
                while (j < len2 && version2.charAt(j) != '.') {
                    num2 = num2 * 10 + (version2.charAt(j) - '0');
                    j++;
                }
                if (num1 != num2) {
                    return num1 < num2 ? 1 : -1;
                }
                num1 = 0;
                num2 = 0;
                i++;
                j++;
            }
            return 0;
        }
    }
