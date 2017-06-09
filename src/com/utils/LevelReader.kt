package com.utils

import java.io.File

object LevelReader {

    val levelMap = mutableListOf<List<String>>()

    fun turnFileIntoPoints(pathToFile: String): List<List<String>> {
        val lines = File(pathToFile).readLines()
        var rowNum = 0
        lines.forEach { line ->
            levelMap.add(rowNum, line.map(Char::toString))
            rowNum += 1
        }
        return levelMap
    }

    fun findAreaPlayerCanMoveTo(character: String) {
        //TODO Recursive function that "fills" like paint might

    }

    fun findPlayerCoordinate(color: String): Pair<Int, Int> {
        levelMap.forEach { row ->
            if (row.contains(color)) {
                print("found $color at ${row.indexOf(color)}, ${levelMap.indexOf(row)}")
                return Pair(row.indexOf(color), levelMap.indexOf(row))
            }
        }
        return Pair(-1, -1)
    }

    fun playerCanMoveToSpot(x: Int, y: Int, color: String): Boolean {
        if (isInMap(x, y)) {
            return when (color) {
                PLAYER_TILE_MAPPINGS.GREEN.color  -> when (levelMap[x][y]) {
                    " ", "g", "." -> true
                    else          -> false
                }
                PLAYER_TILE_MAPPINGS.BLUE.color   -> when (levelMap[x][y]) {
                    " ", "b", "*" -> true
                    else          -> false
                }
                PLAYER_TILE_MAPPINGS.RED.color    -> when (levelMap[x][y]) {
                    " ", "r", "+" -> true
                    else          -> false
                }
                PLAYER_TILE_MAPPINGS.YELLOW.color -> when (levelMap[x][y]) {
                    " ", "y", "-" -> true
                    else          -> false
                }
                else                              -> false

            }
        } else return false
    }

    fun isInMap(x: Int, y: Int): Boolean {
        try {
            levelMap[x][y]
        } catch (e: IndexOutOfBoundsException) {
            return false
        }
        return true
    }
}

enum class PLAYER_TILE_MAPPINGS(
        val color: String, val goal: String, val door: String) {
    GREEN("G", ".", "g"),
    BLUE("B", "*", "b"),
    RED("R", "+", "r"),
    YELLOW("Y", "-", "y")
}
