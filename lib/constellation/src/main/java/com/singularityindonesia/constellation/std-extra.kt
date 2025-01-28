package com.singularityindonesia.constellation

infix fun <T : Any, S : Any> T.vs(other: S) = Pair(this, other)