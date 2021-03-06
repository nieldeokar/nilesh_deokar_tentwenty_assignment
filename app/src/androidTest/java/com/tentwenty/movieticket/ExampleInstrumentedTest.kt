package com.tentwenty.movieticket

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.google.gson.GsonBuilder
import com.tentwenty.movieticket.feature.shared.model.TheaterLayout
import com.tentwenty.movieticket.utils.util.AESEncyption
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    companion object {
        private const val JSON_STRING = "[\n" +
                "  {\n" +
                "    \"row\": \"A\",\n" +
                "    \"values\": [1,1,0,0,1,1]\n" +
                "  },\n" +
                "  {\n" +
                "    \"row\": \"B\",\n" +
                "    \"values\": [1,1,0,0,1,1]\n" +
                "  },\n" +
                "  {\n" +
                "    \"row\": \"C\",\n" +
                "    \"values\": [1,1,0,0,1,1]\n" +
                "  },\n" +
                "  {\n" +
                "    \"row\": \"D\",\n" +
                "    \"values\": [1,1,1,1,1,1]\n" +
                "  }\n" +
                "]"
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.tentwenty.movieticket", appContext.packageName)
    }

    @Test
    fun testGsonDeserializer() {

        val gson = GsonBuilder().create()

        val theaterRows = gson.fromJson(JSON_STRING, Array<TheaterLayout>::class.java)

        theaterRows.forEach {
            print("Row details: " + it.rowName +" +"+it.values[0])
        }

    }


    @Test
    fun test_AESEncryption() {
        val originalData = "ORIGIANALA"
        val seed = "thisIs EED"

        var encrypted = AESEncyption.encrypt(originalData,seed)

        assertEquals(originalData, AESEncyption.decrypt(encrypted,seed))

    }
}
