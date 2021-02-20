package com.iflytek.mvvm_cli.extens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable

import android.util.Log
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.iflytek.mvvm_cli.BuildConfig
import com.iflytek.mvvm_cli.net.NetManager
import com.iflytek.mvvm_cli.ui.login.view.activity.LoginActivity
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import java.io.Serializable


/**
 * description：some extens
 *
 * Created by ditclear on 2017/9/29.
 */

fun Activity.getCompactColor(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

fun Activity.dpToPx(@DimenRes resID: Int): Int = this.resources.getDimensionPixelOffset(resID)

fun Any.logD(msg: String?) {
    if (BuildConfig.DEBUG) {
        Log.d(javaClass.simpleName, msg)
    }
}

fun <T> Any.retrofit(service: Class<T>): T {
    return NetManager.getService(service)
}

fun Activity.toast(msg: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.showSuccessDialog(msg: String?, callback: (() -> Unit)? = null) {
    val dialog = QMUITipDialog.Builder(this)
        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
        .setTipWord(msg)
        .create()
    dialog.show()
    Handler().postDelayed({
        dialog.dismiss()
        callback?.invoke()
    }, 1500)
}

fun Activity.showFailedDialog(msg: String?, callback: (() -> Unit)? = null) {
    val dialog = QMUITipDialog.Builder(this)
        .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
        .setTipWord(msg)
        .create()
    dialog.show()
    Handler().postDelayed({
        dialog.dismiss()
        callback?.invoke()
    }, 1500)
}

fun Activity.showWarningDialog(msg: String?, callback: (() -> Unit)? = null) {
    val dialog = QMUITipDialog.Builder(this)
        .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
        .setTipWord(msg)
        .create()
    dialog.show()
    Handler().postDelayed({
        dialog.dismiss()
        callback?.invoke()
    }, 1500)
}

fun Fragment.showSuccessDialog(msg: String?, callback: (() -> Unit)? = null) {
    val dialog = QMUITipDialog.Builder(activity)
        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
        .setTipWord(msg)
        .create()
    dialog.show()
    Handler().postDelayed({
        dialog.dismiss()
        callback?.invoke()
    }, 1500)
}

fun Fragment.showFailedDialog(msg: String?, callback: (() -> Unit)? = null) {
    val dialog = QMUITipDialog.Builder(activity)
        .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
        .setTipWord(msg)
        .create()
    dialog.show()
    Handler().postDelayed({
        dialog.dismiss()
        callback?.invoke()
    }, 1500)
}

fun Fragment.showWarningDialog(msg: String?, callback: (() -> Unit)? = null) {
    val dialog = QMUITipDialog.Builder(activity)
        .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
        .setTipWord(msg)
        .create()
    dialog.show()
    Handler().postDelayed({
        dialog.dismiss()
        callback?.invoke()
    }, 1500)
}

inline fun <reified T : Activity> Activity.startActivity(
    needLogin: Boolean? = false,
    flags: Int? = null,
    extra: Bundle? = null,
    values: List<Pair<String, Any>?>? = null
): Unit =
    if (needLogin == true) {
        val login = Intent(this, LoginActivity::class.java)
        login.putExtra("intent", getIntent<T>(flags, extra, values))
        startActivity(login)
    } else {
        startActivity(getIntent<T>(flags, extra, values))
    }


inline fun <reified T : Activity> Fragment.startActivity(
    needLogin: Boolean? = false,
    flags: Int? = null,
    extra: Bundle? = null,
    values: List<Pair<String, Any>?>? = null
): Unit =
    if (needLogin == true) {
        val login = Intent(activity, LoginActivity::class.java)
        login.putExtra("intent", context?.getIntent<T>(flags, extra, values))
        startActivity(login)
    } else {
        startActivity(context?.getIntent<T>(flags, extra, values))
    }

inline fun <reified T : Context> Context.getIntent(
    flags: Int?,
    extra: Bundle?,
    pairs: List<Pair<String, Any>?>?
): Intent =
    Intent(this, T::class.java).apply {
        flags?.let { setFlags(flags) }
        extra?.let { putExtras(extra) }
        pairs?.let {
            for (pair in pairs)
                pair?.let {
                    val name = pair.first
                    when (val value = pair.second) {
                        is Int -> putExtra(name, value)
                        is Byte -> putExtra(name, value)
                        is Char -> putExtra(name, value)
                        is Short -> putExtra(name, value)
                        is Boolean -> putExtra(name, value)
                        is Long -> putExtra(name, value)
                        is Float -> putExtra(name, value)
                        is Double -> putExtra(name, value)
                        is String -> putExtra(name, value)
                        is CharSequence -> putExtra(name, value)
                        is Parcelable -> putExtra(name, value)
                        is Array<*> -> putExtra(name, value)
                        is ArrayList<*> -> putExtra(name, value)
                        is Serializable -> putExtra(name, value)
                        is BooleanArray -> putExtra(name, value)
                        is ByteArray -> putExtra(name, value)
                        is ShortArray -> putExtra(name, value)
                        is CharArray -> putExtra(name, value)
                        is IntArray -> putExtra(name, value)
                        is LongArray -> putExtra(name, value)
                        is FloatArray -> putExtra(name, value)
                        is DoubleArray -> putExtra(name, value)
                        is Bundle -> putExtra(name, value)
                        is Intent -> putExtra(name, value)
                        else -> {
                        }
                    }
                }
        }
    }


