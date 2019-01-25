public class test {

    public static int test1(long a){

        String temp = Long.toBinaryString(a);

        int count = 0;
        char[] m = temp.toCharArray();
        for (char s : m){
            if (s == '1'){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(test1(3));
    }
}
