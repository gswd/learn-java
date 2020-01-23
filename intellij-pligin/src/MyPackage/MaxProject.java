package MyPackage;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Chursin
 * Date: Aug 13, 2010
 * Time: 3:50:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class MaxProject implements ProjectComponent {
  protected static final Logger log = Logger.getInstance(MaxProject.class);
  public MaxProject(Project project) {
    log.info("----> construct MaxProject - " + project.getProjectFile());
  }

  public void initComponent() {
    log.info("----> initComponent - ");
    // TODO: insert component initialization logic here
  }

  public void disposeComponent() {
    log.info("----> disposeComponent - ");
    // TODO: insert component disposal logic here
  }

  @NotNull
  public String getComponentName() {
    log.info("----> getComponentName << ");
    return "MaxProject";
  }

  public void projectOpened() {
    log.info("----> projectOpened!! ");
    // called when project is opened
    MyCounter CommandCounter = ServiceManager.getService(MyCounter.class);

    if (CommandCounter.IncreaseCounter() == -1) {
      Messages.showMessageDialog(
          "The maximum number of opened projects exceeds " + String.valueOf(CommandCounter.MaxCount) +
              " projects!", "Error", Messages.getErrorIcon());
      ProjectManager PM = ProjectManager.getInstance();
      Project[] AllProjects = PM.getOpenProjects();
      Project project = AllProjects[AllProjects.length - 1];
      PM.closeProject(project);
    }
  }


  public void projectClosed() {
    log.info("----> projectClosed!! ");
    // called when project is being closed
    MyCounter CommandCounter = ServiceManager.getService(MyCounter.class);
    CommandCounter.DecreaseCounter();
  }
}
