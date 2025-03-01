package org.owasp.mastestapp

import android.content.Context
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

/**
 * Run MastgTest
 */
@RunWith(MockitoJUnitRunner::class)
class RunMastgTest {
    @Test
    fun runMastgTest() {
        val mockContext = mock<Context> {}
        val tested =  MastgTest(mockContext)

        val text = tested.mastgTest()

        println("MastgTestOutput: $text")
    }
}