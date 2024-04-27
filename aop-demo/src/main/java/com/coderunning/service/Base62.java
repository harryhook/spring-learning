package com.coderunning.service;

public class Base62 {

    public static final String BASE_62_DIGITS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    /**
     * 0->0,1-1,2->2,3->3,4->4,5->5,6->6,7->7,8->8,9->9
     * 10->a,11->b,12->c,13->d,14->e,15->f,16->g,17->h,18->i,19->j
     * 61-Z
     * 11157 = 2*62^2 + 55*62^1 + 59*62^0 = 2,55,59 = 2TX
     *
     * @param baseId
     * @return
     */
    public String encode(Long baseId) {
        // 对62^0, 62^1 62^2, 62^3 取余数,得到最终表示
        StringBuilder base62 = new StringBuilder();
        while (baseId > 0) {
            int remainder = (int) (baseId % 62);
            base62.insert(0, BASE_62_DIGITS.charAt(remainder));
            baseId /= 62;
        }
        return base62.toString();


    }

    public Long decode(String str) {
        long baseId = 0L;
        for (int i = 0; i < str.length(); i++) {
            baseId = baseId * 62 + BASE_62_DIGITS.indexOf(str.charAt(i));
        }
        return baseId;
    }

    public static void main(String[] args) {

        Base62 base62 = new Base62();
        System.out.println(base62.encode(11157L ));

        System.out.println(base62.decode("2TZ"));
    }
}
