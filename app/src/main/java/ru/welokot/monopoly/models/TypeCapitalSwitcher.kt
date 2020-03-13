package ru.welokot.monopoly.models

class TypeCapitalSwitcher {

    private var type = TypeCapital.K

    fun switchType() {
        type = when (type) {
            TypeCapital.K -> TypeCapital.M
            else -> TypeCapital.K
        }
    }

    fun getType() = type
}

enum class TypeCapital {
    K, M
}