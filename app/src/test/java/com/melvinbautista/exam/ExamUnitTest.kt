package com.melvinbautista.exam

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.melvinbautista.exam.constants.States
import com.melvinbautista.exam.model.Authentication
import com.melvinbautista.exam.repository.UserRepository
import com.melvinbautista.exam.viewmodel.AuthenticationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ExamUnitTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val userRepository: UserRepository = mock()
    private lateinit var viewModel: AuthenticationViewModel

    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = AuthenticationViewModel(userRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `register success`() = runTest {
        whenever(userRepository.register("user", "pass")).thenReturn(true)

        val observer = Observer<Authentication?> {}
        viewModel.authentication.observeForever(observer)

        viewModel.register("user", "pass")
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.authentication.value
        assertEquals(States.Registered.SUCCESS, result?.auth)
        assertEquals("user", result?.username)

        viewModel.authentication.removeObserver(observer)
    }

    @Test
    fun `register failure`() = runTest {
        whenever(userRepository.register("user", "pass")).thenReturn(false)

        val observer = Observer<Authentication?> {}
        viewModel.authentication.observeForever(observer)

        viewModel.register("user", "pass")
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.authentication.value
        assertEquals(States.Registered.FAILED, result?.auth)

        viewModel.authentication.removeObserver(observer)
    }

    @Test
    fun `login success`() = runTest {
        whenever(userRepository.login("user", "pass")).thenReturn(true)

        val observer = Observer<Authentication?> {}
        viewModel.authentication.observeForever(observer)

        viewModel.login("user", "pass")
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.authentication.value
        assertEquals(States.LoggedIn.SUCCESS, result?.auth)

        viewModel.authentication.removeObserver(observer)
    }

    @Test
    fun `login failure`() = runTest {
        whenever(userRepository.login("user", "pass")).thenReturn(false)

        val observer = Observer<Authentication?> {}
        viewModel.authentication.observeForever(observer)

        viewModel.login("user", "pass")
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.authentication.value
        assertEquals(States.LoggedIn.FAILED, result?.auth)

        viewModel.authentication.removeObserver(observer)
    }

    @Test
    fun `logout`() {
        val observer = Observer<Authentication?> {}
        viewModel.authentication.observeForever(observer)

        viewModel.logout()

        val result = viewModel.authentication.value
        assertNull(result)

        viewModel.authentication.removeObserver(observer)
    }
}