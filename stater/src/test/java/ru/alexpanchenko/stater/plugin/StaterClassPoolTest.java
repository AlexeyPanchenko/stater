package ru.alexpanchenko.stater.plugin;

import com.android.build.gradle.BaseExtension;

import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionContainer;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javassist.CtClass;
import javassist.NotFoundException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StaterClassPoolTest {

  private static final String A_CLASS = "ru/alexpanchenko/stater/plugin/utils/models/A.class";
  private static final String A_PACKAGE = A_CLASS.replace(".class", "").replace("/", ".");
  private static final String B_CLASS = "ru/alexpanchenko/stater/plugin/utils/models/B.class";
  private static final String B_PACKAGE = B_CLASS.replace(".class", "").replace("/", ".");

  @Test
  public void testAppendClasses() throws NotFoundException {
    Project project = mock(Project.class);
    ExtensionContainer extensionContainer = mock(ExtensionContainer.class);
    BaseExtension baseExtension = mock(BaseExtension.class);
    when(project.getExtensions()).thenReturn(extensionContainer);
    when(extensionContainer.findByType(BaseExtension.class)).thenReturn(baseExtension);
    ArrayList<File> files = new ArrayList<>();
    files.add(new File(A_CLASS));
    when(baseExtension.getBootClasspath()).thenReturn(files);

    StaterClassPool classPool = new StaterClassPool(
      project,
      Collections.emptyList(),
      Collections.emptyList()
    );
    CtClass aClass = classPool.get(A_PACKAGE);
    CtClass bClass = classPool.get(B_PACKAGE);

    assertNotNull(aClass);
    assertNotNull(bClass);
  }

  @Test(expected = RuntimeException.class)
  public void testClearClassPaths() throws NotFoundException, IOException {
    Project project = mock(Project.class);
    ExtensionContainer extensionContainer = mock(ExtensionContainer.class);
    BaseExtension baseExtension = mock(BaseExtension.class);
    when(project.getExtensions()).thenReturn(extensionContainer);
    when(extensionContainer.findByType(BaseExtension.class)).thenReturn(baseExtension);
    ArrayList<File> files = new ArrayList<>();
    files.add(new File(A_CLASS));
    when(baseExtension.getBootClasspath()).thenReturn(files);

    StaterClassPool classPool = new StaterClassPool(
      project,
      Collections.emptyList(),
      Collections.emptyList()
    );
    CtClass aClass = classPool.get(A_PACKAGE);
    CtClass bClass = classPool.get(B_PACKAGE);
    assertNotNull(aClass);
    assertNotNull(bClass);

    classPool.close();

    assertNull(classPool.get(A_PACKAGE));
    assertNull(classPool.get(B_PACKAGE));
  }
}
