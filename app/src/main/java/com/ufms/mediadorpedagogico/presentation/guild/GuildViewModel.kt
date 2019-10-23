package com.ufms.mediadorpedagogico.presentation.guild

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.presentation.guild.delegate.GuildDelegate
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class GuildViewModel constructor(
    guildDelegate: GuildDelegate
) : BaseViewModel(), GuildDelegate by guildDelegate {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
        getGuild({}, {}, ::setPlaceholder)
    }
}