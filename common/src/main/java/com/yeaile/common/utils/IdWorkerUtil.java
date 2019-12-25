package com.yeaile.common.utils;


import com.yeaile.common.utils.conn.Sequence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/23
 * @return
 **/
public class IdWorkerUtil {
    private static Sequence WORKER = new Sequence();
    public static final DateTimeFormatter MILLISECOND = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public IdWorkerUtil() {
    }

    public static long getId() {
        return WORKER.nextId();
    }

    public static String getIdStr() {
        return String.valueOf(WORKER.nextId());
    }

    public static String getMillisecond() {
        return LocalDateTime.now().format(MILLISECOND);
    }

    public static String getTimeId() {
        return getMillisecond() + getId();
    }

    public static void initSequence(long workerId, long datacenterId) {
        WORKER = new Sequence(workerId, datacenterId);
    }

    public static String get32UUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return (new UUID(random.nextLong(), random.nextLong())).toString().replace("-", "");
    }
}
