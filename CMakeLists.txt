cmake_minimum_required(VERSION 3.8)

set(CMAKE_MODULE_PATH ${CMAKE_CURRENT_LIST_DIR}/KotlinCMakeModule)

project(tsp Kotlin)

set(
        SOURCE_FILES
        src/Graph.kt
        src/Greedy.kt
        src/main.kt
        src/Node.kt
        src/parseFile.kt
)

set(CMAKE_Kotlin_FLAGS_DEBUG "-g")

set(CMAKE_Kotlin_FLAGS_RELEASE "-opt")

konanc_executable(
        NAME tsp
        SOURCES ${SOURCE_FILES}
)