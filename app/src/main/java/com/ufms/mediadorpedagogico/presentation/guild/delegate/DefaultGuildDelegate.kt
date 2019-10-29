package com.ufms.mediadorpedagogico.presentation.guild.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ufms.mediadorpedagogico.domain.entity.Guild
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.guild.GetGuild
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import io.reactivex.rxkotlin.subscribeBy

class DefaultGuildDelegate constructor(
    private val getGuild: GetGuild,
    private val schedulerProvider: SchedulerProvider
) : GuildDelegate {

    override val guildReceived: LiveData<Guild> get() = guildReceivedLiveData

    private val guildReceivedLiveData = MutableLiveData<Guild>()

    override fun getGuild(
        onSuccess: (Guild) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderPlacerAction: (Placeholder) -> (Unit)
    ) {
        getGuild.execute().defaultSched(schedulerProvider)
            .defaultPlaceholders(placeholderPlacerAction)
            .subscribeBy(onFailure) {
                guildReceivedLiveData.value = it
                onSuccess.invoke(it)
            }
    }
}