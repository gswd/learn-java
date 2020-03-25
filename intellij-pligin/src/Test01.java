import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiClass;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;

public class Test01 extends AnAction {
	protected static final Logger log = Logger.getInstance(Test01.class);

	@Override
	public void actionPerformed(AnActionEvent e) {
//		PsiDocumentManager.getInstance().getDocument();

	}
//	@Override
//	public void actionPerformed(AnActionEvent e) {
//		log.info("------- > actionPerformed ....");
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


//		Project project = e.getData(PlatformDataKeys.PROJECT);
//		String txt= Messages.showInputDialog(project, "What is your name?", "Input your name", Messages.getQuestionIcon());
//		Messages.showMessageDialog(project, "Hello, " + txt + "!\n I am glad to see you.", "Information", Messages.getInformationIcon());
//	}


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
