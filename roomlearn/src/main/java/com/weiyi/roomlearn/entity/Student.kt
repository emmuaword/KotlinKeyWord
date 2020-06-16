package com.weiyi.roomlearn.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.weiyi.roomlearn.STUDENT_TABLE

/**
 * @author TW
 * @date 2020/6/3 11:47
 * @description
 * @mail 2278671454@qq.com
 */
@Entity(tableName = STUDENT_TABLE)
class Student {
    //学号
    @PrimaryKey(autoGenerate = true)
    var sid: Int = 0

    //班级
    @ColumnInfo
    var grade: String = ""


}