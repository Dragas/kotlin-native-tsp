import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.*

fun parseFile(fileName: String): Graph {
    val file = fopen(fileName, "r")
    if (file == null) {
        perror("cannot open input file $fileName")
        exit(1)
    }

    val graph = Graph()

    try {
        memScoped {
            fseek(file, 0, SEEK_END)
            val bufferLength = ftell(file) + 1
            fseek(file, 0, SEEK_SET)
            val buffer = allocArray<ByteVar>(bufferLength)
            if (fread(buffer, 1, bufferLength, file) <= 0) {
                println("Error while reading")
                exit(1)
            }

            val fileContent = buffer.toKString().split("\n")
            fileContent.filter(String::isNotBlank).forEachIndexed { lineNumber, line ->
                addNeighbors(line, graph, lineNumber)
            }

            println("Parsing ended")
        }
    } finally {
        fclose(file)
    }

    return graph
}