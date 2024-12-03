package mg.techlab.mobile.myapp

import android.content.ContextWrapper
import android.view.View
import java.util.UUID

fun String.capitalizeFirstLetter(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

val randomUUID: String
    get() = UUID.randomUUID().toString()

typealias Closure<T> = (T) -> Unit

inline fun <reified PARENT> View.getParentActivity(): PARENT? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is PARENT) {
            return context
        }
        context = context.baseContext
    }
    return null
}