public class KMPnext {
    private int [] next ;
    private String s ;
    private String pattern;

    public KMPnext(String s,String pattern) {
        this.s=s;
        this.pattern=pattern;
        next=new int[pattern.length()];
        makeNext(pattern);

    }


    private void makeNext(String pat) {
        next[0]=-1;
        int i=0;     // i 表示匹配到第几个了，j表示有几个重复的
        int j=-1;  // i 始终比 j 大 ，如果i 到了 pat。length（）-1，
                   // 那说明这一个loop内就会超过pattern的长度了，此时j 刚好，全程结束

        while(i<pat.length()-1) { // i 到了倒数第一位就要停止了
            if(j==-1 || pat.charAt(i)==pat.charAt(j) ) { //如果相等，大家可以携手共进
                ++i;
                ++j;
                next[i]=j;   //如果一开始就匹配上了，next【i】 = j, 我的剑就是你的剑，j表示有几个可重用的，可能一个都没有！！
                             //以后如果谁再第i个如果失配，请回到现在的j重新开始，因为在此之前一定都是前后缀相同可重用
            }
            else { // 如果没匹配上 凉了
                j=next[j];        // 没匹配的话请滚到解放前
            }
        }
    }

    public int kmp () {
        String p=pattern;
        int i=0;
        int j=0;

        while(i< s.length() && j<p.length()) {
            if(j==-1 || s.charAt(i)==p.charAt(j)){
                j++;
                i++;                 // kmp本身则是携手共进
            }
            else
                j=next[j];         // 滚回解放前
        }

        if(j==p.length())
            return i-j;
        else
            return -1;
    }



    public static void main(String[] args) {
        KMPnext kmp=new KMPnext("aarousssry","asda");
        System.out.println(kmp.kmp());
    }
}
