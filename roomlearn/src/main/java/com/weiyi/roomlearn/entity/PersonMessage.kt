package com.weiyi.roomlearn.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

/**
 * @author TW
 * @date 2020/6/4 16:32
 * @description
 * @mail 2278671454@qq.com
 */
@Entity(foreignKeys = [ForeignKey(entity = Person::class,
        parentColumns = ["id"], childColumns = ["personId"], onDelete = CASCADE)])
data class PersonMessage(
        @ColumnInfo var marriage: String = "") {
    @PrimaryKey
    @ColumnInfo(name = "personId")
    var person_id: Long = 0
    override fun toString(): String {
        return "personId:$person_id,marriage:$marriage"
    }
}