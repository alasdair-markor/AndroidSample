package com.markor.contactslist.csvReader

import android.content.Context
import java.io.BufferedReader
import java.io.StringReader

interface CSVReader {
    fun readNextLine(): String?
}

class CSVReaderImpl(context: Context) : CSVReader {
    private val bufferedReader: BufferedReader

    init {
        val lines = readAsset(context, "contacts.csv")
        bufferedReader = BufferedReader(StringReader(lines))
    }

    override fun readNextLine(): String? {

        val line = bufferedReader.readLine()
        if (line == null) {
            bufferedReader.close()
        }

        return line
    }

    private fun readAsset(context: Context, fileName: String): String =
        context
            .assets
            .open(fileName)
            .bufferedReader()
            .use(BufferedReader::readText)
}