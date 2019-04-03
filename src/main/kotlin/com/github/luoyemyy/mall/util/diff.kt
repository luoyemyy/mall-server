package com.github.luoyemyy.mall.util

/**
 * 比较2个列表
 * @return  a  oldList 中存在 newList 不存在的元素列表     //待删除
 *          b  oldList 不中存在 newList 存在的元素列表     //待新增
 */
fun <T> diff(oldList: List<T>?, newList: List<T>?, eq: (T, T) -> Boolean = { o, n -> o == n }): Pair<List<T>, List<T>> {
    if (oldList == null || oldList.isEmpty()) {
        return Pair(listOf(), newList ?: listOf())
    } else if (newList == null || newList.isEmpty()) {
        return Pair(oldList, listOf())
    }
    val mutableList1 = oldList.let {
        if (it is MutableList) {
            it
        } else {
            it.toMutableList()
        }
    }
    val mutableList2 = newList.let {
        if (it is MutableList) {
            it
        } else {
            it.toMutableList()
        }
    }

    val delete = mutableList1.filter { o -> newList.none { n -> eq(o, n) } }
    val add = mutableList2.filter { n -> oldList.none { o -> eq(o, n) } }
    return Pair(delete, add)
}

/**
 * 减去与 list 中相匹配的元素
 * @return list 中不匹配的元素
 */
fun <T> List<T>?.minus(list: List<T>?, eq: (T, T) -> Boolean = { o, n -> o == n }): List<T> {
    if (this.isNullOrEmpty()) return listOf()
    if (list.isNullOrEmpty()) return this
    val mutableList = this.let {
        if (it is MutableList) {
            it
        } else {
            it.toMutableList()
        }
    }
    return mutableList.filter { o -> list.none { n -> eq(o, n) } }
}

