import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.JavaRecursiveElementVisitor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;

public class Test01 extends AnAction {
	protected static final Logger log = Logger.getInstance(Test01.class);
	@Override
	public void actionPerformed(AnActionEvent e) {
		log.info("------- > actionPerformed ....");
//		Application application = ApplicationManager.getApplication();
//		MyComponent myComponent = application.getComponent(MyComponent.class);
//		myComponent.sayHello();

//		//获取当前在操作的工程上下文
//		Project project = e.getData(PlatformDataKeys.PROJECT);
//		//获取当前操作的类文件
//		PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
//
////		//获取当前类文件的路径
////		String classPath = psiFile.getVirtualFile().getPath();
//		String title = "Hello World!";
//
//		//显示对话框
//		Messages.showMessageDialog(project, "123asdf", title, Messages.getInformationIcon());

//		System.out.println("---------testEditor----------");
//		testEditor(e);

		System.out.println("---------testPSI----------");
		testPSI(e);

		Project project = e.getData(PlatformDataKeys.PROJECT);
		String txt= Messages.showInputDialog(project, "What is your name?", "Input your name", Messages.getQuestionIcon());
		Messages.showMessageDialog(project, "Hello, " + txt + "!\n I am glad to see you.", "Information", Messages.getInformationIcon());
	}

	private void testPSI(AnActionEvent anActionEvent) {
		Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
		PsiFile psiFile = anActionEvent.getData(CommonDataKeys.PSI_FILE);
		if (editor == null ||psiFile == null) {

			System.out.println("-----psiFile ---> " + psiFile);
			System.out.println("-----editor ---> " + editor);
			return;
		}
		int offset = editor.getCaretModel().getOffset();
		final StringBuilder infoBuilder = new StringBuilder();
		PsiElement element = psiFile.findElementAt(offset);
		infoBuilder.append("Element at caret: ").append(element).append("\n");
		if (element != null) {
			PsiMethod containingMethod = PsiTreeUtil.getParentOfType(element, PsiMethod.class);
			infoBuilder
				.append("Containing method: ")
				.append(containingMethod != null ? containingMethod.getName() : "none")
				.append("\n");
			if (containingMethod != null) {
				PsiClass containingClass = containingMethod.getContainingClass();
				infoBuilder
					.append("Containing class: ")
					.append(containingClass != null ? containingClass.getName() : "none")
					.append("\n");

				infoBuilder.append("Local variables:\n");
				containingMethod.accept(new JavaRecursiveElementVisitor() {
					@Override
					public void visitLocalVariable(PsiLocalVariable variable) {
						super.visitLocalVariable(variable);
						infoBuilder.append(variable.getName()).append("\n");
					}
				});
			}
		}
		System.out.println(infoBuilder.toString());
		Messages.showMessageDialog(anActionEvent.getProject(), infoBuilder.toString(), "PSI Info", null);


	}

	private void testEditor(AnActionEvent anActionEvent) {
		Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
		System.out.println("---> editor  : " + editor);
		if (editor != null) {
			Document document = editor.getDocument();
			int lineCount = document.getLineCount();
			System.out.println("---> lineCount   :  " + lineCount);
			log.info("---> lineCount" + lineCount);

			String text = document.getText();
			System.out.println(text);

		}
	}
}
