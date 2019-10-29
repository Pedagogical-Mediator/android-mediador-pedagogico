package com.ufms.mediadorpedagogico.presentation.guild.delegate

import androidx.lifecycle.LiveData
import com.ufms.mediadorpedagogico.domain.entity.Guild
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder

interface GuildDelegate {
    val guildReceived: LiveData<Guild>
    fun getGuild(
        onSuccess: (Guild) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderPlacerAction: (Placeholder) -> (Unit)
    )
}