package com.weiyi.roomlearn.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author TW
 * @date 2020/6/3 11:50
 * @description
 * @mail 2278671454@qq.com
 */
//@Entity(
////        indices = [Index(value = ["username", "idCard"], name = "identity", unique = true)],
////       primaryKeys = ["id", "idCard"],
//        tableName = PERSON_TABLE
//)
@Entity
data class Person(@ColumnInfo(name = "username")
                  var name: String = "") {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo
    var idCard = ""

    @ColumnInfo
    var location: String = ""

    @ColumnInfo
    var age: Int = 0

    @ColumnInfo
    //0男，1女
    var sex: Int = 0

    //身份证照片
//    @Ignore
//    var picture: Bitmap? = null

    @Embedded(prefix = "first")
    var message1: PersonMessage? = null


    @Embedded(prefix = "second")
    var message2: PersonMessage? = null

    private fun getSex(sex: Int): String {
        return if (sex == 0) {
            "男"
        } else {
            "女"
        }
    }

    override fun toString(): String {
        return "name:$name;age:$age;sex:${getSex(sex)};location:$location;idCard:$idCard;marriage:${message1?.marriage}}\n"
    }
}