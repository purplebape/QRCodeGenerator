package generator;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

// Telegram: @senior_javist
// YouTube: @senior_javist

public class QRCodeGenerator {
    public static void main(String[] args) {
        String url = "https://youtube.com";

        int width = 300;
        int height = 300;
        String format = "png";
        String filePath = "qrcode.png";

        // Настройка параметров генерации QR-кода
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        // Создание объекта QR-кода
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height, hints);

            BufferedImage qrCodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            qrCodeImage.createGraphics();

            // Рендеринг QR-кода в изображение
            Graphics2D graphics = (Graphics2D) qrCodeImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (bitMatrix.get(x, y)) {
                        graphics.fillRect(x, y, 1, 1);
                    }
                }
            }

            // Сохранение QR-кода в файл
            File qrCodeFile = new File(filePath);
            ImageIO.write(qrCodeImage, format, qrCodeFile);

            System.out.println("QR-код успешно сгенерирован и сохранен");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
