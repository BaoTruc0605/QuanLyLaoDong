package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import connectDB.ConnectDB;

public class XuLyChuyenTrang {
	public static void openJFrame(JFrame jFrame, JFrame newJFrame) {
		newJFrame.setVisible(true);
		jFrame.setVisible(false);
		jFrame.dispose();
	}

	public static void thoat() {
		int res = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát không",
				"Thoát", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (res == JOptionPane.OK_OPTION) {
			ConnectDB.getInstance().disconnect();
			System.out.println("Thành công");
			System.exit(0);
		}
	}

	public static ImageIcon convertImageToIcon(String pathname, int width, int height) {
		ImageIcon icon = null;
		try {
			BufferedImage image = ImageIO.read(new File(pathname));

			icon = new ImageIcon(image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi hình ảnh");
		}
		return icon;
	}
}
