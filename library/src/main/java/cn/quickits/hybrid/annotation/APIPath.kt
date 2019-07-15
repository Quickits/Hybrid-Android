package cn.quickits.hybrid.annotation


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-15 14:57
 **/
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class APIPath(val path: String)
