package com.weiyi.roomlearn.converters;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * @author TW
 * @date 2020/6/10 10:37
 * @description
 * @mail 2278671454@qq.com
 */
public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
