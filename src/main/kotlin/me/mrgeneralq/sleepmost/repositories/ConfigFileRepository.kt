package me.mrgeneralq.sleepmost.repositories

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import me.mrgeneralq.sleepmost.extensions.dataFolder
import me.mrgeneralq.sleepmost.extensions.mkdirsIfNotExists
import me.mrgeneralq.sleepmost.extensions.writeValue
import me.mrgeneralq.sleepmost.models.abstracts.Configurable
import java.nio.file.Path

abstract class ConfigFileRepository<ID, T : Configurable<ID>>(
    private val objectMapper: ObjectMapper = ObjectMapper(YAMLFactory())
        .findAndRegisterModules()
        .registerKotlinModule()
        .enable(SerializationFeature.INDENT_OUTPUT),

    private val configFilesDirectory: Path? = null
) {
    private val displayName: String
        get() = javaClass.simpleName.replace("repository", "", ignoreCase = true)

    private val configFilePrefix: String
        get() = "${displayName.lowercase()}_"

    private fun configFileName(input: T) = configFileName(input.id)
    private fun configFileName(id: ID) = "$configFilePrefix$id.yml"

    private fun configFile(input: T) = configFile(input.id)
    private fun configFile(id: ID) = configFilesDir.resolve(configFileName(id))

    private val configFilesDir
        get() = configFilesDirectory ?: dataFolder.resolve(displayName.lowercase())

    private inline fun <reified R : Configurable<ID>> doSave(input: R): R {
        configFilesDir.mkdirsIfNotExists()

        objectMapper.writeValue(configFile(input.id), input)
        return doLoad(input.id)
    }

    private inline fun <reified R> doLoad(id: ID): R {
        configFilesDir.mkdirsIfNotExists()

        return objectMapper.readValue(configFile(id).toFile(), R::class.java)
    }

    //fun saveConfig(input: T): T = doSave(input)
    //fun loadConfig(id: ID): T = doLoad(id)

    fun initRepository() {
        configFilesDir.mkdirsIfNotExists()
    }
}


