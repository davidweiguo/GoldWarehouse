package com.goldwarehouse.common.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by David on 2016/12/20.
 */
public class IdGenerator {

    static private IdGenerator instance;

    private IdGenerator() {
        dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");
        setDecimalFormat(4);
        lastTimeStamp = Clock.getInstance().now();
    }

    static public IdGenerator getInstance() {
        if (null == instance) {
            instance = new IdGenerator();
        }
        return instance;
    }

    private SimpleDateFormat dateFormat;
    private int digits;
    private DecimalFormat decimalFormat;
    private Date lastTimeStamp;
    private long seq = 0;
    private String prefix = "";
    private Random ran = new Random();
    private int max = 1;
    private String systemId;
    private AtomicInteger count = new AtomicInteger(1);

    private void setDecimalFormat(int digits) {
        this.digits = digits;
        String str = "";
        for (int i = 0; i < digits; i++) {
            str += "0";
            max *= 10;
        }
        decimalFormat = new DecimalFormat(str);

    }

    private void setRandomSeq(Date time) {
        if (time.equals(lastTimeStamp)) {
            seq++;
            if (seq >= max) {
                System.out.println("increase id length");
                setDecimalFormat(digits * 2);
            }
        } else {
            lastTimeStamp = time;
            seq = ran.nextInt(max / 4);
        }
    }

    synchronized public String getNextID() {
        Date time = Clock.getInstance().now();
        setRandomSeq(time);
        return prefix + dateFormat.format(time) + "-" + decimalFormat.format(seq);
    }

    // this one works better for hash and comparison
    synchronized public String getRevNextID() {
        Date time = Clock.getInstance().now();
        setRandomSeq(time);
        return prefix + decimalFormat.format(seq) + "-" + dateFormat.format(time);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getNextSimpleId() {
        Date time = Clock.getInstance().now();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        long start = 0;
        try {
            Date startDate = dateFormat.parse("2014");
            start = startDate.getTime();
        } catch (ParseException e) {
        }
        long number = time.getTime();
        number -= start;
        number /= 1000;
        int suffix = count.getAndIncrement();
        return "" + number + String.format("%03d", suffix);
    }
}
