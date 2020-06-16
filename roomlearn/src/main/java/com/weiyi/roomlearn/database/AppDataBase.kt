package com.weiyi.roomlearn.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.weiyi.roomlearn.DB_NAME
import com.weiyi.roomlearn.converters.Converters
import com.weiyi.roomlearn.dao.PersonDao
import com.weiyi.roomlearn.dao.StudentDao
import com.weiyi.roomlearn.entity.Person
import com.weiyi.roomlearn.entity.Student

/**
 * @author TW
 * @date 2020/6/4 9:52
 * @description
 * @mail 2278671454@qq.com
 */
//@TypeConverters(Converters::class)
@Database(entities = [Person::class, Student::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getPersonDao(): PersonDao
    abstract fun getStudentDap(): StudentDao

    companion object {
        @JvmStatic
        @Volatile
        private var appDataBase: AppDataBase? = null

        @JvmStatic
        fun getInstance(context: Context): AppDataBase {
            return appDataBase ?: buildDataBase(context).apply { appDataBase = this }

        }

        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                TODO("Not yet implemented")
            }
        }

        private fun buildDataBase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
                    //允许主线程访问数据库
                    .allowMainThreadQueries()
                    //如果 Room 无法找到将该设备的数据库从旧版本升级到当前版本的迁移规则，则会发生 IllegalStateException,使用此方法可强制迁移
                    .fallbackToDestructiveMigration()
                    .addMigrations(migration_1_2)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                        }
                    })
                    .build()
        }
    }


}

