package me.mrgeneralq.sleepmost.repositories

import com.fasterxml.jackson.databind.ObjectMapper
import me.mrgeneralq.sleepmost.models.abstracts.Configurable
import java.nio.file.Path

interface IMultiFileConfigsRepository<ID, T : Configurable<ID>> {
    val objectMapper: ObjectMapper
    val configFilesDir: Path

    val configsById: MutableMap<ID, T>

    fun createDefault(id: ID): T
    fun createDefaultAndSave(id: ID): T = save(createDefault(id))

    fun create(input: T): T
    fun createAndSave(input: T): T = save(create(input))

    fun update(id: ID, input: T): T
    fun updateAndSave(id: ID, input: T): T

    fun save(input: T): T
    fun save(id: ID): T
    fun save()

    fun load(id: ID): T
    fun load()
}