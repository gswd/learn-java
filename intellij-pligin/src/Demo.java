import java.awt.*;

import javax.swing.*;

public class Demo {
	private JPanel panel1;
	private JButton button1;

	{
		// GUI initializer generated by IntelliJ IDEA GUI Designer
		// >>> IMPORTANT!! <<<
		// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/** Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		button1 = new JButton();
		Font button1Font = this.$$$getFont$$$("Courier New", -1, -1, button1.getFont());
		if (button1Font != null)
			button1.setFont(button1Font);
		button1.setHideActionText(false);
		button1.setText("Button");
		panel1.add(button1);
	}

	/** @noinspection ALL */
	private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
		if (currentFont == null)
			return null;
		String resultName;
		if (fontName == null) {
			resultName = currentFont.getName();
		} else {
			Font testFont = new Font(fontName, Font.PLAIN, 10);
			if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
				resultName = fontName;
			} else {
				resultName = currentFont.getName();
			}
		}
		return new Font(resultName, style >= 0 ? style : currentFont.getStyle(),
			size >= 0 ? size : currentFont.getSize());
	}

	/** @noinspection ALL */
	public JComponent $$$getRootComponent$$$() {
		return panel1;
	}
}
