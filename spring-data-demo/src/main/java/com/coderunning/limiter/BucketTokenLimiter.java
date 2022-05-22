package com.coderunning.limiter;

public class BucketTokenLimiter {

    // 令牌生速率
    private int speed = 1;

    // 更新时间
    private long updateTime;

    // 桶容量
    private int capacity;

    // 当前令牌数量
    public int num;

    public BucketTokenLimiter(int speed, long updateTime, int capacity, int num) {
        this.speed = speed;
        this.updateTime = updateTime;
        this.capacity = capacity;
        this.num = num;
    }

    public String filter(int permit) {
        /*先结算令牌数量*/
        long now = System.currentTimeMillis();
        //上限为桶容量，超过就丢弃
        long time = (now - this.updateTime) / 1000;
        this.num = Math.toIntExact(Math.min(capacity, this.num + time * speed));
        this.updateTime = now;
        String message = "当前令牌数：" + this.num + ",需要令牌数" + permit;
        if (this.num < permit) {
            String error = message + "，请求拒绝================";
            System.out.println(message);
            return error;
        } else {
            System.out.println(message);
            this.num = this.num - permit;
            return message;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        BucketTokenLimiter bucketTokenLimiter = new BucketTokenLimiter( 1,System.currentTimeMillis() - 1,100, 10);

        while (true) {
            bucketTokenLimiter.filter(10);
            Thread.sleep(100L);
        }
    }
}
