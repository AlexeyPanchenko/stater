package ru.alexpanchenko.stater.plugin;

import com.android.build.api.transform.Transform;
import com.android.build.gradle.AppPlugin;
import com.android.build.gradle.BaseExtension;
import com.android.build.gradle.LibraryPlugin;

import org.gradle.api.GradleException;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Dependency;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@Ignore
public class StaterPluginTest {

  @Test(expected = GradleException.class)
  public void testApplyToNonAndroidProject() {
    final Project project = ProjectBuilder.builder().build();
    new StaterPlugin().apply(project);
  }

  @Test
  public void testApplyToAndroidProject() {
    Project project = ProjectBuilder.builder().build();
    project.getPlugins().apply(AppPlugin.class);
    StaterPlugin staterPlugin = new StaterPlugin();
    staterPlugin.apply(project);

    BaseExtension androidExtension = (BaseExtension) project.getExtensions().findByName("android");

    assertNotNull(androidExtension);

    Transform staterTransform = androidExtension.getTransforms().stream()
      .filter(transform -> transform instanceof StaterTransform)
      .findFirst()
      .get();

    assertNotNull(staterTransform);
  }

  @Test
  public void testApplyToAndroidLibraryProject() {
    Project project = ProjectBuilder.builder().build();
    project.getPlugins().apply(LibraryPlugin.class);
    StaterPlugin staterPlugin = new StaterPlugin();
    staterPlugin.apply(project);

    BaseExtension androidExtension = (BaseExtension) project.getExtensions().findByName("android");

    assertNotNull(androidExtension);

    Transform staterTransform = androidExtension.getTransforms().stream()
      .filter(transform -> transform instanceof StaterTransform)
      .findFirst()
      .get();

    assertNotNull(staterTransform);
  }

  @Test
  public void testAddStaterDependency() {
    Project project = ProjectBuilder.builder().build();
    project.getPlugins().apply(LibraryPlugin.class);
    StaterPlugin staterPlugin = new StaterPlugin();
    staterPlugin.apply(project);

    BaseExtension androidExtension = (BaseExtension) project.getExtensions().findByName("android");

    assertNotNull(androidExtension);

    Dependency staterDependency = project.getConfigurations().getByName("implementation").getAllDependencies().stream()
      .filter(dependency -> {
        return dependency != null && dependency.getGroup().equals("ru.alexpanchenko") && dependency.getName().equals("stater");
      })
      .findFirst()
      .get();

    assertNotNull(staterDependency);
  }


  @Test
  public void testNoAddSerializerDependency() {
    Project project = ProjectBuilder.builder().build();
    project.getPlugins().apply(LibraryPlugin.class);
    StaterPlugin staterPlugin = new StaterPlugin();
    staterPlugin.apply(project);

    StaterPluginExtension staterExtension = project.getExtensions().getByType(StaterPluginExtension.class);
    staterExtension.setCustomSerializerEnabled(false);

    assertNotNull(staterExtension);

    Optional<Dependency> serializerDependencyOption = project.getConfigurations().getByName("implementation").getAllDependencies().stream()
      .filter(dependency -> {
        return dependency != null && dependency.getGroup().equals("ru.alexpanchenko") && dependency.getName().equals("serializer");
      })
      .findFirst();

    assertFalse(serializerDependencyOption.isPresent());
  }

  // todo: uncomment after serializer deploy
  //@Test
  //public void testAddSerializerDependency() {
  //  Project project = ProjectBuilder.builder().build();
  //  project.getPlugins().apply(LibraryPlugin.class);
  //  StaterPlugin staterPlugin = new StaterPlugin();
  //  staterPlugin.apply(project);
  //
  //  StaterPluginExtension staterExtension = project.getExtensions().getByType(StaterPluginExtension.class);
  //  staterExtension.setCustomSerializerEnabled(true);
  //
  //  assertNotNull(staterExtension);
  //
  //  Dependency serializerDependencyOption = project.getConfigurations().getByName("implementation").getAllDependencies().stream()
  //    .filter(dependency -> {
  //      return dependency != null && dependency.getGroup().equals("ru.alexpanchenko") && dependency.getName().equals("serializer");
  //    })
  //    .findFirst()
  //    .get();
  //
  //  assertNotNull(serializerDependencyOption);
  //}
}
