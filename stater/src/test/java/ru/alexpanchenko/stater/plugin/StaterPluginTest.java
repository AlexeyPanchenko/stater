package ru.alexpanchenko.stater.plugin;

//public class StaterPluginTest {
//
//  @Test(expected = GradleException.class)
//  public void testApplyToNonAndroidProject() {
//    final Project project = ProjectBuilder.builder().build();
//    new StaterPlugin().apply(project);
//  }
//
//  @Test
//  public void testApplyToAndroidProject() {
//    Project project = ProjectBuilder.builder().build();
//    project.getPlugins().apply(AppPlugin.class);
//    StaterPlugin staterPlugin = new StaterPlugin();
//    staterPlugin.apply(project);
//
//    BaseExtension androidExtension = (BaseExtension) project.getExtensions().findByName("android");
//
//    assertNotNull(androidExtension);
//
//    Transform staterTransform = androidExtension.getTransforms().stream()
//      .filter(transform -> transform instanceof StaterTransform)
//      .findFirst()
//      .get();
//
//    assertNotNull(staterTransform);
//  }
//
//  @Test
//  public void testApplyToAndroidLibraryProject() {
//    Project project = ProjectBuilder.builder().build();
//    project.getPlugins().apply(LibraryPlugin.class);
//    StaterPlugin staterPlugin = new StaterPlugin();
//    staterPlugin.apply(project);
//
//    BaseExtension androidExtension = (BaseExtension) project.getExtensions().findByName("android");
//
//    assertNotNull(androidExtension);
//
//    Transform staterTransform = androidExtension.getTransforms().stream()
//      .filter(transform -> transform instanceof StaterTransform)
//      .findFirst()
//      .get();
//
//    assertNotNull(staterTransform);
//  }
//
//  @Test
//  public void testAddStaterDependency() {
//    Project project = ProjectBuilder.builder().build();
//    project.getPlugins().apply(LibraryPlugin.class);
//    StaterPlugin staterPlugin = new StaterPlugin();
//    staterPlugin.apply(project);
//
//    BaseExtension androidExtension = (BaseExtension) project.getExtensions().findByName("android");
//
//    assertNotNull(androidExtension);
//
//    Dependency staterDependency = project.getConfigurations().getByName("implementation").getAllDependencies().stream()
//      .filter(dependency -> {
//        System.out.println(dependency);
//        return dependency != null && dependency.getGroup().equals("ru.alexpanchenko") && dependency.getName().equals("stater");
//      })
//      .findFirst()
//      .get();
//
//    assertNotNull(staterDependency);
//  }
//}
