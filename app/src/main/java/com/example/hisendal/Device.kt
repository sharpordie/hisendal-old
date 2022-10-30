package com.example.hisendal

import android.content.Context
import dadb.AdbKeyPair
import dadb.AdbShellResponse
import dadb.Dadb
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.File

class Device(
    private val address: String,
    private val context: Context,
) {
    private lateinit var manager: Dadb

    suspend fun attach(timeout: Int = 2000) = withContext(IO) {
        var handler: Dadb? = null
        // val correct = InetAddress.getByName(address).isReachable(timeout)
        // if (!correct) throw Exception("The address is unreachable")
        val running = GlobalScope.async { handler = Dadb.discover(address, keygen(refresh = false)) }
        Thread.sleep(timeout.toLong())
        running.cancel()
        if (handler == null) throw Exception("The authentication is required, authorize and retry")
        manager = handler as Dadb
    }

    suspend fun detach() = withContext(IO) {
        manager.close()
    }

    suspend fun invoke(command: String): AdbShellResponse = withContext(IO) {
        manager.shell(command)
    }

    suspend fun keygen(refresh: Boolean = false): AdbKeyPair = withContext(IO) {
        val tempdir = context.cacheDir
        val pvtFile = File(tempdir, "adbkey")
        val pubFile = File(tempdir, "adbkey.pub")
        if (refresh || !pubFile.exists() || !pvtFile.exists()) {
            if (pubFile.exists()) pubFile.delete()
            if (pvtFile.exists()) pvtFile.delete()
            AdbKeyPair.generate(pvtFile, pubFile)
        }
        AdbKeyPair.read(pvtFile, pubFile)
    }
}