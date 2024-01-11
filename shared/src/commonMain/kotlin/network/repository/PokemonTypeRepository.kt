package network.repository

import dataClass.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import network.api.PokemonTypeAPI

object PokemonTypeRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var _typeState: MutableStateFlow<List<Type>> = MutableStateFlow(listOf<Type>())
    val typeState: StateFlow<List<Type>> = _typeState

    fun initPokemonTypes() {
        coroutineScope.launch {
            _typeState.update {
                PokemonTypeAPI.getAllPokemonType()
            }
        }
    }
}