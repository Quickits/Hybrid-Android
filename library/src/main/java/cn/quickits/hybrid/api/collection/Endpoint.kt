package cn.quickits.hybrid.api.collection

import cn.quickits.hybrid.annotation.APIParam
import cn.quickits.hybrid.api.AbsApi
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Method


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-15 15:27
 **/
class Endpoint(private val absApi: AbsApi, private val method: Method) {


    fun invoke(param: String?): Any? {
        val parameterAnnotations = method.parameterAnnotations
        val parameterTypes = method.parameterTypes

        return if (parameterAnnotations.isNotEmpty()) {
            val jsonObject = Gson().fromJson(param, JsonObject::class.java)

            val parameters: Array<Any?> = arrayOfNulls(method.parameterTypes.size)

            parameterAnnotations.forEachIndexed { index, arrayOfAnnotations ->
                val type = parameterTypes[index]

                arrayOfAnnotations.forEach {
                    if (it is APIParam) {
                        val jsonElement = jsonObject.get(it.name)
                        parameters[index] = castValueToType(jsonElement, type)
                    }
                }
            }

            method.invoke(absApi, *parameters)
        } else {
            method.invoke(absApi)
        }
    }

    private fun castValueToType(jsonElement: JsonElement?, type: Class<*>?): Any? {
        if (jsonElement == null || jsonElement.toString() == "null") return null

        return when (type) {
            String::class.java -> jsonElement.asString
            Int::class.java -> jsonElement.asInt
            else -> null
        }
    }

}