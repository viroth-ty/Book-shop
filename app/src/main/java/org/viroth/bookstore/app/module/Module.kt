package org.viroth.bookstore.app.module

import org.koin.core.component.getScopeId
import org.koin.core.qualifier.named
import org.koin.dsl.module

data class Classes(
    val classId: Int? = 1,
    val className: String? = "Class A",
    val classTime: Long? = 8438743,
    val classLectures: ArrayList<String>? = arrayListOf("Jecob", "Jessica June"),
)

class Student(val classes: Classes)

class UserSession() {
    fun startSession(userName: String = "User name") {
        println("$userName is starting...")
    }

    fun endSession() {
        println("Session ends")
    }
}

val appModule = module {
    single { params -> Classes(classId = params.get(), className = params.get(), classTime = params.get(), classLectures = params.get()) }

    factory {
        Student(get())
    }

    scope(named("Session")) {
        scoped { UserSession() }
    }

    scope<Classes> {
        scoped { params ->
            Classes(classId = params.get(), className = params.get(), classTime = params.get(), classLectures = params.get())
        }
        scoped {
            Student(get())
        }
    }

}