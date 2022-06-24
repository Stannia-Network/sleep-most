package me.mrgeneralq.sleepmost.extensions

import com.fasterxml.jackson.databind.ObjectMapper
import me.mrgeneralq.sleepmost.Sleepmost
import java.io.File
import java.nio.file.Path
import java.util.logging.Logger

val sleepmost: Sleepmost get() = Sleepmost.getInstance()
val dataFolder: Path = sleepmost.dataFolder.toPath()

val logger: Logger get() = sleepmost.logger

fun Path.mkdirsIfNotExists() = with(if (toFile().isFile) toFile().parentFile else toFile()) {
    if (!mkdirs()) logger.severe("Failed to create directory tree at: $absolutePath")
}

fun <T> ObjectMapper.writeValue(configFile: Path, input: T) = writeValue(configFile.toFile(), input)