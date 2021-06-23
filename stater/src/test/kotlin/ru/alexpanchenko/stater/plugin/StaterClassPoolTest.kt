package ru.alexpanchenko.stater.plugin

import com.android.build.gradle.BaseExtension
import javassist.NotFoundException
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.File
import java.io.IOException
import java.util.*
import org.mockito.Mockito.`when` as whenever

class StaterClassPoolTest {

  companion object {
    private const val A_CLASS = "ru/alexpanchenko/stater/plugin/utils/A.class"
    private const val B_CLASS = "ru/alexpanchenko/stater/plugin/utils/B.class"
    private val A_PACKAGE = A_CLASS.replace(".class", "").replace("/", ".")
    private val B_PACKAGE = B_CLASS.replace(".class", "").replace("/", ".")
  }

  @Test
  @Throws(NotFoundException::class)
  fun testAppendClasses() {
    val project = mock(Project::class.java)
    val extensionContainer = mock(ExtensionContainer::class.java)
    val baseExtension = mock(BaseExtension::class.java)
    whenever(project.extensions).thenReturn(extensionContainer)
    whenever(extensionContainer.findByType(BaseExtension::class.java)).thenReturn(baseExtension)
    val files = ArrayList<File>()
    files.add(File(A_CLASS))
    whenever(baseExtension.bootClasspath).thenReturn(files)
    val classPool = StaterClassPool(project, emptyList(), emptyList())
    val aClass = classPool[A_PACKAGE]
    val bClass = classPool[B_PACKAGE]
    assertNotNull(aClass)
    assertNotNull(bClass)
  }

  @Test(expected = RuntimeException::class)
  @Throws(NotFoundException::class, IOException::class)
  fun testClearClassPaths() {
    val project = mock(Project::class.java)
    val extensionContainer = mock(ExtensionContainer::class.java)
    val baseExtension = mock(BaseExtension::class.java)
    whenever(project.extensions).thenReturn(extensionContainer)
    whenever(extensionContainer.findByType(BaseExtension::class.java)).thenReturn(baseExtension)
    val files = ArrayList<File>()
    files.add(File(A_CLASS))
    whenever(baseExtension.bootClasspath).thenReturn(files)
    val classPool = StaterClassPool(
      project, emptyList(), emptyList())
    val aClass = classPool[A_PACKAGE]
    val bClass = classPool[B_PACKAGE]
    assertNotNull(aClass)
    assertNotNull(bClass)
    classPool.close()
    assertNull(classPool[A_PACKAGE])
    assertNull(classPool[B_PACKAGE])
  }
}